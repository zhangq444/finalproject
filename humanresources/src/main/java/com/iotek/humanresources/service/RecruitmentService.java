package com.iotek.humanresources.service;

import com.iotek.humanresources.model.Recruitment;

import java.util.List;

/**
 * Created by grzha on 2018/7/28.
 */
public interface RecruitmentService {
    void addNewRecruitment(Recruitment recruitment);

    List<Recruitment> getAllRecruitment();

}
