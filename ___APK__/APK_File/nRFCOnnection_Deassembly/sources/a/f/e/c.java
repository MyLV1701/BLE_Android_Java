package a.f.e;

import a.f.d.d.c;
import a.f.d.d.f;
import a.f.i.b;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;

@SuppressLint({"NewApi"})
/* loaded from: classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static final i f167a;

    /* renamed from: b, reason: collision with root package name */
    private static final a.d.e<String, Typeface> f168b;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            f167a = new h();
        } else if (i >= 28) {
            f167a = new g();
        } else if (i >= 26) {
            f167a = new f();
        } else if (i >= 24 && e.a()) {
            f167a = new e();
        } else if (Build.VERSION.SDK_INT >= 21) {
            f167a = new d();
        } else {
            f167a = new i();
        }
        f168b = new a.d.e<>(16);
    }

    private static String a(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }

    public static Typeface b(Resources resources, int i, int i2) {
        return f168b.b(a(resources, i, i2));
    }

    public static Typeface a(Context context, c.a aVar, Resources resources, int i, int i2, f.a aVar2, Handler handler, boolean z) {
        Typeface a2;
        if (aVar instanceof c.d) {
            c.d dVar = (c.d) aVar;
            boolean z2 = false;
            if (!z ? aVar2 == null : dVar.a() == 0) {
                z2 = true;
            }
            a2 = a.f.i.b.a(context, dVar.b(), aVar2, handler, z2, z ? dVar.c() : -1, i2);
        } else {
            a2 = f167a.a(context, (c.b) aVar, resources, i2);
            if (aVar2 != null) {
                if (a2 != null) {
                    aVar2.a(a2, handler);
                } else {
                    aVar2.a(-3, handler);
                }
            }
        }
        if (a2 != null) {
            f168b.a(a(resources, i, i2), a2);
        }
        return a2;
    }

    private static Typeface b(Context context, Typeface typeface, int i) {
        c.b a2 = f167a.a(typeface);
        if (a2 == null) {
            return null;
        }
        return f167a.a(context, a2, context.getResources(), i);
    }

    public static Typeface a(Context context, Resources resources, int i, String str, int i2) {
        Typeface a2 = f167a.a(context, resources, i, str, i2);
        if (a2 != null) {
            f168b.a(a(resources, i, i2), a2);
        }
        return a2;
    }

    public static Typeface a(Context context, CancellationSignal cancellationSignal, b.f[] fVarArr, int i) {
        return f167a.a(context, cancellationSignal, fVarArr, i);
    }

    public static Typeface a(Context context, Typeface typeface, int i) {
        Typeface b2;
        if (context != null) {
            return (Build.VERSION.SDK_INT >= 21 || (b2 = b(context, typeface, i)) == null) ? Typeface.create(typeface, i) : b2;
        }
        throw new IllegalArgumentException("Context cannot be null");
    }
}
