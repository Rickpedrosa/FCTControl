package com.example.fctcontrol.ui.alumnos;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fctcontrol.R;
import com.example.fctcontrol.base.CustomTextWatcher;
import com.example.fctcontrol.base.SimpleSelectionDialogFragment;
import com.example.fctcontrol.data.local.AppDatabase;
import com.example.fctcontrol.data.local.entity.Student;
import com.example.fctcontrol.databinding.FragmentAlumnoDetailBinding;
import com.example.fctcontrol.dto.BusinessForDialog;
import com.example.fctcontrol.dto.StudentDetail;
import com.example.fctcontrol.ui.main.MainActivityViewModel;
import com.example.fctcontrol.ui.main.MainActivityViewModelFactory;
import com.example.fctcontrol.utils.EditTextUtils;
import com.example.fctcontrol.utils.ValidationUtils;
import com.google.android.material.snackbar.Snackbar;

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
        setupMainComponents();
        obtainArguments();
        observeStudent();
        setupFabToolbar();
        setupEditTexts();
        setupDialogs();
    }

    private void setupMainComponents() {
        viewModel = ViewModelProviders.of(requireActivity(),
                new MainActivityViewModelFactory(requireActivity().getApplication(),
                        AppDatabase.getInstance(requireContext()))).get(MainActivityViewModel.class);
        navController = NavHostFragment.findNavController(this);
    }

    private void obtainArguments() {
        studentId = DetailStudentFragmentArgs.fromBundle(Objects.requireNonNull(getArguments())).getStudentId();
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

    private void setResultFromDialog(EditText txt, DialogInterface dialog, int which, String[] businessNames) {
        if (txt.getId() == b.lblStudentCourse.getId()) {
            viewModel.setStudentCourse(getResources().getStringArray(R.array.courseValues)[which]);
            Toast.makeText(requireContext(),
                    getResources().getStringArray(R.array.courseValues)[which],
                    Toast.LENGTH_SHORT).show();
            b.lblStudentCourse.setText(viewModel.getStudentCourse());
            b.lblStudentCourse.setTypeface(Typeface.DEFAULT_BOLD);
            dialog.dismiss();
        } else {
            viewModel.setStudentCompany(businessNames[which]);
            Toast.makeText(requireContext(),
                    viewModel.getStudentCompany(),
                    Toast.LENGTH_SHORT).show();
            b.lblStudentCompany.setText(viewModel.getStudentCompany());
            b.lblStudentCompany.setTypeface(Typeface.DEFAULT_BOLD);
            dialog.dismiss();
        }
    }

    private void showSelectionDialogCourse() {
        SimpleSelectionDialogFragment dialog = SimpleSelectionDialogFragment.newInstance("Title",
                getResources().getStringArray(R.array.courseValues), "Ok", 0);
        dialog.setListener(new SimpleSelectionDialogFragment.Listener() {
            @Override
            public void onConfirmSelection(DialogInterface dialog, int which) {
                setResultFromDialog(b.lblStudentCourse, dialog, which, null);
            }

            @Override
            public void onItemSelected(DialogInterface dialog, int which) {
                setResultFromDialog(b.lblStudentCourse, dialog, which, null);
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
                businessNames, "Ok", 0);
        dialog.setListener(new SimpleSelectionDialogFragment.Listener() {
            @Override
            public void onConfirmSelection(DialogInterface dialog, int which) {
                setResultFromDialog(b.lblStudentCompany, dialog, which, businessNames);
                viewModel.setStudentBusinessId(bus[which].getId());
            }

            @Override
            public void onItemSelected(DialogInterface dialog, int which) {
                setResultFromDialog(b.lblStudentCompany, dialog, which, businessNames);
                viewModel.setStudentBusinessId(bus[which].getId());
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
                !TextUtils.isEmpty(viewModel.getStudentCompany()) &&
                !TextUtils.isEmpty(viewModel.getStudentCourse()) &&
                !TextUtils.isEmpty(b.txtStudentName.getText().toString());
    }

    private Student getCurrentStudent() {
        return new Student(studentId,
                b.txtStudentName.getText().toString(),
                Integer.valueOf(b.txtStudentPhone.getText().toString()),
                b.txtStudentEmail.getText().toString(),
                viewModel.getStudentCourse(),
                viewModel.getStudentBusinessId(),
                b.txtTutorName.getText().toString(),
                Integer.parseInt(b.txtTutorPhone.getText().toString()),
                b.txtTutorSchedule.getText().toString());
    }

    private void save(Student st) {
        if (grantPermissionToExecuteQuery()) {
            if (studentId > 0) {
                viewModel.updateStudent(st);
                Snackbar.make(b.txtTutorSchedule,
                        getString(R.string.student_update, st.getName()),
                        Snackbar.LENGTH_LONG).show();
                navController.popBackStack();
            } else {
                viewModel.addStudent(st);
                Snackbar.make(b.txtTutorSchedule,
                        getString(R.string.student_add, st.getName()),
                        Snackbar.LENGTH_LONG).show();
                navController.popBackStack();
            }
        } else {
            Toast.makeText(requireContext(), getString(R.string.error_field), Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteStudent() {
        viewModel.deleteStudent(getCurrentStudent());
        navController.popBackStack();
    }

    private void setupFabToolbar() {
        b.fabtoolbarFab.setOnClickListener(v -> b.fabtoolbar.show());
        b.save.setOnClickListener(v -> save(getCurrentStudent()));
        b.delete.setOnClickListener(v -> {
            if (studentId > 0) {
                deleteStudent();
            } else {
                Toast.makeText(requireContext(), getString(R.string.error_no_delete), Toast.LENGTH_SHORT).show();
            }
        });
        b.dismiss.setOnClickListener(v -> b.fabtoolbar.hide());
    }

    private void hideFabToolbar() {
        if (b.fabtoolbar.isShown()) {
            b.fabtoolbar.hide();
        }
    }

    private void setupEditTexts() {
        editTextFocusHandling();
        editTextContentHandling();
    }

    private void editTextContentHandling() {
        b.txtStudentName.addTextChangedListener((CustomTextWatcher.onAfterText) s ->
                EditTextUtils.validateFields(
                        b.lblStudentName,
                        b.txtStudentName,
                        !TextUtils.isEmpty(b.txtStudentName.getText().toString()),
                        requireContext()));

        b.txtStudentEmail.addTextChangedListener((CustomTextWatcher.onTextChanged)
                (s, start, before, count) ->
                        EditTextUtils.validateFields(
                                b.lblStudentEmail,
                                b.txtStudentEmail,
                                ValidationUtils.isValidEmail(b.txtStudentEmail.getText().toString()),
                                requireContext()));

        b.txtStudentPhone.addTextChangedListener((CustomTextWatcher.onTextChanged)
                (s, start, before, count) ->
                        EditTextUtils.validateFields(
                                b.lblStudentPhone,
                                b.txtStudentPhone,
                                ValidationUtils.isValidPhone(b.txtStudentPhone.getText().toString()),
                                requireContext()));

        b.txtTutorPhone.addTextChangedListener((CustomTextWatcher.onTextChanged)
                (s, start, before, count) ->
                        EditTextUtils.validateFields(
                                b.lblTutorPhone,
                                b.txtTutorPhone,
                                ValidationUtils.isValidPhone(b.txtTutorPhone.getText().toString()),
                                requireContext()));

        b.txtTutorName.addTextChangedListener((CustomTextWatcher.onAfterText) s ->
                EditTextUtils.validateFields(
                        b.lblTutorName,
                        b.txtTutorName,
                        !TextUtils.isEmpty(b.txtTutorName.getText().toString()),
                        requireContext()));

        b.txtTutorSchedule.addTextChangedListener((CustomTextWatcher.onTextChanged) (s, start, before, count) ->
                EditTextUtils.validateFields(
                        b.lblTutorSchedule,
                        b.txtTutorSchedule,
                        !TextUtils.isEmpty(b.txtTutorSchedule.getText().toString()),
                        requireContext()));
    }

    private void editTextFocusHandling() {
        b.dummy.setOnClickListener(v -> hideFabToolbar());
        b.txtStudentEmail.setOnFocusChangeListener((v, hasFocus) ->
        {
            hideFabToolbar();
            EditTextUtils.changeFontOnFocus(hasFocus, b.lblStudentEmail);
        });
        b.txtStudentName.setOnFocusChangeListener((v, hasFocus) -> {
            hideFabToolbar();
            EditTextUtils.changeFontOnFocus(hasFocus, b.lblStudentName);
        });
        b.txtStudentPhone.setOnFocusChangeListener((v, hasFocus) -> {
            hideFabToolbar();
            EditTextUtils.changeFontOnFocus(hasFocus, b.lblStudentPhone);
        });
        b.txtTutorName.setOnFocusChangeListener((v, hasFocus) -> {
            hideFabToolbar();
            EditTextUtils.changeFontOnFocus(hasFocus, b.lblTutorName);
        });
        b.txtTutorPhone.setOnFocusChangeListener((v, hasFocus) -> {
            hideFabToolbar();
            EditTextUtils.changeFontOnFocus(hasFocus, b.lblTutorPhone);
        });
        b.txtTutorSchedule.setOnFocusChangeListener((v, hasFocus) -> {
            hideFabToolbar();
            EditTextUtils.changeFontOnFocus(hasFocus, b.lblTutorSchedule);
        });
    }

}
