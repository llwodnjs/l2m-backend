package com.l2m.util.global;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * http request 관련 util 모음
 */
public class HttpRequestUtil {

  // HttpServletRequest 가져오기
  public static HttpServletRequest getRequest() {
    return (
      (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()
    ).getRequest();
  }
}
