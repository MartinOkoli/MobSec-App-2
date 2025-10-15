package androidx.fragment.app;

import android.os.Bundle;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\fragment\app\FragmentTabHost$TabInfo.smali */
final class FragmentTabHost$TabInfo {
    final Bundle args;
    final Class<?> clss;
    Fragment fragment;
    final String tag;

    FragmentTabHost$TabInfo(String _tag, Class<?> _class, Bundle _args) {
        this.tag = _tag;
        this.clss = _class;
        this.args = _args;
    }
}
