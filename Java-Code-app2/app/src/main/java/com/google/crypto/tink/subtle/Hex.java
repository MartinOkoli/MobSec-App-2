package com.google.crypto.tink.subtle;

import kotlin.UByte;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\Hex.smali */
public final class Hex {
    public static String encode(final byte[] bytes) {
        StringBuilder result = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            int val = b & UByte.MAX_VALUE;
            result.append("0123456789abcdef".charAt(val / 16));
            result.append("0123456789abcdef".charAt(val % 16));
        }
        return result.toString();
    }

    public static byte[] decode(String hex) {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException("Expected a string of even length");
        }
        int size = hex.length() / 2;
        byte[] result = new byte[size];
        for (int i = 0; i < size; i++) {
            int hi = Character.digit(hex.charAt(i * 2), 16);
            int lo = Character.digit(hex.charAt((i * 2) + 1), 16);
            if (hi == -1 || lo == -1) {
                throw new IllegalArgumentException("input is not hexadecimal");
            }
            result[i] = (byte) ((hi * 16) + lo);
        }
        return result;
    }
}
