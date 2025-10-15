package com.google.android.gms.tasks;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\tasks\Continuation.smali */
public interface Continuation<TResult, TContinuationResult> {
    TContinuationResult then(Task<TResult> task) throws Exception;
}
