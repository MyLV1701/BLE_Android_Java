package d.p;

/* loaded from: classes.dex */
public final class i {

    /* renamed from: a, reason: collision with root package name */
    private static d.q.c f2865a = d.q.c.b(i.class);

    private i() {
    }

    public static void a(int i, int i2, StringBuffer stringBuffer) {
        a(i, stringBuffer);
        stringBuffer.append(Integer.toString(i2 + 1));
    }

    private static int b(String str) {
        int lastIndexOf = str.lastIndexOf(33) + 1;
        boolean z = false;
        while (!z && lastIndexOf < str.length()) {
            char charAt = str.charAt(lastIndexOf);
            if (charAt < '0' || charAt > '9') {
                lastIndexOf++;
            } else {
                z = true;
            }
        }
        return lastIndexOf;
    }

    public static int c(String str) {
        try {
            return Integer.parseInt(str.substring(b(str))) - 1;
        } catch (NumberFormatException e2) {
            f2865a.a(e2, e2);
            return 65535;
        }
    }

    public static boolean d(String str) {
        return str.charAt(0) != '$';
    }

    public static boolean e(String str) {
        return str.charAt(b(str) - 1) != '$';
    }

    public static void a(int i, boolean z, int i2, boolean z2, StringBuffer stringBuffer) {
        if (z) {
            stringBuffer.append('$');
        }
        a(i, stringBuffer);
        if (z2) {
            stringBuffer.append('$');
        }
        stringBuffer.append(Integer.toString(i2 + 1));
    }

    public static void a(int i, StringBuffer stringBuffer) {
        int i2 = i % 26;
        StringBuffer stringBuffer2 = new StringBuffer();
        for (int i3 = i / 26; i3 != 0; i3 /= 26) {
            stringBuffer2.append((char) (i2 + 65));
            i2 = (i3 % 26) - 1;
        }
        stringBuffer2.append((char) (i2 + 65));
        for (int length = stringBuffer2.length() - 1; length >= 0; length--) {
            stringBuffer.append(stringBuffer2.charAt(length));
        }
    }

    public static void a(int i, int i2, int i3, d.p.r0.t tVar, StringBuffer stringBuffer) {
        stringBuffer.append(j0.a(tVar.b(i), "'", "''"));
        stringBuffer.append('!');
        a(i2, i3, stringBuffer);
    }

    public static void a(int i, int i2, boolean z, int i3, boolean z2, d.p.r0.t tVar, StringBuffer stringBuffer) {
        stringBuffer.append(tVar.b(i));
        stringBuffer.append('!');
        a(i2, z, i3, z2, stringBuffer);
    }

    public static String a(int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        a(i, i2, stringBuffer);
        return stringBuffer.toString();
    }

    public static int a(String str) {
        int b2 = b(str);
        String upperCase = str.toUpperCase();
        int lastIndexOf = str.lastIndexOf(33) + 1;
        if (str.charAt(lastIndexOf) == '$') {
            lastIndexOf++;
        }
        if (str.charAt(b2 - 1) == '$') {
            b2--;
        }
        int i = 0;
        for (int i2 = lastIndexOf; i2 < b2; i2++) {
            if (i2 != lastIndexOf) {
                i = (i + 1) * 26;
            }
            i += upperCase.charAt(i2) - 'A';
        }
        return i;
    }
}
