package com.l2m.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.l2m.model.FavoritesDto;
import com.l2m.service.FavoritesService;
import com.querydsl.core.QueryResults;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@Tag(name = "즐겨찾기 관련 APi")
@RestController
@RequestMapping("favorite")
@RequiredArgsConstructor
public class FavoritesRestController {

    @NonNull
    private FavoritesService favoritesService;
    
    @Operation(summary = "즐겨찾기 등록")
    @PostMapping("/control")
    public FavoritesDto.controlFavorite addFavorite(@RequestBody final FavoritesDto.favoriteParam favoriteParam) {
        return favoritesService.controlFavorite(favoriteParam);
    }

    @Operation(summary = "즐겨찾기 조회")
    @GetMapping("/getItems")
    public QueryResults<FavoritesDto.getFavorite> getFavorite(@ModelAttribute final FavoritesDto.getFavoriteParam getFavoriteParam){
        return favoritesService.getFavorite(getFavoriteParam);
    }

    @Operation(summary = "서버별 차트 조회")
    @PostMapping("/getCharts")
    public List<FavoritesDto.getChart> getCharts(@RequestBody final FavoritesDto.getChartParam getChartParam) {
        return favoritesService.getCharts(getChartParam);
    }
    
}
