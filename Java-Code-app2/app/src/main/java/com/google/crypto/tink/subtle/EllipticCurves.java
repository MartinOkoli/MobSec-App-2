package com.google.crypto.tink.subtle;

import androidx.exifinterface.media.ExifInterface;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECField;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import javax.crypto.KeyAgreement;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\EllipticCurves.smali */
public final class EllipticCurves {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\EllipticCurves$CurveType.smali */
    public enum CurveType {
        NIST_P256,
        NIST_P384,
        NIST_P521
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\EllipticCurves$EcdsaEncoding.smali */
    public enum EcdsaEncoding {
        IEEE_P1363,
        DER
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\EllipticCurves$PointFormatType.smali */
    public enum PointFormatType {
        UNCOMPRESSED,
        COMPRESSED,
        DO_NOT_USE_CRUNCHY_UNCOMPRESSED
    }

    public static ECParameterSpec getNistP256Params() {
        return getNistCurveSpec("115792089210356248762697446949407573530086143415290314195533631308867097853951", "115792089210356248762697446949407573529996955224135760342422259061068512044369", "5ac635d8aa3a93e7b3ebbd55769886bc651d06b0cc53b0f63bce3c3e27d2604b", "6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296", "4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5");
    }

    public static ECParameterSpec getNistP384Params() {
        return getNistCurveSpec("39402006196394479212279040100143613805079739270465446667948293404245721771496870329047266088258938001861606973112319", "39402006196394479212279040100143613805079739270465446667946905279627659399113263569398956308152294913554433653942643", "b3312fa7e23ee7e4988e056be3f82d19181d9c6efe8141120314088f5013875ac656398d8a2ed19d2a85c8edd3ec2aef", "aa87ca22be8b05378eb1c71ef320ad746e1d3b628ba79b9859f741e082542a385502f25dbf55296c3a545e3872760ab7", "3617de4a96262c6f5d9e98bf9292dc29f8f41dbd289a147ce9da3113b5f0b8c00a60b1ce1d7e819d7a431d7c90ea0e5f");
    }

    public static ECParameterSpec getNistP521Params() {
        return getNistCurveSpec("6864797660130609714981900799081393217269435300143305409394463459185543183397656052122559640661454554977296311391480858037121987999716643812574028291115057151", "6864797660130609714981900799081393217269435300143305409394463459185543183397655394245057746333217197532963996371363321113864768612440380340372808892707005449", "051953eb9618e1c9a1f929a21a0b68540eea2da725b99b315f3b8b489918ef109e156193951ec7e937b1652c0bd3bb1bf073573df883d2c34f1ef451fd46b503f00", "c6858e06b70404e9cd9e3ecb662395b4429c648139053fb521f828af606b4d3dbaa14b5e77efe75928fe1dc127a2ffa8de3348b3c1856a429bf97e7e31c2e5bd66", "11839296a789a3bc0045c8a5fb42c7d1bd998f54449579b446817afbd17273e662c97ee72995ef42640c550b9013fad0761353c7086a272c24088be94769fd16650");
    }

    static void checkPointOnCurve(ECPoint point, EllipticCurve ec) throws GeneralSecurityException {
        BigInteger p = getModulus(ec);
        BigInteger x = point.getAffineX();
        BigInteger y = point.getAffineY();
        if (x == null || y == null) {
            throw new GeneralSecurityException("point is at infinity");
        }
        if (x.signum() == -1 || x.compareTo(p) >= 0) {
            throw new GeneralSecurityException("x is out of range");
        }
        if (y.signum() == -1 || y.compareTo(p) >= 0) {
            throw new GeneralSecurityException("y is out of range");
        }
        BigInteger lhs = y.multiply(y).mod(p);
        BigInteger rhs = x.multiply(x).add(ec.getA()).multiply(x).add(ec.getB()).mod(p);
        if (!lhs.equals(rhs)) {
            throw new GeneralSecurityException("Point is not on curve");
        }
    }

    static void checkPublicKey(ECPublicKey key) throws GeneralSecurityException {
        checkPointOnCurve(key.getW(), key.getParams().getCurve());
    }

    public static boolean isNistEcParameterSpec(ECParameterSpec spec) {
        return isSameEcParameterSpec(spec, getNistP256Params()) || isSameEcParameterSpec(spec, getNistP384Params()) || isSameEcParameterSpec(spec, getNistP521Params());
    }

