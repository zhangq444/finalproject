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


    public List<Recruit> getAllRecruitByState(Recruit recruit) {
        return recruitMapper.getAllRecruitByState(recruit);
    }

    public Recruit getRecruitById(Recruit temp) {
        return recruitMapper.getRecruitById(temp);
    }

    public List<Recruit> getAllRecruit() {
        return recruitMapper.getAllRecruit();
    }

    public void addNewRecruit(Recruit recruit) {
        recruitMapper.addNewRecruit(recruit);
    }

    public void modifyRecruitById(Recruit recruit) {
        recruitMapper.modifyRecruitById(recruit);
    }

    public void modifyRecruitStateById(Recruit recruit) {
        recruitMapper.modifyRecruitStateById(recruit);
    }

    public void deleteRecruitById(Recruit recruit) {
        recruitMapper.deleteRecruitById(recruit);
    }

    public List<Recruit> getRecruitCurrentPage(int state, int start, int end) {
        return recruitMapper.getRecruitCurrentPage(state,start,end);
    }


}
