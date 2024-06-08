package com.google.android.material.appbar;

import a.f.l.w;
import android.view.View;

/* loaded from: classes.dex */
class d {

    /* renamed from: a, reason: collision with root package name */
    private final View f2365a;

    /* renamed from: b, reason: collision with root package name */
    private int f2366b;

    /* renamed from: c, reason: collision with root package name */
    private int f2367c;

    /* renamed from: d, reason: collision with root package name */
    private int f2368d;

    /* renamed from: e, reason: collision with root package name */
    private int f2369e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f2370f = true;
    private boolean g = true;

    public d(View view) {
        this.f2365a = view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        View view = this.f2365a;
        w.f(view, this.f2368d - (view.getTop() - this.f2366b));
        View view2 = this.f2365a;
        w.e(view2, this.f2369e - (view2.getLeft() - this.f2367c));
    }

    public boolean b(int i) {
        if (!this.f2370f || this.f2368d == i) {
            return false;
        }
        this.f2368d = i;
        a();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        this.f2366b = this.f2365a.getTop();
        this.f2367c = this.f2365a.getLeft();
    }

    public boolean a(int i) {
        if (!this.g || this.f2369e == i) {
            return false;
        }
        this.f2369e = i;
        a();
        return true;
    }

    public int b() {
        return this.f2368d;
    }
}
