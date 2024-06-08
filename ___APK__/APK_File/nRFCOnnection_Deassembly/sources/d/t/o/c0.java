package d.t.o;

/* loaded from: classes.dex */
class c0 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private String f3247c;

    public c0(String str) {
        super(d.p.k0.E0);
        d.q.c.b(c0.class);
        this.f3247c = str;
    }

    @Override // d.p.n0
    public byte[] u() {
        byte[] bArr = new byte[(this.f3247c.length() * 2) + 12];
        bArr[6] = (byte) this.f3247c.length();
        bArr[7] = 1;
        d.p.j0.b(this.f3247c, bArr, 8);
        int length = (this.f3247c.length() * 2) + 8;
        bArr[length] = 2;
        bArr[length + 1] = 0;
        bArr[length + 2] = 28;
        bArr[length + 3] = 23;
        return bArr;
    }
}
