package com.ityun.travel.service;

import java.util.List;

public interface RouteService {
    List list(int cid, String routeName, int start, int pageSize);
    int count(int cid, String routeName);
}
