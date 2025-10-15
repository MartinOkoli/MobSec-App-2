package com.google.firebase.database.core.view;

import com.google.firebase.database.core.Path;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\view\Event.smali */
public interface Event {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\view\Event$EventType.smali */
    public enum EventType {
        CHILD_REMOVED,
        CHILD_ADDED,
        CHILD_MOVED,
        CHILD_CHANGED,
        VALUE
    }

    void fire();

    Path getPath();

    String toString();
}
