package com.l2m.repository.manager;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.l2m.domain.Favorites;
import com.l2m.domain.Member;
import com.l2m.model.FavoritesDto;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FavoritesRepositoryManagerImpl implements FavoritesRepositoryManager{

    @NonNull
    private EntityManager entityManager;
    
    @Override
    public FavoritesDto.controlFavorite addFavorite(Member member, FavoritesDto.favoriteParam favoriteParam) {
        final Favorites favorites = Favorites.addFavorites(member, favoriteParam).get();
        entityManager.persist(favorites);
        return new FavoritesDto.controlFavorite(favorites);
    }

    @Override
    public FavoritesDto.controlFavorite cancelFavorite(Favorites favorites) {
        favorites.cancelFavorite(favorites);
        return new FavoritesDto.controlFavorite(favorites);
    }
}
