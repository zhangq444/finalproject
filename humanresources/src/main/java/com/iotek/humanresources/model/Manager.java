package com.iotek.humanresources.model;

import java.io.Serializable;

/**
 * Created by grzha on 2018/7/27.
 */
public class Manager implements Serializable {
    private int id;
    private String name;
    private String password;
    private Employee employee;

    public Manager() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", employee=" + employee +
                '}';
    }
}
