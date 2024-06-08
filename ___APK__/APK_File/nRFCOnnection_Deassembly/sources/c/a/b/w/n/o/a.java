package c.a.b.w.n.o;

import java.util.TimeZone;

/* loaded from: classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final TimeZone f2313a = TimeZone.getTimeZone("UTC");

    /* JADX WARN: Removed duplicated region for block: B:44:0x00ca A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b7, NumberFormatException -> 0x01b9, IndexOutOfBoundsException -> 0x01bb, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b7, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:18:0x0050, B:20:0x0060, B:21:0x0062, B:23:0x006e, B:24:0x0070, B:26:0x0076, B:30:0x0080, B:35:0x0090, B:37:0x0098, B:42:0x00c4, B:44:0x00ca, B:46:0x00d1, B:47:0x017e, B:52:0x00db, B:53:0x00f6, B:54:0x00f7, B:57:0x0113, B:59:0x0120, B:62:0x0129, B:64:0x0148, B:67:0x0157, B:68:0x0179, B:70:0x017c, B:71:0x0102, B:72:0x01af, B:73:0x01b6, B:74:0x00b2, B:75:0x00b5), top: B:2:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01af A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b7, NumberFormatException -> 0x01b9, IndexOutOfBoundsException -> 0x01bb, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b7, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:18:0x0050, B:20:0x0060, B:21:0x0062, B:23:0x006e, B:24:0x0070, B:26:0x0076, B:30:0x0080, B:35:0x0090, B:37:0x0098, B:42:0x00c4, B:44:0x00ca, B:46:0x00d1, B:47:0x017e, B:52:0x00db, B:53:0x00f6, B:54:0x00f7, B:57:0x0113, B:59:0x0120, B:62:0x0129, B:64:0x0148, B:67:0x0157, B:68:0x0179, B:70:0x017c, B:71:0x0102, B:72:0x01af, B:73:0x01b6, B:74:0x00b2, B:75:0x00b5), top: B:2:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Date a(java.lang.String r17, java.text.ParsePosition r18) {
        /*
            Method dump skipped, instructions count: 550
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.b.w.n.o.a.a(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private static boolean a(String str, int i, char c2) {
        return i < str.length() && str.charAt(i) == c2;
    }

    private static int a(String str, int i, int i2) {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i3 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i4 = -digit;
        } else {
            i3 = i;
            i4 = 0;
        }
        while (i3 < i2) {
            int i5 = i3 + 1;
            int digit2 = Character.digit(str.charAt(i3), 10);
            if (digit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i4 = (i4 * 10) - digit2;
            i3 = i5;
        }
        return -i4;
    }

    private static int a(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }
}
