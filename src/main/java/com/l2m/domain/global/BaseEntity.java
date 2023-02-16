package com.l2m.domain.global;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.l2m.domain.base.enums.DomainPrefix;
import com.l2m.util.global.BusinessKeyUtil;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * base entity 표준안
 * 
 * 기본적인 등록자, 수정자, 등록일시, 수정일시만 정의
 *
 * by jaewon
 */
@Getter
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseEntity {

  @Size(max = 13)
  protected String createUserKey;

  @CreationTimestamp
  protected LocalDateTime createDateTime;

  @Size(max = 13)
  protected String updateUserKey;

  @UpdateTimestamp
  protected LocalDateTime updateDateTime;

  /**
   * 최초 생성용 생성자
   * (오직 create userKey. 나머지는 null 대신할 기본값으로 세팅)
   * @param createUserKey
   */
  protected BaseEntity(String createUserKey) {
    this.createUserKey = createUserKey;
    this.updateUserKey = BusinessKeyUtil.create(DomainPrefix.SYSTEM);
  }

  /**
   * 최초 생성용 생성자
   * (날짜 제외한 create Keys + update Keys. 외부에서 null 아닌 키를 넘겨준다고 가정함.)
   * @param createUserKey
   * @param updateUserKey
   */
  protected BaseEntity(String createUserKey, String updateUserKey) {
    this.createUserKey = createUserKey;
    this.updateUserKey = updateUserKey;
  }

  /**
   * all args 생성자
   * (외부에서 null 아닌 키를 넘겨준다고 가정함.)
   * @param createUserKey
   * @param createDateTime
   * @param updateUserKey
   * @param updateDateTime
   */
  protected BaseEntity(
    String createUserKey, 
    LocalDateTime createDateTime, 
    String updateUserKey,
    LocalDateTime updateDateTime
  ) {
    this.createUserKey = createUserKey;
    this.createDateTime = createDateTime;
    this.updateUserKey = updateUserKey;
    this.updateDateTime = updateDateTime;
  }

  
}
