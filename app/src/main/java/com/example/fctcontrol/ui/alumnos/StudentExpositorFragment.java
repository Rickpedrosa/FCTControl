package com.example.fctcontrol.ui.alumnos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.fctcontrol.R;
import com.example.fctcontrol.data.local.AppDatabase;
import com.example.fctcontrol.databinding.FragmentExpoalumnosBinding;
import com.example.fctcontrol.ui.main.MainActivityViewModel;
import com.example.fctcontrol.ui.main.MainActivityViewModelFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;

public class StudentExpositorFragment extends Fragment {

    private FragmentExpoalumnosBinding b;
    private NavController navController;
    private MainActivityViewModel viewModel;
    private StudentExpositorFragmentAdapter listAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_expoalumnos, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(requireActivity(), new MainActivityViewModelFactory(
                requireActivity().getApplication(),
                AppDatabase.getInstance(requireContext()))).get(MainActivityViewModel.class);
        navController = NavHostFragment.findNavController(this);
        navigateToAddNewStudent();
        setupRecyclerView();
        observeStudents();
    }

    private void setupRecyclerView() {
        listAdapter = new StudentExpositorFragmentAdapter(navController);
        b.listStudents.setHasFixedSize(true);
        b.listStudents.setLayoutManager(new GridLayoutManager(requireContext(), 1));
        b.listStudents.setItemAnimator(new DefaultItemAnimator());
        b.listStudents.addItemDecoration(new DividerItemDecoration(requireContext(), GridLayout.VERTICAL));
        b.listStudents.setAdapter(listAdapter);
    }

    private void observeStudents() {
        viewModel.getAllStudents().observe(this, studentResumes -> {
            b.lblEmptyView.setVisibility(studentResumes.size() == 0 ? View.VISIBLE : View.INVISIBLE);
            listAdapter.submitList(studentResumes);
        });
    }

    private void navigateToAddNewStudent() {
        viewModel.getCompaniesCount().observe(this, integer -> {
            b.fab.setOnClickListener(v -> letsNavigate(integer));
            b.lblEmptyView.setOnClickListener(v -> letsNavigate(integer));
        });
    }

    private void letsNavigate(Integer integer) {
        if (integer > 0) {
            navController.navigate(R.id.detailStudentFragment);
        } else {
            Toast.makeText(requireContext(), "Not able to add student, no companies yet", Toast.LENGTH_SHORT).show();
        }
    }
}