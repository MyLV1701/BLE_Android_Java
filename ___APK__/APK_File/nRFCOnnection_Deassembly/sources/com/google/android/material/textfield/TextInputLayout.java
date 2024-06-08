package com.google.android.material.textfield;

import a.f.l.w;
import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.a0;
import androidx.appcompat.widget.j;
import androidx.appcompat.widget.t0;
import androidx.core.widget.i;
import c.a.a.a.b0.k;
import c.a.a.a.k;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.l;
import com.google.android.material.internal.m;
import java.util.Iterator;
import java.util.LinkedHashSet;
import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
public class TextInputLayout extends LinearLayout {
    private static final int D0 = k.Widget_Design_TextInputLayout;
    private final int A;
    private ValueAnimator A0;
    private int B;
    private boolean B0;
    private final int C;
    private boolean C0;
    private int D;
    private final int E;
    private final int F;
    private int G;
    private int H;
    private final Rect I;
    private final Rect J;
    private final RectF K;
    private Typeface L;
    private final CheckableImageButton M;
    private ColorStateList N;
    private boolean O;
    private PorterDuff.Mode P;
    private boolean Q;
    private Drawable R;
    private int S;
    private View.OnLongClickListener T;
    private final LinkedHashSet<f> U;
    private int V;
    private final SparseArray<com.google.android.material.textfield.e> W;
    private final CheckableImageButton a0;

    /* renamed from: b, reason: collision with root package name */
    private final FrameLayout f2715b;
    private final LinkedHashSet<g> b0;

    /* renamed from: c, reason: collision with root package name */
    private final LinearLayout f2716c;
    private ColorStateList c0;

    /* renamed from: d, reason: collision with root package name */
    private final LinearLayout f2717d;
    private boolean d0;

    /* renamed from: e, reason: collision with root package name */
    private final FrameLayout f2718e;
    private PorterDuff.Mode e0;

    /* renamed from: f, reason: collision with root package name */
    EditText f2719f;
    private boolean f0;
    private CharSequence g;
    private Drawable g0;
    private final com.google.android.material.textfield.f h;
    private int h0;
    boolean i;
    private Drawable i0;
    private int j;
    private View.OnLongClickListener j0;
    private boolean k;
    private final CheckableImageButton k0;
    private TextView l;
    private ColorStateList l0;
    private int m;
    private ColorStateList m0;
    private int n;
    private ColorStateList n0;
    private ColorStateList o;
    private int o0;
    private ColorStateList p;
    private int p0;
    private CharSequence q;
    private int q0;
    private final TextView r;
    private ColorStateList r0;
    private CharSequence s;
    private int s0;
    private final TextView t;
    private final int t0;
    private boolean u;
    private final int u0;
    private CharSequence v;
    private final int v0;
    private boolean w;
    private int w0;
    private c.a.a.a.b0.g x;
    private boolean x0;
    private c.a.a.a.b0.g y;
    final com.google.android.material.internal.a y0;
    private c.a.a.a.b0.k z;
    private boolean z0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements TextWatcher {
        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            TextInputLayout.this.a(!r0.C0);
            TextInputLayout textInputLayout = TextInputLayout.this;
            if (textInputLayout.i) {
                textInputLayout.a(editable.length());
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.a0.performClick();
            TextInputLayout.this.a0.jumpDrawablesToCurrentState();
        }
    }

