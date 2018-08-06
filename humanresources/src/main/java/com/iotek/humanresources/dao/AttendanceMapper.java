package com.iotek.humanresources.dao;

import com.iotek.humanresources.model.Attendance;
import com.iotek.humanresources.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by grzha on 2018/8/3.
 */
@Repository
public interface AttendanceMapper {
    List<Attendance> getAttendanceByEMPID(Employee employee);

    void addPunintime(Attendance attendance1);

    void modifyAttendancePunchouttimeState(Attendance attendance);

    Attendance getAttendanceTodayByEMPID(Employee employee);

    void modifyAttendanceOvertimeStateById(Attendance attendance);

    List<Attendance> getAttendanceByEmpIDByPage(int id, int start, int end);

}
