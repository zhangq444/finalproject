package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.User;
import com.iotek.humanresources.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by grzha on 2018/7/25.
 */
@Controller
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public String login(String name,String pass){
        User user=new User();
        user.setName(name);
        user.setPassword(pass);
        User user1=userService.getUserByNamePassword(user);
        if(user1!=null){
            return "success";
        }else {
            return "../../index";
        }


    }


}
