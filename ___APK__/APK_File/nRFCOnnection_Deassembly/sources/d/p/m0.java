package d.p;

import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
public class m0 extends n0 {

    /* renamed from: c, reason: collision with root package name */
    private int f2891c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f2892d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f2893e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f2894f;

    static {
        d.q.c.b(m0.class);
    }

    public m0() {
        super(k0.F);
        this.f2891c = 1217;
    }

    public void a(boolean z) {
        this.f2892d = true;
    }

    public void b(boolean z) {
        this.f2894f = z;
    }

    public void c(boolean z) {
        this.f2892d = true;
    }

    @Override // d.p.n0
    public byte[] u() {
        byte[] bArr = new byte[2];
        if (this.f2894f) {
            this.f2891c |= 256;
        }
        if (this.f2892d) {
            this.f2891c |= 1024;
        }
        if (this.f2893e) {
            this.f2891c |= DfuBaseService.ERROR_REMOTE_TYPE_SECURE_BUTTONLESS;
        }
        d0.b(this.f2891c, bArr, 0);
        return bArr;
    }
}
