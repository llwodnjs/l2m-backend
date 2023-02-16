package com.l2m.repository.support;

import com.l2m.domain.BusinessKeyStore;

/**
 * 비즈니스키 스토어 조회 쿼리 모음
 * by miri
 */
public interface BusinessKeyStoreRepositorySupport {
  /**
   * 해당 비즈니스키가 중복이 아니면 true 반환
   * @param store
   * @return
   */
  boolean isUnique(BusinessKeyStore entity);
}
