package androidx.appcompat.widget;

import android.graphics.Rect;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\appcompat\widget\FitWindowsViewGroup.smali */
public interface FitWindowsViewGroup {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\appcompat\widget\FitWindowsViewGroup$OnFitSystemWindowsListener.smali */
    public interface OnFitSystemWindowsListener {
        void onFitSystemWindows(Rect rect);
    }

    void setOnFitSystemWindowsListener(OnFitSystemWindowsListener onFitSystemWindowsListener);
}
