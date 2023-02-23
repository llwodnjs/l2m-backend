package com.l2m.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.l2m.domain.MySetting;
import com.l2m.domain.SettingFiles;
import com.l2m.model.MySettingDto;
import com.l2m.repository.manager.MySettingRepositoryManager;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MySettingServiceImpl implements MySettingService {

  @Value("${filepath.root}")
  private String root;

  @Value("${filepath.contentDir}")
  private String contentDir;
  
  @NonNull
  private MySettingRepositoryManager mySettingRepositoryManager;
  
  @Override
  public MySettingDto.fileInsert fileInsert(MultipartFile file) {
    // 저장 폴더명
    final String path = "mySetting";
    // 파일 저장
    final SettingFiles settingFiles = mySettingRepositoryManager.insertFile(file, contentDir, root, path);

    return new MySettingDto.fileInsert(settingFiles.getFullPath());
  }

  @Override
  public MySettingDto.insert insert(MySettingDto.insertParam insertParam) {
    final List<MySettingDto.itemInfo> list = insertParam.getList();

    final MySetting mySetting = mySettingRepositoryManager.insertMySetting(insertParam);
    final String mySettingKey = mySetting.getBusinessKey();
    for (MySettingDto.itemInfo itemInfo : list) {
      mySettingRepositoryManager.insertItemInfo(itemInfo, mySettingKey);
    }

    return new MySettingDto.insert(mySettingKey);
  }
}
