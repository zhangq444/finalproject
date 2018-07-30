package com.iotek.humanresources.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by grzha on 2018/7/27.
 */
public class Interview implements Serializable {
    private int id;
    private Recruit recruit;
    private Resume resume;
    private Date time;
    private String address;
    private Employee employee;
    private int state;
    private Users users;

    public Interview() {
    }

    public Interview(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Recruit getRecruit() {
        return recruit;
    }

    public void setRecruit(Recruit recruit) {
        this.recruit = recruit;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "id=" + id +
                ", recruit=" + recruit +
                ", resume=" + resume +
                ", time=" + time +
                ", address='" + address + '\'' +
                ", employee=" + employee +
                ", state=" + state +
                ", users=" + users +
                '}';
    }
}
