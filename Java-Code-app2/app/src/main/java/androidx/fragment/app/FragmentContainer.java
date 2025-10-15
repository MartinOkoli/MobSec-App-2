package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\fragment\app\FragmentContainer.smali */
public abstract class FragmentContainer {
    public abstract View onFindViewById(int i);

    public abstract boolean onHasView();

    @Deprecated
    public Fragment instantiate(Context context, String className, Bundle arguments) {
        return Fragment.instantiate(context, className, arguments);
    }
}
