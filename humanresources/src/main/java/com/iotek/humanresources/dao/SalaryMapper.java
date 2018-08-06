package com.iotek.humanresources.dao;

import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.model.Salary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by grzha on 2018/8/4.
 */
@Repository
public interface SalaryMapper {
    void addNewSalary(Salary salary);

    List<Salary> getAllSalary();

    List<Salary> getSalaryByEMPID(Employee employee);

    List<Salary> getSalaryByEMPIDByPage(int id, int start, int end);

    Salary getSalaryById(Salary temp);

    void modifySalaryStateById(Salary salary);

    List<Salary> getReconsiderSalaryByState(int state1, int state2);

    List<Salary> getReconsiderSalaryByStateByPage(int state1, int state2, int start, int end);

}
