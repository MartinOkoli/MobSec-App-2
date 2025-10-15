package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\components\OptionalProvider.smali */
class OptionalProvider<T> implements Provider<T>, Deferred<T> {
    private volatile Provider<T> delegate;
    private Deferred.DeferredHandler<T> handler;
    private static final Deferred.DeferredHandler<Object> NOOP_HANDLER = OptionalProvider$$Lambda$4.instance;
    private static final Provider<Object> EMPTY_PROVIDER = OptionalProvider$$Lambda$5.instance;

    static /* synthetic */ void lambda$static$0(Provider p) {
    }

    static /* synthetic */ Object lambda$static$1() {
        return null;
    }

    private OptionalProvider(Deferred.DeferredHandler<T> handler, Provider<T> provider) {
        this.handler = handler;
        this.delegate = provider;
    }

    static <T> OptionalProvider<T> empty() {
        return new OptionalProvider<>(NOOP_HANDLER, EMPTY_PROVIDER);
    }

    static <T> OptionalProvider<T> of(Provider<T> provider) {
        return new OptionalProvider<>(null, provider);
    }

    @Override // com.google.firebase.inject.Provider
    public T get() {
        return this.delegate.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void set(Provider<T> provider) {
        Deferred.DeferredHandler<T> localHandler;
        if (this.delegate != EMPTY_PROVIDER) {
            throw new IllegalStateException("provide() can be called only once.");
        }
        synchronized (this) {
            localHandler = this.handler;
            this.handler = null;
            this.delegate = provider;
        }
        localHandler.handle(provider);
    }

    @Override // com.google.firebase.inject.Deferred
    public void whenAvailable(Deferred.DeferredHandler<T> handler) {
        Provider<T> provider;
        Provider<T> provider2 = this.delegate;
        Provider<Object> provider3 = EMPTY_PROVIDER;
        if (provider2 != provider3) {
            handler.handle(provider2);
            return;
        }
        Provider<T> toRun = null;
        synchronized (this) {
            provider = this.delegate;
            if (provider != provider3) {
                toRun = provider;
            } else {
                Deferred.DeferredHandler<T> existingHandler = this.handler;
                this.handler = OptionalProvider$$Lambda$1.lambdaFactory$(existingHandler, handler);
            }
        }
        if (toRun != null) {
            handler.handle(provider);
        }
    }

    static /* synthetic */ void lambda$whenAvailable$2(Deferred.DeferredHandler existingHandler, Deferred.DeferredHandler handler, Provider p) {
        existingHandler.handle(p);
        handler.handle(p);
    }
}
