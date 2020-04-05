package com.ityun.travel.service;

import com.ityun.travel.domain.User;

public interface UserService {
    boolean register(User user);
    User login(User user);
}
