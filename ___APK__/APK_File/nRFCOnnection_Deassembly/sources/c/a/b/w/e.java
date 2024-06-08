package c.a.b.w;

/* loaded from: classes.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    private static final int f2223a = a();

    private static int a() {
        return b(System.getProperty("java.version"));
    }

    static int b(String str) {
        int c2 = c(str);
        if (c2 == -1) {
            c2 = a(str);
        }
        if (c2 == -1) {
            return 6;
        }
        return c2;
    }

    private static int c(String str) {
        try {
            String[] split = str.split("[._]");
            int parseInt = Integer.parseInt(split[0]);
            return (parseInt != 1 || split.length <= 1) ? parseInt : Integer.parseInt(split[1]);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    private static int a(String str) {
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (!Character.isDigit(charAt)) {
                    break;
                }
                sb.append(charAt);
            }
            return Integer.parseInt(sb.toString());
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public static int b() {
        return f2223a;
    }

    public static boolean c() {
        return f2223a >= 9;
    }
}
