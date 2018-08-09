package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Department;
import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.model.Position;
import com.iotek.humanresources.service.DepartmentService;
import com.iotek.humanresources.service.EmployeeService;
import com.iotek.humanresources.service.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by grzha on 2018/7/30.
 */
@Controller
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private PositionService positionService;

    @RequestMapping("/showDepartment")
    public String showDepartment(HttpSession session){
        List<Department> departmentList=departmentService.getAllDepartment();
        //System.out.println(departmentList);

        session.setAttribute("showDepartmentList",departmentList);
        session.setAttribute("showPositionList",null);//刷新session，将之前的查询记录清空
        session.setAttribute("deleteDepartmentError","");//刷新session，将之前的错误的删除部门的记录清空
        session.setAttribute("showSelectDepartment",null);//刷新session，将之前增加职位时选择的什么部门增加职位记录清空
        session.setAttribute("addPositionError","");//刷新session，将之前增加职位的错误信息清空
        session.setAttribute("deletePositionError","");//刷新session，将之前删除职位的错误信息清空
        return "showDepartment";
    }

    @RequestMapping("/checkPosition")
    public String checkPosition(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,int selectDep,String showButton,String modifyButton,String addButton,String deleteButton,HttpSession session ){
        if(showButton!=null){
            if(selectDep==0){
                return "showDepartment";
            }
            Department temp=new Department(selectDep);
            Department department=departmentService.getDepartment(temp);
            //System.out.println(department);
            List<Position> positionList=department.getPositionList();
            for(Position p:positionList){
                p.setDepartment(department);
            }

            int totalNum=positionList.size();
            int pageSize=2;
            int totalPages=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;

            List<Position> positionList1=departmentService.getPositionLisByPage(positionList,currentPage,pageSize);

            session.setAttribute("showPositionList",positionList1);
            session.setAttribute("showPositionListTotalPages",totalPages);
            session.setAttribute("showSelectDepartment",department);
            session.setAttribute("addPositionError","");//刷新session，将之前增加职位的错误信息清空
            session.setAttribute("deletePositionError","");//刷新session，将之前删除职位的错误信息清空
            return "showDepartment";
        }
        if(addButton!=null){
            session.setAttribute("addDepartmentError","");
            session.setAttribute("addPositionError","");//刷新session，将之前增加职位的错误信息清空
            session.setAttribute("deletePositionError","");//刷新session，将之前删除职位的错误信息清空
            return "addDepartment";
        }
        if(modifyButton!=null){
            if(selectDep==0){
                return "showDepartment";
            }
            Department temp=new Department(selectDep);
            Department department=departmentService.getDepartment(temp);

            session.setAttribute("modifyDepartment",department);
            session.setAttribute("modifyDepartmentError","");
            session.setAttribute("addPositionError","");//刷新session，将之前增加职位的错误信息清空
            session.setAttribute("deletePositionError","");//刷新session，将之前删除职位的错误信息清空
            return "modifyDepartment";
        }
        if(deleteButton!=null){
            if(selectDep==0){
                return "showDepartment";
            }
            Department temp=new Department(selectDep);
            List<Employee> employeeList=employeeService.getEmployeeByDEPID(temp);
            if(employeeList!=null&&employeeList.size()>0){
                session.setAttribute("deleteDepartmentError","该部门仍有员工，不能删除");
                return "showDepartment";
            }
            departmentService.deleteDepartmentById(temp);
            positionService.deletePositionByDepId(selectDep);

            //更新session
            List<Department> departmentList=departmentService.getAllDepartment();
            session.setAttribute("showDepartmentList",departmentList);
            return "showDepartment";
        }

        return null;
    }



















    @RequestMapping("/addDepartment")
    public String addDepartment(String departmentName,HttpSession session){
        Department temp=new Department(departmentName);
        Department department=departmentService.getDepartmentByName(temp);

        if(department!=null){
            session.setAttribute("addDepartmentError","部门名重复");
            return "addDepartment";
        }
        Department newDep=new Department();
        newDep.setName(departmentName);
        newDep.setTime(new Date());
        departmentService.addNewDepartment(newDep);
        //更新department的session
        List<Department> departmentList=departmentService.getAllDepartment();
        session.setAttribute("showDepartmentList",departmentList);

        session.setAttribute("addPositionError","");//刷新session，将之前增加职位的错误信息清空
        session.setAttribute("deletePositionError","");//刷新session，将之前删除职位的错误信息清空
        return "showDepartment";
    }

    @RequestMapping("/modifyDepartment")
    public String modifyDepartment(String departmentNewName,HttpSession session){
        Department department= (Department) session.getAttribute("modifyDepartment");
        department.setName(departmentNewName);
        Department temp=departmentService.getDepartmentByName(department);
        if(temp!=null){
            session.setAttribute("modifyDepartmentError","新部门名重复");
            return "modifyDepartment";
        }
        departmentService.modifyDepartmentNameById(department);

        //更新department的session
        List<Department> departmentList=departmentService.getAllDepartment();
        session.setAttribute("showDepartmentList",departmentList);
        session.setAttribute("addPositionError","");//刷新session，将之前增加职位的错误信息清空
        session.setAttribute("deletePositionError","");//刷新session，将之前删除职位的错误信息清空

        return "showDepartment";
    }



}
