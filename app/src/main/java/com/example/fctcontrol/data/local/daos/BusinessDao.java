package com.example.fctcontrol.data.local.daos;

import com.example.fctcontrol.data.local.entity.Business;
import com.example.fctcontrol.dto.BusinessForDialog;
import com.example.fctcontrol.dto.BusinessResume;

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

    @Query("SELECT id, logo, phone, name FROM business")
    LiveData<List<BusinessResume>> getAllCompanies();

    @Query("SELECT COUNT(id) FROM business")
    LiveData<Integer> getCompaniesCount();

    @Query("SELECT id, name FROM business")
    LiveData<BusinessForDialog[]> getBusinessForDialog();

    @Query("SELECT * FROM business WHERE id = :businessId")
    LiveData<Business> getBusinessById(long businessId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addBusiness(Business business);

    @Delete
    void deleteBusiness(Business business);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateBusiness(Business business);
}
