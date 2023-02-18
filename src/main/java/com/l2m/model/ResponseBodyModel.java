package com.l2m.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.l2m.domain.base.enums.DomainPrefix;
import com.l2m.exception.base.PandasonException;
import com.l2m.exception.enums.base.ExceptionDetailCode;
import com.l2m.exception.enums.base.ExceptionTypeCode;
import com.querydsl.core.QueryResults;

import lombok.Getter;
import lombok.Setter;

/**
 * PandasonException 제외한
 * 비즈니스 성공 시 반환 데이터의 구조틀.
 *
 * [고려사항]
 * TODO (구현완료 20211221/20211221) '빈 results' 값 정의 필요
 * TODo (구현필요 20230307/x) failCnt/successCnt: 현재 틀만 정의. 각 컨트롤러에서 어떻게 취합하여 줄지 구현필요.
 * 
 * @author miri
 * @version 2023-02-01 성공/실패 시 응답 객체 통일
 * @version 2023-02-07 failCnt/successCnt 컬럼 추가
 */
public class ResponseBodyModel {

  /**
   * [내부용] 기본 틀
   *
   * 1. bizStatusCode, bizStatusMessage
   * 비즈니스 상태에 대한 코드와 메시지.
   *
   * 2. empty, limit, offset, total:
   * 페이징 정보.
   * 페이징이 아닐 경우 limit, offset 은 0, total 은 객체 size, empty 는 size 및 Null 여부.
   *
   * 3. results
   * 실제 데이터가 담기는 그릇.
   * 데이터가 정말 없으면 null 이고 total = 0, empty = true.
   * 
   * 4. timestamp
   * 응답 반환 시점의 타임스탬프.
   * 
   * 5. failCnt/successCnt
   * 각 응답을 취합하여 실패개수/성공개수
   * 
   * 6. source
   * 반영 시스템명 (CUV...)
   */
  @Getter
  @Setter
  public abstract static class basic {

    protected String bizStatusCode = 
      new StringBuilder(ExceptionTypeCode.SUCCESS.getCode())
        .append(DomainPrefix.GLOBAL.getCode())
        .append(ExceptionDetailCode.SUCCESS_DEFAULT.getCode())
        .toString();
    protected String bizStatusMessage = ExceptionDetailCode.SUCCESS_DEFAULT.getMessage();
    protected boolean empty;
    protected Long limit;
    protected Long offset;
    protected Long total;
    protected LocalDateTime timestamp;

    protected Long failCnt;
    protected Long successCnt;
    protected String source;

    // Long 값을 받아, null 이면 기본값 OL 아니면 기존 값 그대로 반환
    private Long getLongOrZero(Long target) {
      return Optional.ofNullable(target).orElseGet(() -> 0L);
    }

    /**
     * QueryResults 타입 결과물을 응답객체로 치환
     * @param data
     */
    public basic(QueryResults<?> data) {
      this.empty = data.isEmpty();
      this.limit = getLongOrZero(data.getLimit());
      this.offset = getLongOrZero(data.getOffset());
      this.total = getLongOrZero(data.getTotal());
      this.timestamp = LocalDateTime.now();
    }

    /**
     * Collection 타입 결과물을 응답객체로 치환
     * @param data
     */
    public basic(Collection<?> data) {
      this.empty = data.isEmpty();
      this.limit = 0L;
      this.offset = 0L;
      this.total = Long.valueOf(data.size());
      this.failCnt = 0L;
      this.successCnt = 0L;
      this.timestamp = LocalDateTime.now();
    }

    /**
     * 일반 객체 타입 결과물을 응답객체로 치환
     * @param data
     */
    public basic(Object data) {
      final Optional<Object> dataOptional = Optional.ofNullable(data);
      this.limit = 0L;
      this.offset = 0L;
      this.empty = !dataOptional.isPresent();
      this.total = this.empty ? 0L : 1L;
      this.failCnt = 0L;
      this.successCnt = 0L;
      this.timestamp = LocalDateTime.now();
    }

    /**
     * 예외를 응답 객체로 치환
     * @param exception
     */
    public basic(Exception exception){
      this.bizStatusMessage = exception.getMessage();
      this.bizStatusCode =
        exception instanceof PandasonException
        ? ((PandasonException) exception).getBizStatus()
        : null;
      this.empty = true;
      this.limit = 0L;
      this.offset = 0L;
      this.total = 0L;
      this.failCnt = 0L;
      this.successCnt = 0L;
      this.timestamp = LocalDateTime.now();
    }
  }

  /**
   * 예외 데이터 반환용
   */
  @Getter
  public static class exception<T extends Exception> extends ResponseBodyModel.basic {

    public exception(T data) {
      super(data);
      this.results = null;
    }

    private Object results;
  }

  /**
   * 단건 데이터 반환용
   */
  @Getter
  public static class object<T> extends ResponseBodyModel.basic {

    public object(T data) {
      super(data);
      this.results = data;
    }

    private T results;
  }

  /**
   * 다건 데이터 반환용
   * Collection 중에서도 QueryResults, List 를 가장 기본값으로 둠.
   */
  @Getter
  public static class collection<T> extends ResponseBodyModel.basic {

    public collection(QueryResults<T> data) {
      super(data);
      this.results = data.getResults();
    }

    public collection(Collection<T> data) {
      super(data);
      this.results = new ArrayList<>(data);
    }

    private List<T> results = new ArrayList<>();
  }
}
