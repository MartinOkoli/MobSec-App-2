package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CoroutineExceptionHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\u001a%\u0010\u0000\u001a\u00020\u00012\u001a\b\u0004\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003H\u0086\b\u001a\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0005H\u0007\u001a\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H\u0000¨\u0006\r"}, d2 = {"CoroutineExceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handler", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext;", "", "", "handleCoroutineException", "context", "exception", "handlerException", "originalException", "thrownException", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 15})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\CoroutineExceptionHandlerKt.smali */
public final class CoroutineExceptionHandlerKt {
    public static final void handleCoroutineException(CoroutineContext context, Throwable exception) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(exception, "exception");
        try {
            CoroutineExceptionHandler it = (CoroutineExceptionHandler) context.get(CoroutineExceptionHandler.INSTANCE);
            if (it != null) {
                it.handleException(context, exception);
            } else {
                CoroutineExceptionHandlerImplKt.handleCoroutineExceptionImpl(context, exception);
            }
        } catch (Throwable t) {
            CoroutineExceptionHandlerImplKt.handleCoroutineExceptionImpl(context, handlerException(exception, t));
        }
    }

    public static final Throwable handlerException(Throwable originalException, Throwable thrownException) {
        Intrinsics.checkParameterIsNotNull(originalException, "originalException");
        Intrinsics.checkParameterIsNotNull(thrownException, "thrownException");
        if (originalException == thrownException) {
            return originalException;
        }
        Throwable $this$apply = new RuntimeException("Exception while trying to handle coroutine exception", thrownException);
        Throwable $this$addSuppressedThrowable$iv = $this$apply;
        kotlin.ExceptionsKt.addSuppressed($this$addSuppressedThrowable$iv, originalException);
        return $this$apply;
    }

    /* compiled from: CoroutineExceptionHandler.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"kotlinx/coroutines/CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handleException", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 15})
    /* renamed from: kotlinx.coroutines.CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1.smali */
    public static final class AnonymousClass1 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
        final /* synthetic */ Function2 $handler;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Function2 $captured_local_variable$0, CoroutineContext.Key $super_call_param$1) {
            super($super_call_param$1);
            this.$handler = $captured_local_variable$0;
        }

        @Override // kotlinx.coroutines.CoroutineExceptionHandler
        public void handleException(CoroutineContext context, Throwable exception) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(exception, "exception");
            this.$handler.invoke(context, exception);
        }
    }

    public static final CoroutineExceptionHandler CoroutineExceptionHandler(Function2<? super CoroutineContext, ? super Throwable, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        return new AnonymousClass1(handler, CoroutineExceptionHandler.INSTANCE);
    }
}
