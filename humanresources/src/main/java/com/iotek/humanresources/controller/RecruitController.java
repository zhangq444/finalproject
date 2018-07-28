package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Recruit;
import com.iotek.humanresources.model.Resume;
import com.iotek.humanresources.model.Users;
import com.iotek.humanresources.service.RecruitService;
import com.iotek.humanresources.service.ResumeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by grzha on 2018/7/25.
 */
@Controller
public class RecruitController {
    @Resource
    private RecruitService recruitService;
    @Resource
    private ResumeService resumeService;

    @RequestMapping("/recruitInfo")
    public String recruitInfo(int recruitInfoId, HttpSession session){
        Recruit temp=new Recruit();
        temp.setId(recruitInfoId);
        Recruit recruit=recruitService.getRecruitById(temp);
        List<Resume> resumeList=new ArrayList<Resume>();
        Users loginUser= (Users) session.getAttribute("loginUser");
        if(loginUser!=null){
            resumeList=resumeService.getResumeByUid(loginUser);
        }

        session.setAttribute("recruitInfo",recruit);
        session.setAttribute("resumeList",resumeList);
        return "recruitInfo";
    }





}
