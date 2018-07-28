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
    private int state;

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

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", employee=" + employee +
                ", date=" + date +
                ", punchin=" + punchin +
                ", punchout=" + punchout +
                ", state=" + state +
                '}';
    }
}
