package com.iotek.humanresources.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by grzha on 2018/7/27.
 */
public class Salary implements Serializable {
    private int id;
    private Employee employee;
    private double basesalary;
    private double performance;
    private double overtime;
    private double rewards;
    private double social;
    private double realsalary;
    private int state;
    private String instruction;
    private Date time;//工资结算时间，如果为8月，代表是在8月结算的工资，也就是7月的工资

    public Salary() {
    }

    public Salary(Employee employee, double basesalary, double performance, double overtime, double rewards, double social, double realsalary, int state, Date time) {
        this.employee = employee;
        this.basesalary = basesalary;
        this.performance = performance;
        this.overtime = overtime;
        this.rewards = rewards;
        this.social = social;
        this.realsalary = realsalary;
        this.state = state;
        this.time = time;
    }

    public Salary(int id) {
        this.id = id;
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

    public double getBasesalary() {
        return basesalary;
    }

    public void setBasesalary(double basesalary) {
        this.basesalary = basesalary;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

    public double getOvertime() {
        return overtime;
    }

    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }

    public double getRewards() {
        return rewards;
    }

    public void setRewards(double rewards) {
        this.rewards = rewards;
    }

    public double getSocial() {
        return social;
    }

    public void setSocial(double social) {
        this.social = social;
    }

    public double getRealsalary() {
        return realsalary;
    }

    public void setRealsalary(double realsalary) {
        this.realsalary = realsalary;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", employee=" + employee +
                ", basesalary=" + basesalary +
                ", performance=" + performance +
                ", overtime=" + overtime +
                ", rewards=" + rewards +
                ", social=" + social +
                ", realsalary=" + realsalary +
                ", state=" + state +
                ", instruction='" + instruction + '\'' +
                ", time=" + time +
                '}';
    }
}
