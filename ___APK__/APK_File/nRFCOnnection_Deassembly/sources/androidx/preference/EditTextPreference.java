package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;
import androidx.preference.Preference;

/* loaded from: classes.dex */
public class EditTextPreference extends DialogPreference {
    private String h;
    private a i;

    /* loaded from: classes.dex */
    public interface a {
        void a(EditText editText);
    }

    /* loaded from: classes.dex */
    public static final class c implements Preference.g<EditTextPreference> {

        /* renamed from: a, reason: collision with root package name */
        private static c f1578a;

        private c() {
        }

        public static c a() {
            if (f1578a == null) {
                f1578a = new c();
            }
            return f1578a;
        }

        @Override // androidx.preference.Preference.g
        public CharSequence a(EditTextPreference editTextPreference) {
            if (TextUtils.isEmpty(editTextPreference.h())) {
                return editTextPreference.getContext().getString(r.not_set);
            }
            return editTextPreference.h();
        }
    }

    public EditTextPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.EditTextPreference, i, i2);
        int i3 = t.EditTextPreference_useSimpleSummaryProvider;
        if (a.f.d.d.g.a(obtainStyledAttributes, i3, i3, false)) {
            setSummaryProvider(c.a());
        }
        obtainStyledAttributes.recycle();
    }

    public void a(String str) {
        boolean shouldDisableDependents = shouldDisableDependents();
        this.h = str;
        persistString(str);
        boolean shouldDisableDependents2 = shouldDisableDependents();
        if (shouldDisableDependents2 != shouldDisableDependents) {
            notifyDependencyChange(shouldDisableDependents2);
        }
        notifyChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a g() {
        return this.i;
    }

    public String h() {
        return this.h;
    }

    @Override // androidx.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        return typedArray.getString(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable != null && parcelable.getClass().equals(b.class)) {
            b bVar = (b) parcelable;
            super.onRestoreInstanceState(bVar.getSuperState());
            a(bVar.f1577b);
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
        b bVar = new b(onSaveInstanceState);
        bVar.f1577b = h();
        return bVar;
    }

    @Override // androidx.preference.Preference
    protected void onSetInitialValue(Object obj) {
        a(getPersistedString((String) obj));
    }

    @Override // androidx.preference.Preference
    public boolean shouldDisableDependents() {
        return TextUtils.isEmpty(this.h) || super.shouldDisableDependents();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b extends Preference.b {
        public static final Parcelable.Creator<b> CREATOR = new a();

        /* renamed from: b, reason: collision with root package name */
        String f1577b;

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

        b(Parcel parcel) {
            super(parcel);
            this.f1577b = parcel.readString();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.f1577b);
        }

        b(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public EditTextPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public EditTextPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.f.d.d.g.a(context, m.editTextPreferenceStyle, R.attr.editTextPreferenceStyle));
    }
}
