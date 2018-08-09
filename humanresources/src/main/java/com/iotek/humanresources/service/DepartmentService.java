package com.iotek.humanresources.service;

import com.iotek.humanresources.model.Department;
import com.iotek.humanresources.model.Position;

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

    List<Position> getPositionLisByPage(List<Position> positionList, int currentPage, int pageSize);

}
