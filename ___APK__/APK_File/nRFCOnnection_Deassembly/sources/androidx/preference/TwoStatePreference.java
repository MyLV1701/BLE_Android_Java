package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.preference.Preference;

/* loaded from: classes.dex */
public abstract class TwoStatePreference extends Preference {

    /* renamed from: b, reason: collision with root package name */
    protected boolean f1603b;

    /* renamed from: c, reason: collision with root package name */
    private CharSequence f1604c;

    /* renamed from: d, reason: collision with root package name */
    private CharSequence f1605d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f1606e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f1607f;

    public TwoStatePreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public boolean a() {
        return this.f1603b;
    }

    public void b(CharSequence charSequence) {
        this.f1604c = charSequence;
        if (a()) {
            notifyChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public void onClick() {
        super.onClick();
        boolean z = !a();
        if (callChangeListener(Boolean.valueOf(z))) {
            a(z);
        }
    }

    @Override // androidx.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        return Boolean.valueOf(typedArray.getBoolean(i, false));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable != null && parcelable.getClass().equals(a.class)) {
            a aVar = (a) parcelable;
            super.onRestoreInstanceState(aVar.getSuperState());
            a(aVar.f1608b);
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
        aVar.f1608b = a();
        return aVar;
    }

    @Override // androidx.preference.Preference
    protected void onSetInitialValue(Object obj) {
        if (obj == null) {
            obj = false;
        }
        a(getPersistedBoolean(((Boolean) obj).booleanValue()));
    }

    @Override // androidx.preference.Preference
    public boolean shouldDisableDependents() {
        return (this.f1607f ? this.f1603b : !this.f1603b) || super.shouldDisableDependents();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends Preference.b {
        public static final Parcelable.Creator<a> CREATOR = new C0056a();

        /* renamed from: b, reason: collision with root package name */
        boolean f1608b;

        /* renamed from: androidx.preference.TwoStatePreference$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        static class C0056a implements Parcelable.Creator<a> {
            C0056a() {
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
            this.f1608b = parcel.readInt() == 1;
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1608b ? 1 : 0);
        }

        a(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public TwoStatePreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public void a(boolean z) {
        boolean z2 = this.f1603b != z;
        if (z2 || !this.f1606e) {
            this.f1603b = z;
            this.f1606e = true;
            persistBoolean(z);
            if (z2) {
                notifyDependencyChange(shouldDisableDependents());
                notifyChanged();
            }
        }
    }

    public TwoStatePreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void b(boolean z) {
        this.f1607f = z;
    }

    public void a(CharSequence charSequence) {
        this.f1605d = charSequence;
        if (a()) {
            return;
        }
        notifyChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(l lVar) {
        a(lVar.a(R.id.summary));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(android.view.View r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof android.widget.TextView
            if (r0 != 0) goto L5
            return
        L5:
            android.widget.TextView r5 = (android.widget.TextView) r5
            r0 = 1
            boolean r1 = r4.f1603b
            r2 = 0
            if (r1 == 0) goto L1c
            java.lang.CharSequence r1 = r4.f1604c
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L1c
            java.lang.CharSequence r0 = r4.f1604c
            r5.setText(r0)
        L1a:
            r0 = 0
            goto L2e
        L1c:
            boolean r1 = r4.f1603b
            if (r1 != 0) goto L2e
            java.lang.CharSequence r1 = r4.f1605d
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L2e
            java.lang.CharSequence r0 = r4.f1605d
            r5.setText(r0)
            goto L1a
        L2e:
            if (r0 == 0) goto L3e
            java.lang.CharSequence r1 = r4.getSummary()
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 != 0) goto L3e
            r5.setText(r1)
            r0 = 0
        L3e:
            r1 = 8
            if (r0 != 0) goto L43
            r1 = 0
        L43:
            int r0 = r5.getVisibility()
            if (r1 == r0) goto L4c
            r5.setVisibility(r1)
        L4c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.TwoStatePreference.a(android.view.View):void");
    }
}
