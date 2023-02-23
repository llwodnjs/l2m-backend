package com.l2m.repository.manager;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.l2m.domain.MySetting;
import com.l2m.domain.SettingFiles;
import com.l2m.domain.SettingItem;
import com.l2m.model.MySettingDto;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MySettingRepositoryManagerImpl implements MySettingRepositoryManager {
  
  @NonNull
  private EntityManager entityManager;

  @Override
  public SettingFiles insertFile(MultipartFile file, String contentDir, String root, String path) {
    final SettingFiles settingFiles = SettingFiles.createFile(file, contentDir, root, path).get();
    entityManager.persist(settingFiles);
    return settingFiles;
  }

  @Override
  public MySetting insertMySetting(MySettingDto.insertParam insertParam) {
    final MySetting mySetting = MySetting.create(insertParam).get();
    entityManager.persist(mySetting);
    return mySetting;
  }

  @Override
  public void insertItemInfo(MySettingDto.itemInfo itemInfo, String mySettingKey) {
    final SettingItem settingItem = SettingItem.create(itemInfo, mySettingKey).get();
    entityManager.persist(settingItem);
  }
}
