package androidx.core.widget;

import android.os.Build;
import android.view.View;
import android.widget.PopupMenu;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\widget\PopupMenuCompat.smali */
public final class PopupMenuCompat {
    private PopupMenuCompat() {
    }

    public static View.OnTouchListener getDragToOpenListener(Object popupMenu) {
        if (Build.VERSION.SDK_INT >= 19) {
            return ((PopupMenu) popupMenu).getDragToOpenListener();
        }
        return null;
    }
}
