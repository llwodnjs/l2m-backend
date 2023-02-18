package com.l2m.exception.enums.base;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 예외 소분류 글로벌 코드.
 * 각 도메인에 특화 세부코드/메시지는 XXXBizExceptionDetailCode Enum 을 별도 정의한다.
 * 
 * 각 도메인에 대한 코드, 메시지는 별도 ENUM 으로 정의하여 사용.
 *
 * @author miri
 * @version 2022-10-25 예외 타입 소분류 기본 정의
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum ExceptionDetailCode {
  SUCCESS_DEFAULT("000", "비즈니스가 정상적으로 완료되었습니다.", "비즈니스 성공"),
  STATUS_DEFAULT("000", "부적절한 비즈니스 상태입니다.", "특정 비즈니스 정책에 대한 validate 실패 및 예외적 상황"),
  NODATA_DEFAULT("000", "데이터가 존재하지 않습니다.", "데이터 없음 예외"),
  FILE_DEFAULT("000", "파일 관련 오류가 발생했습니다.", "파일 관련 예외"),
  PARAM_DEFAULT("000", "입력된 파라미터가 부적절합니다.", "파라미터 정합성 관련 예외");

  private String code;
  private String message;
  private String description;
}
