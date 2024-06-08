package d.q;

/* loaded from: classes.dex */
public final class a {
    public static void a(boolean z) {
        if (!z) {
            throw new b();
        }
    }

    public static void a(boolean z, String str) {
        if (!z) {
            throw new b(str);
        }
    }
}
