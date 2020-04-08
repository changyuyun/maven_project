package com.ityun.travel.dao;

import com.ityun.travel.domain.Favorite;

public interface FavoriteDao {
    Favorite findOne(int uid, int rid);
}
