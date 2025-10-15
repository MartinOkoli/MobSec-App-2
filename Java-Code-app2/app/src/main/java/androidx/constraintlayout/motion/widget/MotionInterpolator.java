package androidx.constraintlayout.motion.widget;

import android.view.animation.Interpolator;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\constraintlayout\motion\widget\MotionInterpolator.smali */
public abstract class MotionInterpolator implements Interpolator {
    @Override // android.animation.TimeInterpolator
    public abstract float getInterpolation(float f);

    public abstract float getVelocity();
}
