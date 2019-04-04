package com.example.fctcontrol.dto;

import androidx.room.ColumnInfo;

public class BusinessForDialog {

    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "name")
    private String name;

    public BusinessForDialog(long id, String name) {
        this.id = id;
        this.name = name;
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
}
