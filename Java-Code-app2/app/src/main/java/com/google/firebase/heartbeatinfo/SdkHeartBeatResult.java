package com.google.firebase.heartbeatinfo;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\heartbeatinfo\SdkHeartBeatResult.smali */
public abstract class SdkHeartBeatResult implements Comparable<SdkHeartBeatResult> {
    public abstract long getMillis();

    public abstract String getSdkName();

    public static SdkHeartBeatResult create(String sdkName, long millis) {
        return new AutoValue_SdkHeartBeatResult(sdkName, millis);
    }

    @Override // java.lang.Comparable
    public int compareTo(SdkHeartBeatResult sdkHeartBeatResult) {
        return getMillis() < sdkHeartBeatResult.getMillis() ? -1 : 1;
    }
}
