package com.l2m.service;

import java.util.Map;

import com.l2m.model.SearchDto;

public interface SearchService {

  /**
   * 최저가 조회
   * @param lowPriceSearchParam
   * @return
   */
  Map<String, Object> lowPriceSearch(SearchDto.lowPriceSearchParam lowPriceSearchParam);
  
}
