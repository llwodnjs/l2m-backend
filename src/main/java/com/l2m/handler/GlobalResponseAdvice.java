package com.l2m.handler;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.l2m.handler.enums.GlobalResponsePolicyEnum;
import com.l2m.model.ResponseBodyModel;

/**
 * Exception 제외한
 * 성공한 단건/다건/Void(Null) 반환 객체를 제어하기 위한 advisor
 *
 * [고려사항]
 * TODO 2022-10-25 (구현필요) 스웨거 의존성 사용 시 string 메시지 컨버터가 충돌하여 String 반환 불가, 리팩토링 필요
 */
@RestControllerAdvice(
  basePackages = {
    "com.l2m.api",
  }
)
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {

  // 별도로 매핑안하고 그대로 내보내고 싶은 애들
  private final List<Class<?>> notAllowedFilter = Arrays.asList(
    Map.class,
    ResponseBodyModel.object.class,
    ResponseBodyModel.collection.class,
    ResponseEntity.class,
    Resource.class
  );
  private final Predicate<Class<?>> isNeedToMapping = target ->
    !notAllowedFilter.contains(target);

  @Override
  public boolean supports(
    MethodParameter returnType,
    Class<? extends HttpMessageConverter<?>> converterType
  ) {
    return isNeedToMapping.test(returnType.getMethod().getReturnType());
  }

  @Override
  public Object beforeBodyWrite(
    Object body,
    MethodParameter returnType,
    MediaType selectedContentType,
    Class<? extends HttpMessageConverter<?>> selectedConverterType,
    ServerHttpRequest request,
    ServerHttpResponse response
  ) {
    return GlobalResponsePolicyEnum
      .getFilter()
      .stream()
      .filter(policy -> policy.check(body))
      .findFirst()
      .orElseGet(() -> GlobalResponsePolicyEnum.IS_OBJECT)
      .returnModel(body);
  }
}
