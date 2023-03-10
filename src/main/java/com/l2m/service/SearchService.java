package com.l2m.service;

import java.util.List;

import com.l2m.model.SearchDto;
import com.querydsl.core.QueryResults;

public interface SearchService {

  /**
   * 최저가 조회
   * @param lowPriceSearchParam
   * @return
   */
  List<SearchDto.itemListInfo> lowPriceSearch(SearchDto.lowPriceSearchParam lowPriceSearchParam);

  /**
   * 아이템 교체 팝업 리스트 조회
   * @param changePopListParam
   * @return
   */
  QueryResults<SearchDto.itemListInfo> changePopList(SearchDto.changePopListParam changePopListParam);

  /**
   * 아이템 정보 팝업 조회
   * @param itemInfoPopParam
   *
   */
  SearchDto.itemInfoPop getItemPopInfo(SearchDto.itemInfoPopParam itemInfoPopParam);

  /**
   * 나의 세팅 키로 최저가 조회
   * @param mySettingLowPriceSearchParam
   * @return
   */
  SearchDto.mySettingLowPriceSearch mySettingLowPriceSearch(SearchDto.mySettingLowPriceSearchParam mySettingLowPriceSearchParam);
  
  /** 
   * 아이템 비교 팝업 조회
   * @param itemInfoPopParamList
   */
  List<SearchDto.itemInfoPop> getCompareItemInfo(SearchDto.itemCompareListParam itemCompareListParam);
}
