package androidx.core.app;

import android.app.Dialog;
import android.os.Build;
import android.view.View;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\app\DialogCompat.smali */
public class DialogCompat {
    private DialogCompat() {
    }

    public static View requireViewById(Dialog dialog, int id) {
        if (Build.VERSION.SDK_INT >= 28) {
            return dialog.requireViewById(id);
        }
        View view = dialog.findViewById(id);
        if (view == null) {
            throw new IllegalArgumentException("ID does not reference a View inside this Dialog");
        }
        return view;
    }
}
