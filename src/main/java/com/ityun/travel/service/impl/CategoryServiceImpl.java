package com.ityun.travel.service.impl;

import com.ityun.travel.dao.impl.CategoryDaoImpl;
import com.ityun.travel.domain.Category;
import com.ityun.travel.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDaoImpl categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        return categoryDao.getAllCategory();
    }
}
