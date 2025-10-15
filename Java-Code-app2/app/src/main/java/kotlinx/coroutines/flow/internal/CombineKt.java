package kotlinx.coroutines.flow.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.ChannelCoroutine;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectBuilder;

/* compiled from: Combine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000\u001an\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0005\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00032(\u0010\t\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\nH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000f*\u00020\u00102\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0002\u001a\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u000f*\u00020\u00102\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0002\u001a\u008e\u0001\u0010\u0012\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0014*\b\u0012\u0004\u0012\u0002H\u00040\u00152\u0014\u0010\u0016\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00030\u00172\u0014\u0010\u0018\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00140\u00170\u001929\u0010\t\u001a5\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n¢\u0006\u0002\b\u001aH\u0081@ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001a¢\u0001\u0010\u001c\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0005\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00152\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00032\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00032W\u0010\t\u001aS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u0015\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H\u0006¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u001f¢\u0006\u0002\b\u001aH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010$\u001av\u0010%\u001a\u00020\u0013*\b\u0012\u0004\u0012\u00020\u00130&2\u0006\u0010'\u001a\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\f0\u000f2\u000e\b\u0004\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00130\u001923\b\b\u0010%\u001a-\b\u0001\u0012\u0013\u0012\u00110\f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(,\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0+H\u0082\bø\u0001\u0000¢\u0006\u0002\u0010-\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006."}, d2 = {"getNull", "Lkotlinx/coroutines/internal/Symbol;", "zipImpl", "Lkotlinx/coroutines/flow/Flow;", "R", "T1", "T2", "flow", "flow2", "transform", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "asChannel", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lkotlinx/coroutines/CoroutineScope;", "asFairChannel", "combineInternal", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/FlowCollector;", "flows", "", "arrayFactory", "Lkotlin/Function0;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/FlowCollector;[Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "combineTransformInternal", "first", "second", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "a", "b", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onReceive", "Lkotlinx/coroutines/selects/SelectBuilder;", "isClosed", "", "channel", "onClosed", "Lkotlin/Function2;", "value", "(Lkotlinx/coroutines/selects/SelectBuilder;ZLkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 15})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\internal\CombineKt.smali */
public final class CombineKt {
    public static final /* synthetic */ ReceiveChannel access$asChannel(CoroutineScope $this$access_u24asChannel, Flow flow) {
        return asChannel($this$access_u24asChannel, flow);
    }

    public static final Symbol getNull() {
        return NullSurrogateKt.NULL;
    }

    /* compiled from: Combine.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\u00020\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$combineTransformInternal$2", f = "Combine.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {144}, m = "invokeSuspend", n = {"$this$coroutineScope", "firstChannel", "secondChannel", "firstValue", "secondValue", "firstIsClosed", "secondIsClosed"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"})
    /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$combineTransformInternal$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\internal\CombineKt$combineTransformInternal$2.smali */
    static final class C01772 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Flow $first;
        final /* synthetic */ Flow $second;
        final /* synthetic */ FlowCollector $this_combineTransformInternal;
        final /* synthetic */ Function4 $transform;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01772(FlowCollector flowCollector, Flow flow, Flow flow2, Function4 function4, Continuation continuation) {
            super(2, continuation);
            this.$this_combineTransformInternal = flowCollector;
            this.$first = flow;
            this.$second = flow2;
            this.$transform = function4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C01772 c01772 = new C01772(this.$this_combineTransformInternal, this.$first, this.$second, this.$transform, completion);
            c01772.p$ = (CoroutineScope) obj;
            return c01772;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01772) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Can't wrap try/catch for region: R(14:16|(1:55)|17|18|51|19|(1:21)(4:22|23|59|24)|25|57|26|(1:28)(4:29|30|53|31)|44|(1:46)|(1:48)(18:49|50|9|(1:11)|16|55|17|18|51|19|(0)(0)|25|57|26|(0)(0)|44|(0)|(0)(0))) */
        /* JADX WARN: Can't wrap try/catch for region: R(14:16|55|17|18|51|19|(1:21)(4:22|23|59|24)|25|57|26|(1:28)(4:29|30|53|31)|44|(1:46)|(1:48)(18:49|50|9|(1:11)|16|55|17|18|51|19|(0)(0)|25|57|26|(0)(0)|44|(0)|(0)(0))) */
        /* JADX WARN: Can't wrap try/catch for region: R(4:22|23|59|24) */
        /* JADX WARN: Can't wrap try/catch for region: R(4:29|30|53|31) */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0178, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x017a, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x017b, code lost:
        
