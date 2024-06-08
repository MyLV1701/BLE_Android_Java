package c.a.a.a.y;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import androidx.appcompat.widget.t0;

/* loaded from: classes.dex */
public class c {
    public static ColorStateList a(Context context, TypedArray typedArray, int i) {
        int color;
        int resourceId;
        ColorStateList b2;
        if (typedArray.hasValue(i) && (resourceId = typedArray.getResourceId(i, 0)) != 0 && (b2 = a.a.k.a.a.b(context, resourceId)) != null) {
            return b2;
        }
        if (Build.VERSION.SDK_INT <= 15 && (color = typedArray.getColor(i, -1)) != -1) {
            return ColorStateList.valueOf(color);
        }
        return typedArray.getColorStateList(i);
    }

    public static Drawable b(Context context, TypedArray typedArray, int i) {
        int resourceId;
        Drawable c2;
        return (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0 || (c2 = a.a.k.a.a.c(context, resourceId)) == null) ? typedArray.getDrawable(i) : c2;
    }

    public static d c(Context context, TypedArray typedArray, int i) {
        int resourceId;
        if (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0) {
            return null;
        }
        return new d(context, resourceId);
    }

    public static ColorStateList a(Context context, t0 t0Var, int i) {
        int a2;
        int g;
        ColorStateList b2;
        if (t0Var.g(i) && (g = t0Var.g(i, 0)) != 0 && (b2 = a.a.k.a.a.b(context, g)) != null) {
            return b2;
        }
        if (Build.VERSION.SDK_INT <= 15 && (a2 = t0Var.a(i, -1)) != -1) {
            return ColorStateList.valueOf(a2);
        }
        return t0Var.a(i);
    }

    public static int a(Context context, TypedArray typedArray, int i, int i2) {
        TypedValue typedValue = new TypedValue();
        if (typedArray.getValue(i, typedValue) && typedValue.type == 2) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{typedValue.data});
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, i2);
            obtainStyledAttributes.recycle();
            return dimensionPixelSize;
        }
        return typedArray.getDimensionPixelSize(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(TypedArray typedArray, int i, int i2) {
        return typedArray.hasValue(i) ? i : i2;
    }
}
