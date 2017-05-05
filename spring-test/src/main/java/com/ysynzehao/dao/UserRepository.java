package com.ysynzehao.dao;

import com.ysynzehao.entry.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/5/4.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
