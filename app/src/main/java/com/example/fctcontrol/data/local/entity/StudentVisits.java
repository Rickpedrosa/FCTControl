package com.example.fctcontrol.data.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "studentVisits",
        indices = {@Index(value = "visitId")},
        primaryKeys = {"studentId", "visitId"},
        foreignKeys = {
                @ForeignKey(entity = Student.class,
                        parentColumns = "id",
                        childColumns = "studentId",
                        onUpdate = CASCADE,
                        onDelete = CASCADE
                ),
                @ForeignKey(entity = Visits.class,
                        parentColumns = "id",
                        childColumns = "visitId",
                        onUpdate = CASCADE,
                        onDelete = CASCADE
                )})
public class StudentVisits {

    @ColumnInfo(name = "studentId")
    private long studentId;
    @ColumnInfo(name = "visitId")
    private long visitId;

    public StudentVisits(long studentId, long visitId) {
        this.studentId = studentId;
        this.visitId = visitId;
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
}
