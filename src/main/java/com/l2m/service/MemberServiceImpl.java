package com.l2m.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.l2m.model.MemberDto;
import com.l2m.repository.manager.MemberRepositoryManager;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 회원관련 Service정의
 * 
 * by jaewon
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  @NonNull
  private MemberRepositoryManager memberRepositoryManager;
  
  @Override
  public MemberDto.join join(MemberDto.joinParam joinParam) {
    return memberRepositoryManager.join(joinParam);
  }
}
