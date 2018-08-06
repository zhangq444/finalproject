package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Attendance;
import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.model.Rewards;
import com.iotek.humanresources.model.Salary;
import com.iotek.humanresources.service.AttendanceService;
import com.iotek.humanresources.service.EmployeeService;
import com.iotek.humanresources.service.RewardsService;
import com.iotek.humanresources.service.SalaryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by grzha on 2018/8/4.
 */
@Controller
public class SalaryController {
    @Resource
    private SalaryService salaryService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private AttendanceService attendanceService;
    @Resource
    private RewardsService rewardsService;

    @RequestMapping("/salaryManage")
    public String salaryManage(HttpSession session){
        session.setAttribute("settleSalaryError","");
        session.setAttribute("reconsiderSalaryList",null);//情况session中之前的查询记录

        return "salaryManage";
    }

    @RequestMapping("/settleSalary")
    public String settleSalary(HttpSession session){
        int state1=0;//代表试用期
        int state2=1;//代表正式
        List<Employee> employeeList=employeeService.getEmployeeByStateOnJob(state1,state2);//获得在职所有员工
        Calendar calendarNow=Calendar.getInstance();//获得结算工资的当前时间
        //检测本月工资是否已经结算，如果结算就不允许进行第二次计算
        //我在工资中储存的时间是工资结算时间，所以查看当前时间和已经存在的工资对象的结算时间是否为同一个月，如果同一个月
        //代表在本月已经结算过了
        boolean flag=false;
        List<Salary> salaryList=salaryService.getAllSalary();
        for(Salary salary:salaryList){
            Date date=salary.getTime();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            if(calendar.get(Calendar.YEAR)==calendarNow.get(Calendar.YEAR)&&calendar.get(Calendar.MONTH)==calendarNow.get(Calendar.MONTH)){
                flag=true;
                break;
            }
        }
        if(flag){
            session.setAttribute("settleSalaryError","本月工资已结算，不能再次结算");
            return "salaryManage";
        }
        //开始对每一个员工的工资进行结算
        for(Employee employee:employeeList){//遍历每一个职员，全都进行结算
            double baseSalary=employee.getPosition().getSalary();//获得职员的基本工资
            List<Attendance> attendanceList=attendanceService.getAttendanceByEMPID(employee);//获得职员全部出勤记录
            if(attendanceList.size()==0){//如果该员工上个月没有出勤记录，就不计算
                continue;
            }
            //获得职员目前月份的上一个月的所有出勤记录
            List<Attendance> attendanceListPreviousMonth=new ArrayList<Attendance>();
            for(Attendance attendance:attendanceList){
                Date date=attendance.getDate();
                Calendar calendarTemp=Calendar.getInstance();
                calendarTemp.setTime(date);
                if(calendarTemp.get(Calendar.YEAR)==calendarNow.get(Calendar.YEAR)&&calendarTemp.get(Calendar.MONTH)==calendarNow.get(Calendar.MONTH)-1){
                    attendanceListPreviousMonth.add(attendance);
                }
            }
            //遍历上个月的出勤记录，计算共有几次旷工
            int absenteeism=0;
            for(Attendance attendance:attendanceListPreviousMonth){
                if(attendance.getState()==4||attendance.getState()==9){
                    absenteeism++;
                }
            }
            //计算基本工资
            int attendanceTimes=attendanceListPreviousMonth.size();//上个月的出勤次数
            if(attendanceTimes<22){
                double baseSalaryTemp=baseSalary-(baseSalary/22)*(22-attendanceTimes+absenteeism);
                BigDecimal bigDecimal=new BigDecimal(baseSalaryTemp);
                baseSalary=bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            }else {
                double baseSalaryTemp=baseSalary-(baseSalary/22)*absenteeism;
                BigDecimal bigDecimal=new BigDecimal(baseSalaryTemp);
                baseSalary=bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            //计算绩效考核
            double performance=employee.getPosition().getSalary()*0.3;
            //计算加班工资
            int overtimeTimes=0;
            for(Attendance attendance:attendanceListPreviousMonth){
                if(attendance.getState()>=5&&attendance.getState()<=9){
                    overtimeTimes++;
                }
            }
            double overtime=200*overtimeTimes;
            //计算奖惩总金额
            List<Rewards> rewardsList=rewardsService.getAllRewardsByEMPID(employee);
            List<Rewards> rewardsListPreviousMonth=new ArrayList<Rewards>();
            //获得上个月的所有奖惩
            for(Rewards rewards:rewardsList){
                Calendar calendarTemp=Calendar.getInstance();
                Date date=rewards.getTime();
                calendarTemp.setTime(date);
                if(calendarTemp.get(Calendar.YEAR)==calendarNow.get(Calendar.YEAR)&&calendarTemp.get(Calendar.MONTH)==calendarNow.get(Calendar.MONTH)-1){
                    rewardsListPreviousMonth.add(rewards);
                }
            }
            //计算上个月奖惩总金额
            double rewards=0;
            for(Rewards rewards1:rewardsListPreviousMonth){
                rewards+=rewards1.getMoney();
            }
            //计算社保
            double social=employee.getPosition().getSalary()*0.25;
            //计算真实工资，并且保留2位
            double realSalaryTemp=baseSalary+performance+overtime+rewards-social;
            BigDecimal bigDecimal=new BigDecimal(realSalaryTemp);
            double realSalary=bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            //设置工资状态，0为正常，1为员工要求复议，2为复议结束
            int state=0;
            //薪资中的说明字段不需要使用
            //薪资中的时间
            Date time=new Date();
            //开始生成薪资对象
            Salary salary=new Salary(employee,baseSalary,performance,overtime,rewards,social,realSalary,state,time);
            //测试使用
            //System.out.println(salary);

            //将该员工的薪资对象插入数据库
            salaryService.addNewSalary(salary);
        }
        return "managerWelcome";

    }

    @RequestMapping("/checkSalary")
    public String checkSalary(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage, HttpSession session){
        Employee employee= (Employee) session.getAttribute("loginEmployee");
        List<Salary> salaryList=salaryService.getSalaryByEMPID(employee);

        int totalNum=salaryList.size();
        int pageSize=1;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Salary> salaryList1=salaryService.getSalaryByEMPIDByPage(employee.getId(),start,end);

        session.setAttribute("checkSalaryList",salaryList1);
        session.setAttribute("checkSalaryTotalPages",totalPages);
        return "checkSalary";

    }

    @RequestMapping("/reconsider")
    public String reconsider(int reconsiderId){
        int state=1;
        Salary temp=new Salary(reconsiderId);
        Salary salary=salaryService.getSalaryById(temp);
        salary.setState(state);
        salaryService.modifySalaryStateById(salary);

        return "employeeWelcome";

    }

    @RequestMapping("/reconsiderSalary")
    public String reconsiderSalary(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,HttpSession session){
        int state1=1;//代表需要复议的工资对象
        int state2=2;//代表复议完成的工资对象
        List<Salary> salaryList=salaryService.getReconsiderSalaryByState(state1,state2);

        int totalNum=salaryList.size();
        int pageSize=1;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Salary> salaryList1=salaryService.getReconsiderSalaryByStateByPage(state1,state2,start,end);

        session.setAttribute("reconsiderSalaryList",salaryList1);
        session.setAttribute("reconsiderSalaryListTotalPages",totalPages);

        return "salaryManage";

    }

    @RequestMapping("/finishReconsiderSalary")
    public String finishReconsiderSalary(int finishReconsiderSalaryId,HttpSession session){
        Salary temp=new Salary(finishReconsiderSalaryId);
        Salary salary=salaryService.getSalaryById(temp);
        int state=2;//代表完成复议
        salary.setState(state);

        salaryService.modifySalaryStateById(salary);
        //更新session
        int currentPage=1;
        int state1=1;//代表需要复议的工资对象
        int state2=2;//代表复议完成的工资对象
        List<Salary> salaryList=salaryService.getReconsiderSalaryByState(state1,state2);

        int totalNum=salaryList.size();
        int pageSize=1;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Salary> salaryList1=salaryService.getReconsiderSalaryByStateByPage(state1,state2,start,end);

        session.setAttribute("reconsiderSalaryList",salaryList1);
        session.setAttribute("reconsiderSalaryListTotalPages",totalPages);

        return "salaryManage";
    }






}
