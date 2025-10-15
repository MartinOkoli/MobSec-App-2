package com.google.firebase.storage.network;

import android.net.Uri;
import android.text.TextUtils;
import androidx.browser.trusted.sharing.ShareTarget;
import com.google.firebase.FirebaseApp;
import java.util.HashMap;
import java.util.Map;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\network\ListNetworkRequest.smali */
public class ListNetworkRequest extends NetworkRequest {
    private final Integer maxPageSize;
    private final String nextPageToken;

    public ListNetworkRequest(Uri gsUri, FirebaseApp app, Integer maxPageSize, String nextPageToken) {
        super(gsUri, app);
        this.maxPageSize = maxPageSize;
        this.nextPageToken = nextPageToken;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return ShareTarget.METHOD_GET;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected Uri getURL() {
        return Uri.parse(sNetworkRequestUrl + "/b/" + this.mGsUri.getAuthority() + "/o");
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected Map<String, String> getQueryParameters() {
        Map<String, String> headers = new HashMap<>();
        String prefix = getPathWithoutBucket();
        if (!prefix.isEmpty()) {
            headers.put("prefix", prefix + "/");
        }
        headers.put("delimiter", "/");
        Integer num = this.maxPageSize;
        if (num != null) {
            headers.put("maxResults", Integer.toString(num.intValue()));
        }
        if (!TextUtils.isEmpty(this.nextPageToken)) {
            headers.put("pageToken", this.nextPageToken);
        }
        return headers;
    }
}
