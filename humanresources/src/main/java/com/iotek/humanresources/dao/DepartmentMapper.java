package com.iotek.humanresources.dao;

import com.iotek.humanresources.model.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by grzha on 2018/7/30.
 */
@Repository
public interface DepartmentMapper {

    List<Department> getAllDepartment();

    Department getDepartment(Department temp);

    Department getDepartmentByName(Department temp);

    void addNewDepartment(Department newDep);

    void modifyDepartmentNameById(Department department);

    void deleteDepartmentById(Department temp);

}
