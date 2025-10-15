package com.google.firebase.storage;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.inject.Provider;
import java.util.HashMap;
import java.util.Map;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\FirebaseStorageComponent.smali */
class FirebaseStorageComponent {
    private final FirebaseApp app;
    private final Provider<InternalAuthProvider> authProvider;
    private final Map<String, FirebaseStorage> instances = new HashMap();

    FirebaseStorageComponent(FirebaseApp app, Provider<InternalAuthProvider> authProvider) {
        this.app = app;
        this.authProvider = authProvider;
    }

    synchronized FirebaseStorage get(String bucketName) {
        FirebaseStorage storage;
        storage = this.instances.get(bucketName);
        if (storage == null) {
            storage = new FirebaseStorage(bucketName, this.app, this.authProvider);
            this.instances.put(bucketName, storage);
        }
        return storage;
    }

    synchronized void clearInstancesForTesting() {
        this.instances.clear();
    }
}
