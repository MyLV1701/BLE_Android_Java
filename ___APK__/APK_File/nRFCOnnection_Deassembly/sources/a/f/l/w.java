package a.f.l;

import a.f.l.a;
import a.f.l.f0.c;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
public class w {

    /* renamed from: a, reason: collision with root package name */
    private static Field f331a;

    /* renamed from: b, reason: collision with root package name */
    private static boolean f332b;

    /* renamed from: c, reason: collision with root package name */
    private static Field f333c;

    /* renamed from: d, reason: collision with root package name */
    private static boolean f334d;

    /* renamed from: e, reason: collision with root package name */
    private static WeakHashMap<View, String> f335e;

    /* renamed from: f, reason: collision with root package name */
    private static WeakHashMap<View, a0> f336f;
    private static Field g;
    private static boolean h;
    private static ThreadLocal<Rect> i;

    /* loaded from: classes.dex */
    static class a implements View.OnApplyWindowInsetsListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ r f337a;

        a(r rVar) {
            this.f337a = rVar;
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            return this.f337a.a(view, e0.a(windowInsets)).h();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class f<T> {

        /* renamed from: a, reason: collision with root package name */
        private final int f339a;

        /* renamed from: b, reason: collision with root package name */
        private final Class<T> f340b;

        /* renamed from: c, reason: collision with root package name */
        private final int f341c;

        f(int i, Class<T> cls, int i2) {
            this(i, cls, 0, i2);
        }

        private boolean a() {
            return Build.VERSION.SDK_INT >= 19;
        }

        abstract T a(View view);

        abstract void a(View view, T t);

        abstract boolean a(T t, T t2);

        void b(View view, T t) {
            if (b()) {
                a(view, (View) t);
            } else if (a() && a(b(view), t)) {
                w.t(view);
                view.setTag(this.f339a, t);
                w.d(view, 0);
            }
        }

        f(int i, Class<T> cls, int i2, int i3) {
            this.f339a = i;
            this.f340b = cls;
            this.f341c = i3;
        }

        boolean a(Boolean bool, Boolean bool2) {
            return (bool == null ? false : bool.booleanValue()) == (bool2 == null ? false : bool2.booleanValue());
        }

        T b(View view) {
            if (b()) {
                return a(view);
            }
            if (!a()) {
                return null;
            }
            T t = (T) view.getTag(this.f339a);
            if (this.f340b.isInstance(t)) {
                return t;
            }
            return null;
        }

        private boolean b() {
            return Build.VERSION.SDK_INT >= this.f341c;
        }
    }

    /* loaded from: classes.dex */
    public interface g {
        boolean a(View view, KeyEvent keyEvent);
    }

    static {
        new AtomicInteger(1);
        f336f = null;
        h = false;
        int[] iArr = {a.f.b.accessibility_custom_action_0, a.f.b.accessibility_custom_action_1, a.f.b.accessibility_custom_action_2, a.f.b.accessibility_custom_action_3, a.f.b.accessibility_custom_action_4, a.f.b.accessibility_custom_action_5, a.f.b.accessibility_custom_action_6, a.f.b.accessibility_custom_action_7, a.f.b.accessibility_custom_action_8, a.f.b.accessibility_custom_action_9, a.f.b.accessibility_custom_action_10, a.f.b.accessibility_custom_action_11, a.f.b.accessibility_custom_action_12, a.f.b.accessibility_custom_action_13, a.f.b.accessibility_custom_action_14, a.f.b.accessibility_custom_action_15, a.f.b.accessibility_custom_action_16, a.f.b.accessibility_custom_action_17, a.f.b.accessibility_custom_action_18, a.f.b.accessibility_custom_action_19, a.f.b.accessibility_custom_action_20, a.f.b.accessibility_custom_action_21, a.f.b.accessibility_custom_action_22, a.f.b.accessibility_custom_action_23, a.f.b.accessibility_custom_action_24, a.f.b.accessibility_custom_action_25, a.f.b.accessibility_custom_action_26, a.f.b.accessibility_custom_action_27, a.f.b.accessibility_custom_action_28, a.f.b.accessibility_custom_action_29, a.f.b.accessibility_custom_action_30, a.f.b.accessibility_custom_action_31};
        new e();
    }

    public static boolean A(View view) {
        if (Build.VERSION.SDK_INT >= 15) {
            return view.hasOnClickListeners();
        }
        return false;
    }

    public static boolean B(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.hasOverlappingRendering();
        }
        return true;
    }

