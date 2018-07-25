package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Recruit;
import com.iotek.humanresources.model.Users;
import com.iotek.humanresources.service.RecruitService;
import com.iotek.humanresources.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping("/")
    public String showUser(HttpSession session){

        return "welcome";

    }

    @RequestMapping("/loginAndRegister")
    public String loginAndRegister(String name, String password, String login, String register, String loginDirect, HttpSession session, HttpServletResponse response, HttpServletRequest request){

        if(login!=null){
            Users users=new Users();
            users.setName(name);
            users.setPassword(password);
            Users users1=usersService.getUsersByNamePassword(users);
            if(users1!=null){
                Cookie cookie=new Cookie("name",users1.getName());
                Cookie cookie1=new Cookie("password",users1.getPassword());
                cookie.setMaxAge(60*60*10);
                cookie1.setMaxAge(60*60*10);
                response.addCookie(cookie);
                response.addCookie(cookie1);

                session.setAttribute("loginUser",users1);
                session.setAttribute("loginError","");
                return "welcome";
            }else {
                session.setAttribute("loginError","用户名或密码错误");
                return "welcome";
            }
        }
        if(register!=null){
            return "register";
        }
        if(loginDirect!=null){
            Cookie[] cookies=request.getCookies();
            String nameTemp=null;
            String passwordTemp=null;

            for(int i=0;i<cookies.length;i++){
                if(cookies[i].getName().equals("name")){
                    nameTemp=cookies[i].getValue();
                }
                if(cookies[i].getName().equals("password")){
                    passwordTemp=cookies[i].getValue();
                }
            }
            Users usersTemp=new Users();
            usersTemp.setName(nameTemp);
            usersTemp.setPassword(passwordTemp);

            Users usersTemp1=usersService.getUsersByNamePassword(usersTemp);
            if(usersTemp1!=null){
                session.setAttribute("loginUser",usersTemp1);
                return "welcome";
            }else {
                session.setAttribute("loginError","Cookies过期");
                return "welcome";
            }
        }

        return null;

    }

    @RequestMapping("/register")
    public String register(String name,String password,HttpSession session){
        Users users=new Users(name,password);
        Users temp=usersService.getUsersByName(users);
        if(temp!=null){
            session.setAttribute("registerError","用户名重复");
            return "register";
        }else {
            usersService.addNewUser(users);
            return "welcome";
        }

    }




}
