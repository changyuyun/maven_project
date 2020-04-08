package com.ityun.travel.service;

import com.ityun.travel.domain.Favorite;

public interface FavoriteService {
    boolean isFavorite(int uid, int rid);
}
