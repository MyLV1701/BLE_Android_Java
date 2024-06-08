package androidx.appcompat.widget;

import a.f.d.d.f;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;

/* loaded from: classes.dex */
public class t0 {

    /* renamed from: a, reason: collision with root package name */
    private final Context f1091a;

    /* renamed from: b, reason: collision with root package name */
    private final TypedArray f1092b;

    /* renamed from: c, reason: collision with root package name */
    private TypedValue f1093c;

    private t0(Context context, TypedArray typedArray) {
        this.f1091a = context;
        this.f1092b = typedArray;
    }

    public static t0 a(Context context, AttributeSet attributeSet, int[] iArr) {
        return new t0(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public Drawable b(int i) {
        int resourceId;
        if (this.f1092b.hasValue(i) && (resourceId = this.f1092b.getResourceId(i, 0)) != 0) {
            return a.a.k.a.a.c(this.f1091a, resourceId);
        }
        return this.f1092b.getDrawable(i);
    }

    public Drawable c(int i) {
        int resourceId;
        if (!this.f1092b.hasValue(i) || (resourceId = this.f1092b.getResourceId(i, 0)) == 0) {
            return null;
        }
        return j.b().a(this.f1091a, resourceId, true);
    }

    public String d(int i) {
        return this.f1092b.getString(i);
    }

    public CharSequence e(int i) {
        return this.f1092b.getText(i);
    }

    public int f(int i, int i2) {
        return this.f1092b.getLayoutDimension(i, i2);
    }

    public int g(int i, int i2) {
        return this.f1092b.getResourceId(i, i2);
    }

    public static t0 a(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new t0(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    public int d(int i, int i2) {
        return this.f1092b.getInt(i, i2);
    }

    public int e(int i, int i2) {
        return this.f1092b.getInteger(i, i2);
    }

    public CharSequence[] f(int i) {
        return this.f1092b.getTextArray(i);
    }

    public boolean g(int i) {
        return this.f1092b.hasValue(i);
    }

    public static t0 a(Context context, int i, int[] iArr) {
        return new t0(context, context.obtainStyledAttributes(i, iArr));
    }

    public int c(int i, int i2) {
        return this.f1092b.getDimensionPixelSize(i, i2);
    }

    public Typeface a(int i, int i2, f.a aVar) {
        int resourceId = this.f1092b.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f1093c == null) {
            this.f1093c = new TypedValue();
        }
        return a.f.d.d.f.a(this.f1091a, resourceId, this.f1093c, i2, aVar);
    }

    public float b(int i, float f2) {
        return this.f1092b.getFloat(i, f2);
    }

    public int b(int i, int i2) {
        return this.f1092b.getDimensionPixelOffset(i, i2);
    }

    public boolean a(int i, boolean z) {
        return this.f1092b.getBoolean(i, z);
    }

    public int a(int i, int i2) {
        return this.f1092b.getColor(i, i2);
    }

    public ColorStateList a(int i) {
        int resourceId;
        ColorStateList b2;
        return (!this.f1092b.hasValue(i) || (resourceId = this.f1092b.getResourceId(i, 0)) == 0 || (b2 = a.a.k.a.a.b(this.f1091a, resourceId)) == null) ? this.f1092b.getColorStateList(i) : b2;
    }

    public float a(int i, float f2) {
        return this.f1092b.getDimension(i, f2);
    }

    public void a() {
        this.f1092b.recycle();
    }
}
