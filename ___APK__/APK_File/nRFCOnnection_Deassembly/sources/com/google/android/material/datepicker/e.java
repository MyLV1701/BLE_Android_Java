package com.google.android.material.datepicker;

import android.os.Build;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes.dex */
class e {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(long j) {
        return a(j, Locale.getDefault());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(long j) {
        return b(j, Locale.getDefault());
    }

    static String a(long j, Locale locale) {
        if (Build.VERSION.SDK_INT >= 24) {
            return o.a(locale).format(new Date(j));
        }
        return o.b(locale).format(new Date(j));
    }

    static String b(long j, Locale locale) {
        if (Build.VERSION.SDK_INT >= 24) {
            return o.c(locale).format(new Date(j));
        }
        return o.b(locale).format(new Date(j));
    }
}
