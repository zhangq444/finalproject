package com.iotek.humanresources.service.impl;

import com.iotek.humanresources.dao.EmployeeMapper;
import com.iotek.humanresources.model.Department;
import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by grzha on 2018/7/29.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    public List<Employee> getAllEmployee() {
        return employeeMapper.getAllEmployee();
    }

    public Employee getEmployeeById(Employee temp) {
        return employeeMapper.getEmployeeById(temp);
    }

    public List<Employee> getEmployeeByName(Employee temp) {
        return employeeMapper.getEmployeeByName(temp);
    }

    public void addNewEmployee(Employee employee) {
        employeeMapper.addNewEmployee(employee);
    }

    public List<Employee> getEmployeeByDEPID(Department temp) {
        return employeeMapper.getEmployeeByDEPID(temp);
    }

    public List<Employee> getEmployeeByPOSID(int checkEmpPosId) {
        return employeeMapper.getEmployeeByPOSID(checkEmpPosId);
    }

    public List<Employee> getEmployeeByState(Employee employee) {
        return employeeMapper.getEmployeeByState(employee);
    }

    public List<Employee> getEmployeeByDEPIDAndState(Integer selectDep, int state1, int state2) {
        return employeeMapper.getEmployeeByDEPIDAndState(selectDep,state1,state2);
    }

    public List<Employee> getEmployeeByPOSIDAndState(Integer selectPosition, int state1, int state2) {
        return employeeMapper.getEmployeeByPOSIDAndState(selectPosition,state1,state2);
    }

    public void modifyEmployeeDepartmenPositionById(Employee employee) {
        employeeMapper.modifyEmployeeDepartmenPositionById(employee);
    }

    public void modifyEmployeeStateById(Employee employee) {
        employeeMapper.modifyEmployeeStateById(employee);
    }


}
