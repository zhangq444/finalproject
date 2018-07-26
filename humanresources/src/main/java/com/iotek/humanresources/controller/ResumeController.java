package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Resume;
import com.iotek.humanresources.model.Users;
import com.iotek.humanresources.service.ResumeService;
import com.iotek.humanresources.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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


}
