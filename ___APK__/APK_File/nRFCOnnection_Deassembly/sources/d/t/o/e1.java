package d.t.o;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/* loaded from: classes.dex */
public abstract class e1 extends j {
    private static DecimalFormat n = new DecimalFormat("#.###");
    private double l;
    private NumberFormat m;

    /* JADX INFO: Access modifiers changed from: protected */
    public e1(int i, int i2, double d2, d.r.d dVar) {
        super(d.p.k0.t, i, i2, dVar);
        this.l = d2;
    }

    @Override // d.a
    public String c() {
        if (this.m == null) {
            this.m = ((d.p.p0) d()).z();
            if (this.m == null) {
                this.m = n;
            }
        }
        return this.m.format(this.l);
    }

    @Override // d.a
    public d.d getType() {
        return d.d.f2810d;
    }

    @Override // d.t.o.j, d.p.n0
    public byte[] u() {
        byte[] u = super.u();
        byte[] bArr = new byte[u.length + 8];
        System.arraycopy(u, 0, bArr, 0, u.length);
        d.p.u.a(this.l, bArr, u.length);
        return bArr;
    }

    public double z() {
        return this.l;
    }
}
