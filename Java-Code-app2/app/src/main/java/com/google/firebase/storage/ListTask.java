package com.google.firebase.storage;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.ListNetworkRequest;
import com.google.firebase.storage.network.NetworkRequest;
import org.json.JSONException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\ListTask.smali */
class ListTask implements Runnable {
    private static final String TAG = "ListTask";
    private final Integer maxResults;
    private final String pageToken;
    private final TaskCompletionSource<ListResult> pendingResult;
    private final ExponentialBackoffSender sender;
    private final StorageReference storageRef;

    ListTask(StorageReference storageRef, Integer maxResults, String pageToken, TaskCompletionSource<ListResult> pendingResult) {
        Preconditions.checkNotNull(storageRef);
        Preconditions.checkNotNull(pendingResult);
        this.storageRef = storageRef;
        this.maxResults = maxResults;
        this.pageToken = pageToken;
        this.pendingResult = pendingResult;
        FirebaseStorage storage = storageRef.getStorage();
        this.sender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getMaxDownloadRetryTimeMillis());
    }

    @Override // java.lang.Runnable
    public void run() throws JSONException {
        NetworkRequest request = new ListNetworkRequest(this.storageRef.getStorageUri(), this.storageRef.getApp(), this.maxResults, this.pageToken);
        this.sender.sendWithExponentialBackoff(request);
        ListResult listResult = null;
        if (request.isResultSuccess()) {
            try {
                listResult = ListResult.fromJSON(this.storageRef.getStorage(), request.getResultBody());
            } catch (JSONException e) {
                Log.e(TAG, "Unable to parse response body. " + request.getRawResult(), e);
                this.pendingResult.setException(StorageException.fromException(e));
                return;
            }
        }
        TaskCompletionSource<ListResult> taskCompletionSource = this.pendingResult;
        if (taskCompletionSource != null) {
            request.completeTask(taskCompletionSource, listResult);
        }
    }
}
