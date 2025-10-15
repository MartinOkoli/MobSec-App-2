package com.scottyab.rootbeer;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.scottyab.rootbeer.util.QLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\scottyab\rootbeer\RootBeer.smali */
public class RootBeer {
    private boolean loggingEnabled = true;
    private final Context mContext;

    public RootBeer(Context context) {
        this.mContext = context;
    }

    public boolean isRooted() {
        return detectRootManagementApps() || detectPotentiallyDangerousApps() || checkForBinary(Const.BINARY_SU) || checkForDangerousProps() || checkForRWPaths() || detectTestKeys() || checkSuExists() || checkForRootNative() || checkForMagiskBinary();
    }

    public boolean isRootedWithoutBusyBoxCheck() {
        return isRooted();
    }

    public boolean isRootedWithBusyBoxCheck() {
        return detectRootManagementApps() || detectPotentiallyDangerousApps() || checkForBinary(Const.BINARY_SU) || checkForBinary(Const.BINARY_BUSYBOX) || checkForDangerousProps() || checkForRWPaths() || detectTestKeys() || checkSuExists() || checkForRootNative() || checkForMagiskBinary();
    }

    public boolean detectTestKeys() {
        String buildTags = Build.TAGS;
        return buildTags != null && buildTags.contains("test-keys");
    }

    public boolean detectRootManagementApps() {
        return detectRootManagementApps(null);
    }

    public boolean detectRootManagementApps(String[] additionalRootManagementApps) {
        ArrayList<String> packages = new ArrayList<>(Arrays.asList(Const.knownRootAppsPackages));
        if (additionalRootManagementApps != null && additionalRootManagementApps.length > 0) {
            packages.addAll(Arrays.asList(additionalRootManagementApps));
        }
        return isAnyPackageFromListInstalled(packages);
    }

    public boolean detectPotentiallyDangerousApps() {
        return detectPotentiallyDangerousApps(null);
    }

    public boolean detectPotentiallyDangerousApps(String[] additionalDangerousApps) {
        ArrayList<String> packages = new ArrayList<>();
        packages.addAll(Arrays.asList(Const.knownDangerousAppsPackages));
        if (additionalDangerousApps != null && additionalDangerousApps.length > 0) {
            packages.addAll(Arrays.asList(additionalDangerousApps));
        }
        return isAnyPackageFromListInstalled(packages);
    }

    public boolean detectRootCloakingApps() {
        return detectRootCloakingApps(null) || (canLoadNativeLibrary() && !checkForNativeLibraryReadAccess());
    }

    public boolean detectRootCloakingApps(String[] additionalRootCloakingApps) {
        ArrayList<String> packages = new ArrayList<>(Arrays.asList(Const.knownRootCloakingPackages));
        if (additionalRootCloakingApps != null && additionalRootCloakingApps.length > 0) {
            packages.addAll(Arrays.asList(additionalRootCloakingApps));
        }
        return isAnyPackageFromListInstalled(packages);
    }

    public boolean checkForSuBinary() {
        return checkForBinary(Const.BINARY_SU);
    }

    public boolean checkForMagiskBinary() {
        return checkForBinary("magisk");
    }

    public boolean checkForBusyBoxBinary() {
        return checkForBinary(Const.BINARY_BUSYBOX);
    }

    public boolean checkForBinary(String filename) {
        String[] pathsArray = Const.getPaths();
        boolean result = false;
        for (String path : pathsArray) {
            String completePath = path + filename;
            File f = new File(path, filename);
            boolean fileExists = f.exists();
            if (fileExists) {
                QLog.v(completePath + " binary detected!");
                result = true;
            }
        }
        return result;
    }

    public void setLogging(boolean logging) {
        this.loggingEnabled = logging;
        QLog.LOGGING_LEVEL = logging ? 5 : 0;
    }

    private String[] propsReader() {
        try {
            InputStream inputstream = Runtime.getRuntime().exec("getprop").getInputStream();
            if (inputstream == null) {
                return null;
            }
            String propVal = new Scanner(inputstream).useDelimiter("\\A").next();
            return propVal.split("\n");
        } catch (IOException | NoSuchElementException e) {
            QLog.e(e);
            return null;
        }
    }

