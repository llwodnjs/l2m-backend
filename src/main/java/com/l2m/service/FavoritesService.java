package com.l2m.service;

import java.util.List;

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

    /** 
     * 아이템 서버별 차트 데이터 목록
     * @param getChartParam
     * @return
     */
    public List<FavoritesDto.getChart> getCharts(FavoritesDto.getChartParam getChartParam);
    
}
