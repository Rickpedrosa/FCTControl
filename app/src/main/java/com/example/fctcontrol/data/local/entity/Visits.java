package com.example.fctcontrol.data.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "visits",
        indices = {@Index(value = {"day", "studentId"}), @Index(value = "studentId")},
        foreignKeys = @ForeignKey(entity = Student.class,
                parentColumns = "id",
                childColumns = "studentId",
                onUpdate = CASCADE,
                onDelete = CASCADE))
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
    @ColumnInfo(name = "studentId")
    private long studentId;

    public Visits(long id, String day, String start_hour, String ending_hour, String commentary, long studentId) {
        this.id = id;
        this.day = day;
        this.start_hour = start_hour;
        this.ending_hour = ending_hour;
        this.commentary = commentary;
        this.studentId = studentId;
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

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
}
