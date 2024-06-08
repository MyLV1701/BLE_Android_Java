package com.google.android.material.transformation;

import a.f.l.w;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.List;

/* loaded from: classes.dex */
public abstract class ExpandableBehavior extends CoordinatorLayout.c<View> {

    /* renamed from: a, reason: collision with root package name */
    private int f2778a;

    /* loaded from: classes.dex */
    class a implements ViewTreeObserver.OnPreDrawListener {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f2779b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f2780c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ c.a.a.a.v.b f2781d;

        a(View view, int i, c.a.a.a.v.b bVar) {
            this.f2779b = view;
            this.f2780c = i;
            this.f2781d = bVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.f2779b.getViewTreeObserver().removeOnPreDrawListener(this);
            if (ExpandableBehavior.this.f2778a == this.f2780c) {
                ExpandableBehavior expandableBehavior = ExpandableBehavior.this;
                c.a.a.a.v.b bVar = this.f2781d;
                expandableBehavior.a((View) bVar, this.f2779b, bVar.a(), false);
            }
            return false;
        }
    }

    public ExpandableBehavior() {
        this.f2778a = 0;
    }

    protected abstract boolean a(View view, View view2, boolean z, boolean z2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean b(CoordinatorLayout coordinatorLayout, View view, View view2) {
        c.a.a.a.v.b bVar = (c.a.a.a.v.b) view2;
        if (!a(bVar.a())) {
            return false;
        }
        this.f2778a = bVar.a() ? 1 : 2;
        return a((View) bVar, view, bVar.a(), true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected c.a.a.a.v.b e(CoordinatorLayout coordinatorLayout, View view) {
        List<View> b2 = coordinatorLayout.b(view);
        int size = b2.size();
        for (int i = 0; i < size; i++) {
            View view2 = b2.get(i);
            if (a(coordinatorLayout, (CoordinatorLayout) view, view2)) {
                return (c.a.a.a.v.b) view2;
            }
        }
        return null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean a(CoordinatorLayout coordinatorLayout, View view, int i) {
        c.a.a.a.v.b e2;
        if (w.F(view) || (e2 = e(coordinatorLayout, view)) == null || !a(e2.a())) {
            return false;
        }
        this.f2778a = e2.a() ? 1 : 2;
        view.getViewTreeObserver().addOnPreDrawListener(new a(view, this.f2778a, e2));
        return false;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2778a = 0;
    }

    private boolean a(boolean z) {
        if (!z) {
            return this.f2778a == 1;
        }
        int i = this.f2778a;
        return i == 0 || i == 2;
    }
}
