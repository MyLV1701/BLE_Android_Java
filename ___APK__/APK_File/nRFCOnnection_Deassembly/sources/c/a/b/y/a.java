package c.a.b.y;

import c.a.b.w.f;
import c.a.b.w.n.e;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

/* loaded from: classes.dex */
public class a implements Closeable {
    private static final char[] q = ")]}'\n".toCharArray();

    /* renamed from: b, reason: collision with root package name */
    private final Reader f2321b;
    private long j;
    private int k;
    private String l;
    private int n;
    private String[] o;
    private int[] p;

    /* renamed from: c, reason: collision with root package name */
    private boolean f2322c = false;

    /* renamed from: d, reason: collision with root package name */
    private final char[] f2323d = new char[1024];

    /* renamed from: e, reason: collision with root package name */
    private int f2324e = 0;

    /* renamed from: f, reason: collision with root package name */
    private int f2325f = 0;
    private int g = 0;
    private int h = 0;
    int i = 0;
    private int[] m = new int[32];

    /* renamed from: c.a.b.y.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0079a extends f {
        C0079a() {
        }

        @Override // c.a.b.w.f
        public void a(a aVar) {
            if (aVar instanceof e) {
                ((e) aVar).s();
                return;
            }
            int i = aVar.i;
            if (i == 0) {
                i = aVar.c();
            }
            if (i == 13) {
                aVar.i = 9;
                return;
            }
            if (i == 12) {
                aVar.i = 8;
                return;
            }
            if (i == 14) {
                aVar.i = 10;
                return;
            }
            throw new IllegalStateException("Expected a name but was " + aVar.q() + aVar.i());
        }
    }

    static {
        f.f2224a = new C0079a();
    }

    public a(Reader reader) {
        this.n = 0;
        int[] iArr = this.m;
        int i = this.n;
        this.n = i + 1;
        iArr[i] = 6;
        this.o = new String[32];
        this.p = new int[32];
        if (reader != null) {
            this.f2321b = reader;
            return;
        }
        throw new NullPointerException("in == null");
    }

    private void s() {
        if (this.f2322c) {
            return;
        }
        b("Use JsonReader.setLenient(true) to accept malformed JSON");
        throw null;
    }

    private void t() {
        b(true);
        this.f2324e--;
        int i = this.f2324e;
        char[] cArr = q;
        if (i + cArr.length > this.f2325f && !a(cArr.length)) {
            return;
        }
        int i2 = 0;
        while (true) {
            char[] cArr2 = q;
            if (i2 < cArr2.length) {
                if (this.f2323d[this.f2324e + i2] != cArr2[i2]) {
                    return;
                } else {
                    i2++;
                }
            } else {
                this.f2324e += cArr2.length;
                return;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x004b, code lost:
    
        s();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:54:0x0045. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String u() {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            r2 = r1
        L3:
            r1 = 0
        L4:
            int r3 = r6.f2324e
            int r4 = r3 + r1
            int r5 = r6.f2325f
            if (r4 >= r5) goto L4f
            char[] r4 = r6.f2323d
            int r3 = r3 + r1
            char r3 = r4[r3]
            r4 = 9
            if (r3 == r4) goto L5d
            r4 = 10
            if (r3 == r4) goto L5d
            r4 = 12
            if (r3 == r4) goto L5d
            r4 = 13
            if (r3 == r4) goto L5d
            r4 = 32
            if (r3 == r4) goto L5d
            r4 = 35
            if (r3 == r4) goto L4b
            r4 = 44
            if (r3 == r4) goto L5d
            r4 = 47
            if (r3 == r4) goto L4b
            r4 = 61
            if (r3 == r4) goto L4b
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L5d
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L5d
            r4 = 58
            if (r3 == r4) goto L5d
            r4 = 59
            if (r3 == r4) goto L4b
            switch(r3) {
                case 91: goto L5d;
                case 92: goto L4b;
                case 93: goto L5d;
                default: goto L48;
            }
        L48:
            int r1 = r1 + 1
            goto L4
        L4b:
            r6.s()
            goto L5d
        L4f:
            char[] r3 = r6.f2323d
            int r3 = r3.length
            if (r1 >= r3) goto L5f
            int r3 = r1 + 1
            boolean r3 = r6.a(r3)
            if (r3 == 0) goto L5d
            goto L4
        L5d:
            r0 = r1
            goto L7f
        L5f:
            if (r2 != 0) goto L6c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r3 = 16
            int r3 = java.lang.Math.max(r1, r3)
            r2.<init>(r3)
        L6c:
            char[] r3 = r6.f2323d
            int r4 = r6.f2324e
            r2.append(r3, r4, r1)
            int r3 = r6.f2324e
            int r3 = r3 + r1
            r6.f2324e = r3
            r1 = 1
            boolean r1 = r6.a(r1)
            if (r1 != 0) goto L3
        L7f:
            if (r2 != 0) goto L8b
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.f2323d
            int r3 = r6.f2324e
            r1.<init>(r2, r3, r0)
            goto L96
        L8b:
            char[] r1 = r6.f2323d
            int r3 = r6.f2324e
            r2.append(r1, r3, r0)
            java.lang.String r1 = r2.toString()
        L96:
            int r2 = r6.f2324e
            int r2 = r2 + r0
            r6.f2324e = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.b.y.a.u():java.lang.String");
    }

    private int v() {
        int i;
        String str;
        String str2;
        char c2 = this.f2323d[this.f2324e];
        if (c2 == 't' || c2 == 'T') {
            i = 5;
            str = "true";
            str2 = "TRUE";
        } else if (c2 == 'f' || c2 == 'F') {
            i = 6;
            str = "false";
            str2 = "FALSE";
        } else {
            if (c2 != 'n' && c2 != 'N') {
                return 0;
            }
            i = 7;
            str = "null";
            str2 = "NULL";
        }
        int length = str.length();
        for (int i2 = 1; i2 < length; i2++) {
            if (this.f2324e + i2 >= this.f2325f && !a(i2 + 1)) {
                return 0;
            }
            char c3 = this.f2323d[this.f2324e + i2];
            if (c3 != str.charAt(i2) && c3 != str2.charAt(i2)) {
                return 0;
            }
        }
        if ((this.f2324e + length < this.f2325f || a(length + 1)) && a(this.f2323d[this.f2324e + length])) {
            return 0;
        }
        this.f2324e += length;
        this.i = i;
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x009c, code lost:
    
        if (r9 != 2) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x009e, code lost:
    
        if (r10 == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x00a4, code lost:
    
        if (r11 != Long.MIN_VALUE) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x00a6, code lost:
    
        if (r13 == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x00ac, code lost:
    
        if (r11 != 0) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00ae, code lost:
    
        if (r13 != false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00b0, code lost:
    
        if (r13 == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00b3, code lost:
    
        r11 = -r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00b4, code lost:
    
        r18.j = r11;
        r18.f2324e += r3;
        r18.i = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00bf, code lost:
    
        return 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00c0, code lost:
    
        if (r9 == 2) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00c3, code lost:
    
        if (r9 == 4) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00c6, code lost:
    
        if (r9 != 7) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00c9, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00cb, code lost:
    
        r18.k = r3;
        r18.i = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00d1, code lost:
    
        return 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x009a, code lost:
    
        if (a(r14) != false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00d2, code lost:
    
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int w() {
        /*
            Method dump skipped, instructions count: 257
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.b.y.a.w():int");
    }

    private char x() {
        int i;
        int i2;
        if (this.f2324e == this.f2325f && !a(1)) {
            b("Unterminated escape sequence");
            throw null;
        }
        char[] cArr = this.f2323d;
        int i3 = this.f2324e;
        this.f2324e = i3 + 1;
        char c2 = cArr[i3];
        if (c2 == '\n') {
            this.g++;
            this.h = this.f2324e;
        } else if (c2 != '\"' && c2 != '\'' && c2 != '/' && c2 != '\\') {
            if (c2 == 'b') {
                return '\b';
            }
            if (c2 == 'f') {
                return '\f';
            }
            if (c2 == 'n') {
                return '\n';
            }
            if (c2 == 'r') {
                return '\r';
            }
            if (c2 == 't') {
                return '\t';
            }
            if (c2 == 'u') {
                if (this.f2324e + 4 > this.f2325f && !a(4)) {
                    b("Unterminated escape sequence");
                    throw null;
                }
                char c3 = 0;
                int i4 = this.f2324e;
                int i5 = i4 + 4;
                while (i4 < i5) {
                    char c4 = this.f2323d[i4];
                    char c5 = (char) (c3 << 4);
                    if (c4 < '0' || c4 > '9') {
                        if (c4 >= 'a' && c4 <= 'f') {
                            i = c4 - 'a';
                        } else {
                            if (c4 < 'A' || c4 > 'F') {
                                throw new NumberFormatException("\\u" + new String(this.f2323d, this.f2324e, 4));
                            }
                            i = c4 - 'A';
                        }
                        i2 = i + 10;
                    } else {
                        i2 = c4 - '0';
                    }
                    c3 = (char) (c5 + i2);
                    i4++;
                }
                this.f2324e += 4;
                return c3;
            }
            b("Invalid escape sequence");
            throw null;
        }
        return c2;
    }

    private void y() {
        char c2;
        do {
            if (this.f2324e >= this.f2325f && !a(1)) {
                return;
            }
            char[] cArr = this.f2323d;
            int i = this.f2324e;
            this.f2324e = i + 1;
            c2 = cArr[i];
            if (c2 == '\n') {
                this.g++;
                this.h = this.f2324e;
                return;
            }
        } while (c2 != '\r');
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.RegionMaker.calcSwitchOut(RegionMaker.java:923)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:797)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:735)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:152)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:735)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:152)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:735)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:152)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:740)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:152)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:740)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:152)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:735)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:152)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:735)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:152)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:411)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:201)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:242)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    private void z() {
        /*
            r4 = this;
        L0:
            r0 = 0
        L1:
            int r1 = r4.f2324e
            int r2 = r1 + r0
            int r3 = r4.f2325f
            if (r2 >= r3) goto L51
            char[] r2 = r4.f2323d
            int r1 = r1 + r0
            char r1 = r2[r1]
            r2 = 9
            if (r1 == r2) goto L4b
            r2 = 10
            if (r1 == r2) goto L4b
            r2 = 12
            if (r1 == r2) goto L4b
            r2 = 13
            if (r1 == r2) goto L4b
            r2 = 32
            if (r1 == r2) goto L4b
            r2 = 35
            if (r1 == r2) goto L48
            r2 = 44
            if (r1 == r2) goto L4b
            r2 = 47
            if (r1 == r2) goto L48
            r2 = 61
            if (r1 == r2) goto L48
            r2 = 123(0x7b, float:1.72E-43)
            if (r1 == r2) goto L4b
            r2 = 125(0x7d, float:1.75E-43)
            if (r1 == r2) goto L4b
            r2 = 58
            if (r1 == r2) goto L4b
            r2 = 59
            if (r1 == r2) goto L48
            switch(r1) {
                case 91: goto L4b;
                case 92: goto L48;
                case 93: goto L4b;
                default: goto L45;
            }
        L45:
            int r0 = r0 + 1
            goto L1
        L48:
            r4.s()
        L4b:
            int r1 = r4.f2324e
            int r1 = r1 + r0
            r4.f2324e = r1
            return
        L51:
            int r1 = r1 + r0
            r4.f2324e = r1
            r0 = 1
            boolean r0 = r4.a(r0)
            if (r0 != 0) goto L0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.b.y.a.z():void");
    }

    public final void a(boolean z) {
        this.f2322c = z;
    }

    public void b() {
        int i = this.i;
        if (i == 0) {
            i = c();
        }
        if (i == 1) {
            b(3);
            this.i = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + q() + i());
        }
    }

    int c() {
        int b2;
        int[] iArr = this.m;
        int i = this.n;
        int i2 = iArr[i - 1];
        if (i2 == 1) {
            iArr[i - 1] = 2;
        } else if (i2 == 2) {
            int b3 = b(true);
            if (b3 != 44) {
                if (b3 != 59) {
                    if (b3 == 93) {
                        this.i = 4;
                        return 4;
                    }
                    b("Unterminated array");
                    throw null;
                }
                s();
            }
        } else {
            if (i2 == 3 || i2 == 5) {
                this.m[this.n - 1] = 4;
                if (i2 == 5 && (b2 = b(true)) != 44) {
                    if (b2 != 59) {
                        if (b2 == 125) {
                            this.i = 2;
                            return 2;
                        }
                        b("Unterminated object");
                        throw null;
                    }
                    s();
                }
                int b4 = b(true);
                if (b4 == 34) {
                    this.i = 13;
                    return 13;
                }
                if (b4 == 39) {
                    s();
                    this.i = 12;
                    return 12;
                }
                if (b4 == 125) {
                    if (i2 != 5) {
                        this.i = 2;
                        return 2;
                    }
                    b("Expected name");
                    throw null;
                }
                s();
                this.f2324e--;
                if (a((char) b4)) {
                    this.i = 14;
                    return 14;
                }
                b("Expected name");
                throw null;
            }
            if (i2 == 4) {
                iArr[i - 1] = 5;
                int b5 = b(true);
                if (b5 != 58) {
                    if (b5 == 61) {
                        s();
                        if (this.f2324e < this.f2325f || a(1)) {
                            char[] cArr = this.f2323d;
                            int i3 = this.f2324e;
                            if (cArr[i3] == '>') {
                                this.f2324e = i3 + 1;
                            }
                        }
                    } else {
                        b("Expected ':'");
                        throw null;
                    }
                }
            } else if (i2 == 6) {
                if (this.f2322c) {
                    t();
                }
                this.m[this.n - 1] = 7;
            } else if (i2 == 7) {
                if (b(false) == -1) {
                    this.i = 17;
                    return 17;
                }
                s();
                this.f2324e--;
            } else if (i2 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        int b6 = b(true);
        if (b6 == 34) {
            this.i = 9;
            return 9;
        }
        if (b6 == 39) {
            s();
            this.i = 8;
            return 8;
        }
        if (b6 != 44 && b6 != 59) {
            if (b6 == 91) {
                this.i = 3;
                return 3;
            }
            if (b6 != 93) {
                if (b6 != 123) {
                    this.f2324e--;
                    int v = v();
                    if (v != 0) {
                        return v;
                    }
                    int w = w();
                    if (w != 0) {
                        return w;
                    }
                    if (a(this.f2323d[this.f2324e])) {
                        s();
                        this.i = 10;
                        return 10;
                    }
                    b("Expected value");
                    throw null;
                }
                this.i = 1;
                return 1;
            }
            if (i2 == 1) {
                this.i = 4;
                return 4;
            }
        }
        if (i2 != 1 && i2 != 2) {
            b("Unexpected value");
            throw null;
        }
        s();
        this.f2324e--;
        this.i = 7;
        return 7;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.i = 0;
        this.m[0] = 8;
        this.n = 1;
        this.f2321b.close();
    }

    public void d() {
        int i = this.i;
        if (i == 0) {
            i = c();
        }
        if (i == 4) {
            this.n--;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            this.i = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + q() + i());
    }

    public void e() {
        int i = this.i;
        if (i == 0) {
            i = c();
        }
        if (i == 2) {
            this.n--;
            String[] strArr = this.o;
            int i2 = this.n;
            strArr[i2] = null;
            int[] iArr = this.p;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
            this.i = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + q() + i());
    }

    public String f() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i = this.n;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.m[i2];
            if (i3 == 1 || i3 == 2) {
                sb.append('[');
                sb.append(this.p[i2]);
                sb.append(']');
            } else if (i3 == 3 || i3 == 4 || i3 == 5) {
                sb.append('.');
                String[] strArr = this.o;
                if (strArr[i2] != null) {
                    sb.append(strArr[i2]);
                }
            }
        }
        return sb.toString();
    }

    public boolean g() {
        int i = this.i;
        if (i == 0) {
            i = c();
        }
        return (i == 2 || i == 4) ? false : true;
    }

    public final boolean h() {
        return this.f2322c;
    }

    String i() {
        return " at line " + (this.g + 1) + " column " + ((this.f2324e - this.h) + 1) + " path " + f();
    }

    public boolean j() {
        int i = this.i;
        if (i == 0) {
            i = c();
        }
        if (i == 5) {
            this.i = 0;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        }
        if (i == 6) {
            this.i = 0;
            int[] iArr2 = this.p;
            int i3 = this.n - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        }
        throw new IllegalStateException("Expected a boolean but was " + q() + i());
    }

    public double k() {
        int i = this.i;
        if (i == 0) {
            i = c();
        }
        if (i == 15) {
            this.i = 0;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.j;
        }
        if (i == 16) {
            this.l = new String(this.f2323d, this.f2324e, this.k);
            this.f2324e += this.k;
        } else if (i == 8 || i == 9) {
            this.l = b(i == 8 ? '\'' : '\"');
        } else if (i == 10) {
            this.l = u();
        } else if (i != 11) {
            throw new IllegalStateException("Expected a double but was " + q() + i());
        }
        this.i = 11;
        double parseDouble = Double.parseDouble(this.l);
        if (!this.f2322c && (Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
            throw new d("JSON forbids NaN and infinities: " + parseDouble + i());
        }
        this.l = null;
        this.i = 0;
        int[] iArr2 = this.p;
        int i3 = this.n - 1;
        iArr2[i3] = iArr2[i3] + 1;
        return parseDouble;
    }

    public int l() {
        int i = this.i;
        if (i == 0) {
            i = c();
        }
        if (i == 15) {
            long j = this.j;
            int i2 = (int) j;
            if (j == i2) {
                this.i = 0;
                int[] iArr = this.p;
                int i3 = this.n - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
            throw new NumberFormatException("Expected an int but was " + this.j + i());
        }
        if (i == 16) {
            this.l = new String(this.f2323d, this.f2324e, this.k);
            this.f2324e += this.k;
        } else {
            if (i != 8 && i != 9 && i != 10) {
                throw new IllegalStateException("Expected an int but was " + q() + i());
            }
            if (i == 10) {
                this.l = u();
            } else {
                this.l = b(i == 8 ? '\'' : '\"');
            }
            try {
                int parseInt = Integer.parseInt(this.l);
                this.i = 0;
                int[] iArr2 = this.p;
                int i4 = this.n - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        }
        this.i = 11;
        double parseDouble = Double.parseDouble(this.l);
        int i5 = (int) parseDouble;
        if (i5 == parseDouble) {
            this.l = null;
            this.i = 0;
            int[] iArr3 = this.p;
            int i6 = this.n - 1;
            iArr3[i6] = iArr3[i6] + 1;
            return i5;
        }
        throw new NumberFormatException("Expected an int but was " + this.l + i());
    }

    public long m() {
        int i = this.i;
        if (i == 0) {
            i = c();
        }
        if (i == 15) {
            this.i = 0;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.j;
        }
        if (i == 16) {
            this.l = new String(this.f2323d, this.f2324e, this.k);
            this.f2324e += this.k;
        } else {
            if (i != 8 && i != 9 && i != 10) {
                throw new IllegalStateException("Expected a long but was " + q() + i());
            }
            if (i == 10) {
                this.l = u();
            } else {
                this.l = b(i == 8 ? '\'' : '\"');
            }
            try {
                long parseLong = Long.parseLong(this.l);
                this.i = 0;
                int[] iArr2 = this.p;
                int i3 = this.n - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        }
        this.i = 11;
        double parseDouble = Double.parseDouble(this.l);
        long j = (long) parseDouble;
        if (j == parseDouble) {
            this.l = null;
            this.i = 0;
            int[] iArr3 = this.p;
            int i4 = this.n - 1;
            iArr3[i4] = iArr3[i4] + 1;
            return j;
        }
        throw new NumberFormatException("Expected a long but was " + this.l + i());
    }

    public String n() {
        String b2;
        int i = this.i;
        if (i == 0) {
            i = c();
        }
        if (i == 14) {
            b2 = u();
        } else if (i == 12) {
            b2 = b('\'');
        } else if (i == 13) {
            b2 = b('\"');
        } else {
            throw new IllegalStateException("Expected a name but was " + q() + i());
        }
        this.i = 0;
        this.o[this.n - 1] = b2;
        return b2;
    }

    public void o() {
        int i = this.i;
        if (i == 0) {
            i = c();
        }
        if (i == 7) {
            this.i = 0;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + q() + i());
    }

    public String p() {
        String str;
        int i = this.i;
        if (i == 0) {
            i = c();
        }
        if (i == 10) {
            str = u();
        } else if (i == 8) {
            str = b('\'');
        } else if (i == 9) {
            str = b('\"');
        } else if (i == 11) {
            str = this.l;
            this.l = null;
        } else if (i == 15) {
            str = Long.toString(this.j);
        } else if (i == 16) {
            str = new String(this.f2323d, this.f2324e, this.k);
            this.f2324e += this.k;
        } else {
            throw new IllegalStateException("Expected a string but was " + q() + i());
        }
        this.i = 0;
        int[] iArr = this.p;
        int i2 = this.n - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    public b q() {
        int i = this.i;
        if (i == 0) {
            i = c();
        }
        switch (i) {
            case 1:
                return b.BEGIN_OBJECT;
            case 2:
                return b.END_OBJECT;
            case 3:
                return b.BEGIN_ARRAY;
            case 4:
                return b.END_ARRAY;
            case 5:
            case 6:
                return b.BOOLEAN;
            case 7:
                return b.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return b.STRING;
            case 12:
            case 13:
            case 14:
                return b.NAME;
            case 15:
            case 16:
                return b.NUMBER;
            case 17:
                return b.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public void r() {
        int i = 0;
        do {
            int i2 = this.i;
            if (i2 == 0) {
                i2 = c();
            }
            if (i2 == 3) {
                b(1);
            } else if (i2 == 1) {
                b(3);
            } else {
                if (i2 == 4) {
                    this.n--;
                } else if (i2 == 2) {
                    this.n--;
                } else {
                    if (i2 == 14 || i2 == 10) {
                        z();
                    } else if (i2 == 8 || i2 == 12) {
                        c('\'');
                    } else if (i2 == 9 || i2 == 13) {
                        c('\"');
                    } else if (i2 == 16) {
                        this.f2324e += this.k;
                    }
                    this.i = 0;
                }
                i--;
                this.i = 0;
            }
            i++;
            this.i = 0;
        } while (i != 0);
        int[] iArr = this.p;
        int i3 = this.n;
        int i4 = i3 - 1;
        iArr[i4] = iArr[i4] + 1;
        this.o[i3 - 1] = "null";
    }

    public String toString() {
        return getClass().getSimpleName() + i();
    }

    public void a() {
        int i = this.i;
        if (i == 0) {
            i = c();
        }
        if (i == 3) {
            b(1);
            this.p[this.n - 1] = 0;
            this.i = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + q() + i());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x005c, code lost:
    
        if (r2 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x005e, code lost:
    
        r2 = new java.lang.StringBuilder(java.lang.Math.max((r3 - r5) * 2, 16));
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006c, code lost:
    
        r2.append(r0, r5, r3 - r5);
        r10.f2324e = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String b(char r11) {
        /*
            r10 = this;
            char[] r0 = r10.f2323d
            r1 = 0
            r2 = r1
        L4:
            int r3 = r10.f2324e
            int r4 = r10.f2325f
        L8:
            r5 = r3
        L9:
            r6 = 16
            r7 = 1
            if (r3 >= r4) goto L5c
            int r8 = r3 + 1
            char r3 = r0[r3]
            if (r3 != r11) goto L28
            r10.f2324e = r8
            int r8 = r8 - r5
            int r8 = r8 - r7
            if (r2 != 0) goto L20
            java.lang.String r11 = new java.lang.String
            r11.<init>(r0, r5, r8)
            return r11
        L20:
            r2.append(r0, r5, r8)
            java.lang.String r11 = r2.toString()
            return r11
        L28:
            r9 = 92
            if (r3 != r9) goto L4f
            r10.f2324e = r8
            int r8 = r8 - r5
            int r8 = r8 - r7
            if (r2 != 0) goto L40
            int r2 = r8 + 1
            int r2 = r2 * 2
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            int r2 = java.lang.Math.max(r2, r6)
            r3.<init>(r2)
            r2 = r3
        L40:
            r2.append(r0, r5, r8)
            char r3 = r10.x()
            r2.append(r3)
            int r3 = r10.f2324e
            int r4 = r10.f2325f
            goto L8
        L4f:
            r6 = 10
            if (r3 != r6) goto L5a
            int r3 = r10.g
            int r3 = r3 + r7
            r10.g = r3
            r10.h = r8
        L5a:
            r3 = r8
            goto L9
        L5c:
            if (r2 != 0) goto L6c
            int r2 = r3 - r5
            int r2 = r2 * 2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r2 = java.lang.Math.max(r2, r6)
            r4.<init>(r2)
            r2 = r4
        L6c:
            int r4 = r3 - r5
            r2.append(r0, r5, r4)
            r10.f2324e = r3
            boolean r3 = r10.a(r7)
            if (r3 == 0) goto L7a
            goto L4
        L7a:
            java.lang.String r11 = "Unterminated string"
            r10.b(r11)
            goto L81
        L80:
            throw r1
        L81:
            goto L80
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.b.y.a.b(char):java.lang.String");
    }

    private boolean a(char c2) {
        if (c2 == '\t' || c2 == '\n' || c2 == '\f' || c2 == '\r' || c2 == ' ') {
            return false;
        }
        if (c2 != '#') {
            if (c2 == ',') {
                return false;
            }
            if (c2 != '/' && c2 != '=') {
                if (c2 == '{' || c2 == '}' || c2 == ':') {
                    return false;
                }
                if (c2 != ';') {
                    switch (c2) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        s();
        return false;
    }

    private boolean a(int i) {
        int i2;
        char[] cArr = this.f2323d;
        int i3 = this.h;
        int i4 = this.f2324e;
        this.h = i3 - i4;
        int i5 = this.f2325f;
        if (i5 != i4) {
            this.f2325f = i5 - i4;
            System.arraycopy(cArr, i4, cArr, 0, this.f2325f);
        } else {
            this.f2325f = 0;
        }
        this.f2324e = 0;
        do {
            Reader reader = this.f2321b;
            int i6 = this.f2325f;
            int read = reader.read(cArr, i6, cArr.length - i6);
            if (read == -1) {
                return false;
            }
            this.f2325f += read;
            if (this.g == 0 && (i2 = this.h) == 0 && this.f2325f > 0 && cArr[0] == 65279) {
                this.f2324e++;
                this.h = i2 + 1;
                i++;
            }
        } while (this.f2325f < i);
        return true;
    }

    private boolean a(String str) {
        int length = str.length();
        while (true) {
            if (this.f2324e + length > this.f2325f && !a(length)) {
                return false;
            }
            char[] cArr = this.f2323d;
            int i = this.f2324e;
            if (cArr[i] != '\n') {
                for (int i2 = 0; i2 < length; i2++) {
                    if (this.f2323d[this.f2324e + i2] != str.charAt(i2)) {
                        break;
                    }
                }
                return true;
            }
            this.g++;
            this.h = i + 1;
            this.f2324e++;
        }
    }

    private void b(int i) {
        int i2 = this.n;
        int[] iArr = this.m;
        if (i2 == iArr.length) {
            int i3 = i2 * 2;
            this.m = Arrays.copyOf(iArr, i3);
            this.p = Arrays.copyOf(this.p, i3);
            this.o = (String[]) Arrays.copyOf(this.o, i3);
        }
        int[] iArr2 = this.m;
        int i4 = this.n;
        this.n = i4 + 1;
        iArr2[i4] = i;
    }

    private int b(boolean z) {
        char[] cArr = this.f2323d;
        int i = this.f2324e;
        int i2 = this.f2325f;
        while (true) {
            if (i == i2) {
                this.f2324e = i;
                if (!a(1)) {
                    if (!z) {
                        return -1;
                    }
                    throw new EOFException("End of input" + i());
                }
                i = this.f2324e;
                i2 = this.f2325f;
            }
            int i3 = i + 1;
            char c2 = cArr[i];
            if (c2 == '\n') {
                this.g++;
                this.h = i3;
            } else if (c2 != ' ' && c2 != '\r' && c2 != '\t') {
                if (c2 == '/') {
                    this.f2324e = i3;
                    if (i3 == i2) {
                        this.f2324e--;
                        boolean a2 = a(2);
                        this.f2324e++;
                        if (!a2) {
                            return c2;
                        }
                    }
                    s();
                    int i4 = this.f2324e;
                    char c3 = cArr[i4];
                    if (c3 == '*') {
                        this.f2324e = i4 + 1;
                        if (a("*/")) {
                            i = this.f2324e + 2;
                            i2 = this.f2325f;
                        } else {
                            b("Unterminated comment");
                            throw null;
                        }
                    } else {
                        if (c3 != '/') {
                            return c2;
                        }
                        this.f2324e = i4 + 1;
                        y();
                        i = this.f2324e;
                        i2 = this.f2325f;
                    }
                } else if (c2 == '#') {
                    this.f2324e = i3;
                    s();
                    y();
                    i = this.f2324e;
                    i2 = this.f2325f;
                } else {
                    this.f2324e = i3;
                    return c2;
                }
            }
            i = i3;
        }
    }

    private void c(char c2) {
        char[] cArr = this.f2323d;
        do {
            int i = this.f2324e;
            int i2 = this.f2325f;
            while (i < i2) {
                int i3 = i + 1;
                char c3 = cArr[i];
                if (c3 == c2) {
                    this.f2324e = i3;
                    return;
                }
                if (c3 == '\\') {
                    this.f2324e = i3;
                    x();
                    i = this.f2324e;
                    i2 = this.f2325f;
                } else {
                    if (c3 == '\n') {
                        this.g++;
                        this.h = i3;
                    }
                    i = i3;
                }
            }
            this.f2324e = i;
        } while (a(1));
        b("Unterminated string");
        throw null;
    }

    private IOException b(String str) {
        throw new d(str + i());
    }
}
