package com.google.crypto.tink.subtle;

import java.security.InvalidKeyException;
import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;
import okio.Utf8;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\X25519.smali */
public final class X25519 {
    public static byte[] generatePrivateKey() {
        byte[] privateKey = Random.randBytes(32);
        privateKey[0] = (byte) (privateKey[0] | 7);
        privateKey[31] = (byte) (privateKey[31] & Utf8.REPLACEMENT_BYTE);
        privateKey[31] = (byte) (privateKey[31] | ByteCompanionObject.MIN_VALUE);
        return privateKey;
    }

    public static byte[] computeSharedSecret(byte[] privateKey, byte[] peersPublicValue) throws InvalidKeyException {
        if (privateKey.length != 32) {
            throw new InvalidKeyException("Private key must have 32 bytes.");
        }
        long[] x = new long[11];
        byte[] e = Arrays.copyOf(privateKey, 32);
        e[0] = (byte) (e[0] & 248);
        e[31] = (byte) (e[31] & ByteCompanionObject.MAX_VALUE);
        e[31] = (byte) (e[31] | 64);
        Curve25519.curveMult(x, e, peersPublicValue);
        return Field25519.contract(x);
    }

    public static byte[] publicFromPrivate(byte[] privateKey) throws InvalidKeyException {
        if (privateKey.length != 32) {
            throw new InvalidKeyException("Private key must have 32 bytes.");
        }
        byte[] base = new byte[32];
        base[0] = 9;
        return computeSharedSecret(privateKey, base);
    }
}
