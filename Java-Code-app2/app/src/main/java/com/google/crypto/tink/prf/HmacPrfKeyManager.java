package com.google.crypto.tink.prf;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacPrfKey;
import com.google.crypto.tink.proto.HmacPrfKeyFormat;
import com.google.crypto.tink.proto.HmacPrfParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.PrfHmacJce;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\prf\HmacPrfKeyManager.smali */
public final class HmacPrfKeyManager extends KeyTypeManager<HmacPrfKey> {
    private static final int MIN_KEY_SIZE_IN_BYTES = 16;

    public HmacPrfKeyManager() {
        super(HmacPrfKey.class, new KeyTypeManager.PrimitiveFactory<Prf, HmacPrfKey>(Prf.class) { // from class: com.google.crypto.tink.prf.HmacPrfKeyManager.1
            @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
            public Prf getPrimitive(HmacPrfKey key) throws GeneralSecurityException {
                HashType hash = key.getParams().getHash();
                byte[] keyValue = key.getKeyValue().toByteArray();
                SecretKeySpec keySpec = new SecretKeySpec(keyValue, "HMAC");
                int i = AnonymousClass3.$SwitchMap$com$google$crypto$tink$proto$HashType[hash.ordinal()];
                if (i == 1) {
                    return new PrfHmacJce("HMACSHA1", keySpec);
                }
                if (i == 2) {
                    return new PrfHmacJce("HMACSHA256", keySpec);
                }
                if (i == 3) {
                    return new PrfHmacJce("HMACSHA512", keySpec);
                }
                throw new GeneralSecurityException("unknown hash");
            }
        });
    }

    /* renamed from: com.google.crypto.tink.prf.HmacPrfKeyManager$3, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\prf\HmacPrfKeyManager$3.smali */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$HashType;

        static {
            int[] iArr = new int[HashType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$HashType = iArr;
            try {
                iArr[HashType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA512.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HmacPrfKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(HmacPrfKey key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
        if (key.getKeyValue().size() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        validateParams(key.getParams());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    /* renamed from: parseKey */
    public HmacPrfKey mo6parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return HmacPrfKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateParams(HmacPrfParams params) throws GeneralSecurityException {
        if (params.getHash() != HashType.SHA1 && params.getHash() != HashType.SHA256 && params.getHash() != HashType.SHA512) {
            throw new GeneralSecurityException("unknown hash type");
        }
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<HmacPrfKeyFormat, HmacPrfKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<HmacPrfKeyFormat, HmacPrfKey>(HmacPrfKeyFormat.class) { // from class: com.google.crypto.tink.prf.HmacPrfKeyManager.2
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public void validateKeyFormat(HmacPrfKeyFormat format) throws GeneralSecurityException {
                if (format.getKeySize() >= 16) {
                    HmacPrfKeyManager.validateParams(format.getParams());
                    return;
                }
                throw new GeneralSecurityException("key too short");
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public HmacPrfKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return HmacPrfKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public HmacPrfKey createKey(HmacPrfKeyFormat format) {
                return HmacPrfKey.newBuilder().setVersion(HmacPrfKeyManager.this.getVersion()).setParams(format.getParams()).setKeyValue(ByteString.copyFrom(Random.randBytes(format.getKeySize()))).build();
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public HmacPrfKey deriveKey(HmacPrfKeyFormat format, InputStream inputStream) throws GeneralSecurityException {
                Validators.validateVersion(format.getVersion(), HmacPrfKeyManager.this.getVersion());
                byte[] pseudorandomness = new byte[format.getKeySize()];
                try {
                    int read = inputStream.read(pseudorandomness);
                    if (read != format.getKeySize()) {
                        throw new GeneralSecurityException("Not enough pseudorandomness given");
                    }
                    return HmacPrfKey.newBuilder().setVersion(HmacPrfKeyManager.this.getVersion()).setParams(format.getParams()).setKeyValue(ByteString.copyFrom(pseudorandomness)).build();
                } catch (IOException e) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", e);
                }
            }
        };
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new HmacPrfKeyManager(), newKeyAllowed);
    }

    public static final KeyTemplate hmacSha256Template() {
        return createTemplate(32, HashType.SHA256);
    }

    public static final KeyTemplate hmacSha512Template() {
        return createTemplate(64, HashType.SHA512);
    }

    private static KeyTemplate createTemplate(int keySize, HashType hashType) {
        HmacPrfParams params = HmacPrfParams.newBuilder().setHash(hashType).build();
        HmacPrfKeyFormat format = HmacPrfKeyFormat.newBuilder().setParams(params).setKeySize(keySize).build();
        return KeyTemplate.create(new HmacPrfKeyManager().getKeyType(), format.toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }
}
