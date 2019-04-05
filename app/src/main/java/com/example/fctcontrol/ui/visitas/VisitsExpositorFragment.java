package com.example.fctcontrol.ui.visitas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fctcontrol.R;
import com.example.fctcontrol.data.local.AppDatabase;
import com.example.fctcontrol.databinding.FragmentExpovisitasBinding;
import com.example.fctcontrol.dto.LastStudentVisit;
import com.example.fctcontrol.ui.empresas.BusinessExpositorFragmentAdapter;
import com.example.fctcontrol.ui.main.MainActivityViewModel;
import com.example.fctcontrol.ui.main.MainActivityViewModelFactory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;

public class VisitsExpositorFragment extends Fragment {

    private FragmentExpovisitasBinding b;
    private NavController navController;
    private MainActivityViewModel viewModel;
    private VisitsExpositorFragmentAdapter listAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_expovisitas, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(requireActivity(),
                new MainActivityViewModelFactory(requireActivity().getApplication(),
                        AppDatabase.getInstance(requireContext()))).get(MainActivityViewModel.class);
        navController = NavHostFragment.findNavController(this);
        b.fab.setOnClickListener(v -> navController.navigate(R.id.visitsDetailFragment));
        setupRecyclerView();
        observeStudents();
    }

    private void setupRecyclerView() {
        listAdapter = new VisitsExpositorFragmentAdapter(navController);
        b.listVisits.setHasFixedSize(true);
        b.listVisits.setItemAnimator(new DefaultItemAnimator());
        b.listVisits.setAdapter(listAdapter);
    }

    private void observeStudents() {
        viewModel.getLastVisitFromStudents().observe(this, lastStudentVisits -> {
            b.lblEmptyView.setVisibility(lastStudentVisits.size() == 0 ? View.VISIBLE : View.INVISIBLE);
            listAdapter.submitList(lastStudentVisits);
        });
    }
}
