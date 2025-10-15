package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: Errors.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aj\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012B\u0010\u0003\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0004¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a1\u0010\u000f\u001a\u0004\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a\u0019\u0010\u0012\u001a\u00020\u0013*\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0002¢\u0006\u0002\b\u0016\u001a\u001b\u0010\u0017\u001a\u00020\u0013*\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0002\b\u0019\u001aB\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0014\b\u0002\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u001dH\u0007\u001a>\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\u0014\b\u0002\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u001dH\u0007\u001ae\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020!23\b\u0002\u0010\u001c\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\"H\u0007ø\u0001\u0000¢\u0006\u0002\u0010#\u001a\u007f\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012W\u0010\u001c\u001aS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110!¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(&\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\n\u0012\u0006\u0012\u0004\u0018\u00010\f0%¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010'*\\\b\u0007\u0010(\"\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u001d2\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u001dB6\b)\u0012\n\b*\u0012\u0006\b\n0+8,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u001c\b/\u0012\u0018\b\u000bB\u0014\b0\u0012\u0006\b1\u0012\u0002\b\f\u0012\b\b2\u0012\u0004\b\b(3\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, d2 = {"catch", "Lkotlinx/coroutines/flow/Flow;", ExifInterface.GPS_DIRECTION_TRUE, "action", "Lkotlin/Function3;", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lkotlin/ParameterName;", "name", "cause", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "catchImpl", "collector", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isCancellationCause", "", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "isCancellationCause$FlowKt__ErrorsKt", "isSameExceptionAs", "other", "isSameExceptionAs$FlowKt__ErrorsKt", "onErrorCollect", "fallback", "predicate", "Lkotlin/Function1;", "retry", "retries", "", "", "Lkotlin/Function2;", "(Lkotlinx/coroutines/flow/Flow;JLkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "retryWhen", "Lkotlin/Function4;", "attempt", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;)Lkotlinx/coroutines/flow/Flow;", "ExceptionPredicate", "Lkotlin/Deprecated;", "level", "Lkotlin/DeprecationLevel;", "ERROR", "message", "Use (Throwable) -> Boolean functional type", "replaceWith", "Lkotlin/ReplaceWith;", "imports", "expression", "(Throwable) -> Boolean", "kotlinx-coroutines-core"}, k = 5, mv = {1, 1, 15}, xs = "kotlinx/coroutines/flow/FlowKt")
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\FlowKt__ErrorsKt.smali */
final /* synthetic */ class FlowKt__ErrorsKt {

    /* compiled from: Errors.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0080@"}, d2 = {"catchImpl", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/Flow;", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt", f = "Errors.kt", i = {0, 0, 0, 0}, l = {232}, m = "catchImpl", n = {"$this$catchImpl", "collector", "fromDownstream", "$this$collect$iv"}, s = {"L$0", "L$1", "L$2", "L$3"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\FlowKt__ErrorsKt$catchImpl$1.smali */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.catchImpl(null, null, this);
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use (Throwable) -> Boolean functional type", replaceWith = @ReplaceWith(expression = "(Throwable) -> Boolean", imports = {}))
    public static /* synthetic */ void ExceptionPredicate$annotations() {
    }

    /* renamed from: catch, reason: not valid java name */
    public static final <T> Flow<T> m1346catch(Flow<? extends T> flow, Function3<? super FlowCollector<? super T>, ? super Throwable, ? super Continuation<? super Unit>, ? extends Object> action) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$catch");
        Intrinsics.checkParameterIsNotNull(action, "action");
        return new FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1(flow, action);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: Errors.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/FlowCollector;", "e", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$onErrorCollect$2", f = "Errors.kt", i = {0, 0, 0, 0}, l = {230}, m = "invokeSuspend", n = {"$this$catch", "e", "$this$emitAll$iv", "flow$iv"}, s = {"L$0", "L$1", "L$2", "L$3"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$onErrorCollect$2, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\FlowKt__ErrorsKt$onErrorCollect$2.smali */
    static final class AnonymousClass2<T> extends SuspendLambda implements Function3<FlowCollector<? super T>, Throwable, Continuation<? super Unit>, Object> {
        final /* synthetic */ Flow $fallback;
        final /* synthetic */ Function1 $predicate;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        private FlowCollector p$;
        private Throwable p$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Function1 function1, Flow flow, Continuation continuation) {
            super(3, continuation);
            this.$predicate = function1;
            this.$fallback = flow;
        }

        public final Continuation<Unit> create(FlowCollector<? super T> create, Throwable e, Continuation<? super Unit> continuation) {
            Intrinsics.checkParameterIsNotNull(create, "$this$create");
            Intrinsics.checkParameterIsNotNull(e, "e");
            Intrinsics.checkParameterIsNotNull(continuation, "continuation");
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$predicate, this.$fallback, continuation);
            anonymousClass2.p$ = create;
            anonymousClass2.p$0 = e;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Throwable th, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create((FlowCollector) obj, th, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure($result);
                FlowCollector $this$catch = this.p$;
                Throwable e = this.p$0;
                if (!((Boolean) this.$predicate.invoke(e)).booleanValue()) {
                    throw e;
                }
                Flow flow$iv = this.$fallback;
                this.L$0 = $this$catch;
                this.L$1 = e;
                this.L$2 = $this$catch;
                this.L$3 = flow$iv;
                this.label = 1;
                if (flow$iv.collect($this$catch, this) == coroutine_suspended) {
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

    public static /* synthetic */ Flow onErrorCollect$default(Flow flow, Flow flow2, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<Throwable, Boolean>() { // from class: kotlinx.coroutines.flow.FlowKt__ErrorsKt.onErrorCollect.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Throwable th) {
                    return Boolean.valueOf(invoke2(th));
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(Throwable it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return true;
                }
            };
        }
        return FlowKt.onErrorCollect(flow, flow2, function1);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use catch { e -> if (predicate(e)) emitAll(fallback) else throw e }", replaceWith = @ReplaceWith(expression = "catch { e -> if (predicate(e)) emitAll(fallback) else throw e }", imports = {}))
    public static final <T> Flow<T> onErrorCollect(Flow<? extends T> onErrorCollect, Flow<? extends T> fallback, Function1<? super Throwable, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(onErrorCollect, "$this$onErrorCollect");
        Intrinsics.checkParameterIsNotNull(fallback, "fallback");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        return FlowKt.m1345catch(onErrorCollect, new AnonymousClass2(predicate, fallback, null));
    }

    /* compiled from: Errors.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$1", f = "Errors.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\FlowKt__ErrorsKt$retry$1.smali */
    static final class C01681 extends SuspendLambda implements Function2<Throwable, Continuation<? super Boolean>, Object> {
        int label;
        private Throwable p$0;

        C01681(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C01681 c01681 = new C01681(completion);
            c01681.p$0 = (Throwable) obj;
            return c01681;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Throwable th, Continuation<? super Boolean> continuation) {
            return ((C01681) create(th, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure($result);
            Throwable th = this.p$0;
            return Boxing.boxBoolean(true);
        }
    }

    public static /* synthetic */ Flow retry$default(Flow flow, long j, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = LongCompanionObject.MAX_VALUE;
        }
        if ((i & 2) != 0) {
            function2 = new C01681(null);
        }
        return FlowKt.retry(flow, j, (Function2<? super Throwable, ? super Continuation<? super Boolean>, ? extends Object>) function2);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: Errors.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@¢\u0006\u0004\b\b\u0010\t"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/FlowCollector;", "cause", "", "attempt", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$3", f = "Errors.kt", i = {0, 0, 0}, l = {WebSocketProtocol.PAYLOAD_SHORT}, m = "invokeSuspend", n = {"$this$retryWhen", "cause", "attempt"}, s = {"L$0", "L$1", "J$0"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$3, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\FlowKt__ErrorsKt$retry$3.smali */
    static final class AnonymousClass3<T> extends SuspendLambda implements Function4<FlowCollector<? super T>, Throwable, Long, Continuation<? super Boolean>, Object> {
        final /* synthetic */ Function2 $predicate;
        final /* synthetic */ long $retries;
        long J$0;
        Object L$0;
        Object L$1;
        int label;
        private FlowCollector p$;
        private Throwable p$0;
        private long p$1;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(long j, Function2 function2, Continuation continuation) {
            super(4, continuation);
            this.$retries = j;
            this.$predicate = function2;
        }

        public final Continuation<Unit> create(FlowCollector<? super T> create, Throwable cause, long j, Continuation<? super Boolean> continuation) {
            Intrinsics.checkParameterIsNotNull(create, "$this$create");
            Intrinsics.checkParameterIsNotNull(cause, "cause");
            Intrinsics.checkParameterIsNotNull(continuation, "continuation");
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$retries, this.$predicate, continuation);
            anonymousClass3.p$ = create;
            anonymousClass3.p$0 = cause;
            anonymousClass3.p$1 = j;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Throwable th, Long l, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass3) create((FlowCollector) obj, th, l.longValue(), continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws Throwable {
            Throwable cause;
            long attempt;
            Object objInvoke;
            Throwable cause2;
            long attempt2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            boolean z = true;
            if (i == 0) {
                ResultKt.throwOnFailure($result);
                FlowCollector $this$retryWhen = this.p$;
                cause = this.p$0;
                attempt = this.p$1;
                if (attempt < this.$retries) {
                    Function2 function2 = this.$predicate;
                    this.L$0 = $this$retryWhen;
                    this.L$1 = cause;
                    this.J$0 = attempt;
                    this.label = 1;
                    objInvoke = function2.invoke(cause, this);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    cause2 = cause;
                    attempt2 = attempt;
                }
                z = false;
                return Boxing.boxBoolean(z);
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            attempt2 = this.J$0;
            cause2 = (Throwable) this.L$1;
            ResultKt.throwOnFailure($result);
            objInvoke = $result;
            if (!((Boolean) objInvoke).booleanValue()) {
                attempt = attempt2;
                cause = cause2;
                z = false;
            }
            return Boxing.boxBoolean(z);
        }
    }

    public static final <T> Flow<T> retry(Flow<? extends T> retry, long retries, Function2<? super Throwable, ? super Continuation<? super Boolean>, ? extends Object> predicate) {
        Intrinsics.checkParameterIsNotNull(retry, "$this$retry");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        if (retries > 0) {
            return FlowKt.retryWhen(retry, new AnonymousClass3(retries, predicate, null));
        }
        throw new IllegalArgumentException(("Expected positive amount of retries, but had " + retries).toString());
    }

    public static /* synthetic */ Flow retry$default(Flow flow, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Integer.MAX_VALUE;
        }
        if ((i2 & 2) != 0) {
            function1 = new Function1<Throwable, Boolean>() { // from class: kotlinx.coroutines.flow.FlowKt__ErrorsKt.retry.4
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Throwable th) {
                    return Boolean.valueOf(invoke2(th));
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(Throwable it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return true;
                }
            };
        }
        return retry(flow, i, (Function1<? super Throwable, Boolean>) function1);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: Errors.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@¢\u0006\u0004\b\b\u0010\t"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/FlowCollector;", "cause", "", "attempt", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$6", f = "Errors.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$6, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\FlowKt__ErrorsKt$retry$6.smali */
    static final class AnonymousClass6<T> extends SuspendLambda implements Function4<FlowCollector<? super T>, Throwable, Long, Continuation<? super Boolean>, Object> {
        final /* synthetic */ Function1 $predicate;
        final /* synthetic */ int $retries;
        int label;
        private FlowCollector p$;
        private Throwable p$0;
        private long p$1;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass6(int i, Function1 function1, Continuation continuation) {
            super(4, continuation);
            this.$retries = i;
            this.$predicate = function1;
        }

        public final Continuation<Unit> create(FlowCollector<? super T> create, Throwable cause, long j, Continuation<? super Boolean> continuation) {
            Intrinsics.checkParameterIsNotNull(create, "$this$create");
            Intrinsics.checkParameterIsNotNull(cause, "cause");
            Intrinsics.checkParameterIsNotNull(continuation, "continuation");
            AnonymousClass6 anonymousClass6 = new AnonymousClass6(this.$retries, this.$predicate, continuation);
            anonymousClass6.p$ = create;
            anonymousClass6.p$0 = cause;
            anonymousClass6.p$1 = j;
            return anonymousClass6;
        }

        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Throwable th, Long l, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass6) create((FlowCollector) obj, th, l.longValue(), continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure($result);
            FlowCollector flowCollector = this.p$;
            Throwable cause = this.p$0;
            long attempt = this.p$1;
            return Boxing.boxBoolean(((Boolean) this.$predicate.invoke(cause)).booleanValue() && attempt < ((long) this.$retries));
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "binary compatibility with retries: Int preview version")
    public static final /* synthetic */ <T> Flow<T> retry(Flow<? extends T> retry, int retries, Function1<? super Throwable, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(retry, "$this$retry");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        if (retries > 0) {
            return FlowKt.retryWhen(retry, new AnonymousClass6(retries, predicate, null));
        }
        throw new IllegalArgumentException(("Expected positive amount of retries, but had " + retries).toString());
    }

    public static final <T> Flow<T> retryWhen(Flow<? extends T> retryWhen, Function4<? super FlowCollector<? super T>, ? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> predicate) {
        Intrinsics.checkParameterIsNotNull(retryWhen, "$this$retryWhen");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        return new FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1(retryWhen, predicate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Type inference failed for: r3v0, types: [int] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object catchImpl(kotlinx.coroutines.flow.Flow<? extends T> r9, kotlinx.coroutines.flow.FlowCollector<? super T> r10, kotlin.coroutines.Continuation<? super java.lang.Throwable> r11) throws java.lang.Throwable {
        /*
            boolean r0 = r11 instanceof kotlinx.coroutines.flow.FlowKt__ErrorsKt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r11
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$1 r0 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$1 r0 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$1
            r0.<init>(r11)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 1
            r5 = 0
            if (r3 == 0) goto L4a
            if (r3 != r4) goto L42
            r2 = r5
            r3 = r5
            r4 = 0
            java.lang.Object r6 = r0.L$3
            r2 = r6
            kotlinx.coroutines.flow.Flow r2 = (kotlinx.coroutines.flow.Flow) r2
            java.lang.Object r6 = r0.L$2
            r3 = r6
            kotlin.jvm.internal.Ref$ObjectRef r3 = (kotlin.jvm.internal.Ref.ObjectRef) r3
            java.lang.Object r6 = r0.L$1
            r10 = r6
            kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
            java.lang.Object r6 = r0.L$0
            r9 = r6
            kotlinx.coroutines.flow.Flow r9 = (kotlinx.coroutines.flow.Flow) r9
            kotlin.ResultKt.throwOnFailure(r1)     // Catch: java.lang.Throwable -> L76
            goto L72
        L42:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L4a:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlin.jvm.internal.Ref$ObjectRef r3 = new kotlin.jvm.internal.Ref$ObjectRef
            r3.<init>()
            r6 = r5
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            r3.element = r6
            r6 = r9
            r7 = 0
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1 r8 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1     // Catch: java.lang.Throwable -> L76
            r8.<init>(r10, r3)     // Catch: java.lang.Throwable -> L76
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8     // Catch: java.lang.Throwable -> L76
            r0.L$0 = r9     // Catch: java.lang.Throwable -> L76
            r0.L$1 = r10     // Catch: java.lang.Throwable -> L76
            r0.L$2 = r3     // Catch: java.lang.Throwable -> L76
            r0.L$3 = r6     // Catch: java.lang.Throwable -> L76
            r0.label = r4     // Catch: java.lang.Throwable -> L76
            java.lang.Object r4 = r6.collect(r8, r0)     // Catch: java.lang.Throwable -> L76
            if (r4 != r2) goto L72
            return r2
        L72:
            return r5
        L76:
            r2 = move-exception
            T r4 = r3.element
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            boolean r4 = isSameExceptionAs$FlowKt__ErrorsKt(r2, r4)
            if (r4 != 0) goto L8c
            kotlin.coroutines.CoroutineContext r4 = r0.get$context()
            boolean r4 = isCancellationCause$FlowKt__ErrorsKt(r2, r4)
            if (r4 != 0) goto L8c
            return r2
        L8c:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ErrorsKt.catchImpl(kotlinx.coroutines.flow.Flow, kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final boolean isCancellationCause$FlowKt__ErrorsKt(Throwable $this$isCancellationCause, CoroutineContext coroutineContext) {
        Job job = (Job) coroutineContext.get(Job.INSTANCE);
        if (job == null || !job.isCancelled()) {
            return false;
        }
        return isSameExceptionAs$FlowKt__ErrorsKt($this$isCancellationCause, job.getCancellationException());
    }

    private static final boolean isSameExceptionAs$FlowKt__ErrorsKt(Throwable $this$isSameExceptionAs, Throwable other) {
        return other != null && Intrinsics.areEqual(StackTraceRecoveryKt.unwrap(other), StackTraceRecoveryKt.unwrap($this$isSameExceptionAs));
    }
}
