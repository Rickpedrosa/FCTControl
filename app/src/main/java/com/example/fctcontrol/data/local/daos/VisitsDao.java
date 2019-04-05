package com.example.fctcontrol.data.local.daos;

import com.example.fctcontrol.data.local.entity.Visits;
import com.example.fctcontrol.dto.LastStudentVisit;
import com.example.fctcontrol.dto.VisitsForDialog;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface VisitsDao {
    @Query("SELECT * FROM visits")
    LiveData<List<Visits>> getAllVisits();

    @Query("SELECT * FROM visits WHERE id = :visitId")
    LiveData<Visits> getVisitById(long visitId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addVisit(Visits visit);

    @Delete
    void deleteVisit(Visits visit);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateVisit(Visits visit);

    @Query("SELECT st.id AS stId, v.id AS vId, st.name AS studentName, MAX(v.day) AS maxDay, v.start_hour, v.ending_hour " +
            "FROM student st LEFT JOIN visits v ON v.studentId = st.id " +
            "GROUP BY st.id " +
            "ORDER BY v.day DESC")
    LiveData<List<LastStudentVisit>> getLastVisitFromAllStudents();

    @Query("SELECT v.id AS visitId, st.id AS studentId, st.name AS studentName, v.day AS visitDay, v.start_hour, v.ending_hour " +
            "FROM visits v INNER JOIN student st ON v.studentId = st.id " +
            "WHERE st.id = :studentId")
    LiveData<List<VisitsForDialog>> getAllVisitsByStudentId(long studentId);
}
