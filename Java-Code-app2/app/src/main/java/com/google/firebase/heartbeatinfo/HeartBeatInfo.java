package com.google.firebase.heartbeatinfo;

import com.google.android.gms.tasks.Task;
import java.util.List;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\heartbeatinfo\HeartBeatInfo.smali */
public interface HeartBeatInfo {
    Task<List<HeartBeatResult>> getAndClearStoredHeartBeatInfo();

    HeartBeat getHeartBeatCode(String str);

    Task<Void> storeHeartBeatInfo(String str);

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\heartbeatinfo\HeartBeatInfo$HeartBeat.smali */
    public enum HeartBeat {
        NONE(0),
        SDK(1),
        GLOBAL(2),
        COMBINED(3);

        private final int code;

        HeartBeat(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }
    }
}
