package com.ityun.travel.dao.impl;

import com.ityun.travel.dao.CategoryDao;
import com.ityun.travel.domain.Category;
import com.ityun.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> getAllCategory() {
        String sql = "select * from tab_category";
        List<Category> category = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        return category;
    }
}
