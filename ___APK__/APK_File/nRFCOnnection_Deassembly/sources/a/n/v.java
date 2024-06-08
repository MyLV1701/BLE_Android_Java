package a.n;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

/* loaded from: classes.dex */
class v implements w {

    /* renamed from: a, reason: collision with root package name */
    private final ViewGroupOverlay f524a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public v(ViewGroup viewGroup) {
        this.f524a = viewGroup.getOverlay();
    }

    @Override // a.n.b0
    public void a(Drawable drawable) {
        this.f524a.add(drawable);
    }

    @Override // a.n.b0
    public void b(Drawable drawable) {
        this.f524a.remove(drawable);
    }

    @Override // a.n.w
    public void a(View view) {
        this.f524a.add(view);
    }

    @Override // a.n.w
    public void b(View view) {
        this.f524a.remove(view);
    }
}
