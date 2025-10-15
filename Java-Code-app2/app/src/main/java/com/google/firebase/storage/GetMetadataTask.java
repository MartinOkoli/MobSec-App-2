package com.google.firebase.storage;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.GetMetadataNetworkRequest;
import com.google.firebase.storage.network.NetworkRequest;
import org.json.JSONException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\GetMetadataTask.smali */
class GetMetadataTask implements Runnable {
    private static final String TAG = "GetMetadataTask";
    private TaskCompletionSource<StorageMetadata> mPendingResult;
    private StorageMetadata mResultMetadata;
    private ExponentialBackoffSender mSender;
    private StorageReference mStorageRef;

    GetMetadataTask(StorageReference storageRef, TaskCompletionSource<StorageMetadata> pendingResult) {
        Preconditions.checkNotNull(storageRef);
        Preconditions.checkNotNull(pendingResult);
        this.mStorageRef = storageRef;
        this.mPendingResult = pendingResult;
        if (storageRef.getRoot().getName().equals(storageRef.getName())) {
            throw new IllegalArgumentException("getMetadata() is not supported at the root of the bucket.");
        }
        FirebaseStorage storage = this.mStorageRef.getStorage();
        this.mSender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getMaxDownloadRetryTimeMillis());
    }

    @Override // java.lang.Runnable
    public void run() {
        NetworkRequest request = new GetMetadataNetworkRequest(this.mStorageRef.getStorageUri(), this.mStorageRef.getApp());
        this.mSender.sendWithExponentialBackoff(request);
        if (request.isResultSuccess()) {
            try {
                this.mResultMetadata = new StorageMetadata.Builder(request.getResultBody(), this.mStorageRef).build();
            } catch (JSONException e) {
                Log.e(TAG, "Unable to parse resulting metadata. " + request.getRawResult(), e);
                this.mPendingResult.setException(StorageException.fromException(e));
                return;
            }
        }
        TaskCompletionSource<StorageMetadata> taskCompletionSource = this.mPendingResult;
        if (taskCompletionSource != null) {
            request.completeTask(taskCompletionSource, this.mResultMetadata);
        }
    }
}