    public static boolean isSameEcParameterSpec(ECParameterSpec one, ECParameterSpec two) {
        return one.getCurve().equals(two.getCurve()) && one.getGenerator().equals(two.getGenerator()) && one.getOrder().equals(two.getOrder()) && one.getCofactor() == two.getCofactor();
    }

    public static void validatePublicKey(ECPublicKey publicKey, ECPrivateKey privateKey) throws GeneralSecurityException {
        validatePublicKeySpec(publicKey, privateKey);
        checkPointOnCurve(publicKey.getW(), privateKey.getParams().getCurve());
    }

    static void validatePublicKeySpec(ECPublicKey publicKey, ECPrivateKey privateKey) throws GeneralSecurityException {
        try {
            ECParameterSpec publicKeySpec = publicKey.getParams();
            ECParameterSpec privateKeySpec = privateKey.getParams();
            if (!isSameEcParameterSpec(publicKeySpec, privateKeySpec)) {
                throw new GeneralSecurityException("invalid public key spec");
            }
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new GeneralSecurityException(ex.toString());
        }
    }

    public static BigInteger getModulus(EllipticCurve curve) throws GeneralSecurityException {
        ECField field = curve.getField();
        if (field instanceof ECFieldFp) {
            return ((ECFieldFp) field).getP();
        }
        throw new GeneralSecurityException("Only curves over prime order fields are supported");
    }

    static int fieldSizeInBits(EllipticCurve curve) throws GeneralSecurityException {
        return getModulus(curve).subtract(BigInteger.ONE).bitLength();
    }

    public static int fieldSizeInBytes(EllipticCurve curve) throws GeneralSecurityException {
        return (fieldSizeInBits(curve) + 7) / 8;
    }

    private static ECParameterSpec getNistCurveSpec(String decimalP, String decimalN, String hexB, String hexGX, String hexGY) {
        BigInteger p = new BigInteger(decimalP);
        BigInteger n = new BigInteger(decimalN);
        BigInteger three = new BigInteger(ExifInterface.GPS_MEASUREMENT_3D);
        BigInteger a = p.subtract(three);
        BigInteger b = new BigInteger(hexB, 16);
        BigInteger gx = new BigInteger(hexGX, 16);
        BigInteger gy = new BigInteger(hexGY, 16);
        ECFieldFp fp = new ECFieldFp(p);
        EllipticCurve curveSpec = new EllipticCurve(fp, a, b);
        ECPoint g = new ECPoint(gx, gy);
        ECParameterSpec ecSpec = new ECParameterSpec(curveSpec, g, n, 1);
        return ecSpec;
    }

    protected static BigInteger modSqrt(BigInteger x, BigInteger p) throws GeneralSecurityException {
        if (p.signum() != 1) {
            throw new InvalidAlgorithmParameterException("p must be positive");
        }
        BigInteger x2 = x.mod(p);
        BigInteger squareRoot = null;
        if (x2.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        }
        if (!p.testBit(0) || !p.testBit(1)) {
            if (p.testBit(0) && !p.testBit(1)) {
                BigInteger a = BigInteger.ONE;
                BigInteger q1 = p.subtract(BigInteger.ONE).shiftRight(1);
                int tries = 0;
                while (true) {
                    BigInteger d = a.multiply(a).subtract(x2).mod(p);
                    if (d.equals(BigInteger.ZERO)) {
                        return a;
                    }
                    BigInteger t = d.modPow(q1, p);
                    if (!t.add(BigInteger.ONE).equals(p)) {
                        BigInteger v = BigInteger.ONE;
                        if (!t.equals(v)) {
                            throw new InvalidAlgorithmParameterException("p is not prime");
                        }
                        a = a.add(BigInteger.ONE);
                        tries++;
                        if (tries == 128 && !p.isProbablePrime(80)) {
                            throw new InvalidAlgorithmParameterException("p is not prime");
                        }
                    } else {
                        BigInteger q = p.add(BigInteger.ONE).shiftRight(1);
                        BigInteger u = a;
                        BigInteger v2 = BigInteger.ONE;
                        for (int bit = q.bitLength() - 2; bit >= 0; bit--) {
                            BigInteger tmp = u.multiply(v2);
                            u = u.multiply(u).add(v2.multiply(v2).mod(p).multiply(d)).mod(p);
                            v2 = tmp.add(tmp).mod(p);
                            if (q.testBit(bit)) {
                                BigInteger tmp2 = u.multiply(a).add(v2.multiply(d)).mod(p);
                                v2 = a.multiply(v2).add(u).mod(p);
                                u = tmp2;
                            }
                        }
                        squareRoot = u;
                    }
                }
            }
        } else {
            squareRoot = x2.modPow(p.add(BigInteger.ONE).shiftRight(2), p);
        }
        if (squareRoot != null && squareRoot.multiply(squareRoot).mod(p).compareTo(x2) != 0) {
            throw new GeneralSecurityException("Could not find a modular square root");
        }
        return squareRoot;
    }

