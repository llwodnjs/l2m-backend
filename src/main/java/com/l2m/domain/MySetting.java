package com.l2m.domain;

import java.math.BigDecimal;
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
 * 나의 세팅 Entity
 * 
 * by jaewon
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MySetting extends BaseEntity implements BaseFunction<MySetting> {
  @Id
  @GeneratedValue
  @Column(name = "mySettingId")
  private Long id;

  // 비즈니스 키
  @Column
  private String businessKey;

  // 서버 id
  @Column
  private Integer serverId;

  // 클래스 코드
  @Column
  private String classId;

  // 등급 코드
  @Column
  private String gradeId;

  // 강화 수치
  @Column
  private Integer fromEnchantLevel;

  // 세팅명
  @Column
  private String settingName;

  // fileUrl
  @Column
  private String fileUrl;

  // 저장시 최저가 합계금액
  @Column
  private BigDecimal totalPrice;

  /**
   * AllArgsConstructor
   * @param createUserKey
   * @param createDateTime
   * @param updateUserKey
   * @param updateDateTime
   * @param id
   * @param businessKey
   * @param serverId
   * @param classId
   * @param gradeId
   * @param fromEnchantLevel
   * @param settingName
   * @param fileUrl
   * @param totalPrice
   */
  protected MySetting(String createUserKey, LocalDateTime createDateTime, String updateUserKey,
      LocalDateTime updateDateTime, Long id, String businessKey, Integer serverId, String classId, String gradeId,
      Integer fromEnchantLevel, String settingName, String fileUrl, BigDecimal totalPrice) {
    super(createUserKey, createDateTime, updateUserKey, updateDateTime);
    this.id = id;
    this.businessKey = businessKey;
    this.serverId = serverId;
    this.classId = classId;
    this.gradeId = gradeId;
    this.fromEnchantLevel = fromEnchantLevel;
    this.settingName = settingName;
    this.fileUrl = fileUrl;
    this.totalPrice = totalPrice;
  }

  /**
   * clone용 생성자
   */
  protected MySetting(MySetting mySetting) {
    super(mySetting.getCreateUserKey(), mySetting.getCreateDateTime(), mySetting.getUpdateUserKey(),
        mySetting.getUpdateDateTime());
    this.businessKey = mySetting.getBusinessKey();
    this.serverId = mySetting.getServerId();
    this.classId = mySetting.getClassId();
    this.gradeId = mySetting.getGradeId();
    this.fromEnchantLevel = mySetting.getFromEnchantLevel();
    this.settingName = mySetting.getSettingName();
    this.fileUrl = mySetting.getFileUrl();
    this.totalPrice = mySetting.getTotalPrice();
  }

  @Override
  public Supplier<MySetting> identity() {
    return () -> new MySetting();
  }

  @Override
  public MySetting clone(MySetting e) {
    return new MySetting(e);
  }

  @Override
  public MySetting destroy(MySetting e) {
    // TODO Auto-generated method stub
    return null;
  }

  public static Supplier<MySetting> create(MySettingDto.insertParam insertParam) {
    return () -> new MySetting(insertParam);
  }

  /**
   * 나의 세팅 저장
   * @param insertParam
   */
  protected MySetting(MySettingDto.insertParam insertParam) {
    this.createUserKey = SessionUtil.getSession().getBusinessKey();
    this.updateUserKey = SessionUtil.getSession().getBusinessKey();
    this.businessKey = BusinessKeyUtil.create(DomainPrefix.MYSETTING);
    this.serverId = insertParam.getServerId();
    this.classId = insertParam.getClassId();
    this.gradeId = insertParam.getGradeId();
    this.fromEnchantLevel = insertParam.getFromEnchantLevel();
    this.settingName = insertParam.getSettingName();
    this.fileUrl = insertParam.getFileUrl();
    this.totalPrice = insertParam.getTotalPrice();
  }
}
