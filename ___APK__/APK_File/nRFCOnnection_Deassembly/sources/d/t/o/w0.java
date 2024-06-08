package d.t.o;

/* loaded from: classes.dex */
class w0 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte f3405c;

    /* renamed from: d, reason: collision with root package name */
    private byte f3406d;

    /* renamed from: e, reason: collision with root package name */
    private byte[] f3407e;

    public w0(int i, int i2) {
        super(d.p.k0.j0);
        this.f3405c = (byte) i;
        this.f3406d = (byte) i2;
        this.f3407e = new byte[2];
        byte[] bArr = this.f3407e;
        bArr[0] = this.f3405c;
        bArr[1] = this.f3406d;
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3407e;
    }
}
