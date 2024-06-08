package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.widget.t0;

/* loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements g.b, o, AdapterView.OnItemClickListener {

    /* renamed from: d, reason: collision with root package name */
    private static final int[] f746d = {R.attr.background, R.attr.divider};

    /* renamed from: b, reason: collision with root package name */
    private g f747b;

    /* renamed from: c, reason: collision with root package name */
    private int f748c;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listViewStyle);
    }

    @Override // androidx.appcompat.view.menu.o
    public void a(g gVar) {
        this.f747b = gVar;
    }

    public int getWindowAnimations() {
        return this.f748c;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        a((j) getAdapter().getItem(i));
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        t0 a2 = t0.a(context, attributeSet, f746d, i, 0);
        if (a2.g(0)) {
            setBackgroundDrawable(a2.b(0));
        }
        if (a2.g(1)) {
            setDivider(a2.b(1));
        }
        a2.a();
    }

    @Override // androidx.appcompat.view.menu.g.b
    public boolean a(j jVar) {
        return this.f747b.a(jVar, 0);
    }
}
