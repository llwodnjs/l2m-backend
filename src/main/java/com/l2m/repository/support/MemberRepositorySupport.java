package com.l2m.repository.support;

import java.util.Optional;

import com.l2m.domain.Member;

public interface MemberRepositorySupport {
  /**
   * ID로 회원 검색
   * @param username
   * @return
   */
  Optional<Member> findByUsername(String username);

  /**
   * 이름, ID로 회원 검색
   * @param name
   * @param username
   * @return
   */
  Optional<Member> findByNameAndUsername(String name, String username);

  /**
   * 비즈니스키로 회원조회
   * @param businessKey
   * @return
   */
  Optional<Member> findByBusinessKey(String businessKey);
}
