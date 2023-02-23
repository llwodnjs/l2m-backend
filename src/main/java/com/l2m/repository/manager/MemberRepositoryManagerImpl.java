package com.l2m.repository.manager;

import javax.persistence.EntityManager;

import org.springframework.security.crypto.password.PasswordEncoder;
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

  @NonNull
  private PasswordEncoder passwordEncoder;

  @Override
  public MemberDto.join join(MemberDto.joinParam joinParam) {
    final Member member = Member.joinMember(joinParam, passwordEncoder::encode).get();
    entityManager.persist(member);
    return new MemberDto.join(member.getBusinessKey());
  }

  @Override
  public MemberDto.findPw findPw(Member member, String changePw) {
    // 비밀번호 변경처리
    member.findPw(changePw, passwordEncoder::encode);

    return new MemberDto.findPw(changePw);
  }

  @Override
  public MemberDto.editInfo editInfo(Member member, String password) {
    // 내정보 수정처리
    member.editInfo(password, passwordEncoder::encode);

    return new MemberDto.editInfo(member.getBusinessKey());
  }
}
