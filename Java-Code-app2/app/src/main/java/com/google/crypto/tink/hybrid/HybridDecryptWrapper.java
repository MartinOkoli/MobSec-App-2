package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\hybrid\HybridDecryptWrapper.smali */
public class HybridDecryptWrapper implements PrimitiveWrapper<HybridDecrypt, HybridDecrypt> {
    private static final Logger logger = Logger.getLogger(HybridDecryptWrapper.class.getName());

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\hybrid\HybridDecryptWrapper$WrappedHybridDecrypt.smali */
    private static class WrappedHybridDecrypt implements HybridDecrypt {
        private final PrimitiveSet<HybridDecrypt> primitives;

        public WrappedHybridDecrypt(final PrimitiveSet<HybridDecrypt> primitives) {
            this.primitives = primitives;
        }

        @Override // com.google.crypto.tink.HybridDecrypt
        public byte[] decrypt(final byte[] ciphertext, final byte[] contextInfo) throws GeneralSecurityException {
            if (ciphertext.length > 5) {
                byte[] prefix = Arrays.copyOfRange(ciphertext, 0, 5);
                byte[] ciphertextNoPrefix = Arrays.copyOfRange(ciphertext, 5, ciphertext.length);
                List<PrimitiveSet.Entry<HybridDecrypt>> entries = this.primitives.getPrimitive(prefix);
                Iterator<PrimitiveSet.Entry<HybridDecrypt>> it = entries.iterator();
                while (it.hasNext()) {
                    PrimitiveSet.Entry<HybridDecrypt> entry = it.next();
                    try {
                        return entry.getPrimitive().decrypt(ciphertextNoPrefix, contextInfo);
                    } catch (GeneralSecurityException e) {
                        HybridDecryptWrapper.logger.info("ciphertext prefix matches a key, but cannot decrypt: " + e.toString());
                    }
                }
            }
            List<PrimitiveSet.Entry<HybridDecrypt>> entries2 = this.primitives.getRawPrimitives();
            Iterator<PrimitiveSet.Entry<HybridDecrypt>> it2 = entries2.iterator();
            while (it2.hasNext()) {
                PrimitiveSet.Entry<HybridDecrypt> entry2 = it2.next();
                try {
                    return entry2.getPrimitive().decrypt(ciphertext, contextInfo);
                } catch (GeneralSecurityException e2) {
                }
            }
            throw new GeneralSecurityException("decryption failed");
        }
    }

    HybridDecryptWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public HybridDecrypt wrap(final PrimitiveSet<HybridDecrypt> primitives) {
        return new WrappedHybridDecrypt(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<HybridDecrypt> getPrimitiveClass() {
        return HybridDecrypt.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<HybridDecrypt> getInputPrimitiveClass() {
        return HybridDecrypt.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new HybridDecryptWrapper());
    }
}
