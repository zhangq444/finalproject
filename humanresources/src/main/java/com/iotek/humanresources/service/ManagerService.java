package com.iotek.humanresources.service;

import com.iotek.humanresources.model.Manager;

/**
 * Created by grzha on 2018/7/28.
 */
public interface ManagerService {
    Manager getManagerByNamePassword(Manager manager);

}
