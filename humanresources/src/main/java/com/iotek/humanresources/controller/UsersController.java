package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Recruit;
import com.iotek.humanresources.model.Users;
import com.iotek.humanresources.service.RecruitService;
import com.iotek.humanresources.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by grzha on 2018/7/25.
 */
@Controller
public class UsersController {
    @Resource
    private UsersService usersService;
    @Resource
    private RecruitService recruitService;

    @RequestMapping("/loginAndRegister")
    public String loginAndRegister(String name,String password,String login,String register,HttpSession session){

        if(login!=null){
            Users users=new Users();
            users.setName(name);
            users.setPassword(password);


        }

        Users users=new Users();
        users.setName(name);
        users.setPassword(pass);
        Users users1=usersService.getUsersByNamePassword(users);
        if(users1!=null){
            return "success";
        }else {
            return "/haha.jsp";
        }

    }

    @RequestMapping("/")
    public String showUser(HttpSession session){

        return "welcome";

    }



}
