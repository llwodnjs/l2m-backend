package com.l2m.domain;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import com.l2m.domain.base.enums.DomainPrefix;

/**
 * 비즈니스키 발급 이력 엔티티.
 * 이 엔티티는 독립적으로 의미 있는 개념은 아니기에 BaseEntity, Function 을 상속/구현받지 않았다.
 *
 * @author miri
 * @version 2022-10-25 도메인별 접두사를 기반으로 한 키 발행
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessKeyStore {

  @Id
  @GeneratedValue
  @Column(name = "businessKeyStoreId")
  private Long id;

  @CreationTimestamp
  private LocalDateTime createDateTime;

  private String businessKey;

  /**
   * 비즈니스키 최초 발급 생성자
   */
  public BusinessKeyStore(DomainPrefix prefix) {
    // 난수 발생
    String number = String.valueOf(
      ThreadLocalRandom.current().nextInt(1000000000)
    );

    // 10자리 미만일 경우, 10자리가 될 때까지 0 을 붙여준다.
    while (number.length() < 10) number = 0 + number;

    this.createDateTime = LocalDateTime.now();
    this.businessKey = prefix.getCode() + number;
  }

  /**
   * 비즈니스키 최초 발급
   */
  public static Supplier<BusinessKeyStore> create(DomainPrefix prefix) {
    return () -> new BusinessKeyStore(prefix);
  }
}
