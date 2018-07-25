package com.iotek.humanresources.dao;

import com.iotek.humanresources.model.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by grzha on 2018/7/25.
 */
@Repository
public interface UsersMapper {
    Users getUsersByNamePassword(Users users);

    List<Users> getUsers();

    Users getUsersByName(Users users);

    void addNewUser(Users users);

}
