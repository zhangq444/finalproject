package com.iotek.humanresources.service.impl;

import com.iotek.humanresources.dao.EmpToTrMapper;
import com.iotek.humanresources.model.EmpToTr;
import com.iotek.humanresources.service.EmpToTrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by grzha on 2018/8/2.
 */
@Service
public class EmpToTrServiceImpl implements EmpToTrService {
    @Resource
    private EmpToTrMapper empToTrMapper;

    public void addNewEmpToTr(EmpToTr empToTr) {
        empToTrMapper.addNewEmpToTr(empToTr);
    }
}
