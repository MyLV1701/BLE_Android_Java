package d.t.o;

import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
class j1 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3322c;

    public j1(String str) {
        super(d.p.k0.Z);
        if (str == null) {
            this.f3322c = new byte[2];
            d.p.d0.b(0, this.f3322c, 0);
            return;
        }
        byte[] bytes = str.getBytes();
        int i = 0;
        int i2 = 0;
        while (i < bytes.length) {
            byte b2 = bytes[i];
            i++;
            i2 ^= a(b2, i);
        }
        int length = (bytes.length ^ i2) ^ 52811;
        this.f3322c = new byte[2];
        d.p.d0.b(length, this.f3322c, 0);
    }

    private int a(int i, int i2) {
        int i3 = i & 32767;
        while (i2 > 0) {
            i3 = (i3 & DfuBaseService.ERROR_CONNECTION_MASK) != 0 ? ((i3 << 1) & 32767) + 1 : (i3 << 1) & 32767;
            i2--;
        }
        return i3;
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3322c;
    }

    public j1(int i) {
        super(d.p.k0.Z);
        this.f3322c = new byte[2];
        d.p.d0.b(i, this.f3322c, 0);
    }
}
