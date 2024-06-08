package d.s.a;

import d.p.c;
import java.util.ArrayList;
import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
public final class c extends d.p.c {
    private static d.q.c h = d.q.c.b(c.class);

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3196c;

    /* renamed from: d, reason: collision with root package name */
    private int[] f3197d;

    /* renamed from: e, reason: collision with root package name */
    private int[] f3198e;

    /* renamed from: f, reason: collision with root package name */
    private ArrayList f3199f;
    private c.a g;

    private c.a c(int i) {
        return (c.a) this.f3199f.get(i);
    }

    private byte[] d(int i) {
        byte[] bArr = new byte[0];
        int i2 = i;
        int i3 = 0;
        while (i3 <= this.f3197d.length && i2 != -2) {
            byte[] bArr2 = new byte[bArr.length + DfuBaseService.ERROR_REMOTE_TYPE_SECURE];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            System.arraycopy(this.f3196c, (i2 + 1) * DfuBaseService.ERROR_REMOTE_TYPE_SECURE, bArr2, bArr.length, DfuBaseService.ERROR_REMOTE_TYPE_SECURE);
            int[] iArr = this.f3197d;
            if (iArr[i2] != i2) {
                i2 = iArr[i2];
                i3++;
                bArr = bArr2;
            } else {
                throw new b(b.f3194b);
            }
        }
        if (i3 <= this.f3197d.length) {
            return bArr;
        }
        throw new b(b.f3194b);
    }

    public c.a a(String str) {
        return a(str, this.g);
    }

    public byte[] b(int i) {
        c.a c2 = c(i);
        if (c2.f2849e < 4096 && !c2.f2845a.equalsIgnoreCase("Root Entry")) {
            return b(c2);
        }
        return a(c2);
    }

    private c.a a(String str, c.a aVar) {
        int i = aVar.h;
        if (i == -1) {
            return null;
        }
        c.a c2 = c(i);
        if (c2.f2845a.equalsIgnoreCase(str)) {
            return c2;
        }
        c.a aVar2 = c2;
        do {
            int i2 = aVar2.f2850f;
            if (i2 != -1) {
                aVar2 = c(i2);
            } else {
                c.a aVar3 = c2;
                do {
                    int i3 = aVar3.g;
                    if (i3 != -1) {
                        aVar3 = c(i3);
                    } else {
                        return a(str, c2);
                    }
                } while (!aVar3.f2845a.equalsIgnoreCase(str));
                return aVar3;
            }
        } while (!aVar2.f2845a.equalsIgnoreCase(str));
        return aVar2;
    }

    private byte[] b(c.a aVar) {
        byte[] d2 = d(this.g.f2848d);
        int i = aVar.f2848d;
        byte[] bArr = new byte[0];
        int i2 = 0;
        while (i2 <= this.f3198e.length && i != -2) {
            byte[] bArr2 = new byte[bArr.length + 64];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            System.arraycopy(d2, i * 64, bArr2, bArr.length, 64);
            i = this.f3198e[i];
            if (i == -1) {
                h.b("Incorrect terminator for small block stream " + aVar.f2845a);
                i = -2;
            }
            i2++;
            bArr = bArr2;
        }
        if (i2 <= this.f3198e.length) {
            return bArr;
        }
        throw new b(b.f3194b);
    }

    private byte[] a(c.a aVar) {
        int i = aVar.f2849e;
        int i2 = i / DfuBaseService.ERROR_REMOTE_TYPE_SECURE;
        if (i % DfuBaseService.ERROR_REMOTE_TYPE_SECURE != 0) {
            i2++;
        }
        byte[] bArr = new byte[i2 * DfuBaseService.ERROR_REMOTE_TYPE_SECURE];
        int i3 = aVar.f2848d;
        int i4 = 0;
        while (i3 != -2 && i4 < i2) {
            System.arraycopy(this.f3196c, (i3 + 1) * DfuBaseService.ERROR_REMOTE_TYPE_SECURE, bArr, i4 * DfuBaseService.ERROR_REMOTE_TYPE_SECURE, DfuBaseService.ERROR_REMOTE_TYPE_SECURE);
            i4++;
            i3 = this.f3197d[i3];
        }
        if (i3 != -2 && i4 == i2) {
            h.b("Property storage size inconsistent with block chain.");
        }
        return bArr;
    }

    public int a() {
        return this.f3199f.size();
    }

    public c.a a(int i) {
        return c(i);
    }
}
