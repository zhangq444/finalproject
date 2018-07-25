package com.iotek.humanresources.service;

import com.iotek.humanresources.model.User;

/**
 * Created by grzha on 2018/7/25.
 */
public interface UserService {
    User getUserByNamePassword(User user);
}
