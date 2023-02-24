package com.l2m.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.l2m.domain.MySetting;
import com.l2m.domain.SettingFiles;
import com.l2m.exception.base.NoDataException;
import com.l2m.model.MySettingDto;
import com.l2m.repository.manager.MySettingRepositoryManager;
import com.l2m.repository.support.MemberRepositorySupport;
import com.l2m.repository.support.MySettingRepositorySupport;
import com.l2m.util.global.SessionUtil;
import com.querydsl.core.QueryResults;

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
  private MySettingRepositorySupport mySettingRepositorySupport;
  
  @NonNull
  private MySettingRepositoryManager mySettingRepositoryManager;

  @NonNull
  private MemberRepositorySupport memberRepositorySupport;
  
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

  @Override
  public QueryResults<MySettingDto.list> list(MySettingDto.listParam listParam) {
    final String sessionMemberKey = SessionUtil.getSession().getBusinessKey();

    // 세션 회원 정보 조회
    memberRepositorySupport.findByBusinessKey(sessionMemberKey)
      .orElseThrow(() -> new NoDataException("회원정보가 존재하지 않습니다."));
    return mySettingRepositorySupport.list(listParam, sessionMemberKey);
  }
}
