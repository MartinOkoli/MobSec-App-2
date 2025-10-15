package androidx.core.location;

import android.location.LocationManager;
import android.os.Build;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\location\LocationManagerCompat.smali */
public final class LocationManagerCompat {
    public static boolean isLocationEnabled(LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return locationManager.isLocationEnabled();
        }
        return locationManager.isProviderEnabled("network") || locationManager.isProviderEnabled("gps");
    }

    private LocationManagerCompat() {
    }
}
