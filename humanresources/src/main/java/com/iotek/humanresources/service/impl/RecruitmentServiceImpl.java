package com.iotek.humanresources.service.impl;

import com.iotek.humanresources.dao.RecruitmentMapper;
import com.iotek.humanresources.model.Recruitment;
import com.iotek.humanresources.service.RecruitmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by grzha on 2018/7/28.
 */
@Service
public class RecruitmentServiceImpl implements RecruitmentService {
    @Resource
    private RecruitmentMapper recruitmentMapper;

    public void addNewRecruitment(Recruitment recruitment) {
        recruitmentMapper.addNewRecruitment(recruitment);
    }

    public List<Recruitment> getAllRecruitment() {
        return recruitmentMapper.getAllRecruitment();
    }
}
