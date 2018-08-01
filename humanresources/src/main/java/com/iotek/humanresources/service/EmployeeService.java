package com.iotek.humanresources.service;

import com.iotek.humanresources.model.Department;
import com.iotek.humanresources.model.Employee;

import java.util.List;

/**
 * Created by grzha on 2018/7/29.
 */
public interface EmployeeService {
    List<Employee> getAllEmployee();

    Employee getEmployeeById(Employee temp);

    List<Employee> getEmployeeByName(Employee temp);

    void addNewEmployee(Employee employee);

    List<Employee> getEmployeeByDEPID(Department temp);

    List<Employee> getEmployeeByPOSID(int checkEmpPosId);

    List<Employee> getEmployeeByState(Employee employee);

    List<Employee> getEmployeeByDEPIDAndState(Integer selectDep, int state1, int state2);

    List<Employee> getEmployeeByPOSIDAndState(Integer selectPosition, int state1, int state2);

    void modifyEmployeeDepartmenPositionById(Employee employee);

    void modifyEmployeeStateById(Employee employee);

}
