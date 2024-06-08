package d.p;

/* loaded from: classes.dex */
public abstract class c {

    /* renamed from: a, reason: collision with root package name */
    protected static final byte[] f2843a;

    /* renamed from: b, reason: collision with root package name */
    public static final String[] f2844b;

    /* loaded from: classes.dex */
    public class a {

        /* renamed from: a, reason: collision with root package name */
        public String f2845a;

        /* renamed from: b, reason: collision with root package name */
        public int f2846b;

        /* renamed from: c, reason: collision with root package name */
        public int f2847c;

        /* renamed from: d, reason: collision with root package name */
        public int f2848d;

        /* renamed from: e, reason: collision with root package name */
        public int f2849e;

        /* renamed from: f, reason: collision with root package name */
        public int f2850f;
        public int g;
        public int h;
        public byte[] i = new byte[128];

        public a(c cVar, String str) {
            d.q.a.a(str.length() < 32);
            d0.b((str.length() + 1) * 2, this.i, 64);
            for (int i = 0; i < str.length(); i++) {
                this.i[i * 2] = (byte) str.charAt(i);
            }
        }

        public void a(int i) {
            this.h = i;
            d0.a(this.h, this.i, 76);
        }

        public void b(int i) {
            this.f2847c = i == 0 ? 0 : 1;
            this.i[67] = (byte) this.f2847c;
        }

        public void c(int i) {
            this.g = i;
            d0.a(this.g, this.i, 72);
        }

        public void d(int i) {
            this.f2850f = i;
            d0.a(i, this.i, 68);
        }

        public void e(int i) {
            this.f2849e = i;
            d0.a(i, this.i, 120);
        }

        public void f(int i) {
            this.f2848d = i;
            d0.a(i, this.i, 116);
        }

        public void g(int i) {
            this.f2846b = i;
            this.i[66] = (byte) i;
        }
    }

    static {
        d.q.c.b(c.class);
        f2843a = new byte[]{-48, -49, 17, -32, -95, -79, 26, -31};
        f2844b = new String[]{"Root Entry", "Workbook", "\u0005SummaryInformation", "\u0005DocumentSummaryInformation"};
    }
}
