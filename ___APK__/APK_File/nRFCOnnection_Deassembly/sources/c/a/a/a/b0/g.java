package c.a.a.a.b0;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import c.a.a.a.b0.k;
import c.a.a.a.b0.l;
import c.a.a.a.b0.m;

/* loaded from: classes.dex */
public class g extends Drawable implements androidx.core.graphics.drawable.b, n {
    private static final Paint x = new Paint(1);

    /* renamed from: b, reason: collision with root package name */
    private c f2033b;

    /* renamed from: c, reason: collision with root package name */
    private final m.g[] f2034c;

    /* renamed from: d, reason: collision with root package name */
    private final m.g[] f2035d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f2036e;

    /* renamed from: f, reason: collision with root package name */
    private final Matrix f2037f;
    private final Path g;
    private final Path h;
    private final RectF i;
    private final RectF j;
    private final Region k;
    private final Region l;
    private k m;
    private final Paint n;
    private final Paint o;
    private final c.a.a.a.a0.a p;
    private final l.a q;
    private final l r;
    private PorterDuffColorFilter s;
    private PorterDuffColorFilter t;
    private Rect u;
    private final RectF v;
    private boolean w;

    /* loaded from: classes.dex */
    class a implements l.a {
        a() {
        }

        @Override // c.a.a.a.b0.l.a
        public void a(m mVar, Matrix matrix, int i) {
            g.this.f2035d[i] = mVar.a(matrix);
        }

