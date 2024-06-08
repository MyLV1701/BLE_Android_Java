package com.google.android.material.datepicker;

import android.annotation.TargetApi;
import android.icu.text.DateFormat;
import android.icu.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/* loaded from: classes.dex */
class o {
    @TargetApi(24)
    private static TimeZone a() {
        return TimeZone.getTimeZone("UTC");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Calendar b() {
        Calendar calendar = Calendar.getInstance(c());
        calendar.clear();
        return calendar;
    }

    static java.util.TimeZone c() {
        return java.util.TimeZone.getTimeZone("UTC");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Calendar d() {
        return a(Calendar.getInstance());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SimpleDateFormat e() {
        return d(Locale.getDefault());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Calendar a(Calendar calendar) {
        Calendar b2 = b();
        b2.set(calendar.get(1), calendar.get(2), calendar.get(5));
        return b2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(24)
    public static DateFormat c(Locale locale) {
        return a("yMMMEd", locale);
    }

    private static SimpleDateFormat d(Locale locale) {
        return b("MMMM, yyyy", locale);
    }

    private static SimpleDateFormat b(String str, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, locale);
        simpleDateFormat.setTimeZone(c());
        return simpleDateFormat;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static java.text.DateFormat b(Locale locale) {
        return a(0, locale);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long a(long j) {
        Calendar b2 = b();
        b2.setTimeInMillis(j);
        return a(b2).getTimeInMillis();
    }

    @TargetApi(24)
    private static DateFormat a(String str, Locale locale) {
        DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton(str, locale);
        instanceForSkeleton.setTimeZone(a());
        return instanceForSkeleton;
    }

    private static java.text.DateFormat a(int i, Locale locale) {
        java.text.DateFormat dateInstance = java.text.DateFormat.getDateInstance(i, locale);
        dateInstance.setTimeZone(c());
        return dateInstance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(24)
    public static DateFormat a(Locale locale) {
        return a("MMMEd", locale);
    }
}
