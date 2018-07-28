package com.iotek.humanresources.service.impl;

import com.iotek.humanresources.dao.ManagerMapper;
import com.iotek.humanresources.model.Manager;
import com.iotek.humanresources.service.ManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by grzha on 2018/7/28.
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    @Resource
    private ManagerMapper managerMapper;

    public Manager getManagerByNamePassword(Manager manager) {
        return managerMapper.getManagerByNamePassword(manager);
    }
}
