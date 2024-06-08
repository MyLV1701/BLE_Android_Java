package androidx.viewpager2.adapter;

import androidx.lifecycle.g;
import androidx.lifecycle.h;
import androidx.lifecycle.j;

/* loaded from: classes.dex */
class FragmentStateAdapter$2 implements h {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ b f2013a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ a f2014b;

    @Override // androidx.lifecycle.h
    public void a(j jVar, g.a aVar) {
        if (this.f2014b.a()) {
            return;
        }
        jVar.getLifecycle().b(this);
        this.f2013a.a();
        throw null;
    }
}
