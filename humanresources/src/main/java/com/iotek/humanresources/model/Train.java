package com.iotek.humanresources.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by grzha on 2018/7/27.
 */
public class Train implements Serializable {
    private int id;
    private String theme;
    private String content;
    private Date begintime;
    private Date endtime;
    private String address;
    private Date releasetime;
    private int state;
    private List<Employee> employeeList=new ArrayList<Employee>();

    public Train() {
    }

    public Train(String theme, String content, Date begintime, Date endtime, String address, int state) {
        this.theme = theme;
        this.content = content;
        this.begintime = begintime;
        this.endtime = endtime;
        this.address = address;
        this.state = state;
    }

    public Train(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(Date releasetime) {
        this.releasetime = releasetime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", theme='" + theme + '\'' +
                ", content='" + content + '\'' +
                ", begintime=" + begintime +
                ", endtime=" + endtime +
                ", address='" + address + '\'' +
                ", releasetime=" + releasetime +
                ", state=" + state +
                ", employeeList=" + employeeList +
                '}';
    }
}
