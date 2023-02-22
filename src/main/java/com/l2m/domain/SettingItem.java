package com.l2m.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.l2m.domain.global.BaseEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * MySetting과 1:N 관계처리 될 item Entity
 * 
 * by jaewon
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SettingItem extends BaseEntity {
  @Id
  @GeneratedValue
  @Column(name = "settingItemId")
  private Long id;

  // 비즈니스 키
  @Column
  private String businesskey;

  // 강화수치
  @Column
  private Integer enchantLevel;

  // 장비 등급 코드
  @Column
  private String grade;

  // 아이템 PK
  @Column
  private Integer itemId;

  // 아이템 명
  @Column
  private String itemName;

  /**
   * AllArgsConstructor
   * @param createUserKey
   * @param createDateTime
   * @param updateUserKey
   * @param updateDateTime
   * @param id
   * @param businesskey
   * @param enchantLevel
   * @param grade
   * @param itemId
   * @param itemName
   */
  protected SettingItem(String createUserKey, LocalDateTime createDateTime, String updateUserKey,
      LocalDateTime updateDateTime, Long id, String businesskey, Integer enchantLevel, String grade, Integer itemId,
      String itemName) {
    super(createUserKey, createDateTime, updateUserKey, updateDateTime);
    this.id = id;
    this.businesskey = businesskey;
    this.enchantLevel = enchantLevel;
    this.grade = grade;
    this.itemId = itemId;
    this.itemName = itemName;
  }

}
