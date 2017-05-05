package com.ysynzehao.service.impl;

import com.ysynzehao.dao.UserRepository;
import com.ysynzehao.entry.User;
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

    @Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")
    @Override
    public List<User> getUserList() {
        System.out.println("--------------------------------------");
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(long id) {
        return userRepository.findOne(id);
    }
}
