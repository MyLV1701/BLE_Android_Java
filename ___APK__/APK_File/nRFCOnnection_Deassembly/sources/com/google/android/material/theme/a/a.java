package com.google.android.material.theme.a;

import a.a.o.d;
import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import c.a.a.a.b;

/* loaded from: classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f2776a = {R.attr.theme, b.theme};

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f2777b = {b.materialThemeOverlay};

    private static int a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f2776a);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(1, 0);
        obtainStyledAttributes.recycle();
        return resourceId != 0 ? resourceId : resourceId2;
    }

    public static Context b(Context context, AttributeSet attributeSet, int i, int i2) {
        int a2 = a(context, attributeSet, i, i2);
        boolean z = (context instanceof d) && ((d) context).a() == a2;
        if (a2 == 0 || z) {
            return context;
        }
        d dVar = new d(context, a2);
        int a3 = a(context, attributeSet);
        if (a3 != 0) {
            dVar.getTheme().applyStyle(a3, true);
        }
        return dVar;
    }

    private static int a(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f2777b, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }
}
