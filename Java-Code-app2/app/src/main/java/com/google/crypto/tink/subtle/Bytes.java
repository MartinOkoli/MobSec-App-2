package com.google.crypto.tink.subtle;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\Bytes.smali */
public final class Bytes {
    public static final boolean equal(final byte[] x, final byte[] y) {
        if (x == null || y == null || x.length != y.length) {
            return false;
        }
        int res = 0;
        for (int i = 0; i < x.length; i++) {
            res |= x[i] ^ y[i];
        }
        return res == 0;
    }

    public static byte[] concat(byte[]... chunks) throws GeneralSecurityException {
        int length = 0;
        for (byte[] chunk : chunks) {
            if (length > Integer.MAX_VALUE - chunk.length) {
                throw new GeneralSecurityException("exceeded size limit");
            }
            length += chunk.length;
        }
        byte[] res = new byte[length];
        int pos = 0;
        for (byte[] chunk2 : chunks) {
            System.arraycopy(chunk2, 0, res, pos, chunk2.length);
            pos += chunk2.length;
        }
        return res;
    }

    public static final byte[] xor(final byte[] x, int offsetX, final byte[] y, int offsetY, int len) {
        if (len < 0 || x.length - len < offsetX || y.length - len < offsetY) {
            throw new IllegalArgumentException("That combination of buffers, offsets and length to xor result in out-of-bond accesses.");
        }
        byte[] res = new byte[len];
        for (int i = 0; i < len; i++) {
            res[i] = (byte) (x[i + offsetX] ^ y[i + offsetY]);
        }
        return res;
    }

    public static final void xor(ByteBuffer output, ByteBuffer x, ByteBuffer y, int len) {
        if (len < 0 || x.remaining() < len || y.remaining() < len || output.remaining() < len) {
            throw new IllegalArgumentException("That combination of buffers, offsets and length to xor result in out-of-bond accesses.");
        }
        for (int i = 0; i < len; i++) {
            output.put((byte) (x.get() ^ y.get()));
        }
    }

    public static final byte[] xor(final byte[] x, final byte[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("The lengths of x and y should match.");
        }
        return xor(x, 0, y, 0, x.length);
    }

    public static final byte[] xorEnd(final byte[] a, final byte[] b) {
        if (a.length < b.length) {
            throw new IllegalArgumentException("xorEnd requires a.length >= b.length");
        }
        int paddingLength = a.length - b.length;
        byte[] res = Arrays.copyOf(a, a.length);
        for (int i = 0; i < b.length; i++) {
            int i2 = paddingLength + i;
            res[i2] = (byte) (res[i2] ^ b[i]);
        }
        return res;
    }

    public static byte[] intToByteArray(int capacity, int value) {
        byte[] result = new byte[capacity];
        for (int i = 0; i < capacity; i++) {
            result[i] = (byte) ((value >> (i * 8)) & 255);
        }
        return result;
    }

    public static int byteArrayToInt(byte[] bytes) {
        return byteArrayToInt(bytes, bytes.length);
    }

    public static int byteArrayToInt(byte[] bytes, int length) {
        return byteArrayToInt(bytes, 0, length);
    }

    public static int byteArrayToInt(byte[] bytes, int offset, int length) {
        int value = 0;
        for (int i = 0; i < length; i++) {
            value += (bytes[i + offset] & 255) << (i * 8);
        }
        return value;
    }
}
