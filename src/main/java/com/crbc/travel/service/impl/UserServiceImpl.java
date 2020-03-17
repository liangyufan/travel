package com.crbc.travel.service.impl;

import com.crbc.travel.dao.UserDao;
import com.crbc.travel.dao.impl.UserDaoImpl;
import com.crbc.travel.domain.User;
import com.crbc.travel.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean register(User user) {
        // 1. 根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        // 判断u是否为null
        if (u != null) {
            // 用户名存在，注册失败
            return false;
        }
        // 2. 保存用户信息
        userDao.save(user);
        return true;
    }
}
