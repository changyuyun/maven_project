package com.ityun.travel.service.impl;

import com.ityun.travel.dao.impl.FavoriteDaoImpl;
import com.ityun.travel.domain.Favorite;
import com.ityun.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    FavoriteDaoImpl favoriteDao = new FavoriteDaoImpl();
    @Override
    public boolean isFavorite(int uid, int rid) {
        Favorite favorite = favoriteDao.findOne(uid, rid);
        if (favorite == null) {
            return false;
        }
        int rrid = favorite.getRid();
        if (rrid == 0) {
            return false;
        }
        return true;
    }
}
