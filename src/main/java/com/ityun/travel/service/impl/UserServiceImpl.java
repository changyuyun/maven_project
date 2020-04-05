package com.ityun.travel.service.impl;

import com.ityun.travel.dao.UserDao;
import com.ityun.travel.dao.impl.UserDaoImpl;
import com.ityun.travel.domain.User;
import com.ityun.travel.service.UserService;
import com.ityun.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    @Override
    public boolean register(User user) {
        User u = dao.findUserByName(user.getUsername());
        if (u != null) {
            return false;
        }
        user.setCode(UuidUtil.getUuid());
        user.setStatus("Y");
        dao.save(user);
        return true;
    }
}
