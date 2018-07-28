package com.iotek.humanresources.model;

import java.io.Serializable;

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

    public Salary() {
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
                '}';
    }
}
