package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Resume;
import com.iotek.humanresources.model.Users;
import com.iotek.humanresources.service.ResumeService;
import com.iotek.humanresources.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by grzha on 2018/7/26.
 */
@Controller
public class ResumeController {
    @Resource
    private ResumeService resumeService;
    @Resource
    private UsersService usersService;

    @RequestMapping("/resume")
    public String resume(HttpSession session){
        Users loginUser= (Users) session.getAttribute("loginUser");
        if(loginUser==null){
            session.setAttribute("resumeError","请先登录再进行简历管理");
            return "welcome";
        }

        List<Resume> resumeList=resumeService.getResumeByUid(loginUser);
        session.setAttribute("showResumeList",resumeList);
        return "resume";
    }

    @RequestMapping("/addResume")
    public String addResume(){
        return "addResume";
    }

    @RequestMapping("/addResume1")
    public String addResume1(Resume resume,String birthdaydate,HttpSession session){
        Users loginUser= (Users) session.getAttribute("loginUser");
        resume.setUsers(loginUser);
        resume.setBirthday(UtilController.StringToDate(birthdaydate));
        resumeService.addNewResume(resume);
        List<Resume> resumeList=resumeService.getResumeByUid(loginUser);
        session.setAttribute("showResumeList",resumeList);
        return "resume";
    }

    @RequestMapping("/resumeInfo")
    public String resumeInfo(int resumeInfoId,HttpSession session){
        Resume resume=new Resume();
        resume.setId(resumeInfoId);
        Resume resumeTemp=resumeService.getResumeById(resume);

        session.setAttribute("showResumeInfoBirthday",UtilController.DateToString(resumeTemp.getBirthday()));
        session.setAttribute("showResumeInfo",resumeTemp);
        return "resumeInfo";
    }

    @RequestMapping("/modifyResume")
    public String modifyResume(){
        return "modifyResume";
    }

    @RequestMapping("/modifyResume1")
    public String modifyResume1(Resume resume,String birthdaydate,HttpSession session){
        Users loginUser= (Users) session.getAttribute("loginUser");
        resume.setUsers(loginUser);
        resume.setBirthday(UtilController.StringToDate(birthdaydate));

        resumeService.modifyResumeById(resume);
        List<Resume> resumeList=resumeService.getResumeByUid(loginUser);
        session.setAttribute("showResumeList",resumeList);
        return "resume";
    }

    @RequestMapping("/deleteResume")
    public String deleteResume(int deleteResumeId,HttpSession session){
        Resume resume=new Resume();
        resume.setId(deleteResumeId);
        resumeService.deleteResumeById(resume);

        Users loginUser= (Users) session.getAttribute("loginUser");
        List<Resume> resumeList=resumeService.getResumeByUid(loginUser);
        session.setAttribute("showResumeList",resumeList);
        return "resume";
    }





}
