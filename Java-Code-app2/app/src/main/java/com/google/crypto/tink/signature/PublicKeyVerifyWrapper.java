package com.google.crypto.tink.signature;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\signature\PublicKeyVerifyWrapper.smali */
class PublicKeyVerifyWrapper implements PrimitiveWrapper<PublicKeyVerify, PublicKeyVerify> {
    private static final Logger logger = Logger.getLogger(PublicKeyVerifyWrapper.class.getName());

    PublicKeyVerifyWrapper() {
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\signature\PublicKeyVerifyWrapper$WrappedPublicKeyVerify.smali */
    private static class WrappedPublicKeyVerify implements PublicKeyVerify {
        private final PrimitiveSet<PublicKeyVerify> primitives;

        public WrappedPublicKeyVerify(PrimitiveSet<PublicKeyVerify> primitives) {
            this.primitives = primitives;
        }

        @Override // com.google.crypto.tink.PublicKeyVerify
        public void verify(final byte[] signature, final byte[] data) throws GeneralSecurityException {
            if (signature.length <= 5) {
                throw new GeneralSecurityException("signature too short");
            }
            byte[] prefix = Arrays.copyOfRange(signature, 0, 5);
            byte[] sigNoPrefix = Arrays.copyOfRange(signature, 5, signature.length);
            List<PrimitiveSet.Entry<PublicKeyVerify>> entries = this.primitives.getPrimitive(prefix);
            for (PrimitiveSet.Entry<PublicKeyVerify> entry : entries) {
                try {
                    if (entry.getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                        byte[] formatVersion = {0};
                        byte[] dataWithFormatVersion = Bytes.concat(data, formatVersion);
                        entry.getPrimitive().verify(sigNoPrefix, dataWithFormatVersion);
                        return;
                    }
                    entry.getPrimitive().verify(sigNoPrefix, data);
                    return;
                } catch (GeneralSecurityException e) {
                    PublicKeyVerifyWrapper.logger.info("signature prefix matches a key, but cannot verify: " + e.toString());
                }
            }
            List<PrimitiveSet.Entry<PublicKeyVerify>> entries2 = this.primitives.getRawPrimitives();
            Iterator<PrimitiveSet.Entry<PublicKeyVerify>> it = entries2.iterator();
            while (it.hasNext()) {
                try {
                    it.next().getPrimitive().verify(signature, data);
                    return;
                } catch (GeneralSecurityException e2) {
                }
            }
            throw new GeneralSecurityException("invalid signature");
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public PublicKeyVerify wrap(final PrimitiveSet<PublicKeyVerify> primitives) {
        return new WrappedPublicKeyVerify(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<PublicKeyVerify> getPrimitiveClass() {
        return PublicKeyVerify.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<PublicKeyVerify> getInputPrimitiveClass() {
        return PublicKeyVerify.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new PublicKeyVerifyWrapper());
    }
}
