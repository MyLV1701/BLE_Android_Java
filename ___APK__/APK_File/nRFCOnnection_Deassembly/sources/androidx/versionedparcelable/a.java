package androidx.versionedparcelable;

import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    protected final a.d.a<String, Method> f1988a;

    /* renamed from: b, reason: collision with root package name */
    protected final a.d.a<String, Method> f1989b;

    /* renamed from: c, reason: collision with root package name */
    protected final a.d.a<String, Class> f1990c;

    public a(a.d.a<String, Method> aVar, a.d.a<String, Method> aVar2, a.d.a<String, Class> aVar3) {
        this.f1988a = aVar;
        this.f1989b = aVar2;
        this.f1990c = aVar3;
    }

    protected abstract void a();

    protected abstract void a(Parcelable parcelable);

    protected abstract void a(CharSequence charSequence);

    protected abstract void a(String str);

    protected abstract void a(boolean z);

    public void a(boolean z, boolean z2) {
    }

    protected abstract void a(byte[] bArr);

    protected abstract boolean a(int i);

    public boolean a(boolean z, int i) {
        return !a(i) ? z : d();
    }

    protected abstract a b();

    protected abstract void b(int i);

    public void b(boolean z, int i) {
        b(i);
        a(z);
    }

    protected abstract void c(int i);

    public boolean c() {
        return false;
    }

    protected abstract boolean d();

    protected abstract byte[] e();

    protected abstract CharSequence f();

    protected abstract int g();

    protected abstract <T extends Parcelable> T h();

    protected abstract String i();

    /* JADX INFO: Access modifiers changed from: protected */
    public <T extends c> T j() {
        String i = i();
        if (i == null) {
            return null;
        }
        return (T) a(i, b());
    }

    public int a(int i, int i2) {
        return !a(i2) ? i : g();
    }

    public void b(byte[] bArr, int i) {
        b(i);
        a(bArr);
    }

    public String a(String str, int i) {
        return !a(i) ? str : i();
    }

    public void b(CharSequence charSequence, int i) {
        b(i);
        a(charSequence);
    }

    public byte[] a(byte[] bArr, int i) {
        return !a(i) ? bArr : e();
    }

    public void b(int i, int i2) {
        b(i2);
        c(i);
    }

    public <T extends Parcelable> T a(T t, int i) {
        return !a(i) ? t : (T) h();
    }

    public void b(String str, int i) {
        b(i);
        a(str);
    }

    public CharSequence a(CharSequence charSequence, int i) {
        return !a(i) ? charSequence : f();
    }

    public void b(Parcelable parcelable, int i) {
        b(i);
        a(parcelable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(c cVar) {
        if (cVar == null) {
            a((String) null);
            return;
        }
        b(cVar);
        a b2 = b();
        a((a) cVar, b2);
        b2.a();
    }

    public void b(c cVar, int i) {
        b(i);
        a(cVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b(c cVar) {
        try {
            a(a((Class<? extends c>) cVar.getClass()).getName());
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException(cVar.getClass().getSimpleName() + " does not have a Parcelizer", e2);
        }
    }

    private Method b(String str) {
        Method method = this.f1988a.get(str);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, a.class.getClassLoader()).getDeclaredMethod("read", a.class);
        this.f1988a.put(str, declaredMethod);
        return declaredMethod;
    }

    public <T extends c> T a(T t, int i) {
        return !a(i) ? t : (T) j();
    }

    protected <T extends c> T a(String str, a aVar) {
        try {
            return (T) b(str).invoke(null, aVar);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e4);
        } catch (InvocationTargetException e5) {
            if (e5.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e5.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e5);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Method b(Class cls) {
        Method method = this.f1989b.get(cls.getName());
        if (method != null) {
            return method;
        }
        Class a2 = a((Class<? extends c>) cls);
        System.currentTimeMillis();
        Method declaredMethod = a2.getDeclaredMethod("write", cls, a.class);
        this.f1989b.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    protected <T extends c> void a(T t, a aVar) {
        try {
            b(t.getClass()).invoke(null, t, aVar);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e4);
        } catch (InvocationTargetException e5) {
            if (e5.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e5.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e5);
        }
    }

    private Class a(Class<? extends c> cls) {
        Class cls2 = this.f1990c.get(cls.getName());
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        this.f1990c.put(cls.getName(), cls3);
        return cls3;
    }
}
