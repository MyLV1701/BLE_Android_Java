package androidx.preference;

import android.R;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.preference.j;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public class Preference implements Comparable<Preference> {
    private static final String CLIPBOARD_ID = "Preference";
    public static final int DEFAULT_ORDER = Integer.MAX_VALUE;
    private boolean mAllowDividerAbove;
    private boolean mAllowDividerBelow;
    private boolean mBaseMethodCalled;
    private final View.OnClickListener mClickListener;
    private Context mContext;
    private boolean mCopyingEnabled;
    private Object mDefaultValue;
    private String mDependencyKey;
    private boolean mDependencyMet;
    private List<Preference> mDependents;
    private boolean mEnabled;
    private Bundle mExtras;
    private String mFragment;
    private boolean mHasId;
    private boolean mHasSingleLineTitleAttr;
    private Drawable mIcon;
    private int mIconResId;
    private boolean mIconSpaceReserved;
    private long mId;
    private Intent mIntent;
    private String mKey;
    private int mLayoutResId;
    private c mListener;
    private d mOnChangeListener;
    private e mOnClickListener;
    private f mOnCopyListener;
    private int mOrder;
    private boolean mParentDependencyMet;
    private PreferenceGroup mParentGroup;
    private boolean mPersistent;
    private androidx.preference.e mPreferenceDataStore;
    private j mPreferenceManager;
    private boolean mRequiresKey;
    private boolean mSelectable;
    private boolean mShouldDisableView;
    private boolean mSingleLineTitle;
    private CharSequence mSummary;
    private g mSummaryProvider;
    private CharSequence mTitle;
    private int mViewId;
    private boolean mVisible;
    private boolean mWasDetached;
    private int mWidgetLayoutResId;

    /* loaded from: classes.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Preference.this.performClick(view);
        }
    }

    /* loaded from: classes.dex */
    public static class b extends AbsSavedState {
        public static final Parcelable.Creator<b> CREATOR = new a();

        /* loaded from: classes.dex */
        static class a implements Parcelable.Creator<b> {
            a() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public b createFromParcel(Parcel parcel) {
                return new b(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public b[] newArray(int i) {
                return new b[i];
            }
        }

        public b(Parcel parcel) {
            super(parcel);
        }

        public b(Parcelable parcelable) {
            super(parcelable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface c {
        void a(Preference preference);

        void b(Preference preference);

        void c(Preference preference);
    }

    /* loaded from: classes.dex */
    public interface d {
        boolean a(Preference preference, Object obj);
    }

    /* loaded from: classes.dex */
    public interface e {
        boolean a(Preference preference);
    }

    /* loaded from: classes.dex */
    private static class f implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        /* renamed from: b, reason: collision with root package name */
        private final Preference f1583b;

        f(Preference preference) {
            this.f1583b = preference;
        }

        @Override // android.view.View.OnCreateContextMenuListener
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            CharSequence summary = this.f1583b.getSummary();
            if (!this.f1583b.isCopyingEnabled() || TextUtils.isEmpty(summary)) {
                return;
            }
            contextMenu.setHeaderTitle(summary);
            contextMenu.add(0, 0, 0, r.copy).setOnMenuItemClickListener(this);
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            ClipboardManager clipboardManager = (ClipboardManager) this.f1583b.getContext().getSystemService("clipboard");
            CharSequence summary = this.f1583b.getSummary();
            clipboardManager.setPrimaryClip(ClipData.newPlainText(Preference.CLIPBOARD_ID, summary));
            Toast.makeText(this.f1583b.getContext(), this.f1583b.getContext().getString(r.preference_copied, summary), 0).show();
            return true;
        }
    }

    /* loaded from: classes.dex */
    public interface g<T extends Preference> {
        CharSequence a(T t);
    }

    public Preference(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mOrder = DEFAULT_ORDER;
        this.mViewId = 0;
        this.mEnabled = true;
        this.mSelectable = true;
        this.mPersistent = true;
        this.mDependencyMet = true;
        this.mParentDependencyMet = true;
        this.mVisible = true;
        this.mAllowDividerAbove = true;
        this.mAllowDividerBelow = true;
        this.mSingleLineTitle = true;
        this.mShouldDisableView = true;
        this.mLayoutResId = q.preference;
        this.mClickListener = new a();
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.Preference, i, i2);
        this.mIconResId = a.f.d.d.g.b(obtainStyledAttributes, t.Preference_icon, t.Preference_android_icon, 0);
        this.mKey = a.f.d.d.g.b(obtainStyledAttributes, t.Preference_key, t.Preference_android_key);
        this.mTitle = a.f.d.d.g.c(obtainStyledAttributes, t.Preference_title, t.Preference_android_title);
        this.mSummary = a.f.d.d.g.c(obtainStyledAttributes, t.Preference_summary, t.Preference_android_summary);
        this.mOrder = a.f.d.d.g.a(obtainStyledAttributes, t.Preference_order, t.Preference_android_order, DEFAULT_ORDER);
        this.mFragment = a.f.d.d.g.b(obtainStyledAttributes, t.Preference_fragment, t.Preference_android_fragment);
        this.mLayoutResId = a.f.d.d.g.b(obtainStyledAttributes, t.Preference_layout, t.Preference_android_layout, q.preference);
        this.mWidgetLayoutResId = a.f.d.d.g.b(obtainStyledAttributes, t.Preference_widgetLayout, t.Preference_android_widgetLayout, 0);
        this.mEnabled = a.f.d.d.g.a(obtainStyledAttributes, t.Preference_enabled, t.Preference_android_enabled, true);
        this.mSelectable = a.f.d.d.g.a(obtainStyledAttributes, t.Preference_selectable, t.Preference_android_selectable, true);
        this.mPersistent = a.f.d.d.g.a(obtainStyledAttributes, t.Preference_persistent, t.Preference_android_persistent, true);
        this.mDependencyKey = a.f.d.d.g.b(obtainStyledAttributes, t.Preference_dependency, t.Preference_android_dependency);
        int i3 = t.Preference_allowDividerAbove;
        this.mAllowDividerAbove = a.f.d.d.g.a(obtainStyledAttributes, i3, i3, this.mSelectable);
        int i4 = t.Preference_allowDividerBelow;
        this.mAllowDividerBelow = a.f.d.d.g.a(obtainStyledAttributes, i4, i4, this.mSelectable);
        if (obtainStyledAttributes.hasValue(t.Preference_defaultValue)) {
            this.mDefaultValue = onGetDefaultValue(obtainStyledAttributes, t.Preference_defaultValue);
        } else if (obtainStyledAttributes.hasValue(t.Preference_android_defaultValue)) {
            this.mDefaultValue = onGetDefaultValue(obtainStyledAttributes, t.Preference_android_defaultValue);
        }
        this.mShouldDisableView = a.f.d.d.g.a(obtainStyledAttributes, t.Preference_shouldDisableView, t.Preference_android_shouldDisableView, true);
        this.mHasSingleLineTitleAttr = obtainStyledAttributes.hasValue(t.Preference_singleLineTitle);
        if (this.mHasSingleLineTitleAttr) {
            this.mSingleLineTitle = a.f.d.d.g.a(obtainStyledAttributes, t.Preference_singleLineTitle, t.Preference_android_singleLineTitle, true);
        }
        this.mIconSpaceReserved = a.f.d.d.g.a(obtainStyledAttributes, t.Preference_iconSpaceReserved, t.Preference_android_iconSpaceReserved, false);
        int i5 = t.Preference_isPreferenceVisible;
        this.mVisible = a.f.d.d.g.a(obtainStyledAttributes, i5, i5, true);
        int i6 = t.Preference_enableCopying;
        this.mCopyingEnabled = a.f.d.d.g.a(obtainStyledAttributes, i6, i6, false);
        obtainStyledAttributes.recycle();
    }

    private void dispatchSetInitialValue() {
        if (getPreferenceDataStore() != null) {
            onSetInitialValue(true, this.mDefaultValue);
            return;
        }
        if (shouldPersist() && getSharedPreferences().contains(this.mKey)) {
            onSetInitialValue(true, null);
            return;
        }
        Object obj = this.mDefaultValue;
        if (obj != null) {
            onSetInitialValue(false, obj);
        }
    }

    private void registerDependency() {
        if (TextUtils.isEmpty(this.mDependencyKey)) {
            return;
        }
        Preference findPreferenceInHierarchy = findPreferenceInHierarchy(this.mDependencyKey);
        if (findPreferenceInHierarchy != null) {
            findPreferenceInHierarchy.registerDependent(this);
            return;
        }
        throw new IllegalStateException("Dependency \"" + this.mDependencyKey + "\" not found for preference \"" + this.mKey + "\" (title: \"" + ((Object) this.mTitle) + "\"");
    }

    private void registerDependent(Preference preference) {
        if (this.mDependents == null) {
            this.mDependents = new ArrayList();
        }
        this.mDependents.add(preference);
        preference.onDependencyChanged(this, shouldDisableDependents());
    }

    private void setEnabledStateOnViews(View view, boolean z) {
        view.setEnabled(z);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                setEnabledStateOnViews(viewGroup.getChildAt(childCount), z);
            }
        }
    }

    private void tryCommit(SharedPreferences.Editor editor) {
        if (this.mPreferenceManager.i()) {
            editor.apply();
        }
    }

    private void unregisterDependency() {
        Preference findPreferenceInHierarchy;
        String str = this.mDependencyKey;
        if (str == null || (findPreferenceInHierarchy = findPreferenceInHierarchy(str)) == null) {
            return;
        }
        findPreferenceInHierarchy.unregisterDependent(this);
    }

    private void unregisterDependent(Preference preference) {
        List<Preference> list = this.mDependents;
        if (list != null) {
            list.remove(preference);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void assignParent(PreferenceGroup preferenceGroup) {
        if (preferenceGroup != null && this.mParentGroup != null) {
            throw new IllegalStateException("This preference already has a parent. You must remove the existing parent before assigning a new one.");
        }
        this.mParentGroup = preferenceGroup;
    }

    public boolean callChangeListener(Object obj) {
        d dVar = this.mOnChangeListener;
        return dVar == null || dVar.a(this, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void clearWasDetached() {
        this.mWasDetached = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchRestoreInstanceState(Bundle bundle) {
        Parcelable parcelable;
        if (!hasKey() || (parcelable = bundle.getParcelable(this.mKey)) == null) {
            return;
        }
        this.mBaseMethodCalled = false;
        onRestoreInstanceState(parcelable);
        if (!this.mBaseMethodCalled) {
            throw new IllegalStateException("Derived class did not call super.onRestoreInstanceState()");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchSaveInstanceState(Bundle bundle) {
        if (hasKey()) {
            this.mBaseMethodCalled = false;
            Parcelable onSaveInstanceState = onSaveInstanceState();
            if (!this.mBaseMethodCalled) {
                throw new IllegalStateException("Derived class did not call super.onSaveInstanceState()");
            }
            if (onSaveInstanceState != null) {
                bundle.putParcelable(this.mKey, onSaveInstanceState);
            }
        }
    }

    protected <T extends Preference> T findPreferenceInHierarchy(String str) {
        j jVar = this.mPreferenceManager;
        if (jVar == null) {
            return null;
        }
        return (T) jVar.a((CharSequence) str);
    }

    public Context getContext() {
        return this.mContext;
    }

    public String getDependency() {
        return this.mDependencyKey;
    }

    public Bundle getExtras() {
        if (this.mExtras == null) {
            this.mExtras = new Bundle();
        }
        return this.mExtras;
    }

    StringBuilder getFilterableStringBuilder() {
        StringBuilder sb = new StringBuilder();
        CharSequence title = getTitle();
        if (!TextUtils.isEmpty(title)) {
            sb.append(title);
            sb.append(' ');
        }
        CharSequence summary = getSummary();
        if (!TextUtils.isEmpty(summary)) {
            sb.append(summary);
            sb.append(' ');
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb;
    }

    public String getFragment() {
        return this.mFragment;
    }

    public Drawable getIcon() {
        int i;
        if (this.mIcon == null && (i = this.mIconResId) != 0) {
            this.mIcon = a.a.k.a.a.c(this.mContext, i);
        }
        return this.mIcon;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getId() {
        return this.mId;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public String getKey() {
        return this.mKey;
    }

    public final int getLayoutResource() {
        return this.mLayoutResId;
    }

    public d getOnPreferenceChangeListener() {
        return this.mOnChangeListener;
    }

    public e getOnPreferenceClickListener() {
        return this.mOnClickListener;
    }

    public int getOrder() {
        return this.mOrder;
    }

    public PreferenceGroup getParent() {
        return this.mParentGroup;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean getPersistedBoolean(boolean z) {
        if (!shouldPersist()) {
            return z;
        }
        androidx.preference.e preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore != null) {
            return preferenceDataStore.a(this.mKey, z);
        }
        return this.mPreferenceManager.h().getBoolean(this.mKey, z);
    }

    protected float getPersistedFloat(float f2) {
        if (!shouldPersist()) {
            return f2;
        }
        androidx.preference.e preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore != null) {
            return preferenceDataStore.a(this.mKey, f2);
        }
        return this.mPreferenceManager.h().getFloat(this.mKey, f2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getPersistedInt(int i) {
        if (!shouldPersist()) {
            return i;
        }
        androidx.preference.e preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore != null) {
            return preferenceDataStore.a(this.mKey, i);
        }
        return this.mPreferenceManager.h().getInt(this.mKey, i);
    }

    protected long getPersistedLong(long j) {
        if (!shouldPersist()) {
            return j;
        }
        androidx.preference.e preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore != null) {
            return preferenceDataStore.a(this.mKey, j);
        }
        return this.mPreferenceManager.h().getLong(this.mKey, j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getPersistedString(String str) {
        if (!shouldPersist()) {
            return str;
        }
        androidx.preference.e preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore != null) {
            return preferenceDataStore.a(this.mKey, str);
        }
        return this.mPreferenceManager.h().getString(this.mKey, str);
    }

    public Set<String> getPersistedStringSet(Set<String> set) {
        if (!shouldPersist()) {
            return set;
        }
        androidx.preference.e preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore != null) {
            return preferenceDataStore.a(this.mKey, set);
        }
        return this.mPreferenceManager.h().getStringSet(this.mKey, set);
    }

    public androidx.preference.e getPreferenceDataStore() {
        androidx.preference.e eVar = this.mPreferenceDataStore;
        if (eVar != null) {
            return eVar;
        }
        j jVar = this.mPreferenceManager;
        if (jVar != null) {
            return jVar.f();
        }
        return null;
    }

    public j getPreferenceManager() {
        return this.mPreferenceManager;
    }

    public SharedPreferences getSharedPreferences() {
        if (this.mPreferenceManager == null || getPreferenceDataStore() != null) {
            return null;
        }
        return this.mPreferenceManager.h();
    }

    public boolean getShouldDisableView() {
        return this.mShouldDisableView;
    }

    public CharSequence getSummary() {
        if (getSummaryProvider() != null) {
            return getSummaryProvider().a(this);
        }
        return this.mSummary;
    }

    public final g getSummaryProvider() {
        return this.mSummaryProvider;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public final int getWidgetLayoutResource() {
        return this.mWidgetLayoutResId;
    }

    public boolean hasKey() {
        return !TextUtils.isEmpty(this.mKey);
    }

    public boolean isCopyingEnabled() {
        return this.mCopyingEnabled;
    }

    public boolean isEnabled() {
        return this.mEnabled && this.mDependencyMet && this.mParentDependencyMet;
    }

    public boolean isIconSpaceReserved() {
        return this.mIconSpaceReserved;
    }

    public boolean isPersistent() {
        return this.mPersistent;
    }

    public boolean isSelectable() {
        return this.mSelectable;
    }

    public final boolean isShown() {
        if (!isVisible() || getPreferenceManager() == null) {
            return false;
        }
        if (this == getPreferenceManager().g()) {
            return true;
        }
        PreferenceGroup parent = getParent();
        if (parent == null) {
            return false;
        }
        return parent.isShown();
    }

    public boolean isSingleLineTitle() {
        return this.mSingleLineTitle;
    }

    public final boolean isVisible() {
        return this.mVisible;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyChanged() {
        c cVar = this.mListener;
        if (cVar != null) {
            cVar.b(this);
        }
    }

    public void notifyDependencyChange(boolean z) {
        List<Preference> list = this.mDependents;
        if (list == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.get(i).onDependencyChanged(this, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyHierarchyChanged() {
        c cVar = this.mListener;
        if (cVar != null) {
            cVar.a(this);
        }
    }

    public void onAttached() {
        registerDependency();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onAttachedToHierarchy(j jVar) {
        this.mPreferenceManager = jVar;
        if (!this.mHasId) {
            this.mId = jVar.b();
        }
        dispatchSetInitialValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onBindViewHolder(androidx.preference.l r9) {
        /*
            Method dump skipped, instructions count: 283
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.Preference.onBindViewHolder(androidx.preference.l):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onClick() {
    }

    public void onDependencyChanged(Preference preference, boolean z) {
        if (this.mDependencyMet == z) {
            this.mDependencyMet = !z;
            notifyDependencyChange(shouldDisableDependents());
            notifyChanged();
        }
    }

    public void onDetached() {
        unregisterDependency();
        this.mWasDetached = true;
    }

    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        return null;
    }

    @Deprecated
    public void onInitializeAccessibilityNodeInfo(a.f.l.f0.c cVar) {
    }

    public void onParentChanged(Preference preference, boolean z) {
        if (this.mParentDependencyMet == z) {
            this.mParentDependencyMet = !z;
            notifyDependencyChange(shouldDisableDependents());
            notifyChanged();
        }
    }

    protected void onPrepareForRemoval() {
        unregisterDependency();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        this.mBaseMethodCalled = true;
        if (parcelable != AbsSavedState.EMPTY_STATE && parcelable != null) {
            throw new IllegalArgumentException("Wrong state class -- expecting Preference State");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        this.mBaseMethodCalled = true;
        return AbsSavedState.EMPTY_STATE;
    }

    protected void onSetInitialValue(Object obj) {
    }

    @Deprecated
    protected void onSetInitialValue(boolean z, Object obj) {
        onSetInitialValue(obj);
    }

    public Bundle peekExtras() {
        return this.mExtras;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void performClick(View view) {
        performClick();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean persistBoolean(boolean z) {
        if (!shouldPersist()) {
            return false;
        }
        if (z == getPersistedBoolean(!z)) {
            return true;
        }
        androidx.preference.e preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore != null) {
            preferenceDataStore.b(this.mKey, z);
        } else {
            SharedPreferences.Editor a2 = this.mPreferenceManager.a();
            a2.putBoolean(this.mKey, z);
            tryCommit(a2);
        }
        return true;
    }

    protected boolean persistFloat(float f2) {
        if (!shouldPersist()) {
            return false;
        }
        if (f2 == getPersistedFloat(Float.NaN)) {
            return true;
        }
        androidx.preference.e preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore != null) {
            preferenceDataStore.b(this.mKey, f2);
        } else {
            SharedPreferences.Editor a2 = this.mPreferenceManager.a();
            a2.putFloat(this.mKey, f2);
            tryCommit(a2);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean persistInt(int i) {
        if (!shouldPersist()) {
            return false;
        }
        if (i == getPersistedInt(i ^ (-1))) {
            return true;
        }
        androidx.preference.e preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore != null) {
            preferenceDataStore.b(this.mKey, i);
        } else {
            SharedPreferences.Editor a2 = this.mPreferenceManager.a();
            a2.putInt(this.mKey, i);
            tryCommit(a2);
        }
        return true;
    }

    protected boolean persistLong(long j) {
        if (!shouldPersist()) {
            return false;
        }
        if (j == getPersistedLong((-1) ^ j)) {
            return true;
        }
        androidx.preference.e preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore != null) {
            preferenceDataStore.b(this.mKey, j);
        } else {
            SharedPreferences.Editor a2 = this.mPreferenceManager.a();
            a2.putLong(this.mKey, j);
            tryCommit(a2);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean persistString(String str) {
        if (!shouldPersist()) {
            return false;
        }
        if (TextUtils.equals(str, getPersistedString(null))) {
            return true;
        }
        androidx.preference.e preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore != null) {
            preferenceDataStore.b(this.mKey, str);
        } else {
            SharedPreferences.Editor a2 = this.mPreferenceManager.a();
            a2.putString(this.mKey, str);
            tryCommit(a2);
        }
        return true;
    }

    public boolean persistStringSet(Set<String> set) {
        if (!shouldPersist()) {
            return false;
        }
        if (set.equals(getPersistedStringSet(null))) {
            return true;
        }
        androidx.preference.e preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore != null) {
            preferenceDataStore.b(this.mKey, set);
        } else {
            SharedPreferences.Editor a2 = this.mPreferenceManager.a();
            a2.putStringSet(this.mKey, set);
            tryCommit(a2);
        }
        return true;
    }

    void requireKey() {
        if (!TextUtils.isEmpty(this.mKey)) {
            this.mRequiresKey = true;
            return;
        }
        throw new IllegalStateException("Preference does not have a key assigned.");
    }

    public void restoreHierarchyState(Bundle bundle) {
        dispatchRestoreInstanceState(bundle);
    }

    public void saveHierarchyState(Bundle bundle) {
        dispatchSaveInstanceState(bundle);
    }

    public void setCopyingEnabled(boolean z) {
        if (this.mCopyingEnabled != z) {
            this.mCopyingEnabled = z;
            notifyChanged();
        }
    }

    public void setDefaultValue(Object obj) {
        this.mDefaultValue = obj;
    }

    public void setDependency(String str) {
        unregisterDependency();
        this.mDependencyKey = str;
        registerDependency();
    }

    public void setEnabled(boolean z) {
        if (this.mEnabled != z) {
            this.mEnabled = z;
            notifyDependencyChange(shouldDisableDependents());
            notifyChanged();
        }
    }

    public void setFragment(String str) {
        this.mFragment = str;
    }

    public void setIcon(Drawable drawable) {
        if (this.mIcon != drawable) {
            this.mIcon = drawable;
            this.mIconResId = 0;
            notifyChanged();
        }
    }

    public void setIconSpaceReserved(boolean z) {
        if (this.mIconSpaceReserved != z) {
            this.mIconSpaceReserved = z;
            notifyChanged();
        }
    }

    public void setIntent(Intent intent) {
        this.mIntent = intent;
    }

    public void setKey(String str) {
        this.mKey = str;
        if (!this.mRequiresKey || hasKey()) {
            return;
        }
        requireKey();
    }

    public void setLayoutResource(int i) {
        this.mLayoutResId = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setOnPreferenceChangeInternalListener(c cVar) {
        this.mListener = cVar;
    }

    public void setOnPreferenceChangeListener(d dVar) {
        this.mOnChangeListener = dVar;
    }

    public void setOnPreferenceClickListener(e eVar) {
        this.mOnClickListener = eVar;
    }

    public void setOrder(int i) {
        if (i != this.mOrder) {
            this.mOrder = i;
            notifyHierarchyChanged();
        }
    }

    public void setPersistent(boolean z) {
        this.mPersistent = z;
    }

    public void setPreferenceDataStore(androidx.preference.e eVar) {
        this.mPreferenceDataStore = eVar;
    }

    public void setSelectable(boolean z) {
        if (this.mSelectable != z) {
            this.mSelectable = z;
            notifyChanged();
        }
    }

    public void setShouldDisableView(boolean z) {
        if (this.mShouldDisableView != z) {
            this.mShouldDisableView = z;
            notifyChanged();
        }
    }

    public void setSingleLineTitle(boolean z) {
        this.mHasSingleLineTitleAttr = true;
        this.mSingleLineTitle = z;
    }

    public void setSummary(CharSequence charSequence) {
        if (getSummaryProvider() == null) {
            if (TextUtils.equals(this.mSummary, charSequence)) {
                return;
            }
            this.mSummary = charSequence;
            notifyChanged();
            return;
        }
        throw new IllegalStateException("Preference already has a SummaryProvider set.");
    }

    public final void setSummaryProvider(g gVar) {
        this.mSummaryProvider = gVar;
        notifyChanged();
    }

    public void setTitle(CharSequence charSequence) {
        if ((charSequence != null || this.mTitle == null) && (charSequence == null || charSequence.equals(this.mTitle))) {
            return;
        }
        this.mTitle = charSequence;
        notifyChanged();
    }

    public void setViewId(int i) {
        this.mViewId = i;
    }

    public final void setVisible(boolean z) {
        if (this.mVisible != z) {
            this.mVisible = z;
            c cVar = this.mListener;
            if (cVar != null) {
                cVar.c(this);
            }
        }
    }

    public void setWidgetLayoutResource(int i) {
        this.mWidgetLayoutResId = i;
    }

    public boolean shouldDisableDependents() {
        return !isEnabled();
    }

    protected boolean shouldPersist() {
        return this.mPreferenceManager != null && isPersistent() && hasKey();
    }

    public String toString() {
        return getFilterableStringBuilder().toString();
    }

    final boolean wasDetached() {
        return this.mWasDetached;
    }

    @Override // java.lang.Comparable
    public int compareTo(Preference preference) {
        int i = this.mOrder;
        int i2 = preference.mOrder;
        if (i != i2) {
            return i - i2;
        }
        CharSequence charSequence = this.mTitle;
        CharSequence charSequence2 = preference.mTitle;
        if (charSequence == charSequence2) {
            return 0;
        }
        if (charSequence == null) {
            return 1;
        }
        if (charSequence2 == null) {
            return -1;
        }
        return charSequence.toString().compareToIgnoreCase(preference.mTitle.toString());
    }

    public void performClick() {
        j.c d2;
        if (isEnabled() && isSelectable()) {
            onClick();
            e eVar = this.mOnClickListener;
            if (eVar == null || !eVar.a(this)) {
                j preferenceManager = getPreferenceManager();
                if ((preferenceManager == null || (d2 = preferenceManager.d()) == null || !d2.onPreferenceTreeClick(this)) && this.mIntent != null) {
                    getContext().startActivity(this.mIntent);
                }
            }
        }
    }

    public void setTitle(int i) {
        setTitle(this.mContext.getString(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onAttachedToHierarchy(j jVar, long j) {
        this.mId = j;
        this.mHasId = true;
        try {
            onAttachedToHierarchy(jVar);
        } finally {
            this.mHasId = false;
        }
    }

    public void setIcon(int i) {
        setIcon(a.a.k.a.a.c(this.mContext, i));
        this.mIconResId = i;
    }

    public void setSummary(int i) {
        setSummary(this.mContext.getString(i));
    }

    public Preference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public Preference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.f.d.d.g.a(context, m.preferenceStyle, R.attr.preferenceStyle));
    }

    public Preference(Context context) {
        this(context, null);
    }
}
