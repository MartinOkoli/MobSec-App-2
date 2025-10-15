package com.google.android.material.timepicker;

import android.text.InputFilter;
import android.text.Spanned;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\timepicker\MaxInputValidator.smali */
class MaxInputValidator implements InputFilter {
    private int max;

    public MaxInputValidator(int max) {
        this.max = max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return this.max;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            StringBuilder builder = new StringBuilder(dest);
            builder.replace(dstart, dend, source.subSequence(start, end).toString());
            String newVal = builder.toString();
            int input = Integer.parseInt(newVal);
            if (input <= this.max) {
                return null;
            }
            return "";
        } catch (NumberFormatException e) {
            return "";
        }
    }
}
