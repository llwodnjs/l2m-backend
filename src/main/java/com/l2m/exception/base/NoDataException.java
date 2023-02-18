package com.l2m.exception.base;

import com.l2m.exception.enums.base.ExceptionTypeCode;

/**
 * 찾는 데이터가 not available 하거나
 * 물리적으로 존재하지 않는 경우에 대한 예외 클래스
 *
 * @author miri
 * @version 2022-10-25 모델링 시작
 */
public class NoDataException extends PandasonException {

  /**
   * 글로벌 예외 생성자
   * @param message
   */
  public NoDataException(String message) {
    super(ExceptionTypeCode.NODATA.getCode(), message);
  }

  /**
   * 특화 도메인 예외 생성자
   */
  public NoDataException(
    String domainCode,
    String domainDetailCode,
    String message
  ) {
    super(
      ExceptionTypeCode.NODATA.getCode(),
      domainCode,
      domainDetailCode,
      message
    );
  }
}
