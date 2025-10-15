package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.FlowCoroutineKt;

/* compiled from: Delay.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a&\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u001a$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006*\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u0004H\u0000\u001a&\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\f\u001a\u00020\u0004H\u0007¨\u0006\r"}, d2 = {"debounce", "Lkotlinx/coroutines/flow/Flow;", ExifInterface.GPS_DIRECTION_TRUE, "timeoutMillis", "", "fixedPeriodTicker", "Lkotlinx/coroutines/channels/ReceiveChannel;", "", "Lkotlinx/coroutines/CoroutineScope;", "delayMillis", "initialDelayMillis", "sample", "periodMillis", "kotlinx-coroutines-core"}, k = 5, mv = {1, 1, 15}, xs = "kotlinx/coroutines/flow/FlowKt")
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\FlowKt__DelayKt.smali */
final /* synthetic */ class FlowKt__DelayKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: Delay.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "downstream", "Lkotlinx/coroutines/flow/FlowCollector;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2", f = "Delay.kt", i = {0, 0, 0, 0}, l = {137}, m = "invokeSuspend", n = {"$this$scopedFlow", "downstream", "values", "lastValue"}, s = {"L$0", "L$1", "L$2", "L$3"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\FlowKt__DelayKt$debounce$2.smali */
    static final class AnonymousClass2<T> extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Flow $this_debounce;
        final /* synthetic */ long $timeoutMillis;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        private CoroutineScope p$;
        private FlowCollector p$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Flow flow, long j, Continuation continuation) {
            super(3, continuation);
            this.$this_debounce = flow;
            this.$timeoutMillis = j;
        }

        public final Continuation<Unit> create(CoroutineScope create, FlowCollector<? super T> downstream, Continuation<? super Unit> continuation) {
            Intrinsics.checkParameterIsNotNull(create, "$this$create");
            Intrinsics.checkParameterIsNotNull(downstream, "downstream");
            Intrinsics.checkParameterIsNotNull(continuation, "continuation");
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_debounce, this.$timeoutMillis, continuation);
            anonymousClass2.p$ = create;
            anonymousClass2.p$0 = downstream;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, Object obj, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, (FlowCollector) obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Can't wrap try/catch for region: R(11:11|42|12|13|38|14|(4:16|17|40|18)(1:22)|23|29|(1:31)|(1:33)(4:34|35|9|(2:36|37)(0))) */
        /* JADX WARN: Can't wrap try/catch for region: R(4:16|17|40|18) */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x00cb, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x00d5, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x00d6, code lost:
        
            r24 = r12;
            r25 = r13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x00e4, code lost:
        
            r3 = r20;
            r3.handleBuilderException(r0);
         */
        /* JADX WARN: Removed duplicated region for block: B:11:0x006d  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00f4  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00fd A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00fe  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0106  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00fe -> B:35:0x0102). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r27) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 265
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final <T> Flow<T> debounce(Flow<? extends T> debounce, long timeoutMillis) {
        Intrinsics.checkParameterIsNotNull(debounce, "$this$debounce");
        if (timeoutMillis > 0) {
            return FlowCoroutineKt.scopedFlow(new AnonymousClass2(debounce, timeoutMillis, null));
        }
        throw new IllegalArgumentException("Debounce timeout should be positive".toString());
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: Delay.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "downstream", "Lkotlinx/coroutines/flow/FlowCollector;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2", f = "Delay.kt", i = {0, 0, 0, 0, 0}, l = {137}, m = "invokeSuspend", n = {"$this$scopedFlow", "downstream", "values", "lastValue", "ticker"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\FlowKt__DelayKt$sample$2.smali */
    static final class C01662<T> extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $periodMillis;
        final /* synthetic */ Flow $this_sample;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        private CoroutineScope p$;
        private FlowCollector p$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01662(Flow flow, long j, Continuation continuation) {
            super(3, continuation);
            this.$this_sample = flow;
            this.$periodMillis = j;
        }

        public final Continuation<Unit> create(CoroutineScope create, FlowCollector<? super T> downstream, Continuation<? super Unit> continuation) {
            Intrinsics.checkParameterIsNotNull(create, "$this$create");
            Intrinsics.checkParameterIsNotNull(downstream, "downstream");
            Intrinsics.checkParameterIsNotNull(continuation, "continuation");
            C01662 c01662 = new C01662(this.$this_sample, this.$periodMillis, continuation);
            c01662.p$ = create;
            c01662.p$0 = downstream;
            return c01662;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, Object obj, Continuation<? super Unit> continuation) {
            return ((C01662) create(coroutineScope, (FlowCollector) obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Can't wrap try/catch for region: R(10:11|32|12|13|30|14|15|21|(1:23)|(1:25)(4:26|27|9|(2:28|29)(0))) */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x00d7, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x00e0, code lost:
        
            r3 = r20;
            r3.handleBuilderException(r0);
         */
        /* JADX WARN: Removed duplicated region for block: B:11:0x007e  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00f0  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x00f9 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00fa  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0100  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00fa -> B:27:0x00fc). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r24) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 259
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt.C01662.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final <T> Flow<T> sample(Flow<? extends T> sample, long periodMillis) {
        Intrinsics.checkParameterIsNotNull(sample, "$this$sample");
        if (periodMillis > 0) {
            return FlowCoroutineKt.scopedFlow(new C01662(sample, periodMillis, null));
        }
        throw new IllegalArgumentException("Sample period should be positive".toString());
    }

    public static /* synthetic */ ReceiveChannel fixedPeriodTicker$default(CoroutineScope coroutineScope, long j, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            j2 = j;
        }
        return FlowKt.fixedPeriodTicker(coroutineScope, j, j2);
    }

    public static final ReceiveChannel<Unit> fixedPeriodTicker(CoroutineScope fixedPeriodTicker, long delayMillis, long initialDelayMillis) {
        Intrinsics.checkParameterIsNotNull(fixedPeriodTicker, "$this$fixedPeriodTicker");
        if (!(delayMillis >= 0)) {
            throw new IllegalArgumentException(("Expected non-negative delay, but has " + delayMillis + " ms").toString());
        }
        if (initialDelayMillis >= 0) {
            return ProduceKt.produce$default(fixedPeriodTicker, null, 0, new AnonymousClass3(initialDelayMillis, delayMillis, null), 1, null);
        }
        throw new IllegalArgumentException(("Expected non-negative initial delay, but has " + initialDelayMillis + " ms").toString());
    }

    /* compiled from: Delay.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$fixedPeriodTicker$3", f = "Delay.kt", i = {0, 1, 2}, l = {129, 131, 132}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", "$this$produce"}, s = {"L$0", "L$0", "L$0"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__DelayKt$fixedPeriodTicker$3, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\FlowKt__DelayKt$fixedPeriodTicker$3.smali */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<ProducerScope<? super Unit>, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $delayMillis;
        final /* synthetic */ long $initialDelayMillis;
        Object L$0;
        int label;
        private ProducerScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(long j, long j2, Continuation continuation) {
            super(2, continuation);
            this.$initialDelayMillis = j;
            this.$delayMillis = j2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$initialDelayMillis, this.$delayMillis, completion);
            anonymousClass3.p$ = (ProducerScope) obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super Unit> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        /* JADX WARN: Removed duplicated region for block: B:19:0x005d A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:22:0x006a A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0068 -> B:17:0x004d). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L3a
                r5 = 0
                if (r1 == r4) goto L30
                if (r1 == r3) goto L25
                if (r1 != r2) goto L1d
                r1 = r5
                java.lang.Object r4 = r7.L$0
                r1 = r4
                kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
                kotlin.ResultKt.throwOnFailure(r8)
                r4 = r7
                goto L6b
            L1d:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L25:
                r1 = r5
                java.lang.Object r4 = r7.L$0
                r1 = r4
                kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
                kotlin.ResultKt.throwOnFailure(r8)
                r4 = r7
                goto L5e
            L30:
                r1 = r5
                java.lang.Object r4 = r7.L$0
                r1 = r4
                kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
                kotlin.ResultKt.throwOnFailure(r8)
                goto L4c
            L3a:
                kotlin.ResultKt.throwOnFailure(r8)
                kotlinx.coroutines.channels.ProducerScope r1 = r7.p$
                long r5 = r7.$initialDelayMillis
                r7.L$0 = r1
                r7.label = r4
                java.lang.Object r4 = kotlinx.coroutines.DelayKt.delay(r5, r7)
                if (r4 != r0) goto L4c
                return r0
            L4c:
                r4 = r7
            L4d:
                kotlinx.coroutines.channels.SendChannel r5 = r1.getChannel()
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                r4.L$0 = r1
                r4.label = r3
                java.lang.Object r5 = r5.send(r6, r4)
                if (r5 != r0) goto L5e
                return r0
            L5e:
                long r5 = r4.$delayMillis
                r4.L$0 = r1
                r4.label = r2
                java.lang.Object r5 = kotlinx.coroutines.DelayKt.delay(r5, r4)
                if (r5 != r0) goto L6b
                return r0
            L6b:
                goto L4d
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
