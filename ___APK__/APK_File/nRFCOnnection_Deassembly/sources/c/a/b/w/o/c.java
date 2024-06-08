package c.a.b.w.o;

import c.a.b.k;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
final class c extends b {

    /* renamed from: d, reason: collision with root package name */
    private static Class f2315d;

    /* renamed from: b, reason: collision with root package name */
    private final Object f2316b = c();

    /* renamed from: c, reason: collision with root package name */
    private final Field f2317c = b();

    private static Object c() {
        try {
            f2315d = Class.forName("sun.misc.Unsafe");
            Field declaredField = f2315d.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // c.a.b.w.o.b
    public void a(AccessibleObject accessibleObject) {
        if (b(accessibleObject)) {
            return;
        }
        try {
            accessibleObject.setAccessible(true);
        } catch (SecurityException e2) {
            throw new k("Gson couldn't modify fields for " + accessibleObject + "\nand sun.misc.Unsafe not found.\nEither write a custom type adapter, or make fields accessible, or include sun.misc.Unsafe.", e2);
        }
    }

    boolean b(AccessibleObject accessibleObject) {
        if (this.f2316b != null && this.f2317c != null) {
            try {
                f2315d.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE).invoke(this.f2316b, accessibleObject, Long.valueOf(((Long) f2315d.getMethod("objectFieldOffset", Field.class).invoke(this.f2316b, this.f2317c)).longValue()), true);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private static Field b() {
        try {
            return AccessibleObject.class.getDeclaredField("override");
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }
}
