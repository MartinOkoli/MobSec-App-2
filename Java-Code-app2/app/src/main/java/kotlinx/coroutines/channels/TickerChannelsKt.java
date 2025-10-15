package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: TickerChannels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a/\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a4\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"fixedDelayTicker", "", "delayMillis", "", "initialDelayMillis", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "(JJLkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fixedPeriodTicker", "ticker", "Lkotlinx/coroutines/channels/ReceiveChannel;", "context", "Lkotlin/coroutines/CoroutineContext;", "mode", "Lkotlinx/coroutines/channels/TickerMode;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 15})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\channels\TickerChannelsKt.smali */
public final class TickerChannelsKt {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 15})
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\channels\TickerChannelsKt$WhenMappings.smali */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TickerMode.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[TickerMode.FIXED_PERIOD.ordinal()] = 1;
            iArr[TickerMode.FIXED_DELAY.ordinal()] = 2;
        }
    }

    /* compiled from: TickerChannels.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0082@"}, d2 = {"fixedDelayTicker", "", "delayMillis", "", "initialDelayMillis", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.channels.TickerChannelsKt", f = "TickerChannels.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 2}, l = {106, 108, 109}, m = "fixedDelayTicker", n = {"delayMillis", "initialDelayMillis", "channel", "delayMillis", "initialDelayMillis", "channel", "delayMillis", "initialDelayMillis", "channel"}, s = {"J$0", "J$1", "L$0", "J$0", "J$1", "L$0", "J$0", "J$1", "L$0"})
    /* renamed from: kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\channels\TickerChannelsKt$fixedDelayTicker$1.smali */
    static final class AnonymousClass1 extends ContinuationImpl {
        long J$0;
        long J$1;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TickerChannelsKt.fixedDelayTicker(0L, 0L, null, this);
        }
    }

    /* compiled from: TickerChannels.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0082@"}, d2 = {"fixedPeriodTicker", "", "delayMillis", "", "initialDelayMillis", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.channels.TickerChannelsKt", f = "TickerChannels.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3}, l = {84, 88, 94, 96}, m = "fixedPeriodTicker", n = {"delayMillis", "initialDelayMillis", "channel", "deadline", "delayMillis", "initialDelayMillis", "channel", "deadline", "delayNs", "delayMillis", "initialDelayMillis", "channel", "deadline", "delayNs", "now", "nextDelay", "adjustedDelay", "delayMillis", "initialDelayMillis", "channel", "deadline", "delayNs", "now", "nextDelay"}, s = {"J$0", "J$1", "L$0", "J$2", "J$0", "J$1", "L$0", "J$2", "J$3", "J$0", "J$1", "L$0", "J$2", "J$3", "J$4", "J$5", "J$6", "J$0", "J$1", "L$0", "J$2", "J$3", "J$4", "J$5"})
    /* renamed from: kotlinx.coroutines.channels.TickerChannelsKt$fixedPeriodTicker$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\channels\TickerChannelsKt$fixedPeriodTicker$1.smali */
    static final class C01651 extends ContinuationImpl {
        long J$0;
        long J$1;
        long J$2;
        long J$3;
        long J$4;
        long J$5;
        long J$6;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C01651(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TickerChannelsKt.fixedPeriodTicker(0L, 0L, null, this);
        }
    }

    public static /* synthetic */ ReceiveChannel ticker$default(long j, long j2, CoroutineContext coroutineContext, TickerMode tickerMode, int i, Object obj) {
        if ((i & 2) != 0) {
            j2 = j;
        }
        if ((i & 4) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 8) != 0) {
            tickerMode = TickerMode.FIXED_PERIOD;
        }
        return ticker(j, j2, coroutineContext, tickerMode);
    }

    public static final ReceiveChannel<Unit> ticker(long delayMillis, long initialDelayMillis, CoroutineContext context, TickerMode mode) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        if (!(delayMillis >= 0)) {
            throw new IllegalArgumentException(("Expected non-negative delay, but has " + delayMillis + " ms").toString());
        }
        if (initialDelayMillis >= 0) {
            return ProduceKt.produce(GlobalScope.INSTANCE, Dispatchers.getUnconfined().plus(context), 0, new AnonymousClass3(mode, delayMillis, initialDelayMillis, null));
        }
        throw new IllegalArgumentException(("Expected non-negative initial delay, but has " + initialDelayMillis + " ms").toString());
    }

    /* compiled from: TickerChannels.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.channels.TickerChannelsKt$ticker$3", f = "TickerChannels.kt", i = {0, 1}, l = {72, 73}, m = "invokeSuspend", n = {"$this$produce", "$this$produce"}, s = {"L$0", "L$0"})
    /* renamed from: kotlinx.coroutines.channels.TickerChannelsKt$ticker$3, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\channels\TickerChannelsKt$ticker$3.smali */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<ProducerScope<? super Unit>, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $delayMillis;
        final /* synthetic */ long $initialDelayMillis;
        final /* synthetic */ TickerMode $mode;
        Object L$0;
        int label;
        private ProducerScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(TickerMode tickerMode, long j, long j2, Continuation continuation) {
            super(2, continuation);
            this.$mode = tickerMode;
            this.$delayMillis = j;
            this.$initialDelayMillis = j2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$mode, this.$delayMillis, this.$initialDelayMillis, completion);
            anonymousClass3.p$ = (ProducerScope) obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super Unit> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws Throwable {
            ProducerScope $this$produce;
            ProducerScope $this$produce2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure($result);
                ProducerScope $this$produce3 = this.p$;
                int i2 = WhenMappings.$EnumSwitchMapping$0[this.$mode.ordinal()];
                if (i2 == 1) {
                    long j = this.$delayMillis;
                    long j2 = this.$initialDelayMillis;
                    SendChannel channel = $this$produce3.getChannel();
                    this.L$0 = $this$produce3;
                    this.label = 1;
                    if (TickerChannelsKt.fixedPeriodTicker(j, j2, channel, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $this$produce = $this$produce3;
                } else if (i2 == 2) {
                    long j3 = this.$delayMillis;
                    long j4 = this.$initialDelayMillis;
                    SendChannel channel2 = $this$produce3.getChannel();
                    this.L$0 = $this$produce3;
                    this.label = 2;
                    if (TickerChannelsKt.fixedDelayTicker(j3, j4, channel2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $this$produce2 = $this$produce3;
                }
            } else if (i == 1) {
                $this$produce = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure($result);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                $this$produce2 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure($result);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Path cross not found for [B:36:0x011b, B:39:0x012a], limit reached: 56 */
    /* JADX WARN: Removed duplicated region for block: B:32:0x010e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01be A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:52:0x01bf -> B:53:0x01c9). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final /* synthetic */ java.lang.Object fixedPeriodTicker(long r27, long r29, kotlinx.coroutines.channels.SendChannel<? super kotlin.Unit> r31, kotlin.coroutines.Continuation<? super kotlin.Unit> r32) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 470
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.TickerChannelsKt.fixedPeriodTicker(long, long, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0072 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0081 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x007f -> B:21:0x0061). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final /* synthetic */ java.lang.Object fixedDelayTicker(long r7, long r9, kotlinx.coroutines.channels.SendChannel<? super kotlin.Unit> r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.TickerChannelsKt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r12
            kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1 r0 = (kotlinx.coroutines.channels.TickerChannelsKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1 r0 = new kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1
            r0.<init>(r12)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 3
            r5 = 2
            r6 = 1
            if (r3 == 0) goto L4f
            if (r3 == r6) goto L42
            if (r3 == r5) goto L35
            if (r3 != r4) goto L2d
            goto L42
        L2d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L35:
            java.lang.Object r3 = r0.L$0
            r11 = r3
            kotlinx.coroutines.channels.SendChannel r11 = (kotlinx.coroutines.channels.SendChannel) r11
            long r9 = r0.J$1
            long r7 = r0.J$0
            kotlin.ResultKt.throwOnFailure(r1)
            goto L73
        L42:
            java.lang.Object r3 = r0.L$0
            r11 = r3
            kotlinx.coroutines.channels.SendChannel r11 = (kotlinx.coroutines.channels.SendChannel) r11
            long r9 = r0.J$1
            long r7 = r0.J$0
            kotlin.ResultKt.throwOnFailure(r1)
            goto L61
        L4f:
            kotlin.ResultKt.throwOnFailure(r1)
            r0.J$0 = r7
            r0.J$1 = r9
            r0.L$0 = r11
            r0.label = r6
            java.lang.Object r3 = kotlinx.coroutines.DelayKt.delay(r9, r0)
            if (r3 != r2) goto L61
            return r2
        L61:
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r0.J$0 = r7
            r0.J$1 = r9
            r0.L$0 = r11
            r0.label = r5
            java.lang.Object r3 = r11.send(r3, r0)
            if (r3 != r2) goto L73
            return r2
        L73:
            r0.J$0 = r7
            r0.J$1 = r9
            r0.L$0 = r11
            r0.label = r4
            java.lang.Object r3 = kotlinx.coroutines.DelayKt.delay(r7, r0)
            if (r3 != r2) goto L82
            return r2
        L82:
            goto L61
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.TickerChannelsKt.fixedDelayTicker(long, long, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