        @Override // c.a.a.a.b0.l.a
        public void b(m mVar, Matrix matrix, int i) {
            g.this.f2034c[i] = mVar.a(matrix);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements k.c {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f2039a;

        b(g gVar, float f2) {
            this.f2039a = f2;
        }

        @Override // c.a.a.a.b0.k.c
        public c.a.a.a.b0.c a(c.a.a.a.b0.c cVar) {
            return cVar instanceof i ? cVar : new c.a.a.a.b0.b(this.f2039a, cVar);
        }
    }

    /* synthetic */ g(c cVar, a aVar) {
        this(cVar);
    }

    private boolean A() {
        PorterDuffColorFilter porterDuffColorFilter = this.s;
        PorterDuffColorFilter porterDuffColorFilter2 = this.t;
        c cVar = this.f2033b;
        this.s = a(cVar.g, cVar.h, this.n, true);
        c cVar2 = this.f2033b;
        this.t = a(cVar2.f2045f, cVar2.h, this.o, false);
        c cVar3 = this.f2033b;
        if (cVar3.u) {
            this.p.a(cVar3.g.getColorForState(getState(), 0));
        }
        return (a.f.k.c.a(porterDuffColorFilter, this.s) && a.f.k.c.a(porterDuffColorFilter2, this.t)) ? false : true;
    }

    private void B() {
        float p = p();
        this.f2033b.r = (int) Math.ceil(0.75f * p);
        this.f2033b.s = (int) Math.ceil(p * 0.25f);
        A();
        y();
    }

    private static int a(int i, int i2) {
        return (i * (i2 + (i2 >>> 7))) >>> 8;
    }

    private int c(int i) {
        float p = p() + g();
        c.a.a.a.u.a aVar = this.f2033b.f2041b;
        return aVar != null ? aVar.b(i, p) : i;
    }

    private void s() {
        this.m = k().a(new b(this, -u()));
        this.r.a(this.m, this.f2033b.k, t(), this.h);
    }

    private RectF t() {
        this.j.set(d());
        float u = u();
        this.j.inset(u, u);
        return this.j;
    }

    private float u() {
        if (x()) {
            return this.o.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    private boolean v() {
        c cVar = this.f2033b;
        int i = cVar.q;
        return i != 1 && cVar.r > 0 && (i == 2 || z());
    }

    private boolean w() {
        Paint.Style style = this.f2033b.v;
        return style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL;
    }

    private boolean x() {
        Paint.Style style = this.f2033b.v;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.o.getStrokeWidth() > 0.0f;
    }

    private void y() {
        super.invalidateSelf();
    }

    private boolean z() {
        return Build.VERSION.SDK_INT < 21 || !(r() || this.g.isConvex());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RectF d() {
        this.i.set(getBounds());
        return this.i;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.n.setColorFilter(this.s);
        int alpha = this.n.getAlpha();
        this.n.setAlpha(a(alpha, this.f2033b.m));
        this.o.setColorFilter(this.t);
        this.o.setStrokeWidth(this.f2033b.l);
        int alpha2 = this.o.getAlpha();
        this.o.setAlpha(a(alpha2, this.f2033b.m));
        if (this.f2036e) {
            s();
            b(d(), this.g);
            this.f2036e = false;
        }
        d(canvas);
        if (w()) {
            b(canvas);
        }
        if (x()) {
            c(canvas);
        }
        this.n.setAlpha(alpha);
        this.o.setAlpha(alpha2);
    }

    public void e(float f2) {
        this.f2033b.l = f2;
        invalidateSelf();
    }

    public ColorStateList f() {
        return this.f2033b.f2043d;
    }

    public float g() {
        return this.f2033b.n;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f2033b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.f2033b.q == 2) {
            return;
        }
        if (r()) {
            outline.setRoundRect(getBounds(), m());
        } else {
            b(d(), this.g);
            if (this.g.isConvex()) {
                outline.setConvexPath(this.g);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        Rect rect2 = this.u;
        if (rect2 != null) {
            rect.set(rect2);
            return true;
        }
        return super.getPadding(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        this.k.set(getBounds());
        b(d(), this.g);
        this.l.setPath(this.g, this.k);
        this.k.op(this.l, Region.Op.DIFFERENCE);
        return this.k;
    }

    public int h() {
        double d2 = this.f2033b.s;
        double sin = Math.sin(Math.toRadians(r0.t));
        Double.isNaN(d2);
        return (int) (d2 * sin);
    }

    public int i() {
        double d2 = this.f2033b.s;
        double cos = Math.cos(Math.toRadians(r0.t));
        Double.isNaN(d2);
        return (int) (d2 * cos);
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        this.f2036e = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        return super.isStateful() || ((colorStateList = this.f2033b.g) != null && colorStateList.isStateful()) || (((colorStateList2 = this.f2033b.f2045f) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.f2033b.f2044e) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.f2033b.f2043d) != null && colorStateList4.isStateful())));
    }

    public int j() {
        return this.f2033b.r;
    }

    public k k() {
        return this.f2033b.f2040a;
    }

    public ColorStateList l() {
        return this.f2033b.g;
    }

    public float m() {
        return this.f2033b.f2040a.j().a(d());
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.f2033b = new c(this.f2033b);
        return this;
    }

    public float n() {
        return this.f2033b.f2040a.l().a(d());
    }

    public float o() {
        return this.f2033b.p;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.f2036e = true;
        super.onBoundsChange(rect);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean z = a(iArr) || A();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    public float p() {
        return e() + o();
    }

    public boolean q() {
        c.a.a.a.u.a aVar = this.f2033b.f2041b;
        return aVar != null && aVar.a();
    }

    public boolean r() {
        return this.f2033b.f2040a.a(d());
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        c cVar = this.f2033b;
        if (cVar.m != i) {
            cVar.m = i;
            y();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f2033b.f2042c = colorFilter;
        y();
    }

    @Override // c.a.a.a.b0.n
    public void setShapeAppearanceModel(k kVar) {
        this.f2033b.f2040a = kVar;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        this.f2033b.g = colorStateList;
        A();
        y();
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        c cVar = this.f2033b;
        if (cVar.h != mode) {
            cVar.h = mode;
            A();
            y();
        }
    }

    public g() {
        this(new k());
    }

    public void b(ColorStateList colorStateList) {
        c cVar = this.f2033b;
        if (cVar.f2044e != colorStateList) {
            cVar.f2044e = colorStateList;
            onStateChange(getState());
        }
    }

    public g(Context context, AttributeSet attributeSet, int i, int i2) {
        this(k.a(context, attributeSet, i, i2).a());
    }

    public static g a(Context context, float f2) {
        int a2 = c.a.a.a.s.a.a(context, c.a.a.a.b.colorSurface, g.class.getSimpleName());
        g gVar = new g();
        gVar.a(context);
        gVar.a(ColorStateList.valueOf(a2));
        gVar.b(f2);
        return gVar;
    }

    public void d(float f2) {
        c cVar = this.f2033b;
        if (cVar.n != f2) {
            cVar.n = f2;
            B();
        }
    }

    public float e() {
        return this.f2033b.o;
    }

    public g(k kVar) {
        this(new c(kVar, null));
    }

    private void e(Canvas canvas) {
        int h = h();
        int i = i();
        if (Build.VERSION.SDK_INT < 21 && this.w) {
            Rect clipBounds = canvas.getClipBounds();
            int i2 = this.f2033b.r;
            clipBounds.inset(-i2, -i2);
            clipBounds.offset(h, i);
            canvas.clipRect(clipBounds, Region.Op.REPLACE);
        }
        canvas.translate(h, i);
    }

    public void c(float f2) {
        c cVar = this.f2033b;
        if (cVar.k != f2) {
            cVar.k = f2;
            this.f2036e = true;
            invalidateSelf();
        }
    }

    private g(c cVar) {
        this.f2034c = new m.g[4];
        this.f2035d = new m.g[4];
        this.f2037f = new Matrix();
        this.g = new Path();
        this.h = new Path();
        this.i = new RectF();
        this.j = new RectF();
        this.k = new Region();
        this.l = new Region();
        this.n = new Paint(1);
        this.o = new Paint(1);
        this.p = new c.a.a.a.a0.a();
        this.r = new l();
        this.v = new RectF();
        this.w = true;
        this.f2033b = cVar;
        this.o.setStyle(Paint.Style.STROKE);
        this.n.setStyle(Paint.Style.FILL);
        x.setColor(-1);
        x.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        A();
        a(getState());
        this.q = new a();
    }

    public void b(float f2) {
        c cVar = this.f2033b;
        if (cVar.o != f2) {
            cVar.o = f2;
            B();
        }
    }

    private void d(Canvas canvas) {
        if (v()) {
            canvas.save();
            e(canvas);
            if (!this.w) {
                a(canvas);
                canvas.restore();
                return;
            }
            int width = (int) (this.v.width() - getBounds().width());
            int height = (int) (this.v.height() - getBounds().height());
            Bitmap createBitmap = Bitmap.createBitmap(((int) this.v.width()) + (this.f2033b.r * 2) + width, ((int) this.v.height()) + (this.f2033b.r * 2) + height, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap);
            float f2 = (getBounds().left - this.f2033b.r) - width;
            float f3 = (getBounds().top - this.f2033b.r) - height;
            canvas2.translate(-f2, -f3);
            a(canvas2);
            canvas.drawBitmap(createBitmap, f2, f3, (Paint) null);
            createBitmap.recycle();
            canvas.restore();
        }
    }

    private void c(Canvas canvas) {
        a(canvas, this.o, this.h, this.m, t());
    }

    public void b(int i) {
        c cVar = this.f2033b;
        if (cVar.t != i) {
            cVar.t = i;
            y();
        }
    }

    public void a(ColorStateList colorStateList) {
        c cVar = this.f2033b;
        if (cVar.f2043d != colorStateList) {
            cVar.f2043d = colorStateList;
            onStateChange(getState());
        }
    }

    private void b(Canvas canvas) {
        a(canvas, this.n, this.g, this.f2033b.f2040a, d());
    }

    public float c() {
        return this.f2033b.f2040a.e().a(d());
    }

    private void b(RectF rectF, Path path) {
        a(rectF, path);
        if (this.f2033b.j != 1.0f) {
            this.f2037f.reset();
            Matrix matrix = this.f2037f;
            float f2 = this.f2033b.j;
            matrix.setScale(f2, f2, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.f2037f);
        }
        path.computeBounds(this.v, true);
    }

    public void a(float f2, int i) {
        e(f2);
        b(ColorStateList.valueOf(i));
    }

    public void a(float f2, ColorStateList colorStateList) {
        e(f2);
        b(colorStateList);
    }

    public void a(float f2) {
        setShapeAppearanceModel(this.f2033b.f2040a.a(f2));
    }

    public void a(int i, int i2, int i3, int i4) {
        c cVar = this.f2033b;
        if (cVar.i == null) {
            cVar.i = new Rect();
        }
        this.f2033b.i.set(i, i2, i3, i4);
        this.u = this.f2033b.i;
        invalidateSelf();
    }

    public float b() {
        return this.f2033b.f2040a.c().a(d());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class c extends Drawable.ConstantState {

        /* renamed from: a, reason: collision with root package name */
        public k f2040a;

        /* renamed from: b, reason: collision with root package name */
        public c.a.a.a.u.a f2041b;

        /* renamed from: c, reason: collision with root package name */
        public ColorFilter f2042c;

        /* renamed from: d, reason: collision with root package name */
        public ColorStateList f2043d;

        /* renamed from: e, reason: collision with root package name */
        public ColorStateList f2044e;

        /* renamed from: f, reason: collision with root package name */
        public ColorStateList f2045f;
        public ColorStateList g;
        public PorterDuff.Mode h;
        public Rect i;
        public float j;
        public float k;
        public float l;
        public int m;
        public float n;
        public float o;
        public float p;
        public int q;
        public int r;
        public int s;
        public int t;
        public boolean u;
        public Paint.Style v;

        public c(k kVar, c.a.a.a.u.a aVar) {
            this.f2043d = null;
            this.f2044e = null;
            this.f2045f = null;
            this.g = null;
            this.h = PorterDuff.Mode.SRC_IN;
            this.i = null;
            this.j = 1.0f;
            this.k = 1.0f;
            this.m = 255;
            this.n = 0.0f;
            this.o = 0.0f;
            this.p = 0.0f;
            this.q = 0;
            this.r = 0;
            this.s = 0;
            this.t = 0;
            this.u = false;
            this.v = Paint.Style.FILL_AND_STROKE;
            this.f2040a = kVar;
            this.f2041b = aVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            g gVar = new g(this, null);
            gVar.f2036e = true;
            return gVar;
        }

        public c(c cVar) {
            this.f2043d = null;
            this.f2044e = null;
            this.f2045f = null;
            this.g = null;
            this.h = PorterDuff.Mode.SRC_IN;
            this.i = null;
            this.j = 1.0f;
            this.k = 1.0f;
            this.m = 255;
            this.n = 0.0f;
            this.o = 0.0f;
            this.p = 0.0f;
            this.q = 0;
            this.r = 0;
            this.s = 0;
            this.t = 0;
            this.u = false;
            this.v = Paint.Style.FILL_AND_STROKE;
            this.f2040a = cVar.f2040a;
            this.f2041b = cVar.f2041b;
            this.l = cVar.l;
            this.f2042c = cVar.f2042c;
            this.f2043d = cVar.f2043d;
            this.f2044e = cVar.f2044e;
            this.h = cVar.h;
            this.g = cVar.g;
            this.m = cVar.m;
            this.j = cVar.j;
            this.s = cVar.s;
            this.q = cVar.q;
            this.u = cVar.u;
            this.k = cVar.k;
            this.n = cVar.n;
            this.o = cVar.o;
            this.p = cVar.p;
            this.r = cVar.r;
            this.t = cVar.t;
            this.f2045f = cVar.f2045f;
            this.v = cVar.v;
            Rect rect = cVar.i;
            if (rect != null) {
                this.i = new Rect(rect);
            }
        }
    }

    public void a(Context context) {
        this.f2033b.f2041b = new c.a.a.a.u.a(context);
        B();
    }

    public void a(int i) {
        this.p.a(i);
        this.f2033b.u = false;
        y();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Canvas canvas, Paint paint, Path path, RectF rectF) {
        a(canvas, paint, path, this.f2033b.f2040a, rectF);
    }

    private void a(Canvas canvas, Paint paint, Path path, k kVar, RectF rectF) {
        if (kVar.a(rectF)) {
            float a2 = kVar.l().a(rectF);
            canvas.drawRoundRect(rectF, a2, a2, paint);
        } else {
            canvas.drawPath(path, paint);
        }
    }

    private void a(Canvas canvas) {
        if (this.f2033b.s != 0) {
            canvas.drawPath(this.g, this.p.a());
        }
        for (int i = 0; i < 4; i++) {
            this.f2034c[i].a(this.p, this.f2033b.r, canvas);
            this.f2035d[i].a(this.p, this.f2033b.r, canvas);
        }
        if (this.w) {
            int h = h();
            int i2 = i();
            canvas.translate(-h, -i2);
            canvas.drawPath(this.g, x);
            canvas.translate(h, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(RectF rectF, Path path) {
        l lVar = this.r;
        c cVar = this.f2033b;
        lVar.a(cVar.f2040a, cVar.k, rectF, this.q, path);
    }

    private PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode, Paint paint, boolean z) {
        if (colorStateList != null && mode != null) {
            return a(colorStateList, mode, z);
        }
        return a(paint, z);
    }

    private PorterDuffColorFilter a(Paint paint, boolean z) {
        int color;
        int c2;
        if (!z || (c2 = c((color = paint.getColor()))) == color) {
            return null;
        }
        return new PorterDuffColorFilter(c2, PorterDuff.Mode.SRC_IN);
    }

    private PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode, boolean z) {
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z) {
            colorForState = c(colorForState);
        }
        return new PorterDuffColorFilter(colorForState, mode);
    }

    private boolean a(int[] iArr) {
        boolean z;
        int color;
        int colorForState;
        int color2;
        int colorForState2;
        if (this.f2033b.f2043d == null || color2 == (colorForState2 = this.f2033b.f2043d.getColorForState(iArr, (color2 = this.n.getColor())))) {
            z = false;
        } else {
            this.n.setColor(colorForState2);
            z = true;
        }
        if (this.f2033b.f2044e == null || color == (colorForState = this.f2033b.f2044e.getColorForState(iArr, (color = this.o.getColor())))) {
            return z;
        }
        this.o.setColor(colorForState);
        return true;
    }
}
