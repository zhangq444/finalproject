package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Department;
import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.model.Interview;
import com.iotek.humanresources.model.Position;
import com.iotek.humanresources.service.DepartmentService;
import com.iotek.humanresources.service.EmployeeService;
import com.iotek.humanresources.service.InterviewService;
import com.iotek.humanresources.service.PositionService;
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
    @Resource
    private DepartmentService departmentService;
    @Resource
    private PositionService positionService;

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

    @RequestMapping("/checkEmployee")
    public String checkEmployee(int checkEmpPosId,HttpSession session){
        List<Employee> employeeList=employeeService.getEmployeeByPOSID(checkEmpPosId);

        session.setAttribute("checkEmployeeList",employeeList);
        return "checkEmployee";

    }

    @RequestMapping("/employeeManage")
    public String employeeManage(HttpSession session){
        List<Department> departmentList=departmentService.getAllDepartment();

        session.setAttribute("EmpDepartmentList",departmentList);
        session.setAttribute("selectEmployeeError","");//清空session，去掉选择错误的提示
        session.setAttribute("showEmployeeList",null);//清空session，将之前的查询记录删除
        return "employeeManage";
    }

    @RequestMapping("/selectEmployee")
    public String selectEmployee(Integer selectDep,Integer selectPosition,Integer selectOnJob,String onDep,String onDepPos,HttpSession session){
        //System.out.println(selectDep+"--"+selectPosition+"--"+selectOnJob+"--"+onDep+"--"+onDepPos);
        session.setAttribute("selectEmployeeError","");
        if(onDep!=null){
            if(selectDep==null||selectDep==0||selectOnJob==null||selectOnJob==0){
                session.setAttribute("selectEmployeeError","请选择相关条件");
                return "employeeManage";
            }
            if(selectOnJob==-1){//查询离职员工的情况
                int state=-1;
                Employee employee=new Employee();
                employee.setState(state);
                List<Employee> employeeList=employeeService.getEmployeeByState(employee);

                session.setAttribute("showEmployeeList",employeeList);
                return "employeeManage";
            }
            //按照部门查询在职员工情况
            int state1=0;//代表试用期
            int state2=1;//代表正式员工
            List<Employee> employeeList=employeeService.getEmployeeByDEPIDAndState(selectDep,state1,state2);

            session.setAttribute("showEmployeeList",employeeList);
            return "employeeManage";
        }
        if(onDepPos!=null){
            if(selectDep==null||selectDep==0||selectPosition==null||selectPosition==0||selectOnJob==null||selectOnJob==0){
                session.setAttribute("selectEmployeeError","请选择相关条件");
                return "employeeManage";
            }
            if(selectOnJob==-1){//查询离职员工的情况
                int state=-1;
                Employee employee=new Employee();
                employee.setState(state);
                List<Employee> employeeList=employeeService.getEmployeeByState(employee);
                System.out.println(employeeList);

                session.setAttribute("showEmployeeList",employeeList);
                return "employeeManage";
            }
            //按照职位查询在职员工情况
            int state1=0;//代表试用期
            int state2=1;//代表正式员工
            List<Employee> employeeList=employeeService.getEmployeeByPOSIDAndState(selectPosition,state1,state2);

            session.setAttribute("showEmployeeList",employeeList);
            return "employeeManage";
        }
        return "employeeManage";

    }

    @RequestMapping("/checkEmployeeInfo")
    public String checkEmployeeInfo(int checkInfo,int employeeInfoId,HttpSession session){
        Employee testEmp=new Employee(employeeInfoId);
        Employee testEmp1=employeeService.getEmployeeByIdNoDepPos(testEmp);//查询不带部门与职位，判断要显示详细信息的员工是否离职
        Employee testEmp2=null;
        if(testEmp1.getState()!=-1){
            testEmp2=employeeService.getEmployeeById(testEmp);
        }

        if(checkInfo==1){
            if(testEmp1.getState()==-1){
                session.setAttribute("showEmployeeInfo",testEmp1);
            }else {
                session.setAttribute("showEmployeeInfo",testEmp2);
            }
            return "showEmployeeInfo";
        }
        if(checkInfo==2){

            return "showEmployeeSalary";
        }
        if(checkInfo==3){

            return "showEmployeeTrain";
        }
        if(checkInfo==4){

            return "showEmployeePerformance";
        }
        if(checkInfo==5){

            return "showEmployeeAttendance";
        }

        return null;
    }

    @RequestMapping("/changePosition")
    public String changePosition(int changePositionId,HttpSession session){
        Employee temp=new Employee(changePositionId);
        Employee employee=employeeService.getEmployeeById(temp);

        session.setAttribute("changePositionEmployee",employee);
        List<Department> departmentList=departmentService.getAllDepartment();
        session.setAttribute("departmentList",departmentList);
        return "changePosition";
    }

    @RequestMapping("/changePosition1")
    public String changePosition1(int selectDep,int selectPosition,HttpSession session){
        if(selectDep==0||selectPosition==0){
            session.setAttribute("changePositionError","请选择部门与职位");
            return "changePosition";
        }
        Employee employee= (Employee) session.getAttribute("changePositionEmployee");
        Department temp=new Department(selectDep);
        Department departmentNew=departmentService.getDepartment(temp);
        Position temp1=new Position(selectPosition);
        Position positionNew=positionService.getPositionById(temp1);
        employee.setDepartment(departmentNew);
        employee.setPosition(positionNew);

        employeeService.modifyEmployeeDepartmenPositionById(employee);
        session.setAttribute("changePositionError","");
        return "employeeManage";

    }

    @RequestMapping("/departureEmployee")
    public String departureEmployee(int departureId,HttpSession session){
        int departmentId=0;
        int positionId=0;
        int state=-1;

        employeeService.modifyEmployeeStateDepartmentPositionById(departureId,departmentId,positionId,state);
        return "managerWelcome";
    }

    @RequestMapping("/employeeLogin")
    public String employeeLogin(String uname,String upassword,HttpSession session){
        Employee temp=new Employee(uname,upassword);
        Employee loginEmployee=employeeService.getEmployeeByUnameUpassword(temp);
        if(loginEmployee==null){
            session.setAttribute("employeeLoginError","用户名或密码错误");
            return "employeeLogin";
        }
        session.setAttribute("loginEmployee",loginEmployee);
        return "employeeWelcome";


    }

    @RequestMapping("/personInfoManage")
    public String personInfoManage(HttpSession session){
        //Employee employee= (Employee) session.getAttribute("loginEmployee");

        return "personInfo";
    }

    @RequestMapping("/modifyPersonInfo")
    public String modifyPersonInfo(){
        return "modifyPersonInfo";
    }

    @RequestMapping("/modifyPersonInfo1")
    public String modifyPersonInfo1(String phone,String email,String address,String idcard,String birthday,String school,String major,HttpSession session){
        Employee employee= (Employee) session.getAttribute("loginEmployee");
        employee.setPhone(phone);
        employee.setEmail(email);
        employee.setAddress(address);
        employee.setIdcard(idcard);
        employee.setBirthday(UtilController.StringToDate(birthday));
        employee.setSchool(school);
        employee.setMajor(major);

        employeeService.modifyEmployeeInfoById(employee);
        session.setAttribute("loginEmployee",employee);//更新session
        return "personInfo";
    }

    @RequestMapping("/checkEmployeeDetail")
    public String checkEmployeeDetail(HttpSession session){
        List<Department> departmentList=departmentService.getAllDepartment();

        session.setAttribute("checkEmployeeDepartmentList",departmentList);
        return "checkEmployeeDetail";

    }

    @RequestMapping("/checkEmployeeDetail1")
    public String checkEmployeeDetail1(int selectDep,int selectPosition,HttpSession session){
        if(selectDep==0||selectPosition==0){
            session.setAttribute("checkEmployeeDetailError","请对部门和职位进行选择");
            return "checkEmployeeDetail";
        }

        List<Employee> employeeList=employeeService.getEmployeeByPOSID(selectPosition);

        session.setAttribute("checkEmployeeDetailList",employeeList);
        return "checkEmployeeDetail";



    }


}
