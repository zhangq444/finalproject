package com.iotek.humanresources.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by grzha on 2018/7/25.
 */
public class Recruit implements Serializable {
    private int id;
    private String department;
    private String position;
    private int number;
    private int salary;
    private Date time;
    private String description;
    private int state;

    public Recruit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Recruit{" +
                "id=" + id +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", number=" + number +
                ", salary=" + salary +
                ", time=" + time +
                ", description='" + description + '\'' +
                ", state=" + state +
                '}';
    }
}
