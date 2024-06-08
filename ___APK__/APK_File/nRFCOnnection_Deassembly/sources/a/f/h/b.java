package a.f.h;

/* loaded from: classes.dex */
public class b extends RuntimeException {
    public b() {
        this(null);
    }

    public b(String str) {
        super(str == null ? "The operation has been canceled." : str);
    }
}
