package com.l2m.exception.base;

import com.l2m.domain.base.enums.DomainPrefix;
import com.l2m.exception.enums.base.ExceptionTypeCode;

/**
 * 판다이슨 프레임워크에서 사용할 예외 클래스 틀.
 *
 * [고려사항]
 * TODO 2022-10-15 (구현필요) 최상위 Exceptions 타입을 고려한 모델링 필요
 *
 * @author miri
 * @version 2022-10-25 개발자 정의 예외코드를 사용하기 위한 단순 틀
 */
public class PandasonException extends RuntimeException {

  /**
   * [response 에 반환할 예외코드]
   * 1. typeCode: 예외 대분류 2글자
   *    ExceptionTypeCode 에 대분류 예외를 정의하여 사용
   *
   * 2. domainCode: 도메인 3글자
   *    DomainPrefix 에서 도메인을 선택하여 사용
   *
   * 3. detailCode: 예외 소분류 3글자
   *    DomainDetailCode 을 Domain 별로 새로 정의하여 사용
   */
  protected String typeCode = ExceptionTypeCode.SUCCESS.getCode();
  protected String domainCode = DomainPrefix.GLOBAL.getCode();
  protected String domainDetailCode = "000";

  /**
   * all args 생성자 for 도메인 특화 예외
   */
  public PandasonException(
    String typeCode,
    String domainCode,
    String domainDetailCode,
    String message
  ) {
    super(message);
    this.typeCode = typeCode;
    this.domainCode = domainCode;
    this.domainDetailCode = domainDetailCode;
  }

  /**
   * all args for 글로벌 예외
   */
  public PandasonException(String typeCode, String message) {
    super(message);
    this.typeCode = typeCode;
  }

  /**
   * 예외 메시지로 반환할 bizStatus 코드값.
   * @return typeCode + domainCode + domainDetailCode 를 결합한 문자열
   */
  public String getBizStatus() {
    final StringBuilder builder = new StringBuilder();
    builder.append(this.typeCode);
    builder.append(this.domainCode);
    builder.append(this.domainDetailCode);
    return builder.toString();
  }
}
