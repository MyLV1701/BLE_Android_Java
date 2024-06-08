package c.a.b;

import java.lang.reflect.Field;
import java.util.Locale;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public abstract class c implements c.a.b.d {

    /* renamed from: b, reason: collision with root package name */
    public static final c f2172b = new a("IDENTITY", 0);

    /* renamed from: c, reason: collision with root package name */
    public static final c f2173c = new c("UPPER_CAMEL_CASE", 1) { // from class: c.a.b.c.b
        {
            a aVar = null;
        }

        @Override // c.a.b.d
        public String a(Field field) {
            return c.a(field.getName());
        }
    };

    /* renamed from: d, reason: collision with root package name */
    public static final c f2174d = new c("UPPER_CAMEL_CASE_WITH_SPACES", 2) { // from class: c.a.b.c.c
        {
            a aVar = null;
        }

        @Override // c.a.b.d
        public String a(Field field) {
            return c.a(c.a(field.getName(), " "));
        }
    };

    /* renamed from: e, reason: collision with root package name */
    public static final c f2175e = new c("LOWER_CASE_WITH_UNDERSCORES", 3) { // from class: c.a.b.c.d
        {
            a aVar = null;
        }

        @Override // c.a.b.d
        public String a(Field field) {
            return c.a(field.getName(), "_").toLowerCase(Locale.ENGLISH);
        }
    };

    /* renamed from: f, reason: collision with root package name */
    public static final c f2176f = new c("LOWER_CASE_WITH_DASHES", 4) { // from class: c.a.b.c.e
        {
            a aVar = null;
        }

        @Override // c.a.b.d
        public String a(Field field) {
            return c.a(field.getName(), "-").toLowerCase(Locale.ENGLISH);
        }
    };
    public static final c g = new c("LOWER_CASE_WITH_DOTS", 5) { // from class: c.a.b.c.f
        {
            a aVar = null;
        }

        @Override // c.a.b.d
        public String a(Field field) {
            return c.a(field.getName(), ".").toLowerCase(Locale.ENGLISH);
        }
    };
    private static final /* synthetic */ c[] h = {f2172b, f2173c, f2174d, f2175e, f2176f, g};

    /* loaded from: classes.dex */
    enum a extends c {
        a(String str, int i) {
            super(str, i, null);
        }

        @Override // c.a.b.d
        public String a(Field field) {
            return field.getName();
        }
    }

    private c(String str, int i) {
    }

    static String a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt) && sb.length() != 0) {
                sb.append(str2);
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    public static c valueOf(String str) {
        return (c) Enum.valueOf(c.class, str);
    }

    public static c[] values() {
        return (c[]) h.clone();
    }

    /* synthetic */ c(String str, int i, a aVar) {
        this(str, i);
    }

    static String a(String str) {
        int length = str.length() - 1;
        int i = 0;
        while (!Character.isLetter(str.charAt(i)) && i < length) {
            i++;
        }
        char charAt = str.charAt(i);
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        char upperCase = Character.toUpperCase(charAt);
        if (i == 0) {
            return upperCase + str.substring(1);
        }
        return str.substring(0, i) + upperCase + str.substring(i + 1);
    }
}
