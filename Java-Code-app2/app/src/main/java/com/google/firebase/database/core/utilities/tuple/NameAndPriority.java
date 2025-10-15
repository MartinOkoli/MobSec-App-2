package com.google.firebase.database.core.utilities.tuple;

import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.NodeUtilities;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\utilities\tuple\NameAndPriority.smali */
public class NameAndPriority implements Comparable<NameAndPriority> {
    private ChildKey name;
    private Node priority;

    public NameAndPriority(ChildKey name, Node priority) {
        this.name = name;
        this.priority = priority;
    }

    public ChildKey getName() {
        return this.name;
    }

    public Node getPriority() {
        return this.priority;
    }

    @Override // java.lang.Comparable
    public int compareTo(NameAndPriority o) {
        return NodeUtilities.nameAndPriorityCompare(this.name, this.priority, o.name, o.priority);
    }
}
