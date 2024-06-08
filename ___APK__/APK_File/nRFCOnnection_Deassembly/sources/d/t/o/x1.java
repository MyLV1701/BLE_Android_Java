package d.t.o;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
class x1 extends d.p.n0 {
    private static int i = 8216;

    /* renamed from: c, reason: collision with root package name */
    private int f3417c;

    /* renamed from: d, reason: collision with root package name */
    private int f3418d;

    /* renamed from: e, reason: collision with root package name */
    private ArrayList f3419e;

    /* renamed from: f, reason: collision with root package name */
    private ArrayList f3420f;
    private byte[] g;
    private int h;

    public x1(int i2, int i3) {
        super(d.p.k0.p);
        this.f3417c = i2;
        this.f3418d = i3;
        this.h = 0;
        this.f3419e = new ArrayList(50);
        this.f3420f = new ArrayList(50);
    }

    public int a(String str) {
        int length = (str.length() * 2) + 3;
        if (this.h >= i - 5) {
            if (str.length() > 0) {
                return str.length();
            }
            return -1;
        }
        this.f3420f.add(new Integer(str.length()));
        int i2 = this.h;
        int i3 = length + i2;
        int i4 = i;
        if (i3 < i4) {
            this.f3419e.add(str);
            this.h += length;
            return 0;
        }
        int i5 = (i4 - 3) - i2;
        if (i5 % 2 != 0) {
            i5--;
        }
        int i6 = i5 / 2;
        this.f3419e.add(str.substring(0, i6));
        this.h += (i6 * 2) + 3;
        return str.length() - i6;
    }

    @Override // d.p.n0
    public byte[] u() {
        int i2 = 8;
        this.g = new byte[this.h + 8];
        int i3 = 0;
        d.p.d0.a(this.f3417c, this.g, 0);
        d.p.d0.a(this.f3418d, this.g, 4);
        Iterator it = this.f3419e.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            d.p.d0.b(((Integer) this.f3420f.get(i3)).intValue(), this.g, i2);
            byte[] bArr = this.g;
            bArr[i2 + 2] = 1;
            d.p.j0.b(str, bArr, i2 + 3);
            i2 += (str.length() * 2) + 3;
            i3++;
        }
        return this.g;
    }

    public int v() {
        return this.h + 8;
    }
}
