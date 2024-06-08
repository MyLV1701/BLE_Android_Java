package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.a;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.n;
import androidx.appcompat.widget.ActionMenuView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class Toolbar extends ViewGroup {
    private ColorStateList A;
    private ColorStateList B;
    private boolean C;
    private boolean D;
    private final ArrayList<View> E;
    private final ArrayList<View> F;
    private final int[] G;
    f H;
    private final ActionMenuView.e I;
    private u0 J;
    private androidx.appcompat.widget.c K;
    private d L;
    private n.a M;
    private g.a N;
    private boolean O;
    private final Runnable P;

    /* renamed from: b, reason: collision with root package name */
    private ActionMenuView f911b;

    /* renamed from: c, reason: collision with root package name */
    private TextView f912c;

    /* renamed from: d, reason: collision with root package name */
    private TextView f913d;

    /* renamed from: e, reason: collision with root package name */
    private ImageButton f914e;

    /* renamed from: f, reason: collision with root package name */
    private ImageView f915f;
    private Drawable g;
    private CharSequence h;
    ImageButton i;
    View j;
    private Context k;
    private int l;
    private int m;
    private int n;
    int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private l0 u;
    private int v;
    private int w;
    private int x;
    private CharSequence y;
    private CharSequence z;

    /* loaded from: classes.dex */
    class a implements ActionMenuView.e {
        a() {
        }

        @Override // androidx.appcompat.widget.ActionMenuView.e
        public boolean onMenuItemClick(MenuItem menuItem) {
            f fVar = Toolbar.this.H;
            if (fVar != null) {
                return fVar.onMenuItemClick(menuItem);
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Toolbar.this.k();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Toolbar.this.c();
        }
    }

    /* loaded from: classes.dex */
    public interface f {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public Toolbar(Context context) {
        this(context, null);
    }

    private MenuInflater getMenuInflater() {
        return new a.a.o.g(getContext());
    }

    private void l() {
        if (this.u == null) {
            this.u = new l0();
        }
    }

    private void m() {
        if (this.f915f == null) {
            this.f915f = new AppCompatImageView(getContext());
        }
    }

    private void n() {
        o();
        if (this.f911b.j() == null) {
            androidx.appcompat.view.menu.g gVar = (androidx.appcompat.view.menu.g) this.f911b.getMenu();
            if (this.L == null) {
                this.L = new d();
            }
            this.f911b.setExpandedActionViewsExclusive(true);
            gVar.a(this.L, this.k);
        }
    }

    private void o() {
        if (this.f911b == null) {
            this.f911b = new ActionMenuView(getContext());
            this.f911b.setPopupTheme(this.l);
            this.f911b.setOnMenuItemClickListener(this.I);
            this.f911b.a(this.M, this.N);
            e generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.f651a = 8388613 | (this.o & 112);
            this.f911b.setLayoutParams(generateDefaultLayoutParams);
            a((View) this.f911b, false);
        }
    }

    private void p() {
        if (this.f914e == null) {
            this.f914e = new m(getContext(), null, a.a.a.toolbarNavigationButtonStyle);
            e generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.f651a = 8388611 | (this.o & 112);
            this.f914e.setLayoutParams(generateDefaultLayoutParams);
        }
    }

    private void q() {
        removeCallbacks(this.P);
        post(this.P);
    }

    private boolean r() {
        if (!this.O) {
            return false;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (d(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    public void a(androidx.appcompat.view.menu.g gVar, androidx.appcompat.widget.c cVar) {
        if (gVar == null && this.f911b == null) {
            return;
        }
        o();
        androidx.appcompat.view.menu.g j = this.f911b.j();
        if (j == gVar) {
            return;
        }
        if (j != null) {
            j.b(this.K);
            j.b(this.L);
        }
        if (this.L == null) {
            this.L = new d();
        }
        cVar.b(true);
        if (gVar != null) {
            gVar.a(cVar, this.k);
            gVar.a(this.L, this.k);
        } else {
            cVar.a(this.k, (androidx.appcompat.view.menu.g) null);
            this.L.a(this.k, (androidx.appcompat.view.menu.g) null);
            cVar.a(true);
            this.L.a(true);
        }
        this.f911b.setPopupTheme(this.l);
        this.f911b.setPresenter(cVar);
        this.K = cVar;
    }

    public boolean b() {
        ActionMenuView actionMenuView;
        return getVisibility() == 0 && (actionMenuView = this.f911b) != null && actionMenuView.i();
    }

    public void c() {
        d dVar = this.L;
        androidx.appcompat.view.menu.j jVar = dVar == null ? null : dVar.f920c;
        if (jVar != null) {
            jVar.collapseActionView();
        }
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof e);
    }

    public void d() {
        ActionMenuView actionMenuView = this.f911b;
        if (actionMenuView != null) {
            actionMenuView.d();
        }
    }

    void e() {
        if (this.i == null) {
            this.i = new m(getContext(), null, a.a.a.toolbarNavigationButtonStyle);
            this.i.setImageDrawable(this.g);
            this.i.setContentDescription(this.h);
            e generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.f651a = 8388611 | (this.o & 112);
            generateDefaultLayoutParams.f922b = 2;
            this.i.setLayoutParams(generateDefaultLayoutParams);
            this.i.setOnClickListener(new c());
        }
    }

    public boolean f() {
        d dVar = this.L;
        return (dVar == null || dVar.f920c == null) ? false : true;
    }

    public boolean g() {
        ActionMenuView actionMenuView = this.f911b;
        return actionMenuView != null && actionMenuView.f();
    }

    public CharSequence getCollapseContentDescription() {
        ImageButton imageButton = this.i;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        ImageButton imageButton = this.i;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        l0 l0Var = this.u;
        if (l0Var != null) {
            return l0Var.a();
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i = this.w;
        return i != Integer.MIN_VALUE ? i : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        l0 l0Var = this.u;
        if (l0Var != null) {
            return l0Var.b();
        }
        return 0;
    }

    public int getContentInsetRight() {
        l0 l0Var = this.u;
        if (l0Var != null) {
            return l0Var.c();
        }
        return 0;
    }

    public int getContentInsetStart() {
        l0 l0Var = this.u;
        if (l0Var != null) {
            return l0Var.d();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i = this.v;
        return i != Integer.MIN_VALUE ? i : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        androidx.appcompat.view.menu.g j;
        ActionMenuView actionMenuView = this.f911b;
        if ((actionMenuView == null || (j = actionMenuView.j()) == null || !j.hasVisibleItems()) ? false : true) {
            return Math.max(getContentInsetEnd(), Math.max(this.w, 0));
        }
        return getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        if (a.f.l.w.q(this) == 1) {
            return getCurrentContentInsetEnd();
        }
        return getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        if (a.f.l.w.q(this) == 1) {
            return getCurrentContentInsetStart();
        }
        return getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        if (getNavigationIcon() != null) {
            return Math.max(getContentInsetStart(), Math.max(this.v, 0));
        }
        return getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.f915f;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.f915f;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        n();
        return this.f911b.getMenu();
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.f914e;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.f914e;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    androidx.appcompat.widget.c getOuterActionMenuPresenter() {
        return this.K;
    }

    public Drawable getOverflowIcon() {
        n();
        return this.f911b.getOverflowIcon();
    }

    Context getPopupContext() {
        return this.k;
    }

    public int getPopupTheme() {
        return this.l;
    }

    public CharSequence getSubtitle() {
        return this.z;
    }

    final TextView getSubtitleTextView() {
        return this.f913d;
    }

    public CharSequence getTitle() {
        return this.y;
    }

    public int getTitleMarginBottom() {
        return this.t;
    }

    public int getTitleMarginEnd() {
        return this.r;
    }

    public int getTitleMarginStart() {
        return this.q;
    }

    public int getTitleMarginTop() {
        return this.s;
    }

    final TextView getTitleTextView() {
        return this.f912c;
    }

    public z getWrapper() {
        if (this.J == null) {
            this.J = new u0(this, true);
        }
        return this.J;
    }

    public boolean h() {
        ActionMenuView actionMenuView = this.f911b;
        return actionMenuView != null && actionMenuView.g();
    }

    public boolean i() {
        ActionMenuView actionMenuView = this.f911b;
        return actionMenuView != null && actionMenuView.h();
    }

    void j() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (((e) childAt.getLayoutParams()).f922b != 2 && childAt != this.f911b) {
                removeViewAt(childCount);
                this.F.add(childAt);
            }
        }
    }

    public boolean k() {
        ActionMenuView actionMenuView = this.f911b;
        return actionMenuView != null && actionMenuView.k();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.P);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.D = false;
        }
        if (!this.D) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.D = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.D = false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x02a6 A[LOOP:0: B:41:0x02a4->B:42:0x02a6, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x02c8 A[LOOP:1: B:45:0x02c6->B:46:0x02c8, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0302 A[LOOP:2: B:54:0x0300->B:55:0x0302, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x022c  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instructions count: 791
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        char c2;
        char c3;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int[] iArr = this.G;
        if (z0.a(this)) {
            c2 = 1;
            c3 = 0;
        } else {
            c2 = 0;
            c3 = 1;
        }
        if (d(this.f914e)) {
            a(this.f914e, i, 0, i2, 0, this.p);
            i3 = this.f914e.getMeasuredWidth() + a(this.f914e);
            i4 = Math.max(0, this.f914e.getMeasuredHeight() + b(this.f914e));
            i5 = View.combineMeasuredStates(0, this.f914e.getMeasuredState());
        } else {
            i3 = 0;
            i4 = 0;
            i5 = 0;
        }
        if (d(this.i)) {
            a(this.i, i, 0, i2, 0, this.p);
            i3 = this.i.getMeasuredWidth() + a(this.i);
            i4 = Math.max(i4, this.i.getMeasuredHeight() + b(this.i));
            i5 = View.combineMeasuredStates(i5, this.i.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int max = 0 + Math.max(currentContentInsetStart, i3);
        iArr[c2] = Math.max(0, currentContentInsetStart - i3);
        if (d(this.f911b)) {
            a(this.f911b, i, max, i2, 0, this.p);
            i6 = this.f911b.getMeasuredWidth() + a(this.f911b);
            i4 = Math.max(i4, this.f911b.getMeasuredHeight() + b(this.f911b));
            i5 = View.combineMeasuredStates(i5, this.f911b.getMeasuredState());
        } else {
            i6 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int max2 = max + Math.max(currentContentInsetEnd, i6);
        iArr[c3] = Math.max(0, currentContentInsetEnd - i6);
        if (d(this.j)) {
            max2 += a(this.j, i, max2, i2, 0, iArr);
            i4 = Math.max(i4, this.j.getMeasuredHeight() + b(this.j));
            i5 = View.combineMeasuredStates(i5, this.j.getMeasuredState());
        }
        if (d(this.f915f)) {
            max2 += a(this.f915f, i, max2, i2, 0, iArr);
            i4 = Math.max(i4, this.f915f.getMeasuredHeight() + b(this.f915f));
            i5 = View.combineMeasuredStates(i5, this.f915f.getMeasuredState());
        }
        int childCount = getChildCount();
        int i10 = i4;
        int i11 = max2;
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (((e) childAt.getLayoutParams()).f922b == 0 && d(childAt)) {
                i11 += a(childAt, i, i11, i2, 0, iArr);
                i10 = Math.max(i10, childAt.getMeasuredHeight() + b(childAt));
                i5 = View.combineMeasuredStates(i5, childAt.getMeasuredState());
            }
        }
        int i13 = this.s + this.t;
        int i14 = this.q + this.r;
        if (d(this.f912c)) {
            a(this.f912c, i, i11 + i14, i2, i13, iArr);
            int measuredWidth = this.f912c.getMeasuredWidth() + a(this.f912c);
            i9 = this.f912c.getMeasuredHeight() + b(this.f912c);
            i7 = View.combineMeasuredStates(i5, this.f912c.getMeasuredState());
            i8 = measuredWidth;
        } else {
            i7 = i5;
            i8 = 0;
            i9 = 0;
        }
        if (d(this.f913d)) {
            i8 = Math.max(i8, a(this.f913d, i, i11 + i14, i2, i9 + i13, iArr));
            i9 += this.f913d.getMeasuredHeight() + b(this.f913d);
            i7 = View.combineMeasuredStates(i7, this.f913d.getMeasuredState());
        }
        int max3 = Math.max(i10, i9);
        int paddingLeft = i11 + i8 + getPaddingLeft() + getPaddingRight();
        int paddingTop = max3 + getPaddingTop() + getPaddingBottom();
        int resolveSizeAndState = View.resolveSizeAndState(Math.max(paddingLeft, getSuggestedMinimumWidth()), i, (-16777216) & i7);
        int resolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i2, i7 << 16);
        if (r()) {
            resolveSizeAndState2 = 0;
        }
        setMeasuredDimension(resolveSizeAndState, resolveSizeAndState2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem findItem;
        if (!(parcelable instanceof g)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        g gVar = (g) parcelable;
        super.onRestoreInstanceState(gVar.d());
        ActionMenuView actionMenuView = this.f911b;
        androidx.appcompat.view.menu.g j = actionMenuView != null ? actionMenuView.j() : null;
        int i = gVar.f923d;
        if (i != 0 && this.L != null && j != null && (findItem = j.findItem(i)) != null) {
            findItem.expandActionView();
        }
        if (gVar.f924e) {
            q();
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        if (Build.VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(i);
        }
        l();
        this.u.a(i == 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        androidx.appcompat.view.menu.j jVar;
        g gVar = new g(super.onSaveInstanceState());
        d dVar = this.L;
        if (dVar != null && (jVar = dVar.f920c) != null) {
            gVar.f923d = jVar.getItemId();
        }
        gVar.f924e = i();
        return gVar;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.C = false;
        }
        if (!this.C) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.C = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.C = false;
        }
        return true;
    }

    public void setCollapseContentDescription(int i) {
        setCollapseContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setCollapseIcon(int i) {
        setCollapseIcon(a.a.k.a.a.c(getContext(), i));
    }

    public void setCollapsible(boolean z) {
        this.O = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i) {
        if (i < 0) {
            i = RecyclerView.UNDEFINED_DURATION;
        }
        if (i != this.w) {
            this.w = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i) {
        if (i < 0) {
            i = RecyclerView.UNDEFINED_DURATION;
        }
        if (i != this.v) {
            this.v = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i) {
        setLogo(a.a.k.a.a.c(getContext(), i));
    }

    public void setLogoDescription(int i) {
        setLogoDescription(getContext().getText(i));
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setNavigationIcon(int i) {
        setNavigationIcon(a.a.k.a.a.c(getContext(), i));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        p();
        this.f914e.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(f fVar) {
        this.H = fVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        n();
        this.f911b.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i) {
        if (this.l != i) {
            this.l = i;
            if (i == 0) {
                this.k = getContext();
            } else {
                this.k = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setSubtitle(int i) {
        setSubtitle(getContext().getText(i));
    }

    public void setSubtitleTextColor(int i) {
        setSubtitleTextColor(ColorStateList.valueOf(i));
    }

    public void setTitle(int i) {
        setTitle(getContext().getText(i));
    }

    public void setTitleMarginBottom(int i) {
        this.t = i;
        requestLayout();
    }

    public void setTitleMarginEnd(int i) {
        this.r = i;
        requestLayout();
    }

    public void setTitleMarginStart(int i) {
        this.q = i;
        requestLayout();
    }

    public void setTitleMarginTop(int i) {
        this.s = i;
        requestLayout();
    }

    public void setTitleTextColor(int i) {
        setTitleTextColor(ColorStateList.valueOf(i));
    }

    /* loaded from: classes.dex */
    public static class e extends a.C0038a {

        /* renamed from: b, reason: collision with root package name */
        int f922b;

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f922b = 0;
        }

        void a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
        }

        public e(int i, int i2) {
            super(i, i2);
            this.f922b = 0;
            this.f651a = 8388627;
        }

        public e(e eVar) {
            super((a.C0038a) eVar);
            this.f922b = 0;
            this.f922b = eVar.f922b;
        }

        public e(a.C0038a c0038a) {
            super(c0038a);
            this.f922b = 0;
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f922b = 0;
            a(marginLayoutParams);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f922b = 0;
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.a.a.toolbarStyle);
    }

    public void b(Context context, int i) {
        this.m = i;
        TextView textView = this.f912c;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public e generateDefaultLayoutParams() {
        return new e(-2, -2);
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            e();
        }
        ImageButton imageButton = this.i;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            e();
            this.i.setImageDrawable(drawable);
        } else {
            ImageButton imageButton = this.i;
            if (imageButton != null) {
                imageButton.setImageDrawable(this.g);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            m();
            if (!c(this.f915f)) {
                a((View) this.f915f, true);
            }
        } else {
            ImageView imageView = this.f915f;
            if (imageView != null && c(imageView)) {
                removeView(this.f915f);
                this.F.remove(this.f915f);
            }
        }
        ImageView imageView2 = this.f915f;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            m();
        }
        ImageView imageView = this.f915f;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            p();
        }
        ImageButton imageButton = this.f914e;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            p();
            if (!c(this.f914e)) {
                a((View) this.f914e, true);
            }
        } else {
            ImageButton imageButton = this.f914e;
            if (imageButton != null && c(imageButton)) {
                removeView(this.f914e);
                this.F.remove(this.f914e);
            }
        }
        ImageButton imageButton2 = this.f914e;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.f913d == null) {
                Context context = getContext();
                this.f913d = new AppCompatTextView(context);
                this.f913d.setSingleLine();
                this.f913d.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.n;
                if (i != 0) {
                    this.f913d.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.B;
                if (colorStateList != null) {
                    this.f913d.setTextColor(colorStateList);
                }
            }
            if (!c(this.f913d)) {
                a((View) this.f913d, true);
            }
        } else {
            TextView textView = this.f913d;
            if (textView != null && c(textView)) {
                removeView(this.f913d);
                this.F.remove(this.f913d);
            }
        }
        TextView textView2 = this.f913d;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.z = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.B = colorStateList;
        TextView textView = this.f913d;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.f912c == null) {
                Context context = getContext();
                this.f912c = new AppCompatTextView(context);
                this.f912c.setSingleLine();
                this.f912c.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.m;
                if (i != 0) {
                    this.f912c.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.A;
                if (colorStateList != null) {
                    this.f912c.setTextColor(colorStateList);
                }
            }
            if (!c(this.f912c)) {
                a((View) this.f912c, true);
            }
        } else {
            TextView textView = this.f912c;
            if (textView != null && c(textView)) {
                removeView(this.f912c);
                this.F.remove(this.f912c);
            }
        }
        TextView textView2 = this.f912c;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.y = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.A = colorStateList;
        TextView textView = this.f912c;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class d implements androidx.appcompat.view.menu.n {

        /* renamed from: b, reason: collision with root package name */
        androidx.appcompat.view.menu.g f919b;

        /* renamed from: c, reason: collision with root package name */
        androidx.appcompat.view.menu.j f920c;

        d() {
        }

        @Override // androidx.appcompat.view.menu.n
        public int a() {
            return 0;
        }

        @Override // androidx.appcompat.view.menu.n
        public void a(Context context, androidx.appcompat.view.menu.g gVar) {
            androidx.appcompat.view.menu.j jVar;
            androidx.appcompat.view.menu.g gVar2 = this.f919b;
            if (gVar2 != null && (jVar = this.f920c) != null) {
                gVar2.a(jVar);
            }
            this.f919b = gVar;
        }

        @Override // androidx.appcompat.view.menu.n
        public void a(Parcelable parcelable) {
        }

        @Override // androidx.appcompat.view.menu.n
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
        }

        @Override // androidx.appcompat.view.menu.n
        public boolean a(androidx.appcompat.view.menu.s sVar) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.n
        public boolean b() {
            return false;
        }

        @Override // androidx.appcompat.view.menu.n
        public boolean b(androidx.appcompat.view.menu.g gVar, androidx.appcompat.view.menu.j jVar) {
            Toolbar.this.e();
            ViewParent parent = Toolbar.this.i.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.i);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.i);
            }
            Toolbar.this.j = jVar.getActionView();
            this.f920c = jVar;
            ViewParent parent2 = Toolbar.this.j.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.j);
                }
                e generateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                Toolbar toolbar4 = Toolbar.this;
                generateDefaultLayoutParams.f651a = 8388611 | (toolbar4.o & 112);
                generateDefaultLayoutParams.f922b = 2;
                toolbar4.j.setLayoutParams(generateDefaultLayoutParams);
                Toolbar toolbar5 = Toolbar.this;
                toolbar5.addView(toolbar5.j);
            }
            Toolbar.this.j();
            Toolbar.this.requestLayout();
            jVar.a(true);
            KeyEvent.Callback callback = Toolbar.this.j;
            if (callback instanceof a.a.o.c) {
                ((a.a.o.c) callback).b();
            }
            return true;
        }

        @Override // androidx.appcompat.view.menu.n
        public Parcelable c() {
            return null;
        }

        @Override // androidx.appcompat.view.menu.n
        public void a(boolean z) {
            if (this.f920c != null) {
                androidx.appcompat.view.menu.g gVar = this.f919b;
                boolean z2 = false;
                if (gVar != null) {
                    int size = gVar.size();
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            break;
                        }
                        if (this.f919b.getItem(i) == this.f920c) {
                            z2 = true;
                            break;
                        }
                        i++;
                    }
                }
                if (z2) {
                    return;
                }
                a(this.f919b, this.f920c);
            }
        }

        @Override // androidx.appcompat.view.menu.n
        public boolean a(androidx.appcompat.view.menu.g gVar, androidx.appcompat.view.menu.j jVar) {
            KeyEvent.Callback callback = Toolbar.this.j;
            if (callback instanceof a.a.o.c) {
                ((a.a.o.c) callback).c();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.j);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.i);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.j = null;
            toolbar3.a();
            this.f920c = null;
            Toolbar.this.requestLayout();
            jVar.a(false);
            return true;
        }
    }

    /* loaded from: classes.dex */
    public static class g extends a.h.a.a {
        public static final Parcelable.Creator<g> CREATOR = new a();

        /* renamed from: d, reason: collision with root package name */
        int f923d;

        /* renamed from: e, reason: collision with root package name */
        boolean f924e;

        /* loaded from: classes.dex */
        static class a implements Parcelable.ClassLoaderCreator<g> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            public g[] newArray(int i) {
                return new g[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public g createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new g(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public g createFromParcel(Parcel parcel) {
                return new g(parcel, null);
            }
        }

        public g(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f923d = parcel.readInt();
            this.f924e = parcel.readInt() != 0;
        }

        @Override // a.h.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f923d);
            parcel.writeInt(this.f924e ? 1 : 0);
        }

        public g(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.x = 8388627;
        this.E = new ArrayList<>();
        this.F = new ArrayList<>();
        this.G = new int[2];
        this.I = new a();
        this.P = new b();
        t0 a2 = t0.a(getContext(), attributeSet, a.a.j.Toolbar, i, 0);
        this.m = a2.g(a.a.j.Toolbar_titleTextAppearance, 0);
        this.n = a2.g(a.a.j.Toolbar_subtitleTextAppearance, 0);
        this.x = a2.e(a.a.j.Toolbar_android_gravity, this.x);
        this.o = a2.e(a.a.j.Toolbar_buttonGravity, 48);
        int b2 = a2.b(a.a.j.Toolbar_titleMargin, 0);
        b2 = a2.g(a.a.j.Toolbar_titleMargins) ? a2.b(a.a.j.Toolbar_titleMargins, b2) : b2;
        this.t = b2;
        this.s = b2;
        this.r = b2;
        this.q = b2;
        int b3 = a2.b(a.a.j.Toolbar_titleMarginStart, -1);
        if (b3 >= 0) {
            this.q = b3;
        }
        int b4 = a2.b(a.a.j.Toolbar_titleMarginEnd, -1);
        if (b4 >= 0) {
            this.r = b4;
        }
        int b5 = a2.b(a.a.j.Toolbar_titleMarginTop, -1);
        if (b5 >= 0) {
            this.s = b5;
        }
        int b6 = a2.b(a.a.j.Toolbar_titleMarginBottom, -1);
        if (b6 >= 0) {
            this.t = b6;
        }
        this.p = a2.c(a.a.j.Toolbar_maxButtonHeight, -1);
        int b7 = a2.b(a.a.j.Toolbar_contentInsetStart, RecyclerView.UNDEFINED_DURATION);
        int b8 = a2.b(a.a.j.Toolbar_contentInsetEnd, RecyclerView.UNDEFINED_DURATION);
        int c2 = a2.c(a.a.j.Toolbar_contentInsetLeft, 0);
        int c3 = a2.c(a.a.j.Toolbar_contentInsetRight, 0);
        l();
        this.u.a(c2, c3);
        if (b7 != Integer.MIN_VALUE || b8 != Integer.MIN_VALUE) {
            this.u.b(b7, b8);
        }
        this.v = a2.b(a.a.j.Toolbar_contentInsetStartWithNavigation, RecyclerView.UNDEFINED_DURATION);
        this.w = a2.b(a.a.j.Toolbar_contentInsetEndWithActions, RecyclerView.UNDEFINED_DURATION);
        this.g = a2.b(a.a.j.Toolbar_collapseIcon);
        this.h = a2.e(a.a.j.Toolbar_collapseContentDescription);
        CharSequence e2 = a2.e(a.a.j.Toolbar_title);
        if (!TextUtils.isEmpty(e2)) {
            setTitle(e2);
        }
        CharSequence e3 = a2.e(a.a.j.Toolbar_subtitle);
        if (!TextUtils.isEmpty(e3)) {
            setSubtitle(e3);
        }
        this.k = getContext();
        setPopupTheme(a2.g(a.a.j.Toolbar_popupTheme, 0));
        Drawable b9 = a2.b(a.a.j.Toolbar_navigationIcon);
        if (b9 != null) {
            setNavigationIcon(b9);
        }
        CharSequence e4 = a2.e(a.a.j.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(e4)) {
            setNavigationContentDescription(e4);
        }
        Drawable b10 = a2.b(a.a.j.Toolbar_logo);
        if (b10 != null) {
            setLogo(b10);
        }
        CharSequence e5 = a2.e(a.a.j.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(e5)) {
            setLogoDescription(e5);
        }
        if (a2.g(a.a.j.Toolbar_titleTextColor)) {
            setTitleTextColor(a2.a(a.a.j.Toolbar_titleTextColor));
        }
        if (a2.g(a.a.j.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor(a2.a(a.a.j.Toolbar_subtitleTextColor));
        }
        if (a2.g(a.a.j.Toolbar_menu)) {
            a(a2.g(a.a.j.Toolbar_menu, 0));
        }
        a2.a();
    }

    private int c(int i) {
        int i2 = i & 112;
        return (i2 == 16 || i2 == 48 || i2 == 80) ? i2 : this.x & 112;
    }

    private boolean d(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    @Override // android.view.ViewGroup
    public e generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    private boolean c(View view) {
        return view.getParent() == this || this.F.contains(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public e generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof e) {
            return new e((e) layoutParams);
        }
        if (layoutParams instanceof a.C0038a) {
            return new e((a.C0038a) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new e((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new e(layoutParams);
    }

    private int b(View view, int i, int[] iArr, int i2) {
        e eVar = (e) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - iArr[1];
        int max = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        int a2 = a(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max - measuredWidth, a2, max, view.getMeasuredHeight() + a2);
        return max - (measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).leftMargin);
    }

    private int b(int i) {
        int q = a.f.l.w.q(this);
        int a2 = a.f.l.d.a(i, q) & 7;
        return (a2 == 1 || a2 == 3 || a2 == 5) ? a2 : q == 1 ? 5 : 3;
    }

    private int b(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public void a(Context context, int i) {
        this.n = i;
        TextView textView = this.f913d;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    public void a(int i) {
        getMenuInflater().inflate(i, getMenu());
    }

    public void a(int i, int i2) {
        l();
        this.u.b(i, i2);
    }

    private void a(View view, boolean z) {
        e eVar;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            eVar = generateDefaultLayoutParams();
        } else if (!checkLayoutParams(layoutParams)) {
            eVar = generateLayoutParams(layoutParams);
        } else {
            eVar = (e) layoutParams;
        }
        eVar.f922b = 1;
        if (z && this.j != null) {
            view.setLayoutParams(eVar);
            this.F.add(view);
        } else {
            addView(view, eVar);
        }
    }

    private void a(View view, int i, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i5 >= 0) {
            if (mode != 0) {
                i5 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i5);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    private int a(View view, int i, int i2, int i3, int i4, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i5) + Math.max(0, i6);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + max + i2, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    private int a(List<View> list, int[] iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        int size = list.size();
        int i3 = i2;
        int i4 = i;
        int i5 = 0;
        int i6 = 0;
        while (i5 < size) {
            View view = list.get(i5);
            e eVar = (e) view.getLayoutParams();
            int i7 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - i4;
            int i8 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - i3;
            int max = Math.max(0, i7);
            int max2 = Math.max(0, i8);
            int max3 = Math.max(0, -i7);
            int max4 = Math.max(0, -i8);
            i6 += max + view.getMeasuredWidth() + max2;
            i5++;
            i3 = max4;
            i4 = max3;
        }
        return i6;
    }

    private int a(View view, int i, int[] iArr, int i2) {
        e eVar = (e) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - iArr[0];
        int max = i + Math.max(0, i3);
        iArr[0] = Math.max(0, -i3);
        int a2 = a(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max, a2, max + measuredWidth, view.getMeasuredHeight() + a2);
        return max + measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).rightMargin;
    }

    private int a(View view, int i) {
        e eVar = (e) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        int c2 = c(eVar.f651a);
        if (c2 == 48) {
            return getPaddingTop() - i2;
        }
        if (c2 != 80) {
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            int height = getHeight();
            int i3 = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
            int i4 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
            if (i3 < i4) {
                i3 = i4;
            } else {
                int i5 = (((height - paddingBottom) - measuredHeight) - i3) - paddingTop;
                int i6 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
                if (i5 < i6) {
                    i3 = Math.max(0, i3 - (i6 - i5));
                }
            }
            return paddingTop + i3;
        }
        return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) - i2;
    }

    private void a(List<View> list, int i) {
        boolean z = a.f.l.w.q(this) == 1;
        int childCount = getChildCount();
        int a2 = a.f.l.d.a(i, a.f.l.w.q(this));
        list.clear();
        if (!z) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.f922b == 0 && d(childAt) && b(eVar.f651a) == a2) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i3 = childCount - 1; i3 >= 0; i3--) {
            View childAt2 = getChildAt(i3);
            e eVar2 = (e) childAt2.getLayoutParams();
            if (eVar2.f922b == 0 && d(childAt2) && b(eVar2.f651a) == a2) {
                list.add(childAt2);
            }
        }
    }

    private int a(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return a.f.l.g.b(marginLayoutParams) + a.f.l.g.a(marginLayoutParams);
    }

    void a() {
        for (int size = this.F.size() - 1; size >= 0; size--) {
            addView(this.F.get(size));
        }
        this.F.clear();
    }

    public void a(n.a aVar, g.a aVar2) {
        this.M = aVar;
        this.N = aVar2;
        ActionMenuView actionMenuView = this.f911b;
        if (actionMenuView != null) {
            actionMenuView.a(aVar, aVar2);
        }
    }
}
