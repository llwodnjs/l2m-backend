package com.l2m.repository.support;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.l2m.domain.Favorites;
import com.l2m.domain.QFavorites;
import com.l2m.domain.QMember;
import com.l2m.model.FavoritesDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class FavoritesRepositorySupportImpl implements FavoritesRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public FavoritesRepositorySupportImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    // 회원 키 기준으로 즐겨찾기 등록 상태인 아이템 조회
    @Override
    public Favorites findFavoritesUsernameAndItemId(String memberKey, Integer itemId) {
        final QFavorites favorites = QFavorites.favorites;

        return jpaQueryFactory.select(favorites)
                            .from(favorites)
                            .where(favorites.memberKey.eq(memberKey)
                            .and(favorites.itemId.eq(itemId))
                            .and(favorites.isFavorite.eq('Y')))
                            .fetchFirst();
    }

    // 회원 키 기준으로 즐겨찾기 등록 상태로 되어있는 아이템 목록 조회
    @Override
    public List<FavoritesDto.getFavorite> getFavorite(String memberKey) {
        final QFavorites favorites = QFavorites.favorites;
        final QMember member = QMember.member;

        return jpaQueryFactory.select(Projections.constructor(FavoritesDto.getFavorite.class, favorites))
                                .from(favorites)
                                .join(member)
                                .on(favorites.memberKey.eq(member.businessKey))
                                .where(favorites.isFavorite.eq('Y'))
                                .fetch();
    }
}
