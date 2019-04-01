package com.example.fctcontrol.dto;

import androidx.room.ColumnInfo;

public class StudentResume {

    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "course")
    private String course;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "business")
    private String business;
    @ColumnInfo(name = "name")
    private String name;

    public StudentResume(long id, String course, String email, String business, String name) {
        this.id = id;
        this.course = course;
        this.email = email;
        this.business = business;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
