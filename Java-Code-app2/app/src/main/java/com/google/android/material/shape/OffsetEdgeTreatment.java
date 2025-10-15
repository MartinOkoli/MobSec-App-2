package com.google.android.material.shape;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\shape\OffsetEdgeTreatment.smali */
public final class OffsetEdgeTreatment extends EdgeTreatment {
    private final float offset;
    private final EdgeTreatment other;

    public OffsetEdgeTreatment(EdgeTreatment other, float offset) {
        this.other = other;
        this.offset = offset;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float length, float center, float interpolation, ShapePath shapePath) {
        this.other.getEdgePath(length, center - this.offset, interpolation, shapePath);
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    boolean forceIntersection() {
        return this.other.forceIntersection();
    }
}
