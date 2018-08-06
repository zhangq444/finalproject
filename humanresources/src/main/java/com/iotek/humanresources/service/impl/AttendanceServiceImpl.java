package com.iotek.humanresources.service.impl;

import com.iotek.humanresources.dao.AttendanceMapper;
import com.iotek.humanresources.model.Attendance;
import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.service.AttendanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by grzha on 2018/8/3.
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Resource
    private AttendanceMapper attendanceMapper;

    public List<Attendance> getAttendanceByEMPID(Employee employee) {
        return attendanceMapper.getAttendanceByEMPID(employee);
    }

    public void addPunintime(Attendance attendance1) {
        attendanceMapper.addPunintime(attendance1);
    }

    public void modifyAttendancePunchouttimeState(Attendance attendance) {
        attendanceMapper.modifyAttendancePunchouttimeState(attendance);
    }

    public Attendance getAttendanceTodayByEMPID(Employee employee) {
        return attendanceMapper.getAttendanceTodayByEMPID(employee);
    }

    public void modifyAttendanceOvertimeStateById(Attendance attendance) {
        attendanceMapper.modifyAttendanceOvertimeStateById(attendance);
    }

    public List<Attendance> getAttendanceByEmpIDByPage(int id, int start, int end) {
        return attendanceMapper.getAttendanceByEmpIDByPage(id,start,end);
    }
}
