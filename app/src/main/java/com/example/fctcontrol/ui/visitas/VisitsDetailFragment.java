package com.example.fctcontrol.ui.visitas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fctcontrol.R;
import com.example.fctcontrol.base.DatePickerDialogFragment;
import com.example.fctcontrol.base.TimePickerDialogFragment;
import com.example.fctcontrol.data.local.AppDatabase;
import com.example.fctcontrol.databinding.FragmentVisitaDetailBinding;
import com.example.fctcontrol.dto.StudentVisitDetail;
import com.example.fctcontrol.ui.main.MainActivityViewModel;
import com.example.fctcontrol.ui.main.MainActivityViewModelFactory;
import com.example.fctcontrol.utils.TimeCustomUtils;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class VisitsDetailFragment extends Fragment {

    private FragmentVisitaDetailBinding b;
    private MainActivityViewModel viewModel;
    private NavController navController;
    private long studentId;
    private long visitId;
    //TODO Custom dialog to pick students

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_visita_detail, container, false);
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
        observeLiveData();
        setupDialogs();
    }

    private void obtainArguments() {
        VisitsDetailFragmentArgs arguments = VisitsDetailFragmentArgs.fromBundle(requireArguments());
        studentId = arguments.getStudentId();
        visitId = arguments.getVisitId();
        //Si visitid es 0, es para añadir. Si visitid != 0 es para ver/actualizar. Siempre vendra
        //con un studentid distinto de 0
    }

    private void observeLiveData() {
        if (visitId != 0) {
            viewModel.getVisitById(visitId, studentId).observe(this, this::setupValues);
        } else {
            viewModel.getStudentName(studentId).observe(this, s -> {
                Date today = TimeCustomUtils.TODAY;
                setupCalendarValue(TimeCustomUtils.obtainStringFromDate(today));
                setupStartTime(TimeCustomUtils.getTime(today, 0));
                setupEndingTime(TimeCustomUtils.getTime(today, 1));
                b.lblValueStudent.setText(s);
            });
        }
    }

    private void setupValues(StudentVisitDetail st) {
        b.txtComments.setText(st.getCommentary());
        b.lblValueStudent.setText(st.getStudentName());

        setupCalendarValue(st.getDay());
        setupStartTime(st.getStart_hour());
        setupEndingTime(st.getEnding_hour());
    }

    private void setupCalendarValue(String date) {
        viewModel.setCalendarDayOfVisit(date); //Al entrar en la App
        b.lblValueDay.setText(date);
        if (viewModel.getDayOfVisit() != null && !viewModel.getCalendarDayOfVisit().equals(viewModel.getDayOfVisit())) {
            viewModel.setCalendarDayOfVisit(viewModel.getDayOfVisit()); //Al usar el diálogo para mantener la fecha en él
            b.lblValueDay.setText(viewModel.getDayOfVisit());
        }
    }

    private void setupStartTime(String time) {
        viewModel.setStartTimeUtil(time);
        b.lblStartTimeValue.setText(time);
        if (viewModel.getStartTime() != null && !viewModel.getStartTimeUtil().equals(viewModel.getStartTime())) {
            viewModel.setStartTimeUtil(viewModel.getStartTime());
            b.lblStartTimeValue.setText(viewModel.getStartTime());
        }
    }

    private void setupEndingTime(String time) {
        viewModel.setEndingTimeUtil(time);
        b.lblEndTimeValue.setText(time);
        if (viewModel.getEndingTime() != null && !viewModel.getEndingTimeUtil().equals(viewModel.getEndingTime())) {
            viewModel.setEndingTimeUtil(viewModel.getEndingTime());
            b.lblEndTimeValue.setText(viewModel.getEndingTime());
        }
    }

    //TODO CAMBIAR NEWINSTANCE() POR NEWINSTANCE(PARAMS) EN VISITID=0
    private void setupDialogs() {
        setDatePickerDialog();
        setTimePickerDialog();
    }

    private void setDatePickerDialog() {
        b.btnDay.setOnClickListener(v ->
                setupDateDialog(TimeCustomUtils.getDayMonthYear(viewModel.getCalendarDayOfVisit())));
    }

    private void setupDateDialog(int[] studentVisitDateValues) {
        DatePickerDialogFragment datePick;
        datePick = DatePickerDialogFragment.newInstance(
                studentVisitDateValues[2],
                studentVisitDateValues[1],
                studentVisitDateValues[0]
        );
        datePick.setListener((view, year, month, dayOfMonth) -> {
            viewModel.setDayOfVisit(String.valueOf(year),
                    String.valueOf(month + 1),
                    String.valueOf(dayOfMonth));
            b.lblValueDay.setText(viewModel.getDayOfVisit());
            viewModel.setCalendarDayOfVisit(viewModel.getDayOfVisit());
        });
        datePick.show(requireFragmentManager(), "DatePickerDialogFragment");
    }

    private void setTimePickerDialog() {
        b.btnStartTime.setOnClickListener(v ->
                setupStartTimeDialog(TimeCustomUtils.getHoursMinutes(viewModel.getStartTimeUtil())));
        b.btnEndTime.setOnClickListener(v ->
                setupEndingTimeDialog(TimeCustomUtils.getHoursMinutes(viewModel.getEndingTimeUtil())));
    }

    private void setupEndingTimeDialog(int[] hoursMinutes) {
        TimePickerDialogFragment timeDialog;
        timeDialog =
                TimePickerDialogFragment.newInstance(hoursMinutes[0], hoursMinutes[1], true);
        timeDialog.setListener((view, hourOfDay, minute) -> {
            viewModel.setEndingTime(String.valueOf(hourOfDay), String.valueOf(minute));
            b.lblEndTimeValue.setText(viewModel.getEndingTime());
            viewModel.setEndingTimeUtil(viewModel.getEndingTime());
        });
        timeDialog.show(requireFragmentManager(), "TimePickerDialogFragment");
    }

    private void setupStartTimeDialog(int[] hoursMinutes) {
        TimePickerDialogFragment timeDialog;
        timeDialog =
                TimePickerDialogFragment.newInstance(hoursMinutes[0], hoursMinutes[1], true);
        timeDialog.setListener((view, hourOfDay, minute) -> {
            viewModel.setStartTime(String.valueOf(hourOfDay), String.valueOf(minute));
            b.lblStartTimeValue.setText(viewModel.getStartTime());
            viewModel.setStartTimeUtil(viewModel.getStartTime());
        });
        timeDialog.show(requireFragmentManager(), "TimePickerDialogFragment");
    }


}
