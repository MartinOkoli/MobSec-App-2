package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.OutputPrefixType;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\KeysetManager.smali */
public final class KeysetManager {
    private final Keyset.Builder keysetBuilder;

    private KeysetManager(Keyset.Builder val) {
        this.keysetBuilder = val;
    }

    public static KeysetManager withKeysetHandle(KeysetHandle val) {
        return new KeysetManager(val.getKeyset().toBuilder());
    }

    public static KeysetManager withEmptyKeyset() {
        return new KeysetManager(Keyset.newBuilder());
    }

    public synchronized KeysetHandle getKeysetHandle() throws GeneralSecurityException {
        return KeysetHandle.fromKeyset(this.keysetBuilder.build());
    }

    @Deprecated
    public synchronized KeysetManager rotate(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        addNewKey(keyTemplate, true);
        return this;
    }

    @Deprecated
    public synchronized KeysetManager add(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        addNewKey(keyTemplate, false);
        return this;
    }

    public synchronized KeysetManager add(KeyTemplate keyTemplate) throws GeneralSecurityException {
        addNewKey(keyTemplate.getProto(), false);
        return this;
    }

    @Deprecated
    public synchronized int addNewKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate, boolean asPrimary) throws GeneralSecurityException {
        Keyset.Key key;
        key = newKey(keyTemplate);
        this.keysetBuilder.addKey(key);
        if (asPrimary) {
            this.keysetBuilder.setPrimaryKeyId(key.getKeyId());
        }
        return key.getKeyId();
    }

    public synchronized KeysetManager setPrimary(int keyId) throws GeneralSecurityException {
        for (int i = 0; i < this.keysetBuilder.getKeyCount(); i++) {
            Keyset.Key key = this.keysetBuilder.getKey(i);
            if (key.getKeyId() == keyId) {
                if (!key.getStatus().equals(KeyStatusType.ENABLED)) {
                    throw new GeneralSecurityException("cannot set key as primary because it's not enabled: " + keyId);
                }
                this.keysetBuilder.setPrimaryKeyId(keyId);
            }
        }
        throw new GeneralSecurityException("key not found: " + keyId);
        return this;
    }

    @Deprecated
    public synchronized KeysetManager promote(int keyId) throws GeneralSecurityException {
        return setPrimary(keyId);
    }

    public synchronized KeysetManager enable(int keyId) throws GeneralSecurityException {
        for (int i = 0; i < this.keysetBuilder.getKeyCount(); i++) {
            Keyset.Key key = this.keysetBuilder.getKey(i);
            if (key.getKeyId() == keyId) {
                if (key.getStatus() != KeyStatusType.ENABLED && key.getStatus() != KeyStatusType.DISABLED) {
                    throw new GeneralSecurityException("cannot enable key with id " + keyId + " and status " + key.getStatus());
                }
                this.keysetBuilder.setKey(i, key.toBuilder().setStatus(KeyStatusType.ENABLED).build());
            }
        }
        throw new GeneralSecurityException("key not found: " + keyId);
        return this;
    }

    public synchronized KeysetManager disable(int keyId) throws GeneralSecurityException {
        if (keyId == this.keysetBuilder.getPrimaryKeyId()) {
            throw new GeneralSecurityException("cannot disable the primary key");
        }
        for (int i = 0; i < this.keysetBuilder.getKeyCount(); i++) {
            Keyset.Key key = this.keysetBuilder.getKey(i);
            if (key.getKeyId() == keyId) {
                if (key.getStatus() != KeyStatusType.ENABLED && key.getStatus() != KeyStatusType.DISABLED) {
                    throw new GeneralSecurityException("cannot disable key with id " + keyId + " and status " + key.getStatus());
                }
                this.keysetBuilder.setKey(i, key.toBuilder().setStatus(KeyStatusType.DISABLED).build());
            }
        }
        throw new GeneralSecurityException("key not found: " + keyId);
        return this;
    }

    public synchronized KeysetManager delete(int keyId) throws GeneralSecurityException {
        if (keyId == this.keysetBuilder.getPrimaryKeyId()) {
            throw new GeneralSecurityException("cannot delete the primary key");
        }
        for (int i = 0; i < this.keysetBuilder.getKeyCount(); i++) {
            Keyset.Key key = this.keysetBuilder.getKey(i);
            if (key.getKeyId() == keyId) {
                this.keysetBuilder.removeKey(i);
            }
        }
        throw new GeneralSecurityException("key not found: " + keyId);
        return this;
    }

    public synchronized KeysetManager destroy(int keyId) throws GeneralSecurityException {
        if (keyId == this.keysetBuilder.getPrimaryKeyId()) {
            throw new GeneralSecurityException("cannot destroy the primary key");
        }
        for (int i = 0; i < this.keysetBuilder.getKeyCount(); i++) {
            Keyset.Key key = this.keysetBuilder.getKey(i);
            if (key.getKeyId() == keyId) {
                if (key.getStatus() != KeyStatusType.ENABLED && key.getStatus() != KeyStatusType.DISABLED && key.getStatus() != KeyStatusType.DESTROYED) {
                    throw new GeneralSecurityException("cannot destroy key with id " + keyId + " and status " + key.getStatus());
                }
                this.keysetBuilder.setKey(i, key.toBuilder().setStatus(KeyStatusType.DESTROYED).clearKeyData().build());
            }
        }
        throw new GeneralSecurityException("key not found: " + keyId);
        return this;
    }

    private synchronized Keyset.Key newKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeyData keyData;
        int keyId;
        OutputPrefixType outputPrefixType;
        keyData = Registry.newKeyData(keyTemplate);
        keyId = newKeyId();
        outputPrefixType = keyTemplate.getOutputPrefixType();
        if (outputPrefixType == OutputPrefixType.UNKNOWN_PREFIX) {
            outputPrefixType = OutputPrefixType.TINK;
        }
        return Keyset.Key.newBuilder().setKeyData(keyData).setKeyId(keyId).setStatus(KeyStatusType.ENABLED).setOutputPrefixType(outputPrefixType).build();
    }

    private synchronized boolean keyIdExists(int keyId) {
        for (Keyset.Key key : this.keysetBuilder.getKeyList()) {
            if (key.getKeyId() == keyId) {
                return true;
            }
        }
        return false;
    }

    private synchronized int newKeyId() {
        int keyId;
        keyId = randPositiveInt();
        while (keyIdExists(keyId)) {
            keyId = randPositiveInt();
        }
        return keyId;
    }

    private static int randPositiveInt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] rand = new byte[4];
        int result = 0;
        while (result == 0) {
            secureRandom.nextBytes(rand);
            result = ((rand[0] & ByteCompanionObject.MAX_VALUE) << 24) | ((rand[1] & UByte.MAX_VALUE) << 16) | ((rand[2] & UByte.MAX_VALUE) << 8) | (rand[3] & UByte.MAX_VALUE);
        }
        return result;
    }
}
