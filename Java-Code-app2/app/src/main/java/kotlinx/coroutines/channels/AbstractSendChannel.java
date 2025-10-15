package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.YieldKt;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

/* compiled from: AbstractChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002:\u0005_`abcB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000b\u0010\fJ#\u0010\u0010\u001a\u000e\u0012\u0002\b\u00030\u000ej\u0006\u0012\u0002\b\u0003`\u000f2\u0006\u0010\r\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u0010\u0010\u0011J#\u0010\u0012\u001a\u000e\u0012\u0002\b\u00030\u000ej\u0006\u0012\u0002\b\u0003`\u000f2\u0006\u0010\r\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u0012\u0010\u0011J\u001d\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00132\u0006\u0010\r\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u001b\u0010\u001e\u001a\u00020\u001d2\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001bH\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ)\u0010#\u001a\u00020\u001d2\u0018\u0010\"\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u001d0 j\u0002`!H\u0016¢\u0006\u0004\b#\u0010$J\u0019\u0010%\u001a\u00020\u001d2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b%\u0010&J\u0015\u0010'\u001a\u00020\u00072\u0006\u0010\r\u001a\u00028\u0000¢\u0006\u0004\b'\u0010(J\u0017\u0010)\u001a\u00020\u00182\u0006\u0010\r\u001a\u00028\u0000H\u0014¢\u0006\u0004\b)\u0010*J#\u0010-\u001a\u00020\u00182\u0006\u0010\r\u001a\u00028\u00002\n\u0010,\u001a\u0006\u0012\u0002\b\u00030+H\u0014¢\u0006\u0004\b-\u0010.J\u0017\u00100\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020/H\u0014¢\u0006\u0004\b0\u00101JX\u00106\u001a\u00020\u001d\"\u0004\b\u0001\u001022\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00010+2\u0006\u0010\r\u001a\u00028\u00002(\u00105\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000104\u0012\u0006\u0012\u0004\u0018\u00010\u001803H\u0002ø\u0001\u0000¢\u0006\u0004\b6\u00107J\u001b\u0010\u0017\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0017\u00108J\u001d\u0010:\u001a\b\u0012\u0002\b\u0003\u0018\u0001092\u0006\u0010\r\u001a\u00028\u0000H\u0004¢\u0006\u0004\b:\u0010;J\u001b\u0010=\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00028\u0000H\u0080@ø\u0001\u0000¢\u0006\u0004\b<\u00108J\u001b\u0010>\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00028\u0000H\u0082@ø\u0001\u0000¢\u0006\u0004\b>\u00108J\u0017\u0010?\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u000109H\u0014¢\u0006\u0004\b?\u0010@J\u0011\u0010A\u001a\u0004\u0018\u00010\u0016H\u0004¢\u0006\u0004\bA\u0010BJ\u000f\u0010D\u001a\u00020CH\u0016¢\u0006\u0004\bD\u0010ER\u0016\u0010G\u001a\u00020C8T@\u0014X\u0094\u0004¢\u0006\u0006\u001a\u0004\bF\u0010ER\u001c\u0010J\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001b8D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\bH\u0010IR\u001c\u0010L\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001b8D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\bK\u0010IR\u0016\u0010O\u001a\u00020\u00078B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\bM\u0010NR\u0016\u0010P\u001a\u00020\u00078$@$X¤\u0004¢\u0006\u0006\u001a\u0004\bP\u0010NR\u0016\u0010Q\u001a\u00020\u00078$@$X¤\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010NR\u0013\u0010R\u001a\u00020\u00078F@\u0006¢\u0006\u0006\u001a\u0004\bR\u0010NR\u0013\u0010S\u001a\u00020\u00078F@\u0006¢\u0006\u0006\u001a\u0004\bS\u0010NR%\u0010W\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020T8F@\u0006¢\u0006\u0006\u001a\u0004\bU\u0010VR\u001c\u0010Y\u001a\u00020X8\u0004@\u0004X\u0084\u0004¢\u0006\f\n\u0004\bY\u0010Z\u001a\u0004\b[\u0010\\R\u0016\u0010^\u001a\u00020C8B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b]\u0010E\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006d"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/SendChannel;", "<init>", "()V", "", "cause", "", "close", "(Ljava/lang/Throwable;)Z", "", "countQueueSize", "()I", "element", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/internal/AddLastDesc;", "describeSendBuffered", "(Ljava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "describeSendConflated", "Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "describeTryOffer", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "Lkotlinx/coroutines/channels/Send;", "send", "", "enqueueSend", "(Lkotlinx/coroutines/channels/Send;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/Closed;", "closed", "", "helpClose", "(Lkotlinx/coroutines/channels/Closed;)V", "Lkotlin/Function1;", "Lkotlinx/coroutines/channels/Handler;", "handler", "invokeOnClose", "(Lkotlin/jvm/functions/Function1;)V", "invokeOnCloseHandler", "(Ljava/lang/Throwable;)V", "offer", "(Ljava/lang/Object;)Z", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "offerSelectInternal", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "onClosedIdempotent", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "R", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "block", "registerSelectSend", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "sendBuffered", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/ReceiveOrClosed;", "sendFair$kotlinx_coroutines_core", "sendFair", "sendSuspend", "takeFirstReceiveOrPeekClosed", "()Lkotlinx/coroutines/channels/ReceiveOrClosed;", "takeFirstSendOrPeekClosed", "()Lkotlinx/coroutines/channels/Send;", "", "toString", "()Ljava/lang/String;", "getBufferDebugString", "bufferDebugString", "getClosedForReceive", "()Lkotlinx/coroutines/channels/Closed;", "closedForReceive", "getClosedForSend", "closedForSend", "getFull", "()Z", "full", "isBufferAlwaysFull", "isBufferFull", "isClosedForSend", "isFull", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "onSend", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getQueue", "()Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getQueueDebugStateString", "queueDebugStateString", "SendBuffered", "SendBufferedDesc", "SendConflatedDesc", "SendSelect", "TryOfferDesc", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\channels\AbstractSendChannel.smali */
public abstract class AbstractSendChannel<E> implements SendChannel<E> {
    private static final AtomicReferenceFieldUpdater onCloseHandler$FU = AtomicReferenceFieldUpdater.newUpdater(AbstractSendChannel.class, Object.class, "onCloseHandler");
    private final LockFreeLinkedListHead queue = new LockFreeLinkedListHead();
    private volatile Object onCloseHandler = null;

