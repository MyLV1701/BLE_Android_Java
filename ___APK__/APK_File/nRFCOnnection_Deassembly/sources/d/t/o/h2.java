package d.t.o;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class h2 extends d.p.n0 {
    private static d.q.c i = d.q.c.b(h2.class);
    public static final b j;
    public static final b k;
    public static final b l;

    /* renamed from: c, reason: collision with root package name */
    private b f3301c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f3302d;

    /* renamed from: e, reason: collision with root package name */
    private int f3303e;

    /* renamed from: f, reason: collision with root package name */
    private String f3304f;
    private String[] g;
    private d.o h;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {
        private b() {
        }
    }

    static {
        j = new b();
        k = new b();
        l = new b();
        new b();
        new b();
    }

    public h2() {
        super(d.p.k0.f2887f);
        this.f3301c = l;
    }

    private void x() {
        this.f3302d = new byte[]{1, 0, 1, 58};
    }

    private void y() {
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < this.f3303e; i4++) {
            i3 += this.g[i4].length();
        }
        byte[] a2 = d.p.w.a(this.f3304f, this.h);
        int length = a2.length + 6;
        int i5 = this.f3303e;
        this.f3302d = new byte[length + (i5 * 3) + (i3 * 2)];
        d.p.d0.b(i5, this.f3302d, 0);
        d.p.d0.b(a2.length + 1, this.f3302d, 2);
        byte[] bArr = this.f3302d;
        bArr[4] = 0;
        bArr[5] = 1;
        System.arraycopy(a2, 0, bArr, 6, a2.length);
        int length2 = a2.length + 4 + 2;
        while (true) {
            String[] strArr = this.g;
            if (i2 >= strArr.length) {
                return;
            }
            d.p.d0.b(strArr[i2].length(), this.f3302d, length2);
            byte[] bArr2 = this.f3302d;
            bArr2[length2 + 2] = 1;
            d.p.j0.b(this.g[i2], bArr2, length2 + 3);
            length2 += (this.g[i2].length() * 2) + 3;
            i2++;
        }
    }

    private void z() {
        this.f3302d = new byte[4];
        d.p.d0.b(this.f3303e, this.f3302d, 0);
        byte[] bArr = this.f3302d;
        bArr[2] = 1;
        bArr[3] = 4;
        this.f3301c = j;
    }

    public int a(String str) {
        int i2 = 0;
        boolean z = false;
        while (true) {
            String[] strArr = this.g;
            if (i2 >= strArr.length || z) {
                break;
            }
            if (strArr[i2].equals(str)) {
                z = true;
            }
            i2++;
        }
        if (z) {
            return 0;
        }
        String[] strArr2 = this.g;
        String[] strArr3 = new String[strArr2.length + 1];
        System.arraycopy(strArr2, 0, strArr3, 0, strArr2.length);
        strArr3[this.g.length] = str;
        this.g = strArr3;
        return this.g.length - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(int i2) {
        d.q.a.a(this.f3301c == j);
        this.f3303e = i2;
        z();
    }

    public String c(int i2) {
        return this.g[i2];
    }

    public b getType() {
        return this.f3301c;
    }

    @Override // d.p.n0
    public byte[] u() {
        b bVar = this.f3301c;
        if (bVar == j) {
            z();
        } else if (bVar == k) {
            y();
        } else if (bVar == l) {
            x();
        } else {
            i.b("unsupported supbook type - defaulting to internal");
            z();
        }
        return this.f3302d;
    }

    public String v() {
        return this.f3304f;
    }

    public int w() {
        return this.f3303e;
    }

    public h2(int i2, d.o oVar) {
        super(d.p.k0.f2887f);
        this.f3303e = i2;
        this.f3301c = j;
        this.h = oVar;
    }

    public h2(String str, d.o oVar) {
        super(d.p.k0.f2887f);
        this.f3304f = str;
        this.f3303e = 1;
        this.g = new String[0];
        this.h = oVar;
        this.f3301c = k;
    }
}
