package a.n;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
class i0 {

    /* renamed from: a, reason: collision with root package name */
    private static Method f467a;

    /* renamed from: b, reason: collision with root package name */
    private static boolean f468b;

    /* renamed from: c, reason: collision with root package name */
    private static Field f469c;

    /* renamed from: d, reason: collision with root package name */
    private static boolean f470d;

    public void a(View view, float f2) {
        Float f3 = (Float) view.getTag(j.save_non_transition_alpha);
        if (f3 != null) {
            view.setAlpha(f3.floatValue() * f2);
        } else {
            view.setAlpha(f2);
        }
    }

    public float b(View view) {
        Float f2 = (Float) view.getTag(j.save_non_transition_alpha);
        if (f2 != null) {
            return view.getAlpha() / f2.floatValue();
        }
        return view.getAlpha();
    }

    public void c(View view) {
        if (view.getTag(j.save_non_transition_alpha) == null) {
            view.setTag(j.save_non_transition_alpha, Float.valueOf(view.getAlpha()));
        }
    }

    public void a(View view) {
        if (view.getVisibility() == 0) {
            view.setTag(j.save_non_transition_alpha, null);
        }
    }

    public void b(View view, Matrix matrix) {
        Object parent = view.getParent();
        if (parent instanceof View) {
            b((View) parent, matrix);
            matrix.postTranslate(r0.getScrollX(), r0.getScrollY());
        }
        matrix.postTranslate(-view.getLeft(), -view.getTop());
        Matrix matrix2 = view.getMatrix();
        if (matrix2.isIdentity()) {
            return;
        }
        Matrix matrix3 = new Matrix();
        if (matrix2.invert(matrix3)) {
            matrix.postConcat(matrix3);
        }
    }

    public void a(View view, Matrix matrix) {
        Object parent = view.getParent();
        if (parent instanceof View) {
            a((View) parent, matrix);
            matrix.preTranslate(-r0.getScrollX(), -r0.getScrollY());
        }
        matrix.preTranslate(view.getLeft(), view.getTop());
        Matrix matrix2 = view.getMatrix();
        if (matrix2.isIdentity()) {
            return;
        }
        matrix.preConcat(matrix2);
    }

    public void a(View view, int i, int i2, int i3, int i4) {
        a();
        Method method = f467a;
        if (method != null) {
            try {
                method.invoke(view, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
    }

    public void a(View view, int i) {
        if (!f470d) {
            try {
                f469c = View.class.getDeclaredField("mViewFlags");
                f469c.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.i("ViewUtilsBase", "fetchViewFlagsField: ");
            }
            f470d = true;
        }
        Field field = f469c;
        if (field != null) {
            try {
                f469c.setInt(view, i | (field.getInt(view) & (-13)));
            } catch (IllegalAccessException unused2) {
            }
        }
    }

    @SuppressLint({"PrivateApi"})
    private void a() {
        if (f468b) {
            return;
        }
        try {
            f467a = View.class.getDeclaredMethod("setFrame", Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE);
            f467a.setAccessible(true);
        } catch (NoSuchMethodException e2) {
            Log.i("ViewUtilsBase", "Failed to retrieve setFrame method", e2);
        }
        f468b = true;
    }
}
