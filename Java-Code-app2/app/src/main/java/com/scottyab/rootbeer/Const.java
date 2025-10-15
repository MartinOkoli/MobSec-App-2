package com.scottyab.rootbeer;

import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\scottyab\rootbeer\Const.smali */
public final class Const {
    public static final String BINARY_BUSYBOX = "busybox";
    public static final String BINARY_SU = "su";
    public static final String[] knownRootAppsPackages = {"com.noshufou.android.su", "com.noshufou.android.su.elite", "eu.chainfire.supersu", "com.koushikdutta.superuser", "com.thirdparty.superuser", "com.yellowes.su", "com.topjohnwu.magisk", "com.kingroot.kinguser", "com.kingo.root", "com.smedialink.oneclickroot", "com.zhiqupk.root.global", "com.alephzain.framaroot"};
    public static final String[] knownDangerousAppsPackages = {"com.koushikdutta.rommanager", "com.koushikdutta.rommanager.license", "com.dimonvideo.luckypatcher", "com.chelpus.lackypatch", "com.ramdroid.appquarantine", "com.ramdroid.appquarantinepro", "com.android.vending.billing.InAppBillingService.COIN", "com.chelpus.luckypatcher"};
    public static final String[] knownRootCloakingPackages = {"com.devadvance.rootcloak", "com.devadvance.rootcloakplus", "de.robv.android.xposed.installer", "com.saurik.substrate", "com.zachspong.temprootremovejb", "com.amphoras.hidemyroot", "com.amphoras.hidemyrootadfree", "com.formyhm.hiderootPremium", "com.formyhm.hideroot"};
    public static final String[] suPaths = {"/data/local/", "/data/local/bin/", "/data/local/xbin/", "/sbin/", "/su/bin/", "/system/bin/", "/system/bin/.ext/", "/system/bin/failsafe/", "/system/sd/xbin/", "/system/usr/we-need-root/", "/system/xbin/", "/cache/", "/data/", "/dev/"};
    public static final String[] pathsThatShouldNotBeWritable = {"/system", "/system/bin", "/system/sbin", "/system/xbin", "/vendor/bin", "/sbin", "/etc"};

    private Const() throws InstantiationException {
        throw new InstantiationException("This class is not for instantiation");
    }

    static String[] getPaths() {
        ArrayList<String> paths = new ArrayList<>(Arrays.asList(suPaths));
        String sysPaths = System.getenv("PATH");
        if (sysPaths == null || "".equals(sysPaths)) {
            return (String[]) paths.toArray(new String[0]);
        }
        String[] strArrSplit = sysPaths.split(":");
        int length = strArrSplit.length;
        for (int i = 0; i < length; i++) {
            String path = strArrSplit[i];
            if (!path.endsWith("/")) {
                path = path + '/';
            }
            if (!paths.contains(path)) {
                paths.add(path);
            }
        }
        return (String[]) paths.toArray(new String[0]);
    }
}
