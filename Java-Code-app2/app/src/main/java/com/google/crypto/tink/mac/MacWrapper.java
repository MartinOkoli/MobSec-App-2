package com.google.crypto.tink.mac;

import com.google.crypto.tink.Mac;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\mac\MacWrapper.smali */
class MacWrapper implements PrimitiveWrapper<Mac, Mac> {
    private static final Logger logger = Logger.getLogger(MacWrapper.class.getName());

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\mac\MacWrapper$WrappedMac.smali */
    private static class WrappedMac implements Mac {
        private final byte[] formatVersion;
        private final PrimitiveSet<Mac> primitives;

        private WrappedMac(PrimitiveSet<Mac> primitives) {
            this.formatVersion = new byte[]{0};
            this.primitives = primitives;
        }

        @Override // com.google.crypto.tink.Mac
        public byte[] computeMac(final byte[] data) throws GeneralSecurityException {
            if (this.primitives.getPrimary().getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                return Bytes.concat(this.primitives.getPrimary().getIdentifier(), this.primitives.getPrimary().getPrimitive().computeMac(Bytes.concat(data, this.formatVersion)));
            }
            return Bytes.concat(this.primitives.getPrimary().getIdentifier(), this.primitives.getPrimary().getPrimitive().computeMac(data));
        }

        @Override // com.google.crypto.tink.Mac
        public void verifyMac(final byte[] mac, final byte[] data) throws GeneralSecurityException {
            if (mac.length <= 5) {
                throw new GeneralSecurityException("tag too short");
            }
            byte[] prefix = Arrays.copyOf(mac, 5);
            byte[] macNoPrefix = Arrays.copyOfRange(mac, 5, mac.length);
            List<PrimitiveSet.Entry<Mac>> entries = this.primitives.getPrimitive(prefix);
            for (PrimitiveSet.Entry<Mac> entry : entries) {
                try {
                    if (entry.getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                        entry.getPrimitive().verifyMac(macNoPrefix, Bytes.concat(data, this.formatVersion));
                        return;
                    } else {
                        entry.getPrimitive().verifyMac(macNoPrefix, data);
                        return;
                    }
                } catch (GeneralSecurityException e) {
                    MacWrapper.logger.info("tag prefix matches a key, but cannot verify: " + e);
                }
            }
            List<PrimitiveSet.Entry<Mac>> entries2 = this.primitives.getRawPrimitives();
            Iterator<PrimitiveSet.Entry<Mac>> it = entries2.iterator();
            while (it.hasNext()) {
                try {
                    it.next().getPrimitive().verifyMac(mac, data);
                    return;
                } catch (GeneralSecurityException e2) {
                }
            }
            throw new GeneralSecurityException("invalid MAC");
        }
    }

    MacWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Mac wrap(final PrimitiveSet<Mac> primitives) throws GeneralSecurityException {
        return new WrappedMac(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Mac> getPrimitiveClass() {
        return Mac.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Mac> getInputPrimitiveClass() {
        return Mac.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new MacWrapper());
    }
}
