package com.crbc.travel.dao.impl;

import com.crbc.travel.dao.UserDao;
import com.crbc.travel.domain.User;
import com.crbc.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {

    private  JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            // 1. 定义sql
            String sql = "select * from tab_user where username = ?";
            // 2. 执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {
            // 没有查询到会抛异常
        }

        return user;
    }

    @Override
    public void save(User user) {
        // 1. 定义sql
        String sql = "insert into tab_user(username, password, name, birthday, sex, telephone, email) values(?, ?, ?, ?, ?, ?, ?)";
        // 2. 执行sql
        template.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(),
                user.getTelephone(), user.getEmail());
    }
}
