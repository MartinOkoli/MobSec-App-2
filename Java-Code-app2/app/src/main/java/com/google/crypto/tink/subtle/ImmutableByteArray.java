package com.google.crypto.tink.subtle;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\ImmutableByteArray.smali */
public final class ImmutableByteArray {
    private final byte[] data;

    public static ImmutableByteArray of(final byte[] data) {
        if (data == null) {
            return null;
        }
        return of(data, 0, data.length);
    }

    public static ImmutableByteArray of(final byte[] data, final int start, final int len) {
        return new ImmutableByteArray(data, start, len);
    }

    public byte[] getBytes() {
        byte[] bArr = this.data;
        byte[] result = new byte[bArr.length];
        System.arraycopy(bArr, 0, result, 0, bArr.length);
        return result;
    }

    public int getLength() {
        return this.data.length;
    }

    private ImmutableByteArray(final byte[] buf, final int start, final int len) {
        byte[] bArr = new byte[len];
        this.data = bArr;
        System.arraycopy(buf, start, bArr, 0, len);
    }
}
