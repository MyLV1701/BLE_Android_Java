package d.p;

/* loaded from: classes.dex */
public abstract class b0 {

    /* renamed from: a, reason: collision with root package name */
    private a f2839a = a();

    /* renamed from: b, reason: collision with root package name */
    private a f2840b = a();

    /* renamed from: c, reason: collision with root package name */
    private a f2841c = a();

    /* loaded from: classes.dex */
    protected static class a {

        /* renamed from: a, reason: collision with root package name */
        private StringBuffer f2842a = new StringBuffer();

        /* JADX INFO: Access modifiers changed from: protected */
        public boolean a() {
            StringBuffer stringBuffer = this.f2842a;
            return stringBuffer == null || stringBuffer.length() == 0;
        }

        protected String b() {
            StringBuffer stringBuffer = this.f2842a;
            return stringBuffer != null ? stringBuffer.toString() : "";
        }
    }

    static {
        d.q.c.b(b0.class);
    }

    protected abstract a a();

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (!this.f2839a.a()) {
            stringBuffer.append("&L");
            stringBuffer.append(this.f2839a.b());
        }
        if (!this.f2841c.a()) {
            stringBuffer.append("&C");
            stringBuffer.append(this.f2841c.b());
        }
        if (!this.f2840b.a()) {
            stringBuffer.append("&R");
            stringBuffer.append(this.f2840b.b());
        }
        return stringBuffer.toString();
    }
}