    protected abstract boolean isBufferAlwaysFull();

    protected abstract boolean isBufferFull();

    protected final LockFreeLinkedListHead getQueue() {
        return this.queue;
    }

    protected Object offerInternal(E element) {
        ReceiveOrClosed receive;
        Object token;
        do {
            receive = takeFirstReceiveOrPeekClosed();
            if (receive == null) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            token = receive.tryResumeReceive(element, null);
        } while (token == null);
        receive.completeResumeReceive(token);
        return receive.getOfferResult();
    }

    protected Object offerSelectInternal(E element, SelectInstance<?> select) {
        Intrinsics.checkParameterIsNotNull(select, "select");
        TryOfferDesc offerOp = describeTryOffer(element);
        Object failure = select.performAtomicTrySelect(offerOp);
        if (failure != null) {
            return failure;
        }
        ReceiveOrClosed receive = offerOp.getResult();
        Object obj = offerOp.resumeToken;
        if (obj == null) {
            Intrinsics.throwNpe();
        }
        receive.completeResumeReceive(obj);
        return receive.getOfferResult();
    }

    protected final Closed<?> getClosedForSend() {
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        if (!(prevNode instanceof Closed)) {
            prevNode = null;
        }
        Closed it = (Closed) prevNode;
        if (it == null) {
            return null;
        }
        helpClose(it);
        return it;
    }

