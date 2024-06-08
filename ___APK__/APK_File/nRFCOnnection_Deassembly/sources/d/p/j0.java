package d.p;

import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public final class j0 {

    /* renamed from: a, reason: collision with root package name */
    private static d.q.c f2873a = d.q.c.b(j0.class);

    /* renamed from: b, reason: collision with root package name */
    public static String f2874b = "UnicodeLittle";

    private j0() {
    }

    public static byte[] a(String str) {
        return str.getBytes();
    }

    public static byte[] b(String str) {
        try {
            byte[] bytes = str.getBytes(f2874b);
            if (bytes.length != (str.length() * 2) + 2) {
                return bytes;
            }
            byte[] bArr = new byte[bytes.length - 2];
            System.arraycopy(bytes, 2, bArr, 0, bArr.length);
            return bArr;
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public static byte[] a(String str, d.o oVar) {
        try {
            return str.getBytes(oVar.c());
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public static void a(String str, byte[] bArr, int i) {
        byte[] a2 = a(str);
        System.arraycopy(a2, 0, bArr, i, a2.length);
    }

    public static String a(byte[] bArr, int i, int i2, d.o oVar) {
        if (i == 0) {
            return "";
        }
        try {
            return new String(bArr, i2, i, oVar.c());
        } catch (UnsupportedEncodingException e2) {
            f2873a.b(e2.toString());
            return "";
        }
    }

    public static void b(String str, byte[] bArr, int i) {
        byte[] b2 = b(str);
        System.arraycopy(b2, 0, bArr, i, b2.length);
    }

    public static String a(byte[] bArr, int i, int i2) {
        int i3 = i * 2;
        try {
            byte[] bArr2 = new byte[i3];
            System.arraycopy(bArr, i2, bArr2, 0, i3);
            return new String(bArr2, f2874b);
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    public static final String a(String str, String str2, String str3) {
        int indexOf = str.indexOf(str2);
        while (indexOf != -1) {
            StringBuffer stringBuffer = new StringBuffer(str.substring(0, indexOf));
            stringBuffer.append(str3);
            stringBuffer.append(str.substring(str2.length() + indexOf));
            str = stringBuffer.toString();
            indexOf = str.indexOf(str2, indexOf + str3.length());
        }
        return str;
    }
}
