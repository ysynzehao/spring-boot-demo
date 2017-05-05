package com.ysynzehao.service;

import com.ysynzehao.entry.User;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */
public interface UserService {

    public List<User> getUserList();

    public User saveUser(User user);

    public User getUser(long id);
}
