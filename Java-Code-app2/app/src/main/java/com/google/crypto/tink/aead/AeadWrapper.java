package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\aead\AeadWrapper.smali */
public class AeadWrapper implements PrimitiveWrapper<Aead, Aead> {
    private static final Logger logger = Logger.getLogger(AeadWrapper.class.getName());

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\aead\AeadWrapper$WrappedAead.smali */
    private static class WrappedAead implements Aead {
        private final PrimitiveSet<Aead> pSet;

        private WrappedAead(PrimitiveSet<Aead> pSet) {
            this.pSet = pSet;
        }

        public byte[] encrypt(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
            return Bytes.concat(this.pSet.getPrimary().getIdentifier(), this.pSet.getPrimary().getPrimitive().encrypt(plaintext, associatedData));
        }

        public byte[] decrypt(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
            if (ciphertext.length > 5) {
                byte[] prefix = Arrays.copyOfRange(ciphertext, 0, 5);
                byte[] ciphertextNoPrefix = Arrays.copyOfRange(ciphertext, 5, ciphertext.length);
                List<PrimitiveSet.Entry<Aead>> entries = this.pSet.getPrimitive(prefix);
                Iterator<PrimitiveSet.Entry<Aead>> it = entries.iterator();
                while (it.hasNext()) {
                    PrimitiveSet.Entry<Aead> entry = it.next();
                    try {
                        return entry.getPrimitive().decrypt(ciphertextNoPrefix, associatedData);
                    } catch (GeneralSecurityException e) {
                        AeadWrapper.logger.info("ciphertext prefix matches a key, but cannot decrypt: " + e.toString());
                    }
                }
            }
            List<PrimitiveSet.Entry<Aead>> entries2 = this.pSet.getRawPrimitives();
            Iterator<PrimitiveSet.Entry<Aead>> it2 = entries2.iterator();
            while (it2.hasNext()) {
                PrimitiveSet.Entry<Aead> entry2 = it2.next();
                try {
                    return entry2.getPrimitive().decrypt(ciphertext, associatedData);
                } catch (GeneralSecurityException e2) {
                }
            }
            throw new GeneralSecurityException("decryption failed");
        }
    }

    AeadWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Aead wrap(final PrimitiveSet<Aead> pset) throws GeneralSecurityException {
        return new WrappedAead(pset);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Aead> getPrimitiveClass() {
        return Aead.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Aead> getInputPrimitiveClass() {
        return Aead.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new AeadWrapper());
    }
}
