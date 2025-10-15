package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.proto.EcPointFormat;
import com.google.crypto.tink.proto.EciesAeadDemParams;
import com.google.crypto.tink.proto.EciesAeadHkdfKeyFormat;
import com.google.crypto.tink.proto.EciesAeadHkdfParams;
import com.google.crypto.tink.proto.EciesHkdfKemParams;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;

@Deprecated
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\hybrid\HybridKeyTemplates.smali */
public final class HybridKeyTemplates {
    public static final KeyTemplate ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256;
    public static final KeyTemplate ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM;
    public static final KeyTemplate ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_COMPRESSED_WITHOUT_PREFIX;
    private static final byte[] EMPTY_SALT;

    static {
        byte[] bArr = new byte[0];
        EMPTY_SALT = bArr;
        ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM = createEciesAeadHkdfKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.UNCOMPRESSED, AeadKeyTemplates.AES128_GCM, OutputPrefixType.TINK, bArr);
        ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_COMPRESSED_WITHOUT_PREFIX = createEciesAeadHkdfKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.COMPRESSED, AeadKeyTemplates.AES128_GCM, OutputPrefixType.RAW, bArr);
        ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256 = createEciesAeadHkdfKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.UNCOMPRESSED, AeadKeyTemplates.AES128_CTR_HMAC_SHA256, OutputPrefixType.TINK, bArr);
    }

    public static KeyTemplate createEciesAeadHkdfKeyTemplate(EllipticCurveType curve, HashType hashType, EcPointFormat ecPointFormat, KeyTemplate demKeyTemplate, OutputPrefixType outputPrefixType, byte[] salt) {
        EciesAeadHkdfKeyFormat format = EciesAeadHkdfKeyFormat.newBuilder().setParams(createEciesAeadHkdfParams(curve, hashType, ecPointFormat, demKeyTemplate, salt)).build();
        return KeyTemplate.newBuilder().setTypeUrl(new EciesAeadHkdfPrivateKeyManager().getKeyType()).setOutputPrefixType(outputPrefixType).setValue(format.toByteString()).build();
    }

    public static EciesAeadHkdfParams createEciesAeadHkdfParams(EllipticCurveType curve, HashType hashType, EcPointFormat ecPointFormat, KeyTemplate demKeyTemplate, byte[] salt) {
        EciesHkdfKemParams kemParams = EciesHkdfKemParams.newBuilder().setCurveType(curve).setHkdfHashType(hashType).setHkdfSalt(ByteString.copyFrom(salt)).build();
        EciesAeadDemParams demParams = EciesAeadDemParams.newBuilder().setAeadDem(demKeyTemplate).build();
        return EciesAeadHkdfParams.newBuilder().setKemParams(kemParams).setDemParams(demParams).setEcPointFormat(ecPointFormat).build();
    }
}
