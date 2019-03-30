package com.example.fctcontrol.data.local.daos;

import com.example.fctcontrol.data.local.entity.Student;
import com.example.fctcontrol.data.local.entity.Visits;

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
}
