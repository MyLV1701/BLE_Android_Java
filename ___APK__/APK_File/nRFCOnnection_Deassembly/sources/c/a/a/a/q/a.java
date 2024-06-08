package c.a.a.a.q;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.g;
import androidx.core.widget.c;
import c.a.a.a.b;
import c.a.a.a.k;
import com.google.android.material.internal.l;

/* loaded from: classes.dex */
public class a extends g {
    private static final int g = k.Widget_MaterialComponents_CompoundButton_CheckBox;
    private static final int[][] h = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};

    /* renamed from: e, reason: collision with root package name */
    private ColorStateList f2127e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f2128f;

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.checkboxStyle);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.f2127e == null) {
            int[] iArr = new int[h.length];
            int a2 = c.a.a.a.s.a.a(this, b.colorControlActivated);
            int a3 = c.a.a.a.s.a.a(this, b.colorSurface);
            int a4 = c.a.a.a.s.a.a(this, b.colorOnSurface);
            iArr[0] = c.a.a.a.s.a.a(a3, a2, 1.0f);
            iArr[1] = c.a.a.a.s.a.a(a3, a4, 0.54f);
            iArr[2] = c.a.a.a.s.a.a(a3, a4, 0.38f);
            iArr[3] = c.a.a.a.s.a.a(a3, a4, 0.38f);
            this.f2127e = new ColorStateList(h, iArr);
        }
        return this.f2127e;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f2128f && c.b(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.f2128f = z;
        if (z) {
            c.a(this, getMaterialThemeColorsTintList());
        } else {
            c.a(this, (ColorStateList) null);
        }
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(com.google.android.material.theme.a.a.b(context, attributeSet, i, g), attributeSet, i);
        Context context2 = getContext();
        TypedArray c2 = l.c(context2, attributeSet, c.a.a.a.l.MaterialCheckBox, i, g, new int[0]);
        if (c2.hasValue(c.a.a.a.l.MaterialCheckBox_buttonTint)) {
            c.a(this, c.a.a.a.y.c.a(context2, c2, c.a.a.a.l.MaterialCheckBox_buttonTint));
        }
        this.f2128f = c2.getBoolean(c.a.a.a.l.MaterialCheckBox_useMaterialThemeColors, false);
        c2.recycle();
    }
}
