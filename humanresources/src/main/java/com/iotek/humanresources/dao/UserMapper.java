package com.iotek.humanresources.dao;

import com.iotek.humanresources.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by grzha on 2018/7/25.
 */
@Repository
public interface UserMapper {
    User getUserNamePassword(User user);

}
