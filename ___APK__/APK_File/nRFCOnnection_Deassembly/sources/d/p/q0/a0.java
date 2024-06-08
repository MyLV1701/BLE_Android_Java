package d.p.q0;

import d.p.n0;

/* loaded from: classes.dex */
public class a0 extends n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f2929c;

    /* renamed from: d, reason: collision with root package name */
    private int f2930d;

    /* renamed from: e, reason: collision with root package name */
    private int f2931e;

    /* renamed from: f, reason: collision with root package name */
    private int f2932f;

    static {
        d.q.c.b(a0.class);
    }

    public a0(int i, int i2, int i3) {
        super(d.p.k0.k);
        this.f2930d = i2;
        this.f2931e = i;
        this.f2932f = i3;
    }

    @Override // d.p.n0
    public byte[] u() {
        byte[] bArr = this.f2929c;
        if (bArr != null) {
            return bArr;
        }
        this.f2929c = new byte[12];
        d.p.d0.b(this.f2930d, this.f2929c, 0);
        d.p.d0.b(this.f2931e, this.f2929c, 2);
        d.p.d0.b(this.f2932f, this.f2929c, 6);
        d.p.d0.b(0, this.f2929c, 8);
        return this.f2929c;
    }
}
