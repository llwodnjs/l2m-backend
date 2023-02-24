package com.l2m.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.l2m.domain.Favorites;
import com.l2m.domain.ItemInfo;
import com.l2m.domain.Member;
import com.l2m.domain.MySetting;
import com.l2m.domain.base.enums.ItemEnum;
import com.l2m.exception.base.NoDataException;
import com.l2m.model.MySettingDto;
import com.l2m.model.SearchDto;
import com.l2m.repository.support.FavoritesRepositorySupport;
import com.l2m.repository.support.ItemInfoRepositorySupport;
import com.l2m.repository.support.MemberRepositorySupport;
import com.l2m.repository.support.MySettingRepositorySupport;
import com.l2m.util.L2mApiUtil;
import com.l2m.util.global.IsNullUtil;
import com.querydsl.core.QueryResults;

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

  @NonNull
  private FavoritesRepositorySupport favoritesRepositorySupport;

  @NonNull
  private MemberRepositorySupport memberRepositorySupport;

  @NonNull
  private MySettingRepositorySupport mySettingRepositorySupport;

  @Override
  public List<SearchDto.itemListInfo> lowPriceSearch(SearchDto.lowPriceSearchParam lowPriceSearchParam) {

    // 반환용 리스트
    final List<SearchDto.itemListInfo> resultList = new ArrayList<>();
    // final Map<String, Object> resultMap = new LinkedHashMap<>();
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
                                            itemEnum.getType());
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

      // 반지일 경우 별개로 돌려줌
      if (itemEnum.getType().equals("ring")) {
        List<SearchDto.itemListInfo> minItemList = l2mApiItemList.stream().filter(x -> !IsNullUtil.check(x) && !IsNullUtil.check(x.getItem_id()))
                                                                          .sorted(comparator)
                                                                          .limit(2)
                                                                          .collect(Collectors.toList());

        for (int i = 0; i < minItemList.size(); i++) {
          SearchDto.itemListInfo minItem = minItemList.get(i);
          minItem.setTradeCategoryName("반지" + (i + 1));
          resultList.add(minItem);
        }
      } else {
        // 부위별
        SearchDto.itemListInfo resultMinItem = l2mApiItemList.stream().filter(x -> !IsNullUtil.check(x) && !IsNullUtil.check(x.getItem_id()))
                                                                      .min(comparator)
                                                                      .orElseGet(() -> new SearchDto.itemListInfo());
        resultList.add(resultMinItem);
      }
    }

    // 3. 만들어진 데이터 세팅하여 반환
    return resultList;
  }

  @Override
  public QueryResults<SearchDto.itemListInfo> changePopList(SearchDto.changePopListParam changePopListParam) {
    // 반환용 리스트
    final List<SearchDto.itemListInfo> resultList = new ArrayList<>();

    // itemInfo 테이블에서 아이템 정보 조회해옴.
    final List<ItemInfo> itemInfoList = itemInfoRepositorySupport.findByItemTypeAndName(changePopListParam);
    
    try {
      for (ItemInfo itemInfo : itemInfoList) {
        // 2. 받아온 리스트의 item_id기준으로 l2m 리스트 api 호출
        List<SearchDto.itemListInfo> result = L2mApiUtil.getItemList(changePopListParam.getServerId(),
                                          itemInfo.getItemName(),
                                          changePopListParam.getEnchantLevel(),
                                          changePopListParam.getItemType());
        
        resultList.addAll(result);
      }
    } catch(IOException e) {

    }
    return new QueryResults<>(
      resultList, 
      (long) changePopListParam.makePageable().getPageSize(), 
      (long) changePopListParam.getPage(), 
      resultList.size()
    );
  }

  // 아이템 팝업 정보 조회
  @Override
  public SearchDto.itemInfoPop getItemPopInfo(SearchDto.itemInfoPopParam itemInfoPopParam) {

    final Integer itemId = itemInfoPopParam.getItem_id();
    final Integer serverId = itemInfoPopParam.getServer_id();
    final Integer enchantLevel = itemInfoPopParam.getEnchant_level();
    final String username = itemInfoPopParam.getUsername();

    SearchDto.l2mApiItemInfo itemInfoResult = new SearchDto.l2mApiItemInfo();
    SearchDto.l2mApiPriceInfo itemPriceInfoResult = new SearchDto.l2mApiPriceInfo();
    Character isFavorite = 'N';

    try {
      itemInfoResult = L2mApiUtil.getItemInfo(itemId, enchantLevel); // 아이템 정보
      itemPriceInfoResult = L2mApiUtil.getPriceInfo(itemId, serverId, enchantLevel); // 아이템 가격 정보
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (!IsNullUtil.check(username)) {
      final Member member = memberRepositorySupport.findByUsername(username)
        .orElseThrow(() -> new NoDataException("회원 정보가 맞지 않습니다."));
      
      final Favorites favorites = favoritesRepositorySupport.findFavoritesUsernameAndItemId(member.getBusinessKey(), itemId);
      isFavorite = IsNullUtil.check(favorites) ? 'N' : 'Y'; // 즐겨찾기 목록에 있다면 Y로 세팅
    }

    return new SearchDto.itemInfoPop(itemInfoResult, itemPriceInfoResult, isFavorite);
  }

  @Override
  public SearchDto.mySettingLowPriceSearch mySettingLowPriceSearch(SearchDto.mySettingLowPriceSearchParam mySettingLowPriceSearchParam) {
    // 1. 나의 세팅 키로 세팅정보 조회(파라미터 세팅)
    // 2. 나의 세팅 키로 아이템 정보 리스트 조회
    // 3. 아이템 정보 리스트의 itemKey로 item정보 조회 후 반환

    // 최종 반환 객체
    final SearchDto.mySettingLowPriceSearch result = new SearchDto.mySettingLowPriceSearch();
    final String mySettingKey = mySettingLowPriceSearchParam.getMySettingKey();

    // Comparator(현재가가 더 싼것을 찾는다)
    final Comparator<SearchDto.itemListInfo> comparator = (s1, s2) -> s1.getNow_min_unit_price().compareTo(s2.getNow_min_unit_price());

    // 나의 세팅정보 조회
    final MySetting mySetting = mySettingRepositorySupport.findByKey(mySettingKey)
                                  .orElseThrow(() -> new NoDataException("세팅 키가 존재하지 않습니다."));

    // 결과 반환에 포함될 객체
    final SearchDto.lowPriceSearchParam lowPriceSearchParam = new SearchDto.lowPriceSearchParam(mySetting);
    final List<SearchDto.itemListInfo> l2mApiItemList = new ArrayList<>();
    // 나의 세팅 아이템 명 리스트 조회
    final List<MySettingDto.settingItemList> settingItemList = mySettingRepositorySupport.findSettingItemNameList(mySettingKey);
    
    for (MySettingDto.settingItemList settingItem : settingItemList) {
      List<SearchDto.itemListInfo> itemList = new ArrayList<>();

      try {
        // 2. 받아온 리스트의 item_id기준으로 l2m 리스트 api 호출
        itemList = L2mApiUtil.getItemList(lowPriceSearchParam.getServer_id(),
                                          settingItem.getItemName(),
                                          lowPriceSearchParam.getFrom_enchant_level(),
                                          settingItem.getTradeCategoryCode());
      } catch (IOException e) {

      }

      // 리스트로 조회한 것중 가장싼 아이템만 하나 가져옴
      SearchDto.itemListInfo minItem = itemList.stream().filter(content -> !IsNullUtil.check(content))
                                                        .min(comparator)
                                                        .orElseGet(() -> new SearchDto.itemListInfo());

      // 카테고리명 세팅
      minItem.setTradeCategoryName(settingItem.getTradeCategoryName());

      l2mApiItemList.add(minItem);
    }

    result.setList(l2mApiItemList);
    return result;
  }
}
