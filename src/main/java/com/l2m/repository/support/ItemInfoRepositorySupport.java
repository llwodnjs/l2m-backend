package com.l2m.repository.support;

import java.util.List;

import com.l2m.domain.ItemInfo;
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

  /**
   * id로 아이템 정보 조회
   * @param itemId
   * @return
   */
  ItemInfo findById(Long itemId);

  /**
   * 아이템 정보 조회
   * @param changePopListParam
   * @return
   */
  List<ItemInfo> findByItemTypeAndName(SearchDto.changePopListParam changePopListParam);

  /**
   * 아이템 카테고리 비교를 위한 정보 조회
   * @param itemId
   * @return
   */
  String findCategoryNameByItemId(Long itemId);
}
