package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Recruit;
import com.iotek.humanresources.model.Recruitment;
import com.iotek.humanresources.model.Resume;
import com.iotek.humanresources.model.Users;
import com.iotek.humanresources.service.RecruitService;
import com.iotek.humanresources.service.RecruitmentService;
import com.iotek.humanresources.service.ResumeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by grzha on 2018/7/28.
 */
@Controller
public class RecruitmentController {
    @Resource
    private RecruitmentService recruitmentService;
    @Resource
    private ResumeService resumeService;
    @Resource
    private RecruitService recruitService;

    @RequestMapping("/sendResume")
    public String sendResume(String resumeChoiceId, String recruitChoiceId, HttpSession session){
        if(recruitChoiceId==null||resumeChoiceId==null){
            session.setAttribute("sendResumeResult","���ȵ�¼");
            return "recruitInfo";
        }
        Resume resumeTemp=new Resume(Integer.parseInt(resumeChoiceId));
        Recruit recruitTemp=new Recruit(Integer.parseInt(recruitChoiceId));
        Resume resume=resumeService.getResumeById(resumeTemp);
        Recruit recruit=recruitService.getRecruitById(recruitTemp);
        Users loginUser= (Users) session.getAttribute("loginUser");
        int invite=0;//������δ��������
        int read=0;//������δ�Ķ�
        Date date=new Date();

        Recruitment recruitment=new Recruitment(loginUser,resume,read,invite,recruit,date);
        recruitmentService.addNewRecruitment(recruitment);
        session.setAttribute("sendResumeResult","����Ͷ�ݳɹ�");
        return "recruitInfo";

    }

    @RequestMapping("/showRecruitment")
    public String showRecruitment(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,HttpSession session){
        List<Recruitment> recruitmentList=recruitmentService.getAllRecruitment();

        int totalNum=recruitmentList.size();
        int pageSize=5;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Recruitment> recruitmentList1=recruitmentService.getAllRecruitmentByPage(start,end);

        session.setAttribute("showRecruitmentList",recruitmentList1);
        session.setAttribute("showRecruitmentListTotalPages",totalPages);

        return "showRecruitment";
    }

    @RequestMapping("/showRecruitmentInfo")
    public String showRecruitmentInfo(int showRecruitmentInfoId,HttpSession session){
        Recruitment temp=new Recruitment(showRecruitmentInfoId);
        Recruitment recruitment=recruitmentService.getRecruitmentById(temp);

        int read=1;
        recruitment.setRead(read);
        recruitmentService.modifyRecruitmentReadById(recruitment);

        session.setAttribute("showRecruitmentInfo",recruitment);
        return "showRecruitmentInfo";

    }





}
