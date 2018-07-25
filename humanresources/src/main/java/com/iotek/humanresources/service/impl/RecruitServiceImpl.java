package com.iotek.humanresources.service.impl;

import com.iotek.humanresources.dao.RecruitMapper;
import com.iotek.humanresources.model.Recruit;
import com.iotek.humanresources.service.RecruitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by grzha on 2018/7/25.
 */
@Service
public class RecruitServiceImpl implements RecruitService {
    @Resource
    private RecruitMapper recruitMapper;


}
