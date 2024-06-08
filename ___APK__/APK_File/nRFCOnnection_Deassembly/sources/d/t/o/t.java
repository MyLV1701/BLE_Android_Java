package d.t.o;

import java.util.Date;

/* loaded from: classes.dex */
public abstract class t extends j {
    static final d.t.i n;
    private double l;
    private Date m;

    static {
        d.q.c.b(t.class);
        n = new d.t.i(d.t.c.f3208b);
    }

    @Override // d.a
    public String c() {
        return this.m.toString();
    }

    @Override // d.a
    public d.d getType() {
        return d.d.f2812f;
    }

    @Override // d.t.o.j, d.p.n0
    public byte[] u() {
        byte[] u = super.u();
        byte[] bArr = new byte[u.length + 8];
        System.arraycopy(u, 0, bArr, 0, u.length);
        d.p.u.a(this.l, bArr, u.length);
        return bArr;
    }
}
