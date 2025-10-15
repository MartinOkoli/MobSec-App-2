package com.google.firebase.storage.network;

import android.net.Uri;
import androidx.browser.trusted.sharing.ShareTarget;
import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.FirebaseApp;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\network\ResumableUploadQueryRequest.smali */
public class ResumableUploadQueryRequest extends ResumableNetworkRequest {
    private final Uri uploadURL;

    public ResumableUploadQueryRequest(Uri gsUri, FirebaseApp app, Uri uploadURL) {
        super(gsUri, app);
        this.uploadURL = uploadURL;
        super.setCustomHeader("X-Goog-Upload-Protocol", "resumable");
        super.setCustomHeader("X-Goog-Upload-Command", SearchIntents.EXTRA_QUERY);
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
