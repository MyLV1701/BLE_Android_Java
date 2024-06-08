package d.p;

/* loaded from: classes.dex */
final class d implements d.r.g, t {

    /* renamed from: b, reason: collision with root package name */
    public static d[] f2852b = new d[50];

    /* renamed from: a, reason: collision with root package name */
    private int f2853a;

    static {
        f2852b[0] = new d("", 0);
        f2852b[1] = new d("0", 1);
        f2852b[2] = new d("0.00", 2);
        f2852b[3] = new d("#,##0", 3);
        f2852b[4] = new d("#,##0.00", 4);
        f2852b[5] = new d("($#,##0_);($#,##0)", 5);
        f2852b[6] = new d("($#,##0_);[Red]($#,##0)", 6);
        f2852b[7] = new d("($#,##0_);[Red]($#,##0)", 7);
        f2852b[8] = new d("($#,##0.00_);[Red]($#,##0.00)", 8);
        f2852b[9] = new d("0%", 9);
        f2852b[10] = new d("0.00%", 10);
        f2852b[11] = new d("0.00E+00", 11);
        f2852b[12] = new d("# ?/?", 12);
        f2852b[13] = new d("# ??/??", 13);
        f2852b[14] = new d("dd/mm/yyyy", 14);
        f2852b[15] = new d("d-mmm-yy", 15);
        f2852b[16] = new d("d-mmm", 16);
        f2852b[17] = new d("mmm-yy", 17);
        f2852b[18] = new d("h:mm AM/PM", 18);
        f2852b[19] = new d("h:mm:ss AM/PM", 19);
        f2852b[20] = new d("h:mm", 20);
        f2852b[21] = new d("h:mm:ss", 21);
        f2852b[22] = new d("m/d/yy h:mm", 22);
        f2852b[37] = new d("(#,##0_);(#,##0)", 37);
        f2852b[38] = new d("(#,##0_);[Red](#,##0)", 38);
        f2852b[39] = new d("(#,##0.00_);(#,##0.00)", 39);
        f2852b[40] = new d("(#,##0.00_);[Red](#,##0.00)", 40);
        f2852b[41] = new d("_(*#,##0_);_(*(#,##0);_(*\"-\"_);(@_)", 41);
        f2852b[42] = new d("_($*#,##0_);_($*(#,##0);_($*\"-\"_);(@_)", 42);
        f2852b[43] = new d("_(* #,##0.00_);_(* (#,##0.00);_(* \"-\"??_);(@_)", 43);
        f2852b[44] = new d("_($* #,##0.00_);_($* (#,##0.00);_($* \"-\"??_);(@_)", 44);
        f2852b[45] = new d("mm:ss", 45);
        f2852b[46] = new d("[h]mm:ss", 46);
        f2852b[47] = new d("mm:ss.0", 47);
        f2852b[48] = new d("##0.0E+0", 48);
        f2852b[49] = new d("@", 49);
    }

    private d(String str, int i) {
        this.f2853a = i;
    }

    @Override // d.p.t
    public void a(int i) {
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof d) && this.f2853a == ((d) obj).f2853a;
    }

    @Override // d.p.t
    public boolean g() {
        return true;
    }

    @Override // d.p.t
    public int h() {
        return this.f2853a;
    }

    @Override // d.p.t
    public boolean i() {
        return true;
    }
}
