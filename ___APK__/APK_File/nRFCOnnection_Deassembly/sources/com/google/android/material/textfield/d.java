package com.google.android.material.textfield;

import a.f.l.w;
import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import c.a.a.a.b0.k;
import c.a.a.a.j;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class d extends com.google.android.material.textfield.e {
    private static final boolean o;

    /* renamed from: d, reason: collision with root package name */
    private final TextWatcher f2738d;

    /* renamed from: e, reason: collision with root package name */
    private final TextInputLayout.e f2739e;

    /* renamed from: f, reason: collision with root package name */
    private final TextInputLayout.f f2740f;
    private boolean g;
    private boolean h;
    private long i;
    private StateListDrawable j;
    private c.a.a.a.b0.g k;
    private AccessibilityManager l;
    private ValueAnimator m;
    private ValueAnimator n;

    /* loaded from: classes.dex */
    class a implements TextWatcher {

        /* renamed from: com.google.android.material.textfield.d$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class RunnableC0091a implements Runnable {

            /* renamed from: b, reason: collision with root package name */
            final /* synthetic */ AutoCompleteTextView f2742b;

            RunnableC0091a(AutoCompleteTextView autoCompleteTextView) {
                this.f2742b = autoCompleteTextView;
            }

            @Override // java.lang.Runnable
            public void run() {
                boolean isPopupShowing = this.f2742b.isPopupShowing();
                d.this.b(isPopupShowing);
                d.this.g = isPopupShowing;
            }
        }

        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            d dVar = d.this;
            AutoCompleteTextView a2 = dVar.a(dVar.f2753a.getEditText());
            a2.post(new RunnableC0091a(a2));
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* loaded from: classes.dex */
    class b extends TextInputLayout.e {
        b(TextInputLayout textInputLayout) {
            super(textInputLayout);
        }

        @Override // com.google.android.material.textfield.TextInputLayout.e, a.f.l.a
        public void onInitializeAccessibilityNodeInfo(View view, a.f.l.f0.c cVar) {
            super.onInitializeAccessibilityNodeInfo(view, cVar);
            cVar.a((CharSequence) Spinner.class.getName());
            if (cVar.w()) {
                cVar.d((CharSequence) null);
            }
        }

        @Override // a.f.l.a
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            d dVar = d.this;
            AutoCompleteTextView a2 = dVar.a(dVar.f2753a.getEditText());
            if (accessibilityEvent.getEventType() == 1 && d.this.l.isTouchExplorationEnabled()) {
                d.this.d(a2);
            }
        }
    }

    /* loaded from: classes.dex */
    class c implements TextInputLayout.f {
        c() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.f
        public void a(TextInputLayout textInputLayout) {
            AutoCompleteTextView a2 = d.this.a(textInputLayout.getEditText());
            d.this.b(a2);
            d.this.a(a2);
            d.this.c(a2);
            a2.setThreshold(0);
            a2.removeTextChangedListener(d.this.f2738d);
            a2.addTextChangedListener(d.this.f2738d);
            textInputLayout.setErrorIconDrawable((Drawable) null);
            textInputLayout.setTextInputAccessibilityDelegate(d.this.f2739e);
            textInputLayout.setEndIconVisible(true);
        }
    }

    /* renamed from: com.google.android.material.textfield.d$d, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class ViewOnClickListenerC0092d implements View.OnClickListener {
        ViewOnClickListenerC0092d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            d.this.d((AutoCompleteTextView) d.this.f2753a.getEditText());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements View.OnTouchListener {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ AutoCompleteTextView f2747b;

        e(AutoCompleteTextView autoCompleteTextView) {
            this.f2747b = autoCompleteTextView;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                if (d.this.d()) {
                    d.this.g = false;
                }
                d.this.d(this.f2747b);
                view.performClick();
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f implements View.OnFocusChangeListener {
        f() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            d.this.f2753a.setEndIconActivated(z);
            if (z) {
                return;
            }
            d.this.b(false);
            d.this.g = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class g implements AutoCompleteTextView.OnDismissListener {
        g() {
        }

        @Override // android.widget.AutoCompleteTextView.OnDismissListener
        public void onDismiss() {
            d.this.g = true;
            d.this.i = System.currentTimeMillis();
            d.this.b(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class h extends AnimatorListenerAdapter {
        h() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d dVar = d.this;
            dVar.f2755c.setChecked(dVar.h);
            d.this.n.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class i implements ValueAnimator.AnimatorUpdateListener {
        i() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            d.this.f2755c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    static {
        o = Build.VERSION.SDK_INT >= 21;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.f2738d = new a();
        this.f2739e = new b(this.f2753a);
        this.f2740f = new c();
        this.g = false;
        this.h = false;
        this.i = Long.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.textfield.e
    public boolean a(int i2) {
        return i2 != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.textfield.e
    public boolean b() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView.setOnTouchListener(new e(autoCompleteTextView));
        autoCompleteTextView.setOnFocusChangeListener(new f());
        if (o) {
            autoCompleteTextView.setOnDismissListener(new g());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView == null) {
            return;
        }
        if (d()) {
            this.g = false;
        }
        if (!this.g) {
            if (o) {
                b(!this.h);
            } else {
                this.h = !this.h;
                this.f2755c.toggle();
            }
            if (this.h) {
                autoCompleteTextView.requestFocus();
                autoCompleteTextView.showDropDown();
                return;
            } else {
                autoCompleteTextView.dismissDropDown();
                return;
            }
        }
        this.g = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(AutoCompleteTextView autoCompleteTextView) {
        if (o) {
            int boxBackgroundMode = this.f2753a.getBoxBackgroundMode();
            if (boxBackgroundMode == 2) {
                autoCompleteTextView.setDropDownBackgroundDrawable(this.k);
            } else if (boxBackgroundMode == 1) {
                autoCompleteTextView.setDropDownBackgroundDrawable(this.j);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.textfield.e
    public void a() {
        float dimensionPixelOffset = this.f2754b.getResources().getDimensionPixelOffset(c.a.a.a.d.mtrl_shape_corner_size_small_component);
        float dimensionPixelOffset2 = this.f2754b.getResources().getDimensionPixelOffset(c.a.a.a.d.mtrl_exposed_dropdown_menu_popup_elevation);
        int dimensionPixelOffset3 = this.f2754b.getResources().getDimensionPixelOffset(c.a.a.a.d.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        c.a.a.a.b0.g a2 = a(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        c.a.a.a.b0.g a3 = a(0.0f, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        this.k = a2;
        this.j = new StateListDrawable();
        this.j.addState(new int[]{R.attr.state_above_anchor}, a2);
        this.j.addState(new int[0], a3);
        this.f2753a.setEndIconDrawable(a.a.k.a.a.c(this.f2754b, o ? c.a.a.a.e.mtrl_dropdown_arrow : c.a.a.a.e.mtrl_ic_arrow_drop_down));
        TextInputLayout textInputLayout = this.f2753a;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(j.exposed_dropdown_menu_content_description));
        this.f2753a.setEndIconOnClickListener(new ViewOnClickListenerC0092d());
        this.f2753a.a(this.f2740f);
        c();
        w.i(this.f2755c, 2);
        this.l = (AccessibilityManager) this.f2754b.getSystemService("accessibility");
    }

    private void c() {
        this.n = a(67, 0.0f, 1.0f);
        this.m = a(50, 1.0f, 0.0f);
        this.m.addListener(new h());
    }

    private void b(AutoCompleteTextView autoCompleteTextView, int i2, int[][] iArr, c.a.a.a.b0.g gVar) {
        LayerDrawable layerDrawable;
        int a2 = c.a.a.a.s.a.a(autoCompleteTextView, c.a.a.a.b.colorSurface);
        c.a.a.a.b0.g gVar2 = new c.a.a.a.b0.g(gVar.k());
        int a3 = c.a.a.a.s.a.a(i2, a2, 0.1f);
        gVar2.a(new ColorStateList(iArr, new int[]{a3, 0}));
        if (o) {
            gVar2.setTint(a2);
            ColorStateList colorStateList = new ColorStateList(iArr, new int[]{a3, a2});
            c.a.a.a.b0.g gVar3 = new c.a.a.a.b0.g(gVar.k());
            gVar3.setTint(-1);
            layerDrawable = new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, gVar2, gVar3), gVar});
        } else {
            layerDrawable = new LayerDrawable(new Drawable[]{gVar2, gVar});
        }
        w.a(autoCompleteTextView, layerDrawable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d() {
        long currentTimeMillis = System.currentTimeMillis() - this.i;
        return currentTimeMillis < 0 || currentTimeMillis > 300;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (this.h != z) {
            this.h = z;
            this.n.cancel();
            this.m.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView.getKeyListener() != null) {
            return;
        }
        int boxBackgroundMode = this.f2753a.getBoxBackgroundMode();
        c.a.a.a.b0.g boxBackground = this.f2753a.getBoxBackground();
        int a2 = c.a.a.a.s.a.a(autoCompleteTextView, c.a.a.a.b.colorControlHighlight);
        int[][] iArr = {new int[]{R.attr.state_pressed}, new int[0]};
        if (boxBackgroundMode == 2) {
            b(autoCompleteTextView, a2, iArr, boxBackground);
        } else if (boxBackgroundMode == 1) {
            a(autoCompleteTextView, a2, iArr, boxBackground);
        }
    }

    private void a(AutoCompleteTextView autoCompleteTextView, int i2, int[][] iArr, c.a.a.a.b0.g gVar) {
        int boxBackgroundColor = this.f2753a.getBoxBackgroundColor();
        int[] iArr2 = {c.a.a.a.s.a.a(i2, boxBackgroundColor, 0.1f), boxBackgroundColor};
        if (o) {
            w.a(autoCompleteTextView, new RippleDrawable(new ColorStateList(iArr, iArr2), gVar, gVar));
            return;
        }
        c.a.a.a.b0.g gVar2 = new c.a.a.a.b0.g(gVar.k());
        gVar2.a(new ColorStateList(iArr, iArr2));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{gVar, gVar2});
        int v = w.v(autoCompleteTextView);
        int paddingTop = autoCompleteTextView.getPaddingTop();
        int u = w.u(autoCompleteTextView);
        int paddingBottom = autoCompleteTextView.getPaddingBottom();
        w.a(autoCompleteTextView, layerDrawable);
        w.b(autoCompleteTextView, v, paddingTop, u, paddingBottom);
    }

    private c.a.a.a.b0.g a(float f2, float f3, float f4, int i2) {
        k.b n = k.n();
        n.d(f2);
        n.e(f2);
        n.b(f3);
        n.c(f3);
        k a2 = n.a();
        c.a.a.a.b0.g a3 = c.a.a.a.b0.g.a(this.f2754b, f4);
        a3.setShapeAppearanceModel(a2);
        a3.a(0, i2, 0, i2);
        return a3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AutoCompleteTextView a(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            return (AutoCompleteTextView) editText;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    private ValueAnimator a(int i2, float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(c.a.a.a.m.a.f2093a);
        ofFloat.setDuration(i2);
        ofFloat.addUpdateListener(new i());
        return ofFloat;
    }
}
