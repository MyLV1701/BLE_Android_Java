package androidx.appcompat.widget;

import a.f.l.b;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.n;
import androidx.appcompat.view.menu.o;
import androidx.appcompat.widget.ActionMenuView;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c extends androidx.appcompat.view.menu.b implements b.a {
    RunnableC0043c A;
    private b B;
    final f C;
    int D;
    d k;
    private Drawable l;
    private boolean m;
    private boolean n;
    private boolean o;
    private int p;
    private int q;
    private int r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private int w;
    private final SparseBooleanArray x;
    e y;
    a z;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class a extends androidx.appcompat.view.menu.m {
        public a(Context context, androidx.appcompat.view.menu.s sVar, View view) {
            super(context, sVar, view, false, a.a.a.actionOverflowMenuStyle);
            if (!((androidx.appcompat.view.menu.j) sVar.getItem()).h()) {
                View view2 = c.this.k;
                a(view2 == null ? (View) ((androidx.appcompat.view.menu.b) c.this).i : view2);
            }
            a(c.this.C);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.appcompat.view.menu.m
        public void d() {
            c cVar = c.this;
            cVar.z = null;
            cVar.D = 0;
            super.d();
        }
    }

    /* loaded from: classes.dex */
    private class b extends ActionMenuItemView.b {
        b() {
        }

        @Override // androidx.appcompat.view.menu.ActionMenuItemView.b
        public androidx.appcompat.view.menu.q a() {
            a aVar = c.this.z;
            if (aVar != null) {
                return aVar.b();
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: androidx.appcompat.widget.c$c, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class RunnableC0043c implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        private e f951b;

        public RunnableC0043c(e eVar) {
            this.f951b = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (((androidx.appcompat.view.menu.b) c.this).f762d != null) {
                ((androidx.appcompat.view.menu.b) c.this).f762d.a();
            }
            View view = (View) ((androidx.appcompat.view.menu.b) c.this).i;
            if (view != null && view.getWindowToken() != null && this.f951b.f()) {
                c.this.y = this.f951b;
            }
            c.this.A = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class d extends AppCompatImageView implements ActionMenuView.a {

        /* loaded from: classes.dex */
        class a extends d0 {
            a(View view, c cVar) {
                super(view);
            }

            @Override // androidx.appcompat.widget.d0
            public androidx.appcompat.view.menu.q a() {
                e eVar = c.this.y;
                if (eVar == null) {
                    return null;
                }
                return eVar.b();
            }

            @Override // androidx.appcompat.widget.d0
            public boolean b() {
                c.this.k();
                return true;
            }

            @Override // androidx.appcompat.widget.d0
            public boolean c() {
                c cVar = c.this;
                if (cVar.A != null) {
                    return false;
                }
                cVar.g();
                return true;
            }
        }

        public d(Context context) {
            super(context, null, a.a.a.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            v0.a(this, getContentDescription());
            setOnTouchListener(new a(this, c.this));
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean b() {
            return false;
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean c() {
            return false;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            c.this.k();
            return true;
        }

        @Override // android.widget.ImageView
        protected boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int max = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                androidx.core.graphics.drawable.a.a(background, paddingLeft - max, paddingTop - max, paddingLeft + max, paddingTop + max);
            }
            return frame;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class e extends androidx.appcompat.view.menu.m {
        public e(Context context, androidx.appcompat.view.menu.g gVar, View view, boolean z) {
            super(context, gVar, view, z, a.a.a.actionOverflowMenuStyle);
            a(8388613);
            a(c.this.C);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.appcompat.view.menu.m
        public void d() {
            if (((androidx.appcompat.view.menu.b) c.this).f762d != null) {
                ((androidx.appcompat.view.menu.b) c.this).f762d.close();
            }
            c.this.y = null;
            super.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"BanParcelableUsage"})
    /* loaded from: classes.dex */
    public static class g implements Parcelable {
        public static final Parcelable.Creator<g> CREATOR = new a();

        /* renamed from: b, reason: collision with root package name */
        public int f955b;

        /* loaded from: classes.dex */
        static class a implements Parcelable.Creator<g> {
            a() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public g createFromParcel(Parcel parcel) {
                return new g(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public g[] newArray(int i) {
                return new g[i];
            }
        }

        g() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f955b);
        }

        g(Parcel parcel) {
            this.f955b = parcel.readInt();
        }
    }

    public c(Context context) {
        super(context, a.a.g.abc_action_menu_layout, a.a.g.abc_action_menu_item_layout);
        this.x = new SparseBooleanArray();
        this.C = new f();
    }

    public boolean g() {
        Object obj;
        RunnableC0043c runnableC0043c = this.A;
        if (runnableC0043c != null && (obj = this.i) != null) {
            ((View) obj).removeCallbacks(runnableC0043c);
            this.A = null;
            return true;
        }
        e eVar = this.y;
        if (eVar == null) {
            return false;
        }
        eVar.a();
        return true;
    }

    public boolean h() {
        a aVar = this.z;
        if (aVar == null) {
            return false;
        }
        aVar.a();
        return true;
    }

    public boolean i() {
        return this.A != null || j();
    }

    public boolean j() {
        e eVar = this.y;
        return eVar != null && eVar.c();
    }

    public boolean k() {
        androidx.appcompat.view.menu.g gVar;
        if (!this.n || j() || (gVar = this.f762d) == null || this.i == null || this.A != null || gVar.j().isEmpty()) {
            return false;
        }
        this.A = new RunnableC0043c(new e(this.f761c, this.f762d, this.k, true));
        ((View) this.i).post(this.A);
        super.a((androidx.appcompat.view.menu.s) null);
        return true;
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.n
    public void a(Context context, androidx.appcompat.view.menu.g gVar) {
        super.a(context, gVar);
        Resources resources = context.getResources();
        a.a.o.a a2 = a.a.o.a.a(context);
        if (!this.o) {
            this.n = a2.g();
        }
        if (!this.u) {
            this.p = a2.b();
        }
        if (!this.s) {
            this.r = a2.c();
        }
        int i = this.p;
        if (this.n) {
            if (this.k == null) {
                this.k = new d(this.f760b);
                if (this.m) {
                    this.k.setImageDrawable(this.l);
                    this.l = null;
                    this.m = false;
                }
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.k.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i -= this.k.getMeasuredWidth();
        } else {
            this.k = null;
        }
        this.q = i;
        this.w = (int) (resources.getDisplayMetrics().density * 56.0f);
    }

    public void b(boolean z) {
        this.v = z;
    }

    public void c(boolean z) {
        this.n = z;
        this.o = true;
    }

    public boolean e() {
        return g() | h();
    }

    public Drawable f() {
        d dVar = this.k;
        if (dVar != null) {
            return dVar.getDrawable();
        }
        if (this.m) {
            return this.l;
        }
        return null;
    }

    /* loaded from: classes.dex */
    private class f implements n.a {
        f() {
        }

        @Override // androidx.appcompat.view.menu.n.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            if (gVar == null) {
                return false;
            }
            c.this.D = ((androidx.appcompat.view.menu.s) gVar).getItem().getItemId();
            n.a d2 = c.this.d();
            if (d2 != null) {
                return d2.a(gVar);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.n.a
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
            if (gVar instanceof androidx.appcompat.view.menu.s) {
                gVar.m().a(false);
            }
            n.a d2 = c.this.d();
            if (d2 != null) {
                d2.a(gVar, z);
            }
        }
    }

    @Override // androidx.appcompat.view.menu.b
    public androidx.appcompat.view.menu.o b(ViewGroup viewGroup) {
        androidx.appcompat.view.menu.o oVar = this.i;
        androidx.appcompat.view.menu.o b2 = super.b(viewGroup);
        if (oVar != b2) {
            ((ActionMenuView) b2).setPresenter(this);
        }
        return b2;
    }

    @Override // androidx.appcompat.view.menu.n
    public Parcelable c() {
        g gVar = new g();
        gVar.f955b = this.D;
        return gVar;
    }

    @Override // androidx.appcompat.view.menu.n
    public boolean b() {
        ArrayList<androidx.appcompat.view.menu.j> arrayList;
        int i;
        int i2;
        int i3;
        int i4;
        c cVar = this;
        androidx.appcompat.view.menu.g gVar = cVar.f762d;
        View view = null;
        int i5 = 0;
        if (gVar != null) {
            arrayList = gVar.n();
            i = arrayList.size();
        } else {
            arrayList = null;
            i = 0;
        }
        int i6 = cVar.r;
        int i7 = cVar.q;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) cVar.i;
        int i8 = i6;
        boolean z = false;
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < i; i11++) {
            androidx.appcompat.view.menu.j jVar = arrayList.get(i11);
            if (jVar.k()) {
                i9++;
            } else if (jVar.j()) {
                i10++;
            } else {
                z = true;
            }
            if (cVar.v && jVar.isActionViewExpanded()) {
                i8 = 0;
            }
        }
        if (cVar.n && (z || i10 + i9 > i8)) {
            i8--;
        }
        int i12 = i8 - i9;
        SparseBooleanArray sparseBooleanArray = cVar.x;
        sparseBooleanArray.clear();
        if (cVar.t) {
            int i13 = cVar.w;
            i3 = i7 / i13;
            i2 = i13 + ((i7 % i13) / i3);
        } else {
            i2 = 0;
            i3 = 0;
        }
        int i14 = i7;
        int i15 = 0;
        int i16 = 0;
        while (i15 < i) {
            androidx.appcompat.view.menu.j jVar2 = arrayList.get(i15);
            if (jVar2.k()) {
                View a2 = cVar.a(jVar2, view, viewGroup);
                if (cVar.t) {
                    i3 -= ActionMenuView.a(a2, i2, i3, makeMeasureSpec, i5);
                } else {
                    a2.measure(makeMeasureSpec, makeMeasureSpec);
                }
                int measuredWidth = a2.getMeasuredWidth();
                i14 -= measuredWidth;
                if (i16 != 0) {
                    measuredWidth = i16;
                }
                int groupId = jVar2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                jVar2.d(true);
                i16 = measuredWidth;
                i4 = i;
            } else if (jVar2.j()) {
                int groupId2 = jVar2.getGroupId();
                boolean z2 = sparseBooleanArray.get(groupId2);
                boolean z3 = (i12 > 0 || z2) && i14 > 0 && (!cVar.t || i3 > 0);
                boolean z4 = z3;
                i4 = i;
                if (z3) {
                    View a3 = cVar.a(jVar2, null, viewGroup);
                    if (cVar.t) {
                        int a4 = ActionMenuView.a(a3, i2, i3, makeMeasureSpec, 0);
                        i3 -= a4;
                        z4 = a4 == 0 ? false : z4;
                    } else {
                        a3.measure(makeMeasureSpec, makeMeasureSpec);
                    }
                    int measuredWidth2 = a3.getMeasuredWidth();
                    i14 -= measuredWidth2;
                    if (i16 == 0) {
                        i16 = measuredWidth2;
                    }
                    z3 = z4 & (!cVar.t ? i14 + i16 <= 0 : i14 < 0);
                }
                if (z3 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z2) {
                    sparseBooleanArray.put(groupId2, false);
                    for (int i17 = 0; i17 < i15; i17++) {
                        androidx.appcompat.view.menu.j jVar3 = arrayList.get(i17);
                        if (jVar3.getGroupId() == groupId2) {
                            if (jVar3.h()) {
                                i12++;
                            }
                            jVar3.d(false);
                        }
                    }
                }
                if (z3) {
                    i12--;
                }
                jVar2.d(z3);
            } else {
                i4 = i;
                jVar2.d(false);
                i15++;
                i = i4;
                view = null;
                i5 = 0;
                cVar = this;
            }
            i15++;
            i = i4;
            view = null;
            i5 = 0;
            cVar = this;
        }
        return true;
    }

    public void a(Configuration configuration) {
        if (!this.s) {
            this.r = a.a.o.a.a(this.f761c).c();
        }
        androidx.appcompat.view.menu.g gVar = this.f762d;
        if (gVar != null) {
            gVar.b(true);
        }
    }

    public void a(Drawable drawable) {
        d dVar = this.k;
        if (dVar != null) {
            dVar.setImageDrawable(drawable);
        } else {
            this.m = true;
            this.l = drawable;
        }
    }

    @Override // androidx.appcompat.view.menu.b
    public View a(androidx.appcompat.view.menu.j jVar, View view, ViewGroup viewGroup) {
        View actionView = jVar.getActionView();
        if (actionView == null || jVar.f()) {
            actionView = super.a(jVar, view, viewGroup);
        }
        actionView.setVisibility(jVar.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // androidx.appcompat.view.menu.b
    public void a(androidx.appcompat.view.menu.j jVar, o.a aVar) {
        aVar.a(jVar, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.i);
        if (this.B == null) {
            this.B = new b();
        }
        actionMenuItemView.setPopupCallback(this.B);
    }

    @Override // androidx.appcompat.view.menu.b
    public boolean a(int i, androidx.appcompat.view.menu.j jVar) {
        return jVar.h();
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.n
    public void a(boolean z) {
        super.a(z);
        ((View) this.i).requestLayout();
        androidx.appcompat.view.menu.g gVar = this.f762d;
        boolean z2 = false;
        if (gVar != null) {
            ArrayList<androidx.appcompat.view.menu.j> c2 = gVar.c();
            int size = c2.size();
            for (int i = 0; i < size; i++) {
                a.f.l.b a2 = c2.get(i).a();
                if (a2 != null) {
                    a2.a(this);
                }
            }
        }
        androidx.appcompat.view.menu.g gVar2 = this.f762d;
        ArrayList<androidx.appcompat.view.menu.j> j = gVar2 != null ? gVar2.j() : null;
        if (this.n && j != null) {
            int size2 = j.size();
            if (size2 == 1) {
                z2 = !j.get(0).isActionViewExpanded();
            } else if (size2 > 0) {
                z2 = true;
            }
        }
        if (z2) {
            if (this.k == null) {
                this.k = new d(this.f760b);
            }
            ViewGroup viewGroup = (ViewGroup) this.k.getParent();
            if (viewGroup != this.i) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.k);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.i;
                actionMenuView.addView(this.k, actionMenuView.e());
            }
        } else {
            d dVar = this.k;
            if (dVar != null) {
                Object parent = dVar.getParent();
                Object obj = this.i;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.k);
                }
            }
        }
        ((ActionMenuView) this.i).setOverflowReserved(this.n);
    }

    @Override // androidx.appcompat.view.menu.b
    public boolean a(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.k) {
            return false;
        }
        return super.a(viewGroup, i);
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.n
    public boolean a(androidx.appcompat.view.menu.s sVar) {
        boolean z = false;
        if (!sVar.hasVisibleItems()) {
            return false;
        }
        androidx.appcompat.view.menu.s sVar2 = sVar;
        while (sVar2.t() != this.f762d) {
            sVar2 = (androidx.appcompat.view.menu.s) sVar2.t();
        }
        View a2 = a(sVar2.getItem());
        if (a2 == null) {
            return false;
        }
        this.D = sVar.getItem().getItemId();
        int size = sVar.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            MenuItem item = sVar.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
            i++;
        }
        this.z = new a(this.f761c, sVar, a2);
        this.z.a(z);
        this.z.e();
        super.a(sVar);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private View a(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.i;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof o.a) && ((o.a) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.n
    public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
        e();
        super.a(gVar, z);
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(Parcelable parcelable) {
        int i;
        MenuItem findItem;
        if ((parcelable instanceof g) && (i = ((g) parcelable).f955b) > 0 && (findItem = this.f762d.findItem(i)) != null) {
            a((androidx.appcompat.view.menu.s) findItem.getSubMenu());
        }
    }

    public void a(ActionMenuView actionMenuView) {
        this.i = actionMenuView;
        actionMenuView.a(this.f762d);
    }
}
