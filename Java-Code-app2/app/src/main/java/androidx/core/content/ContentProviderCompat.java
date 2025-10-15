package androidx.core.content;

import android.content.ContentProvider;
import android.content.Context;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\content\ContentProviderCompat.smali */
public final class ContentProviderCompat {
    private ContentProviderCompat() {
    }

    public static Context requireContext(ContentProvider provider) {
        Context ctx = provider.getContext();
        if (ctx == null) {
            throw new IllegalStateException("Cannot find context from the provider.");
        }
        return ctx;
    }
}
