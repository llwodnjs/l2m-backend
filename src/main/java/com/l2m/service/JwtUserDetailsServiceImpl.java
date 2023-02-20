package com.l2m.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.l2m.domain.Member;
import com.l2m.model.CustomUserDetails;
import com.l2m.repository.support.MemberRepositorySupport;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {
  @NonNull
  private MemberRepositorySupport memberRepositorySupport;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    // 회원 조회
    Member member = memberRepositorySupport.findByUsername(username)
                      .orElseThrow(() -> new UsernameNotFoundException("아이디 또는 비밀번호가 맞지 않습니다."));

    return new CustomUserDetails(member);
  }
}
