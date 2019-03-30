package com.example.fctcontrol.ui.main;

import android.app.Application;

import com.example.fctcontrol.data.local.AppDatabase;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

    private final AppDatabase database;
    private final Application application;

    public MainActivityViewModelFactory(Application application, AppDatabase database) {
        this.application = application;
        this.database = database;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainActivityViewModel(application, database);
    }
}
