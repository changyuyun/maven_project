package com.ityun.travel.service.impl;

import com.ityun.travel.dao.impl.RouteDaoImpl;
import com.ityun.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    RouteDaoImpl routeDao = new RouteDaoImpl();
    @Override
    public List list(int cid, String routeName, int start, int pageSize) {
        return routeDao.list(cid, routeName, start, pageSize);
    }

    @Override
    public int count(int cid, String routeName) {
        return routeDao.count(cid, routeName);
    }
}
