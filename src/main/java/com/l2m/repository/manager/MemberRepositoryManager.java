package com.l2m.repository.manager;

import com.l2m.model.MemberDto;

public interface MemberRepositoryManager {
  /**
   * 회원가입 처리
   * @param joinParam
   * @return
   */
  public MemberDto.join join(MemberDto.joinParam joinParam);
}
