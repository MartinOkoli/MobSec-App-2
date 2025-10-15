package com.google.firebase.database;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\DatabaseRegistrar.smali */
public class DatabaseRegistrar implements ComponentRegistrar {
    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        return Arrays.asList(Component.builder(FirebaseDatabaseComponent.class).add(Dependency.required(FirebaseApp.class)).add(Dependency.deferred(InternalAuthProvider.class)).factory(DatabaseRegistrar$$Lambda$1.instance).build(), LibraryVersionComponent.create("fire-rtdb", "19.7.0"));
    }

    static /* synthetic */ FirebaseDatabaseComponent lambda$getComponents$0(ComponentContainer c) {
        return new FirebaseDatabaseComponent((FirebaseApp) c.get(FirebaseApp.class), c.getDeferred(InternalAuthProvider.class));
    }
}
