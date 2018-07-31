package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Department;
import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.model.Position;
import com.iotek.humanresources.service.DepartmentService;
import com.iotek.humanresources.service.EmployeeService;
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

    @RequestMapping("/showDepartment")
    public String showDepartment(HttpSession session){
        List<Department> departmentList=departmentService.getAllDepartment();
        //System.out.println(departmentList);

        session.setAttribute("showDepartmentList",departmentList);
        session.setAttribute("showPositionList",null);//ˢ��session����֮ǰ�Ĳ�ѯ��¼���
        session.setAttribute("deleteDepartmentError","");//ˢ��session����֮ǰ�Ĵ����ɾ�����ŵļ�¼���
        session.setAttribute("showSelectDepartment",null);//ˢ��session����֮ǰ����ְλʱѡ���ʲô��������ְλ��¼���
        session.setAttribute("addPositionError","");//ˢ��session����֮ǰ����ְλ�Ĵ�����Ϣ���
        session.setAttribute("deletePositionError","");//ˢ��session����֮ǰɾ��ְλ�Ĵ�����Ϣ���
        return "showDepartment";
    }

    @RequestMapping("/checkPosition")
    public String checkPosition(int selectDep,String showButton,String modifyButton,String addButton,String deleteButton,HttpSession session ){
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

            session.setAttribute("showPositionList",positionList);
            session.setAttribute("showSelectDepartment",department);
            session.setAttribute("addPositionError","");//ˢ��session����֮ǰ����ְλ�Ĵ�����Ϣ���
            session.setAttribute("deletePositionError","");//ˢ��session����֮ǰɾ��ְλ�Ĵ�����Ϣ���
            return "showDepartment";
        }
        if(addButton!=null){
            session.setAttribute("addDepartmentError","");
            session.setAttribute("addPositionError","");//ˢ��session����֮ǰ����ְλ�Ĵ�����Ϣ���
            session.setAttribute("deletePositionError","");//ˢ��session����֮ǰɾ��ְλ�Ĵ�����Ϣ���
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
            session.setAttribute("addPositionError","");//ˢ��session����֮ǰ����ְλ�Ĵ�����Ϣ���
            session.setAttribute("deletePositionError","");//ˢ��session����֮ǰɾ��ְλ�Ĵ�����Ϣ���
            return "modifyDepartment";
        }
        if(deleteButton!=null){


            //����������Ҫ���






            if(selectDep==0){
                return "showDepartment";
            }
            Department temp=new Department(selectDep);
            List<Employee> employeeList=employeeService.getEmployeeByDEPID(temp);
            if(employeeList!=null&&employeeList.size()>0){
                session.setAttribute("deleteDepartmentError","�ò�������Ա��������ɾ��");
                return "showDepartment";
            }
            departmentService.deleteDepartmentById(temp);
            //����session
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
            session.setAttribute("addDepartmentError","�������ظ�");
            return "addDepartment";
        }
        Department newDep=new Department();
        newDep.setName(departmentName);
        newDep.setTime(new Date());
        departmentService.addNewDepartment(newDep);
        //����department��session
        List<Department> departmentList=departmentService.getAllDepartment();
        session.setAttribute("showDepartmentList",departmentList);

        session.setAttribute("addPositionError","");//ˢ��session����֮ǰ����ְλ�Ĵ�����Ϣ���
        session.setAttribute("deletePositionError","");//ˢ��session����֮ǰɾ��ְλ�Ĵ�����Ϣ���
        return "showDepartment";
    }

    @RequestMapping("/modifyDepartment")
    public String modifyDepartment(String departmentNewName,HttpSession session){
        Department department= (Department) session.getAttribute("modifyDepartment");
        department.setName(departmentNewName);
        Department temp=departmentService.getDepartmentByName(department);
        if(temp!=null){
            session.setAttribute("modifyDepartmentError","�²������ظ�");
            return "modifyDepartment";
        }
        departmentService.modifyDepartmentNameById(department);

        //����department��session
        List<Department> departmentList=departmentService.getAllDepartment();
        session.setAttribute("showDepartmentList",departmentList);
        session.setAttribute("addPositionError","");//ˢ��session����֮ǰ����ְλ�Ĵ�����Ϣ���
        session.setAttribute("deletePositionError","");//ˢ��session����֮ǰɾ��ְλ�Ĵ�����Ϣ���

        return "showDepartment";
    }



}
