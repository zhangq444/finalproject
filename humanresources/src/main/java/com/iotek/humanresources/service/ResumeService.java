package com.iotek.humanresources.service;

import com.iotek.humanresources.model.Resume;
import com.iotek.humanresources.model.Users;

import java.util.List;

/**
 * Created by grzha on 2018/7/26.
 */
public interface ResumeService {
    List<Resume> getResumeByUid(Users loginUser);

}
