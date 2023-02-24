package com.l2m.repository.manager;

import com.l2m.domain.Favorites;
import com.l2m.domain.Member;
import com.l2m.model.FavoritesDto;

public interface FavoritesRepositoryManager {

    /** 
     * 즐겨찾기 등록
     * @param member, favoriteParam
     * @return addFavorite
     */
    public FavoritesDto.controlFavorite addFavorite(Member member, FavoritesDto.favoriteParam favoriteParam);

    /** 
     * 즐겨찾기 취소
     * @param favorites
     */
    public FavoritesDto.controlFavorite cancelFavorite(Favorites favorites);
}