    private String[] mountReader() {
        try {
            InputStream inputstream = Runtime.getRuntime().exec("mount").getInputStream();
            if (inputstream == null) {
                return null;
            }
            String propVal = new Scanner(inputstream).useDelimiter("\\A").next();
            return propVal.split("\n");
        } catch (IOException | NoSuchElementException e) {
            QLog.e(e);
            return null;
        }
    }

    private boolean isAnyPackageFromListInstalled(List<String> packages) {
        boolean result = false;
        PackageManager pm = this.mContext.getPackageManager();
        for (String packageName : packages) {
            try {
                pm.getPackageInfo(packageName, 0);
                QLog.e(packageName + " ROOT management app detected!");
                result = true;
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        return result;
    }

    public boolean checkForDangerousProps() {
        Map<String, String> dangerousProps = new HashMap<>();
        dangerousProps.put("ro.debuggable", "1");
        dangerousProps.put("ro.secure", "0");
        boolean result = false;
        String[] lines = propsReader();
        if (lines == null) {
            return false;
        }
        for (String line : lines) {
            for (String key : dangerousProps.keySet()) {
                if (line.contains(key)) {
                    String badValue = "[" + dangerousProps.get(key) + "]";
                    if (line.contains(badValue)) {
                        QLog.v(key + " = " + badValue + " detected!");
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    public boolean checkForRWPaths() {
        boolean result;
        boolean result2 = false;
        String[] lines = mountReader();
        int i = 0;
        if (lines == null) {
            return false;
        }
        int length = lines.length;
        int i2 = 0;
        while (i2 < length) {
            String line = lines[i2];
            String[] args = line.split(" ");
            if (args.length < 4) {
                QLog.e("Error formatting mount line: " + line);
            } else {
                String mountPoint = args[1];
                String mountOptions = args[3];
                String[] strArr = Const.pathsThatShouldNotBeWritable;
                int length2 = strArr.length;
                int i3 = i;
                while (i3 < length2) {
                    String pathToCheck = strArr[i3];
                    if (!mountPoint.equalsIgnoreCase(pathToCheck)) {
                        result = result2;
                    } else {
                        String[] strArrSplit = mountOptions.split(",");
                        int length3 = strArrSplit.length;
                        int i4 = i;
                        while (i4 < length3) {
                            String option = strArrSplit[i4];
                            boolean result3 = result2;
                            if (!option.equalsIgnoreCase("rw")) {
                                i4++;
                                result2 = result3;
                            } else {
                                QLog.v(pathToCheck + " path is mounted with rw permissions! " + line);
                                result2 = true;
                                break;
                            }
                        }
                        result = result2;
                    }
                    result2 = result;
                    i3++;
                    i = 0;
                }
            }
            i2++;
            i = 0;
        }
        return result2;
    }

    public boolean checkSuExists() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"which", Const.BINARY_SU});
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            boolean z = in.readLine() != null;
            if (process != null) {
                process.destroy();
            }
            return z;
        } catch (Throwable th) {
            if (process != null) {
                process.destroy();
            }
            return false;
        }
    }

    public boolean checkForNativeLibraryReadAccess() {
        RootBeerNative rootBeerNative = new RootBeerNative();
        try {
            rootBeerNative.setLogDebugMessages(this.loggingEnabled);
            return true;
        } catch (UnsatisfiedLinkError e) {
            return false;
        }
    }

    public boolean canLoadNativeLibrary() {
        return new RootBeerNative().wasNativeLibraryLoaded();
    }

    public boolean checkForRootNative() {
        if (!canLoadNativeLibrary()) {
            QLog.e("We could not load the native library to test for root");
            return false;
        }
        String[] paths = Const.getPaths();
        String[] checkPaths = new String[paths.length];
        for (int i = 0; i < checkPaths.length; i++) {
            checkPaths[i] = paths[i] + Const.BINARY_SU;
        }
        RootBeerNative rootBeerNative = new RootBeerNative();
        try {
            rootBeerNative.setLogDebugMessages(this.loggingEnabled);
            return rootBeerNative.checkForRoot(checkPaths) > 0;
        } catch (UnsatisfiedLinkError e) {
            return false;
        }
    }
}
