package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import androidx.preference.Preference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public abstract class PreferenceGroup extends Preference {

    /* renamed from: b, reason: collision with root package name */
    final a.d.g<String, Long> f1584b;

    /* renamed from: c, reason: collision with root package name */
    private List<Preference> f1585c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f1586d;

    /* renamed from: e, reason: collision with root package name */
    private int f1587e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f1588f;
    private int g;
    private b h;

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this) {
                PreferenceGroup.this.f1584b.clear();
            }
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void a();
    }

    /* loaded from: classes.dex */
    public interface c {
        int a(String str);

        int d(Preference preference);
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f1584b = new a.d.g<>();
        new Handler();
        this.f1586d = true;
        this.f1587e = 0;
        this.f1588f = false;
        this.g = Preference.DEFAULT_ORDER;
        this.h = null;
        new a();
        this.f1585c = new ArrayList();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.PreferenceGroup, i, i2);
        int i3 = t.PreferenceGroup_orderingFromXml;
        this.f1586d = a.f.d.d.g.a(obtainStyledAttributes, i3, i3, true);
        if (obtainStyledAttributes.hasValue(t.PreferenceGroup_initialExpandedChildrenCount)) {
            int i4 = t.PreferenceGroup_initialExpandedChildrenCount;
            b(a.f.d.d.g.a(obtainStyledAttributes, i4, i4, Preference.DEFAULT_ORDER));
        }
        obtainStyledAttributes.recycle();
    }

    public void a(boolean z) {
        this.f1586d = z;
    }

    public void b(int i) {
        if (i != Integer.MAX_VALUE && !hasKey()) {
            Log.e("PreferenceGroup", getClass().getSimpleName() + " should have a key defined if it contains an expandable preference");
        }
        this.g = i;
    }

    public int c() {
        return this.f1585c.size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean d() {
        return true;
    }

    @Override // androidx.preference.Preference
    protected void dispatchRestoreInstanceState(Bundle bundle) {
        super.dispatchRestoreInstanceState(bundle);
        int c2 = c();
        for (int i = 0; i < c2; i++) {
            a(i).dispatchRestoreInstanceState(bundle);
        }
    }

    @Override // androidx.preference.Preference
    protected void dispatchSaveInstanceState(Bundle bundle) {
        super.dispatchSaveInstanceState(bundle);
        int c2 = c();
        for (int i = 0; i < c2; i++) {
            a(i).dispatchSaveInstanceState(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        synchronized (this) {
            Collections.sort(this.f1585c);
        }
    }

    @Override // androidx.preference.Preference
    public void notifyDependencyChange(boolean z) {
        super.notifyDependencyChange(z);
        int c2 = c();
        for (int i = 0; i < c2; i++) {
            a(i).onParentChanged(this, z);
        }
    }

    @Override // androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        this.f1588f = true;
        int c2 = c();
        for (int i = 0; i < c2; i++) {
            a(i).onAttached();
        }
    }

    @Override // androidx.preference.Preference
    public void onDetached() {
        super.onDetached();
        this.f1588f = false;
        int c2 = c();
        for (int i = 0; i < c2; i++) {
            a(i).onDetached();
        }
    }

    @Override // androidx.preference.Preference
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable != null && parcelable.getClass().equals(d.class)) {
            d dVar = (d) parcelable;
            this.g = dVar.f1590b;
            super.onRestoreInstanceState(dVar.getSuperState());
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override // androidx.preference.Preference
    protected Parcelable onSaveInstanceState() {
        return new d(super.onSaveInstanceState(), this.g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d extends Preference.b {
        public static final Parcelable.Creator<d> CREATOR = new a();

        /* renamed from: b, reason: collision with root package name */
        int f1590b;

        /* loaded from: classes.dex */
        static class a implements Parcelable.Creator<d> {
            a() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public d createFromParcel(Parcel parcel) {
                return new d(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public d[] newArray(int i) {
                return new d[i];
            }
        }

        d(Parcel parcel) {
            super(parcel);
            this.f1590b = parcel.readInt();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1590b);
        }

        d(Parcelable parcelable, int i) {
            super(parcelable);
            this.f1590b = i;
        }
    }

    public int a() {
        return this.g;
    }

    protected boolean c(Preference preference) {
        preference.onParentChanged(this, shouldDisableDependents());
        return true;
    }

    public void a(Preference preference) {
        b(preference);
    }

    public Preference a(int i) {
        return this.f1585c.get(i);
    }

    public boolean b(Preference preference) {
        long b2;
        if (this.f1585c.contains(preference)) {
            return true;
        }
        if (preference.getKey() != null) {
            PreferenceGroup preferenceGroup = this;
            while (preferenceGroup.getParent() != null) {
                preferenceGroup = preferenceGroup.getParent();
            }
            String key = preference.getKey();
            if (preferenceGroup.a(key) != null) {
                Log.e("PreferenceGroup", "Found duplicated key: \"" + key + "\". This can cause unintended behaviour, please use unique keys for every preference.");
            }
        }
        if (preference.getOrder() == Integer.MAX_VALUE) {
            if (this.f1586d) {
                int i = this.f1587e;
                this.f1587e = i + 1;
                preference.setOrder(i);
            }
            if (preference instanceof PreferenceGroup) {
                ((PreferenceGroup) preference).a(this.f1586d);
            }
        }
        int binarySearch = Collections.binarySearch(this.f1585c, preference);
        if (binarySearch < 0) {
            binarySearch = (binarySearch * (-1)) - 1;
        }
        if (!c(preference)) {
            return false;
        }
        synchronized (this) {
            this.f1585c.add(binarySearch, preference);
        }
        j preferenceManager = getPreferenceManager();
        String key2 = preference.getKey();
        if (key2 != null && this.f1584b.containsKey(key2)) {
            b2 = this.f1584b.get(key2).longValue();
            this.f1584b.remove(key2);
        } else {
            b2 = preferenceManager.b();
        }
        preference.onAttachedToHierarchy(preferenceManager, b2);
        preference.assignParent(this);
        if (this.f1588f) {
            preference.onAttached();
        }
        notifyHierarchyChanged();
        return true;
    }

    public <T extends Preference> T a(CharSequence charSequence) {
        T t;
        if (charSequence != null) {
            if (TextUtils.equals(getKey(), charSequence)) {
                return this;
            }
            int c2 = c();
            for (int i = 0; i < c2; i++) {
                PreferenceGroup preferenceGroup = (T) a(i);
                if (TextUtils.equals(preferenceGroup.getKey(), charSequence)) {
                    return preferenceGroup;
                }
                if ((preferenceGroup instanceof PreferenceGroup) && (t = (T) preferenceGroup.a(charSequence)) != null) {
                    return t;
                }
            }
            return null;
        }
        throw new IllegalArgumentException("Key cannot be null");
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public b b() {
        return this.h;
    }
}
