package com.google.crypto.tink.subtle;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\StreamingAeadEncryptingStream.smali */
class StreamingAeadEncryptingStream extends FilterOutputStream {
    ByteBuffer ctBuffer;
    private StreamSegmentEncrypter encrypter;
    boolean open;
    private int plaintextSegmentSize;
    ByteBuffer ptBuffer;

    public StreamingAeadEncryptingStream(NonceBasedStreamingAead streamAead, OutputStream ciphertextChannel, byte[] associatedData) throws GeneralSecurityException, IOException {
        super(ciphertextChannel);
        this.encrypter = streamAead.newStreamSegmentEncrypter(associatedData);
        int plaintextSegmentSize = streamAead.getPlaintextSegmentSize();
        this.plaintextSegmentSize = plaintextSegmentSize;
        this.ptBuffer = ByteBuffer.allocate(plaintextSegmentSize);
        this.ctBuffer = ByteBuffer.allocate(streamAead.getCiphertextSegmentSize());
        this.ptBuffer.limit(this.plaintextSegmentSize - streamAead.getCiphertextOffset());
        ByteBuffer header = this.encrypter.getHeader();
        byte[] headerBytes = new byte[header.remaining()];
        header.get(headerBytes);
        this.out.write(headerBytes);
        this.open = true;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int b) throws IOException {
        write(new byte[]{(byte) b});
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(byte[] pt, int offset, int length) throws IOException {
        if (!this.open) {
            throw new IOException("Trying to write to closed stream");
        }
        int startPosition = offset;
        int remaining = length;
        while (remaining > this.ptBuffer.remaining()) {
            int sliceSize = this.ptBuffer.remaining();
            ByteBuffer slice = ByteBuffer.wrap(pt, startPosition, sliceSize);
            startPosition += sliceSize;
            remaining -= sliceSize;
            try {
                this.ptBuffer.flip();
                this.ctBuffer.clear();
                this.encrypter.encryptSegment(this.ptBuffer, slice, false, this.ctBuffer);
                this.ctBuffer.flip();
                this.out.write(this.ctBuffer.array(), this.ctBuffer.position(), this.ctBuffer.remaining());
                this.ptBuffer.clear();
                this.ptBuffer.limit(this.plaintextSegmentSize);
            } catch (GeneralSecurityException ex) {
                throw new IOException(ex);
            }
        }
        this.ptBuffer.put(pt, startPosition, remaining);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.open) {
            try {
                this.ptBuffer.flip();
                this.ctBuffer.clear();
                this.encrypter.encryptSegment(this.ptBuffer, true, this.ctBuffer);
                this.ctBuffer.flip();
                this.out.write(this.ctBuffer.array(), this.ctBuffer.position(), this.ctBuffer.remaining());
                this.open = false;
                super.close();
            } catch (GeneralSecurityException ex) {
                throw new IOException("ptBuffer.remaining():" + this.ptBuffer.remaining() + " ctBuffer.remaining():" + this.ctBuffer.remaining(), ex);
            }
        }
    }
}
