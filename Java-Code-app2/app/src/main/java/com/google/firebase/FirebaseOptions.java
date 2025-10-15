package com.google.firebase;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.Strings;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\FirebaseOptions.smali */
public final class FirebaseOptions {
    private static final String API_KEY_RESOURCE_NAME = "google_api_key";
    private static final String APP_ID_RESOURCE_NAME = "google_app_id";
    private static final String DATABASE_URL_RESOURCE_NAME = "firebase_database_url";
    private static final String GA_TRACKING_ID_RESOURCE_NAME = "ga_trackingId";
    private static final String GCM_SENDER_ID_RESOURCE_NAME = "gcm_defaultSenderId";
    private static final String PROJECT_ID_RESOURCE_NAME = "project_id";
    private static final String STORAGE_BUCKET_RESOURCE_NAME = "google_storage_bucket";
    private final String apiKey;
    private final String applicationId;
    private final String databaseUrl;
    private final String gaTrackingId;
    private final String gcmSenderId;
    private final String projectId;
    private final String storageBucket;

    private FirebaseOptions(String applicationId, String apiKey, String databaseUrl, String gaTrackingId, String gcmSenderId, String storageBucket, String projectId) {
        Preconditions.checkState(!Strings.isEmptyOrWhitespace(applicationId), "ApplicationId must be set.");
        this.applicationId = applicationId;
        this.apiKey = apiKey;
        this.databaseUrl = databaseUrl;
        this.gaTrackingId = gaTrackingId;
        this.gcmSenderId = gcmSenderId;
        this.storageBucket = storageBucket;
        this.projectId = projectId;
    }

    public static FirebaseOptions fromResource(Context context) {
        StringResourceValueReader reader = new StringResourceValueReader(context);
        String applicationId = reader.getString(APP_ID_RESOURCE_NAME);
        if (TextUtils.isEmpty(applicationId)) {
            return null;
        }
        return new FirebaseOptions(applicationId, reader.getString(API_KEY_RESOURCE_NAME), reader.getString(DATABASE_URL_RESOURCE_NAME), reader.getString(GA_TRACKING_ID_RESOURCE_NAME), reader.getString(GCM_SENDER_ID_RESOURCE_NAME), reader.getString(STORAGE_BUCKET_RESOURCE_NAME), reader.getString(PROJECT_ID_RESOURCE_NAME));
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public String getDatabaseUrl() {
        return this.databaseUrl;
    }

    public String getGaTrackingId() {
        return this.gaTrackingId;
    }

    public String getGcmSenderId() {
        return this.gcmSenderId;
    }

    public String getStorageBucket() {
        return this.storageBucket;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public boolean equals(Object o) {
        if (!(o instanceof FirebaseOptions)) {
            return false;
        }
        FirebaseOptions other = (FirebaseOptions) o;
        return Objects.equal(this.applicationId, other.applicationId) && Objects.equal(this.apiKey, other.apiKey) && Objects.equal(this.databaseUrl, other.databaseUrl) && Objects.equal(this.gaTrackingId, other.gaTrackingId) && Objects.equal(this.gcmSenderId, other.gcmSenderId) && Objects.equal(this.storageBucket, other.storageBucket) && Objects.equal(this.projectId, other.projectId);
    }

    public int hashCode() {
        return Objects.hashCode(this.applicationId, this.apiKey, this.databaseUrl, this.gaTrackingId, this.gcmSenderId, this.storageBucket, this.projectId);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("applicationId", this.applicationId).add("apiKey", this.apiKey).add("databaseUrl", this.databaseUrl).add("gcmSenderId", this.gcmSenderId).add("storageBucket", this.storageBucket).add("projectId", this.projectId).toString();
    }
}
