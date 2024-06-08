package c.a.a.a.x;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.widget.c;
import c.a.a.a.b;
import c.a.a.a.k;
import com.google.android.material.internal.l;

/* loaded from: classes.dex */
public class a extends AppCompatRadioButton {
    private static final int g = k.Widget_MaterialComponents_CompoundButton_RadioButton;
    private static final int[][] h = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};

    /* renamed from: e, reason: collision with root package name */
    private ColorStateList f2146e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f2147f;

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.radioButtonStyle);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.f2146e == null) {
            int a2 = c.a.a.a.s.a.a(this, b.colorControlActivated);
            int a3 = c.a.a.a.s.a.a(this, b.colorOnSurface);
            int a4 = c.a.a.a.s.a.a(this, b.colorSurface);
            int[] iArr = new int[h.length];
            iArr[0] = c.a.a.a.s.a.a(a4, a2, 1.0f);
            iArr[1] = c.a.a.a.s.a.a(a4, a3, 0.54f);
            iArr[2] = c.a.a.a.s.a.a(a4, a3, 0.38f);
            iArr[3] = c.a.a.a.s.a.a(a4, a3, 0.38f);
            this.f2146e = new ColorStateList(h, iArr);
        }
        return this.f2146e;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f2147f && c.b(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.f2147f = z;
        if (z) {
            c.a(this, getMaterialThemeColorsTintList());
        } else {
            c.a(this, (ColorStateList) null);
        }
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(com.google.android.material.theme.a.a.b(context, attributeSet, i, g), attributeSet, i);
        TypedArray c2 = l.c(getContext(), attributeSet, c.a.a.a.l.MaterialRadioButton, i, g, new int[0]);
        this.f2147f = c2.getBoolean(c.a.a.a.l.MaterialRadioButton_useMaterialThemeColors, false);
        c2.recycle();
    }
}
