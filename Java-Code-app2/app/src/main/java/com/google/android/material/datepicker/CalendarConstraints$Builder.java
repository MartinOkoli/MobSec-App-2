package com.google.android.material.datepicker;

import android.os.Bundle;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\datepicker\CalendarConstraints$Builder.smali */
public final class CalendarConstraints$Builder {
    private static final String DEEP_COPY_VALIDATOR_KEY = "DEEP_COPY_VALIDATOR_KEY";
    private long end;
    private Long openAt;
    private long start;
    private CalendarConstraints$DateValidator validator;
    static final long DEFAULT_START = UtcDates.canonicalYearMonthDay(Month.create(1900, 0).timeInMillis);
    static final long DEFAULT_END = UtcDates.canonicalYearMonthDay(Month.create(2100, 11).timeInMillis);

    public CalendarConstraints$Builder() {
        this.start = DEFAULT_START;
        this.end = DEFAULT_END;
        this.validator = DateValidatorPointForward.from(Long.MIN_VALUE);
    }

    CalendarConstraints$Builder(CalendarConstraints clone) {
        this.start = DEFAULT_START;
        this.end = DEFAULT_END;
        this.validator = DateValidatorPointForward.from(Long.MIN_VALUE);
        this.start = CalendarConstraints.access$100(clone).timeInMillis;
        this.end = CalendarConstraints.access$200(clone).timeInMillis;
        this.openAt = Long.valueOf(CalendarConstraints.access$300(clone).timeInMillis);
        this.validator = CalendarConstraints.access$400(clone);
    }

    public CalendarConstraints$Builder setStart(long month) {
        this.start = month;
        return this;
    }

    public CalendarConstraints$Builder setEnd(long month) {
        this.end = month;
        return this;
    }

    public CalendarConstraints$Builder setOpenAt(long month) {
        this.openAt = Long.valueOf(month);
        return this;
    }

    public CalendarConstraints$Builder setValidator(CalendarConstraints$DateValidator validator) {
        this.validator = validator;
        return this;
    }

    public CalendarConstraints build() {
        Bundle deepCopyBundle = new Bundle();
        deepCopyBundle.putParcelable(DEEP_COPY_VALIDATOR_KEY, this.validator);
        Month monthCreate = Month.create(this.start);
        Month monthCreate2 = Month.create(this.end);
        CalendarConstraints$DateValidator calendarConstraints$DateValidator = (CalendarConstraints$DateValidator) deepCopyBundle.getParcelable(DEEP_COPY_VALIDATOR_KEY);
        Long l = this.openAt;
        return new CalendarConstraints(monthCreate, monthCreate2, calendarConstraints$DateValidator, l == null ? null : Month.create(l.longValue()), (CalendarConstraints$1) null);
    }
}
