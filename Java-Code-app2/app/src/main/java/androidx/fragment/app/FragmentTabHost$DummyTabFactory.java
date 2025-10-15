package androidx.fragment.app;

import android.content.Context;
import android.view.View;
import android.widget.TabHost;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\fragment\app\FragmentTabHost$DummyTabFactory.smali */
class FragmentTabHost$DummyTabFactory implements TabHost.TabContentFactory {
    private final Context mContext;

    public FragmentTabHost$DummyTabFactory(Context context) {
        this.mContext = context;
    }

    @Override // android.widget.TabHost.TabContentFactory
    public View createTabContent(String tag) {
        View v = new View(this.mContext);
        v.setMinimumWidth(0);
        v.setMinimumHeight(0);
        return v;
    }
}
