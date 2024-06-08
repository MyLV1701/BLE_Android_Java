package d.t.o;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
class w1 extends d.p.n0 {
    private static int j = 8224;

    /* renamed from: c, reason: collision with root package name */
    private String f3408c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f3409d;

    /* renamed from: e, reason: collision with root package name */
    private int f3410e;

    /* renamed from: f, reason: collision with root package name */
    private ArrayList f3411f;
    private ArrayList g;
    private byte[] h;
    private int i;

    public w1() {
        super(d.p.k0.r);
        this.i = 0;
        this.f3411f = new ArrayList(50);
        this.g = new ArrayList(50);
    }

    public int a(String str, boolean z) {
        int length;
        this.f3409d = z;
        this.f3410e = str.length();
        if (!this.f3409d) {
            length = (str.length() * 2) + 1;
        } else {
            length = (str.length() * 2) + 3;
        }
        int i = j;
        if (length <= i) {
            this.f3408c = str;
            this.i += length;
            return 0;
        }
        int i2 = (this.f3409d ? i - 4 : i - 2) / 2;
        this.f3408c = str.substring(0, i2);
        this.i = j - 1;
        return str.length() - i2;
    }

    @Override // d.p.n0
    public byte[] u() {
        int i;
        this.h = new byte[this.i];
        int i2 = 0;
        if (this.f3409d) {
            d.p.d0.b(this.f3410e, this.h, 0);
            this.h[2] = 1;
            i = 3;
        } else {
            this.h[0] = 1;
            i = 1;
        }
        d.p.j0.b(this.f3408c, this.h, i);
        int length = i + (this.f3408c.length() * 2);
        Iterator it = this.f3411f.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            d.p.d0.b(((Integer) this.g.get(i2)).intValue(), this.h, length);
            byte[] bArr = this.h;
            bArr[length + 2] = 1;
            d.p.j0.b(str, bArr, length + 3);
            length += (str.length() * 2) + 3;
            i2++;
        }
        return this.h;
    }

    public int v() {
        return this.i;
    }

    public int a(String str) {
        int length = (str.length() * 2) + 3;
        if (this.i >= j - 5) {
            return str.length();
        }
        this.g.add(new Integer(str.length()));
        int i = this.i;
        int i2 = length + i;
        int i3 = j;
        if (i2 < i3) {
            this.f3411f.add(str);
            this.i += length;
            return 0;
        }
        int i4 = (i3 - 3) - i;
        if (i4 % 2 != 0) {
            i4--;
        }
        int i5 = i4 / 2;
        this.f3411f.add(str.substring(0, i5));
        this.i += (i5 * 2) + 3;
        return str.length() - i5;
    }
}
