package com.google.firebase.database.core.utilities;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\utilities\Predicate.smali */
public interface Predicate<T> {
    public static final Predicate<Object> TRUE = new Predicate<Object>() { // from class: com.google.firebase.database.core.utilities.Predicate.1
        @Override // com.google.firebase.database.core.utilities.Predicate
        public boolean evaluate(Object object) {
            return true;
        }
    };

    boolean evaluate(T t);
}
