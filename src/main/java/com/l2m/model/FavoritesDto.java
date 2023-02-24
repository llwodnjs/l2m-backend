package com.l2m.model;

import com.l2m.domain.Favorites;
import com.l2m.model.global.PageModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 즐겨찾기 DTO
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoritesDto {
    
    // 즐겨찾기 등록 파라미터
    @Getter
    @Setter
    public static class favoriteParam {
        
        @Schema(description = "아이템 아이디")
        private Integer itemId;

        @Schema(description = "아이템 이름")
        private String itemName;

        @Schema(description = "등급 코드")
        private String gradeCode;

        @Schema(description = "등급 이름")
        private String gradeName;

        @Schema(description = "아이템 이미지 url")
        private String imgUrl;

        @Schema(description = "회원 아이디")
        private String username;
    }

    // 즐겨찾기 등록 및 취소 반환객체
    @Getter
    @Setter
    public static class controlFavorite {
        @Schema(description = "즐겨찾기 비즈니스키")
        private Character isFavorite;

        public controlFavorite(Favorites favorites) {
            this.isFavorite = favorites.getIsFavorite();
        }
    }

    // 즐겨찾기 목록 조회 파라미터
    @Getter
    @Setter
    public static class getFavoriteParam extends PageModel{
        @Schema(description = "회원 아이디")
        private String username;
    }

    // 즐겨찾기 목록 반환 객체
    @Getter
    @Setter
    public static class getFavorite {
        @Schema(description = "아이템 아이디")
        private Integer itemId;

        @Schema(description = "아이템 이름")
        private String itemName;

        @Schema(description = "등급 코드")
        private String gradeCode;

        @Schema(description = "아이템 이미지 url")
        private String imgUrl;

        @Schema(description = "즐겨찾기 여부")
        private Character isFavorite;

        public getFavorite(Favorites favorites) {
            itemId = favorites.getItemId();
            itemName = favorites.getItemName();
            gradeCode = favorites.getGradeCode();
            imgUrl = favorites.getImgUrl();
            isFavorite = favorites.getIsFavorite();
        }
    }
}
