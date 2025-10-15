package com.google.android.material.datepicker;

import androidx.fragment.app.Fragment;
import java.util.LinkedHashSet;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\datepicker\PickerFragment.smali */
abstract class PickerFragment<S> extends Fragment {
    protected final LinkedHashSet<OnSelectionChangedListener<S>> onSelectionChangedListeners = new LinkedHashSet<>();

    abstract DateSelector<S> getDateSelector();

    PickerFragment() {
    }

    boolean addOnSelectionChangedListener(OnSelectionChangedListener<S> listener) {
        return this.onSelectionChangedListeners.add(listener);
    }

    boolean removeOnSelectionChangedListener(OnSelectionChangedListener<S> listener) {
        return this.onSelectionChangedListeners.remove(listener);
    }

    void clearOnSelectionChangedListeners() {
        this.onSelectionChangedListeners.clear();
    }
}
