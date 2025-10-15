package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty1;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\jvm\internal\PropertyReference1.smali */
public abstract class PropertyReference1 extends PropertyReference implements KProperty1 {
    public PropertyReference1() {
    }

    public PropertyReference1(Object receiver) {
        super(receiver);
    }

    public PropertyReference1(Object receiver, Class owner, String name, String signature, int flags) {
        super(receiver, owner, name, signature, flags);
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected KCallable computeReflected() {
        return Reflection.property1(this);
    }

    @Override // kotlin.jvm.functions.Function1
    public Object invoke(Object receiver) {
        return get(receiver);
    }

    @Override // kotlin.reflect.KProperty
    public KProperty1.Getter getGetter() {
        return ((KProperty1) getReflected()).getGetter();
    }

    @Override // kotlin.reflect.KProperty1
    public Object getDelegate(Object receiver) {
        return ((KProperty1) getReflected()).getDelegate(receiver);
    }
}
