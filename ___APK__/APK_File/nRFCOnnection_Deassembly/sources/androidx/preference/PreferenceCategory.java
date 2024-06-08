package androidx.preference;

import a.f.l.f0.c;
import android.R;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/* loaded from: classes.dex */
public class PreferenceCategory extends PreferenceGroup {
    public PreferenceCategory(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // androidx.preference.Preference
    public boolean isEnabled() {
        return false;
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(l lVar) {
        TextView textView;
        super.onBindViewHolder(lVar);
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            lVar.itemView.setAccessibilityHeading(true);
            return;
        }
        if (i < 21) {
            TypedValue typedValue = new TypedValue();
            if (getContext().getTheme().resolveAttribute(m.colorAccent, typedValue, true) && (textView = (TextView) lVar.a(R.id.title)) != null) {
                if (textView.getCurrentTextColor() != a.f.d.b.a(getContext(), n.preference_fallback_accent_color)) {
                    return;
                }
                textView.setTextColor(typedValue.data);
            }
        }
    }

    @Override // androidx.preference.Preference
    @Deprecated
    public void onInitializeAccessibilityNodeInfo(a.f.l.f0.c cVar) {
        c.C0018c e2;
        super.onInitializeAccessibilityNodeInfo(cVar);
        if (Build.VERSION.SDK_INT >= 28 || (e2 = cVar.e()) == null) {
            return;
        }
        cVar.b(c.C0018c.a(e2.c(), e2.d(), e2.a(), e2.b(), true, e2.e()));
    }

    @Override // androidx.preference.Preference
    public boolean shouldDisableDependents() {
        return !super.isEnabled();
    }

    public PreferenceCategory(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public PreferenceCategory(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.f.d.d.g.a(context, m.preferenceCategoryStyle, R.attr.preferenceCategoryStyle));
    }
}
