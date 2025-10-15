package androidx.browser.trusted;

import androidx.concurrent.futures.ResolvableFuture;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\browser\trusted\FutureUtils.smali */
class FutureUtils {
    static <T> ListenableFuture<T> immediateFailedFuture(Throwable cause) {
        ResolvableFuture<T> future = ResolvableFuture.create();
        future.setException(cause);
        return future;
    }

    private FutureUtils() {
    }
}
