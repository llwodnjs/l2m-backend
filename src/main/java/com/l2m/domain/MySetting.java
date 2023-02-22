package com.l2m.domain;

import java.math.BigDecimal;
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
 * 나의 세팅 Entity
 * 
 * by jaewon
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MySetting extends BaseEntity {
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

  
}
