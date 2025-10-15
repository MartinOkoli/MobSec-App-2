package com.google.firebase.storage;

import android.app.Activity;
import android.os.Build;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.StorageTask.ProvideError;
import com.google.firebase.storage.internal.ActivityLifecycleListener;
import com.google.firebase.storage.internal.SmartHandler;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\TaskListenerImpl.smali */
class TaskListenerImpl<ListenerTypeT, ResultT extends StorageTask.ProvideError> {
    private OnRaise<ListenerTypeT, ResultT> onRaise;
    private int targetStates;
    private StorageTask<ResultT> task;
    private final Queue<ListenerTypeT> listenerQueue = new ConcurrentLinkedQueue();
    private final HashMap<ListenerTypeT, SmartHandler> handlerMap = new HashMap<>();

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\TaskListenerImpl$OnRaise.smali */
    interface OnRaise<ListenerTypeT, ResultT> {
        void raise(ListenerTypeT listenertypet, ResultT resultt);
    }

    public TaskListenerImpl(StorageTask<ResultT> task, int targetInternalStates, OnRaise<ListenerTypeT, ResultT> onRaise) {
        this.task = task;
        this.targetStates = targetInternalStates;
        this.onRaise = onRaise;
    }

    public int getListenerCount() {
        return Math.max(this.listenerQueue.size(), this.handlerMap.size());
    }

    public void addListener(Activity activity, Executor executor, ListenerTypeT listener) {
        SmartHandler handler;
        Preconditions.checkNotNull(listener);
        boolean shouldFire = false;
        synchronized (this.task.getSyncObject()) {
            if ((this.task.getInternalState() & this.targetStates) != 0) {
                shouldFire = true;
            }
            this.listenerQueue.add(listener);
            handler = new SmartHandler(executor);
            this.handlerMap.put(listener, handler);
            if (activity != null) {
                if (Build.VERSION.SDK_INT >= 17) {
                    Preconditions.checkArgument(!activity.isDestroyed(), "Activity is already destroyed!");
                }
                ActivityLifecycleListener.getInstance().runOnActivityStopped(activity, listener, TaskListenerImpl$$Lambda$1.lambdaFactory$(this, listener));
            }
        }
        if (shouldFire) {
            handler.callBack(TaskListenerImpl$$Lambda$2.lambdaFactory$(this, listener, this.task.snapState()));
        }
    }

    static /* synthetic */ void lambda$addListener$1(TaskListenerImpl taskListenerImpl, Object listener, StorageTask.ProvideError snappedState) {
        taskListenerImpl.onRaise.raise(listener, snappedState);
    }

    public void onInternalStateChanged() {
        if ((this.task.getInternalState() & this.targetStates) != 0) {
            StorageTask.ProvideError provideErrorSnapState = this.task.snapState();
            for (ListenerTypeT c : this.listenerQueue) {
                SmartHandler handler = this.handlerMap.get(c);
                if (handler != null) {
                    handler.callBack(TaskListenerImpl$$Lambda$3.lambdaFactory$(this, c, provideErrorSnapState));
                }
            }
        }
    }

    static /* synthetic */ void lambda$onInternalStateChanged$2(TaskListenerImpl taskListenerImpl, Object finalCallback, StorageTask.ProvideError snappedState) {
        taskListenerImpl.onRaise.raise(finalCallback, snappedState);
    }

    public void removeListener(ListenerTypeT listener) {
        Preconditions.checkNotNull(listener);
        synchronized (this.task.getSyncObject()) {
            this.handlerMap.remove(listener);
            this.listenerQueue.remove(listener);
            ActivityLifecycleListener.getInstance().removeCookie(listener);
        }
    }
}