    public static BigInteger getY(BigInteger x, boolean lsb, EllipticCurve curve) throws GeneralSecurityException {
        BigInteger p = getModulus(curve);
        BigInteger a = curve.getA();
        BigInteger b = curve.getB();
        BigInteger rhs = x.multiply(x).add(a).multiply(x).add(b).mod(p);
        BigInteger y = modSqrt(rhs, p);
        if (lsb != y.testBit(0)) {
            return p.subtract(y).mod(p);
        }
        return y;
    }

    private static byte[] toMinimalSignedNumber(byte[] bs) {
        int start = 0;
        while (start < bs.length && bs[start] == 0) {
            start++;
        }
        if (start == bs.length) {
            start = bs.length - 1;
        }
        int extraZero = 0;
        if ((bs[start] & ByteCompanionObject.MIN_VALUE) == 128) {
            extraZero = 1;
        }
        byte[] res = new byte[(bs.length - start) + extraZero];
        System.arraycopy(bs, start, res, extraZero, bs.length - start);
        return res;
    }

    public static byte[] ecdsaIeee2Der(byte[] ieee) throws GeneralSecurityException {
        byte[] der;
        int offset;
        if (ieee.length % 2 != 0 || ieee.length == 0 || ieee.length > 132) {
            throw new GeneralSecurityException("Invalid IEEE_P1363 encoding");
        }
        byte[] r = toMinimalSignedNumber(Arrays.copyOf(ieee, ieee.length / 2));
        byte[] s = toMinimalSignedNumber(Arrays.copyOfRange(ieee, ieee.length / 2, ieee.length));
        int length = r.length + 2 + 1 + 1 + s.length;
        if (length >= 128) {
            der = new byte[length + 3];
            int offset2 = 0 + 1;
            der[0] = 48;
            int offset3 = offset2 + 1;
            der[offset2] = -127;
            offset = offset3 + 1;
            der[offset3] = (byte) length;
        } else {
            der = new byte[length + 2];
            int offset4 = 0 + 1;
            der[0] = 48;
            offset = offset4 + 1;
            der[offset4] = (byte) length;
        }
        int offset5 = offset + 1;
        der[offset] = 2;
        int offset6 = offset5 + 1;
        der[offset5] = (byte) r.length;
        System.arraycopy(r, 0, der, offset6, r.length);
        int offset7 = offset6 + r.length;
        int offset8 = offset7 + 1;
        der[offset7] = 2;
        der[offset8] = (byte) s.length;
        System.arraycopy(s, 0, der, offset8 + 1, s.length);
        return der;
    }

    public static byte[] ecdsaDer2Ieee(byte[] der, int ieeeLength) throws GeneralSecurityException {
        if (!isValidDerEncoding(der)) {
            throw new GeneralSecurityException("Invalid DER encoding");
        }
        byte[] ieee = new byte[ieeeLength];
        int length = der[1] & 255;
        int offset = 2;
        if (length >= 128) {
            offset = 2 + 1;
        }
        int offset2 = offset + 1;
        int offset3 = offset2 + 1;
        int rLength = der[offset2];
        int extraZero = 0;
        if (der[offset3] == 0) {
            extraZero = 1;
        }
        System.arraycopy(der, offset3 + extraZero, ieee, ((ieeeLength / 2) - rLength) + extraZero, rLength - extraZero);
        int offset4 = offset3 + rLength + 1;
        int offset5 = offset4 + 1;
        int sLength = der[offset4];
        int extraZero2 = 0;
        if (der[offset5] == 0) {
            extraZero2 = 1;
        }
        System.arraycopy(der, offset5 + extraZero2, ieee, (ieeeLength - sLength) + extraZero2, sLength - extraZero2);
        return ieee;
    }

