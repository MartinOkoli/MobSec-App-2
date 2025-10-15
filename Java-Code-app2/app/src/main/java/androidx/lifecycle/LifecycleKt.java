package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* compiled from: Lifecycle.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"coroutineScope", "Landroidx/lifecycle/LifecycleCoroutineScope;", "Landroidx/lifecycle/Lifecycle;", "getCoroutineScope", "(Landroidx/lifecycle/Lifecycle;)Landroidx/lifecycle/LifecycleCoroutineScope;", "lifecycle-runtime-ktx_release"}, k = 2, mv = {1, 1, 15})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\lifecycle\LifecycleKt.smali */
public final class LifecycleKt {
    public static final LifecycleCoroutineScope getCoroutineScope(Lifecycle coroutineScope) {
        LifecycleCoroutineScopeImpl newScope;
        Intrinsics.checkParameterIsNotNull(coroutineScope, "$this$coroutineScope");
        do {
            LifecycleCoroutineScopeImpl existing = (LifecycleCoroutineScopeImpl) coroutineScope.mInternalScopeRef.get();
            if (existing != null) {
                return existing;
            }
            newScope = new LifecycleCoroutineScopeImpl(coroutineScope, SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain().getImmediate()));
        } while (!coroutineScope.mInternalScopeRef.compareAndSet(null, newScope));
        newScope.register();
        return newScope;
    }
}
