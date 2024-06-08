package c.a.b.w.o;

import c.a.b.w.e;
import java.lang.reflect.AccessibleObject;

/* loaded from: classes.dex */
public abstract class b {

    /* renamed from: a, reason: collision with root package name */
    private static final b f2314a;

    static {
        f2314a = e.b() < 9 ? new a() : new c();
    }

    public static b a() {
        return f2314a;
    }

    public abstract void a(AccessibleObject accessibleObject);
}
