package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import androidx.preference.Preference;

/* loaded from: classes.dex */
public class ListPreference extends DialogPreference {
    private CharSequence[] h;
    private CharSequence[] i;
    private String j;
    private String k;
    private boolean l;

    /* loaded from: classes.dex */
    public static final class b implements Preference.g<ListPreference> {

        /* renamed from: a, reason: collision with root package name */
        private static b f1580a;

        private b() {
        }

        public static b a() {
            if (f1580a == null) {
                f1580a = new b();
            }
            return f1580a;
        }

        @Override // androidx.preference.Preference.g
        public CharSequence a(ListPreference listPreference) {
            if (TextUtils.isEmpty(listPreference.h())) {
                return listPreference.getContext().getString(r.not_set);
            }
            return listPreference.h();
        }
    }

    public ListPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.ListPreference, i, i2);
        this.h = a.f.d.d.g.d(obtainStyledAttributes, t.ListPreference_entries, t.ListPreference_android_entries);
        this.i = a.f.d.d.g.d(obtainStyledAttributes, t.ListPreference_entryValues, t.ListPreference_android_entryValues);
        int i3 = t.ListPreference_useSimpleSummaryProvider;
        if (a.f.d.d.g.a(obtainStyledAttributes, i3, i3, false)) {
            setSummaryProvider(b.a());
        }
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, t.Preference, i, i2);
        this.k = a.f.d.d.g.b(obtainStyledAttributes2, t.Preference_summary, t.Preference_android_summary);
        obtainStyledAttributes2.recycle();
    }

    private int k() {
        return a(this.j);
    }

    public int a(String str) {
        CharSequence[] charSequenceArr;
        if (str == null || (charSequenceArr = this.i) == null) {
            return -1;
        }
        for (int length = charSequenceArr.length - 1; length >= 0; length--) {
            if (this.i[length].equals(str)) {
                return length;
            }
        }
        return -1;
    }

    public void b(String str) {
        boolean z = !TextUtils.equals(this.j, str);
        if (z || !this.l) {
            this.j = str;
            this.l = true;
            persistString(str);
            if (z) {
                notifyChanged();
            }
        }
    }

    public CharSequence[] g() {
        return this.h;
    }

    @Override // androidx.preference.Preference
    public CharSequence getSummary() {
        if (getSummaryProvider() != null) {
            return getSummaryProvider().a(this);
        }
        CharSequence h = h();
        CharSequence summary = super.getSummary();
        String str = this.k;
        if (str == null) {
            return summary;
        }
        Object[] objArr = new Object[1];
        if (h == null) {
            h = "";
        }
        objArr[0] = h;
        String format = String.format(str, objArr);
        if (TextUtils.equals(format, summary)) {
            return summary;
        }
        Log.w("ListPreference", "Setting a summary with a String formatting marker is no longer supported. You should use a SummaryProvider instead.");
        return format;
    }

    public CharSequence h() {
        CharSequence[] charSequenceArr;
        int k = k();
        if (k < 0 || (charSequenceArr = this.h) == null) {
            return null;
        }
        return charSequenceArr[k];
    }

    public CharSequence[] i() {
        return this.i;
    }

    public String j() {
        return this.j;
    }

    @Override // androidx.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        return typedArray.getString(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable != null && parcelable.getClass().equals(a.class)) {
            a aVar = (a) parcelable;
            super.onRestoreInstanceState(aVar.getSuperState());
            b(aVar.f1579b);
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
        aVar.f1579b = j();
        return aVar;
    }

    @Override // androidx.preference.Preference
    protected void onSetInitialValue(Object obj) {
        b(getPersistedString((String) obj));
    }

    @Override // androidx.preference.Preference
    public void setSummary(CharSequence charSequence) {
        super.setSummary(charSequence);
        if (charSequence == null && this.k != null) {
            this.k = null;
        } else {
            if (charSequence == null || charSequence.equals(this.k)) {
                return;
            }
            this.k = charSequence.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a extends Preference.b {
        public static final Parcelable.Creator<a> CREATOR = new C0054a();

        /* renamed from: b, reason: collision with root package name */
        String f1579b;

        /* renamed from: androidx.preference.ListPreference$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        static class C0054a implements Parcelable.Creator<a> {
            C0054a() {
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
            this.f1579b = parcel.readString();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.f1579b);
        }

        a(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public ListPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ListPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.f.d.d.g.a(context, m.dialogPreferenceStyle, R.attr.dialogPreferenceStyle));
    }
}
