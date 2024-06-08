package c.a.b;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public abstract class s {

    /* renamed from: b, reason: collision with root package name */
    public static final s f2190b = new a("DEFAULT", 0);

    /* renamed from: c, reason: collision with root package name */
    public static final s f2191c = new s("STRING", 1) { // from class: c.a.b.s.b
        {
            a aVar = null;
        }
    };

    /* renamed from: d, reason: collision with root package name */
    private static final /* synthetic */ s[] f2192d = {f2190b, f2191c};

    /* loaded from: classes.dex */
    enum a extends s {
        a(String str, int i) {
            super(str, i, null);
        }
    }

    private s(String str, int i) {
    }

    public static s valueOf(String str) {
        return (s) Enum.valueOf(s.class, str);
    }

    public static s[] values() {
        return (s[]) f2192d.clone();
    }

    /* synthetic */ s(String str, int i, a aVar) {
        this(str, i);
    }
}
