package com.google.firebase.database.core;

import com.google.firebase.database.core.view.QuerySpec;
import java.util.concurrent.Callable;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\SyncTree$$Lambda$1.smali */
final /* synthetic */ class SyncTree$$Lambda$1 implements Callable {
    private final SyncTree arg$1;
    private final QuerySpec arg$2;

    private SyncTree$$Lambda$1(SyncTree syncTree, QuerySpec querySpec) {
        this.arg$1 = syncTree;
        this.arg$2 = querySpec;
    }

    public static Callable lambdaFactory$(SyncTree syncTree, QuerySpec querySpec) {
        return new SyncTree$$Lambda$1(syncTree, querySpec);
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return SyncTree.lambda$getServerValue$0(this.arg$1, this.arg$2);
    }
}
