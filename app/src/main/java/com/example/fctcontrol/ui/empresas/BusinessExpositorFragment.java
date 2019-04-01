package com.example.fctcontrol.ui.empresas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fctcontrol.R;
import com.example.fctcontrol.data.local.AppDatabase;
import com.example.fctcontrol.data.local.entity.Business;
import com.example.fctcontrol.databinding.FragmentExpoempresasBinding;
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

public class BusinessExpositorFragment extends Fragment {

    private FragmentExpoempresasBinding b;
    private NavController navController;
    private BusinessExpositorFragmentAdapter listAdapter;
    private MainActivityViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_expoempresas, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        viewModel = ViewModelProviders.of(requireActivity(),
                new MainActivityViewModelFactory(requireActivity().getApplication(),
                        AppDatabase.getInstance(requireContext()))).get(MainActivityViewModel.class);
        navigateToAddNewCompany();
        setupRecyclerView();
        observeCompanies();
    }

    private void observeCompanies() {
        viewModel.getAllCompanies().observe(this, businessResumes -> {
            b.lblEmptyView.setVisibility(businessResumes.isEmpty() ? View.VISIBLE : View.INVISIBLE);
            listAdapter.submitList(businessResumes);
        });
    }

    private void setupRecyclerView() {
        listAdapter = new BusinessExpositorFragmentAdapter(navController);
        b.listBusiness.setHasFixedSize(true);
        b.listBusiness.setItemAnimator(new DefaultItemAnimator());
        b.listBusiness.setAdapter(listAdapter);
    }

    private void navigateToAddNewCompany() {
        b.lblEmptyView.setOnClickListener(v -> navController.navigate(R.id.businessDetailFragment));
        b.fab.setOnClickListener(v -> navController.navigate(R.id.businessDetailFragment));
    }
}