    /* loaded from: classes.dex */
    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.f2719f.requestLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements ValueAnimator.AnimatorUpdateListener {
        d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            TextInputLayout.this.y0.b(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* loaded from: classes.dex */
    public static class e extends a.f.l.a {

        /* renamed from: a, reason: collision with root package name */
        private final TextInputLayout f2724a;

        public e(TextInputLayout textInputLayout) {
            this.f2724a = textInputLayout;
        }

        @Override // a.f.l.a
        public void onInitializeAccessibilityNodeInfo(View view, a.f.l.f0.c cVar) {
            super.onInitializeAccessibilityNodeInfo(view, cVar);
            EditText editText = this.f2724a.getEditText();
            Editable text = editText != null ? editText.getText() : null;
            CharSequence hint = this.f2724a.getHint();
            CharSequence error = this.f2724a.getError();
            CharSequence counterOverflowDescription = this.f2724a.getCounterOverflowDescription();
            boolean z = !TextUtils.isEmpty(text);
            boolean z2 = !TextUtils.isEmpty(hint);
            boolean z3 = !TextUtils.isEmpty(error);
            boolean z4 = false;
            boolean z5 = z3 || !TextUtils.isEmpty(counterOverflowDescription);
            if (z) {
                cVar.g(text);
            } else if (z2) {
                cVar.g(hint);
            }
            if (z2) {
                cVar.d(hint);
                if (!z && z2) {
                    z4 = true;
                }
                cVar.o(z4);
            }
            if (z5) {
                if (!z3) {
                    error = counterOverflowDescription;
                }
                cVar.c(error);
                cVar.e(true);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface f {
        void a(TextInputLayout textInputLayout);
    }

    /* loaded from: classes.dex */
    public interface g {
        void a(TextInputLayout textInputLayout, int i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class h extends a.h.a.a {
        public static final Parcelable.Creator<h> CREATOR = new a();

        /* renamed from: d, reason: collision with root package name */
        CharSequence f2725d;

        /* renamed from: e, reason: collision with root package name */
        boolean f2726e;

        /* loaded from: classes.dex */
        static class a implements Parcelable.ClassLoaderCreator<h> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            public h[] newArray(int i) {
                return new h[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public h createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new h(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public h createFromParcel(Parcel parcel) {
                return new h(parcel, null);
            }
        }

        h(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.f2725d) + "}";
        }

        @Override // a.h.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(this.f2725d, parcel, i);
            parcel.writeInt(this.f2726e ? 1 : 0);
        }

        h(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2725d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f2726e = parcel.readInt() == 1;
        }
    }

    public TextInputLayout(Context context) {
        this(context, null);
    }

    private boolean A() {
        return !(getStartIconDrawable() == null && this.q == null) && this.f2716c.getMeasuredWidth() > 0;
    }

    private boolean B() {
        EditText editText = this.f2719f;
        return (editText == null || this.x == null || editText.getBackground() != null || this.B == 0) ? false : true;
    }

    private void C() {
        if (this.l != null) {
            EditText editText = this.f2719f;
            a(editText == null ? 0 : editText.getText().length());
        }
    }

    private void D() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        TextView textView = this.l;
        if (textView != null) {
            a(textView, this.k ? this.m : this.n);
            if (!this.k && (colorStateList2 = this.o) != null) {
                this.l.setTextColor(colorStateList2);
            }
            if (!this.k || (colorStateList = this.p) == null) {
                return;
            }
            this.l.setTextColor(colorStateList);
        }
    }

    private boolean E() {
        boolean z;
        if (this.f2719f == null) {
            return false;
        }
        if (A()) {
            int measuredWidth = this.f2716c.getMeasuredWidth() - this.f2719f.getPaddingLeft();
            if (this.R == null || this.S != measuredWidth) {
                this.R = new ColorDrawable();
                this.S = measuredWidth;
                this.R.setBounds(0, 0, this.S, 1);
            }
            Drawable[] a2 = i.a(this.f2719f);
            Drawable drawable = a2[0];
            Drawable drawable2 = this.R;
            if (drawable != drawable2) {
                i.a(this.f2719f, drawable2, a2[1], a2[2], a2[3]);
                z = true;
            }
            z = false;
        } else {
            if (this.R != null) {
                Drawable[] a3 = i.a(this.f2719f);
                i.a(this.f2719f, null, a3[1], a3[2], a3[3]);
                this.R = null;
                z = true;
            }
            z = false;
        }
        if (z()) {
            int measuredWidth2 = this.t.getMeasuredWidth() - this.f2719f.getPaddingRight();
            CheckableImageButton endIconToUpdateDummyDrawable = getEndIconToUpdateDummyDrawable();
            if (endIconToUpdateDummyDrawable != null) {
                measuredWidth2 = measuredWidth2 + endIconToUpdateDummyDrawable.getMeasuredWidth() + a.f.l.g.b((ViewGroup.MarginLayoutParams) endIconToUpdateDummyDrawable.getLayoutParams());
            }
            Drawable[] a4 = i.a(this.f2719f);
            Drawable drawable3 = this.g0;
            if (drawable3 != null && this.h0 != measuredWidth2) {
                this.h0 = measuredWidth2;
                drawable3.setBounds(0, 0, this.h0, 1);
                i.a(this.f2719f, a4[0], a4[1], this.g0, a4[3]);
            } else {
                if (this.g0 == null) {
                    this.g0 = new ColorDrawable();
                    this.h0 = measuredWidth2;
                    this.g0.setBounds(0, 0, this.h0, 1);
                }
                Drawable drawable4 = a4[2];
                Drawable drawable5 = this.g0;
                if (drawable4 == drawable5) {
                    return z;
                }
                this.i0 = a4[2];
                i.a(this.f2719f, a4[0], a4[1], drawable5, a4[3]);
            }
            return true;
        }
        if (this.g0 == null) {
            return z;
        }
        Drawable[] a5 = i.a(this.f2719f);
        if (a5[2] == this.g0) {
            i.a(this.f2719f, a5[0], a5[1], this.i0, a5[3]);
            z = true;
        }
        this.g0 = null;
        return z;
    }

    private boolean F() {
        int max;
        if (this.f2719f == null || this.f2719f.getMeasuredHeight() >= (max = Math.max(this.f2717d.getMeasuredHeight(), this.f2716c.getMeasuredHeight()))) {
            return false;
        }
        this.f2719f.setMinimumHeight(max);
        return true;
    }

    private void G() {
        if (this.B != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f2715b.getLayoutParams();
            int n = n();
            if (n != layoutParams.topMargin) {
                layoutParams.topMargin = n;
                this.f2715b.requestLayout();
            }
        }
    }

    private void H() {
        if (this.f2719f == null) {
            return;
        }
        this.r.setPadding(e() ? 0 : this.f2719f.getPaddingLeft(), this.f2719f.getCompoundPaddingTop(), this.r.getCompoundPaddingRight(), this.f2719f.getCompoundPaddingBottom());
    }

    private void I() {
        this.r.setVisibility((this.q == null || c()) ? 8 : 0);
        E();
    }

    private void J() {
        if (this.f2719f == null) {
            return;
        }
        TextView textView = this.t;
        textView.setPadding(textView.getPaddingLeft(), this.f2719f.getPaddingTop(), (a() || u()) ? 0 : this.f2719f.getPaddingRight(), this.f2719f.getPaddingBottom());
    }

    private void K() {
        int visibility = this.t.getVisibility();
        boolean z = (this.s == null || c()) ? false : true;
        this.t.setVisibility(z ? 0 : 8);
        if (visibility != this.t.getVisibility()) {
            getEndIconDelegate().a(z);
        }
        E();
    }

    private void c(Rect rect) {
        c.a.a.a.b0.g gVar = this.y;
        if (gVar != null) {
            int i = rect.bottom;
            gVar.setBounds(rect.left, i - this.F, rect.right, i);
        }
    }

    private com.google.android.material.textfield.e getEndIconDelegate() {
        com.google.android.material.textfield.e eVar = this.W.get(this.V);
        return eVar != null ? eVar : this.W.get(0);
    }

    private CheckableImageButton getEndIconToUpdateDummyDrawable() {
        if (this.k0.getVisibility() == 0) {
            return this.k0;
        }
        if (t() && a()) {
            return this.a0;
        }
        return null;
    }

    private void h() {
        c.a.a.a.b0.g gVar = this.x;
        if (gVar == null) {
            return;
        }
        gVar.setShapeAppearanceModel(this.z);
        if (o()) {
            this.x.a(this.D, this.G);
        }
        this.H = m();
        this.x.a(ColorStateList.valueOf(this.H));
        if (this.V == 3) {
            this.f2719f.getBackground().invalidateSelf();
        }
        i();
        invalidate();
    }

    private void i() {
        if (this.y == null) {
            return;
        }
        if (p()) {
            this.y.a(ColorStateList.valueOf(this.G));
        }
        invalidate();
    }

    private void j() {
        a(this.a0, this.d0, this.c0, this.f0, this.e0);
    }

    private void k() {
        a(this.M, this.O, this.N, this.Q, this.P);
    }

    private void l() {
        int i = this.B;
        if (i == 0) {
            this.x = null;
            this.y = null;
            return;
        }
        if (i == 1) {
            this.x = new c.a.a.a.b0.g(this.z);
            this.y = new c.a.a.a.b0.g();
        } else {
            if (i == 2) {
                if (this.u && !(this.x instanceof com.google.android.material.textfield.c)) {
                    this.x = new com.google.android.material.textfield.c(this.z);
                } else {
                    this.x = new c.a.a.a.b0.g(this.z);
                }
                this.y = null;
                return;
            }
            throw new IllegalArgumentException(this.B + " is illegal; only @BoxBackgroundMode constants are supported.");
        }
    }

    private int m() {
        return this.B == 1 ? c.a.a.a.s.a.a(c.a.a.a.s.a.a(this, c.a.a.a.b.colorSurface, 0), this.H) : this.H;
    }

    private int n() {
        float c2;
        if (!this.u) {
            return 0;
        }
        int i = this.B;
        if (i == 0 || i == 1) {
            c2 = this.y0.c();
        } else {
            if (i != 2) {
                return 0;
            }
            c2 = this.y0.c() / 2.0f;
        }
        return (int) c2;
    }

    private boolean o() {
        return this.B == 2 && p();
    }

    private boolean p() {
        return this.D > -1 && this.G != 0;
    }

    private void q() {
        if (r()) {
            ((com.google.android.material.textfield.c) this.x).t();
        }
    }

    private boolean r() {
        return this.u && !TextUtils.isEmpty(this.v) && (this.x instanceof com.google.android.material.textfield.c);
    }

    private void s() {
        Iterator<f> it = this.U.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    private void setEditText(EditText editText) {
        if (this.f2719f == null) {
            if (this.V != 3 && !(editText instanceof TextInputEditText)) {
                Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
            }
            this.f2719f = editText;
            w();
            setTextInputAccessibilityDelegate(new e(this));
            this.y0.b(this.f2719f.getTypeface());
            this.y0.a(this.f2719f.getTextSize());
            int gravity = this.f2719f.getGravity();
            this.y0.b((gravity & (-113)) | 48);
            this.y0.c(gravity);
            this.f2719f.addTextChangedListener(new a());
            if (this.m0 == null) {
                this.m0 = this.f2719f.getHintTextColors();
            }
            if (this.u) {
                if (TextUtils.isEmpty(this.v)) {
                    this.g = this.f2719f.getHint();
                    setHint(this.g);
                    this.f2719f.setHint((CharSequence) null);
                }
                this.w = true;
            }
            if (this.l != null) {
                a(this.f2719f.getText().length());
            }
            f();
            this.h.a();
            this.f2716c.bringToFront();
            this.f2717d.bringToFront();
            this.f2718e.bringToFront();
            this.k0.bringToFront();
            s();
            H();
            J();
            if (!isEnabled()) {
                editText.setEnabled(false);
            }
            a(false, true);
            return;
        }
        throw new IllegalArgumentException("We already have an EditText, can only have one");
    }

    private void setErrorIconVisible(boolean z) {
        this.k0.setVisibility(z ? 0 : 8);
        this.f2718e.setVisibility(z ? 8 : 0);
        J();
        if (t()) {
            return;
        }
        E();
    }

    private void setHintInternal(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.v)) {
            return;
        }
        this.v = charSequence;
        this.y0.a(charSequence);
        if (this.x0) {
            return;
        }
        x();
    }

    private boolean t() {
        return this.V != 0;
    }

    private boolean u() {
        return this.k0.getVisibility() == 0;
    }

    private boolean v() {
        return this.B == 1 && (Build.VERSION.SDK_INT < 16 || this.f2719f.getMinLines() <= 1);
    }

    private void w() {
        l();
        y();
        g();
        if (this.B != 0) {
            G();
        }
    }

    private void x() {
        if (r()) {
            RectF rectF = this.K;
            this.y0.a(rectF, this.f2719f.getWidth(), this.f2719f.getGravity());
            a(rectF);
            rectF.offset(-getPaddingLeft(), 0.0f);
            ((com.google.android.material.textfield.c) this.x).a(rectF);
        }
    }

    private void y() {
        if (B()) {
            w.a(this.f2719f, this.x);
        }
    }

    private boolean z() {
        return (this.k0.getVisibility() == 0 || ((t() && a()) || this.s != null)) && this.f2717d.getMeasuredWidth() > 0;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof EditText) {
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
            layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
            this.f2715b.addView(view, layoutParams2);
            this.f2715b.setLayoutParams(layoutParams);
            G();
            setEditText((EditText) view);
            return;
        }
        super.addView(view, i, layoutParams);
    }

    public boolean d() {
        return this.w;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i) {
        EditText editText;
        if (this.g != null && (editText = this.f2719f) != null) {
            boolean z = this.w;
            this.w = false;
            CharSequence hint = editText.getHint();
            this.f2719f.setHint(this.g);
            try {
                super.dispatchProvideAutofillStructure(viewStructure, i);
                return;
            } finally {
                this.f2719f.setHint(hint);
                this.w = z;
            }
        }
        super.dispatchProvideAutofillStructure(viewStructure, i);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        this.C0 = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.C0 = false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        b(canvas);
        a(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        if (this.B0) {
            return;
        }
        this.B0 = true;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        com.google.android.material.internal.a aVar = this.y0;
        boolean a2 = aVar != null ? aVar.a(drawableState) | false : false;
        if (this.f2719f != null) {
            a(w.F(this) && isEnabled());
        }
        f();
        g();
        if (a2) {
            invalidate();
        }
        this.B0 = false;
    }

    public boolean e() {
        return this.M.getVisibility() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        Drawable background;
        TextView textView;
        EditText editText = this.f2719f;
        if (editText == null || this.B != 0 || (background = editText.getBackground()) == null) {
            return;
        }
        if (a0.a(background)) {
            background = background.mutate();
        }
        if (this.h.c()) {
            background.setColorFilter(j.a(this.h.f(), PorterDuff.Mode.SRC_IN));
        } else if (this.k && (textView = this.l) != null) {
            background.setColorFilter(j.a(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            androidx.core.graphics.drawable.a.b(background);
            this.f2719f.refreshDrawableState();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g() {
        TextView textView;
        EditText editText;
        EditText editText2;
        if (this.x == null || this.B == 0) {
            return;
        }
        boolean z = false;
        boolean z2 = isFocused() || ((editText2 = this.f2719f) != null && editText2.hasFocus());
        boolean z3 = isHovered() || ((editText = this.f2719f) != null && editText.isHovered());
        if (!isEnabled()) {
            this.G = this.w0;
        } else if (this.h.c()) {
            if (this.r0 != null) {
                b(z2, z3);
            } else {
                this.G = this.h.f();
            }
        } else if (!this.k || (textView = this.l) == null) {
            if (z2) {
                this.G = this.q0;
            } else if (z3) {
                this.G = this.p0;
            } else {
                this.G = this.o0;
            }
        } else if (this.r0 != null) {
            b(z2, z3);
        } else {
            this.G = textView.getCurrentTextColor();
        }
        if (getErrorIconDrawable() != null && this.h.l() && this.h.c()) {
            z = true;
        }
        setErrorIconVisible(z);
        a(this.k0, this.l0);
        a(this.M, this.N);
        a(this.a0, this.c0);
        if (getEndIconDelegate().b()) {
            d(this.h.c());
        }
        if (z2 && isEnabled()) {
            this.D = this.F;
        } else {
            this.D = this.E;
        }
        if (this.B == 1) {
            if (!isEnabled()) {
                this.H = this.t0;
            } else if (z3 && !z2) {
                this.H = this.v0;
            } else if (z2) {
                this.H = this.u0;
            } else {
                this.H = this.s0;
            }
        }
        h();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.f2719f;
        if (editText != null) {
            return editText.getBaseline() + getPaddingTop() + n();
        }
        return super.getBaseline();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c.a.a.a.b0.g getBoxBackground() {
        int i = this.B;
        if (i != 1 && i != 2) {
            throw new IllegalStateException();
        }
        return this.x;
    }

    public int getBoxBackgroundColor() {
        return this.H;
    }

    public int getBoxBackgroundMode() {
        return this.B;
    }

    public float getBoxCornerRadiusBottomEnd() {
        return this.x.b();
    }

    public float getBoxCornerRadiusBottomStart() {
        return this.x.c();
    }

    public float getBoxCornerRadiusTopEnd() {
        return this.x.n();
    }

    public float getBoxCornerRadiusTopStart() {
        return this.x.m();
    }

    public int getBoxStrokeColor() {
        return this.q0;
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.r0;
    }

    public int getCounterMaxLength() {
        return this.j;
    }

    CharSequence getCounterOverflowDescription() {
        TextView textView;
        if (this.i && this.k && (textView = this.l) != null) {
            return textView.getContentDescription();
        }
        return null;
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.o;
    }

    public ColorStateList getCounterTextColor() {
        return this.o;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.m0;
    }

    public EditText getEditText() {
        return this.f2719f;
    }

    public CharSequence getEndIconContentDescription() {
        return this.a0.getContentDescription();
    }

    public Drawable getEndIconDrawable() {
        return this.a0.getDrawable();
    }

    public int getEndIconMode() {
        return this.V;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CheckableImageButton getEndIconView() {
        return this.a0;
    }

    public CharSequence getError() {
        if (this.h.l()) {
            return this.h.e();
        }
        return null;
    }

    public CharSequence getErrorContentDescription() {
        return this.h.d();
    }

    public int getErrorCurrentTextColors() {
        return this.h.f();
    }

    public Drawable getErrorIconDrawable() {
        return this.k0.getDrawable();
    }

    final int getErrorTextCurrentColor() {
        return this.h.f();
    }

    public CharSequence getHelperText() {
        if (this.h.m()) {
            return this.h.h();
        }
        return null;
    }

    public int getHelperTextCurrentTextColor() {
        return this.h.i();
    }

    public CharSequence getHint() {
        if (this.u) {
            return this.v;
        }
        return null;
    }

    final float getHintCollapsedTextHeight() {
        return this.y0.c();
    }

    final int getHintCurrentCollapsedTextColor() {
        return this.y0.d();
    }

    public ColorStateList getHintTextColor() {
        return this.n0;
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.a0.getContentDescription();
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.a0.getDrawable();
    }

    public CharSequence getPrefixText() {
        return this.q;
    }

    public ColorStateList getPrefixTextColor() {
        return this.r.getTextColors();
    }

    public TextView getPrefixTextView() {
        return this.r;
    }

    public CharSequence getStartIconContentDescription() {
        return this.M.getContentDescription();
    }

    public Drawable getStartIconDrawable() {
        return this.M.getDrawable();
    }

    public CharSequence getSuffixText() {
        return this.s;
    }

    public ColorStateList getSuffixTextColor() {
        return this.t.getTextColors();
    }

    public TextView getSuffixTextView() {
        return this.t;
    }

    public Typeface getTypeface() {
        return this.L;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        EditText editText = this.f2719f;
        if (editText != null) {
            Rect rect = this.I;
            com.google.android.material.internal.b.a(this, editText, rect);
            c(rect);
            if (this.u) {
                int gravity = this.f2719f.getGravity() & (-113);
                this.y0.b(gravity | 48);
                this.y0.c(gravity);
                this.y0.a(a(rect));
                this.y0.b(b(rect));
                this.y0.i();
                if (!r() || this.x0) {
                    return;
                }
                x();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        boolean F = F();
        boolean E = E();
        if (F || E) {
            this.f2719f.post(new c());
        }
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof h)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        h hVar = (h) parcelable;
        super.onRestoreInstanceState(hVar.d());
        setError(hVar.f2725d);
        if (hVar.f2726e) {
            this.a0.post(new b());
        }
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        h hVar = new h(super.onSaveInstanceState());
        if (this.h.c()) {
            hVar.f2725d = getError();
        }
        hVar.f2726e = t() && this.a0.isChecked();
        return hVar;
    }

    public void setBoxBackgroundColor(int i) {
        if (this.H != i) {
            this.H = i;
            this.s0 = i;
            h();
        }
    }

    public void setBoxBackgroundColorResource(int i) {
        setBoxBackgroundColor(a.f.d.b.a(getContext(), i));
    }

    public void setBoxBackgroundMode(int i) {
        if (i == this.B) {
            return;
        }
        this.B = i;
        if (this.f2719f != null) {
            w();
        }
    }

    public void setBoxStrokeColor(int i) {
        if (this.q0 != i) {
            this.q0 = i;
            g();
        }
    }

    public void setBoxStrokeColorStateList(ColorStateList colorStateList) {
        if (colorStateList.isStateful()) {
            this.o0 = colorStateList.getDefaultColor();
            this.w0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.p0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            this.q0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        } else if (this.q0 != colorStateList.getDefaultColor()) {
            this.q0 = colorStateList.getDefaultColor();
        }
        g();
    }

    public void setBoxStrokeErrorColor(ColorStateList colorStateList) {
        if (this.r0 != colorStateList) {
            this.r0 = colorStateList;
            g();
        }
    }

    public void setCounterEnabled(boolean z) {
        if (this.i != z) {
            if (z) {
                this.l = new AppCompatTextView(getContext());
                this.l.setId(c.a.a.a.f.textinput_counter);
                Typeface typeface = this.L;
                if (typeface != null) {
                    this.l.setTypeface(typeface);
                }
                this.l.setMaxLines(1);
                this.h.a(this.l, 2);
                D();
                C();
            } else {
                this.h.b(this.l, 2);
                this.l = null;
            }
            this.i = z;
        }
    }

    public void setCounterMaxLength(int i) {
        if (this.j != i) {
            if (i > 0) {
                this.j = i;
            } else {
                this.j = -1;
            }
            if (this.i) {
                C();
            }
        }
    }

    public void setCounterOverflowTextAppearance(int i) {
        if (this.m != i) {
            this.m = i;
            D();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList colorStateList) {
        if (this.p != colorStateList) {
            this.p = colorStateList;
            D();
        }
    }

    public void setCounterTextAppearance(int i) {
        if (this.n != i) {
            this.n = i;
            D();
        }
    }

    public void setCounterTextColor(ColorStateList colorStateList) {
        if (this.o != colorStateList) {
            this.o = colorStateList;
            D();
        }
    }

    public void setDefaultHintTextColor(ColorStateList colorStateList) {
        this.m0 = colorStateList;
        this.n0 = colorStateList;
        if (this.f2719f != null) {
            a(false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        a(this, z);
        super.setEnabled(z);
    }

    public void setEndIconActivated(boolean z) {
        this.a0.setActivated(z);
    }

    public void setEndIconCheckable(boolean z) {
        this.a0.setCheckable(z);
    }

    public void setEndIconContentDescription(int i) {
        setEndIconContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    public void setEndIconDrawable(int i) {
        setEndIconDrawable(i != 0 ? a.a.k.a.a.c(getContext(), i) : null);
    }

    public void setEndIconMode(int i) {
        int i2 = this.V;
        this.V = i;
        setEndIconVisible(i != 0);
        if (getEndIconDelegate().a(this.B)) {
            getEndIconDelegate().a();
            j();
            b(i2);
        } else {
            throw new IllegalStateException("The current box background mode " + this.B + " is not supported by the end icon mode " + i);
        }
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        a(this.a0, onClickListener, this.j0);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.j0 = onLongClickListener;
        b(this.a0, onLongClickListener);
    }

    public void setEndIconTintList(ColorStateList colorStateList) {
        if (this.c0 != colorStateList) {
            this.c0 = colorStateList;
            this.d0 = true;
            j();
        }
    }

    public void setEndIconTintMode(PorterDuff.Mode mode) {
        if (this.e0 != mode) {
            this.e0 = mode;
            this.f0 = true;
            j();
        }
    }

    public void setEndIconVisible(boolean z) {
        if (a() != z) {
            this.a0.setVisibility(z ? 0 : 8);
            J();
            E();
        }
    }

    public void setError(CharSequence charSequence) {
        if (!this.h.l()) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        if (!TextUtils.isEmpty(charSequence)) {
            this.h.b(charSequence);
        } else {
            this.h.j();
        }
    }

    public void setErrorContentDescription(CharSequence charSequence) {
        this.h.a(charSequence);
    }

    public void setErrorEnabled(boolean z) {
        this.h.a(z);
    }

    public void setErrorIconDrawable(int i) {
        setErrorIconDrawable(i != 0 ? a.a.k.a.a.c(getContext(), i) : null);
    }

    public void setErrorIconTintList(ColorStateList colorStateList) {
        this.l0 = colorStateList;
        Drawable drawable = this.k0.getDrawable();
        if (drawable != null) {
            drawable = androidx.core.graphics.drawable.a.i(drawable).mutate();
            androidx.core.graphics.drawable.a.a(drawable, colorStateList);
        }
        if (this.k0.getDrawable() != drawable) {
            this.k0.setImageDrawable(drawable);
        }
    }

    public void setErrorIconTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.k0.getDrawable();
        if (drawable != null) {
            drawable = androidx.core.graphics.drawable.a.i(drawable).mutate();
            androidx.core.graphics.drawable.a.a(drawable, mode);
        }
        if (this.k0.getDrawable() != drawable) {
            this.k0.setImageDrawable(drawable);
        }
    }

    public void setErrorTextAppearance(int i) {
        this.h.b(i);
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        this.h.a(colorStateList);
    }

    public void setHelperText(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            if (b()) {
                setHelperTextEnabled(false);
            }
        } else {
            if (!b()) {
                setHelperTextEnabled(true);
            }
            this.h.c(charSequence);
        }
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        this.h.b(colorStateList);
    }

    public void setHelperTextEnabled(boolean z) {
        this.h.b(z);
    }

    public void setHelperTextTextAppearance(int i) {
        this.h.c(i);
    }

    public void setHint(CharSequence charSequence) {
        if (this.u) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(DfuBaseService.ERROR_REMOTE_TYPE_SECURE_BUTTONLESS);
        }
    }

    public void setHintAnimationEnabled(boolean z) {
        this.z0 = z;
    }

    public void setHintEnabled(boolean z) {
        if (z != this.u) {
            this.u = z;
            if (!this.u) {
                this.w = false;
                if (!TextUtils.isEmpty(this.v) && TextUtils.isEmpty(this.f2719f.getHint())) {
                    this.f2719f.setHint(this.v);
                }
                setHintInternal(null);
            } else {
                CharSequence hint = this.f2719f.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.v)) {
                        setHint(hint);
                    }
                    this.f2719f.setHint((CharSequence) null);
                }
                this.w = true;
            }
            if (this.f2719f != null) {
                G();
            }
        }
    }

    public void setHintTextAppearance(int i) {
        this.y0.a(i);
        this.n0 = this.y0.b();
        if (this.f2719f != null) {
            a(false);
            G();
        }
    }

    public void setHintTextColor(ColorStateList colorStateList) {
        if (this.n0 != colorStateList) {
            if (this.m0 == null) {
                this.y0.a(colorStateList);
            }
            this.n0 = colorStateList;
            if (this.f2719f != null) {
                a(false);
            }
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(int i) {
        setPasswordVisibilityToggleContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int i) {
        setPasswordVisibilityToggleDrawable(i != 0 ? a.a.k.a.a.c(getContext(), i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z) {
        if (z && this.V != 1) {
            setEndIconMode(1);
        } else {
            if (z) {
                return;
            }
            setEndIconMode(0);
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(ColorStateList colorStateList) {
        this.c0 = colorStateList;
        this.d0 = true;
        j();
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        this.e0 = mode;
        this.f0 = true;
        j();
    }

    public void setPrefixText(CharSequence charSequence) {
        this.q = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.r.setText(charSequence);
        I();
    }

    public void setPrefixTextAppearance(int i) {
        i.d(this.r, i);
    }

    public void setPrefixTextColor(ColorStateList colorStateList) {
        this.r.setTextColor(colorStateList);
    }

    public void setStartIconCheckable(boolean z) {
        this.M.setCheckable(z);
    }

    public void setStartIconContentDescription(int i) {
        setStartIconContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    public void setStartIconDrawable(int i) {
        setStartIconDrawable(i != 0 ? a.a.k.a.a.c(getContext(), i) : null);
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        a(this.M, onClickListener, this.T);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.T = onLongClickListener;
        b(this.M, onLongClickListener);
    }

    public void setStartIconTintList(ColorStateList colorStateList) {
        if (this.N != colorStateList) {
            this.N = colorStateList;
            this.O = true;
            k();
        }
    }

    public void setStartIconTintMode(PorterDuff.Mode mode) {
        if (this.P != mode) {
            this.P = mode;
            this.Q = true;
            k();
        }
    }

    public void setStartIconVisible(boolean z) {
        if (e() != z) {
            this.M.setVisibility(z ? 0 : 8);
            H();
            E();
        }
    }

    public void setSuffixText(CharSequence charSequence) {
        this.s = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.t.setText(charSequence);
        K();
    }

    public void setSuffixTextAppearance(int i) {
        i.d(this.t, i);
    }

    public void setSuffixTextColor(ColorStateList colorStateList) {
        this.t.setTextColor(colorStateList);
    }

    public void setTextInputAccessibilityDelegate(e eVar) {
        EditText editText = this.f2719f;
        if (editText != null) {
            w.a(editText, eVar);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != this.L) {
            this.L = typeface;
            this.y0.b(typeface);
            this.h.a(typeface);
            TextView textView = this.l;
            if (textView != null) {
                textView.setTypeface(typeface);
            }
        }
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.a.a.a.b.textInputStyle);
    }

    private void d(boolean z) {
        if (z && getEndIconDrawable() != null) {
            Drawable mutate = androidx.core.graphics.drawable.a.i(getEndIconDrawable()).mutate();
            androidx.core.graphics.drawable.a.b(mutate, this.h.f());
            this.a0.setImageDrawable(mutate);
            return;
        }
        j();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        a(z, false);
    }

    public boolean b() {
        return this.h.m();
    }

    public void setEndIconContentDescription(CharSequence charSequence) {
        if (getEndIconContentDescription() != charSequence) {
            this.a0.setContentDescription(charSequence);
        }
    }

    public void setEndIconDrawable(Drawable drawable) {
        this.a0.setImageDrawable(drawable);
    }

    public void setErrorIconDrawable(Drawable drawable) {
        this.k0.setImageDrawable(drawable);
        setErrorIconVisible(drawable != null && this.h.l());
    }

    public void setStartIconContentDescription(CharSequence charSequence) {
        if (getStartIconContentDescription() != charSequence) {
            this.M.setContentDescription(charSequence);
        }
    }

    public void setStartIconDrawable(Drawable drawable) {
        this.M.setImageDrawable(drawable);
        if (drawable != null) {
            setStartIconVisible(true);
            k();
        } else {
            setStartIconVisible(false);
            setStartIconOnClickListener(null);
            setStartIconOnLongClickListener(null);
            setStartIconContentDescription((CharSequence) null);
        }
    }

    public TextInputLayout(Context context, AttributeSet attributeSet, int i) {
        super(com.google.android.material.theme.a.a.b(context, attributeSet, i, D0), attributeSet, i);
        this.h = new com.google.android.material.textfield.f(this);
        this.I = new Rect();
        this.J = new Rect();
        this.K = new RectF();
        this.U = new LinkedHashSet<>();
        this.V = 0;
        this.W = new SparseArray<>();
        this.b0 = new LinkedHashSet<>();
        this.y0 = new com.google.android.material.internal.a(this);
        Context context2 = getContext();
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        this.f2715b = new FrameLayout(context2);
        this.f2715b.setAddStatesFromChildren(true);
        addView(this.f2715b);
        this.f2716c = new LinearLayout(context2);
        this.f2716c.setOrientation(0);
        this.f2716c.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        this.f2715b.addView(this.f2716c);
        this.f2717d = new LinearLayout(context2);
        this.f2717d.setOrientation(0);
        this.f2717d.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388613));
        this.f2715b.addView(this.f2717d);
        this.f2718e = new FrameLayout(context2);
        this.f2718e.setLayoutParams(new FrameLayout.LayoutParams(-2, -1));
        this.y0.b(c.a.a.a.m.a.f2093a);
        this.y0.a(c.a.a.a.m.a.f2093a);
        this.y0.b(8388659);
        t0 d2 = l.d(context2, attributeSet, c.a.a.a.l.TextInputLayout, i, D0, c.a.a.a.l.TextInputLayout_counterTextAppearance, c.a.a.a.l.TextInputLayout_counterOverflowTextAppearance, c.a.a.a.l.TextInputLayout_errorTextAppearance, c.a.a.a.l.TextInputLayout_helperTextTextAppearance, c.a.a.a.l.TextInputLayout_hintTextAppearance);
        this.u = d2.a(c.a.a.a.l.TextInputLayout_hintEnabled, true);
        setHint(d2.e(c.a.a.a.l.TextInputLayout_android_hint));
        this.z0 = d2.a(c.a.a.a.l.TextInputLayout_hintAnimationEnabled, true);
        this.z = c.a.a.a.b0.k.a(context2, attributeSet, i, D0).a();
        this.A = context2.getResources().getDimensionPixelOffset(c.a.a.a.d.mtrl_textinput_box_label_cutout_padding);
        this.C = d2.b(c.a.a.a.l.TextInputLayout_boxCollapsedPaddingTop, 0);
        this.E = d2.c(c.a.a.a.l.TextInputLayout_boxStrokeWidth, context2.getResources().getDimensionPixelSize(c.a.a.a.d.mtrl_textinput_box_stroke_width_default));
        this.F = d2.c(c.a.a.a.l.TextInputLayout_boxStrokeWidthFocused, context2.getResources().getDimensionPixelSize(c.a.a.a.d.mtrl_textinput_box_stroke_width_focused));
        this.D = this.E;
        float a2 = d2.a(c.a.a.a.l.TextInputLayout_boxCornerRadiusTopStart, -1.0f);
        float a3 = d2.a(c.a.a.a.l.TextInputLayout_boxCornerRadiusTopEnd, -1.0f);
        float a4 = d2.a(c.a.a.a.l.TextInputLayout_boxCornerRadiusBottomEnd, -1.0f);
        float a5 = d2.a(c.a.a.a.l.TextInputLayout_boxCornerRadiusBottomStart, -1.0f);
        k.b m = this.z.m();
        if (a2 >= 0.0f) {
            m.d(a2);
        }
        if (a3 >= 0.0f) {
            m.e(a3);
        }
        if (a4 >= 0.0f) {
            m.c(a4);
        }
        if (a5 >= 0.0f) {
            m.b(a5);
        }
        this.z = m.a();
        ColorStateList a6 = c.a.a.a.y.c.a(context2, d2, c.a.a.a.l.TextInputLayout_boxBackgroundColor);
        if (a6 != null) {
            this.s0 = a6.getDefaultColor();
            this.H = this.s0;
            if (a6.isStateful()) {
                this.t0 = a6.getColorForState(new int[]{-16842910}, -1);
                this.u0 = a6.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
                this.v0 = a6.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            } else {
                this.u0 = this.s0;
                ColorStateList b2 = a.a.k.a.a.b(context2, c.a.a.a.c.mtrl_filled_background_color);
                this.t0 = b2.getColorForState(new int[]{-16842910}, -1);
                this.v0 = b2.getColorForState(new int[]{R.attr.state_hovered}, -1);
            }
        } else {
            this.H = 0;
            this.s0 = 0;
            this.t0 = 0;
            this.u0 = 0;
            this.v0 = 0;
        }
        if (d2.g(c.a.a.a.l.TextInputLayout_android_textColorHint)) {
            ColorStateList a7 = d2.a(c.a.a.a.l.TextInputLayout_android_textColorHint);
            this.n0 = a7;
            this.m0 = a7;
        }
        ColorStateList a8 = c.a.a.a.y.c.a(context2, d2, c.a.a.a.l.TextInputLayout_boxStrokeColor);
        this.q0 = d2.a(c.a.a.a.l.TextInputLayout_boxStrokeColor, 0);
        this.o0 = a.f.d.b.a(context2, c.a.a.a.c.mtrl_textinput_default_box_stroke_color);
        this.w0 = a.f.d.b.a(context2, c.a.a.a.c.mtrl_textinput_disabled_color);
        this.p0 = a.f.d.b.a(context2, c.a.a.a.c.mtrl_textinput_hovered_box_stroke_color);
        if (a8 != null) {
            setBoxStrokeColorStateList(a8);
        }
        if (d2.g(c.a.a.a.l.TextInputLayout_boxStrokeErrorColor)) {
            setBoxStrokeErrorColor(c.a.a.a.y.c.a(context2, d2, c.a.a.a.l.TextInputLayout_boxStrokeErrorColor));
        }
        if (d2.g(c.a.a.a.l.TextInputLayout_hintTextAppearance, -1) != -1) {
            setHintTextAppearance(d2.g(c.a.a.a.l.TextInputLayout_hintTextAppearance, 0));
        }
        int g2 = d2.g(c.a.a.a.l.TextInputLayout_errorTextAppearance, 0);
        CharSequence e2 = d2.e(c.a.a.a.l.TextInputLayout_errorContentDescription);
        boolean a9 = d2.a(c.a.a.a.l.TextInputLayout_errorEnabled, false);
        this.k0 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(c.a.a.a.h.design_text_input_end_icon, (ViewGroup) this.f2717d, false);
        this.k0.setVisibility(8);
        if (d2.g(c.a.a.a.l.TextInputLayout_errorIconDrawable)) {
            setErrorIconDrawable(d2.b(c.a.a.a.l.TextInputLayout_errorIconDrawable));
        }
        if (d2.g(c.a.a.a.l.TextInputLayout_errorIconTint)) {
            setErrorIconTintList(c.a.a.a.y.c.a(context2, d2, c.a.a.a.l.TextInputLayout_errorIconTint));
        }
        if (d2.g(c.a.a.a.l.TextInputLayout_errorIconTintMode)) {
            setErrorIconTintMode(m.a(d2.d(c.a.a.a.l.TextInputLayout_errorIconTintMode, -1), (PorterDuff.Mode) null));
        }
        this.k0.setContentDescription(getResources().getText(c.a.a.a.j.error_icon_content_description));
        w.i(this.k0, 2);
        this.k0.setClickable(false);
        this.k0.setPressable(false);
        this.k0.setFocusable(false);
        int g3 = d2.g(c.a.a.a.l.TextInputLayout_helperTextTextAppearance, 0);
        boolean a10 = d2.a(c.a.a.a.l.TextInputLayout_helperTextEnabled, false);
        CharSequence e3 = d2.e(c.a.a.a.l.TextInputLayout_helperText);
        int g4 = d2.g(c.a.a.a.l.TextInputLayout_prefixTextAppearance, 0);
        CharSequence e4 = d2.e(c.a.a.a.l.TextInputLayout_prefixText);
        int g5 = d2.g(c.a.a.a.l.TextInputLayout_suffixTextAppearance, 0);
        CharSequence e5 = d2.e(c.a.a.a.l.TextInputLayout_suffixText);
        boolean a11 = d2.a(c.a.a.a.l.TextInputLayout_counterEnabled, false);
        setCounterMaxLength(d2.d(c.a.a.a.l.TextInputLayout_counterMaxLength, -1));
        this.n = d2.g(c.a.a.a.l.TextInputLayout_counterTextAppearance, 0);
        this.m = d2.g(c.a.a.a.l.TextInputLayout_counterOverflowTextAppearance, 0);
        this.M = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(c.a.a.a.h.design_text_input_start_icon, (ViewGroup) this.f2716c, false);
        this.M.setVisibility(8);
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        if (d2.g(c.a.a.a.l.TextInputLayout_startIconDrawable)) {
            setStartIconDrawable(d2.b(c.a.a.a.l.TextInputLayout_startIconDrawable));
            if (d2.g(c.a.a.a.l.TextInputLayout_startIconContentDescription)) {
                setStartIconContentDescription(d2.e(c.a.a.a.l.TextInputLayout_startIconContentDescription));
            }
            setStartIconCheckable(d2.a(c.a.a.a.l.TextInputLayout_startIconCheckable, true));
        }
        if (d2.g(c.a.a.a.l.TextInputLayout_startIconTint)) {
            setStartIconTintList(c.a.a.a.y.c.a(context2, d2, c.a.a.a.l.TextInputLayout_startIconTint));
        }
        if (d2.g(c.a.a.a.l.TextInputLayout_startIconTintMode)) {
            setStartIconTintMode(m.a(d2.d(c.a.a.a.l.TextInputLayout_startIconTintMode, -1), (PorterDuff.Mode) null));
        }
        setBoxBackgroundMode(d2.d(c.a.a.a.l.TextInputLayout_boxBackgroundMode, 0));
        this.a0 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(c.a.a.a.h.design_text_input_end_icon, (ViewGroup) this.f2718e, false);
        this.f2718e.addView(this.a0);
        this.a0.setVisibility(8);
        this.W.append(-1, new com.google.android.material.textfield.b(this));
        this.W.append(0, new com.google.android.material.textfield.g(this));
        this.W.append(1, new com.google.android.material.textfield.h(this));
        this.W.append(2, new com.google.android.material.textfield.a(this));
        this.W.append(3, new com.google.android.material.textfield.d(this));
        if (d2.g(c.a.a.a.l.TextInputLayout_endIconMode)) {
            setEndIconMode(d2.d(c.a.a.a.l.TextInputLayout_endIconMode, 0));
            if (d2.g(c.a.a.a.l.TextInputLayout_endIconDrawable)) {
                setEndIconDrawable(d2.b(c.a.a.a.l.TextInputLayout_endIconDrawable));
            }
            if (d2.g(c.a.a.a.l.TextInputLayout_endIconContentDescription)) {
                setEndIconContentDescription(d2.e(c.a.a.a.l.TextInputLayout_endIconContentDescription));
            }
            setEndIconCheckable(d2.a(c.a.a.a.l.TextInputLayout_endIconCheckable, true));
        } else if (d2.g(c.a.a.a.l.TextInputLayout_passwordToggleEnabled)) {
            setEndIconMode(d2.a(c.a.a.a.l.TextInputLayout_passwordToggleEnabled, false) ? 1 : 0);
            setEndIconDrawable(d2.b(c.a.a.a.l.TextInputLayout_passwordToggleDrawable));
            setEndIconContentDescription(d2.e(c.a.a.a.l.TextInputLayout_passwordToggleContentDescription));
            if (d2.g(c.a.a.a.l.TextInputLayout_passwordToggleTint)) {
                setEndIconTintList(c.a.a.a.y.c.a(context2, d2, c.a.a.a.l.TextInputLayout_passwordToggleTint));
            }
            if (d2.g(c.a.a.a.l.TextInputLayout_passwordToggleTintMode)) {
                setEndIconTintMode(m.a(d2.d(c.a.a.a.l.TextInputLayout_passwordToggleTintMode, -1), (PorterDuff.Mode) null));
            }
        }
        if (!d2.g(c.a.a.a.l.TextInputLayout_passwordToggleEnabled)) {
            if (d2.g(c.a.a.a.l.TextInputLayout_endIconTint)) {
                setEndIconTintList(c.a.a.a.y.c.a(context2, d2, c.a.a.a.l.TextInputLayout_endIconTint));
            }
            if (d2.g(c.a.a.a.l.TextInputLayout_endIconTintMode)) {
                setEndIconTintMode(m.a(d2.d(c.a.a.a.l.TextInputLayout_endIconTintMode, -1), (PorterDuff.Mode) null));
            }
        }
        this.r = new AppCompatTextView(context2);
        this.r.setId(c.a.a.a.f.textinput_prefix_text);
        this.r.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        w.h(this.r, 1);
        this.f2716c.addView(this.M);
        this.f2716c.addView(this.r);
        this.t = new AppCompatTextView(context2);
        this.t.setId(c.a.a.a.f.textinput_suffix_text);
        this.t.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 80));
        w.h(this.t, 1);
        this.f2717d.addView(this.t);
        this.f2717d.addView(this.k0);
        this.f2717d.addView(this.f2718e);
        setHelperTextEnabled(a10);
        setHelperText(e3);
        setHelperTextTextAppearance(g3);
        setErrorEnabled(a9);
        setErrorTextAppearance(g2);
        setErrorContentDescription(e2);
        setCounterTextAppearance(this.n);
        setCounterOverflowTextAppearance(this.m);
        setPrefixText(e4);
        setPrefixTextAppearance(g4);
        setSuffixText(e5);
        setSuffixTextAppearance(g5);
        if (d2.g(c.a.a.a.l.TextInputLayout_errorTextColor)) {
            setErrorTextColor(d2.a(c.a.a.a.l.TextInputLayout_errorTextColor));
        }
        if (d2.g(c.a.a.a.l.TextInputLayout_helperTextTextColor)) {
            setHelperTextColor(d2.a(c.a.a.a.l.TextInputLayout_helperTextTextColor));
        }
        if (d2.g(c.a.a.a.l.TextInputLayout_hintTextColor)) {
            setHintTextColor(d2.a(c.a.a.a.l.TextInputLayout_hintTextColor));
        }
        if (d2.g(c.a.a.a.l.TextInputLayout_counterTextColor)) {
            setCounterTextColor(d2.a(c.a.a.a.l.TextInputLayout_counterTextColor));
        }
        if (d2.g(c.a.a.a.l.TextInputLayout_counterOverflowTextColor)) {
            setCounterOverflowTextColor(d2.a(c.a.a.a.l.TextInputLayout_counterOverflowTextColor));
        }
        if (d2.g(c.a.a.a.l.TextInputLayout_prefixTextColor)) {
            setPrefixTextColor(d2.a(c.a.a.a.l.TextInputLayout_prefixTextColor));
        }
        if (d2.g(c.a.a.a.l.TextInputLayout_suffixTextColor)) {
            setSuffixTextColor(d2.a(c.a.a.a.l.TextInputLayout_suffixTextColor));
        }
        setCounterEnabled(a11);
        setEnabled(d2.a(c.a.a.a.l.TextInputLayout_android_enabled, true));
        d2.a();
        w.i(this, 2);
    }

    private void a(boolean z, boolean z2) {
        ColorStateList colorStateList;
        TextView textView;
        boolean isEnabled = isEnabled();
        EditText editText = this.f2719f;
        boolean z3 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.f2719f;
        boolean z4 = editText2 != null && editText2.hasFocus();
        boolean c2 = this.h.c();
        ColorStateList colorStateList2 = this.m0;
        if (colorStateList2 != null) {
            this.y0.a(colorStateList2);
            this.y0.b(this.m0);
        }
        if (!isEnabled) {
            this.y0.a(ColorStateList.valueOf(this.w0));
            this.y0.b(ColorStateList.valueOf(this.w0));
        } else if (c2) {
            this.y0.a(this.h.g());
        } else if (this.k && (textView = this.l) != null) {
            this.y0.a(textView.getTextColors());
        } else if (z4 && (colorStateList = this.n0) != null) {
            this.y0.a(colorStateList);
        }
        if (!z3 && (!isEnabled() || (!z4 && !c2))) {
            if (z2 || !this.x0) {
                c(z);
                return;
            }
            return;
        }
        if (z2 || this.x0) {
            b(z);
        }
    }

    private int b(int i, boolean z) {
        int compoundPaddingRight = i - this.f2719f.getCompoundPaddingRight();
        return (this.q == null || !z) ? compoundPaddingRight : compoundPaddingRight + this.r.getMeasuredWidth() + this.r.getPaddingRight();
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence charSequence) {
        this.a0.setContentDescription(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable drawable) {
        this.a0.setImageDrawable(drawable);
    }

    private void c(boolean z) {
        ValueAnimator valueAnimator = this.A0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.A0.cancel();
        }
        if (z && this.z0) {
            a(0.0f);
        } else {
            this.y0.b(0.0f);
        }
        if (r() && ((com.google.android.material.textfield.c) this.x).s()) {
            q();
        }
        this.x0 = true;
        I();
        K();
    }

    private Rect b(Rect rect) {
        if (this.f2719f != null) {
            Rect rect2 = this.J;
            float e2 = this.y0.e();
            rect2.left = rect.left + this.f2719f.getCompoundPaddingLeft();
            rect2.top = a(rect, e2);
            rect2.right = rect.right - this.f2719f.getCompoundPaddingRight();
            rect2.bottom = a(rect, rect2, e2);
            return rect2;
        }
        throw new IllegalStateException();
    }

    private void b(int i) {
        Iterator<g> it = this.b0.iterator();
        while (it.hasNext()) {
            it.next().a(this, i);
        }
    }

    final boolean c() {
        return this.x0;
    }

    private static void b(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        a(checkableImageButton, onLongClickListener);
    }

    private void b(Canvas canvas) {
        if (this.u) {
            this.y0.a(canvas);
        }
    }

    private void b(boolean z) {
        ValueAnimator valueAnimator = this.A0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.A0.cancel();
        }
        if (z && this.z0) {
            a(1.0f);
        } else {
            this.y0.b(1.0f);
        }
        this.x0 = false;
        if (r()) {
            x();
        }
        I();
        K();
    }

    void a(int i) {
        boolean z = this.k;
        if (this.j == -1) {
            this.l.setText(String.valueOf(i));
            this.l.setContentDescription(null);
            this.k = false;
        } else {
            if (w.e(this.l) == 1) {
                w.h(this.l, 0);
            }
            this.k = i > this.j;
            a(getContext(), this.l, i, this.j, this.k);
            if (z != this.k) {
                D();
                if (this.k) {
                    w.h(this.l, 1);
                }
            }
            this.l.setText(getContext().getString(c.a.a.a.j.character_counter_pattern, Integer.valueOf(i), Integer.valueOf(this.j)));
        }
        if (this.f2719f == null || z == this.k) {
            return;
        }
        a(false);
        g();
        f();
    }

    private void b(boolean z, boolean z2) {
        int defaultColor = this.r0.getDefaultColor();
        int colorForState = this.r0.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, defaultColor);
        int colorForState2 = this.r0.getColorForState(new int[]{R.attr.state_activated, R.attr.state_enabled}, defaultColor);
        if (z) {
            this.G = colorForState2;
        } else if (z2) {
            this.G = colorForState;
        } else {
            this.G = defaultColor;
        }
    }

    private static void a(Context context, TextView textView, int i, int i2, boolean z) {
        textView.setContentDescription(context.getString(z ? c.a.a.a.j.character_counter_overflowed_content_description : c.a.a.a.j.character_counter_content_description, Integer.valueOf(i), Integer.valueOf(i2)));
    }

    private static void a(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                a((ViewGroup) childAt, z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0015, code lost:
    
        if (r3.getTextColors().getDefaultColor() == (-65281)) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(android.widget.TextView r3, int r4) {
        /*
            r2 = this;
            r0 = 1
            androidx.core.widget.i.d(r3, r4)     // Catch: java.lang.Exception -> L1b
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L1b
            r1 = 23
            if (r4 < r1) goto L18
            android.content.res.ColorStateList r4 = r3.getTextColors()     // Catch: java.lang.Exception -> L1b
            int r4 = r4.getDefaultColor()     // Catch: java.lang.Exception -> L1b
            r1 = -65281(0xffffffffffff00ff, float:NaN)
            if (r4 != r1) goto L18
            goto L1c
        L18:
            r4 = 0
            r0 = 0
            goto L1c
        L1b:
        L1c:
            if (r0 == 0) goto L30
            int r4 = c.a.a.a.k.TextAppearance_AppCompat_Caption
            androidx.core.widget.i.d(r3, r4)
            android.content.Context r4 = r2.getContext()
            int r0 = c.a.a.a.c.design_error
            int r4 = a.f.d.b.a(r4, r0)
            r3.setTextColor(r4)
        L30:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.a(android.widget.TextView, int):void");
    }

    private Rect a(Rect rect) {
        if (this.f2719f != null) {
            Rect rect2 = this.J;
            boolean z = w.q(this) == 1;
            rect2.bottom = rect.bottom;
            int i = this.B;
            if (i == 1) {
                rect2.left = a(rect.left, z);
                rect2.top = rect.top + this.C;
                rect2.right = b(rect.right, z);
                return rect2;
            }
            if (i != 2) {
                rect2.left = rect.left + this.f2719f.getCompoundPaddingLeft();
                rect2.top = getPaddingTop();
                rect2.right = rect.right - this.f2719f.getCompoundPaddingRight();
                return rect2;
            }
            rect2.left = rect.left + this.f2719f.getPaddingLeft();
            rect2.top = rect.top - n();
            rect2.right = rect.right - this.f2719f.getPaddingRight();
            return rect2;
        }
        throw new IllegalStateException();
    }

    private int a(int i, boolean z) {
        int compoundPaddingLeft = i + this.f2719f.getCompoundPaddingLeft();
        return (this.q == null || z) ? compoundPaddingLeft : (compoundPaddingLeft - this.r.getMeasuredWidth()) + this.r.getPaddingLeft();
    }

    private int a(Rect rect, float f2) {
        if (v()) {
            return (int) (rect.centerY() - (f2 / 2.0f));
        }
        return rect.top + this.f2719f.getCompoundPaddingTop();
    }

    private int a(Rect rect, Rect rect2, float f2) {
        if (this.B == 1) {
            return (int) (rect2.top + f2);
        }
        return rect.bottom - this.f2719f.getCompoundPaddingBottom();
    }

    public boolean a() {
        return this.f2718e.getVisibility() == 0 && this.a0.getVisibility() == 0;
    }

    public void a(g gVar) {
        this.b0.add(gVar);
    }

    public void a(f fVar) {
        this.U.add(fVar);
        if (this.f2719f != null) {
            fVar.a(this);
        }
    }

    private void a(CheckableImageButton checkableImageButton, boolean z, ColorStateList colorStateList, boolean z2, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null && (z || z2)) {
            drawable = androidx.core.graphics.drawable.a.i(drawable).mutate();
            if (z) {
                androidx.core.graphics.drawable.a.a(drawable, colorStateList);
            }
            if (z2) {
                androidx.core.graphics.drawable.a.a(drawable, mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    private static void a(CheckableImageButton checkableImageButton, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        checkableImageButton.setOnClickListener(onClickListener);
        a(checkableImageButton, onLongClickListener);
    }

    private static void a(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        boolean A = w.A(checkableImageButton);
        boolean z = onLongClickListener != null;
        boolean z2 = A || z;
        checkableImageButton.setFocusable(z2);
        checkableImageButton.setClickable(A);
        checkableImageButton.setPressable(A);
        checkableImageButton.setLongClickable(z);
        w.i(checkableImageButton, z2 ? 1 : 2);
    }

    private void a(Canvas canvas) {
        c.a.a.a.b0.g gVar = this.y;
        if (gVar != null) {
            Rect bounds = gVar.getBounds();
            bounds.top = bounds.bottom - this.D;
            this.y.draw(canvas);
        }
    }

    private void a(RectF rectF) {
        float f2 = rectF.left;
        int i = this.A;
        rectF.left = f2 - i;
        rectF.top -= i;
        rectF.right += i;
        rectF.bottom += i;
    }

    private void a(CheckableImageButton checkableImageButton, ColorStateList colorStateList) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (checkableImageButton.getDrawable() == null || colorStateList == null || !colorStateList.isStateful()) {
            return;
        }
        int colorForState = colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
        Drawable mutate = androidx.core.graphics.drawable.a.i(drawable).mutate();
        androidx.core.graphics.drawable.a.a(mutate, ColorStateList.valueOf(colorForState));
        checkableImageButton.setImageDrawable(mutate);
    }

    void a(float f2) {
        if (this.y0.f() == f2) {
            return;
        }
        if (this.A0 == null) {
            this.A0 = new ValueAnimator();
            this.A0.setInterpolator(c.a.a.a.m.a.f2094b);
            this.A0.setDuration(167L);
            this.A0.addUpdateListener(new d());
        }
        this.A0.setFloatValues(this.y0.f(), f2);
        this.A0.start();
    }
}
