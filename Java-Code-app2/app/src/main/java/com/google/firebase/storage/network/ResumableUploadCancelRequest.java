package com.google.firebase.storage.network;

import android.net.Uri;
import androidx.browser.trusted.sharing.ShareTarget;
import com.google.firebase.FirebaseApp;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\network\ResumableUploadCancelRequest.smali */
public class ResumableUploadCancelRequest extends ResumableNetworkRequest {
    public static boolean cancelCalled = false;
    private final Uri uploadURL;

    public ResumableUploadCancelRequest(Uri gsUri, FirebaseApp app, Uri uploadURL) {
        super(gsUri, app);
        cancelCalled = true;
        this.uploadURL = uploadURL;
        super.setCustomHeader("X-Goog-Upload-Protocol", "resumable");
        super.setCustomHeader("X-Goog-Upload-Command", "cancel");
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return ShareTarget.METHOD_POST;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected Uri getURL() {
        return this.uploadURL;
    }
}