    public static boolean C(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.hasTransientState();
        }
        return false;
    }

    public static boolean D(View view) {
        Boolean b2 = a().b(view);
        if (b2 == null) {
            return false;
        }
        return b2.booleanValue();
    }

    public static boolean E(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            return view.isAttachedToWindow();
        }
        return view.getWindowToken() != null;
    }

    public static boolean F(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            return view.isLaidOut();
        }
        return view.getWidth() > 0 && view.getHeight() > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean G(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.isNestedScrollingEnabled();
        }
        if (view instanceof l) {
            return ((l) view).isNestedScrollingEnabled();
        }
        return false;
    }

    @Deprecated
    public static boolean H(View view) {
        return view.isOpaque();
    }

    public static boolean I(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.isPaddingRelative();
        }
        return false;
    }

    public static boolean J(View view) {
        Boolean b2 = d().b(view);
        if (b2 == null) {
            return false;
        }
        return b2.booleanValue();
    }

    public static void K(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation();
        } else {
            view.postInvalidate();
        }
    }

    public static void L(View view) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 20) {
            view.requestApplyInsets();
        } else if (i2 >= 16) {
            view.requestFitSystemWindows();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void M(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.stopNestedScroll();
        } else if (view instanceof l) {
            ((l) view).stopNestedScroll();
        }
    }

    private static void N(View view) {
        float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }

    @Deprecated
    public static boolean a(View view, int i2) {
        return view.canScrollHorizontally(i2);
    }

    private static Rect b() {
        if (i == null) {
            i = new ThreadLocal<>();
        }
        Rect rect = i.get();
        if (rect == null) {
            rect = new Rect();
            i.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    private static View.AccessibilityDelegate c(View view) {
        if (Build.VERSION.SDK_INT >= 29) {
            return view.getAccessibilityDelegate();
        }
        return d(view);
    }

    private static View.AccessibilityDelegate d(View view) {
        if (h) {
            return null;
        }
        if (g == null) {
            try {
                g = View.class.getDeclaredField("mAccessibilityDelegate");
                g.setAccessible(true);
            } catch (Throwable unused) {
                h = true;
                return null;
            }
        }
        try {
            Object obj = g.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            h = true;
            return null;
        }
    }

    public static int e(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            return view.getAccessibilityLiveRegion();
        }
        return 0;
    }

    public static void f(View view, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 23) {
            view.offsetTopAndBottom(i2);
            return;
        }
        if (i3 >= 21) {
            Rect b2 = b();
            boolean z = false;
            Object parent = view.getParent();
            if (parent instanceof View) {
                View view2 = (View) parent;
                b2.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
                z = !b2.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
            c(view, i2);
            if (z && b2.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                ((View) parent).invalidate(b2);
                return;
            }
            return;
        }
        c(view, i2);
    }

    public static void g(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            a(i2, view);
            d(view, 0);
        }
    }

    public static void h(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 19) {
            view.setAccessibilityLiveRegion(i2);
        }
    }

    public static void i(View view, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 19) {
            view.setImportantForAccessibility(i2);
        } else if (i3 >= 16) {
            if (i2 == 4) {
                i2 = 2;
            }
            view.setImportantForAccessibility(i2);
        }
    }

    public static void j(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setImportantForAutofill(i2);
        }
    }

    public static Display k(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getDisplay();
        }
        if (E(view)) {
            return ((WindowManager) view.getContext().getSystemService("window")).getDefaultDisplay();
        }
        return null;
    }

    public static float l(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getElevation();
        }
        return 0.0f;
    }

    public static boolean m(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getFitsSystemWindows();
        }
        return false;
    }

    public static int n(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getImportantForAccessibility();
        }
        return 0;
    }

    @SuppressLint({"InlinedApi"})
    public static int o(View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.getImportantForAutofill();
        }
        return 0;
    }

    @Deprecated
    public static int p(View view) {
        return view.getLayerType();
    }

    public static int q(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getLayoutDirection();
        }
        return 0;
    }

    public static int r(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getMinimumHeight();
        }
        if (!f334d) {
            try {
                f333c = View.class.getDeclaredField("mMinHeight");
                f333c.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            f334d = true;
        }
        Field field = f333c;
        if (field == null) {
            return 0;
        }
        try {
            return ((Integer) field.get(view)).intValue();
        } catch (Exception unused2) {
            return 0;
        }
    }

    public static int s(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getMinimumWidth();
        }
        if (!f332b) {
            try {
                f331a = View.class.getDeclaredField("mMinWidth");
                f331a.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            f332b = true;
        }
        Field field = f331a;
        if (field == null) {
            return 0;
        }
        try {
            return ((Integer) field.get(view)).intValue();
        } catch (Exception unused2) {
            return 0;
        }
    }

    static a.f.l.a t(View view) {
        a.f.l.a b2 = b(view);
        if (b2 == null) {
            b2 = new a.f.l.a();
        }
        a(view, b2);
        return b2;
    }

    public static int u(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getPaddingEnd();
        }
        return view.getPaddingRight();
    }

    public static int v(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getPaddingStart();
        }
        return view.getPaddingLeft();
    }

    public static ViewParent w(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getParentForAccessibility();
        }
        return view.getParent();
    }

    public static String x(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getTransitionName();
        }
        WeakHashMap<View, String> weakHashMap = f335e;
        if (weakHashMap == null) {
            return null;
        }
        return weakHashMap.get(view);
    }

    public static int y(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getWindowSystemUiVisibility();
        }
        return 0;
    }

    public static float z(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getZ();
        }
        return 0.0f;
    }

    public static void a(View view, a.f.l.f0.c cVar) {
        view.onInitializeAccessibilityNodeInfo(cVar.z());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b extends f<Boolean> {
        b(int i, Class cls, int i2) {
            super(i, cls, i2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // a.f.l.w.f
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public boolean a(Boolean bool, Boolean bool2) {
            return !a(bool, bool2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // a.f.l.w.f
        public Boolean a(View view) {
            return Boolean.valueOf(view.isScreenReaderFocusable());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // a.f.l.w.f
        public void a(View view, Boolean bool) {
            view.setScreenReaderFocusable(bool.booleanValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c extends f<CharSequence> {
        c(int i, Class cls, int i2, int i3) {
            super(i, cls, i2, i3);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // a.f.l.w.f
        public CharSequence a(View view) {
            return view.getAccessibilityPaneTitle();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // a.f.l.w.f
        public void a(View view, CharSequence charSequence) {
            view.setAccessibilityPaneTitle(charSequence);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // a.f.l.w.f
        public boolean a(CharSequence charSequence, CharSequence charSequence2) {
            return !TextUtils.equals(charSequence, charSequence2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d extends f<Boolean> {
        d(int i, Class cls, int i2) {
            super(i, cls, i2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // a.f.l.w.f
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public boolean a(Boolean bool, Boolean bool2) {
            return !a(bool, bool2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // a.f.l.w.f
        public Boolean a(View view) {
            return Boolean.valueOf(view.isAccessibilityHeading());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // a.f.l.w.f
        public void a(View view, Boolean bool) {
            view.setAccessibilityHeading(bool.booleanValue());
        }
    }

    /* loaded from: classes.dex */
    static class e implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {

        /* renamed from: b, reason: collision with root package name */
        private WeakHashMap<View, Boolean> f338b = new WeakHashMap<>();

        e() {
        }

        private void a(View view, boolean z) {
            boolean z2 = view.getVisibility() == 0;
            if (z != z2) {
                if (z2) {
                    w.d(view, 16);
                }
                this.f338b.put(view, Boolean.valueOf(z2));
            }
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            for (Map.Entry<View, Boolean> entry : this.f338b.entrySet()) {
                a(entry.getKey(), entry.getValue().booleanValue());
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            a(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }

        private void a(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }
    }

    /* loaded from: classes.dex */
    static class h {

        /* renamed from: d, reason: collision with root package name */
        private static final ArrayList<WeakReference<View>> f342d = new ArrayList<>();

        /* renamed from: a, reason: collision with root package name */
        private WeakHashMap<View, Boolean> f343a = null;

        /* renamed from: b, reason: collision with root package name */
        private SparseArray<WeakReference<View>> f344b = null;

        /* renamed from: c, reason: collision with root package name */
        private WeakReference<KeyEvent> f345c = null;

        h() {
        }

        private SparseArray<WeakReference<View>> a() {
            if (this.f344b == null) {
                this.f344b = new SparseArray<>();
            }
            return this.f344b;
        }

        private View b(View view, KeyEvent keyEvent) {
            WeakHashMap<View, Boolean> weakHashMap = this.f343a;
            if (weakHashMap != null && weakHashMap.containsKey(view)) {
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        View b2 = b(viewGroup.getChildAt(childCount), keyEvent);
                        if (b2 != null) {
                            return b2;
                        }
                    }
                }
                if (c(view, keyEvent)) {
                    return view;
                }
            }
            return null;
        }

        private boolean c(View view, KeyEvent keyEvent) {
            ArrayList arrayList = (ArrayList) view.getTag(a.f.b.tag_unhandled_key_listeners);
            if (arrayList == null) {
                return false;
            }
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (((g) arrayList.get(size)).a(view, keyEvent)) {
                    return true;
                }
            }
            return false;
        }

        static h a(View view) {
            h hVar = (h) view.getTag(a.f.b.tag_unhandled_key_event_manager);
            if (hVar != null) {
                return hVar;
            }
            h hVar2 = new h();
            view.setTag(a.f.b.tag_unhandled_key_event_manager, hVar2);
            return hVar2;
        }

        private void b() {
            WeakHashMap<View, Boolean> weakHashMap = this.f343a;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            if (f342d.isEmpty()) {
                return;
            }
            synchronized (f342d) {
                if (this.f343a == null) {
                    this.f343a = new WeakHashMap<>();
                }
                for (int size = f342d.size() - 1; size >= 0; size--) {
                    View view = f342d.get(size).get();
                    if (view == null) {
                        f342d.remove(size);
                    } else {
                        this.f343a.put(view, Boolean.TRUE);
                        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                            this.f343a.put((View) parent, Boolean.TRUE);
                        }
                    }
                }
            }
        }

        boolean a(View view, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 0) {
                b();
            }
            View b2 = b(view, keyEvent);
            if (keyEvent.getAction() == 0) {
                int keyCode = keyEvent.getKeyCode();
                if (b2 != null && !KeyEvent.isModifierKey(keyCode)) {
                    a().put(keyCode, new WeakReference<>(b2));
                }
            }
            return b2 != null;
        }

        boolean a(KeyEvent keyEvent) {
            int indexOfKey;
            WeakReference<KeyEvent> weakReference = this.f345c;
            if (weakReference != null && weakReference.get() == keyEvent) {
                return false;
            }
            this.f345c = new WeakReference<>(keyEvent);
            WeakReference<View> weakReference2 = null;
            SparseArray<WeakReference<View>> a2 = a();
            if (keyEvent.getAction() == 1 && (indexOfKey = a2.indexOfKey(keyEvent.getKeyCode())) >= 0) {
                weakReference2 = a2.valueAt(indexOfKey);
                a2.removeAt(indexOfKey);
            }
            if (weakReference2 == null) {
                weakReference2 = a2.get(keyEvent.getKeyCode());
            }
            if (weakReference2 == null) {
                return false;
            }
            View view = weakReference2.get();
            if (view != null && w.E(view)) {
                c(view, keyEvent);
            }
            return true;
        }
    }

    public static void a(View view, a.f.l.a aVar) {
        if (aVar == null && (c(view) instanceof a.C0015a)) {
            aVar = new a.f.l.a();
        }
        view.setAccessibilityDelegate(aVar == null ? null : aVar.getBridge());
    }

    public static void e(View view, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 23) {
            view.offsetLeftAndRight(i2);
            return;
        }
        if (i3 >= 21) {
            Rect b2 = b();
            boolean z = false;
            Object parent = view.getParent();
            if (parent instanceof View) {
                View view2 = (View) parent;
                b2.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
                z = !b2.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
            b(view, i2);
            if (z && b2.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                ((View) parent).invalidate(b2);
                return;
            }
            return;
        }
        b(view, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ColorStateList h(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintList();
        }
        if (view instanceof v) {
            return ((v) view).getSupportBackgroundTintList();
        }
        return null;
    }

    public static Rect j(View view) {
        if (Build.VERSION.SDK_INT >= 18) {
            return view.getClipBounds();
        }
        return null;
    }

    public static void c(View view, boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setHasTransientState(z);
        }
    }

    private static List<c.a> g(View view) {
        ArrayList arrayList = (ArrayList) view.getTag(a.f.b.tag_accessibility_actions);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        view.setTag(a.f.b.tag_accessibility_actions, arrayList2);
        return arrayList2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static PorterDuff.Mode i(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintMode();
        }
        if (view instanceof v) {
            return ((v) view).getSupportBackgroundTintMode();
        }
        return null;
    }

    public static void a(View view, int i2, int i3, int i4, int i5) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation(i2, i3, i4, i5);
        } else {
            view.postInvalidate(i2, i3, i4, i5);
        }
    }

    private static void c(View view, int i2) {
        view.offsetTopAndBottom(i2);
        if (view.getVisibility() == 0) {
            N(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                N((View) parent);
            }
        }
    }

    public static a.f.l.a b(View view) {
        View.AccessibilityDelegate c2 = c(view);
        if (c2 == null) {
            return null;
        }
        if (c2 instanceof a.C0015a) {
            return ((a.C0015a) c2).f275a;
        }
        return new a.f.l.a(c2);
    }

    public static void a(View view, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimation(runnable);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay());
        }
    }

    public static void b(View view, int i2, int i3, int i4, int i5) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setPaddingRelative(i2, i3, i4, i5);
        } else {
            view.setPadding(i2, i3, i4, i5);
        }
    }

    private static f<Boolean> d() {
        return new b(a.f.b.tag_screen_reader_focusable, Boolean.class, 28);
    }

    public static void a(View view, Runnable runnable, long j) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimationDelayed(runnable, j);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay() + j);
        }
    }

    private static f<CharSequence> c() {
        return new c(a.f.b.tag_accessibility_pane_title, CharSequence.class, 8, 28);
    }

    static void d(View view, int i2) {
        if (((AccessibilityManager) view.getContext().getSystemService("accessibility")).isEnabled()) {
            boolean z = f(view) != null;
            if (e(view) == 0 && (!z || view.getVisibility() != 0)) {
                if (view.getParent() != null) {
                    try {
                        view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i2);
                        return;
                    } catch (AbstractMethodError e2) {
                        Log.e("ViewCompat", view.getParent().getClass().getSimpleName() + " does not fully implement ViewParent", e2);
                        return;
                    }
                }
                return;
            }
            AccessibilityEvent obtain = AccessibilityEvent.obtain();
            obtain.setEventType(z ? 32 : DfuBaseService.ERROR_REMOTE_TYPE_SECURE_BUTTONLESS);
            obtain.setContentChangeTypes(i2);
            view.sendAccessibilityEventUnchecked(obtain);
        }
    }

    @Deprecated
    public static void b(View view, boolean z) {
        view.setFitsSystemWindows(z);
    }

    public static boolean a(View view, int i2, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.performAccessibilityAction(i2, bundle);
        }
        return false;
    }

    public static e0 b(View view, e0 e0Var) {
        if (Build.VERSION.SDK_INT < 21) {
            return e0Var;
        }
        WindowInsets h2 = e0Var.h();
        WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(h2);
        if (!onApplyWindowInsets.equals(h2)) {
            h2 = new WindowInsets(onApplyWindowInsets);
        }
        return e0.a(h2);
    }

    public static void a(View view, c.a aVar, CharSequence charSequence, a.f.l.f0.f fVar) {
        if (fVar == null && charSequence == null) {
            g(view, aVar.a());
        } else {
            a(view, aVar.a(charSequence, fVar));
        }
    }

    public static CharSequence f(View view) {
        return c().b(view);
    }

    private static void a(View view, c.a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            t(view);
            a(aVar.a(), view);
            g(view).add(aVar);
            d(view, 0);
        }
    }

    private static void b(View view, int i2) {
        view.offsetLeftAndRight(i2);
        if (view.getVisibility() == 0) {
            N(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                N((View) parent);
            }
        }
    }

    private static void a(int i2, View view) {
        List<c.a> g2 = g(view);
        for (int i3 = 0; i3 < g2.size(); i3++) {
            if (g2.get(i3).a() == i2) {
                g2.remove(i3);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean b(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return h.a(view).a(keyEvent);
    }

    @Deprecated
    public static void a(View view, int i2, Paint paint) {
        view.setLayerType(i2, paint);
    }

    public static void a(View view, Paint paint) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setLayerPaint(paint);
        } else {
            view.setLayerType(view.getLayerType(), paint);
            view.invalidate();
        }
    }

    public static a0 a(View view) {
        if (f336f == null) {
            f336f = new WeakHashMap<>();
        }
        a0 a0Var = f336f.get(view);
        if (a0Var != null) {
            return a0Var;
        }
        a0 a0Var2 = new a0(view);
        f336f.put(view, a0Var2);
        return a0Var2;
    }

    public static void a(View view, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setElevation(f2);
        }
    }

    public static void a(View view, String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setTransitionName(str);
            return;
        }
        if (f335e == null) {
            f335e = new WeakHashMap<>();
        }
        f335e.put(view, str);
    }

    public static void a(View view, r rVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            if (rVar == null) {
                view.setOnApplyWindowInsetsListener(null);
            } else {
                view.setOnApplyWindowInsetsListener(new a(rVar));
            }
        }
    }

    public static e0 a(View view, e0 e0Var) {
        if (Build.VERSION.SDK_INT < 21) {
            return e0Var;
        }
        WindowInsets h2 = e0Var.h();
        WindowInsets dispatchApplyWindowInsets = view.dispatchApplyWindowInsets(h2);
        if (!dispatchApplyWindowInsets.equals(h2)) {
            h2 = new WindowInsets(dispatchApplyWindowInsets);
        }
        return e0.a(h2);
    }

    public static void a(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(View view, ColorStateList colorStateList) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintList(colorStateList);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable background = view.getBackground();
                boolean z = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
                if (background == null || !z) {
                    return;
                }
                if (background.isStateful()) {
                    background.setState(view.getDrawableState());
                }
                view.setBackground(background);
                return;
            }
            return;
        }
        if (view instanceof v) {
            ((v) view).setSupportBackgroundTintList(colorStateList);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(View view, PorterDuff.Mode mode) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintMode(mode);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable background = view.getBackground();
                boolean z = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
                if (background == null || !z) {
                    return;
                }
                if (background.isStateful()) {
                    background.setState(view.getDrawableState());
                }
                view.setBackground(background);
                return;
            }
            return;
        }
        if (view instanceof v) {
            ((v) view).setSupportBackgroundTintMode(mode);
        }
    }

    public static void a(View view, Rect rect) {
        if (Build.VERSION.SDK_INT >= 18) {
            view.setClipBounds(rect);
        }
    }

    public static void a(View view, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(i2, i3);
        }
    }

    public static void a(View view, t tVar) {
        if (Build.VERSION.SDK_INT >= 24) {
            view.setPointerIcon((PointerIcon) (tVar != null ? tVar.a() : null));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return h.a(view).a(view, keyEvent);
    }

    public static void a(View view, boolean z) {
        a().b(view, Boolean.valueOf(z));
    }

    private static f<Boolean> a() {
        return new d(a.f.b.tag_accessibility_heading, Boolean.class, 28);
    }
}
