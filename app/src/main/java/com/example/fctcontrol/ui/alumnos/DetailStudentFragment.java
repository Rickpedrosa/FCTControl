package com.example.fctcontrol.ui.alumnos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fctcontrol.R;
import com.example.fctcontrol.databinding.FragmentAlumnoDetailBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class DetailStudentFragment extends Fragment {

    private FragmentAlumnoDetailBinding b;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_alumno_detail, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupFab();
        focusHandlingOfEditTexts();
    }

    private void setupFab() {
        b.fabtoolbarFab.setOnClickListener(v -> b.fabtoolbar.show());
        b.save.setOnClickListener(v -> {
            //TODO Insert in database
            b.fabtoolbar.hide();
        });
        b.delete.setOnClickListener(v -> {
            //TODO Delete in database
            b.fabtoolbar.hide();
        });
        b.dismiss.setOnClickListener(v -> b.fabtoolbar.hide());
        b.dummy.setOnClickListener(v -> hideFabToolbar());
    }

    private void hideFabToolbar() {
        if (b.fabtoolbar.isShown()) {
            b.fabtoolbar.hide();
        }
    }

    private void focusHandlingOfEditTexts() {
        b.txtStudentEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                hideFabToolbar();
            }
        });
        b.txtStudentName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                hideFabToolbar();
            }
        });
        b.txtStudentPhone.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                hideFabToolbar();
            }
        });
        b.txtTutorName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                hideFabToolbar();
            }
        });
        b.txtTutorPhone.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                hideFabToolbar();
            }
        });
        b.txtTutorSchedule.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                hideFabToolbar();
            }
        });
    }

}
