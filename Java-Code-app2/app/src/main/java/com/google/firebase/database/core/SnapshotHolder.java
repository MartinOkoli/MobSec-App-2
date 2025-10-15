package com.google.firebase.database.core;

import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.Node;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\SnapshotHolder.smali */
public class SnapshotHolder {
    private Node rootNode;

    SnapshotHolder() {
        this.rootNode = EmptyNode.Empty();
    }

    public SnapshotHolder(Node node) {
        this.rootNode = node;
    }

    public Node getRootNode() {
        return this.rootNode;
    }

    public Node getNode(Path path) {
        return this.rootNode.getChild(path);
    }

    public void update(Path path, Node node) {
        this.rootNode = this.rootNode.updateChild(path, node);
    }
}
