package com.google.firebase.database.core.utilities;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\utilities\OffsetClock.smali */
public class OffsetClock implements Clock {
    private final Clock baseClock;
    private long offset;

    public OffsetClock(Clock baseClock, long offset) {
        this.offset = 0L;
        this.baseClock = baseClock;
        this.offset = offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    @Override // com.google.firebase.database.core.utilities.Clock
    public long millis() {
        return this.baseClock.millis() + this.offset;
    }
}
