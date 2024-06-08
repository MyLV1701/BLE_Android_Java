package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/* loaded from: classes.dex */
public class e0 extends ViewGroup {

    /* renamed from: b, reason: collision with root package name */
    private boolean f972b;

    /* renamed from: c, reason: collision with root package name */
    private int f973c;

    /* renamed from: d, reason: collision with root package name */
    private int f974d;

    /* renamed from: e, reason: collision with root package name */
    private int f975e;

    /* renamed from: f, reason: collision with root package name */
    private int f976f;
    private int g;
    private float h;
    private boolean i;
    private int[] j;
    private int[] k;
    private Drawable l;
    private int m;
    private int n;
    private int o;
    private int p;

    public e0(Context context) {
        this(context, null);
    }

    private void c(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View a2 = a(i3);
            if (a2.getVisibility() != 8) {
                a aVar = (a) a2.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) aVar).height == -1) {
                    int i4 = ((ViewGroup.MarginLayoutParams) aVar).width;
                    ((ViewGroup.MarginLayoutParams) aVar).width = a2.getMeasuredWidth();
                    measureChildWithMargins(a2, i2, 0, makeMeasureSpec, 0);
                    ((ViewGroup.MarginLayoutParams) aVar).width = i4;
                }
            }
        }
    }

    private void d(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View a2 = a(i3);
            if (a2.getVisibility() != 8) {
                a aVar = (a) a2.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) aVar).width == -1) {
                    int i4 = ((ViewGroup.MarginLayoutParams) aVar).height;
                    ((ViewGroup.MarginLayoutParams) aVar).height = a2.getMeasuredHeight();
                    measureChildWithMargins(a2, makeMeasureSpec, 0, i2, 0);
                    ((ViewGroup.MarginLayoutParams) aVar).height = i4;
                }
            }
        }
    }

    int a(View view) {
        return 0;
    }

    int a(View view, int i) {
        return 0;
    }

    void a(Canvas canvas) {
        int right;
        int left;
        int i;
        int left2;
        int virtualChildCount = getVirtualChildCount();
        boolean a2 = z0.a(this);
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View a3 = a(i2);
            if (a3 != null && a3.getVisibility() != 8 && b(i2)) {
                a aVar = (a) a3.getLayoutParams();
                if (a2) {
                    left2 = a3.getRight() + ((ViewGroup.MarginLayoutParams) aVar).rightMargin;
                } else {
                    left2 = (a3.getLeft() - ((ViewGroup.MarginLayoutParams) aVar).leftMargin) - this.m;
                }
                b(canvas, left2);
            }
        }
        if (b(virtualChildCount)) {
            View a4 = a(virtualChildCount - 1);
            if (a4 != null) {
                a aVar2 = (a) a4.getLayoutParams();
                if (a2) {
                    left = a4.getLeft() - ((ViewGroup.MarginLayoutParams) aVar2).leftMargin;
                    i = this.m;
                    right = left - i;
                } else {
                    right = a4.getRight() + ((ViewGroup.MarginLayoutParams) aVar2).rightMargin;
                }
            } else if (a2) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i = this.m;
                right = left - i;
            }
            b(canvas, right);
        }
    }

    int b(View view) {
        return 0;
    }

    void b(Canvas canvas) {
        int bottom;
        int virtualChildCount = getVirtualChildCount();
        for (int i = 0; i < virtualChildCount; i++) {
            View a2 = a(i);
            if (a2 != null && a2.getVisibility() != 8 && b(i)) {
                a(canvas, (a2.getTop() - ((ViewGroup.MarginLayoutParams) ((a) a2.getLayoutParams())).topMargin) - this.n);
            }
        }
        if (b(virtualChildCount)) {
            View a3 = a(virtualChildCount - 1);
            if (a3 == null) {
                bottom = (getHeight() - getPaddingBottom()) - this.n;
            } else {
                bottom = a3.getBottom() + ((ViewGroup.MarginLayoutParams) ((a) a3.getLayoutParams())).bottomMargin;
            }
            a(canvas, bottom);
        }
    }

    int c(int i) {
        return 0;
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    @Override // android.view.View
    public int getBaseline() {
        int i;
        if (this.f973c < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.f973c;
        if (childCount > i2) {
            View childAt = getChildAt(i2);
            int baseline = childAt.getBaseline();
            if (baseline == -1) {
                if (this.f973c == 0) {
                    return -1;
                }
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
            int i3 = this.f974d;
            if (this.f975e == 1 && (i = this.f976f & 112) != 48) {
                if (i == 16) {
                    i3 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.g) / 2;
                } else if (i == 80) {
                    i3 = ((getBottom() - getTop()) - getPaddingBottom()) - this.g;
                }
            }
            return i3 + ((ViewGroup.MarginLayoutParams) ((a) childAt.getLayoutParams())).topMargin + baseline;
        }
        throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
    }

    public int getBaselineAlignedChildIndex() {
        return this.f973c;
    }

    public Drawable getDividerDrawable() {
        return this.l;
    }

    public int getDividerPadding() {
        return this.p;
    }

    public int getDividerWidth() {
        return this.m;
    }

    public int getGravity() {
        return this.f976f;
    }

    public int getOrientation() {
        return this.f975e;
    }

    public int getShowDividers() {
        return this.o;
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.h;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.l == null) {
            return;
        }
        if (this.f975e == 1) {
            b(canvas);
        } else {
            a(canvas);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.f975e == 1) {
            b(i, i2, i3, i4);
        } else {
            a(i, i2, i3, i4);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        if (this.f975e == 1) {
            b(i, i2);
        } else {
            a(i, i2);
        }
    }

    public void setBaselineAligned(boolean z) {
        this.f972b = z;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i >= 0 && i < getChildCount()) {
            this.f973c = i;
            return;
        }
        throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.l) {
            return;
        }
        this.l = drawable;
        if (drawable != null) {
            this.m = drawable.getIntrinsicWidth();
            this.n = drawable.getIntrinsicHeight();
        } else {
            this.m = 0;
            this.n = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i) {
        this.p = i;
    }

    public void setGravity(int i) {
        if (this.f976f != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.f976f = i;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & 8388615;
        int i3 = this.f976f;
        if ((8388615 & i3) != i2) {
            this.f976f = i2 | ((-8388616) & i3);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.i = z;
    }

    public void setOrientation(int i) {
        if (this.f975e != i) {
            this.f975e = i;
            requestLayout();
        }
    }

    public void setShowDividers(int i) {
        if (i != this.o) {
            requestLayout();
        }
        this.o = i;
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        int i3 = this.f976f;
        if ((i3 & 112) != i2) {
            this.f976f = i2 | (i3 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f2) {
        this.h = Math.max(0.0f, f2);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public e0(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public a generateDefaultLayoutParams() {
        int i = this.f975e;
        if (i == 0) {
            return new a(-2, -2);
        }
        if (i == 1) {
            return new a(-1, -2);
        }
        return null;
    }

    public e0(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f972b = true;
        this.f973c = -1;
        this.f974d = 0;
        this.f976f = 8388659;
        t0 a2 = t0.a(context, attributeSet, a.a.j.LinearLayoutCompat, i, 0);
        int d2 = a2.d(a.a.j.LinearLayoutCompat_android_orientation, -1);
        if (d2 >= 0) {
            setOrientation(d2);
        }
        int d3 = a2.d(a.a.j.LinearLayoutCompat_android_gravity, -1);
        if (d3 >= 0) {
            setGravity(d3);
        }
        boolean a3 = a2.a(a.a.j.LinearLayoutCompat_android_baselineAligned, true);
        if (!a3) {
            setBaselineAligned(a3);
        }
        this.h = a2.b(a.a.j.LinearLayoutCompat_android_weightSum, -1.0f);
        this.f973c = a2.d(a.a.j.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.i = a2.a(a.a.j.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(a2.b(a.a.j.LinearLayoutCompat_divider));
        this.o = a2.d(a.a.j.LinearLayoutCompat_showDividers, 0);
        this.p = a2.c(a.a.j.LinearLayoutCompat_dividerPadding, 0);
        a2.a();
    }

    @Override // android.view.ViewGroup
    public a generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public a generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new a(layoutParams);
    }

    /* loaded from: classes.dex */
    public static class a extends ViewGroup.MarginLayoutParams {

        /* renamed from: a, reason: collision with root package name */
        public float f977a;

        /* renamed from: b, reason: collision with root package name */
        public int f978b;

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f978b = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.a.j.LinearLayoutCompat_Layout);
            this.f977a = obtainStyledAttributes.getFloat(a.a.j.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.f978b = obtainStyledAttributes.getInt(a.a.j.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            obtainStyledAttributes.recycle();
        }

        public a(int i, int i2) {
            super(i, i2);
            this.f978b = -1;
            this.f977a = 0.0f;
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f978b = -1;
        }
    }

    void b(Canvas canvas, int i) {
        this.l.setBounds(i, getPaddingTop() + this.p, this.m + i, (getHeight() - getPaddingBottom()) - this.p);
        this.l.draw(canvas);
    }

    void a(Canvas canvas, int i) {
        this.l.setBounds(getPaddingLeft() + this.p, i, (getWidth() - getPaddingRight()) - this.p, this.n + i);
        this.l.draw(canvas);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean b(int i) {
        if (i == 0) {
            return (this.o & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.o & 4) != 0;
        }
        if ((this.o & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    View a(int i) {
        return getChildAt(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01d5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void a(int r39, int r40) {
        /*
            Method dump skipped, instructions count: 1300
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.e0.a(int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:158:0x032e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void b(int r34, int r35) {
        /*
            Method dump skipped, instructions count: 921
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.e0.b(int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void b(int r18, int r19, int r20, int r21) {
        /*
            r17 = this;
            r6 = r17
            int r7 = r17.getPaddingLeft()
            int r0 = r20 - r18
            int r1 = r17.getPaddingRight()
            int r8 = r0 - r1
            int r0 = r0 - r7
            int r1 = r17.getPaddingRight()
            int r9 = r0 - r1
            int r10 = r17.getVirtualChildCount()
            int r0 = r6.f976f
            r1 = r0 & 112(0x70, float:1.57E-43)
            r2 = 8388615(0x800007, float:1.1754953E-38)
            r11 = r0 & r2
            r0 = 16
            if (r1 == r0) goto L3b
            r0 = 80
            if (r1 == r0) goto L2f
            int r0 = r17.getPaddingTop()
            goto L47
        L2f:
            int r0 = r17.getPaddingTop()
            int r0 = r0 + r21
            int r0 = r0 - r19
            int r1 = r6.g
            int r0 = r0 - r1
            goto L47
        L3b:
            int r0 = r17.getPaddingTop()
            int r1 = r21 - r19
            int r2 = r6.g
            int r1 = r1 - r2
            int r1 = r1 / 2
            int r0 = r0 + r1
        L47:
            r1 = 0
            r12 = 0
        L49:
            if (r12 >= r10) goto Lc9
            android.view.View r13 = r6.a(r12)
            r14 = 1
            if (r13 != 0) goto L59
            int r1 = r6.c(r12)
            int r0 = r0 + r1
            goto Lc6
        L59:
            int r1 = r13.getVisibility()
            r2 = 8
            if (r1 == r2) goto Lc6
            int r4 = r13.getMeasuredWidth()
            int r15 = r13.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r1 = r13.getLayoutParams()
            r5 = r1
            androidx.appcompat.widget.e0$a r5 = (androidx.appcompat.widget.e0.a) r5
            int r1 = r5.f978b
            if (r1 >= 0) goto L75
            r1 = r11
        L75:
            int r2 = a.f.l.w.q(r17)
            int r1 = a.f.l.d.a(r1, r2)
            r1 = r1 & 7
            if (r1 == r14) goto L8e
            r2 = 5
            if (r1 == r2) goto L89
            int r1 = r5.leftMargin
            int r1 = r1 + r7
        L87:
            r2 = r1
            goto L9a
        L89:
            int r1 = r8 - r4
            int r2 = r5.rightMargin
            goto L98
        L8e:
            int r1 = r9 - r4
            int r1 = r1 / 2
            int r1 = r1 + r7
            int r2 = r5.leftMargin
            int r1 = r1 + r2
            int r2 = r5.rightMargin
        L98:
            int r1 = r1 - r2
            goto L87
        L9a:
            boolean r1 = r6.b(r12)
            if (r1 == 0) goto La3
            int r1 = r6.n
            int r0 = r0 + r1
        La3:
            int r1 = r5.topMargin
            int r16 = r0 + r1
            int r0 = r6.a(r13)
            int r3 = r16 + r0
            r0 = r17
            r1 = r13
            r14 = r5
            r5 = r15
            r0.a(r1, r2, r3, r4, r5)
            int r0 = r14.bottomMargin
            int r15 = r15 + r0
            int r0 = r6.b(r13)
            int r15 = r15 + r0
            int r16 = r16 + r15
            int r0 = r6.a(r13, r12)
            int r12 = r12 + r0
            r0 = r16
        Lc6:
            r1 = 1
            int r12 = r12 + r1
            goto L49
        Lc9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.e0.b(int, int, int, int):void");
    }

    void a(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00e4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void a(int r25, int r26, int r27, int r28) {
        /*
            Method dump skipped, instructions count: 321
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.e0.a(int, int, int, int):void");
    }

    private void a(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }
}
