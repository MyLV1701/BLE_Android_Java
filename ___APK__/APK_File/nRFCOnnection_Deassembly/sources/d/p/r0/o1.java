package d.p.r0;

import java.io.Reader;
import no.nordicsemi.android.dfu.DfuBaseService;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class o1 {
    private static final char[] p = a("\b\u0000\u0003\u0015\u0015\u0000\u0001\u0015\u0001\u0014\u0001\u0011\u0001\u0016\u0001\b\u0002\u0000\u0001\u0012\u0001\u0005\u0001\u0006\u0001!\u0001\u001f\u0001\u0004\u0001 \u0001\u0007\u0001\u001b\u0001\u001c\t\u0002\u0001\u0003\u0001\u0000\u0001$\u0001#\u0001\"\u0001\u001e\u0001\u0000\u0001\u000e\u0002\u0001\u0001\u0018\u0001\f\u0001\r\u0002\u0001\u0001\u0019\u0002\u0001\u0001\u000f\u0001\u001d\u0001\u0017\u0003\u0001\u0001\n\u0001\u0010\u0001\t\u0001\u000b\u0001\u001a\u0004\u0001\u0004\u0000\u0001\u0013\u0001\u0000\u001a\u0001ﾅ\u0000");
    private static final int[] q = e();
    private static final int[] r = g();
    private static final int[] s = h();
    private static final String[] t = {"Unkown internal scanner error", "Error: could not match input", "Error: pushback value was too large"};
    private static final int[] u = f();

    /* renamed from: a, reason: collision with root package name */
    private Reader f3092a;

    /* renamed from: b, reason: collision with root package name */
    private int f3093b;

    /* renamed from: c, reason: collision with root package name */
    private int f3094c = 0;

    /* renamed from: d, reason: collision with root package name */
    private char[] f3095d = new char[DfuBaseService.ERROR_CONNECTION_MASK];

    /* renamed from: e, reason: collision with root package name */
    private int f3096e;

    /* renamed from: f, reason: collision with root package name */
    private int f3097f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private boolean l;
    private boolean m;
    private t n;
    private d.p.l0 o;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o1(Reader reader) {
        this.f3092a = reader;
    }

    private static int a(String str, int i, int[] iArr) {
        int i2;
        int length = str.length();
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3 + 1;
            int charAt = str.charAt(i3);
            int i5 = i4 + 1;
            char charAt2 = str.charAt(i4);
            while (true) {
                i2 = i + 1;
                iArr[i] = charAt2;
                charAt--;
                if (charAt <= 0) {
                    break;
                }
                i = i2;
            }
            i3 = i5;
            i = i2;
        }
        return i;
    }

    private static int b(String str, int i, int[] iArr) {
        int i2;
        int length = str.length();
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3 + 1;
            int charAt = str.charAt(i3);
            int i5 = i4 + 1;
            char charAt2 = str.charAt(i4);
            while (true) {
                i2 = i + 1;
                iArr[i] = charAt2;
                charAt--;
                if (charAt <= 0) {
                    break;
                }
                i = i2;
            }
            i3 = i5;
            i = i2;
        }
        return i;
    }

    private static int c(String str, int i, int[] iArr) {
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            iArr[i] = (str.charAt(i2) << 16) | str.charAt(i3);
            i++;
            i2 = i3 + 1;
        }
        return i;
    }

    private static int d(String str, int i, int[] iArr) {
        int i2;
        int length = str.length();
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3 + 1;
            int charAt = str.charAt(i3);
            int i5 = i4 + 1;
            int charAt2 = str.charAt(i4) - 1;
            while (true) {
                i2 = i + 1;
                iArr[i] = charAt2;
                charAt--;
                if (charAt <= 0) {
                    break;
                }
                i = i2;
            }
            i3 = i5;
            i = i2;
        }
        return i;
    }

    private static int[] e() {
        int[] iArr = new int[94];
        a("\u0001\u0000\u0001\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0007\u0001\u0000\u0002\u0002\u0001\b\u0001\u0000\u0001\t\u0001\u0000\u0001\n\u0001\u000b\u0001\f\u0001\r\u0001\u000e\u0001\u000f\u0001\u0010\u0001\u0001\u0001\u0011\u0001\u0002\u0001\u0012\u0001\u0000\u0001\u0013\u0001\u0000\u0001\u0002\u0003\u0000\u0002\u0002\u0005\u0000\u0001\u0014\u0001\u0015\u0001\u0016\u0001\u0002\u0001\u0000\u0001\u0017\u0001\u0000\u0001\u0012\u0002\u0000\u0001\u0018\u0001\u0000\u0002\u0002\b\u0000\u0001\u0017\u0001\u0000\u0001\u0019\u0001\u0000\u0001\u001a\b\u0000\u0001\u001b\u0002\u0000\u0001\u0019\u0002\u0000\u0001\u001c\u0004\u0000\u0001\u001d\u0003\u0000\u0001\u001d\u0001\u0000\u0001\u001e\u0001\u0000", 0, iArr);
        return iArr;
    }

    private static int[] f() {
        int[] iArr = new int[94];
        b("\u0001\u0000\u0003\u0001\u0004\t\u0001\u0000\u0002\u0001\u0001\t\u0001\u0000\u0001\t\u0001\u0000\u0004\t\u0001\u0001\u0001\t\u0002\u0001\u0001\t\u0002\u0001\u0001\u0000\u0001\t\u0001\u0000\u0001\u0001\u0003\u0000\u0002\u0001\u0005\u0000\u0003\t\u0001\u0001\u0001\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0002\u0000\u0001\u0001\u0001\u0000\u0002\u0001\b\u0000\u0001\t\u0001\u0000\u0001\u0001\u0001\u0000\u0001\u0001\b\u0000\u0001\u0001\u0002\u0000\u0001\u0001\u0002\u0000\u0001\t\u0004\u0000\u0001\u0001\u0003\u0000\u0001\t\u0001\u0000\u0001\u0001\u0001\u0000", 0, iArr);
        return iArr;
    }

    private static int[] g() {
        int[] iArr = new int[94];
        c("\u0000\u0000\u0000%\u0000J\u0000o\u0000\u0094\u0000\u0094\u0000\u0094\u0000\u0094\u0000¹\u0000Þ\u0000ă\u0000\u0094\u0000Ĩ\u0000\u0094\u0000ō\u0000\u0094\u0000\u0094\u0000\u0094\u0000\u0094\u0000Ų\u0000\u0094\u0000Ɨ\u0000Ƽ\u0000\u0094\u0000ǡ\u0000Ȇ\u0000ȫ\u0000\u0094\u0000ɐ\u0000ɵ\u0000ʚ\u0000ʿ\u0000ˤ\u0000̉\u0000̮\u0000͓\u0000\u0378\u0000Ν\u0000ς\u0000ϧ\u0000\u0094\u0000\u0094\u0000\u0094\u0000Ќ\u0000б\u0000і\u0000ѻ\u0000Ҡ\u0000Ӆ\u0000Ӫ\u0000ʿ\u0000ԏ\u0000Դ\u0000ՙ\u0000վ\u0000֣\u0000\u05c8\u0000\u05ed\u0000ؒ\u0000ط\u0000ٜ\u0000ځ\u0000\u0094\u0000ڦ\u0000ۋ\u0000ۋ\u0000Ќ\u0000۰\u0000ܕ\u0000ܺ\u0000ݟ\u0000ބ\u0000ީ\u0000ߎ\u0000߳\u0000࠘\u0000࠘\u0000࠽\u0000ࡢ\u0000ࢇ\u0000ࢬ\u0000\u0094\u0000࣑\u0000ࣶ\u0000छ\u0000ी\u0000॥\u0000ঊ\u0000য\u0000\u09d4\u0000\u0094\u0000৹\u0000ਞ\u0000ਞ", 0, iArr);
        return iArr;
    }

    private static int[] h() {
        int[] iArr = new int[2627];
        d("\u0001\u0000\u0001\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0007\u0001\b\u0001\u0000\u0001\t\u0001\n\u0003\u0003\u0001\u000b\u0003\u0003\u0001\f\u0001\r\u0002\u0000\u0001\u000e\u0001\u000f\u0004\u0003\u0001\u0010\u0001\u0004\u0001\u0003\u0001\u0000\u0001\u0011\u0001\u0012\u0001\u0013\u0001\u0014\u0001\u0015\u0001\u0016\u0011\u0017\u0001\u0018\u0013\u0017\u0001\u0000\u0001\u0019\u0001\u001a\u0001\u001b\u0001\u0000\u0001\u001c\u0002\u0000\u0001\u001d\b\u0019\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004\u0019\u0001\u0000\u0001\u001a\u0001\u0019\t\u0000\u0001\u0004\u0004\u0000\u0001 \u0014\u0000\u0001\u0004.\u0000\u0001!\u0007\u0000\b!\u0006\u0000\u0004!\u0002\u0000\u0001!\b\u0000\u0001\u0019\u0001\u001a\u0001\u001b\u0001\u0000\u0001\u001c\u0002\u0000\u0001\u001d\u0001\u0019\u0001\"\u0006\u0019\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004\u0019\u0001\u0000\u0001\u001a\u0001\u0019\b\u0000\u0001\u0019\u0001\u001a\u0001\u001b\u0001\u0000\u0001\u001c\u0002\u0000\u0001\u001d\u0005\u0019\u0001#\u0002\u0019\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004\u0019\u0001\u0000\u0001\u001a\u0001\u0019\u0007\u0000\u0012\r\u0001$\u0012\r\n\u0000\u0001%\f\u0000\u0001&\u0001'\u0001\u0000\u0001(-\u0000\u0001)#\u0000\u0001*\u0001+\u0001\u0000\u0011\u0017\u0001\u0000\u0013\u0017\u0001\u0000\u0001,\u0001\u001a\u0001\u001b\u0001\u0000\u0001\u001c\u0002\u0000\u0001\u001d\b,\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004,\u0001\u0000\u0001\u001a\u0001,\b\u0000\u0001\u001e\u0001\u001a\u0001-\u0005\u0000\b\u001e\u0002\u0000\u0001\u001e\u0003\u0000\u0004\u001e\u0001\u0000\u0001\u001a\u0001\u001e\b\u0000\u0001.\u0006\u0000\u0001/\b.\u0006\u0000\u0004.\u0002\u0000\u0001.\t\u0000\u00010\u0019\u0000\u00010\t\u0000\u0002\u001e\u0006\u0000\b\u001e\u0002\u0000\u0001\u001e\u0003\u0000\u0004\u001e\u0001\u0000\u0002\u001e\b\u0000\u00011\u0006\u0000\u00012\b1\u0006\u0000\u00041\u0002\u0000\u00011\t\u0000\u00013\u0019\u0000\u00013\t\u0000\u00014\u00010\u0001\u001b\u0004\u0000\u0001\u001d\b4\u0006\u0000\u00044\u0001\u0000\u00010\u00014\b\u0000\u0001,\u0001\u001a\u0001\u001b\u0001\u0000\u0001\u001c\u0002\u0000\u0001\u001d\u0002,\u00015\u0005,\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004,\u0001\u0000\u0001\u001a\u0001,\b\u0000\u0001,\u0001\u001a\u0001\u001b\u0001\u0000\u0001\u001c\u0002\u0000\u0001\u001d\u0006,\u00016\u0001,\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004,\u0001\u0000\u0001\u001a\u0001,\u001b\u0000\u00017\u001c\u0000\u00018#\u0000\u00019\u0002\u0000\u0001:/\u0000\u0001;\u0019\u0000\u0001<\u0017\u0000\u0001,\u0001\u001e\u0002\u0000\u0001\u001c\u0003\u0000\b,\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004,\u0001\u0000\u0001\u001e\u0001,\b\u0000\u0001=\u0006\u0000\u0001>\b=\u0006\u0000\u0004=\u0002\u0000\u0001=\b\u0000\u0001?\u0007\u0000\b?\u0006\u0000\u0004?\u0002\u0000\u0001?\b\u0000\u0001.\u0007\u0000\b.\u0006\u0000\u0004.\u0002\u0000\u0001.\t\u0000\u00010\u0001-\u0018\u0000\u00010\t\u0000\u0001@\u0001A\u0005\u0000\u0001B\b@\u0006\u0000\u0004@\u0001\u0000\u0001A\u0001@\b\u0000\u00011\u0007\u0000\b1\u0006\u0000\u00041\u0002\u0000\u00011\t\u0000\u00010\u0001\u001b\u0004\u0000\u0001\u001d\u0013\u0000\u00010\t\u0000\u0001,\u0001\u001e\u0002\u0000\u0001\u001c\u0003\u0000\u0003,\u0001C\u0004,\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004,\u0001\u0000\u0001\u001e\u0001,\b\u0000\u0001,\u0001\u001e\u0002\u0000\u0001\u001c\u0003\u0000\u0007,\u00015\u0002\u0000\u0001\u001e\u0001\u001f\u0002\u0000\u0004,\u0001\u0000\u0001\u001e\u0001,\b\u0000\u0001D\u0006\u0000\u0001E\bD\u0006\u0000\u0004D\u0002\u0000\u0001D\u0014\u0000\u0001F&\u0000\u0001G\r\u0000\u0001F$\u0000\u0001H!\u0000\u0001I\u0019\u0000\u0001J\u0016\u0000\u0001K\u0001L\u0005\u0000\u0001M\bK\u0006\u0000\u0004K\u0001\u0000\u0001L\u0001K\b\u0000\u0001=\u0007\u0000\b=\u0006\u0000\u0004=\u0002\u0000\u0001=\t\u0000\u0001A\u0005\u0000\u0001B\u0013\u0000\u0001A\n\u0000\u0001A\u0019\u0000\u0001A\t\u0000\u0001N\u0001O\u0001P\u0004\u0000\u0001Q\bN\u0006\u0000\u0004N\u0001\u0000\u0001O\u0001N\b\u0000\u0001D\u0007\u0000\bD\u0006\u0000\u0004D\u0002\u0000\u0001D\u001b\u0000\u0001R\u001f\u0000\u0001F!\u0000\u0001S3\u0000\u0001T\u0014\u0000\u0001U\u001b\u0000\u0001L\u0005\u0000\u0001M\u0013\u0000\u0001L\n\u0000\u0001L\u0019\u0000\u0001L\n\u0000\u0001O\u0001P\u0004\u0000\u0001Q\u0013\u0000\u0001O\n\u0000\u0001O\u0001V\u0018\u0000\u0001O\t\u0000\u0001W\u0006\u0000\u0001X\bW\u0006\u0000\u0004W\u0002\u0000\u0001W\t\u0000\u0001O\u0019\u0000\u0001O&\u0000\u0001R\"\u0000\u0001F\u0014\u0000\u0001F\u0019\u0000\u0001Y\u0006\u0000\u0001Z\bY\u0006\u0000\u0004Y\u0002\u0000\u0001Y\b\u0000\u0001[\u0007\u0000\b[\u0006\u0000\u0004[\u0002\u0000\u0001[\b\u0000\u0001W\u0007\u0000\bW\u0006\u0000\u0004W\u0002\u0000\u0001W\b\u0000\u0001\\\u0001]\u0005\u0000\u0001^\b\\\u0006\u0000\u0004\\\u0001\u0000\u0001]\u0001\\\b\u0000\u0001Y\u0007\u0000\bY\u0006\u0000\u0004Y\u0002\u0000\u0001Y\t\u0000\u0001]\u0005\u0000\u0001^\u0013\u0000\u0001]\n\u0000\u0001]\u0019\u0000\u0001]\b\u0000", 0, iArr);
        return iArr;
    }

    public final String c() {
        char[] cArr = this.f3095d;
        int i = this.h;
        return new String(cArr, i, this.f3096e - i);
    }

    private void b(int i) {
        String str;
        try {
            str = t[i];
        } catch (ArrayIndexOutOfBoundsException unused) {
            str = t[0];
        }
        throw new Error(str);
    }

    private boolean d() {
        int i = this.h;
        if (i > 0) {
            char[] cArr = this.f3095d;
            System.arraycopy(cArr, i, cArr, 0, this.i - i);
            int i2 = this.i;
            int i3 = this.h;
            this.i = i2 - i3;
            this.g -= i3;
            this.f3096e -= i3;
            this.f3097f -= i3;
            this.h = 0;
        }
        int i4 = this.g;
        char[] cArr2 = this.f3095d;
        if (i4 >= cArr2.length) {
            char[] cArr3 = new char[i4 * 2];
            System.arraycopy(cArr2, 0, cArr3, 0, cArr2.length);
            this.f3095d = cArr3;
        }
        Reader reader = this.f3092a;
        char[] cArr4 = this.f3095d;
        int i5 = this.i;
        int read = reader.read(cArr4, i5, cArr4.length - i5);
        if (read < 0) {
            return true;
        }
        this.i += read;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return this.k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(t tVar) {
        this.n = tVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(d.p.l0 l0Var) {
        this.o = l0Var;
    }

    private static char[] a(String str) {
        int i;
        char[] cArr = new char[65536];
        int i2 = 0;
        int i3 = 0;
        while (i2 < 100) {
            int i4 = i2 + 1;
            int charAt = str.charAt(i2);
            int i5 = i4 + 1;
            char charAt2 = str.charAt(i4);
            while (true) {
                i = i3 + 1;
                cArr[i3] = charAt2;
                charAt--;
                if (charAt <= 0) {
                    break;
                }
                i3 = i;
            }
            i2 = i5;
            i3 = i;
        }
        return cArr;
    }

    public s0 b() {
        char[] cArr;
        char c2;
        int i;
        char[] cArr2;
        boolean z;
        int i2 = this.i;
        char[] cArr3 = this.f3095d;
        char[] cArr4 = p;
        int[] iArr = s;
        int[] iArr2 = r;
        int[] iArr3 = u;
        while (true) {
            int i3 = this.f3096e;
            int i4 = this.k;
            int i5 = this.h;
            this.k = i4 + (i3 - i5);
            boolean z2 = false;
            while (i5 < i3) {
                char c3 = cArr3[i5];
                if (c3 != 133 && c3 != 8232 && c3 != 8233) {
                    switch (c3) {
                        case '\n':
                            if (!z2) {
                                this.j++;
                                break;
                            }
                            break;
                        case '\r':
                            this.j++;
                            z2 = true;
                            break;
                    }
                    z2 = false;
                    i5++;
                }
                this.j++;
                z2 = false;
                i5++;
            }
            if (z2) {
                if (i3 < i2) {
                    boolean z3 = cArr3[i3] == '\n';
                    cArr2 = cArr3;
                    i = i2;
                    z = z3;
                } else {
                    if (this.l) {
                        cArr2 = cArr3;
                        i = i2;
                    } else {
                        boolean d2 = d();
                        i = this.i;
                        i3 = this.f3096e;
                        cArr2 = this.f3095d;
                        if (!d2 && cArr2[i3] == '\n') {
                            z = true;
                        }
                    }
                    z = false;
                }
                if (z) {
                    this.j--;
                }
                i2 = i;
                cArr3 = cArr2;
            }
            this.h = i3;
            this.g = i3;
            this.f3093b = this.f3094c;
            int i6 = -1;
            int i7 = i3;
            int i8 = -1;
            while (true) {
                if (i3 < i2) {
                    cArr = cArr3;
                    c2 = cArr3[i3];
                    i3++;
                } else if (this.l) {
                    cArr = cArr3;
                    c2 = 65535;
                } else {
                    this.g = i3;
                    this.f3096e = i7;
                    boolean d3 = d();
                    int i9 = this.g;
                    i7 = this.f3096e;
                    char[] cArr5 = this.f3095d;
                    int i10 = this.i;
                    if (d3) {
                        i2 = i10;
                        c2 = 65535;
                        cArr = cArr5;
                    } else {
                        int i11 = i9 + 1;
                        c2 = cArr5[i9];
                        i3 = i11;
                        i2 = i10;
                        cArr = cArr5;
                    }
                }
                int i12 = iArr[iArr2[this.f3093b] + cArr4[c2]];
                if (i12 != i6) {
                    this.f3093b = i12;
                    int i13 = this.f3093b;
                    int i14 = iArr3[i13];
                    if ((i14 & 1) != 1) {
                        i6 = -1;
                    } else if ((i14 & 8) == 8) {
                        i7 = i3;
                        i8 = i13;
                    } else {
                        i6 = -1;
                        i7 = i3;
                        i8 = i13;
                    }
                    cArr3 = cArr;
                }
            }
            this.f3096e = i7;
            if (i8 >= 0) {
                i8 = q[i8];
            }
            switch (i8) {
                case 1:
                    this.m = false;
                    return new f1(c());
                case 2:
                    return new k0(c(), this.o);
                case 3:
                    return new b0(c());
                case 4:
                    return new y0();
                case 5:
                    return new d();
                case 6:
                    return new n0();
                case 7:
                    return new l();
                case 8:
                    this.m = true;
                    a(1);
                    break;
                case 9:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                    break;
                case 10:
                    return new p();
                case 11:
                    return new w0();
                case 12:
                    return new g0();
                case 13:
                    return new i0();
                case 14:
                    return new a0();
                case 15:
                    return new r();
                case 16:
                    return new d0();
                case 17:
                    a(0);
                    if (!this.m) {
                        break;
                    } else {
                        return new f1("");
                    }
                case 18:
                    return new j(c());
                case 19:
                    return new c1(c());
                case 20:
                    return new z();
                case 21:
                    return new l0();
                case 22:
                    return new c0();
                case 23:
                    return new n(c());
                case 24:
                    return new q(c());
                case 25:
                    return new i(c(), this.n);
                case 26:
                    return new g(c());
                case 27:
                    return new c(c());
                case 28:
                    return new s(c());
                case 29:
                    return new m(c(), this.n);
                case 30:
                    return new b(c(), this.n);
                default:
                    if (c2 == 65535 && this.h == this.g) {
                        this.l = true;
                        return null;
                    }
                    b(1);
                    throw null;
            }
            cArr3 = cArr;
        }
    }

    public final void a(int i) {
        this.f3094c = i;
    }
}
