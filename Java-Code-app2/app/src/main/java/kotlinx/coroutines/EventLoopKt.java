package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;

/* compiled from: EventLoop.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000\u001a\b\u0010\u0002\u001a\u00020\u0003H\u0007¨\u0006\u0004"}, d2 = {"createEventLoop", "Lkotlinx/coroutines/EventLoop;", "processNextEventInCurrentThread", "", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 15})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\EventLoopKt.smali */
public final class EventLoopKt {
    public static final EventLoop createEventLoop() {
        Thread threadCurrentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(threadCurrentThread, "Thread.currentThread()");
        return new BlockingEventLoop(threadCurrentThread);
    }

    public static final long processNextEventInCurrentThread() {
        EventLoop eventLoopCurrentOrNull$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.currentOrNull$kotlinx_coroutines_core();
        return eventLoopCurrentOrNull$kotlinx_coroutines_core != null ? eventLoopCurrentOrNull$kotlinx_coroutines_core.processNextEvent() : LongCompanionObject.MAX_VALUE;
    }
}
