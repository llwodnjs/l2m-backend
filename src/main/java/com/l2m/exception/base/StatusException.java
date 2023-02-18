package com.l2m.exception.base;

import com.l2m.exception.enums.base.ExceptionTypeCode;

/**
 * 비즈니스 상태 관련 exception
 *
 * @author miri
 * @version 2022-10-25 모델링 시작
 */
public class StatusException extends PandasonException {

  /**
   * all args for 도메인 특화 예외
   */
  public StatusException(
    String domainCode,
    String domainDetailCode,
    String message
  ) {
    super(
      ExceptionTypeCode.STATUS.getCode(),
      domainCode,
      domainDetailCode,
      message
    );
  }

  /**
   * all args for 글로벌 예외
   */
  public StatusException(String message) {
    super(ExceptionTypeCode.STATUS.getCode(), message);
  }
}
