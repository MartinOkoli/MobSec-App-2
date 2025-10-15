package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Hex;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\PrimitiveSet.smali */
public final class PrimitiveSet<P> {
    private Entry<P> primary;
    private final Class<P> primitiveClass;
    private final ConcurrentMap<Prefix, List<Entry<P>>> primitives = new ConcurrentHashMap();

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\PrimitiveSet$Entry.smali */
    public static final class Entry<P> {
        private final byte[] identifier;
        private final int keyId;
        private final OutputPrefixType outputPrefixType;
        private final P primitive;
        private final KeyStatusType status;

        Entry(P primitive, final byte[] identifier, KeyStatusType status, OutputPrefixType outputPrefixType, int keyId) {
            this.primitive = primitive;
            this.identifier = Arrays.copyOf(identifier, identifier.length);
            this.status = status;
            this.outputPrefixType = outputPrefixType;
            this.keyId = keyId;
        }

        public P getPrimitive() {
            return this.primitive;
        }

        public KeyStatusType getStatus() {
            return this.status;
        }

        public OutputPrefixType getOutputPrefixType() {
            return this.outputPrefixType;
        }

        public final byte[] getIdentifier() {
            byte[] bArr = this.identifier;
            if (bArr == null) {
                return null;
            }
            return Arrays.copyOf(bArr, bArr.length);
        }

        public int getKeyId() {
            return this.keyId;
        }
    }

    public Entry<P> getPrimary() {
        return this.primary;
    }

    public List<Entry<P>> getRawPrimitives() {
        return getPrimitive(CryptoFormat.RAW_PREFIX);
    }

    public List<Entry<P>> getPrimitive(final byte[] identifier) {
        List<Entry<P>> found = this.primitives.get(new Prefix(identifier));
        return found != null ? found : Collections.emptyList();
    }

    protected List<Entry<P>> getPrimitive(Keyset.Key key) throws GeneralSecurityException {
        return getPrimitive(CryptoFormat.getOutputPrefix(key));
    }

    public Collection<List<Entry<P>>> getAll() {
        return this.primitives.values();
    }

    private PrimitiveSet(Class<P> primitiveClass) {
        this.primitiveClass = primitiveClass;
    }

    public static <P> PrimitiveSet<P> newPrimitiveSet(Class<P> primitiveClass) {
        return new PrimitiveSet<>(primitiveClass);
    }

    public void setPrimary(final Entry<P> primary) {
        if (primary == null) {
            throw new IllegalArgumentException("the primary entry must be non-null");
        }
        if (primary.getStatus() != KeyStatusType.ENABLED) {
            throw new IllegalArgumentException("the primary entry has to be ENABLED");
        }
        List<Entry<P>> entries = getPrimitive(primary.getIdentifier());
        if (entries.isEmpty()) {
            throw new IllegalArgumentException("the primary entry cannot be set to an entry which is not held by this primitive set");
        }
        this.primary = primary;
    }

    public Entry<P> addPrimitive(final P primitive, Keyset.Key key) throws GeneralSecurityException {
        if (key.getStatus() != KeyStatusType.ENABLED) {
            throw new GeneralSecurityException("only ENABLED key is allowed");
        }
        Entry<P> entry = new Entry<>(primitive, CryptoFormat.getOutputPrefix(key), key.getStatus(), key.getOutputPrefixType(), key.getKeyId());
        List<Entry<P>> list = new ArrayList<>();
        list.add(entry);
        Prefix identifier = new Prefix(entry.getIdentifier());
        Collection<? extends Entry<P>> existing = (List) this.primitives.put(identifier, Collections.unmodifiableList(list));
        if (existing != null) {
            List<Entry<P>> newList = new ArrayList<>();
            newList.addAll(existing);
            newList.add(entry);
            this.primitives.put(identifier, Collections.unmodifiableList(newList));
        }
        return entry;
    }

    public Class<P> getPrimitiveClass() {
        return this.primitiveClass;
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\PrimitiveSet$Prefix.smali */
    private static class Prefix implements Comparable<Prefix> {
        private final byte[] prefix;

        private Prefix(byte[] prefix) {
            this.prefix = Arrays.copyOf(prefix, prefix.length);
        }

        public int hashCode() {
            return Arrays.hashCode(this.prefix);
        }

        public boolean equals(Object o) {
            if (!(o instanceof Prefix)) {
                return false;
            }
            Prefix other = (Prefix) o;
            return Arrays.equals(this.prefix, other.prefix);
        }

        @Override // java.lang.Comparable
        public int compareTo(Prefix o) {
            byte[] bArr = this.prefix;
            int length = bArr.length;
            byte[] bArr2 = o.prefix;
            if (length != bArr2.length) {
                return bArr.length - bArr2.length;
            }
            int i = 0;
            while (true) {
                byte[] bArr3 = this.prefix;
                if (i < bArr3.length) {
                    byte b = bArr3[i];
                    byte[] bArr4 = o.prefix;
                    if (b == bArr4[i]) {
                        i++;
                    } else {
                        return bArr3[i] - bArr4[i];
                    }
                } else {
                    return 0;
                }
            }
        }

        public String toString() {
            return Hex.encode(this.prefix);
        }
    }
}
