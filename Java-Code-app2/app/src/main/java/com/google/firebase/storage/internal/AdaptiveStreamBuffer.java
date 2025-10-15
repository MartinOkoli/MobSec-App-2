package com.google.firebase.storage.internal;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\internal\AdaptiveStreamBuffer.smali */
public class AdaptiveStreamBuffer {
    private static final String TAG = "AdaptiveStreamBuffer";
    private static final Runtime runtime = Runtime.getRuntime();
    private byte[] buffer;
    private final InputStream source;
    private int availableBytes = 0;
    private boolean adaptiveMode = true;
    private boolean reachedEnd = false;

    public AdaptiveStreamBuffer(InputStream source, int initialBufferSize) {
        this.source = source;
        this.buffer = new byte[initialBufferSize];
    }

    public int available() {
        return this.availableBytes;
    }

    public byte[] get() {
        return this.buffer;
    }

    public int advance(int bytes) throws IOException {
        int i = this.availableBytes;
        if (bytes <= i) {
            int i2 = i - bytes;
            this.availableBytes = i2;
            byte[] bArr = this.buffer;
            System.arraycopy(bArr, bytes, bArr, 0, i2);
            return bytes;
        }
        this.availableBytes = 0;
        int bytesAdvanced = this.availableBytes;
        while (bytesAdvanced < bytes) {
            int currentSkip = (int) this.source.skip(bytes - bytesAdvanced);
            if (currentSkip > 0) {
                bytesAdvanced += currentSkip;
            } else if (currentSkip != 0) {
                continue;
            } else if (this.source.read() != -1) {
                bytesAdvanced++;
            } else {
                return bytesAdvanced;
            }
        }
        return bytesAdvanced;
    }

    public int fill(int targetSize) throws IOException {
        if (targetSize > this.buffer.length) {
            targetSize = Math.min(targetSize, resize(targetSize));
        }
        while (true) {
            int i = this.availableBytes;
            if (i >= targetSize) {
                break;
            }
            int currentRead = this.source.read(this.buffer, i, targetSize - i);
            if (currentRead == -1) {
                this.reachedEnd = true;
                break;
            }
            this.availableBytes += currentRead;
        }
        return this.availableBytes;
    }

    private int resize(int targetSize) {
        int newBufferSize = Math.max(this.buffer.length * 2, targetSize);
        Runtime runtime2 = runtime;
        long currentFootprint = runtime2.totalMemory() - runtime2.freeMemory();
        long availableMemory = runtime2.maxMemory() - currentFootprint;
        if (this.adaptiveMode && newBufferSize < availableMemory) {
            try {
                byte[] chunkBuffer = new byte[newBufferSize];
                System.arraycopy(this.buffer, 0, chunkBuffer, 0, this.availableBytes);
                this.buffer = chunkBuffer;
            } catch (OutOfMemoryError e) {
                Log.w(TAG, "Turning off adaptive buffer resizing due to low memory.");
                this.adaptiveMode = false;
            }
        } else {
            Log.w(TAG, "Turning off adaptive buffer resizing to conserve memory.");
        }
        return this.buffer.length;
    }

    public boolean isFinished() {
        return this.reachedEnd;
    }

    public void close() throws IOException {
        this.source.close();
    }
}
