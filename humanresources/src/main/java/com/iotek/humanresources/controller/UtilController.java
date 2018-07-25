package com.iotek.humanresources.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by grzha on 2018/7/25.
 */
@Controller
public class UtilController {

    @RequestMapping("/returnWelcome")
    public String returnWelcome(){
        return "welcome";
    }

}
