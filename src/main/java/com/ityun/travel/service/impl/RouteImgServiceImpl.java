package com.ityun.travel.service.impl;

import com.ityun.travel.dao.impl.RouteImgDaoImpl;
import com.ityun.travel.service.RouteImgService;

import java.util.List;

public class RouteImgServiceImpl implements RouteImgService {
    RouteImgDaoImpl routeImgDao = new RouteImgDaoImpl();
    @Override
    public List getImgs(int rid) {
        return routeImgDao.getSome(rid);
    }
}
