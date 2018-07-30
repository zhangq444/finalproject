package com.iotek.humanresources.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by grzha on 2018/7/27.
 */
public class Department implements Serializable {
    private int id;
    private String name;
    private Date time;
    private List<Position> positionList=new ArrayList<Position>();

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Department(int id) {
        this.id = id;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", positionList=" + positionList +
                '}';
    }
}
