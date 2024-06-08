package d;

import d.p.r0.y;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.AppearanceLibrary;

/* loaded from: classes.dex */
public final class o {
    private static d.q.c u = d.q.c.b(o.class);

    /* renamed from: c, reason: collision with root package name */
    private boolean f2822c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f2823d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f2824e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f2825f;
    private boolean g;
    private File h;
    private Locale i;
    private y j;
    private String k;
    private boolean q;
    private String s;

    /* renamed from: a, reason: collision with root package name */
    private int f2820a = 5242880;

    /* renamed from: b, reason: collision with root package name */
    private int f2821b = AppearanceLibrary.MASK_BEACON;
    private HashMap n = new HashMap();
    private String l = d.p.n.f2897e.a();
    private String m = d.p.n.f2898f.a();
    private boolean o = false;
    private boolean p = false;
    private boolean r = false;
    private int t = 0;

    /* JADX WARN: Removed duplicated region for block: B:16:0x00ce A[Catch: SecurityException -> 0x00d5, TRY_LEAVE, TryCatch #0 {SecurityException -> 0x00d5, blocks: (B:8:0x00a5, B:10:0x00ab, B:13:0x00b2, B:14:0x00c8, B:16:0x00ce, B:21:0x00c2), top: B:7:0x00a5 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public o() {
        /*
            r6 = this;
            java.lang.String r0 = "jxl.encoding"
            java.lang.String r1 = "jxl.country"
            java.lang.String r2 = "jxl.lang"
            java.lang.String r3 = "Error accessing system properties."
            r6.<init>()
            r4 = 0
            r6.q = r4
            r5 = 5242880(0x500000, float:7.34684E-39)
            r6.f2820a = r5
            r5 = 1048576(0x100000, float:1.469368E-39)
            r6.f2821b = r5
            java.util.HashMap r5 = new java.util.HashMap
            r5.<init>()
            r6.n = r5
            d.p.n r5 = d.p.n.f2897e
            java.lang.String r5 = r5.a()
            r6.l = r5
            d.p.n r5 = d.p.n.f2898f
            java.lang.String r5 = r5.a()
            r6.m = r5
            r6.o = r4
            r6.p = r4
            r6.q = r4
            r6.r = r4
            r6.t = r4
            java.lang.String r4 = "jxl.nowarnings"
            boolean r4 = java.lang.Boolean.getBoolean(r4)     // Catch: java.lang.SecurityException -> L9f
            r6.a(r4)     // Catch: java.lang.SecurityException -> L9f
            java.lang.String r4 = "jxl.nodrawings"
            boolean r4 = java.lang.Boolean.getBoolean(r4)     // Catch: java.lang.SecurityException -> L9f
            r6.f2822c = r4     // Catch: java.lang.SecurityException -> L9f
            java.lang.String r4 = "jxl.nonames"
            java.lang.Boolean.getBoolean(r4)     // Catch: java.lang.SecurityException -> L9f
            java.lang.String r4 = "jxl.nogc"
            boolean r4 = java.lang.Boolean.getBoolean(r4)     // Catch: java.lang.SecurityException -> L9f
            r6.f2823d = r4     // Catch: java.lang.SecurityException -> L9f
            java.lang.String r4 = "jxl.norat"
            boolean r4 = java.lang.Boolean.getBoolean(r4)     // Catch: java.lang.SecurityException -> L9f
            r6.f2824e = r4     // Catch: java.lang.SecurityException -> L9f
            java.lang.String r4 = "jxl.nomergedcellchecks"
            boolean r4 = java.lang.Boolean.getBoolean(r4)     // Catch: java.lang.SecurityException -> L9f
            r6.f2825f = r4     // Catch: java.lang.SecurityException -> L9f
            java.lang.String r4 = "jxl.noformulaadjust"
            java.lang.Boolean.getBoolean(r4)     // Catch: java.lang.SecurityException -> L9f
            java.lang.String r4 = "jxl.nopropertysets"
            java.lang.Boolean.getBoolean(r4)     // Catch: java.lang.SecurityException -> L9f
            java.lang.String r4 = "jxl.ignoreblanks"
            java.lang.Boolean.getBoolean(r4)     // Catch: java.lang.SecurityException -> L9f
            java.lang.String r4 = "jxl.nocellvalidation"
            java.lang.Boolean.getBoolean(r4)     // Catch: java.lang.SecurityException -> L9f
            java.lang.String r4 = "jxl.autofilter"
            boolean r4 = java.lang.Boolean.getBoolean(r4)     // Catch: java.lang.SecurityException -> L9f
            java.lang.String r4 = "jxl.usetemporaryfileduringwrite"
            boolean r4 = java.lang.Boolean.getBoolean(r4)     // Catch: java.lang.SecurityException -> L9f
            r6.g = r4     // Catch: java.lang.SecurityException -> L9f
            java.lang.String r4 = "jxl.temporaryfileduringwritedirectory"
            java.lang.String r4 = java.lang.System.getProperty(r4)     // Catch: java.lang.SecurityException -> L9f
            if (r4 == 0) goto L96
            java.io.File r5 = new java.io.File     // Catch: java.lang.SecurityException -> L9f
            r5.<init>(r4)     // Catch: java.lang.SecurityException -> L9f
            r6.h = r5     // Catch: java.lang.SecurityException -> L9f
        L96:
            java.lang.String r4 = "file.encoding"
            java.lang.String r4 = java.lang.System.getProperty(r4)     // Catch: java.lang.SecurityException -> L9f
            r6.k = r4     // Catch: java.lang.SecurityException -> L9f
            goto La5
        L9f:
            r4 = move-exception
            d.q.c r5 = d.o.u
            r5.a(r3, r4)
        La5:
            java.lang.String r4 = java.lang.System.getProperty(r2)     // Catch: java.lang.SecurityException -> Ld5
            if (r4 == 0) goto Lc2
            java.lang.String r4 = java.lang.System.getProperty(r1)     // Catch: java.lang.SecurityException -> Ld5
            if (r4 != 0) goto Lb2
            goto Lc2
        Lb2:
            java.util.Locale r4 = new java.util.Locale     // Catch: java.lang.SecurityException -> Ld5
            java.lang.String r2 = java.lang.System.getProperty(r2)     // Catch: java.lang.SecurityException -> Ld5
            java.lang.String r1 = java.lang.System.getProperty(r1)     // Catch: java.lang.SecurityException -> Ld5
            r4.<init>(r2, r1)     // Catch: java.lang.SecurityException -> Ld5
            r6.i = r4     // Catch: java.lang.SecurityException -> Ld5
            goto Lc8
        Lc2:
            java.util.Locale r1 = java.util.Locale.getDefault()     // Catch: java.lang.SecurityException -> Ld5
            r6.i = r1     // Catch: java.lang.SecurityException -> Ld5
        Lc8:
            java.lang.String r1 = java.lang.System.getProperty(r0)     // Catch: java.lang.SecurityException -> Ld5
            if (r1 == 0) goto Le1
            java.lang.String r0 = java.lang.System.getProperty(r0)     // Catch: java.lang.SecurityException -> Ld5
            r6.k = r0     // Catch: java.lang.SecurityException -> Ld5
            goto Le1
        Ld5:
            r0 = move-exception
            d.q.c r1 = d.o.u
            r1.a(r3, r0)
            java.util.Locale r0 = java.util.Locale.getDefault()
            r6.i = r0
        Le1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: d.o.<init>():void");
    }

    public int a() {
        return this.f2821b;
    }

    public boolean b() {
        return this.f2822c;
    }

    public String c() {
        return this.k;
    }

    public boolean d() {
        return this.q;
    }

    public String e() {
        return this.l;
    }

    public String f() {
        return this.m;
    }

    public y g() {
        if (this.j == null) {
            this.j = (y) this.n.get(this.i);
            if (this.j == null) {
                this.j = new y(this.i);
                this.n.put(this.i, this.j);
            }
        }
        return this.j;
    }

    public boolean h() {
        return this.f2823d;
    }

    public int i() {
        return this.t;
    }

    public int j() {
        return this.f2820a;
    }

    public boolean k() {
        return this.f2825f;
    }

    public boolean l() {
        return this.f2824e;
    }

    public boolean m() {
        return this.o;
    }

    public boolean n() {
        return this.p;
    }

    public File o() {
        return this.h;
    }

    public boolean p() {
        return this.g;
    }

    public boolean q() {
        return this.r;
    }

    public String r() {
        return this.s;
    }

    public void a(boolean z) {
        u.a(z);
    }
}
