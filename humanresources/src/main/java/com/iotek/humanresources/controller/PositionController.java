package com.iotek.humanresources.controller;

import com.iotek.humanresources.model.Department;
import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.model.Position;
import com.iotek.humanresources.service.DepartmentService;
import com.iotek.humanresources.service.EmployeeService;
import com.iotek.humanresources.service.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by grzha on 2018/7/30.
 */
@Controller
public class PositionController {
    @Resource
    private PositionService positionService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private EmployeeService employeeService;

    @RequestMapping("/addNewPosition")
    public String addNewPosition(Integer addPosDepId, HttpSession session){
        if(addPosDepId==null){
            session.setAttribute("addPositionError","����ѡ���������ְλ");
            return "showDepartment";
        }

        Department temp=new Department(addPosDepId);
        Department department=departmentService.getDepartment(temp);

        session.setAttribute("addPosDepartment",department);
        session.setAttribute("addPositionError","");//ˢ��session����֮ǰ����ְλ�Ĵ�����Ϣ���
        session.setAttribute("deletePositionError","");//ˢ��session����֮ǰɾ��ְλ�Ĵ�����Ϣ���
        return "addNewPosition";
    }

    @RequestMapping("/addNewPosition1")
    public String addNewPosition1(String positionName,double positionSalary,HttpSession session ){
        Department department= (Department) session.getAttribute("addPosDepartment");
        Position position=new Position(positionName,department,positionSalary);
        Position temp=positionService.getPositionByName(position);
        if(temp!=null){
            session.setAttribute("addPositionNameError","ְλ���ظ�");
            return "addNewPosition";
        }

        positionService.addNewPosition(position);
        return "showDepartment";
    }

    @RequestMapping("/modifyPosition")
    public String modifyPosition(int modifyPositionInfoId,HttpSession session){
        Position temp=new Position(modifyPositionInfoId);
        Position position=positionService.getPositionById(temp);

        session.setAttribute("modifyPositionObj",position);
        return "modifyPosition";
    }

    @RequestMapping("/modifyPosition1")
    public String modifyPosition1(String newPositionName,double newPositionSalary,HttpSession session){
        Position position= (Position) session.getAttribute("modifyPositionObj");
        position.setName(newPositionName);
        position.setSalary(newPositionSalary);
        Position temp=positionService.getPositionByName(position);
        if(temp!=null){
            session.setAttribute("modifyPositionError","�޸ĵ�ְλ���ظ�");
            return "modifyPosition";
        }
        positionService.modifyPositionNameSalaryById(position);
        //����session
        List<Department> departmentList=departmentService.getAllDepartment();
        session.setAttribute("showDepartmentList",departmentList);
        return "showDepartment";

    }

    @RequestMapping("/deletePosition")
    public String deletePosition(int deletePositionId,HttpSession session){
        List<Employee> employeeList=employeeService.getEmployeeByPOSID(deletePositionId);
        if(employeeList!=null&&employeeList.size()>0){
            session.setAttribute("deletePositionError","�ò��Ż���Ա�����޷�ɾ��");
            return "showDepartment";
        }

        Position position=new Position(deletePositionId);
        positionService.deletePositionById(position);
        return "managerWelcome";


    }


}
