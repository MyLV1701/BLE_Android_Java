package a.o.a.a;

import a.f.e.b;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import no.nordicsemi.android.dfu.DfuBaseService;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class i extends a.o.a.a.h {
    static final PorterDuff.Mode k = PorterDuff.Mode.SRC_IN;

    /* renamed from: c, reason: collision with root package name */
    private h f559c;

    /* renamed from: d, reason: collision with root package name */
    private PorterDuffColorFilter f560d;

    /* renamed from: e, reason: collision with root package name */
    private ColorFilter f561e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f562f;
    private boolean g;
    private final float[] h;
    private final Matrix i;
    private final Rect j;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b extends f {
        b() {
        }

        public void a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (a.f.d.d.g.a(xmlPullParser, "pathData")) {
                TypedArray a2 = a.f.d.d.g.a(resources, theme, attributeSet, a.o.a.a.a.f540d);
                a(a2, xmlPullParser);
                a2.recycle();
            }
        }

        @Override // a.o.a.a.i.f
        public boolean b() {
            return true;
        }

        b(b bVar) {
            super(bVar);
        }

        private void a(TypedArray typedArray, XmlPullParser xmlPullParser) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.f572b = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.f571a = a.f.e.b.a(string2);
            }
            this.f573c = a.f.d.d.g.b(typedArray, xmlPullParser, "fillType", 2, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class e {
        private e() {
        }

        public boolean a() {
            return false;
        }

        public boolean a(int[] iArr) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class h extends Drawable.ConstantState {

        /* renamed from: a, reason: collision with root package name */
        int f581a;

        /* renamed from: b, reason: collision with root package name */
        g f582b;

        /* renamed from: c, reason: collision with root package name */
        ColorStateList f583c;

        /* renamed from: d, reason: collision with root package name */
        PorterDuff.Mode f584d;

        /* renamed from: e, reason: collision with root package name */
        boolean f585e;

        /* renamed from: f, reason: collision with root package name */
        Bitmap f586f;
        ColorStateList g;
        PorterDuff.Mode h;
        int i;
        boolean j;
        boolean k;
        Paint l;

        public h(h hVar) {
            this.f583c = null;
            this.f584d = i.k;
            if (hVar != null) {
                this.f581a = hVar.f581a;
                this.f582b = new g(hVar.f582b);
                Paint paint = hVar.f582b.f579e;
                if (paint != null) {
                    this.f582b.f579e = new Paint(paint);
                }
                Paint paint2 = hVar.f582b.f578d;
                if (paint2 != null) {
                    this.f582b.f578d = new Paint(paint2);
                }
                this.f583c = hVar.f583c;
                this.f584d = hVar.f584d;
                this.f585e = hVar.f585e;
            }
        }

        public void a(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f586f, (Rect) null, rect, a(colorFilter));
        }

        public boolean b() {
            return this.f582b.getRootAlpha() < 255;
        }

        public void c(int i, int i2) {
            this.f586f.eraseColor(0);
            this.f582b.a(new Canvas(this.f586f), i, i2, (ColorFilter) null);
        }

        public void d() {
            this.g = this.f583c;
            this.h = this.f584d;
            this.i = this.f582b.getRootAlpha();
            this.j = this.f585e;
            this.k = false;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f581a;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new i(this);
        }

        public void b(int i, int i2) {
            if (this.f586f == null || !a(i, i2)) {
                this.f586f = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                this.k = true;
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new i(this);
        }

        public Paint a(ColorFilter colorFilter) {
            if (!b() && colorFilter == null) {
                return null;
            }
            if (this.l == null) {
                this.l = new Paint();
                this.l.setFilterBitmap(true);
            }
            this.l.setAlpha(this.f582b.getRootAlpha());
            this.l.setColorFilter(colorFilter);
            return this.l;
        }

        public boolean c() {
            return this.f582b.a();
        }

        public boolean a(int i, int i2) {
            return i == this.f586f.getWidth() && i2 == this.f586f.getHeight();
        }

        public boolean a() {
            return !this.k && this.g == this.f583c && this.h == this.f584d && this.j == this.f585e && this.i == this.f582b.getRootAlpha();
        }

        public h() {
            this.f583c = null;
            this.f584d = i.k;
            this.f582b = new g();
        }

        public boolean a(int[] iArr) {
            boolean a2 = this.f582b.a(iArr);
            this.k |= a2;
            return a2;
        }
    }

    i() {
        this.g = true;
        this.h = new float[9];
        this.i = new Matrix();
        this.j = new Rect();
        this.f559c = new h();
    }

    public static i createFromXmlInner(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        i iVar = new i();
        iVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return iVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object a(String str) {
        return this.f559c.f582b.p.get(str);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f558b;
        if (drawable == null) {
            return false;
        }
        androidx.core.graphics.drawable.a.a(drawable);
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.j);
        if (this.j.width() <= 0 || this.j.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.f561e;
        if (colorFilter == null) {
            colorFilter = this.f560d;
        }
        canvas.getMatrix(this.i);
        this.i.getValues(this.h);
        float abs = Math.abs(this.h[0]);
        float abs2 = Math.abs(this.h[4]);
        float abs3 = Math.abs(this.h[1]);
        float abs4 = Math.abs(this.h[3]);
        if (abs3 != 0.0f || abs4 != 0.0f) {
            abs = 1.0f;
            abs2 = 1.0f;
        }
        int min = Math.min(DfuBaseService.ERROR_REMOTE_TYPE_SECURE_BUTTONLESS, (int) (this.j.width() * abs));
        int min2 = Math.min(DfuBaseService.ERROR_REMOTE_TYPE_SECURE_BUTTONLESS, (int) (this.j.height() * abs2));
        if (min <= 0 || min2 <= 0) {
            return;
        }
        int save = canvas.save();
        Rect rect = this.j;
        canvas.translate(rect.left, rect.top);
        if (a()) {
            canvas.translate(this.j.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        this.j.offsetTo(0, 0);
        this.f559c.b(min, min2);
        if (!this.g) {
            this.f559c.c(min, min2);
        } else if (!this.f559c.a()) {
            this.f559c.c(min, min2);
            this.f559c.d();
        }
        this.f559c.a(canvas, colorFilter, this.j);
        canvas.restoreToCount(save);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return androidx.core.graphics.drawable.a.c(drawable);
        }
        return this.f559c.f582b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.f559c.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return androidx.core.graphics.drawable.a.d(drawable);
        }
        return this.f561e;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f558b;
        if (drawable != null && Build.VERSION.SDK_INT >= 24) {
            return new C0035i(drawable.getConstantState());
        }
        this.f559c.f581a = getChangingConfigurations();
        return this.f559c;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return (int) this.f559c.f582b.j;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return (int) this.f559c.f582b.i;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return androidx.core.graphics.drawable.a.f(drawable);
        }
        return this.f559c.f585e;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        h hVar;
        ColorStateList colorStateList;
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return drawable.isStateful();
        }
        return super.isStateful() || ((hVar = this.f559c) != null && (hVar.c() || ((colorStateList = this.f559c.f583c) != null && colorStateList.isStateful())));
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.f562f && super.mutate() == this) {
            this.f559c = new h(this.f559c);
            this.f562f = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        boolean z = false;
        h hVar = this.f559c;
        ColorStateList colorStateList = hVar.f583c;
        if (colorStateList != null && (mode = hVar.f584d) != null) {
            this.f560d = a(this.f560d, colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        if (!hVar.c() || !hVar.a(iArr)) {
            return z;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else if (this.f559c.f582b.getRootAlpha() != i) {
            this.f559c.f582b.setRootAlpha(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, z);
        } else {
            this.f559c.f585e = z;
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.b(drawable, i);
        } else {
            setTintList(ColorStateList.valueOf(i));
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, colorStateList);
            return;
        }
        h hVar = this.f559c;
        if (hVar.f583c != colorStateList) {
            hVar.f583c = colorStateList;
            this.f560d = a(this.f560d, colorStateList, hVar.f584d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, mode);
            return;
        }
        h hVar = this.f559c;
        if (hVar.f584d != mode) {
            hVar.f584d = mode;
            this.f560d = a(this.f560d, hVar.f583c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    /* renamed from: a.o.a.a.i$i, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0035i extends Drawable.ConstantState {

        /* renamed from: a, reason: collision with root package name */
        private final Drawable.ConstantState f587a;

        public C0035i(Drawable.ConstantState constantState) {
            this.f587a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f587a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f587a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            i iVar = new i();
            iVar.f558b = (VectorDrawable) this.f587a.newDrawable();
            return iVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            i iVar = new i();
            iVar.f558b = (VectorDrawable) this.f587a.newDrawable(resources);
            return iVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            i iVar = new i();
            iVar.f558b = (VectorDrawable) this.f587a.newDrawable(resources, theme);
            return iVar;
        }
    }

    PorterDuffColorFilter a(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f561e = colorFilter;
            invalidateSelf();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c extends f {

        /* renamed from: e, reason: collision with root package name */
        private int[] f563e;

        /* renamed from: f, reason: collision with root package name */
        a.f.d.d.b f564f;
        float g;
        a.f.d.d.b h;
        float i;
        float j;
        float k;
        float l;
        float m;
        Paint.Cap n;
        Paint.Join o;
        float p;

        c() {
            this.g = 0.0f;
            this.i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
        }

        private Paint.Cap a(int i, Paint.Cap cap) {
            if (i == 0) {
                return Paint.Cap.BUTT;
            }
            if (i != 1) {
                return i != 2 ? cap : Paint.Cap.SQUARE;
            }
            return Paint.Cap.ROUND;
        }

        float getFillAlpha() {
            return this.j;
        }

        int getFillColor() {
            return this.h.a();
        }

        float getStrokeAlpha() {
            return this.i;
        }

        int getStrokeColor() {
            return this.f564f.a();
        }

        float getStrokeWidth() {
            return this.g;
        }

        float getTrimPathEnd() {
            return this.l;
        }

        float getTrimPathOffset() {
            return this.m;
        }

        float getTrimPathStart() {
            return this.k;
        }

        void setFillAlpha(float f2) {
            this.j = f2;
        }

        void setFillColor(int i) {
            this.h.a(i);
        }

        void setStrokeAlpha(float f2) {
            this.i = f2;
        }

        void setStrokeColor(int i) {
            this.f564f.a(i);
        }

        void setStrokeWidth(float f2) {
            this.g = f2;
        }

        void setTrimPathEnd(float f2) {
            this.l = f2;
        }

        void setTrimPathOffset(float f2) {
            this.m = f2;
        }

        void setTrimPathStart(float f2) {
            this.k = f2;
        }

        private Paint.Join a(int i, Paint.Join join) {
            if (i == 0) {
                return Paint.Join.MITER;
            }
            if (i != 1) {
                return i != 2 ? join : Paint.Join.BEVEL;
            }
            return Paint.Join.ROUND;
        }

        public void a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray a2 = a.f.d.d.g.a(resources, theme, attributeSet, a.o.a.a.a.f539c);
            a(a2, xmlPullParser, theme);
            a2.recycle();
        }

        private void a(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
            this.f563e = null;
            if (a.f.d.d.g.a(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.f572b = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.f571a = a.f.e.b.a(string2);
                }
                this.h = a.f.d.d.g.a(typedArray, xmlPullParser, theme, "fillColor", 1, 0);
                this.j = a.f.d.d.g.a(typedArray, xmlPullParser, "fillAlpha", 12, this.j);
                this.n = a(a.f.d.d.g.b(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.n);
                this.o = a(a.f.d.d.g.b(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.o);
                this.p = a.f.d.d.g.a(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.p);
                this.f564f = a.f.d.d.g.a(typedArray, xmlPullParser, theme, "strokeColor", 3, 0);
                this.i = a.f.d.d.g.a(typedArray, xmlPullParser, "strokeAlpha", 11, this.i);
                this.g = a.f.d.d.g.a(typedArray, xmlPullParser, "strokeWidth", 4, this.g);
                this.l = a.f.d.d.g.a(typedArray, xmlPullParser, "trimPathEnd", 6, this.l);
                this.m = a.f.d.d.g.a(typedArray, xmlPullParser, "trimPathOffset", 7, this.m);
                this.k = a.f.d.d.g.a(typedArray, xmlPullParser, "trimPathStart", 5, this.k);
                this.f573c = a.f.d.d.g.b(typedArray, xmlPullParser, "fillType", 13, this.f573c);
            }
        }

        c(c cVar) {
            super(cVar);
            this.g = 0.0f;
            this.i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
            this.f563e = cVar.f563e;
            this.f564f = cVar.f564f;
            this.g = cVar.g;
            this.i = cVar.i;
            this.h = cVar.h;
            this.f573c = cVar.f573c;
            this.j = cVar.j;
            this.k = cVar.k;
            this.l = cVar.l;
            this.m = cVar.m;
            this.n = cVar.n;
            this.o = cVar.o;
            this.p = cVar.p;
        }

        @Override // a.o.a.a.i.e
        public boolean a() {
            return this.h.d() || this.f564f.d();
        }

        @Override // a.o.a.a.i.e
        public boolean a(int[] iArr) {
            return this.f564f.a(iArr) | this.h.a(iArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d extends e {

        /* renamed from: a, reason: collision with root package name */
        final Matrix f565a;

        /* renamed from: b, reason: collision with root package name */
        final ArrayList<e> f566b;

        /* renamed from: c, reason: collision with root package name */
        float f567c;

        /* renamed from: d, reason: collision with root package name */
        private float f568d;

        /* renamed from: e, reason: collision with root package name */
        private float f569e;

        /* renamed from: f, reason: collision with root package name */
        private float f570f;
        private float g;
        private float h;
        private float i;
        final Matrix j;
        int k;
        private int[] l;
        private String m;

        public d(d dVar, a.d.a<String, Object> aVar) {
            super();
            f bVar;
            this.f565a = new Matrix();
            this.f566b = new ArrayList<>();
            this.f567c = 0.0f;
            this.f568d = 0.0f;
            this.f569e = 0.0f;
            this.f570f = 1.0f;
            this.g = 1.0f;
            this.h = 0.0f;
            this.i = 0.0f;
            this.j = new Matrix();
            this.m = null;
            this.f567c = dVar.f567c;
            this.f568d = dVar.f568d;
            this.f569e = dVar.f569e;
            this.f570f = dVar.f570f;
            this.g = dVar.g;
            this.h = dVar.h;
            this.i = dVar.i;
            this.l = dVar.l;
            this.m = dVar.m;
            this.k = dVar.k;
            String str = this.m;
            if (str != null) {
                aVar.put(str, this);
            }
            this.j.set(dVar.j);
            ArrayList<e> arrayList = dVar.f566b;
            for (int i = 0; i < arrayList.size(); i++) {
                e eVar = arrayList.get(i);
                if (eVar instanceof d) {
                    this.f566b.add(new d((d) eVar, aVar));
                } else {
                    if (eVar instanceof c) {
                        bVar = new c((c) eVar);
                    } else if (eVar instanceof b) {
                        bVar = new b((b) eVar);
                    } else {
                        throw new IllegalStateException("Unknown object in the tree!");
                    }
                    this.f566b.add(bVar);
                    String str2 = bVar.f572b;
                    if (str2 != null) {
                        aVar.put(str2, bVar);
                    }
                }
            }
        }

        private void b() {
            this.j.reset();
            this.j.postTranslate(-this.f568d, -this.f569e);
            this.j.postScale(this.f570f, this.g);
            this.j.postRotate(this.f567c, 0.0f, 0.0f);
            this.j.postTranslate(this.h + this.f568d, this.i + this.f569e);
        }

        public void a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray a2 = a.f.d.d.g.a(resources, theme, attributeSet, a.o.a.a.a.f538b);
            a(a2, xmlPullParser);
            a2.recycle();
        }

        public String getGroupName() {
            return this.m;
        }

        public Matrix getLocalMatrix() {
            return this.j;
        }

        public float getPivotX() {
            return this.f568d;
        }

        public float getPivotY() {
            return this.f569e;
        }

        public float getRotation() {
            return this.f567c;
        }

        public float getScaleX() {
            return this.f570f;
        }

        public float getScaleY() {
            return this.g;
        }

        public float getTranslateX() {
            return this.h;
        }

        public float getTranslateY() {
            return this.i;
        }

        public void setPivotX(float f2) {
            if (f2 != this.f568d) {
                this.f568d = f2;
                b();
            }
        }

        public void setPivotY(float f2) {
            if (f2 != this.f569e) {
                this.f569e = f2;
                b();
            }
        }

        public void setRotation(float f2) {
            if (f2 != this.f567c) {
                this.f567c = f2;
                b();
            }
        }

        public void setScaleX(float f2) {
            if (f2 != this.f570f) {
                this.f570f = f2;
                b();
            }
        }

        public void setScaleY(float f2) {
            if (f2 != this.g) {
                this.g = f2;
                b();
            }
        }

        public void setTranslateX(float f2) {
            if (f2 != this.h) {
                this.h = f2;
                b();
            }
        }

        public void setTranslateY(float f2) {
            if (f2 != this.i) {
                this.i = f2;
                b();
            }
        }

        private void a(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.l = null;
            this.f567c = a.f.d.d.g.a(typedArray, xmlPullParser, "rotation", 5, this.f567c);
            this.f568d = typedArray.getFloat(1, this.f568d);
            this.f569e = typedArray.getFloat(2, this.f569e);
            this.f570f = a.f.d.d.g.a(typedArray, xmlPullParser, "scaleX", 3, this.f570f);
            this.g = a.f.d.d.g.a(typedArray, xmlPullParser, "scaleY", 4, this.g);
            this.h = a.f.d.d.g.a(typedArray, xmlPullParser, "translateX", 6, this.h);
            this.i = a.f.d.d.g.a(typedArray, xmlPullParser, "translateY", 7, this.i);
            String string = typedArray.getString(0);
            if (string != null) {
                this.m = string;
            }
            b();
        }

        @Override // a.o.a.a.i.e
        public boolean a() {
            for (int i = 0; i < this.f566b.size(); i++) {
                if (this.f566b.get(i).a()) {
                    return true;
                }
            }
            return false;
        }

        @Override // a.o.a.a.i.e
        public boolean a(int[] iArr) {
            boolean z = false;
            for (int i = 0; i < this.f566b.size(); i++) {
                z |= this.f566b.get(i).a(iArr);
            }
            return z;
        }

        public d() {
            super();
            this.f565a = new Matrix();
            this.f566b = new ArrayList<>();
            this.f567c = 0.0f;
            this.f568d = 0.0f;
            this.f569e = 0.0f;
            this.f570f = 1.0f;
            this.g = 1.0f;
            this.h = 0.0f;
            this.i = 0.0f;
            this.j = new Matrix();
            this.m = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class f extends e {

        /* renamed from: a, reason: collision with root package name */
        protected b.C0008b[] f571a;

        /* renamed from: b, reason: collision with root package name */
        String f572b;

        /* renamed from: c, reason: collision with root package name */
        int f573c;

        /* renamed from: d, reason: collision with root package name */
        int f574d;

        public f() {
            super();
            this.f571a = null;
            this.f573c = 0;
        }

        public void a(Path path) {
            path.reset();
            b.C0008b[] c0008bArr = this.f571a;
            if (c0008bArr != null) {
                b.C0008b.a(c0008bArr, path);
            }
        }

        public boolean b() {
            return false;
        }

        public b.C0008b[] getPathData() {
            return this.f571a;
        }

        public String getPathName() {
            return this.f572b;
        }

        public void setPathData(b.C0008b[] c0008bArr) {
            if (!a.f.e.b.a(this.f571a, c0008bArr)) {
                this.f571a = a.f.e.b.a(c0008bArr);
            } else {
                a.f.e.b.b(this.f571a, c0008bArr);
            }
        }

        public f(f fVar) {
            super();
            this.f571a = null;
            this.f573c = 0;
            this.f572b = fVar.f572b;
            this.f574d = fVar.f574d;
            this.f571a = a.f.e.b.a(fVar.f571a);
        }
    }

    public static i a(Resources resources, int i, Resources.Theme theme) {
        int next;
        if (Build.VERSION.SDK_INT >= 24) {
            i iVar = new i();
            iVar.f558b = a.f.d.d.f.a(resources, i, theme);
            new C0035i(iVar.f558b.getConstantState());
            return iVar;
        }
        try {
            XmlResourceParser xml = resources.getXml(i);
            AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
            do {
                next = xml.next();
                if (next == 2) {
                    break;
                }
            } while (next != 1);
            if (next == 2) {
                return createFromXmlInner(resources, (XmlPullParser) xml, asAttributeSet, theme);
            }
            throw new XmlPullParserException("No start tag found");
        } catch (IOException e2) {
            Log.e("VectorDrawableCompat", "parser error", e2);
            return null;
        } catch (XmlPullParserException e3) {
            Log.e("VectorDrawableCompat", "parser error", e3);
            return null;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        h hVar = this.f559c;
        hVar.f582b = new g();
        TypedArray a2 = a.f.d.d.g.a(resources, theme, attributeSet, a.o.a.a.a.f537a);
        a(a2, xmlPullParser, theme);
        a2.recycle();
        hVar.f581a = getChangingConfigurations();
        hVar.k = true;
        a(resources, xmlPullParser, attributeSet, theme);
        this.f560d = a(this.f560d, hVar.f583c, hVar.f584d);
    }

    i(h hVar) {
        this.g = true;
        this.h = new float[9];
        this.i = new Matrix();
        this.j = new Rect();
        this.f559c = hVar;
        this.f560d = a(this.f560d, hVar.f583c, hVar.f584d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class g {
        private static final Matrix q = new Matrix();

        /* renamed from: a, reason: collision with root package name */
        private final Path f575a;

        /* renamed from: b, reason: collision with root package name */
        private final Path f576b;

        /* renamed from: c, reason: collision with root package name */
        private final Matrix f577c;

        /* renamed from: d, reason: collision with root package name */
        Paint f578d;

        /* renamed from: e, reason: collision with root package name */
        Paint f579e;

        /* renamed from: f, reason: collision with root package name */
        private PathMeasure f580f;
        private int g;
        final d h;
        float i;
        float j;
        float k;
        float l;
        int m;
        String n;
        Boolean o;
        final a.d.a<String, Object> p;

        public g() {
            this.f577c = new Matrix();
            this.i = 0.0f;
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 255;
            this.n = null;
            this.o = null;
            this.p = new a.d.a<>();
            this.h = new d();
            this.f575a = new Path();
            this.f576b = new Path();
        }

        private static float a(float f2, float f3, float f4, float f5) {
            return (f2 * f5) - (f3 * f4);
        }

        private void a(d dVar, Matrix matrix, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            dVar.f565a.set(matrix);
            dVar.f565a.preConcat(dVar.j);
            canvas.save();
            for (int i3 = 0; i3 < dVar.f566b.size(); i3++) {
                e eVar = dVar.f566b.get(i3);
                if (eVar instanceof d) {
                    a((d) eVar, dVar.f565a, canvas, i, i2, colorFilter);
                } else if (eVar instanceof f) {
                    a(dVar, (f) eVar, canvas, i, i2, colorFilter);
                }
            }
            canvas.restore();
        }

        public float getAlpha() {
            return getRootAlpha() / 255.0f;
        }

        public int getRootAlpha() {
            return this.m;
        }

        public void setAlpha(float f2) {
            setRootAlpha((int) (f2 * 255.0f));
        }

        public void setRootAlpha(int i) {
            this.m = i;
        }

        public void a(Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            a(this.h, q, canvas, i, i2, colorFilter);
        }

        public g(g gVar) {
            this.f577c = new Matrix();
            this.i = 0.0f;
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 255;
            this.n = null;
            this.o = null;
            this.p = new a.d.a<>();
            this.h = new d(gVar.h, this.p);
            this.f575a = new Path(gVar.f575a);
            this.f576b = new Path(gVar.f576b);
            this.i = gVar.i;
            this.j = gVar.j;
            this.k = gVar.k;
            this.l = gVar.l;
            this.g = gVar.g;
            this.m = gVar.m;
            this.n = gVar.n;
            String str = gVar.n;
            if (str != null) {
                this.p.put(str, this);
            }
            this.o = gVar.o;
        }

        private void a(d dVar, f fVar, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            float f2 = i / this.k;
            float f3 = i2 / this.l;
            float min = Math.min(f2, f3);
            Matrix matrix = dVar.f565a;
            this.f577c.set(matrix);
            this.f577c.postScale(f2, f3);
            float a2 = a(matrix);
            if (a2 == 0.0f) {
                return;
            }
            fVar.a(this.f575a);
            Path path = this.f575a;
            this.f576b.reset();
            if (fVar.b()) {
                this.f576b.setFillType(fVar.f573c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                this.f576b.addPath(path, this.f577c);
                canvas.clipPath(this.f576b);
                return;
            }
            c cVar = (c) fVar;
            if (cVar.k != 0.0f || cVar.l != 1.0f) {
                float f4 = cVar.k;
                float f5 = cVar.m;
                float f6 = (f4 + f5) % 1.0f;
                float f7 = (cVar.l + f5) % 1.0f;
                if (this.f580f == null) {
                    this.f580f = new PathMeasure();
                }
                this.f580f.setPath(this.f575a, false);
                float length = this.f580f.getLength();
                float f8 = f6 * length;
                float f9 = f7 * length;
                path.reset();
                if (f8 > f9) {
                    this.f580f.getSegment(f8, length, path, true);
                    this.f580f.getSegment(0.0f, f9, path, true);
                } else {
                    this.f580f.getSegment(f8, f9, path, true);
                }
                path.rLineTo(0.0f, 0.0f);
            }
            this.f576b.addPath(path, this.f577c);
            if (cVar.h.e()) {
                a.f.d.d.b bVar = cVar.h;
                if (this.f579e == null) {
                    this.f579e = new Paint(1);
                    this.f579e.setStyle(Paint.Style.FILL);
                }
                Paint paint = this.f579e;
                if (bVar.c()) {
                    Shader b2 = bVar.b();
                    b2.setLocalMatrix(this.f577c);
                    paint.setShader(b2);
                    paint.setAlpha(Math.round(cVar.j * 255.0f));
                } else {
                    paint.setShader(null);
                    paint.setAlpha(255);
                    paint.setColor(i.a(bVar.a(), cVar.j));
                }
                paint.setColorFilter(colorFilter);
                this.f576b.setFillType(cVar.f573c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                canvas.drawPath(this.f576b, paint);
            }
            if (cVar.f564f.e()) {
                a.f.d.d.b bVar2 = cVar.f564f;
                if (this.f578d == null) {
                    this.f578d = new Paint(1);
                    this.f578d.setStyle(Paint.Style.STROKE);
                }
                Paint paint2 = this.f578d;
                Paint.Join join = cVar.o;
                if (join != null) {
                    paint2.setStrokeJoin(join);
                }
                Paint.Cap cap = cVar.n;
                if (cap != null) {
                    paint2.setStrokeCap(cap);
                }
                paint2.setStrokeMiter(cVar.p);
                if (bVar2.c()) {
                    Shader b3 = bVar2.b();
                    b3.setLocalMatrix(this.f577c);
                    paint2.setShader(b3);
                    paint2.setAlpha(Math.round(cVar.i * 255.0f));
                } else {
                    paint2.setShader(null);
                    paint2.setAlpha(255);
                    paint2.setColor(i.a(bVar2.a(), cVar.i));
                }
                paint2.setColorFilter(colorFilter);
                paint2.setStrokeWidth(cVar.g * min * a2);
                canvas.drawPath(this.f576b, paint2);
            }
        }

        private float a(Matrix matrix) {
            float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
            matrix.mapVectors(fArr);
            float hypot = (float) Math.hypot(fArr[0], fArr[1]);
            float hypot2 = (float) Math.hypot(fArr[2], fArr[3]);
            float a2 = a(fArr[0], fArr[1], fArr[2], fArr[3]);
            float max = Math.max(hypot, hypot2);
            if (max > 0.0f) {
                return Math.abs(a2) / max;
            }
            return 0.0f;
        }

        public boolean a() {
            if (this.o == null) {
                this.o = Boolean.valueOf(this.h.a());
            }
            return this.o.booleanValue();
        }

        public boolean a(int[] iArr) {
            return this.h.a(iArr);
        }
    }

    static int a(int i, float f2) {
        return (i & 16777215) | (((int) (Color.alpha(i) * f2)) << 24);
    }

    private static PorterDuff.Mode a(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i != 9) {
            switch (i) {
                case 14:
                    return PorterDuff.Mode.MULTIPLY;
                case 15:
                    return PorterDuff.Mode.SCREEN;
                case 16:
                    return PorterDuff.Mode.ADD;
                default:
                    return mode;
            }
        }
        return PorterDuff.Mode.SRC_ATOP;
    }

    private void a(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
        h hVar = this.f559c;
        g gVar = hVar.f582b;
        hVar.f584d = a(a.f.d.d.g.b(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList a2 = a.f.d.d.g.a(typedArray, xmlPullParser, theme, "tint", 1);
        if (a2 != null) {
            hVar.f583c = a2;
        }
        hVar.f585e = a.f.d.d.g.a(typedArray, xmlPullParser, "autoMirrored", 5, hVar.f585e);
        gVar.k = a.f.d.d.g.a(typedArray, xmlPullParser, "viewportWidth", 7, gVar.k);
        gVar.l = a.f.d.d.g.a(typedArray, xmlPullParser, "viewportHeight", 8, gVar.l);
        if (gVar.k <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (gVar.l > 0.0f) {
            gVar.i = typedArray.getDimension(3, gVar.i);
            gVar.j = typedArray.getDimension(2, gVar.j);
            if (gVar.i <= 0.0f) {
                throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
            }
            if (gVar.j > 0.0f) {
                gVar.setAlpha(a.f.d.d.g.a(typedArray, xmlPullParser, "alpha", 4, gVar.getAlpha()));
                String string = typedArray.getString(0);
                if (string != null) {
                    gVar.n = string;
                    gVar.p.put(string, gVar);
                    return;
                }
                return;
            }
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
        }
        throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
    }

    private void a(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        h hVar = this.f559c;
        g gVar = hVar.f582b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(gVar.h);
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        boolean z = true;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                d dVar = (d) arrayDeque.peek();
                if ("path".equals(name)) {
                    c cVar = new c();
                    cVar.a(resources, attributeSet, theme, xmlPullParser);
                    dVar.f566b.add(cVar);
                    if (cVar.getPathName() != null) {
                        gVar.p.put(cVar.getPathName(), cVar);
                    }
                    z = false;
                    hVar.f581a = cVar.f574d | hVar.f581a;
                } else if ("clip-path".equals(name)) {
                    b bVar = new b();
                    bVar.a(resources, attributeSet, theme, xmlPullParser);
                    dVar.f566b.add(bVar);
                    if (bVar.getPathName() != null) {
                        gVar.p.put(bVar.getPathName(), bVar);
                    }
                    hVar.f581a = bVar.f574d | hVar.f581a;
                } else if ("group".equals(name)) {
                    d dVar2 = new d();
                    dVar2.a(resources, attributeSet, theme, xmlPullParser);
                    dVar.f566b.add(dVar2);
                    arrayDeque.push(dVar2);
                    if (dVar2.getGroupName() != null) {
                        gVar.p.put(dVar2.getGroupName(), dVar2);
                    }
                    hVar.f581a = dVar2.k | hVar.f581a;
                }
            } else if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                arrayDeque.pop();
            }
            eventType = xmlPullParser.next();
        }
        if (z) {
            throw new XmlPullParserException("no path defined");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        this.g = z;
    }

    private boolean a() {
        return Build.VERSION.SDK_INT >= 17 && isAutoMirrored() && androidx.core.graphics.drawable.a.e(this) == 1;
    }
}
