package com.google.crypto.tink.shaded.protobuf;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\shaded\protobuf\MutabilityOracle.smali */
interface MutabilityOracle {
    public static final MutabilityOracle IMMUTABLE = new MutabilityOracle() { // from class: com.google.crypto.tink.shaded.protobuf.MutabilityOracle.1
        @Override // com.google.crypto.tink.shaded.protobuf.MutabilityOracle
        public void ensureMutable() {
            throw new UnsupportedOperationException();
        }
    };

    void ensureMutable();
}
