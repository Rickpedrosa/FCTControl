package com.example.fctcontrol.dto;

import androidx.room.ColumnInfo;

public class StudentDetail {
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "phone")
    private int phone;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "course")
    private String course;
    @ColumnInfo(name = "businessName")
    private String businessName;
    @ColumnInfo(name = "tutor")
    private String tutor;
    @ColumnInfo(name = "tutor_phone")
    private int tutor_phone;
    @ColumnInfo(name = "tutor_schedule")
    private String tutor_schedule;

    public StudentDetail(long id, String name, int phone, String email,
                         String course, String businessName, String tutor,
                         int tutor_phone, String tutor_schedule) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.course = course;
        this.businessName = businessName;
        this.tutor = tutor;
        this.tutor_phone = tutor_phone;
        this.tutor_schedule = tutor_schedule;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public int getTutor_phone() {
        return tutor_phone;
    }

    public void setTutor_phone(int tutor_phone) {
        this.tutor_phone = tutor_phone;
    }

    public String getTutor_schedule() {
        return tutor_schedule;
    }

    public void setTutor_schedule(String tutor_schedule) {
        this.tutor_schedule = tutor_schedule;
    }
}
