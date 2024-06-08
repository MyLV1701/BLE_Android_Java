package androidx.recyclerview.widget;

import android.graphics.Canvas;
import android.os.Build;
import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class l implements k {

    /* renamed from: a, reason: collision with root package name */
    static final k f1907a = new l();

    l() {
    }

    private static float a(RecyclerView recyclerView, View view) {
        int childCount = recyclerView.getChildCount();
        float f2 = 0.0f;
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            if (childAt != view) {
                float l = a.f.l.w.l(childAt);
                if (l > f2) {
                    f2 = l;
                }
            }
        }
        return f2;
    }

    @Override // androidx.recyclerview.widget.k
    public void a(Canvas canvas, RecyclerView recyclerView, View view, float f2, float f3, int i, boolean z) {
    }

    @Override // androidx.recyclerview.widget.k
    public void b(Canvas canvas, RecyclerView recyclerView, View view, float f2, float f3, int i, boolean z) {
        if (Build.VERSION.SDK_INT >= 21 && z && view.getTag(a.m.c.item_touch_helper_previous_elevation) == null) {
            Float valueOf = Float.valueOf(a.f.l.w.l(view));
            a.f.l.w.a(view, a(recyclerView, view) + 1.0f);
            view.setTag(a.m.c.item_touch_helper_previous_elevation, valueOf);
        }
        view.setTranslationX(f2);
        view.setTranslationY(f3);
    }

    @Override // androidx.recyclerview.widget.k
    public void b(View view) {
    }

    @Override // androidx.recyclerview.widget.k
    public void a(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            Object tag = view.getTag(a.m.c.item_touch_helper_previous_elevation);
            if (tag instanceof Float) {
                a.f.l.w.a(view, ((Float) tag).floatValue());
            }
            view.setTag(a.m.c.item_touch_helper_previous_elevation, null);
        }
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
    }
}
