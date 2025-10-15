package com.google.firebase.database.core.operation;

import com.google.firebase.database.core.Path;
import com.google.firebase.database.snapshot.ChildKey;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\operation\Operation.smali */
public abstract class Operation {
    protected final Path path;
    protected final OperationSource source;
    protected final OperationType type;

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\operation\Operation$OperationType.smali */
    public enum OperationType {
        Overwrite,
        Merge,
        AckUserWrite,
        ListenComplete
    }

    public abstract Operation operationForChild(ChildKey childKey);

    protected Operation(OperationType type, OperationSource source, Path path) {
        this.type = type;
        this.source = source;
        this.path = path;
    }

    public Path getPath() {
        return this.path;
    }

    public OperationSource getSource() {
        return this.source;
    }

    public OperationType getType() {
        return this.type;
    }
}
