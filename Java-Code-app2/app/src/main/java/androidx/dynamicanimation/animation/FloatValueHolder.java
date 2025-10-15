package androidx.dynamicanimation.animation;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\dynamicanimation\animation\FloatValueHolder.smali */
public final class FloatValueHolder {
    private float mValue = 0.0f;

    public FloatValueHolder() {
    }

    public FloatValueHolder(float value) {
        setValue(value);
    }

    public void setValue(float value) {
        this.mValue = value;
    }

    public float getValue() {
        return this.mValue;
    }
}
