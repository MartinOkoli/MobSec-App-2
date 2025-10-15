package com.google.firebase.storage;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\StorageTaskManager.smali */
class StorageTaskManager {
    private static final StorageTaskManager _instance = new StorageTaskManager();
    private final Map<String, WeakReference<StorageTask<?>>> inProgressTasks = new HashMap();
    private final Object syncObject = new Object();

    StorageTaskManager() {
    }

    static StorageTaskManager getInstance() {
        return _instance;
    }

    public List<UploadTask> getUploadTasksUnder(StorageReference parent) {
        List<UploadTask> listUnmodifiableList;
        synchronized (this.syncObject) {
            ArrayList<UploadTask> inProgressList = new ArrayList<>();
            String parentPath = parent.toString();
            for (Map.Entry<String, WeakReference<StorageTask<?>>> entry : this.inProgressTasks.entrySet()) {
                if (entry.getKey().startsWith(parentPath)) {
                    StorageTask<?> task = entry.getValue().get();
                    if (task instanceof UploadTask) {
                        inProgressList.add((UploadTask) task);
                    }
                }
            }
            listUnmodifiableList = Collections.unmodifiableList(inProgressList);
        }
        return listUnmodifiableList;
    }

    public List<FileDownloadTask> getDownloadTasksUnder(StorageReference parent) {
        List<FileDownloadTask> listUnmodifiableList;
        synchronized (this.syncObject) {
            ArrayList<FileDownloadTask> inProgressList = new ArrayList<>();
            String parentPath = parent.toString();
            for (Map.Entry<String, WeakReference<StorageTask<?>>> entry : this.inProgressTasks.entrySet()) {
                if (entry.getKey().startsWith(parentPath)) {
                    StorageTask<?> task = entry.getValue().get();
                    if (task instanceof FileDownloadTask) {
                        inProgressList.add((FileDownloadTask) task);
                    }
                }
            }
            listUnmodifiableList = Collections.unmodifiableList(inProgressList);
        }
        return listUnmodifiableList;
    }

    public void ensureRegistered(StorageTask<?> targetTask) {
        synchronized (this.syncObject) {
            this.inProgressTasks.put(targetTask.getStorage().toString(), new WeakReference<>(targetTask));
        }
    }

    public void unRegister(StorageTask<?> targetTask) {
        synchronized (this.syncObject) {
            String key = targetTask.getStorage().toString();
            WeakReference<StorageTask<?>> weakReference = this.inProgressTasks.get(key);
            StorageTask<?> task = weakReference != null ? weakReference.get() : null;
            if (task == null || task == targetTask) {
                this.inProgressTasks.remove(key);
            }
        }
    }
}