            r29 = r35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x017e, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x017f, code lost:
        
            r29 = r35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0182, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0183, code lost:
        
            r29 = r35;
            r31 = r15;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x019a, code lost:
        
            r3.handleBuilderException(r0);
         */
        /* JADX WARN: Removed duplicated region for block: B:11:0x0091  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00f2  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x00f5 A[Catch: all -> 0x0182, TRY_LEAVE, TryCatch #0 {all -> 0x0182, blocks: (B:19:0x00e2, B:22:0x00f5), top: B:51:0x00e2 }] */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0148  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x014b A[Catch: all -> 0x017a, TRY_LEAVE, TryCatch #3 {all -> 0x017a, blocks: (B:26:0x0122, B:29:0x014b), top: B:57:0x0122 }] */
        /* JADX WARN: Removed duplicated region for block: B:46:0x01a8  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x01b1 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:49:0x01b2  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x01b2 -> B:50:0x01c0). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r35) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 452
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt.C01772.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final <T1, T2, R> Object combineTransformInternal(FlowCollector<? super R> flowCollector, Flow<? extends T1> flow, Flow<? extends T2> flow2, Function4<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super Continuation<? super Unit>, ? extends Object> function4, Continuation<? super Unit> continuation) {
        return CoroutineScopeKt.coroutineScope(new C01772(flowCollector, flow, flow2, function4, null), continuation);
    }

    /* compiled from: Combine.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "R", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2", f = "Combine.kt", i = {0, 0, 0, 0, 0}, l = {146}, m = "invokeSuspend", n = {"$this$coroutineScope", "size", "channels", "latestValues", "isClosed"}, s = {"L$0", "I$0", "L$1", "L$2", "L$3"})
    /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\internal\CombineKt$combineInternal$2.smali */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0 $arrayFactory;
        final /* synthetic */ Flow[] $flows;
        final /* synthetic */ FlowCollector $this_combineInternal;
        final /* synthetic */ Function3 $transform;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(FlowCollector flowCollector, Flow[] flowArr, Function0 function0, Function3 function3, Continuation continuation) {
            super(2, continuation);
            this.$this_combineInternal = flowCollector;
            this.$flows = flowArr;
            this.$arrayFactory = function0;
            this.$transform = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_combineInternal, this.$flows, this.$arrayFactory, this.$transform, completion);
            anonymousClass2.p$ = (CoroutineScope) obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0095  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00b0  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x016f  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0178 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:48:0x0179  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x0188  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x00ad A[SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x0179 -> B:49:0x0183). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r32) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 395
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final <R, T> Object combineInternal(FlowCollector<? super R> flowCollector, Flow<? extends T>[] flowArr, Function0<T[]> function0, Function3<? super FlowCollector<? super R>, ? super T[], ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) {
        return CoroutineScopeKt.coroutineScope(new AnonymousClass2(flowCollector, flowArr, function0, function3, null), continuation);
    }

    /* compiled from: Combine.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$onReceive$1", f = "Combine.kt", i = {0, 1}, l = {90, 90}, m = "invokeSuspend", n = {"it", "it"}, s = {"L$0", "L$0"})
    /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$onReceive$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\internal\CombineKt$onReceive$1.smali */
    public static final class C01781 extends SuspendLambda implements Function2<Object, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0 $onClosed;
        final /* synthetic */ Function2 $onReceive;
        Object L$0;
        int label;
        private Object p$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01781(Function0 function0, Function2 function2, Continuation continuation) {
            super(2, continuation);
            this.$onClosed = function0;
            this.$onReceive = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C01781 c01781 = new C01781(this.$onClosed, this.$onReceive, completion);
            c01781.p$0 = obj;
            return c01781;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
            return ((C01781) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws Throwable {
            Object it;
            Object objInvoke;
            Object it2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure($result);
                it = this.p$0;
                if (it == null) {
                    this.$onClosed.invoke();
                    return Unit.INSTANCE;
                }
                Function2 function2 = this.$onReceive;
                this.L$0 = it;
                this.label = 2;
                this.L$0 = it;
                this.label = 1;
                objInvoke = function2.invoke(it, this);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    it2 = this.L$0;
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                }
                it = this.L$0;
                ResultKt.throwOnFailure($result);
                objInvoke = $result;
            }
            if (objInvoke == coroutine_suspended) {
                return coroutine_suspended;
            }
            it2 = it;
            return Unit.INSTANCE;
        }

