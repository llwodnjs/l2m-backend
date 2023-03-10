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

  /**
   * 내 정보 확인
   * @param confirmInfoParam
   * @return
   */
  public MemberDto.confirmInfo confirmInfo(MemberDto.confirmInfoParam confirmInfoParam);

  /**
   * 내 정보 수정
   * @param editInfoParam
   * @return
   */
  public MemberDto.editInfo editInfo(MemberDto.editInfoParam editInfoParam);
}
