package androidx.appcompat.widget;

import android.R;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import androidx.appcompat.graphics.drawable.DrawableWrapper;
import androidx.core.graphics.drawable.WrappedDrawable;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\appcompat\widget\DrawableUtils.smali */
public class DrawableUtils {
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int[] EMPTY_STATE_SET = new int[0];
    public static final Rect INSETS_NONE = new Rect();
    private static final String TAG = "DrawableUtils";
    private static final String VECTOR_DRAWABLE_CLAZZ_NAME = "android.graphics.drawable.VectorDrawable";
    private static Class<?> sInsetsClazz;

    static {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                sInsetsClazz = Class.forName("android.graphics.Insets");
            } catch (ClassNotFoundException e) {
            }
        }
    }

    private DrawableUtils() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:14:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Rect getOpticalBounds(android.graphics.drawable.Drawable r14) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 29
            if (r0 < r1) goto L20
            android.graphics.Insets r0 = r14.getOpticalInsets()
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            int r2 = r0.left
            r1.left = r2
            int r2 = r0.right
            r1.right = r2
            int r2 = r0.top
            r1.top = r2
            int r2 = r0.bottom
            r1.bottom = r2
            return r1
        L20:
            java.lang.Class<?> r0 = androidx.appcompat.widget.DrawableUtils.sInsetsClazz
            if (r0 == 0) goto Lb9
            android.graphics.drawable.Drawable r0 = androidx.core.graphics.drawable.DrawableCompat.unwrap(r14)     // Catch: java.lang.Exception -> Lb1
            r14 = r0
            java.lang.Class r0 = r14.getClass()     // Catch: java.lang.Exception -> Lb1
            java.lang.String r1 = "getOpticalInsets"
            r2 = 0
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch: java.lang.Exception -> Lb1
            java.lang.reflect.Method r0 = r0.getMethod(r1, r3)     // Catch: java.lang.Exception -> Lb1
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> Lb1
            java.lang.Object r1 = r0.invoke(r14, r1)     // Catch: java.lang.Exception -> Lb1
            if (r1 == 0) goto Lb0
            android.graphics.Rect r3 = new android.graphics.Rect     // Catch: java.lang.Exception -> Lb1
            r3.<init>()     // Catch: java.lang.Exception -> Lb1
            java.lang.Class<?> r4 = androidx.appcompat.widget.DrawableUtils.sInsetsClazz     // Catch: java.lang.Exception -> Lb1
            java.lang.reflect.Field[] r4 = r4.getFields()     // Catch: java.lang.Exception -> Lb1
            int r5 = r4.length     // Catch: java.lang.Exception -> Lb1
            r6 = r2
        L4b:
            if (r6 >= r5) goto Laf
            r7 = r4[r6]     // Catch: java.lang.Exception -> Lb1
            java.lang.String r8 = r7.getName()     // Catch: java.lang.Exception -> Lb1
            r9 = -1
            int r10 = r8.hashCode()     // Catch: java.lang.Exception -> Lb1
            r11 = 3
            r12 = 2
            r13 = 1
            switch(r10) {
                case -1383228885: goto L7e;
                case 115029: goto L73;
                case 3317767: goto L69;
                case 108511772: goto L5f;
                default: goto L5e;
            }     // Catch: java.lang.Exception -> Lb1
        L5e:
            goto L87
        L5f:
            java.lang.String r10 = "right"
            boolean r8 = r8.equals(r10)     // Catch: java.lang.Exception -> Lb1
            if (r8 == 0) goto L5e
            r9 = r12
            goto L87
        L69:
            java.lang.String r10 = "left"
            boolean r8 = r8.equals(r10)     // Catch: java.lang.Exception -> Lb1
            if (r8 == 0) goto L5e
            r9 = r2
            goto L87
        L73:
            java.lang.String r10 = "top"
            boolean r8 = r8.equals(r10)     // Catch: java.lang.Exception -> Lb1
            if (r8 == 0) goto L5e
            r9 = r13
            goto L87
        L7e:
            java.lang.String r10 = "bottom"
            boolean r8 = r8.equals(r10)     // Catch: java.lang.Exception -> Lb1
            if (r8 == 0) goto L5e
            r9 = r11
        L87:
            if (r9 == 0) goto La5
            if (r9 == r13) goto L9e
            if (r9 == r12) goto L97
            if (r9 == r11) goto L90
            goto Lac
        L90:
            int r8 = r7.getInt(r1)     // Catch: java.lang.Exception -> Lb1
            r3.bottom = r8     // Catch: java.lang.Exception -> Lb1
            goto Lac
        L97:
            int r8 = r7.getInt(r1)     // Catch: java.lang.Exception -> Lb1
            r3.right = r8     // Catch: java.lang.Exception -> Lb1
            goto Lac
        L9e:
            int r8 = r7.getInt(r1)     // Catch: java.lang.Exception -> Lb1
            r3.top = r8     // Catch: java.lang.Exception -> Lb1
            goto Lac
        La5:
            int r8 = r7.getInt(r1)     // Catch: java.lang.Exception -> Lb1
            r3.left = r8     // Catch: java.lang.Exception -> Lb1
        Lac:
            int r6 = r6 + 1
            goto L4b
        Laf:
            return r3
        Lb0:
            goto Lb9
        Lb1:
            r0 = move-exception
            java.lang.String r1 = "DrawableUtils"
            java.lang.String r2 = "Couldn't obtain the optical insets. Ignoring."
            android.util.Log.e(r1, r2)
        Lb9:
            android.graphics.Rect r0 = androidx.appcompat.widget.DrawableUtils.INSETS_NONE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.DrawableUtils.getOpticalBounds(android.graphics.drawable.Drawable):android.graphics.Rect");
    }

    static void fixDrawable(Drawable drawable) {
        if (Build.VERSION.SDK_INT == 21 && VECTOR_DRAWABLE_CLAZZ_NAME.equals(drawable.getClass().getName())) {
            fixVectorDrawableTinting(drawable);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean canSafelyMutateDrawable(Drawable drawable) {
        if (Build.VERSION.SDK_INT < 15 && (drawable instanceof InsetDrawable)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 15 && (drawable instanceof GradientDrawable)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17 && (drawable instanceof LayerDrawable)) {
            return false;
        }
        if (!(drawable instanceof DrawableContainer)) {
            if (drawable instanceof WrappedDrawable) {
                return canSafelyMutateDrawable(((WrappedDrawable) drawable).getWrappedDrawable());
            }
            if (drawable instanceof DrawableWrapper) {
                return canSafelyMutateDrawable(((DrawableWrapper) drawable).getWrappedDrawable());
            }
            if (drawable instanceof ScaleDrawable) {
                return canSafelyMutateDrawable(((ScaleDrawable) drawable).getDrawable());
            }
            return true;
        }
        Drawable.ConstantState state = drawable.getConstantState();
        if (state instanceof DrawableContainer.DrawableContainerState) {
            DrawableContainer.DrawableContainerState containerState = (DrawableContainer.DrawableContainerState) state;
            for (Drawable child : containerState.getChildren()) {
                if (!canSafelyMutateDrawable(child)) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    private static void fixVectorDrawableTinting(Drawable drawable) {
        int[] originalState = drawable.getState();
        if (originalState == null || originalState.length == 0) {
            drawable.setState(CHECKED_STATE_SET);
        } else {
            drawable.setState(EMPTY_STATE_SET);
        }
        drawable.setState(originalState);
    }

    public static PorterDuff.Mode parseTintMode(int value, PorterDuff.Mode defaultMode) {
        if (value == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (value == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (value == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (value) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return defaultMode;
        }
    }
}
