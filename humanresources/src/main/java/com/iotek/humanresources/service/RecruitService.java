package com.iotek.humanresources.service;

import com.iotek.humanresources.model.Recruit;

import java.util.List;

/**
 * Created by grzha on 2018/7/25.
 */
public interface RecruitService {

    List<Recruit> getAllRecruitByState(Recruit recruit);

    Recruit getRecruitById(Recruit temp);

    List<Recruit> getAllRecruit();

    void addNewRecruit(Recruit recruit);

    void modifyRecruitById(Recruit recruit);

    void modifyRecruitStateById(Recruit recruit);

    void deleteRecruitById(Recruit recruit);

    List<Recruit> getRecruitCurrentPage(int state, int start, int end);

}
