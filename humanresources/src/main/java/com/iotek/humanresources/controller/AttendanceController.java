package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Attendance;
import com.iotek.humanresources.model.Department;
import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.model.Rewards;
import com.iotek.humanresources.service.AttendanceService;
import com.iotek.humanresources.service.DepartmentService;
import com.iotek.humanresources.service.EmployeeService;
import com.iotek.humanresources.service.RewardsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by grzha on 2018/8/3.
 */
@Controller
public class AttendanceController {
    @Resource
    private AttendanceService attendanceService;
    @Resource
    private RewardsService rewardsService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private EmployeeService employeeService;

    @RequestMapping("/clockin")
    public String clockin(HttpSession session){
        session.setAttribute("punchinError","");
        session.setAttribute("punchoutError","");
        session.setAttribute("overtimeError","");

        return "clockin";
    }

    @RequestMapping("/punchin")
    public String punchin(HttpSession session){
        Employee employee= (Employee) session.getAttribute("loginEmployee");
        List<Attendance> attendanceList=attendanceService.getAttendanceByEMPID(employee);
        //System.out.println(attendanceList);
        Calendar nowCalendar=Calendar.getInstance();
        Attendance attendance=null;
        for(Attendance temp:attendanceList){
            Date date=temp.getDate();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            if(calendar.get(Calendar.YEAR)==nowCalendar.get(Calendar.YEAR)&&calendar.get(Calendar.MONTH)==nowCalendar.get(Calendar.MONTH)&&calendar.get(Calendar.DATE)==nowCalendar.get(Calendar.DATE)){
                attendance=temp;
            }
        }

        if(attendance!=null){
            session.setAttribute("punchinError","�ϰ��Ѵ򿨣������ٴ�");
            return "clockin";
        }

        int state=3;
        /*if(nowCalendar.get(Calendar.HOUR_OF_DAY)<9){
            state=3;
        }else if(nowCalendar.get(Calendar.HOUR_OF_DAY)>=9&&nowCalendar.get(Calendar.HOUR_OF_DAY)<12){
            state=3;
        }else {
            state=3;
        }*/

        Attendance attendance1=new Attendance();
        attendance1.setEmployee(employee);
        attendance1.setDate(new Date());
        attendance1.setPunchin(new Date());
        attendance1.setState(state);

        attendanceService.addPunintime(attendance1);
        return "clockin";
    }

    @RequestMapping("/punchout")
    public String punchout(HttpSession session){
        Employee employee= (Employee) session.getAttribute("loginEmployee");
        List<Attendance> attendanceList=attendanceService.getAttendanceByEMPID(employee);
        Calendar nowCalendar=Calendar.getInstance();
        //��õ���򿨼�¼
        Attendance attendance=null;
        for(Attendance temp:attendanceList){
            Date date=temp.getDate();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            if(calendar.get(Calendar.YEAR)==nowCalendar.get(Calendar.YEAR)&&calendar.get(Calendar.MONTH)==nowCalendar.get(Calendar.MONTH)&&calendar.get(Calendar.DATE)==nowCalendar.get(Calendar.DATE)){
                attendance=temp;
            }
        }
        //����򿨼�¼˵���ϰ�û�д򿨣��Ƚ����ϰ��
        if(attendance==null){
            session.setAttribute("punchoutError","��δ���ϰ࿨�����ȴ��ϰ࿨");
            return "clockin";
        }

        //����ϰ�ʱ����°�ʱ�䣬�Ƚ���������������ֱ���д���
        Date date=attendance.getPunchin();
        Calendar punchin=Calendar.getInstance();
        punchin.setTime(date);
        int state1=0;
        if(punchin.get(Calendar.HOUR_OF_DAY)<9){
            state1=0;
        }else if(punchin.get(Calendar.HOUR_OF_DAY)>=9&&punchin.get(Calendar.HOUR_OF_DAY)<12){
            state1=1;
        }else {
            state1=4;
        }

        int state2=0;
        if(nowCalendar.get(Calendar.HOUR_OF_DAY)>=18){
            state2=0;
        }else if(nowCalendar.get(Calendar.HOUR_OF_DAY)>=15&&nowCalendar.get(Calendar.HOUR_OF_DAY)<18){
            state2=2;
        }else {
            state2=4;
        }

        /*System.out.println(punchin.get(Calendar.HOUR_OF_DAY));
        System.out.println(nowCalendar.get(Calendar.HOUR_OF_DAY));
        System.out.println(state1+"---"+state2);*/

        int state=0;
        if(state1==0&&state2==0){
            state=0;
            String explain="�ٵ�";
            int empId=employee.getId();
            rewardsService.deleteRewardsLate(explain,empId);
        }else if(state1==1&&state2==0){
            state=1;
            String explain="�ٵ�";
            int empId=employee.getId();
            Rewards rewards=rewardsService.getTodayLateRewards(explain,empId);

            if(rewards==null){
                Rewards rewards1=new Rewards();
                rewards1.setMoney(-100);
                rewards1.setTime(new Date());
                rewards1.setEmployee(employee);
                rewards1.setExplain("�ٵ�");
                rewardsService.addNewRewards(rewards1);
            }
        }else if(state1==0&&state2==2){
            state=2;
            String explain="����";
            int empId=employee.getId();
            Rewards rewards=rewardsService.getTodayEarlyRewards(explain,empId);

            if(rewards==null){
                Rewards rewards1=new Rewards();
                rewards1.setMoney(-100);
                rewards1.setTime(new Date());
                rewards1.setEmployee(employee);
                rewards1.setExplain("����");
                rewardsService.addNewRewards(rewards1);
            }
        }else if(state1==1&&state2==2){
            state=3;
            String explain1="�ٵ�";
            int empId=employee.getId();
            Rewards rewards=rewardsService.getTodayLateRewards(explain1,empId);

            if(rewards==null){
                Rewards rewards1=new Rewards();
                rewards1.setMoney(-100);
                rewards1.setTime(new Date());
                rewards1.setEmployee(employee);
                rewards1.setExplain("�ٵ�");
                rewardsService.addNewRewards(rewards1);
            }

            String explain2="����";
            Rewards rewardsTemp=rewardsService.getTodayEarlyRewards(explain2,empId);

            if(rewardsTemp==null){
                Rewards rewards1=new Rewards();
                rewards1.setMoney(-100);
                rewards1.setTime(new Date());
                rewards1.setEmployee(employee);
                rewards1.setExplain("����");
                rewardsService.addNewRewards(rewards1);
            }

        }else if(state1==4||state2==4){
            state=4;
            String explain1="�ٵ�";
            String explain2="����";
            int empId=employee.getId();
            rewardsService.deleteRewardsLate(explain1,empId);
            rewardsService.deleteRewardsLate(explain2,empId);
        }

        //�޸��ϰ���γɵĳ��ڼ�¼,��Ҫ�޸��°��ʱ��ͽ����״̬
        attendance.setPunchout(nowCalendar.getTime());
        attendance.setState(state);

        attendanceService.modifyAttendancePunchouttimeState(attendance);

        return "clockin";
    }

