package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SwitchCompat;

/* loaded from: classes.dex */
public class SwitchPreferenceCompat extends TwoStatePreference {
    private final a g;
    private CharSequence h;
    private CharSequence i;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class a implements CompoundButton.OnCheckedChangeListener {
        a() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (!SwitchPreferenceCompat.this.callChangeListener(Boolean.valueOf(z))) {
                compoundButton.setChecked(!z);
            } else {
                SwitchPreferenceCompat.this.a(z);
            }
        }
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.g = new a();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.SwitchPreferenceCompat, i, i2);
        b(a.f.d.d.g.b(obtainStyledAttributes, t.SwitchPreferenceCompat_summaryOn, t.SwitchPreferenceCompat_android_summaryOn));
        a(a.f.d.d.g.b(obtainStyledAttributes, t.SwitchPreferenceCompat_summaryOff, t.SwitchPreferenceCompat_android_summaryOff));
        d(a.f.d.d.g.b(obtainStyledAttributes, t.SwitchPreferenceCompat_switchTextOn, t.SwitchPreferenceCompat_android_switchTextOn));
        c(a.f.d.d.g.b(obtainStyledAttributes, t.SwitchPreferenceCompat_switchTextOff, t.SwitchPreferenceCompat_android_switchTextOff));
        b(a.f.d.d.g.a(obtainStyledAttributes, t.SwitchPreferenceCompat_disableDependentsState, t.SwitchPreferenceCompat_android_disableDependentsState, false));
        obtainStyledAttributes.recycle();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b(View view) {
        boolean z = view instanceof SwitchCompat;
        if (z) {
            ((SwitchCompat) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.f1603b);
        }
        if (z) {
            SwitchCompat switchCompat = (SwitchCompat) view;
            switchCompat.setTextOn(this.h);
            switchCompat.setTextOff(this.i);
            switchCompat.setOnCheckedChangeListener(this.g);
        }
    }

    public void c(CharSequence charSequence) {
        this.i = charSequence;
        notifyChanged();
    }

    public void d(CharSequence charSequence) {
        this.h = charSequence;
        notifyChanged();
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(l lVar) {
        super.onBindViewHolder(lVar);
        b(lVar.a(p.switchWidget));
        a(lVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public void performClick(View view) {
        super.performClick(view);
        c(view);
    }

    private void c(View view) {
        if (((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
            b(view.findViewById(p.switchWidget));
            a(view.findViewById(R.id.summary));
        }
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, m.switchPreferenceCompatStyle);
    }
}
