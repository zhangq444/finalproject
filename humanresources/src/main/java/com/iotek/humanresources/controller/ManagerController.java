package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Manager;
import com.iotek.humanresources.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by grzha on 2018/7/28.
 */
@Controller
public class ManagerController {
    @Resource
    private ManagerService managerService;

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






}
