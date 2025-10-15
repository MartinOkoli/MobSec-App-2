package com.google.firebase.database.collection;

import java.lang.Comparable;
import java.util.Comparator;

/* compiled from: com.google.firebase:firebase-database-collection@@17.0.1 */
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\collection\StandardComparator.smali */
public class StandardComparator<A extends Comparable<A>> implements Comparator<A> {
    private static StandardComparator INSTANCE = new StandardComparator();

    private StandardComparator() {
    }

    public static <T extends Comparable<T>> StandardComparator<T> getComparator(Class<T> clazz) {
        return INSTANCE;
    }

    @Override // java.util.Comparator
    public int compare(A o1, A o2) {
        return o1.compareTo(o2);
    }
}
