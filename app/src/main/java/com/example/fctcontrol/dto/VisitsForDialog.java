package com.example.fctcontrol.dto;

import androidx.room.ColumnInfo;

public class VisitsForDialog {

    @ColumnInfo(name = "visitId")
    private long visitId;
    @ColumnInfo(name = "studentId")
    private long studentId;
    @ColumnInfo(name = "studentName")
    private String studentName;
    @ColumnInfo(name = "visitDay")
    private String visitDay;
    @ColumnInfo(name = "start_hour")
    private String start_hour;
    @ColumnInfo(name = "ending_hour")
    private String ending_hour;

    public VisitsForDialog(long visitId, long studentId, String studentName,
                           String visitDay, String start_hour, String ending_hour) {
        this.visitId = visitId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.visitDay = visitDay;
        this.start_hour = start_hour;
        this.ending_hour = ending_hour;
    }

    public long getVisitId() {
        return visitId;
    }

    public void setVisitId(long visitId) {
        this.visitId = visitId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getVisitDay() {
        return visitDay;
    }

    public void setVisitDay(String visitDay) {
        this.visitDay = visitDay;
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
