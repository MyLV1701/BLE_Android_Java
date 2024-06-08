package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import c.a.a.a.j;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a extends com.google.android.material.textfield.e {

    /* renamed from: d, reason: collision with root package name */
    private final TextWatcher f2727d;

    /* renamed from: e, reason: collision with root package name */
    private final TextInputLayout.f f2728e;

    /* renamed from: f, reason: collision with root package name */
    private AnimatorSet f2729f;
    private ValueAnimator g;

    /* renamed from: com.google.android.material.textfield.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0089a implements TextWatcher {
        C0089a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (a.this.f2753a.getSuffixText() != null) {
                return;
            }
            a.this.b(a.b(editable));
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* loaded from: classes.dex */
    class b implements TextInputLayout.f {

        /* renamed from: com.google.android.material.textfield.a$b$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class ViewOnFocusChangeListenerC0090a implements View.OnFocusChangeListener {
            ViewOnFocusChangeListenerC0090a() {
            }

            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                a.this.b((TextUtils.isEmpty(((EditText) view).getText()) ^ true) && z);
            }
        }

        b() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.f
        public void a(TextInputLayout textInputLayout) {
            EditText editText = textInputLayout.getEditText();
            textInputLayout.setEndIconVisible(a.b(editText.getText()));
            textInputLayout.setEndIconCheckable(false);
            editText.setOnFocusChangeListener(new ViewOnFocusChangeListenerC0090a());
            editText.removeTextChangedListener(a.this.f2727d);
            editText.addTextChangedListener(a.this.f2727d);
        }
    }

    /* loaded from: classes.dex */
    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a.this.f2753a.getEditText().setText((CharSequence) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d extends AnimatorListenerAdapter {
        d() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            a.this.f2753a.setEndIconVisible(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e extends AnimatorListenerAdapter {
        e() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            a.this.f2753a.setEndIconVisible(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f implements ValueAnimator.AnimatorUpdateListener {
        f() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            a.this.f2755c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class g implements ValueAnimator.AnimatorUpdateListener {
        g() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            a.this.f2755c.setScaleX(floatValue);
            a.this.f2755c.setScaleY(floatValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.f2727d = new C0089a();
        this.f2728e = new b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        boolean z2 = this.f2753a.a() == z;
        if (z) {
            this.g.cancel();
            this.f2729f.start();
            if (z2) {
                this.f2729f.end();
                return;
            }
            return;
        }
        this.f2729f.cancel();
        this.g.start();
        if (z2) {
            this.g.end();
        }
    }

    private ValueAnimator c() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.8f, 1.0f);
        ofFloat.setInterpolator(c.a.a.a.m.a.f2096d);
        ofFloat.setDuration(150L);
        ofFloat.addUpdateListener(new g());
        return ofFloat;
    }

    private void d() {
        ValueAnimator c2 = c();
        ValueAnimator a2 = a(0.0f, 1.0f);
        this.f2729f = new AnimatorSet();
        this.f2729f.playTogether(c2, a2);
        this.f2729f.addListener(new d());
        this.g = a(1.0f, 0.0f);
        this.g.addListener(new e());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.textfield.e
    public void a() {
        this.f2753a.setEndIconDrawable(a.a.k.a.a.c(this.f2754b, c.a.a.a.e.mtrl_ic_cancel));
        TextInputLayout textInputLayout = this.f2753a;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(j.clear_text_end_icon_content_description));
        this.f2753a.setEndIconOnClickListener(new c());
        this.f2753a.a(this.f2728e);
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(Editable editable) {
        return editable.length() > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.textfield.e
    public void a(boolean z) {
        if (this.f2753a.getSuffixText() == null) {
            return;
        }
        b(z);
    }

    private ValueAnimator a(float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(c.a.a.a.m.a.f2093a);
        ofFloat.setDuration(100L);
        ofFloat.addUpdateListener(new f());
        return ofFloat;
    }
}
