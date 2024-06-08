package com.google.android.material.navigation;

import a.f.l.e0;
import a.f.l.w;
import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.widget.t0;
import c.a.a.a.b0.h;
import c.a.a.a.b0.k;
import c.a.a.a.l;
import com.google.android.material.internal.e;
import com.google.android.material.internal.f;
import com.google.android.material.internal.i;

/* loaded from: classes.dex */
public class NavigationView extends i {
    private static final int[] n = {R.attr.state_checked};
    private static final int[] o = {-16842910};
    private final e g;
    private final f h;
    c i;
    private final int j;
    private final int[] k;
    private MenuInflater l;
    private ViewTreeObserver.OnGlobalLayoutListener m;

    /* loaded from: classes.dex */
    class a implements g.a {
        a() {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void a(g gVar) {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(g gVar, MenuItem menuItem) {
            c cVar = NavigationView.this.i;
            return cVar != null && cVar.a(menuItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements ViewTreeObserver.OnGlobalLayoutListener {
        b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            NavigationView navigationView = NavigationView.this;
            navigationView.getLocationOnScreen(navigationView.k);
            boolean z = NavigationView.this.k[1] == 0;
            NavigationView.this.h.b(z);
            NavigationView.this.setDrawTopInsetForeground(z);
            Context context = NavigationView.this.getContext();
            if (!(context instanceof Activity) || Build.VERSION.SDK_INT < 21) {
                return;
            }
            Activity activity = (Activity) context;
            NavigationView.this.setDrawBottomInsetForeground((activity.findViewById(R.id.content).getHeight() == NavigationView.this.getHeight()) && (Color.alpha(activity.getWindow().getNavigationBarColor()) != 0));
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        boolean a(MenuItem menuItem);
    }

    public NavigationView(Context context) {
        this(context, null);
    }

    private ColorStateList c(int i) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i, typedValue, true)) {
            return null;
        }
        ColorStateList b2 = a.a.k.a.a.b(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(a.a.a.colorPrimary, typedValue, true)) {
            return null;
        }
        int i2 = typedValue.data;
        int defaultColor = b2.getDefaultColor();
        return new ColorStateList(new int[][]{o, n, FrameLayout.EMPTY_STATE_SET}, new int[]{b2.getColorForState(o, defaultColor), i2, defaultColor});
    }

    private MenuInflater getMenuInflater() {
        if (this.l == null) {
            this.l = new a.a.o.g(getContext());
        }
        return this.l;
    }

    public MenuItem getCheckedItem() {
        return this.h.d();
    }

    public int getHeaderCount() {
        return this.h.e();
    }

    public Drawable getItemBackground() {
        return this.h.f();
    }

    public int getItemHorizontalPadding() {
        return this.h.g();
    }

    public int getItemIconPadding() {
        return this.h.h();
    }

    public ColorStateList getItemIconTintList() {
        return this.h.k();
    }

    public int getItemMaxLines() {
        return this.h.i();
    }

    public ColorStateList getItemTextColor() {
        return this.h.j();
    }

    public Menu getMenu() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.material.internal.i, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        h.a(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.material.internal.i, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (Build.VERSION.SDK_INT < 16) {
            getViewTreeObserver().removeGlobalOnLayoutListener(this.m);
        } else {
            getViewTreeObserver().removeOnGlobalLayoutListener(this.m);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE) {
            i = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i), this.j), 1073741824);
        } else if (mode == 0) {
            i = View.MeasureSpec.makeMeasureSpec(this.j, 1073741824);
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof d)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        d dVar = (d) parcelable;
        super.onRestoreInstanceState(dVar.d());
        this.g.b(dVar.f2623d);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        d dVar = new d(super.onSaveInstanceState());
        dVar.f2623d = new Bundle();
        this.g.d(dVar.f2623d);
        return dVar;
    }

    public void setCheckedItem(int i) {
        MenuItem findItem = this.g.findItem(i);
        if (findItem != null) {
            this.h.a((j) findItem);
        }
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.setElevation(f2);
        }
        h.a(this, f2);
    }

    public void setItemBackground(Drawable drawable) {
        this.h.a(drawable);
    }

    public void setItemBackgroundResource(int i) {
        setItemBackground(a.f.d.b.c(getContext(), i));
    }

    public void setItemHorizontalPadding(int i) {
        this.h.c(i);
    }

    public void setItemHorizontalPaddingResource(int i) {
        this.h.c(getResources().getDimensionPixelSize(i));
    }

    public void setItemIconPadding(int i) {
        this.h.d(i);
    }

    public void setItemIconPaddingResource(int i) {
        this.h.d(getResources().getDimensionPixelSize(i));
    }

    public void setItemIconSize(int i) {
        this.h.e(i);
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.h.a(colorStateList);
    }

    public void setItemMaxLines(int i) {
        this.h.f(i);
    }

    public void setItemTextAppearance(int i) {
        this.h.g(i);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.h.b(colorStateList);
    }

    public void setNavigationItemSelectedListener(c cVar) {
        this.i = cVar;
    }

    @Override // android.view.View
    public void setOverScrollMode(int i) {
        super.setOverScrollMode(i);
        f fVar = this.h;
        if (fVar != null) {
            fVar.h(i);
        }
    }

    /* loaded from: classes.dex */
    public static class d extends a.h.a.a {
        public static final Parcelable.Creator<d> CREATOR = new a();

        /* renamed from: d, reason: collision with root package name */
        public Bundle f2623d;

        /* loaded from: classes.dex */
        static class a implements Parcelable.ClassLoaderCreator<d> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            public d[] newArray(int i) {
                return new d[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public d createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new d(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public d createFromParcel(Parcel parcel) {
                return new d(parcel, null);
            }
        }

        public d(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2623d = parcel.readBundle(classLoader);
        }

        @Override // a.h.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.f2623d);
        }

        public d(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public NavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.a.a.a.b.navigationViewStyle);
    }

    private final Drawable a(t0 t0Var) {
        c.a.a.a.b0.g gVar = new c.a.a.a.b0.g(k.a(getContext(), t0Var.g(l.NavigationView_itemShapeAppearance, 0), t0Var.g(l.NavigationView_itemShapeAppearanceOverlay, 0)).a());
        gVar.a(c.a.a.a.y.c.a(getContext(), t0Var, l.NavigationView_itemShapeFillColor));
        return new InsetDrawable((Drawable) gVar, t0Var.c(l.NavigationView_itemShapeInsetStart, 0), t0Var.c(l.NavigationView_itemShapeInsetTop, 0), t0Var.c(l.NavigationView_itemShapeInsetEnd, 0), t0Var.c(l.NavigationView_itemShapeInsetBottom, 0));
    }

    private boolean b(t0 t0Var) {
        return t0Var.g(l.NavigationView_itemShapeAppearance) || t0Var.g(l.NavigationView_itemShapeAppearanceOverlay);
    }

    public NavigationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ColorStateList c2;
        boolean z;
        int i2;
        this.h = new f();
        this.k = new int[2];
        this.g = new e(context);
        t0 d2 = com.google.android.material.internal.l.d(context, attributeSet, l.NavigationView, i, c.a.a.a.k.Widget_Design_NavigationView, new int[0]);
        if (d2.g(l.NavigationView_android_background)) {
            w.a(this, d2.b(l.NavigationView_android_background));
        }
        if (getBackground() == null || (getBackground() instanceof ColorDrawable)) {
            Drawable background = getBackground();
            c.a.a.a.b0.g gVar = new c.a.a.a.b0.g();
            if (background instanceof ColorDrawable) {
                gVar.a(ColorStateList.valueOf(((ColorDrawable) background).getColor()));
            }
            gVar.a(context);
            w.a(this, gVar);
        }
        if (d2.g(l.NavigationView_elevation)) {
            setElevation(d2.c(l.NavigationView_elevation, 0));
        }
        setFitsSystemWindows(d2.a(l.NavigationView_android_fitsSystemWindows, false));
        this.j = d2.c(l.NavigationView_android_maxWidth, 0);
        if (d2.g(l.NavigationView_itemIconTint)) {
            c2 = d2.a(l.NavigationView_itemIconTint);
        } else {
            c2 = c(R.attr.textColorSecondary);
        }
        if (d2.g(l.NavigationView_itemTextAppearance)) {
            i2 = d2.g(l.NavigationView_itemTextAppearance, 0);
            z = true;
        } else {
            z = false;
            i2 = 0;
        }
        if (d2.g(l.NavigationView_itemIconSize)) {
            setItemIconSize(d2.c(l.NavigationView_itemIconSize, 0));
        }
        ColorStateList a2 = d2.g(l.NavigationView_itemTextColor) ? d2.a(l.NavigationView_itemTextColor) : null;
        if (!z && a2 == null) {
            a2 = c(R.attr.textColorPrimary);
        }
        Drawable b2 = d2.b(l.NavigationView_itemBackground);
        if (b2 == null && b(d2)) {
            b2 = a(d2);
        }
        if (d2.g(l.NavigationView_itemHorizontalPadding)) {
            this.h.c(d2.c(l.NavigationView_itemHorizontalPadding, 0));
        }
        int c3 = d2.c(l.NavigationView_itemIconPadding, 0);
        setItemMaxLines(d2.d(l.NavigationView_itemMaxLines, 1));
        this.g.a(new a());
        this.h.b(1);
        this.h.a(context, this.g);
        this.h.a(c2);
        this.h.h(getOverScrollMode());
        if (z) {
            this.h.g(i2);
        }
        this.h.b(a2);
        this.h.a(b2);
        this.h.d(c3);
        this.g.a(this.h);
        addView((View) this.h.a((ViewGroup) this));
        if (d2.g(l.NavigationView_menu)) {
            b(d2.g(l.NavigationView_menu, 0));
        }
        if (d2.g(l.NavigationView_headerLayout)) {
            a(d2.g(l.NavigationView_headerLayout, 0));
        }
        d2.a();
        a();
    }

    public void setCheckedItem(MenuItem menuItem) {
        MenuItem findItem = this.g.findItem(menuItem.getItemId());
        if (findItem != null) {
            this.h.a((j) findItem);
            return;
        }
        throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }

    public void b(int i) {
        this.h.c(true);
        getMenuInflater().inflate(i, this.g);
        this.h.c(false);
        this.h.a(false);
    }

    @Override // com.google.android.material.internal.i
    protected void a(e0 e0Var) {
        this.h.a(e0Var);
    }

    public View a(int i) {
        return this.h.a(i);
    }

    private void a() {
        this.m = new b();
        getViewTreeObserver().addOnGlobalLayoutListener(this.m);
    }
}
