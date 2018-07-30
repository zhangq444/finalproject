package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.model.Interview;
import com.iotek.humanresources.service.EmployeeService;
import com.iotek.humanresources.service.InterviewService;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by grzha on 2018/7/29.
 */
@Controller
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;
    @Resource
    private InterviewService interviewService;

    @RequestMapping("/admitEmployee")
    public String admitEmployee(HttpSession session){
        int state=1;
        Interview interview=new Interview(state);
        List<Interview> interviewList=interviewService.getInterviewByState(interview);

        session.setAttribute("admitInterviewList",interviewList);
        return "admitEmployee";
    }

    @RequestMapping("/admitEmployee1")
    public String admitEmployee1(int admitEmployeeId,HttpSession session){
        Interview interview=null;
        List<Interview> interviewList= (List<Interview>) session.getAttribute("admitInterviewList");
        for(Interview temp:interviewList){
            if(temp.getId()==admitEmployeeId){
                interview=temp;
            }
        }

        session.setAttribute("newEmployeeInterview",interview);
        return "admitEmployee1";
    }

    @RequestMapping("/admitEmployee2")
    public String admitEmployee2(String uname,String upassword,HttpSession session){
        Employee temp=new Employee();
        temp.setUname(uname);
        List<Employee> employeeList=employeeService.getEmployeeByName(temp);
        System.out.println(employeeList);
        if(employeeList.size()!=0){
            session.setAttribute("admitEmployeeError","用户名重复");
            return "admitEmployee1";
        }

        Interview interview= (Interview) session.getAttribute("newEmployeeInterview");
        Employee employee=new Employee();
        employee.setName(interview.getResume().getName());
        employee.setGender(interview.getResume().getGender());
        employee.setPhone(interview.getResume().getPhone());
        employee.setEmail(interview.getResume().getEmail());
        employee.setAddress(interview.getResume().getAddress());
        employee.setIdcard(interview.getResume().getIdcard());
        employee.setBirthday(interview.getResume().getBirthday());
        employee.setSchool(interview.getResume().getSchool());
        employee.setMajor(interview.getResume().getMajor());
        employee.setEducation(interview.getResume().getEducation());
        employee.setDepartment(interview.getRecruit().getDepartment());
        employee.setPosition(interview.getRecruit().getPosition());
        employee.setHiredate(new Date());
        employee.setState(0);
        employee.setUname(uname);
        employee.setUpassword(upassword);

        employeeService.addNewEmployee(employee);

        int state=2;//代表已经录取，interview的属性
        interview.setState(state);
        interviewService.modifyInterviewStateById(interview);

        return "managerWelcome";

    }




}
