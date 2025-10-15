package kotlinx.coroutines.sync;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlinx.coroutines.CancelHandlerBase;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.internal.SegmentQueue;

/* compiled from: Semaphore.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\n\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\f\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u000bJ\r\u0010\r\u001a\u00020\u0004¢\u0006\u0004\b\r\u0010\u000eJ!\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\tH\u0000¢\u0006\u0004\b\u0016\u0010\u0015J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001c\u001a\u00020\u00048V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u000eR\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001d\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lkotlinx/coroutines/sync/SemaphoreImpl;", "Lkotlinx/coroutines/sync/Semaphore;", "Lkotlinx/coroutines/internal/SegmentQueue;", "Lkotlinx/coroutines/sync/SemaphoreSegment;", "", "permits", "acquiredPermits", "<init>", "(II)V", "", "acquire", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addToQueueAndSuspend", "incPermits", "()I", "", "id", "prev", "newSegment", "(JLkotlinx/coroutines/sync/SemaphoreSegment;)Lkotlinx/coroutines/sync/SemaphoreSegment;", "release", "()V", "resumeNextFromQueue$kotlinx_coroutines_core", "resumeNextFromQueue", "", "tryAcquire", "()Z", "getAvailablePermits", "availablePermits", "I", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\sync\SemaphoreImpl.smali */
final class SemaphoreImpl extends SegmentQueue<SemaphoreSegment> implements Semaphore {
    private volatile int _availablePermits;
    private volatile long deqIdx;
    volatile long enqIdx;
    private final int permits;
    private static final AtomicIntegerFieldUpdater _availablePermits$FU = AtomicIntegerFieldUpdater.newUpdater(SemaphoreImpl.class, "_availablePermits");
    static final AtomicLongFieldUpdater enqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "enqIdx");
    private static final AtomicLongFieldUpdater deqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "deqIdx");

    public SemaphoreImpl(int permits, int acquiredPermits) {
        this.permits = permits;
        if (!(permits > 0)) {
            throw new IllegalArgumentException(("Semaphore should have at least 1 permit, but had " + permits).toString());
        }
        if (acquiredPermits >= 0 && permits >= acquiredPermits) {
            this._availablePermits = permits - acquiredPermits;
            this.enqIdx = 0L;
            this.deqIdx = 0L;
        } else {
            throw new IllegalArgumentException(("The number of acquired permits should be in 0.." + permits).toString());
        }
    }

    public static final /* synthetic */ SemaphoreSegment access$getSegment(SemaphoreImpl $this, SemaphoreSegment startFrom, long id) {
        return $this.getSegment(startFrom, id);
    }

    public static final /* synthetic */ SemaphoreSegment access$getTail$p(SemaphoreImpl $this) {
        return $this.getTail();
    }

    @Override // kotlinx.coroutines.internal.SegmentQueue
    public SemaphoreSegment newSegment(long id, SemaphoreSegment prev) {
        return new SemaphoreSegment(id, prev);
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public int getAvailablePermits() {
        return Math.max(this._availablePermits, 0);
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public boolean tryAcquire() {
        int p;
        do {
            p = this._availablePermits;
            if (p <= 0) {
                return false;
            }
        } while (!_availablePermits$FU.compareAndSet(this, p, p - 1));
        return true;
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public Object acquire(Continuation<? super Unit> continuation) {
        int p = _availablePermits$FU.getAndDecrement(this);
        return p > 0 ? Unit.INSTANCE : addToQueueAndSuspend(continuation);
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public void release() {
        int p = incPermits();
        if (p >= 0) {
            return;
        }
        resumeNextFromQueue$kotlinx_coroutines_core();
    }

    public final int incPermits() {
        int cur$iv;
        int upd$iv;
        do {
            cur$iv = this._availablePermits;
            if (!(cur$iv < this.permits)) {
                throw new IllegalStateException(("The number of released permits cannot be greater than " + this.permits).toString());
            }
            upd$iv = cur$iv + 1;
        } while (!_availablePermits$FU.compareAndSet(this, cur$iv, upd$iv));
        return cur$iv;
    }

    final /* synthetic */ Object addToQueueAndSuspend(Continuation<? super Unit> continuation) {
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 0);
        CancellableContinuationImpl cancellableContinuationImpl = cancellable$iv;
        SemaphoreSegment last = access$getTail$p(this);
        long enqIdx = enqIdx$FU.getAndIncrement(this);
        SemaphoreSegment segment = access$getSegment(this, last, enqIdx / SemaphoreKt.SEGMENT_SIZE);
        int i = (int) (enqIdx % SemaphoreKt.SEGMENT_SIZE);
        if (segment != null && segment.acquirers.get(i) != SemaphoreKt.RESUMED && segment.acquirers.compareAndSet(i, null, cancellableContinuationImpl)) {
            CancelHandlerBase $this$asHandler$iv = new CancelSemaphoreAcquisitionHandler(this, segment, i);
            cancellableContinuationImpl.invokeOnCancellation($this$asHandler$iv);
        } else {
            Unit unit = Unit.INSTANCE;
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationImpl.resumeWith(Result.m14constructorimpl(unit));
        }
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final void resumeNextFromQueue$kotlinx_coroutines_core() {
        while (true) {
            SemaphoreSegment first = getHead();
            long deqIdx = deqIdx$FU.getAndIncrement(this);
            SemaphoreSegment segment = getSegmentAndMoveHead(first, deqIdx / SemaphoreKt.SEGMENT_SIZE);
            if (segment != null) {
                int i = (int) (deqIdx % SemaphoreKt.SEGMENT_SIZE);
                Object value$iv = segment.acquirers.getAndSet(i, SemaphoreKt.RESUMED);
                if (value$iv == null) {
                    return;
                }
                if (value$iv != SemaphoreKt.CANCELLED) {
                    Unit unit = Unit.INSTANCE;
                    Result.Companion companion = Result.INSTANCE;
                    ((CancellableContinuation) value$iv).resumeWith(Result.m14constructorimpl(unit));
                    return;
                }
            }
        }
    }
}
