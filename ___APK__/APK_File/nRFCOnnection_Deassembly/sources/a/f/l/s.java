package a.f.l;

import android.view.View;
import android.view.ViewTreeObserver;

/* loaded from: classes.dex */
public final class s implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* renamed from: b, reason: collision with root package name */
    private final View f327b;

    /* renamed from: c, reason: collision with root package name */
    private ViewTreeObserver f328c;

    /* renamed from: d, reason: collision with root package name */
    private final Runnable f329d;

    private s(View view, Runnable runnable) {
        this.f327b = view;
        this.f328c = view.getViewTreeObserver();
        this.f329d = runnable;
    }

    public static s a(View view, Runnable runnable) {
        if (view == null) {
            throw new NullPointerException("view == null");
        }
        if (runnable != null) {
            s sVar = new s(view, runnable);
            view.getViewTreeObserver().addOnPreDrawListener(sVar);
            view.addOnAttachStateChangeListener(sVar);
            return sVar;
        }
        throw new NullPointerException("runnable == null");
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        a();
        this.f329d.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.f328c = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        a();
    }

    public void a() {
        if (this.f328c.isAlive()) {
            this.f328c.removeOnPreDrawListener(this);
        } else {
            this.f327b.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.f327b.removeOnAttachStateChangeListener(this);
    }
}
