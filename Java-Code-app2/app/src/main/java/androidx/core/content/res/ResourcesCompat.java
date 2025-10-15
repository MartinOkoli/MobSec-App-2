package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import androidx.core.util.Preconditions;
import java.lang.reflect.Method;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\content\res\ResourcesCompat.smali */
public final class ResourcesCompat {
    public static final int ID_NULL = 0;
    private static final String TAG = "ResourcesCompat";

    public static Drawable getDrawable(Resources res, int id, Resources.Theme theme) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 21) {
            return res.getDrawable(id, theme);
        }
        return res.getDrawable(id);
    }

    public static Drawable getDrawableForDensity(Resources res, int id, int density, Resources.Theme theme) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 21) {
            return res.getDrawableForDensity(id, density, theme);
        }
        if (Build.VERSION.SDK_INT >= 15) {
            return res.getDrawableForDensity(id, density);
        }
        return res.getDrawable(id);
    }

    public static int getColor(Resources res, int id, Resources.Theme theme) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 23) {
            return res.getColor(id, theme);
        }
        return res.getColor(id);
    }

    public static ColorStateList getColorStateList(Resources res, int id, Resources.Theme theme) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 23) {
            return res.getColorStateList(id, theme);
        }
        return res.getColorStateList(id);
    }

    public static float getFloat(Resources res, int id) throws Resources.NotFoundException {
        TypedValue value = new TypedValue();
        res.getValue(id, value, true);
        if (value.type == 4) {
            return value.getFloat();
        }
        throw new Resources.NotFoundException("Resource ID #0x" + Integer.toHexString(id) + " type #0x" + Integer.toHexString(value.type) + " is not valid");
    }

    public static Typeface getFont(Context context, int id) throws Resources.NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, id, new TypedValue(), 0, null, null, false);
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\content\res\ResourcesCompat$FontCallback.smali */
    public static abstract class FontCallback {
        public abstract void onFontRetrievalFailed(int i);

        public abstract void onFontRetrieved(Typeface typeface);

        public final void callbackSuccessAsync(final Typeface typeface, Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() { // from class: androidx.core.content.res.ResourcesCompat.FontCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    FontCallback.this.onFontRetrieved(typeface);
                }
            });
        }

        public final void callbackFailAsync(final int reason, Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() { // from class: androidx.core.content.res.ResourcesCompat.FontCallback.2
                @Override // java.lang.Runnable
                public void run() {
                    FontCallback.this.onFontRetrievalFailed(reason);
                }
            });
        }
    }

    public static void getFont(Context context, int id, FontCallback fontCallback, Handler handler) throws Resources.NotFoundException {
        Preconditions.checkNotNull(fontCallback);
        if (context.isRestricted()) {
            fontCallback.callbackFailAsync(-4, handler);
        } else {
            loadFont(context, id, new TypedValue(), 0, fontCallback, handler, false);
        }
    }

    public static Typeface getFont(Context context, int id, TypedValue value, int style, FontCallback fontCallback) throws Resources.NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, id, value, style, fontCallback, null, true);
    }

    private static Typeface loadFont(Context context, int id, TypedValue value, int style, FontCallback fontCallback, Handler handler, boolean isRequestFromLayoutInflator) throws Resources.NotFoundException {
        Resources resources = context.getResources();
        resources.getValue(id, value, true);
        Typeface typeface = loadFont(context, resources, value, id, style, fontCallback, handler, isRequestFromLayoutInflator);
        if (typeface == null && fontCallback == null) {
            throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(id) + " could not be retrieved.");
        }
        return typeface;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.graphics.Typeface loadFont(android.content.Context r19, android.content.res.Resources r20, android.util.TypedValue r21, int r22, int r23, androidx.core.content.res.ResourcesCompat.FontCallback r24, android.os.Handler r25, boolean r26) throws android.content.res.Resources.NotFoundException {
        /*
            Method dump skipped, instructions count: 296
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.ResourcesCompat.loadFont(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, androidx.core.content.res.ResourcesCompat$FontCallback, android.os.Handler, boolean):android.graphics.Typeface");
    }

    private ResourcesCompat() {
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\content\res\ResourcesCompat$ThemeCompat.smali */
    public static final class ThemeCompat {
        private ThemeCompat() {
        }

        public static void rebase(Resources.Theme theme) {
            if (Build.VERSION.SDK_INT >= 29) {
                ImplApi29.rebase(theme);
            } else if (Build.VERSION.SDK_INT >= 23) {
                ImplApi23.rebase(theme);
            }
        }

        /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\content\res\ResourcesCompat$ThemeCompat$ImplApi29.smali */
        static class ImplApi29 {
            private ImplApi29() {
            }

            static void rebase(Resources.Theme theme) {
                theme.rebase();
            }
        }

        /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\content\res\ResourcesCompat$ThemeCompat$ImplApi23.smali */
        static class ImplApi23 {
            private static Method sRebaseMethod;
            private static boolean sRebaseMethodFetched;
            private static final Object sRebaseMethodLock = new Object();

            private ImplApi23() {
            }

            /* JADX WARN: Removed duplicated region for block: B:30:0x0027 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            static void rebase(android.content.res.Resources.Theme r6) {
                /*
                    java.lang.Object r0 = androidx.core.content.res.ResourcesCompat.ThemeCompat.ImplApi23.sRebaseMethodLock
                    monitor-enter(r0)
                    boolean r1 = androidx.core.content.res.ResourcesCompat.ThemeCompat.ImplApi23.sRebaseMethodFetched     // Catch: java.lang.Throwable -> L3c
                    r2 = 0
                    if (r1 != 0) goto L23
                    r1 = 1
                    java.lang.Class<android.content.res.Resources$Theme> r3 = android.content.res.Resources.Theme.class
                    java.lang.String r4 = "rebase"
                    java.lang.Class[] r5 = new java.lang.Class[r2]     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3c
                    java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r5)     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3c
                    androidx.core.content.res.ResourcesCompat.ThemeCompat.ImplApi23.sRebaseMethod = r3     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3c
                    r3.setAccessible(r1)     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3c
                    goto L21
                L19:
                    r3 = move-exception
                    java.lang.String r4 = "ResourcesCompat"
                    java.lang.String r5 = "Failed to retrieve rebase() method"
                    android.util.Log.i(r4, r5, r3)     // Catch: java.lang.Throwable -> L3c
                L21:
                    androidx.core.content.res.ResourcesCompat.ThemeCompat.ImplApi23.sRebaseMethodFetched = r1     // Catch: java.lang.Throwable -> L3c
                L23:
                    java.lang.reflect.Method r1 = androidx.core.content.res.ResourcesCompat.ThemeCompat.ImplApi23.sRebaseMethod     // Catch: java.lang.Throwable -> L3c
                    if (r1 == 0) goto L3a
                    java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.reflect.InvocationTargetException -> L2d java.lang.IllegalAccessException -> L2f java.lang.Throwable -> L3c
                    r1.invoke(r6, r2)     // Catch: java.lang.reflect.InvocationTargetException -> L2d java.lang.IllegalAccessException -> L2f java.lang.Throwable -> L3c
                    goto L3a
                L2d:
                    r1 = move-exception
                    goto L30
                L2f:
                    r1 = move-exception
                L30:
                    java.lang.String r2 = "ResourcesCompat"
                    java.lang.String r3 = "Failed to invoke rebase() method via reflection"
                    android.util.Log.i(r2, r3, r1)     // Catch: java.lang.Throwable -> L3c
                    r2 = 0
                    androidx.core.content.res.ResourcesCompat.ThemeCompat.ImplApi23.sRebaseMethod = r2     // Catch: java.lang.Throwable -> L3c
                L3a:
                    monitor-exit(r0)     // Catch: java.lang.Throwable -> L3c
                    return
                L3c:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch: java.lang.Throwable -> L3c
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.ResourcesCompat.ThemeCompat.ImplApi23.rebase(android.content.res.Resources$Theme):void");
            }
        }
    }
}
