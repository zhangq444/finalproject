package com.iotek.humanresources.dao;

import com.iotek.humanresources.model.Resume;
import com.iotek.humanresources.model.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by grzha on 2018/7/26.
 */
@Repository
public interface ResumeMapper {

    List<Resume> getResumeByUid(Users loginUser);

    void addNewResume(Resume resume);

    Resume getResumeById(Resume resume);

    void modifyResumeById(Resume resume);

    void deleteResumeById(Resume resume);

    List<Resume> getResumeByUidByPage(int id, int start, int end);

}
