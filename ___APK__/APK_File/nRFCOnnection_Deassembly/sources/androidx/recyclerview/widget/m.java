package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
class m {

    /* renamed from: b, reason: collision with root package name */
    int f1909b;

    /* renamed from: c, reason: collision with root package name */
    int f1910c;

    /* renamed from: d, reason: collision with root package name */
    int f1911d;

    /* renamed from: e, reason: collision with root package name */
    int f1912e;
    boolean h;
    boolean i;

    /* renamed from: a, reason: collision with root package name */
    boolean f1908a = true;

    /* renamed from: f, reason: collision with root package name */
    int f1913f = 0;
    int g = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(RecyclerView.a0 a0Var) {
        int i = this.f1910c;
        return i >= 0 && i < a0Var.a();
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f1909b + ", mCurrentPosition=" + this.f1910c + ", mItemDirection=" + this.f1911d + ", mLayoutDirection=" + this.f1912e + ", mStartLine=" + this.f1913f + ", mEndLine=" + this.g + '}';
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View a(RecyclerView.v vVar) {
        View d2 = vVar.d(this.f1910c);
        this.f1910c += this.f1911d;
        return d2;
    }
}
