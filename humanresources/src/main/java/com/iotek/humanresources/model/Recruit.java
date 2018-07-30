package com.iotek.humanresources.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by grzha on 2018/7/25.
 */
public class Recruit implements Serializable {
    private int id;
    private Department department;
    private Position position;
    private int number;
    private double salary;
    private Date time;
    private String description;
    private int state;
    private String requirement;

    public Recruit() {
    }

    public Recruit(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
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

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    @Override
    public String toString() {
        return "Recruit{" +
                "id=" + id +
                ", department=" + department +
                ", position=" + position +
                ", number=" + number +
                ", salary=" + salary +
                ", time=" + time +
                ", description='" + description + '\'' +
                ", state=" + state +
                ", requirement='" + requirement + '\'' +
                '}';
    }
}
