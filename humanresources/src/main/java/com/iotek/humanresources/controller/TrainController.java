package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Department;
import com.iotek.humanresources.model.EmpToTr;
import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.model.Train;
import com.iotek.humanresources.service.DepartmentService;
import com.iotek.humanresources.service.EmpToTrService;
import com.iotek.humanresources.service.EmployeeService;
import com.iotek.humanresources.service.TrainService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by grzha on 2018/8/1.
 */
@Controller
public class TrainController {
    @Resource
    private TrainService trainService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private EmpToTrService empToTrService;

    @RequestMapping("/trainManage")
    public String trainManage(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage, HttpSession session){
        List<Train> trainList=trainService.getAllTrain();

        int totalNum=trainList.size();
        int pageSize=2;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Train> trainList1=trainService.getAllTrainByPage(start,end);

        session.setAttribute("trainList",trainList1);
        session.setAttribute("trainListTotalPages",totalPages);
        session.setAttribute("withdrawTrainError","");//将错误信息清空
        return "trainManage";
    }

    @RequestMapping("/addTrain")
    public String addTrain(HttpSession session){
        List<Department> departmentList=departmentService.getAllDepartment();
        session.setAttribute("addTrainDepartmentList",departmentList);
        session.setAttribute("addTrainError","");//清空之前的错误记录

        return "addTrain";
    }

    @RequestMapping("/addTrain1")
    public String addTrain1(String theme,String content,String beginTime,String endTime,String address,String selectDep,String button1,String traindate,String button2,HttpSession session){
        //System.out.println(theme+"--"+content+"--"+beginTime+"--"+endTime+"--"+address);
        //System.out.println(selectDep+"||"+button1+"||"+traindate+"||"+button2);

        if(theme.equals("")||content.equals("")||beginTime.equals("")||endTime.equals("")||address.equals("")){
            session.setAttribute("addTrainError","输入参数有误");
            return "addTrain";
        }
        Date date=UtilController.StringToDate(beginTime);
        Date date1=UtilController.StringToDate(endTime);
        boolean flag=UtilController.compareDate(date,date1);
        if(!flag){
            session.setAttribute("addTrainError","培训时间有误");
            return "addTrain";
        }
        if(button1!=null){
            if(selectDep.equals("")){
                session.setAttribute("addTrainError","输入参数有误");
                return "addTrain";
            }
            int state=0;//代表未发布
            Train train=new Train(theme,content,UtilController.StringToDate1(beginTime),UtilController.StringToDate1(endTime),address,state);
            trainService.addNewTrain(train);
            Train trainAfter=trainService.getTrainByThemeContentAddress(train);

            Department temp=new Department(Integer.parseInt(selectDep));
            List<Employee> employeeList=employeeService.getEmployeeByDEPID(temp);
            for(Employee tempEmp:employeeList){
                EmpToTr empToTr=new EmpToTr();
                empToTr.setEmpid(tempEmp.getId());
                empToTr.setTid(trainAfter.getId());

                empToTrService.addNewEmpToTr(empToTr);
            }
            return "managerWelcome";

        }
        if(button2!=null){
            if(traindate.equals("")){
                session.setAttribute("addTrainError","输入参数有误");
                return "addTrain";
            }
            int state=0;//代表未发布
            Train train=new Train(theme,content,UtilController.StringToDate1(beginTime),UtilController.StringToDate1(endTime),address,state);
            trainService.addNewTrain(train);
            Train trainAfter=trainService.getTrainByThemeContentAddress(train);

            Date trainDate1=UtilController.StringToDate1(traindate);
            int state1=0;
            int state2=1;
            List<Employee> employeeList=employeeService.getEmployeeByStateOnJob(state1,state2);
            List<Employee> trainEmployeeList=new ArrayList<Employee>();
            for(Employee temp:employeeList){
                if(UtilController.compareDate(trainDate1,temp.getHiredate())){
                    trainEmployeeList.add(temp);
                }
            }

            for(Employee empTemp:trainEmployeeList){
                EmpToTr empTotr=new EmpToTr();
                empTotr.setEmpid(empTemp.getId());
                empTotr.setTid(trainAfter.getId());

                empToTrService.addNewEmpToTr(empTotr);
            }
            return "managerWelcome";

        }


        return null;
    }

