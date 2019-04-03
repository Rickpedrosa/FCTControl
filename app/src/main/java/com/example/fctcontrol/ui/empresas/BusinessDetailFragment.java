package com.example.fctcontrol.ui.empresas;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.example.fctcontrol.R;
import com.example.fctcontrol.base.CustomTextWatcher;
import com.example.fctcontrol.data.local.AppDatabase;
import com.example.fctcontrol.data.local.entity.Business;
import com.example.fctcontrol.databinding.FragmentBusinessDetailBinding;
import com.example.fctcontrol.ui.main.MainActivityViewModel;
import com.example.fctcontrol.ui.main.MainActivityViewModelFactory;
import com.example.fctcontrol.utils.EditTextUtils;
import com.example.fctcontrol.utils.KeyboardUtils;
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

public class BusinessDetailFragment extends Fragment {

    private FragmentBusinessDetailBinding b;
    private MainActivityViewModel viewModel;
    private long businessId;
    private NavController navController;

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
        navController = NavHostFragment.findNavController(this);
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
                Snackbar.make(b.lblBusinessName,
                        requireContext().getString(R.string.company_update, b.txtBusinessName.getText().toString()),
                        Snackbar.LENGTH_LONG).show();
                navController.popBackStack();
            } else {
                viewModel.addCompany(bus);
                Snackbar.make(b.lblBusinessName,
                        requireContext().getString(R.string.company_add, b.txtBusinessName.getText().toString()),
                        Snackbar.LENGTH_LONG).show();
                navController.popBackStack();
            }
        } else {
            Toast.makeText(requireContext(), getString(R.string.error_field), Toast.LENGTH_SHORT).show();
        }
    }

    private void setupFabToolbar() {
        b.fabtoolbarFab.setOnClickListener(v -> b.fabtoolbar.show());
        b.save.setOnClickListener(v -> save(getCurrentBusiness()));
        b.delete.setOnClickListener(v -> {
            if (businessId > 0) {
                deleteCompany();
            } else {
                Toast.makeText(requireContext(), getString(R.string.error_no_delete), Toast.LENGTH_SHORT).show();
            }
        });
        b.dismiss.setOnClickListener(v -> b.fabtoolbar.hide());
    }

    private void deleteCompany() {
        viewModel.deleteCompany(getCurrentBusiness());
        navController.popBackStack();
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
        b.txtContact.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                KeyboardUtils.hideSoftKeyboard(requireActivity());
                save(getCurrentBusiness());
                return true;
            }
            return false;
        });
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
        b.txtBusinessName.addTextChangedListener((CustomTextWatcher.onAfterText) s ->
                EditTextUtils.validateFields(
                        b.lblBusinessName,
                        b.txtBusinessName,
                        !TextUtils.isEmpty(b.txtBusinessName.getText().toString()),
                        requireContext()));

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
