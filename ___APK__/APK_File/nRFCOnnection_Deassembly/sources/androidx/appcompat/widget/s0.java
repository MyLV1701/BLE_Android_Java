package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
class s0 extends k0 {

    /* renamed from: b, reason: collision with root package name */
    private final WeakReference<Context> f1087b;

    public s0(Context context, Resources resources) {
        super(resources);
        this.f1087b = new WeakReference<>(context);
    }

    @Override // androidx.appcompat.widget.k0, android.content.res.Resources
    public Drawable getDrawable(int i) {
        Drawable drawable = super.getDrawable(i);
        Context context = this.f1087b.get();
        if (drawable != null && context != null) {
            j0.a().a(context, i, drawable);
        }
        return drawable;
    }
}
