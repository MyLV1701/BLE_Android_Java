package d.p.q0;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class e extends s {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f2965c;

    /* renamed from: d, reason: collision with root package name */
    private int f2966d;

    /* renamed from: e, reason: collision with root package name */
    private double f2967e;

    /* renamed from: f, reason: collision with root package name */
    private double f2968f;
    private double g;
    private double h;

    static {
        d.q.c.b(e.class);
    }

    public e(v vVar) {
        super(vVar);
        byte[] a2 = a();
        this.f2966d = d.p.d0.a(a2[0], a2[1]);
        double a3 = d.p.d0.a(a2[2], a2[3]);
        double a4 = d.p.d0.a(a2[4], a2[5]);
        Double.isNaN(a4);
        Double.isNaN(a3);
        this.f2967e = a3 + (a4 / 1024.0d);
        double a5 = d.p.d0.a(a2[6], a2[7]);
        double a6 = d.p.d0.a(a2[8], a2[9]);
        Double.isNaN(a6);
        Double.isNaN(a5);
        this.f2968f = a5 + (a6 / 256.0d);
        double a7 = d.p.d0.a(a2[10], a2[11]);
        double a8 = d.p.d0.a(a2[12], a2[13]);
        Double.isNaN(a8);
        Double.isNaN(a7);
        this.g = a7 + (a8 / 1024.0d);
        double a9 = d.p.d0.a(a2[14], a2[15]);
        double a10 = d.p.d0.a(a2[16], a2[17]);
        Double.isNaN(a10);
        Double.isNaN(a9);
        this.h = a9 + (a10 / 256.0d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.q0.s, d.p.q0.u
    public byte[] b() {
        this.f2965c = new byte[18];
        d.p.d0.b(this.f2966d, this.f2965c, 0);
        d.p.d0.b((int) this.f2967e, this.f2965c, 2);
        double d2 = this.f2967e;
        double d3 = (int) d2;
        Double.isNaN(d3);
        d.p.d0.b((int) ((d2 - d3) * 1024.0d), this.f2965c, 4);
        d.p.d0.b((int) this.f2968f, this.f2965c, 6);
        double d4 = this.f2968f;
        double d5 = (int) d4;
        Double.isNaN(d5);
        d.p.d0.b((int) ((d4 - d5) * 256.0d), this.f2965c, 8);
        d.p.d0.b((int) this.g, this.f2965c, 10);
        double d6 = this.g;
        double d7 = (int) d6;
        Double.isNaN(d7);
        d.p.d0.b((int) ((d6 - d7) * 1024.0d), this.f2965c, 12);
        d.p.d0.b((int) this.h, this.f2965c, 14);
        double d8 = this.h;
        double d9 = (int) d8;
        Double.isNaN(d9);
        d.p.d0.b((int) ((d8 - d9) * 256.0d), this.f2965c, 16);
        return a(this.f2965c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int i() {
        return this.f2966d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double j() {
        return this.f2967e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double k() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double l() {
        return this.f2968f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double m() {
        return this.h;
    }

    public e(double d2, double d3, double d4, double d5, int i) {
        super(w.o);
        this.f2967e = d2;
        this.f2968f = d3;
        this.g = d4;
        this.h = d5;
        this.f2966d = i;
    }
}
