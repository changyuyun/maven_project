package com.ityun.travel.dao;

import java.util.List;

public interface RouteDao {
    List list(int cid, String routeName, int start, int pageSize);
    int count(int cid, String routeName);
}
