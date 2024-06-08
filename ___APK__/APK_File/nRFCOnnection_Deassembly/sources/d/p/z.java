package d.p;

/* loaded from: classes.dex */
public class z extends n0 implements t, d.r.g {

    /* renamed from: c, reason: collision with root package name */
    private boolean f3146c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f3147d;

    /* renamed from: e, reason: collision with root package name */
    private int f3148e;

    /* renamed from: f, reason: collision with root package name */
    private String f3149f;
    private boolean g;
    private boolean h;

    /* loaded from: classes.dex */
    private static class b {
        private b() {
        }
    }

    static {
        d.q.c.b(z.class);
        String[] strArr = {"dd", "mm", "yy", "hh", "ss", "m/", "/d"};
        new b();
        new b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public z() {
        super(k0.x);
        this.f3146c = false;
    }

    @Override // d.p.t
    public void a(int i) {
        this.f3148e = i;
        this.f3146c = true;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof z)) {
            return false;
        }
        z zVar = (z) obj;
        if (this.f3146c && zVar.f3146c) {
            if (this.g == zVar.g && this.h == zVar.h) {
                return this.f3149f.equals(zVar.f3149f);
            }
            return false;
        }
        return this.f3149f.equals(zVar.f3149f);
    }

    @Override // d.p.t
    public boolean g() {
        return false;
    }

    @Override // d.p.t
    public int h() {
        return this.f3148e;
    }

    public int hashCode() {
        return this.f3149f.hashCode();
    }

    @Override // d.p.t
    public boolean i() {
        return this.f3146c;
    }

    @Override // d.p.n0
    public byte[] u() {
        this.f3147d = new byte[(this.f3149f.length() * 2) + 3 + 2];
        d0.b(this.f3148e, this.f3147d, 0);
        d0.b(this.f3149f.length(), this.f3147d, 2);
        byte[] bArr = this.f3147d;
        bArr[4] = 1;
        j0.b(this.f3149f, bArr, 5);
        return this.f3147d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String a(String str, String str2, String str3) {
        int indexOf = str.indexOf(str2);
        while (indexOf != -1) {
            StringBuffer stringBuffer = new StringBuffer(str.substring(0, indexOf));
            stringBuffer.append(str3);
            stringBuffer.append(str.substring(indexOf + str2.length()));
            str = stringBuffer.toString();
            indexOf = str.indexOf(str2);
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(String str) {
        this.f3149f = str;
    }
}
