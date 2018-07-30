package com.iotek.humanresources.service.impl;

import com.iotek.humanresources.dao.EmployeeMapper;
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


}