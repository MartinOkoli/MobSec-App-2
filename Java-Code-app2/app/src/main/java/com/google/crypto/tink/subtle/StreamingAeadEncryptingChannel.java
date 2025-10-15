package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\StreamingAeadEncryptingChannel.smali */
class StreamingAeadEncryptingChannel implements WritableByteChannel {
    private WritableByteChannel ciphertextChannel;
    ByteBuffer ctBuffer;
    private StreamSegmentEncrypter encrypter;
    boolean open = true;
    private int plaintextSegmentSize;
    ByteBuffer ptBuffer;

    public StreamingAeadEncryptingChannel(NonceBasedStreamingAead streamAead, WritableByteChannel ciphertextChannel, byte[] associatedData) throws GeneralSecurityException, IOException {
        this.ciphertextChannel = ciphertextChannel;
        this.encrypter = streamAead.newStreamSegmentEncrypter(associatedData);
        int plaintextSegmentSize = streamAead.getPlaintextSegmentSize();
        this.plaintextSegmentSize = plaintextSegmentSize;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(plaintextSegmentSize);
        this.ptBuffer = byteBufferAllocate;
        byteBufferAllocate.limit(this.plaintextSegmentSize - streamAead.getCiphertextOffset());
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(streamAead.getCiphertextSegmentSize());
        this.ctBuffer = byteBufferAllocate2;
        byteBufferAllocate2.put(this.encrypter.getHeader());
        this.ctBuffer.flip();
        ciphertextChannel.write(this.ctBuffer);
    }

    @Override // java.nio.channels.WritableByteChannel
    public synchronized int write(ByteBuffer pt) throws IOException {
        if (!this.open) {
            throw new ClosedChannelException();
        }
        if (this.ctBuffer.remaining() > 0) {
            this.ciphertextChannel.write(this.ctBuffer);
        }
        int startPosition = pt.position();
        while (pt.remaining() > this.ptBuffer.remaining()) {
            if (this.ctBuffer.remaining() > 0) {
                return pt.position() - startPosition;
            }
            int sliceSize = this.ptBuffer.remaining();
            ByteBuffer slice = pt.slice();
            slice.limit(sliceSize);
            pt.position(pt.position() + sliceSize);
            try {
                this.ptBuffer.flip();
                this.ctBuffer.clear();
                if (slice.remaining() != 0) {
                    this.encrypter.encryptSegment(this.ptBuffer, slice, false, this.ctBuffer);
                } else {
                    this.encrypter.encryptSegment(this.ptBuffer, false, this.ctBuffer);
                }
                this.ctBuffer.flip();
                this.ciphertextChannel.write(this.ctBuffer);
                this.ptBuffer.clear();
                this.ptBuffer.limit(this.plaintextSegmentSize);
            } catch (GeneralSecurityException ex) {
                throw new IOException(ex);
            }
        }
        this.ptBuffer.put(pt);
        return pt.position() - startPosition;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.open) {
            while (this.ctBuffer.remaining() > 0) {
                int n = this.ciphertextChannel.write(this.ctBuffer);
                if (n <= 0) {
                    throw new IOException("Failed to write ciphertext before closing");
                }
            }
            try {
                this.ctBuffer.clear();
                this.ptBuffer.flip();
                this.encrypter.encryptSegment(this.ptBuffer, true, this.ctBuffer);
                this.ctBuffer.flip();
                while (this.ctBuffer.remaining() > 0) {
                    int n2 = this.ciphertextChannel.write(this.ctBuffer);
                    if (n2 <= 0) {
                        throw new IOException("Failed to write ciphertext before closing");
                    }
                }
                this.ciphertextChannel.close();
                this.open = false;
            } catch (GeneralSecurityException ex) {
                throw new IOException(ex);
            }
        }
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.open;
    }
}
