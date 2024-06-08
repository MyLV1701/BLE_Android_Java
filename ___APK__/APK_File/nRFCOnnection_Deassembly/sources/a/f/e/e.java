package a.f.e;

import a.f.d.d.c;
import a.f.i.b;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: classes.dex */
class e extends i {

    /* renamed from: b, reason: collision with root package name */
    private static final Class<?> f174b;

    /* renamed from: c, reason: collision with root package name */
    private static final Constructor<?> f175c;

    /* renamed from: d, reason: collision with root package name */
    private static final Method f176d;

    /* renamed from: e, reason: collision with root package name */
    private static final Method f177e;

    static {
        Class<?> cls;
        Method method;
        Method method2;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(new Class[0]);
            method2 = cls.getMethod("addFontWeightStyle", ByteBuffer.class, Integer.TYPE, List.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi24Impl", e2.getClass().getName(), e2);
            cls = null;
            method = null;
            method2 = null;
        }
        f175c = constructor;
        f174b = cls;
        f176d = method2;
        f177e = method;
    }

    public static boolean a() {
        if (f176d == null) {
            Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }
        return f176d != null;
    }

    private static Object b() {
        try {
            return f175c.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    private static boolean a(Object obj, ByteBuffer byteBuffer, int i, int i2, boolean z) {
        try {
            return ((Boolean) f176d.invoke(obj, byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    private static Typeface a(Object obj) {
        try {
            Object newInstance = Array.newInstance(f174b, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) f177e.invoke(null, newInstance);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    @Override // a.f.e.i
    public Typeface a(Context context, CancellationSignal cancellationSignal, b.f[] fVarArr, int i) {
        Object b2 = b();
        if (b2 == null) {
            return null;
        }
        a.d.g gVar = new a.d.g();
        for (b.f fVar : fVarArr) {
            Uri c2 = fVar.c();
            ByteBuffer byteBuffer = (ByteBuffer) gVar.get(c2);
            if (byteBuffer == null) {
                byteBuffer = j.a(context, cancellationSignal, c2);
                gVar.put(c2, byteBuffer);
            }
            if (byteBuffer == null || !a(b2, byteBuffer, fVar.b(), fVar.d(), fVar.e())) {
                return null;
            }
        }
        Typeface a2 = a(b2);
        if (a2 == null) {
            return null;
        }
        return Typeface.create(a2, i);
    }

    @Override // a.f.e.i
    public Typeface a(Context context, c.b bVar, Resources resources, int i) {
        Object b2 = b();
        if (b2 == null) {
            return null;
        }
        for (c.C0006c c0006c : bVar.a()) {
            ByteBuffer a2 = j.a(context, resources, c0006c.b());
            if (a2 == null || !a(b2, a2, c0006c.c(), c0006c.e(), c0006c.f())) {
                return null;
            }
        }
        return a(b2);
    }
}
