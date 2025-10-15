package com.google.firebase.emulators;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\emulators\EmulatedServiceSettings.smali */
public final class EmulatedServiceSettings {
    private final String host;
    private final int port;

    public EmulatedServiceSettings(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }
}
