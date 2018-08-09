package com.iotek.humanresources.service.impl;

import com.iotek.humanresources.dao.InterviewMapper;
import com.iotek.humanresources.model.Interview;
import com.iotek.humanresources.model.Users;
import com.iotek.humanresources.service.InterviewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by grzha on 2018/7/29.
 */
@Service
public class InterviewServiceImpl implements InterviewService {
    @Resource
    private InterviewMapper interviewMapper;


    public void addNewInterview(Interview interview) {
        interviewMapper.addNewInterview(interview);
    }

    public List<Interview> getInterviewByUid(Users loginUser) {
        return interviewMapper.getInterviewByUid(loginUser);
    }

    public void modifyInterviewStateById(Interview enterInterview) {
        interviewMapper.modifyInterviewStateById(enterInterview);
    }

    public List<Interview> getInterviewByState(Interview interview) {
        return interviewMapper.getInterviewByState(interview);
    }

    public List<Interview> getInterviewByUidByPage(int id, int start, int end) {
        return interviewMapper.getInterviewByUidByPage(id,start,end);
    }

    public List<Interview> getInterviewByStateByPage(int state, int start, int end) {
        return interviewMapper.getInterviewByStateByPage(state,start,end);
    }
}
