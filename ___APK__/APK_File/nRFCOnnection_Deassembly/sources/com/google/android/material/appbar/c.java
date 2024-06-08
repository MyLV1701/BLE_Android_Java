package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c<V extends View> extends CoordinatorLayout.c<V> {

    /* renamed from: a, reason: collision with root package name */
    private d f2362a;

    /* renamed from: b, reason: collision with root package name */
    private int f2363b;

    /* renamed from: c, reason: collision with root package name */
    private int f2364c;

    public c() {
        this.f2363b = 0;
        this.f2364c = 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean a(CoordinatorLayout coordinatorLayout, V v, int i) {
        b(coordinatorLayout, (CoordinatorLayout) v, i);
        if (this.f2362a == null) {
            this.f2362a = new d(v);
        }
        this.f2362a.c();
        this.f2362a.a();
        int i2 = this.f2363b;
        if (i2 != 0) {
            this.f2362a.b(i2);
            this.f2363b = 0;
        }
        int i3 = this.f2364c;
        if (i3 == 0) {
            return true;
        }
        this.f2362a.a(i3);
        this.f2364c = 0;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(CoordinatorLayout coordinatorLayout, V v, int i) {
        coordinatorLayout.c(v, i);
    }

    public int b() {
        d dVar = this.f2362a;
        if (dVar != null) {
            return dVar.b();
        }
        return 0;
    }

    public c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2363b = 0;
        this.f2364c = 0;
    }

    public boolean a(int i) {
        d dVar = this.f2362a;
        if (dVar != null) {
            return dVar.b(i);
        }
        this.f2363b = i;
        return false;
    }
}
