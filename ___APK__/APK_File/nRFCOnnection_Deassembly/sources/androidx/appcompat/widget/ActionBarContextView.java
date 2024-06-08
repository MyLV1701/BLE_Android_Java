package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class ActionBarContextView extends androidx.appcompat.widget.a {
    private CharSequence j;
    private CharSequence k;
    private View l;
    private View m;
    private LinearLayout n;
    private TextView o;
    private TextView p;
    private int q;
    private int r;
    private boolean s;
    private int t;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements View.OnClickListener {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ a.a.o.b f842b;

        a(ActionBarContextView actionBarContextView, a.a.o.b bVar) {
            this.f842b = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f842b.a();
        }
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    private void e() {
        if (this.n == null) {
            LayoutInflater.from(getContext()).inflate(a.a.g.abc_action_bar_title_item, this);
            this.n = (LinearLayout) getChildAt(getChildCount() - 1);
            this.o = (TextView) this.n.findViewById(a.a.f.action_bar_title);
            this.p = (TextView) this.n.findViewById(a.a.f.action_bar_subtitle);
            if (this.q != 0) {
                this.o.setTextAppearance(getContext(), this.q);
            }
            if (this.r != 0) {
                this.p.setTextAppearance(getContext(), this.r);
            }
        }
        this.o.setText(this.j);
        this.p.setText(this.k);
        boolean z = !TextUtils.isEmpty(this.j);
        boolean z2 = !TextUtils.isEmpty(this.k);
        int i = 0;
        this.p.setVisibility(z2 ? 0 : 8);
        LinearLayout linearLayout = this.n;
        if (!z && !z2) {
            i = 8;
        }
        linearLayout.setVisibility(i);
        if (this.n.getParent() == null) {
            addView(this.n);
        }
    }

    public boolean b() {
        return this.s;
    }

    public void c() {
        removeAllViews();
        this.m = null;
        this.f932d = null;
    }

    public boolean d() {
        c cVar = this.f933e;
        if (cVar != null) {
            return cVar.k();
        }
        return false;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.a
    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    @Override // androidx.appcompat.widget.a
    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.k;
    }

    public CharSequence getTitle() {
        return this.j;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c cVar = this.f933e;
        if (cVar != null) {
            cVar.g();
            this.f933e.h();
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 32) {
            accessibilityEvent.setSource(this);
            accessibilityEvent.setClassName(ActionBarContextView.class.getName());
            accessibilityEvent.setPackageName(getContext().getPackageName());
            accessibilityEvent.setContentDescription(this.j);
            return;
        }
        super.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        boolean a2 = z0.a(this);
        int paddingRight = a2 ? (i3 - i) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        View view = this.l;
        if (view == null || view.getVisibility() == 8) {
            i5 = paddingRight;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.l.getLayoutParams();
            int i6 = a2 ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i7 = a2 ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int a3 = androidx.appcompat.widget.a.a(paddingRight, i6, a2);
            i5 = androidx.appcompat.widget.a.a(a3 + a(this.l, a3, paddingTop, paddingTop2, a2), i7, a2);
        }
        LinearLayout linearLayout = this.n;
        if (linearLayout != null && this.m == null && linearLayout.getVisibility() != 8) {
            i5 += a(this.n, i5, paddingTop, paddingTop2, a2);
        }
        int i8 = i5;
        View view2 = this.m;
        if (view2 != null) {
            a(view2, i8, paddingTop, paddingTop2, a2);
        }
        int paddingLeft = a2 ? getPaddingLeft() : (i3 - i) - getPaddingRight();
        ActionMenuView actionMenuView = this.f932d;
        if (actionMenuView != null) {
            a(actionMenuView, paddingLeft, paddingTop, paddingTop2, !a2);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i) == 1073741824) {
            if (View.MeasureSpec.getMode(i2) != 0) {
                int size = View.MeasureSpec.getSize(i);
                int i3 = this.f934f;
                if (i3 <= 0) {
                    i3 = View.MeasureSpec.getSize(i2);
                }
                int paddingTop = getPaddingTop() + getPaddingBottom();
                int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
                int i4 = i3 - paddingTop;
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i4, RecyclerView.UNDEFINED_DURATION);
                View view = this.l;
                if (view != null) {
                    int a2 = a(view, paddingLeft, makeMeasureSpec, 0);
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.l.getLayoutParams();
                    paddingLeft = a2 - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
                }
                ActionMenuView actionMenuView = this.f932d;
                if (actionMenuView != null && actionMenuView.getParent() == this) {
                    paddingLeft = a(this.f932d, paddingLeft, makeMeasureSpec, 0);
                }
                LinearLayout linearLayout = this.n;
                if (linearLayout != null && this.m == null) {
                    if (this.s) {
                        this.n.measure(View.MeasureSpec.makeMeasureSpec(0, 0), makeMeasureSpec);
                        int measuredWidth = this.n.getMeasuredWidth();
                        boolean z = measuredWidth <= paddingLeft;
                        if (z) {
                            paddingLeft -= measuredWidth;
                        }
                        this.n.setVisibility(z ? 0 : 8);
                    } else {
                        paddingLeft = a(linearLayout, paddingLeft, makeMeasureSpec, 0);
                    }
                }
                View view2 = this.m;
                if (view2 != null) {
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    int i5 = layoutParams.width != -2 ? 1073741824 : RecyclerView.UNDEFINED_DURATION;
                    int i6 = layoutParams.width;
                    if (i6 >= 0) {
                        paddingLeft = Math.min(i6, paddingLeft);
                    }
                    int i7 = layoutParams.height == -2 ? RecyclerView.UNDEFINED_DURATION : 1073741824;
                    int i8 = layoutParams.height;
                    if (i8 >= 0) {
                        i4 = Math.min(i8, i4);
                    }
                    this.m.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i5), View.MeasureSpec.makeMeasureSpec(i4, i7));
                }
                if (this.f934f <= 0) {
                    int childCount = getChildCount();
                    int i9 = 0;
                    for (int i10 = 0; i10 < childCount; i10++) {
                        int measuredHeight = getChildAt(i10).getMeasuredHeight() + paddingTop;
                        if (measuredHeight > i9) {
                            i9 = measuredHeight;
                        }
                    }
                    setMeasuredDimension(size, i9);
                    return;
                }
                setMeasuredDimension(size, i3);
                return;
            }
            throw new IllegalStateException(ActionBarContextView.class.getSimpleName() + " can only be used with android:layout_height=\"wrap_content\"");
        }
        throw new IllegalStateException(ActionBarContextView.class.getSimpleName() + " can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
    }

    @Override // androidx.appcompat.widget.a
    public void setContentHeight(int i) {
        this.f934f = i;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.m;
        if (view2 != null) {
            removeView(view2);
        }
        this.m = view;
        if (view != null && (linearLayout = this.n) != null) {
            removeView(linearLayout);
            this.n = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.k = charSequence;
        e();
    }

    public void setTitle(CharSequence charSequence) {
        this.j = charSequence;
        e();
    }

    public void setTitleOptional(boolean z) {
        if (z != this.s) {
            requestLayout();
        }
        this.s = z;
    }

    @Override // androidx.appcompat.widget.a, android.view.View
    public /* bridge */ /* synthetic */ void setVisibility(int i) {
        super.setVisibility(i);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.a.a.actionModeStyle);
    }

    public void a(a.a.o.b bVar) {
        View view = this.l;
        if (view == null) {
            this.l = LayoutInflater.from(getContext()).inflate(this.t, (ViewGroup) this, false);
            addView(this.l);
        } else if (view.getParent() == null) {
            addView(this.l);
        }
        this.l.findViewById(a.a.f.action_mode_close_button).setOnClickListener(new a(this, bVar));
        androidx.appcompat.view.menu.g gVar = (androidx.appcompat.view.menu.g) bVar.c();
        c cVar = this.f933e;
        if (cVar != null) {
            cVar.e();
        }
        this.f933e = new c(getContext());
        this.f933e.c(true);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        gVar.a(this.f933e, this.f931c);
        this.f932d = (ActionMenuView) this.f933e.b(this);
        a.f.l.w.a(this.f932d, (Drawable) null);
        addView(this.f932d, layoutParams);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        t0 a2 = t0.a(context, attributeSet, a.a.j.ActionMode, i, 0);
        a.f.l.w.a(this, a2.b(a.a.j.ActionMode_background));
        this.q = a2.g(a.a.j.ActionMode_titleTextStyle, 0);
        this.r = a2.g(a.a.j.ActionMode_subtitleTextStyle, 0);
        this.f934f = a2.f(a.a.j.ActionMode_height, 0);
        this.t = a2.g(a.a.j.ActionMode_closeItemLayout, a.a.g.abc_action_mode_close_item_material);
        a2.a();
    }

    public void a() {
        if (this.l == null) {
            c();
        }
    }
}
