package com.google.crypto.tink;

import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\Registry.smali */
public final class Registry {
    private static final Logger logger = Logger.getLogger(Registry.class.getName());
    private static final ConcurrentMap<String, KeyManagerContainer> keyManagerMap = new ConcurrentHashMap();
    private static final ConcurrentMap<String, KeyDeriverContainer> keyDeriverMap = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Boolean> newKeyAllowedMap = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Catalogue<?>> catalogueMap = new ConcurrentHashMap();
    private static final ConcurrentMap<Class<?>, PrimitiveWrapper<?, ?>> primitiveWrapperMap = new ConcurrentHashMap();

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\Registry$KeyDeriverContainer.smali */
    private interface KeyDeriverContainer {
        KeyData deriveKey(ByteString serializedKeyFormat, InputStream stream) throws GeneralSecurityException;
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\Registry$KeyManagerContainer.smali */
    private interface KeyManagerContainer {
        Class<?> getImplementingClass();

        <P> KeyManager<P> getKeyManager(Class<P> primitiveClass) throws GeneralSecurityException;

        KeyManager<?> getUntypedKeyManager();

        MessageLite parseKey(ByteString serializedKey) throws InvalidProtocolBufferException, GeneralSecurityException;

        Class<?> publicKeyManagerClassOrNull();

        Set<Class<?>> supportedPrimitives();
    }

    private static <P> KeyManagerContainer createContainerFor(final KeyManager<P> keyManager) {
        return new KeyManagerContainer() { // from class: com.google.crypto.tink.Registry.1
            @Override // com.google.crypto.tink.Registry.KeyManagerContainer
            public <Q> KeyManager<Q> getKeyManager(Class<Q> primitiveClass) throws GeneralSecurityException {
                if (!keyManager.getPrimitiveClass().equals(primitiveClass)) {
                    throw new InternalError("This should never be called, as we always first check supportedPrimitives.");
                }
                KeyManager<Q> result = keyManager;
                return result;
            }

            @Override // com.google.crypto.tink.Registry.KeyManagerContainer
            public KeyManager<?> getUntypedKeyManager() {
                return keyManager;
            }

            @Override // com.google.crypto.tink.Registry.KeyManagerContainer
            public Class<?> getImplementingClass() {
                return keyManager.getClass();
            }

            @Override // com.google.crypto.tink.Registry.KeyManagerContainer
            public Set<Class<?>> supportedPrimitives() {
                return Collections.singleton(keyManager.getPrimitiveClass());
            }

            @Override // com.google.crypto.tink.Registry.KeyManagerContainer
            public Class<?> publicKeyManagerClassOrNull() {
                return null;
            }

            @Override // com.google.crypto.tink.Registry.KeyManagerContainer
            public MessageLite parseKey(ByteString serializedKey) throws InvalidProtocolBufferException, GeneralSecurityException {
                return null;
            }
        };
    }

    private static <KeyProtoT extends MessageLite> KeyManagerContainer createContainerFor(final KeyTypeManager<KeyProtoT> keyManager) {
        return new KeyManagerContainer() { // from class: com.google.crypto.tink.Registry.2
            @Override // com.google.crypto.tink.Registry.KeyManagerContainer
            public <Q> KeyManager<Q> getKeyManager(Class<Q> primitiveClass) throws GeneralSecurityException {
                try {
                    return new KeyManagerImpl(keyManager, primitiveClass);
                } catch (IllegalArgumentException e) {
                    throw new GeneralSecurityException("Primitive type not supported", e);
                }
            }

            @Override // com.google.crypto.tink.Registry.KeyManagerContainer
            public KeyManager<?> getUntypedKeyManager() {
                KeyTypeManager keyTypeManager = keyManager;
                return new KeyManagerImpl(keyTypeManager, keyTypeManager.firstSupportedPrimitiveClass());
            }

            @Override // com.google.crypto.tink.Registry.KeyManagerContainer
            public Class<?> getImplementingClass() {
                return keyManager.getClass();
            }

            @Override // com.google.crypto.tink.Registry.KeyManagerContainer
            public Set<Class<?>> supportedPrimitives() {
                return keyManager.supportedPrimitives();
            }

            @Override // com.google.crypto.tink.Registry.KeyManagerContainer
            public Class<?> publicKeyManagerClassOrNull() {
                return null;
            }

            @Override // com.google.crypto.tink.Registry.KeyManagerContainer
            public MessageLite parseKey(ByteString serializedKey) throws InvalidProtocolBufferException, GeneralSecurityException {
                MessageLite messageLiteMo6parseKey = keyManager.mo6parseKey(serializedKey);
                keyManager.validateKey(messageLiteMo6parseKey);
                return messageLiteMo6parseKey;
            }
        };
    }

