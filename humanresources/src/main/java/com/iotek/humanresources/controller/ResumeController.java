package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Resume;
import com.iotek.humanresources.model.Users;
import com.iotek.humanresources.service.ResumeService;
import com.iotek.humanresources.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String resume(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage, HttpSession session){
        Users loginUser= (Users) session.getAttribute("loginUser");
        if(loginUser==null){
            session.setAttribute("resumeError","请先登录再进行简历管理");
            return "welcome";
        }

        List<Resume> resumeList=resumeService.getResumeByUid(loginUser);

        int totalNum=resumeList.size();
        int pageSize=5;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Resume> resumeList1=resumeService.getResumeByUidByPage(loginUser.getId(),start,end);

        session.setAttribute("showResumeList",resumeList1);
        session.setAttribute("showResumeListTotalPages",totalPages);
        return "resume";
    }

    @RequestMapping("/addResume")
    public String addResume(HttpSession session){
        session.setAttribute("addResumeError","");//情况session
        return "addResume";
    }

    @RequestMapping("/addResume1")
    public String addResume1(Resume resume,String birthdaydate,HttpSession session){
        //System.out.println(resume);
        String reg="[A-z]+[A-z0-9_-]*\\@[A-z0-9]+\\.[A-z]+";
        /*System.out.println("sdfaf@163.".matches(reg));*/
        //System.out.println(resume.getEmail().matches(reg));
        if(resume.getEmail().matches(reg)==false||resume.getName().equals("")||resume.getGender().equals("")||resume.getPhone().equals("")||
                resume.getAddress().equals("")||resume.getEducation().equals("")||resume.getWorkexperience().equals("")||resume.getIntroduction().equals("")||
                resume.getResumename().equals("")||resume.getIdcard().equals("")||birthdaydate==null||resume.getSchool().equals("")||
                resume.getMajor().equals("")){
            session.setAttribute("addResumeError","输入信息有误，请重新输入");
            return "addResume";

        }
        Users loginUser= (Users) session.getAttribute("loginUser");
        resume.setUsers(loginUser);
        resume.setBirthday(UtilController.StringToDate(birthdaydate));
        resumeService.addNewResume(resume);

        List<Resume> resumeList=resumeService.getResumeByUid(loginUser);

        int currentPage=1;
        int totalNum=resumeList.size();
        int pageSize=5;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Resume> resumeList1=resumeService.getResumeByUidByPage(loginUser.getId(),start,end);

        session.setAttribute("showResumeList",resumeList1);
        session.setAttribute("showResumeListTotalPages",totalPages);
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
        String reg="[A-z]+[A-z0-9_-]*\\@[A-z0-9]+\\.[A-z]+";
        /*System.out.println("sdfaf@163.".matches(reg));*/
        //System.out.println(resume.getEmail().matches(reg));
        if(resume.getEmail().matches(reg)==false||resume.getName().equals("")||resume.getGender().equals("")||resume.getPhone().equals("")||
                resume.getAddress().equals("")||resume.getEducation().equals("")||resume.getWorkexperience().equals("")||resume.getIntroduction().equals("")||
                resume.getResumename().equals("")||resume.getIdcard().equals("")||birthdaydate==null||resume.getSchool().equals("")||
                resume.getMajor().equals("")){
            session.setAttribute("modifyResumeError","输入信息有误，请重新输入");
            return "modifyResume";

        }
        Users loginUser= (Users) session.getAttribute("loginUser");
        resume.setUsers(loginUser);
        resume.setBirthday(UtilController.StringToDate(birthdaydate));

        resumeService.modifyResumeById(resume);
        List<Resume> resumeList=resumeService.getResumeByUid(loginUser);

        int currentPage=1;
        int totalNum=resumeList.size();
        int pageSize=5;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Resume> resumeList1=resumeService.getResumeByUidByPage(loginUser.getId(),start,end);

        session.setAttribute("showResumeList",resumeList1);
        session.setAttribute("showResumeListTotalPages",totalPages);

        return "resume";
    }

    @RequestMapping("/deleteResume")
    public String deleteResume(int deleteResumeId,HttpSession session){
        Resume resume=new Resume();
        resume.setId(deleteResumeId);
        resumeService.deleteResumeById(resume);

        Users loginUser= (Users) session.getAttribute("loginUser");
        List<Resume> resumeList=resumeService.getResumeByUid(loginUser);

        int currentPage=1;
        int totalNum=resumeList.size();
        int pageSize=5;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Resume> resumeList1=resumeService.getResumeByUidByPage(loginUser.getId(),start,end);

        session.setAttribute("showResumeList",resumeList1);
        session.setAttribute("showResumeListTotalPages",totalPages);
        return "resume";
    }





}
