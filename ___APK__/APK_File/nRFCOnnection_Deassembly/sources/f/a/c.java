package f.a;

import f.a.f.e;
import f.a.f.f;
import f.a.f.g;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.impl.StaticLoggerBinder;

/* loaded from: classes.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    static volatile int f3432a;

    /* renamed from: b, reason: collision with root package name */
    static final f f3433b = new f();

    /* renamed from: c, reason: collision with root package name */
    static final f.a.f.c f3434c = new f.a.f.c();

    /* renamed from: d, reason: collision with root package name */
    static boolean f3435d = g.b("slf4j.detectLoggerNameMismatch");

    /* renamed from: e, reason: collision with root package name */
    private static final String[] f3436e = {"1.6", "1.7"};

    /* renamed from: f, reason: collision with root package name */
    private static String f3437f = "org/slf4j/impl/StaticLoggerBinder.class";

    private c() {
    }

    private static final void a() {
        Set<URL> set = null;
        try {
            if (!f()) {
                set = c();
                c(set);
            }
            StaticLoggerBinder.getSingleton();
            f3432a = 3;
            b(set);
            d();
            h();
            f3433b.a();
        } catch (Exception e2) {
            a(e2);
            throw new IllegalStateException("Unexpected initialization failure", e2);
        } catch (NoClassDefFoundError e3) {
            if (b(e3.getMessage())) {
                f3432a = 4;
                g.a("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
                g.a("Defaulting to no-operation (NOP) logger implementation");
                g.a("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
                return;
            }
            a(e3);
            throw e3;
        } catch (NoSuchMethodError e4) {
            String message = e4.getMessage();
            if (message != null && message.contains("org.slf4j.impl.StaticLoggerBinder.getSingleton()")) {
                f3432a = 2;
                g.a("slf4j-api 1.6.x (or later) is incompatible with this binding.");
                g.a("Your binding is version 1.5.5 or earlier.");
                g.a("Upgrade your binding to version 1.6.x.");
            }
            throw e4;
        }
    }

    private static boolean b(String str) {
        if (str == null) {
            return false;
        }
        return str.contains("org/slf4j/impl/StaticLoggerBinder") || str.contains("org.slf4j.impl.StaticLoggerBinder");
    }

    static Set<URL> c() {
        Enumeration<URL> resources;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        try {
            ClassLoader classLoader = c.class.getClassLoader();
            if (classLoader == null) {
                resources = ClassLoader.getSystemResources(f3437f);
            } else {
                resources = classLoader.getResources(f3437f);
            }
            while (resources.hasMoreElements()) {
                linkedHashSet.add(resources.nextElement());
            }
        } catch (IOException e2) {
            g.a("Error getting resources from path", e2);
        }
        return linkedHashSet;
    }

    private static void d() {
        synchronized (f3433b) {
            f3433b.d();
            for (e eVar : f3433b.c()) {
                eVar.a(a(eVar.getName()));
            }
        }
    }

    public static a e() {
        if (f3432a == 0) {
            synchronized (c.class) {
                if (f3432a == 0) {
                    f3432a = 1;
                    g();
                }
            }
        }
        int i = f3432a;
        if (i == 1) {
            return f3433b;
        }
        if (i == 2) {
            throw new IllegalStateException("org.slf4j.LoggerFactory in failed state. Original exception was thrown EARLIER. See also http://www.slf4j.org/codes.html#unsuccessfulInit");
        }
        if (i == 3) {
            return StaticLoggerBinder.getSingleton().getLoggerFactory();
        }
        if (i == 4) {
            return f3434c;
        }
        throw new IllegalStateException("Unreachable code");
    }

    private static boolean f() {
        String c2 = g.c("java.vendor.url");
        if (c2 == null) {
            return false;
        }
        return c2.toLowerCase().contains("android");
    }

    private static final void g() {
        a();
        if (f3432a == 3) {
            i();
        }
    }

    private static void h() {
        LinkedBlockingQueue<f.a.e.d> b2 = f3433b.b();
        int size = b2.size();
        ArrayList<f.a.e.d> arrayList = new ArrayList(128);
        int i = 0;
        while (b2.drainTo(arrayList, 128) != 0) {
            for (f.a.e.d dVar : arrayList) {
                a(dVar);
                int i2 = i + 1;
                if (i == 0) {
                    a(dVar, size);
                }
                i = i2;
            }
            arrayList.clear();
        }
    }

    private static final void i() {
        try {
            String str = StaticLoggerBinder.REQUESTED_API_VERSION;
            boolean z = false;
            for (String str2 : f3436e) {
                if (str.startsWith(str2)) {
                    z = true;
                }
            }
            if (z) {
                return;
            }
            g.a("The requested version " + str + " by your slf4j binding is not compatible with " + Arrays.asList(f3436e).toString());
            g.a("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
        } catch (NoSuchFieldError unused) {
        } catch (Throwable th) {
            g.a("Unexpected problem occured during version sanity check", th);
        }
    }

    private static void b() {
        g.a("The following set of substitute loggers may have been accessed");
        g.a("during the initialization phase. Logging calls during this");
        g.a("phase were not honored. However, subsequent logging calls to these");
        g.a("loggers will work as normally expected.");
        g.a("See also http://www.slf4j.org/codes.html#substituteLogger");
    }

    private static void b(Set<URL> set) {
        if (set == null || !a(set)) {
            return;
        }
        g.a("Actual binding is of type [" + StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr() + "]");
    }

    private static void c(Set<URL> set) {
        if (a(set)) {
            g.a("Class path contains multiple SLF4J bindings.");
            Iterator<URL> it = set.iterator();
            while (it.hasNext()) {
                g.a("Found binding in [" + it.next() + "]");
            }
            g.a("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
        }
    }

    static void a(Throwable th) {
        f3432a = 2;
        g.a("Failed to instantiate SLF4J LoggerFactory", th);
    }

    private static void a(f.a.e.d dVar, int i) {
        if (dVar.a().b()) {
            a(i);
        } else {
            if (dVar.a().c()) {
                return;
            }
            b();
        }
    }

    private static void a(f.a.e.d dVar) {
        if (dVar == null) {
            return;
        }
        e a2 = dVar.a();
        String name = a2.getName();
        if (!a2.d()) {
            if (a2.c()) {
                return;
            }
            if (a2.b()) {
                a2.a(dVar);
                return;
            } else {
                g.a(name);
                return;
            }
        }
        throw new IllegalStateException("Delegate logger cannot be null at this state.");
    }

    private static void a(int i) {
        g.a("A number (" + i + ") of logging calls during the initialization phase have been intercepted and are");
        g.a("now being replayed. These are subject to the filtering rules of the underlying logging system.");
        g.a("See also http://www.slf4j.org/codes.html#replay");
    }

    private static boolean a(Set<URL> set) {
        return set.size() > 1;
    }

    public static b a(String str) {
        return e().a(str);
    }

    public static b a(Class<?> cls) {
        Class<?> a2;
        b a3 = a(cls.getName());
        if (f3435d && (a2 = g.a()) != null && a(cls, a2)) {
            g.a(String.format("Detected logger name mismatch. Given name: \"%s\"; computed name: \"%s\".", a3.getName(), a2.getName()));
            g.a("See http://www.slf4j.org/codes.html#loggerNameMismatch for an explanation");
        }
        return a3;
    }

    private static boolean a(Class<?> cls, Class<?> cls2) {
        return !cls2.isAssignableFrom(cls);
    }
}
