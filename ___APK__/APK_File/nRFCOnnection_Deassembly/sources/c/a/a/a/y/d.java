package c.a.a.a.y;

import a.f.d.d.f;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import c.a.a.a.l;

/* loaded from: classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    public final float f2151a;

    /* renamed from: b, reason: collision with root package name */
    public final ColorStateList f2152b;

    /* renamed from: c, reason: collision with root package name */
    public final int f2153c;

    /* renamed from: d, reason: collision with root package name */
    public final int f2154d;

    /* renamed from: e, reason: collision with root package name */
    public final String f2155e;

    /* renamed from: f, reason: collision with root package name */
    public final ColorStateList f2156f;
    public final float g;
    public final float h;
    public final float i;
    private final int j;
    private boolean k = false;
    private Typeface l;

    public d(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, l.TextAppearance);
        this.f2151a = obtainStyledAttributes.getDimension(l.TextAppearance_android_textSize, 0.0f);
        this.f2152b = c.a(context, obtainStyledAttributes, l.TextAppearance_android_textColor);
        c.a(context, obtainStyledAttributes, l.TextAppearance_android_textColorHint);
        c.a(context, obtainStyledAttributes, l.TextAppearance_android_textColorLink);
        this.f2153c = obtainStyledAttributes.getInt(l.TextAppearance_android_textStyle, 0);
        this.f2154d = obtainStyledAttributes.getInt(l.TextAppearance_android_typeface, 1);
        int a2 = c.a(obtainStyledAttributes, l.TextAppearance_fontFamily, l.TextAppearance_android_fontFamily);
        this.j = obtainStyledAttributes.getResourceId(a2, 0);
        this.f2155e = obtainStyledAttributes.getString(a2);
        obtainStyledAttributes.getBoolean(l.TextAppearance_textAllCaps, false);
        this.f2156f = c.a(context, obtainStyledAttributes, l.TextAppearance_android_shadowColor);
        this.g = obtainStyledAttributes.getFloat(l.TextAppearance_android_shadowDx, 0.0f);
        this.h = obtainStyledAttributes.getFloat(l.TextAppearance_android_shadowDy, 0.0f);
        this.i = obtainStyledAttributes.getFloat(l.TextAppearance_android_shadowRadius, 0.0f);
        obtainStyledAttributes.recycle();
    }

    private void b() {
        String str;
        if (this.l == null && (str = this.f2155e) != null) {
            this.l = Typeface.create(str, this.f2153c);
        }
        if (this.l == null) {
            int i = this.f2154d;
            if (i == 1) {
                this.l = Typeface.SANS_SERIF;
            } else if (i == 2) {
                this.l = Typeface.SERIF;
            } else if (i != 3) {
                this.l = Typeface.DEFAULT;
            } else {
                this.l = Typeface.MONOSPACE;
            }
            this.l = Typeface.create(this.l, this.f2153c);
        }
    }

    public void c(Context context, TextPaint textPaint, f fVar) {
        if (e.a()) {
            a(textPaint, a(context));
        } else {
            a(context, textPaint, fVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends f {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ TextPaint f2159a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ f f2160b;

        b(TextPaint textPaint, f fVar) {
            this.f2159a = textPaint;
            this.f2160b = fVar;
        }

        @Override // c.a.a.a.y.f
        public void a(Typeface typeface, boolean z) {
            d.this.a(this.f2159a, typeface);
            this.f2160b.a(typeface, z);
        }

        @Override // c.a.a.a.y.f
        public void a(int i) {
            this.f2160b.a(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends f.a {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ f f2157a;

        a(f fVar) {
            this.f2157a = fVar;
        }

        @Override // a.f.d.d.f.a
        public void a(Typeface typeface) {
            d dVar = d.this;
            dVar.l = Typeface.create(typeface, dVar.f2153c);
            d.this.k = true;
            this.f2157a.a(d.this.l, false);
        }

        @Override // a.f.d.d.f.a
        public void a(int i) {
            d.this.k = true;
            this.f2157a.a(i);
        }
    }

    public Typeface a(Context context) {
        if (this.k) {
            return this.l;
        }
        if (!context.isRestricted()) {
            try {
                this.l = a.f.d.d.f.a(context, this.j);
                if (this.l != null) {
                    this.l = Typeface.create(this.l, this.f2153c);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            } catch (Exception e2) {
                Log.d("TextAppearance", "Error loading font " + this.f2155e, e2);
            }
        }
        b();
        this.k = true;
        return this.l;
    }

    public void b(Context context, TextPaint textPaint, f fVar) {
        c(context, textPaint, fVar);
        ColorStateList colorStateList = this.f2152b;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor()) : -16777216);
        float f2 = this.i;
        float f3 = this.g;
        float f4 = this.h;
        ColorStateList colorStateList2 = this.f2156f;
        textPaint.setShadowLayer(f2, f3, f4, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor()) : 0);
    }

    public void a(Context context, f fVar) {
        if (e.a()) {
            a(context);
        } else {
            b();
        }
        if (this.j == 0) {
            this.k = true;
        }
        if (this.k) {
            fVar.a(this.l, true);
            return;
        }
        try {
            a.f.d.d.f.a(context, this.j, new a(fVar), null);
        } catch (Resources.NotFoundException unused) {
            this.k = true;
            fVar.a(1);
        } catch (Exception e2) {
            Log.d("TextAppearance", "Error loading font " + this.f2155e, e2);
            this.k = true;
            fVar.a(-3);
        }
    }

    public void a(Context context, TextPaint textPaint, f fVar) {
        a(textPaint, a());
        a(context, new b(textPaint, fVar));
    }

    public Typeface a() {
        b();
        return this.l;
    }

    public void a(TextPaint textPaint, Typeface typeface) {
        textPaint.setTypeface(typeface);
        int style = (typeface.getStyle() ^ (-1)) & this.f2153c;
        textPaint.setFakeBoldText((style & 1) != 0);
        textPaint.setTextSkewX((style & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.f2151a);
    }
}
