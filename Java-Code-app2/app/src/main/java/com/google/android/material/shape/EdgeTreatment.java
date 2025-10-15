package com.google.android.material.shape;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\shape\EdgeTreatment.smali */
public class EdgeTreatment {
    @Deprecated
    public void getEdgePath(float length, float interpolation, ShapePath shapePath) {
        float center = length / 2.0f;
        getEdgePath(length, center, interpolation, shapePath);
    }

    public void getEdgePath(float length, float center, float interpolation, ShapePath shapePath) {
        shapePath.lineTo(length, 0.0f);
    }

    boolean forceIntersection() {
        return false;
    }
}
