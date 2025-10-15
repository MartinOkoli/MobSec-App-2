package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.appcompat.widget.FitWindowsViewGroup;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\appcompat\widget\FitWindowsLinearLayout.smali */
public class FitWindowsLinearLayout extends LinearLayout implements FitWindowsViewGroup {
    private FitWindowsViewGroup.OnFitSystemWindowsListener mListener;

    public FitWindowsLinearLayout(Context context) {
        super(context);
    }

    public FitWindowsLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // androidx.appcompat.widget.FitWindowsViewGroup
    public void setOnFitSystemWindowsListener(FitWindowsViewGroup.OnFitSystemWindowsListener listener) {
        this.mListener = listener;
    }

    @Override // android.view.View
    protected boolean fitSystemWindows(Rect insets) {
        FitWindowsViewGroup.OnFitSystemWindowsListener onFitSystemWindowsListener = this.mListener;
        if (onFitSystemWindowsListener != null) {
            onFitSystemWindowsListener.onFitSystemWindows(insets);
        }
        return super.fitSystemWindows(insets);
    }
}
