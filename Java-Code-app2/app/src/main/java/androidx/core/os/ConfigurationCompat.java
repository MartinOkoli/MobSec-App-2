package androidx.core.os;

import android.content.res.Configuration;
import android.os.Build;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\os\ConfigurationCompat.smali */
public final class ConfigurationCompat {
    private ConfigurationCompat() {
    }

    public static LocaleListCompat getLocales(Configuration configuration) {
        return Build.VERSION.SDK_INT >= 24 ? LocaleListCompat.wrap(configuration.getLocales()) : LocaleListCompat.create(configuration.locale);
    }
}
