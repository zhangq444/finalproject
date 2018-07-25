package com.iotek.humanresources.service;

import com.iotek.humanresources.model.Users;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by grzha on 2018/7/25.
 */
public interface UsersService {

    Users getUsersByNamePassword(Users users);

    List<Users> getUsers();

    Users getUsersByName(Users users);

    void addNewUser(Users users);

}
