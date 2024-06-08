package d.p;

/* loaded from: classes.dex */
public class w {

    /* renamed from: a, reason: collision with root package name */
    private static byte f3136a;

    /* renamed from: b, reason: collision with root package name */
    private static byte f3137b;

    /* renamed from: c, reason: collision with root package name */
    private static byte f3138c;

    /* renamed from: d, reason: collision with root package name */
    private static byte f3139d;

    /* renamed from: e, reason: collision with root package name */
    private static byte f3140e;

    static {
        d.q.c.b(w.class);
        f3136a = (byte) 1;
        f3137b = (byte) 2;
        f3138c = (byte) 3;
        f3139d = (byte) 4;
        f3140e = (byte) 5;
    }

    public static byte[] a(String str, d.o oVar) {
        if (str.startsWith("http:")) {
            return c(str, oVar);
        }
        return b(str, oVar);
    }

    private static byte[] b(String str, d.o oVar) {
        int i;
        int max;
        String substring;
        int i2;
        g gVar = new g();
        if (str.charAt(1) == ':') {
            gVar.a(f3136a);
            gVar.a((byte) str.charAt(0));
            i = 2;
        } else {
            if (str.charAt(0) == '\\' || str.charAt(0) == '/') {
                gVar.a(f3137b);
            }
            i = 0;
        }
        while (true) {
            if (str.charAt(i) != '\\' && str.charAt(i) != '/') {
                break;
            }
            i++;
        }
        while (i < str.length()) {
            int indexOf = str.indexOf(47, i);
            int indexOf2 = str.indexOf(92, i);
            if (indexOf != -1 && indexOf2 != -1) {
                max = Math.min(indexOf, indexOf2);
            } else {
                max = (indexOf == -1 || indexOf2 == -1) ? Math.max(indexOf, indexOf2) : 0;
            }
            if (max == -1) {
                substring = str.substring(i);
                i2 = str.length();
            } else {
                substring = str.substring(i, max);
                i2 = max + 1;
            }
            if (!substring.equals(".")) {
                if (substring.equals("..")) {
                    gVar.a(f3139d);
                } else {
                    gVar.a(j0.a(substring, oVar));
                }
            }
            if (i2 < str.length()) {
                gVar.a(f3138c);
            }
            i = i2;
        }
        return gVar.a();
    }

    private static byte[] c(String str, d.o oVar) {
        g gVar = new g();
        gVar.a(f3140e);
        gVar.a((byte) str.length());
        gVar.a(j0.a(str, oVar));
        return gVar.a();
    }
}
