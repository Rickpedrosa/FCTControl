package com.example.fctcontrol.ui.empresas;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fctcontrol.R;
import com.example.fctcontrol.base.CustomTextWatcher;
import com.example.fctcontrol.data.local.AppDatabase;
import com.example.fctcontrol.data.local.entity.Business;
import com.example.fctcontrol.databinding.FragmentBusinessDetailBinding;
import com.example.fctcontrol.ui.main.MainActivityViewModel;
import com.example.fctcontrol.ui.main.MainActivityViewModelFactory;
import com.example.fctcontrol.utils.EditTextUtils;
import com.example.fctcontrol.utils.ValidationUtils;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
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
        obtainArguments();
        viewModel = ViewModelProviders.of(requireActivity(),
                new MainActivityViewModelFactory(requireActivity().getApplication(),
                        AppDatabase.getInstance(requireContext()))).get(MainActivityViewModel.class);
        observeCompany();
        setupFabToolbar();
        setupEditTexts();
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
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle(business.getName());
    }

    private void obtainArguments() {
        businessId = BusinessDetailFragmentArgs.fromBundle(Objects.requireNonNull(getArguments())).getBusinessId();
    }

    private boolean grantPermissionToExecuteQuery() {
        return ValidationUtils.isValidCif(b.txtCif.getText().toString()) &&
                ValidationUtils.isValidEmail(b.txtEmail.getText().toString()) &&
                ValidationUtils.isValidPhone(b.txtPhone.getText().toString()) &&
                ValidationUtils.isValidUrl(b.txtUrl.getText().toString()) &&
                !TextUtils.isEmpty(b.txtBusinessAddress.getText().toString()) &&
                !TextUtils.isEmpty(b.txtBusinessName.getText().toString()) &&
                !TextUtils.isEmpty(b.txtContact.getText().toString());
    }

    private void save(Business bus) {
        if (grantPermissionToExecuteQuery()) {
            if (businessId > 0) {
                viewModel.updateCompany(bus);
                Toast.makeText(requireContext(), "Company updated!", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.addCompany(bus);
                Toast.makeText(requireContext(), "Company added!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupFabToolbar() {
        b.fabtoolbarFab.setOnClickListener(v -> b.fabtoolbar.show());
        b.save.setOnClickListener(v -> {
            save(getCurrentBusiness());
            //requireActivity().onBackPressed();
        });
        if (businessId > 0) {
            b.delete.setOnClickListener(v -> {
                viewModel.deleteCompany(getCurrentBusiness());
                //Toast.makeText(requireContext(), "Company deleted!", Toast.LENGTH_SHORT).show();
                requireActivity().onBackPressed();
            });
        } else {
            b.delete.setEnabled(false);
        }
        b.dismiss.setOnClickListener(v -> b.fabtoolbar.hide());
    }

    private void hideFabToolbar() {
        if (b.fabtoolbar.isShown()) {
            b.fabtoolbar.hide();
        }
    }

    private Business getCurrentBusiness() {
        return new Business
                (businessId,
                        b.txtBusinessName.getText().toString(),
                        b.txtCif.getText().toString(),
                        b.txtBusinessAddress.getText().toString(),
                        b.txtBusinessAddress.getText().toString(),
                        TextUtils.isEmpty(b.txtPhone.getText().toString()) ? 0 : Integer.valueOf(b.txtPhone.getText().toString()),
                        b.txtEmail.getText().toString(),
                        b.txtUrl.getText().toString(),
                        b.txtContact.getText().toString());
    }

    private void setupEditTexts() {
        editTextFocusHandling();
        editTextContentHandling();
        editTextClickHandling();
    }

    private void editTextFocusHandling() {
        b.txtBusinessName.setOnFocusChangeListener((v, hasFocus) ->
                EditTextUtils.changeFontOnFocus(hasFocus, b.lblBusinessName));
        b.txtCif.setOnFocusChangeListener((v, hasFocus) ->
                EditTextUtils.changeFontOnFocus(hasFocus, b.lblCif));
        b.txtBusinessAddress.setOnFocusChangeListener((v, hasFocus) ->
                EditTextUtils.changeFontOnFocus(hasFocus, b.lblBusinessAddress));
        b.txtPhone.setOnFocusChangeListener((v, hasFocus) ->
                EditTextUtils.changeFontOnFocus(hasFocus, b.lblPhone));
        b.txtEmail.setOnFocusChangeListener((v, hasFocus) ->
                EditTextUtils.changeFontOnFocus(hasFocus, b.lblEmail));
        b.txtUrl.setOnFocusChangeListener((v, hasFocus) ->
                EditTextUtils.changeFontOnFocus(hasFocus, b.lblUrl));
        b.txtContact.setOnFocusChangeListener((v, hasFocus) ->
                EditTextUtils.changeFontOnFocus(hasFocus, b.lblContact));
    }

    private void editTextContentHandling() {
        b.txtBusinessName.addTextChangedListener((CustomTextWatcher.onAfterText) s -> {
            if (TextUtils.isEmpty(b.txtBusinessName.getText().toString())) {
                b.txtBusinessName.setError(getString(R.string.invalid_data));
                b.lblBusinessName.setEnabled(false);
            } else {
                b.lblBusinessName.setEnabled(true);
            }
        });

        b.txtEmail.addTextChangedListener((CustomTextWatcher.onTextChanged)
                (s, start, before, count) ->
                        EditTextUtils.validateFields(
                                b.lblEmail,
                                b.txtEmail,
                                ValidationUtils.isValidEmail(b.txtEmail.getText().toString()),
                                requireContext()));

        b.txtPhone.addTextChangedListener((CustomTextWatcher.onTextChanged)
                (s, start, before, count) ->
                        EditTextUtils.validateFields(
                                b.lblPhone,
                                b.txtPhone,
                                ValidationUtils.isValidPhone(b.txtPhone.getText().toString()),
                                requireContext()));

        b.txtBusinessAddress.addTextChangedListener((CustomTextWatcher.onAfterText) s ->
                EditTextUtils.validateFields(
                        b.lblBusinessAddress,
                        b.txtBusinessAddress,
                        !TextUtils.isEmpty(b.txtBusinessAddress.getText().toString()),
                        requireContext()));

        b.txtUrl.addTextChangedListener((CustomTextWatcher.onTextChanged) (s, start, before, count) ->
                EditTextUtils.validateFields(
                        b.lblUrl,
                        b.txtUrl,
                        ValidationUtils.isValidUrl(b.txtUrl.getText().toString()),
                        requireContext()));

        b.txtCif.addTextChangedListener((CustomTextWatcher.onTextChanged) (s, start, before, count) ->
                EditTextUtils.validateFields(
                        b.lblCif,
                        b.txtCif,
                        ValidationUtils.isValidCif(b.txtCif.getText().toString()),
                        requireContext()));

        b.txtContact.addTextChangedListener((CustomTextWatcher.onTextChanged) (s, start, before, count) ->
                EditTextUtils.validateFields(
                        b.lblContact,
                        b.txtContact,
                        !TextUtils.isEmpty(b.txtContact.getText().toString()),
                        requireContext()));
    }

    private void editTextClickHandling() {
        b.txtContact.setOnClickListener(v -> hideFabToolbar());
        b.txtCif.setOnClickListener(v -> hideFabToolbar());
        b.txtEmail.setOnClickListener(v -> hideFabToolbar());
        b.txtPhone.setOnClickListener(v -> hideFabToolbar());
        b.txtBusinessAddress.setOnClickListener(v -> hideFabToolbar());
        b.txtBusinessName.setOnClickListener(v -> hideFabToolbar());
        b.txtUrl.setOnClickListener(v -> hideFabToolbar());
    }
}
