package com.google.firebase.storage;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\StorageRegistrar.smali */
public class StorageRegistrar implements ComponentRegistrar {
    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        return Arrays.asList(Component.builder(FirebaseStorageComponent.class).add(Dependency.required(FirebaseApp.class)).add(Dependency.optionalProvider(InternalAuthProvider.class)).factory(StorageRegistrar$$Lambda$1.instance).build(), LibraryVersionComponent.create("fire-gcs", "19.2.2"));
    }

    static /* synthetic */ FirebaseStorageComponent lambda$getComponents$0(ComponentContainer c) {
        return new FirebaseStorageComponent((FirebaseApp) c.get(FirebaseApp.class), c.getProvider(InternalAuthProvider.class));
    }
}
