package androidx.preference;

import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

@Deprecated
/* loaded from: classes.dex */
public class k extends androidx.recyclerview.widget.s {

    /* renamed from: c, reason: collision with root package name */
    final RecyclerView f1658c;

    /* renamed from: d, reason: collision with root package name */
    final a.f.l.a f1659d;

    /* renamed from: e, reason: collision with root package name */
    final a.f.l.a f1660e;

    /* loaded from: classes.dex */
    class a extends a.f.l.a {
        a() {
        }

        @Override // a.f.l.a
        public void onInitializeAccessibilityNodeInfo(View view, a.f.l.f0.c cVar) {
            Preference a2;
            k.this.f1659d.onInitializeAccessibilityNodeInfo(view, cVar);
            int childAdapterPosition = k.this.f1658c.getChildAdapterPosition(view);
            RecyclerView.g adapter = k.this.f1658c.getAdapter();
            if ((adapter instanceof h) && (a2 = ((h) adapter).a(childAdapterPosition)) != null) {
                a2.onInitializeAccessibilityNodeInfo(cVar);
            }
        }

        @Override // a.f.l.a
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            return k.this.f1659d.performAccessibilityAction(view, i, bundle);
        }
    }

    public k(RecyclerView recyclerView) {
        super(recyclerView);
        this.f1659d = super.a();
        this.f1660e = new a();
        this.f1658c = recyclerView;
    }

    @Override // androidx.recyclerview.widget.s
    public a.f.l.a a() {
        return this.f1660e;
    }
}
