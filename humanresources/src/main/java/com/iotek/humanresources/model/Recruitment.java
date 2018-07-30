package com.iotek.humanresources.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by grzha on 2018/7/27.
 */
public class Recruitment implements Serializable {
    private int id;
    private Users users;
    private Resume resume;
    private int read;
    private int invite;
    private Recruit recruit;
    private Date time;

    public Recruitment() {
    }

    public Recruitment(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Recruitment(Users users, Resume resume, int read, int invite, Recruit recruit, Date time) {
        this.users = users;
        this.resume = resume;
        this.read = read;
        this.invite = invite;
        this.recruit = recruit;
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getInvite() {
        return invite;
    }

    public void setInvite(int invite) {
        this.invite = invite;
    }

    public Recruit getRecruit() {
        return recruit;
    }

    public void setRecruit(Recruit recruit) {
        this.recruit = recruit;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Recruitment{" +
                "id=" + id +
                ", users=" + users +
                ", resume=" + resume +
                ", read=" + read +
                ", invite=" + invite +
                ", recruit=" + recruit +
                ", time=" + time +
                '}';
    }
}
