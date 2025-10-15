package com.google.firebase.heartbeatinfo;

import com.google.firebase.components.Component;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\heartbeatinfo\HeartBeatConsumerComponent.smali */
public class HeartBeatConsumerComponent {
    private HeartBeatConsumerComponent() {
    }

    public static Component<?> create() {
        return Component.intoSet(new HeartBeatConsumer() { // from class: com.google.firebase.heartbeatinfo.HeartBeatConsumerComponent.1
        }, HeartBeatConsumer.class);
    }
}
