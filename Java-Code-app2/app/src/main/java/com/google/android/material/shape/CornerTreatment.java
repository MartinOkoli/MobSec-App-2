package com.google.android.material.shape;

import android.graphics.RectF;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\shape\CornerTreatment.smali */
public class CornerTreatment {
    @Deprecated
    public void getCornerPath(float angle, float interpolation, ShapePath shapePath) {
    }

    public void getCornerPath(ShapePath shapePath, float angle, float interpolation, float radius) {
        getCornerPath(angle, interpolation, shapePath);
    }

    public void getCornerPath(ShapePath shapePath, float angle, float interpolation, RectF bounds, CornerSize size) {
        getCornerPath(shapePath, angle, interpolation, size.getCornerSize(bounds));
    }
}
