package com.google.firebase.database;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\DatabaseRegistrar$$Lambda$1.smali */
final /* synthetic */ class DatabaseRegistrar$$Lambda$1 implements ComponentFactory {
    private static final DatabaseRegistrar$$Lambda$1 instance = new DatabaseRegistrar$$Lambda$1();

    private DatabaseRegistrar$$Lambda$1() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public Object create(ComponentContainer componentContainer) {
        return DatabaseRegistrar.lambda$getComponents$0(componentContainer);
    }
}
