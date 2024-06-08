package androidx.viewpager2.adapter;

import android.os.Handler;
import androidx.lifecycle.g;
import androidx.lifecycle.h;
import androidx.lifecycle.j;

/* loaded from: classes.dex */
class FragmentStateAdapter$5 implements h {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Handler f2015a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ Runnable f2016b;

    @Override // androidx.lifecycle.h
    public void a(j jVar, g.a aVar) {
        if (aVar == g.a.ON_DESTROY) {
            this.f2015a.removeCallbacks(this.f2016b);
            jVar.getLifecycle().b(this);
        }
    }
}
