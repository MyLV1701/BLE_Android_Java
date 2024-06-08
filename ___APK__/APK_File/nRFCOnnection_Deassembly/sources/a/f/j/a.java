package a.f.j;

import android.text.SpannableStringBuilder;
import java.util.Locale;

/* loaded from: classes.dex */
public final class a {

    /* renamed from: d, reason: collision with root package name */
    static final d f227d = e.f257c;

    /* renamed from: e, reason: collision with root package name */
    private static final String f228e = Character.toString(8206);

    /* renamed from: f, reason: collision with root package name */
    private static final String f229f = Character.toString(8207);
    static final a g = new a(false, 2, f227d);
    static final a h = new a(true, 2, f227d);

    /* renamed from: a, reason: collision with root package name */
    private final boolean f230a;

    /* renamed from: b, reason: collision with root package name */
    private final int f231b;

    /* renamed from: c, reason: collision with root package name */
    private final d f232c;

    /* renamed from: a.f.j.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0012a {

        /* renamed from: a, reason: collision with root package name */
        private boolean f233a;

        /* renamed from: b, reason: collision with root package name */
        private int f234b;

        /* renamed from: c, reason: collision with root package name */
        private d f235c;

        public C0012a() {
            b(a.a(Locale.getDefault()));
        }

        private static a a(boolean z) {
            return z ? a.h : a.g;
        }

        private void b(boolean z) {
            this.f233a = z;
            this.f235c = a.f227d;
            this.f234b = 2;
        }

