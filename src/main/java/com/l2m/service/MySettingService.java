package com.l2m.service;

import org.springframework.web.multipart.MultipartFile;

import com.l2m.model.MySettingDto;
import com.querydsl.core.QueryResults;

public interface MySettingService {
  /**
   * 나의 세팅 파일 저장
   * @param file
   * @return
   */
  MySettingDto.fileInsert fileInsert(MultipartFile file);

  /**
   * 나의 세팅 정보 저장
   * @param insertParam
   * @return
   */
  MySettingDto.insert insert(MySettingDto.insertParam insertParam);

  /**
   * 나의 세팅 리스트 조회
   * @param listParam
   * @return
   */
  QueryResults<MySettingDto.list> list(MySettingDto.listParam listParam);
}
