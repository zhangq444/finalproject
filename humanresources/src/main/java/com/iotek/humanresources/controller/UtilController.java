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

    public static Date StringToDate1(String dateStr){
        String[] strArr=dateStr.split("T");
        String newDateStr=strArr[0]+" "+strArr[1];
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date=simpleDateFormat.parse(newDateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String DateToString(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
        String date1=simpleDateFormat.format(date);
        return date1;
    }

    public static String DateToString1(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 hh点mm分");
        String date1=simpleDateFormat.format(date);
        return date1;
    }

    @RequestMapping("/returnManagerWelcome")
    public String returnManagerWelcome(){
        return "managerWelcome";
    }

    @RequestMapping("/returnEmployeeManage")
    public String returnEmployeeManage(){
        return "employeeManage";
    }

    public static boolean compareDate(Date begin,Date end){
        long date1=begin.getTime();
        long date2=end.getTime();
        return date1<date2?true:false;
    }

    public static boolean compareTimeTenMinute(Date firstTime,Date secondTime){
        long time1=firstTime.getTime();
        long time2=secondTime.getTime();
        return (time2-time1)>=1000*60*10?true:false;
    }



}
