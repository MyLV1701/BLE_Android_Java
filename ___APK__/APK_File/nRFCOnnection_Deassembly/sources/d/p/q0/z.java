package d.p.q0;

import d.p.n0;

/* loaded from: classes.dex */
public class z extends n0 {

    /* renamed from: c, reason: collision with root package name */
    private boolean f3046c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f3047d;

    static {
        d.q.c.b(z.class);
    }

    public z(byte[] bArr) {
        super(d.p.k0.y0);
        this.f3047d = bArr;
        this.f3046c = false;
    }

    @Override // d.p.h0
    public d.s.a.e t() {
        return super.t();
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3047d;
    }

    public boolean v() {
        return this.f3046c;
    }
}