    private static <KeyProtoT extends MessageLite, PublicKeyProtoT extends MessageLite> KeyManagerContainer createPrivateKeyContainerFor(final PrivateKeyTypeManager<KeyProtoT, PublicKeyProtoT> privateKeyTypeManager, final KeyTypeManager<PublicKeyProtoT> publicKeyTypeManager) {
        return new KeyManagerContainer() { // from class: com.google.crypto.tink.Registry.3
            @Override // com.google.crypto.tink.Registry.KeyManagerContainer
            public <Q> KeyManager<Q> getKeyManager(Class<Q> primitiveClass) throws GeneralSecurityException {
                try {
                    return new PrivateKeyManagerImpl(privateKeyTypeManager, publicKeyTypeManager, primitiveClass);
                } catch (IllegalArgumentException e) {
                    throw new GeneralSecurityException("Primitive type not supported", e);
                }
            }

            @Override // com.google.crypto.tink.Registry.KeyManagerContainer
            public KeyManager<?> getUntypedKeyManager() {
                PrivateKeyTypeManager privateKeyTypeManager2 = privateKeyTypeManager;
                return new PrivateKeyManagerImpl(privateKeyTypeManager2, publicKeyTypeManager, privateKeyTypeManager2.firstSupportedPrimitiveClass());
            }

            @Override // com.google.crypto.tink.Registry.KeyManagerContainer
            public Class<?> getImplementingClass() {
                return privateKeyTypeManager.getClass();
            }

            @Override // com.google.crypto.tink.Registry.KeyManagerContainer
            public Set<Class<?>> supportedPrimitives() {
                return privateKeyTypeManager.supportedPrimitives();
            }

            @Override // com.google.crypto.tink.Registry.KeyManagerContainer
            public Class<?> publicKeyManagerClassOrNull() {
                return publicKeyTypeManager.getClass();
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [com.google.crypto.tink.shaded.protobuf.MessageLite] */
            @Override // com.google.crypto.tink.Registry.KeyManagerContainer
            public MessageLite parseKey(ByteString serializedKey) throws InvalidProtocolBufferException, GeneralSecurityException {
                ?? key = privateKeyTypeManager.mo6parseKey(serializedKey);
                privateKeyTypeManager.validateKey(key);
                return key;
            }
        };
    }

    private static <KeyProtoT extends MessageLite> KeyDeriverContainer createDeriverFor(final KeyTypeManager<KeyProtoT> keyManager) {
        return new KeyDeriverContainer() { // from class: com.google.crypto.tink.Registry.4
            /* JADX WARN: Unknown type variable: KeyProtoT in type: com.google.crypto.tink.KeyTypeManager$KeyFactory<KeyFormatProtoT extends com.google.crypto.tink.shaded.protobuf.MessageLite, KeyProtoT> */
            private <KeyFormatProtoT extends MessageLite> MessageLite deriveKeyWithFactory(ByteString serializedKeyFormat, InputStream stream, KeyTypeManager.KeyFactory<KeyFormatProtoT, KeyProtoT> keyFactory) throws GeneralSecurityException {
                try {
                    MessageLite keyFormat = keyFactory.parseKeyFormat(serializedKeyFormat);
                    keyFactory.validateKeyFormat(keyFormat);
                    return (MessageLite) keyFactory.deriveKey(keyFormat, stream);
                } catch (InvalidProtocolBufferException e) {
                    throw new GeneralSecurityException("parsing key format failed in deriveKey", e);
                }
            }

            @Override // com.google.crypto.tink.Registry.KeyDeriverContainer
            public KeyData deriveKey(ByteString serializedKeyFormat, InputStream stream) throws GeneralSecurityException {
                MessageLite keyValue = deriveKeyWithFactory(serializedKeyFormat, stream, keyManager.keyFactory());
                KeyData keyData = KeyData.newBuilder().setTypeUrl(keyManager.getKeyType()).setValue(keyValue.toByteString()).setKeyMaterialType(keyManager.keyMaterialType()).build();
                return keyData;
            }
        };
    }

    private static synchronized KeyManagerContainer getKeyManagerContainerOrThrow(String typeUrl) throws GeneralSecurityException {
        ConcurrentMap<String, KeyManagerContainer> concurrentMap;
        concurrentMap = keyManagerMap;
        if (!concurrentMap.containsKey(typeUrl)) {
            throw new GeneralSecurityException("No key manager found for key type " + typeUrl);
        }
        return concurrentMap.get(typeUrl);
    }

    static synchronized void reset() {
        keyManagerMap.clear();
        keyDeriverMap.clear();
        newKeyAllowedMap.clear();
        catalogueMap.clear();
        primitiveWrapperMap.clear();
    }

    @Deprecated
    public static synchronized void addCatalogue(String catalogueName, Catalogue<?> catalogue) throws GeneralSecurityException {
        if (catalogueName == null) {
            throw new IllegalArgumentException("catalogueName must be non-null.");
        }
        if (catalogue == null) {
            throw new IllegalArgumentException("catalogue must be non-null.");
        }
        ConcurrentMap<String, Catalogue<?>> concurrentMap = catalogueMap;
        if (concurrentMap.containsKey(catalogueName.toLowerCase(Locale.US))) {
            Catalogue<?> existing = concurrentMap.get(catalogueName.toLowerCase(Locale.US));
            if (!catalogue.getClass().equals(existing.getClass())) {
                logger.warning("Attempted overwrite of a catalogueName catalogue for name " + catalogueName);
                throw new GeneralSecurityException("catalogue for name " + catalogueName + " has been already registered");
            }
        }
        concurrentMap.put(catalogueName.toLowerCase(Locale.US), catalogue);
    }

    @Deprecated
    public static Catalogue<?> getCatalogue(String catalogueName) throws GeneralSecurityException {
        if (catalogueName == null) {
            throw new IllegalArgumentException("catalogueName must be non-null.");
        }
        Catalogue<?> catalogue = catalogueMap.get(catalogueName.toLowerCase(Locale.US));
        if (catalogue == null) {
            String error = String.format("no catalogue found for %s. ", catalogueName);
            if (catalogueName.toLowerCase(Locale.US).startsWith("tinkaead")) {
                error = error + "Maybe call AeadConfig.register().";
            }
            if (catalogueName.toLowerCase(Locale.US).startsWith("tinkdeterministicaead")) {
                error = error + "Maybe call DeterministicAeadConfig.register().";
            } else if (catalogueName.toLowerCase(Locale.US).startsWith("tinkstreamingaead")) {
                error = error + "Maybe call StreamingAeadConfig.register().";
            } else if (catalogueName.toLowerCase(Locale.US).startsWith("tinkhybriddecrypt") || catalogueName.toLowerCase(Locale.US).startsWith("tinkhybridencrypt")) {
                error = error + "Maybe call HybridConfig.register().";
            } else if (catalogueName.toLowerCase(Locale.US).startsWith("tinkmac")) {
                error = error + "Maybe call MacConfig.register().";
            } else if (catalogueName.toLowerCase(Locale.US).startsWith("tinkpublickeysign") || catalogueName.toLowerCase(Locale.US).startsWith("tinkpublickeyverify")) {
                error = error + "Maybe call SignatureConfig.register().";
            } else if (catalogueName.toLowerCase(Locale.US).startsWith("tink")) {
                error = error + "Maybe call TinkConfig.register().";
            }
            throw new GeneralSecurityException(error);
        }
        return catalogue;
    }

    private static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static synchronized <P> void registerKeyManager(final KeyManager<P> manager) throws GeneralSecurityException {
        registerKeyManager((KeyManager) manager, true);
    }

    private static synchronized void ensureKeyManagerInsertable(String typeUrl, Class<?> implementingClass, boolean newKeyAllowed) throws GeneralSecurityException {
        ConcurrentMap<String, KeyManagerContainer> concurrentMap = keyManagerMap;
        if (concurrentMap.containsKey(typeUrl)) {
            KeyManagerContainer container = concurrentMap.get(typeUrl);
            if (!container.getImplementingClass().equals(implementingClass)) {
                logger.warning("Attempted overwrite of a registered key manager for key type " + typeUrl);
                throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", typeUrl, container.getImplementingClass().getName(), implementingClass.getName()));
            }
            if (newKeyAllowed && !newKeyAllowedMap.get(typeUrl).booleanValue()) {
                throw new GeneralSecurityException("New keys are already disallowed for key type " + typeUrl);
            }
        }
    }

    public static synchronized <P> void registerKeyManager(final KeyManager<P> manager, boolean newKeyAllowed) throws GeneralSecurityException {
        if (manager == null) {
            throw new IllegalArgumentException("key manager must be non-null.");
        }
        String typeUrl = manager.getKeyType();
        ensureKeyManagerInsertable(typeUrl, manager.getClass(), newKeyAllowed);
        keyManagerMap.putIfAbsent(typeUrl, createContainerFor(manager));
        newKeyAllowedMap.put(typeUrl, Boolean.valueOf(newKeyAllowed));
    }

    public static synchronized <KeyProtoT extends MessageLite> void registerKeyManager(final KeyTypeManager<KeyProtoT> manager, boolean newKeyAllowed) throws GeneralSecurityException {
        if (manager == null) {
            throw new IllegalArgumentException("key manager must be non-null.");
        }
        String typeUrl = manager.getKeyType();
        ensureKeyManagerInsertable(typeUrl, manager.getClass(), newKeyAllowed);
        ConcurrentMap<String, KeyManagerContainer> concurrentMap = keyManagerMap;
        if (!concurrentMap.containsKey(typeUrl)) {
            concurrentMap.put(typeUrl, createContainerFor(manager));
            keyDeriverMap.put(typeUrl, createDeriverFor(manager));
        }
        newKeyAllowedMap.put(typeUrl, Boolean.valueOf(newKeyAllowed));
    }

    public static synchronized <KeyProtoT extends MessageLite, PublicKeyProtoT extends MessageLite> void registerAsymmetricKeyManagers(final PrivateKeyTypeManager<KeyProtoT, PublicKeyProtoT> privateKeyTypeManager, final KeyTypeManager<PublicKeyProtoT> publicKeyTypeManager, boolean newKeyAllowed) throws GeneralSecurityException {
        Class<?> existingPublicKeyManagerClass;
        if (privateKeyTypeManager == null || publicKeyTypeManager == null) {
            throw new IllegalArgumentException("given key managers must be non-null.");
        }
        String privateTypeUrl = privateKeyTypeManager.getKeyType();
        String publicTypeUrl = publicKeyTypeManager.getKeyType();
        ensureKeyManagerInsertable(privateTypeUrl, privateKeyTypeManager.getClass(), newKeyAllowed);
        ensureKeyManagerInsertable(publicTypeUrl, publicKeyTypeManager.getClass(), false);
        if (privateTypeUrl.equals(publicTypeUrl)) {
            throw new GeneralSecurityException("Private and public key type must be different.");
        }
        ConcurrentMap<String, KeyManagerContainer> concurrentMap = keyManagerMap;
        if (concurrentMap.containsKey(privateTypeUrl) && (existingPublicKeyManagerClass = concurrentMap.get(privateTypeUrl).publicKeyManagerClassOrNull()) != null && !existingPublicKeyManagerClass.equals(publicKeyTypeManager.getClass())) {
            logger.warning("Attempted overwrite of a registered key manager for key type " + privateTypeUrl + " with inconsistent public key type " + publicTypeUrl);
            throw new GeneralSecurityException(String.format("public key manager corresponding to %s is already registered with %s, cannot be re-registered with %s", privateKeyTypeManager.getClass().getName(), existingPublicKeyManagerClass.getName(), publicKeyTypeManager.getClass().getName()));
        }
        if (!concurrentMap.containsKey(privateTypeUrl) || concurrentMap.get(privateTypeUrl).publicKeyManagerClassOrNull() == null) {
            concurrentMap.put(privateTypeUrl, createPrivateKeyContainerFor(privateKeyTypeManager, publicKeyTypeManager));
            keyDeriverMap.put(privateTypeUrl, createDeriverFor(privateKeyTypeManager));
        }
        ConcurrentMap<String, Boolean> concurrentMap2 = newKeyAllowedMap;
        concurrentMap2.put(privateTypeUrl, Boolean.valueOf(newKeyAllowed));
        if (!concurrentMap.containsKey(publicTypeUrl)) {
            concurrentMap.put(publicTypeUrl, createContainerFor(publicKeyTypeManager));
        }
        concurrentMap2.put(publicTypeUrl, false);
    }

    @Deprecated
    public static synchronized <P> void registerKeyManager(String typeUrl, final KeyManager<P> manager) throws GeneralSecurityException {
        registerKeyManager(typeUrl, manager, true);
    }

    @Deprecated
    public static synchronized <P> void registerKeyManager(String typeUrl, final KeyManager<P> manager, boolean newKeyAllowed) throws GeneralSecurityException {
        try {
            if (manager == null) {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
            if (!typeUrl.equals(manager.getKeyType())) {
                throw new GeneralSecurityException("Manager does not support key type " + typeUrl + ".");
            }
            registerKeyManager(manager, newKeyAllowed);
        } catch (Throwable th) {
            throw th;
        }
    }

    public static synchronized <B, P> void registerPrimitiveWrapper(final PrimitiveWrapper<B, P> wrapper) throws GeneralSecurityException {
        if (wrapper == null) {
            throw new IllegalArgumentException("wrapper must be non-null");
        }
        Class<P> classObject = wrapper.getPrimitiveClass();
        ConcurrentMap<Class<?>, PrimitiveWrapper<?, ?>> concurrentMap = primitiveWrapperMap;
        if (concurrentMap.containsKey(classObject)) {
            PrimitiveWrapper<?, ?> primitiveWrapper = concurrentMap.get(classObject);
            if (!wrapper.getClass().equals(primitiveWrapper.getClass())) {
                logger.warning("Attempted overwrite of a registered SetWrapper for type " + classObject);
                throw new GeneralSecurityException(String.format("SetWrapper for primitive (%s) is already registered to be %s, cannot be re-registered with %s", classObject.getName(), primitiveWrapper.getClass().getName(), wrapper.getClass().getName()));
            }
        }
        concurrentMap.put(classObject, wrapper);
    }

    @Deprecated
    public static <P> KeyManager<P> getKeyManager(String typeUrl) throws GeneralSecurityException {
        return getKeyManagerInternal(typeUrl, null);
    }

    public static KeyManager<?> getUntypedKeyManager(String typeUrl) throws GeneralSecurityException {
        KeyManagerContainer container = getKeyManagerContainerOrThrow(typeUrl);
        return container.getUntypedKeyManager();
    }

    public static <P> KeyManager<P> getKeyManager(String typeUrl, Class<P> primitiveClass) throws GeneralSecurityException {
        return getKeyManagerInternal(typeUrl, (Class) checkNotNull(primitiveClass));
    }

    private static String toCommaSeparatedString(Set<Class<?>> setOfClasses) {
        StringBuilder b = new StringBuilder();
        boolean first = true;
        for (Class<?> clazz : setOfClasses) {
            if (!first) {
                b.append(", ");
            }
            b.append(clazz.getCanonicalName());
            first = false;
        }
        return b.toString();
    }

    private static <P> KeyManager<P> getKeyManagerInternal(String str, Class<P> cls) throws GeneralSecurityException {
        KeyManagerContainer keyManagerContainerOrThrow = getKeyManagerContainerOrThrow(str);
        if (cls == null) {
            return (KeyManager<P>) keyManagerContainerOrThrow.getUntypedKeyManager();
        }
        if (keyManagerContainerOrThrow.supportedPrimitives().contains(cls)) {
            return keyManagerContainerOrThrow.getKeyManager(cls);
        }
        throw new GeneralSecurityException("Primitive type " + cls.getName() + " not supported by key manager of type " + keyManagerContainerOrThrow.getImplementingClass() + ", supported primitives: " + toCommaSeparatedString(keyManagerContainerOrThrow.supportedPrimitives()));
    }

    public static synchronized KeyData newKeyData(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeyManager<?> manager;
        manager = getUntypedKeyManager(keyTemplate.getTypeUrl());
        if (newKeyAllowedMap.get(keyTemplate.getTypeUrl()).booleanValue()) {
        } else {
            throw new GeneralSecurityException("newKey-operation not permitted for key type " + keyTemplate.getTypeUrl());
        }
        return manager.newKeyData(keyTemplate.getValue());
    }

    public static synchronized KeyData newKeyData(KeyTemplate keyTemplate) throws GeneralSecurityException {
        return newKeyData(keyTemplate.getProto());
    }

    public static synchronized MessageLite newKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeyManager<?> manager;
        manager = getUntypedKeyManager(keyTemplate.getTypeUrl());
        if (newKeyAllowedMap.get(keyTemplate.getTypeUrl()).booleanValue()) {
        } else {
            throw new GeneralSecurityException("newKey-operation not permitted for key type " + keyTemplate.getTypeUrl());
        }
        return manager.newKey(keyTemplate.getValue());
    }

    public static synchronized MessageLite newKey(String typeUrl, MessageLite format) throws GeneralSecurityException {
        KeyManager<?> manager;
        manager = getKeyManager(typeUrl);
        if (newKeyAllowedMap.get(typeUrl).booleanValue()) {
        } else {
            throw new GeneralSecurityException("newKey-operation not permitted for key type " + typeUrl);
        }
        return manager.newKey(format);
    }

    static synchronized KeyData deriveKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate, InputStream randomStream) throws GeneralSecurityException {
        KeyDeriverContainer deriver;
        String typeUrl = keyTemplate.getTypeUrl();
        ConcurrentMap<String, KeyDeriverContainer> concurrentMap = keyDeriverMap;
        if (!concurrentMap.containsKey(typeUrl)) {
            throw new GeneralSecurityException("No keymanager registered or key manager cannot derive keys for " + typeUrl);
        }
        deriver = concurrentMap.get(typeUrl);
        return deriver.deriveKey(keyTemplate.getValue(), randomStream);
    }

