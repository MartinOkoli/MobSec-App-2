package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\StreamingAeadSeekableDecryptingChannel.smali */
class StreamingAeadSeekableDecryptingChannel implements SeekableByteChannel {
    private static final int PLAINTEXT_SEGMENT_EXTRA_SIZE = 16;
    private final byte[] aad;
    private final SeekableByteChannel ciphertextChannel;
    private final long ciphertextChannelSize;
    private final int ciphertextOffset;
    private final ByteBuffer ciphertextSegment;
    private final int ciphertextSegmentSize;
    private int currentSegmentNr;
    private final StreamSegmentDecrypter decrypter;
    private final int firstSegmentOffset;
    private final ByteBuffer header;
    private boolean headerRead;
    private boolean isCurrentSegmentDecrypted;
    private boolean isopen;
    private final int lastCiphertextSegmentSize;
    private final int numberOfSegments;
    private long plaintextPosition;
    private final ByteBuffer plaintextSegment;
    private final int plaintextSegmentSize;
    private long plaintextSize;

    public StreamingAeadSeekableDecryptingChannel(NonceBasedStreamingAead streamAead, SeekableByteChannel ciphertext, byte[] associatedData) throws GeneralSecurityException, IOException {
        this.decrypter = streamAead.newStreamSegmentDecrypter();
        this.ciphertextChannel = ciphertext;
        this.header = ByteBuffer.allocate(streamAead.getHeaderLength());
        int ciphertextSegmentSize = streamAead.getCiphertextSegmentSize();
        this.ciphertextSegmentSize = ciphertextSegmentSize;
        this.ciphertextSegment = ByteBuffer.allocate(ciphertextSegmentSize);
        int plaintextSegmentSize = streamAead.getPlaintextSegmentSize();
        this.plaintextSegmentSize = plaintextSegmentSize;
        this.plaintextSegment = ByteBuffer.allocate(plaintextSegmentSize + 16);
        this.plaintextPosition = 0L;
        this.headerRead = false;
        this.currentSegmentNr = -1;
        this.isCurrentSegmentDecrypted = false;
        long size = ciphertext.size();
        this.ciphertextChannelSize = size;
        this.aad = Arrays.copyOf(associatedData, associatedData.length);
        this.isopen = ciphertext.isOpen();
        int fullSegments = (int) (size / ciphertextSegmentSize);
        int remainder = (int) (size % ciphertextSegmentSize);
        int ciphertextOverhead = streamAead.getCiphertextOverhead();
        if (remainder > 0) {
            this.numberOfSegments = fullSegments + 1;
            if (remainder < ciphertextOverhead) {
                throw new IOException("Invalid ciphertext size");
            }
            this.lastCiphertextSegmentSize = remainder;
        } else {
            this.numberOfSegments = fullSegments;
            this.lastCiphertextSegmentSize = ciphertextSegmentSize;
        }
        int ciphertextOffset = streamAead.getCiphertextOffset();
        this.ciphertextOffset = ciphertextOffset;
        int headerLength = ciphertextOffset - streamAead.getHeaderLength();
        this.firstSegmentOffset = headerLength;
        if (headerLength < 0) {
            throw new IOException("Invalid ciphertext offset or header length");
        }
        long overhead = (this.numberOfSegments * ciphertextOverhead) + ciphertextOffset;
        if (overhead > size) {
            throw new IOException("Ciphertext is too short");
        }
        this.plaintextSize = size - overhead;
    }

    public synchronized String toString() {
        StringBuilder res;
        String ctChannel;
        res = new StringBuilder();
        try {
            ctChannel = "position:" + this.ciphertextChannel.position();
        } catch (IOException e) {
            ctChannel = "position: n/a";
        }
        res.append("StreamingAeadSeekableDecryptingChannel");
        res.append("\nciphertextChannel");
        res.append(ctChannel);
        res.append("\nciphertextChannelSize:");
        res.append(this.ciphertextChannelSize);
        res.append("\nplaintextSize:");
        res.append(this.plaintextSize);
        res.append("\nciphertextSegmentSize:");
        res.append(this.ciphertextSegmentSize);
        res.append("\nnumberOfSegments:");
        res.append(this.numberOfSegments);
        res.append("\nheaderRead:");
        res.append(this.headerRead);
        res.append("\nplaintextPosition:");
        res.append(this.plaintextPosition);
        res.append("\nHeader");
        res.append(" position:");
        res.append(this.header.position());
        res.append(" limit:");
        res.append(this.header.position());
        res.append("\ncurrentSegmentNr:");
        res.append(this.currentSegmentNr);
        res.append("\nciphertextSgement");
        res.append(" position:");
        res.append(this.ciphertextSegment.position());
        res.append(" limit:");
        res.append(this.ciphertextSegment.limit());
        res.append("\nisCurrentSegmentDecrypted:");
        res.append(this.isCurrentSegmentDecrypted);
        res.append("\nplaintextSegment");
        res.append(" position:");
        res.append(this.plaintextSegment.position());
        res.append(" limit:");
        res.append(this.plaintextSegment.limit());
        return res.toString();
    }

    @Override // java.nio.channels.SeekableByteChannel
    public synchronized long position() {
        return this.plaintextPosition;
    }

