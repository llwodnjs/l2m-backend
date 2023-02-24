package com.l2m.service;

import com.l2m.model.FavoritesDto;
import com.querydsl.core.QueryResults;

public interface FavoritesService {
    
    /** 
     * 즐겨찾기 등록
     * @param favoriteParam
     * @return addFavorite
     */
    public FavoritesDto.controlFavorite controlFavorite(FavoritesDto.favoriteParam favoriteParam);

    /** 
     * 즐겨찾기 목록
     * @param getFavoriteParam
     * @return
     */
    public QueryResults<FavoritesDto.getFavorite> getFavorite(FavoritesDto.getFavoriteParam getFavoriteParam);
    
}