    public static KeyData getPublicKeyData(String typeUrl, ByteString serializedPrivateKey) throws GeneralSecurityException {
        KeyManager<?> manager = getKeyManager(typeUrl);
        if (!(manager instanceof PrivateKeyManager)) {
            throw new GeneralSecurityException("manager for key type " + typeUrl + " is not a PrivateKeyManager");
        }
        return ((PrivateKeyManager) manager).getPublicKeyData(serializedPrivateKey);
    }

    @Deprecated
    public static <P> P getPrimitive(String str, MessageLite messageLite) throws GeneralSecurityException {
        return (P) getPrimitiveInternal(str, messageLite, (Class) null);
    }

    public static <P> P getPrimitive(String str, MessageLite messageLite, Class<P> cls) throws GeneralSecurityException {
        return (P) getPrimitiveInternal(str, messageLite, (Class) checkNotNull(cls));
    }

    private static <P> P getPrimitiveInternal(String typeUrl, MessageLite key, Class<P> primitiveClass) throws GeneralSecurityException {
        KeyManager<P> manager = getKeyManagerInternal(typeUrl, primitiveClass);
        return manager.getPrimitive(key);
    }

    @Deprecated
    public static <P> P getPrimitive(String str, ByteString byteString) throws GeneralSecurityException {
        return (P) getPrimitiveInternal(str, byteString, (Class) null);
    }

