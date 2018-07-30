package com.iotek.humanresources.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.iotek.humanresources.model.Department;
import com.iotek.humanresources.model.Manager;
import com.iotek.humanresources.service.DepartmentService;
import com.iotek.humanresources.service.ManagerService;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by grzha on 2018/7/28.
 */
@Controller
public class ManagerController {
    @Resource
    private ManagerService managerService;
    @Resource
    private DepartmentService departmentService;

    @RequestMapping("/managerLogin")
    public String managerLogin(Manager manager, HttpSession session){
        Manager loginManager=managerService.getManagerByNamePassword(manager);

        if(loginManager==null){
            session.setAttribute("managerLoginError","µÇÂ¼Ãû»òÃÜÂë´íÎó");
            return "../../managerLogin";
        }else {
            session.setAttribute("loginManager",loginManager);
            return "managerWelcome";
        }
    }


    @RequestMapping("/test")
    public String test(HttpSession session){
        List<Department> departmentList=departmentService.getAllDepartment();
        //System.out.println(departmentList);

        session.setAttribute("departmentList",departmentList);
        return "test";
    }

    @RequestMapping("/test2")
    public void test2(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String id=request.getParameter("key");
        //System.out.println(id);
        Department temp=new Department();
        temp.setId(Integer.parseInt(id));
        Department department=departmentService.getDepartment(temp);
        //System.out.println(department);
        //String departmentJson= JSON.toJSONString(department);
        //System.out.println(departmentJson);
        String positionJson= JSONArray.toJSONString(department.getPositionList());
        System.out.println(positionJson);
        try {
            response.getWriter().println(positionJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/test3")
    public String test3(int selectDep,int selectPosition){
        System.out.println(selectDep+"-----"+selectPosition);

        return null;
    }



}
