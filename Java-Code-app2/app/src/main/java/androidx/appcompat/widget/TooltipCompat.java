package androidx.appcompat.widget;

import android.os.Build;
import android.view.View;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\appcompat\widget\TooltipCompat.smali */
public class TooltipCompat {
    public static void setTooltipText(View view, CharSequence tooltipText) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setTooltipText(tooltipText);
        } else {
            TooltipCompatHandler.setTooltipText(view, tooltipText);
        }
    }

    private TooltipCompat() {
    }
}
