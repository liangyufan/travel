package com.crbc.travel.service;

import com.crbc.travel.domain.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean register(User user);
}
