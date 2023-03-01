package com.l2m.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.l2m.config.security.JwtProvider;
import com.l2m.domain.Member;
import com.l2m.exception.base.NoDataException;
import com.l2m.model.MemberDto;
import com.l2m.repository.manager.MemberRepositoryManager;
import com.l2m.repository.support.MemberRepositorySupport;
import com.l2m.util.global.RandomGenerator;
import com.l2m.util.global.SessionUtil;

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
  private JwtProvider jwtProvider;

  @NonNull
  private MemberRepositoryManager memberRepositoryManager;
  
  @NonNull
  private MemberRepositorySupport memberRepositorySupport;

  @NonNull
  private PasswordEncoder passwordEncoder;
  
  @Override
  public MemberDto.join join(MemberDto.joinParam joinParam) {
    final String username = joinParam.getUsername();

    // 아이디 체크
    if (memberRepositorySupport.findByUsername(username).isPresent()) {
      throw new NoDataException("이미 해당 아이디로 가입된 회원이 존재합니다.");
    }

    // 비밀번호와 비밀번호 확인란 체크
    if (!joinParam.getPassword().equals(joinParam.getRePassword())){
      throw new BadCredentialsException("비밀번호와 확인 비밀번호가 맞지 않습니다.");
    }
    
    return memberRepositoryManager.join(joinParam);
  }

  @Override
  public MemberDto.login login(MemberDto.loginParam loginParam) {
    // 회원 정보
    final Member member = memberRepositorySupport.findByUsername(loginParam.getUsername())
                            .orElseThrow(() -> new UsernameNotFoundException("아이디 또는 비밀번호가 맞지 않습니다."));

    // 패스워드 확인
    if (!passwordEncoder.matches(loginParam.getPassword(), member.getPassword())) {
      throw new BadCredentialsException("아이디 또는 비밀번호가 맞지 않습니다.");
    }

    // 토큰 팔행
    String token = jwtProvider.createToken(member.getUsername(), member.getRoles());

    return new MemberDto.login(member, token);
  }

  @Override
  public MemberDto.findPw findPw(MemberDto.findPwParam findPwParam) {
    final String name = findPwParam.getName(),          // 이름
                username = findPwParam.getUsername();   // 아이디

    // jwtProvider.resolveToken(HttpRequestUtil.getRequest());

    SessionUtil.getSession();

    // 이름, 아이디로 회원정보 조회
    final Member member = memberRepositorySupport.findByNameAndUsername(name, username)
                            .orElseThrow(() -> new NoDataException("회원이 존재하지 않습니다."));

    // 랜덤값 10자리 포맷의 패스워드 임시 생성
    final String changePw = RandomGenerator.randomStringMaker(10);
    
    return memberRepositoryManager.findPw(member, changePw);
  }

  @Override
  public MemberDto.confirmInfo confirmInfo(MemberDto.confirmInfoParam confirmInfoParam) {
    final String name = confirmInfoParam.getUsername(),          // 아이디
                password = confirmInfoParam.getPassword();   // 비밀번호

    // SessionUtil.getSession();
    // 회원 정보
    final Member member = memberRepositorySupport.findByUsername(name)
    .orElseThrow(() -> new UsernameNotFoundException("아이디 또는 비밀번호가 맞지 않습니다."));
              
    // 패스워드 확인
    if (!passwordEncoder.matches(password, member.getPassword())) {
      throw new BadCredentialsException("아이디 또는 비밀번호가 맞지 않습니다.");
    }
    return new MemberDto.confirmInfo(member);
  }

  @Override
  public MemberDto.editInfo editInfo(MemberDto.editInfoParam editInfoParam) {
    final String username = editInfoParam.getUsername();

    // 비밀번호와 비밀번호 확인란 체크
    if (!editInfoParam.getPassword().equals(editInfoParam.getRePassword())){
      throw new BadCredentialsException("비밀번호와 확인 비밀번호가 맞지 않습니다.");
    }

    // 회원 찾아오기
    final Member member = memberRepositorySupport.findByUsername(username)
    .orElseThrow(() -> new NoDataException("회원 정보가 올바르지 않습니다."));

    return memberRepositoryManager.editInfo(member, editInfoParam.getPassword());
  }
}
