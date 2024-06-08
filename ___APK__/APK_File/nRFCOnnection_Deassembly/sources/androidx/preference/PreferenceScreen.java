package androidx.preference;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.j;

/* loaded from: classes.dex */
public final class PreferenceScreen extends PreferenceGroup {
    private boolean i;

    public PreferenceScreen(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, a.f.d.d.g.a(context, m.preferenceScreenStyle, R.attr.preferenceScreenStyle));
        this.i = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.PreferenceGroup
    public boolean d() {
        return false;
    }

    public boolean f() {
        return this.i;
    }

    @Override // androidx.preference.Preference
    protected void onClick() {
        j.b c2;
        if (getIntent() != null || getFragment() != null || c() == 0 || (c2 = getPreferenceManager().c()) == null) {
            return;
        }
        c2.onNavigateToScreen(this);
    }
}
