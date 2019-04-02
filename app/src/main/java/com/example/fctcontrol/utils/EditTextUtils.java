package com.example.fctcontrol.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fctcontrol.R;

public class EditTextUtils {

    private EditTextUtils() {
    }

    public static void changeFontOnFocus(boolean hasFocus, TextView lbl) {
        if (hasFocus) {
            lbl.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            lbl.setTypeface(Typeface.DEFAULT);
        }
    }

    public static void validateFields(TextView lbl, EditText txt, boolean isValid, Context context) {
        if (!isValid) {
            lbl.setEnabled(false);
            txt.setError(context.getResources().getString(R.string.invalid_data));
        } else {
            lbl.setEnabled(true);
        }
    }
}
