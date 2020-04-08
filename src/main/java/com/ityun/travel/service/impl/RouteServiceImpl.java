package com.ityun.travel.service.impl;

import com.ityun.travel.dao.impl.RouteDaoImpl;
import com.ityun.travel.dao.impl.RouteImgDaoImpl;
import com.ityun.travel.dao.impl.SellerDaoImpl;
import com.ityun.travel.domain.Route;
import com.ityun.travel.domain.Seller;
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

    @Override
    public Route findOne(int rid) {

        Route route = routeDao.findOne(rid);

        RouteImgDaoImpl routeImgDao = new RouteImgDaoImpl();
        SellerDaoImpl sellerDao = new SellerDaoImpl();

        List routeImgList = routeImgDao.getSome(rid);
        route.setRouteImgList(routeImgList);

        Seller seller = sellerDao.findBySid(route.getSid());
        route.setSeller(seller);
        return route;
    }
}
