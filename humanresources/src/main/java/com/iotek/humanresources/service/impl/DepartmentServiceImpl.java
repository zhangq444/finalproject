package com.iotek.humanresources.service.impl;

import com.iotek.humanresources.dao.DepartmentMapper;
import com.iotek.humanresources.model.Department;
import com.iotek.humanresources.model.Position;
import com.iotek.humanresources.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by grzha on 2018/7/30.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;


    public List<Department> getAllDepartment() {
        return departmentMapper.getAllDepartment();
    }

    public Department getDepartment(Department temp) {
        return departmentMapper.getDepartment(temp);
    }

    public Department getDepartmentByName(Department temp) {
        return departmentMapper.getDepartmentByName(temp);
    }

    public void addNewDepartment(Department newDep) {
        departmentMapper.addNewDepartment(newDep);
    }

    public void modifyDepartmentNameById(Department department) {
        departmentMapper.modifyDepartmentNameById(department);
    }

    public void deleteDepartmentById(Department temp) {
        departmentMapper.deleteDepartmentById(temp);
    }

    public List<Position> getPositionLisByPage(List<Position> positionList, int currentPage, int pageSize) {
        List<Position> positionList1=new ArrayList<Position>();
        int max=(currentPage-1)*pageSize+pageSize>positionList.size()?positionList.size():(currentPage-1)*pageSize+pageSize;
        for(int i=(currentPage-1)*pageSize;i<max;i++){
            positionList1.add(positionList.get(i));
        }
        return positionList1;
    }
}
