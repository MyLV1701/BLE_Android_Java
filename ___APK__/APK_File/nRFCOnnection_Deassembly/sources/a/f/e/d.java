package a.f.e;

import a.f.d.d.c;
import a.f.i.b;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
class d extends i {

    /* renamed from: b, reason: collision with root package name */
    private static Class<?> f169b = null;

    /* renamed from: c, reason: collision with root package name */
    private static Constructor<?> f170c = null;

    /* renamed from: d, reason: collision with root package name */
    private static Method f171d = null;

    /* renamed from: e, reason: collision with root package name */
    private static Method f172e = null;

    /* renamed from: f, reason: collision with root package name */
    private static boolean f173f = false;

    private static void a() {
        Method method;
        Class<?> cls;
        Method method2;
        if (f173f) {
            return;
        }
        f173f = true;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(new Class[0]);
            method2 = cls.getMethod("addFontWeightStyle", String.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi21Impl", e2.getClass().getName(), e2);
            method = null;
            cls = null;
            method2 = null;
        }
        f170c = constructor;
        f169b = cls;
        f171d = method2;
        f172e = method;
    }

    private static Object b() {
        a();
        try {
            return f170c.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    private File a(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            String readlink = Os.readlink("/proc/self/fd/" + parcelFileDescriptor.getFd());
            if (OsConstants.S_ISREG(Os.stat(readlink).st_mode)) {
                return new File(readlink);
            }
        } catch (ErrnoException unused) {
        }
        return null;
    }

    private static Typeface a(Object obj) {
        a();
        try {
            Object newInstance = Array.newInstance(f169b, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) f172e.invoke(null, newInstance);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    private static boolean a(Object obj, String str, int i, boolean z) {
        a();
        try {
            return ((Boolean) f171d.invoke(obj, str, Integer.valueOf(i), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // a.f.e.i
    public Typeface a(Context context, CancellationSignal cancellationSignal, b.f[] fVarArr, int i) {
        if (fVarArr.length < 1) {
            return null;
        }
        b.f a2 = a(fVarArr, i);
        try {
            ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(a2.c(), "r", cancellationSignal);
            if (openFileDescriptor == null) {
                if (openFileDescriptor != null) {
                    openFileDescriptor.close();
                }
                return null;
            }
            try {
                File a3 = a(openFileDescriptor);
                if (a3 != null && a3.canRead()) {
                    Typeface createFromFile = Typeface.createFromFile(a3);
                    if (openFileDescriptor != null) {
                        openFileDescriptor.close();
                    }
                    return createFromFile;
                }
                try {
                    Typeface a4 = super.a(context, new FileInputStream(openFileDescriptor.getFileDescriptor()));
                    if (openFileDescriptor != null) {
                        openFileDescriptor.close();
                    }
                    return a4;
                } finally {
                }
            } finally {
            }
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // a.f.e.i
    public Typeface a(Context context, c.b bVar, Resources resources, int i) {
        Object b2 = b();
        for (c.C0006c c0006c : bVar.a()) {
            File a2 = j.a(context);
            if (a2 == null) {
                return null;
            }
            try {
                if (!j.a(a2, resources, c0006c.b())) {
                    return null;
                }
                if (!a(b2, a2.getPath(), c0006c.e(), c0006c.f())) {
                    return null;
                }
                a2.delete();
            } catch (RuntimeException unused) {
                return null;
            } finally {
                a2.delete();
            }
        }
        return a(b2);
    }
}
