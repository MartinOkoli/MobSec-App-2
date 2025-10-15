package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\widget\TintableCompoundButton.smali */
public interface TintableCompoundButton {
    ColorStateList getSupportButtonTintList();

    PorterDuff.Mode getSupportButtonTintMode();

    void setSupportButtonTintList(ColorStateList colorStateList);

    void setSupportButtonTintMode(PorterDuff.Mode mode);
}
