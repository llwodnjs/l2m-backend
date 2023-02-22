package com.l2m.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.l2m.model.SearchDto;
import com.l2m.service.SearchService;

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
  private List<SearchDto.itemListInfo> lowPriceSearch(@ModelAttribute final SearchDto.lowPriceSearchParam lowPriceSearchParam) {
    return searchService.lowPriceSearch(lowPriceSearchParam);
  }
}
