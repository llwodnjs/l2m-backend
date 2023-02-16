package com.l2m.domain.global;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * entity function 표준안 for DDD
 * by panda
 *
 * 1. DDD 페턴에서 활용 되는 객체 처리에 활용 될 Api Interface
 * 2. 기본적인 객체 생성, 소멸, 복제 등을 비즈니스 관점으로 모델링
 * 3. 각 행위를 정의 하고 필요 시 implements 한다.
 *
 * todo: entity 의 기본 규격 function 의 정의를 위한 기본 스케치 시작. (2020.12.13 진행 중)
 * @param <E> entity 를 주입받아 처리하는 규격
 */
public interface BaseFunction<E extends BaseEntity> extends Serializable {
  /**
   * 모델링 객체 초기화 (빈 객체)
   * @return Supplier<E> 객체의 supplier type 반환
   */
  Supplier<E> identity();

  /**
   * 모델링 치환 객체 생성
   * @param e 객체 생성 주체가 되는 entity
   * @return E 객체 타입을 치환하여 신규 객체 생성 반환
   */
  E clone(E e);

  /**
   * 모델링 객체 파기
   * 객체를 실제 파가 하는 것이 아닌 초기화 된 객체 반환
   * @param e 삭제 주체가 되는 entity
   * @return E 초기화 된 entity
   */
  E destroy(E e);
}
