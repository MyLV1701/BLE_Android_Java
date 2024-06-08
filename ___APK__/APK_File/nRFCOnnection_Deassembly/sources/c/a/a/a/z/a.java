package c.a.a.a.z;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import c.a.a.a.b0.g;
import c.a.a.a.b0.k;
import c.a.a.a.b0.n;

/* loaded from: classes.dex */
public class a extends Drawable implements n, androidx.core.graphics.drawable.b {

    /* renamed from: b, reason: collision with root package name */
    private b f2163b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b extends Drawable.ConstantState {

        /* renamed from: a, reason: collision with root package name */
        g f2164a;

        /* renamed from: b, reason: collision with root package name */
        boolean f2165b;

        public b(g gVar) {
            this.f2164a = gVar;
            this.f2165b = false;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public a newDrawable() {
            return new a(new b(this));
        }

        public b(b bVar) {
            this.f2164a = (g) bVar.f2164a.getConstantState().newDrawable();
            this.f2165b = bVar.f2165b;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        b bVar = this.f2163b;
        if (bVar.f2165b) {
            bVar.f2164a.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f2163b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f2163b.f2164a.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable mutate() {
        mutate();
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f2163b.f2164a.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean onStateChange = super.onStateChange(iArr);
        if (this.f2163b.f2164a.setState(iArr)) {
            onStateChange = true;
        }
        boolean a2 = c.a.a.a.z.b.a(iArr);
        b bVar = this.f2163b;
        if (bVar.f2165b == a2) {
            return onStateChange;
        }
        bVar.f2165b = a2;
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f2163b.f2164a.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f2163b.f2164a.setColorFilter(colorFilter);
    }

    @Override // c.a.a.a.b0.n
    public void setShapeAppearanceModel(k kVar) {
        this.f2163b.f2164a.setShapeAppearanceModel(kVar);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i) {
        this.f2163b.f2164a.setTint(i);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        this.f2163b.f2164a.setTintList(colorStateList);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        this.f2163b.f2164a.setTintMode(mode);
    }

    public a(k kVar) {
        this(new b(new g(kVar)));
    }

    @Override // android.graphics.drawable.Drawable
    public a mutate() {
        this.f2163b = new b(this.f2163b);
        return this;
    }

    private a(b bVar) {
        this.f2163b = bVar;
    }
}
