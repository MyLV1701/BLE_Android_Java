package com.google.android.material.datepicker;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;

/* loaded from: classes.dex */
public final class h<S> extends androidx.fragment.app.c {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(c.a.a.a.y.b.a(context, c.a.a.a.b.materialCalendarStyle, g.class.getCanonicalName()), new int[]{R.attr.windowFullscreen});
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        return z;
    }
}
