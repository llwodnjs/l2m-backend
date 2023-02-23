package com.l2m.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.l2m.model.MySettingDto;
import com.l2m.service.MySettingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 나의 세팅 API 정의
 * 
 * by jaewon
 */
@Tag(name = "나의 세팅 API")
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "mySetting")
public class MySettingRestController {

  @NonNull
  private MySettingService mySettingService;
  
  @Operation(description = "나의 세팅 파일 저장")
  @PostMapping(value = "/fileInsert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public MySettingDto.fileInsert fileInsert(@RequestPart("file") MultipartFile file) {
    return mySettingService.fileInsert(file);
  }
  
  @Operation(description = "나의 세팅 정보 저장")
  @PostMapping(value = "/insert")
  public MySettingDto.insert insert(@RequestBody MySettingDto.insertParam insertParam) {
    return mySettingService.insert(insertParam);
  }
}