package d.t.o;

import no.nordicsemi.android.dfu.DfuBaseService;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class q2 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3374c;

    public q2(d.m mVar) {
        super(d.p.k0.c0);
        int i = (mVar.J() ? 2 : 0) | 4 | 0;
        int i2 = (mVar.h() ? i | 16 : i) | 32 | 128;
        i2 = (mVar.p() == 0 && mVar.L() == 0) ? i2 : i2 | 8 | 256;
        i2 = mVar.R() ? i2 | 1536 : i2;
        i2 = mVar.v() ? i2 | DfuBaseService.ERROR_REMOTE_TYPE_SECURE_BUTTONLESS : i2;
        this.f3374c = new byte[18];
        d.p.d0.b(i2, this.f3374c, 0);
        d.p.d0.b(64, this.f3374c, 6);
        d.p.d0.b(mVar.u(), this.f3374c, 10);
        d.p.d0.b(mVar.s(), this.f3374c, 12);
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3374c;
    }
}
