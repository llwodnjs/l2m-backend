package com.l2m.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.l2m.domain.Favorites;
import com.l2m.domain.Member;
import com.l2m.exception.base.NoDataException;
import com.l2m.model.FavoritesDto;
import com.l2m.repository.manager.FavoritesRepositoryManager;
import com.l2m.repository.support.FavoritesRepositorySupport;
import com.l2m.repository.support.MemberRepositorySupport;
import com.l2m.util.global.IsNullUtil;
import com.querydsl.core.QueryResults;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoritesServiceImpl implements FavoritesService {

    @NonNull
    private FavoritesRepositoryManager favoritesRepositoryManager;

    @NonNull
    private FavoritesRepositorySupport favoritesRepositorySupport;

    @NonNull
    private MemberRepositorySupport memberRepositorySupport;
    
    @Override
    public FavoritesDto.controlFavorite controlFavorite(FavoritesDto.favoriteParam favoriteParam) {

        final String username = favoriteParam.getUsername();
        final Integer itemId = favoriteParam.getItemId();

        // 멤버키 받아오기
        final Member member = memberRepositorySupport.findByUsername(username)
                .orElseThrow(() -> new NoDataException("잘못된 회원 정보입니다."));
        final String memberKey = member.getBusinessKey();

        final Favorites favorites = favoritesRepositorySupport.findFavoritesUsernameAndItemId(memberKey, itemId);
        
        // 이미 즐겨찾기에 등록한 아이템일 경우 Y로 업데이트
        if (!IsNullUtil.check(favorites)) {
            return favoritesRepositoryManager.cancelFavorite(favorites);
        }
        
        // 없을 경우 추가한다.
        return favoritesRepositoryManager.addFavorite(member, favoriteParam);
    }

    // 즐겨찾기 여부가 Y인 아이템 목록을 가져온다.
    @Override
    public QueryResults<FavoritesDto.getFavorite> getFavorite(FavoritesDto.getFavoriteParam getFavoriteParam) {

        // 멤버키 받아오기
        final String username = getFavoriteParam.getUsername();
        final Member member = memberRepositorySupport.findByUsername(username)
                .orElseThrow(() -> new NoDataException("잘못된 회원 정보입니다."));
        
        final String memberKey = member.getBusinessKey();
        // 등록되어 있는 즐겨찾기 목록 불러오기
        final List<FavoritesDto.getFavorite> favoritesList = favoritesRepositorySupport.getFavorite(memberKey);
        getFavoriteParam.setSize(5);

        return new QueryResults<>(favoritesList, (long) getFavoriteParam.makePageable().getPageSize(), (long) getFavoriteParam.getPage(), favoritesList.size());
    }

    // @Override
    // public FavoritesDto.cancelFavorite cancelFavorite(FavoritesDto.cancelFavoriteParam cancelFavoriteParam) {
    //     // 멤버키 받아오기
    //     final String username = cancelFavoriteParam.getUsername();
    //     final Integer itemId = cancelFavoriteParam.getItemId();

    //     final Member member = memberRepositorySupport.findByUsername(username)
    //             .orElseThrow(() -> new NoDataException("잘못된 회원 정보입니다."));
        
    //     final String memberKey = member.getBusinessKey();
        
    //     final Favorites favorites = favoritesRepositorySupport.findFavoritesUsernameAndItemId(memberKey, itemId);
        
    //     if (IsNullUtil.check(favorites)) {
    //         throw new NoDataException("올바르지 않은 아이템 이름입니다.");
    //     } else if (favorites.getIsFavorite().charValue() == 'N') {
    //         throw new IllegalArgumentException("이미 취소가 된 아이템입니다.");
    //     }

    //     return favoritesRepositoryManager.cancelFavorite(favorites);
    // }
}