        public a a() {
            if (this.f234b == 2 && this.f235c == a.f227d) {
                return a(this.f233a);
            }
            return new a(this.f233a, this.f234b, this.f235c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: f, reason: collision with root package name */
        private static final byte[] f236f = new byte[1792];

        /* renamed from: a, reason: collision with root package name */
        private final CharSequence f237a;

        /* renamed from: b, reason: collision with root package name */
        private final boolean f238b;

        /* renamed from: c, reason: collision with root package name */
        private final int f239c;

        /* renamed from: d, reason: collision with root package name */
        private int f240d;

        /* renamed from: e, reason: collision with root package name */
        private char f241e;

        static {
            for (int i = 0; i < 1792; i++) {
                f236f[i] = Character.getDirectionality(i);
            }
        }

        b(CharSequence charSequence, boolean z) {
            this.f237a = charSequence;
            this.f238b = z;
            this.f239c = charSequence.length();
        }

        private static byte a(char c2) {
            return c2 < 1792 ? f236f[c2] : Character.getDirectionality(c2);
        }

        private byte e() {
            char c2;
            int i = this.f240d;
            do {
                int i2 = this.f240d;
                if (i2 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f237a;
                int i3 = i2 - 1;
                this.f240d = i3;
                this.f241e = charSequence.charAt(i3);
                c2 = this.f241e;
                if (c2 == '&') {
                    return (byte) 12;
                }
            } while (c2 != ';');
            this.f240d = i;
            this.f241e = ';';
            return (byte) 13;
        }

        private byte f() {
            char charAt;
            do {
                int i = this.f240d;
                if (i >= this.f239c) {
                    return (byte) 12;
                }
                CharSequence charSequence = this.f237a;
                this.f240d = i + 1;
                charAt = charSequence.charAt(i);
                this.f241e = charAt;
            } while (charAt != ';');
            return (byte) 12;
        }

        private byte g() {
            char charAt;
            int i = this.f240d;
            while (true) {
                int i2 = this.f240d;
                if (i2 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f237a;
                int i3 = i2 - 1;
                this.f240d = i3;
                this.f241e = charSequence.charAt(i3);
                char c2 = this.f241e;
                if (c2 == '<') {
                    return (byte) 12;
                }
                if (c2 == '>') {
                    break;
                }
                if (c2 == '\"' || c2 == '\'') {
                    char c3 = this.f241e;
                    do {
                        int i4 = this.f240d;
                        if (i4 > 0) {
                            CharSequence charSequence2 = this.f237a;
                            int i5 = i4 - 1;
                            this.f240d = i5;
                            charAt = charSequence2.charAt(i5);
                            this.f241e = charAt;
                        }
                    } while (charAt != c3);
                }
            }
            this.f240d = i;
            this.f241e = '>';
            return (byte) 13;
        }

        private byte h() {
            char charAt;
            int i = this.f240d;
            while (true) {
                int i2 = this.f240d;
                if (i2 < this.f239c) {
                    CharSequence charSequence = this.f237a;
                    this.f240d = i2 + 1;
                    this.f241e = charSequence.charAt(i2);
                    char c2 = this.f241e;
                    if (c2 == '>') {
                        return (byte) 12;
                    }
                    if (c2 == '\"' || c2 == '\'') {
                        char c3 = this.f241e;
                        do {
                            int i3 = this.f240d;
                            if (i3 < this.f239c) {
                                CharSequence charSequence2 = this.f237a;
                                this.f240d = i3 + 1;
                                charAt = charSequence2.charAt(i3);
                                this.f241e = charAt;
                            }
                        } while (charAt != c3);
                    }
                } else {
                    this.f240d = i;
                    this.f241e = '<';
                    return (byte) 13;
                }
            }
        }

        byte b() {
            this.f241e = this.f237a.charAt(this.f240d);
            if (Character.isHighSurrogate(this.f241e)) {
                int codePointAt = Character.codePointAt(this.f237a, this.f240d);
                this.f240d += Character.charCount(codePointAt);
                return Character.getDirectionality(codePointAt);
            }
            this.f240d++;
            byte a2 = a(this.f241e);
            if (!this.f238b) {
                return a2;
            }
            char c2 = this.f241e;
            if (c2 == '<') {
                return h();
            }
            return c2 == '&' ? f() : a2;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:46:0x0045. Please report as an issue. */
        int c() {
            this.f240d = 0;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (this.f240d < this.f239c && i == 0) {
                byte b2 = b();
                if (b2 != 0) {
                    if (b2 == 1 || b2 == 2) {
                        if (i3 == 0) {
                            return 1;
                        }
                    } else if (b2 != 9) {
                        switch (b2) {
                            case 14:
                            case 15:
                                i3++;
                                i2 = -1;
                                continue;
                            case 16:
                            case 17:
                                i3++;
                                i2 = 1;
                                continue;
                            case 18:
                                i3--;
                                i2 = 0;
                                continue;
                        }
                    }
                } else if (i3 == 0) {
                    return -1;
                }
                i = i3;
            }
            if (i == 0) {
                return 0;
            }
            if (i2 != 0) {
                return i2;
            }
            while (this.f240d > 0) {
                switch (a()) {
                    case 14:
                    case 15:
                        if (i == i3) {
                            return -1;
                        }
                        i3--;
                    case 16:
                    case 17:
                        if (i == i3) {
                            return 1;
                        }
                        i3--;
                    case 18:
                        i3++;
                }
            }
            return 0;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:33:0x001c. Please report as an issue. */
        int d() {
            this.f240d = this.f239c;
            int i = 0;
            int i2 = 0;
            while (this.f240d > 0) {
                byte a2 = a();
                if (a2 != 0) {
                    if (a2 == 1 || a2 == 2) {
                        if (i2 == 0) {
                            return 1;
                        }
                        if (i == 0) {
                            i = i2;
                        }
                    } else if (a2 != 9) {
                        switch (a2) {
                            case 14:
                            case 15:
                                if (i == i2) {
                                    return -1;
                                }
                                i2--;
                                break;
                            case 16:
                            case 17:
                                if (i == i2) {
                                    return 1;
                                }
                                i2--;
                                break;
                            case 18:
                                i2++;
                                break;
                            default:
                                if (i != 0) {
                                    break;
                                } else {
                                    i = i2;
                                    break;
                                }
                        }
                    } else {
                        continue;
                    }
                } else {
                    if (i2 == 0) {
                        return -1;
                    }
                    if (i == 0) {
                        i = i2;
                    }
                }
            }
            return 0;
        }

        byte a() {
            this.f241e = this.f237a.charAt(this.f240d - 1);
            if (Character.isLowSurrogate(this.f241e)) {
                int codePointBefore = Character.codePointBefore(this.f237a, this.f240d);
                this.f240d -= Character.charCount(codePointBefore);
                return Character.getDirectionality(codePointBefore);
            }
            this.f240d--;
            byte a2 = a(this.f241e);
            if (!this.f238b) {
                return a2;
            }
            char c2 = this.f241e;
            if (c2 == '>') {
                return g();
            }
            return c2 == ';' ? e() : a2;
        }
    }

    a(boolean z, int i, d dVar) {
        this.f230a = z;
        this.f231b = i;
        this.f232c = dVar;
    }

    public static a b() {
        return new C0012a().a();
    }

    private static int c(CharSequence charSequence) {
        return new b(charSequence, false).d();
    }

    public boolean a() {
        return (this.f231b & 2) != 0;
    }

    private String a(CharSequence charSequence, d dVar) {
        boolean a2 = dVar.a(charSequence, 0, charSequence.length());
        if (this.f230a || !(a2 || c(charSequence) == 1)) {
            return this.f230a ? (!a2 || c(charSequence) == -1) ? f229f : "" : "";
        }
        return f228e;
    }

    private String b(CharSequence charSequence, d dVar) {
        boolean a2 = dVar.a(charSequence, 0, charSequence.length());
        if (this.f230a || !(a2 || b(charSequence) == 1)) {
            return this.f230a ? (!a2 || b(charSequence) == -1) ? f229f : "" : "";
        }
        return f228e;
    }

    private static int b(CharSequence charSequence) {
        return new b(charSequence, false).c();
    }

    public CharSequence a(CharSequence charSequence, d dVar, boolean z) {
        if (charSequence == null) {
            return null;
        }
        boolean a2 = dVar.a(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (a() && z) {
            spannableStringBuilder.append((CharSequence) b(charSequence, a2 ? e.f256b : e.f255a));
        }
        if (a2 != this.f230a) {
            spannableStringBuilder.append(a2 ? (char) 8235 : (char) 8234);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z) {
            spannableStringBuilder.append((CharSequence) a(charSequence, a2 ? e.f256b : e.f255a));
        }
        return spannableStringBuilder;
    }

    public CharSequence a(CharSequence charSequence) {
        return a(charSequence, this.f232c, true);
    }

    static boolean a(Locale locale) {
        return f.b(locale) == 1;
    }
}
