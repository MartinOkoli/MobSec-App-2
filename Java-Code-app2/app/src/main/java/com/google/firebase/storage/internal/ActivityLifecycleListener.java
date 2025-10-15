package com.google.firebase.storage.internal;

import android.app.Activity;
import android.util.Log;
import com.google.android.gms.common.api.internal.LifecycleActivity;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\internal\ActivityLifecycleListener.smali */
public class ActivityLifecycleListener {
    private static final ActivityLifecycleListener instance = new ActivityLifecycleListener();
    private final Map<Object, LifecycleEntry> cookieMap = new HashMap();
    private final Object sync = new Object();

    private ActivityLifecycleListener() {
    }

    public static ActivityLifecycleListener getInstance() {
        return instance;
    }

    public void runOnActivityStopped(Activity activityToListenOn, Object cookie, Runnable runnable) {
        synchronized (this.sync) {
            LifecycleEntry entry = new LifecycleEntry(activityToListenOn, runnable, cookie);
            OnStopCallback.getInstance(activityToListenOn).addEntry(entry);
            this.cookieMap.put(cookie, entry);
        }
    }

    public void removeCookie(Object cookie) {
        synchronized (this.sync) {
            LifecycleEntry entry = this.cookieMap.get(cookie);
            if (entry != null) {
                OnStopCallback.getInstance(entry.getActivity()).removeEntry(entry);
            }
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\internal\ActivityLifecycleListener$LifecycleEntry.smali */
    private static class LifecycleEntry {
        private final Activity activity;
        private final Object cookie;
        private final Runnable runnable;

        public LifecycleEntry(Activity activity, Runnable runnable, Object cookie) {
            this.activity = activity;
            this.runnable = runnable;
            this.cookie = cookie;
        }

        public int hashCode() {
            return this.cookie.hashCode();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof LifecycleEntry)) {
                return false;
            }
            LifecycleEntry other = (LifecycleEntry) obj;
            return other.cookie.equals(this.cookie) && other.runnable == this.runnable && other.activity == this.activity;
        }

        public Activity getActivity() {
            return this.activity;
        }

        public Runnable getRunnable() {
            return this.runnable;
        }

        public Object getCookie() {
            return this.cookie;
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\internal\ActivityLifecycleListener$OnStopCallback.smali */
    private static class OnStopCallback extends LifecycleCallback {
        private static final String TAG = "StorageOnStopCallback";
        private final List<LifecycleEntry> listeners;

        private OnStopCallback(LifecycleFragment fragment) {
            super(fragment);
            this.listeners = new ArrayList();
            this.mLifecycleFragment.addCallback(TAG, this);
        }

        public static OnStopCallback getInstance(Activity activity) {
            LifecycleFragment fragment = getFragment(new LifecycleActivity(activity));
            OnStopCallback callback = (OnStopCallback) fragment.getCallbackOrNull(TAG, OnStopCallback.class);
            if (callback == null) {
                return new OnStopCallback(fragment);
            }
            return callback;
        }

        public void addEntry(LifecycleEntry entry) {
            synchronized (this.listeners) {
                this.listeners.add(entry);
            }
        }

        public void removeEntry(LifecycleEntry listener) {
            synchronized (this.listeners) {
                this.listeners.remove(listener);
            }
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        public void onStop() {
            ArrayList<LifecycleEntry> copy;
            synchronized (this.listeners) {
                copy = new ArrayList<>(this.listeners);
                this.listeners.clear();
            }
            Iterator<LifecycleEntry> it = copy.iterator();
            while (it.hasNext()) {
                LifecycleEntry entry = it.next();
                if (entry != null) {
                    Log.d(TAG, "removing subscription from activity.");
                    entry.getRunnable().run();
                    ActivityLifecycleListener.getInstance().removeCookie(entry.getCookie());
                }
            }
        }
    }
}
