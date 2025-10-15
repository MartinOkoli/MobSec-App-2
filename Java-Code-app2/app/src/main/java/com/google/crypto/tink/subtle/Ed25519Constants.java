package com.google.crypto.tink.subtle;

import com.google.crypto.tink.subtle.Ed25519;
import java.lang.reflect.Array;
import java.math.BigInteger;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\Ed25519Constants.smali */
final class Ed25519Constants {
    static final Ed25519.CachedXYT[] B2;
    static final Ed25519.CachedXYT[][] B_TABLE;
    static final long[] D;
    static final long[] D2;
    private static final BigInteger D2_BI;
    private static final BigInteger D_BI;
    private static final BigInteger P_BI;
    static final long[] SQRTM1;
    private static final BigInteger SQRTM1_BI;

    Ed25519Constants() {
    }

    static {
        BigInteger bigIntegerSubtract = BigInteger.valueOf(2L).pow(255).subtract(BigInteger.valueOf(19L));
        P_BI = bigIntegerSubtract;
        BigInteger bigIntegerMod = BigInteger.valueOf(-121665L).multiply(BigInteger.valueOf(121666L).modInverse(bigIntegerSubtract)).mod(bigIntegerSubtract);
        D_BI = bigIntegerMod;
        BigInteger bigIntegerMod2 = BigInteger.valueOf(2L).multiply(bigIntegerMod).mod(bigIntegerSubtract);
        D2_BI = bigIntegerMod2;
        BigInteger bigIntegerModPow = BigInteger.valueOf(2L).modPow(bigIntegerSubtract.subtract(BigInteger.ONE).divide(BigInteger.valueOf(4L)), bigIntegerSubtract);
        SQRTM1_BI = bigIntegerModPow;
        Point b = new Point();
        b.y = BigInteger.valueOf(4L).multiply(BigInteger.valueOf(5L).modInverse(bigIntegerSubtract)).mod(bigIntegerSubtract);
        b.x = recoverX(b.y);
        D = Field25519.expand(toLittleEndian(bigIntegerMod));
        D2 = Field25519.expand(toLittleEndian(bigIntegerMod2));
        SQRTM1 = Field25519.expand(toLittleEndian(bigIntegerModPow));
        Point bi = b;
        B_TABLE = (Ed25519.CachedXYT[][]) Array.newInstance((Class<?>) Ed25519.CachedXYT.class, 32, 8);
        for (int i = 0; i < 32; i++) {
            Point bij = bi;
            for (int j = 0; j < 8; j++) {
                B_TABLE[i][j] = getCachedXYT(bij);
                bij = edwards(bij, bi);
            }
            for (int j2 = 0; j2 < 8; j2++) {
                bi = edwards(bi, bi);
            }
        }
        Point bi2 = b;
        Point b2 = edwards(b, b);
        B2 = new Ed25519.CachedXYT[8];
        for (int i2 = 0; i2 < 8; i2++) {
            B2[i2] = getCachedXYT(bi2);
            bi2 = edwards(bi2, b2);
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\Ed25519Constants$Point.smali */
    private static class Point {
        private BigInteger x;
        private BigInteger y;

        private Point() {
        }
    }

    private static BigInteger recoverX(BigInteger y) {
        BigInteger bigIntegerSubtract = y.pow(2).subtract(BigInteger.ONE);
        BigInteger bigIntegerAdd = D_BI.multiply(y.pow(2)).add(BigInteger.ONE);
        BigInteger bigInteger = P_BI;
        BigInteger xx = bigIntegerSubtract.multiply(bigIntegerAdd.modInverse(bigInteger));
        BigInteger x = xx.modPow(bigInteger.add(BigInteger.valueOf(3L)).divide(BigInteger.valueOf(8L)), bigInteger);
        if (!x.pow(2).subtract(xx).mod(bigInteger).equals(BigInteger.ZERO)) {
            x = x.multiply(SQRTM1_BI).mod(bigInteger);
        }
        if (x.testBit(0)) {
            return bigInteger.subtract(x);
        }
        return x;
    }

    private static Point edwards(Point a, Point b) {
        Point o = new Point();
        BigInteger bigIntegerMultiply = D_BI.multiply(a.x.multiply(b.x).multiply(a.y).multiply(b.y));
        BigInteger bigInteger = P_BI;
        BigInteger xxyy = bigIntegerMultiply.mod(bigInteger);
        o.x = a.x.multiply(b.y).add(b.x.multiply(a.y)).multiply(BigInteger.ONE.add(xxyy).modInverse(bigInteger)).mod(bigInteger);
        o.y = a.y.multiply(b.y).add(a.x.multiply(b.x)).multiply(BigInteger.ONE.subtract(xxyy).modInverse(bigInteger)).mod(bigInteger);
        return o;
    }

    private static byte[] toLittleEndian(BigInteger n) {
        byte[] b = new byte[32];
        byte[] nBytes = n.toByteArray();
        System.arraycopy(nBytes, 0, b, 32 - nBytes.length, nBytes.length);
        for (int i = 0; i < b.length / 2; i++) {
            byte t = b[i];
            b[i] = b[(b.length - i) - 1];
            b[(b.length - i) - 1] = t;
        }
        return b;
    }

    private static Ed25519.CachedXYT getCachedXYT(Point p) {
        BigInteger bigIntegerAdd = p.y.add(p.x);
        BigInteger bigInteger = P_BI;
        return new Ed25519.CachedXYT(Field25519.expand(toLittleEndian(bigIntegerAdd.mod(bigInteger))), Field25519.expand(toLittleEndian(p.y.subtract(p.x).mod(bigInteger))), Field25519.expand(toLittleEndian(D2_BI.multiply(p.x).multiply(p.y).mod(bigInteger))));
    }
}
