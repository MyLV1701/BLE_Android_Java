package c.a.b.w.n;

import c.a.b.o;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public final class e extends c.a.b.y.a {
    private static final Object v;
    private Object[] r;
    private int s;
    private String[] t;
    private int[] u;

    /* loaded from: classes.dex */
    class a extends Reader {
        a() {
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            throw new AssertionError();
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }
    }

    static {
        new a();
        v = new Object();
    }

    private String i() {
        return " at path " + f();
    }

    private Object t() {
        return this.r[this.s - 1];
    }

    private Object u() {
        Object[] objArr = this.r;
        int i = this.s - 1;
        this.s = i;
        Object obj = objArr[i];
        objArr[this.s] = null;
        return obj;
    }

    @Override // c.a.b.y.a
    public void a() {
        a(c.a.b.y.b.BEGIN_ARRAY);
        a(((c.a.b.g) t()).iterator());
        this.u[this.s - 1] = 0;
    }

    @Override // c.a.b.y.a
    public void b() {
        a(c.a.b.y.b.BEGIN_OBJECT);
        a(((c.a.b.m) t()).h().iterator());
    }

    @Override // c.a.b.y.a, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.r = new Object[]{v};
        this.s = 1;
    }

    @Override // c.a.b.y.a
    public void d() {
        a(c.a.b.y.b.END_ARRAY);
        u();
        u();
        int i = this.s;
        if (i > 0) {
            int[] iArr = this.u;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // c.a.b.y.a
    public void e() {
        a(c.a.b.y.b.END_OBJECT);
        u();
        u();
        int i = this.s;
        if (i > 0) {
            int[] iArr = this.u;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // c.a.b.y.a
    public String f() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i = 0;
        while (i < this.s) {
            Object[] objArr = this.r;
            if (objArr[i] instanceof c.a.b.g) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('[');
                    sb.append(this.u[i]);
                    sb.append(']');
                }
            } else if (objArr[i] instanceof c.a.b.m) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('.');
                    String[] strArr = this.t;
                    if (strArr[i] != null) {
                        sb.append(strArr[i]);
                    }
                }
            }
            i++;
        }
        return sb.toString();
    }

    @Override // c.a.b.y.a
    public boolean g() {
        c.a.b.y.b q = q();
        return (q == c.a.b.y.b.END_OBJECT || q == c.a.b.y.b.END_ARRAY) ? false : true;
    }

    @Override // c.a.b.y.a
    public boolean j() {
        a(c.a.b.y.b.BOOLEAN);
        boolean h = ((o) u()).h();
        int i = this.s;
        if (i > 0) {
            int[] iArr = this.u;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return h;
    }

    @Override // c.a.b.y.a
    public double k() {
        c.a.b.y.b q = q();
        if (q != c.a.b.y.b.NUMBER && q != c.a.b.y.b.STRING) {
            throw new IllegalStateException("Expected " + c.a.b.y.b.NUMBER + " but was " + q + i());
        }
        double i = ((o) t()).i();
        if (!h() && (Double.isNaN(i) || Double.isInfinite(i))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + i);
        }
        u();
        int i2 = this.s;
        if (i2 > 0) {
            int[] iArr = this.u;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
        return i;
    }

    @Override // c.a.b.y.a
    public int l() {
        c.a.b.y.b q = q();
        if (q != c.a.b.y.b.NUMBER && q != c.a.b.y.b.STRING) {
            throw new IllegalStateException("Expected " + c.a.b.y.b.NUMBER + " but was " + q + i());
        }
        int j = ((o) t()).j();
        u();
        int i = this.s;
        if (i > 0) {
            int[] iArr = this.u;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return j;
    }

    @Override // c.a.b.y.a
    public long m() {
        c.a.b.y.b q = q();
        if (q != c.a.b.y.b.NUMBER && q != c.a.b.y.b.STRING) {
            throw new IllegalStateException("Expected " + c.a.b.y.b.NUMBER + " but was " + q + i());
        }
        long k = ((o) t()).k();
        u();
        int i = this.s;
        if (i > 0) {
            int[] iArr = this.u;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return k;
    }

    @Override // c.a.b.y.a
    public String n() {
        a(c.a.b.y.b.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) t()).next();
        String str = (String) entry.getKey();
        this.t[this.s - 1] = str;
        a(entry.getValue());
        return str;
    }

    @Override // c.a.b.y.a
    public void o() {
        a(c.a.b.y.b.NULL);
        u();
        int i = this.s;
        if (i > 0) {
            int[] iArr = this.u;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // c.a.b.y.a
    public String p() {
        c.a.b.y.b q = q();
        if (q != c.a.b.y.b.STRING && q != c.a.b.y.b.NUMBER) {
            throw new IllegalStateException("Expected " + c.a.b.y.b.STRING + " but was " + q + i());
        }
        String m = ((o) u()).m();
        int i = this.s;
        if (i > 0) {
            int[] iArr = this.u;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return m;
    }

    @Override // c.a.b.y.a
    public c.a.b.y.b q() {
        if (this.s == 0) {
            return c.a.b.y.b.END_DOCUMENT;
        }
        Object t = t();
        if (t instanceof Iterator) {
            boolean z = this.r[this.s - 2] instanceof c.a.b.m;
            Iterator it = (Iterator) t;
            if (!it.hasNext()) {
                return z ? c.a.b.y.b.END_OBJECT : c.a.b.y.b.END_ARRAY;
            }
            if (z) {
                return c.a.b.y.b.NAME;
            }
            a(it.next());
            return q();
        }
        if (t instanceof c.a.b.m) {
            return c.a.b.y.b.BEGIN_OBJECT;
        }
        if (t instanceof c.a.b.g) {
            return c.a.b.y.b.BEGIN_ARRAY;
        }
        if (t instanceof o) {
            o oVar = (o) t;
            if (oVar.p()) {
                return c.a.b.y.b.STRING;
            }
            if (oVar.n()) {
                return c.a.b.y.b.BOOLEAN;
            }
            if (oVar.o()) {
                return c.a.b.y.b.NUMBER;
            }
            throw new AssertionError();
        }
        if (t instanceof c.a.b.l) {
            return c.a.b.y.b.NULL;
        }
        if (t == v) {
            throw new IllegalStateException("JsonReader is closed");
        }
        throw new AssertionError();
    }

    @Override // c.a.b.y.a
    public void r() {
        if (q() == c.a.b.y.b.NAME) {
            n();
            this.t[this.s - 2] = "null";
        } else {
            u();
            int i = this.s;
            if (i > 0) {
                this.t[i - 1] = "null";
            }
        }
        int i2 = this.s;
        if (i2 > 0) {
            int[] iArr = this.u;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    public void s() {
        a(c.a.b.y.b.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) t()).next();
        a(entry.getValue());
        a(new o((String) entry.getKey()));
    }

    @Override // c.a.b.y.a
    public String toString() {
        return e.class.getSimpleName();
    }

    private void a(c.a.b.y.b bVar) {
        if (q() == bVar) {
            return;
        }
        throw new IllegalStateException("Expected " + bVar + " but was " + q() + i());
    }

    private void a(Object obj) {
        int i = this.s;
        Object[] objArr = this.r;
        if (i == objArr.length) {
            int i2 = i * 2;
            this.r = Arrays.copyOf(objArr, i2);
            this.u = Arrays.copyOf(this.u, i2);
            this.t = (String[]) Arrays.copyOf(this.t, i2);
        }
        Object[] objArr2 = this.r;
        int i3 = this.s;
        this.s = i3 + 1;
        objArr2[i3] = obj;
    }
}
