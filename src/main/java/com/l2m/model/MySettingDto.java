package com.l2m.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.l2m.domain.MySetting;
import com.l2m.domain.SettingItem;
import com.l2m.model.global.PageModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 나의 세팅용 DTO
 * 
 * by jaewon
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MySettingDto {

  /**
   * 파일저장 반환
   */
  @Getter
  @Setter
  public static class fileInsert {
    @Schema(description = "파일 url")
    private String fileUrl;

    public fileInsert(String fileUrl) {
      this.fileUrl = fileUrl;
    }
  }

  /**
   * 나의 세팅 등록 파라미터
   */
  @Getter
  @Setter
  public static class insertParam {
    @Schema(description = "아이템 정보 리스트")
    private List<MySettingDto.itemInfo> list = new ArrayList<>();

    @Schema(description = "서버 ID")
    private Integer serverId;

    @Schema(description = "클래스 코드")
    private String classId;
    
    @Schema(description = "등급 코드")
    private String gradeId;

    @Schema(description = "강화 수치")
    private Integer fromEnchantLevel;

    @Schema(description = "세팅 명")
    private String settingName;

    @Schema(description = "파일 url")
    private String fileUrl;

    @Schema(description = "세팅 최저가")
    private BigDecimal totalPrice;
  }

  @Getter
  @Setter
  public static class itemInfo {
    @Schema(description = "평균가")
    private BigDecimal avg_unit_price;

    @Schema(description = "강화 수치")
    private Integer enchant_level;

    @Schema(description = "등급 코드")
    private String grade;

    @Schema(description = "이미지 url")
    private String image;

    @Schema(description = "아이템 PK")
    private Long item_id;

    @Schema(description = "아이템 명")
    private String item_name;

    @Schema(description = "현 최저가")
    private BigDecimal now_min_unit_price;

    @Schema(description = "서버 ID")
    private Integer server_id;

    @Schema(description = "서버 명")
    private String server_name;

    @Schema(description = "월드 여부")
    private Boolean world;

    @Schema(description = "카테고리 명")
    private String tradeCategoryName;
  }

  /**
   * 나의 세팅 등록 반환객체
   */
  @Getter
  @Setter
  public static class insert {
    @Schema(description = "세팅 키")
    private String mySettingKey;

    public insert(String mySettingKey) {
      this.mySettingKey = mySettingKey;
    }
  }

  @Getter
  @Setter
  public static class settingFile {
    @Schema(description = "실제 파일명")
    private String originalFileName;

    @Schema(description = "변경된 파일명")
    private String replacedFileName;

    @Schema(description = "확장자")
    private String extension;

    @Schema(description = "URI")
    private String path;

    @Schema(description = "파일 사이즈")
    private Long size;

    public settingFile(String originalFileName, String storedFileName, String fileExt, String path, Long size) {
      this.originalFileName = originalFileName;
      this.replacedFileName = storedFileName;
      this.extension = fileExt;
      this.path = path;
      this.size = size;
    }
  }

  /**
   * 나의 세팅 리스트 조회 반환
   */
  @Getter
  @Setter
  public static class list {
    
    @Schema(description = "세팅 비즈니스 키")
    private String mySettingKey;
    
    @Schema(description = "파일 url")
    private String imageUrl;
    
    @Schema(description = "세팅명")
    private String settingName;
    
    @Schema(description = "세팅 total price")
    private BigDecimal totalPrice;
    
    @Schema(description = "서버 ID")
    private Integer serverId;
    
    @Schema(description = "클래스 ID")
    private String classId;
    
    @Schema(description = "등급 ID")
    private String gradeId;
    
    @Schema(description = "강화수치")
    private Integer fromEnchantLevel;

    public list(MySetting mySetting) {
      this.mySettingKey = mySetting.getBusinessKey();
      this.imageUrl = mySetting.getFileUrl();
      this.settingName = mySetting.getSettingName();
      this.totalPrice = mySetting.getTotalPrice();
      this.serverId = mySetting.getServerId();
      this.classId = mySetting.getClassId();
      this.gradeId = mySetting.getGradeId();
      this.fromEnchantLevel = mySetting.getFromEnchantLevel();
    }
  }

  /**
   * 나의 세팅 리스트 조회 파라미터
   */
  @Getter
  @Setter
  public static class listParam extends PageModel {
    @Schema(description = "세팅명")
    private String searchKeyword;
  }

  /**
   * 나의 세팅 아이템 리스트 반환 객체
   */
  @Getter
  @Setter
  public static class settingItemList {
    @Schema(description = "아이템 명")
    private String itemName;

    @Schema(description = "카테고리 코드")
    private String tradeCategoryCode;

    @Schema(description = "카테고리 명")
    private String tradeCategoryName;

    public settingItemList(SettingItem settingItem) {
      this.itemName = settingItem.getItemName();
      this.tradeCategoryCode = settingItem.getTradeCategoryCode();
      this.tradeCategoryName = settingItem.getTradeCategoryName();
    }
  }
}
