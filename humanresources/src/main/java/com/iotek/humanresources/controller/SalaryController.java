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
        session.setAttribute("reconsiderSalaryList",null);//���session��֮ǰ�Ĳ�ѯ��¼

        return "salaryManage";
    }

    @RequestMapping("/settleSalary")
    public String settleSalary(HttpSession session){
        int state1=0;//����������
        int state2=1;//������ʽ
        List<Employee> employeeList=employeeService.getEmployeeByStateOnJob(state1,state2);//�����ְ����Ա��
        Calendar calendarNow=Calendar.getInstance();//��ý��㹤�ʵĵ�ǰʱ��
        //��Ȿ�¹����Ƿ��Ѿ����㣬�������Ͳ�������еڶ��μ���
        //���ڹ����д����ʱ���ǹ��ʽ���ʱ�䣬���Բ鿴��ǰʱ����Ѿ����ڵĹ��ʶ���Ľ���ʱ���Ƿ�Ϊͬһ���£����ͬһ����
        //�����ڱ����Ѿ��������
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
            session.setAttribute("settleSalaryError","���¹����ѽ��㣬�����ٴν���");
            return "salaryManage";
        }
        //��ʼ��ÿһ��Ա���Ĺ��ʽ��н���
        for(Employee employee:employeeList){//����ÿһ��ְԱ��ȫ�����н���
            double baseSalary=employee.getPosition().getSalary();//���ְԱ�Ļ�������
            List<Attendance> attendanceList=attendanceService.getAttendanceByEMPID(employee);//���ְԱȫ�����ڼ�¼
            if(attendanceList.size()==0){//�����Ա���ϸ���û�г��ڼ�¼���Ͳ�����
                continue;
            }
            //���ְԱĿǰ�·ݵ���һ���µ����г��ڼ�¼
            List<Attendance> attendanceListPreviousMonth=new ArrayList<Attendance>();
            for(Attendance attendance:attendanceList){
                Date date=attendance.getDate();
                Calendar calendarTemp=Calendar.getInstance();
                calendarTemp.setTime(date);
                if(calendarTemp.get(Calendar.YEAR)==calendarNow.get(Calendar.YEAR)&&calendarTemp.get(Calendar.MONTH)==calendarNow.get(Calendar.MONTH)-1){
                    attendanceListPreviousMonth.add(attendance);
                }
            }
            //�����ϸ��µĳ��ڼ�¼�����㹲�м��ο���
            int absenteeism=0;
            for(Attendance attendance:attendanceListPreviousMonth){
                if(attendance.getState()==4||attendance.getState()==9){
                    absenteeism++;
                }
            }
            //�����������
            int attendanceTimes=attendanceListPreviousMonth.size();//�ϸ��µĳ��ڴ���
            if(attendanceTimes<22){
                double baseSalaryTemp=baseSalary-(baseSalary/22)*(22-attendanceTimes+absenteeism);
                BigDecimal bigDecimal=new BigDecimal(baseSalaryTemp);
                baseSalary=bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            }else {
                double baseSalaryTemp=baseSalary-(baseSalary/22)*absenteeism;
                BigDecimal bigDecimal=new BigDecimal(baseSalaryTemp);
                baseSalary=bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            //���㼨Ч����
            double performance=employee.getPosition().getSalary()*0.3;
            //����Ӱ๤��
            int overtimeTimes=0;
            for(Attendance attendance:attendanceListPreviousMonth){
                if(attendance.getState()>=5&&attendance.getState()<=9){
                    overtimeTimes++;
                }
            }
            double overtime=200*overtimeTimes;
            //���㽱���ܽ��
            List<Rewards> rewardsList=rewardsService.getAllRewardsByEMPID(employee);
            List<Rewards> rewardsListPreviousMonth=new ArrayList<Rewards>();
            //����ϸ��µ����н���
            for(Rewards rewards:rewardsList){
                Calendar calendarTemp=Calendar.getInstance();
                Date date=rewards.getTime();
                calendarTemp.setTime(date);
                if(calendarTemp.get(Calendar.YEAR)==calendarNow.get(Calendar.YEAR)&&calendarTemp.get(Calendar.MONTH)==calendarNow.get(Calendar.MONTH)-1){
                    rewardsListPreviousMonth.add(rewards);
                }
            }
            //�����ϸ��½����ܽ��
            double rewards=0;
            for(Rewards rewards1:rewardsListPreviousMonth){
                rewards+=rewards1.getMoney();
            }
            //�����籣
            double social=employee.getPosition().getSalary()*0.25;
            //������ʵ���ʣ����ұ���2λ
            double realSalaryTemp=baseSalary+performance+overtime+rewards-social;
            BigDecimal bigDecimal=new BigDecimal(realSalaryTemp);
            double realSalary=bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            //���ù���״̬��0Ϊ������1ΪԱ��Ҫ���飬2Ϊ�������
            int state=0;
            //н���е�˵���ֶβ���Ҫʹ��
            //н���е�ʱ��
            Date time=new Date();
            //��ʼ����н�ʶ���
            Salary salary=new Salary(employee,baseSalary,performance,overtime,rewards,social,realSalary,state,time);
            //����ʹ��
            //System.out.println(salary);

            //����Ա����н�ʶ���������ݿ�
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
        int state1=1;//������Ҫ����Ĺ��ʶ���
        int state2=2;//��������ɵĹ��ʶ���
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
        int state=2;//������ɸ���
        salary.setState(state);

        salaryService.modifySalaryStateById(salary);
        //����session
        int currentPage=1;
        int state1=1;//������Ҫ����Ĺ��ʶ���
        int state2=2;//��������ɵĹ��ʶ���
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
