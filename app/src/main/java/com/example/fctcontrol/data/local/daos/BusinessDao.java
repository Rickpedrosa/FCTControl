package com.example.fctcontrol.data.local.daos;

import com.example.fctcontrol.data.local.entity.Business;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BusinessDao {

    @Query("SELECT * FROM business")
    LiveData<List<Business>> getAllCompanys();

    @Query("SELECT * FROM business WHERE id = :businessId")
    LiveData<Business> getBusinessById(long businessId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addBusiness(Business business);

    @Delete
    void deleteBusiness(Business business);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateBusiness(Business business);
}
