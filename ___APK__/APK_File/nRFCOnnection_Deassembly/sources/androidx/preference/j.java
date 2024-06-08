package androidx.preference;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes.dex */
public class j {

    /* renamed from: a, reason: collision with root package name */
    private Context f1652a;

    /* renamed from: c, reason: collision with root package name */
    private SharedPreferences f1654c;

    /* renamed from: d, reason: collision with root package name */
    private e f1655d;

    /* renamed from: e, reason: collision with root package name */
    private SharedPreferences.Editor f1656e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f1657f;
    private String g;
    private int h;
    private PreferenceScreen j;
    private d k;
    private c l;
    private a m;
    private b n;

    /* renamed from: b, reason: collision with root package name */
    private long f1653b = 0;
    private int i = 0;

    /* loaded from: classes.dex */
    public interface a {
        void onDisplayPreferenceDialog(Preference preference);
    }

    /* loaded from: classes.dex */
    public interface b {
        void onNavigateToScreen(PreferenceScreen preferenceScreen);
    }

    /* loaded from: classes.dex */
    public interface c {
        boolean onPreferenceTreeClick(Preference preference);
    }

    /* loaded from: classes.dex */
    public static abstract class d {
        public abstract boolean a(Preference preference, Preference preference2);

        public abstract boolean b(Preference preference, Preference preference2);
    }

    public j(Context context) {
        this.f1652a = context;
        a(b(context));
    }

    public static SharedPreferences a(Context context) {
        return context.getSharedPreferences(b(context), j());
    }

    private static String b(Context context) {
        return context.getPackageName() + "_preferences";
    }

    private static int j() {
        return 0;
    }

    public b c() {
        return this.n;
    }

    public c d() {
        return this.l;
    }

    public d e() {
        return this.k;
    }

    public e f() {
        return this.f1655d;
    }

    public PreferenceScreen g() {
        return this.j;
    }

    public SharedPreferences h() {
        Context a2;
        if (f() != null) {
            return null;
        }
        if (this.f1654c == null) {
            if (this.i != 1) {
                a2 = this.f1652a;
            } else {
                a2 = a.f.d.b.a(this.f1652a);
            }
            this.f1654c = a2.getSharedPreferences(this.g, this.h);
        }
        return this.f1654c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean i() {
        return !this.f1657f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long b() {
        long j;
        synchronized (this) {
            j = this.f1653b;
            this.f1653b = 1 + j;
        }
        return j;
    }

    public PreferenceScreen a(Context context, int i, PreferenceScreen preferenceScreen) {
        a(true);
        PreferenceScreen preferenceScreen2 = (PreferenceScreen) new i(context, this).a(i, preferenceScreen);
        preferenceScreen2.onAttachedToHierarchy(this);
        a(false);
        return preferenceScreen2;
    }

    public void a(String str) {
        this.g = str;
        this.f1654c = null;
    }

    public boolean a(PreferenceScreen preferenceScreen) {
        PreferenceScreen preferenceScreen2 = this.j;
        if (preferenceScreen == preferenceScreen2) {
            return false;
        }
        if (preferenceScreen2 != null) {
            preferenceScreen2.onDetached();
        }
        this.j = preferenceScreen;
        return true;
    }

    public <T extends Preference> T a(CharSequence charSequence) {
        PreferenceScreen preferenceScreen = this.j;
        if (preferenceScreen == null) {
            return null;
        }
        return (T) preferenceScreen.a(charSequence);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SharedPreferences.Editor a() {
        if (this.f1655d != null) {
            return null;
        }
        if (this.f1657f) {
            if (this.f1656e == null) {
                this.f1656e = h().edit();
            }
            return this.f1656e;
        }
        return h().edit();
    }

    private void a(boolean z) {
        SharedPreferences.Editor editor;
        if (!z && (editor = this.f1656e) != null) {
            editor.apply();
        }
        this.f1657f = z;
    }

    public void a(a aVar) {
        this.m = aVar;
    }

    public void a(Preference preference) {
        a aVar = this.m;
        if (aVar != null) {
            aVar.onDisplayPreferenceDialog(preference);
        }
    }

    public void a(c cVar) {
        this.l = cVar;
    }

    public void a(b bVar) {
        this.n = bVar;
    }
}
