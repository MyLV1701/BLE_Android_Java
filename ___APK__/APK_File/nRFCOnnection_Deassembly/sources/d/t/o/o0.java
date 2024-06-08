package d.t.o;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class o0 extends d.p.n0 {
    private static final b n;
    private static final b o;
    private static final b p;
    private static final b q;

    /* renamed from: c, reason: collision with root package name */
    private int f3354c;

    /* renamed from: d, reason: collision with root package name */
    private int f3355d;

    /* renamed from: e, reason: collision with root package name */
    private int f3356e;

    /* renamed from: f, reason: collision with root package name */
    private int f3357f;
    private URL g;
    private File h;
    private String i;
    private String j;
    private b k;
    private byte[] l;
    private boolean m;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {
        private b() {
        }
    }

    static {
        d.q.c.b(o0.class);
        n = new b();
        o = new b();
        p = new b();
        q = new b();
        new b();
    }

    private byte[] a(byte[] bArr) {
        char charAt;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.add(this.h.getName());
        arrayList2.add(a(this.h.getName()));
        for (File parentFile = this.h.getParentFile(); parentFile != null; parentFile = parentFile.getParentFile()) {
            arrayList.add(parentFile.getName());
            arrayList2.add(a(parentFile.getName()));
        }
        int size = arrayList.size() - 1;
        boolean z = true;
        int i = 0;
        while (z) {
            if (((String) arrayList.get(size)).equals("..")) {
                i++;
                arrayList.remove(size);
                arrayList2.remove(size);
            } else {
                z = false;
            }
            size--;
        }
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        if (this.h.getPath().charAt(1) == ':' && (charAt = this.h.getPath().charAt(0)) != 'C' && charAt != 'c') {
            stringBuffer.append(charAt);
            stringBuffer.append(':');
            stringBuffer2.append(charAt);
            stringBuffer2.append(':');
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            stringBuffer.append((String) arrayList.get(size2));
            stringBuffer2.append((String) arrayList2.get(size2));
            if (size2 != 0) {
                stringBuffer.append("\\");
                stringBuffer2.append("\\");
            }
        }
        String stringBuffer3 = stringBuffer.toString();
        String stringBuffer4 = stringBuffer2.toString();
        int length = bArr.length + 4 + stringBuffer4.length() + 1 + 16 + 2 + 8 + ((stringBuffer3.length() + 1) * 2) + 24;
        String str = this.j;
        if (str != null) {
            length += ((str.length() + 1) * 2) + 4;
        }
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        int length2 = bArr.length;
        String str2 = this.j;
        if (str2 != null) {
            d.p.d0.a(str2.length() + 1, bArr2, length2);
            d.p.j0.b(this.j, bArr2, length2 + 4);
            length2 += ((this.j.length() + 1) * 2) + 4;
        }
        bArr2[length2] = 3;
        bArr2[length2 + 1] = 3;
        bArr2[length2 + 2] = 0;
        bArr2[length2 + 3] = 0;
        bArr2[length2 + 4] = 0;
        bArr2[length2 + 5] = 0;
        bArr2[length2 + 6] = 0;
        bArr2[length2 + 7] = 0;
        bArr2[length2 + 8] = -64;
        bArr2[length2 + 9] = 0;
        bArr2[length2 + 10] = 0;
        bArr2[length2 + 11] = 0;
        bArr2[length2 + 12] = 0;
        bArr2[length2 + 13] = 0;
        bArr2[length2 + 14] = 0;
        bArr2[length2 + 15] = 70;
        int i2 = length2 + 16;
        d.p.d0.b(i, bArr2, i2);
        int i3 = i2 + 2;
        d.p.d0.a(stringBuffer4.length() + 1, bArr2, i3);
        d.p.j0.a(stringBuffer4, bArr2, i3 + 4);
        int length3 = i3 + stringBuffer4.length() + 1 + 4;
        bArr2[length3] = -1;
        bArr2[length3 + 1] = -1;
        bArr2[length3 + 2] = -83;
        bArr2[length3 + 3] = -34;
        bArr2[length3 + 4] = 0;
        bArr2[length3 + 5] = 0;
        bArr2[length3 + 6] = 0;
        bArr2[length3 + 7] = 0;
        bArr2[length3 + 8] = 0;
        bArr2[length3 + 9] = 0;
        bArr2[length3 + 10] = 0;
        bArr2[length3 + 11] = 0;
        bArr2[length3 + 12] = 0;
        bArr2[length3 + 13] = 0;
        bArr2[length3 + 14] = 0;
        bArr2[length3 + 15] = 0;
        bArr2[length3 + 16] = 0;
        bArr2[length3 + 17] = 0;
        bArr2[length3 + 18] = 0;
        bArr2[length3 + 19] = 0;
        bArr2[length3 + 20] = 0;
        bArr2[length3 + 21] = 0;
        bArr2[length3 + 22] = 0;
        bArr2[length3 + 23] = 0;
        int i4 = length3 + 24;
        d.p.d0.a((stringBuffer3.length() * 2) + 6, bArr2, i4);
        int i5 = i4 + 4;
        d.p.d0.a(stringBuffer3.length() * 2, bArr2, i5);
        int i6 = i5 + 4;
        bArr2[i6] = 3;
        bArr2[i6 + 1] = 0;
        d.p.j0.b(stringBuffer3, bArr2, i6 + 2);
        stringBuffer3.length();
        return bArr2;
    }

    private byte[] b(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 4 + ((this.i.length() + 1) * 2)];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        int length = bArr.length;
        d.p.d0.a(this.i.length() + 1, bArr2, length);
        d.p.j0.b(this.i, bArr2, length + 4);
        return bArr2;
    }

    private byte[] c(byte[] bArr) {
        String path = this.h.getPath();
        byte[] bArr2 = new byte[bArr.length + (path.length() * 2) + 2 + 4];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        int length = bArr.length;
        d.p.d0.a(path.length() + 1, bArr2, length);
        d.p.j0.b(path, bArr2, length + 4);
        return bArr2;
    }

    private byte[] d(byte[] bArr) {
        String url = this.g.toString();
        int length = bArr.length + 20 + ((url.length() + 1) * 2);
        String str = this.j;
        if (str != null) {
            length += ((str.length() + 1) * 2) + 4;
        }
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        int length2 = bArr.length;
        String str2 = this.j;
        if (str2 != null) {
            d.p.d0.a(str2.length() + 1, bArr2, length2);
            d.p.j0.b(this.j, bArr2, length2 + 4);
            length2 += ((this.j.length() + 1) * 2) + 4;
        }
        bArr2[length2] = -32;
        bArr2[length2 + 1] = -55;
        bArr2[length2 + 2] = -22;
        bArr2[length2 + 3] = 121;
        bArr2[length2 + 4] = -7;
        bArr2[length2 + 5] = -70;
        bArr2[length2 + 6] = -50;
        bArr2[length2 + 7] = 17;
        bArr2[length2 + 8] = -116;
        bArr2[length2 + 9] = -126;
        bArr2[length2 + 10] = 0;
        bArr2[length2 + 11] = -86;
        bArr2[length2 + 12] = 0;
        bArr2[length2 + 13] = 75;
        bArr2[length2 + 14] = -87;
        bArr2[length2 + 15] = 11;
        d.p.d0.a((url.length() + 1) * 2, bArr2, length2 + 16);
        d.p.j0.b(url, bArr2, length2 + 20);
        return bArr2;
    }

    public String toString() {
        if (v()) {
            return this.h.toString();
        }
        if (y()) {
            return this.g.toString();
        }
        return x() ? this.h.toString() : "";
    }

    @Override // d.p.n0
    public byte[] u() {
        if (!this.m) {
            return this.l;
        }
        int i = 0;
        d.p.d0.b(this.f3354c, r0, 0);
        d.p.d0.b(this.f3355d, r0, 2);
        d.p.d0.b(this.f3356e, r0, 4);
        d.p.d0.b(this.f3357f, r0, 6);
        byte[] bArr = {0, 0, 0, 0, 0, 0, 0, 0, -48, -55, -22, 121, -7, -70, -50, 17, -116, -126, 0, -86, 0, 75, -87, 11, 2, 0, 0, 0};
        if (y()) {
            i = 3;
            if (this.j != null) {
                i = 23;
            }
        } else if (v()) {
            i = 1;
            if (this.j != null) {
                i = 21;
            }
        } else if (w()) {
            i = 8;
        } else if (x()) {
            i = 259;
        }
        d.p.d0.a(i, bArr, 28);
        if (y()) {
            this.l = d(bArr);
        } else if (v()) {
            this.l = a(bArr);
        } else if (w()) {
            this.l = b(bArr);
        } else if (x()) {
            this.l = c(bArr);
        }
        return this.l;
    }

    public boolean v() {
        return this.k == o;
    }

    public boolean w() {
        return this.k == q;
    }

    public boolean x() {
        return this.k == p;
    }

    public boolean y() {
        return this.k == n;
    }

    private String a(String str) {
        String substring;
        int indexOf = str.indexOf(46);
        if (indexOf == -1) {
            substring = "";
        } else {
            String substring2 = str.substring(0, indexOf);
            substring = str.substring(indexOf + 1);
            str = substring2;
        }
        if (str.length() > 8) {
            str = (str.substring(0, 6) + "~" + (str.length() - 8)).substring(0, 8);
        }
        String substring3 = substring.substring(0, Math.min(3, substring.length()));
        if (substring3.length() <= 0) {
            return str;
        }
        return str + '.' + substring3;
    }
}
