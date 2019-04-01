package com.example.fctcontrol.ui.empresas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fctcontrol.R;
import com.example.fctcontrol.data.local.AppDatabase;
import com.example.fctcontrol.data.local.entity.Business;
import com.example.fctcontrol.databinding.FragmentBusinessDetailBinding;
import com.example.fctcontrol.ui.main.MainActivityViewModel;
import com.example.fctcontrol.ui.main.MainActivityViewModelFactory;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class BusinessDetailFragment extends Fragment {

    private FragmentBusinessDetailBinding b;
    private MainActivityViewModel viewModel;
    private long businessId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_business_detail, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //TODO Llamar al codigo de abajo junto con Picasso para traer el drawable
//        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity()))
//                .getSupportActionBar())
//                .setTitle("titile");
        obtainArguments();
        viewModel = ViewModelProviders.of(requireActivity(),
                new MainActivityViewModelFactory(requireActivity().getApplication(),
                        AppDatabase.getInstance(requireContext()))).get(MainActivityViewModel.class);
        observeCompany();
    }

    private void observeCompany() {
        if (businessId > 0) {
            viewModel.getBusiness(businessId).observe(this, this::setValues);
        }
    }

    private void setValues(Business business) {
        b.txtCif.setText(business.getCif());
        b.txtBusinessAddress.setText(business.getAddress());
        b.txtBusinessName.setText(business.getName());
        b.txtContact.setText(business.getContact());
        b.txtPhone.setText(String.valueOf(business.getPhone()));
        b.txtEmail.setText(business.getEmail());
        b.txtUrl.setText(business.getUrl_logo());
    }

    private void obtainArguments() {
        businessId = BusinessDetailFragmentArgs.fromBundle(Objects.requireNonNull(getArguments())).getBusinessId();
    }
}
