package no.nordicsemi.android.mcp.widget.advertiser;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.SwitchCompat;

/* loaded from: classes.dex */
public class DelayedSwitch extends SwitchCompat {
    public DelayedSwitch(Context context) {
        super(context);
    }

    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
    }

    public DelayedSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DelayedSwitch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
