package com.example.fctcontrol.ui.alumnos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fctcontrol.R;
import com.example.fctcontrol.data.local.AppDatabase;
import com.example.fctcontrol.data.local.entity.Student;
import com.example.fctcontrol.databinding.FragmentAlumnoDetailBinding;
import com.example.fctcontrol.dto.StudentDetail;
import com.example.fctcontrol.ui.main.MainActivityViewModel;
import com.example.fctcontrol.ui.main.MainActivityViewModelFactory;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class DetailStudentFragment extends Fragment {

    private FragmentAlumnoDetailBinding b;
    private long studentId;
    private MainActivityViewModel viewModel;
    private NavController navController;

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
        viewModel = ViewModelProviders.of(requireActivity(),
                new MainActivityViewModelFactory(requireActivity().getApplication(),
                        AppDatabase.getInstance(requireContext()))).get(MainActivityViewModel.class);
        navController = NavHostFragment.findNavController(this);
        obtainArguments();
        observeStudent();
        setupFab();
        focusHandlingOfEditTexts();
    }

    private void observeStudent() {
        viewModel.getStudent(studentId).observe(this, this::setValues);
    }

    private void setValues(StudentDetail st) {
        b.txtTutorSchedule.setText(st.getTutor_schedule());
        b.txtTutorPhone.setText(String.valueOf(st.getTutor_phone()));
        b.txtTutorName.setText(st.getTutor());
        b.txtStudentPhone.setText(String.valueOf(st.getPhone()));
        b.txtStudentName.setText(st.getName());
        b.txtStudentCompany.setText(st.getBusinessName());
    }

    private void obtainArguments() {
        studentId = DetailStudentFragmentArgs.fromBundle(Objects.requireNonNull(getArguments())).getStudentId();
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
    }

    private void hideFabToolbar() {
        if (b.fabtoolbar.isShown()) {
            b.fabtoolbar.hide();
        }
    }

    private void focusHandlingOfEditTexts() {
        b.dummy.setOnClickListener(v -> hideFabToolbar());
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