    protected final Closed<?> getClosedForReceive() {
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        if (!(nextNode instanceof Closed)) {
            nextNode = null;
        }
        Closed it = (Closed) nextNode;
        if (it == null) {
            return null;
        }
        helpClose(it);
        return it;
    }

    protected final Send takeFirstSendOrPeekClosed() {
        LockFreeLinkedListNode first$iv;
        LockFreeLinkedListNode this_$iv = this.queue;
        while (true) {
            Object next = this_$iv.getNext();
            if (next == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
            first$iv = (LockFreeLinkedListNode) next;
            if (first$iv != this_$iv) {
                if (!(first$iv instanceof Send)) {
                    first$iv = null;
                    break;
                }
                Send it = (Send) first$iv;
                if ((it instanceof Closed) || first$iv.remove()) {
                    break;
                }
                first$iv.helpDelete();
            } else {
                first$iv = null;
                break;
            }
        }
        return (Send) first$iv;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected final ReceiveOrClosed<?> sendBuffered(E element) {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        LockFreeLinkedListNode this_$iv = this.queue;
        LockFreeLinkedListNode node$iv = new SendBuffered(element);
        do {
            Object prev = this_$iv.getPrev();
            if (prev != null) {
                lockFreeLinkedListNode = (LockFreeLinkedListNode) prev;
                if (lockFreeLinkedListNode instanceof ReceiveOrClosed) {
                    return (ReceiveOrClosed) lockFreeLinkedListNode;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        } while (!lockFreeLinkedListNode.addNext(node$iv, this_$iv));
        return null;
    }

    protected final LockFreeLinkedListNode.AddLastDesc<?> describeSendBuffered(E element) {
        return new SendBufferedDesc(this.queue, element);
    }

    /* compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0012\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0001¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBufferedDesc;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", "Lkotlinx/coroutines/internal/AddLastDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "element", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;Ljava/lang/Object;)V", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 15})
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\channels\AbstractSendChannel$SendBufferedDesc.smali */
    private static class SendBufferedDesc<E> extends LockFreeLinkedListNode.AddLastDesc<SendBuffered<? extends E>> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SendBufferedDesc(LockFreeLinkedListHead queue, E e) {
            super(queue, new SendBuffered(e));
            Intrinsics.checkParameterIsNotNull(queue, "queue");
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected Object failure(LockFreeLinkedListNode affected) {
            Intrinsics.checkParameterIsNotNull(affected, "affected");
            if (affected instanceof Closed) {
                return affected;
            }
            if (affected instanceof ReceiveOrClosed) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            return null;
        }
    }

    protected final LockFreeLinkedListNode.AddLastDesc<?> describeSendConflated(E element) {
        return new SendConflatedDesc(this.queue, element);
    }

    /* compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0001¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0014¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendConflatedDesc;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/AbstractSendChannel$SendBufferedDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "element", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;Ljava/lang/Object;)V", "finishOnSuccess", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "next", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 15})
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\channels\AbstractSendChannel$SendConflatedDesc.smali */
    private static final class SendConflatedDesc<E> extends SendBufferedDesc<E> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SendConflatedDesc(LockFreeLinkedListHead queue, E e) {
            super(queue, e);
            Intrinsics.checkParameterIsNotNull(queue, "queue");
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AddLastDesc, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected void finishOnSuccess(LockFreeLinkedListNode affected, LockFreeLinkedListNode next) {
            Intrinsics.checkParameterIsNotNull(affected, "affected");
            Intrinsics.checkParameterIsNotNull(next, "next");
            super.finishOnSuccess(affected, next);
            SendBuffered sendBuffered = (SendBuffered) (!(affected instanceof SendBuffered) ? null : affected);
            if (sendBuffered != null) {
                sendBuffered.remove();
            }
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean isClosedForSend() {
        return getClosedForSend() != null;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean isFull() {
        return getFull();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getFull() {
        return !(this.queue.getNextNode() instanceof ReceiveOrClosed) && isBufferFull();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final Object send(E e, Continuation<? super Unit> continuation) {
        return offer(e) ? Unit.INSTANCE : sendSuspend(e, continuation);
    }

    public final Object sendFair$kotlinx_coroutines_core(E e, Continuation<? super Unit> continuation) {
        if (offer(e)) {
            return YieldKt.yield(continuation);
        }
        return sendSuspend(e, continuation);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean offer(E element) throws Throwable {
        Throwable it;
        Throwable it2;
        Object result = offerInternal(element);
        if (result == AbstractChannelKt.OFFER_SUCCESS) {
            return true;
        }
        if (result == AbstractChannelKt.OFFER_FAILED) {
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend == null || (it = closedForSend.getSendException()) == null || (it2 = StackTraceRecoveryKt.recoverStackTrace(it)) == null) {
                return false;
            }
            throw it2;
        }
        if (result instanceof Closed) {
            throw StackTraceRecoveryKt.recoverStackTrace(((Closed) result).getSendException());
        }
        throw new IllegalStateException(("offerInternal returned " + result).toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0081 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object sendSuspend(E r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r11 = this;
            r0 = 0
            r1 = r13
            r2 = 0
            kotlinx.coroutines.CancellableContinuationImpl r3 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r1)
            r5 = 0
            r3.<init>(r4, r5)
            r4 = r3
            kotlinx.coroutines.CancellableContinuation r4 = (kotlinx.coroutines.CancellableContinuation) r4
            r5 = 0
        L11:
            boolean r6 = access$getFull$p(r11)
            if (r6 == 0) goto L78
            kotlinx.coroutines.channels.SendElement r6 = new kotlinx.coroutines.channels.SendElement
            r6.<init>(r12, r4)
            r7 = r6
            kotlinx.coroutines.channels.Send r7 = (kotlinx.coroutines.channels.Send) r7
            java.lang.Object r7 = access$enqueueSend(r11, r7)
            if (r7 != 0) goto L2f
            r8 = r6
            kotlinx.coroutines.internal.LockFreeLinkedListNode r8 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r8
            kotlinx.coroutines.CancellableContinuationKt.removeOnCancellation(r4, r8)
            goto Lb8
        L2f:
            boolean r8 = r7 instanceof kotlinx.coroutines.channels.Closed
            if (r8 == 0) goto L51
            r8 = r7
            kotlinx.coroutines.channels.Closed r8 = (kotlinx.coroutines.channels.Closed) r8
            access$helpClose(r11, r8)
            r8 = r4
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r9 = r7
            kotlinx.coroutines.channels.Closed r9 = (kotlinx.coroutines.channels.Closed) r9
            java.lang.Throwable r9 = r9.getSendException()
            kotlin.Result$Companion r10 = kotlin.Result.INSTANCE
            java.lang.Object r9 = kotlin.ResultKt.createFailure(r9)
            java.lang.Object r9 = kotlin.Result.m14constructorimpl(r9)
            r8.resumeWith(r9)
            goto Lb8
        L51:
            java.lang.Object r8 = kotlinx.coroutines.channels.AbstractChannelKt.ENQUEUE_FAILED
            if (r7 != r8) goto L56
            goto L78
        L56:
            boolean r8 = r7 instanceof kotlinx.coroutines.channels.Receive
            if (r8 == 0) goto L5b
            goto L78
        L5b:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "enqueueSend returned "
            r8.append(r9)
            r8.append(r7)
            java.lang.String r8 = r8.toString()
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r8 = r8.toString()
            r9.<init>(r8)
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            throw r9
        L78:
            java.lang.Object r6 = r11.offerInternal(r12)
            java.lang.Object r7 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS
            if (r6 != r7) goto L90
            r7 = r4
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            kotlin.Result$Companion r9 = kotlin.Result.INSTANCE
            java.lang.Object r8 = kotlin.Result.m14constructorimpl(r8)
            r7.resumeWith(r8)
            goto Lb8
        L90:
            java.lang.Object r7 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_FAILED
            if (r6 != r7) goto L96
            goto L11
        L96:
            boolean r7 = r6 instanceof kotlinx.coroutines.channels.Closed
            if (r7 == 0) goto Lc7
            r7 = r6
            kotlinx.coroutines.channels.Closed r7 = (kotlinx.coroutines.channels.Closed) r7
            access$helpClose(r11, r7)
            r7 = r4
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r8 = r6
            kotlinx.coroutines.channels.Closed r8 = (kotlinx.coroutines.channels.Closed) r8
            java.lang.Throwable r8 = r8.getSendException()
            kotlin.Result$Companion r9 = kotlin.Result.INSTANCE
            java.lang.Object r8 = kotlin.ResultKt.createFailure(r8)
            java.lang.Object r8 = kotlin.Result.m14constructorimpl(r8)
            r7.resumeWith(r8)
        Lb8:
            java.lang.Object r3 = r3.getResult()
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r3 != r1) goto Lc5
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r13)
        Lc5:
            return r3
        Lc7:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "offerInternal returned "
            r7.append(r8)
            r7.append(r6)
            java.lang.String r7 = r7.toString()
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r7 = r7.toString()
            r8.<init>(r7)
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.sendSuspend(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object enqueueSend(Send send) {
        boolean z;
        LockFreeLinkedListNode prev$iv;
        if (isBufferAlwaysFull()) {
            LockFreeLinkedListNode this_$iv = this.queue;
            do {
                Object prev = this_$iv.getPrev();
                if (prev != null) {
                    prev$iv = (LockFreeLinkedListNode) prev;
                    if (prev$iv instanceof ReceiveOrClosed) {
                        return prev$iv;
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
                }
            } while (!prev$iv.addNext(send, this_$iv));
            return null;
        }
        LockFreeLinkedListNode this_$iv2 = this.queue;
        final Send send2 = send;
        final Send send3 = send;
        LockFreeLinkedListNode.CondAddOp condAdd$iv = new LockFreeLinkedListNode.CondAddOp(send3) { // from class: kotlinx.coroutines.channels.AbstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1
            @Override // kotlinx.coroutines.internal.AtomicOp
            public Object prepare(LockFreeLinkedListNode affected) {
                Intrinsics.checkParameterIsNotNull(affected, "affected");
                if (this.isBufferFull()) {
                    return null;
                }
                return LockFreeLinkedListKt.getCONDITION_FALSE();
            }
        };
        while (true) {
            Object prev2 = this_$iv2.getPrev();
            if (prev2 != null) {
                LockFreeLinkedListNode prev$iv2 = (LockFreeLinkedListNode) prev2;
                if (!(prev$iv2 instanceof ReceiveOrClosed)) {
                    int iTryCondAddNext = prev$iv2.tryCondAddNext(send, this_$iv2, condAdd$iv);
                    z = true;
                    if (iTryCondAddNext != 1) {
                        if (iTryCondAddNext == 2) {
                            z = false;
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    return prev$iv2;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        }
        if (!z) {
            return AbstractChannelKt.ENQUEUE_FAILED;
        }
        return null;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: close */
    public boolean cancel(Throwable cause) {
        LockFreeLinkedListNode this_$iv;
        Closed closed = new Closed(cause);
        LockFreeLinkedListNode this_$iv2 = this.queue;
        while (true) {
            Object prev = this_$iv2.getPrev();
            if (prev != null) {
                LockFreeLinkedListNode prev$iv = (LockFreeLinkedListNode) prev;
                if (!(!(prev$iv instanceof Closed))) {
                    this_$iv = null;
                    break;
                }
                if (prev$iv.addNext(closed, this_$iv2)) {
                    this_$iv = 1;
                    break;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        }
        if (this_$iv == null) {
            LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
            if (prevNode == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.Closed<*>");
            }
            Closed actualClosed = (Closed) prevNode;
            helpClose(actualClosed);
            return false;
        }
        helpClose(closed);
        invokeOnCloseHandler(cause);
        return true;
    }

    private final void invokeOnCloseHandler(Throwable cause) {
        Object handler = this.onCloseHandler;
        if (handler != null && handler != AbstractChannelKt.HANDLER_INVOKED && onCloseHandler$FU.compareAndSet(this, handler, AbstractChannelKt.HANDLER_INVOKED)) {
            ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(handler, 1)).invoke(cause);
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = onCloseHandler$FU;
        if (!atomicReferenceFieldUpdater.compareAndSet(this, null, handler)) {
            Object value = this.onCloseHandler;
            if (value == AbstractChannelKt.HANDLER_INVOKED) {
                throw new IllegalStateException("Another handler was already registered and successfully invoked");
            }
            throw new IllegalStateException("Another handler was already registered: " + value);
        }
        Closed closedToken = getClosedForSend();
        if (closedToken != null && atomicReferenceFieldUpdater.compareAndSet(this, handler, AbstractChannelKt.HANDLER_INVOKED)) {
            handler.invoke(closedToken.closeCause);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void helpClose(Closed<?> closed) {
        while (true) {
            LockFreeLinkedListNode previous = closed.getPrevNode();
            if ((previous instanceof LockFreeLinkedListHead) || !(previous instanceof Receive)) {
                break;
            }
            if (!previous.remove()) {
                previous.helpRemove();
            } else {
                ((Receive) previous).resumeReceiveClosed(closed);
            }
        }
        onClosedIdempotent(closed);
    }

    protected void onClosedIdempotent(LockFreeLinkedListNode closed) {
        Intrinsics.checkParameterIsNotNull(closed, "closed");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [kotlinx.coroutines.internal.LockFreeLinkedListNode] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v6 */
    protected ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        ?? r2;
        LockFreeLinkedListNode this_$iv = this.queue;
        while (true) {
            Object next = this_$iv.getNext();
            if (next == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
            r2 = (LockFreeLinkedListNode) next;
            if (r2 != this_$iv) {
                if (!(r2 instanceof ReceiveOrClosed)) {
                    r2 = 0;
                    break;
                }
                ReceiveOrClosed it = (ReceiveOrClosed) r2;
                if ((it instanceof Closed) || r2.remove()) {
                    break;
                }
                r2.helpDelete();
            } else {
                r2 = 0;
                break;
            }
        }
        return (ReceiveOrClosed) r2;
    }

    protected final TryOfferDesc<E> describeTryOffer(E element) {
        return new TryOfferDesc<>(element, this.queue);
    }

    /* compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0014J\u0016\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003H\u0014R\u0012\u0010\u0005\u001a\u00028\u00018\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "Lkotlinx/coroutines/internal/RemoveFirstDesc;", "element", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListHead;)V", "Ljava/lang/Object;", "resumeToken", "", "failure", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "validatePrepared", "", "node", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 15})
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\channels\AbstractSendChannel$TryOfferDesc.smali */
    protected static final class TryOfferDesc<E> extends LockFreeLinkedListNode.RemoveFirstDesc<ReceiveOrClosed<? super E>> {
        public final E element;
        public Object resumeToken;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TryOfferDesc(E e, LockFreeLinkedListHead queue) {
            super(queue);
            Intrinsics.checkParameterIsNotNull(queue, "queue");
            this.element = e;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected Object failure(LockFreeLinkedListNode affected) {
            Intrinsics.checkParameterIsNotNull(affected, "affected");
            if (affected instanceof Closed) {
                return affected;
            }
            if (affected instanceof ReceiveOrClosed) {
                return null;
            }
            return AbstractChannelKt.OFFER_FAILED;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc
        public boolean validatePrepared(ReceiveOrClosed<? super E> node) {
            Intrinsics.checkParameterIsNotNull(node, "node");
            Object objTryResumeReceive = node.tryResumeReceive(this.element, this);
            if (objTryResumeReceive == null) {
                return false;
            }
            this.resumeToken = objTryResumeReceive;
            return true;
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final SelectClause2<E, SendChannel<E>> getOnSend() {
        return new SelectClause2<E, SendChannel<? super E>>() { // from class: kotlinx.coroutines.channels.AbstractSendChannel$onSend$1
            @Override // kotlinx.coroutines.selects.SelectClause2
            public <R> void registerSelectClause2(SelectInstance<? super R> select, E param, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> block) throws Throwable {
                Intrinsics.checkParameterIsNotNull(select, "select");
                Intrinsics.checkParameterIsNotNull(block, "block");
                this.this$0.registerSelectSend(select, param, block);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <R> void registerSelectSend(SelectInstance<? super R> select, E element, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> block) throws Throwable {
        while (!select.isSelected()) {
            if (getFull()) {
                SendSelect node = new SendSelect(element, this, select, block);
                Object enqueueResult = enqueueSend(node);
                if (enqueueResult == null) {
                    select.disposeOnSelect(node);
                    return;
                }
                if (enqueueResult instanceof Closed) {
                    helpClose((Closed) enqueueResult);
                    throw StackTraceRecoveryKt.recoverStackTrace(((Closed) enqueueResult).getSendException());
                }
                if (enqueueResult != AbstractChannelKt.ENQUEUE_FAILED && !(enqueueResult instanceof Receive)) {
                    throw new IllegalStateException(("enqueueSend returned " + enqueueResult + ' ').toString());
                }
            }
            Object offerResult = offerSelectInternal(element, select);
            if (offerResult == SelectKt.getALREADY_SELECTED()) {
                return;
            }
            if (offerResult != AbstractChannelKt.OFFER_FAILED) {
                if (offerResult == AbstractChannelKt.OFFER_SUCCESS) {
                    UndispatchedKt.startCoroutineUnintercepted(block, this, select.getCompletion());
                    return;
                } else {
                    if (offerResult instanceof Closed) {
                        helpClose((Closed) offerResult);
                        throw StackTraceRecoveryKt.recoverStackTrace(((Closed) offerResult).getSendException());
                    }
                    throw new IllegalStateException(("offerSelectInternal returned " + offerResult).toString());
                }
            }
        }
    }

    public String toString() {
        return DebugStringsKt.getClassSimpleName(this) + '@' + DebugStringsKt.getHexAddress(this) + '{' + getQueueDebugStateString() + '}' + getBufferDebugString();
    }

    private final String getQueueDebugStateString() {
        String result;
        LockFreeLinkedListNode head = this.queue.getNextNode();
        if (head == this.queue) {
            return "EmptyQueue";
        }
        if (head instanceof Closed) {
            result = head.toString();
        } else if (head instanceof Receive) {
            result = "ReceiveQueued";
        } else if (head instanceof Send) {
            result = "SendQueued";
        } else {
            result = "UNEXPECTED:" + head;
        }
        LockFreeLinkedListNode tail = this.queue.getPrevNode();
        if (tail != head) {
            String result2 = result + ",queueSize=" + countQueueSize();
            if (tail instanceof Closed) {
                return result2 + ",closedForSend=" + tail;
            }
            return result2;
        }
        return result;
    }

    private final int countQueueSize() {
        int size = 0;
        LockFreeLinkedListHead this_$iv = this.queue;
        Object next = this_$iv.getNext();
        if (next == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
        }
        for (LockFreeLinkedListNode cur$iv = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(cur$iv, this_$iv); cur$iv = cur$iv.getNextNode()) {
            if (cur$iv instanceof LockFreeLinkedListNode) {
                size++;
            }
        }
        return size;
    }

    protected String getBufferDebugString() {
        return "";
    }

    /* compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\u00020\u00032\u00020\u0004BX\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00020\n\u0012(\u0010\u000b\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u00060\fø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006H\u0016J\b\u0010\u0015\u001a\u00020\u0013H\u0016J\u0014\u0010\u0016\u001a\u00020\u00132\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0014\u0010\u001b\u001a\u0004\u0018\u00010\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u0006H\u0016R7\u0010\u000b\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u00060\f8\u0006X\u0087\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000fR\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendSelect;", ExifInterface.LONGITUDE_EAST, "R", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/DisposableHandle;", "pollResult", "", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/SendChannel;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "", "token", "dispose", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "idempotent", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 15})
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\channels\AbstractSendChannel$SendSelect.smali */
    private static final class SendSelect<E, R> extends Send implements DisposableHandle {
        public final Function2<SendChannel<? super E>, Continuation<? super R>, Object> block;
        public final SendChannel<E> channel;
        private final Object pollResult;
        public final SelectInstance<R> select;

        /* JADX WARN: Multi-variable type inference failed */
        public SendSelect(Object pollResult, SendChannel<? super E> channel, SelectInstance<? super R> select, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> block) {
            Intrinsics.checkParameterIsNotNull(channel, "channel");
            Intrinsics.checkParameterIsNotNull(select, "select");
            Intrinsics.checkParameterIsNotNull(block, "block");
            this.pollResult = pollResult;
            this.channel = channel;
            this.select = select;
            this.block = block;
        }

        @Override // kotlinx.coroutines.channels.Send
        public Object getPollResult() {
            return this.pollResult;
        }

        @Override // kotlinx.coroutines.channels.Send
        public Object tryResumeSend(Object idempotent) {
            if (this.select.trySelect(idempotent)) {
                return AbstractChannelKt.SELECT_STARTED;
            }
            return null;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void completeResumeSend(Object token) {
            Intrinsics.checkParameterIsNotNull(token, "token");
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(token == AbstractChannelKt.SELECT_STARTED)) {
                    throw new AssertionError();
                }
            }
            ContinuationKt.startCoroutine(this.block, this.channel, this.select.getCompletion());
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            remove();
        }

        @Override // kotlinx.coroutines.channels.Send
        public void resumeSendClosed(Closed<?> closed) {
            Intrinsics.checkParameterIsNotNull(closed, "closed");
            if (this.select.trySelect(null)) {
                this.select.resumeSelectCancellableWithException(closed.getSendException());
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "SendSelect(" + getPollResult() + ")[" + this.channel + ", " + this.select + ']';
        }
    }

    /* compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0016J\u0014\u0010\r\u001a\u00020\u000b2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0016J\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u0007H\u0016R\u0012\u0010\u0003\u001a\u00028\u00018\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/Send;", "element", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "pollResult", "", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "", "token", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "tryResumeSend", "idempotent", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 15})
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\channels\AbstractSendChannel$SendBuffered.smali */
    public static final class SendBuffered<E> extends Send {
        public final E element;

        public SendBuffered(E e) {
            this.element = e;
        }

        @Override // kotlinx.coroutines.channels.Send
        public Object getPollResult() {
            return this.element;
        }

        @Override // kotlinx.coroutines.channels.Send
        public Object tryResumeSend(Object idempotent) {
            return AbstractChannelKt.SEND_RESUMED;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void completeResumeSend(Object token) {
            Intrinsics.checkParameterIsNotNull(token, "token");
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(token == AbstractChannelKt.SEND_RESUMED)) {
                    throw new AssertionError();
                }
            }
        }

        @Override // kotlinx.coroutines.channels.Send
        public void resumeSendClosed(Closed<?> closed) {
            Intrinsics.checkParameterIsNotNull(closed, "closed");
        }
    }
}