    public static <P> P getPrimitive(String str, ByteString byteString, Class<P> cls) throws GeneralSecurityException {
        return (P) getPrimitiveInternal(str, byteString, (Class) checkNotNull(cls));
    }

    private static <P> P getPrimitiveInternal(String typeUrl, ByteString serializedKey, Class<P> primitiveClass) throws GeneralSecurityException {
        KeyManager<P> manager = getKeyManagerInternal(typeUrl, primitiveClass);
        return manager.getPrimitive(serializedKey);
    }

    @Deprecated
    public static <P> P getPrimitive(String str, byte[] bArr) throws GeneralSecurityException {
        return (P) getPrimitive(str, ByteString.copyFrom(bArr));
    }

    public static <P> P getPrimitive(String str, byte[] bArr, Class<P> cls) throws GeneralSecurityException {
        return (P) getPrimitive(str, ByteString.copyFrom(bArr), cls);
    }

    @Deprecated
    public static <P> P getPrimitive(KeyData keyData) throws GeneralSecurityException {
        return (P) getPrimitive(keyData.getTypeUrl(), keyData.getValue());
    }

    public static <P> P getPrimitive(KeyData keyData, Class<P> cls) throws GeneralSecurityException {
        return (P) getPrimitive(keyData.getTypeUrl(), keyData.getValue(), cls);
    }

