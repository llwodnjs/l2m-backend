package com.l2m.domain;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.l2m.domain.base.enums.DomainPrefix;
import com.l2m.domain.global.BaseEntity;
import com.l2m.domain.global.BaseFunction;
import com.l2m.model.MySettingDto;
import com.l2m.util.global.BusinessKeyUtil;
import com.l2m.util.global.SessionUtil;

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
public class SettingItem extends BaseEntity implements BaseFunction<SettingItem> {
  @Id
  @GeneratedValue
  @Column(name = "settingItemId")
  private Long id;

  // 비즈니스 키
  @Column
  private String businesskey;

  // 세팅 비즈니스 키
  @Column
  private String settingBusinessKey;

  // 카테고리 코드
  @Column
  private String tradeCategoryCode;

  // 카테고리 명
  @Column
  private String tradeCategoryName;

  // 아이템 PK
  @Column
  private Long itemId;

  // 아이템 명
  @Column
  private String itemName;

  // 서버 ID
  @Column
  private Integer serverId;
  
  // 서버 명
  @Column
  private String serverName;

  // 강화수치
  @Column
  private Integer enchantLevel;

  // 장비 등급 코드
  @Column
  private String grade;

  /**
   * AllArgsConstructor
   * @param createUserKey
   * @param createDateTime
   * @param updateUserKey
   * @param updateDateTime
   * @param id
   * @param businesskey
   * @param settingBusinessKey
   * @param tradeCategoryCode
   * @param tradeCategoryName
   * @param itemId
   * @param itemName
   * @param serverId
   * @param serverName
   * @param enchantLevel
   * @param grade
   */
  protected SettingItem(String createUserKey, LocalDateTime createDateTime, String updateUserKey,
      LocalDateTime updateDateTime, Long id, String businesskey, String settingBusinessKey, String tradeCategoryCode, String tradeCategoryName,
      Long itemId, String itemName, Integer serverId, String serverName, Integer enchantLevel, String grade) {
    super(createUserKey, createDateTime, updateUserKey, updateDateTime);
    this.id = id;
    this.businesskey = businesskey;
    this.settingBusinessKey = settingBusinessKey;
    this.tradeCategoryCode = tradeCategoryCode;
    this.tradeCategoryName = tradeCategoryName;
    this.itemId = itemId;
    this.itemName = itemName;
    this.serverId = serverId;
    this.serverName = serverName;
    this.enchantLevel = enchantLevel;
    this.grade = grade;
  }

  /**
   * clone용 생성자
   * @param settingItem
   */
  protected SettingItem(SettingItem settingItem) {
    super(settingItem.getCreateUserKey(), settingItem.getCreateDateTime(), settingItem.getUpdateUserKey(),
        settingItem.getUpdateDateTime());
    this.businesskey = settingItem.getBusinesskey();
    this.settingBusinessKey = settingItem.getSettingBusinessKey();
    this.tradeCategoryCode = settingItem.getTradeCategoryCode();
    this.tradeCategoryName = settingItem.getTradeCategoryName();
    this.itemId = settingItem.getItemId();
    this.itemName = settingItem.getItemName();
    this.serverId = settingItem.getServerId();
    this.serverName = settingItem.getServerName();
    this.enchantLevel = settingItem.getEnchantLevel();
    this.grade = settingItem.getGrade();
  }

  @Override
  public Supplier<SettingItem> identity() {
    return () -> new SettingItem();
  }

  @Override
  public SettingItem clone(SettingItem e) {
    return new SettingItem(e);
  }

  @Override
  public SettingItem destroy(SettingItem e) {
    // TODO Auto-generated method stub
    return null;
  }

  public static Supplier<SettingItem> create(MySettingDto.itemInfo itemInfo, String mySettingKey) {
    return () -> new SettingItem(itemInfo, mySettingKey);
  }

  /**
   * 나의 세팅 아이템 저장
   * @param itemInfo
   */
  protected SettingItem(MySettingDto.itemInfo itemInfo, String mySettingKey) {
    this.createUserKey = SessionUtil.getSession().getBusinessKey();
    this.updateUserKey = SessionUtil.getSession().getBusinessKey();
    this.businesskey = BusinessKeyUtil.create(DomainPrefix.SETTINGITEM);
    this.settingBusinessKey = mySettingKey;
    this.tradeCategoryCode = itemInfo.getTradeCategoryName();
    this.tradeCategoryName = itemInfo.getTradeCategoryName();
    this.itemId = itemInfo.getItem_id();
    this.itemName = itemInfo.getItem_name();
    this.serverId = itemInfo.getServer_id();
    this.serverName = itemInfo.getServer_name();
    this.enchantLevel = itemInfo.getEnchant_level();
    this.grade = itemInfo.getGrade();
  }
  
}
