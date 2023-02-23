package com.l2m.repository.manager;

import org.springframework.web.multipart.MultipartFile;

import com.l2m.domain.MySetting;
import com.l2m.domain.SettingFiles;
import com.l2m.model.MySettingDto;

public interface MySettingRepositoryManager {
  /**
   * 파일 저장
   * @param file - file
   * @param contentDir - root path
   * @param root - root dir
   * @param path - stored path
   */
  SettingFiles insertFile(MultipartFile file, String contentDir, String root, String path);

  /**
   * 세팅 메인 정보 저장
   * @param insertParam
   */
  MySetting insertMySetting(MySettingDto.insertParam insertParam);

  /**
   * 세팅 아이템 저장
   * @param itemInfo
   * @param mySettingKey
   */
  void insertItemInfo(MySettingDto.itemInfo itemInfo, String mySettingKey);
}
