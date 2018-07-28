package com.iotek.humanresources.service.impl;

import com.iotek.humanresources.dao.ResumeMapper;
import com.iotek.humanresources.model.Resume;
import com.iotek.humanresources.model.Users;
import com.iotek.humanresources.service.ResumeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by grzha on 2018/7/26.
 */
@Service
public class ResumeServiceImpl implements ResumeService {
    @Resource
    private ResumeMapper resumeMapper;

    public List<Resume> getResumeByUid(Users loginUser) {
        return resumeMapper.getResumeByUid(loginUser);
    }

    public void addNewResume(Resume resume) {
        resumeMapper.addNewResume(resume);
    }

    public Resume getResumeById(Resume resume) {
        return resumeMapper.getResumeById(resume);
    }

    public void modifyResumeById(Resume resume) {
        resumeMapper.modifyResumeById(resume);
    }

    public void deleteResumeById(Resume resume) {
        resumeMapper.deleteResumeById(resume);
    }



}
