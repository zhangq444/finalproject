package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.*;
import com.iotek.humanresources.service.*;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Resource
    private SalaryService salaryService;
    @Resource
    private TrainService trainService;

    @RequestMapping("/admitEmployee")
    public String admitEmployee(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,HttpSession session){
        int state=1;
        Interview interview=new Interview(state);
        List<Interview> interviewList=interviewService.getInterviewByState(interview);

        int totalNum=interviewList.size();
        int pageSize=3;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Interview> interviewList1=interviewService.getInterviewByStateByPage(interview.getState(),start,end);

        session.setAttribute("admitInterviewList",interviewList1);
        session.setAttribute("admitInterviewListTotalPages",totalPages);

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
    public String checkEmployee(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,int checkEmpPosId,HttpSession session){
        List<Employee> employeeList=employeeService.getEmployeeByPOSID(checkEmpPosId);

        int totalNum=employeeList.size();
        int pageSize=1;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Employee> employeeList1=employeeService.getEmployeeByPOSIDByPage(checkEmpPosId,start,end);

        session.setAttribute("checkEmployeeList",employeeList1);
        session.setAttribute("checkEmployeeListTotalPages",totalPages);
        session.setAttribute("checkEmployeeListCheckEmpPosId",checkEmpPosId);

        return "checkEmployee";

    }

    @RequestMapping("/employeeManage")
    public String employeeManage(HttpSession session){
        List<Department> departmentList=departmentService.getAllDepartment();

        session.setAttribute("EmpDepartmentList",departmentList);
        session.setAttribute("selectEmployeeError","");//清空session，去掉选择错误的提示
        session.setAttribute("showEmployeeList",null);//清空session，将之前的查询记录删除
        session.setAttribute("showEmployeeInfoError","");//清空session,将之前查询离职员工的错误记录删除
        session.setAttribute("comfirmationEmployeeError","");//清空session，将之前转正的错误记录删除

        return "employeeManage";
    }

    @RequestMapping("/selectEmployee")
    public String selectEmployee(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,Integer selectDep,Integer selectPosition,Integer selectOnJob,String onDep,String onDepPos,HttpSession session){
        //System.out.println(selectDep+"--"+selectPosition+"--"+selectOnJob+"--"+onDep+"--"+onDepPos);

        session.setAttribute("selectEmployeeError","");
        if(onDep!=null&&!onDep.equals("")){
            if(selectDep==null||selectDep==0||selectOnJob==null||selectOnJob==0){
                session.setAttribute("selectEmployeeError","请选择相关条件");
                return "employeeManage";
            }
            if(selectOnJob==-1){//查询离职员工的情况
                int state=-1;
                Employee employee=new Employee();
                employee.setState(state);
                List<Employee> employeeList=employeeService.getEmployeeByState(employee);

                int totalNum=employeeList.size();
                int pageSize=2;
                int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;

                List<Employee> employeeList1=employeeService.getEmployeeByStateByPage(employeeList,currentPage,pageSize);

                session.setAttribute("showEmployeeList",employeeList1);
                session.setAttribute("showEmployeeListTotalPages",totalPages);
                session.setAttribute("showEmployeeListSelectDep",selectDep);
                session.setAttribute("showEmployeeListSelectPosition",selectPosition);
                session.setAttribute("showEmployeeListSelectOnJob",selectOnJob);
                session.setAttribute("showEmployeeListOnDep",onDep);
                session.setAttribute("showEmployeeListOnDepPos",onDepPos);

                return "employeeManage";
            }
            //按照部门查询在职员工情况
            int state1=0;//代表试用期
            int state2=1;//代表正式员工
            List<Employee> employeeList=employeeService.getEmployeeByDEPIDAndState(selectDep,state1,state2);

            int totalNum=employeeList.size();
            int pageSize=2;
            int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;

            List<Employee> employeeList1=employeeService.getEmployeeByStateByPage(employeeList,currentPage,pageSize);

            session.setAttribute("showEmployeeList",employeeList1);
            session.setAttribute("showEmployeeListTotalPages",totalPages);
            session.setAttribute("showEmployeeListSelectDep",selectDep);
            session.setAttribute("showEmployeeListSelectPosition",selectPosition);
            session.setAttribute("showEmployeeListSelectOnJob",selectOnJob);
            session.setAttribute("showEmployeeListOnDep",onDep);
            session.setAttribute("showEmployeeListOnDepPos",onDepPos);
            return "employeeManage";
        }
        if(onDepPos!=null&&!onDepPos.equals("")){
            if(selectDep==null||selectDep==0||selectPosition==null||selectPosition==0||selectOnJob==null||selectOnJob==0){
                session.setAttribute("selectEmployeeError","请选择相关条件");
                return "employeeManage";
            }
            if(selectOnJob==-1){//查询离职员工的情况
                int state=-1;
                Employee employee=new Employee();
                employee.setState(state);
                List<Employee> employeeList=employeeService.getEmployeeByState(employee);
                //System.out.println(employeeList);

                int totalNum=employeeList.size();
                int pageSize=2;
                int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;

                List<Employee> employeeList1=employeeService.getEmployeeByStateByPage(employeeList,currentPage,pageSize);

                session.setAttribute("showEmployeeList",employeeList1);
                session.setAttribute("showEmployeeListTotalPages",totalPages);
                session.setAttribute("showEmployeeListSelectDep",selectDep);
                session.setAttribute("showEmployeeListSelectPosition",selectPosition);
                session.setAttribute("showEmployeeListSelectOnJob",selectOnJob);
                session.setAttribute("showEmployeeListOnDep",onDep);
                session.setAttribute("showEmployeeListOnDepPos",onDepPos);
                return "employeeManage";
            }
            //按照职位查询在职员工情况
            int state1=0;//代表试用期
            int state2=1;//代表正式员工
            List<Employee> employeeList=employeeService.getEmployeeByPOSIDAndState(selectPosition,state1,state2);

            int totalNum=employeeList.size();
            int pageSize=2;
            int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;

            List<Employee> employeeList1=employeeService.getEmployeeByStateByPage(employeeList,currentPage,pageSize);

            session.setAttribute("showEmployeeList",employeeList1);
            session.setAttribute("showEmployeeListTotalPages",totalPages);
            session.setAttribute("showEmployeeListSelectDep",selectDep);
            session.setAttribute("showEmployeeListSelectPosition",selectPosition);
            session.setAttribute("showEmployeeListSelectOnJob",selectOnJob);
            session.setAttribute("showEmployeeListOnDep",onDep);
            session.setAttribute("showEmployeeListOnDepPos",onDepPos);
            return "employeeManage";
        }
        return "employeeManage";

    }

    @RequestMapping("/checkEmployeeInfo")
    public String checkEmployeeInfo(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,int checkInfo,int employeeInfoId,HttpSession session){
        //System.out.println(currentPage+"----"+checkInfo+"----"+employeeInfoId);

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
            if(testEmp1.getState()==-1){
                session.setAttribute("showEmployeeInfoError","离职员工无法查询薪资");
                return "employeeManage";
            }
            Employee temp=new Employee(employeeInfoId);
            List<Salary> salaryList=salaryService.getSalaryByEMPID(temp);

            int totalNum=salaryList.size();
            int pageSize=2;
            int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
            int start=(currentPage-1)*pageSize+1;
            int end=pageSize*currentPage;

            List<Salary> salaryList1=salaryService.getSalaryByEMPIDByPage(employeeInfoId,start,end);

            session.setAttribute("showEmployeeSalaryList",salaryList1);
            session.setAttribute("showEmployeeSalaryListTotalPages",totalPages);
            session.setAttribute("showEmployeeSalaryListCheckInfo",checkInfo);
            session.setAttribute("showEmployeeSalaryListEmployeeInfoId",employeeInfoId);

            return "showEmployeeSalary";
        }
        if(checkInfo==3){
            if(testEmp1.getState()==-1){
                session.setAttribute("showEmployeeInfoError","离职员工无法查询培训");
                return "employeeManage";
            }
            int state=1;//代表培训已经发布
            List<Train> trainList=trainService.getTrainByEmpIdState(employeeInfoId,state);

            int totalNum=trainList.size();
            int pageSize=2;
            int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
            int start=(currentPage-1)*pageSize+1;
            int end=pageSize*currentPage;

            List<Train> trainList1=trainService.getTrainByEmpIdStateByPage(employeeInfoId,state,start,end);

            session.setAttribute("showEmployeeTrainList",trainList1);
            session.setAttribute("showEmployeeTrainListTotalPages",totalPages);
            session.setAttribute("showEmployeeTrainListCheckInfo",checkInfo);
            session.setAttribute("showEmployeeTrainListEmployeeInfoId",employeeInfoId);

            return "showEmployeeTrain";
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

    @RequestMapping("/comfirmationEmployee")
    public String comfirmationEmployee(int comfirmEmployeeId,HttpSession session){
        int state=1;
        Employee temp=new Employee(comfirmEmployeeId);
        Employee employee=employeeService.getEmployeeById(temp);
        if(employee.getState()!=0){
            session.setAttribute("comfirmationEmployeeError","员工不是试用期员工，无法转正");
            return "employeeManage";
        }

        Date hireDate=employee.getHiredate();
        Date nowDate=new Date();
        long time=(long) 1000*60*60*24*30;

        if((nowDate.getTime()-hireDate.getTime())<time){
            session.setAttribute("comfirmationEmployeeError","试用期未满，无法转正");
            return "employeeManage";
        }else {
            employee.setState(state);
            employeeService.modifyEmployeeStateById(employee);
            return "employeeManage";
        }

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
    public String checkEmployeeDetail1(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,int selectDep,int selectPosition,HttpSession session){
        if(selectDep==0||selectPosition==0){
            session.setAttribute("checkEmployeeDetailError","请对部门和职位进行选择");
            return "checkEmployeeDetail";
        }

        List<Employee> employeeList=employeeService.getEmployeeByPOSID(selectPosition);

        int totalNum=employeeList.size();
        int pageSize=1;
        int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        int start=(currentPage-1)*pageSize+1;
        int end=pageSize*currentPage;

        List<Employee> employeeList1=employeeService.getEmployeeByPOSIDByPage(selectPosition,start,end);

        session.setAttribute("checkEmployeeDetailList",employeeList1);
        session.setAttribute("checkEmployeeDetailListTotalPages",totalPages);
        session.setAttribute("checkEmployeeDetailListSelectDep",selectDep);
        session.setAttribute("checkEmployeeDetailListSelectPosition",selectPosition);
        return "checkEmployeeDetail";



    }


}
