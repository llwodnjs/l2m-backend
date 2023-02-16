package com.l2m.service;

import com.l2m.model.MemberDto;

public interface MemberService {
  /**
   * 회원가입
   * @param joinParam
   * @return
   */
  public MemberDto.join join(MemberDto.joinParam joinParam);
}
