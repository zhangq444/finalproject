package com.iotek.humanresources.dao;

import com.iotek.humanresources.model.Department;
import com.iotek.humanresources.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by grzha on 2018/7/29.
 */
@Repository
public interface EmployeeMapper {
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

    void modifyEmployeeStateDepartmentPositionById(int departureId, int departmentId, int positionId, int state);

    Employee getEmployeeByIdNoDepPos(Employee temp);

}