    public static boolean isValidDerEncoding(final byte[] sig) {
        if (sig.length < 8 || sig[0] != 48) {
            return false;
        }
        int totalLen = sig[1] & UByte.MAX_VALUE;
        int totalLenLen = 1;
        if (totalLen == 129) {
            totalLenLen = 2;
            totalLen = sig[2] & UByte.MAX_VALUE;
            if (totalLen < 128) {
                return false;
            }
        } else if (totalLen == 128 || totalLen > 129) {
            return false;
        }
        if (totalLen != (sig.length - 1) - totalLenLen || sig[totalLenLen + 1] != 2) {
            return false;
        }
        int rLen = sig[totalLenLen + 1 + 1] & UByte.MAX_VALUE;
        if (totalLenLen + 1 + 1 + 1 + rLen + 1 >= sig.length || rLen == 0 || (sig[totalLenLen + 3] & UByte.MAX_VALUE) >= 128) {
            return false;
        }
        if ((rLen > 1 && sig[totalLenLen + 3] == 0 && (sig[totalLenLen + 4] & UByte.MAX_VALUE) < 128) || sig[totalLenLen + 3 + rLen] != 2) {
            return false;
        }
        int sLen = sig[totalLenLen + 1 + 1 + 1 + rLen + 1] & UByte.MAX_VALUE;
        if (totalLenLen + 1 + 1 + 1 + rLen + 1 + 1 + sLen == sig.length && sLen != 0 && (sig[totalLenLen + 5 + rLen] & UByte.MAX_VALUE) < 128) {
            return sLen <= 1 || sig[(totalLenLen + 5) + rLen] != 0 || (sig[(totalLenLen + 6) + rLen] & UByte.MAX_VALUE) >= 128;
        }
        return false;
    }

    public static int encodingSizeInBytes(EllipticCurve curve, PointFormatType format) throws GeneralSecurityException {
        int coordinateSize = fieldSizeInBytes(curve);
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType[format.ordinal()];
        if (i == 1) {
            return (coordinateSize * 2) + 1;
        }
        if (i == 2) {
            return coordinateSize * 2;
        }
        if (i == 3) {
            return coordinateSize + 1;
        }
        throw new GeneralSecurityException("unknown EC point format");
    }

    @Deprecated
    public static ECPoint ecPointDecode(EllipticCurve curve, PointFormatType format, byte[] encoded) throws GeneralSecurityException {
        return pointDecode(curve, format, encoded);
    }

    public static ECPoint pointDecode(CurveType curveType, PointFormatType format, byte[] encoded) throws GeneralSecurityException {
        return pointDecode(getCurveSpec(curveType).getCurve(), format, encoded);
    }

    public static ECPoint pointDecode(EllipticCurve curve, PointFormatType format, byte[] encoded) throws GeneralSecurityException {
        boolean lsb;
        int coordinateSize = fieldSizeInBytes(curve);
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType[format.ordinal()];
        if (i == 1) {
            if (encoded.length != (coordinateSize * 2) + 1) {
                throw new GeneralSecurityException("invalid point size");
            }
            if (encoded[0] != 4) {
                throw new GeneralSecurityException("invalid point format");
            }
            BigInteger x = new BigInteger(1, Arrays.copyOfRange(encoded, 1, coordinateSize + 1));
            BigInteger y = new BigInteger(1, Arrays.copyOfRange(encoded, coordinateSize + 1, encoded.length));
            ECPoint point = new ECPoint(x, y);
            checkPointOnCurve(point, curve);
            return point;
        }
        if (i == 2) {
            if (encoded.length != coordinateSize * 2) {
                throw new GeneralSecurityException("invalid point size");
            }
            BigInteger x2 = new BigInteger(1, Arrays.copyOfRange(encoded, 0, coordinateSize));
            BigInteger y2 = new BigInteger(1, Arrays.copyOfRange(encoded, coordinateSize, encoded.length));
            ECPoint point2 = new ECPoint(x2, y2);
            checkPointOnCurve(point2, curve);
            return point2;
        }
        if (i == 3) {
            BigInteger p = getModulus(curve);
            if (encoded.length != coordinateSize + 1) {
                throw new GeneralSecurityException("compressed point has wrong length");
            }
            if (encoded[0] == 2) {
                lsb = false;
            } else if (encoded[0] == 3) {
                lsb = true;
            } else {
                throw new GeneralSecurityException("invalid format");
            }
            BigInteger x3 = new BigInteger(1, Arrays.copyOfRange(encoded, 1, encoded.length));
            if (x3.signum() == -1 || x3.compareTo(p) >= 0) {
                throw new GeneralSecurityException("x is out of range");
            }
            BigInteger y3 = getY(x3, lsb, curve);
            return new ECPoint(x3, y3);
        }
        throw new GeneralSecurityException("invalid format:" + format);
    }

