package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty0;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\jvm\internal\PropertyReference0.smali */
public abstract class PropertyReference0 extends PropertyReference implements KProperty0 {
    public PropertyReference0() {
    }

    public PropertyReference0(Object receiver) {
        super(receiver);
    }

    public PropertyReference0(Object receiver, Class owner, String name, String signature, int flags) {
        super(receiver, owner, name, signature, flags);
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected KCallable computeReflected() {
        return Reflection.property0(this);
    }

    @Override // kotlin.jvm.functions.Function0
    public Object invoke() {
        return get();
    }

    @Override // kotlin.reflect.KProperty
    public KProperty0.Getter getGetter() {
        return ((KProperty0) getReflected()).getGetter();
    }

    @Override // kotlin.reflect.KProperty0
    public Object getDelegate() {
        return ((KProperty0) getReflected()).getDelegate();
    }
}
