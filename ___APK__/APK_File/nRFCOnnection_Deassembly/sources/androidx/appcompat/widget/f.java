package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;

/* loaded from: classes.dex */
public class f extends Button implements a.f.l.v, androidx.core.widget.b {

    /* renamed from: b, reason: collision with root package name */
    private final e f979b;

    /* renamed from: c, reason: collision with root package name */
    private final v f980c;

    public f(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        e eVar = this.f979b;
        if (eVar != null) {
            eVar.a();
        }
        v vVar = this.f980c;
        if (vVar != null) {
            vVar.a();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (androidx.core.widget.b.f1314a) {
            return super.getAutoSizeMaxTextSize();
        }
        v vVar = this.f980c;
        if (vVar != null) {
            return vVar.c();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (androidx.core.widget.b.f1314a) {
            return super.getAutoSizeMinTextSize();
        }
        v vVar = this.f980c;
        if (vVar != null) {
            return vVar.d();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (androidx.core.widget.b.f1314a) {
            return super.getAutoSizeStepGranularity();
        }
        v vVar = this.f980c;
        if (vVar != null) {
            return vVar.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (androidx.core.widget.b.f1314a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        v vVar = this.f980c;
        return vVar != null ? vVar.f() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (androidx.core.widget.b.f1314a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        v vVar = this.f980c;
        if (vVar != null) {
            return vVar.g();
        }
        return 0;
    }

    @Override // a.f.l.v
    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.f979b;
        if (eVar != null) {
            return eVar.b();
        }
        return null;
    }

    @Override // a.f.l.v
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.f979b;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        v vVar = this.f980c;
        if (vVar != null) {
            vVar.a(z, i, i2, i3, i4);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        v vVar = this.f980c;
        if (vVar == null || androidx.core.widget.b.f1314a || !vVar.j()) {
            return;
        }
        this.f980c.b();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (androidx.core.widget.b.f1314a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        v vVar = this.f980c;
        if (vVar != null) {
            vVar.a(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        if (androidx.core.widget.b.f1314a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        v vVar = this.f980c;
        if (vVar != null) {
            vVar.a(iArr, i);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (androidx.core.widget.b.f1314a) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        v vVar = this.f980c;
        if (vVar != null) {
            vVar.a(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.f979b;
        if (eVar != null) {
            eVar.a(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        e eVar = this.f979b;
        if (eVar != null) {
            eVar.a(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.i.a(this, callback));
    }

    public void setSupportAllCaps(boolean z) {
        v vVar = this.f980c;
        if (vVar != null) {
            vVar.a(z);
        }
    }

    @Override // a.f.l.v
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e eVar = this.f979b;
        if (eVar != null) {
            eVar.b(colorStateList);
        }
    }

    @Override // a.f.l.v
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e eVar = this.f979b;
        if (eVar != null) {
            eVar.a(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        v vVar = this.f980c;
        if (vVar != null) {
            vVar.a(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f2) {
        if (androidx.core.widget.b.f1314a) {
            super.setTextSize(i, f2);
            return;
        }
        v vVar = this.f980c;
        if (vVar != null) {
            vVar.a(i, f2);
        }
    }

    public f(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.a.a.buttonStyle);
    }

    public f(Context context, AttributeSet attributeSet, int i) {
        super(q0.b(context), attributeSet, i);
        this.f979b = new e(this);
        this.f979b.a(attributeSet, i);
        this.f980c = new v(this);
        this.f980c.a(attributeSet, i);
        this.f980c.a();
    }
}
