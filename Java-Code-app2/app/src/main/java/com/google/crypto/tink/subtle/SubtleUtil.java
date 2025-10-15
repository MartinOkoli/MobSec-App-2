package com.google.crypto.tink.subtle;

import com.google.crypto.tink.subtle.Enums;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\SubtleUtil.smali */
public class SubtleUtil {
    public static String toEcdsaAlgo(Enums.HashType hash) throws GeneralSecurityException {
        Validators.validateSignatureHash(hash);
        return hash + "withECDSA";
    }

    public static String toRsaSsaPkcs1Algo(Enums.HashType hash) throws GeneralSecurityException {
        Validators.validateSignatureHash(hash);
        return hash + "withRSA";
    }

    /* renamed from: com.google.crypto.tink.subtle.SubtleUtil$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\SubtleUtil$1.smali */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType;

        static {
            int[] iArr = new int[Enums.HashType.values().length];
            $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType = iArr;
            try {
                iArr[Enums.HashType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[Enums.HashType.SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[Enums.HashType.SHA384.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[Enums.HashType.SHA512.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public static String toDigestAlgo(Enums.HashType hash) throws GeneralSecurityException {
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[hash.ordinal()];
        if (i == 1) {
            return "SHA-1";
        }
        if (i == 2) {
            return "SHA-256";
        }
        if (i == 3) {
            return "SHA-384";
        }
        if (i == 4) {
            return "SHA-512";
        }
        throw new GeneralSecurityException("Unsupported hash " + hash);
    }

    public static boolean isAndroid() {
        try {
            Class.forName("android.app.Application", false, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static int androidApiLevel() {
        try {
            Class<?> buildVersion = Class.forName("android.os.Build$VERSION");
            return buildVersion.getDeclaredField("SDK_INT").getInt(null);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            return -1;
        }
    }

    public static BigInteger bytes2Integer(byte[] bs) {
        return new BigInteger(1, bs);
    }

    public static byte[] integer2Bytes(BigInteger num, int intendedLength) throws GeneralSecurityException {
        byte[] b = num.toByteArray();
        if (b.length == intendedLength) {
            return b;
        }
        if (b.length > intendedLength + 1) {
            throw new GeneralSecurityException("integer too large");
        }
        if (b.length == intendedLength + 1) {
            if (b[0] == 0) {
                return Arrays.copyOfRange(b, 1, b.length);
            }
            throw new GeneralSecurityException("integer too large");
        }
        byte[] res = new byte[intendedLength];
        System.arraycopy(b, 0, res, intendedLength - b.length, b.length);
        return res;
    }

    public static byte[] mgf1(byte[] mgfSeed, int maskLen, Enums.HashType mgfHash) throws GeneralSecurityException {
        MessageDigest digest = EngineFactory.MESSAGE_DIGEST.getInstance(toDigestAlgo(mgfHash));
        int hLen = digest.getDigestLength();
        byte[] t = new byte[maskLen];
        int tPos = 0;
        for (int counter = 0; counter <= (maskLen - 1) / hLen; counter++) {
            digest.reset();
            digest.update(mgfSeed);
            digest.update(integer2Bytes(BigInteger.valueOf(counter), 4));
            byte[] c = digest.digest();
            System.arraycopy(c, 0, t, tPos, Math.min(c.length, t.length - tPos));
            tPos += c.length;
        }
        return t;
    }

    public static void putAsUnsigedInt(ByteBuffer buffer, long value) throws GeneralSecurityException {
        if (0 > value || value >= 4294967296L) {
            throw new GeneralSecurityException("Index out of range");
        }
        buffer.putInt((int) value);
    }
}
