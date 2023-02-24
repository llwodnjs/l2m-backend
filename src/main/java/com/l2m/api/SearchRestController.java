package com.l2m.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.l2m.model.SearchDto;
import com.l2m.service.SearchService;
import com.querydsl.core.QueryResults;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@Tag(name = "검색관련 API")
@RestController
@RequestMapping("search")
@RequiredArgsConstructor
public class SearchRestController {

  @NonNull
  private SearchService searchService;
  
  @Operation(summary = "최저가 조회")
  @GetMapping(value = "/lowPriceSearch")
  public List<SearchDto.itemListInfo> lowPriceSearch(@ModelAttribute final SearchDto.lowPriceSearchParam lowPriceSearchParam) {
    return searchService.lowPriceSearch(lowPriceSearchParam);
  }

  @Operation(summary = "아이템 교체 팝업 리스트 조회")
  @GetMapping(value = "/changePopList")
  public QueryResults<SearchDto.itemListInfo> changePopList(@ModelAttribute final SearchDto.changePopListParam changePopListParam) {
    return searchService.changePopList(changePopListParam);
  }

  @Operation(summary = "아이템 정보 팝업 조회")
  @GetMapping(value = "/getItemInfoPop")
  public SearchDto.itemInfoPop getItemPopInfo(@ModelAttribute final SearchDto.itemInfoPopParam itemInfoPopParam) {
    return searchService.getItemPopInfo(itemInfoPopParam);
  }

  @Operation(summary = "나의 세팅 키로 최저가 조회")
  @GetMapping(value = "/mySettingLowPriceSearch")
  public SearchDto.mySettingLowPriceSearch mySettingLowPriceSearch(@ModelAttribute final SearchDto.mySettingLowPriceSearchParam mySettingLowPriceSearchParam) {
    return searchService.mySettingLowPriceSearch(mySettingLowPriceSearchParam);
  }
}
