package a.n;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* loaded from: classes.dex */
class a0 implements b0 {

    /* renamed from: a, reason: collision with root package name */
    private final ViewOverlay f425a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a0(View view) {
        this.f425a = view.getOverlay();
    }

    @Override // a.n.b0
    public void a(Drawable drawable) {
        this.f425a.add(drawable);
    }

    @Override // a.n.b0
    public void b(Drawable drawable) {
        this.f425a.remove(drawable);
    }
}
