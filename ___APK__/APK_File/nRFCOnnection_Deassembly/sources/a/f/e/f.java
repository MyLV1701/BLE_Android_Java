package a.f.e;

import a.f.d.d.c;
import a.f.i.b;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

/* loaded from: classes.dex */
public class f extends d {
    protected final Class<?> g;
    protected final Constructor<?> h;
    protected final Method i;
    protected final Method j;
    protected final Method k;
    protected final Method l;
    protected final Method m;

    public f() {
        Method method;
        Constructor<?> constructor;
        Method method2;
        Method method3;
        Method method4;
        Method method5;
        Class<?> cls = null;
        try {
            Class<?> a2 = a();
            constructor = e(a2);
            method2 = b(a2);
            method3 = c(a2);
            method4 = f(a2);
            method5 = a(a2);
            method = d(a2);
            cls = a2;
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class " + e2.getClass().getName(), e2);
            method = null;
            constructor = null;
            method2 = null;
            method3 = null;
            method4 = null;
            method5 = null;
        }
        this.g = cls;
        this.h = constructor;
        this.i = method2;
        this.j = method3;
        this.k = method4;
        this.l = method5;
        this.m = method;
    }

    private boolean a(Context context, Object obj, String str, int i, int i2, int i3, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.i.invoke(obj, context.getAssets(), str, 0, false, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    private boolean b() {
        if (this.i == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.i != null;
    }

    private Object c() {
        try {
            return this.h.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    protected Method d(Class<?> cls) {
        Class cls2 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass(), cls2, cls2);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }

    protected Constructor<?> e(Class<?> cls) {
        return cls.getConstructor(new Class[0]);
    }

    protected Method f(Class<?> cls) {
        return cls.getMethod("freeze", new Class[0]);
    }

    private boolean c(Object obj) {
        try {
            return ((Boolean) this.k.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    protected Method c(Class<?> cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromBuffer", ByteBuffer.class, cls2, FontVariationAxis[].class, cls2, cls2);
    }

    private void b(Object obj) {
        try {
            this.l.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }

    private boolean a(Object obj, ByteBuffer byteBuffer, int i, int i2, int i3) {
        try {
            return ((Boolean) this.j.invoke(obj, byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Integer.valueOf(i3))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    protected Method b(Class<?> cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class);
    }

    protected Typeface a(Object obj) {
        try {
            Object newInstance = Array.newInstance(this.g, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) this.m.invoke(null, newInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    @Override // a.f.e.d, a.f.e.i
    public Typeface a(Context context, c.b bVar, Resources resources, int i) {
        if (!b()) {
            return super.a(context, bVar, resources, i);
        }
        Object c2 = c();
        if (c2 == null) {
            return null;
        }
        for (c.C0006c c0006c : bVar.a()) {
            if (!a(context, c2, c0006c.a(), c0006c.c(), c0006c.e(), c0006c.f() ? 1 : 0, FontVariationAxis.fromFontVariationSettings(c0006c.d()))) {
                b(c2);
                return null;
            }
        }
        if (c(c2)) {
            return a(c2);
        }
        return null;
    }

    @Override // a.f.e.d, a.f.e.i
    public Typeface a(Context context, CancellationSignal cancellationSignal, b.f[] fVarArr, int i) {
        Typeface a2;
        if (fVarArr.length < 1) {
            return null;
        }
        if (!b()) {
            b.f a3 = a(fVarArr, i);
            try {
                ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(a3.c(), "r", cancellationSignal);
                if (openFileDescriptor == null) {
                    if (openFileDescriptor != null) {
                        openFileDescriptor.close();
                    }
                    return null;
                }
                try {
                    Typeface build = new Typeface.Builder(openFileDescriptor.getFileDescriptor()).setWeight(a3.d()).setItalic(a3.e()).build();
                    if (openFileDescriptor != null) {
                        openFileDescriptor.close();
                    }
                    return build;
                } finally {
                }
            } catch (IOException unused) {
                return null;
            }
        } else {
            Map<Uri, ByteBuffer> a4 = a.f.i.b.a(context, fVarArr, cancellationSignal);
            Object c2 = c();
            if (c2 == null) {
                return null;
            }
            boolean z = false;
            for (b.f fVar : fVarArr) {
                ByteBuffer byteBuffer = a4.get(fVar.c());
                if (byteBuffer != null) {
                    if (!a(c2, byteBuffer, fVar.b(), fVar.d(), fVar.e() ? 1 : 0)) {
                        b(c2);
                        return null;
                    }
                    z = true;
                }
            }
            if (!z) {
                b(c2);
                return null;
            }
            if (c(c2) && (a2 = a(c2)) != null) {
                return Typeface.create(a2, i);
            }
            return null;
        }
    }

    @Override // a.f.e.i
    public Typeface a(Context context, Resources resources, int i, String str, int i2) {
        if (!b()) {
            return super.a(context, resources, i, str, i2);
        }
        Object c2 = c();
        if (c2 == null) {
            return null;
        }
        if (!a(context, c2, str, 0, -1, -1, null)) {
            b(c2);
            return null;
        }
        if (c(c2)) {
            return a(c2);
        }
        return null;
    }

    protected Class<?> a() {
        return Class.forName("android.graphics.FontFamily");
    }

    protected Method a(Class<?> cls) {
        return cls.getMethod("abortCreation", new Class[0]);
    }
}
