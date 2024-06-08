package com.google.android.material.appbar;

import a.f.l.e0;
import a.f.l.w;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class b extends c<View> {

    /* renamed from: d, reason: collision with root package name */
    final Rect f2359d;

    /* renamed from: e, reason: collision with root package name */
    final Rect f2360e;

    /* renamed from: f, reason: collision with root package name */
    private int f2361f;
    private int g;

    public b() {
        this.f2359d = new Rect();
        this.f2360e = new Rect();
        this.f2361f = 0;
    }

    private static int c(int i) {
        if (i == 0) {
            return 8388659;
        }
        return i;
    }

    abstract View a(List<View> list);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean a(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
        View a2;
        e0 lastWindowInsets;
        int i5 = view.getLayoutParams().height;
        if ((i5 != -1 && i5 != -2) || (a2 = a(coordinatorLayout.b(view))) == null) {
            return false;
        }
        int size = View.MeasureSpec.getSize(i3);
        if (size > 0) {
            if (w.m(a2) && (lastWindowInsets = coordinatorLayout.getLastWindowInsets()) != null) {
                size += lastWindowInsets.e() + lastWindowInsets.b();
            }
        } else {
            size = coordinatorLayout.getHeight();
        }
        int c2 = size + c(a2);
        int measuredHeight = a2.getMeasuredHeight();
        if (e()) {
            view.setTranslationY(-measuredHeight);
        } else {
            c2 -= measuredHeight;
        }
        coordinatorLayout.a(view, i, i2, View.MeasureSpec.makeMeasureSpec(c2, i5 == -1 ? 1073741824 : RecyclerView.UNDEFINED_DURATION), i4);
        return true;
    }

    abstract float b(View view);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.material.appbar.c
    public void b(CoordinatorLayout coordinatorLayout, View view, int i) {
        View a2 = a(coordinatorLayout.b(view));
        if (a2 != null) {
            CoordinatorLayout.f fVar = (CoordinatorLayout.f) view.getLayoutParams();
            Rect rect = this.f2359d;
            rect.set(coordinatorLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, a2.getBottom() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin, ((coordinatorLayout.getHeight() + a2.getBottom()) - coordinatorLayout.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin);
            e0 lastWindowInsets = coordinatorLayout.getLastWindowInsets();
            if (lastWindowInsets != null && w.m(coordinatorLayout) && !w.m(view)) {
                rect.left += lastWindowInsets.c();
                rect.right -= lastWindowInsets.d();
            }
            Rect rect2 = this.f2360e;
            a.f.l.d.a(c(fVar.f1171c), view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i);
            int a3 = a(a2);
            view.layout(rect2.left, rect2.top - a3, rect2.right, rect2.bottom - a3);
            this.f2361f = rect2.top - a2.getBottom();
            return;
        }
        super.b(coordinatorLayout, (CoordinatorLayout) view, i);
        this.f2361f = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c(View view) {
        return view.getMeasuredHeight();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int d() {
        return this.f2361f;
    }

    protected boolean e() {
        return false;
    }

    public final int c() {
        return this.g;
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2359d = new Rect();
        this.f2360e = new Rect();
        this.f2361f = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(View view) {
        if (this.g == 0) {
            return 0;
        }
        float b2 = b(view);
        int i = this.g;
        return a.f.g.a.a((int) (b2 * i), 0, i);
    }

    public final void b(int i) {
        this.g = i;
    }
}
