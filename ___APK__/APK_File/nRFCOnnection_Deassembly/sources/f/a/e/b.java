package f.a.e;

/* loaded from: classes.dex */
public enum b {
    ERROR(40, "ERROR"),
    WARN(30, "WARN"),
    INFO(20, "INFO"),
    DEBUG(10, "DEBUG"),
    TRACE(0, "TRACE");


    /* renamed from: b, reason: collision with root package name */
    private String f3445b;

    b(int i, String str) {
        this.f3445b = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.f3445b;
    }
}
