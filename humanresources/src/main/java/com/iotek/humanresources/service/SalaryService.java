package com.iotek.humanresources.service;

import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.model.Salary;

import java.util.List;

/**
 * Created by grzha on 2018/8/4.
 */
public interface SalaryService {
    void addNewSalary(Salary salary);

    List<Salary> getAllSalary();

    List<Salary> getSalaryByEMPID(Employee employee);

    List<Salary> getSalaryByEMPIDByPage(int id, int start, int end);

    Salary getSalaryById(Salary temp);

    void modifySalaryStateById(Salary salary);

    List<Salary> getReconsiderSalaryByState(int state1, int state2);

    List<Salary> getReconsiderSalaryByStateByPage(int state1, int state2, int start, int end);

}
