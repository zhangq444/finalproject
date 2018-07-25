package com.iotek.humanresources.service.impl;

import com.iotek.humanresources.dao.UsersMapper;
import com.iotek.humanresources.model.Users;
import com.iotek.humanresources.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by grzha on 2018/7/25.
 */
@Service
public class UsersServiceImpl implements UsersService {
    @Resource
    private UsersMapper usersMapper;

    public Users getUsersByNamePassword(Users users) {
        return usersMapper.getUsersByNamePassword(users);
    }

    public List<Users> getUsers() {
        return usersMapper.getUsers();
    }

    public Users getUsersByName(Users users) {
        return usersMapper.getUsersByName(users);
    }

    public void addNewUser(Users users) {
        usersMapper.addNewUser(users);
    }


}
