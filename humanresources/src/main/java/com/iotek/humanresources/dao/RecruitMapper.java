package com.iotek.humanresources.dao;

import com.iotek.humanresources.model.Recruit;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by grzha on 2018/7/25.
 */
@Repository
public interface RecruitMapper {

    List<Recruit> getAllRecruitByState(Recruit recruit);

    Recruit getRecruitById(Recruit temp);

    List<Recruit> getAllRecruit();

    void addNewRecruit(Recruit recruit);

    void modifyRecruitById(Recruit recruit);

    void modifyRecruitStateById(Recruit recruit);

    void deleteRecruitById(Recruit recruit);

    List<Recruit> getRecruitCurrentPage(int state, int start, int end);

}
