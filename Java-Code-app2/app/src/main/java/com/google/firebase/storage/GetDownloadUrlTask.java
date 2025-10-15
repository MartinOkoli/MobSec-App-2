package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.GetMetadataNetworkRequest;
import com.google.firebase.storage.network.NetworkRequest;
import org.json.JSONObject;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\GetDownloadUrlTask.smali */
class GetDownloadUrlTask implements Runnable {
    private static final String DOWNLOAD_TOKENS_KEY = "downloadTokens";
    private static final String TAG = "GetMetadataTask";
    private TaskCompletionSource<Uri> pendingResult;
    private ExponentialBackoffSender sender;
    private StorageReference storageRef;

    GetDownloadUrlTask(StorageReference storageRef, TaskCompletionSource<Uri> pendingResult) {
        Preconditions.checkNotNull(storageRef);
        Preconditions.checkNotNull(pendingResult);
        this.storageRef = storageRef;
        this.pendingResult = pendingResult;
        if (storageRef.getRoot().getName().equals(storageRef.getName())) {
            throw new IllegalArgumentException("getDownloadUrl() is not supported at the root of the bucket.");
        }
        FirebaseStorage storage = this.storageRef.getStorage();
        this.sender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getMaxOperationRetryTimeMillis());
    }

    private Uri extractDownloadUrl(JSONObject response) {
        String downloadTokens = response.optString(DOWNLOAD_TOKENS_KEY);
        if (!TextUtils.isEmpty(downloadTokens)) {
            String downloadToken = downloadTokens.split(",", -1)[0];
            Uri.Builder uriBuilder = NetworkRequest.getDefaultURL(this.storageRef.getStorageUri()).buildUpon();
            uriBuilder.appendQueryParameter("alt", "media");
            uriBuilder.appendQueryParameter("token", downloadToken);
            return uriBuilder.build();
        }
        return null;
    }

    @Override // java.lang.Runnable
    public void run() {
        NetworkRequest request = new GetMetadataNetworkRequest(this.storageRef.getStorageUri(), this.storageRef.getApp());
        this.sender.sendWithExponentialBackoff(request);
        Uri downloadUrl = null;
        if (request.isResultSuccess()) {
            downloadUrl = extractDownloadUrl(request.getResultBody());
        }
        TaskCompletionSource<Uri> taskCompletionSource = this.pendingResult;
        if (taskCompletionSource != null) {
            request.completeTask(taskCompletionSource, downloadUrl);
        }
    }
}
