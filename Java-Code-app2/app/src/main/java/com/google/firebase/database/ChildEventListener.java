package com.google.firebase.database;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\ChildEventListener.smali */
public interface ChildEventListener {
    void onCancelled(DatabaseError databaseError);

    void onChildAdded(DataSnapshot dataSnapshot, String str);

    void onChildChanged(DataSnapshot dataSnapshot, String str);

    void onChildMoved(DataSnapshot dataSnapshot, String str);

    void onChildRemoved(DataSnapshot dataSnapshot);
}
