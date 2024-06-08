package androidx.recyclerview.widget;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public class s extends a.f.l.a {

    /* renamed from: a, reason: collision with root package name */
    final RecyclerView f1920a;

    /* renamed from: b, reason: collision with root package name */
    private final a f1921b;

    /* loaded from: classes.dex */
    public static class a extends a.f.l.a {

        /* renamed from: a, reason: collision with root package name */
        final s f1922a;

        /* renamed from: b, reason: collision with root package name */
        private Map<View, a.f.l.a> f1923b = new WeakHashMap();

        public a(s sVar) {
            this.f1922a = sVar;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public a.f.l.a a(View view) {
            return this.f1923b.remove(view);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void b(View view) {
            a.f.l.a b2 = a.f.l.w.b(view);
            if (b2 == null || b2 == this) {
                return;
            }
            this.f1923b.put(view, b2);
        }

        @Override // a.f.l.a
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            a.f.l.a aVar = this.f1923b.get(view);
            if (aVar != null) {
                return aVar.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
            }
            return super.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
        }

        @Override // a.f.l.a
        public a.f.l.f0.d getAccessibilityNodeProvider(View view) {
            a.f.l.a aVar = this.f1923b.get(view);
            if (aVar != null) {
                return aVar.getAccessibilityNodeProvider(view);
            }
            return super.getAccessibilityNodeProvider(view);
        }

        @Override // a.f.l.a
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            a.f.l.a aVar = this.f1923b.get(view);
            if (aVar != null) {
                aVar.onInitializeAccessibilityEvent(view, accessibilityEvent);
            } else {
                super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            }
        }

        @Override // a.f.l.a
        public void onInitializeAccessibilityNodeInfo(View view, a.f.l.f0.c cVar) {
            if (!this.f1922a.b() && this.f1922a.f1920a.getLayoutManager() != null) {
                this.f1922a.f1920a.getLayoutManager().a(view, cVar);
                a.f.l.a aVar = this.f1923b.get(view);
                if (aVar != null) {
                    aVar.onInitializeAccessibilityNodeInfo(view, cVar);
                    return;
                } else {
                    super.onInitializeAccessibilityNodeInfo(view, cVar);
                    return;
                }
            }
            super.onInitializeAccessibilityNodeInfo(view, cVar);
        }

        @Override // a.f.l.a
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            a.f.l.a aVar = this.f1923b.get(view);
            if (aVar != null) {
                aVar.onPopulateAccessibilityEvent(view, accessibilityEvent);
            } else {
                super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            }
        }

        @Override // a.f.l.a
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            a.f.l.a aVar = this.f1923b.get(viewGroup);
            if (aVar != null) {
                return aVar.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
            }
            return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }

        @Override // a.f.l.a
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (!this.f1922a.b() && this.f1922a.f1920a.getLayoutManager() != null) {
                a.f.l.a aVar = this.f1923b.get(view);
                if (aVar != null) {
                    if (aVar.performAccessibilityAction(view, i, bundle)) {
                        return true;
                    }
                } else if (super.performAccessibilityAction(view, i, bundle)) {
                    return true;
                }
                return this.f1922a.f1920a.getLayoutManager().a(view, i, bundle);
            }
            return super.performAccessibilityAction(view, i, bundle);
        }

        @Override // a.f.l.a
        public void sendAccessibilityEvent(View view, int i) {
            a.f.l.a aVar = this.f1923b.get(view);
            if (aVar != null) {
                aVar.sendAccessibilityEvent(view, i);
            } else {
                super.sendAccessibilityEvent(view, i);
            }
        }

        @Override // a.f.l.a
        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            a.f.l.a aVar = this.f1923b.get(view);
            if (aVar != null) {
                aVar.sendAccessibilityEventUnchecked(view, accessibilityEvent);
            } else {
                super.sendAccessibilityEventUnchecked(view, accessibilityEvent);
            }
        }
    }

    public s(RecyclerView recyclerView) {
        this.f1920a = recyclerView;
        a.f.l.a a2 = a();
        if (a2 != null && (a2 instanceof a)) {
            this.f1921b = (a) a2;
        } else {
            this.f1921b = new a(this);
        }
    }

    public a.f.l.a a() {
        return this.f1921b;
    }

    boolean b() {
        return this.f1920a.hasPendingAdapterUpdates();
    }

    @Override // a.f.l.a
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        if (!(view instanceof RecyclerView) || b()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().a(accessibilityEvent);
        }
    }

    @Override // a.f.l.a
    public void onInitializeAccessibilityNodeInfo(View view, a.f.l.f0.c cVar) {
        super.onInitializeAccessibilityNodeInfo(view, cVar);
        if (b() || this.f1920a.getLayoutManager() == null) {
            return;
        }
        this.f1920a.getLayoutManager().a(cVar);
    }

    @Override // a.f.l.a
    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (super.performAccessibilityAction(view, i, bundle)) {
            return true;
        }
        if (b() || this.f1920a.getLayoutManager() == null) {
            return false;
        }
        return this.f1920a.getLayoutManager().a(i, bundle);
    }
}
