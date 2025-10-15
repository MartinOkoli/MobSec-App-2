package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.inject.Provider;
import com.google.firebase.storage.internal.Util;
import java.io.UnsupportedEncodingException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\FirebaseStorage.smali */
public class FirebaseStorage {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String STORAGE_BUCKET_WITH_PATH_EXCEPTION = "The storage Uri cannot contain a path element.";
    private static final String STORAGE_URI_PARSE_EXCEPTION = "The storage Uri could not be parsed.";
    private static final String TAG = "FirebaseStorage";
    private final FirebaseApp mApp;
    private final Provider<InternalAuthProvider> mAuthProvider;
    private final String mBucketName;
    private long sMaxUploadRetry = 600000;
    private long sMaxDownloadRetry = 600000;
    private long sMaxQueryRetry = 120000;

    FirebaseStorage(String bucketName, FirebaseApp app, Provider<InternalAuthProvider> authProvider) {
        this.mBucketName = bucketName;
        this.mApp = app;
        this.mAuthProvider = authProvider;
    }

    private static FirebaseStorage getInstanceImpl(FirebaseApp app, Uri url) {
        String bucketName = url != null ? url.getHost() : null;
        if (url != null && !TextUtils.isEmpty(url.getPath())) {
            throw new IllegalArgumentException(STORAGE_BUCKET_WITH_PATH_EXCEPTION);
        }
        Preconditions.checkNotNull(app, "Provided FirebaseApp must not be null.");
        FirebaseStorageComponent component = (FirebaseStorageComponent) app.get(FirebaseStorageComponent.class);
        Preconditions.checkNotNull(component, "Firebase Storage component is not present.");
        return component.get(bucketName);
    }

    public static FirebaseStorage getInstance() {
        FirebaseApp app = FirebaseApp.getInstance();
        Preconditions.checkArgument(app != null, "You must call FirebaseApp.initialize() first.");
        if (app == null) {
            throw new AssertionError();
        }
        return getInstance(app);
    }

    public static FirebaseStorage getInstance(String url) {
        FirebaseApp app = FirebaseApp.getInstance();
        Preconditions.checkArgument(app != null, "You must call FirebaseApp.initialize() first.");
        if (app == null) {
            throw new AssertionError();
        }
        return getInstance(app, url);
    }

    public static FirebaseStorage getInstance(FirebaseApp app) {
        Preconditions.checkArgument(app != null, "Null is not a valid value for the FirebaseApp.");
        String storageBucket = app.getOptions().getStorageBucket();
        if (storageBucket == null) {
            return getInstanceImpl(app, null);
        }
        try {
            return getInstanceImpl(app, Util.normalize(app, "gs://" + app.getOptions().getStorageBucket()));
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Unable to parse bucket:" + storageBucket, e);
            throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
        }
    }

    public static FirebaseStorage getInstance(FirebaseApp app, String url) {
        Preconditions.checkArgument(app != null, "Null is not a valid value for the FirebaseApp.");
        Preconditions.checkArgument(url != null, "Null is not a valid value for the Firebase Storage URL.");
        if (!url.toLowerCase().startsWith("gs://")) {
            throw new IllegalArgumentException("Please use a gs:// URL for your Firebase Storage bucket.");
        }
        try {
            return getInstanceImpl(app, Util.normalize(app, url));
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Unable to parse url:" + url, e);
            throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
        }
    }

    public long getMaxDownloadRetryTimeMillis() {
        return this.sMaxDownloadRetry;
    }

    public void setMaxDownloadRetryTimeMillis(long maxTransferRetryMillis) {
        this.sMaxDownloadRetry = maxTransferRetryMillis;
    }

    public long getMaxUploadRetryTimeMillis() {
        return this.sMaxUploadRetry;
    }

    public void setMaxUploadRetryTimeMillis(long maxTransferRetryMillis) {
        this.sMaxUploadRetry = maxTransferRetryMillis;
    }

    public long getMaxOperationRetryTimeMillis() {
        return this.sMaxQueryRetry;
    }

    public void setMaxOperationRetryTimeMillis(long maxTransferRetryMillis) {
        this.sMaxQueryRetry = maxTransferRetryMillis;
    }

    private String getBucketName() {
        return this.mBucketName;
    }

    public StorageReference getReference() {
        String bucketName = getBucketName();
        if (TextUtils.isEmpty(bucketName)) {
            throw new IllegalStateException("FirebaseApp was not initialized with a bucket name.");
        }
        Uri uri = new Uri.Builder().scheme("gs").authority(getBucketName()).path("/").build();
        return getReference(uri);
    }

    public StorageReference getReferenceFromUrl(String fullUrl) {
        Preconditions.checkArgument(!TextUtils.isEmpty(fullUrl), "location must not be null or empty");
        String lowerCaseLocation = fullUrl.toLowerCase();
        if (lowerCaseLocation.startsWith("gs://") || lowerCaseLocation.startsWith("https://") || lowerCaseLocation.startsWith("http://")) {
            try {
                Uri uri = Util.normalize(this.mApp, fullUrl);
                if (uri == null) {
                    throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
                }
                return getReference(uri);
            } catch (UnsupportedEncodingException e) {
                Log.e(TAG, "Unable to parse location:" + fullUrl, e);
                throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
            }
        }
        throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
    }

    public StorageReference getReference(String location) {
        Preconditions.checkArgument(!TextUtils.isEmpty(location), "location must not be null or empty");
        String lowerCaseLocation = location.toLowerCase();
        if (lowerCaseLocation.startsWith("gs://") || lowerCaseLocation.startsWith("https://") || lowerCaseLocation.startsWith("http://")) {
            throw new IllegalArgumentException("location should not be a full URL.");
        }
        return getReference().child(location);
    }

    private StorageReference getReference(Uri uri) {
        Preconditions.checkNotNull(uri, "uri must not be null");
        String bucketName = getBucketName();
        Preconditions.checkArgument(TextUtils.isEmpty(bucketName) || uri.getAuthority().equalsIgnoreCase(bucketName), "The supplied bucketname does not match the storage bucket of the current instance.");
        return new StorageReference(uri, this);
    }

    public FirebaseApp getApp() {
        return this.mApp;
    }

    InternalAuthProvider getAuthProvider() {
        Provider<InternalAuthProvider> provider = this.mAuthProvider;
        if (provider != null) {
            return provider.get();
        }
        return null;
    }
}
