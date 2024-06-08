package c.a.a.a.p;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.FrameLayout;
import androidx.cardview.widget.CardView;
import c.a.a.a.b0.k;
import c.a.a.a.b0.n;

/* loaded from: classes.dex */
public class a extends CardView implements Checkable, n {
    private static final int[] p = {R.attr.state_checkable};
    private static final int[] q = {R.attr.state_checked};
    private static final int[] r = {c.a.a.a.b.state_dragged};
    private final b k;
    private final boolean l;
    private boolean m;
    private boolean n;
    private InterfaceC0068a o;

    /* renamed from: c.a.a.a.p.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0068a {
        void a(a aVar, boolean z);
    }

    private void e() {
        if (Build.VERSION.SDK_INT <= 26) {
            return;
        }
        this.k.a();
        throw null;
    }

    public boolean c() {
        b bVar = this.k;
        if (bVar == null) {
            return false;
        }
        bVar.o();
        throw null;
    }

    public boolean d() {
        return this.n;
    }

    @Override // androidx.cardview.widget.CardView
    public ColorStateList getCardBackgroundColor() {
        this.k.c();
        throw null;
    }

    float getCardViewRadius() {
        return super.getRadius();
    }

    public Drawable getCheckedIcon() {
        this.k.d();
        throw null;
    }

    public ColorStateList getCheckedIconTint() {
        this.k.e();
        throw null;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingBottom() {
        this.k.m();
        throw null;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingLeft() {
        this.k.m();
        throw null;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingRight() {
        this.k.m();
        throw null;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingTop() {
        this.k.m();
        throw null;
    }

    public float getProgress() {
        this.k.g();
        throw null;
    }

    @Override // androidx.cardview.widget.CardView
    public float getRadius() {
        this.k.f();
        throw null;
    }

    public ColorStateList getRippleColor() {
        this.k.h();
        throw null;
    }

    public k getShapeAppearanceModel() {
        this.k.i();
        throw null;
    }

    @Deprecated
    public int getStrokeColor() {
        this.k.j();
        throw null;
    }

    public ColorStateList getStrokeColorStateList() {
        this.k.k();
        throw null;
    }

    public int getStrokeWidth() {
        this.k.l();
        throw null;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.m;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.k.b();
        throw null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 3);
        if (c()) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, p);
        }
        if (isChecked()) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, q);
        }
        if (d()) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, r);
        }
        return onCreateDrawableState;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(CardView.class.getName());
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(CardView.class.getName());
        accessibilityNodeInfo.setCheckable(c());
        accessibilityNodeInfo.setClickable(isClickable());
        accessibilityNodeInfo.setChecked(isChecked());
    }

    @Override // androidx.cardview.widget.CardView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.k.a(getMeasuredWidth(), getMeasuredHeight());
        throw null;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (this.l) {
            this.k.n();
            throw null;
        }
    }

    void setBackgroundInternal(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(int i) {
        this.k.a(ColorStateList.valueOf(i));
        throw null;
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardElevation(float f2) {
        super.setCardElevation(f2);
        this.k.q();
        throw null;
    }

    public void setCheckable(boolean z) {
        this.k.a(z);
        throw null;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.m != z) {
            toggle();
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        this.k.a(drawable);
        throw null;
    }

    public void setCheckedIconResource(int i) {
        this.k.a(a.a.k.a.a.c(getContext(), i));
        throw null;
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        this.k.b(colorStateList);
        throw null;
    }

    @Override // android.view.View
    public void setClickable(boolean z) {
        super.setClickable(z);
        this.k.p();
        throw null;
    }

    public void setDragged(boolean z) {
        if (this.n != z) {
            this.n = z;
            refreshDrawableState();
            e();
            invalidate();
        }
    }

    @Override // androidx.cardview.widget.CardView
    public void setMaxCardElevation(float f2) {
        super.setMaxCardElevation(f2);
        this.k.r();
        throw null;
    }

    public void setOnCheckedChangeListener(InterfaceC0068a interfaceC0068a) {
        this.o = interfaceC0068a;
    }

    @Override // androidx.cardview.widget.CardView
    public void setPreventCornerOverlap(boolean z) {
        super.setPreventCornerOverlap(z);
        this.k.r();
        throw null;
    }

    public void setProgress(float f2) {
        this.k.b(f2);
        throw null;
    }

    @Override // androidx.cardview.widget.CardView
    public void setRadius(float f2) {
        super.setRadius(f2);
        this.k.a(f2);
        throw null;
    }

    public void setRippleColor(ColorStateList colorStateList) {
        this.k.c(colorStateList);
        throw null;
    }

    public void setRippleColorResource(int i) {
        this.k.c(a.a.k.a.a.b(getContext(), i));
        throw null;
    }

    @Override // c.a.a.a.b0.n
    public void setShapeAppearanceModel(k kVar) {
        this.k.a(kVar);
        throw null;
    }

    public void setStrokeColor(int i) {
        this.k.d(ColorStateList.valueOf(i));
        throw null;
    }

    public void setStrokeWidth(int i) {
        this.k.a(i);
        throw null;
    }

    @Override // androidx.cardview.widget.CardView
    public void setUseCompatPadding(boolean z) {
        super.setUseCompatPadding(z);
        this.k.r();
        throw null;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        if (c() && isEnabled()) {
            this.m = !this.m;
            refreshDrawableState();
            e();
            InterfaceC0068a interfaceC0068a = this.o;
            if (interfaceC0068a != null) {
                interfaceC0068a.a(this, this.m);
            }
        }
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(ColorStateList colorStateList) {
        this.k.a(colorStateList);
        throw null;
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        this.k.d(colorStateList);
        throw null;
    }
}
