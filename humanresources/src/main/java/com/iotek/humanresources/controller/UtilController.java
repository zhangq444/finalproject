package com.iotek.humanresources.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by grzha on 2018/7/25.
 */
@Controller
public class UtilController {

    @RequestMapping("/returnWelcome")
    public String returnWelcome(){
        return "welcome";
    }

    public static Date StringToDate(String dateStr){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date=simpleDateFormat.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String DateToString(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyƒÍMM‘¬dd»’");
        String date1=simpleDateFormat.format(date);
        return date1;
    }


}
