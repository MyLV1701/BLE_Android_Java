package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;

/* loaded from: classes.dex */
public class CheckBoxPreference extends TwoStatePreference {
    private final a g;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class a implements CompoundButton.OnCheckedChangeListener {
        a() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (!CheckBoxPreference.this.callChangeListener(Boolean.valueOf(z))) {
                compoundButton.setChecked(!z);
            } else {
                CheckBoxPreference.this.a(z);
            }
        }
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b(View view) {
        boolean z = view instanceof CompoundButton;
        if (z) {
            ((CompoundButton) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.f1603b);
        }
        if (z) {
            ((CompoundButton) view).setOnCheckedChangeListener(this.g);
        }
    }

    private void c(View view) {
        if (((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
            b(view.findViewById(R.id.checkbox));
            a(view.findViewById(R.id.summary));
        }
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(l lVar) {
        super.onBindViewHolder(lVar);
        b(lVar.a(R.id.checkbox));
        a(lVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public void performClick(View view) {
        super.performClick(view);
        c(view);
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.g = new a();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.CheckBoxPreference, i, i2);
        b(a.f.d.d.g.b(obtainStyledAttributes, t.CheckBoxPreference_summaryOn, t.CheckBoxPreference_android_summaryOn));
        a(a.f.d.d.g.b(obtainStyledAttributes, t.CheckBoxPreference_summaryOff, t.CheckBoxPreference_android_summaryOff));
        b(a.f.d.d.g.a(obtainStyledAttributes, t.CheckBoxPreference_disableDependentsState, t.CheckBoxPreference_android_disableDependentsState, false));
        obtainStyledAttributes.recycle();
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.f.d.d.g.a(context, m.checkBoxPreferenceStyle, R.attr.checkBoxPreferenceStyle));
    }
}
