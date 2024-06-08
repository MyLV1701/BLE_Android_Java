package a.f.l;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class a0 {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<View> f276a;

    /* renamed from: b, reason: collision with root package name */
    Runnable f277b = null;

    /* renamed from: c, reason: collision with root package name */
    Runnable f278c = null;

    /* renamed from: d, reason: collision with root package name */
    int f279d = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ b0 f280a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f281b;

        a(a0 a0Var, b0 b0Var, View view) {
            this.f280a = b0Var;
            this.f281b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f280a.a(this.f281b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f280a.b(this.f281b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f280a.c(this.f281b);
        }
    }

    /* loaded from: classes.dex */
    class b implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ d0 f282a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f283b;

        b(a0 a0Var, d0 d0Var, View view) {
            this.f282a = d0Var;
            this.f283b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f282a.a(this.f283b);
        }
    }

    /* loaded from: classes.dex */
    static class c implements b0 {

        /* renamed from: a, reason: collision with root package name */
        a0 f284a;

        /* renamed from: b, reason: collision with root package name */
        boolean f285b;

        c(a0 a0Var) {
            this.f284a = a0Var;
        }

        @Override // a.f.l.b0
        public void a(View view) {
            Object tag = view.getTag(2113929216);
            b0 b0Var = tag instanceof b0 ? (b0) tag : null;
            if (b0Var != null) {
                b0Var.a(view);
            }
        }

        @Override // a.f.l.b0
        @SuppressLint({"WrongConstant"})
        public void b(View view) {
            int i = this.f284a.f279d;
            if (i > -1) {
                view.setLayerType(i, null);
                this.f284a.f279d = -1;
            }
            if (Build.VERSION.SDK_INT >= 16 || !this.f285b) {
                a0 a0Var = this.f284a;
                Runnable runnable = a0Var.f278c;
                if (runnable != null) {
                    a0Var.f278c = null;
                    runnable.run();
                }
                Object tag = view.getTag(2113929216);
                b0 b0Var = tag instanceof b0 ? (b0) tag : null;
                if (b0Var != null) {
                    b0Var.b(view);
                }
                this.f285b = true;
            }
        }

        @Override // a.f.l.b0
        public void c(View view) {
            this.f285b = false;
            if (this.f284a.f279d > -1) {
                view.setLayerType(2, null);
            }
            a0 a0Var = this.f284a;
            Runnable runnable = a0Var.f277b;
            if (runnable != null) {
                a0Var.f277b = null;
                runnable.run();
            }
            Object tag = view.getTag(2113929216);
            b0 b0Var = tag instanceof b0 ? (b0) tag : null;
            if (b0Var != null) {
                b0Var.c(view);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a0(View view) {
        this.f276a = new WeakReference<>(view);
    }

    public a0 a(long j) {
        View view = this.f276a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public a0 b(float f2) {
        View view = this.f276a.get();
        if (view != null) {
            view.animate().translationY(f2);
        }
        return this;
    }

    public void c() {
        View view = this.f276a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public a0 a(float f2) {
        View view = this.f276a.get();
        if (view != null) {
            view.animate().alpha(f2);
        }
        return this;
    }

    public long b() {
        View view = this.f276a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public a0 a(Interpolator interpolator) {
        View view = this.f276a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public a0 b(long j) {
        View view = this.f276a.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    public void a() {
        View view = this.f276a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public a0 a(b0 b0Var) {
        View view = this.f276a.get();
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                a(view, b0Var);
            } else {
                view.setTag(2113929216, b0Var);
                a(view, new c(this));
            }
        }
        return this;
    }

    private void a(View view, b0 b0Var) {
        if (b0Var != null) {
            view.animate().setListener(new a(this, b0Var, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public a0 a(d0 d0Var) {
        View view = this.f276a.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            view.animate().setUpdateListener(d0Var != null ? new b(this, d0Var, view) : null);
        }
        return this;
    }
}
