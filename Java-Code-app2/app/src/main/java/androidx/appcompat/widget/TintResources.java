package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\appcompat\widget\TintResources.smali */
class TintResources extends ResourcesWrapper {
    private final WeakReference<Context> mContextRef;

    public TintResources(Context context, Resources res) {
        super(res);
        this.mContextRef = new WeakReference<>(context);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public Drawable getDrawable(int id) throws Resources.NotFoundException {
        Drawable d = super.getDrawable(id);
        Context context = this.mContextRef.get();
        if (d != null && context != null) {
            ResourceManagerInternal.get().tintDrawableUsingColorFilter(context, id, d);
        }
        return d;
    }
}
