package d.p.q0;

import d.p.n0;

/* loaded from: classes.dex */
public class l0 extends n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3008c;

    /* renamed from: d, reason: collision with root package name */
    private int f3009d;

    static {
        d.q.c.b(l0.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public l0(String str) {
        super(d.p.k0.l);
        this.f3009d = str.length();
    }

    @Override // d.p.n0
    public byte[] u() {
        byte[] bArr = this.f3008c;
        if (bArr != null) {
            return bArr;
        }
        this.f3008c = new byte[18];
        d.p.d0.b(530, this.f3008c, 0);
        d.p.d0.b(this.f3009d, this.f3008c, 10);
        d.p.d0.b(16, this.f3008c, 12);
        return this.f3008c;
    }
}
