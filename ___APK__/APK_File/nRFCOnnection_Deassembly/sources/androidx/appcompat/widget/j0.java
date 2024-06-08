package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public final class j0 {
    private static j0 i;

    /* renamed from: a, reason: collision with root package name */
    private WeakHashMap<Context, a.d.h<ColorStateList>> f1020a;

    /* renamed from: b, reason: collision with root package name */
    private a.d.a<String, d> f1021b;

    /* renamed from: c, reason: collision with root package name */
    private a.d.h<String> f1022c;

    /* renamed from: d, reason: collision with root package name */
    private final WeakHashMap<Context, a.d.d<WeakReference<Drawable.ConstantState>>> f1023d = new WeakHashMap<>(0);

    /* renamed from: e, reason: collision with root package name */
    private TypedValue f1024e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f1025f;
    private e g;
    private static final PorterDuff.Mode h = PorterDuff.Mode.SRC_IN;
    private static final c j = new c(6);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a implements d {
        a() {
        }

        @Override // androidx.appcompat.widget.j0.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return a.a.l.a.a.b(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e2) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e2);
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b implements d {
        b() {
        }

        @Override // androidx.appcompat.widget.j0.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return a.o.a.a.c.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e2) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e2);
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c extends a.d.e<Integer, PorterDuffColorFilter> {
        public c(int i) {
            super(i);
        }

        private static int b(int i, PorterDuff.Mode mode) {
            return ((i + 31) * 31) + mode.hashCode();
        }

        PorterDuffColorFilter a(int i, PorterDuff.Mode mode) {
            return b(Integer.valueOf(b(i, mode)));
        }

        PorterDuffColorFilter a(int i, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return a((c) Integer.valueOf(b(i, mode)), (Integer) porterDuffColorFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface d {
        Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface e {
        ColorStateList a(Context context, int i);

        PorterDuff.Mode a(int i);

        Drawable a(j0 j0Var, Context context, int i);

        boolean a(Context context, int i, Drawable drawable);

        boolean b(Context context, int i, Drawable drawable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class f implements d {
        f() {
        }

        @Override // androidx.appcompat.widget.j0.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return a.o.a.a.i.createFromXmlInner(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e2) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e2);
                return null;
            }
        }
    }

    public static synchronized j0 a() {
        j0 j0Var;
        synchronized (j0.class) {
            if (i == null) {
                i = new j0();
                a(i);
            }
            j0Var = i;
        }
        return j0Var;
    }

    private Drawable c(Context context, int i2) {
        if (this.f1024e == null) {
            this.f1024e = new TypedValue();
        }
        TypedValue typedValue = this.f1024e;
        context.getResources().getValue(i2, typedValue, true);
        long a2 = a(typedValue);
        Drawable a3 = a(context, a2);
        if (a3 != null) {
            return a3;
        }
        e eVar = this.g;
        Drawable a4 = eVar == null ? null : eVar.a(this, context, i2);
        if (a4 != null) {
            a4.setChangingConfigurations(typedValue.changingConfigurations);
            a(context, a2, a4);
        }
        return a4;
    }

    private ColorStateList d(Context context, int i2) {
        a.d.h<ColorStateList> hVar;
        WeakHashMap<Context, a.d.h<ColorStateList>> weakHashMap = this.f1020a;
        if (weakHashMap == null || (hVar = weakHashMap.get(context)) == null) {
            return null;
        }
        return hVar.a(i2);
    }

    private Drawable e(Context context, int i2) {
        int next;
        a.d.a<String, d> aVar = this.f1021b;
        if (aVar == null || aVar.isEmpty()) {
            return null;
        }
        a.d.h<String> hVar = this.f1022c;
        if (hVar != null) {
            String a2 = hVar.a(i2);
            if ("appcompat_skip_skip".equals(a2) || (a2 != null && this.f1021b.get(a2) == null)) {
                return null;
            }
        } else {
            this.f1022c = new a.d.h<>();
        }
        if (this.f1024e == null) {
            this.f1024e = new TypedValue();
        }
        TypedValue typedValue = this.f1024e;
        Resources resources = context.getResources();
        resources.getValue(i2, typedValue, true);
        long a3 = a(typedValue);
        Drawable a4 = a(context, a3);
        if (a4 != null) {
            return a4;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i2);
                AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next == 2) {
                    String name = xml.getName();
                    this.f1022c.a(i2, name);
                    d dVar = this.f1021b.get(name);
                    if (dVar != null) {
                        a4 = dVar.a(context, xml, asAttributeSet, context.getTheme());
                    }
                    if (a4 != null) {
                        a4.setChangingConfigurations(typedValue.changingConfigurations);
                        a(context, a3, a4);
                    }
                } else {
                    throw new XmlPullParserException("No start tag found");
                }
            } catch (Exception e2) {
                Log.e("ResourceManagerInternal", "Exception while inflating drawable", e2);
            }
        }
        if (a4 == null) {
            this.f1022c.a(i2, "appcompat_skip_skip");
        }
        return a4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized ColorStateList b(Context context, int i2) {
        ColorStateList d2;
        d2 = d(context, i2);
        if (d2 == null) {
            d2 = this.g == null ? null : this.g.a(context, i2);
            if (d2 != null) {
                a(context, i2, d2);
            }
        }
        return d2;
    }

    private static void a(j0 j0Var) {
        if (Build.VERSION.SDK_INT < 24) {
            j0Var.a("vector", new f());
            j0Var.a("animated-vector", new b());
            j0Var.a("animated-selector", new a());
        }
    }

    private void b(Context context) {
        if (this.f1025f) {
            return;
        }
        this.f1025f = true;
        Drawable a2 = a(context, a.a.m.a.abc_vector_test);
        if (a2 == null || !a(a2)) {
            this.f1025f = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    public synchronized void a(e eVar) {
        this.g = eVar;
    }

    public synchronized Drawable a(Context context, int i2) {
        return a(context, i2, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Drawable a(Context context, int i2, boolean z) {
        Drawable e2;
        b(context);
        e2 = e(context, i2);
        if (e2 == null) {
            e2 = c(context, i2);
        }
        if (e2 == null) {
            e2 = a.f.d.b.c(context, i2);
        }
        if (e2 != null) {
            e2 = a(context, i2, z, e2);
        }
        if (e2 != null) {
            a0.b(e2);
        }
        return e2;
    }

    public synchronized void a(Context context) {
        a.d.d<WeakReference<Drawable.ConstantState>> dVar = this.f1023d.get(context);
        if (dVar != null) {
            dVar.a();
        }
    }

    private static long a(TypedValue typedValue) {
        return (typedValue.assetCookie << 32) | typedValue.data;
    }

    private Drawable a(Context context, int i2, boolean z, Drawable drawable) {
        ColorStateList b2 = b(context, i2);
        if (b2 != null) {
            if (a0.a(drawable)) {
                drawable = drawable.mutate();
            }
            Drawable i3 = androidx.core.graphics.drawable.a.i(drawable);
            androidx.core.graphics.drawable.a.a(i3, b2);
            PorterDuff.Mode a2 = a(i2);
            if (a2 == null) {
                return i3;
            }
            androidx.core.graphics.drawable.a.a(i3, a2);
            return i3;
        }
        e eVar = this.g;
        if ((eVar == null || !eVar.b(context, i2, drawable)) && !a(context, i2, drawable) && z) {
            return null;
        }
        return drawable;
    }

    private synchronized Drawable a(Context context, long j2) {
        a.d.d<WeakReference<Drawable.ConstantState>> dVar = this.f1023d.get(context);
        if (dVar == null) {
            return null;
        }
        WeakReference<Drawable.ConstantState> b2 = dVar.b(j2);
        if (b2 != null) {
            Drawable.ConstantState constantState = b2.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            dVar.a(j2);
        }
        return null;
    }

    private synchronized boolean a(Context context, long j2, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        a.d.d<WeakReference<Drawable.ConstantState>> dVar = this.f1023d.get(context);
        if (dVar == null) {
            dVar = new a.d.d<>();
            this.f1023d.put(context, dVar);
        }
        dVar.c(j2, new WeakReference<>(constantState));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Drawable a(Context context, y0 y0Var, int i2) {
        Drawable e2 = e(context, i2);
        if (e2 == null) {
            e2 = y0Var.a(i2);
        }
        if (e2 == null) {
            return null;
        }
        return a(context, i2, false, e2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(Context context, int i2, Drawable drawable) {
        e eVar = this.g;
        return eVar != null && eVar.a(context, i2, drawable);
    }

    private void a(String str, d dVar) {
        if (this.f1021b == null) {
            this.f1021b = new a.d.a<>();
        }
        this.f1021b.put(str, dVar);
    }

    PorterDuff.Mode a(int i2) {
        e eVar = this.g;
        if (eVar == null) {
            return null;
        }
        return eVar.a(i2);
    }

    private void a(Context context, int i2, ColorStateList colorStateList) {
        if (this.f1020a == null) {
            this.f1020a = new WeakHashMap<>();
        }
        a.d.h<ColorStateList> hVar = this.f1020a.get(context);
        if (hVar == null) {
            hVar = new a.d.h<>();
            this.f1020a.put(context, hVar);
        }
        hVar.a(i2, colorStateList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Drawable drawable, r0 r0Var, int[] iArr) {
        if (a0.a(drawable) && drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        if (r0Var.f1085d || r0Var.f1084c) {
            drawable.setColorFilter(a(r0Var.f1085d ? r0Var.f1082a : null, r0Var.f1084c ? r0Var.f1083b : h, iArr));
        } else {
            drawable.clearColorFilter();
        }
        if (Build.VERSION.SDK_INT <= 23) {
            drawable.invalidateSelf();
        }
    }

    private static PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return a(colorStateList.getColorForState(iArr, 0), mode);
    }

    public static synchronized PorterDuffColorFilter a(int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter a2;
        synchronized (j0.class) {
            a2 = j.a(i2, mode);
            if (a2 == null) {
                a2 = new PorterDuffColorFilter(i2, mode);
                j.a(i2, mode, a2);
            }
        }
        return a2;
    }

    private static boolean a(Drawable drawable) {
        return (drawable instanceof a.o.a.a.i) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }
}