    @RequestMapping("/overtime")
    public String overtime(HttpSession session){
        Employee employee= (Employee) session.getAttribute("loginEmployee");
        Attendance attendance=attendanceService.getAttendanceTodayByEMPID(employee);

        if(attendance==null||attendance.getPunchout()==null){
            session.setAttribute("overtimeError","���Ƚ��н���������");
            return "clockin";
        }

        Calendar nowCalendar=Calendar.getInstance();
        if(nowCalendar.get(Calendar.HOUR_OF_DAY)<21){
            session.setAttribute("overtimeError","û����ɼӰ�ʱ�䣬����Ӱ�");
            return "clockin";
        }

        int state=attendance.getState();
        if(state>=0&&state<=4){
            state+=5;
        }
        attendance.setOvertime(nowCalendar.getTime());
        attendance.setState(state);
        attendanceService.modifyAttendanceOvertimeStateById(attendance);
        return "clockin";
    }

    @RequestMapping("/checkAttendance")
    public String checkAttendance(HttpSession session){
        Employee employee= (Employee) session.getAttribute("loginEmployee");
        List<Attendance> attendanceList=attendanceService.getAttendanceByEMPID(employee);

        session.setAttribute("showPersonAttendanceList",attendanceList);
        return "checkAttendance";
    }

    @RequestMapping("/checkEmployeeAttendance")
    public String checkEmployeeAttendance(HttpSession session){
        List<Department> departmentList=departmentService.getAllDepartment();

        session.setAttribute("EmpDepartmentList",departmentList);
        return "checkEmployeeAttendance";

    }

    @RequestMapping("/checkEmployeeAttendance1")
    public String checkEmployeeAttendance1(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage, int selectDep, int selectPosition, HttpSession session){
        if(selectDep==0||selectPosition==0){
            session.setAttribute("checkEmployeeAttendanceError","��ѡ������");
            return "checkEmployeeAttendance";
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


        session.setAttribute("checkEmployeeAttendanceList",employeeList1);
        session.setAttribute("checkEmployeeAttendanceTotalPages",totalPages);
        return "checkEmployeeAttendance";
    }

    @RequestMapping("/checkEmployeeAttendanceInfo")
    public String checkEmployeeAttendanceInfo(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,int checkEmployeeAttendanceId,HttpSession session){
        Employee employee=new Employee(checkEmployeeAttendanceId);
        List<Attendance> attendanceList=attendanceService.getAttendanceByEMPID(employee);

        int totalNum=attendanceList.size();
        int pageSize=1;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Attendance> attendanceList1=attendanceService.getAttendanceByEmpIDByPage(employee.getId(),start,end);

        session.setAttribute("showCheckEmployeeAttendanceList",attendanceList1);
        session.setAttribute("showCheckEmployeeAttendanceListTotalPages",totalPages);
        return "showEmployeeAttendanceInfo";

    }





}
