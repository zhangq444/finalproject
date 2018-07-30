package com.iotek.humanresources.dao;

import com.iotek.humanresources.model.Interview;
import com.iotek.humanresources.model.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by grzha on 2018/7/29.
 */
@Repository
public interface InterviewMapper {
    void addNewInterview(Interview interview);

    List<Interview> getInterviewByUid(Users loginUser);

    void modifyInterviewStateById(Interview enterInterview);

    List<Interview> getInterviewByState(Interview interview);

}
