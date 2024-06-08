package d.t.o;

import java.util.List;

/* loaded from: classes.dex */
class b1 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private int f3239c;

    /* renamed from: d, reason: collision with root package name */
    private int f3240d;

    /* renamed from: e, reason: collision with root package name */
    private int f3241e;

    /* renamed from: f, reason: collision with root package name */
    private int[] f3242f;
    private int[] g;

    public b1(List list) {
        super(d.p.k0.m);
        this.f3239c = ((d.t.e) list.get(0)).e();
        this.f3240d = ((d.t.e) list.get(0)).f();
        this.f3241e = (this.f3240d + list.size()) - 1;
        this.f3242f = new int[list.size()];
        this.g = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            this.f3242f[i] = (int) ((d.t.e) list.get(i)).z();
            this.g[i] = ((j) list.get(i)).w();
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        byte[] bArr = new byte[(this.f3242f.length * 6) + 6];
        d.p.d0.b(this.f3239c, bArr, 0);
        d.p.d0.b(this.f3240d, bArr, 2);
        int i = 4;
        for (int i2 = 0; i2 < this.f3242f.length; i2++) {
            d.p.d0.b(this.g[i2], bArr, i);
            d.p.d0.a((this.f3242f[i2] << 2) | 2, bArr, i + 2);
            i += 6;
        }
        d.p.d0.b(this.f3241e, bArr, i);
        return bArr;
    }
}
