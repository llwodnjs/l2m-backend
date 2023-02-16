package com.l2m.util.global;

import java.util.Collection;
import java.util.Optional;

/**
 * null check 용 유틸 모음
 */
public class IsNullUtil {

  /**
   * 해당 객체가 null이라면 true, 아니라면 false.
   * 빈 String 문자열이거나, 빈 Collection 일 경우 true 반환한다.
   *
   * 그 외 check 공식이 따로 필요한 클래스가 존재한다면
   * 해당 비즈니스용 널체크 유틸을 만들 것.
   *
   * [고려사항]
   * 대부분 메모리와 할당값의 타입을 일치해서 쓰므로
   * 실제로 하위 if 문을 탈 필요는 없겠으나 혹시 몰라 구현해놓음.
   * @param obj
   * @return null 혹은 빈 Collection 혹은 blank string 일 경우 true
   */
  public static boolean check(Object object) {
    if (object == null) {
      return object == null;
    } else if (object instanceof Collection<?>) {
      return check((Collection<?>) object);
    } else if (object instanceof Optional<?>) {
      return check((Optional<?>) object);
    } else if (object instanceof String) {
      return check((String) object);
    } else {
      return false;
    }
  }

  public static boolean check(Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  public static boolean check(Optional<?> optional) {
    return optional == null || !optional.isPresent();
  }

  public static boolean check(String string) {
    return string == null || string.isEmpty();
  }
}
