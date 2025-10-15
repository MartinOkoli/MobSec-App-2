package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.internal.ConcurrentKt;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

/* compiled from: ArrayBroadcastChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u00019B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010 \u001a\u00020\u00172\b\u0010!\u001a\u0004\u0018\u00010\"H\u0017J\u0018\u0010 \u001a\u00020#2\u000e\u0010!\u001a\n\u0018\u00010$j\u0004\u0018\u0001`%H\u0016J\u0012\u0010&\u001a\u00020\u00172\b\u0010!\u001a\u0004\u0018\u00010\"H\u0002J\b\u0010'\u001a\u00020#H\u0002J\u0012\u0010(\u001a\u00020\u00172\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010)\u001a\u00020\u0015H\u0002J\u0015\u0010*\u001a\u00028\u00002\u0006\u0010+\u001a\u00020\u0015H\u0002¢\u0006\u0002\u0010,J\u0015\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010/J!\u00100\u001a\u00020\t2\u0006\u0010.\u001a\u00028\u00002\n\u00101\u001a\u0006\u0012\u0002\b\u000302H\u0014¢\u0006\u0002\u00103J\u000e\u00104\u001a\b\u0012\u0004\u0012\u00028\u000005H\u0016J-\u00106\u001a\u00020#2\u0010\b\u0002\u00107\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001d2\u0010\b\u0002\u00108\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001dH\u0082\u0010R\u0018\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u00178TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00178TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0018R\u000e\u0010\u001a\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u001b\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001d0\u001cj\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001d`\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lkotlinx/coroutines/channels/ArrayBroadcastChannel;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/AbstractSendChannel;", "Lkotlinx/coroutines/channels/BroadcastChannel;", "capacity", "", "(I)V", "buffer", "", "", "[Ljava/lang/Object;", "bufferDebugString", "", "getBufferDebugString", "()Ljava/lang/String;", "bufferLock", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "getCapacity", "()I", "head", "", "isBufferAlwaysFull", "", "()Z", "isBufferFull", "size", "subscribers", "", "Lkotlinx/coroutines/channels/ArrayBroadcastChannel$Subscriber;", "Lkotlinx/coroutines/internal/SubscribersList;", "tail", "cancel", "cause", "", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cancelInternal", "checkSubOffers", "close", "computeMinHead", "elementAt", "index", "(J)Ljava/lang/Object;", "offerInternal", "element", "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "updateHead", "addSub", "removeSub", "Subscriber", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\channels\ArrayBroadcastChannel.smali */
public final class ArrayBroadcastChannel<E> extends AbstractSendChannel<E> implements BroadcastChannel<E> {
    private final Object[] buffer;
    private final ReentrantLock bufferLock;
    private final int capacity;
    private volatile long head;
    private volatile int size;
    private final List<Subscriber<E>> subscribers;
    private volatile long tail;

    public ArrayBroadcastChannel(int capacity) {
        this.capacity = capacity;
        if (capacity >= 1) {
            this.bufferLock = new ReentrantLock();
            this.buffer = new Object[capacity];
            this.subscribers = ConcurrentKt.subscriberList();
        } else {
            throw new IllegalArgumentException(("ArrayBroadcastChannel capacity must be at least 1, but " + capacity + " was specified").toString());
        }
    }