    public static <P> PrimitiveSet<P> getPrimitives(KeysetHandle keysetHandle, Class<P> primitiveClass) throws GeneralSecurityException {
        return getPrimitives(keysetHandle, null, primitiveClass);
    }

    public static <P> PrimitiveSet<P> getPrimitives(KeysetHandle keysetHandle, final KeyManager<P> customManager, Class<P> primitiveClass) throws GeneralSecurityException {
        return getPrimitivesInternal(keysetHandle, customManager, (Class) checkNotNull(primitiveClass));
    }

    private static <P> PrimitiveSet<P> getPrimitivesInternal(KeysetHandle keysetHandle, KeyManager<P> keyManager, Class<P> cls) throws GeneralSecurityException {
        P primitive;
        Util.validateKeyset(keysetHandle.getKeyset());
        PrimitiveSet<P> primitiveSetNewPrimitiveSet = PrimitiveSet.newPrimitiveSet(cls);
        for (Keyset.Key key : keysetHandle.getKeyset().getKeyList()) {
            if (key.getStatus() == KeyStatusType.ENABLED) {
                if (keyManager != null && keyManager.doesSupport(key.getKeyData().getTypeUrl())) {
                    primitive = keyManager.getPrimitive(key.getKeyData().getValue());
                } else {
                    primitive = (P) getPrimitiveInternal(key.getKeyData().getTypeUrl(), key.getKeyData().getValue(), cls);
                }
                PrimitiveSet.Entry<P> entryAddPrimitive = primitiveSetNewPrimitiveSet.addPrimitive(primitive, key);
                if (key.getKeyId() == keysetHandle.getKeyset().getPrimaryKeyId()) {
                    primitiveSetNewPrimitiveSet.setPrimary(entryAddPrimitive);
                }
            }
        }
        return primitiveSetNewPrimitiveSet;
    }

