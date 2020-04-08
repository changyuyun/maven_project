package com.ityun.travel.dao;

import com.ityun.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    List list(int cid, String routeName, int start, int pageSize);
    int count(int cid, String routeName);
    Route findOne(int rid);
}
