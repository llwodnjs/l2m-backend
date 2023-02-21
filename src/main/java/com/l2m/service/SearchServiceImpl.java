package com.l2m.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.l2m.domain.base.enums.ItemEnum;
import com.l2m.model.SearchDto;
import com.l2m.repository.support.ItemInfoRepositorySupport;
import com.l2m.util.L2mApiUtil;
import com.l2m.util.global.IsNullUtil;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 검색 관련 service
 * 
 * by jaewon
 */
@Service
@Transactional
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

  @NonNull
  private ItemInfoRepositorySupport itemInfoRepositorySupport;

  @Override
  public Map<String, Object> lowPriceSearch(SearchDto.lowPriceSearchParam lowPriceSearchParam) {

    // 반환용 리스트
    final Map<String, Object> resultMap = new LinkedHashMap<>();
    // Comparator(현재가가 더 싼것을 찾는다)
    final Comparator<SearchDto.itemListInfo> comparator = (s1, s2) -> s1.getNow_min_unit_price().compareTo(s2.getNow_min_unit_price());
    // 1. 서버, 강화수치, 아이템명을 이용하여 itemInfo 정보조회 (부위별 5개)
    for (ItemEnum itemEnum : ItemEnum.values()) {
      final List<SearchDto.itemListInfo> l2mApiItemList = new ArrayList<>();
      // 아이템 조회
      List<SearchDto.itemInfo> result = itemInfoRepositorySupport.itemInfoList(lowPriceSearchParam, itemEnum);
      // l2m 리스트 api 호출하여 반환받을 리스트
      List<SearchDto.itemListInfo> itemList = new ArrayList<>();

      // itemInfo 테이블에서 조회한 부위별 5가지의 아이템을 l2m api를 사용하여 호출
      for (SearchDto.itemInfo info : result) {
        try {
          // 2. 받아온 리스트의 item_id기준으로 l2m 리스트 api 호출
          itemList = L2mApiUtil.getItemList(lowPriceSearchParam.getServer_id(),
                                            info.getItemName(),
                                            lowPriceSearchParam.getFrom_enchant_level(),
                                            itemEnum);
        } catch (IOException e) {

        }

        // 리스트로 조회한 것중 가장싼 아이템만 하나 가져옴
        SearchDto.itemListInfo minItem = itemList.stream().filter(content -> !IsNullUtil.check(content))
                                                          .min(comparator)
                                                          .orElseGet(() -> new SearchDto.itemListInfo());

        // 카테고리명 세팅
        minItem.setTradeCategoryName(itemEnum.getTradeCategoryName());

        l2mApiItemList.add(minItem);
      }
      // 부위별
      SearchDto.itemListInfo resultMinItem = l2mApiItemList.stream().filter(x -> !IsNullUtil.check(x) && !IsNullUtil.check(x.getItem_id()))
                                                                    .filter(item -> item.getTradeCategoryName().equals(itemEnum.getTradeCategoryName()))
                                                                    .min(comparator)
                                                                    .orElseGet(() -> new SearchDto.itemListInfo());

      resultMap.put(itemEnum.getType(), resultMinItem);
    }

    // 3. 만들어진 데이터 세팅하여 반환
    return resultMap;
  }
}
