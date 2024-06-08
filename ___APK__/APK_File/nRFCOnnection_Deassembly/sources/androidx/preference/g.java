package androidx.preference;

import android.R;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.preference.DialogPreference;
import androidx.preference.PreferenceGroup;
import androidx.preference.j;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public abstract class g extends Fragment implements j.c, j.a, j.b, DialogPreference.a {
    public static final String ARG_PREFERENCE_ROOT = "androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT";
    private static final String DIALOG_FRAGMENT_TAG = "androidx.preference.PreferenceFragment.DIALOG";
    private static final int MSG_BIND_PREFERENCES = 1;
    private static final String PREFERENCES_TAG = "android:preferences";
    private static final String TAG = "PreferenceFragment";
    private boolean mHavePrefs;
    private boolean mInitDone;
    RecyclerView mList;
    private j mPreferenceManager;
    private Runnable mSelectPreferenceRunnable;
    private final d mDividerDecoration = new d();
    private int mLayoutResId = q.preference_list_fragment;
    private Handler mHandler = new a();
    private final Runnable mRequestFocus = new b();

    /* loaded from: classes.dex */
    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            g.this.bindPreferences();
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView recyclerView = g.this.mList;
            recyclerView.focusableViewAvailable(recyclerView);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Preference f1619b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f1620c;

        c(Preference preference, String str) {
            this.f1619b = preference;
            this.f1620c = str;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            int a2;
            RecyclerView.g adapter = g.this.mList.getAdapter();
            if (!(adapter instanceof PreferenceGroup.c)) {
                if (adapter != 0) {
                    throw new IllegalStateException("Adapter must implement PreferencePositionCallback");
                }
                return;
            }
            Preference preference = this.f1619b;
            if (preference != null) {
                a2 = ((PreferenceGroup.c) adapter).d(preference);
            } else {
                a2 = ((PreferenceGroup.c) adapter).a(this.f1620c);
            }
            if (a2 != -1) {
                g.this.mList.scrollToPosition(a2);
            } else {
                adapter.registerAdapterDataObserver(new h(adapter, g.this.mList, this.f1619b, this.f1620c));
            }
        }
    }

    /* loaded from: classes.dex */
    public interface e {
        boolean a(g gVar, Preference preference);
    }

    /* loaded from: classes.dex */
    public interface f {
        boolean a(g gVar, Preference preference);
    }

    /* renamed from: androidx.preference.g$g, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0057g {
        boolean a(g gVar, PreferenceScreen preferenceScreen);
    }

    /* loaded from: classes.dex */
    private static class h extends RecyclerView.i {

        /* renamed from: a, reason: collision with root package name */
        private final RecyclerView.g f1626a;

        /* renamed from: b, reason: collision with root package name */
        private final RecyclerView f1627b;

        /* renamed from: c, reason: collision with root package name */
        private final Preference f1628c;

        /* renamed from: d, reason: collision with root package name */
        private final String f1629d;

        public h(RecyclerView.g gVar, RecyclerView recyclerView, Preference preference, String str) {
            this.f1626a = gVar;
            this.f1627b = recyclerView;
            this.f1628c = preference;
            this.f1629d = str;
        }

        private void b() {
            int a2;
            this.f1626a.unregisterAdapterDataObserver(this);
            Preference preference = this.f1628c;
            if (preference != null) {
                a2 = ((PreferenceGroup.c) this.f1626a).d(preference);
            } else {
                a2 = ((PreferenceGroup.c) this.f1626a).a(this.f1629d);
            }
            if (a2 != -1) {
                this.f1627b.scrollToPosition(a2);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void a() {
            b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void c(int i, int i2) {
            b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void a(int i, int i2) {
            b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void a(int i, int i2, Object obj) {
            b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void a(int i, int i2, int i3) {
            b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void b(int i, int i2) {
            b();
        }
    }

    private void postBindPreferences() {
        if (this.mHandler.hasMessages(1)) {
            return;
        }
        this.mHandler.obtainMessage(1).sendToTarget();
    }

    private void requirePreferenceManager() {
        if (this.mPreferenceManager == null) {
            throw new RuntimeException("This should be called after super.onCreate.");
        }
    }

    private void scrollToPreferenceInternal(Preference preference, String str) {
        c cVar = new c(preference, str);
        if (this.mList == null) {
            this.mSelectPreferenceRunnable = cVar;
        } else {
            cVar.run();
        }
    }

    private void unbindPreferences() {
        getListView().setAdapter(null);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            preferenceScreen.onDetached();
        }
        onUnbindPreferences();
    }

    public void addPreferencesFromResource(int i) {
        requirePreferenceManager();
        setPreferenceScreen(this.mPreferenceManager.a(getContext(), i, getPreferenceScreen()));
    }

    void bindPreferences() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            getListView().setAdapter(onCreateAdapter(preferenceScreen));
            preferenceScreen.onAttached();
        }
        onBindPreferences();
    }

    @Override // androidx.preference.DialogPreference.a
    public <T extends Preference> T findPreference(CharSequence charSequence) {
        j jVar = this.mPreferenceManager;
        if (jVar == null) {
            return null;
        }
        return (T) jVar.a(charSequence);
    }

    public Fragment getCallbackFragment() {
        return null;
    }

    public final RecyclerView getListView() {
        return this.mList;
    }

    public j getPreferenceManager() {
        return this.mPreferenceManager;
    }

    public PreferenceScreen getPreferenceScreen() {
        return this.mPreferenceManager.g();
    }

    protected void onBindPreferences() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(m.preferenceTheme, typedValue, true);
        int i = typedValue.resourceId;
        if (i == 0) {
            i = s.PreferenceThemeOverlay;
        }
        getActivity().getTheme().applyStyle(i, false);
        this.mPreferenceManager = new j(getContext());
        this.mPreferenceManager.a((j.b) this);
        onCreatePreferences(bundle, getArguments() != null ? getArguments().getString(ARG_PREFERENCE_ROOT) : null);
    }

    protected RecyclerView.g onCreateAdapter(PreferenceScreen preferenceScreen) {
        return new androidx.preference.h(preferenceScreen);
    }

    public RecyclerView.o onCreateLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    public abstract void onCreatePreferences(Bundle bundle, String str);

    public RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RecyclerView recyclerView;
        if (getContext().getPackageManager().hasSystemFeature("android.hardware.type.automotive") && (recyclerView = (RecyclerView) viewGroup.findViewById(p.recycler_view)) != null) {
            return recyclerView;
        }
        RecyclerView recyclerView2 = (RecyclerView) layoutInflater.inflate(q.preference_recyclerview, viewGroup, false);
        recyclerView2.setLayoutManager(onCreateLayoutManager());
        recyclerView2.setAccessibilityDelegateCompat(new k(recyclerView2));
        return recyclerView2;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, t.PreferenceFragmentCompat, m.preferenceFragmentCompatStyle, 0);
        this.mLayoutResId = obtainStyledAttributes.getResourceId(t.PreferenceFragmentCompat_android_layout, this.mLayoutResId);
        Drawable drawable = obtainStyledAttributes.getDrawable(t.PreferenceFragmentCompat_android_divider);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(t.PreferenceFragmentCompat_android_dividerHeight, -1);
        boolean z = obtainStyledAttributes.getBoolean(t.PreferenceFragmentCompat_allowDividerAfterLastItem, true);
        obtainStyledAttributes.recycle();
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(getContext());
        View inflate = cloneInContext.inflate(this.mLayoutResId, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.list_container);
        if (findViewById instanceof ViewGroup) {
            ViewGroup viewGroup2 = (ViewGroup) findViewById;
            RecyclerView onCreateRecyclerView = onCreateRecyclerView(cloneInContext, viewGroup2, bundle);
            if (onCreateRecyclerView != null) {
                this.mList = onCreateRecyclerView;
                onCreateRecyclerView.addItemDecoration(this.mDividerDecoration);
                setDivider(drawable);
                if (dimensionPixelSize != -1) {
                    setDividerHeight(dimensionPixelSize);
                }
                this.mDividerDecoration.a(z);
                if (this.mList.getParent() == null) {
                    viewGroup2.addView(this.mList);
                }
                this.mHandler.post(this.mRequestFocus);
                return inflate;
            }
            throw new RuntimeException("Could not create RecyclerView");
        }
        throw new IllegalStateException("Content has view with id attribute 'android.R.id.list_container' that is not a ViewGroup class");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mHandler.removeCallbacks(this.mRequestFocus);
        this.mHandler.removeMessages(1);
        if (this.mHavePrefs) {
            unbindPreferences();
        }
        this.mList = null;
        super.onDestroyView();
    }

    @Override // androidx.preference.j.a
    public void onDisplayPreferenceDialog(Preference preference) {
        androidx.fragment.app.c a2;
        boolean a3 = getCallbackFragment() instanceof e ? ((e) getCallbackFragment()).a(this, preference) : false;
        if (!a3 && (getActivity() instanceof e)) {
            a3 = ((e) getActivity()).a(this, preference);
        }
        if (!a3 && getFragmentManager().b(DIALOG_FRAGMENT_TAG) == null) {
            if (preference instanceof EditTextPreference) {
                a2 = androidx.preference.a.a(preference.getKey());
            } else if (preference instanceof ListPreference) {
                a2 = androidx.preference.c.a(preference.getKey());
            } else if (preference instanceof MultiSelectListPreference) {
                a2 = androidx.preference.d.a(preference.getKey());
            } else {
                throw new IllegalArgumentException("Cannot display dialog for an unknown Preference type: " + preference.getClass().getSimpleName() + ". Make sure to implement onPreferenceDisplayDialog() to handle displaying a custom dialog for this Preference.");
            }
            a2.setTargetFragment(this, 0);
            a2.show(getFragmentManager(), DIALOG_FRAGMENT_TAG);
        }
    }

    @Override // androidx.preference.j.b
    public void onNavigateToScreen(PreferenceScreen preferenceScreen) {
        if ((getCallbackFragment() instanceof InterfaceC0057g ? ((InterfaceC0057g) getCallbackFragment()).a(this, preferenceScreen) : false) || !(getActivity() instanceof InterfaceC0057g)) {
            return;
        }
        ((InterfaceC0057g) getActivity()).a(this, preferenceScreen);
    }

    @Override // androidx.preference.j.c
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference.getFragment() == null) {
            return false;
        }
        boolean a2 = getCallbackFragment() instanceof f ? ((f) getCallbackFragment()).a(this, preference) : false;
        if (!a2 && (getActivity() instanceof f)) {
            a2 = ((f) getActivity()).a(this, preference);
        }
        if (a2) {
            return true;
        }
        Log.w(TAG, "onPreferenceStartFragment is not implemented in the parent activity - attempting to use a fallback implementation. You should implement this method so that you can configure the new fragment that will be displayed, and set a transition between the fragments.");
        androidx.fragment.app.l supportFragmentManager = requireActivity().getSupportFragmentManager();
        Bundle extras = preference.getExtras();
        Fragment a3 = supportFragmentManager.o().a(requireActivity().getClassLoader(), preference.getFragment());
        a3.setArguments(extras);
        a3.setTargetFragment(this, 0);
        androidx.fragment.app.t b2 = supportFragmentManager.b();
        b2.b(((View) getView().getParent()).getId(), a3);
        b2.a((String) null);
        b2.a();
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            Bundle bundle2 = new Bundle();
            preferenceScreen.saveHierarchyState(bundle2);
            bundle.putBundle(PREFERENCES_TAG, bundle2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mPreferenceManager.a((j.c) this);
        this.mPreferenceManager.a((j.a) this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mPreferenceManager.a((j.c) null);
        this.mPreferenceManager.a((j.a) null);
    }

    protected void onUnbindPreferences() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Bundle bundle2;
        PreferenceScreen preferenceScreen;
        super.onViewCreated(view, bundle);
        if (bundle != null && (bundle2 = bundle.getBundle(PREFERENCES_TAG)) != null && (preferenceScreen = getPreferenceScreen()) != null) {
            preferenceScreen.restoreHierarchyState(bundle2);
        }
        if (this.mHavePrefs) {
            bindPreferences();
            Runnable runnable = this.mSelectPreferenceRunnable;
            if (runnable != null) {
                runnable.run();
                this.mSelectPreferenceRunnable = null;
            }
        }
        this.mInitDone = true;
    }

    public void scrollToPreference(String str) {
        scrollToPreferenceInternal(null, str);
    }

    public void setDivider(Drawable drawable) {
        this.mDividerDecoration.a(drawable);
    }

    public void setDividerHeight(int i) {
        this.mDividerDecoration.a(i);
    }

    public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
        if (!this.mPreferenceManager.a(preferenceScreen) || preferenceScreen == null) {
            return;
        }
        onUnbindPreferences();
        this.mHavePrefs = true;
        if (this.mInitDone) {
            postBindPreferences();
        }
    }

    public void setPreferencesFromResource(int i, String str) {
        requirePreferenceManager();
        PreferenceScreen a2 = this.mPreferenceManager.a(getContext(), i, null);
        Object obj = a2;
        if (str != null) {
            Object a3 = a2.a(str);
            boolean z = a3 instanceof PreferenceScreen;
            obj = a3;
            if (!z) {
                throw new IllegalArgumentException("Preference object with key " + str + " is not a PreferenceScreen");
            }
        }
        setPreferenceScreen((PreferenceScreen) obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class d extends RecyclerView.n {

        /* renamed from: a, reason: collision with root package name */
        private Drawable f1622a;

        /* renamed from: b, reason: collision with root package name */
        private int f1623b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f1624c = true;

        d() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.n
        public void a(Rect rect, View view, RecyclerView recyclerView, RecyclerView.a0 a0Var) {
            if (a(view, recyclerView)) {
                rect.bottom = this.f1623b;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.n
        public void b(Canvas canvas, RecyclerView recyclerView, RecyclerView.a0 a0Var) {
            if (this.f1622a == null) {
                return;
            }
            int childCount = recyclerView.getChildCount();
            int width = recyclerView.getWidth();
            for (int i = 0; i < childCount; i++) {
                View childAt = recyclerView.getChildAt(i);
                if (a(childAt, recyclerView)) {
                    int y = ((int) childAt.getY()) + childAt.getHeight();
                    this.f1622a.setBounds(0, y, width, this.f1623b + y);
                    this.f1622a.draw(canvas);
                }
            }
        }

        private boolean a(View view, RecyclerView recyclerView) {
            RecyclerView.d0 childViewHolder = recyclerView.getChildViewHolder(view);
            if (!((childViewHolder instanceof l) && ((l) childViewHolder).b())) {
                return false;
            }
            boolean z = this.f1624c;
            int indexOfChild = recyclerView.indexOfChild(view);
            if (indexOfChild >= recyclerView.getChildCount() - 1) {
                return z;
            }
            RecyclerView.d0 childViewHolder2 = recyclerView.getChildViewHolder(recyclerView.getChildAt(indexOfChild + 1));
            return (childViewHolder2 instanceof l) && ((l) childViewHolder2).a();
        }

        public void a(Drawable drawable) {
            if (drawable != null) {
                this.f1623b = drawable.getIntrinsicHeight();
            } else {
                this.f1623b = 0;
            }
            this.f1622a = drawable;
            g.this.mList.invalidateItemDecorations();
        }

        public void a(int i) {
            this.f1623b = i;
            g.this.mList.invalidateItemDecorations();
        }

        public void a(boolean z) {
            this.f1624c = z;
        }
    }

    public void scrollToPreference(Preference preference) {
        scrollToPreferenceInternal(preference, null);
    }
}
