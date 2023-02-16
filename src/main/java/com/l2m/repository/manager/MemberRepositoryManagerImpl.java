package com.l2m.repository.manager;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.l2m.domain.Member;
import com.l2m.model.MemberDto;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 회원 관련 RepositoryManager
 * 
 * by jaewon
 */
@Repository
@RequiredArgsConstructor
public class MemberRepositoryManagerImpl implements MemberRepositoryManager {
  @NonNull
  private EntityManager entityManager;

  @Override
  public MemberDto.join join(MemberDto.joinParam joinParam) {
    final Member member = Member.joinMember(joinParam).get();
    entityManager.persist(member);
    return new MemberDto.join(member.getBusinessKey());
  }
}