        public final Object invokeSuspend$$forInline(Object $result) {
            Object it = this.p$0;
            if (it == null) {
                this.$onClosed.invoke();
            } else {
                Function2 function2 = this.$onReceive;
                InlineMarker.mark(0);
                function2.invoke(it, this);
                InlineMarker.mark(2);
                InlineMarker.mark(1);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onReceive(SelectBuilder<? super Unit> selectBuilder, boolean isClosed, ReceiveChannel<? extends Object> receiveChannel, Function0<Unit> function0, Function2<Object, ? super Continuation<? super Unit>, ? extends Object> function2) {
        if (isClosed) {
            return;
        }
        selectBuilder.invoke(receiveChannel.getOnReceiveOrNull(), new C01781(function0, function2, null));
    }

    /* compiled from: Combine.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$asFairChannel$1", f = "Combine.kt", i = {0, 0, 0}, l = {144}, m = "invokeSuspend", n = {"$this$produce", "channel", "$this$collect$iv"}, s = {"L$0", "L$1", "L$2"})
    /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$asFairChannel$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\internal\CombineKt$asFairChannel$1.smali */
    static final class C01761 extends SuspendLambda implements Function2<ProducerScope<? super Object>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Flow $flow;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        private ProducerScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01761(Flow flow, Continuation continuation) {
            super(2, continuation);
            this.$flow = flow;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C01761 c01761 = new C01761(this.$flow, completion);
            c01761.p$ = (ProducerScope) obj;
            return c01761;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super Object> producerScope, Continuation<? super Unit> continuation) {
            return ((C01761) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure($result);
                ProducerScope $this$produce = this.p$;
                SendChannel channel = $this$produce.getChannel();
                if (channel == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelCoroutine<kotlin.Any>");
                }
                final ChannelCoroutine channel2 = (ChannelCoroutine) channel;
                Flow $this$collect$iv = this.$flow;
                FlowCollector<Object> flowCollector = new FlowCollector<Object>() { // from class: kotlinx.coroutines.flow.internal.CombineKt$asFairChannel$1$invokeSuspend$$inlined$collect$1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object value, Continuation $completion) {
                        return channel2.sendFair(value != null ? value : NullSurrogateKt.NULL, $completion);
                    }
                };
                this.L$0 = $this$produce;
                this.L$1 = channel2;
                this.L$2 = $this$collect$iv;
                this.label = 1;
                if ($this$collect$iv.collect(flowCollector, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure($result);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ReceiveChannel<Object> asFairChannel(CoroutineScope $this$asFairChannel, Flow<?> flow) {
        return ProduceKt.produce$default($this$asFairChannel, null, 0, new C01761(flow, null), 3, null);
    }

    public static final <T1, T2, R> Flow<R> zipImpl(Flow<? extends T1> flow, Flow<? extends T2> flow2, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> transform) {
        Intrinsics.checkParameterIsNotNull(flow, "flow");
        Intrinsics.checkParameterIsNotNull(flow2, "flow2");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        return new CombineKt$zipImpl$$inlined$unsafeFlow$1(flow, flow2, transform);
    }

    /* compiled from: Combine.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$asChannel$1", f = "Combine.kt", i = {0, 0}, l = {144}, m = "invokeSuspend", n = {"$this$produce", "$this$collect$iv"}, s = {"L$0", "L$1"})
    /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$asChannel$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\internal\CombineKt$asChannel$1.smali */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<ProducerScope<? super Object>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Flow $flow;
        Object L$0;
        Object L$1;
        int label;
        private ProducerScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Flow flow, Continuation continuation) {
            super(2, continuation);
            this.$flow = flow;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$flow, completion);
            anonymousClass1.p$ = (ProducerScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super Object> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure($result);
                final ProducerScope $this$produce = this.p$;
                Flow $this$collect$iv = this.$flow;
                FlowCollector<Object> flowCollector = new FlowCollector<Object>() { // from class: kotlinx.coroutines.flow.internal.CombineKt$asChannel$1$invokeSuspend$$inlined$collect$1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object value, Continuation $completion) {
                        return $this$produce.getChannel().send(value != null ? value : NullSurrogateKt.NULL, $completion);
                    }
                };
                this.L$0 = $this$produce;
                this.L$1 = $this$collect$iv;
                this.label = 1;
                if ($this$collect$iv.collect(flowCollector, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure($result);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ReceiveChannel<Object> asChannel(CoroutineScope $this$asChannel, Flow<?> flow) {
        return ProduceKt.produce$default($this$asChannel, null, 0, new AnonymousClass1(flow, null), 3, null);
    }
}
