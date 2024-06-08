package com.google.android.material.chip;

import a.f.l.f0.c;
import a.f.l.w;
import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.widget.g;
import androidx.recyclerview.widget.RecyclerView;
import c.a.a.a.b0.k;
import c.a.a.a.b0.n;
import c.a.a.a.j;
import c.a.a.a.m.h;
import c.a.a.a.y.d;
import c.a.a.a.y.f;
import com.google.android.material.chip.a;
import com.google.android.material.internal.l;
import com.google.android.material.internal.m;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/* loaded from: classes.dex */
public class Chip extends g implements a.InterfaceC0081a, n {
    private static final Rect u = new Rect();
    private static final int[] v = {R.attr.state_selected};
    private static final int[] w = {R.attr.state_checkable};

    /* renamed from: e, reason: collision with root package name */
    private com.google.android.material.chip.a f2439e;

    /* renamed from: f, reason: collision with root package name */
    private InsetDrawable f2440f;
    private RippleDrawable g;
    private View.OnClickListener h;
    private CompoundButton.OnCheckedChangeListener i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private int o;
    private int p;
    private final c q;
    private final Rect r;
    private final RectF s;
    private final f t;

    /* loaded from: classes.dex */
    class a extends f {
        a() {
        }

        @Override // c.a.a.a.y.f
        public void a(int i) {
        }