    public static <B, P> P wrap(PrimitiveSet<B> primitiveSet, Class<P> cls) throws GeneralSecurityException {
        PrimitiveWrapper<?, ?> primitiveWrapper = primitiveWrapperMap.get(cls);
        if (primitiveWrapper == null) {
            throw new GeneralSecurityException("No wrapper found for " + primitiveSet.getPrimitiveClass().getName());
        }
        if (!primitiveWrapper.getInputPrimitiveClass().equals(primitiveSet.getPrimitiveClass())) {
            throw new GeneralSecurityException("Wrong input primitive class, expected " + primitiveWrapper.getInputPrimitiveClass() + ", got " + primitiveSet.getPrimitiveClass());
        }
        return (P) primitiveWrapper.wrap(primitiveSet);
    }

    public static <P> P wrap(PrimitiveSet<P> primitiveSet) throws GeneralSecurityException {
        return (P) wrap(primitiveSet, primitiveSet.getPrimitiveClass());
    }

    public static Class<?> getInputPrimitive(Class<?> wrappedPrimitive) {
        PrimitiveWrapper<?, ?> wrapper = primitiveWrapperMap.get(wrappedPrimitive);
        if (wrapper == null) {
            return null;
        }
        return wrapper.getInputPrimitiveClass();
    }

    static MessageLite parseKeyData(KeyData keyData) throws InvalidProtocolBufferException, GeneralSecurityException {
        KeyManagerContainer container = getKeyManagerContainerOrThrow(keyData.getTypeUrl());
        return container.parseKey(keyData.getValue());
    }

    private Registry() {
    }
}
