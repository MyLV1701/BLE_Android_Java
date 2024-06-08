package androidx.appcompat.app;

import a.a.o.b;
import a.a.o.f;
import a.f.l.a0;
import a.f.l.b0;
import a.f.l.e;
import a.f.l.e0;
import a.f.l.r;
import a.f.l.w;
import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.app.b;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.n;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.c0;
import androidx.appcompat.widget.t0;
import androidx.appcompat.widget.y;
import androidx.appcompat.widget.y0;
import androidx.appcompat.widget.z0;
import androidx.lifecycle.g;
import java.lang.Thread;
import java.util.List;
import java.util.Map;
import no.nordicsemi.android.dfu.DfuBaseService;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class h extends androidx.appcompat.app.g implements g.a, LayoutInflater.Factory2 {
    private static final Map<Class<?>, Integer> b0 = new a.d.a();
    private static final boolean c0;
    private static final int[] d0;
    private static boolean e0;
    private static final boolean f0;
    private boolean A;
    boolean B;
    boolean C;
    boolean D;
    boolean E;
    boolean F;
    private boolean G;
    private p[] H;
    private p I;
    private boolean J;
    private boolean K;
    private boolean L;
    private boolean M;
    boolean N;
    private int O;
    private int P;
    private boolean Q;
    private boolean R;
    private m S;
    private m T;
    boolean U;
    int V;
    private final Runnable W;
    private boolean X;
    private Rect Y;
    private Rect Z;
    private AppCompatViewInflater a0;

    /* renamed from: e, reason: collision with root package name */
    final Object f668e;

    /* renamed from: f, reason: collision with root package name */
    final Context f669f;
    Window g;
    private k h;
    final androidx.appcompat.app.f i;
    androidx.appcompat.app.a j;
    MenuInflater k;
    private CharSequence l;
    private y m;
    private i n;
    private q o;
    a.a.o.b p;
    ActionBarContextView q;
    PopupWindow r;
    Runnable s;
    a0 t;
    private boolean u;
    private boolean v;
    private ViewGroup w;
    private TextView x;
    private View y;
    private boolean z;

    /* loaded from: classes.dex */
    static class a implements Thread.UncaughtExceptionHandler {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Thread.UncaughtExceptionHandler f670a;

        a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.f670a = uncaughtExceptionHandler;
        }

        private boolean a(Throwable th) {
            String message;
            if (!(th instanceof Resources.NotFoundException) || (message = th.getMessage()) == null) {
                return false;
            }
            return message.contains("drawable") || message.contains("Drawable");
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            if (a(th)) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
                notFoundException.initCause(th.getCause());
                notFoundException.setStackTrace(th.getStackTrace());
                this.f670a.uncaughtException(thread, notFoundException);
                return;
            }
            this.f670a.uncaughtException(thread, th);
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            if ((hVar.V & 1) != 0) {
                hVar.g(0);
            }
            h hVar2 = h.this;
            if ((hVar2.V & 4096) != 0) {
                hVar2.g(108);
            }
            h hVar3 = h.this;
            hVar3.U = false;
            hVar3.V = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements r {
        c() {
        }

        @Override // a.f.l.r
        public e0 a(View view, e0 e0Var) {
            int e2 = e0Var.e();
            int k = h.this.k(e2);
            if (e2 != k) {
                e0Var = e0Var.a(e0Var.c(), k, e0Var.d(), e0Var.b());
            }
            return w.b(view, e0Var);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements c0.a {
        d() {
        }

        @Override // androidx.appcompat.widget.c0.a
        public void a(Rect rect) {
            rect.top = h.this.k(rect.top);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements ContentFrameLayout.a {
        e() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void a() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void onDetachedFromWindow() {
            h.this.l();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f implements Runnable {

        /* loaded from: classes.dex */
        class a extends a.f.l.c0 {
            a() {
            }

            @Override // a.f.l.b0
            public void b(View view) {
                h.this.q.setAlpha(1.0f);
                h.this.t.a((b0) null);
                h.this.t = null;
            }

            @Override // a.f.l.c0, a.f.l.b0
            public void c(View view) {
                h.this.q.setVisibility(0);
            }
        }

        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            hVar.r.showAtLocation(hVar.q, 55, 0, 0);
            h.this.n();
            if (h.this.v()) {
                h.this.q.setAlpha(0.0f);
                h hVar2 = h.this;
                a0 a2 = w.a(hVar2.q);
                a2.a(1.0f);
                hVar2.t = a2;
                h.this.t.a(new a());
                return;
            }
            h.this.q.setAlpha(1.0f);
            h.this.q.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class g extends a.f.l.c0 {
        g() {
        }

        @Override // a.f.l.b0
        public void b(View view) {
            h.this.q.setAlpha(1.0f);
            h.this.t.a((b0) null);
            h.this.t = null;
        }

        @Override // a.f.l.c0, a.f.l.b0
        public void c(View view) {
            h.this.q.setVisibility(0);
            h.this.q.sendAccessibilityEvent(32);
            if (h.this.q.getParent() instanceof View) {
                w.L((View) h.this.q.getParent());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class j implements b.a {

        /* renamed from: b, reason: collision with root package name */
        private b.a f680b;

        /* loaded from: classes.dex */
        class a extends a.f.l.c0 {
            a() {
            }

            @Override // a.f.l.b0
            public void b(View view) {
                h.this.q.setVisibility(8);
                h hVar = h.this;
                PopupWindow popupWindow = hVar.r;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (hVar.q.getParent() instanceof View) {
                    w.L((View) h.this.q.getParent());
                }
                h.this.q.removeAllViews();
                h.this.t.a((b0) null);
                h.this.t = null;
            }
        }

        public j(b.a aVar) {
            this.f680b = aVar;
        }

        @Override // a.a.o.b.a
        public boolean onActionItemClicked(a.a.o.b bVar, MenuItem menuItem) {
            return this.f680b.onActionItemClicked(bVar, menuItem);
        }

        @Override // a.a.o.b.a
        public boolean onCreateActionMode(a.a.o.b bVar, Menu menu) {
            return this.f680b.onCreateActionMode(bVar, menu);
        }

        @Override // a.a.o.b.a
        public void onDestroyActionMode(a.a.o.b bVar) {
            this.f680b.onDestroyActionMode(bVar);
            h hVar = h.this;
            if (hVar.r != null) {
                hVar.g.getDecorView().removeCallbacks(h.this.s);
            }
            h hVar2 = h.this;
            if (hVar2.q != null) {
                hVar2.n();
                h hVar3 = h.this;
                a0 a2 = w.a(hVar3.q);
                a2.a(0.0f);
                hVar3.t = a2;
                h.this.t.a(new a());
            }
            h hVar4 = h.this;
            androidx.appcompat.app.f fVar = hVar4.i;
            if (fVar != null) {
                fVar.onSupportActionModeFinished(hVar4.p);
            }
            h.this.p = null;
        }

        @Override // a.a.o.b.a
        public boolean onPrepareActionMode(a.a.o.b bVar, Menu menu) {
            return this.f680b.onPrepareActionMode(bVar, menu);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class l extends m {

        /* renamed from: c, reason: collision with root package name */
        private final PowerManager f684c;

        l(Context context) {
            super();
            this.f684c = (PowerManager) context.getSystemService("power");
        }

        @Override // androidx.appcompat.app.h.m
        IntentFilter b() {
            if (Build.VERSION.SDK_INT < 21) {
                return null;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.h.m
        public int c() {
            return (Build.VERSION.SDK_INT < 21 || !this.f684c.isPowerSaveMode()) ? 1 : 2;
        }

        @Override // androidx.appcompat.app.h.m
        public void d() {
            h.this.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public abstract class m {

        /* renamed from: a, reason: collision with root package name */
        private BroadcastReceiver f686a;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a extends BroadcastReceiver {
            a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                m.this.d();
            }
        }

        m() {
        }

        void a() {
            BroadcastReceiver broadcastReceiver = this.f686a;
            if (broadcastReceiver != null) {
                try {
                    h.this.f669f.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.f686a = null;
            }
        }

        abstract IntentFilter b();

        abstract int c();

        abstract void d();

        void e() {
            a();
            IntentFilter b2 = b();
            if (b2 == null || b2.countActions() == 0) {
                return;
            }
            if (this.f686a == null) {
                this.f686a = new a();
            }
            h.this.f669f.registerReceiver(this.f686a, b2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class n extends m {

        /* renamed from: c, reason: collision with root package name */
        private final androidx.appcompat.app.m f689c;

        n(androidx.appcompat.app.m mVar) {
            super();
            this.f689c = mVar;
        }

        @Override // androidx.appcompat.app.h.m
        IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.h.m
        public int c() {
            return this.f689c.a() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.h.m
        public void d() {
            h.this.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class o extends ContentFrameLayout {
        public o(Context context) {
            super(context);
        }

        private boolean a(int i, int i2) {
            return i < -5 || i2 < -5 || i > getWidth() + 5 || i2 > getHeight() + 5;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return h.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0 && a((int) motionEvent.getX(), (int) motionEvent.getY())) {
                h.this.f(0);
                return true;
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // android.view.View
        public void setBackgroundResource(int i) {
            setBackgroundDrawable(a.a.k.a.a.c(getContext(), i));
        }
    }

    static {
        boolean z = false;
        c0 = Build.VERSION.SDK_INT < 21;
        d0 = new int[]{R.attr.windowBackground};
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21 && i2 <= 25) {
            z = true;
        }
        f0 = z;
        if (!c0 || e0) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new a(Thread.getDefaultUncaughtExceptionHandler()));
        e0 = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(Activity activity, androidx.appcompat.app.f fVar) {
        this(activity, null, fVar, activity);
    }

    private void A() {
        if (this.v) {
            return;
        }
        this.w = z();
        CharSequence q2 = q();
        if (!TextUtils.isEmpty(q2)) {
            y yVar = this.m;
            if (yVar != null) {
                yVar.setWindowTitle(q2);
            } else if (u() != null) {
                u().a(q2);
            } else {
                TextView textView = this.x;
                if (textView != null) {
                    textView.setText(q2);
                }
            }
        }
        w();
        a(this.w);
        this.v = true;
        p a2 = a(0, false);
        if (this.N) {
            return;
        }
        if (a2 == null || a2.j == null) {
            l(108);
        }
    }

    private void B() {
        if (this.g == null) {
            Object obj = this.f668e;
            if (obj instanceof Activity) {
                a(((Activity) obj).getWindow());
            }
        }
        if (this.g == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    private m C() {
        if (this.T == null) {
            this.T = new l(this.f669f);
        }
        return this.T;
    }

    private void D() {
        A();
        if (this.B && this.j == null) {
            Object obj = this.f668e;
            if (obj instanceof Activity) {
                this.j = new androidx.appcompat.app.n((Activity) obj, this.C);
            } else if (obj instanceof Dialog) {
                this.j = new androidx.appcompat.app.n((Dialog) obj);
            }
            androidx.appcompat.app.a aVar = this.j;
            if (aVar != null) {
                aVar.c(this.X);
            }
        }
    }

    private boolean E() {
        if (!this.R && (this.f668e instanceof Activity)) {
            PackageManager packageManager = this.f669f.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            try {
                ActivityInfo activityInfo = packageManager.getActivityInfo(new ComponentName(this.f669f, this.f668e.getClass()), 0);
                this.Q = (activityInfo == null || (activityInfo.configChanges & DfuBaseService.ERROR_REMOTE_TYPE_SECURE) == 0) ? false : true;
            } catch (PackageManager.NameNotFoundException e2) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e2);
                this.Q = false;
            }
        }
        this.R = true;
        return this.Q;
    }

    private void F() {
        if (this.v) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private androidx.appcompat.app.e G() {
        for (Context context = this.f669f; context != null; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof androidx.appcompat.app.e) {
                return (androidx.appcompat.app.e) context;
            }
            if (!(context instanceof ContextWrapper)) {
                break;
            }
        }
        return null;
    }

    private void l(int i2) {
        this.V = (1 << i2) | this.V;
        if (this.U) {
            return;
        }
        w.a(this.g.getDecorView(), this.W);
        this.U = true;
    }

    private int m(int i2) {
        if (i2 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        }
        if (i2 != 9) {
            return i2;
        }
        Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
        return 109;
    }

    private void w() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.w.findViewById(R.id.content);
        View decorView = this.g.getDecorView();
        contentFrameLayout.a(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray obtainStyledAttributes = this.f669f.obtainStyledAttributes(a.a.j.AppCompatTheme);
        obtainStyledAttributes.getValue(a.a.j.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(a.a.j.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        if (obtainStyledAttributes.hasValue(a.a.j.AppCompatTheme_windowFixedWidthMajor)) {
            obtainStyledAttributes.getValue(a.a.j.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
        }
        if (obtainStyledAttributes.hasValue(a.a.j.AppCompatTheme_windowFixedWidthMinor)) {
            obtainStyledAttributes.getValue(a.a.j.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
        }
        if (obtainStyledAttributes.hasValue(a.a.j.AppCompatTheme_windowFixedHeightMajor)) {
            obtainStyledAttributes.getValue(a.a.j.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
        }
        if (obtainStyledAttributes.hasValue(a.a.j.AppCompatTheme_windowFixedHeightMinor)) {
            obtainStyledAttributes.getValue(a.a.j.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    private int x() {
        int i2 = this.O;
        return i2 != -100 ? i2 : androidx.appcompat.app.g.m();
    }

    private void y() {
        m mVar = this.S;
        if (mVar != null) {
            mVar.a();
        }
        m mVar2 = this.T;
        if (mVar2 != null) {
            mVar2.a();
        }
    }

    private ViewGroup z() {
        ViewGroup viewGroup;
        Context context;
        TypedArray obtainStyledAttributes = this.f669f.obtainStyledAttributes(a.a.j.AppCompatTheme);
        if (obtainStyledAttributes.hasValue(a.a.j.AppCompatTheme_windowActionBar)) {
            if (obtainStyledAttributes.getBoolean(a.a.j.AppCompatTheme_windowNoTitle, false)) {
                b(1);
            } else if (obtainStyledAttributes.getBoolean(a.a.j.AppCompatTheme_windowActionBar, false)) {
                b(108);
            }
            if (obtainStyledAttributes.getBoolean(a.a.j.AppCompatTheme_windowActionBarOverlay, false)) {
                b(109);
            }
            if (obtainStyledAttributes.getBoolean(a.a.j.AppCompatTheme_windowActionModeOverlay, false)) {
                b(10);
            }
            this.E = obtainStyledAttributes.getBoolean(a.a.j.AppCompatTheme_android_windowIsFloating, false);
            obtainStyledAttributes.recycle();
            B();
            this.g.getDecorView();
            LayoutInflater from = LayoutInflater.from(this.f669f);
            if (!this.F) {
                if (this.E) {
                    viewGroup = (ViewGroup) from.inflate(a.a.g.abc_dialog_title_material, (ViewGroup) null);
                    this.C = false;
                    this.B = false;
                } else if (this.B) {
                    TypedValue typedValue = new TypedValue();
                    this.f669f.getTheme().resolveAttribute(a.a.a.actionBarTheme, typedValue, true);
                    int i2 = typedValue.resourceId;
                    if (i2 != 0) {
                        context = new a.a.o.d(this.f669f, i2);
                    } else {
                        context = this.f669f;
                    }
                    viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(a.a.g.abc_screen_toolbar, (ViewGroup) null);
                    this.m = (y) viewGroup.findViewById(a.a.f.decor_content_parent);
                    this.m.setWindowCallback(r());
                    if (this.C) {
                        this.m.a(109);
                    }
                    if (this.z) {
                        this.m.a(2);
                    }
                    if (this.A) {
                        this.m.a(5);
                    }
                } else {
                    viewGroup = null;
                }
            } else {
                if (this.D) {
                    viewGroup = (ViewGroup) from.inflate(a.a.g.abc_screen_simple_overlay_action_mode, (ViewGroup) null);
                } else {
                    viewGroup = (ViewGroup) from.inflate(a.a.g.abc_screen_simple, (ViewGroup) null);
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    w.a(viewGroup, new c());
                } else {
                    ((c0) viewGroup).setOnFitSystemWindowsListener(new d());
                }
            }
            if (viewGroup != null) {
                if (this.m == null) {
                    this.x = (TextView) viewGroup.findViewById(a.a.f.title);
                }
                z0.b(viewGroup);
                ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(a.a.f.action_bar_activity_content);
                ViewGroup viewGroup2 = (ViewGroup) this.g.findViewById(R.id.content);
                if (viewGroup2 != null) {
                    while (viewGroup2.getChildCount() > 0) {
                        View childAt = viewGroup2.getChildAt(0);
                        viewGroup2.removeViewAt(0);
                        contentFrameLayout.addView(childAt);
                    }
                    viewGroup2.setId(-1);
                    contentFrameLayout.setId(R.id.content);
                    if (viewGroup2 instanceof FrameLayout) {
                        ((FrameLayout) viewGroup2).setForeground(null);
                    }
                }
                this.g.setContentView(viewGroup);
                contentFrameLayout.setAttachListener(new e());
                return viewGroup;
            }
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.B + ", windowActionBarOverlay: " + this.C + ", android:windowIsFloating: " + this.E + ", windowActionModeOverlay: " + this.D + ", windowNoTitle: " + this.F + " }");
        }
        obtainStyledAttributes.recycle();
        throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    }

    @Override // androidx.appcompat.app.g
    public void a(Context context) {
        a(false);
        this.K = true;
    }

    void a(ViewGroup viewGroup) {
    }

    @Override // androidx.appcompat.app.g
    public void b(Bundle bundle) {
        A();
    }

    @Override // androidx.appcompat.app.g
    public void c(int i2) {
        A();
        ViewGroup viewGroup = (ViewGroup) this.w.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f669f).inflate(i2, viewGroup);
        this.h.a().onContentChanged();
    }

    @Override // androidx.appcompat.app.g
    public MenuInflater d() {
        if (this.k == null) {
            D();
            androidx.appcompat.app.a aVar = this.j;
            this.k = new a.a.o.g(aVar != null ? aVar.h() : this.f669f);
        }
        return this.k;
    }

    @Override // androidx.appcompat.app.g
    public androidx.appcompat.app.a e() {
        D();
        return this.j;
    }

    @Override // androidx.appcompat.app.g
    public void f() {
        LayoutInflater from = LayoutInflater.from(this.f669f);
        if (from.getFactory() == null) {
            a.f.l.f.b(from, this);
        } else {
            if (from.getFactory2() instanceof h) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    @Override // androidx.appcompat.app.g
    public void g() {
        androidx.appcompat.app.a e2 = e();
        if (e2 == null || !e2.i()) {
            l(0);
        }
    }

    @Override // androidx.appcompat.app.g
    public void h() {
        androidx.appcompat.app.g.b(this);
        if (this.U) {
            this.g.getDecorView().removeCallbacks(this.W);
        }
        this.M = false;
        this.N = true;
        androidx.appcompat.app.a aVar = this.j;
        if (aVar != null) {
            aVar.j();
        }
        y();
    }

    @Override // androidx.appcompat.app.g
    public void i() {
        androidx.appcompat.app.a e2 = e();
        if (e2 != null) {
            e2.f(true);
        }
    }

    @Override // androidx.appcompat.app.g
    public void j() {
        this.M = true;
        a();
        androidx.appcompat.app.g.a(this);
    }

    @Override // androidx.appcompat.app.g
    public void k() {
        this.M = false;
        androidx.appcompat.app.g.b(this);
        androidx.appcompat.app.a e2 = e();
        if (e2 != null) {
            e2.f(false);
        }
        if (this.f668e instanceof Dialog) {
            y();
        }
    }

    void n() {
        a0 a0Var = this.t;
        if (a0Var != null) {
            a0Var.a();
        }
    }

    final Context o() {
        androidx.appcompat.app.a e2 = e();
        Context h = e2 != null ? e2.h() : null;
        return h == null ? this.f669f : h;
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return a(view, str, context, attributeSet);
    }

    final m p() {
        if (this.S == null) {
            this.S = new n(androidx.appcompat.app.m.a(this.f669f));
        }
        return this.S;
    }

    final CharSequence q() {
        Object obj = this.f668e;
        if (obj instanceof Activity) {
            return ((Activity) obj).getTitle();
        }
        return this.l;
    }

    final Window.Callback r() {
        return this.g.getCallback();
    }

    public boolean s() {
        return this.u;
    }

    boolean t() {
        a.a.o.b bVar = this.p;
        if (bVar != null) {
            bVar.a();
            return true;
        }
        androidx.appcompat.app.a e2 = e();
        return e2 != null && e2.f();
    }

    final androidx.appcompat.app.a u() {
        return this.j;
    }

    final boolean v() {
        ViewGroup viewGroup;
        return this.v && (viewGroup = this.w) != null && w.F(viewGroup);
    }

    /* renamed from: androidx.appcompat.app.h$h, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private class C0040h implements b.InterfaceC0039b {
        C0040h() {
        }

        @Override // androidx.appcompat.app.b.InterfaceC0039b
        public boolean a() {
            androidx.appcompat.app.a e2 = h.this.e();
            return (e2 == null || (e2.g() & 4) == 0) ? false : true;
        }

        @Override // androidx.appcompat.app.b.InterfaceC0039b
        public Drawable b() {
            t0 a2 = t0.a(c(), (AttributeSet) null, new int[]{a.a.a.homeAsUpIndicator});
            Drawable b2 = a2.b(0);
            a2.a();
            return b2;
        }

        @Override // androidx.appcompat.app.b.InterfaceC0039b
        public Context c() {
            return h.this.o();
        }

        @Override // androidx.appcompat.app.b.InterfaceC0039b
        public void a(Drawable drawable, int i) {
            androidx.appcompat.app.a e2 = h.this.e();
            if (e2 != null) {
                e2.a(drawable);
                e2.b(i);
            }
        }

        @Override // androidx.appcompat.app.b.InterfaceC0039b
        public void a(int i) {
            androidx.appcompat.app.a e2 = h.this.e();
            if (e2 != null) {
                e2.b(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class i implements n.a {
        i() {
        }

        @Override // androidx.appcompat.view.menu.n.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            Window.Callback r = h.this.r();
            if (r == null) {
                return true;
            }
            r.onMenuOpened(108, gVar);
            return true;
        }

        @Override // androidx.appcompat.view.menu.n.a
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
            h.this.b(gVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(Dialog dialog, androidx.appcompat.app.f fVar) {
        this(dialog.getContext(), dialog.getWindow(), fVar, dialog);
    }

    @Override // androidx.appcompat.app.g
    public void b(View view, ViewGroup.LayoutParams layoutParams) {
        A();
        ViewGroup viewGroup = (ViewGroup) this.w.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.h.a().onContentChanged();
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static final class p {

        /* renamed from: a, reason: collision with root package name */
        int f691a;

        /* renamed from: b, reason: collision with root package name */
        int f692b;

        /* renamed from: c, reason: collision with root package name */
        int f693c;

        /* renamed from: d, reason: collision with root package name */
        int f694d;

        /* renamed from: e, reason: collision with root package name */
        int f695e;

        /* renamed from: f, reason: collision with root package name */
        int f696f;
        ViewGroup g;
        View h;
        View i;
        androidx.appcompat.view.menu.g j;
        androidx.appcompat.view.menu.e k;
        Context l;
        boolean m;
        boolean n;
        boolean o;
        public boolean p;
        boolean q = false;
        boolean r;
        Bundle s;

        p(int i) {
            this.f691a = i;
        }

        public boolean a() {
            if (this.h == null) {
                return false;
            }
            return this.i != null || this.k.d().getCount() > 0;
        }

        void a(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(a.a.a.actionBarPopupTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                newTheme.applyStyle(i, true);
            }
            newTheme.resolveAttribute(a.a.a.panelMenuListTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                newTheme.applyStyle(i2, true);
            } else {
                newTheme.applyStyle(a.a.i.Theme_AppCompat_CompactMenu, true);
            }
            a.a.o.d dVar = new a.a.o.d(context, 0);
            dVar.getTheme().setTo(newTheme);
            this.l = dVar;
            TypedArray obtainStyledAttributes = dVar.obtainStyledAttributes(a.a.j.AppCompatTheme);
            this.f692b = obtainStyledAttributes.getResourceId(a.a.j.AppCompatTheme_panelBackground, 0);
            this.f696f = obtainStyledAttributes.getResourceId(a.a.j.AppCompatTheme_android_windowAnimationStyle, 0);
            obtainStyledAttributes.recycle();
        }

        void a(androidx.appcompat.view.menu.g gVar) {
            androidx.appcompat.view.menu.e eVar;
            androidx.appcompat.view.menu.g gVar2 = this.j;
            if (gVar == gVar2) {
                return;
            }
            if (gVar2 != null) {
                gVar2.b(this.k);
            }
            this.j = gVar;
            if (gVar == null || (eVar = this.k) == null) {
                return;
            }
            gVar.a(eVar);
        }

        androidx.appcompat.view.menu.o a(n.a aVar) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                this.k = new androidx.appcompat.view.menu.e(this.l, a.a.g.abc_list_menu_item_layout);
                this.k.a(aVar);
                this.j.a(this.k);
            }
            return this.k.a(this.g);
        }
    }

    private h(Context context, Window window, androidx.appcompat.app.f fVar, Object obj) {
        Integer num;
        androidx.appcompat.app.e G;
        this.t = null;
        this.u = true;
        this.O = -100;
        this.W = new b();
        this.f669f = context;
        this.i = fVar;
        this.f668e = obj;
        if (this.O == -100 && (this.f668e instanceof Dialog) && (G = G()) != null) {
            this.O = G.getDelegate().c();
        }
        if (this.O == -100 && (num = b0.get(this.f668e.getClass())) != null) {
            this.O = num.intValue();
            b0.remove(this.f668e.getClass());
        }
        if (window != null) {
            a(window);
        }
        androidx.appcompat.widget.j.c();
    }

    private boolean e(int i2, KeyEvent keyEvent) {
        boolean z;
        boolean z2;
        y yVar;
        if (this.p != null) {
            return false;
        }
        p a2 = a(i2, true);
        if (i2 == 0 && (yVar = this.m) != null && yVar.f() && !ViewConfiguration.get(this.f669f).hasPermanentMenuKey()) {
            if (!this.m.a()) {
                if (!this.N && b(a2, keyEvent)) {
                    z = this.m.e();
                }
                z = false;
            } else {
                z = this.m.d();
            }
        } else if (!a2.o && !a2.n) {
            if (a2.m) {
                if (a2.r) {
                    a2.m = false;
                    z2 = b(a2, keyEvent);
                } else {
                    z2 = true;
                }
                if (z2) {
                    a(a2, keyEvent);
                    z = true;
                }
            }
            z = false;
        } else {
            z = a2.o;
            a(a2, true);
        }
        if (z) {
            AudioManager audioManager = (AudioManager) this.f669f.getSystemService("audio");
            if (audioManager != null) {
                audioManager.playSoundEffect(0);
            } else {
                Log.w("AppCompatDelegate", "Couldn't get audio manager");
            }
        }
        return z;
    }

    @Override // androidx.appcompat.app.g
    public void a(Bundle bundle) {
        this.K = true;
        a(false);
        B();
        Object obj = this.f668e;
        if (obj instanceof Activity) {
            String str = null;
            try {
                str = androidx.core.app.e.b((Activity) obj);
            } catch (IllegalArgumentException unused) {
            }
            if (str != null) {
                androidx.appcompat.app.a u = u();
                if (u == null) {
                    this.X = true;
                } else {
                    u.c(true);
                }
            }
        }
        this.L = true;
    }

    void i(int i2) {
        androidx.appcompat.app.a e2;
        if (i2 != 108 || (e2 = e()) == null) {
            return;
        }
        e2.b(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class k extends a.a.o.i {
        k(Window.Callback callback) {
            super(callback);
        }

        final ActionMode a(ActionMode.Callback callback) {
            f.a aVar = new f.a(h.this.f669f, callback);
            a.a.o.b a2 = h.this.a(aVar);
            if (a2 != null) {
                return aVar.a(a2);
            }
            return null;
        }

        @Override // a.a.o.i, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return h.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // a.a.o.i, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || h.this.b(keyEvent.getKeyCode(), keyEvent);
        }

        @Override // a.a.o.i, android.view.Window.Callback
        public void onContentChanged() {
        }

        @Override // a.a.o.i, android.view.Window.Callback
        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof androidx.appcompat.view.menu.g)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        @Override // a.a.o.i, android.view.Window.Callback
        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            h.this.i(i);
            return true;
        }

        @Override // a.a.o.i, android.view.Window.Callback
        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            h.this.j(i);
        }

        @Override // a.a.o.i, android.view.Window.Callback
        public boolean onPreparePanel(int i, View view, Menu menu) {
            androidx.appcompat.view.menu.g gVar = menu instanceof androidx.appcompat.view.menu.g ? (androidx.appcompat.view.menu.g) menu : null;
            if (i == 0 && gVar == null) {
                return false;
            }
            if (gVar != null) {
                gVar.c(true);
            }
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (gVar != null) {
                gVar.c(false);
            }
            return onPreparePanel;
        }

        @Override // a.a.o.i, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            androidx.appcompat.view.menu.g gVar;
            p a2 = h.this.a(0, true);
            if (a2 != null && (gVar = a2.j) != null) {
                super.onProvideKeyboardShortcuts(list, gVar, i);
            } else {
                super.onProvideKeyboardShortcuts(list, menu, i);
            }
        }

        @Override // a.a.o.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            if (h.this.s()) {
                return a(callback);
            }
            return super.onWindowStartingActionMode(callback);
        }

        @Override // a.a.o.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            if (h.this.s() && i == 0) {
                return a(callback);
            }
            return super.onWindowStartingActionMode(callback, i);
        }
    }

    void g(int i2) {
        p a2;
        p a3 = a(i2, true);
        if (a3.j != null) {
            Bundle bundle = new Bundle();
            a3.j.c(bundle);
            if (bundle.size() > 0) {
                a3.s = bundle;
            }
            a3.j.s();
            a3.j.clear();
        }
        a3.r = true;
        a3.q = true;
        if ((i2 != 108 && i2 != 0) || this.m == null || (a2 = a(0, false)) == null) {
            return;
        }
        a2.m = false;
        b(a2, (KeyEvent) null);
    }

    void j(int i2) {
        if (i2 == 108) {
            androidx.appcompat.app.a e2 = e();
            if (e2 != null) {
                e2.b(false);
                return;
            }
            return;
        }
        if (i2 == 0) {
            p a2 = a(i2, true);
            if (a2.o) {
                a(a2, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class q implements n.a {
        q() {
        }

        @Override // androidx.appcompat.view.menu.n.a
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
            androidx.appcompat.view.menu.g m = gVar.m();
            boolean z2 = m != gVar;
            h hVar = h.this;
            if (z2) {
                gVar = m;
            }
            p a2 = hVar.a((Menu) gVar);
            if (a2 != null) {
                if (z2) {
                    h.this.a(a2.f691a, a2, m);
                    h.this.a(a2, true);
                } else {
                    h.this.a(a2, z);
                }
            }
        }

        @Override // androidx.appcompat.view.menu.n.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            Window.Callback r;
            if (gVar != null) {
                return true;
            }
            h hVar = h.this;
            if (!hVar.B || (r = hVar.r()) == null || h.this.N) {
                return true;
            }
            r.onMenuOpened(108, gVar);
            return true;
        }
    }

    void l() {
        androidx.appcompat.view.menu.g gVar;
        y yVar = this.m;
        if (yVar != null) {
            yVar.g();
        }
        if (this.r != null) {
            this.g.getDecorView().removeCallbacks(this.s);
            if (this.r.isShowing()) {
                try {
                    this.r.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.r = null;
        }
        n();
        p a2 = a(0, false);
        if (a2 == null || (gVar = a2.j) == null) {
            return;
        }
        gVar.close();
    }

    @Override // androidx.appcompat.app.g
    public void c(Bundle bundle) {
        if (this.O != -100) {
            b0.put(this.f668e.getClass(), Integer.valueOf(this.O));
        }
    }

    @Override // androidx.appcompat.app.g
    public void d(int i2) {
        this.P = i2;
    }

    void f(int i2) {
        a(a(i2, true), true);
    }

    private boolean d(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        p a2 = a(i2, true);
        if (a2.o) {
            return false;
        }
        return b(a2, keyEvent);
    }

    @Override // androidx.appcompat.app.g
    public boolean b(int i2) {
        int m2 = m(i2);
        if (this.F && m2 == 108) {
            return false;
        }
        if (this.B && m2 == 1) {
            this.B = false;
        }
        if (m2 == 1) {
            F();
            this.F = true;
            return true;
        }
        if (m2 == 2) {
            F();
            this.z = true;
            return true;
        }
        if (m2 == 5) {
            F();
            this.A = true;
            return true;
        }
        if (m2 == 10) {
            F();
            this.D = true;
            return true;
        }
        if (m2 == 108) {
            F();
            this.B = true;
            return true;
        }
        if (m2 != 109) {
            return this.g.requestFeature(m2);
        }
        F();
        this.C = true;
        return true;
    }

    int k(int i2) {
        boolean z;
        boolean z2;
        ActionBarContextView actionBarContextView = this.q;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.q.getLayoutParams();
            if (this.q.isShown()) {
                if (this.Y == null) {
                    this.Y = new Rect();
                    this.Z = new Rect();
                }
                Rect rect = this.Y;
                Rect rect2 = this.Z;
                rect.set(0, i2, 0, 0);
                z0.a(this.w, rect, rect2);
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i2 : 0)) {
                    marginLayoutParams.topMargin = i2;
                    View view = this.y;
                    if (view == null) {
                        this.y = new View(this.f669f);
                        this.y.setBackgroundColor(this.f669f.getResources().getColor(a.a.c.abc_input_method_navigation_guard));
                        this.w.addView(this.y, -1, new ViewGroup.LayoutParams(-1, i2));
                    } else {
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        if (layoutParams.height != i2) {
                            layoutParams.height = i2;
                            this.y.setLayoutParams(layoutParams);
                        }
                    }
                    z2 = true;
                } else {
                    z2 = false;
                }
                z = this.y != null;
                if (!this.D && z) {
                    i2 = 0;
                }
            } else {
                if (marginLayoutParams.topMargin != 0) {
                    marginLayoutParams.topMargin = 0;
                    z2 = true;
                } else {
                    z2 = false;
                }
                z = false;
            }
            if (z2) {
                this.q.setLayoutParams(marginLayoutParams);
            }
        }
        View view2 = this.y;
        if (view2 != null) {
            view2.setVisibility(z ? 0 : 8);
        }
        return i2;
    }

    boolean c(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            boolean z = this.J;
            this.J = false;
            p a2 = a(0, false);
            if (a2 != null && a2.o) {
                if (!z) {
                    a(a2, true);
                }
                return true;
            }
            if (t()) {
                return true;
            }
        } else if (i2 == 82) {
            e(0, keyEvent);
            return true;
        }
        return false;
    }

    int h(int i2) {
        if (i2 == -100) {
            return -1;
        }
        if (i2 == -1) {
            return i2;
        }
        if (i2 == 0) {
            if (Build.VERSION.SDK_INT < 23 || ((UiModeManager) this.f669f.getSystemService(UiModeManager.class)).getNightMode() != 0) {
                return p().c();
            }
            return -1;
        }
        if (i2 == 1 || i2 == 2) {
            return i2;
        }
        if (i2 == 3) {
            return C().c();
        }
        throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
    }

    @Override // androidx.appcompat.app.g
    public void a(Toolbar toolbar) {
        if (this.f668e instanceof Activity) {
            androidx.appcompat.app.a e2 = e();
            if (!(e2 instanceof androidx.appcompat.app.n)) {
                this.k = null;
                if (e2 != null) {
                    e2.j();
                }
                if (toolbar != null) {
                    androidx.appcompat.app.k kVar = new androidx.appcompat.app.k(toolbar, q(), this.h);
                    this.j = kVar;
                    this.g.setCallback(kVar.l());
                } else {
                    this.j = null;
                    this.g.setCallback(this.h);
                }
                g();
                return;
            }
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
        }
    }

    private boolean c(p pVar) {
        Context context = this.f669f;
        int i2 = pVar.f691a;
        if ((i2 == 0 || i2 == 108) && this.m != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(a.a.a.actionBarTheme, typedValue, true);
            Resources.Theme theme2 = null;
            if (typedValue.resourceId != 0) {
                theme2 = context.getResources().newTheme();
                theme2.setTo(theme);
                theme2.applyStyle(typedValue.resourceId, true);
                theme2.resolveAttribute(a.a.a.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(a.a.a.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (theme2 == null) {
                    theme2 = context.getResources().newTheme();
                    theme2.setTo(theme);
                }
                theme2.applyStyle(typedValue.resourceId, true);
            }
            if (theme2 != null) {
                a.a.o.d dVar = new a.a.o.d(context, 0);
                dVar.getTheme().setTo(theme2);
                context = dVar;
            }
        }
        androidx.appcompat.view.menu.g gVar = new androidx.appcompat.view.menu.g(context);
        gVar.a(this);
        pVar.a(gVar);
        return true;
    }

    @Override // androidx.appcompat.app.g
    public <T extends View> T a(int i2) {
        A();
        return (T) this.g.findViewById(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    a.a.o.b b(a.a.o.b.a r8) {
        /*
            Method dump skipped, instructions count: 371
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.h.b(a.a.o.b$a):a.a.o.b");
    }

    @Override // androidx.appcompat.app.g
    public void a(Configuration configuration) {
        androidx.appcompat.app.a e2;
        if (this.B && this.v && (e2 = e()) != null) {
            e2.a(configuration);
        }
        androidx.appcompat.widget.j.b().a(this.f669f);
        a(false);
    }

    @Override // androidx.appcompat.app.g
    public void a(View view) {
        A();
        ViewGroup viewGroup = (ViewGroup) this.w.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.h.a().onContentChanged();
    }

    @Override // androidx.appcompat.app.g
    public int c() {
        return this.O;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void c(int i2, boolean z) {
        Resources resources = this.f669f.getResources();
        Configuration configuration = new Configuration(resources.getConfiguration());
        configuration.uiMode = i2 | (resources.getConfiguration().uiMode & (-49));
        resources.updateConfiguration(configuration, null);
        if (Build.VERSION.SDK_INT < 26) {
            androidx.appcompat.app.j.a(resources);
        }
        int i3 = this.P;
        if (i3 != 0) {
            this.f669f.setTheme(i3);
            if (Build.VERSION.SDK_INT >= 23) {
                this.f669f.getTheme().applyStyle(this.P, true);
            }
        }
        if (z) {
            Object obj = this.f668e;
            if (obj instanceof Activity) {
                Activity activity = (Activity) obj;
                if (activity instanceof androidx.lifecycle.j) {
                    if (((androidx.lifecycle.j) activity).getLifecycle().a().a(g.b.STARTED)) {
                        activity.onConfigurationChanged(configuration);
                    }
                } else if (this.M) {
                    activity.onConfigurationChanged(configuration);
                }
            }
        }
    }

    @Override // androidx.appcompat.app.g
    public void a(View view, ViewGroup.LayoutParams layoutParams) {
        A();
        ((ViewGroup) this.w.findViewById(R.id.content)).addView(view, layoutParams);
        this.h.a().onContentChanged();
    }

    private void a(Window window) {
        if (this.g == null) {
            Window.Callback callback = window.getCallback();
            if (!(callback instanceof k)) {
                this.h = new k(callback);
                window.setCallback(this.h);
                t0 a2 = t0.a(this.f669f, (AttributeSet) null, d0);
                Drawable c2 = a2.c(0);
                if (c2 != null) {
                    window.setBackgroundDrawable(c2);
                }
                a2.a();
                this.g = window;
                return;
            }
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    @Override // androidx.appcompat.app.g
    public final void a(CharSequence charSequence) {
        this.l = charSequence;
        y yVar = this.m;
        if (yVar != null) {
            yVar.setWindowTitle(charSequence);
            return;
        }
        if (u() != null) {
            u().a(charSequence);
            return;
        }
        TextView textView = this.x;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    @Override // androidx.appcompat.view.menu.g.a
    public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        p a2;
        Window.Callback r = r();
        if (r == null || this.N || (a2 = a((Menu) gVar.m())) == null) {
            return false;
        }
        return r.onMenuItemSelected(a2.f691a, menuItem);
    }

    @Override // androidx.appcompat.view.menu.g.a
    public void a(androidx.appcompat.view.menu.g gVar) {
        a(gVar, true);
    }

    @Override // androidx.appcompat.app.g
    public a.a.o.b a(b.a aVar) {
        androidx.appcompat.app.f fVar;
        if (aVar != null) {
            a.a.o.b bVar = this.p;
            if (bVar != null) {
                bVar.a();
            }
            j jVar = new j(aVar);
            androidx.appcompat.app.a e2 = e();
            if (e2 != null) {
                this.p = e2.a(jVar);
                a.a.o.b bVar2 = this.p;
                if (bVar2 != null && (fVar = this.i) != null) {
                    fVar.onSupportActionModeStarted(bVar2);
                }
            }
            if (this.p == null) {
                this.p = b(jVar);
            }
            return this.p;
        }
        throw new IllegalArgumentException("ActionMode callback can not be null.");
    }

    boolean a(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.f668e;
        if (((obj instanceof e.a) || (obj instanceof androidx.appcompat.app.i)) && (decorView = this.g.getDecorView()) != null && a.f.l.e.a(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.h.a().dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? a(keyCode, keyEvent) : c(keyCode, keyEvent);
    }

    boolean a(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            this.J = (keyEvent.getFlags() & 128) != 0;
        } else if (i2 == 82) {
            d(0, keyEvent);
            return true;
        }
        return false;
    }

    boolean b(int i2, KeyEvent keyEvent) {
        androidx.appcompat.app.a e2 = e();
        if (e2 != null && e2.a(i2, keyEvent)) {
            return true;
        }
        p pVar = this.I;
        if (pVar != null && a(pVar, keyEvent.getKeyCode(), keyEvent, 1)) {
            p pVar2 = this.I;
            if (pVar2 != null) {
                pVar2.n = true;
            }
            return true;
        }
        if (this.I == null) {
            p a2 = a(0, true);
            b(a2, keyEvent);
            boolean a3 = a(a2, keyEvent.getKeyCode(), keyEvent, 1);
            a2.m = false;
            if (a3) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View a(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z;
        boolean z2 = false;
        if (this.a0 == null) {
            String string = this.f669f.obtainStyledAttributes(a.a.j.AppCompatTheme).getString(a.a.j.AppCompatTheme_viewInflaterClass);
            if (string != null && !AppCompatViewInflater.class.getName().equals(string)) {
                try {
                    this.a0 = (AppCompatViewInflater) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.a0 = new AppCompatViewInflater();
                }
            } else {
                this.a0 = new AppCompatViewInflater();
            }
        }
        if (c0) {
            if (attributeSet instanceof XmlPullParser) {
                if (((XmlPullParser) attributeSet).getDepth() > 1) {
                    z2 = true;
                }
            } else {
                z2 = a((ViewParent) view);
            }
            z = z2;
        } else {
            z = false;
        }
        return this.a0.createView(view, str, context, attributeSet, z, c0, true, y0.b());
    }

    private boolean b(p pVar) {
        pVar.a(o());
        pVar.g = new o(pVar.l);
        pVar.f693c = 81;
        return true;
    }

    private boolean b(p pVar, KeyEvent keyEvent) {
        y yVar;
        y yVar2;
        y yVar3;
        if (this.N) {
            return false;
        }
        if (pVar.m) {
            return true;
        }
        p pVar2 = this.I;
        if (pVar2 != null && pVar2 != pVar) {
            a(pVar2, false);
        }
        Window.Callback r = r();
        if (r != null) {
            pVar.i = r.onCreatePanelView(pVar.f691a);
        }
        int i2 = pVar.f691a;
        boolean z = i2 == 0 || i2 == 108;
        if (z && (yVar3 = this.m) != null) {
            yVar3.b();
        }
        if (pVar.i == null && (!z || !(u() instanceof androidx.appcompat.app.k))) {
            if (pVar.j == null || pVar.r) {
                if (pVar.j == null && (!c(pVar) || pVar.j == null)) {
                    return false;
                }
                if (z && this.m != null) {
                    if (this.n == null) {
                        this.n = new i();
                    }
                    this.m.a(pVar.j, this.n);
                }
                pVar.j.s();
                if (!r.onCreatePanelMenu(pVar.f691a, pVar.j)) {
                    pVar.a((androidx.appcompat.view.menu.g) null);
                    if (z && (yVar = this.m) != null) {
                        yVar.a(null, this.n);
                    }
                    return false;
                }
                pVar.r = false;
            }
            pVar.j.s();
            Bundle bundle = pVar.s;
            if (bundle != null) {
                pVar.j.a(bundle);
                pVar.s = null;
            }
            if (!r.onPreparePanel(0, pVar.i, pVar.j)) {
                if (z && (yVar2 = this.m) != null) {
                    yVar2.a(null, this.n);
                }
                pVar.j.r();
                return false;
            }
            pVar.p = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            pVar.j.setQwertyMode(pVar.p);
            pVar.j.r();
        }
        pVar.m = true;
        pVar.n = false;
        this.I = pVar;
        return true;
    }

    private boolean a(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.g.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || w.E((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    private void a(p pVar, KeyEvent keyEvent) {
        int i2;
        ViewGroup.LayoutParams layoutParams;
        if (pVar.o || this.N) {
            return;
        }
        if (pVar.f691a == 0) {
            if ((this.f669f.getResources().getConfiguration().screenLayout & 15) == 4) {
                return;
            }
        }
        Window.Callback r = r();
        if (r != null && !r.onMenuOpened(pVar.f691a, pVar.j)) {
            a(pVar, true);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f669f.getSystemService("window");
        if (windowManager != null && b(pVar, keyEvent)) {
            if (pVar.g != null && !pVar.q) {
                View view = pVar.i;
                if (view != null && (layoutParams = view.getLayoutParams()) != null && layoutParams.width == -1) {
                    i2 = -1;
                    pVar.n = false;
                    WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i2, -2, pVar.f694d, pVar.f695e, 1002, 8519680, -3);
                    layoutParams2.gravity = pVar.f693c;
                    layoutParams2.windowAnimations = pVar.f696f;
                    windowManager.addView(pVar.g, layoutParams2);
                    pVar.o = true;
                }
            } else {
                ViewGroup viewGroup = pVar.g;
                if (viewGroup == null) {
                    if (!b(pVar) || pVar.g == null) {
                        return;
                    }
                } else if (pVar.q && viewGroup.getChildCount() > 0) {
                    pVar.g.removeAllViews();
                }
                if (!a(pVar) || !pVar.a()) {
                    return;
                }
                ViewGroup.LayoutParams layoutParams3 = pVar.h.getLayoutParams();
                if (layoutParams3 == null) {
                    layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
                }
                pVar.g.setBackgroundResource(pVar.f692b);
                ViewParent parent = pVar.h.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(pVar.h);
                }
                pVar.g.addView(pVar.h, layoutParams3);
                if (!pVar.h.hasFocus()) {
                    pVar.h.requestFocus();
                }
            }
            i2 = -2;
            pVar.n = false;
            WindowManager.LayoutParams layoutParams22 = new WindowManager.LayoutParams(i2, -2, pVar.f694d, pVar.f695e, 1002, 8519680, -3);
            layoutParams22.gravity = pVar.f693c;
            layoutParams22.windowAnimations = pVar.f696f;
            windowManager.addView(pVar.g, layoutParams22);
            pVar.o = true;
        }
    }

    void b(androidx.appcompat.view.menu.g gVar) {
        if (this.G) {
            return;
        }
        this.G = true;
        this.m.g();
        Window.Callback r = r();
        if (r != null && !this.N) {
            r.onPanelClosed(108, gVar);
        }
        this.G = false;
    }

    private void a(androidx.appcompat.view.menu.g gVar, boolean z) {
        y yVar = this.m;
        if (yVar != null && yVar.f() && (!ViewConfiguration.get(this.f669f).hasPermanentMenuKey() || this.m.c())) {
            Window.Callback r = r();
            if (this.m.a() && z) {
                this.m.d();
                if (this.N) {
                    return;
                }
                r.onPanelClosed(108, a(0, true).j);
                return;
            }
            if (r == null || this.N) {
                return;
            }
            if (this.U && (this.V & 1) != 0) {
                this.g.getDecorView().removeCallbacks(this.W);
                this.W.run();
            }
            p a2 = a(0, true);
            androidx.appcompat.view.menu.g gVar2 = a2.j;
            if (gVar2 == null || a2.r || !r.onPreparePanel(0, a2.i, gVar2)) {
                return;
            }
            r.onMenuOpened(108, a2.j);
            this.m.e();
            return;
        }
        p a3 = a(0, true);
        a3.q = true;
        a(a3, false);
        a(a3, (KeyEvent) null);
    }

    private boolean b(int i2, boolean z) {
        int i3 = this.f669f.getApplicationContext().getResources().getConfiguration().uiMode & 48;
        boolean z2 = true;
        int i4 = i2 != 1 ? i2 != 2 ? i3 : 32 : 16;
        boolean E = E();
        boolean z3 = false;
        if ((f0 || i4 != i3) && !E && Build.VERSION.SDK_INT >= 17 && !this.K && (this.f668e instanceof ContextThemeWrapper)) {
            Configuration configuration = new Configuration();
            configuration.uiMode = (configuration.uiMode & (-49)) | i4;
            try {
                ((ContextThemeWrapper) this.f668e).applyOverrideConfiguration(configuration);
                z3 = true;
            } catch (IllegalStateException e2) {
                Log.e("AppCompatDelegate", "updateForNightMode. Calling applyOverrideConfiguration() failed with an exception. Will fall back to using Resources.updateConfiguration()", e2);
            }
        }
        int i5 = this.f669f.getResources().getConfiguration().uiMode & 48;
        if (!z3 && i5 != i4 && z && !E && this.K && (Build.VERSION.SDK_INT >= 17 || this.L)) {
            Object obj = this.f668e;
            if (obj instanceof Activity) {
                androidx.core.app.a.d((Activity) obj);
                z3 = true;
            }
        }
        if (z3 || i5 == i4) {
            z2 = z3;
        } else {
            c(i4, E);
        }
        if (z2) {
            Object obj2 = this.f668e;
            if (obj2 instanceof androidx.appcompat.app.e) {
                ((androidx.appcompat.app.e) obj2).onNightModeChanged(i2);
            }
        }
        return z2;
    }

    @Override // androidx.appcompat.app.g
    public final b.InterfaceC0039b b() {
        return new C0040h();
    }

    private boolean a(p pVar) {
        View view = pVar.i;
        if (view != null) {
            pVar.h = view;
            return true;
        }
        if (pVar.j == null) {
            return false;
        }
        if (this.o == null) {
            this.o = new q();
        }
        pVar.h = (View) pVar.a(this.o);
        return pVar.h != null;
    }

    void a(p pVar, boolean z) {
        ViewGroup viewGroup;
        y yVar;
        if (z && pVar.f691a == 0 && (yVar = this.m) != null && yVar.a()) {
            b(pVar.j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f669f.getSystemService("window");
        if (windowManager != null && pVar.o && (viewGroup = pVar.g) != null) {
            windowManager.removeView(viewGroup);
            if (z) {
                a(pVar.f691a, pVar, null);
            }
        }
        pVar.m = false;
        pVar.n = false;
        pVar.o = false;
        pVar.h = null;
        pVar.q = true;
        if (this.I == pVar) {
            this.I = null;
        }
    }

    void a(int i2, p pVar, Menu menu) {
        if (menu == null) {
            if (pVar == null && i2 >= 0) {
                p[] pVarArr = this.H;
                if (i2 < pVarArr.length) {
                    pVar = pVarArr[i2];
                }
            }
            if (pVar != null) {
                menu = pVar.j;
            }
        }
        if ((pVar == null || pVar.o) && !this.N) {
            this.h.a().onPanelClosed(i2, menu);
        }
    }

    p a(Menu menu) {
        p[] pVarArr = this.H;
        int length = pVarArr != null ? pVarArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            p pVar = pVarArr[i2];
            if (pVar != null && pVar.j == menu) {
                return pVar;
            }
        }
        return null;
    }

    protected p a(int i2, boolean z) {
        p[] pVarArr = this.H;
        if (pVarArr == null || pVarArr.length <= i2) {
            p[] pVarArr2 = new p[i2 + 1];
            if (pVarArr != null) {
                System.arraycopy(pVarArr, 0, pVarArr2, 0, pVarArr.length);
            }
            this.H = pVarArr2;
            pVarArr = pVarArr2;
        }
        p pVar = pVarArr[i2];
        if (pVar != null) {
            return pVar;
        }
        p pVar2 = new p(i2);
        pVarArr[i2] = pVar2;
        return pVar2;
    }

    private boolean a(p pVar, int i2, KeyEvent keyEvent, int i3) {
        androidx.appcompat.view.menu.g gVar;
        boolean z = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((pVar.m || b(pVar, keyEvent)) && (gVar = pVar.j) != null) {
            z = gVar.performShortcut(i2, keyEvent, i3);
        }
        if (z && (i3 & 1) == 0 && this.m == null) {
            a(pVar, true);
        }
        return z;
    }

    @Override // androidx.appcompat.app.g
    public boolean a() {
        return a(true);
    }

    private boolean a(boolean z) {
        if (this.N) {
            return false;
        }
        int x = x();
        boolean b2 = b(h(x), z);
        if (x == 0) {
            p().e();
        } else {
            m mVar = this.S;
            if (mVar != null) {
                mVar.a();
            }
        }
        if (x == 3) {
            C().e();
        } else {
            m mVar2 = this.T;
            if (mVar2 != null) {
                mVar2.a();
            }
        }
        return b2;
    }
}
