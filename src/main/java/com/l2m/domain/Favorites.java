package com.l2m.domain;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

import com.l2m.domain.base.enums.DomainPrefix;
import com.l2m.domain.global.BaseEntity;
import com.l2m.model.FavoritesDto;
import com.l2m.util.global.BusinessKeyUtil;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorites extends BaseEntity {
    // id
    @Id
    @GeneratedValue
    @Column(name = "favoritesId")
    private Long id;

    // 즐겨찾기 비즈니스키
    @Column
    private String businessKey;

    // 아이템이름
    @Column
    private String itemName;

    // 아이템아이디
    @Column
    private Integer itemId;

    // 등급코드
    @Column
    private String gradeCode;

    // 등급이름
    @Column
    private String gradeName;

    // 이미지링크
    @Column
    private String imgUrl;

    // 즐겨찾기여부 (한번이라도 등록된 아이템이 있는지 && 즐겨찾기 삭제를 위함)
    @Column
    @ColumnDefault(value = "'Y'")
    private Character isFavorite;

    // 멤버 비즈니스키
    @Column
    private String memberKey;

    /**
   * AllArgsConstructor
   * 
   * @param createUserKey
   * @param createDateTime
   * @param updateUserKey
   * @param updateDateTime
   * @param id
   * @param itemId
   * @param itemName
   * @param gradeCode
   * @param gradeName
   * @param imgUrl
   * @param isFavorite
   * @param memberKey
   */
    protected Favorites(String createUserKey, LocalDateTime createDateTime, String updateUserKey, LocalDateTime updateDateTime, 
    Long id, String businessKey, Integer itemId, String itemName, String gradeCode, String gradeName, String imgUrl, Character isFavorite, String memberKey) {
        super(createUserKey, createDateTime, updateUserKey, updateDateTime);
        this.id = id;
        this.businessKey = businessKey;
        this.itemId = itemId;
        this.itemName = itemName;
        this.gradeCode = gradeCode;
        this.gradeName = gradeName;
        this.imgUrl = imgUrl;
        this.isFavorite = isFavorite;
        this.memberKey = memberKey;
    }

    // 즐겨찾기 등록
    protected Favorites(FavoritesDto.favoriteParam favoriteParam, String memberBusinessKey) {
        this.itemId = favoriteParam.getItemId();
        this.businessKey = BusinessKeyUtil.create(DomainPrefix.FAVORITES);
        this.itemName = favoriteParam.getItemName();
        this.gradeCode = favoriteParam.getGradeCode();
        this.gradeName = favoriteParam.getGradeName();
        this.imgUrl = favoriteParam.getImgUrl();
        this.memberKey = memberBusinessKey;
        this.isFavorite = 'Y';
    }

    public static Supplier<Favorites> addFavorites(Member member, FavoritesDto.favoriteParam favoriteParam) {
        final Favorites favorites = new Favorites(favoriteParam, member.getBusinessKey());
        return () -> favorites;
    }

    // 즐겨찾기 취소
    public void cancelFavorite(Favorites favorites) {
        this.isFavorite = 'N';
    }
}
