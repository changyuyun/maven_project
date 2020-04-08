package com.ityun.travel.dao.impl;

import com.ityun.travel.dao.RouteDao;
import com.ityun.travel.domain.Route;
import com.ityun.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List list(int cid, String routeName, int start, int pageSize) {
        String sql = "select * from tab_route where 1=1";

        StringBuilder stringBuilder = new StringBuilder(sql);

        List params = new ArrayList();
        if (cid != 0) {
            stringBuilder.append(" and cid = ?");
            params.add(cid);
        }
        if (routeName != null && routeName.length() > 0) {
            stringBuilder.append(" and rname  like ?");
            params.add("%" + routeName + "%");
        }
        stringBuilder.append(" limit ?,?");
        params.add(start);
        params.add(pageSize);
        sql = stringBuilder.toString();
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
    }

    @Override
    public int count(int cid, String routeName) {
        String sql = "select count(*) from tab_route where 1=1";
        StringBuilder stringBuilder = new StringBuilder(sql);
        List params = new ArrayList();
        if (cid != 0) {
            stringBuilder.append(" and cid = ?");
            params.add(cid);
        }
        if (routeName != null && routeName.length() > 0) {
            stringBuilder.append(" and rname  like ?");
            params.add("%" + routeName + "%");
        }
        sql = stringBuilder.toString();
        return template.queryForObject(sql, Integer.class, params.toArray());
    }
}
