package com.l2m.domain;

import java.util.function.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.l2m.domain.base.enums.DomainPrefix;
import com.l2m.domain.global.BaseEntity;
import com.l2m.util.global.BusinessKeyUtil;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 권한 entity
 * 
 * by jaewon
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "authorityId")
    private Long id;

    @Column
    private String businessKey;

    // 권한명
    @Column
    private String name;

    // 연관관계처리
    @JoinColumn(name = "member")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    /**
     * 양방향용 세터
     * @param member
     */
    public void settingMember(Member member) {
        this.member = member;
    }

    @Builder
    public Authority(Long id, String businessKey, String name, Member member) {
        this.id = id;
        this.businessKey = businessKey;
        this.name = name;
        this.member = member;
    }

    /**
     * 권한 생성 및 매핑
     * @param member
     * @return
     */
    public static Supplier<Authority> createAuth(Member member) {
        final Authority authority = Authority.builder()
                                            .businessKey(BusinessKeyUtil.create(DomainPrefix.AUTH))
                                            .name("ROLE_USER")
                                            .member(member)
                                            .build();
        return () -> authority;
    }

}