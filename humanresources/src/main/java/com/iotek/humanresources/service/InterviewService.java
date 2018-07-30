package com.iotek.humanresources.service;

import com.iotek.humanresources.model.Interview;
import com.iotek.humanresources.model.Users;

import java.util.List;

/**
 * Created by grzha on 2018/7/29.
 */
public interface InterviewService {
    void addNewInterview(Interview interview);

    List<Interview> getInterviewByUid(Users loginUser);

    void modifyInterviewStateById(Interview enterInterview);

    List<Interview> getInterviewByState(Interview interview);

}
