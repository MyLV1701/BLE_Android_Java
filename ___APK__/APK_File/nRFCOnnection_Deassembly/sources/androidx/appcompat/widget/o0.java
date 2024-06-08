package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class o0 {

    /* renamed from: a, reason: collision with root package name */
    private static final ThreadLocal<TypedValue> f1066a = new ThreadLocal<>();

    /* renamed from: b, reason: collision with root package name */
    static final int[] f1067b = {-16842910};

    /* renamed from: c, reason: collision with root package name */
    static final int[] f1068c = {R.attr.state_focused};

    /* renamed from: d, reason: collision with root package name */
    static final int[] f1069d;

    /* renamed from: e, reason: collision with root package name */
    static final int[] f1070e;

    /* renamed from: f, reason: collision with root package name */
    static final int[] f1071f;
    private static final int[] g;

    static {
        new int[1][0] = 16843518;
        f1069d = new int[]{R.attr.state_pressed};
        f1070e = new int[]{R.attr.state_checked};
        new int[1][0] = 16842913;
        f1071f = new int[0];
        g = new int[1];
    }

    public static int a(Context context, int i) {
        ColorStateList c2 = c(context, i);
        if (c2 != null && c2.isStateful()) {
            return c2.getColorForState(f1067b, c2.getDefaultColor());
        }
        TypedValue a2 = a();
        context.getTheme().resolveAttribute(R.attr.disabledAlpha, a2, true);
        return a(context, i, a2.getFloat());
    }

    public static int b(Context context, int i) {
        int[] iArr = g;
        iArr[0] = i;
        t0 a2 = t0.a(context, (AttributeSet) null, iArr);
        try {
            return a2.a(0, 0);
        } finally {
            a2.a();
        }
    }

    public static ColorStateList c(Context context, int i) {
        int[] iArr = g;
        iArr[0] = i;
        t0 a2 = t0.a(context, (AttributeSet) null, iArr);
        try {
            return a2.a(0);
        } finally {
            a2.a();
        }
    }

    private static TypedValue a() {
        TypedValue typedValue = f1066a.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        f1066a.set(typedValue2);
        return typedValue2;
    }

    static int a(Context context, int i, float f2) {
        return a.f.e.a.c(b(context, i), Math.round(Color.alpha(r0) * f2));
    }
}
