package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ToggleButton;

/* loaded from: classes.dex */
public class x extends ToggleButton {

    /* renamed from: b, reason: collision with root package name */
    private final v f1131b;

    public x(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.buttonStyleToggle);
    }

    public x(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1131b = new v(this);
        this.f1131b.a(attributeSet, i);
    }
}
