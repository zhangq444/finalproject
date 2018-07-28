package com.iotek.humanresources.model;

import java.io.Serializable;

/**
 * Created by grzha on 2018/7/28.
 */
public class EmpToTr implements Serializable {
    private int id;
    private int empid;
    private int tid;

    public EmpToTr() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "EmpToTr{" +
                "id=" + id +
                ", empid=" + empid +
                ", tid=" + tid +
                '}';
    }
}
