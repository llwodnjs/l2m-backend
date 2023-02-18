package com.l2m.exception.base;

import com.l2m.exception.enums.base.ExceptionTypeCode;

/**
 * 컨트롤러 파라미터 관련 exception
 * 
 * @author miri
 * @version 2022-02-01
 */
public class ParamException extends PandasonException {
    /**
    * all args for 글로벌 예외
    */
  public ParamException(String message) {
    super(ExceptionTypeCode.PARAM.getCode(), message);
  }
}
