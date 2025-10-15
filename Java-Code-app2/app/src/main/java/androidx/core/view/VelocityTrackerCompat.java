package androidx.core.view;

import android.view.VelocityTracker;

@Deprecated
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\view\VelocityTrackerCompat.smali */
public final class VelocityTrackerCompat {
    @Deprecated
    public static float getXVelocity(VelocityTracker tracker, int pointerId) {
        return tracker.getXVelocity(pointerId);
    }

    @Deprecated
    public static float getYVelocity(VelocityTracker tracker, int pointerId) {
        return tracker.getYVelocity(pointerId);
    }

    private VelocityTrackerCompat() {
    }
}
