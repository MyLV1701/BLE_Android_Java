package d.p.q0;

import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class l extends s {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3002c;

    /* renamed from: d, reason: collision with root package name */
    private int f3003d;

    /* renamed from: e, reason: collision with root package name */
    private int f3004e;

    /* renamed from: f, reason: collision with root package name */
    private ArrayList f3005f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        int f3006a;

        /* renamed from: b, reason: collision with root package name */
        int f3007b;

        a(int i, int i2) {
            this.f3006a = i;
            this.f3007b = i2;
        }
    }

    static {
        d.q.c.b(l.class);
    }

    public l(v vVar) {
        super(vVar);
        this.f3005f = new ArrayList();
        byte[] a2 = a();
        d.p.d0.a(a2[0], a2[1], a2[2], a2[3]);
        this.f3003d = d.p.d0.a(a2[4], a2[5], a2[6], a2[7]);
        this.f3004e = d.p.d0.a(a2[8], a2[9], a2[10], a2[11]);
        d.p.d0.a(a2[12], a2[13], a2[14], a2[15]);
        int i = 16;
        for (int i2 = 0; i2 < this.f3003d; i2++) {
            this.f3005f.add(new a(d.p.d0.a(a2[i], a2[i + 1]), d.p.d0.a(a2[i + 2], a2[i + 3])));
            i += 4;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i, int i2) {
        this.f3005f.add(new a(i, i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.q0.s, d.p.q0.u
    public byte[] b() {
        this.f3003d = this.f3005f.size();
        int i = 16;
        this.f3002c = new byte[(this.f3003d * 4) + 16];
        d.p.d0.a(this.f3004e + 1024, this.f3002c, 0);
        d.p.d0.a(this.f3003d, this.f3002c, 4);
        d.p.d0.a(this.f3004e, this.f3002c, 8);
        d.p.d0.a(1, this.f3002c, 12);
        for (int i2 = 0; i2 < this.f3003d; i2++) {
            a aVar = (a) this.f3005f.get(i2);
            d.p.d0.b(aVar.f3006a, this.f3002c, i);
            d.p.d0.b(aVar.f3007b, this.f3002c, i + 2);
            i += 4;
        }
        return a(this.f3002c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a c(int i) {
        return (a) this.f3005f.get(i);
    }

    public l(int i, int i2) {
        super(w.i);
        this.f3004e = i;
        this.f3005f = new ArrayList();
    }
}
