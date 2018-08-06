package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Department;
import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.model.Rewards;
import com.iotek.humanresources.service.DepartmentService;
import com.iotek.humanresources.service.EmployeeService;
import com.iotek.humanresources.service.RewardsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by grzha on 2018/8/3.
 */
@Controller
public class RewardsController {
    @Resource
    private RewardsService rewardsService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private EmployeeService employeeService;

    @RequestMapping("/checkRewards")
    public String checkRewards(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage, HttpSession session){
        Employee employee= (Employee) session.getAttribute("loginEmployee");
        List<Rewards> rewardsList=rewardsService.getAllRewardsByEMPID(employee);
        List<Rewards> rewardsListNowMonth=new ArrayList<Rewards>();
        for(Rewards temp:rewardsList){
            Date date=temp.getTime();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            Calendar calendarNow=Calendar.getInstance();
            if(calendar.get(Calendar.MONTH)==calendarNow.get(Calendar.MONTH)&&calendar.get(Calendar.YEAR)==calendarNow.get(Calendar.YEAR)){
                rewardsListNowMonth.add(temp);
            }
        }

        int totalNum=rewardsListNowMonth.size();
        int pageSize=2;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;

        List<Rewards> rewardsList1=rewardsService.getAllrewardsByPage(rewardsListNowMonth,currentPage,pageSize);

        session.setAttribute("checkRewardsList",rewardsList1);
        session.setAttribute("checkRewardsTotalPages",totalPages);

        return "checkRewards";

    }

    @RequestMapping("/checkRewards1")
    public String checkRewards1(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,String searchDate,HttpSession session){
        //System.out.println(currentPage);
        //System.out.println(searchDate);

        Employee employee= (Employee) session.getAttribute("loginEmployee");
        List<Rewards> rewardsList=rewardsService.getAllRewardsByEMPID(employee);
        Date date=UtilController.StringToDate(searchDate);
        Calendar calendarSearch=Calendar.getInstance();
        calendarSearch.setTime(date);

        List<Rewards> rewardsListNowMonth=new ArrayList<Rewards>();
        for(Rewards temp:rewardsList){
            Date date1=temp.getTime();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date1);
            if(calendar.get(Calendar.YEAR)==calendarSearch.get(Calendar.YEAR)&&calendar.get(Calendar.MONTH)==calendarSearch.get(Calendar.MONTH)){
                rewardsListNowMonth.add(temp);
            }
        }

        int totalNum=rewardsListNowMonth.size();
        int pageSize=2;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;

        List<Rewards> rewardsList1=rewardsService.getAllrewardsByPage(rewardsListNowMonth,currentPage,pageSize);

        session.setAttribute("checkRewardsList",rewardsList1);
        session.setAttribute("checkRewardsTotalPages",totalPages);

        return "checkRewards";
    }

    @RequestMapping("/rewardsManage")
    public String rewardsManage(HttpSession session){
        List<Department> departmentList=departmentService.getAllDepartment();

        session.setAttribute("rewardsManageDepartmentList",departmentList);
        return "rewardsManage";

    }

    @RequestMapping("/rewardsManage1")
    public String rewardsManage1(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,int selectDep,int selectPosition,HttpSession session){
        if(selectDep==0||selectPosition==0){
            session.setAttribute("rewardsManageError","ÇëÑ¡ÔñÌõ¼þ");
            return "rewardsManage";
        }
        int state1=0;
        int state2=1;
        List<Employee> employeeList=employeeService.getEmployeeByPOSIDAndState(selectPosition,state1,state2);

        int totalNum=employeeList.size();
        int pageSize=1;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Employee> employeeList1=employeeService.getEmployeeByPOSIDAndStateByPage(selectPosition,state1,state2,start,end);

        session.setAttribute("rewardsManageEmployeeList",employeeList1);
        session.setAttribute("rewardsManageEmployeeListTotalPages",totalPages);
        return "rewardsManage";

    }

    @RequestMapping("/checkEmployeeRewards")
    public String checkEmployeeRewards(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,int checkEmployeeRewardId,HttpSession session){
        Employee employee=new Employee(checkEmployeeRewardId);
        List<Rewards> rewardsList=rewardsService.getAllRewardsByEMPID(employee);

        int totalNum=rewardsList.size();
        int pageSize=1;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Rewards> rewardsList1=rewardsService.getAllRewardsByEMPIDByPage(checkEmployeeRewardId,start,end);

        session.setAttribute("checkEmployeeRewardsList",rewardsList1);
        session.setAttribute("checkEmployeeRewardsListTotalPages",totalPages);
        session.setAttribute("checkEmployeeRewardId",checkEmployeeRewardId);
        return "checkEmployeeRewards";

    }

    @RequestMapping("/addNewRewards")
    public String addNewRewards(int addNewRewardsEmpId,HttpSession session){
        session.setAttribute("addNewRewardsEmpId",addNewRewardsEmpId);

        return "addNewRewards";
    }

    @RequestMapping("/addNewRewards1")
    public String addNewRewards1(double money,String explain,int addNewRewardsEmpId,HttpSession session){
        Employee temp=new Employee(addNewRewardsEmpId);
        Employee employee=employeeService.getEmployeeById(temp);

        Rewards rewards=new Rewards(money,new Date(),employee,explain);
        rewardsService.addNewRewards(rewards);

        return "rewardsManage";
    }

    @RequestMapping("/modifyRewards")
    public String modifyRewards(int modifyRewardsId,HttpSession session){
        session.setAttribute("modifyRewardsId",modifyRewardsId);
        return "modifyRewards";
    }

    @RequestMapping("/modifyRewards1")
    public String modifyRewards1(int modifyRewardsId1,double money,String explain,HttpSession session){
        Rewards temp=new Rewards(modifyRewardsId1);
        Rewards rewards=rewardsService.getRewardsById(temp);
        rewards.setMoney(money);
        rewards.setExplain(explain);

        rewardsService.modifyRewardsMoneyExplainById(rewards);
        return "rewardsManage";
    }

    @RequestMapping("/deleteRewards")
    public String deleteRewards(int deleteRewardsId,HttpSession session){
        Rewards temp=new Rewards(deleteRewardsId);
        rewardsService.deleteRewardsById(temp);
        return "rewardsManage";
    }

    @RequestMapping("/reconsiderAddRewards")
    public String reconsiderAddRewards(int addRewardsEMPId,HttpSession session){
        session.setAttribute("addRewardsEMPId",addRewardsEMPId);

        return "reconsiderAddRewards";
    }

    @RequestMapping("/reconsiderAddRewards1")
    public String reconsiderAddRewards1(int addRewardsEMPId,double money,String explain,HttpSession session){
        Employee temp=new Employee(addRewardsEMPId);
        Employee employee=employeeService.getEmployeeById(temp);

        Rewards rewards=new Rewards(money,new Date(),employee,explain);
        rewardsService.addNewRewards(rewards);

        return "salaryManage";

    }

}