    @Override // java.nio.channels.SeekableByteChannel
    public synchronized SeekableByteChannel position(long newPosition) {
        this.plaintextPosition = newPosition;
        return this;
    }

    private boolean tryReadHeader() throws IOException {
        this.ciphertextChannel.position(this.header.position() + this.firstSegmentOffset);
        this.ciphertextChannel.read(this.header);
        if (this.header.remaining() > 0) {
            return false;
        }
        this.header.flip();
        try {
            this.decrypter.init(this.header, this.aad);
            this.headerRead = true;
            return true;
        } catch (GeneralSecurityException ex) {
            throw new IOException(ex);
        }
    }

    private int getSegmentNr(long plaintextPosition) {
        return (int) ((this.ciphertextOffset + plaintextPosition) / this.plaintextSegmentSize);
    }

    private boolean tryLoadSegment(int segmentNr) throws IOException {
        int i;
        if (segmentNr < 0 || segmentNr >= (i = this.numberOfSegments)) {
            throw new IOException("Invalid position");
        }
        boolean isLast = segmentNr == i - 1;
        if (segmentNr == this.currentSegmentNr) {
            if (this.isCurrentSegmentDecrypted) {
                return true;
            }
        } else {
            long ciphertextPosition = segmentNr * this.ciphertextSegmentSize;
            int segmentSize = this.ciphertextSegmentSize;
            if (isLast) {
                segmentSize = this.lastCiphertextSegmentSize;
            }
            if (segmentNr == 0) {
                int i2 = this.ciphertextOffset;
                segmentSize -= i2;
                ciphertextPosition = i2;
            }
            this.ciphertextChannel.position(ciphertextPosition);
            this.ciphertextSegment.clear();
            this.ciphertextSegment.limit(segmentSize);
            this.currentSegmentNr = segmentNr;
            this.isCurrentSegmentDecrypted = false;
        }
        if (this.ciphertextSegment.remaining() > 0) {
            this.ciphertextChannel.read(this.ciphertextSegment);
        }
        if (this.ciphertextSegment.remaining() > 0) {
            return false;
        }
        this.ciphertextSegment.flip();
        this.plaintextSegment.clear();
        try {
            this.decrypter.decryptSegment(this.ciphertextSegment, segmentNr, isLast, this.plaintextSegment);
            this.plaintextSegment.flip();
            this.isCurrentSegmentDecrypted = true;
            return true;
        } catch (GeneralSecurityException ex) {
            this.currentSegmentNr = -1;
            throw new IOException("Failed to decrypt", ex);
        }
    }

    private boolean reachedEnd() {
        return this.isCurrentSegmentDecrypted && this.currentSegmentNr == this.numberOfSegments - 1 && this.plaintextSegment.remaining() == 0;
    }

    public synchronized int read(ByteBuffer dst, long start) throws IOException {
        long oldPosition = position();
        try {
            position(start);
        } finally {
            position(oldPosition);
        }
        return read(dst);
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel
    public synchronized int read(ByteBuffer dst) throws IOException {
        int segmentOffset;
        if (!this.isopen) {
            throw new ClosedChannelException();
        }
        if (!this.headerRead && !tryReadHeader()) {
            return 0;
        }
        int startPos = dst.position();
        while (dst.remaining() > 0) {
            long j = this.plaintextPosition;
            if (j < this.plaintextSize) {
                int segmentNr = getSegmentNr(j);
                if (segmentNr == 0) {
                    segmentOffset = (int) this.plaintextPosition;
                } else {
                    segmentOffset = (int) ((this.plaintextPosition + this.ciphertextOffset) % this.plaintextSegmentSize);
                }
                if (!tryLoadSegment(segmentNr)) {
                    break;
                }
                this.plaintextSegment.position(segmentOffset);
                if (this.plaintextSegment.remaining() <= dst.remaining()) {
                    this.plaintextPosition += this.plaintextSegment.remaining();
                    dst.put(this.plaintextSegment);
                } else {
                    int sliceSize = dst.remaining();
                    ByteBuffer slice = this.plaintextSegment.duplicate();
                    slice.limit(slice.position() + sliceSize);
                    dst.put(slice);
                    this.plaintextPosition += sliceSize;
                    ByteBuffer byteBuffer = this.plaintextSegment;
                    byteBuffer.position(byteBuffer.position() + sliceSize);
                }
            } else {
                break;
            }
        }
        int read = dst.position() - startPos;
        if (read == 0 && reachedEnd()) {
            return -1;
        }
        return read;
    }

    @Override // java.nio.channels.SeekableByteChannel
    public long size() {
        return this.plaintextSize;
    }

    public synchronized long verifiedSize() throws IOException {
        if (tryLoadSegment(this.numberOfSegments - 1)) {
        } else {
            throw new IOException("could not verify the size");
        }
        return this.plaintextSize;
    }

    @Override // java.nio.channels.SeekableByteChannel
    public SeekableByteChannel truncate(long size) throws NonWritableChannelException {
        throw new NonWritableChannelException();
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.WritableByteChannel
    public int write(ByteBuffer src) throws NonWritableChannelException {
        throw new NonWritableChannelException();
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.ciphertextChannel.close();
        this.isopen = false;
    }

    @Override // java.nio.channels.Channel
    public synchronized boolean isOpen() {
        return this.isopen;
    }
}
