package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.KeyTemplate;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\aead\KmsEnvelopeAead.smali */
public final class KmsEnvelopeAead implements Aead {
    private static final byte[] EMPTY_AAD = new byte[0];
    private static final int LENGTH_ENCRYPTED_DEK = 4;
    private final KeyTemplate dekTemplate;
    private final Aead remote;

    public KmsEnvelopeAead(KeyTemplate dekTemplate, Aead remote) {
        this.dekTemplate = dekTemplate;
        this.remote = remote;
    }

    public byte[] encrypt(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
        byte[] dek = Registry.newKey(this.dekTemplate).toByteArray();
        byte[] encryptedDek = this.remote.encrypt(dek, EMPTY_AAD);
        Aead aead = (Aead) Registry.getPrimitive(this.dekTemplate.getTypeUrl(), dek, Aead.class);
        byte[] payload = aead.encrypt(plaintext, associatedData);
        return buildCiphertext(encryptedDek, payload);
    }

    public byte[] decrypt(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(ciphertext);
            int encryptedDekSize = buffer.getInt();
            if (encryptedDekSize <= 0 || encryptedDekSize > ciphertext.length - 4) {
                throw new GeneralSecurityException("invalid ciphertext");
            }
            byte[] encryptedDek = new byte[encryptedDekSize];
            buffer.get(encryptedDek, 0, encryptedDekSize);
            byte[] payload = new byte[buffer.remaining()];
            buffer.get(payload, 0, buffer.remaining());
            byte[] dek = this.remote.decrypt(encryptedDek, EMPTY_AAD);
            Aead aead = (Aead) Registry.getPrimitive(this.dekTemplate.getTypeUrl(), dek, Aead.class);
            return aead.decrypt(payload, associatedData);
        } catch (IndexOutOfBoundsException | NegativeArraySizeException | BufferUnderflowException e) {
            throw new GeneralSecurityException("invalid ciphertext", e);
        }
    }

    private byte[] buildCiphertext(final byte[] encryptedDek, final byte[] payload) {
        return ByteBuffer.allocate(encryptedDek.length + 4 + payload.length).putInt(encryptedDek.length).put(encryptedDek).put(payload).array();
    }
}
