package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.model.Interview;
import com.iotek.humanresources.model.Recruitment;
import com.iotek.humanresources.model.Users;
import com.iotek.humanresources.service.EmployeeService;
import com.iotek.humanresources.service.InterviewService;
import com.iotek.humanresources.service.RecruitmentService;
import com.iotek.humanresources.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by grzha on 2018/7/29.
 */
@Controller
public class InterviewController {
    @Resource
    private InterviewService interviewService;
    @Resource
    private RecruitmentService recruitmentService;
    @Resource
    private EmployeeService employeeService;

    @RequestMapping("/sendInterview")
    public String sendInterview(int sendInterviewId, HttpSession session){
        Recruitment temp=new Recruitment(sendInterviewId);
        Recruitment recruitment=recruitmentService.getRecruitmentById(temp);

        List<Employee> employeeList=employeeService.getAllEmployee();

        session.setAttribute("sendInterviewRecruitment",recruitment);
        session.setAttribute("employeeList",employeeList);
        return "sendInterview";
    }

    @RequestMapping("/sendInterview1")
    public String sendInterview1(String interviewTime,String interviewAddress,int interviewEmpId,HttpSession session){
        //System.out.println(interviewTime);
        Recruitment recruitment= (Recruitment) session.getAttribute("sendInterviewRecruitment");
        Employee temp=new Employee(interviewEmpId);
        Employee employee=employeeService.getEmployeeById(temp);

        Interview interview=new Interview();
        interview.setRecruit(recruitment.getRecruit());
        interview.setResume(recruitment.getResume());
        interview.setTime(UtilController.StringToDate1(interviewTime));
        interview.setAddress(interviewAddress);
        interview.setEmployee(employee);
        interview.setState(0);//代表面试邀请刚刚发送，用户还没有表示参加或拒绝面试,
        interview.setUsers(recruitment.getUsers());

        //interviewService.addNewInterview(interview);
        session.setAttribute("interviewInfo",interview);
        return "sendInterview1";

    }

    @RequestMapping("/sendInterview2")
    public String sendInterview2(HttpSession session){
        Interview interview= (Interview) session.getAttribute("interviewInfo");
        Recruitment recruitment= (Recruitment) session.getAttribute("sendInterviewRecruitment");
        int invite=1;//代表已经邀请面试
        recruitment.setInvite(invite);
        recruitmentService.modifyRecruitmentInviteById(recruitment);

        interviewService.addNewInterview(interview);
        return "managerWelcome";
    }

    @RequestMapping("/checkInterview")
    public String checkInterview(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage, HttpSession session){
        Users loginUser= (Users) session.getAttribute("loginUser");
        if(loginUser==null){
            session.setAttribute("interviewError","请先登录再进行面试管理");
            return "welcome";
        }
        List<Interview> interviewList=interviewService.getInterviewByUid(loginUser);

        int totalNum=interviewList.size();
        int pageSize=2;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Interview> interviewList1=interviewService.getInterviewByUidByPage(loginUser.getId(),start,end);

        session.setAttribute("checkInterviewList",interviewList1);
        session.setAttribute("checkInterviewListTotalPages",totalPages);

        return "checkInterview";

    }

    @RequestMapping("/enterInterview")
    public String enterInterview(int enterInterviewId,HttpSession session){
        List<Interview> interviewList= (List<Interview>) session.getAttribute("checkInterviewList");
        Interview enterInterview=null;
        for(Interview temp:interviewList){
            if(temp.getId()==enterInterviewId){
                enterInterview=temp;
            }
        }

        int state=1;//代表参加面试
        enterInterview.setState(state);
        interviewService.modifyInterviewStateById(enterInterview);

        return "checkInterview";
    }

    @RequestMapping("/refuseInterview")
    public String refuseInterview(int refuseInterviewId,HttpSession session){
        List<Interview> interviewList= (List<Interview>) session.getAttribute("checkInterviewList");
        Interview enterInterview=null;
        for(Interview temp:interviewList){
            if(temp.getId()==refuseInterviewId){
                enterInterview=temp;
            }
        }

        int state=-1;//代表拒绝面试
        enterInterview.setState(state);
        interviewService.modifyInterviewStateById(enterInterview);

        return "checkInterview";
    }


}
