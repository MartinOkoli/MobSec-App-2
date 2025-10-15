package com.google.firebase.storage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\StorageTaskScheduler.smali */
public class StorageTaskScheduler {
    private static final ThreadPoolExecutor CALLBACK_QUEUE_EXECUTOR;
    private static final ThreadPoolExecutor COMMAND_POOL_EXECUTOR;
    private static final ThreadPoolExecutor DOWNLOAD_QUEUE_EXECUTOR;
    private static final ThreadPoolExecutor UPLOAD_QUEUE_EXECUTOR;
    private static BlockingQueue<Runnable> mCallbackQueue;
    private static BlockingQueue<Runnable> mDownloadQueue;
    private static BlockingQueue<Runnable> mUploadQueue;
    public static StorageTaskScheduler sInstance = new StorageTaskScheduler();
    private static BlockingQueue<Runnable> mCommandQueue = new LinkedBlockingQueue();

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 5L, TimeUnit.SECONDS, mCommandQueue, new StorageThreadFactory("Command-"));
        COMMAND_POOL_EXECUTOR = threadPoolExecutor;
        mUploadQueue = new LinkedBlockingQueue();
        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(2, 2, 5L, TimeUnit.SECONDS, mUploadQueue, new StorageThreadFactory("Upload-"));
        UPLOAD_QUEUE_EXECUTOR = threadPoolExecutor2;
        mDownloadQueue = new LinkedBlockingQueue();
        ThreadPoolExecutor threadPoolExecutor3 = new ThreadPoolExecutor(3, 3, 5L, TimeUnit.SECONDS, mDownloadQueue, new StorageThreadFactory("Download-"));
        DOWNLOAD_QUEUE_EXECUTOR = threadPoolExecutor3;
        mCallbackQueue = new LinkedBlockingQueue();
        ThreadPoolExecutor threadPoolExecutor4 = new ThreadPoolExecutor(1, 1, 5L, TimeUnit.SECONDS, mCallbackQueue, new StorageThreadFactory("Callbacks-"));
        CALLBACK_QUEUE_EXECUTOR = threadPoolExecutor4;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor2.allowCoreThreadTimeOut(true);
        threadPoolExecutor3.allowCoreThreadTimeOut(true);
        threadPoolExecutor4.allowCoreThreadTimeOut(true);
    }

    public static StorageTaskScheduler getInstance() {
        return sInstance;
    }

    public void scheduleCommand(Runnable task) {
        COMMAND_POOL_EXECUTOR.execute(task);
    }

    public void scheduleUpload(Runnable task) {
        UPLOAD_QUEUE_EXECUTOR.execute(task);
    }

    public void scheduleDownload(Runnable task) {
        DOWNLOAD_QUEUE_EXECUTOR.execute(task);
    }

    public void scheduleCallback(Runnable task) {
        CALLBACK_QUEUE_EXECUTOR.execute(task);
    }

    public Executor getCommandPoolExecutor() {
        return COMMAND_POOL_EXECUTOR;
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\StorageTaskScheduler$StorageThreadFactory.smali */
    static class StorageThreadFactory implements ThreadFactory {
        private final String mNameSuffix;
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        StorageThreadFactory(String suffix) {
            this.mNameSuffix = suffix;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "FirebaseStorage-" + this.mNameSuffix + this.threadNumber.getAndIncrement());
            t.setDaemon(false);
            t.setPriority(9);
            return t;
        }
    }
}
