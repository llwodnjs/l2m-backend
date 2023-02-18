package com.l2m.exception.enums.base;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 예외 대분류에 대한 글로벌 코드 정의.
 * 각 도메인에 특화 코드는 E1 + XXXBizExceptionDetailCode Enum 을 조합해서 표기한다.
 *
 * @author miri
 * @version 2022-10-25 예외 타입 대분류 기본 정의
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum ExceptionTypeCode {
  SUCCESS("E0", "비즈니스가 정상적으로 완료되었습니다.", "비즈니스 성공"),
  STATUS("E1", "부적절한 비즈니스 상태입니다.", "특정 비즈니스 정책에 대한 validate 실패 및 예외적 상황"),
  NODATA("E2", "데이터가 삭제되었거나 존재하지 않습니다.", "데이터 없음 예외"),
  FILE("E3", "파일 관련 오류가 발생했습니다.", "파일 관련 예외"),
  PARAM("E4", "입력된 파라미터가 부적절합니다.", "파라미터 정합성 관련 예외"),
  CUV_API("E5", "CUV <-> I/F MODULE 비즈니스 중 예외가 발생했습니다.", "CUV <-> I/F MODULE 비즈니스 관련 예외")
  ;

  private String code;
  private String message;
  private String description;
}
