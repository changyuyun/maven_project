package com.ityun.travel.dao;

import com.ityun.travel.domain.User;

public interface UserDao {
    void save(User user);
    User findUserByName(String username);
    User findByUsernameAndPassword(String username, String password);
}