    @RequestMapping("/showTrainInfo")
    public String showTrainInfo(int trainInfoId,HttpSession session){
        Train temp=new Train(trainInfoId);
        Train train=trainService.getTrainById(temp);
        List<Employee> employeeList=train.getEmployeeList();

        session.setAttribute("showTrainInfomation",train);
        session.setAttribute("showTrainInfomationEmployeeList",employeeList);
        return "showTrainInfo";
    }

    @RequestMapping("/modifyTrain")
    public String modifyTrain(int modifyTrainId,HttpSession session){
        Train temp=new Train(modifyTrainId);
        Train train=trainService.getTrainById(temp);

        session.setAttribute("modifyTrainobj",train);
        return "modifyTrain";
    }

    @RequestMapping("/modifyTrain1")
    public String modifyTrain1(String theme,String content,String beginTime,String endTime,String address,HttpSession session){
        Date begin=UtilController.StringToDate1(beginTime);
        Date end=UtilController.StringToDate1(endTime);
        Train train= (Train) session.getAttribute("modifyTrainobj");
        train.setTheme(theme);
        train.setContent(content);
        train.setBegintime(begin);
        train.setEndtime(end);
        train.setAddress(address);

        trainService.modifyTrainThemeContentBegintimeEndtimeAddressById(train);
        return "managerWelcome";
    }

    @RequestMapping("/releaseTrain")
    public String releaseTrain(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,int releaseTrainId,HttpSession session){
        Train temp=new Train(releaseTrainId);
        Train train=trainService.getTrainById(temp);
        int state=1;//代表培训发布
        train.setState(state);
        train.setReleasetime(new Date());
        trainService.modifyTrainReleasetimeStateById(train);
        //更新session
        List<Train> trainList=trainService.getAllTrain();

        int totalNum=trainList.size();
        int pageSize=2;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Train> trainList1=trainService.getAllTrainByPage(start,end);

        session.setAttribute("trainList",trainList1);
        session.setAttribute("trainListTotalPages",totalPages);

        return "trainManage";

    }

    @RequestMapping("/withdrawTrain")
    public String withdrawTrain(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,int withdrawTrainId,HttpSession session){
        Train temp=new Train(withdrawTrainId);
        Train train=trainService.getTrainById(temp);
        Date releasetime=train.getReleasetime();
        Date currenttime=new Date();
        boolean flag=UtilController.compareTimeTenMinute(releasetime,currenttime);
        if(flag){
            session.setAttribute("withdrawTrainError","发布时间超过10分钟，无法撤回");
            return "trainManage";
        }
        int state=0;//代表撤回发布  状态重新变为未发布
        train.setState(state);
        train.setReleasetime(null);//往oracle数据库中添加null好像不行，需要在sql语句中直接用null，无法使用null参数，以后查资料

        trainService.modifyTrainWithdrawTrain(train);
        //更新session
        List<Train> trainList=trainService.getAllTrain();

        int totalNum=trainList.size();
        int pageSize=2;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Train> trainList1=trainService.getAllTrainByPage(start,end);

        session.setAttribute("trainList",trainList1);
        session.setAttribute("trainListTotalPages",totalPages);
        session.setAttribute("withdrawTrainError","");
        return "trainManage";
    }

    @RequestMapping("/checkTrain")
    public String checkTrain(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,HttpSession session){
        Employee employee= (Employee) session.getAttribute("loginEmployee");
        int state=1;//代表发布的培训
        List<Train> trainList=trainService.getTrainByEmpIdState(employee.getId(),state);

        int totalNum=trainList.size();
        int pageSize=2;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Train> trainList1=trainService.getTrainByEmpIdStateByPage(employee.getId(),state,start,end);

        session.setAttribute("checkTrainList",trainList1);
        session.setAttribute("checkTrainListTotalPages",totalPages);

        return "checkTrain";
    }






}
