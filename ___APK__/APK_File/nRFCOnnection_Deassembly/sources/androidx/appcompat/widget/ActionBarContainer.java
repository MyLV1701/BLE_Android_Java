package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {

    /* renamed from: b, reason: collision with root package name */
    private boolean f837b;

    /* renamed from: c, reason: collision with root package name */
    private View f838c;

    /* renamed from: d, reason: collision with root package name */
    private View f839d;

    /* renamed from: e, reason: collision with root package name */
    private View f840e;

    /* renamed from: f, reason: collision with root package name */
    Drawable f841f;
    Drawable g;
    Drawable h;
    boolean i;
    boolean j;
    private int k;

    public ActionBarContainer(Context context) {
        this(context, null);
    }

    private int a(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    private boolean b(View view) {
        return view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f841f;
        if (drawable != null && drawable.isStateful()) {
            this.f841f.setState(getDrawableState());
        }
        Drawable drawable2 = this.g;
        if (drawable2 != null && drawable2.isStateful()) {
            this.g.setState(getDrawableState());
        }
        Drawable drawable3 = this.h;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.h.setState(getDrawableState());
    }

    public View getTabContainer() {
        return this.f838c;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f841f;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.g;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.h;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f839d = findViewById(a.a.f.action_bar);
        this.f840e = findViewById(a.a.f.action_context_bar);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f837b || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Drawable drawable;
        super.onLayout(z, i, i2, i3, i4);
        View view = this.f838c;
        boolean z2 = true;
        boolean z3 = false;
        boolean z4 = (view == null || view.getVisibility() == 8) ? false : true;
        if (view != null && view.getVisibility() != 8) {
            int measuredHeight = getMeasuredHeight();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            view.layout(i, (measuredHeight - view.getMeasuredHeight()) - layoutParams.bottomMargin, i3, measuredHeight - layoutParams.bottomMargin);
        }
        if (this.i) {
            Drawable drawable2 = this.h;
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                z2 = false;
            }
        } else {
            if (this.f841f != null) {
                if (this.f839d.getVisibility() == 0) {
                    this.f841f.setBounds(this.f839d.getLeft(), this.f839d.getTop(), this.f839d.getRight(), this.f839d.getBottom());
                } else {
                    View view2 = this.f840e;
                    if (view2 != null && view2.getVisibility() == 0) {
                        this.f841f.setBounds(this.f840e.getLeft(), this.f840e.getTop(), this.f840e.getRight(), this.f840e.getBottom());
                    } else {
                        this.f841f.setBounds(0, 0, 0, 0);
                    }
                }
                z3 = true;
            }
            this.j = z4;
            if (!z4 || (drawable = this.g) == null) {
                z2 = z3;
            } else {
                drawable.setBounds(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
        }
        if (z2) {
            invalidate();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int a2;
        int i3;
        if (this.f839d == null && View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE && (i3 = this.k) >= 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.min(i3, View.MeasureSpec.getSize(i2)), RecyclerView.UNDEFINED_DURATION);
        }
        super.onMeasure(i, i2);
        if (this.f839d == null) {
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        View view = this.f838c;
        if (view == null || view.getVisibility() == 8 || mode == 1073741824) {
            return;
        }
        if (!b(this.f839d)) {
            a2 = a(this.f839d);
        } else {
            a2 = !b(this.f840e) ? a(this.f840e) : 0;
        }
        setMeasuredDimension(getMeasuredWidth(), Math.min(a2 + a(this.f838c), mode == Integer.MIN_VALUE ? View.MeasureSpec.getSize(i2) : Preference.DEFAULT_ORDER));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.f841f;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f841f);
        }
        this.f841f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.f839d;
            if (view != null) {
                this.f841f.setBounds(view.getLeft(), this.f839d.getTop(), this.f839d.getRight(), this.f839d.getBottom());
            }
        }
        boolean z = true;
        if (!this.i ? this.f841f != null || this.g != null : this.h != null) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.h;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.h);
        }
        this.h = drawable;
        boolean z = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.i && (drawable2 = this.h) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!this.i ? !(this.f841f != null || this.g != null) : this.h == null) {
            z = true;
        }
        setWillNotDraw(z);
        invalidate();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.g;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.g);
        }
        this.g = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.j && (drawable2 = this.g) != null) {
                drawable2.setBounds(this.f838c.getLeft(), this.f838c.getTop(), this.f838c.getRight(), this.f838c.getBottom());
            }
        }
        boolean z = true;
        if (!this.i ? this.f841f != null || this.g != null : this.h != null) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setTabContainer(m0 m0Var) {
        View view = this.f838c;
        if (view != null) {
            removeView(view);
        }
        this.f838c = m0Var;
        if (m0Var != null) {
            addView(m0Var);
            ViewGroup.LayoutParams layoutParams = m0Var.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            m0Var.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean z) {
        this.f837b = z;
        setDescendantFocusability(z ? 393216 : 262144);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.f841f;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
        Drawable drawable2 = this.g;
        if (drawable2 != null) {
            drawable2.setVisible(z, false);
        }
        Drawable drawable3 = this.h;
        if (drawable3 != null) {
            drawable3.setVisible(z, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        if (i != 0) {
            return super.startActionModeForChild(view, callback, i);
        }
        return null;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.f841f && !this.i) || (drawable == this.g && this.j) || ((drawable == this.h && this.i) || super.verifyDrawable(drawable));
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a.f.l.w.a(this, new b(this));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.a.j.ActionBar);
        this.f841f = obtainStyledAttributes.getDrawable(a.a.j.ActionBar_background);
        this.g = obtainStyledAttributes.getDrawable(a.a.j.ActionBar_backgroundStacked);
        this.k = obtainStyledAttributes.getDimensionPixelSize(a.a.j.ActionBar_height, -1);
        if (getId() == a.a.f.split_action_bar) {
            this.i = true;
            this.h = obtainStyledAttributes.getDrawable(a.a.j.ActionBar_backgroundSplit);
        }
        obtainStyledAttributes.recycle();
        boolean z = false;
        if (!this.i ? !(this.f841f != null || this.g != null) : this.h == null) {
            z = true;
        }
        setWillNotDraw(z);
    }
}
