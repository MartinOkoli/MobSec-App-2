package androidx.core.view;

import android.view.View;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\view\NestedScrollingParent.smali */
public interface NestedScrollingParent {
    int getNestedScrollAxes();

    boolean onNestedFling(View view, float f, float f2, boolean z);

    boolean onNestedPreFling(View view, float f, float f2);

    void onNestedPreScroll(View view, int i, int i2, int[] iArr);

    void onNestedScroll(View view, int i, int i2, int i3, int i4);

    void onNestedScrollAccepted(View view, View view2, int i);

    boolean onStartNestedScroll(View view, View view2, int i);

    void onStopNestedScroll(View view);
}
