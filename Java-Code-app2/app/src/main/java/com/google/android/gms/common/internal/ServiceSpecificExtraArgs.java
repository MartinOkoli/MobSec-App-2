package com.google.android.gms.common.internal;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\ServiceSpecificExtraArgs.smali */
public final class ServiceSpecificExtraArgs {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\ServiceSpecificExtraArgs$CastExtraArgs.smali */
    public interface CastExtraArgs {
        public static final String LISTENER = "listener";
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\ServiceSpecificExtraArgs$GamesExtraArgs.smali */
    public interface GamesExtraArgs {
        public static final String DESIRED_LOCALE = "com.google.android.gms.games.key.desiredLocale";
        public static final String GAME_PACKAGE_NAME = "com.google.android.gms.games.key.gamePackageName";
        public static final String SIGNIN_OPTIONS = "com.google.android.gms.games.key.signInOptions";
        public static final String WINDOW_TOKEN = "com.google.android.gms.games.key.popupWindowToken";
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\ServiceSpecificExtraArgs$PlusExtraArgs.smali */
    public interface PlusExtraArgs {
        public static final String PLUS_AUTH_PACKAGE = "auth_package";
    }

    private ServiceSpecificExtraArgs() {
    }
}
