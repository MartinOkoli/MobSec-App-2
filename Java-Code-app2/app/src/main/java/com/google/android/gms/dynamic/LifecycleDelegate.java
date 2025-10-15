package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\dynamic\LifecycleDelegate.smali */
public interface LifecycleDelegate {
    void onCreate(Bundle bundle);

    View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    void onDestroy();

    void onDestroyView();

    void onInflate(Activity activity, Bundle bundle, Bundle bundle2);

    void onLowMemory();

    void onPause();

    void onResume();

    void onSaveInstanceState(Bundle bundle);

    void onStart();

    void onStop();
}
