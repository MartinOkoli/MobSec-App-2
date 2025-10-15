package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Ref;

/* compiled from: Reduce.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a!\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001aE\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001ay\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\f\u001a\u0002H\u000b2H\b\u0004\u0010\r\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u000b¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u0011H\u0001¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000eH\u0087Hø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001as\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\u0015\"\b\b\u0001\u0010\u0001*\u0002H\u0015*\b\u0012\u0004\u0012\u0002H\u00010\u00022F\u0010\r\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u0015¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u0011H\u0001¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00150\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000eH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a!\u0010\u0018\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001a'\u0010\u0019\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\b*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"first", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "predicate", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fold", "R", "initial", "operation", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "acc", "value", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reduce", ExifInterface.LATITUDE_SOUTH, "accumulator", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "single", "singleOrNull", "kotlinx-coroutines-core"}, k = 5, mv = {1, 1, 15}, xs = "kotlinx/coroutines/flow/FlowKt")
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\FlowKt__ReduceKt.smali */
final /* synthetic */ class FlowKt__ReduceKt {

    /* compiled from: Reduce.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0086@"}, d2 = {"first", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/Flow;", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", f = "Reduce.kt", i = {0, 0, 0}, l = {139}, m = "first", n = {"$this$first", "result", "$this$collect$iv"}, s = {"L$0", "L$1", "L$2"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$first$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\FlowKt__ReduceKt$first$1.smali */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.first(null, this);
        }
    }

    /* compiled from: Reduce.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0086@"}, d2 = {"first", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/Flow;", "predicate", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "continuation"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", f = "Reduce.kt", i = {0, 0, 0, 0}, l = {142}, m = "first", n = {"$this$first", "predicate", "result", "$this$collect$iv"}, s = {"L$0", "L$1", "L$2", "L$3"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\FlowKt__ReduceKt$first$3.smali */
    static final class AnonymousClass3 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass3(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.first(null, null, this);
        }
    }

    /* compiled from: Reduce.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u00032H\b\u0004\u0010\u0006\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00030\fH\u0087H"}, d2 = {"fold", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/Flow;", "initial", "operation", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "acc", "value", "Lkotlin/coroutines/Continuation;", "continuation"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", f = "Reduce.kt", i = {0, 0, 0, 0, 0}, l = {127}, m = "fold", n = {"$this$fold", "initial", "operation", "accumulator", "$this$collect$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\FlowKt__ReduceKt$fold$1.smali */
    public static final class C01721 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C01721(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.fold(null, null, null, this);
        }
    }

    /* compiled from: Reduce.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u0002H\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u00042F\u0010\u0005\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000bH\u0087@"}, d2 = {"reduce", "", ExifInterface.LATITUDE_SOUTH, ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/Flow;", "operation", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "accumulator", "value", "Lkotlin/coroutines/Continuation;", "continuation"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", f = "Reduce.kt", i = {0, 0, 0, 0}, l = {124}, m = "reduce", n = {"$this$reduce", "operation", "accumulator", "$this$collect$iv"}, s = {"L$0", "L$1", "L$2", "L$3"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\FlowKt__ReduceKt$reduce$1.smali */
    static final class C01731 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C01731(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.reduce(null, null, this);
        }
    }

    /* compiled from: Reduce.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0086@"}, d2 = {"single", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/Flow;", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", f = "Reduce.kt", i = {0, 0, 0}, l = {133}, m = "single", n = {"$this$single", "result", "$this$collect$iv"}, s = {"L$0", "L$1", "L$2"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$single$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\FlowKt__ReduceKt$single$1.smali */
    static final class C01741 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C01741(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.single(null, this);
        }
    }

    /* compiled from: Reduce.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0005H\u0086@"}, d2 = {"singleOrNull", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/Flow;", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", f = "Reduce.kt", i = {0, 0, 0}, l = {136}, m = "singleOrNull", n = {"$this$singleOrNull", "result", "$this$collect$iv"}, s = {"L$0", "L$1", "L$2"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$singleOrNull$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\FlowKt__ReduceKt$singleOrNull$1.smali */
    static final class C01751 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C01751(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.singleOrNull(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Type inference failed for: r5v0, types: [T, kotlinx.coroutines.internal.Symbol] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <S, T extends S> java.lang.Object reduce(kotlinx.coroutines.flow.Flow<? extends T> r8, kotlin.jvm.functions.Function3<? super S, ? super T, ? super kotlin.coroutines.Continuation<? super S>, ? extends java.lang.Object> r9, kotlin.coroutines.Continuation<? super S> r10) throws java.lang.Throwable {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.C01731
            if (r0 == 0) goto L14
            r0 = r10
            kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.C01731) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$1 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$1
            r0.<init>(r10)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 1
            if (r3 == 0) goto L49
            if (r3 != r4) goto L41
            r2 = 0
            r3 = r2
            r4 = 0
            java.lang.Object r5 = r0.L$3
            r3 = r5
            kotlinx.coroutines.flow.Flow r3 = (kotlinx.coroutines.flow.Flow) r3
            java.lang.Object r5 = r0.L$2
            r2 = r5
            kotlin.jvm.internal.Ref$ObjectRef r2 = (kotlin.jvm.internal.Ref.ObjectRef) r2
            java.lang.Object r5 = r0.L$1
            r9 = r5
            kotlin.jvm.functions.Function3 r9 = (kotlin.jvm.functions.Function3) r9
            java.lang.Object r5 = r0.L$0
            r8 = r5
            kotlinx.coroutines.flow.Flow r8 = (kotlinx.coroutines.flow.Flow) r8
            kotlin.ResultKt.throwOnFailure(r1)
            goto L70
        L41:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L49:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlin.jvm.internal.Ref$ObjectRef r3 = new kotlin.jvm.internal.Ref$ObjectRef
            r3.<init>()
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            r3.element = r5
            r5 = r8
            r6 = 0
            kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$$inlined$collect$1 r7 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$$inlined$collect$1
            r7.<init>(r3, r9)
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r3
            r0.L$3 = r5
            r0.label = r4
            java.lang.Object r4 = r5.collect(r7, r0)
            if (r4 != r2) goto L6f
            return r2
        L6f:
            r2 = r3
        L70:
            T r3 = r2.element
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            if (r3 == r4) goto L7b
        L78:
            T r3 = r2.element
            return r3
        L7b:
            java.lang.UnsupportedOperationException r3 = new java.lang.UnsupportedOperationException
            java.lang.String r4 = "Empty flow can't be reduced"
            r3.<init>(r4)
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt.reduce(kotlinx.coroutines.flow.Flow, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T, R> java.lang.Object fold(kotlinx.coroutines.flow.Flow<? extends T> r9, R r10, kotlin.jvm.functions.Function3<? super R, ? super T, ? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object> r11, kotlin.coroutines.Continuation<? super R> r12) throws java.lang.Throwable {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.C01721
            if (r0 == 0) goto L14
            r0 = r12
            kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.C01721) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$1 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$1
            r0.<init>(r12)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 1
            if (r3 == 0) goto L4c
            if (r3 != r4) goto L44
            r2 = 0
            r3 = r2
            r4 = 0
            r5 = r4
            java.lang.Object r6 = r0.L$4
            r3 = r6
            kotlinx.coroutines.flow.Flow r3 = (kotlinx.coroutines.flow.Flow) r3
            java.lang.Object r6 = r0.L$3
            r2 = r6
            kotlin.jvm.internal.Ref$ObjectRef r2 = (kotlin.jvm.internal.Ref.ObjectRef) r2
            java.lang.Object r6 = r0.L$2
            r11 = r6
            kotlin.jvm.functions.Function3 r11 = (kotlin.jvm.functions.Function3) r11
            java.lang.Object r10 = r0.L$1
            java.lang.Object r6 = r0.L$0
            r9 = r6
            kotlinx.coroutines.flow.Flow r9 = (kotlinx.coroutines.flow.Flow) r9
            kotlin.ResultKt.throwOnFailure(r1)
            goto L74
        L44:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L4c:
            kotlin.ResultKt.throwOnFailure(r1)
            r5 = 0
            kotlin.jvm.internal.Ref$ObjectRef r3 = new kotlin.jvm.internal.Ref$ObjectRef
            r3.<init>()
            r3.element = r10
            r6 = r9
            r7 = 0
            kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$$inlined$collect$1 r8 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$$inlined$collect$1
            r8.<init>(r3, r11)
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            r0.L$0 = r9
            r0.L$1 = r10
            r0.L$2 = r11
            r0.L$3 = r3
            r0.L$4 = r6
            r0.label = r4
            java.lang.Object r4 = r6.collect(r8, r0)
            if (r4 != r2) goto L73
            return r2
        L73:
            r2 = r3
        L74:
            T r3 = r2.element
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt.fold(kotlinx.coroutines.flow.Flow, java.lang.Object, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final Object fold$$forInline(Flow $this$fold, Object obj, Function3 operation, Continuation continuation) {
        Ref.ObjectRef accumulator = new Ref.ObjectRef();
        accumulator.element = obj;
        FlowKt__ReduceKt$fold$$inlined$collect$1 flowKt__ReduceKt$fold$$inlined$collect$1 = new FlowKt__ReduceKt$fold$$inlined$collect$1(accumulator, operation);
        InlineMarker.mark(0);
        $this$fold.collect(flowKt__ReduceKt$fold$$inlined$collect$1, continuation);
        InlineMarker.mark(2);
        InlineMarker.mark(1);
        return accumulator.element;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object single(kotlinx.coroutines.flow.Flow<? extends T> r8, kotlin.coroutines.Continuation<? super T> r9) throws java.lang.Throwable {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.C01741
            if (r0 == 0) goto L14
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__ReduceKt$single$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.C01741) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            kotlinx.coroutines.flow.FlowKt__ReduceKt$single$1 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$single$1
            r0.<init>(r9)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 1
            if (r3 == 0) goto L44
            if (r3 != r4) goto L3c
            r2 = 0
            r3 = r2
            r4 = 0
            java.lang.Object r5 = r0.L$2
            r2 = r5
            kotlinx.coroutines.flow.Flow r2 = (kotlinx.coroutines.flow.Flow) r2
            java.lang.Object r5 = r0.L$1
            r3 = r5
            kotlin.jvm.internal.Ref$ObjectRef r3 = (kotlin.jvm.internal.Ref.ObjectRef) r3
            java.lang.Object r5 = r0.L$0
            r8 = r5
            kotlinx.coroutines.flow.Flow r8 = (kotlinx.coroutines.flow.Flow) r8
            kotlin.ResultKt.throwOnFailure(r1)
            goto L68
        L3c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L44:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlin.jvm.internal.Ref$ObjectRef r3 = new kotlin.jvm.internal.Ref$ObjectRef
            r3.<init>()
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            r3.element = r5
            r5 = r8
            r6 = 0
            kotlinx.coroutines.flow.FlowKt__ReduceKt$single$$inlined$collect$1 r7 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$single$$inlined$collect$1
            r7.<init>()
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
            r0.L$0 = r8
            r0.L$1 = r3
            r0.L$2 = r5
            r0.label = r4
            java.lang.Object r4 = r5.collect(r7, r0)
            if (r4 != r2) goto L68
            return r2
        L68:
            T r2 = r3.element
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            if (r2 == r4) goto L73
        L70:
            T r2 = r3.element
            return r2
        L73:
            java.util.NoSuchElementException r2 = new java.util.NoSuchElementException
            java.lang.String r4 = "Expected at least one element"
            r2.<init>(r4)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt.single(kotlinx.coroutines.flow.Flow, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object singleOrNull(kotlinx.coroutines.flow.Flow<? extends T> r8, kotlin.coroutines.Continuation<? super T> r9) throws java.lang.Throwable {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.C01751
            if (r0 == 0) goto L14
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__ReduceKt$singleOrNull$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.C01751) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            kotlinx.coroutines.flow.FlowKt__ReduceKt$singleOrNull$1 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$singleOrNull$1
            r0.<init>(r9)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 1
            r5 = 0
            if (r3 == 0) goto L45
            if (r3 != r4) goto L3d
            r2 = r5
            r3 = 0
            r4 = r5
            java.lang.Object r5 = r0.L$2
            r4 = r5
            kotlinx.coroutines.flow.Flow r4 = (kotlinx.coroutines.flow.Flow) r4
            java.lang.Object r5 = r0.L$1
            r2 = r5
            kotlin.jvm.internal.Ref$ObjectRef r2 = (kotlin.jvm.internal.Ref.ObjectRef) r2
            java.lang.Object r5 = r0.L$0
            r8 = r5
            kotlinx.coroutines.flow.Flow r8 = (kotlinx.coroutines.flow.Flow) r8
            kotlin.ResultKt.throwOnFailure(r1)
            goto L68
        L3d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L45:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlin.jvm.internal.Ref$ObjectRef r3 = new kotlin.jvm.internal.Ref$ObjectRef
            r3.<init>()
            r3.element = r5
            r5 = r8
            r6 = 0
            kotlinx.coroutines.flow.FlowKt__ReduceKt$singleOrNull$$inlined$collect$1 r7 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$singleOrNull$$inlined$collect$1
            r7.<init>()
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
            r0.L$0 = r8
            r0.L$1 = r3
            r0.L$2 = r5
            r0.label = r4
            java.lang.Object r4 = r5.collect(r7, r0)
            if (r4 != r2) goto L67
            return r2
        L67:
            r2 = r3
        L68:
            T r3 = r2.element
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt.singleOrNull(kotlinx.coroutines.flow.Flow, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object first(kotlinx.coroutines.flow.Flow<? extends T> r8, kotlin.coroutines.Continuation<? super T> r9) throws java.lang.Throwable {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__ReduceKt$first$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            kotlinx.coroutines.flow.FlowKt__ReduceKt$first$1 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$first$1
            r0.<init>(r9)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 1
            if (r3 == 0) goto L44
            if (r3 != r4) goto L3c
            r2 = 0
            r3 = r2
            r4 = 0
            java.lang.Object r5 = r0.L$2
            r2 = r5
            kotlinx.coroutines.flow.Flow r2 = (kotlinx.coroutines.flow.Flow) r2
            java.lang.Object r5 = r0.L$1
            r3 = r5
            kotlin.jvm.internal.Ref$ObjectRef r3 = (kotlin.jvm.internal.Ref.ObjectRef) r3
            java.lang.Object r5 = r0.L$0
            r8 = r5
            kotlinx.coroutines.flow.Flow r8 = (kotlinx.coroutines.flow.Flow) r8
            kotlin.ResultKt.throwOnFailure(r1)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L6a
            goto L69
        L3c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L44:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlin.jvm.internal.Ref$ObjectRef r3 = new kotlin.jvm.internal.Ref$ObjectRef
            r3.<init>()
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            r3.element = r5
            r5 = r8
            r6 = 0
            kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collect$1 r7 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collect$1     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L6a
            r7.<init>()     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L6a
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L6a
            r0.L$0 = r8     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L6a
            r0.L$1 = r3     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L6a
            r0.L$2 = r5     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L6a
            r0.label = r4     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L6a
            java.lang.Object r4 = r5.collect(r7, r0)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L6a
            if (r4 != r2) goto L69
            return r2
        L69:
            goto L6b
        L6a:
            r2 = move-exception
        L6b:
            T r2 = r3.element
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            if (r2 == r4) goto L75
            T r2 = r3.element
            return r2
        L75:
            java.util.NoSuchElementException r2 = new java.util.NoSuchElementException
            java.lang.String r4 = "Expected at least one element"
            r2.<init>(r4)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt.first(kotlinx.coroutines.flow.Flow, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object first(kotlinx.coroutines.flow.Flow<? extends T> r8, final kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super java.lang.Boolean>, ? extends java.lang.Object> r9, kotlin.coroutines.Continuation<? super T> r10) throws java.lang.Throwable {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.AnonymousClass3
            if (r0 == 0) goto L14
            r0 = r10
            kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.AnonymousClass3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3
            r0.<init>(r10)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 1
            if (r3 == 0) goto L4b
            if (r3 != r4) goto L43
            r2 = 0
            r3 = r2
            r4 = 0
            java.lang.Object r5 = r0.L$3
            r3 = r5
            kotlinx.coroutines.flow.Flow r3 = (kotlinx.coroutines.flow.Flow) r3
            java.lang.Object r5 = r0.L$2
            r2 = r5
            kotlin.jvm.internal.Ref$ObjectRef r2 = (kotlin.jvm.internal.Ref.ObjectRef) r2
            java.lang.Object r5 = r0.L$1
            r9 = r5
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
            java.lang.Object r5 = r0.L$0
            r8 = r5
            kotlinx.coroutines.flow.Flow r8 = (kotlinx.coroutines.flow.Flow) r8
            kotlin.ResultKt.throwOnFailure(r1)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L41
            goto L73
        L41:
            r3 = move-exception
            goto L76
        L43:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L4b:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlin.jvm.internal.Ref$ObjectRef r3 = new kotlin.jvm.internal.Ref$ObjectRef
            r3.<init>()
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            r3.element = r5
            r5 = r8
            r6 = 0
            kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collect$2 r7 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collect$2     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L74
            r7.<init>()     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L74
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L74
            r0.L$0 = r8     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L74
            r0.L$1 = r9     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L74
            r0.L$2 = r3     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L74
            r0.L$3 = r5     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L74
            r0.label = r4     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L74
            java.lang.Object r4 = r5.collect(r7, r0)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L74
            if (r4 != r2) goto L72
            return r2
        L72:
            r2 = r3
        L73:
            goto L76
        L74:
            r2 = move-exception
            r2 = r3
        L76:
            T r3 = r2.element
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            if (r3 == r4) goto L80
            T r3 = r2.element
            return r3
        L80:
            java.util.NoSuchElementException r3 = new java.util.NoSuchElementException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Expected at least one element matching the predicate "
            r4.append(r5)
            r4.append(r9)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt.first(kotlinx.coroutines.flow.Flow, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
