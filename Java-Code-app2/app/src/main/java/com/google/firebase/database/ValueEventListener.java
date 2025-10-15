package com.google.firebase.database;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\ValueEventListener.smali */
public interface ValueEventListener {
    void onCancelled(DatabaseError databaseError);

    void onDataChange(DataSnapshot dataSnapshot);
}
