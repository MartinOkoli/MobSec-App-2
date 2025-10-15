package com.google.firebase.database.ktx;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.MutableData;
import com.google.firebase.ktx.Firebase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Database.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b\u001a\u001a\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0001\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\t\u001a\u00020\u0001\u001a\u001c\u0010\n\u001a\u0004\u0018\u0001H\u000b\"\u0006\b\u0000\u0010\u000b\u0018\u0001*\u00020\fH\u0086\b¢\u0006\u0002\u0010\r\u001a\u001c\u0010\n\u001a\u0004\u0018\u0001H\u000b\"\u0006\b\u0000\u0010\u000b\u0018\u0001*\u00020\u000eH\u0086\b¢\u0006\u0002\u0010\u000f\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"LIBRARY_NAME", "", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "Lcom/google/firebase/ktx/Firebase;", "getDatabase", "(Lcom/google/firebase/ktx/Firebase;)Lcom/google/firebase/database/FirebaseDatabase;", "app", "Lcom/google/firebase/FirebaseApp;", ImagesContract.URL, "getValue", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/google/firebase/database/DataSnapshot;", "(Lcom/google/firebase/database/DataSnapshot;)Ljava/lang/Object;", "Lcom/google/firebase/database/MutableData;", "(Lcom/google/firebase/database/MutableData;)Ljava/lang/Object;", "com.google.firebase-firebase-database-ktx"}, k = 2, mv = {1, 1, 13})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\ktx\DatabaseKt.smali */
public final class DatabaseKt {
    public static final String LIBRARY_NAME = "fire-db-ktx";

    public static final FirebaseDatabase getDatabase(Firebase receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(firebaseDatabase, "FirebaseDatabase.getInstance()");
        return firebaseDatabase;
    }

    public static final FirebaseDatabase database(Firebase receiver$0, String url) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(url, "url");
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(url);
        Intrinsics.checkExpressionValueIsNotNull(firebaseDatabase, "FirebaseDatabase.getInstance(url)");
        return firebaseDatabase;
    }

    public static final FirebaseDatabase database(Firebase receiver$0, FirebaseApp app) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(app, "app");
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(app);
        Intrinsics.checkExpressionValueIsNotNull(firebaseDatabase, "FirebaseDatabase.getInstance(app)");
        return firebaseDatabase;
    }

    public static final FirebaseDatabase database(Firebase receiver$0, FirebaseApp app, String url) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(url, "url");
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(app, url);
        Intrinsics.checkExpressionValueIsNotNull(firebaseDatabase, "FirebaseDatabase.getInstance(app, url)");
        return firebaseDatabase;
    }

    private static final <T> T getValue(DataSnapshot dataSnapshot) {
        Intrinsics.needClassReification();
        return (T) dataSnapshot.getValue(new GenericTypeIndicator<T>() { // from class: com.google.firebase.database.ktx.DatabaseKt.getValue.1
        });
    }

    private static final <T> T getValue(MutableData mutableData) {
        Intrinsics.needClassReification();
        return (T) mutableData.getValue(new GenericTypeIndicator<T>() { // from class: com.google.firebase.database.ktx.DatabaseKt.getValue.2
        });
    }
}
