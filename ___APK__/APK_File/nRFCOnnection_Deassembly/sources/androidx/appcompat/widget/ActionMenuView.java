package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.n;
import androidx.appcompat.widget.e0;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class ActionMenuView extends e0 implements g.b, androidx.appcompat.view.menu.o {
    private int A;
    e B;
    private androidx.appcompat.view.menu.g q;
    private Context r;
    private int s;
    private boolean t;
    private androidx.appcompat.widget.c u;
    private n.a v;
    g.a w;
    private boolean x;
    private int y;
    private int z;

    /* loaded from: classes.dex */
    public interface a {
        boolean b();

        boolean c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b implements n.a {
        b() {
        }

        @Override // androidx.appcompat.view.menu.n.a
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
        }

        @Override // androidx.appcompat.view.menu.n.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static class c extends e0.a {

        /* renamed from: c, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f851c;

        /* renamed from: d, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public int f852d;

        /* renamed from: e, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public int f853e;

        /* renamed from: f, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f854f;

        @ViewDebug.ExportedProperty
        public boolean g;
        boolean h;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public c(c cVar) {
            super(cVar);
            this.f851c = cVar.f851c;
        }

        public c(int i, int i2) {
            super(i, i2);
            this.f851c = false;
        }
    }

    /* loaded from: classes.dex */
    public interface e {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(View view, int i, int i2, int i3, int i4) {
        c cVar = (c) view.getLayoutParams();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i3) - i4, View.MeasureSpec.getMode(i3));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        boolean z = actionMenuItemView != null && actionMenuItemView.d();
        int i5 = 2;
        if (i2 <= 0 || (z && i2 < 2)) {
            i5 = 0;
        } else {
            view.measure(View.MeasureSpec.makeMeasureSpec(i2 * i, RecyclerView.UNDEFINED_DURATION), makeMeasureSpec);
            int measuredWidth = view.getMeasuredWidth();
            int i6 = measuredWidth / i;
            if (measuredWidth % i != 0) {
                i6++;
            }
            if (!z || i6 >= 2) {
                i5 = i6;
            }
        }
        cVar.f854f = !cVar.f851c && z;
        cVar.f852d = i5;
        view.measure(View.MeasureSpec.makeMeasureSpec(i * i5, 1073741824), makeMeasureSpec);
        return i5;
    }

    /* JADX WARN: Type inference failed for: r13v12 */
    /* JADX WARN: Type inference failed for: r13v13, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r13v16 */
    private void c(int i, int i2) {
        int i3;
        int i4;
        boolean z;
        int i5;
        int i6;
        int i7;
        int i8;
        ?? r13;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingTop, -2);
        int i9 = size - paddingLeft;
        int i10 = this.z;
        int i11 = i9 / i10;
        int i12 = i9 % i10;
        if (i11 == 0) {
            setMeasuredDimension(i9, 0);
            return;
        }
        int i13 = i10 + (i12 / i11);
        int childCount = getChildCount();
        int i14 = i11;
        int i15 = 0;
        int i16 = 0;
        boolean z2 = false;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        long j = 0;
        while (i15 < childCount) {
            View childAt = getChildAt(i15);
            int i20 = size2;
            if (childAt.getVisibility() != 8) {
                boolean z3 = childAt instanceof ActionMenuItemView;
                int i21 = i17 + 1;
                if (z3) {
                    int i22 = this.A;
                    i8 = i21;
                    r13 = 0;
                    childAt.setPadding(i22, 0, i22, 0);
                } else {
                    i8 = i21;
                    r13 = 0;
                }
                c cVar = (c) childAt.getLayoutParams();
                cVar.h = r13;
                cVar.f853e = r13;
                cVar.f852d = r13;
                cVar.f854f = r13;
                ((ViewGroup.MarginLayoutParams) cVar).leftMargin = r13;
                ((ViewGroup.MarginLayoutParams) cVar).rightMargin = r13;
                cVar.g = z3 && ((ActionMenuItemView) childAt).d();
                int a2 = a(childAt, i13, cVar.f851c ? 1 : i14, childMeasureSpec, paddingTop);
                int max = Math.max(i18, a2);
                if (cVar.f854f) {
                    i19++;
                }
                if (cVar.f851c) {
                    z2 = true;
                }
                i14 -= a2;
                i16 = Math.max(i16, childAt.getMeasuredHeight());
                if (a2 == 1) {
                    j |= 1 << i15;
                    i16 = i16;
                }
                i18 = max;
                i17 = i8;
            }
            i15++;
            size2 = i20;
        }
        int i23 = size2;
        boolean z4 = z2 && i17 == 2;
        boolean z5 = false;
        while (i19 > 0 && i14 > 0) {
            int i24 = Preference.DEFAULT_ORDER;
            int i25 = 0;
            int i26 = 0;
            long j2 = 0;
            while (i25 < childCount) {
                boolean z6 = z5;
                c cVar2 = (c) getChildAt(i25).getLayoutParams();
                int i27 = i16;
                if (cVar2.f854f) {
                    int i28 = cVar2.f852d;
                    if (i28 < i24) {
                        i24 = i28;
                        j2 = 1 << i25;
                        i26 = 1;
                    } else if (i28 == i24) {
                        j2 |= 1 << i25;
                        i26++;
                    }
                }
                i25++;
                i16 = i27;
                z5 = z6;
            }
            z = z5;
            i5 = i16;
            j |= j2;
            if (i26 > i14) {
                i3 = mode;
                i4 = i9;
                break;
            }
            int i29 = i24 + 1;
            int i30 = 0;
            while (i30 < childCount) {
                View childAt2 = getChildAt(i30);
                c cVar3 = (c) childAt2.getLayoutParams();
                int i31 = i9;
                int i32 = mode;
                long j3 = 1 << i30;
                if ((j2 & j3) == 0) {
                    if (cVar3.f852d == i29) {
                        j |= j3;
                    }
                    i7 = i29;
                } else {
                    if (z4 && cVar3.g && i14 == 1) {
                        int i33 = this.A;
                        i7 = i29;
                        childAt2.setPadding(i33 + i13, 0, i33, 0);
                    } else {
                        i7 = i29;
                    }
                    cVar3.f852d++;
                    cVar3.h = true;
                    i14--;
                }
                i30++;
                mode = i32;
                i29 = i7;
                i9 = i31;
            }
            i16 = i5;
            z5 = true;
        }
        i3 = mode;
        i4 = i9;
        z = z5;
        i5 = i16;
        boolean z7 = !z2 && i17 == 1;
        if (i14 <= 0 || j == 0 || (i14 >= i17 - 1 && !z7 && i18 <= 1)) {
            i6 = 0;
        } else {
            float bitCount = Long.bitCount(j);
            if (z7) {
                i6 = 0;
            } else {
                i6 = 0;
                if ((j & 1) != 0 && !((c) getChildAt(0).getLayoutParams()).g) {
                    bitCount -= 0.5f;
                }
                int i34 = childCount - 1;
                if ((j & (1 << i34)) != 0 && !((c) getChildAt(i34).getLayoutParams()).g) {
                    bitCount -= 0.5f;
                }
            }
            int i35 = bitCount > 0.0f ? (int) ((i14 * i13) / bitCount) : 0;
            for (int i36 = 0; i36 < childCount; i36++) {
                if ((j & (1 << i36)) != 0) {
                    View childAt3 = getChildAt(i36);
                    c cVar4 = (c) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        cVar4.f853e = i35;
                        cVar4.h = true;
                        if (i36 == 0 && !cVar4.g) {
                            ((ViewGroup.MarginLayoutParams) cVar4).leftMargin = (-i35) / 2;
                        }
                    } else if (cVar4.f851c) {
                        cVar4.f853e = i35;
                        cVar4.h = true;
                        ((ViewGroup.MarginLayoutParams) cVar4).rightMargin = (-i35) / 2;
                    } else {
                        if (i36 != 0) {
                            ((ViewGroup.MarginLayoutParams) cVar4).leftMargin = i35 / 2;
                        }
                        if (i36 != childCount - 1) {
                            ((ViewGroup.MarginLayoutParams) cVar4).rightMargin = i35 / 2;
                        }
                    }
                    z = true;
                }
            }
        }
        if (z) {
            while (i6 < childCount) {
                View childAt4 = getChildAt(i6);
                c cVar5 = (c) childAt4.getLayoutParams();
                if (cVar5.h) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((cVar5.f852d * i13) + cVar5.f853e, 1073741824), childMeasureSpec);
                }
                i6++;
            }
        }
        setMeasuredDimension(i4, i3 != 1073741824 ? i5 : i23);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.e0, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof c;
    }

    public void d() {
        androidx.appcompat.widget.c cVar = this.u;
        if (cVar != null) {
            cVar.e();
        }
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public c e() {
        c generateDefaultLayoutParams = generateDefaultLayoutParams();
        generateDefaultLayoutParams.f851c = true;
        return generateDefaultLayoutParams;
    }

    public boolean f() {
        androidx.appcompat.widget.c cVar = this.u;
        return cVar != null && cVar.g();
    }

    public boolean g() {
        androidx.appcompat.widget.c cVar = this.u;
        return cVar != null && cVar.i();
    }

    public Menu getMenu() {
        if (this.q == null) {
            Context context = getContext();
            this.q = new androidx.appcompat.view.menu.g(context);
            this.q.a(new d());
            this.u = new androidx.appcompat.widget.c(context);
            this.u.c(true);
            androidx.appcompat.widget.c cVar = this.u;
            n.a aVar = this.v;
            if (aVar == null) {
                aVar = new b();
            }
            cVar.a(aVar);
            this.q.a(this.u, this.r);
            this.u.a(this);
        }
        return this.q;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.u.f();
    }

    public int getPopupTheme() {
        return this.s;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public boolean h() {
        androidx.appcompat.widget.c cVar = this.u;
        return cVar != null && cVar.j();
    }

    public boolean i() {
        return this.t;
    }

    public androidx.appcompat.view.menu.g j() {
        return this.q;
    }

    public boolean k() {
        androidx.appcompat.widget.c cVar = this.u;
        return cVar != null && cVar.k();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        androidx.appcompat.widget.c cVar = this.u;
        if (cVar != null) {
            cVar.a(false);
            if (this.u.j()) {
                this.u.g();
                this.u.k();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        d();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.e0, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int width;
        int i7;
        if (!this.x) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        int childCount = getChildCount();
        int i8 = (i4 - i2) / 2;
        int dividerWidth = getDividerWidth();
        int i9 = i3 - i;
        int paddingRight = (i9 - getPaddingRight()) - getPaddingLeft();
        boolean a2 = z0.a(this);
        int i10 = paddingRight;
        int i11 = 0;
        int i12 = 0;
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.f851c) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (d(i13)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (a2) {
                        i7 = getPaddingLeft() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
                        width = i7 + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
                        i7 = width - measuredWidth;
                    }
                    int i14 = i8 - (measuredHeight / 2);
                    childAt.layout(i7, i14, width, measuredHeight + i14);
                    i10 -= measuredWidth;
                    i11 = 1;
                } else {
                    i10 -= (childAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin) + ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
                    d(i13);
                    i12++;
                }
            }
        }
        if (childCount == 1 && i11 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i15 = (i9 / 2) - (measuredWidth2 / 2);
            int i16 = i8 - (measuredHeight2 / 2);
            childAt2.layout(i15, i16, measuredWidth2 + i15, measuredHeight2 + i16);
            return;
        }
        int i17 = i12 - (i11 ^ 1);
        if (i17 > 0) {
            i6 = i10 / i17;
            i5 = 0;
        } else {
            i5 = 0;
            i6 = 0;
        }
        int max = Math.max(i5, i6);
        if (a2) {
            int width2 = getWidth() - getPaddingRight();
            while (i5 < childCount) {
                View childAt3 = getChildAt(i5);
                c cVar2 = (c) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !cVar2.f851c) {
                    int i18 = width2 - ((ViewGroup.MarginLayoutParams) cVar2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i19 = i8 - (measuredHeight3 / 2);
                    childAt3.layout(i18 - measuredWidth3, i19, i18, measuredHeight3 + i19);
                    width2 = i18 - ((measuredWidth3 + ((ViewGroup.MarginLayoutParams) cVar2).leftMargin) + max);
                }
                i5++;
            }
            return;
        }
        int paddingLeft = getPaddingLeft();
        while (i5 < childCount) {
            View childAt4 = getChildAt(i5);
            c cVar3 = (c) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !cVar3.f851c) {
                int i20 = paddingLeft + ((ViewGroup.MarginLayoutParams) cVar3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i21 = i8 - (measuredHeight4 / 2);
                childAt4.layout(i20, i21, i20 + measuredWidth4, measuredHeight4 + i21);
                paddingLeft = i20 + measuredWidth4 + ((ViewGroup.MarginLayoutParams) cVar3).rightMargin + max;
            }
            i5++;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.e0, android.view.View
    public void onMeasure(int i, int i2) {
        androidx.appcompat.view.menu.g gVar;
        boolean z = this.x;
        this.x = View.MeasureSpec.getMode(i) == 1073741824;
        if (z != this.x) {
            this.y = 0;
        }
        int size = View.MeasureSpec.getSize(i);
        if (this.x && (gVar = this.q) != null && size != this.y) {
            this.y = size;
            gVar.b(true);
        }
        int childCount = getChildCount();
        if (this.x && childCount > 0) {
            c(i, i2);
            return;
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            c cVar = (c) getChildAt(i3).getLayoutParams();
            ((ViewGroup.MarginLayoutParams) cVar).rightMargin = 0;
            ((ViewGroup.MarginLayoutParams) cVar).leftMargin = 0;
        }
        super.onMeasure(i, i2);
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.u.b(z);
    }

    public void setOnMenuItemClickListener(e eVar) {
        this.B = eVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        this.u.a(drawable);
    }

    public void setOverflowReserved(boolean z) {
        this.t = z;
    }

    public void setPopupTheme(int i) {
        if (this.s != i) {
            this.s = i;
            if (i == 0) {
                this.r = getContext();
            } else {
                this.r = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setPresenter(androidx.appcompat.widget.c cVar) {
        this.u = cVar;
        this.u.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class d implements g.a {
        d() {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            e eVar = ActionMenuView.this.B;
            return eVar != null && eVar.onMenuItemClick(menuItem);
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void a(androidx.appcompat.view.menu.g gVar) {
            g.a aVar = ActionMenuView.this.w;
            if (aVar != null) {
                aVar.a(gVar);
            }
        }
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.z = (int) (56.0f * f2);
        this.A = (int) (f2 * 4.0f);
        this.r = context;
        this.s = 0;
    }

    protected boolean d(int i) {
        boolean z = false;
        if (i == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i - 1);
        KeyEvent.Callback childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof a)) {
            z = false | ((a) childAt).b();
        }
        return (i <= 0 || !(childAt2 instanceof a)) ? z : z | ((a) childAt2).c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.e0, android.view.ViewGroup
    public c generateDefaultLayoutParams() {
        c cVar = new c(-2, -2);
        cVar.f978b = 16;
        return cVar;
    }

    @Override // androidx.appcompat.widget.e0, android.view.ViewGroup
    public c generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.e0, android.view.ViewGroup
    public c generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams != null) {
            c cVar = layoutParams instanceof c ? new c((c) layoutParams) : new c(layoutParams);
            if (cVar.f978b <= 0) {
                cVar.f978b = 16;
            }
            return cVar;
        }
        return generateDefaultLayoutParams();
    }

    @Override // androidx.appcompat.view.menu.g.b
    public boolean a(androidx.appcompat.view.menu.j jVar) {
        return this.q.a(jVar, 0);
    }

    @Override // androidx.appcompat.view.menu.o
    public void a(androidx.appcompat.view.menu.g gVar) {
        this.q = gVar;
    }

    public void a(n.a aVar, g.a aVar2) {
        this.v = aVar;
        this.w = aVar2;
    }
}
