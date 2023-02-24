package com.l2m.repository.support;

import java.util.List;
import java.util.Optional;

import com.l2m.domain.MySetting;
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

  /**
   * 세팅키로 세팅정보 조회
   * @param mySettingKey
   * @return
   */
  Optional<MySetting> findByKey(String mySettingKey);
  
  /**
   * 세팅키로 아이템명 리스트 조회
   * @param mySettingKey
   * @return
   */
  List<MySettingDto.settingItemList> findSettingItemNameList(String mySettingKey);
}
