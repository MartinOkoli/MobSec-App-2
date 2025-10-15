package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeysetReader;
import com.google.crypto.tink.proto.EcdsaParams;
import com.google.crypto.tink.proto.EcdsaPublicKey;
import com.google.crypto.tink.proto.EcdsaSignatureEncoding;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.proto.RsaSsaPkcs1Params;
import com.google.crypto.tink.proto.RsaSsaPkcs1PublicKey;
import com.google.crypto.tink.proto.RsaSsaPssParams;
import com.google.crypto.tink.proto.RsaSsaPssPublicKey;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.subtle.PemKeyType;
import com.google.crypto.tink.subtle.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.security.Key;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\signature\SignaturePemKeysetReader.smali */
public final class SignaturePemKeysetReader implements KeysetReader {
    private List<PemKey> pemKeys;

    SignaturePemKeysetReader(List<PemKey> pemKeys) {
        this.pemKeys = pemKeys;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\signature\SignaturePemKeysetReader$Builder.smali */
    public static final class Builder {
        private List<PemKey> pemKeys = new ArrayList();

        Builder() {
        }

        public KeysetReader build() {
            return new SignaturePemKeysetReader(this.pemKeys);
        }

        public Builder addPem(String pem, PemKeyType keyType) {
            PemKey pemKey = new PemKey(null);
            pemKey.reader = new BufferedReader(new StringReader(pem));
            pemKey.type = keyType;
            this.pemKeys.add(pemKey);
            return this;
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\signature\SignaturePemKeysetReader$PemKey.smali */
    private static final class PemKey {
        BufferedReader reader;
        PemKeyType type;

        private PemKey() {
        }

        /* synthetic */ PemKey(AnonymousClass1 x0) {
            this();
        }
    }

    @Override // com.google.crypto.tink.KeysetReader
    public Keyset read() throws IOException {
        Keyset.Builder keyset = Keyset.newBuilder();
        for (PemKey pemKey : this.pemKeys) {
            for (Keyset.Key key = readKey(pemKey.reader, pemKey.type); key != null; key = readKey(pemKey.reader, pemKey.type)) {
                keyset.addKey(key);
            }
        }
        if (keyset.getKeyCount() == 0) {
            throw new IOException("cannot find any key");
        }
        keyset.setPrimaryKeyId(keyset.getKey(0).getKeyId());
        return keyset.build();
    }

    @Override // com.google.crypto.tink.KeysetReader
    public EncryptedKeyset readEncrypted() throws IOException {
        throw new UnsupportedOperationException();
    }

    private static Keyset.Key readKey(BufferedReader reader, PemKeyType pemKeyType) throws IOException {
        KeyData keyData;
        Key key = pemKeyType.readKey(reader);
        if (key == null) {
            return null;
        }
        if (key instanceof RSAPublicKey) {
            keyData = convertRsaPublicKey(pemKeyType, (RSAPublicKey) key);
        } else {
            if (!(key instanceof ECPublicKey)) {
                return null;
            }
            keyData = convertEcPublicKey(pemKeyType, (ECPublicKey) key);
        }
        return Keyset.Key.newBuilder().setKeyData(keyData).setStatus(KeyStatusType.ENABLED).setOutputPrefixType(OutputPrefixType.RAW).setKeyId(Random.randInt()).build();
    }

    private static KeyData convertRsaPublicKey(PemKeyType pemKeyType, RSAPublicKey key) throws IOException {
        if (pemKeyType.algorithm.equals("RSASSA-PKCS1-v1_5")) {
            RsaSsaPkcs1Params params = RsaSsaPkcs1Params.newBuilder().setHashType(getHashType(pemKeyType)).build();
            RsaSsaPkcs1PublicKey pkcs1PubKey = RsaSsaPkcs1PublicKey.newBuilder().setVersion(new RsaSsaPkcs1VerifyKeyManager().getVersion()).setParams(params).setE(ByteString.copyFrom(key.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(key.getModulus().toByteArray())).build();
            return KeyData.newBuilder().setTypeUrl(new RsaSsaPkcs1VerifyKeyManager().getKeyType()).setValue(pkcs1PubKey.toByteString()).setKeyMaterialType(KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC).build();
        }
        if (pemKeyType.algorithm.equals("RSASSA-PSS")) {
            RsaSsaPssParams params2 = RsaSsaPssParams.newBuilder().setSigHash(getHashType(pemKeyType)).setMgf1Hash(getHashType(pemKeyType)).setSaltLength(getDigestSizeInBytes(pemKeyType)).build();
            RsaSsaPssPublicKey pssPubKey = RsaSsaPssPublicKey.newBuilder().setVersion(new RsaSsaPssVerifyKeyManager().getVersion()).setParams(params2).setE(ByteString.copyFrom(key.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(key.getModulus().toByteArray())).build();
            return KeyData.newBuilder().setTypeUrl(new RsaSsaPssVerifyKeyManager().getKeyType()).setValue(pssPubKey.toByteString()).setKeyMaterialType(KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC).build();
        }
        throw new IOException("unsupported RSA signature algorithm: " + pemKeyType.algorithm);
    }

    private static KeyData convertEcPublicKey(PemKeyType pemKeyType, ECPublicKey key) throws IOException {
        if (pemKeyType.algorithm.equals("ECDSA")) {
            EcdsaParams params = EcdsaParams.newBuilder().setHashType(getHashType(pemKeyType)).setCurve(getCurveType(pemKeyType)).setEncoding(EcdsaSignatureEncoding.DER).build();
            EcdsaPublicKey ecdsaPubKey = EcdsaPublicKey.newBuilder().setVersion(new EcdsaVerifyKeyManager().getVersion()).setParams(params).setX(ByteString.copyFrom(key.getW().getAffineX().toByteArray())).setY(ByteString.copyFrom(key.getW().getAffineY().toByteArray())).build();
            return KeyData.newBuilder().setTypeUrl(new EcdsaVerifyKeyManager().getKeyType()).setValue(ecdsaPubKey.toByteString()).setKeyMaterialType(KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC).build();
        }
        throw new IOException("unsupported EC signature algorithm: " + pemKeyType.algorithm);
    }

    /* renamed from: com.google.crypto.tink.signature.SignaturePemKeysetReader$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\signature\SignaturePemKeysetReader$1.smali */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType;

        static {
            int[] iArr = new int[Enums.HashType.values().length];
            $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType = iArr;
            try {
                iArr[Enums.HashType.SHA256.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[Enums.HashType.SHA384.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[Enums.HashType.SHA512.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private static HashType getHashType(PemKeyType pemKeyType) {
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[pemKeyType.hash.ordinal()];
        if (i == 1) {
            return HashType.SHA256;
        }
        if (i == 2) {
            return HashType.SHA384;
        }
        if (i == 3) {
            return HashType.SHA512;
        }
        throw new IllegalArgumentException("unsupported hash type: " + pemKeyType.hash.name());
    }

    private static int getDigestSizeInBytes(PemKeyType pemKeyType) {
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[pemKeyType.hash.ordinal()];
        if (i == 1) {
            return 32;
        }
        if (i == 2) {
            return 48;
        }
        if (i == 3) {
            return 64;
        }
        throw new IllegalArgumentException("unsupported hash type: " + pemKeyType.hash.name());
    }

    private static EllipticCurveType getCurveType(PemKeyType pemKeyType) {
        int i = pemKeyType.keySizeInBits;
        if (i == 256) {
            return EllipticCurveType.NIST_P256;
        }
        if (i == 384) {
            return EllipticCurveType.NIST_P384;
        }
        if (i == 521) {
            return EllipticCurveType.NIST_P521;
        }
        throw new IllegalArgumentException("unsupported curve for key size: " + pemKeyType.keySizeInBits);
    }
}
