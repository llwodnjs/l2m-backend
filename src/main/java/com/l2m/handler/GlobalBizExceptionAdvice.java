package com.l2m.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.l2m.model.ResponseBodyModel;

/**
 * exception -> dto 전환용 핸들러.
 * 모든 @RestController 가 그 대상이 된다.
 *
 * [고려사항]
 * TODO 2022-10-25 (구현필요) 로깅 등을 위해서라도 각 타입별 핸들러 처리 필요함
 *
 * @author miri
 * @version 2022-10-25
 * @version 2023-02-01 성공/예외 응답객체 통일
 *
 */
@Order(Ordered.LOWEST_PRECEDENCE) // global 이므로 최하위 우선순위
@RestControllerAdvice(annotations = RestController.class)
public class GlobalBizExceptionAdvice {

  /**
   * 모든 예외의 경우
   * @param e
   * @return
   */
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ResponseBodyModel.exception<?>> handle(Exception e) {
    return 
      ResponseEntity
        .status(HttpStatus.OK)
        .body(new ResponseBodyModel.exception<>(e));
  }
}
