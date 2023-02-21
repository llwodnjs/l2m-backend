package com.l2m.repository.support;

import java.util.List;

import com.l2m.domain.base.enums.ItemEnum;
import com.l2m.model.SearchDto;

public interface ItemInfoRepositorySupport {
  /**
   * 아이템 정보 조회
   * @param lowPriceSearchParam
   * @param itemEnum
   * @return
   */
  List<SearchDto.itemInfo> itemInfoList(SearchDto.lowPriceSearchParam lowPriceSearchParam, ItemEnum itemEnum);
}
