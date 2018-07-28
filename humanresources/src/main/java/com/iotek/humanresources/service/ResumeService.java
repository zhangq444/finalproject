package com.iotek.humanresources.service;

import com.iotek.humanresources.model.Resume;
import com.iotek.humanresources.model.Users;

import java.util.List;

/**
 * Created by grzha on 2018/7/26.
 */
public interface ResumeService {
    List<Resume> getResumeByUid(Users loginUser);

    void addNewResume(Resume resume);

    Resume getResumeById(Resume resume);

    void modifyResumeById(Resume resume);

    void deleteResumeById(Resume resume);



}
