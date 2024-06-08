package no.nordicsemi.android.mcp.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatSpinner;

/* loaded from: classes.dex */
public class ClosableSpinner extends AppCompatSpinner {
    public ClosableSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void close() {
        super.onDetachedFromWindow();
    }
}
