package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

/* loaded from: classes.dex */
public class AppCompatRadioButton extends RadioButton implements androidx.core.widget.j, a.f.l.v {

    /* renamed from: b, reason: collision with root package name */
    private final i f859b;

    /* renamed from: c, reason: collision with root package name */
    private final e f860c;

    /* renamed from: d, reason: collision with root package name */
    private final v f861d;

    public AppCompatRadioButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        e eVar = this.f860c;
        if (eVar != null) {
            eVar.a();
        }
        v vVar = this.f861d;
        if (vVar != null) {
            vVar.a();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        i iVar = this.f859b;
        return iVar != null ? iVar.a(compoundPaddingLeft) : compoundPaddingLeft;
    }

    @Override // a.f.l.v
    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.f860c;
        if (eVar != null) {
            return eVar.b();
        }
        return null;
    }

    @Override // a.f.l.v
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.f860c;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    @Override // androidx.core.widget.j
    public ColorStateList getSupportButtonTintList() {
        i iVar = this.f859b;
        if (iVar != null) {
            return iVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        i iVar = this.f859b;
        if (iVar != null) {
            return iVar.c();
        }
        return null;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.f860c;
        if (eVar != null) {
            eVar.a(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        e eVar = this.f860c;
        if (eVar != null) {
            eVar.a(i);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        i iVar = this.f859b;
        if (iVar != null) {
            iVar.d();
        }
    }

    @Override // a.f.l.v
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e eVar = this.f860c;
        if (eVar != null) {
            eVar.b(colorStateList);
        }
    }

    @Override // a.f.l.v
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e eVar = this.f860c;
        if (eVar != null) {
            eVar.a(mode);
        }
    }

    @Override // androidx.core.widget.j
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        i iVar = this.f859b;
        if (iVar != null) {
            iVar.a(colorStateList);
        }
    }

    @Override // androidx.core.widget.j
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        i iVar = this.f859b;
        if (iVar != null) {
            iVar.a(mode);
        }
    }

    public AppCompatRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.a.a.radioButtonStyle);
    }

    public AppCompatRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(q0.b(context), attributeSet, i);
        this.f859b = new i(this);
        this.f859b.a(attributeSet, i);
        this.f860c = new e(this);
        this.f860c.a(attributeSet, i);
        this.f861d = new v(this);
        this.f861d.a(attributeSet, i);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(a.a.k.a.a.c(getContext(), i));
    }
}