        @Override // c.a.a.a.y.f
        public void a(Typeface typeface, boolean z) {
            Chip chip = Chip.this;
            chip.setText(chip.f2439e.b0() ? Chip.this.f2439e.S() : Chip.this.getText());
            Chip.this.requestLayout();
            Chip.this.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends ViewOutlineProvider {
        b() {
        }

        @Override // android.view.ViewOutlineProvider
        @TargetApi(21)
        public void getOutline(View view, Outline outline) {
            if (Chip.this.f2439e != null) {
                Chip.this.f2439e.getOutline(outline);
            } else {
                outline.setAlpha(0.0f);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class c extends a.h.b.a {
        c(Chip chip) {
            super(chip);
        }

        @Override // a.h.b.a
        protected int a(float f2, float f3) {
            return (Chip.this.i() && Chip.this.getCloseIconTouchBounds().contains(f2, f3)) ? 1 : 0;
        }

        @Override // a.h.b.a
        protected void a(List<Integer> list) {
            list.add(0);
            if (Chip.this.i() && Chip.this.c() && Chip.this.h != null) {
                list.add(1);
            }
        }

        @Override // a.h.b.a
        protected void a(int i, boolean z) {
            if (i == 1) {
                Chip.this.m = z;
                Chip.this.refreshDrawableState();
            }
        }

        @Override // a.h.b.a
        protected void a(int i, a.f.l.f0.c cVar) {
            if (i == 1) {
                CharSequence closeIconContentDescription = Chip.this.getCloseIconContentDescription();
                if (closeIconContentDescription != null) {
                    cVar.b(closeIconContentDescription);
                } else {
                    CharSequence text = Chip.this.getText();
                    Context context = Chip.this.getContext();
                    int i2 = j.mtrl_chip_close_icon_content_description;
                    Object[] objArr = new Object[1];
                    if (TextUtils.isEmpty(text)) {
                        text = "";
                    }
                    objArr[0] = text;
                    cVar.b((CharSequence) context.getString(i2, objArr).trim());
                }
                cVar.c(Chip.this.getCloseIconTouchBoundsInt());
                cVar.a(c.a.g);
                cVar.g(Chip.this.isEnabled());
                return;
            }
            cVar.b("");
            cVar.c(Chip.u);
        }

        @Override // a.h.b.a
        protected void a(a.f.l.f0.c cVar) {
            cVar.b(Chip.this.b());
            cVar.d(Chip.this.isClickable());
            if (!Chip.this.b() && !Chip.this.isClickable()) {
                cVar.a("android.view.View");
            } else {
                cVar.a((CharSequence) (Chip.this.b() ? "android.widget.CompoundButton" : "android.widget.Button"));
            }
            CharSequence text = Chip.this.getText();
            if (Build.VERSION.SDK_INT >= 23) {
                cVar.g(text);
            } else {
                cVar.b(text);
            }
        }

        @Override // a.h.b.a
        protected boolean a(int i, int i2, Bundle bundle) {
            if (i2 != 16) {
                return false;
            }
            if (i == 0) {
                return Chip.this.performClick();
            }
            if (i == 1) {
                return Chip.this.d();
            }
            return false;
        }
    }

    public Chip(Context context) {
        this(context, null);
    }

    private int[] g() {
        int i = 0;
        int i2 = isEnabled() ? 1 : 0;
        if (this.m) {
            i2++;
        }
        if (this.l) {
            i2++;
        }
        if (this.k) {
            i2++;
        }
        if (isChecked()) {
            i2++;
        }
        int[] iArr = new int[i2];
        if (isEnabled()) {
            iArr[0] = 16842910;
            i = 1;
        }
        if (this.m) {
            iArr[i] = 16842908;
            i++;
        }
        if (this.l) {
            iArr[i] = 16843623;
            i++;
        }
        if (this.k) {
            iArr[i] = 16842919;
            i++;
        }
        if (isChecked()) {
            iArr[i] = 16842913;
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RectF getCloseIconTouchBounds() {
        this.s.setEmpty();
        if (i()) {
            this.f2439e.a(this.s);
        }
        return this.s;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect getCloseIconTouchBoundsInt() {
        RectF closeIconTouchBounds = getCloseIconTouchBounds();
        this.r.set((int) closeIconTouchBounds.left, (int) closeIconTouchBounds.top, (int) closeIconTouchBounds.right, (int) closeIconTouchBounds.bottom);
        return this.r;
    }

    private d getTextAppearance() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.T();
        }
        return null;
    }

    private void h() {
        if (getBackgroundDrawable() == this.f2440f && this.f2439e.getCallback() == null) {
            this.f2439e.setCallback(this.f2440f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i() {
        com.google.android.material.chip.a aVar = this.f2439e;
        return (aVar == null || aVar.F() == null) ? false : true;
    }

    private void j() {
        if (Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new b());
        }
    }

    private void k() {
        if (this.f2440f != null) {
            this.f2440f = null;
            setMinWidth(0);
            setMinHeight((int) getChipMinHeight());
            m();
        }
    }

    private void l() {
        if (i() && c() && this.h != null) {
            w.a(this, this.q);
        } else {
            w.a(this, (a.f.l.a) null);
        }
    }

    private void m() {
        if (c.a.a.a.z.b.f2166a) {
            n();
            return;
        }
        this.f2439e.f(true);
        w.a(this, getBackgroundDrawable());
        o();
        h();
    }

    private void n() {
        this.g = new RippleDrawable(c.a.a.a.z.b.b(this.f2439e.Q()), getBackgroundDrawable(), null);
        this.f2439e.f(false);
        w.a(this, this.g);
        o();
    }

    private void o() {
        com.google.android.material.chip.a aVar;
        if (TextUtils.isEmpty(getText()) || (aVar = this.f2439e) == null) {
            return;
        }
        w.b(this, (int) (this.f2439e.C() + this.f2439e.V() + this.f2439e.s()), getPaddingTop(), (int) (aVar.x() + this.f2439e.U() + this.f2439e.t()), getPaddingBottom());
    }

    private void p() {
        TextPaint paint = getPaint();
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            paint.drawableState = aVar.getState();
        }
        d textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.b(getContext(), paint, this.t);
        }
    }

    private void setCloseIconHovered(boolean z) {
        if (this.l != z) {
            this.l = z;
            refreshDrawableState();
        }
    }

    private void setCloseIconPressed(boolean z) {
        if (this.k != z) {
            this.k = z;
            refreshDrawableState();
        }
    }

    @Override // android.view.View
    protected boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return a(motionEvent) || this.q.a(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!this.q.a(keyEvent) || this.q.b() == Integer.MIN_VALUE) {
            return super.dispatchKeyEvent(keyEvent);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.g, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        com.google.android.material.chip.a aVar = this.f2439e;
        if ((aVar == null || !aVar.Y()) ? false : this.f2439e.a(g())) {
            invalidate();
        }
    }

    public Drawable getBackgroundDrawable() {
        InsetDrawable insetDrawable = this.f2440f;
        return insetDrawable == null ? this.f2439e : insetDrawable;
    }

    public Drawable getCheckedIcon() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.u();
        }
        return null;
    }

    public ColorStateList getChipBackgroundColor() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.v();
        }
        return null;
    }

    public float getChipCornerRadius() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.w();
        }
        return 0.0f;
    }

    public Drawable getChipDrawable() {
        return this.f2439e;
    }

