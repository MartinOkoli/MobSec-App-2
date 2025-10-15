package com.google.firebase.database.core.utilities.tuple;

import com.google.firebase.database.core.Path;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\utilities\tuple\PathAndId.smali */
public class PathAndId {
    private long id;
    private Path path;

    public PathAndId(Path path, long id) {
        this.path = path;
        this.id = id;
    }

    public Path getPath() {
        return this.path;
    }

    public long getId() {
        return this.id;
    }
}
