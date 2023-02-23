package com.l2m.repository.manager;

import com.l2m.domain.Member;
import com.l2m.model.MemberDto;

public interface MemberRepositoryManager {
  /**
   * 회원가입 처리
   * @param joinParam
   * @return
   */
  public MemberDto.join join(MemberDto.joinParam joinParam);

  /**
   * 비밀번호 찾기
   * @param member
   * @param changePw
   * @return
   */
  public MemberDto.findPw findPw(Member member, String changePw);

  /**
   * 내 정보 수정
   * @param member
   * @param editInfo
   * @return
   */
  public MemberDto.editInfo editInfo(Member member, String password);
}
