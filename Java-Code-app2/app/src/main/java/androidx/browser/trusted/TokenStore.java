package androidx.browser.trusted;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\browser\trusted\TokenStore.smali */
public interface TokenStore {
    Token load();

    void store(Token token);
}
