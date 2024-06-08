package f.a.f;

/* loaded from: classes.dex */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    private static b f3456a = null;

    /* renamed from: b, reason: collision with root package name */
    private static boolean f3457b = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class b extends SecurityManager {
        private b() {
        }

        @Override // java.lang.SecurityManager
        protected Class<?>[] getClassContext() {
            return super.getClassContext();
        }
    }

    private g() {
    }

    public static Class<?> a() {
        int i;
        b b2 = b();
        if (b2 == null) {
            return null;
        }
        Class<?>[] classContext = b2.getClassContext();
        String name = g.class.getName();
        int i2 = 0;
        while (i2 < classContext.length && !name.equals(classContext[i2].getName())) {
            i2++;
        }
        if (i2 < classContext.length && (i = i2 + 2) < classContext.length) {
            return classContext[i];
        }
        throw new IllegalStateException("Failed to find org.slf4j.helpers.Util or its caller in the stack; this should not happen");
    }

    public static boolean b(String str) {
        String c2 = c(str);
        if (c2 == null) {
            return false;
        }
        return c2.equalsIgnoreCase("true");
    }

    public static String c(String str) {
        if (str != null) {
            try {
                return System.getProperty(str);
            } catch (SecurityException unused) {
                return null;
            }
        }
        throw new IllegalArgumentException("null input");
    }

    private static b b() {
        b bVar = f3456a;
        if (bVar != null) {
            return bVar;
        }
        if (f3457b) {
            return null;
        }
        f3456a = c();
        f3457b = true;
        return f3456a;
    }

    private static b c() {
        try {
            return new b();
        } catch (SecurityException unused) {
            return null;
        }
    }

    public static final void a(String str, Throwable th) {
        System.err.println(str);
        System.err.println("Reported exception:");
        th.printStackTrace();
    }

    public static final void a(String str) {
        System.err.println("SLF4J: " + str);
    }
}
