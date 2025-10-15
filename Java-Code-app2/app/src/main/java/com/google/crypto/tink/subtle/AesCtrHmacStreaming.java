package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\AesCtrHmacStreaming.smali */
public final class AesCtrHmacStreaming extends NonceBasedStreamingAead {
    private static final int HMAC_KEY_SIZE_IN_BYTES = 32;
    private static final int NONCE_PREFIX_IN_BYTES = 7;
    private static final int NONCE_SIZE_IN_BYTES = 16;
    private final int ciphertextSegmentSize;
    private final int firstSegmentOffset;
    private final String hkdfAlgo;
    private final byte[] ikm;
    private final int keySizeInBytes;
    private final int plaintextSegmentSize;
    private final String tagAlgo;
    private final int tagSizeInBytes;

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead, com.google.crypto.tink.StreamingAead
    public /* bridge */ /* synthetic */ ReadableByteChannel newDecryptingChannel(ReadableByteChannel ciphertextChannel, byte[] associatedData) throws GeneralSecurityException, IOException {
        return super.newDecryptingChannel(ciphertextChannel, associatedData);
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead, com.google.crypto.tink.StreamingAead
    public /* bridge */ /* synthetic */ InputStream newDecryptingStream(InputStream ciphertextStream, byte[] associatedData) throws GeneralSecurityException, IOException {
        return super.newDecryptingStream(ciphertextStream, associatedData);
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead, com.google.crypto.tink.StreamingAead
    public /* bridge */ /* synthetic */ WritableByteChannel newEncryptingChannel(WritableByteChannel ciphertextChannel, byte[] associatedData) throws GeneralSecurityException, IOException {
        return super.newEncryptingChannel(ciphertextChannel, associatedData);
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead, com.google.crypto.tink.StreamingAead
    public /* bridge */ /* synthetic */ OutputStream newEncryptingStream(OutputStream ciphertext, byte[] associatedData) throws GeneralSecurityException, IOException {
        return super.newEncryptingStream(ciphertext, associatedData);
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead, com.google.crypto.tink.StreamingAead
    public /* bridge */ /* synthetic */ SeekableByteChannel newSeekableDecryptingChannel(SeekableByteChannel ciphertextSource, byte[] associatedData) throws GeneralSecurityException, IOException {
        return super.newSeekableDecryptingChannel(ciphertextSource, associatedData);
    }

    public AesCtrHmacStreaming(byte[] ikm, String hkdfAlgo, int keySizeInBytes, String tagAlgo, int tagSizeInBytes, int ciphertextSegmentSize, int firstSegmentOffset) throws InvalidAlgorithmParameterException {
        validateParameters(ikm.length, keySizeInBytes, tagAlgo, tagSizeInBytes, ciphertextSegmentSize, firstSegmentOffset);
        this.ikm = Arrays.copyOf(ikm, ikm.length);
        this.hkdfAlgo = hkdfAlgo;
        this.keySizeInBytes = keySizeInBytes;
        this.tagAlgo = tagAlgo;
        this.tagSizeInBytes = tagSizeInBytes;
        this.ciphertextSegmentSize = ciphertextSegmentSize;
        this.firstSegmentOffset = firstSegmentOffset;
        this.plaintextSegmentSize = ciphertextSegmentSize - tagSizeInBytes;
    }

    private static void validateParameters(int ikmSize, int keySizeInBytes, String tagAlgo, int tagSizeInBytes, int ciphertextSegmentSize, int firstSegmentOffset) throws InvalidAlgorithmParameterException {
        if (ikmSize < 16 || ikmSize < keySizeInBytes) {
            throw new InvalidAlgorithmParameterException("ikm too short, must be >= " + Math.max(16, keySizeInBytes));
        }
        Validators.validateAesKeySize(keySizeInBytes);
        if (tagSizeInBytes < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small " + tagSizeInBytes);
        }
        if ((tagAlgo.equals("HmacSha1") && tagSizeInBytes > 20) || ((tagAlgo.equals("HmacSha256") && tagSizeInBytes > 32) || (tagAlgo.equals("HmacSha512") && tagSizeInBytes > 64))) {
            throw new InvalidAlgorithmParameterException("tag size too big");
        }
        int firstPlaintextSegment = ((((ciphertextSegmentSize - firstSegmentOffset) - tagSizeInBytes) - keySizeInBytes) - 7) - 1;
        if (firstPlaintextSegment <= 0) {
            throw new InvalidAlgorithmParameterException("ciphertextSegmentSize too small");
        }
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public AesCtrHmacStreamEncrypter newStreamSegmentEncrypter(byte[] aad) throws GeneralSecurityException {
        return new AesCtrHmacStreamEncrypter(aad);
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public AesCtrHmacStreamDecrypter newStreamSegmentDecrypter() throws GeneralSecurityException {
        return new AesCtrHmacStreamDecrypter();
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public int getCiphertextSegmentSize() {
        return this.ciphertextSegmentSize;
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public int getPlaintextSegmentSize() {
        return this.plaintextSegmentSize;
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public int getHeaderLength() {
        return this.keySizeInBytes + 1 + 7;
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public int getCiphertextOffset() {
        return getHeaderLength() + this.firstSegmentOffset;
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public int getCiphertextOverhead() {
        return this.tagSizeInBytes;
    }

    public int getFirstSegmentOffset() {
        return this.firstSegmentOffset;
    }

    public long expectedCiphertextSize(long plaintextSize) {
        long offset = getCiphertextOffset();
        int i = this.plaintextSegmentSize;
        long fullSegments = (plaintextSize + offset) / i;
        long ciphertextSize = this.ciphertextSegmentSize * fullSegments;
        long lastSegmentSize = (plaintextSize + offset) % i;
        if (lastSegmentSize > 0) {
            return ciphertextSize + this.tagSizeInBytes + lastSegmentSize;
        }
        return ciphertextSize;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Cipher cipherInstance() throws GeneralSecurityException {
        return EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Mac macInstance() throws GeneralSecurityException {
        return EngineFactory.MAC.getInstance(this.tagAlgo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] randomSalt() {
        return Random.randBytes(this.keySizeInBytes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] nonceForSegment(byte[] bArr, long j, boolean z) throws GeneralSecurityException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(16);
        byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
        byteBufferAllocate.put(bArr);
        SubtleUtil.putAsUnsigedInt(byteBufferAllocate, j);
        byteBufferAllocate.put(z ? (byte) 1 : (byte) 0);
        byteBufferAllocate.putInt(0);
        return byteBufferAllocate.array();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] randomNonce() {
        return Random.randBytes(7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] deriveKeyMaterial(byte[] salt, byte[] aad) throws GeneralSecurityException {
        int keyMaterialSize = this.keySizeInBytes + 32;
        return Hkdf.computeHkdf(this.hkdfAlgo, this.ikm, salt, aad, keyMaterialSize);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SecretKeySpec deriveKeySpec(byte[] keyMaterial) throws GeneralSecurityException {
        return new SecretKeySpec(keyMaterial, 0, this.keySizeInBytes, "AES");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SecretKeySpec deriveHmacKeySpec(byte[] keyMaterial) throws GeneralSecurityException {
        return new SecretKeySpec(keyMaterial, this.keySizeInBytes, 32, this.tagAlgo);
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\AesCtrHmacStreaming$AesCtrHmacStreamEncrypter.smali */
    class AesCtrHmacStreamEncrypter implements StreamSegmentEncrypter {
        private final Cipher cipher = AesCtrHmacStreaming.cipherInstance();
        private long encryptedSegments;
        private ByteBuffer header;
        private final SecretKeySpec hmacKeySpec;
        private final SecretKeySpec keySpec;
        private final Mac mac;
        private final byte[] noncePrefix;

        public AesCtrHmacStreamEncrypter(byte[] aad) throws GeneralSecurityException {
            this.encryptedSegments = 0L;
            this.mac = AesCtrHmacStreaming.this.macInstance();
            this.encryptedSegments = 0L;
            byte[] salt = AesCtrHmacStreaming.this.randomSalt();
            byte[] bArrRandomNonce = AesCtrHmacStreaming.this.randomNonce();
            this.noncePrefix = bArrRandomNonce;
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(AesCtrHmacStreaming.this.getHeaderLength());
            this.header = byteBufferAllocate;
            byteBufferAllocate.put((byte) AesCtrHmacStreaming.this.getHeaderLength());
            this.header.put(salt);
            this.header.put(bArrRandomNonce);
            this.header.flip();
            byte[] keymaterial = AesCtrHmacStreaming.this.deriveKeyMaterial(salt, aad);
            this.keySpec = AesCtrHmacStreaming.this.deriveKeySpec(keymaterial);
            this.hmacKeySpec = AesCtrHmacStreaming.this.deriveHmacKeySpec(keymaterial);
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentEncrypter
        public ByteBuffer getHeader() {
            return this.header.asReadOnlyBuffer();
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentEncrypter
        public synchronized void encryptSegment(ByteBuffer plaintext, boolean isLastSegment, ByteBuffer ciphertext) throws GeneralSecurityException {
            int position = ciphertext.position();
            byte[] nonce = AesCtrHmacStreaming.this.nonceForSegment(this.noncePrefix, this.encryptedSegments, isLastSegment);
            this.cipher.init(1, this.keySpec, new IvParameterSpec(nonce));
            this.encryptedSegments++;
            this.cipher.doFinal(plaintext, ciphertext);
            ByteBuffer ctCopy = ciphertext.duplicate();
            ctCopy.flip();
            ctCopy.position(position);
            this.mac.init(this.hmacKeySpec);
            this.mac.update(nonce);
            this.mac.update(ctCopy);
            byte[] tag = this.mac.doFinal();
            ciphertext.put(tag, 0, AesCtrHmacStreaming.this.tagSizeInBytes);
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentEncrypter
        public synchronized void encryptSegment(ByteBuffer part1, ByteBuffer part2, boolean isLastSegment, ByteBuffer ciphertext) throws GeneralSecurityException {
            int position = ciphertext.position();
            byte[] nonce = AesCtrHmacStreaming.this.nonceForSegment(this.noncePrefix, this.encryptedSegments, isLastSegment);
            this.cipher.init(1, this.keySpec, new IvParameterSpec(nonce));
            this.encryptedSegments++;
            this.cipher.update(part1, ciphertext);
            this.cipher.doFinal(part2, ciphertext);
            ByteBuffer ctCopy = ciphertext.duplicate();
            ctCopy.flip();
            ctCopy.position(position);
            this.mac.init(this.hmacKeySpec);
            this.mac.update(nonce);
            this.mac.update(ctCopy);
            byte[] tag = this.mac.doFinal();
            ciphertext.put(tag, 0, AesCtrHmacStreaming.this.tagSizeInBytes);
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\AesCtrHmacStreaming$AesCtrHmacStreamDecrypter.smali */
    class AesCtrHmacStreamDecrypter implements StreamSegmentDecrypter {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Cipher cipher;
        private SecretKeySpec hmacKeySpec;
        private SecretKeySpec keySpec;
        private Mac mac;
        private byte[] noncePrefix;

        AesCtrHmacStreamDecrypter() {
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentDecrypter
        public synchronized void init(ByteBuffer header, byte[] aad) throws GeneralSecurityException {
            if (header.remaining() != AesCtrHmacStreaming.this.getHeaderLength()) {
                throw new InvalidAlgorithmParameterException("Invalid header length");
            }
            byte firstByte = header.get();
            if (firstByte != AesCtrHmacStreaming.this.getHeaderLength()) {
                throw new GeneralSecurityException("Invalid ciphertext");
            }
            this.noncePrefix = new byte[7];
            byte[] salt = new byte[AesCtrHmacStreaming.this.keySizeInBytes];
            header.get(salt);
            header.get(this.noncePrefix);
            byte[] keymaterial = AesCtrHmacStreaming.this.deriveKeyMaterial(salt, aad);
            this.keySpec = AesCtrHmacStreaming.this.deriveKeySpec(keymaterial);
            this.hmacKeySpec = AesCtrHmacStreaming.this.deriveHmacKeySpec(keymaterial);
            this.cipher = AesCtrHmacStreaming.cipherInstance();
            this.mac = AesCtrHmacStreaming.this.macInstance();
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentDecrypter
        public synchronized void decryptSegment(ByteBuffer ciphertext, int segmentNr, boolean isLastSegment, ByteBuffer plaintext) throws GeneralSecurityException {
            int position = ciphertext.position();
            byte[] nonce = AesCtrHmacStreaming.this.nonceForSegment(this.noncePrefix, segmentNr, isLastSegment);
            int ctLength = ciphertext.remaining();
            if (ctLength < AesCtrHmacStreaming.this.tagSizeInBytes) {
                throw new GeneralSecurityException("Ciphertext too short");
            }
            int ptLength = ctLength - AesCtrHmacStreaming.this.tagSizeInBytes;
            int startOfTag = position + ptLength;
            ByteBuffer ct = ciphertext.duplicate();
            ct.limit(startOfTag);
            ByteBuffer tagBuffer = ciphertext.duplicate();
            tagBuffer.position(startOfTag);
            Mac mac = this.mac;
            if (mac == null) {
                throw new AssertionError();
            }
            SecretKeySpec secretKeySpec = this.hmacKeySpec;
            if (secretKeySpec == null) {
                throw new AssertionError();
            }
            mac.init(secretKeySpec);
            this.mac.update(nonce);
            this.mac.update(ct);
            byte[] tag = Arrays.copyOf(this.mac.doFinal(), AesCtrHmacStreaming.this.tagSizeInBytes);
            byte[] expectedTag = new byte[AesCtrHmacStreaming.this.tagSizeInBytes];
            if (tagBuffer.remaining() != AesCtrHmacStreaming.this.tagSizeInBytes) {
                throw new AssertionError();
            }
            tagBuffer.get(expectedTag);
            if (expectedTag.length != tag.length) {
                throw new AssertionError();
            }
            if (!Bytes.equal(expectedTag, tag)) {
                throw new GeneralSecurityException("Tag mismatch");
            }
            ciphertext.limit(startOfTag);
            this.cipher.init(1, this.keySpec, new IvParameterSpec(nonce));
            this.cipher.doFinal(ciphertext, plaintext);
        }
    }
}
