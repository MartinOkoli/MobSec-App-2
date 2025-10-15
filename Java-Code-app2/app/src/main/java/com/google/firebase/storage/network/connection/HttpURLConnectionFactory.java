package com.google.firebase.storage.network.connection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\network\connection\HttpURLConnectionFactory.smali */
public interface HttpURLConnectionFactory {
    HttpURLConnection createInstance(URL url) throws IOException;
}
