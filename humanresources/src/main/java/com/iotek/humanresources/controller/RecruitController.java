package com.iotek.humanresources.controller;

import com.alibaba.fastjson.JSONArray;
import com.iotek.humanresources.model.*;
import com.iotek.humanresources.service.DepartmentService;
import com.iotek.humanresources.service.PositionService;
import com.iotek.humanresources.service.RecruitService;
import com.iotek.humanresources.service.ResumeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
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
    @Resource
    private DepartmentService departmentService;
    @Resource
    private PositionService positionService;

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

    @RequestMapping("/recruitManage")
    public String recruitManage(HttpSession session){
        List<Recruit> recruitList=recruitService.getAllRecruit();
        session.setAttribute("recruitList",recruitList);
        return "recruitManage";
    }

    @RequestMapping("/addRecruit")
    public String addRecruit(HttpSession session){
        List<Department> departmentList=departmentService.getAllDepartment();
        //System.out.println(departmentList);

        session.setAttribute("departmentList",departmentList);
        return "addRecruit";
    }

    @RequestMapping("/addRecruit1")
    public void addRecruit1(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String id=request.getParameter("key");
        Department temp=new Department();
        temp.setId(Integer.parseInt(id));
        Department department=departmentService.getDepartment(temp);
        String positionJson= JSONArray.toJSONString(department.getPositionList());
        try {
            response.getWriter().println(positionJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/addRecruit2")
    public String addRecruit2(int selectDep,int selectPosition,int number,String description,String requirement){
        Position temp=new Position(selectPosition);
        Position position=positionService.getPositionById(temp);
        Department department=new Department(selectDep);
        int state=0;//代表刚添加的招聘信息是未发布的

        Recruit recruit=new Recruit();
        recruit.setDepartment(department);
        recruit.setPosition(position);
        recruit.setNumber(number);
        recruit.setSalary(position.getSalary());
        recruit.setTime(new Date());
        recruit.setDescription(description);
        recruit.setState(state);
        recruit.setRequirement(requirement);

        recruitService.addNewRecruit(recruit);
        return "managerWelcome";
    }

    @RequestMapping("/showRecruitDetail")
    public String showRecruitDetail(int showRecruitInfoId,HttpSession session){
        Recruit temp=new Recruit(showRecruitInfoId);
        Recruit recruit=recruitService.getRecruitById(temp);

        session.setAttribute("showRecruitDetail",recruit);
        return "showRecruitDetail";

    }

    @RequestMapping("/modifyRecruit")
    public String modifyRecruit(int modifyRecruitId,HttpSession session){
        Recruit temp=new Recruit(modifyRecruitId);
        Recruit recruit=recruitService.getRecruitById(temp);

        session.setAttribute("modifyRecruit",recruit);
        return "modifyRecruit";
    }

    @RequestMapping("/modifyRecruit1")
    public String modifyRecruit1(int number,String description,String requirement,HttpSession session){
        Recruit recruit= (Recruit) session.getAttribute("modifyRecruit");
        recruit.setNumber(number);
        recruit.setDescription(description);
        recruit.setRequirement(requirement);

        recruitService.modifyRecruitById(recruit);
        return "recruitManage";
    }

    @RequestMapping("/releaseRecruit")
    public String releaseRecruit(int releaseRecruitId,HttpSession session){
        Recruit temp=new Recruit(releaseRecruitId);
        Recruit recruit=recruitService.getRecruitById(temp);
        int state=1;//代表进行发布
        recruit.setState(state);
        recruitService.modifyRecruitStateById(recruit);
        //更新session
        List<Recruit> recruitList=recruitService.getAllRecruit();
        session.setAttribute("recruitList",recruitList);

        return "recruitManage";
    }

    @RequestMapping("/unreleaseRecruit")
    public String unreleaseRecruit(int unreleaseRecruitId,HttpSession session){
        Recruit temp=new Recruit(unreleaseRecruitId);
        Recruit recruit=recruitService.getRecruitById(temp);
        int state=0;//代表取消发布
        recruit.setState(state);
        recruitService.modifyRecruitStateById(recruit);
        //更新session
        List<Recruit> recruitList=recruitService.getAllRecruit();
        session.setAttribute("recruitList",recruitList);

        return "recruitManage";
    }

    @RequestMapping("/deleteRecruit")
    public String deleteRecruit(int deleteRecruitId,HttpSession session){
        Recruit temp=new Recruit(deleteRecruitId);
        Recruit recruit=recruitService.getRecruitById(temp);

        recruitService.deleteRecruitById(recruit);
        //更新session
        List<Recruit> recruitList=recruitService.getAllRecruit();
        session.setAttribute("recruitList",recruitList);

        return "recruitManage";
    }


}
