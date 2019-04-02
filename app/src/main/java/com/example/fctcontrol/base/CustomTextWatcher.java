package com.example.fctcontrol.base;

import android.text.Editable;
import android.text.TextWatcher;

public class CustomTextWatcher {

    public interface onAfterText extends TextWatcher {
        @Override
        default void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        default void onTextChanged(CharSequence s, int start, int before, int count) {

        }
    }

    public interface onTextChanged extends TextWatcher {
        @Override
        default void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        default void afterTextChanged(Editable s) {

        }
    }

    public interface onBeforeTextChanged extends TextWatcher {
        @Override
        default void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        default void afterTextChanged(Editable s) {

        }
    }

}
