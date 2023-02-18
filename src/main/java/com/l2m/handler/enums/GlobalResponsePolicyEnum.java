package com.l2m.handler.enums;

import com.l2m.model.ResponseBodyModel;
import com.querydsl.core.QueryResults;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 반환 data => response body 로 파싱하기 위해 사용하는 타입 판단식 모음.
 * null, 하위 타입인지를 우선적으로 검사하도록 선언 위치를 맞추어야 함.
 */
@Getter
@SuppressWarnings("unchecked")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum GlobalResponsePolicyEnum {
  IS_VOID(target -> target == null) {
    @Override
    public ResponseBodyModel.basic returnModel(Object target) {
      return new ResponseBodyModel.object<>(target);
    }
  },
  IS_RESPONSE_BODY_MODEL(target -> target instanceof ResponseBodyModel.basic) {
    @Override
    public ResponseBodyModel.basic returnModel(Object target) {
      return (ResponseBodyModel.basic) target;
    }
  },
  IS_QUERY_RESULTS(target -> target instanceof QueryResults<?>) {
    @Override
    public ResponseBodyModel.basic returnModel(Object target) {
      QueryResults<?> targetObj = (QueryResults<Object>) target;
      if (targetObj.isEmpty()) targetObj =
        new QueryResults<>(
          targetObj.getResults(),
          0l,
          targetObj.getOffset(),
          targetObj.getTotal()
        );
      return new ResponseBodyModel.collection<>(targetObj);
    }
  },
  IS_COLLECTION(target -> target instanceof Collection<?>) {
    @Override
    public ResponseBodyModel.basic returnModel(Object target) {
      return new ResponseBodyModel.collection<>((Collection<?>) target);
    }
  },
  IS_OBJECT(target -> true) {
    @Override
    public ResponseBodyModel.basic returnModel(Object target) {
      return new ResponseBodyModel.object<>(target);
    }
  };

  /**
   * [1. members]
   */
  private Predicate<Object> predicate;

  /**
   * api 에서 반환된 데이터를 받아, responseModel 로 반환.
   * @param target
   * @return
   */
  public abstract ResponseBodyModel.basic returnModel(Object target);

  /**
   * [2. util methods]
   */

  /**
   * 해당 타입의 객체인지 체크하여 반환.
   * @param target
   * @return
   */
  public boolean check(Object target) {
    return this.predicate.test(target);
  }

  /**
   * enums 를 리스트로 반환 (변형 불가)
   * @return
   */
  public static List<GlobalResponsePolicyEnum> getFilter() {
    return Arrays.asList(GlobalResponsePolicyEnum.values());
  }
}
