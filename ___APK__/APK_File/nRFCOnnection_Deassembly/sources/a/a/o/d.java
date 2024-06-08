package a.a.o;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;

/* loaded from: classes.dex */
public class d extends ContextWrapper {

    /* renamed from: a, reason: collision with root package name */
    private int f36a;

    /* renamed from: b, reason: collision with root package name */
    private Resources.Theme f37b;

    /* renamed from: c, reason: collision with root package name */
    private LayoutInflater f38c;

    /* renamed from: d, reason: collision with root package name */
    private Configuration f39d;

    /* renamed from: e, reason: collision with root package name */
    private Resources f40e;

    public d() {
        super(null);
    }

    private Resources b() {
        if (this.f40e == null) {
            Configuration configuration = this.f39d;
            if (configuration == null) {
                this.f40e = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                this.f40e = createConfigurationContext(configuration).getResources();
            }
        }
        return this.f40e;
    }

    private void c() {
        boolean z = this.f37b == null;
        if (z) {
            this.f37b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f37b.setTo(theme);
            }
        }
        a(this.f37b, this.f36a, z);
    }

    public int a() {
        return this.f36a;
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return getResources().getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return b();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if ("layout_inflater".equals(str)) {
            if (this.f38c == null) {
                this.f38c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
            }
            return this.f38c;
        }
        return getBaseContext().getSystemService(str);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f37b;
        if (theme != null) {
            return theme;
        }
        if (this.f36a == 0) {
            this.f36a = a.a.i.Theme_AppCompat_Light;
        }
        c();
        return this.f37b;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        if (this.f36a != i) {
            this.f36a = i;
            c();
        }
    }

    public d(Context context, int i) {
        super(context);
        this.f36a = i;
    }

    protected void a(Resources.Theme theme, int i, boolean z) {
        theme.applyStyle(i, true);
    }

    public d(Context context, Resources.Theme theme) {
        super(context);
        this.f37b = theme;
    }
}
