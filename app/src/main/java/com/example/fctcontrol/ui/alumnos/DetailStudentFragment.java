package com.example.fctcontrol.ui.alumnos;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fctcontrol.R;
import com.example.fctcontrol.base.SimpleSelectionDialogFragment;
import com.example.fctcontrol.data.local.AppDatabase;
import com.example.fctcontrol.databinding.FragmentAlumnoDetailBinding;
import com.example.fctcontrol.dto.BusinessForDialog;
import com.example.fctcontrol.dto.StudentDetail;
import com.example.fctcontrol.ui.main.MainActivityViewModel;
import com.example.fctcontrol.ui.main.MainActivityViewModelFactory;
import com.example.fctcontrol.utils.ValidationUtils;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
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
        setupFabToolbar();
        setupEditTexts();
        setupDialogs();
    }

    private void setupDialogs() {
        b.btnStudentCourse.setOnClickListener(v -> showSelectionDialogCourse());
        viewModel.getBusinessForDialog().observe(this, businessForDialogs ->
                b.btnStudentCompany.setOnClickListener(v -> {
                    if (businessForDialogs.length > 0) {
                        showSelectionDialogCompany(businessForDialogs);
                    } else {
                        Toast.makeText(requireContext(), "No companies added yet", Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    private void showSelectionDialogCourse() {
        SimpleSelectionDialogFragment dialog = SimpleSelectionDialogFragment.newInstance("Title",
                getResources().getStringArray(R.array.courseValues),
                "Ok", 0);
        dialog.setListener(new SimpleSelectionDialogFragment.Listener() {
            @Override
            public void onConfirmSelection(DialogInterface dialog, int which) {
                viewModel.setStudentCourse(getResources().getStringArray(R.array.courseValues)[which]);
                Toast.makeText(requireContext(),
                        getResources().getStringArray(R.array.courseValues)[which],
                        Toast.LENGTH_SHORT).show();
                b.lblStudentCourse.setText(viewModel.getStudentCourse());
            }

            @Override
            public void onItemSelected(DialogInterface dialog, int which) {

            }
        });
        dialog.show(requireActivity().getSupportFragmentManager(), "SimpleSelectionDialogFragment");
    }

    private void showSelectionDialogCompany(BusinessForDialog[] bus) {
        String[] businessNames = new String[bus.length];
        for (int i = 0; i < bus.length; i++) {
            businessNames[i] = bus[i].getName();
        }

        SimpleSelectionDialogFragment dialog = SimpleSelectionDialogFragment.newInstance("Title",
                businessNames,
                "Ok", 0);
        dialog.setListener(new SimpleSelectionDialogFragment.Listener() {
            @Override
            public void onConfirmSelection(DialogInterface dialog, int which) {
                viewModel.setStudentCompany(businessNames[which]);
                Toast.makeText(requireContext(),
                        viewModel.getStudentCompany(),
                        Toast.LENGTH_SHORT).show();
                b.lblStudentCompany.setText(viewModel.getStudentCompany());
            }

            @Override
            public void onItemSelected(DialogInterface dialog, int which) {

            }
        });
        dialog.show(requireActivity().getSupportFragmentManager(), "SimpleSelectionDialogFragment");

    }

    private void observeStudent() {
        if (studentId > 0) {
            viewModel.getStudent(studentId).observe(this, this::setValues);
        }
    }

    private void setValues(StudentDetail st) {
        b.txtTutorSchedule.setText(st.getTutor_schedule());
        b.txtTutorPhone.setText(String.valueOf(st.getTutor_phone()));
        b.txtTutorName.setText(st.getTutor());
        b.txtStudentPhone.setText(String.valueOf(st.getPhone()));
        b.txtStudentName.setText(st.getName());
        b.lblStudentCompany.setText(st.getBusinessName());
        b.lblStudentCourse.setText(st.getCourse());
        b.txtStudentEmail.setText(st.getEmail());
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle(st.getName());
    }

    private boolean grantPermissionToExecuteQuery() {
        return ValidationUtils.isValidEmail(b.txtStudentEmail.getText().toString()) &&
                ValidationUtils.isValidPhone(b.txtStudentPhone.getText().toString()) &&
                ValidationUtils.isValidPhone(b.txtTutorPhone.getText().toString()) &&
                !TextUtils.isEmpty(b.txtTutorSchedule.getText().toString()) &&
                !TextUtils.isEmpty(b.txtTutorName.getText().toString()) &&
                !TextUtils.isEmpty(b.lblStudentCompany.getText().toString()) &&
                !TextUtils.isEmpty(b.lblStudentCourse.getText().toString()) &&
                !TextUtils.isEmpty(b.txtStudentName.getText().toString());
    }

    private void obtainArguments() {
        studentId = DetailStudentFragmentArgs.fromBundle(Objects.requireNonNull(getArguments())).getStudentId();
    }

    private void setupFabToolbar() {
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

    private void setupEditTexts() {
        focusHandlingOfEditTexts();
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
