package com.l2m.repository.support;

import java.util.List;
import java.util.Optional;

import com.l2m.domain.Favorites;
import com.l2m.model.FavoritesDto;
import com.querydsl.core.QueryResults;

public interface FavoritesRepositorySupport {

    // 멤버 키와 아이템 아이디로 엔티티 조회
    public Favorites findFavoritesUsernameAndItemId(String memberKey, Integer itemId);

    // 멤버 비즈니스키로 즐겨찾기 조회
    // public List<FavoritesDto.getFavorite> getFavorite(String memberKey);
    public QueryResults<FavoritesDto.getFavorite> getFavorite(FavoritesDto.getFavoriteParam getFavoriteParam, String memberKey);
}
