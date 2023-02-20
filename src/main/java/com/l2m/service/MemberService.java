package com.l2m.service;

import com.l2m.model.MemberDto;

public interface MemberService {
  /**
   * 회원가입
   * @param joinParam
   * @return
   */
  public MemberDto.join join(MemberDto.joinParam joinParam);

  /**
   * 로그인
   * @param loginParam
   */
  public MemberDto.login login(MemberDto.loginParam loginParam);

  /**
   * 비밀번호 찾기
   * @param findPwParam
   * @return
   */
  public MemberDto.findPw findPw(MemberDto.findPwParam findPwParam);
}
