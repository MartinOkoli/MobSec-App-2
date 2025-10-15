package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\widget\TintableImageSourceView.smali */
public interface TintableImageSourceView {
    ColorStateList getSupportImageTintList();

    PorterDuff.Mode getSupportImageTintMode();

    void setSupportImageTintList(ColorStateList colorStateList);

    void setSupportImageTintMode(PorterDuff.Mode mode);
}
