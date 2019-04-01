package com.example.fctcontrol.dto;

import androidx.room.ColumnInfo;

public class BusinessResume {

    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "logo")
    private String url_logo;
    @ColumnInfo(name = "phone")
    private int phone;
    @ColumnInfo(name = "name")
    private String name;

    public BusinessResume(long id, String url_logo, int phone, String name) {
        this.id = id;
        this.url_logo = url_logo;
        this.phone = phone;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl_logo() {
        return url_logo;
    }

    public void setUrl_logo(String url_logo) {
        this.url_logo = url_logo;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
