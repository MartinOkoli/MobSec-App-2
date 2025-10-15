package com.google.crypto.tink.daead;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\daead\DeterministicAeadWrapper.smali */
public class DeterministicAeadWrapper implements PrimitiveWrapper<DeterministicAead, DeterministicAead> {
    private static final Logger logger = Logger.getLogger(DeterministicAeadWrapper.class.getName());

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\daead\DeterministicAeadWrapper$WrappedDeterministicAead.smali */
    private static class WrappedDeterministicAead implements DeterministicAead {
        private PrimitiveSet<DeterministicAead> primitives;

        public WrappedDeterministicAead(PrimitiveSet<DeterministicAead> primitives) {
            this.primitives = primitives;
        }

        @Override // com.google.crypto.tink.DeterministicAead
        public byte[] encryptDeterministically(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
            return Bytes.concat(this.primitives.getPrimary().getIdentifier(), this.primitives.getPrimary().getPrimitive().encryptDeterministically(plaintext, associatedData));
        }

        @Override // com.google.crypto.tink.DeterministicAead
        public byte[] decryptDeterministically(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
            if (ciphertext.length > 5) {
                byte[] prefix = Arrays.copyOfRange(ciphertext, 0, 5);
                byte[] ciphertextNoPrefix = Arrays.copyOfRange(ciphertext, 5, ciphertext.length);
                List<PrimitiveSet.Entry<DeterministicAead>> entries = this.primitives.getPrimitive(prefix);
                Iterator<PrimitiveSet.Entry<DeterministicAead>> it = entries.iterator();
                while (it.hasNext()) {
                    PrimitiveSet.Entry<DeterministicAead> entry = it.next();
                    try {
                        return entry.getPrimitive().decryptDeterministically(ciphertextNoPrefix, associatedData);
                    } catch (GeneralSecurityException e) {
                        DeterministicAeadWrapper.logger.info("ciphertext prefix matches a key, but cannot decrypt: " + e.toString());
                    }
                }
            }
            List<PrimitiveSet.Entry<DeterministicAead>> entries2 = this.primitives.getRawPrimitives();
            Iterator<PrimitiveSet.Entry<DeterministicAead>> it2 = entries2.iterator();
            while (it2.hasNext()) {
                PrimitiveSet.Entry<DeterministicAead> entry2 = it2.next();
                try {
                    return entry2.getPrimitive().decryptDeterministically(ciphertext, associatedData);
                } catch (GeneralSecurityException e2) {
                }
            }
            throw new GeneralSecurityException("decryption failed");
        }
    }

    DeterministicAeadWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public DeterministicAead wrap(final PrimitiveSet<DeterministicAead> primitives) {
        return new WrappedDeterministicAead(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<DeterministicAead> getPrimitiveClass() {
        return DeterministicAead.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<DeterministicAead> getInputPrimitiveClass() {
        return DeterministicAead.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new DeterministicAeadWrapper());
    }
}
