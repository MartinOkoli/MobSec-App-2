package com.google.android.gms.common.data;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\data\DataBufferObserver.smali */
public interface DataBufferObserver {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\data\DataBufferObserver$Observable.smali */
    public interface Observable {
        void addObserver(DataBufferObserver dataBufferObserver);

        void removeObserver(DataBufferObserver dataBufferObserver);
    }

    void onDataChanged();

    void onDataRangeChanged(int i, int i2);

    void onDataRangeInserted(int i, int i2);

    void onDataRangeMoved(int i, int i2, int i3);

    void onDataRangeRemoved(int i, int i2);
}
