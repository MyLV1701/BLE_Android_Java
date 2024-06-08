package d.p.q0;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b extends s {

    /* renamed from: c, reason: collision with root package name */
    private c f2933c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f2934d;

    /* renamed from: e, reason: collision with root package name */
    private int f2935e;

    /* renamed from: f, reason: collision with root package name */
    private int f2936f;
    private boolean g;

    static {
        d.q.c.b(b.class);
    }

    public b(v vVar) {
        super(vVar);
        this.f2933c = c.a(d());
        this.g = false;
        byte[] a2 = a();
        this.f2936f = d.p.d0.a(a2[24], a2[25], a2[26], a2[27]);
    }

    @Override // d.p.q0.s, d.p.q0.u
    public byte[] b() {
        if (this.g) {
            this.f2934d[0] = (byte) this.f2933c.a();
            this.f2934d[1] = (byte) this.f2933c.a();
            d.p.d0.a(this.f2935e + 8 + 17, this.f2934d, 20);
            d.p.d0.a(this.f2936f, this.f2934d, 24);
            d.p.d0.a(0, this.f2934d, 28);
            byte[] bArr = this.f2934d;
            bArr[32] = 0;
            bArr[33] = 0;
            bArr[34] = 126;
            bArr[35] = 1;
            bArr[36] = 0;
            bArr[37] = 110;
            d.p.d0.b(61470, bArr, 38);
            d.p.d0.a(this.f2935e + 17, this.f2934d, 40);
        } else {
            this.f2934d = a();
        }
        return a(this.f2934d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] i() {
        byte[] a2 = a();
        byte[] bArr = new byte[a2.length - 61];
        System.arraycopy(a2, 61, bArr, 0, bArr.length);
        return bArr;
    }

    public b(n nVar) {
        super(w.j);
        this.f2933c = c.f2946d;
        b(2);
        a(this.f2933c.a());
        byte[] h = nVar.h();
        this.f2935e = h.length;
        int i = this.f2935e;
        this.f2934d = new byte[i + 61];
        System.arraycopy(h, 0, this.f2934d, 61, i);
        this.f2936f = nVar.k();
        this.g = true;
    }
}
