package com.ysynzehao.service.impl;

import com.ysynzehao.dao.UserRepository;
import com.ysynzehao.entry.User;
import com.ysynzehao.service.JRedisService;
import com.ysynzehao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JRedisService jRedisService;

    @Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")
    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(long id) {
        User user = jRedisService.get("user" + id, User.class);
        if (user == null) {
            System.out.println("--------------------------------------");
            user = userRepository.findOne(id);
            jRedisService.set("user" + id, user);
        }
        return user;
    }
}
