package com.l2m.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 검색 관련 DTO
 * 
 * by jaewon
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchDto {
  
  /**
   * 최저가 조회 반환 객체
   */
  @Getter
  @Setter
  public static class lowPriceSearch {
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

    @Schema(description = "서버명")
    private String server_name;

    @Schema(description = "월드여부")
    private Boolean world;
  }

  /**
   * 최저가 조회 파라미터
   */
  @Getter
  @Setter
  public static class lowPriceSearchParam {
    @Schema(description = "서버 ID")
    private Integer server_id;

    @Schema(description = "클래스 명")
    private String class_id;

    @Schema(description = "장비등급 코드")
    private String grade_id;

    @Schema(description = "강화수치")
    private Integer from_enchant_level;

    @Schema(description = "판매여부")
    private Boolean sale;

  }

  /**
   * itemInfo 테이블 조회용 반환객체
   */
  @Getter
  @Setter
  public static class itemInfo {

    @Schema(description = "아이템 PK")
    private Long itemId;

    @Schema(description = "아이템 명")
    private String itemName;

    // @Schema(description = "등급 코드")
    // private String grade;

    // @Schema(description = "등급 명")
    // private String gradeName;

    // @Schema(description = "이미지 url")
    // private String image;

    @Schema(description = "카테고리 명")
    private String tradeCategoryName;

    // @Schema(description = "")

    public itemInfo(Long itemId, String itemName, String tradeCategoryName) {
      this.itemId = itemId;
      this.itemName = itemName;
      this.tradeCategoryName = tradeCategoryName;
    }
  }

  @Getter
  @Setter
  public static class optionInfo {
    @Schema(description = "옵션 명")
    private String option_name;

    @Schema(description = "옵션 수치")
    private String display;

    @Schema(description = "상세 옵션")
    private String extra_display;
  }

  @Getter
  @Setter
  public static class attributeInfo {
    @Schema(description = "안전강화 수치")
    private Integer safe_enchant_level;

    @Schema(description = "거래가능여부")
    private Boolean tradeable;

    @Schema(description = "강화가능여부")
    private Boolean enchantable;

    @Schema(description = "사망시 드랍가능여부")
    private Boolean droppable;

    @Schema(description = "창고이용 가능여부")
    private Boolean storable;

    @Schema(description = "description")
    private String description;

    @Schema(description = "무게")
    private Integer weight;

    @Schema(description = "재질")
    private String material_name;

    @Schema(description = "컬렉션 수")
    private Integer collection_count;
  }

  @Getter
  @Setter
  public static class l2mApiItemInfo {
    @Schema(description = "아이템 PK")
    private Long item_id;

    @Schema(description = "아이템 명")
    private String item_name;

    @Schema(description = "강화 수치")
    private Integer enchant_level;

    @Schema(description = "등급 코드")
    private String grade;

    @Schema(description = "등급 명")
    private String grade_name;

    @Schema(description = "이미지 url")
    private String image;

    @Schema(description = "카테고리 명")
    private String trade_category_name;

    @Schema(description = "옵션")
    private List<SearchDto.optionInfo> options = new ArrayList<>(); 

    @Schema(description = "속성")
    private SearchDto.attributeInfo attribute;
  }

  @Getter
  @Setter
  public static class l2mApiItemList {
    @Schema(description = "데이터")
    private List<SearchDto.itemListInfo> contents;
  }

  /**
   * 리니지 2m 리스트 api 반환 객체
   */
  @Getter
  @Setter
  public static class itemListInfo {
    @Schema(description = "아이템 PK")
    private Integer item_id;

    @Schema(description = "아이템 명")
    private String item_name;

    @Schema(description = "서버 ID")
    private Integer server_id;
    
    @Schema(description = "서버 명")
    private String server_name;
    
    @Schema(description = "월드 여부")
    private Boolean world;
    
    @Schema(description = "강화 수치")
    private Integer enchant_level;
    
    @Schema(description = "등급 코드")
    private String grade;
    
    @Schema(description = "이미지 url")
    private String image;
    
    @Schema(description = "현 최저가")
    private BigDecimal now_min_unit_price;
    
    @Schema(description = "평균가")
    private BigDecimal avg_unit_price;

    @Schema(description = "카테고리 명")
    private String tradeCategoryName;
  }
}
