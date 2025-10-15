package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\RewindableReadableByteChannel.smali */
public final class RewindableReadableByteChannel implements ReadableByteChannel {
    final ReadableByteChannel baseChannel;
    ByteBuffer buffer = null;
    boolean canRewind = true;
    boolean directRead = false;

    public RewindableReadableByteChannel(ReadableByteChannel baseChannel) {
        this.baseChannel = baseChannel;
    }

    public synchronized void disableRewinding() {
        this.canRewind = false;
    }

    public synchronized void rewind() throws IOException {
        if (!this.canRewind) {
            throw new IOException("Cannot rewind anymore.");
        }
        ByteBuffer byteBuffer = this.buffer;
        if (byteBuffer != null) {
            byteBuffer.position(0);
        }
    }

    private synchronized void setBufferLimit(int newLimit) {
        if (this.buffer.capacity() < newLimit) {
            int pos = this.buffer.position();
            int newBufferCapacity = Math.max(this.buffer.capacity() * 2, newLimit);
            ByteBuffer newBuffer = ByteBuffer.allocate(newBufferCapacity);
            this.buffer.rewind();
            newBuffer.put(this.buffer);
            newBuffer.position(pos);
            this.buffer = newBuffer;
        }
        this.buffer.limit(newLimit);
    }

    @Override // java.nio.channels.ReadableByteChannel
    public synchronized int read(ByteBuffer dst) throws IOException {
        if (this.directRead) {
            return this.baseChannel.read(dst);
        }
        int bytesToReadCount = dst.remaining();
        if (bytesToReadCount == 0) {
            return 0;
        }
        ByteBuffer byteBuffer = this.buffer;
        if (byteBuffer == null) {
            if (!this.canRewind) {
                this.directRead = true;
                return this.baseChannel.read(dst);
            }
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bytesToReadCount);
            this.buffer = byteBufferAllocate;
            int baseReadResult = this.baseChannel.read(byteBufferAllocate);
            this.buffer.flip();
            if (baseReadResult > 0) {
                dst.put(this.buffer);
            }
            return baseReadResult;
        }
        int baseReadResult2 = byteBuffer.remaining();
        if (baseReadResult2 >= bytesToReadCount) {
            int limit = this.buffer.limit();
            ByteBuffer byteBuffer2 = this.buffer;
            byteBuffer2.limit(byteBuffer2.position() + bytesToReadCount);
            dst.put(this.buffer);
            this.buffer.limit(limit);
            if (!this.canRewind && !this.buffer.hasRemaining()) {
                this.buffer = null;
                this.directRead = true;
            }
            return bytesToReadCount;
        }
        int bytesFromBufferCount = this.buffer.remaining();
        int stillToReadCount = bytesToReadCount - bytesFromBufferCount;
        int currentReadPos = this.buffer.position();
        int contentLimit = this.buffer.limit();
        setBufferLimit(contentLimit + stillToReadCount);
        this.buffer.position(contentLimit);
        int baseReadResult3 = this.baseChannel.read(this.buffer);
        this.buffer.flip();
        this.buffer.position(currentReadPos);
        dst.put(this.buffer);
        if (bytesFromBufferCount == 0 && baseReadResult3 < 0) {
            return -1;
        }
        int bytesCount = this.buffer.position() - currentReadPos;
        if (!this.canRewind && !this.buffer.hasRemaining()) {
            this.buffer = null;
            this.directRead = true;
        }
        return bytesCount;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.canRewind = false;
        this.directRead = true;
        this.baseChannel.close();
    }

    @Override // java.nio.channels.Channel
    public synchronized boolean isOpen() {
        return this.baseChannel.isOpen();
    }
}
