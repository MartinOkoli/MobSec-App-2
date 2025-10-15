package androidx.transition;

import android.graphics.Matrix;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\transition\GhostViewUtils.smali */
class GhostViewUtils {
    static GhostView addGhost(View view, ViewGroup viewGroup, Matrix matrix) {
        if (Build.VERSION.SDK_INT == 28) {
            return GhostViewPlatform.addGhost(view, viewGroup, matrix);
        }
        return GhostViewPort.addGhost(view, viewGroup, matrix);
    }

    static void removeGhost(View view) throws SecurityException, IllegalArgumentException {
        if (Build.VERSION.SDK_INT == 28) {
            GhostViewPlatform.removeGhost(view);
        } else {
            GhostViewPort.removeGhost(view);
        }
    }

    private GhostViewUtils() {
    }
}
