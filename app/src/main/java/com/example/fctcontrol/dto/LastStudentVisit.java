package com.example.fctcontrol.dto;

import androidx.room.ColumnInfo;

public class LastStudentVisit {

    @ColumnInfo(name = "stId")
    private long studentId;
    @ColumnInfo(name = "vId")
    private long visitId;
    @ColumnInfo(name = "studentName")
    private String studentName;
    @ColumnInfo(name = "day")
    private String day;
    @ColumnInfo(name = "start_hour")
    private String start_hour;
    @ColumnInfo(name = "ending_hour")
    private String ending_hour;

    public LastStudentVisit(long studentId, long visitId, String studentName,
                            String day, String start_hour, String ending_hour) {
        this.studentId = studentId;
        this.visitId = visitId;
        this.studentName = studentName;
        this.day = day;
        this.start_hour = start_hour;
        this.ending_hour = ending_hour;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getVisitId() {
        return visitId;
    }

    public void setVisitId(long visitId) {
        this.visitId = visitId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
}
