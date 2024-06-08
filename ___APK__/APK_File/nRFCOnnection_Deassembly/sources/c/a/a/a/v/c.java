package c.a.a.a.v;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* loaded from: classes.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    private final View f2143a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f2144b = false;

    /* renamed from: c, reason: collision with root package name */
    private int f2145c = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public c(b bVar) {
        this.f2143a = (View) bVar;
    }

    private void d() {
        ViewParent parent = this.f2143a.getParent();
        if (parent instanceof CoordinatorLayout) {
            ((CoordinatorLayout) parent).a(this.f2143a);
        }
    }

    public void a(Bundle bundle) {
        this.f2144b = bundle.getBoolean("expanded", false);
        this.f2145c = bundle.getInt("expandedComponentIdHint", 0);
        if (this.f2144b) {
            d();
        }
    }

    public boolean b() {
        return this.f2144b;
    }

    public Bundle c() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", this.f2144b);
        bundle.putInt("expandedComponentIdHint", this.f2145c);
        return bundle;
    }

    public void a(int i) {
        this.f2145c = i;
    }

    public int a() {
        return this.f2145c;
    }
}
