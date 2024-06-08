package c.a.a.a.s;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import c.a.a.a.y.b;

/* loaded from: classes.dex */
public class a {
    public static int a(View view, int i) {
        return b.a(view, i);
    }

    public static int a(Context context, int i, String str) {
        return b.a(context, i, str);
    }

    public static int a(View view, int i, int i2) {
        return a(view.getContext(), i, i2);
    }

    public static int a(Context context, int i, int i2) {
        TypedValue a2 = b.a(context, i);
        return a2 != null ? a2.data : i2;
    }

    public static int a(View view, int i, int i2, float f2) {
        return a(a(view, i), a(view, i2), f2);
    }

    public static int a(int i, int i2, float f2) {
        return a(i, a.f.e.a.c(i2, Math.round(Color.alpha(i2) * f2)));
    }

    public static int a(int i, int i2) {
        return a.f.e.a.b(i2, i);
    }
}
