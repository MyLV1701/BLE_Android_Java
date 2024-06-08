package c.a.b.y;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

/* loaded from: classes.dex */
public class c implements Closeable, Flushable {
    private static final String[] k = new String[128];
    private static final String[] l;

    /* renamed from: b, reason: collision with root package name */
    private final Writer f2331b;

    /* renamed from: c, reason: collision with root package name */
    private int[] f2332c = new int[32];

    /* renamed from: d, reason: collision with root package name */
    private int f2333d = 0;

    /* renamed from: e, reason: collision with root package name */
    private String f2334e;

    /* renamed from: f, reason: collision with root package name */
    private String f2335f;
    private boolean g;
    private boolean h;
    private String i;
    private boolean j;

    static {
        for (int i = 0; i <= 31; i++) {
            k[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        String[] strArr = k;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        l = (String[]) strArr.clone();
        String[] strArr2 = l;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public c(Writer writer) {
        a(6);
        this.f2335f = ":";
        this.j = true;
        if (writer != null) {
            this.f2331b = writer;
            return;
        }
        throw new NullPointerException("out == null");
    }

    private void h() {
        int k2 = k();
        if (k2 == 5) {
            this.f2331b.write(44);
        } else if (k2 != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        j();
        b(4);
    }

    private void i() {
        int k2 = k();
        if (k2 == 1) {
            b(2);
            j();
            return;
        }
        if (k2 == 2) {
            this.f2331b.append(',');
            j();
        } else {
            if (k2 != 4) {
                if (k2 != 6) {
                    if (k2 == 7) {
                        if (!this.g) {
                            throw new IllegalStateException("JSON must have only one top-level value.");
                        }
                    } else {
                        throw new IllegalStateException("Nesting problem.");
                    }
                }
                b(7);
                return;
            }
            this.f2331b.append((CharSequence) this.f2335f);
            b(5);
        }
    }

    private void j() {
        if (this.f2334e == null) {
            return;
        }
        this.f2331b.write(10);
        int i = this.f2333d;
        for (int i2 = 1; i2 < i; i2++) {
            this.f2331b.write(this.f2334e);
        }
    }

    private int k() {
        int i = this.f2333d;
        if (i != 0) {
            return this.f2332c[i - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private void l() {
        if (this.i != null) {
            h();
            c(this.i);
            this.i = null;
        }
    }

    public final void a(boolean z) {
        this.g = z;
    }

    public c b() {
        l();
        a(3, '{');
        return this;
    }

    public c c() {
        a(1, 2, ']');
        return this;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f2331b.close();
        int i = this.f2333d;
        if (i <= 1 && (i != 1 || this.f2332c[i - 1] == 7)) {
            this.f2333d = 0;
            return;
        }
        throw new IOException("Incomplete document");
    }

    public c d() {
        a(3, 5, '}');
        return this;
    }

    public final boolean e() {
        return this.j;
    }

    public boolean f() {
        return this.g;
    }

    public void flush() {
        if (this.f2333d != 0) {
            this.f2331b.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public c g() {
        if (this.i != null) {
            if (this.j) {
                l();
            } else {
                this.i = null;
                return this;
            }
        }
        i();
        this.f2331b.write("null");
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(java.lang.String r9) {
        /*
            r8 = this;
            boolean r0 = r8.h
            if (r0 == 0) goto L7
            java.lang.String[] r0 = c.a.b.y.c.l
            goto L9
        L7:
            java.lang.String[] r0 = c.a.b.y.c.k
        L9:
            java.io.Writer r1 = r8.f2331b
            r2 = 34
            r1.write(r2)
            int r1 = r9.length()
            r3 = 0
            r4 = 0
        L16:
            if (r3 >= r1) goto L45
            char r5 = r9.charAt(r3)
            r6 = 128(0x80, float:1.8E-43)
            if (r5 >= r6) goto L25
            r5 = r0[r5]
            if (r5 != 0) goto L32
            goto L42
        L25:
            r6 = 8232(0x2028, float:1.1535E-41)
            if (r5 != r6) goto L2c
            java.lang.String r5 = "\\u2028"
            goto L32
        L2c:
            r6 = 8233(0x2029, float:1.1537E-41)
            if (r5 != r6) goto L42
            java.lang.String r5 = "\\u2029"
        L32:
            if (r4 >= r3) goto L3b
            java.io.Writer r6 = r8.f2331b
            int r7 = r3 - r4
            r6.write(r9, r4, r7)
        L3b:
            java.io.Writer r4 = r8.f2331b
            r4.write(r5)
            int r4 = r3 + 1
        L42:
            int r3 = r3 + 1
            goto L16
        L45:
            if (r4 >= r1) goto L4d
            java.io.Writer r0 = r8.f2331b
            int r1 = r1 - r4
            r0.write(r9, r4, r1)
        L4d:
            java.io.Writer r9 = r8.f2331b
            r9.write(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.b.y.c.c(java.lang.String):void");
    }

    public c a() {
        l();
        a(1, '[');
        return this;
    }

    private void b(int i) {
        this.f2332c[this.f2333d - 1] = i;
    }

    private c a(int i, char c2) {
        i();
        a(i);
        this.f2331b.write(c2);
        return this;
    }

    public c b(String str) {
        if (str == null) {
            return g();
        }
        l();
        i();
        c(str);
        return this;
    }

    private c a(int i, int i2, char c2) {
        int k2 = k();
        if (k2 != i2 && k2 != i) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.i == null) {
            this.f2333d--;
            if (k2 == i2) {
                j();
            }
            this.f2331b.write(c2);
            return this;
        }
        throw new IllegalStateException("Dangling name: " + this.i);
    }

    public c b(boolean z) {
        l();
        i();
        this.f2331b.write(z ? "true" : "false");
        return this;
    }

    private void a(int i) {
        int i2 = this.f2333d;
        int[] iArr = this.f2332c;
        if (i2 == iArr.length) {
            this.f2332c = Arrays.copyOf(iArr, i2 * 2);
        }
        int[] iArr2 = this.f2332c;
        int i3 = this.f2333d;
        this.f2333d = i3 + 1;
        iArr2[i3] = i;
    }

    public c a(String str) {
        if (str != null) {
            if (this.i == null) {
                if (this.f2333d != 0) {
                    this.i = str;
                    return this;
                }
                throw new IllegalStateException("JsonWriter is closed.");
            }
            throw new IllegalStateException();
        }
        throw new NullPointerException("name == null");
    }

    public c a(Boolean bool) {
        if (bool == null) {
            return g();
        }
        l();
        i();
        this.f2331b.write(bool.booleanValue() ? "true" : "false");
        return this;
    }

    public c a(long j) {
        l();
        i();
        this.f2331b.write(Long.toString(j));
        return this;
    }

    public c a(Number number) {
        if (number == null) {
            return g();
        }
        l();
        String obj = number.toString();
        if (!this.g && (obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN"))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
        }
        i();
        this.f2331b.append((CharSequence) obj);
        return this;
    }
}
