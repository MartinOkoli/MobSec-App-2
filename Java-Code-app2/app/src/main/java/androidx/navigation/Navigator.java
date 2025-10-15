package androidx.navigation;

import android.os.Bundle;
import androidx.navigation.NavDestination;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\navigation\Navigator.smali */
public abstract class Navigator<D extends NavDestination> {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\navigation\Navigator$Extras.smali */
    public interface Extras {
    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\navigation\Navigator$Name.smali */
    public @interface Name {
        String value();
    }

    public abstract D createDestination();

    public abstract NavDestination navigate(D d, Bundle bundle, NavOptions navOptions, Extras extras);

    public abstract boolean popBackStack();

    public Bundle onSaveState() {
        return null;
    }

    public void onRestoreState(Bundle savedState) {
    }
}
