package androidx.transition;

import android.view.View;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\transition\ViewUtilsApi22.smali */
class ViewUtilsApi22 extends ViewUtilsApi21 {
    private static boolean sTryHiddenSetLeftTopRightBottom = true;

    ViewUtilsApi22() {
    }

    @Override // androidx.transition.ViewUtilsBase
    public void setLeftTopRightBottom(View v, int left, int top, int right, int bottom) {
        if (sTryHiddenSetLeftTopRightBottom) {
            try {
                v.setLeftTopRightBottom(left, top, right, bottom);
            } catch (NoSuchMethodError e) {
                sTryHiddenSetLeftTopRightBottom = false;
            }
        }
    }
}
