package com.google.firebase.database.snapshot;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\snapshot\NamedNode.smali */
public final class NamedNode {
    private final ChildKey name;
    private final Node node;
    private static final NamedNode MIN_NODE = new NamedNode(ChildKey.getMinName(), EmptyNode.Empty());
    private static final NamedNode MAX_NODE = new NamedNode(ChildKey.getMaxName(), Node.MAX_NODE);

    public static NamedNode getMinNode() {
        return MIN_NODE;
    }

    public static NamedNode getMaxNode() {
        return MAX_NODE;
    }

    public NamedNode(ChildKey name, Node node) {
        this.name = name;
        this.node = node;
    }

    public ChildKey getName() {
        return this.name;
    }

    public Node getNode() {
        return this.node;
    }

    public String toString() {
        return "NamedNode{name=" + this.name + ", node=" + this.node + '}';
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NamedNode namedNode = (NamedNode) o;
        if (this.name.equals(namedNode.name) && this.node.equals(namedNode.node)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = this.name.hashCode();
        return (result * 31) + this.node.hashCode();
    }
}
