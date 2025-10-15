package com.google.crypto.tink.subtle;

import android.support.v4.media.session.PlaybackStateCompat;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\Ed25519.smali */
final class Ed25519 {
    public static final int PUBLIC_KEY_LEN = 32;
    public static final int SECRET_KEY_LEN = 32;
    public static final int SIGNATURE_LEN = 64;
    private static final CachedXYT CACHED_NEUTRAL = new CachedXYT(new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    private static final PartialXYZT NEUTRAL = new PartialXYZT(new XYZ(new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}), new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    static final byte[] GROUP_ORDER = {-19, -45, -11, 92, 26, 99, 18, 88, -42, -100, -9, -94, -34, -7, -34, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16};

    Ed25519() {
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\Ed25519$XYZ.smali */
    private static class XYZ {
        final long[] x;
        final long[] y;
        final long[] z;

        XYZ() {
            this(new long[10], new long[10], new long[10]);
        }

        XYZ(long[] x, long[] y, long[] z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        XYZ(XYZ xyz) {
            this.x = Arrays.copyOf(xyz.x, 10);
            this.y = Arrays.copyOf(xyz.y, 10);
            this.z = Arrays.copyOf(xyz.z, 10);
        }

        XYZ(PartialXYZT partialXYZT) {
            this();
            fromPartialXYZT(this, partialXYZT);
        }

        static XYZ fromPartialXYZT(XYZ out, PartialXYZT in) {
            Field25519.mult(out.x, in.xyz.x, in.t);
            Field25519.mult(out.y, in.xyz.y, in.xyz.z);
            Field25519.mult(out.z, in.xyz.z, in.t);
            return out;
        }

        byte[] toBytes() {
            long[] recip = new long[10];
            long[] x = new long[10];
            long[] y = new long[10];
            Field25519.inverse(recip, this.z);
            Field25519.mult(x, this.x, recip);
            Field25519.mult(y, this.y, recip);
            byte[] s = Field25519.contract(y);
            s[31] = (byte) (s[31] ^ (Ed25519.getLsb(x) << 7));
            return s;
        }

        boolean isOnCurve() {
            long[] x2 = new long[10];
            Field25519.square(x2, this.x);
            long[] y2 = new long[10];
            Field25519.square(y2, this.y);
            long[] z2 = new long[10];
            Field25519.square(z2, this.z);
            long[] z4 = new long[10];
            Field25519.square(z4, z2);
            long[] lhs = new long[10];
            Field25519.sub(lhs, y2, x2);
            Field25519.mult(lhs, lhs, z2);
            long[] rhs = new long[10];
            Field25519.mult(rhs, x2, y2);
            Field25519.mult(rhs, rhs, Ed25519Constants.D);
            Field25519.sum(rhs, z4);
            Field25519.reduce(rhs, rhs);
            return Bytes.equal(Field25519.contract(lhs), Field25519.contract(rhs));
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\Ed25519$XYZT.smali */
    private static class XYZT {
        final long[] t;
        final XYZ xyz;

        XYZT() {
            this(new XYZ(), new long[10]);
        }

        XYZT(XYZ xyz, long[] t) {
            this.xyz = xyz;
            this.t = t;
        }

        XYZT(PartialXYZT partialXYZT) {
            this();
            fromPartialXYZT(this, partialXYZT);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static XYZT fromPartialXYZT(XYZT out, PartialXYZT in) {
            Field25519.mult(out.xyz.x, in.xyz.x, in.t);
            Field25519.mult(out.xyz.y, in.xyz.y, in.xyz.z);
            Field25519.mult(out.xyz.z, in.xyz.z, in.t);
            Field25519.mult(out.t, in.xyz.x, in.xyz.y);
            return out;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static XYZT fromBytesNegateVarTime(byte[] s) throws GeneralSecurityException {
            long[] x = new long[10];
            long[] y = Field25519.expand(s);
            long[] z = new long[10];
            z[0] = 1;
            long[] t = new long[10];
            long[] u = new long[10];
            long[] v = new long[10];
            long[] vxx = new long[10];
            long[] check = new long[10];
            Field25519.square(u, y);
            Field25519.mult(v, u, Ed25519Constants.D);
            Field25519.sub(u, u, z);
            Field25519.sum(v, v, z);
            long[] v3 = new long[10];
            Field25519.square(v3, v);
            Field25519.mult(v3, v3, v);
            Field25519.square(x, v3);
            Field25519.mult(x, x, v);
            Field25519.mult(x, x, u);
            Ed25519.pow2252m3(x, x);
            Field25519.mult(x, x, v3);
            Field25519.mult(x, x, u);
            Field25519.square(vxx, x);
            Field25519.mult(vxx, vxx, v);
            Field25519.sub(check, vxx, u);
            if (Ed25519.isNonZeroVarTime(check)) {
                Field25519.sum(check, vxx, u);
                if (Ed25519.isNonZeroVarTime(check)) {
                    throw new GeneralSecurityException("Cannot convert given bytes to extended projective coordinates. No square root exists for modulo 2^255-19");
                }
                Field25519.mult(x, x, Ed25519Constants.SQRTM1);
            }
            if (Ed25519.isNonZeroVarTime(x) || ((s[31] & UByte.MAX_VALUE) >> 7) == 0) {
                if (Ed25519.getLsb(x) == ((s[31] & UByte.MAX_VALUE) >> 7)) {
                    Ed25519.neg(x, x);
                }
                Field25519.mult(t, x, y);
                return new XYZT(new XYZ(x, y, z), t);
            }
            throw new GeneralSecurityException("Cannot convert given bytes to extended projective coordinates. Computed x is zero and encoded x's least significant bit is not zero");
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\Ed25519$PartialXYZT.smali */
    private static class PartialXYZT {
        final long[] t;
        final XYZ xyz;

        PartialXYZT() {
            this(new XYZ(), new long[10]);
        }

        PartialXYZT(XYZ xyz, long[] t) {
            this.xyz = xyz;
            this.t = t;
        }

        PartialXYZT(PartialXYZT other) {
            this.xyz = new XYZ(other.xyz);
            this.t = Arrays.copyOf(other.t, 10);
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\Ed25519$CachedXYT.smali */
    static class CachedXYT {
        final long[] t2d;
        final long[] yMinusX;
        final long[] yPlusX;

        CachedXYT() {
            this(new long[10], new long[10], new long[10]);
        }

        CachedXYT(long[] yPlusX, long[] yMinusX, long[] t2d) {
            this.yPlusX = yPlusX;
            this.yMinusX = yMinusX;
            this.t2d = t2d;
        }

        CachedXYT(CachedXYT other) {
            this.yPlusX = Arrays.copyOf(other.yPlusX, 10);
            this.yMinusX = Arrays.copyOf(other.yMinusX, 10);
            this.t2d = Arrays.copyOf(other.t2d, 10);
        }

        void multByZ(long[] output, long[] in) {
            System.arraycopy(in, 0, output, 0, 10);
        }

        void copyConditional(CachedXYT other, int icopy) {
            Curve25519.copyConditional(this.yPlusX, other.yPlusX, icopy);
            Curve25519.copyConditional(this.yMinusX, other.yMinusX, icopy);
            Curve25519.copyConditional(this.t2d, other.t2d, icopy);
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\Ed25519$CachedXYZT.smali */
    private static class CachedXYZT extends CachedXYT {
        private final long[] z;

        CachedXYZT() {
            this(new long[10], new long[10], new long[10], new long[10]);
        }

        CachedXYZT(XYZT xyzt) {
            this();
            Field25519.sum(this.yPlusX, xyzt.xyz.y, xyzt.xyz.x);
            Field25519.sub(this.yMinusX, xyzt.xyz.y, xyzt.xyz.x);
            System.arraycopy(xyzt.xyz.z, 0, this.z, 0, 10);
            Field25519.mult(this.t2d, xyzt.t, Ed25519Constants.D2);
        }

        CachedXYZT(long[] yPlusX, long[] yMinusX, long[] z, long[] t2d) {
            super(yPlusX, yMinusX, t2d);
            this.z = z;
        }

        @Override // com.google.crypto.tink.subtle.Ed25519.CachedXYT
        public void multByZ(long[] output, long[] in) {
            Field25519.mult(output, in, this.z);
        }
    }

    private static void add(PartialXYZT partialXYZT, XYZT extended, CachedXYT cached) {
        long[] t = new long[10];
        Field25519.sum(partialXYZT.xyz.x, extended.xyz.y, extended.xyz.x);
        Field25519.sub(partialXYZT.xyz.y, extended.xyz.y, extended.xyz.x);
        Field25519.mult(partialXYZT.xyz.y, partialXYZT.xyz.y, cached.yMinusX);
        Field25519.mult(partialXYZT.xyz.z, partialXYZT.xyz.x, cached.yPlusX);
        Field25519.mult(partialXYZT.t, extended.t, cached.t2d);
        cached.multByZ(partialXYZT.xyz.x, extended.xyz.z);
        Field25519.sum(t, partialXYZT.xyz.x, partialXYZT.xyz.x);
        Field25519.sub(partialXYZT.xyz.x, partialXYZT.xyz.z, partialXYZT.xyz.y);
        Field25519.sum(partialXYZT.xyz.y, partialXYZT.xyz.z, partialXYZT.xyz.y);
        Field25519.sum(partialXYZT.xyz.z, t, partialXYZT.t);
        Field25519.sub(partialXYZT.t, t, partialXYZT.t);
    }

    private static void sub(PartialXYZT partialXYZT, XYZT extended, CachedXYT cached) {
        long[] t = new long[10];
        Field25519.sum(partialXYZT.xyz.x, extended.xyz.y, extended.xyz.x);
        Field25519.sub(partialXYZT.xyz.y, extended.xyz.y, extended.xyz.x);
        Field25519.mult(partialXYZT.xyz.y, partialXYZT.xyz.y, cached.yPlusX);
        Field25519.mult(partialXYZT.xyz.z, partialXYZT.xyz.x, cached.yMinusX);
        Field25519.mult(partialXYZT.t, extended.t, cached.t2d);
        cached.multByZ(partialXYZT.xyz.x, extended.xyz.z);
        Field25519.sum(t, partialXYZT.xyz.x, partialXYZT.xyz.x);
        Field25519.sub(partialXYZT.xyz.x, partialXYZT.xyz.z, partialXYZT.xyz.y);
        Field25519.sum(partialXYZT.xyz.y, partialXYZT.xyz.z, partialXYZT.xyz.y);
        Field25519.sub(partialXYZT.xyz.z, t, partialXYZT.t);
        Field25519.sum(partialXYZT.t, t, partialXYZT.t);
    }

    private static void doubleXYZ(PartialXYZT partialXYZT, XYZ p) {
        long[] t0 = new long[10];
        Field25519.square(partialXYZT.xyz.x, p.x);
        Field25519.square(partialXYZT.xyz.z, p.y);
        Field25519.square(partialXYZT.t, p.z);
        Field25519.sum(partialXYZT.t, partialXYZT.t, partialXYZT.t);
        Field25519.sum(partialXYZT.xyz.y, p.x, p.y);
        Field25519.square(t0, partialXYZT.xyz.y);
        Field25519.sum(partialXYZT.xyz.y, partialXYZT.xyz.z, partialXYZT.xyz.x);
        Field25519.sub(partialXYZT.xyz.z, partialXYZT.xyz.z, partialXYZT.xyz.x);
        Field25519.sub(partialXYZT.xyz.x, t0, partialXYZT.xyz.y);
        Field25519.sub(partialXYZT.t, partialXYZT.t, partialXYZT.xyz.z);
    }

    private static void doubleXYZT(PartialXYZT partialXYZT, XYZT p) {
        doubleXYZ(partialXYZT, p.xyz);
    }

    private static int eq(int a, int b) {
        int r = (~(a ^ b)) & 255;
        int r2 = r & (r << 4);
        int r3 = r2 & (r2 << 2);
        return ((r3 & (r3 << 1)) >> 7) & 1;
    }

    private static void select(CachedXYT t, int pos, byte b) {
        int bnegative = (b & UByte.MAX_VALUE) >> 7;
        int babs = b - (((-bnegative) & b) << 1);
        t.copyConditional(Ed25519Constants.B_TABLE[pos][0], eq(babs, 1));
        t.copyConditional(Ed25519Constants.B_TABLE[pos][1], eq(babs, 2));
        t.copyConditional(Ed25519Constants.B_TABLE[pos][2], eq(babs, 3));
        t.copyConditional(Ed25519Constants.B_TABLE[pos][3], eq(babs, 4));
        t.copyConditional(Ed25519Constants.B_TABLE[pos][4], eq(babs, 5));
        t.copyConditional(Ed25519Constants.B_TABLE[pos][5], eq(babs, 6));
        t.copyConditional(Ed25519Constants.B_TABLE[pos][6], eq(babs, 7));
        t.copyConditional(Ed25519Constants.B_TABLE[pos][7], eq(babs, 8));
        long[] yPlusX = Arrays.copyOf(t.yMinusX, 10);
        long[] yMinusX = Arrays.copyOf(t.yPlusX, 10);
        long[] t2d = Arrays.copyOf(t.t2d, 10);
        neg(t2d, t2d);
        CachedXYT minust = new CachedXYT(yPlusX, yMinusX, t2d);
        t.copyConditional(minust, bnegative);
    }

    private static XYZ scalarMultWithBase(byte[] a) {
        byte[] e = new byte[64];
        for (int i = 0; i < 32; i++) {
            e[(i * 2) + 0] = (byte) (((a[i] & UByte.MAX_VALUE) >> 0) & 15);
            e[(i * 2) + 1] = (byte) (((a[i] & UByte.MAX_VALUE) >> 4) & 15);
        }
        int carry = 0;
        for (int i2 = 0; i2 < e.length - 1; i2++) {
            e[i2] = (byte) (e[i2] + carry);
            int carry2 = e[i2] + 8;
            carry = carry2 >> 4;
            int carry3 = e[i2];
            e[i2] = (byte) (carry3 - (carry << 4));
        }
        int i3 = e.length;
        int i4 = i3 - 1;
        e[i4] = (byte) (e[i4] + carry);
        PartialXYZT ret = new PartialXYZT(NEUTRAL);
        XYZT xyzt = new XYZT();
        for (int i5 = 1; i5 < e.length; i5 += 2) {
            CachedXYT t = new CachedXYT(CACHED_NEUTRAL);
            select(t, i5 / 2, e[i5]);
            add(ret, XYZT.fromPartialXYZT(xyzt, ret), t);
        }
        XYZ xyz = new XYZ();
        doubleXYZ(ret, XYZ.fromPartialXYZT(xyz, ret));
        doubleXYZ(ret, XYZ.fromPartialXYZT(xyz, ret));
        doubleXYZ(ret, XYZ.fromPartialXYZT(xyz, ret));
        doubleXYZ(ret, XYZ.fromPartialXYZT(xyz, ret));
        for (int i6 = 0; i6 < e.length; i6 += 2) {
            CachedXYT t2 = new CachedXYT(CACHED_NEUTRAL);
            select(t2, i6 / 2, e[i6]);
            add(ret, XYZT.fromPartialXYZT(xyzt, ret), t2);
        }
        XYZ result = new XYZ(ret);
        if (!result.isOnCurve()) {
            throw new IllegalStateException("arithmetic error in scalar multiplication");
        }
        return result;
    }

    static byte[] scalarMultWithBaseToBytes(byte[] a) {
        return scalarMultWithBase(a).toBytes();
    }

    private static byte[] slide(byte[] a) {
        byte[] r = new byte[256];
        for (int i = 0; i < 256; i++) {
            r[i] = (byte) (1 & ((a[i >> 3] & UByte.MAX_VALUE) >> (i & 7)));
        }
        for (int i2 = 0; i2 < 256; i2++) {
            if (r[i2] != 0) {
                for (int b = 1; b <= 6 && i2 + b < 256; b++) {
                    if (r[i2 + b] != 0) {
                        if (r[i2] + (r[i2 + b] << b) <= 15) {
                            r[i2] = (byte) (r[i2] + (r[i2 + b] << b));
                            r[i2 + b] = 0;
                        } else if (r[i2] - (r[i2 + b] << b) >= -15) {
                            r[i2] = (byte) (r[i2] - (r[i2 + b] << b));
                            int k = i2 + b;
                            while (true) {
                                if (k >= 256) {
                                    break;
                                }
                                if (r[k] == 0) {
                                    r[k] = 1;
                                    break;
                                }
                                r[k] = 0;
                                k++;
                            }
                        }
                    }
                }
            }
        }
        return r;
    }

    private static XYZ doubleScalarMultVarTime(byte[] a, XYZT pointA, byte[] b) {
        CachedXYZT[] pointAArray = new CachedXYZT[8];
        pointAArray[0] = new CachedXYZT(pointA);
        PartialXYZT t = new PartialXYZT();
        doubleXYZT(t, pointA);
        XYZT doubleA = new XYZT(t);
        for (int i = 1; i < pointAArray.length; i++) {
            add(t, doubleA, pointAArray[i - 1]);
            pointAArray[i] = new CachedXYZT(new XYZT(t));
        }
        byte[] aSlide = slide(a);
        byte[] bSlide = slide(b);
        PartialXYZT t2 = new PartialXYZT(NEUTRAL);
        XYZT u = new XYZT();
        int i2 = 255;
        while (i2 >= 0 && aSlide[i2] == 0 && bSlide[i2] == 0) {
            i2--;
        }
        while (i2 >= 0) {
            doubleXYZ(t2, new XYZ(t2));
            if (aSlide[i2] > 0) {
                add(t2, XYZT.fromPartialXYZT(u, t2), pointAArray[aSlide[i2] / 2]);
            } else if (aSlide[i2] < 0) {
                sub(t2, XYZT.fromPartialXYZT(u, t2), pointAArray[(-aSlide[i2]) / 2]);
            }
            if (bSlide[i2] > 0) {
                add(t2, XYZT.fromPartialXYZT(u, t2), Ed25519Constants.B2[bSlide[i2] / 2]);
            } else if (bSlide[i2] < 0) {
                sub(t2, XYZT.fromPartialXYZT(u, t2), Ed25519Constants.B2[(-bSlide[i2]) / 2]);
            }
            i2--;
        }
        return new XYZ(t2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isNonZeroVarTime(long[] in) {
        long[] inCopy = new long[in.length + 1];
        System.arraycopy(in, 0, inCopy, 0, in.length);
        Field25519.reduceCoefficients(inCopy);
        byte[] bytes = Field25519.contract(inCopy);
        for (byte b : bytes) {
            if (b != 0) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getLsb(long[] in) {
        return Field25519.contract(in)[0] & 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void neg(long[] out, long[] in) {
        for (int i = 0; i < in.length; i++) {
            out[i] = -in[i];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void pow2252m3(long[] out, long[] in) {
        long[] t0 = new long[10];
        long[] t1 = new long[10];
        long[] t2 = new long[10];
        Field25519.square(t0, in);
        Field25519.square(t1, t0);
        for (int i = 1; i < 2; i++) {
            Field25519.square(t1, t1);
        }
        Field25519.mult(t1, in, t1);
        Field25519.mult(t0, t0, t1);
        Field25519.square(t0, t0);
        Field25519.mult(t0, t1, t0);
        Field25519.square(t1, t0);
        for (int i2 = 1; i2 < 5; i2++) {
            Field25519.square(t1, t1);
        }
        Field25519.mult(t0, t1, t0);
        Field25519.square(t1, t0);
        for (int i3 = 1; i3 < 10; i3++) {
            Field25519.square(t1, t1);
        }
        Field25519.mult(t1, t1, t0);
        Field25519.square(t2, t1);
        for (int i4 = 1; i4 < 20; i4++) {
            Field25519.square(t2, t2);
        }
        Field25519.mult(t1, t2, t1);
        Field25519.square(t1, t1);
        for (int i5 = 1; i5 < 10; i5++) {
            Field25519.square(t1, t1);
        }
        Field25519.mult(t0, t1, t0);
        Field25519.square(t1, t0);
        for (int i6 = 1; i6 < 50; i6++) {
            Field25519.square(t1, t1);
        }
        Field25519.mult(t1, t1, t0);
        Field25519.square(t2, t1);
        for (int i7 = 1; i7 < 100; i7++) {
            Field25519.square(t2, t2);
        }
        Field25519.mult(t1, t2, t1);
        Field25519.square(t1, t1);
        for (int i8 = 1; i8 < 50; i8++) {
            Field25519.square(t1, t1);
        }
        Field25519.mult(t0, t1, t0);
        Field25519.square(t0, t0);
        for (int i9 = 1; i9 < 2; i9++) {
            Field25519.square(t0, t0);
        }
        Field25519.mult(out, t0, in);
    }

    private static long load3(byte[] in, int idx) {
        long result = in[idx] & 255;
        return result | ((in[idx + 1] & UByte.MAX_VALUE) << 8) | ((in[idx + 2] & UByte.MAX_VALUE) << 16);
    }

    private static long load4(byte[] in, int idx) {
        long result = load3(in, idx);
        return result | ((in[idx + 3] & UByte.MAX_VALUE) << 24);
    }

    private static void reduce(byte[] s) {
        long s0 = load3(s, 0) & 2097151;
        long s1 = (load4(s, 2) >> 5) & 2097151;
        long s2 = (load3(s, 5) >> 2) & 2097151;
        long s3 = (load4(s, 7) >> 7) & 2097151;
        long s4 = (load4(s, 10) >> 4) & 2097151;
        long s5 = (load3(s, 13) >> 1) & 2097151;
        long s6 = (load4(s, 15) >> 6) & 2097151;
        long s7 = (load3(s, 18) >> 3) & 2097151;
        long s8 = load3(s, 21) & 2097151;
        long s9 = (load4(s, 23) >> 5) & 2097151;
        long s10 = (load3(s, 26) >> 2) & 2097151;
        long s11 = (load4(s, 28) >> 7) & 2097151;
        long s12 = (load4(s, 31) >> 4) & 2097151;
        long s13 = (load3(s, 34) >> 1) & 2097151;
        long s14 = (load4(s, 36) >> 6) & 2097151;
        long s15 = (load3(s, 39) >> 3) & 2097151;
        long s16 = load3(s, 42) & 2097151;
        long s17 = (load4(s, 44) >> 5) & 2097151;
        long s18 = (load3(s, 47) >> 2) & 2097151;
        long s19 = (load4(s, 49) >> 7) & 2097151;
        long s20 = (load4(s, 52) >> 4) & 2097151;
        long s21 = (load3(s, 55) >> 1) & 2097151;
        long s22 = (load4(s, 57) >> 6) & 2097151;
        long s23 = load4(s, 60) >> 3;
        long s162 = s16 - (s23 * 683901);
        long s142 = ((s14 - (s23 * 997805)) + (s22 * 136657)) - (s21 * 683901);
        long s122 = ((((s12 + (s23 * 470296)) + (s22 * 654183)) - (s21 * 997805)) + (s20 * 136657)) - (s19 * 683901);
        long s62 = s6 + (s18 * 666643);
        long s82 = s8 + (s20 * 666643) + (s19 * 470296) + (s18 * 654183);
        long s102 = ((((s10 + (s22 * 666643)) + (s21 * 470296)) + (s20 * 654183)) - (s19 * 997805)) + (s18 * 136657);
        long carry6 = (s62 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s72 = s7 + (s19 * 666643) + (s18 * 470296) + carry6;
        long carry8 = (s82 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s92 = ((((s9 + (s21 * 666643)) + (s20 * 470296)) + (s19 * 654183)) - (s18 * 997805)) + carry8;
        long carry10 = (s102 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s112 = ((((((s11 + (s23 * 666643)) + (s22 * 470296)) + (s21 * 654183)) - (s20 * 997805)) + (s19 * 136657)) - (s18 * 683901)) + carry10;
        long carry12 = (s122 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s132 = ((((s13 + (s23 * 654183)) - (s22 * 997805)) + (s21 * 136657)) - (s20 * 683901)) + carry12;
        long carry14 = (s142 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s152 = ((s15 + (s23 * 136657)) - (s22 * 683901)) + carry14;
        long carry16 = (s162 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s172 = s17 + carry16;
        long carry7 = (s72 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long carry9 = (s92 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long carry11 = (s112 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s123 = (s122 - (carry12 << 21)) + carry11;
        long carry13 = (s132 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s143 = (s142 - (carry14 << 21)) + carry13;
        long s133 = s132 - (carry13 << 21);
        long carry15 = (s152 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s163 = (s162 - (carry16 << 21)) + carry15;
        long s153 = s152 - (carry15 << 21);
        long s103 = ((s102 - (carry10 << 21)) + carry9) - (s172 * 683901);
        long s83 = ((((s82 - (carry8 << 21)) + carry7) - (s172 * 997805)) + (s163 * 136657)) - (s153 * 683901);
        long s63 = (((((s62 - (carry6 << 21)) + (s172 * 470296)) + (s163 * 654183)) - (s153 * 997805)) + (s143 * 136657)) - (s133 * 683901);
        long s02 = s0 + (s123 * 666643);
        long s24 = s2 + (s143 * 666643) + (s133 * 470296) + (s123 * 654183);
        long s42 = ((((s4 + (s163 * 666643)) + (s153 * 470296)) + (s143 * 654183)) - (s133 * 997805)) + (s123 * 136657);
        long carry0 = (s02 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s110 = s1 + (s133 * 666643) + (s123 * 470296) + carry0;
        long carry2 = (s24 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s32 = ((((s3 + (s153 * 666643)) + (s143 * 470296)) + (s133 * 654183)) - (s123 * 997805)) + carry2;
        long carry4 = (s42 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s52 = ((((((s5 + (s172 * 666643)) + (s163 * 470296)) + (s153 * 654183)) - (s143 * 997805)) + (s133 * 136657)) - (s123 * 683901)) + carry4;
        long carry62 = (s63 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s73 = (((((s72 - (carry7 << 21)) + (s172 * 654183)) - (s163 * 997805)) + (s153 * 136657)) - (s143 * 683901)) + carry62;
        long carry82 = (s83 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s93 = (((s92 - (carry9 << 21)) + (s172 * 136657)) - (s163 * 683901)) + carry82;
        long carry102 = (s103 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s113 = (s112 - (carry11 << 21)) + carry102;
        long carry1 = (s110 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long carry3 = (s32 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long carry5 = (s52 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long carry72 = (s73 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s84 = (s83 - (carry82 << 21)) + carry72;
        long carry92 = (s93 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s104 = (s103 - (carry102 << 21)) + carry92;
        long carry112 = (s113 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s124 = 0 + carry112;
        long s03 = (s02 - (carry0 << 21)) + (s124 * 666643);
        long carry02 = s03 >> 21;
        long s111 = (s110 - (carry1 << 21)) + (s124 * 470296) + carry02;
        long carry17 = s111 >> 21;
        long s25 = (s24 - (carry2 << 21)) + carry1 + (s124 * 654183) + carry17;
        long carry22 = s25 >> 21;
        long s33 = ((s32 - (carry3 << 21)) - (s124 * 997805)) + carry22;
        long carry32 = s33 >> 21;
        long s43 = (s42 - (carry4 << 21)) + carry3 + (s124 * 136657) + carry32;
        long carry42 = s43 >> 21;
        long s53 = ((s52 - (carry5 << 21)) - (s124 * 683901)) + carry42;
        long carry52 = s53 >> 21;
        long s64 = (s63 - (carry62 << 21)) + carry5 + carry52;
        long carry63 = s64 >> 21;
        long s74 = (s73 - (carry72 << 21)) + carry63;
        long carry73 = s74 >> 21;
        long s85 = s84 + carry73;
        long carry83 = s85 >> 21;
        long s94 = (s93 - (carry92 << 21)) + carry83;
        long carry93 = s94 >> 21;
        long s105 = s104 + carry93;
        long carry103 = s105 >> 21;
        long s114 = (s113 - (carry112 << 21)) + carry103;
        long carry113 = s114 >> 21;
        long s125 = 0 + carry113;
        long s04 = (s03 - (carry02 << 21)) + (666643 * s125);
        long carry03 = s04 >> 21;
        long s115 = (s111 - (carry17 << 21)) + (470296 * s125) + carry03;
        long carry18 = s115 >> 21;
        long s26 = (s25 - (carry22 << 21)) + (654183 * s125) + carry18;
        long s116 = s115 - (carry18 << 21);
        long carry23 = s26 >> 21;
        long s34 = ((s33 - (carry32 << 21)) - (997805 * s125)) + carry23;
        long s27 = s26 - (carry23 << 21);
        long carry33 = s34 >> 21;
        long s44 = (s43 - (carry42 << 21)) + (136657 * s125) + carry33;
        long s35 = s34 - (carry33 << 21);
        long carry43 = s44 >> 21;
        long s54 = ((s53 - (carry52 << 21)) - (683901 * s125)) + carry43;
        long s45 = s44 - (carry43 << 21);
        long carry53 = s54 >> 21;
        long s65 = (s64 - (carry63 << 21)) + carry53;
        long s55 = s54 - (carry53 << 21);
        long carry64 = s65 >> 21;
        long s75 = (s74 - (carry73 << 21)) + carry64;
        long s66 = s65 - (carry64 << 21);
        long carry74 = s75 >> 21;
        long s86 = (s85 - (carry83 << 21)) + carry74;
        long s76 = s75 - (carry74 << 21);
        long carry84 = s86 >> 21;
        long s95 = (s94 - (carry93 << 21)) + carry84;
        long s87 = s86 - (carry84 << 21);
        long s88 = s95 >> 21;
        long s106 = (s105 - (carry103 << 21)) + s88;
        long s96 = s95 - (s88 << 21);
        long carry104 = s106 >> 21;
        long s117 = (s114 - (carry113 << 21)) + carry104;
        long s107 = s106 - (carry104 << 21);
        s[0] = (byte) (s04 - (carry03 << 21));
        s[1] = (byte) (r1 >> 8);
        s[2] = (byte) ((r1 >> 16) | (s116 << 5));
        s[3] = (byte) (s116 >> 3);
        s[4] = (byte) (s116 >> 11);
        s[5] = (byte) ((s116 >> 19) | (s27 << 2));
        s[6] = (byte) (s27 >> 6);
        s[7] = (byte) ((s27 >> 14) | (s35 << 7));
        s[8] = (byte) (s35 >> 1);
        s[9] = (byte) (s35 >> 9);
        s[10] = (byte) ((s35 >> 17) | (s45 << 4));
        s[11] = (byte) (s45 >> 4);
        s[12] = (byte) (s45 >> 12);
        s[13] = (byte) ((s45 >> 20) | (s55 << 1));
        s[14] = (byte) (s55 >> 7);
        s[15] = (byte) ((s55 >> 15) | (s66 << 6));
        s[16] = (byte) (s66 >> 2);
        s[17] = (byte) (s66 >> 10);
        s[18] = (byte) ((s66 >> 18) | (s76 << 3));
        s[19] = (byte) (s76 >> 5);
        s[20] = (byte) (s76 >> 13);
        s[21] = (byte) s87;
        s[22] = (byte) (s87 >> 8);
        s[23] = (byte) ((s87 >> 16) | (s96 << 5));
        s[24] = (byte) (s96 >> 3);
        s[25] = (byte) (s96 >> 11);
        s[26] = (byte) ((s96 >> 19) | (s107 << 2));
        s[27] = (byte) (s107 >> 6);
        s[28] = (byte) ((s107 >> 14) | (s117 << 7));
        s[29] = (byte) (s117 >> 1);
        s[30] = (byte) (s117 >> 9);
        s[31] = (byte) (s117 >> 17);
    }

    private static void mulAdd(byte[] s, byte[] a, byte[] b, byte[] c) {
        long a0 = load3(a, 0) & 2097151;
        long a1 = (load4(a, 2) >> 5) & 2097151;
        long a2 = (load3(a, 5) >> 2) & 2097151;
        long a3 = (load4(a, 7) >> 7) & 2097151;
        long a4 = (load4(a, 10) >> 4) & 2097151;
        long a5 = (load3(a, 13) >> 1) & 2097151;
        long a6 = (load4(a, 15) >> 6) & 2097151;
        long a7 = (load3(a, 18) >> 3) & 2097151;
        long a8 = load3(a, 21) & 2097151;
        long a9 = (load4(a, 23) >> 5) & 2097151;
        long a10 = (load3(a, 26) >> 2) & 2097151;
        long a11 = load4(a, 28) >> 7;
        long b0 = load3(b, 0) & 2097151;
        long b1 = (load4(b, 2) >> 5) & 2097151;
        long b2 = (load3(b, 5) >> 2) & 2097151;
        long b3 = (load4(b, 7) >> 7) & 2097151;
        long b4 = (load4(b, 10) >> 4) & 2097151;
        long b5 = (load3(b, 13) >> 1) & 2097151;
        long b6 = (load4(b, 15) >> 6) & 2097151;
        long b7 = (load3(b, 18) >> 3) & 2097151;
        long b8 = load3(b, 21) & 2097151;
        long b9 = (load4(b, 23) >> 5) & 2097151;
        long b10 = (load3(b, 26) >> 2) & 2097151;
        long b11 = load4(b, 28) >> 7;
        long c0 = load3(c, 0) & 2097151;
        long c1 = (load4(c, 2) >> 5) & 2097151;
        long c2 = (load3(c, 5) >> 2) & 2097151;
        long c3 = (load4(c, 7) >> 7) & 2097151;
        long c4 = (load4(c, 10) >> 4) & 2097151;
        long c5 = (load3(c, 13) >> 1) & 2097151;
        long c6 = (load4(c, 15) >> 6) & 2097151;
        long c7 = (load3(c, 18) >> 3) & 2097151;
        long c8 = load3(c, 21) & 2097151;
        long c9 = (load4(c, 23) >> 5) & 2097151;
        long c10 = (load3(c, 26) >> 2) & 2097151;
        long c11 = load4(c, 28) >> 7;
        long s0 = c0 + (a0 * b0);
        long s2 = c2 + (a0 * b2) + (a1 * b1) + (a2 * b0);
        long s4 = c4 + (a0 * b4) + (a1 * b3) + (a2 * b2) + (a3 * b1) + (a4 * b0);
        long s6 = c6 + (a0 * b6) + (a1 * b5) + (a2 * b4) + (a3 * b3) + (a4 * b2) + (a5 * b1) + (a6 * b0);
        long s8 = c8 + (a0 * b8) + (a1 * b7) + (a2 * b6) + (a3 * b5) + (a4 * b4) + (a5 * b3) + (a6 * b2) + (a7 * b1) + (a8 * b0);
        long s10 = c10 + (a0 * b10) + (a1 * b9) + (a2 * b8) + (a3 * b7) + (a4 * b6) + (a5 * b5) + (a6 * b4) + (a7 * b3) + (a8 * b2) + (a9 * b1) + (a10 * b0);
        long s12 = (a1 * b11) + (a2 * b10) + (a3 * b9) + (a4 * b8) + (a5 * b7) + (a6 * b6) + (a7 * b5) + (a8 * b4) + (a9 * b3) + (a10 * b2) + (a11 * b1);
        long s14 = (a3 * b11) + (a4 * b10) + (a5 * b9) + (a6 * b8) + (a7 * b7) + (a8 * b6) + (a9 * b5) + (a10 * b4) + (a11 * b3);
        long s16 = (a5 * b11) + (a6 * b10) + (a7 * b9) + (a8 * b8) + (a9 * b7) + (a10 * b6) + (a11 * b5);
        long s18 = (a7 * b11) + (a8 * b10) + (a9 * b9) + (a10 * b8) + (a11 * b7);
        long s20 = (a9 * b11) + (a10 * b10) + (a11 * b9);
        long s22 = a11 * b11;
        long carry0 = (s0 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s1 = c1 + (a0 * b1) + (a1 * b0) + carry0;
        long carry2 = (s2 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s3 = c3 + (a0 * b3) + (a1 * b2) + (a2 * b1) + (a3 * b0) + carry2;
        long carry4 = (s4 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s5 = c5 + (a0 * b5) + (a1 * b4) + (a2 * b3) + (a3 * b2) + (a4 * b1) + (a5 * b0) + carry4;
        long carry6 = (s6 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s7 = c7 + (a0 * b7) + (a1 * b6) + (a2 * b5) + (a3 * b4) + (a4 * b3) + (a5 * b2) + (a6 * b1) + (a7 * b0) + carry6;
        long carry8 = (s8 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s9 = c9 + (a0 * b9) + (a1 * b8) + (a2 * b7) + (a3 * b6) + (a4 * b5) + (a5 * b4) + (a6 * b3) + (a7 * b2) + (a8 * b1) + (a9 * b0) + carry8;
        long carry10 = (s10 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s11 = c11 + (a0 * b11) + (a1 * b10) + (a2 * b9) + (a3 * b8) + (a4 * b7) + (a5 * b6) + (a6 * b5) + (a7 * b4) + (a8 * b3) + (a9 * b2) + (a10 * b1) + (a11 * b0) + carry10;
        long carry12 = (s12 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s13 = (a2 * b11) + (a3 * b10) + (a4 * b9) + (a5 * b8) + (a6 * b7) + (a7 * b6) + (a8 * b5) + (a9 * b4) + (a10 * b3) + (a11 * b2) + carry12;
        long carry14 = (s14 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s15 = (a4 * b11) + (a5 * b10) + (a6 * b9) + (a7 * b8) + (a8 * b7) + (a9 * b6) + (a10 * b5) + (a11 * b4) + carry14;
        long carry16 = (s16 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s17 = (a6 * b11) + (a7 * b10) + (a8 * b9) + (a9 * b8) + (a10 * b7) + (a11 * b6) + carry16;
        long carry18 = (s18 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s19 = (a8 * b11) + (a9 * b10) + (a10 * b9) + (a11 * b8) + carry18;
        long carry20 = (s20 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s21 = (a10 * b11) + (a11 * b10) + carry20;
        long carry22 = (s22 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s23 = 0 + carry22;
        long carry1 = (s1 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long carry3 = (s3 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long carry5 = (s5 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long carry7 = (s7 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long carry9 = (s9 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long carry11 = (s11 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long carry13 = (s13 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long carry15 = (s15 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long carry17 = (s17 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s182 = (s18 - (carry18 << 21)) + carry17;
        long carry19 = (s19 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s202 = (s20 - (carry20 << 21)) + carry19;
        long s192 = s19 - (carry19 << 21);
        long carry21 = (s21 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s222 = (s22 - (carry22 << 21)) + carry21;
        long s212 = s21 - (carry21 << 21);
        long s162 = ((s16 - (carry16 << 21)) + carry15) - (s23 * 683901);
        long s142 = ((((s14 - (carry14 << 21)) + carry13) - (s23 * 997805)) + (s222 * 136657)) - (s212 * 683901);
        long s122 = ((((((s12 - (carry12 << 21)) + carry11) + (s23 * 470296)) + (s222 * 654183)) - (s212 * 997805)) + (s202 * 136657)) - (s192 * 683901);
        long s62 = (s6 - (carry6 << 21)) + carry5 + (s182 * 666643);
        long s82 = (s8 - (carry8 << 21)) + carry7 + (s202 * 666643) + (s192 * 470296) + (s182 * 654183);
        long s102 = ((((((s10 - (carry10 << 21)) + carry9) + (s222 * 666643)) + (s212 * 470296)) + (s202 * 654183)) - (s192 * 997805)) + (s182 * 136657);
        long carry62 = (s62 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s72 = (s7 - (carry7 << 21)) + (s192 * 666643) + (s182 * 470296) + carry62;
        long carry82 = (s82 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s92 = (((((s9 - (carry9 << 21)) + (s212 * 666643)) + (s202 * 470296)) + (s192 * 654183)) - (s182 * 997805)) + carry82;
        long carry102 = (s102 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s112 = (((((((s11 - (carry11 << 21)) + (s23 * 666643)) + (s222 * 470296)) + (s212 * 654183)) - (s202 * 997805)) + (s192 * 136657)) - (s182 * 683901)) + carry102;
        long carry122 = (s122 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s132 = (((((s13 - (carry13 << 21)) + (s23 * 654183)) - (s222 * 997805)) + (s212 * 136657)) - (s202 * 683901)) + carry122;
        long carry142 = (s142 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s152 = (((s15 - (carry15 << 21)) + (s23 * 136657)) - (s222 * 683901)) + carry142;
        long carry162 = (s162 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s172 = (s17 - (carry17 << 21)) + carry162;
        long carry72 = (s72 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long carry92 = (s92 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long carry112 = (s112 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s123 = (s122 - (carry122 << 21)) + carry112;
        long carry132 = (s132 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s143 = (s142 - (carry142 << 21)) + carry132;
        long s133 = s132 - (carry132 << 21);
        long carry152 = (s152 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s163 = (s162 - (carry162 << 21)) + carry152;
        long s153 = s152 - (carry152 << 21);
        long s103 = ((s102 - (carry102 << 21)) + carry92) - (s172 * 683901);
        long s83 = ((((s82 - (carry82 << 21)) + carry72) - (s172 * 997805)) + (s163 * 136657)) - (s153 * 683901);
        long s63 = (((((s62 - (carry62 << 21)) + (s172 * 470296)) + (s163 * 654183)) - (s153 * 997805)) + (s143 * 136657)) - (s133 * 683901);
        long s02 = (s0 - (carry0 << 21)) + (s123 * 666643);
        long s24 = (s2 - (carry2 << 21)) + carry1 + (s143 * 666643) + (s133 * 470296) + (s123 * 654183);
        long s42 = ((((((s4 - (carry4 << 21)) + carry3) + (s163 * 666643)) + (s153 * 470296)) + (s143 * 654183)) - (s133 * 997805)) + (s123 * 136657);
        long carry02 = (s02 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s110 = (s1 - (carry1 << 21)) + (s133 * 666643) + (s123 * 470296) + carry02;
        long carry23 = (s24 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s32 = (((((s3 - (carry3 << 21)) + (s153 * 666643)) + (s143 * 470296)) + (s133 * 654183)) - (s123 * 997805)) + carry23;
        long carry42 = (s42 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s52 = (((((((s5 - (carry5 << 21)) + (s172 * 666643)) + (s163 * 470296)) + (s153 * 654183)) - (s143 * 997805)) + (s133 * 136657)) - (s123 * 683901)) + carry42;
        long carry63 = (s63 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s73 = (((((s72 - (carry72 << 21)) + (s172 * 654183)) - (s163 * 997805)) + (s153 * 136657)) - (s143 * 683901)) + carry63;
        long carry83 = (s83 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s93 = (((s92 - (carry92 << 21)) + (s172 * 136657)) - (s163 * 683901)) + carry83;
        long carry103 = (s103 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s113 = (s112 - (carry112 << 21)) + carry103;
        long carry110 = (s110 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long carry32 = (s32 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long carry52 = (s52 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s64 = (s63 - (carry63 << 21)) + carry52;
        long carry73 = (s73 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s84 = (s83 - (carry83 << 21)) + carry73;
        long carry93 = (s93 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s104 = (s103 - (carry103 << 21)) + carry93;
        long carry113 = (s113 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long s124 = 0 + carry113;
        long s03 = (s02 - (carry02 << 21)) + (s124 * 666643);
        long s25 = (s24 - (carry23 << 21)) + carry110 + (s124 * 654183);
        long s43 = (s42 - (carry42 << 21)) + carry32 + (s124 * 136657);
        long carry03 = s03 >> 21;
        long s111 = (s110 - (carry110 << 21)) + (s124 * 470296) + carry03;
        long carry111 = s111 >> 21;
        long s26 = s25 + carry111;
        long carry24 = s26 >> 21;
        long s33 = ((s32 - (carry32 << 21)) - (s124 * 997805)) + carry24;
        long carry33 = s33 >> 21;
        long s44 = s43 + carry33;
        long carry43 = s44 >> 21;
        long s53 = ((s52 - (carry52 << 21)) - (s124 * 683901)) + carry43;
        long carry53 = s53 >> 21;
        long s65 = s64 + carry53;
        long carry64 = s65 >> 21;
        long s74 = (s73 - (carry73 << 21)) + carry64;
        long carry74 = s74 >> 21;
        long s85 = s84 + carry74;
        long carry84 = s85 >> 21;
        long s94 = (s93 - (carry93 << 21)) + carry84;
        long carry94 = s94 >> 21;
        long s105 = s104 + carry94;
        long carry104 = s105 >> 21;
        long s114 = (s113 - (carry113 << 21)) + carry104;
        long carry114 = s114 >> 21;
        long s125 = 0 + carry114;
        long s04 = (s03 - (carry03 << 21)) + (666643 * s125);
        long carry04 = s04 >> 21;
        long s115 = (s111 - (carry111 << 21)) + (470296 * s125) + carry04;
        long s05 = s04 - (carry04 << 21);
        long s06 = s115 >> 21;
        long s27 = (s26 - (carry24 << 21)) + (654183 * s125) + s06;
        long s116 = s115 - (s06 << 21);
        long carry25 = s27 >> 21;
        long s34 = ((s33 - (carry33 << 21)) - (997805 * s125)) + carry25;
        long s28 = s27 - (carry25 << 21);
        long carry34 = s34 >> 21;
        long s45 = (s44 - (carry43 << 21)) + (136657 * s125) + carry34;
        long s35 = s34 - (carry34 << 21);
        long carry44 = s45 >> 21;
        long s54 = ((s53 - (carry53 << 21)) - (683901 * s125)) + carry44;
        long s46 = s45 - (carry44 << 21);
        long carry54 = s54 >> 21;
        long s66 = (s65 - (carry64 << 21)) + carry54;
        long s55 = s54 - (carry54 << 21);
        long carry65 = s66 >> 21;
        long s75 = (s74 - (carry74 << 21)) + carry65;
        long s67 = s66 - (carry65 << 21);
        long carry75 = s75 >> 21;
        long s86 = (s85 - (carry84 << 21)) + carry75;
        long s76 = s75 - (carry75 << 21);
        long carry85 = s86 >> 21;
        long s95 = (s94 - (carry94 << 21)) + carry85;
        long s87 = s86 - (carry85 << 21);
        long s88 = s95 >> 21;
        long s106 = (s105 - (carry104 << 21)) + s88;
        long s96 = s95 - (s88 << 21);
        long carry105 = s106 >> 21;
        long s117 = (s114 - (carry114 << 21)) + carry105;
        long s107 = s106 - (carry105 << 21);
        s[0] = (byte) s05;
        s[1] = (byte) (s05 >> 8);
        s[2] = (byte) ((s05 >> 16) | (s116 << 5));
        s[3] = (byte) (s116 >> 3);
        s[4] = (byte) (s116 >> 11);
        s[5] = (byte) ((s116 >> 19) | (s28 << 2));
        s[6] = (byte) (s28 >> 6);
        s[7] = (byte) ((s28 >> 14) | (s35 << 7));
        s[8] = (byte) (s35 >> 1);
        s[9] = (byte) (s35 >> 9);
        s[10] = (byte) ((s35 >> 17) | (s46 << 4));
        s[11] = (byte) (s46 >> 4);
        s[12] = (byte) (s46 >> 12);
        s[13] = (byte) ((s46 >> 20) | (s55 << 1));
        s[14] = (byte) (s55 >> 7);
        s[15] = (byte) ((s55 >> 15) | (s67 << 6));
        s[16] = (byte) (s67 >> 2);
        s[17] = (byte) (s67 >> 10);
        s[18] = (byte) ((s67 >> 18) | (s76 << 3));
        s[19] = (byte) (s76 >> 5);
        s[20] = (byte) (s76 >> 13);
        s[21] = (byte) s87;
        s[22] = (byte) (s87 >> 8);
        s[23] = (byte) ((s87 >> 16) | (s96 << 5));
        s[24] = (byte) (s96 >> 3);
        s[25] = (byte) (s96 >> 11);
        s[26] = (byte) ((s96 >> 19) | (s107 << 2));
        s[27] = (byte) (s107 >> 6);
        s[28] = (byte) ((s107 >> 14) | (s117 << 7));
        s[29] = (byte) (s117 >> 1);
        s[30] = (byte) (s117 >> 9);
        s[31] = (byte) (s117 >> 17);
    }

    static byte[] getHashedScalar(final byte[] privateKey) throws GeneralSecurityException {
        MessageDigest digest = EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        digest.update(privateKey, 0, 32);
        byte[] h = digest.digest();
        h[0] = (byte) (h[0] & 248);
        h[31] = (byte) (h[31] & ByteCompanionObject.MAX_VALUE);
        h[31] = (byte) (h[31] | 64);
        return h;
    }

    static byte[] sign(final byte[] message, final byte[] publicKey, final byte[] hashedPrivateKey) throws GeneralSecurityException {
        byte[] messageCopy = Arrays.copyOfRange(message, 0, message.length);
        MessageDigest digest = EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        digest.update(hashedPrivateKey, 32, 32);
        digest.update(messageCopy);
        byte[] r = digest.digest();
        reduce(r);
        byte[] rB = Arrays.copyOfRange(scalarMultWithBase(r).toBytes(), 0, 32);
        digest.reset();
        digest.update(rB);
        digest.update(publicKey);
        digest.update(messageCopy);
        byte[] hram = digest.digest();
        reduce(hram);
        byte[] s = new byte[32];
        mulAdd(s, hram, hashedPrivateKey, r);
        return Bytes.concat(rB, s);
    }

    private static boolean isSmallerThanGroupOrder(byte[] s) {
        for (int j = 31; j >= 0; j--) {
            int a = s[j] & 255;
            int b = GROUP_ORDER[j] & 255;
            if (a != b) {
                return a < b;
            }
        }
        return false;
    }

    static boolean verify(final byte[] message, final byte[] signature, final byte[] publicKey) throws GeneralSecurityException {
        if (signature.length != 64) {
            return false;
        }
        byte[] s = Arrays.copyOfRange(signature, 32, 64);
        if (!isSmallerThanGroupOrder(s)) {
            return false;
        }
        MessageDigest digest = EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        digest.update(signature, 0, 32);
        digest.update(publicKey);
        digest.update(message);
        byte[] h = digest.digest();
        reduce(h);
        XYZT negPublicKey = XYZT.fromBytesNegateVarTime(publicKey);
        XYZ xyz = doubleScalarMultVarTime(h, negPublicKey, s);
        byte[] expectedR = xyz.toBytes();
        for (int i = 0; i < 32; i++) {
            if (expectedR[i] != signature[i]) {
                return false;
            }
        }
        return true;
    }
}
