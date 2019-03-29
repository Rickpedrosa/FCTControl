package com.example.fctcontrol.data.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "visits")
public class Visits {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "day")
    private String day;
    @ColumnInfo(name = "start_hour")
    private String start_hour;
    @ColumnInfo(name = "ending_hour")
    private String ending_hour;
    @ColumnInfo(name = "commentary")
    private String commentary;

    public Visits(long id, String day, String start_hour,
                  String ending_hour, String commentary) {
        this.id = id;
        this.day = day;
        this.start_hour = start_hour;
        this.ending_hour = ending_hour;
        this.commentary = commentary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(String start_hour) {
        this.start_hour = start_hour;
    }

    public String getEnding_hour() {
        return ending_hour;
    }

    public void setEnding_hour(String ending_hour) {
        this.ending_hour = ending_hour;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}