    public static byte[] pointEncode(CurveType curveType, PointFormatType format, ECPoint point) throws GeneralSecurityException {
        return pointEncode(getCurveSpec(curveType).getCurve(), format, point);
    }

    public static byte[] pointEncode(EllipticCurve curve, PointFormatType format, ECPoint point) throws GeneralSecurityException {
        checkPointOnCurve(point, curve);
        int coordinateSize = fieldSizeInBytes(curve);
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType[format.ordinal()];
        if (i == 1) {
            byte[] encoded = new byte[(coordinateSize * 2) + 1];
            byte[] x = point.getAffineX().toByteArray();
            byte[] y = point.getAffineY().toByteArray();
            System.arraycopy(y, 0, encoded, ((coordinateSize * 2) + 1) - y.length, y.length);
            System.arraycopy(x, 0, encoded, (coordinateSize + 1) - x.length, x.length);
            encoded[0] = 4;
            return encoded;
        }
        if (i != 2) {
            if (i == 3) {
                byte[] encoded2 = new byte[coordinateSize + 1];
                byte[] x2 = point.getAffineX().toByteArray();
                System.arraycopy(x2, 0, encoded2, (coordinateSize + 1) - x2.length, x2.length);
                encoded2[0] = (byte) (point.getAffineY().testBit(0) ? 3 : 2);
                return encoded2;
            }
            throw new GeneralSecurityException("invalid format:" + format);
        }
        byte[] encoded3 = new byte[coordinateSize * 2];
        byte[] x3 = point.getAffineX().toByteArray();
        if (x3.length > coordinateSize) {
            x3 = Arrays.copyOfRange(x3, x3.length - coordinateSize, x3.length);
        }
        byte[] y2 = point.getAffineY().toByteArray();
        if (y2.length > coordinateSize) {
            y2 = Arrays.copyOfRange(y2, y2.length - coordinateSize, y2.length);
        }
        System.arraycopy(y2, 0, encoded3, (coordinateSize * 2) - y2.length, y2.length);
        System.arraycopy(x3, 0, encoded3, coordinateSize - x3.length, x3.length);
        return encoded3;
    }

    /* renamed from: com.google.crypto.tink.subtle.EllipticCurves$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\EllipticCurves$1.smali */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType;

