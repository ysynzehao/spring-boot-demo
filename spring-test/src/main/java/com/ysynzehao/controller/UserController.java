package com.ysynzehao.controller;

/**
 * Created by Administrator on 2017/5/3.
 */

import com.ysynzehao.entry.Result;
import com.ysynzehao.entry.User;
import com.ysynzehao.service.UserService;
import com.ysynzehao.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    public Result getUserList() {
        return ResultUtils.success(userService.getUserList());
    }
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public Result saveUser(User user) {
        return ResultUtils.success(userService.saveUser(user));
    }
    @PostMapping(value = "/getUser/{id}")
    public Result getUser(@PathVariable(value = "id") Long id) {
        return ResultUtils.success(userService.getUser(id));
    }
}
