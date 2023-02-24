package com.l2m.repository.support;

import com.l2m.model.MySettingDto;
import com.querydsl.core.QueryResults;

public interface MySettingRepositorySupport {
  /**
   * 세션 회원 기준 나의 세팅정보 조회
   * @param listParam
   * @param memberKey
   * @return
   */
  QueryResults<MySettingDto.list> list(MySettingDto.listParam listParam, String memberKey);
}
