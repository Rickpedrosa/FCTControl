package com.example.fctcontrol.ui.main;

import android.app.Application;

import com.example.fctcontrol.R;
import com.example.fctcontrol.base.SharedPreferencesStringLiveData;
import com.example.fctcontrol.data.local.AppDatabase;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.preference.PreferenceManager;

public class MainActivityViewModel extends AndroidViewModel {

    private final LiveData<String> startDestination;
    private final AppDatabase database;

    public MainActivityViewModel(@NonNull Application application, AppDatabase database) {
        super(application);
        this.database = database;
        startDestination = new SharedPreferencesStringLiveData(
                PreferenceManager.getDefaultSharedPreferences(application),
                application.getString(R.string.start_destiny_key),
                application.getString(R.string.start_destiny_defaultValue));

    }

    public LiveData<String> getStartDestination() {
        return startDestination;
    }
}
