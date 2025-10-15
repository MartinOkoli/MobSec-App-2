package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import kotlin.UByte;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\Poly1305.smali */
class Poly1305 {
    public static final int MAC_KEY_SIZE_IN_BYTES = 32;
    public static final int MAC_TAG_SIZE_IN_BYTES = 16;

    private Poly1305() {
    }

    private static long load32(byte[] in, int idx) {
        return ((in[idx] & UByte.MAX_VALUE) | ((in[idx + 1] & UByte.MAX_VALUE) << 8) | ((in[idx + 2] & UByte.MAX_VALUE) << 16) | ((in[idx + 3] & UByte.MAX_VALUE) << 24)) & 4294967295L;
    }

    private static long load26(byte[] in, int idx, int shift) {
        return (load32(in, idx) >> shift) & 67108863;
    }

    private static void toByteArray(byte[] output, long num, int idx) {
        int i = 0;
        while (i < 4) {
            output[idx + i] = (byte) (255 & num);
            i++;
            num >>= 8;
        }
    }

    private static void copyBlockSize(byte[] output, byte[] in, int idx) {
        int copyCount = Math.min(16, in.length - idx);
        System.arraycopy(in, idx, output, 0, copyCount);
        output[copyCount] = 1;
        if (copyCount != 16) {
            Arrays.fill(output, copyCount + 1, output.length, (byte) 0);
        }
    }

    static byte[] computeMac(final byte[] key, byte[] data) {
        if (key.length != 32) {
            throw new IllegalArgumentException("The key length in bytes must be 32.");
        }
        long h0 = 0;
        long h1 = 0;
        long h2 = 0;
        long h3 = 0;
        long h4 = 0;
        long d0 = load26(key, 0, 0) & 67108863;
        long r1 = load26(key, 3, 2) & 67108611;
        long r2 = load26(key, 6, 4) & 67092735;
        long r3 = load26(key, 9, 6) & 66076671;
        long r4 = load26(key, 12, 8) & 1048575;
        long s1 = r1 * 5;
        long s2 = r2 * 5;
        long s3 = r3 * 5;
        long s4 = r4 * 5;
        byte[] buf = new byte[17];
        int i = 0;
        while (true) {
            long r0 = d0;
            if (i < data.length) {
                copyBlockSize(buf, data, i);
                long h02 = h0 + load26(buf, 0, 0);
                long h12 = h1 + load26(buf, 3, 2);
                long h22 = h2 + load26(buf, 6, 4);
                long h32 = h3 + load26(buf, 9, 6);
                long h42 = h4 + (load26(buf, 12, 8) | (buf[16] << 24));
                long d02 = (h02 * r0) + (h12 * s4) + (h22 * s3) + (h32 * s2) + (h42 * s1);
                long d1 = (h02 * r1) + (h12 * r0) + (h22 * s4) + (h32 * s3) + (h42 * s2);
                long d2 = (h02 * r2) + (h12 * r1) + (h22 * r0) + (h32 * s4) + (h42 * s3);
                long d3 = (h02 * r3) + (h12 * r2) + (h22 * r1) + (h32 * r0) + (h42 * s4);
                long d4 = (h02 * r4) + (h12 * r3) + (h22 * r2) + (h32 * r1) + (h42 * r0);
                long c = d02 >> 26;
                long h03 = d02 & 67108863;
                long d12 = d1 + c;
                long c2 = d12 >> 26;
                long h13 = d12 & 67108863;
                long d22 = d2 + c2;
                long c3 = d22 >> 26;
                h2 = d22 & 67108863;
                long d32 = d3 + c3;
                long c4 = d32 >> 26;
                h3 = d32 & 67108863;
                long d42 = d4 + c4;
                long c5 = d42 >> 26;
                h4 = d42 & 67108863;
                long h04 = h03 + (c5 * 5);
                long c6 = h04 >> 26;
                h0 = h04 & 67108863;
                h1 = h13 + c6;
                i += 16;
                d0 = r0;
            } else {
                long c7 = h1 >> 26;
                long h23 = h2 + c7;
                long c8 = h23 >> 26;
                long h24 = h23 & 67108863;
                long h33 = h3 + c8;
                long c9 = h33 >> 26;
                long h34 = h33 & 67108863;
                long h43 = h4 + c9;
                long c10 = h43 >> 26;
                long h44 = h43 & 67108863;
                long h05 = h0 + (c10 * 5);
                long c11 = h05 >> 26;
                long h06 = h05 & 67108863;
                long h14 = (h1 & 67108863) + c11;
                long g0 = h06 + 5;
                long c12 = g0 >> 26;
                long g1 = h14 + c12;
                long c13 = g1 >> 26;
                long g2 = h24 + c13;
                long c14 = g2 >> 26;
                long g3 = h34 + c14;
                long c15 = g3 >> 26;
                long g32 = g3 & 67108863;
                long g4 = (h44 + c15) - 67108864;
                long mask = g4 >> 63;
                long h07 = h06 & mask;
                long h15 = h14 & mask;
                long h25 = h24 & mask;
                long h35 = h34 & mask;
                long h45 = h44 & mask;
                long mask2 = ~mask;
                long h16 = h15 | (g1 & 67108863 & mask2);
                long h26 = h25 | (g2 & 67108863 & mask2);
                long h36 = h35 | (g32 & mask2);
                long h08 = ((h16 << 26) | h07 | (g0 & 67108863 & mask2)) & 4294967295L;
                long h17 = ((h16 >> 6) | (h26 << 20)) & 4294967295L;
                long h27 = ((h26 >> 12) | (h36 << 14)) & 4294967295L;
                long h37 = ((h36 >> 18) | ((h45 | (g4 & mask2)) << 8)) & 4294967295L;
                long c16 = h08 + load32(key, 16);
                long h09 = c16 & 4294967295L;
                long c17 = load32(key, 20) + h17 + (c16 >> 32);
                long h18 = c17 & 4294967295L;
                long c18 = load32(key, 24) + h27 + (c17 >> 32);
                long h28 = c18 & 4294967295L;
                long c19 = load32(key, 28) + h37 + (c18 >> 32);
                byte[] mac = new byte[16];
                toByteArray(mac, h09, 0);
                toByteArray(mac, h18, 4);
                toByteArray(mac, h28, 8);
                toByteArray(mac, c19 & 4294967295L, 12);
                return mac;
            }
        }
    }

    static void verifyMac(final byte[] key, byte[] data, byte[] mac) throws GeneralSecurityException {
        if (!Bytes.equal(computeMac(key, data), mac)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }
}
