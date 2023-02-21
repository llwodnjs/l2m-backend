package com.l2m.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 리니지2M 아이템 정보 Entity
 * 
 * by jaewon
 */
@Table(name = "item_info")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemInfo {
  // pk
  @Id
  @Column(name = "pk")
  private Long id;

  // 아이템 PK
  @Column(name = "item_id")
  private Long itemId;

  // 아이템명
  @Column(name = "item_name")
  private String itemName;

  // 등급코드
  @Column(name = "grade")
  private String grade;

  // 등급명
  @Column(name = "grade_name")
  private String gradeName;

  // 이미지 url
  @Column(name = "image")
  private String image;

  // 카테고리 명
  @Column(name = "trade_category_name")
  private String tradeCategoryName;

  /**
   * AllArgsConstructor
   * @param id
   * @param itemId
   * @param itemName
   * @param grade
   * @param gradeName
   * @param image
   * @param tradeCategoryName
   */
  protected ItemInfo(Long id, Long itemId, String itemName, String grade, String gradeName, String image,
      String tradeCategoryName) {
    this.id = id;
    this.itemId = itemId;
    this.itemName = itemName;
    this.grade = grade;
    this.gradeName = gradeName;
    this.image = image;
    this.tradeCategoryName = tradeCategoryName;
  }

  
}
