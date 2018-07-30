package com.iotek.humanresources.service;

import com.iotek.humanresources.model.Department;

import java.util.List;

/**
 * Created by grzha on 2018/7/30.
 */
public interface DepartmentService {

    List<Department> getAllDepartment();

    Department getDepartment(Department temp);

    Department getDepartmentByName(Department temp);

    void addNewDepartment(Department newDep);

    void modifyDepartmentNameById(Department department);

    void deleteDepartmentById(Department temp);

}
