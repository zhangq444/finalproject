package com.iotek.humanresources.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by grzha on 2018/7/27.
 */
public class Attendance implements Serializable {
    private int id;
    private Employee employee;
    private Date date;
    private Date punchin;
    private Date punchout;
    private int state;//0代表正常，1代表迟到，2代表早退，3代表迟到加早退，4代表旷工，5代表正常和加班，6代表迟到和加班，7代表早退和加班，8代表迟到早退和加班，9代表旷工和加班
    private Date overtime;

    public Attendance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getPunchin() {
        return punchin;
    }

    public void setPunchin(Date punchin) {
        this.punchin = punchin;
    }

    public Date getPunchout() {
        return punchout;
    }

    public void setPunchout(Date punchout) {
        this.punchout = punchout;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getOvertime() {
        return overtime;
    }

    public void setOvertime(Date overtime) {
        this.overtime = overtime;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", employee=" + employee +
                ", date=" + date +
                ", punchin=" + punchin +
                ", punchout=" + punchout +
                ", state=" + state +
                ", overtime=" + overtime +
                '}';
    }
}
