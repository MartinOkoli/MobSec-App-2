package kotlin.jvm.internal;

import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\jvm\internal\MutablePropertyReference1Impl.smali */
public class MutablePropertyReference1Impl extends MutablePropertyReference1 {
    public MutablePropertyReference1Impl(KDeclarationContainer kDeclarationContainer, String str, String str2) {
        super(NO_RECEIVER, ((ClassBasedDeclarationContainer) kDeclarationContainer).getJClass(), str, str2, !(kDeclarationContainer instanceof KClass) ? 1 : 0);
    }

    public MutablePropertyReference1Impl(Class owner, String name, String signature, int flags) {
        super(NO_RECEIVER, owner, name, signature, flags);
    }

    public MutablePropertyReference1Impl(Object receiver, Class owner, String name, String signature, int flags) {
        super(receiver, owner, name, signature, flags);
    }

    @Override // kotlin.reflect.KProperty1
    public Object get(Object receiver) {
        return getGetter().call(receiver);
    }

    @Override // kotlin.reflect.KMutableProperty1
    public void set(Object receiver, Object value) {
        getSetter().call(receiver, value);
    }
}
