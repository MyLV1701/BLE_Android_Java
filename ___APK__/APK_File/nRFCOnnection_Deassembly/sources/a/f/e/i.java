package a.f.e;

import a.f.d.d.c;
import a.f.i.b;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.preference.Preference;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    private ConcurrentHashMap<Long, c.b> f178a = new ConcurrentHashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements c<b.f> {
        a(i iVar) {
        }

        @Override // a.f.e.i.c
        public int a(b.f fVar) {
            return fVar.d();
        }

        @Override // a.f.e.i.c
        public boolean b(b.f fVar) {
            return fVar.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements c<c.C0006c> {
        b(i iVar) {
        }

        @Override // a.f.e.i.c
        public int a(c.C0006c c0006c) {
            return c0006c.e();
        }

        @Override // a.f.e.i.c
        public boolean b(c.C0006c c0006c) {
            return c0006c.f();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface c<T> {
        int a(T t);

        boolean b(T t);
    }

    private static <T> T a(T[] tArr, int i, c<T> cVar) {
        int i2 = (i & 1) == 0 ? 400 : 700;
        boolean z = (i & 2) != 0;
        T t = null;
        int i3 = Preference.DEFAULT_ORDER;
        for (T t2 : tArr) {
            int abs = (Math.abs(cVar.a(t2) - i2) * 2) + (cVar.b(t2) == z ? 0 : 1);
            if (t == null || i3 > abs) {
                t = t2;
                i3 = abs;
            }
        }
        return t;
    }

    private static long b(Typeface typeface) {
        if (typeface == null) {
            return 0L;
        }
        try {
            Field declaredField = Typeface.class.getDeclaredField("native_instance");
            declaredField.setAccessible(true);
            return ((Number) declaredField.get(typeface)).longValue();
        } catch (IllegalAccessException e2) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e2);
            return 0L;
        } catch (NoSuchFieldException e3) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e3);
            return 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public b.f a(b.f[] fVarArr, int i) {
        return (b.f) a(fVarArr, i, new a(this));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Typeface a(Context context, InputStream inputStream) {
        File a2 = j.a(context);
        if (a2 == null) {
            return null;
        }
        try {
            if (j.a(a2, inputStream)) {
                return Typeface.createFromFile(a2.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            a2.delete();
        }
    }

    public Typeface a(Context context, CancellationSignal cancellationSignal, b.f[] fVarArr, int i) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        if (fVarArr.length < 1) {
            return null;
        }
        try {
            inputStream = context.getContentResolver().openInputStream(a(fVarArr, i).c());
        } catch (IOException unused) {
            inputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            Typeface a2 = a(context, inputStream);
            j.a(inputStream);
            return a2;
        } catch (IOException unused2) {
            j.a(inputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            inputStream2 = inputStream;
            j.a(inputStream2);
            throw th;
        }
    }

    private c.C0006c a(c.b bVar, int i) {
        return (c.C0006c) a(bVar.a(), i, new b(this));
    }

    public Typeface a(Context context, c.b bVar, Resources resources, int i) {
        c.C0006c a2 = a(bVar, i);
        if (a2 == null) {
            return null;
        }
        Typeface a3 = a.f.e.c.a(context, resources, a2.b(), a2.a(), i);
        a(a3, bVar);
        return a3;
    }

    public Typeface a(Context context, Resources resources, int i, String str, int i2) {
        File a2 = j.a(context);
        if (a2 == null) {
            return null;
        }
        try {
            if (j.a(a2, resources, i)) {
                return Typeface.createFromFile(a2.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            a2.delete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c.b a(Typeface typeface) {
        long b2 = b(typeface);
        if (b2 == 0) {
            return null;
        }
        return this.f178a.get(Long.valueOf(b2));
    }

    private void a(Typeface typeface, c.b bVar) {
        long b2 = b(typeface);
        if (b2 != 0) {
            this.f178a.put(Long.valueOf(b2), bVar);
        }
    }
}
