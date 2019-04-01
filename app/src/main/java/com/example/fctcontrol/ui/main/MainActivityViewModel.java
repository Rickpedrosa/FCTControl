package com.example.fctcontrol.ui.main;

import android.app.Application;

import com.example.fctcontrol.R;
import com.example.fctcontrol.base.SharedPreferencesStringLiveData;
import com.example.fctcontrol.data.Repository;
import com.example.fctcontrol.data.RepositoryImpl;
import com.example.fctcontrol.data.local.AppDatabase;
import com.example.fctcontrol.data.local.entity.Business;
import com.example.fctcontrol.dto.BusinessResume;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.preference.PreferenceManager;

public class MainActivityViewModel extends AndroidViewModel {

    private final LiveData<String> startDestination;
    private final Repository repo;

    MainActivityViewModel(@NonNull Application application, AppDatabase database) {
        super(application);
        this.repo = new RepositoryImpl(database.studentDao(), database.businessDao(), database.visitsDao(),
                database.studentVisitsDao());
        startDestination = new SharedPreferencesStringLiveData(
                PreferenceManager.getDefaultSharedPreferences(application),
                application.getString(R.string.start_destiny_key),
                application.getString(R.string.start_destiny_defaultValue));
    }

    LiveData<String> getStartDestination() {
        return startDestination;
    }

    /*BUSINESS FRAGMENTS*/
    public LiveData<List<BusinessResume>> getAllCompanies() {
        return repo.getAllCompanies();
    }

    public void addCompany(Business business) {
        repo.addBusiness(business);
    }

    public void updateCompany(Business business) {
        repo.updateBusiness(business);
    }

    public void deleteCompany(Business business) {
        repo.deleteBusiness(business);
    }

    public LiveData<Business> getBusiness(long id) {
        return repo.getBusinessById(id);
    }
    /*END OF BUSINESS FRAGMENTS*/
}
