package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.preference.Preference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class MultiSelectListPreference extends DialogPreference {
    private CharSequence[] h;
    private CharSequence[] i;
    private Set<String> j;

    public MultiSelectListPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.j = new HashSet();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.MultiSelectListPreference, i, i2);
        this.h = a.f.d.d.g.d(obtainStyledAttributes, t.MultiSelectListPreference_entries, t.MultiSelectListPreference_android_entries);
        this.i = a.f.d.d.g.d(obtainStyledAttributes, t.MultiSelectListPreference_entryValues, t.MultiSelectListPreference_android_entryValues);
        obtainStyledAttributes.recycle();
    }

    public void a(Set<String> set) {
        this.j.clear();
        this.j.addAll(set);
        persistStringSet(set);
        notifyChanged();
    }

    public CharSequence[] g() {
        return this.h;
    }

    public CharSequence[] h() {
        return this.i;
    }

    public Set<String> i() {
        return this.j;
    }

    @Override // androidx.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        CharSequence[] textArray = typedArray.getTextArray(i);
        HashSet hashSet = new HashSet();
        for (CharSequence charSequence : textArray) {
            hashSet.add(charSequence.toString());
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable != null && parcelable.getClass().equals(a.class)) {
            a aVar = (a) parcelable;
            super.onRestoreInstanceState(aVar.getSuperState());
            a(aVar.f1581b);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (isPersistent()) {
            return onSaveInstanceState;
        }
        a aVar = new a(onSaveInstanceState);
        aVar.f1581b = i();
        return aVar;
    }

    @Override // androidx.preference.Preference
    protected void onSetInitialValue(Object obj) {
        a(getPersistedStringSet((Set) obj));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a extends Preference.b {
        public static final Parcelable.Creator<a> CREATOR = new C0055a();

        /* renamed from: b, reason: collision with root package name */
        Set<String> f1581b;

        /* renamed from: androidx.preference.MultiSelectListPreference$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        static class C0055a implements Parcelable.Creator<a> {
            C0055a() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public a createFromParcel(Parcel parcel) {
                return new a(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public a[] newArray(int i) {
                return new a[i];
            }
        }

        a(Parcel parcel) {
            super(parcel);
            int readInt = parcel.readInt();
            this.f1581b = new HashSet();
            String[] strArr = new String[readInt];
            parcel.readStringArray(strArr);
            Collections.addAll(this.f1581b, strArr);
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1581b.size());
            Set<String> set = this.f1581b;
            parcel.writeStringArray((String[]) set.toArray(new String[set.size()]));
        }

        a(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public MultiSelectListPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public MultiSelectListPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.f.d.d.g.a(context, m.dialogPreferenceStyle, R.attr.dialogPreferenceStyle));
    }
}
