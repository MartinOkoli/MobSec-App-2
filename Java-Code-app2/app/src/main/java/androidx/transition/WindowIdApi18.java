package androidx.transition;

import android.view.View;
import android.view.WindowId;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\transition\WindowIdApi18.smali */
class WindowIdApi18 implements WindowIdImpl {
    private final WindowId mWindowId;

    WindowIdApi18(View view) {
        this.mWindowId = view.getWindowId();
    }

    public boolean equals(Object o) {
        return (o instanceof WindowIdApi18) && ((WindowIdApi18) o).mWindowId.equals(this.mWindowId);
    }

    public int hashCode() {
        return this.mWindowId.hashCode();
    }
}
