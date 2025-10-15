package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt__ZipKt;

/* JADX INFO: Add missing generic type declarations: [R, T1, T2] */
/* compiled from: Zip.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001a\u0002H\u00022\u0006\u0010\u0007\u001a\u0002H\u0003H\u008a@¢\u0006\u0004\b\b\u0010\t"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "a", "b", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$1$1", f = "Zip.kt", i = {0, 0, 0}, l = {83}, m = "invokeSuspend", n = {"$this$combineTransformInternal", "a", "b"}, s = {"L$0", "L$1", "L$2"})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\flow\FlowKt__ZipKt$combineTransform$1$1.smali */
final class FlowKt__ZipKt$combineTransform$1$1<R, T1, T2> extends SuspendLambda implements Function4<FlowCollector<? super R>, T1, T2, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private FlowCollector p$;
    private Object p$0;
    private Object p$1;
    final /* synthetic */ FlowKt__ZipKt.combineTransform.1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__ZipKt$combineTransform$1$1(FlowKt__ZipKt.combineTransform.1 r1, Continuation continuation) {
        super(4, continuation);
        this.this$0 = r1;
    }

    public final Continuation<Unit> create(FlowCollector<? super R> create, T1 t1, T2 t2, Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(create, "$this$create");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        FlowKt__ZipKt$combineTransform$1$1 flowKt__ZipKt$combineTransform$1$1 = new FlowKt__ZipKt$combineTransform$1$1(this.this$0, continuation);
        flowKt__ZipKt$combineTransform$1$1.p$ = create;
        flowKt__ZipKt$combineTransform$1$1.p$0 = t1;
        flowKt__ZipKt$combineTransform$1$1.p$1 = t2;
        return flowKt__ZipKt$combineTransform$1$1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Continuation<? super Unit> continuation) {
        return ((FlowKt__ZipKt$combineTransform$1$1) create((FlowCollector) obj, obj2, obj3, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure($result);
            FlowCollector $this$combineTransformInternal = this.p$;
            Object a = this.p$0;
            Object b = this.p$1;
            Function4 function4 = this.this$0.$transform;
            this.L$0 = $this$combineTransformInternal;
            this.L$1 = a;
            this.L$2 = b;
            this.label = 1;
            if (function4.invoke($this$combineTransformInternal, a, b, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Object b2 = this.L$2;
            Object a2 = this.L$1;
            ResultKt.throwOnFailure($result);
        }
        return Unit.INSTANCE;
    }
}
