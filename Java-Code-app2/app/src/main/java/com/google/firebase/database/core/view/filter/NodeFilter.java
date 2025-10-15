package com.google.firebase.database.core.view.filter;

import com.google.firebase.database.core.Path;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.Index;
import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.NamedNode;
import com.google.firebase.database.snapshot.Node;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\view\filter\NodeFilter.smali */
public interface NodeFilter {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\view\filter\NodeFilter$CompleteChildSource.smali */
    public interface CompleteChildSource {
        NamedNode getChildAfterChild(Index index, NamedNode namedNode, boolean z);

        Node getCompleteChild(ChildKey childKey);
    }

    boolean filtersNodes();

    Index getIndex();

    NodeFilter getIndexedFilter();

    IndexedNode updateChild(IndexedNode indexedNode, ChildKey childKey, Node node, Path path, CompleteChildSource completeChildSource, ChildChangeAccumulator childChangeAccumulator);

    IndexedNode updateFullNode(IndexedNode indexedNode, IndexedNode indexedNode2, ChildChangeAccumulator childChangeAccumulator);

    IndexedNode updatePriority(IndexedNode indexedNode, Node node);
}
