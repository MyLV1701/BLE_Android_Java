package a.f.l.f0;

import a.f.l.f0.f;
import android.R;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.mcp.ble.parser.AppearanceLibrary;

/* loaded from: classes.dex */
public class c {

    /* renamed from: d, reason: collision with root package name */
    private static int f306d;

    /* renamed from: a, reason: collision with root package name */
    private final AccessibilityNodeInfo f307a;

    /* renamed from: b, reason: collision with root package name */
    public int f308b = -1;

    /* renamed from: c, reason: collision with root package name */
    private int f309c = -1;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: e, reason: collision with root package name */
        public static final a f310e = new a(1, null);

        /* renamed from: f, reason: collision with root package name */
        public static final a f311f = new a(2, null);
        public static final a g;
        public static final a h;
        public static final a i;
        public static final a j;
        public static final a k;
        public static final a l;
        public static final a m;
        public static final a n;

        /* renamed from: a, reason: collision with root package name */
        final Object f312a;

        /* renamed from: b, reason: collision with root package name */
        private final int f313b;

        /* renamed from: c, reason: collision with root package name */
        private final Class<? extends f.a> f314c;

        /* renamed from: d, reason: collision with root package name */
        protected final f f315d;

        static {
            new a(4, null);
            new a(8, null);
            g = new a(16, null);
            new a(32, null);
            new a(64, null);
            new a(128, null);
            new a(256, null, f.b.class);
            new a(DfuBaseService.ERROR_REMOTE_TYPE_SECURE, null, f.b.class);
            new a(1024, null, f.c.class);
            new a(DfuBaseService.ERROR_REMOTE_TYPE_SECURE_BUTTONLESS, null, f.c.class);
            h = new a(4096, null);
            i = new a(DfuBaseService.ERROR_REMOTE_MASK, null);
            new a(DfuBaseService.ERROR_CONNECTION_MASK, null);
            new a(DfuBaseService.ERROR_CONNECTION_STATE_MASK, null);
            new a(65536, null);
            new a(131072, null, f.g.class);
            j = new a(262144, null);
            k = new a(524288, null);
            l = new a(AppearanceLibrary.MASK_BEACON, null);
            new a(2097152, null, f.h.class);
            new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN : null, R.id.accessibilityActionShowOnScreen, null, null, null);
            new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION : null, R.id.accessibilityActionScrollToPosition, null, null, f.e.class);
            m = new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP : null, R.id.accessibilityActionScrollUp, null, null, null);
            new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT : null, R.id.accessibilityActionScrollLeft, null, null, null);
            n = new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN : null, R.id.accessibilityActionScrollDown, null, null, null);
            new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT : null, R.id.accessibilityActionScrollRight, null, null, null);
            new a(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP : null, R.id.accessibilityActionPageUp, null, null, null);
            new a(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN : null, R.id.accessibilityActionPageDown, null, null, null);
            new a(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT : null, R.id.accessibilityActionPageLeft, null, null, null);
            new a(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT : null, R.id.accessibilityActionPageRight, null, null, null);
            new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK : null, R.id.accessibilityActionContextClick, null, null, null);
            new a(Build.VERSION.SDK_INT >= 24 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS : null, R.id.accessibilityActionSetProgress, null, null, f.C0019f.class);
            new a(Build.VERSION.SDK_INT >= 26 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW : null, R.id.accessibilityActionMoveWindow, null, null, f.d.class);
            new a(Build.VERSION.SDK_INT >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null, R.id.accessibilityActionShowTooltip, null, null, null);
            new a(Build.VERSION.SDK_INT >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP : null, R.id.accessibilityActionHideTooltip, null, null, null);
        }

        public a(int i2, CharSequence charSequence) {
            this(null, i2, charSequence, null, null);
        }

        public int a() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.AccessibilityAction) this.f312a).getId();
            }
            return 0;
        }

        public CharSequence b() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.AccessibilityAction) this.f312a).getLabel();
            }
            return null;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            Object obj2 = this.f312a;
            return obj2 == null ? aVar.f312a == null : obj2.equals(aVar.f312a);
        }

        public int hashCode() {
            Object obj = this.f312a;
            if (obj != null) {
                return obj.hashCode();
            }
            return 0;
        }

        a(Object obj) {
            this(obj, 0, null, null, null);
        }

        private a(int i2, CharSequence charSequence, Class<? extends f.a> cls) {
            this(null, i2, charSequence, null, cls);
        }

        public boolean a(View view, Bundle bundle) {
            f.a newInstance;
            if (this.f315d == null) {
                return false;
            }
            f.a aVar = null;
            Class<? extends f.a> cls = this.f314c;
            if (cls != null) {
                try {
                    newInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Exception e2) {
                    e = e2;
                }
                try {
                    newInstance.a(bundle);
                    aVar = newInstance;
                } catch (Exception e3) {
                    e = e3;
                    aVar = newInstance;
                    Class<? extends f.a> cls2 = this.f314c;
                    Log.e("A11yActionCompat", "Failed to execute command with argument class ViewCommandArgument: " + (cls2 == null ? "null" : cls2.getName()), e);
                    return this.f315d.a(view, aVar);
                }
            }
            return this.f315d.a(view, aVar);
        }

        a(Object obj, int i2, CharSequence charSequence, f fVar, Class<? extends f.a> cls) {
            this.f313b = i2;
            this.f315d = fVar;
            if (Build.VERSION.SDK_INT >= 21 && obj == null) {
                this.f312a = new AccessibilityNodeInfo.AccessibilityAction(i2, charSequence);
            } else {
                this.f312a = obj;
            }
            this.f314c = cls;
        }

        public a a(CharSequence charSequence, f fVar) {
            return new a(null, this.f313b, charSequence, fVar, this.f314c);
        }
    }

    private c(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f307a = accessibilityNodeInfo;
    }

    private void A() {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f307a.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
            this.f307a.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
            this.f307a.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
            this.f307a.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        }
    }

    private boolean B() {
        return !a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty();
    }

    public static c C() {
        return a(AccessibilityNodeInfo.obtain());
    }

    public static c a(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new c(accessibilityNodeInfo);
    }

    private static String c(int i) {
        if (i == 1) {
            return "ACTION_FOCUS";
        }
        if (i == 2) {
            return "ACTION_CLEAR_FOCUS";
        }
        switch (i) {
            case 4:
                return "ACTION_SELECT";
            case 8:
                return "ACTION_CLEAR_SELECTION";
            case 16:
                return "ACTION_CLICK";
            case 32:
                return "ACTION_LONG_CLICK";
            case 64:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case DfuBaseService.ERROR_REMOTE_TYPE_SECURE /* 512 */:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case 1024:
                return "ACTION_NEXT_HTML_ELEMENT";
            case DfuBaseService.ERROR_REMOTE_TYPE_SECURE_BUTTONLESS /* 2048 */:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case DfuBaseService.ERROR_REMOTE_MASK /* 8192 */:
                return "ACTION_SCROLL_BACKWARD";
            case DfuBaseService.ERROR_CONNECTION_MASK /* 16384 */:
                return "ACTION_COPY";
            case DfuBaseService.ERROR_CONNECTION_STATE_MASK /* 32768 */:
                return "ACTION_PASTE";
            case 65536:
                return "ACTION_CUT";
            case 131072:
                return "ACTION_SET_SELECTION";
            case 262144:
                return "ACTION_EXPAND";
            case 524288:
                return "ACTION_COLLAPSE";
            case 2097152:
                return "ACTION_SET_TEXT";
            case R.id.accessibilityActionMoveWindow:
                return "ACTION_MOVE_WINDOW";
            default:
                switch (i) {
                    case R.id.accessibilityActionShowOnScreen:
                        return "ACTION_SHOW_ON_SCREEN";
                    case R.id.accessibilityActionScrollToPosition:
                        return "ACTION_SCROLL_TO_POSITION";
                    case R.id.accessibilityActionScrollUp:
                        return "ACTION_SCROLL_UP";
                    case R.id.accessibilityActionScrollLeft:
                        return "ACTION_SCROLL_LEFT";
                    case R.id.accessibilityActionScrollDown:
                        return "ACTION_SCROLL_DOWN";
                    case R.id.accessibilityActionScrollRight:
                        return "ACTION_SCROLL_RIGHT";
                    case R.id.accessibilityActionContextClick:
                        return "ACTION_CONTEXT_CLICK";
                    case R.id.accessibilityActionSetProgress:
                        return "ACTION_SET_PROGRESS";
                    default:
                        switch (i) {
                            case R.id.accessibilityActionShowTooltip:
                                return "ACTION_SHOW_TOOLTIP";
                            case R.id.accessibilityActionHideTooltip:
                                return "ACTION_HIDE_TOOLTIP";
                            case R.id.accessibilityActionPageUp:
                                return "ACTION_PAGE_UP";
                            case R.id.accessibilityActionPageDown:
                                return "ACTION_PAGE_DOWN";
                            case R.id.accessibilityActionPageLeft:
                                return "ACTION_PAGE_LEFT";
                            case R.id.accessibilityActionPageRight:
                                return "ACTION_PAGE_RIGHT";
                            default:
                                return "ACTION_UNKNOWN";
                        }
                }
        }
    }

    public static c f(View view) {
        return a(AccessibilityNodeInfo.obtain(view));
    }

    public int b() {
        return this.f307a.getActions();
    }

    public void c(View view) {
        this.f309c = -1;
        this.f307a.setSource(view);
    }

    public void d(Rect rect) {
        this.f307a.setBoundsInScreen(rect);
    }

    public void e(CharSequence charSequence) {
        this.f307a.setPackageName(charSequence);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = this.f307a;
        if (accessibilityNodeInfo == null) {
            if (cVar.f307a != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(cVar.f307a)) {
            return false;
        }
        return this.f309c == cVar.f309c && this.f308b == cVar.f308b;
    }

    public void g(boolean z) {
        this.f307a.setEnabled(z);
    }

    public int h() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f307a.getMovementGranularities();
        }
        return 0;
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f307a;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public void i(boolean z) {
        this.f307a.setFocused(z);
    }

    public CharSequence j() {
        if (B()) {
            List<Integer> a2 = a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
            List<Integer> a3 = a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
            List<Integer> a4 = a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
            List<Integer> a5 = a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
            SpannableString spannableString = new SpannableString(TextUtils.substring(this.f307a.getText(), 0, this.f307a.getText().length()));
            for (int i = 0; i < a2.size(); i++) {
                spannableString.setSpan(new a.f.l.f0.a(a5.get(i).intValue(), this, g().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), a2.get(i).intValue(), a3.get(i).intValue(), a4.get(i).intValue());
            }
            return spannableString;
        }
        return this.f307a.getText();
    }

    public void k(boolean z) {
        this.f307a.setLongClickable(z);
    }

    public boolean l() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f307a.isAccessibilityFocused();
        }
        return false;
    }

    public boolean m() {
        return this.f307a.isCheckable();
    }

    public boolean n() {
        return this.f307a.isChecked();
    }

    public boolean o() {
        return this.f307a.isClickable();
    }

    public void p(boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f307a.setVisibleToUser(z);
        }
    }

    public boolean q() {
        return this.f307a.isFocusable();
    }

    public boolean r() {
        return this.f307a.isFocused();
    }

    public boolean s() {
        return this.f307a.isLongClickable();
    }

    public boolean t() {
        return this.f307a.isPassword();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        a(rect);
        sb.append("; boundsInParent: " + rect);
        b(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(i());
        sb.append("; className: ");
        sb.append(d());
        sb.append("; text: ");
        sb.append(j());
        sb.append("; contentDescription: ");
        sb.append(f());
        sb.append("; viewId: ");
        sb.append(k());
        sb.append("; checkable: ");
        sb.append(m());
        sb.append("; checked: ");
        sb.append(n());
        sb.append("; focusable: ");
        sb.append(q());
        sb.append("; focused: ");
        sb.append(r());
        sb.append("; selected: ");
        sb.append(v());
        sb.append("; clickable: ");
        sb.append(o());
        sb.append("; longClickable: ");
        sb.append(s());
        sb.append("; enabled: ");
        sb.append(p());
        sb.append("; password: ");
        sb.append(t());
        sb.append("; scrollable: " + u());
        sb.append("; [");
        if (Build.VERSION.SDK_INT >= 21) {
            List<a> a2 = a();
            for (int i = 0; i < a2.size(); i++) {
                a aVar = a2.get(i);
                String c2 = c(aVar.a());
                if (c2.equals("ACTION_UNKNOWN") && aVar.b() != null) {
                    c2 = aVar.b().toString();
                }
                sb.append(c2);
                if (i != a2.size() - 1) {
                    sb.append(", ");
                }
            }
        } else {
            int b2 = b();
            while (b2 != 0) {
                int numberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(b2);
                b2 &= numberOfTrailingZeros ^ (-1);
                sb.append(c(numberOfTrailingZeros));
                if (b2 != 0) {
                    sb.append(", ");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean u() {
        return this.f307a.isScrollable();
    }

    public boolean v() {
        return this.f307a.isSelected();
    }

    public boolean w() {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.f307a.isShowingHintText();
        }
        return d(4);
    }

    public boolean x() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f307a.isVisibleToUser();
        }
        return false;
    }

    public void y() {
        this.f307a.recycle();
    }

    public AccessibilityNodeInfo z() {
        return this.f307a;
    }

    public static c a(c cVar) {
        return a(AccessibilityNodeInfo.obtain(cVar.f307a));
    }

    private SparseArray<WeakReference<ClickableSpan>> e(View view) {
        return (SparseArray) view.getTag(a.f.b.tag_accessibility_clickable_spans);
    }

    public boolean b(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.f307a.removeAction((AccessibilityNodeInfo.AccessibilityAction) aVar.f312a);
        }
        return false;
    }

    public void d(boolean z) {
        this.f307a.setClickable(z);
    }

    public CharSequence f() {
        return this.f307a.getContentDescription();
    }

    public void g(CharSequence charSequence) {
        this.f307a.setText(charSequence);
    }

    public CharSequence i() {
        return this.f307a.getPackageName();
    }

    public String k() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.f307a.getViewIdResourceName();
        }
        return null;
    }

    public void m(boolean z) {
        this.f307a.setScrollable(z);
    }

    public void n(boolean z) {
        this.f307a.setSelected(z);
    }

    public void o(boolean z) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.f307a.setShowingHintText(z);
        } else {
            a(4, z);
        }
    }

    private void g(View view) {
        SparseArray<WeakReference<ClickableSpan>> e2 = e(view);
        if (e2 != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < e2.size(); i++) {
                if (e2.valueAt(i).get() == null) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                e2.remove(((Integer) arrayList.get(i2)).intValue());
            }
        }
    }

    public void a(View view) {
        this.f307a.addChild(view);
    }

    public void c(View view, int i) {
        this.f309c = i;
        if (Build.VERSION.SDK_INT >= 16) {
            this.f307a.setSource(view, i);
        }
    }

    public CharSequence d() {
        return this.f307a.getClassName();
    }

    public C0018c e() {
        AccessibilityNodeInfo.CollectionItemInfo collectionItemInfo;
        if (Build.VERSION.SDK_INT < 19 || (collectionItemInfo = this.f307a.getCollectionItemInfo()) == null) {
            return null;
        }
        return new C0018c(collectionItemInfo);
    }

    public void f(boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f307a.setDismissable(z);
        }
    }

    public void h(boolean z) {
        this.f307a.setFocusable(z);
    }

    public void l(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f307a.setScreenReaderFocusable(z);
        } else {
            a(1, z);
        }
    }

    public boolean p() {
        return this.f307a.isEnabled();
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        final Object f316a;

        b(Object obj) {
            this.f316a = obj;
        }

        public static b a(int i, int i2, boolean z, int i3) {
            int i4 = Build.VERSION.SDK_INT;
            if (i4 >= 21) {
                return new b(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z, i3));
            }
            if (i4 >= 19) {
                return new b(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z));
            }
            return new b(null);
        }

        public static b a(int i, int i2, boolean z) {
            if (Build.VERSION.SDK_INT >= 19) {
                return new b(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z));
            }
            return new b(null);
        }
    }

    /* renamed from: a.f.l.f0.c$c, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0018c {

        /* renamed from: a, reason: collision with root package name */
        final Object f317a;

        C0018c(Object obj) {
            this.f317a = obj;
        }

        public static C0018c a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
            int i5 = Build.VERSION.SDK_INT;
            if (i5 >= 21) {
                return new C0018c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z, z2));
            }
            if (i5 >= 19) {
                return new C0018c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z));
            }
            return new C0018c(null);
        }

        public int b() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionItemInfo) this.f317a).getColumnSpan();
            }
            return 0;
        }

        public int c() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionItemInfo) this.f317a).getRowIndex();
            }
            return 0;
        }

        public int d() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionItemInfo) this.f317a).getRowSpan();
            }
            return 0;
        }

        public boolean e() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.CollectionItemInfo) this.f317a).isSelected();
            }
            return false;
        }

        public int a() {
            if (Build.VERSION.SDK_INT >= 19) {
                return ((AccessibilityNodeInfo.CollectionItemInfo) this.f317a).getColumnIndex();
            }
            return 0;
        }
    }

    private SparseArray<WeakReference<ClickableSpan>> d(View view) {
        SparseArray<WeakReference<ClickableSpan>> e2 = e(view);
        if (e2 != null) {
            return e2;
        }
        SparseArray<WeakReference<ClickableSpan>> sparseArray = new SparseArray<>();
        view.setTag(a.f.b.tag_accessibility_clickable_spans, sparseArray);
        return sparseArray;
    }

    public static ClickableSpan[] h(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            return (ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class);
        }
        return null;
    }

    public void a(View view, int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f307a.addChild(view, i);
        }
    }

    public void b(int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f307a.setMovementGranularities(i);
        }
    }

    public void f(CharSequence charSequence) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            this.f307a.setPaneTitle(charSequence);
        } else if (i >= 19) {
            this.f307a.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequence);
        }
    }

    public void a(int i) {
        this.f307a.addAction(i);
    }

    public void b(View view) {
        this.f308b = -1;
        this.f307a.setParent(view);
    }

    public int c() {
        return this.f307a.getChildCount();
    }

    public void e(boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f307a.setContentInvalid(z);
        }
    }

    private List<Integer> a(String str) {
        if (Build.VERSION.SDK_INT < 19) {
            return new ArrayList();
        }
        ArrayList<Integer> integerArrayList = this.f307a.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.f307a.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    @Deprecated
    public void c(Rect rect) {
        this.f307a.setBoundsInParent(rect);
    }

    public void d(CharSequence charSequence) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            this.f307a.setHintText(charSequence);
        } else if (i >= 19) {
            this.f307a.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY", charSequence);
        }
    }

    public void b(View view, int i) {
        this.f308b = i;
        if (Build.VERSION.SDK_INT >= 16) {
            this.f307a.setParent(view, i);
        }
    }

    public void c(boolean z) {
        this.f307a.setChecked(z);
    }

    public void c(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f307a.setError(charSequence);
        }
    }

    private boolean d(int i) {
        Bundle g = g();
        return g != null && (g.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & i) == i;
    }

    public Bundle g() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.f307a.getExtras();
        }
        return new Bundle();
    }

    public void b(Rect rect) {
        this.f307a.getBoundsInScreen(rect);
    }

    public void b(boolean z) {
        this.f307a.setCheckable(z);
    }

    public void a(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f307a.addAction((AccessibilityNodeInfo.AccessibilityAction) aVar.f312a);
        }
    }

    public void b(CharSequence charSequence) {
        this.f307a.setContentDescription(charSequence);
    }

    public void b(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f307a.setCollectionItemInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionItemInfo) ((C0018c) obj).f317a);
        }
    }

    public boolean a(int i, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f307a.performAction(i, bundle);
        }
        return false;
    }

    public void j(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f307a.setHeading(z);
        } else {
            a(2, z);
        }
    }

    @Deprecated
    public void a(Rect rect) {
        this.f307a.getBoundsInParent(rect);
    }

    public void a(boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f307a.setAccessibilityFocused(z);
        }
    }

    public void a(CharSequence charSequence) {
        this.f307a.setClassName(charSequence);
    }

    public void a(CharSequence charSequence, View view) {
        int i = Build.VERSION.SDK_INT;
        if (i < 19 || i >= 26) {
            return;
        }
        A();
        g(view);
        ClickableSpan[] h = h(charSequence);
        if (h == null || h.length <= 0) {
            return;
        }
        g().putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY", a.f.b.accessibility_action_clickable_span);
        SparseArray<WeakReference<ClickableSpan>> d2 = d(view);
        for (int i2 = 0; h != null && i2 < h.length; i2++) {
            int a2 = a(h[i2], d2);
            d2.put(a2, new WeakReference<>(h[i2]));
            a(h[i2], (Spanned) charSequence, a2);
        }
    }

    private int a(ClickableSpan clickableSpan, SparseArray<WeakReference<ClickableSpan>> sparseArray) {
        if (sparseArray != null) {
            for (int i = 0; i < sparseArray.size(); i++) {
                if (clickableSpan.equals(sparseArray.valueAt(i).get())) {
                    return sparseArray.keyAt(i);
                }
            }
        }
        int i2 = f306d;
        f306d = i2 + 1;
        return i2;
    }

    private void a(ClickableSpan clickableSpan, Spanned spanned, int i) {
        a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").add(Integer.valueOf(spanned.getSpanStart(clickableSpan)));
        a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY").add(Integer.valueOf(spanned.getSpanEnd(clickableSpan)));
        a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY").add(Integer.valueOf(spanned.getSpanFlags(clickableSpan)));
        a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY").add(Integer.valueOf(i));
    }

    public void a(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f307a.setCollectionInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionInfo) ((b) obj).f316a);
        }
    }

    public List<a> a() {
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = Build.VERSION.SDK_INT >= 21 ? this.f307a.getActionList() : null;
        if (actionList != null) {
            ArrayList arrayList = new ArrayList();
            int size = actionList.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(new a(actionList.get(i)));
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    private void a(int i, boolean z) {
        Bundle g = g();
        if (g != null) {
            int i2 = g.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (i ^ (-1));
            if (!z) {
                i = 0;
            }
            g.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i | i2);
        }
    }
}
