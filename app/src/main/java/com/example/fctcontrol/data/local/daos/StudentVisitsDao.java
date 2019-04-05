package com.example.fctcontrol.data.local.daos;

import com.example.fctcontrol.data.local.entity.StudentVisits;
import com.example.fctcontrol.dto.LastStudentVisit;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface StudentVisitsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addVisitRelation(StudentVisits studentVisits);

    @Query("SELECT st.name AS studentName, v.day, v.start_hour," +
            " v.ending_hour, sv.visitId AS vId, sv.studentId AS stId " +
            "FROM student st INNER JOIN studentVisits sv ON st.id = sv.studentId " +
            "INNER JOIN visits v ON sv.visitId = v.id")
    LiveData<List<LastStudentVisit>> getLastVisitFromStudents();

}
