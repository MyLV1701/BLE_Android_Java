package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.Switch;

/* loaded from: classes.dex */
public class SwitchPreference extends TwoStatePreference {
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
            if (!SwitchPreference.this.callChangeListener(Boolean.valueOf(z))) {
                compoundButton.setChecked(!z);
            } else {
                SwitchPreference.this.a(z);
            }
        }
    }

    public SwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.g = new a();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.SwitchPreference, i, i2);
        b(a.f.d.d.g.b(obtainStyledAttributes, t.SwitchPreference_summaryOn, t.SwitchPreference_android_summaryOn));
        a(a.f.d.d.g.b(obtainStyledAttributes, t.SwitchPreference_summaryOff, t.SwitchPreference_android_summaryOff));
        d(a.f.d.d.g.b(obtainStyledAttributes, t.SwitchPreference_switchTextOn, t.SwitchPreference_android_switchTextOn));
        c(a.f.d.d.g.b(obtainStyledAttributes, t.SwitchPreference_switchTextOff, t.SwitchPreference_android_switchTextOff));
        b(a.f.d.d.g.a(obtainStyledAttributes, t.SwitchPreference_disableDependentsState, t.SwitchPreference_android_disableDependentsState, false));
        obtainStyledAttributes.recycle();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b(View view) {
        boolean z = view instanceof Switch;
        if (z) {
            ((Switch) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.f1603b);
        }
        if (z) {
            Switch r4 = (Switch) view;
            r4.setTextOn(this.h);
            r4.setTextOff(this.i);
            r4.setOnCheckedChangeListener(this.g);
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
        b(lVar.a(R.id.switch_widget));
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
            b(view.findViewById(R.id.switch_widget));
            a(view.findViewById(R.id.summary));
        }
    }

    public SwitchPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SwitchPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.f.d.d.g.a(context, m.switchPreferenceStyle, R.attr.switchPreferenceStyle));
    }
}