    public final int getCapacity() {
        return this.capacity;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected boolean isBufferAlwaysFull() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected boolean isBufferFull() {
        return this.size >= this.capacity;
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public ReceiveChannel<E> openSubscription() throws Throwable {
        Subscriber it = new Subscriber(this);
        updateHead$default(this, it, null, 2, null);
        return it;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel, kotlinx.coroutines.channels.SendChannel
    public boolean close(Throwable cause) {
        if (!super.close(cause)) {
            return false;
        }
        checkSubOffers();
        return true;
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public void cancel(CancellationException cause) throws Throwable {
        cancel(cause);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // kotlinx.coroutines.channels.BroadcastChannel
    /* renamed from: cancelInternal, reason: merged with bridge method [inline-methods] */
    public final boolean cancel(Throwable cause) throws Throwable {
        boolean zClose = close(cause);
        for (Subscriber sub : this.subscribers) {
            sub.cancel(cause);
        }
        return zClose;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object offerInternal(E element) {
        ReentrantLock $this$withLock$iv = this.bufferLock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            int size = this.size;
            if (size >= this.capacity) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            long tail = this.tail;
            this.buffer[(int) (tail % this.capacity)] = element;
            this.size = size + 1;
            this.tail = 1 + tail;
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
            checkSubOffers();
            return AbstractChannelKt.OFFER_SUCCESS;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object offerSelectInternal(E element, SelectInstance<?> select) {
        Intrinsics.checkParameterIsNotNull(select, "select");
        ReentrantLock $this$withLock$iv = this.bufferLock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            int size = this.size;
            if (size >= this.capacity) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            if (!select.trySelect(null)) {
                return SelectKt.getALREADY_SELECTED();
            }
            long tail = this.tail;
            this.buffer[(int) (tail % this.capacity)] = element;
            this.size = size + 1;
            this.tail = 1 + tail;
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
            checkSubOffers();
            return AbstractChannelKt.OFFER_SUCCESS;
        } finally {
            reentrantLock.unlock();
        }
    }

    private final void checkSubOffers() {
        boolean updated = false;
        boolean hasSubs = false;
        for (Subscriber sub : this.subscribers) {
            hasSubs = true;
            if (sub.checkOffer()) {
                updated = true;
            }
        }
        if (updated || !hasSubs) {
            updateHead$default(this, null, null, 3, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void updateHead$default(ArrayBroadcastChannel arrayBroadcastChannel, Subscriber subscriber, Subscriber subscriber2, int i, Object obj) throws Throwable {
        if ((i & 1) != 0) {
            subscriber = (Subscriber) null;
        }
        if ((i & 2) != 0) {
            subscriber2 = (Subscriber) null;
        }
        arrayBroadcastChannel.updateHead(subscriber, subscriber2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x00b8, code lost:
    
        r4 = r23.buffer;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00c3, code lost:
    
        r2 = (int) (r12 % r23.capacity);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00c6, code lost:
    
        if (r20 == null) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00c8, code lost:
    
        r4[r2] = r20.getPollResult();
        r23.size = r0 + 1;
        r23.tail = 1 + r12;
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00da, code lost:
    
        r8.unlock();
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00de, code lost:
    
        if (r20 != null) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00e0, code lost:
    
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00f6, code lost:
    
        throw new kotlin.TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.Send");
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00f7, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Removed duplicated region for block: B:105:0x005a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x00b8 A[EDGE_INSN: B:110:0x00b8->B:52:0x00b8 BREAK  A[LOOP:2: B:95:0x008a->B:65:0x00fb], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0039 A[Catch: all -> 0x002c, TRY_ENTER, TRY_LEAVE, TryCatch #6 {all -> 0x002c, blocks: (B:5:0x0017, B:12:0x0039), top: B:97:0x0017 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005e A[Catch: all -> 0x015d, TRY_ENTER, TRY_LEAVE, TryCatch #8 {all -> 0x015d, blocks: (B:17:0x004a, B:22:0x005e), top: B:101:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00fb A[LOOP:2: B:95:0x008a->B:65:0x00fb, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void updateHead(kotlinx.coroutines.channels.ArrayBroadcastChannel.Subscriber<E> r24, kotlinx.coroutines.channels.ArrayBroadcastChannel.Subscriber<E> r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 362
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayBroadcastChannel.updateHead(kotlinx.coroutines.channels.ArrayBroadcastChannel$Subscriber, kotlinx.coroutines.channels.ArrayBroadcastChannel$Subscriber):void");
    }

    private final long computeMinHead() {
        long minHead = LongCompanionObject.MAX_VALUE;
        for (Subscriber sub : this.subscribers) {
            minHead = RangesKt.coerceAtMost(minHead, sub.subHead);
        }
        return minHead;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final E elementAt(long index) {
        return (E) this.buffer[(int) (index % this.capacity)];
    }

    /* compiled from: ArrayBroadcastChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0017\u0010\u0012\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0010¢\u0006\u0002\b\u0015J\u0006\u0010\u0016\u001a\u00020\bJ\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\bH\u0002J\n\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002J\n\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0014J\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u001b2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001fH\u0014R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u0014\u0010\n\u001a\u00020\b8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\tR\u0014\u0010\u000b\u001a\u00020\b8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0014\u0010\f\u001a\u00020\b8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\tR\u0012\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lkotlinx/coroutines/channels/ArrayBroadcastChannel$Subscriber;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/AbstractChannel;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "broadcastChannel", "Lkotlinx/coroutines/channels/ArrayBroadcastChannel;", "(Lkotlinx/coroutines/channels/ArrayBroadcastChannel;)V", "isBufferAlwaysEmpty", "", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "subHead", "", "subLock", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "cancelInternal", "cause", "", "cancelInternal$kotlinx_coroutines_core", "checkOffer", "clearBuffer", "", "needsToCheckOfferWithoutLock", "peekUnderLock", "", "pollInternal", "pollSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 15})
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\channels\ArrayBroadcastChannel$Subscriber.smali */
    private static final class Subscriber<E> extends AbstractChannel<E> implements ReceiveChannel<E> {
        private final ArrayBroadcastChannel<E> broadcastChannel;
        public volatile long subHead;
        private final ReentrantLock subLock;

        public Subscriber(ArrayBroadcastChannel<E> broadcastChannel) {
            Intrinsics.checkParameterIsNotNull(broadcastChannel, "broadcastChannel");
            this.broadcastChannel = broadcastChannel;
            this.subLock = new ReentrantLock();
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        protected boolean isBufferAlwaysEmpty() {
            return false;
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        protected boolean isBufferEmpty() {
            return this.subHead >= ((ArrayBroadcastChannel) this.broadcastChannel).tail;
        }

        @Override // kotlinx.coroutines.channels.AbstractSendChannel
        protected boolean isBufferAlwaysFull() {
            throw new IllegalStateException("Should not be used".toString());
        }

        @Override // kotlinx.coroutines.channels.AbstractSendChannel
        protected boolean isBufferFull() {
            throw new IllegalStateException("Should not be used".toString());
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        /* renamed from: cancelInternal$kotlinx_coroutines_core */
        public boolean cancel(Throwable cause) throws Throwable {
            boolean closed = close(cause);
            if (closed) {
                ArrayBroadcastChannel.updateHead$default(this.broadcastChannel, null, this, 1, null);
            }
            clearBuffer();
            return closed;
        }

        private final void clearBuffer() {
            ReentrantLock $this$withLock$iv = this.subLock;
            ReentrantLock reentrantLock = $this$withLock$iv;
            reentrantLock.lock();
            try {
                this.subHead = ((ArrayBroadcastChannel) this.broadcastChannel).tail;
                Unit unit = Unit.INSTANCE;
            } finally {
                reentrantLock.unlock();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final boolean checkOffer() {
            boolean z = false;
            Closed closed = (Closed) null;
            while (needsToCheckOfferWithoutLock() && this.subLock.tryLock()) {
                try {
                    Object objPeekUnderLock = peekUnderLock();
                    if (objPeekUnderLock != AbstractChannelKt.POLL_FAILED) {
                        if (objPeekUnderLock instanceof Closed) {
                            closed = (Closed) objPeekUnderLock;
                        } else {
                            ReceiveOrClosed<E> receiveOrClosedTakeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
                            if (receiveOrClosedTakeFirstReceiveOrPeekClosed != 0 && !(receiveOrClosedTakeFirstReceiveOrPeekClosed instanceof Closed)) {
                                Object objTryResumeReceive = receiveOrClosedTakeFirstReceiveOrPeekClosed.tryResumeReceive(objPeekUnderLock, null);
                                if (objTryResumeReceive != null) {
                                    this.subHead = 1 + this.subHead;
                                    z = true;
                                    this.subLock.unlock();
                                    if (receiveOrClosedTakeFirstReceiveOrPeekClosed == 0) {
                                        Intrinsics.throwNpe();
                                    }
                                    receiveOrClosedTakeFirstReceiveOrPeekClosed.completeResumeReceive(objTryResumeReceive);
                                }
                            }
                        }
                        break;
                    }
                } finally {
                    this.subLock.unlock();
                }
            }
            if (closed != null) {
                close(closed.closeCause);
            }
            return z;
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        protected Object pollInternal() throws Throwable {
            boolean updated = false;
            ReentrantLock $this$withLock$iv = this.subLock;
            ReentrantLock reentrantLock = $this$withLock$iv;
            reentrantLock.lock();
            try {
                Object result = peekUnderLock();
                if (!(result instanceof Closed) && result != AbstractChannelKt.POLL_FAILED) {
                    long subHead = this.subHead;
                    this.subHead = 1 + subHead;
                    updated = true;
                }
                reentrantLock.unlock();
                Closed it = (Closed) (!(result instanceof Closed) ? null : result);
                if (it != null) {
                    close(it.closeCause);
                }
                if (checkOffer()) {
                    updated = true;
                }
                if (updated) {
                    ArrayBroadcastChannel.updateHead$default(this.broadcastChannel, null, null, 3, null);
                }
                return result;
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        protected Object pollSelectInternal(SelectInstance<?> select) throws Throwable {
            Intrinsics.checkParameterIsNotNull(select, "select");
            boolean updated = false;
            ReentrantLock $this$withLock$iv = this.subLock;
            ReentrantLock reentrantLock = $this$withLock$iv;
            reentrantLock.lock();
            try {
                Object result = peekUnderLock();
                if (!(result instanceof Closed) && result != AbstractChannelKt.POLL_FAILED) {
                    if (!select.trySelect(null)) {
                        result = SelectKt.getALREADY_SELECTED();
                    } else {
                        long subHead = this.subHead;
                        this.subHead = 1 + subHead;
                        updated = true;
                    }
                }
                reentrantLock.unlock();
                Object result2 = result;
                Closed it = (Closed) (!(result2 instanceof Closed) ? null : result2);
                if (it != null) {
                    close(it.closeCause);
                }
                if (checkOffer()) {
                    updated = true;
                }
                if (updated) {
                    ArrayBroadcastChannel.updateHead$default(this.broadcastChannel, null, null, 3, null);
                }
                return result2;
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }

        private final boolean needsToCheckOfferWithoutLock() {
            if (getClosedForReceive() != null) {
                return false;
            }
            return (isBufferEmpty() && this.broadcastChannel.getClosedForReceive() == null) ? false : true;
        }

        private final Object peekUnderLock() {
            long subHead = this.subHead;
            Closed closedBroadcast = this.broadcastChannel.getClosedForReceive();
            long tail = ((ArrayBroadcastChannel) this.broadcastChannel).tail;
            if (subHead < tail) {
                Object result = this.broadcastChannel.elementAt(subHead);
                Closed closedSub = getClosedForReceive();
                return closedSub != null ? closedSub : result;
            }
            Closed closedForReceive = closedBroadcast != null ? closedBroadcast : getClosedForReceive();
            return closedForReceive != null ? closedForReceive : AbstractChannelKt.POLL_FAILED;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected String getBufferDebugString() {
        return "(buffer:capacity=" + this.buffer.length + ",size=" + this.size + ')';
    }
}
