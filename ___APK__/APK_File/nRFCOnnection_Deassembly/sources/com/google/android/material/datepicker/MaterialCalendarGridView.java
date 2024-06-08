package com.google.android.material.datepicker;

import a.f.l.w;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;
import java.util.Calendar;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class MaterialCalendarGridView extends GridView {

    /* renamed from: b, reason: collision with root package name */
    private final Calendar f2448b;

    /* loaded from: classes.dex */
    class a extends a.f.l.a {
        a(MaterialCalendarGridView materialCalendarGridView) {
        }

        @Override // a.f.l.a
        public void onInitializeAccessibilityNodeInfo(View view, a.f.l.f0.c cVar) {
            super.onInitializeAccessibilityNodeInfo(view, cVar);
            cVar.a((Object) null);
        }
    }

    public MaterialCalendarGridView(Context context) {
        this(context, null);
    }

    private void a(int i, Rect rect) {
        if (i == 33) {
            setSelection(getAdapter().b());
        } else if (i == 130) {
            setSelection(getAdapter().a());
        } else {
            super.onFocusChanged(true, i, rect);
        }
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getAdapter().notifyDataSetChanged();
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        int a2;
        int a3;
        int a4;
        int a5;
        MaterialCalendarGridView materialCalendarGridView = this;
        super.onDraw(canvas);
        j adapter = getAdapter();
        d<?> dVar = adapter.f2499c;
        c cVar = adapter.f2500d;
        Long item = adapter.getItem(adapter.a());
        Long item2 = adapter.getItem(adapter.b());
        for (a.f.k.d<Long, Long> dVar2 : dVar.a()) {
            Long l = dVar2.f268a;
            if (l != null) {
                if (dVar2.f269b == null) {
                    continue;
                } else {
                    long longValue = l.longValue();
                    long longValue2 = dVar2.f269b.longValue();
                    if (a(item, item2, Long.valueOf(longValue), Long.valueOf(longValue2))) {
                        return;
                    }
                    if (longValue < item.longValue()) {
                        a2 = adapter.a();
                        a3 = adapter.b(a2) ? 0 : materialCalendarGridView.getChildAt(a2 - 1).getRight();
                    } else {
                        materialCalendarGridView.f2448b.setTimeInMillis(longValue);
                        a2 = adapter.a(materialCalendarGridView.f2448b.get(5));
                        a3 = a(materialCalendarGridView.getChildAt(a2));
                    }
                    if (longValue2 > item2.longValue()) {
                        a4 = adapter.b();
                        if (adapter.c(a4)) {
                            a5 = getWidth();
                        } else {
                            a5 = materialCalendarGridView.getChildAt(a4).getRight();
                        }
                    } else {
                        materialCalendarGridView.f2448b.setTimeInMillis(longValue2);
                        a4 = adapter.a(materialCalendarGridView.f2448b.get(5));
                        a5 = a(materialCalendarGridView.getChildAt(a4));
                    }
                    int itemId = (int) adapter.getItemId(a2);
                    int itemId2 = (int) adapter.getItemId(a4);
                    while (itemId <= itemId2) {
                        int numColumns = getNumColumns() * itemId;
                        int numColumns2 = (getNumColumns() + numColumns) - 1;
                        View childAt = materialCalendarGridView.getChildAt(numColumns);
                        canvas.drawRect(numColumns > a2 ? 0 : a3, childAt.getTop() + cVar.f2460a.b(), a4 > numColumns2 ? getWidth() : a5, childAt.getBottom() - cVar.f2460a.a(), cVar.h);
                        itemId++;
                        materialCalendarGridView = this;
                    }
                }
            }
            materialCalendarGridView = this;
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        if (z) {
            a(i, rect);
        } else {
            super.onFocusChanged(false, i, rect);
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!super.onKeyDown(i, keyEvent)) {
            return false;
        }
        if (getSelectedItemPosition() == -1 || getSelectedItemPosition() >= getAdapter().a()) {
            return true;
        }
        if (19 != i) {
            return false;
        }
        setSelection(getAdapter().a());
        return true;
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public void setSelection(int i) {
        if (i < getAdapter().a()) {
            super.setSelection(getAdapter().a());
        } else {
            super.setSelection(i);
        }
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.widget.AdapterView
    public final void setAdapter(ListAdapter listAdapter) {
        if (listAdapter instanceof j) {
            super.setAdapter(listAdapter);
            return;
        }
        throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", MaterialCalendarGridView.class.getCanonicalName(), j.class.getCanonicalName()));
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2448b = o.b();
        if (h.a(getContext())) {
            setNextFocusLeftId(c.a.a.a.f.cancel_button);
            setNextFocusRightId(c.a.a.a.f.confirm_button);
        }
        w.a(this, new a(this));
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public ListAdapter getAdapter2() {
        return (j) super.getAdapter();
    }

    private static boolean a(Long l, Long l2, Long l3, Long l4) {
        return l == null || l2 == null || l3 == null || l4 == null || l3.longValue() > l2.longValue() || l4.longValue() < l.longValue();
    }

    private static int a(View view) {
        return view.getLeft() + (view.getWidth() / 2);
    }
}
