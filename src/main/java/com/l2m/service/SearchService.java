package com.l2m.service;

import java.util.List;

import com.l2m.model.SearchDto;

public interface SearchService {

  /**
   * 최저가 조회
   * @param lowPriceSearchParam
   * @return
   */
  List<SearchDto.itemListInfo> lowPriceSearch(SearchDto.lowPriceSearchParam lowPriceSearchParam);
  
}