    public float getChipEndPadding() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.x();
        }
        return 0.0f;
    }

    public Drawable getChipIcon() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.y();
        }
        return null;
    }

    public float getChipIconSize() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.z();
        }
        return 0.0f;
    }

    public ColorStateList getChipIconTint() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.A();
        }
        return null;
    }

    public float getChipMinHeight() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.B();
        }
        return 0.0f;
    }

    public float getChipStartPadding() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.C();
        }
        return 0.0f;
    }

    public ColorStateList getChipStrokeColor() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.D();
        }
        return null;
    }

    public float getChipStrokeWidth() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.E();
        }
        return 0.0f;
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    public Drawable getCloseIcon() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.F();
        }
        return null;
    }

    public CharSequence getCloseIconContentDescription() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.G();
        }
        return null;
    }

    public float getCloseIconEndPadding() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.H();
        }
        return 0.0f;
    }

    public float getCloseIconSize() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.I();
        }
        return 0.0f;
    }

    public float getCloseIconStartPadding() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.J();
        }
        return 0.0f;
    }

    public ColorStateList getCloseIconTint() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.L();
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextUtils.TruncateAt getEllipsize() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.M();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public void getFocusedRect(Rect rect) {
        if (this.q.b() != 1 && this.q.a() != 1) {
            super.getFocusedRect(rect);
        } else {
            rect.set(getCloseIconTouchBoundsInt());
        }
    }

    public h getHideMotionSpec() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.N();
        }
        return null;
    }

    public float getIconEndPadding() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.O();
        }
        return 0.0f;
    }

    public float getIconStartPadding() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.P();
        }
        return 0.0f;
    }

    public ColorStateList getRippleColor() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.Q();
        }
        return null;
    }

    public k getShapeAppearanceModel() {
        return this.f2439e.k();
    }

    public h getShowMotionSpec() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.R();
        }
        return null;
    }

    public float getTextEndPadding() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.U();
        }
        return 0.0f;
    }

    public float getTextStartPadding() {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            return aVar.V();
        }
        return 0.0f;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a.a.a.b0.h.a(this, this.f2439e);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (isChecked()) {
            CheckBox.mergeDrawableStates(onCreateDrawableState, v);
        }
        if (b()) {
            CheckBox.mergeDrawableStates(onCreateDrawableState, w);
        }
        return onCreateDrawableState;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        this.q.a(z, i, rect);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7) {
            setCloseIconHovered(getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()));
        } else if (actionMasked == 10) {
            setCloseIconHovered(false);
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (!b() && !isClickable()) {
            accessibilityNodeInfo.setClassName("android.view.View");
        } else {
            accessibilityNodeInfo.setClassName(b() ? "android.widget.CompoundButton" : "android.widget.Button");
        }
        accessibilityNodeInfo.setCheckable(b());
        accessibilityNodeInfo.setClickable(isClickable());
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    @TargetApi(24)
    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        if (getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) && isEnabled()) {
            return PointerIcon.getSystemIcon(getContext(), 1002);
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    @TargetApi(17)
    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        if (this.o != i) {
            this.o = i;
            o();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x001e, code lost:
    
        if (r0 != 3) goto L22;
     */
    @Override // android.widget.TextView, android.view.View
    @android.annotation.SuppressLint({"ClickableViewAccessibility"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionMasked()
            android.graphics.RectF r1 = r5.getCloseIconTouchBounds()
            float r2 = r6.getX()
            float r3 = r6.getY()
            boolean r1 = r1.contains(r2, r3)
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L39
            if (r0 == r3) goto L2b
            r4 = 2
            if (r0 == r4) goto L21
            r1 = 3
            if (r0 == r1) goto L34
            goto L40
        L21:
            boolean r0 = r5.k
            if (r0 == 0) goto L40
            if (r1 != 0) goto L3e
            r5.setCloseIconPressed(r2)
            goto L3e
        L2b:
            boolean r0 = r5.k
            if (r0 == 0) goto L34
            r5.d()
            r0 = 1
            goto L35
        L34:
            r0 = 0
        L35:
            r5.setCloseIconPressed(r2)
            goto L41
        L39:
            if (r1 == 0) goto L40
            r5.setCloseIconPressed(r3)
        L3e:
            r0 = 1
            goto L41
        L40:
            r0 = 0
        L41:
            if (r0 != 0) goto L49
            boolean r6 = super.onTouchEvent(r6)
            if (r6 == 0) goto L4a
        L49:
            r2 = 1
        L4a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        if (drawable != getBackgroundDrawable() && drawable != this.g) {
            Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
        } else {
            super.setBackground(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        Log.w("Chip", "Do not set the background color; Chip manages its own background drawable.");
    }

    @Override // androidx.appcompat.widget.g, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable != getBackgroundDrawable() && drawable != this.g) {
            Log.w("Chip", "Do not set the background drawable; Chip manages its own background drawable.");
        } else {
            super.setBackgroundDrawable(drawable);
        }
    }

    @Override // androidx.appcompat.widget.g, android.view.View
    public void setBackgroundResource(int i) {
        Log.w("Chip", "Do not set the background resource; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        Log.w("Chip", "Do not set the background tint list; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        Log.w("Chip", "Do not set the background tint mode; Chip manages its own background drawable.");
    }

    public void setCheckable(boolean z) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.a(z);
        }
    }

    public void setCheckableResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.c(i);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar == null) {
            this.j = z;
            return;
        }
        if (aVar.X()) {
            boolean isChecked = isChecked();
            super.setChecked(z);
            if (isChecked == z || (onCheckedChangeListener = this.i) == null) {
                return;
            }
            onCheckedChangeListener.onCheckedChanged(this, z);
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.a(drawable);
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z) {
        setCheckedIconVisible(z);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int i) {
        setCheckedIconVisible(i);
    }

    public void setCheckedIconResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.d(i);
        }
    }

    public void setCheckedIconVisible(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.e(i);
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.c(colorStateList);
        }
    }

    public void setChipBackgroundColorResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.f(i);
        }
    }

    @Deprecated
    public void setChipCornerRadius(float f2) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.f(f2);
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.g(i);
        }
    }

    public void setChipDrawable(com.google.android.material.chip.a aVar) {
        com.google.android.material.chip.a aVar2 = this.f2439e;
        if (aVar2 != aVar) {
            b(aVar2);
            this.f2439e = aVar;
            this.f2439e.e(false);
            a(this.f2439e);
            a(this.p);
            m();
        }
    }

    public void setChipEndPadding(float f2) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.g(f2);
        }
    }

    public void setChipEndPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.h(i);
        }
    }

    public void setChipIcon(Drawable drawable) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.b(drawable);
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z) {
        setChipIconVisible(z);
    }

    @Deprecated
    public void setChipIconEnabledResource(int i) {
        setChipIconVisible(i);
    }

    public void setChipIconResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.i(i);
        }
    }

    public void setChipIconSize(float f2) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.h(f2);
        }
    }

    public void setChipIconSizeResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.j(i);
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.d(colorStateList);
        }
    }

    public void setChipIconTintResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.k(i);
        }
    }

    public void setChipIconVisible(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.l(i);
        }
    }

    public void setChipMinHeight(float f2) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.i(f2);
        }
    }

    public void setChipMinHeightResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.m(i);
        }
    }

    public void setChipStartPadding(float f2) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.j(f2);
        }
    }

    public void setChipStartPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.n(i);
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.e(colorStateList);
        }
    }

    public void setChipStrokeColorResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.o(i);
        }
    }

    public void setChipStrokeWidth(float f2) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.k(f2);
        }
    }

    public void setChipStrokeWidthResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.p(i);
        }
    }

    @Deprecated
    public void setChipText(CharSequence charSequence) {
        setText(charSequence);
    }

    @Deprecated
    public void setChipTextResource(int i) {
        setText(getResources().getString(i));
    }

    public void setCloseIcon(Drawable drawable) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.c(drawable);
        }
        l();
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.a(charSequence);
        }
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z) {
        setCloseIconVisible(z);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i) {
        setCloseIconVisible(i);
    }

    public void setCloseIconEndPadding(float f2) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.l(f2);
        }
    }

    public void setCloseIconEndPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.q(i);
        }
    }

    public void setCloseIconResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.r(i);
        }
        l();
    }

    public void setCloseIconSize(float f2) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.m(f2);
        }
    }

    public void setCloseIconSizeResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.s(i);
        }
    }

    public void setCloseIconStartPadding(float f2) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.n(f2);
        }
    }

    public void setCloseIconStartPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.t(i);
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.f(colorStateList);
        }
    }

    public void setCloseIconTintResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.u(i);
        }
    }

    public void setCloseIconVisible(int i) {
        setCloseIconVisible(getResources().getBoolean(i));
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 == null) {
            super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
            return;
        }
        throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 == null) {
            super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
            return;
        }
        throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i3 == 0) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
            return;
        }
        throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i3 == 0) {
            super.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
            return;
        }
        throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.b(f2);
        }
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.f2439e == null) {
            return;
        }
        if (truncateAt != TextUtils.TruncateAt.MARQUEE) {
            super.setEllipsize(truncateAt);
            com.google.android.material.chip.a aVar = this.f2439e;
            if (aVar != null) {
                aVar.a(truncateAt);
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        this.n = z;
        a(this.p);
    }

    @Override // android.widget.TextView
    public void setGravity(int i) {
        if (i != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i);
        }
    }

    public void setHideMotionSpec(h hVar) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.a(hVar);
        }
    }

    public void setHideMotionSpecResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.v(i);
        }
    }

    public void setIconEndPadding(float f2) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.o(f2);
        }
    }

    public void setIconEndPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.w(i);
        }
    }

    public void setIconStartPadding(float f2) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.p(f2);
        }
    }

    public void setIconStartPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.x(i);
        }
    }

    @Override // android.view.View
    public void setLayoutDirection(int i) {
        if (this.f2439e != null && Build.VERSION.SDK_INT >= 17) {
            super.setLayoutDirection(i);
        }
    }

    @Override // android.widget.TextView
    public void setLines(int i) {
        if (i <= 1) {
            super.setLines(i);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i) {
        if (i <= 1) {
            super.setMaxLines(i);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    @Override // android.widget.TextView
    public void setMaxWidth(int i) {
        super.setMaxWidth(i);
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.y(i);
        }
    }

    @Override // android.widget.TextView
    public void setMinLines(int i) {
        if (i <= 1) {
            super.setMinLines(i);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnCheckedChangeListenerInternal(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.i = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.h = onClickListener;
        l();
    }

    public void setRippleColor(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.g(colorStateList);
        }
        if (this.f2439e.W()) {
            return;
        }
        n();
    }

    public void setRippleColorResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.z(i);
            if (this.f2439e.W()) {
                return;
            }
            n();
        }
    }

    @Override // c.a.a.a.b0.n
    public void setShapeAppearanceModel(k kVar) {
        this.f2439e.setShapeAppearanceModel(kVar);
    }

    public void setShowMotionSpec(h hVar) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.b(hVar);
        }
    }

    public void setShowMotionSpecResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.A(i);
        }
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean z) {
        if (z) {
            super.setSingleLine(z);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        if (this.f2439e == null) {
            return;
        }
        if (charSequence == null) {
            charSequence = "";
        }
        super.setText(this.f2439e.b0() ? null : charSequence, bufferType);
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.b(charSequence);
        }
    }

    public void setTextAppearance(d dVar) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.a(dVar);
        }
        p();
    }

    public void setTextAppearanceResource(int i) {
        setTextAppearance(getContext(), i);
    }

    public void setTextEndPadding(float f2) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.q(f2);
        }
    }

    public void setTextEndPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.C(i);
        }
    }

    public void setTextStartPadding(float f2) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.r(f2);
        }
    }

    public void setTextStartPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.D(i);
        }
    }

    public Chip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.a.a.a.b.chipStyle);
    }

    private void b(com.google.android.material.chip.a aVar) {
        if (aVar != null) {
            aVar.a((a.InterfaceC0081a) null);
        }
    }

    public boolean c() {
        com.google.android.material.chip.a aVar = this.f2439e;
        return aVar != null && aVar.Z();
    }

    public boolean d() {
        boolean z = false;
        playSoundEffect(0);
        View.OnClickListener onClickListener = this.h;
        if (onClickListener != null) {
            onClickListener.onClick(this);
            z = true;
        }
        this.q.a(1, 1);
        return z;
    }

    public boolean e() {
        return this.n;
    }

    public void setCloseIconVisible(boolean z) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.d(z);
        }
        l();
    }

    public Chip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.r = new Rect();
        this.s = new RectF();
        this.t = new a();
        a(attributeSet);
        com.google.android.material.chip.a a2 = com.google.android.material.chip.a.a(context, attributeSet, i, c.a.a.a.k.Widget_MaterialComponents_Chip_Action);
        a(context, attributeSet, i);
        setChipDrawable(a2);
        a2.b(w.l(this));
        TypedArray c2 = l.c(context, attributeSet, c.a.a.a.l.Chip, i, c.a.a.a.k.Widget_MaterialComponents_Chip_Action, new int[0]);
        if (Build.VERSION.SDK_INT < 23) {
            setTextColor(c.a.a.a.y.c.a(context, c2, c.a.a.a.l.Chip_android_textColor));
        }
        boolean hasValue = c2.hasValue(c.a.a.a.l.Chip_shapeAppearance);
        c2.recycle();
        this.q = new c(this);
        l();
        if (!hasValue) {
            j();
        }
        setChecked(this.j);
        setText(a2.S());
        setEllipsize(a2.M());
        setIncludeFontPadding(false);
        p();
        if (!this.f2439e.b0()) {
            setSingleLine();
        }
        setGravity(8388627);
        o();
        if (e()) {
            setMinHeight(this.p);
        }
        this.o = w.q(this);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        if (attributeSet == null) {
            return;
        }
        TypedArray c2 = l.c(context, attributeSet, c.a.a.a.l.Chip, i, c.a.a.a.k.Widget_MaterialComponents_Chip_Action, new int[0]);
        this.n = c2.getBoolean(c.a.a.a.l.Chip_ensureMinTouchTargetSize, false);
        this.p = (int) Math.ceil(c2.getDimension(c.a.a.a.l.Chip_chipMinTouchTargetSize, (float) Math.ceil(m.a(getContext(), 48))));
        c2.recycle();
    }

    public boolean b() {
        com.google.android.material.chip.a aVar = this.f2439e;
        return aVar != null && aVar.X();
    }

    public void setCheckedIconVisible(boolean z) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.b(z);
        }
    }

    public void setChipIconVisible(boolean z) {
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.c(z);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 == null) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
            return;
        }
        throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        }
        if (drawable3 == null) {
            super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
            return;
        }
        throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.B(i);
        }
        p();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i) {
        super.setTextAppearance(i);
        com.google.android.material.chip.a aVar = this.f2439e;
        if (aVar != null) {
            aVar.B(i);
        }
        p();
    }

    private void a(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "background") != null) {
            Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
        }
        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableLeft") == null) {
            if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableStart") == null) {
                if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableEnd") == null) {
                    if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableRight") == null) {
                        if (attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/android", "singleLine", true) && attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "lines", 1) == 1 && attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "minLines", 1) == 1 && attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLines", 1) == 1) {
                            if (attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "gravity", 8388627) != 8388627) {
                                Log.w("Chip", "Chip text must be vertically center and start aligned");
                                return;
                            }
                            return;
                        }
                        throw new UnsupportedOperationException("Chip does not support multi-line text");
                    }
                    throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
                }
                throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            }
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
    }

    private void a(com.google.android.material.chip.a aVar) {
        aVar.a(this);
    }

    @Override // com.google.android.material.chip.a.InterfaceC0081a
    public void a() {
        a(this.p);
        m();
        requestLayout();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    @SuppressLint({"PrivateApi"})
    private boolean a(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 10) {
            try {
                Field declaredField = a.h.b.a.class.getDeclaredField("j");
                declaredField.setAccessible(true);
                if (((Integer) declaredField.get(this.q)).intValue() != Integer.MIN_VALUE) {
                    Method declaredMethod = a.h.b.a.class.getDeclaredMethod("i", Integer.TYPE);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(this.q, Integer.valueOf(RecyclerView.UNDEFINED_DURATION));
                    return true;
                }
            } catch (IllegalAccessException e2) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e2);
            } catch (NoSuchFieldException e3) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e3);
            } catch (NoSuchMethodException e4) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e4);
            } catch (InvocationTargetException e5) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e5);
            }
        }
        return false;
    }

    public boolean a(int i) {
        this.p = i;
        if (!e()) {
            k();
            return false;
        }
        int max = Math.max(0, i - this.f2439e.getIntrinsicHeight());
        int max2 = Math.max(0, i - this.f2439e.getIntrinsicWidth());
        if (max2 <= 0 && max <= 0) {
            k();
            return false;
        }
        int i2 = max2 > 0 ? max2 / 2 : 0;
        int i3 = max > 0 ? max / 2 : 0;
        if (this.f2440f != null) {
            Rect rect = new Rect();
            this.f2440f.getPadding(rect);
            if (rect.top == i3 && rect.bottom == i3 && rect.left == i2 && rect.right == i2) {
                return true;
            }
        }
        if (Build.VERSION.SDK_INT >= 16) {
            if (getMinHeight() != i) {
                setMinHeight(i);
            }
            if (getMinWidth() != i) {
                setMinWidth(i);
            }
        } else {
            setMinHeight(i);
            setMinWidth(i);
        }
        a(i2, i3, i2, i3);
        return true;
    }

    private void a(int i, int i2, int i3, int i4) {
        this.f2440f = new InsetDrawable((Drawable) this.f2439e, i, i2, i3, i4);
    }
}
