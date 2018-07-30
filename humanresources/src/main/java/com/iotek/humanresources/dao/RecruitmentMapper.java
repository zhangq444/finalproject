package com.iotek.humanresources.dao;

import com.iotek.humanresources.model.Recruitment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by grzha on 2018/7/28.
 */
@Repository
public interface RecruitmentMapper {
    void addNewRecruitment(Recruitment recruitment);

    List<Recruitment> getAllRecruitment();

    void modifyRecruitmentReadById(Recruitment recruitment);

    Recruitment getRecruitmentById(Recruitment temp);

    void modifyRecruitmentInviteById(Recruitment recruitment);

}
