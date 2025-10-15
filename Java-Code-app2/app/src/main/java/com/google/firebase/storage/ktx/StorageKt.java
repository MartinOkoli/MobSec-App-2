package com.google.firebase.storage.ktx;

import android.net.Uri;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StreamDownloadTask;
import com.google.firebase.storage.UploadTask;
import java.io.InputStream;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Storage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001f\u0010\u0007\u001a\u00020\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r\u001a\u0011\u0010\u000e\u001a\u00020\u000f*\u00060\u0010R\u00020\u0011H\u0086\u0002\u001a\u0013\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012*\u00020\u0014H\u0086\u0002\u001a\u0011\u0010\u000e\u001a\u00020\u000f*\u00060\u0015R\u00020\u0016H\u0086\u0002\u001a\u0011\u0010\u000e\u001a\u00020\u000f*\u00060\u0017R\u00020\u0018H\u0086\u0002\u001a\u0011\u0010\u0019\u001a\u00020\u000f*\u00060\u0010R\u00020\u0011H\u0086\u0002\u001a\u0013\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012*\u00020\u0014H\u0086\u0002\u001a\u0011\u0010\u0019\u001a\u00020\u000f*\u00060\u0015R\u00020\u0016H\u0086\u0002\u001a\u0011\u0010\u0019\u001a\u00020\u000f*\u00060\u0017R\u00020\u0018H\u0086\u0002\u001a\u000f\u0010\u001a\u001a\u0004\u0018\u00010\u0001*\u00020\u0014H\u0086\u0002\u001a\u0011\u0010\u001a\u001a\u00020\u001b*\u00060\u0015R\u00020\u0016H\u0086\u0002\u001a\u0013\u0010\u001a\u001a\u0004\u0018\u00010\b*\u00060\u0017R\u00020\u0018H\u0086\u0002\u001a\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u001d*\u00060\u0017R\u00020\u0018H\u0086\u0002\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001f\u001a\u001a\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0001\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010 \u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006!"}, d2 = {"LIBRARY_NAME", "", "storage", "Lcom/google/firebase/storage/FirebaseStorage;", "Lcom/google/firebase/ktx/Firebase;", "getStorage", "(Lcom/google/firebase/ktx/Firebase;)Lcom/google/firebase/storage/FirebaseStorage;", "storageMetadata", "Lcom/google/firebase/storage/StorageMetadata;", "init", "Lkotlin/Function1;", "Lcom/google/firebase/storage/StorageMetadata$Builder;", "", "Lkotlin/ExtensionFunctionType;", "component1", "", "Lcom/google/firebase/storage/FileDownloadTask$TaskSnapshot;", "Lcom/google/firebase/storage/FileDownloadTask;", "", "Lcom/google/firebase/storage/StorageReference;", "Lcom/google/firebase/storage/ListResult;", "Lcom/google/firebase/storage/StreamDownloadTask$TaskSnapshot;", "Lcom/google/firebase/storage/StreamDownloadTask;", "Lcom/google/firebase/storage/UploadTask$TaskSnapshot;", "Lcom/google/firebase/storage/UploadTask;", "component2", "component3", "Ljava/io/InputStream;", "component4", "Landroid/net/Uri;", "app", "Lcom/google/firebase/FirebaseApp;", ImagesContract.URL, "com.google.firebase-firebase-storage-ktx"}, k = 2, mv = {1, 1, 13})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\ktx\StorageKt.smali */
public final class StorageKt {
    public static final String LIBRARY_NAME = "fire-stg-ktx";

    public static final FirebaseStorage getStorage(Firebase receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(firebaseStorage, "FirebaseStorage.getInstance()");
        return firebaseStorage;
    }

    public static final FirebaseStorage storage(Firebase receiver$0, String url) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(url, "url");
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance(url);
        Intrinsics.checkExpressionValueIsNotNull(firebaseStorage, "FirebaseStorage.getInstance(url)");
        return firebaseStorage;
    }

    public static final FirebaseStorage storage(Firebase receiver$0, FirebaseApp app) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(app, "app");
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance(app);
        Intrinsics.checkExpressionValueIsNotNull(firebaseStorage, "FirebaseStorage.getInstance(app)");
        return firebaseStorage;
    }

    public static final FirebaseStorage storage(Firebase receiver$0, FirebaseApp app, String url) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(url, "url");
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance(app, url);
        Intrinsics.checkExpressionValueIsNotNull(firebaseStorage, "FirebaseStorage.getInstance(app, url)");
        return firebaseStorage;
    }

    public static final StorageMetadata storageMetadata(Function1<? super StorageMetadata.Builder, Unit> init) {
        Intrinsics.checkParameterIsNotNull(init, "init");
        StorageMetadata.Builder builder = new StorageMetadata.Builder();
        init.invoke(builder);
        StorageMetadata storageMetadataBuild = builder.build();
        Intrinsics.checkExpressionValueIsNotNull(storageMetadataBuild, "builder.build()");
        return storageMetadataBuild;
    }

    public static final long component1(UploadTask.TaskSnapshot receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getBytesTransferred();
    }

    public static final long component2(UploadTask.TaskSnapshot receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getTotalByteCount();
    }

    public static final StorageMetadata component3(UploadTask.TaskSnapshot receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getMetadata();
    }

    public static final Uri component4(UploadTask.TaskSnapshot receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getUploadSessionUri();
    }

    public static final long component1(StreamDownloadTask.TaskSnapshot receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getBytesTransferred();
    }

    public static final long component2(StreamDownloadTask.TaskSnapshot receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getTotalByteCount();
    }

    public static final InputStream component3(StreamDownloadTask.TaskSnapshot receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        InputStream stream = receiver$0.getStream();
        Intrinsics.checkExpressionValueIsNotNull(stream, "stream");
        return stream;
    }

    public static final long component1(FileDownloadTask.TaskSnapshot receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getBytesTransferred();
    }

    public static final long component2(FileDownloadTask.TaskSnapshot receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getTotalByteCount();
    }

    public static final List<StorageReference> component1(ListResult receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        List<StorageReference> items = receiver$0.getItems();
        Intrinsics.checkExpressionValueIsNotNull(items, "items");
        return items;
    }

    public static final List<StorageReference> component2(ListResult receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        List<StorageReference> prefixes = receiver$0.getPrefixes();
        Intrinsics.checkExpressionValueIsNotNull(prefixes, "prefixes");
        return prefixes;
    }

    public static final String component3(ListResult receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getPageToken();
    }
}
