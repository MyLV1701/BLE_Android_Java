package com.google.android.material.textfield;

import a.f.l.w;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.i;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    private final Context f2756a;

    /* renamed from: b, reason: collision with root package name */
    private final TextInputLayout f2757b;

    /* renamed from: c, reason: collision with root package name */
    private LinearLayout f2758c;

    /* renamed from: d, reason: collision with root package name */
    private int f2759d;

    /* renamed from: e, reason: collision with root package name */
    private FrameLayout f2760e;

    /* renamed from: f, reason: collision with root package name */
    private int f2761f;
    private Animator g;
    private final float h;
    private int i;
    private int j;
    private CharSequence k;
    private boolean l;
    private TextView m;
    private CharSequence n;
    private int o;
    private ColorStateList p;
    private CharSequence q;
    private boolean r;
    private TextView s;
    private int t;
    private ColorStateList u;
    private Typeface v;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f2762a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ TextView f2763b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f2764c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ TextView f2765d;

        a(int i, TextView textView, int i2, TextView textView2) {
            this.f2762a = i;
            this.f2763b = textView;
            this.f2764c = i2;
            this.f2765d = textView2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f.this.i = this.f2762a;
            f.this.g = null;
            TextView textView = this.f2763b;
            if (textView != null) {
                textView.setVisibility(4);
                if (this.f2764c == 1 && f.this.m != null) {
                    f.this.m.setText((CharSequence) null);
                }
            }
            TextView textView2 = this.f2765d;
            if (textView2 != null) {
                textView2.setTranslationY(0.0f);
                this.f2765d.setAlpha(1.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            TextView textView = this.f2765d;
            if (textView != null) {
                textView.setVisibility(0);
            }
        }
    }

    public f(TextInputLayout textInputLayout) {
        this.f2756a = textInputLayout.getContext();
        this.f2757b = textInputLayout;
        this.h = this.f2756a.getResources().getDimensionPixelSize(c.a.a.a.d.design_textinput_caption_translate_y);
    }

    private TextView d(int i) {
        if (i == 1) {
            return this.m;
        }
        if (i != 2) {
            return null;
        }
        return this.s;
    }

    private boolean e(int i) {
        return (i != 1 || this.m == null || TextUtils.isEmpty(this.k)) ? false : true;
    }

    private boolean n() {
        return (this.f2758c == null || this.f2757b.getEditText() == null) ? false : true;
    }

    boolean a(int i) {
        return i == 0 || i == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(CharSequence charSequence) {
        b();
        this.k = charSequence;
        this.m.setText(charSequence);
        if (this.i != 1) {
            this.j = 1;
        }
        a(this.i, this.j, a(this.m, charSequence));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(CharSequence charSequence) {
        b();
        this.q = charSequence;
        this.s.setText(charSequence);
        if (this.i != 2) {
            this.j = 2;
        }
        a(this.i, this.j, a(this.s, charSequence));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f() {
        TextView textView = this.m;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList g() {
        TextView textView = this.m;
        if (textView != null) {
            return textView.getTextColors();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharSequence h() {
        return this.q;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int i() {
        TextView textView = this.s;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j() {
        this.k = null;
        b();
        if (this.i == 1) {
            if (this.r && !TextUtils.isEmpty(this.q)) {
                this.j = 2;
            } else {
                this.j = 0;
            }
        }
        a(this.i, this.j, a(this.m, (CharSequence) null));
    }

    void k() {
        b();
        if (this.i == 2) {
            this.j = 0;
        }
        a(this.i, this.j, a(this.s, (CharSequence) null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean l() {
        return this.l;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean m() {
        return this.r;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharSequence d() {
        return this.n;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharSequence e() {
        return this.k;
    }

    private boolean a(TextView textView, CharSequence charSequence) {
        return w.F(this.f2757b) && this.f2757b.isEnabled() && !(this.j == this.i && textView != null && TextUtils.equals(textView.getText(), charSequence));
    }

    private void a(int i, int i2, boolean z) {
        if (i == i2) {
            return;
        }
        if (z) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.g = animatorSet;
            ArrayList arrayList = new ArrayList();
            a(arrayList, this.r, this.s, 2, i, i2);
            a(arrayList, this.l, this.m, 1, i, i2);
            c.a.a.a.m.b.a(animatorSet, arrayList);
            animatorSet.addListener(new a(i2, d(i), i, d(i2)));
            animatorSet.start();
        } else {
            a(i, i2);
        }
        this.f2757b.f();
        this.f2757b.a(z);
        this.f2757b.g();
    }

    void b() {
        Animator animator = this.g;
        if (animator != null) {
            animator.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c() {
        return e(this.j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(int i) {
        this.t = i;
        TextView textView = this.s;
        if (textView != null) {
            i.d(textView, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(TextView textView, int i) {
        FrameLayout frameLayout;
        if (this.f2758c == null) {
            return;
        }
        if (a(i) && (frameLayout = this.f2760e) != null) {
            this.f2761f--;
            a(frameLayout, this.f2761f);
            this.f2760e.removeView(textView);
        } else {
            this.f2758c.removeView(textView);
        }
        this.f2759d--;
        a(this.f2758c, this.f2759d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(boolean z) {
        if (this.r == z) {
            return;
        }
        b();
        if (z) {
            this.s = new AppCompatTextView(this.f2756a);
            this.s.setId(c.a.a.a.f.textinput_helper_text);
            Typeface typeface = this.v;
            if (typeface != null) {
                this.s.setTypeface(typeface);
            }
            this.s.setVisibility(4);
            w.h(this.s, 1);
            c(this.t);
            b(this.u);
            a(this.s, 1);
        } else {
            k();
            b(this.s, 1);
            this.s = null;
            this.f2757b.f();
            this.f2757b.g();
        }
        this.r = z;
    }

    private void a(int i, int i2) {
        TextView d2;
        TextView d3;
        if (i == i2) {
            return;
        }
        if (i2 != 0 && (d3 = d(i2)) != null) {
            d3.setVisibility(0);
            d3.setAlpha(1.0f);
        }
        if (i != 0 && (d2 = d(i)) != null) {
            d2.setVisibility(4);
            if (i == 1) {
                d2.setText((CharSequence) null);
            }
        }
        this.i = i2;
    }

    private void a(List<Animator> list, boolean z, TextView textView, int i, int i2, int i3) {
        if (textView == null || !z) {
            return;
        }
        if (i == i3 || i == i2) {
            list.add(a(textView, i3 == i));
            if (i3 == i) {
                list.add(a(textView));
            }
        }
    }

    private ObjectAnimator a(TextView textView, boolean z) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.ALPHA, z ? 1.0f : 0.0f);
        ofFloat.setDuration(167L);
        ofFloat.setInterpolator(c.a.a.a.m.a.f2093a);
        return ofFloat;
    }

    private ObjectAnimator a(TextView textView) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.TRANSLATION_Y, -this.h, 0.0f);
        ofFloat.setDuration(217L);
        ofFloat.setInterpolator(c.a.a.a.m.a.f2096d);
        return ofFloat;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(int i) {
        this.o = i;
        TextView textView = this.m;
        if (textView != null) {
            this.f2757b.a(textView, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        if (n()) {
            w.b(this.f2758c, w.v(this.f2757b.getEditText()), 0, w.u(this.f2757b.getEditText()), 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(ColorStateList colorStateList) {
        this.u = colorStateList;
        TextView textView = this.s;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(TextView textView, int i) {
        if (this.f2758c == null && this.f2760e == null) {
            this.f2758c = new LinearLayout(this.f2756a);
            this.f2758c.setOrientation(0);
            this.f2757b.addView(this.f2758c, -1, -2);
            this.f2760e = new FrameLayout(this.f2756a);
            this.f2758c.addView(this.f2760e, -1, new FrameLayout.LayoutParams(-2, -2));
            this.f2758c.addView(new Space(this.f2756a), new LinearLayout.LayoutParams(0, 0, 1.0f));
            if (this.f2757b.getEditText() != null) {
                a();
            }
        }
        if (a(i)) {
            this.f2760e.setVisibility(0);
            this.f2760e.addView(textView);
            this.f2761f++;
        } else {
            this.f2758c.addView(textView, i);
        }
        this.f2758c.setVisibility(0);
        this.f2759d++;
    }

    private void a(ViewGroup viewGroup, int i) {
        if (i == 0) {
            viewGroup.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        if (this.l == z) {
            return;
        }
        b();
        if (z) {
            this.m = new AppCompatTextView(this.f2756a);
            this.m.setId(c.a.a.a.f.textinput_error);
            Typeface typeface = this.v;
            if (typeface != null) {
                this.m.setTypeface(typeface);
            }
            b(this.o);
            a(this.p);
            a(this.n);
            this.m.setVisibility(4);
            w.h(this.m, 1);
            a(this.m, 0);
        } else {
            j();
            b(this.m, 0);
            this.m = null;
            this.f2757b.f();
            this.f2757b.g();
        }
        this.l = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Typeface typeface) {
        if (typeface != this.v) {
            this.v = typeface;
            a(this.m, typeface);
            a(this.s, typeface);
        }
    }

    private void a(TextView textView, Typeface typeface) {
        if (textView != null) {
            textView.setTypeface(typeface);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ColorStateList colorStateList) {
        this.p = colorStateList;
        TextView textView = this.m;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(CharSequence charSequence) {
        this.n = charSequence;
        TextView textView = this.m;
        if (textView != null) {
            textView.setContentDescription(charSequence);
        }
    }
}
