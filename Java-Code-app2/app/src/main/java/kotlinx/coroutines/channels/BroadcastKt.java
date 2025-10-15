package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: Broadcast.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u009c\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2-\b\u0002\u0010\n\u001a'\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000bj\u0004\u0018\u0001`\u00112/\b\u0001\u0010\u0012\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0013¢\u0006\u0002\b\u0017ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001a0\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00192\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"broadcast", "Lkotlinx/coroutines/channels/BroadcastChannel;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/CoroutineScope;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "start", "Lkotlinx/coroutines/CoroutineStart;", "onCompletion", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/BroadcastChannel;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 15})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\channels\BroadcastKt.smali */
public final class BroadcastKt {
    public static /* synthetic */ BroadcastChannel broadcast$default(ReceiveChannel receiveChannel, int i, CoroutineStart coroutineStart, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 1;
        }
        if ((i2 & 2) != 0) {
            coroutineStart = CoroutineStart.LAZY;
        }
        return broadcast(receiveChannel, i, coroutineStart);
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* compiled from: Broadcast.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.channels.BroadcastKt$broadcast$1", f = "Broadcast.kt", i = {0, 1, 1}, l = {29, 30}, m = "invokeSuspend", n = {"$this$broadcast", "$this$broadcast", "e"}, s = {"L$0", "L$0", "L$1"})
    /* renamed from: kotlinx.coroutines.channels.BroadcastKt$broadcast$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\channels\BroadcastKt$broadcast$1.smali */
    static final class AnonymousClass1<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
        final /* synthetic */ ReceiveChannel $this_broadcast;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        private ProducerScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ReceiveChannel receiveChannel, Continuation continuation) {
            super(2, continuation);
            this.$this_broadcast = receiveChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_broadcast, completion);
            anonymousClass1.p$ = (ProducerScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0054 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0055  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0063  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0081  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0076 -> B:22:0x007e). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) throws java.lang.Throwable {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L3c
                r4 = 0
                if (r1 == r3) goto L29
                if (r1 != r2) goto L21
                r1 = r4
                java.lang.Object r5 = r10.L$2
                kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
                java.lang.Object r4 = r10.L$1
                java.lang.Object r6 = r10.L$0
                r1 = r6
                kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
                kotlin.ResultKt.throwOnFailure(r11)
                r6 = r10
                goto L7e
            L21:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L29:
                r1 = r4
                java.lang.Object r4 = r10.L$1
                kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
                java.lang.Object r5 = r10.L$0
                r1 = r5
                kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
                kotlin.ResultKt.throwOnFailure(r11)
                r5 = r10
                r6 = r4
                r4 = r1
                r1 = r0
                r0 = r11
                goto L5b
            L3c:
                kotlin.ResultKt.throwOnFailure(r11)
                kotlinx.coroutines.channels.ProducerScope r1 = r10.p$
                kotlinx.coroutines.channels.ReceiveChannel r4 = r10.$this_broadcast
                kotlinx.coroutines.channels.ChannelIterator r4 = r4.iterator()
                r5 = r10
            L48:
                r5.L$0 = r1
                r5.L$1 = r4
                r5.label = r3
                java.lang.Object r6 = r4.hasNext(r5)
                if (r6 != r0) goto L55
                return r0
            L55:
                r8 = r0
                r0 = r11
                r11 = r6
                r6 = r4
                r4 = r1
                r1 = r8
            L5b:
                java.lang.Boolean r11 = (java.lang.Boolean) r11
                boolean r11 = r11.booleanValue()
                if (r11 == 0) goto L81
                java.lang.Object r11 = r6.next()
                r5.L$0 = r4
                r5.L$1 = r11
                r5.L$2 = r6
                r5.label = r2
                java.lang.Object r7 = r4.send(r11, r5)
                if (r7 != r1) goto L76
                return r1
            L76:
                r8 = r4
                r4 = r11
                r11 = r0
                r0 = r1
                r1 = r8
                r9 = r6
                r6 = r5
                r5 = r9
            L7e:
                r4 = r5
                r5 = r6
                goto L48
            L81:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BroadcastKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final <E> BroadcastChannel<E> broadcast(ReceiveChannel<? extends E> broadcast, int capacity, CoroutineStart start) {
        Intrinsics.checkParameterIsNotNull(broadcast, "$this$broadcast");
        Intrinsics.checkParameterIsNotNull(start, "start");
        return broadcast(GlobalScope.INSTANCE, Dispatchers.getUnconfined(), capacity, start, ChannelsKt.consumes(broadcast), new AnonymousClass1(broadcast, null));
    }

    public static final <E> BroadcastChannel<E> broadcast(CoroutineScope broadcast, CoroutineContext context, int capacity, CoroutineStart start, Function1<? super Throwable, Unit> function1, Function2<? super ProducerScope<? super E>, ? super Continuation<? super Unit>, ? extends Object> block) {
        LazyBroadcastCoroutine coroutine;
        Intrinsics.checkParameterIsNotNull(broadcast, "$this$broadcast");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(start, "start");
        Intrinsics.checkParameterIsNotNull(block, "block");
        CoroutineContext newContext = CoroutineContextKt.newCoroutineContext(broadcast, context);
        BroadcastChannel channel = BroadcastChannelKt.BroadcastChannel(capacity);
        if (start.isLazy()) {
            coroutine = new LazyBroadcastCoroutine(newContext, channel, block);
        } else {
            coroutine = new BroadcastCoroutine(newContext, channel, true);
        }
        if (function1 != null) {
            coroutine.invokeOnCompletion(function1);
        }
        coroutine.start(start, coroutine, block);
        return coroutine;
    }
}
