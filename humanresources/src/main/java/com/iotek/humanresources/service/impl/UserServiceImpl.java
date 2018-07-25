package com.iotek.humanresources.service.impl;

import com.iotek.humanresources.dao.UserMapper;
import com.iotek.humanresources.model.User;
import com.iotek.humanresources.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by grzha on 2018/7/25.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    public User getUserByNamePassword(User user) {
        return userMapper.getUserNamePassword(user);
    }
}
