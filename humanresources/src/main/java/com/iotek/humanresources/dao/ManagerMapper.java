package com.iotek.humanresources.dao;

import com.iotek.humanresources.model.Manager;
import org.springframework.stereotype.Repository;

/**
 * Created by grzha on 2018/7/28.
 */
@Repository
public interface ManagerMapper {
    Manager getManagerByNamePassword(Manager manager);

}
