package androidx.transition;

import android.os.IBinder;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\transition\WindowIdApi14.smali */
class WindowIdApi14 implements WindowIdImpl {
    private final IBinder mToken;

    WindowIdApi14(IBinder token) {
        this.mToken = token;
    }

    public boolean equals(Object o) {
        return (o instanceof WindowIdApi14) && ((WindowIdApi14) o).mToken.equals(this.mToken);
    }

    public int hashCode() {
        return this.mToken.hashCode();
    }
}
