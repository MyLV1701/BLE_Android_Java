package d.q;

import java.security.AccessControlException;

/* loaded from: classes.dex */
public abstract class c {

    /* renamed from: a, reason: collision with root package name */
    private static c f3150a;

    private static synchronized void a() {
        synchronized (c.class) {
            if (f3150a != null) {
                return;
            }
            String str = d.q.d.a.f3151a;
            try {
                try {
                    try {
                        try {
                            str = System.getProperty("logger");
                            if (str == null) {
                                str = d.q.d.a.f3151a;
                            }
                            f3150a = (c) Class.forName(str).newInstance();
                        } catch (AccessControlException unused) {
                            f3150a = new d.q.d.b();
                            f3150a.b("Could not instantiate logger " + str + " using default");
                        }
                    } catch (ClassNotFoundException unused2) {
                        f3150a = new d.q.d.b();
                        f3150a.b("Could not instantiate logger " + str + " using default");
                    }
                } catch (IllegalAccessException unused3) {
                    f3150a = new d.q.d.b();
                    f3150a.b("Could not instantiate logger " + str + " using default");
                }
            } catch (InstantiationException unused4) {
                f3150a = new d.q.d.b();
                f3150a.b("Could not instantiate logger " + str + " using default");
            }
        }
    }

    public static final c b(Class cls) {
        if (f3150a == null) {
            a();
        }
        c cVar = f3150a;
        cVar.a(cls);
        return cVar;
    }

    protected abstract c a(Class cls);

    public abstract void a(Object obj);

    public abstract void a(Object obj, Throwable th);

    public void a(boolean z) {
    }

    public abstract void b(Object obj);
}
