package com.google.firebase.storage;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.GetNetworkRequest;
import com.google.firebase.storage.network.NetworkRequest;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\StreamDownloadTask.smali */
public class StreamDownloadTask extends StorageTask<TaskSnapshot> {
    static final long PREFERRED_CHUNK_SIZE = 262144;
    private static final String TAG = "StreamDownloadTask";
    private long bytesDownloaded;
    private long bytesDownloadedSnapped;
    private String eTagVerification;
    private InputStream inputStream;
    private StreamProcessor processor;
    private NetworkRequest request;
    private ExponentialBackoffSender sender;
    private StorageReference storageRef;
    private volatile Exception exception = null;
    private volatile int resultCode = 0;
    private long totalBytes = -1;

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\StreamDownloadTask$StreamProcessor.smali */
    public interface StreamProcessor {
        void doInBackground(TaskSnapshot taskSnapshot, InputStream inputStream) throws IOException;
    }

    StreamDownloadTask(StorageReference storageRef) {
        this.storageRef = storageRef;
        FirebaseStorage storage = storageRef.getStorage();
        this.sender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getMaxDownloadRetryTimeMillis());
    }

    StreamDownloadTask setStreamProcessor(StreamProcessor processor) {
        Preconditions.checkNotNull(processor);
        Preconditions.checkState(this.processor == null);
        this.processor = processor;
        return this;
    }

    @Override // com.google.firebase.storage.StorageTask
    StorageReference getStorage() {
        return this.storageRef;
    }

    long getTotalBytes() {
        return this.totalBytes;
    }

    void recordDownloadedBytes(long bytesDownloaded) {
        long j = this.bytesDownloaded + bytesDownloaded;
        this.bytesDownloaded = j;
        if (this.bytesDownloadedSnapped + 262144 <= j) {
            if (getInternalState() == 4) {
                tryChangeState(4, false);
            } else {
                this.bytesDownloadedSnapped = this.bytesDownloaded;
            }
        }
    }

    @Override // com.google.firebase.storage.StorageTask
    protected void schedule() {
        StorageTaskScheduler.getInstance().scheduleDownload(getRunnable());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InputStream createDownloadStream() throws Exception {
        String str;
        this.sender.reset();
        NetworkRequest networkRequest = this.request;
        if (networkRequest != null) {
            networkRequest.performRequestEnd();
        }
        GetNetworkRequest getNetworkRequest = new GetNetworkRequest(this.storageRef.getStorageUri(), this.storageRef.getApp(), this.bytesDownloaded);
        this.request = getNetworkRequest;
        boolean z = false;
        this.sender.sendWithExponentialBackoff(getNetworkRequest, false);
        this.resultCode = this.request.getResultCode();
        this.exception = this.request.getException() != null ? this.request.getException() : this.exception;
        if (isValidHttpResponseCode(this.resultCode) && this.exception == null && getInternalState() == 4) {
            z = true;
        }
        boolean success = z;
        if (success) {
            String newEtag = this.request.getResultString("ETag");
            if (!TextUtils.isEmpty(newEtag) && (str = this.eTagVerification) != null && !str.equals(newEtag)) {
                this.resultCode = 409;
                throw new IOException("The ETag on the server changed.");
            }
            this.eTagVerification = newEtag;
            this.totalBytes = this.request.getResultingContentLength() + this.bytesDownloaded;
            return this.request.getStream();
        }
        throw new IOException("Could not open resulting stream.");
    }

    @Override // com.google.firebase.storage.StorageTask
    void run() {
        if (this.exception != null) {
            tryChangeState(64, false);
            return;
        }
        if (!tryChangeState(4, false)) {
            return;
        }
        StreamProgressWrapper streamWrapper = new StreamProgressWrapper(new Callable<InputStream>() { // from class: com.google.firebase.storage.StreamDownloadTask.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public InputStream call() throws Exception {
                return StreamDownloadTask.this.createDownloadStream();
            }
        }, this);
        this.inputStream = new BufferedInputStream(streamWrapper);
        try {
            streamWrapper.ensureStream();
            StreamProcessor streamProcessor = this.processor;
            if (streamProcessor != null) {
                try {
                    streamProcessor.doInBackground(snapState(), this.inputStream);
                } catch (Exception e) {
                    Log.w(TAG, "Exception occurred calling doInBackground.", e);
                    this.exception = e;
                }
            }
        } catch (IOException e2) {
            Log.d(TAG, "Initial opening of Stream failed", e2);
            this.exception = e2;
        }
        if (this.inputStream == null) {
            this.request.performRequestEnd();
            this.request = null;
        }
        boolean success = this.exception == null && getInternalState() == 4;
        if (success) {
            tryChangeState(4, false);
            tryChangeState(128, false);
            return;
        }
        if (!tryChangeState(getInternalState() == 32 ? 256 : 64, false)) {
            Log.w(TAG, "Unable to change download task to final state from " + getInternalState());
        }
    }

    @Override // com.google.firebase.storage.StorageTask, com.google.firebase.storage.ControllableTask
    public boolean resume() {
        throw new UnsupportedOperationException("this operation is not supported on StreamDownloadTask.");
    }

    @Override // com.google.firebase.storage.StorageTask, com.google.firebase.storage.ControllableTask
    public boolean pause() {
        throw new UnsupportedOperationException("this operation is not supported on StreamDownloadTask.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.storage.StorageTask
    public TaskSnapshot snapStateImpl() {
        return new TaskSnapshot(StorageException.fromExceptionAndHttpCode(this.exception, this.resultCode), this.bytesDownloadedSnapped);
    }

    @Override // com.google.firebase.storage.StorageTask
    protected void onCanceled() {
        this.sender.cancel();
        this.exception = StorageException.fromErrorStatus(Status.RESULT_CANCELED);
    }

    @Override // com.google.firebase.storage.StorageTask
    protected void onProgress() {
        this.bytesDownloadedSnapped = this.bytesDownloaded;
    }

    private boolean isValidHttpResponseCode(int code) {
        return code == 308 || (code >= 200 && code < 300);
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\StreamDownloadTask$StreamProgressWrapper.smali */
    static class StreamProgressWrapper extends InputStream {
        private long mDownloadedBytes;
        private Callable<InputStream> mInputStreamCallable;
        private long mLastExceptionPosition;
        private StreamDownloadTask mParentTask;
        private boolean mStreamClosed;
        private IOException mTemporaryException;
        private InputStream mWrappedStream;

        StreamProgressWrapper(Callable<InputStream> inputStreamCallable, StreamDownloadTask parentTask) {
            this.mParentTask = parentTask;
            this.mInputStreamCallable = inputStreamCallable;
        }

        private void checkCancel() throws IOException {
            StreamDownloadTask streamDownloadTask = this.mParentTask;
            if (streamDownloadTask != null && streamDownloadTask.getInternalState() == 32) {
                throw new CancelException();
            }
        }

        private void recordDownloadedBytes(long delta) {
            StreamDownloadTask streamDownloadTask = this.mParentTask;
            if (streamDownloadTask != null) {
                streamDownloadTask.recordDownloadedBytes(delta);
            }
            this.mDownloadedBytes += delta;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean ensureStream() throws IOException {
            checkCancel();
            if (this.mTemporaryException != null) {
                try {
                    InputStream inputStream = this.mWrappedStream;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                }
                this.mWrappedStream = null;
                if (this.mLastExceptionPosition == this.mDownloadedBytes) {
                    Log.i(StreamDownloadTask.TAG, "Encountered exception during stream operation. Aborting.", this.mTemporaryException);
                    return false;
                }
                Log.i(StreamDownloadTask.TAG, "Encountered exception during stream operation. Retrying at " + this.mDownloadedBytes, this.mTemporaryException);
                this.mLastExceptionPosition = this.mDownloadedBytes;
                this.mTemporaryException = null;
            }
            if (this.mStreamClosed) {
                throw new IOException("Can't perform operation on closed stream");
            }
            if (this.mWrappedStream == null) {
                try {
                    this.mWrappedStream = this.mInputStreamCallable.call();
                    return true;
                } catch (Exception e2) {
                    if (e2 instanceof IOException) {
                        throw ((IOException) e2);
                    }
                    throw new IOException("Unable to open stream", e2);
                }
            }
            return true;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            while (ensureStream()) {
                try {
                    int returnValue = this.mWrappedStream.read();
                    if (returnValue != -1) {
                        recordDownloadedBytes(1L);
                    }
                    return returnValue;
                } catch (IOException e) {
                    this.mTemporaryException = e;
                }
            }
            throw this.mTemporaryException;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            while (ensureStream()) {
                try {
                    return this.mWrappedStream.available();
                } catch (IOException e) {
                    this.mTemporaryException = e;
                }
            }
            throw this.mTemporaryException;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            InputStream inputStream = this.mWrappedStream;
            if (inputStream != null) {
                inputStream.close();
            }
            this.mStreamClosed = true;
            StreamDownloadTask streamDownloadTask = this.mParentTask;
            if (streamDownloadTask != null && streamDownloadTask.request != null) {
                this.mParentTask.request.performRequestEnd();
                this.mParentTask.request = null;
            }
            checkCancel();
        }

        @Override // java.io.InputStream
        public void mark(int readlimit) {
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return false;
        }

        @Override // java.io.InputStream
        public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
            int bytesRead = 0;
            while (ensureStream()) {
                while (byteCount > 262144) {
                    try {
                        int deltaBytesRead = this.mWrappedStream.read(buffer, byteOffset, 262144);
                        if (deltaBytesRead == -1) {
                            if (bytesRead == 0) {
                                return -1;
                            }
                            return bytesRead;
                        }
                        bytesRead += deltaBytesRead;
                        byteOffset += deltaBytesRead;
                        byteCount -= deltaBytesRead;
                        recordDownloadedBytes(deltaBytesRead);
                        checkCancel();
                    } catch (IOException e) {
                        this.mTemporaryException = e;
                    }
                }
                if (byteCount > 0) {
                    int deltaBytesRead2 = this.mWrappedStream.read(buffer, byteOffset, byteCount);
                    if (deltaBytesRead2 == -1) {
                        if (bytesRead == 0) {
                            return -1;
                        }
                        return bytesRead;
                    }
                    byteOffset += deltaBytesRead2;
                    bytesRead += deltaBytesRead2;
                    byteCount -= deltaBytesRead2;
                    recordDownloadedBytes(deltaBytesRead2);
                }
                if (byteCount == 0) {
                    return bytesRead;
                }
            }
            throw this.mTemporaryException;
        }

        @Override // java.io.InputStream
        public long skip(long byteCount) throws IOException {
            long bytesSkipped = 0;
            while (ensureStream()) {
                while (byteCount > 262144) {
                    try {
                        long deltaBytesSkipped = this.mWrappedStream.skip(262144L);
                        if (deltaBytesSkipped < 0) {
                            if (bytesSkipped == 0) {
                                return -1L;
                            }
                            return bytesSkipped;
                        }
                        bytesSkipped += deltaBytesSkipped;
                        byteCount -= deltaBytesSkipped;
                        recordDownloadedBytes(deltaBytesSkipped);
                        checkCancel();
                    } catch (IOException e) {
                        this.mTemporaryException = e;
                    }
                }
                if (byteCount > 0) {
                    long deltaBytesSkipped2 = this.mWrappedStream.skip(byteCount);
                    if (deltaBytesSkipped2 < 0) {
                        if (bytesSkipped == 0) {
                            return -1L;
                        }
                        return bytesSkipped;
                    }
                    bytesSkipped += deltaBytesSkipped2;
                    byteCount -= deltaBytesSkipped2;
                    recordDownloadedBytes(deltaBytesSkipped2);
                }
                if (byteCount == 0) {
                    return bytesSkipped;
                }
            }
            throw this.mTemporaryException;
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\StreamDownloadTask$TaskSnapshot.smali */
    public class TaskSnapshot extends StorageTask<TaskSnapshot>.SnapshotBase {
        private final long mBytesDownloaded;

        TaskSnapshot(Exception error, long bytesDownloaded) {
            super(error);
            this.mBytesDownloaded = bytesDownloaded;
        }

        public long getBytesTransferred() {
            return this.mBytesDownloaded;
        }

        public long getTotalByteCount() {
            return StreamDownloadTask.this.getTotalBytes();
        }

        public InputStream getStream() {
            return StreamDownloadTask.this.inputStream;
        }
    }
}
