package com.ityun.travel.dao;

import com.ityun.travel.domain.Seller;

public interface SellerDao {
    Seller findBySid(int sid);
}
