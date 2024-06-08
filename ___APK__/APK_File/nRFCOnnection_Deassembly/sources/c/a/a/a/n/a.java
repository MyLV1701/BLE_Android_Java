package c.a.a.a.n;

import a.f.l.w;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import c.a.a.a.b0.g;
import c.a.a.a.i;
import c.a.a.a.j;
import c.a.a.a.y.c;
import c.a.a.a.y.d;
import com.google.android.material.internal.k;
import com.google.android.material.internal.l;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class a extends Drawable implements k.b {
    private static final int r = c.a.a.a.k.Widget_MaterialComponents_Badge;
    private static final int s = c.a.a.a.b.badgeStyle;

    /* renamed from: b, reason: collision with root package name */
    private final WeakReference<Context> f2116b;

    /* renamed from: c, reason: collision with root package name */
    private final g f2117c;

    /* renamed from: d, reason: collision with root package name */
    private final k f2118d;

    /* renamed from: e, reason: collision with root package name */
    private final Rect f2119e;

    /* renamed from: f, reason: collision with root package name */
    private final float f2120f;
    private final float g;
    private final float h;
    private final C0066a i;
    private float j;
    private float k;
    private int l;
    private float m;
    private float n;
    private float o;
    private WeakReference<View> p;
    private WeakReference<ViewGroup> q;

    /* renamed from: c.a.a.a.n.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0066a implements Parcelable {
        public static final Parcelable.Creator<C0066a> CREATOR = new C0067a();

        /* renamed from: b, reason: collision with root package name */
        private int f2121b;

        /* renamed from: c, reason: collision with root package name */
        private int f2122c;

        /* renamed from: d, reason: collision with root package name */
        private int f2123d;

        /* renamed from: e, reason: collision with root package name */
        private int f2124e;

        /* renamed from: f, reason: collision with root package name */
        private int f2125f;
        private CharSequence g;
        private int h;
        private int i;
        private int j;
        private int k;

        /* renamed from: c.a.a.a.n.a$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        static class C0067a implements Parcelable.Creator<C0066a> {
            C0067a() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public C0066a createFromParcel(Parcel parcel) {
                return new C0066a(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public C0066a[] newArray(int i) {
                return new C0066a[i];
            }
        }

        public C0066a(Context context) {
            this.f2123d = 255;
            this.f2124e = -1;
            this.f2122c = new d(context, c.a.a.a.k.TextAppearance_MaterialComponents_Badge).f2152b.getDefaultColor();
            this.g = context.getString(j.mtrl_badge_numberless_content_description);
            this.h = i.mtrl_badge_content_description;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f2121b);
            parcel.writeInt(this.f2122c);
            parcel.writeInt(this.f2123d);
            parcel.writeInt(this.f2124e);
            parcel.writeInt(this.f2125f);
            parcel.writeString(this.g.toString());
            parcel.writeInt(this.h);
            parcel.writeInt(this.i);
            parcel.writeInt(this.j);
            parcel.writeInt(this.k);
        }

        protected C0066a(Parcel parcel) {
            this.f2123d = 255;
            this.f2124e = -1;
            this.f2121b = parcel.readInt();
            this.f2122c = parcel.readInt();
            this.f2123d = parcel.readInt();
            this.f2124e = parcel.readInt();
            this.f2125f = parcel.readInt();
            this.g = parcel.readString();
            this.h = parcel.readInt();
            this.i = parcel.readInt();
            this.j = parcel.readInt();
            this.k = parcel.readInt();
        }
    }

    private a(Context context) {
        this.f2116b = new WeakReference<>(context);
        l.b(context);
        Resources resources = context.getResources();
        this.f2119e = new Rect();
        this.f2117c = new g();
        this.f2120f = resources.getDimensionPixelSize(c.a.a.a.d.mtrl_badge_radius);
        this.h = resources.getDimensionPixelSize(c.a.a.a.d.mtrl_badge_long_text_horizontal_padding);
        this.g = resources.getDimensionPixelSize(c.a.a.a.d.mtrl_badge_with_text_radius);
        this.f2118d = new k(this);
        this.f2118d.b().setTextAlign(Paint.Align.CENTER);
        this.i = new C0066a(context);
        h(c.a.a.a.k.TextAppearance_MaterialComponents_Badge);
    }

    public static a a(Context context) {
        return a(context, null, s, r);
    }

    private void b(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray c2 = l.c(context, attributeSet, c.a.a.a.l.Badge, i, i2, new int[0]);
        e(c2.getInt(c.a.a.a.l.Badge_maxCharacterCount, 4));
        if (c2.hasValue(c.a.a.a.l.Badge_number)) {
            f(c2.getInt(c.a.a.a.l.Badge_number, 0));
        }
        a(a(context, c2, c.a.a.a.l.Badge_backgroundColor));
        if (c2.hasValue(c.a.a.a.l.Badge_badgeTextColor)) {
            c(a(context, c2, c.a.a.a.l.Badge_badgeTextColor));
        }
        b(c2.getInt(c.a.a.a.l.Badge_badgeGravity, 8388661));
        d(c2.getDimensionPixelOffset(c.a.a.a.l.Badge_horizontalOffset, 0));
        g(c2.getDimensionPixelOffset(c.a.a.a.l.Badge_verticalOffset, 0));
        c2.recycle();
    }

    private void h(int i) {
        Context context = this.f2116b.get();
        if (context == null) {
            return;
        }
        a(new d(context, i));
    }

    public void c(int i) {
        this.i.f2122c = i;
        if (this.f2118d.b().getColor() != i) {
            this.f2118d.b().setColor(i);
            invalidateSelf();
        }
    }

    public int d() {
        if (e()) {
            return this.i.f2124e;
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (getBounds().isEmpty() || getAlpha() == 0 || !isVisible()) {
            return;
        }
        this.f2117c.draw(canvas);
        if (e()) {
            a(canvas);
        }
    }

    public boolean e() {
        return this.i.f2124e != -1;
    }

    public void f(int i) {
        int max = Math.max(0, i);
        if (this.i.f2124e != max) {
            this.i.f2124e = max;
            this.f2118d.a(true);
            g();
            invalidateSelf();
        }
    }

    public void g(int i) {
        this.i.k = i;
        g();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.i.f2123d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f2119e.height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f2119e.width();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return false;
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.k.b
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.i.f2123d = i;
        this.f2118d.b().setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    private static a a(Context context, AttributeSet attributeSet, int i, int i2) {
        a aVar = new a(context);
        aVar.b(context, attributeSet, i, i2);
        return aVar;
    }

    public void e(int i) {
        if (this.i.f2125f != i) {
            this.i.f2125f = i;
            h();
            this.f2118d.a(true);
            g();
            invalidateSelf();
        }
    }

    private void g() {
        Context context = this.f2116b.get();
        WeakReference<View> weakReference = this.p;
        View view = weakReference != null ? weakReference.get() : null;
        if (context == null || view == null) {
            return;
        }
        Rect rect = new Rect();
        rect.set(this.f2119e);
        Rect rect2 = new Rect();
        view.getDrawingRect(rect2);
        WeakReference<ViewGroup> weakReference2 = this.q;
        ViewGroup viewGroup = weakReference2 != null ? weakReference2.get() : null;
        if (viewGroup != null || b.f2126a) {
            if (viewGroup == null) {
                viewGroup = (ViewGroup) view.getParent();
            }
            viewGroup.offsetDescendantRectToMyCoords(view, rect2);
        }
        a(context, rect2, view);
        b.a(this.f2119e, this.j, this.k, this.n, this.o);
        this.f2117c.a(this.m);
        if (rect.equals(this.f2119e)) {
            return;
        }
        this.f2117c.setBounds(this.f2119e);
    }

    private void h() {
        Double.isNaN(c());
        this.l = ((int) Math.pow(10.0d, r0 - 1.0d)) - 1;
    }

    public void d(int i) {
        this.i.j = i;
        g();
    }

    private static int a(Context context, TypedArray typedArray, int i) {
        return c.a(context, typedArray, i).getDefaultColor();
    }

    public void a(View view, ViewGroup viewGroup) {
        this.p = new WeakReference<>(view);
        this.q = new WeakReference<>(viewGroup);
        g();
        invalidateSelf();
    }

    public int c() {
        return this.i.f2125f;
    }

    private String f() {
        if (d() <= this.l) {
            return Integer.toString(d());
        }
        Context context = this.f2116b.get();
        return context == null ? "" : context.getString(j.mtrl_exceed_max_badge_number_suffix, Integer.valueOf(this.l), "+");
    }

    public void a(int i) {
        this.i.f2121b = i;
        ColorStateList valueOf = ColorStateList.valueOf(i);
        if (this.f2117c.f() != valueOf) {
            this.f2117c.a(valueOf);
            invalidateSelf();
        }
    }

    @Override // com.google.android.material.internal.k.b
    public void a() {
        invalidateSelf();
    }

    private void a(d dVar) {
        Context context;
        if (this.f2118d.a() == dVar || (context = this.f2116b.get()) == null) {
            return;
        }
        this.f2118d.a(dVar, context);
        g();
    }

    public void b(int i) {
        if (this.i.i != i) {
            this.i.i = i;
            WeakReference<View> weakReference = this.p;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            View view = this.p.get();
            WeakReference<ViewGroup> weakReference2 = this.q;
            a(view, weakReference2 != null ? weakReference2.get() : null);
        }
    }

    private void a(Context context, Rect rect, View view) {
        int i = this.i.i;
        if (i == 8388691 || i == 8388693) {
            this.k = rect.bottom - this.i.k;
        } else {
            this.k = rect.top + this.i.k;
        }
        if (d() <= 9) {
            this.m = !e() ? this.f2120f : this.g;
            float f2 = this.m;
            this.o = f2;
            this.n = f2;
        } else {
            this.m = this.g;
            this.o = this.m;
            this.n = (this.f2118d.a(f()) / 2.0f) + this.h;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(e() ? c.a.a.a.d.mtrl_badge_text_horizontal_edge_offset : c.a.a.a.d.mtrl_badge_horizontal_edge_offset);
        int i2 = this.i.i;
        if (i2 != 8388659 && i2 != 8388691) {
            this.j = w.q(view) == 0 ? ((rect.right + this.n) - dimensionPixelSize) - this.i.j : (rect.left - this.n) + dimensionPixelSize + this.i.j;
        } else {
            this.j = w.q(view) == 0 ? (rect.left - this.n) + dimensionPixelSize + this.i.j : ((rect.right + this.n) - dimensionPixelSize) - this.i.j;
        }
    }

    public CharSequence b() {
        Context context;
        if (!isVisible()) {
            return null;
        }
        if (e()) {
            if (this.i.h <= 0 || (context = this.f2116b.get()) == null) {
                return null;
            }
            return context.getResources().getQuantityString(this.i.h, d(), Integer.valueOf(d()));
        }
        return this.i.g;
    }

    private void a(Canvas canvas) {
        Rect rect = new Rect();
        String f2 = f();
        this.f2118d.b().getTextBounds(f2, 0, f2.length(), rect);
        canvas.drawText(f2, this.j, this.k + (rect.height() / 2), this.f2118d.b());
    }
}