        static {
            int[] iArr = new int[CurveType.values().length];
            $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType = iArr;
            try {
                iArr[CurveType.NIST_P256.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[CurveType.NIST_P384.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[CurveType.NIST_P521.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            int[] iArr2 = new int[PointFormatType.values().length];
            $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType = iArr2;
            try {
                iArr2[PointFormatType.UNCOMPRESSED.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType[PointFormatType.DO_NOT_USE_CRUNCHY_UNCOMPRESSED.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType[PointFormatType.COMPRESSED.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public static ECParameterSpec getCurveSpec(CurveType curve) throws NoSuchAlgorithmException {
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[curve.ordinal()];
        if (i == 1) {
            return getNistP256Params();
        }
        if (i == 2) {
            return getNistP384Params();
        }
        if (i == 3) {
            return getNistP521Params();
        }
        throw new NoSuchAlgorithmException("curve not implemented:" + curve);
    }

    public static ECPublicKey getEcPublicKey(final byte[] x509PublicKey) throws GeneralSecurityException {
        KeyFactory kf = EngineFactory.KEY_FACTORY.getInstance("EC");
        return (ECPublicKey) kf.generatePublic(new X509EncodedKeySpec(x509PublicKey));
    }

    public static ECPublicKey getEcPublicKey(CurveType curve, PointFormatType pointFormat, final byte[] publicKey) throws GeneralSecurityException {
        return getEcPublicKey(getCurveSpec(curve), pointFormat, publicKey);
    }

    public static ECPublicKey getEcPublicKey(ECParameterSpec spec, PointFormatType pointFormat, final byte[] publicKey) throws GeneralSecurityException {
        ECPoint point = pointDecode(spec.getCurve(), pointFormat, publicKey);
        ECPublicKeySpec pubSpec = new ECPublicKeySpec(point, spec);
        KeyFactory kf = EngineFactory.KEY_FACTORY.getInstance("EC");
        return (ECPublicKey) kf.generatePublic(pubSpec);
    }

    public static ECPublicKey getEcPublicKey(CurveType curve, final byte[] x, final byte[] y) throws GeneralSecurityException {
        ECParameterSpec ecParams = getCurveSpec(curve);
        BigInteger pubX = new BigInteger(1, x);
        BigInteger pubY = new BigInteger(1, y);
        ECPoint w = new ECPoint(pubX, pubY);
        checkPointOnCurve(w, ecParams.getCurve());
        ECPublicKeySpec spec = new ECPublicKeySpec(w, ecParams);
        KeyFactory kf = EngineFactory.KEY_FACTORY.getInstance("EC");
        return (ECPublicKey) kf.generatePublic(spec);
    }

    public static ECPrivateKey getEcPrivateKey(final byte[] pkcs8PrivateKey) throws GeneralSecurityException {
        KeyFactory kf = EngineFactory.KEY_FACTORY.getInstance("EC");
        return (ECPrivateKey) kf.generatePrivate(new PKCS8EncodedKeySpec(pkcs8PrivateKey));
    }

    public static ECPrivateKey getEcPrivateKey(CurveType curve, final byte[] keyValue) throws GeneralSecurityException {
        ECParameterSpec ecParams = getCurveSpec(curve);
        BigInteger privValue = new BigInteger(1, keyValue);
        ECPrivateKeySpec spec = new ECPrivateKeySpec(privValue, ecParams);
        KeyFactory kf = EngineFactory.KEY_FACTORY.getInstance("EC");
        return (ECPrivateKey) kf.generatePrivate(spec);
    }

    public static KeyPair generateKeyPair(CurveType curve) throws GeneralSecurityException {
        return generateKeyPair(getCurveSpec(curve));
    }

    public static KeyPair generateKeyPair(ECParameterSpec spec) throws GeneralSecurityException {
        KeyPairGenerator keyGen = EngineFactory.KEY_PAIR_GENERATOR.getInstance("EC");
        keyGen.initialize(spec);
        return keyGen.generateKeyPair();
    }

    private static void validateSharedSecret(byte[] secret, ECPrivateKey privateKey) throws GeneralSecurityException {
        EllipticCurve privateKeyCurve = privateKey.getParams().getCurve();
        BigInteger x = new BigInteger(1, secret);
        if (x.signum() == -1 || x.compareTo(getModulus(privateKeyCurve)) >= 0) {
            throw new GeneralSecurityException("shared secret is out of range");
        }
        getY(x, true, privateKeyCurve);
    }

    public static byte[] computeSharedSecret(ECPrivateKey myPrivateKey, ECPublicKey peerPublicKey) throws GeneralSecurityException {
        validatePublicKeySpec(peerPublicKey, myPrivateKey);
        return computeSharedSecret(myPrivateKey, peerPublicKey.getW());
    }

    public static byte[] computeSharedSecret(ECPrivateKey myPrivateKey, ECPoint publicPoint) throws GeneralSecurityException {
        checkPointOnCurve(publicPoint, myPrivateKey.getParams().getCurve());
        ECParameterSpec privSpec = myPrivateKey.getParams();
        ECPublicKeySpec publicKeySpec = new ECPublicKeySpec(publicPoint, privSpec);
        KeyFactory kf = EngineFactory.KEY_FACTORY.getInstance("EC");
        PublicKey publicKey = kf.generatePublic(publicKeySpec);
        KeyAgreement ka = EngineFactory.KEY_AGREEMENT.getInstance("ECDH");
        ka.init(myPrivateKey);
        try {
            ka.doPhase(publicKey, true);
            byte[] secret = ka.generateSecret();
            validateSharedSecret(secret, myPrivateKey);
            return secret;
        } catch (IllegalStateException ex) {
            throw new GeneralSecurityException(ex.toString());
        }
    }
}
