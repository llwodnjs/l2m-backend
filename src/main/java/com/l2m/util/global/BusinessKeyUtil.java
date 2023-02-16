package com.l2m.util.global;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.persistence.EntityManager;

import com.l2m.domain.base.enums.DomainPrefix;
import com.l2m.domain.BusinessKeyStore;
import com.l2m.repository.support.BusinessKeyStoreRepositorySupport;

/**
 * 비즈니스키 유틸 모음
 * 
 * @version 2023-01-30 userKey 등에서 NULL 값 대신 사용하기 위한 SYSTEM_KEY 상수 추가
 */
public class BusinessKeyUtil {

  // 키가 비어있을 때 null 대신 반환하는 DomainPrefix.SYS 키
  private static final String SYSTEM_KEY = new StringBuilder(DomainPrefix.SYSTEM.getCode()).append("0000000000").toString();

  // 비즈니스키가 유효하려면 모두 통과해야할 predicates 모음
  private static final List<Predicate<String>> availablePredicates = Arrays.asList(
    (key) -> !IsNullUtil.check(key),
    (key) -> !key.equals(SYSTEM_KEY),
    (key) -> key.length() == 13
  );

  /**
   * [비즈니스키가 유효한 값인지 여부를 반환]
   * 유효조건과 반대로, 즉 비어있거나/널이거나/기본값(SYSTEM_KEY) 이거나면 false 반환.
   * 반대로 모든 유효조건을 통과하면 true 반환.
   * 
   * [고려사항]
   * TODO (확인완료 20230130/20230130) 13자리 포맷 (prefix 3자리 + 숫자10자리) 포함해 모든 조건식 통과해야만 true 던지도록 변경필요
   */
  public static boolean isAvailable(String key){
    return availablePredicates.stream()
      .filter((predicate) -> predicate.test(key))
      .count()
      == availablePredicates.size();
  }

  /**
   * [비즈니스키로 받아온 값을 검사해, 비어있으면 임의값으로 치환]
   */
  public static String mapToNotNull(String key){
    return IsNullUtil.check(key) ? SYSTEM_KEY : key;
  }

  /**
   * [비즈니스키 생성]
   */
  public static String create(DomainPrefix prefix) {
    return prefix.isSystem() ? createForSystem() : createForDomain.apply(prefix);
  }
  
  // 각각 고유한 실제 도메인 키를 발급할 경우 사용.
  private static final Function<DomainPrefix, String> createForDomain = (prefix) -> {
    final BusinessKeyStoreRepositorySupport businessKeyStoreRepositorySupport = BeansUtil.getBean(
      BusinessKeyStoreRepositorySupport.class
    );
    final EntityManager entityManager = BeansUtil.getBean(EntityManager.class);

    // 중복 체크 후, 중복일 경우 새로 만들고, 아니면 반환.
    String uniqueKey;
    BusinessKeyStore store;
    do {
      store = BusinessKeyStore.create(prefix).get();
      uniqueKey =
        businessKeyStoreRepositorySupport.isUnique(store)
          ? store.getBusinessKey()
          : null;
    } while (uniqueKey == null);

    entityManager.persist(store);
    return uniqueKey;
  };

  // null 값을 대신하여 '시스템에서 자동입력됌' 을 나타내는 특수 키를 발급할 경우 사용.
  private static String createForSystem() {
    return SYSTEM_KEY;
  }

}
