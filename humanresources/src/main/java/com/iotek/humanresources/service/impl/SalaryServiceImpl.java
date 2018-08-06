package com.iotek.humanresources.service.impl;

import com.iotek.humanresources.dao.SalaryMapper;
import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.model.Salary;
import com.iotek.humanresources.service.SalaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by grzha on 2018/8/4.
 */
@Service
public class SalaryServiceImpl implements SalaryService {
    @Resource
    private SalaryMapper salaryMapper;

    public void addNewSalary(Salary salary) {
        salaryMapper.addNewSalary(salary);
    }

    public List<Salary> getAllSalary() {
        return salaryMapper.getAllSalary();
    }

    public List<Salary> getSalaryByEMPID(Employee employee) {
        return salaryMapper.getSalaryByEMPID(employee);
    }

    public List<Salary> getSalaryByEMPIDByPage(int id, int start, int end) {
        return salaryMapper.getSalaryByEMPIDByPage(id,start,end);
    }

    public Salary getSalaryById(Salary temp) {
        return salaryMapper.getSalaryById(temp);
    }

    public void modifySalaryStateById(Salary salary) {
        salaryMapper.modifySalaryStateById(salary);
    }

    public List<Salary> getReconsiderSalaryByState(int state1, int state2) {
        return salaryMapper.getReconsiderSalaryByState(state1,state2);
    }

    public List<Salary> getReconsiderSalaryByStateByPage(int state1, int state2, int start, int end) {
        return salaryMapper.getReconsiderSalaryByStateByPage(state1,state2,start,end);
    }
}
