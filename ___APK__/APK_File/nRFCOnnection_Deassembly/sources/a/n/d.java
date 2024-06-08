package a.n;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public class d extends j0 {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends n {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f448a;

        a(d dVar, View view) {
            this.f448a = view;
        }

        @Override // a.n.m.f
        public void e(m mVar) {
            c0.a(this.f448a, 1.0f);
            c0.a(this.f448a);
            mVar.b(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        private final View f449a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f450b = false;

        b(View view) {
            this.f449a = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            c0.a(this.f449a, 1.0f);
            if (this.f450b) {
                this.f449a.setLayerType(0, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (a.f.l.w.B(this.f449a) && this.f449a.getLayerType() == 0) {
                this.f450b = true;
                this.f449a.setLayerType(2, null);
            }
        }
    }

    public d(int i) {
        a(i);
    }

    private Animator a(View view, float f2, float f3) {
        if (f2 == f3) {
            return null;
        }
        c0.a(view, f2);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, c0.f447b, f3);
        ofFloat.addListener(new b(view));
        a(new a(this, view));
        return ofFloat;
    }

    @Override // a.n.j0
    public Animator b(ViewGroup viewGroup, View view, s sVar, s sVar2) {
        c0.e(view);
        return a(view, a(sVar, 1.0f), 0.0f);
    }

    @Override // a.n.j0, a.n.m
    public void c(s sVar) {
        super.c(sVar);
        sVar.f517a.put("android:fade:transitionAlpha", Float.valueOf(c0.c(sVar.f518b)));
    }

    @Override // a.n.j0
    public Animator a(ViewGroup viewGroup, View view, s sVar, s sVar2) {
        float a2 = a(sVar, 0.0f);
        return a(view, a2 != 1.0f ? a2 : 0.0f, 1.0f);
    }

    private static float a(s sVar, float f2) {
        Float f3;
        return (sVar == null || (f3 = (Float) sVar.f517a.get("android:fade:transitionAlpha")) == null) ? f2 : f3.floatValue();
    }
}
