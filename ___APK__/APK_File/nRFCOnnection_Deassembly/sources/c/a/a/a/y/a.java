package c.a.a.a.y;

import android.graphics.Typeface;

/* loaded from: classes.dex */
public final class a extends f {

    /* renamed from: a, reason: collision with root package name */
    private final Typeface f2148a;

    /* renamed from: b, reason: collision with root package name */
    private final InterfaceC0071a f2149b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f2150c;

    /* renamed from: c.a.a.a.y.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0071a {
        void a(Typeface typeface);
    }

    public a(InterfaceC0071a interfaceC0071a, Typeface typeface) {
        this.f2148a = typeface;
        this.f2149b = interfaceC0071a;
    }

    @Override // c.a.a.a.y.f
    public void a(Typeface typeface, boolean z) {
        a(typeface);
    }

    @Override // c.a.a.a.y.f
    public void a(int i) {
        a(this.f2148a);
    }

    public void a() {
        this.f2150c = true;
    }

    private void a(Typeface typeface) {
        if (this.f2150c) {
            return;
        }
        this.f2149b.a(typeface);
    }
}
