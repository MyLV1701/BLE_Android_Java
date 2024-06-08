package androidx.appcompat.widget;

import a.f.j.c;
import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* loaded from: classes.dex */
public class AppCompatTextView extends TextView implements a.f.l.v, androidx.core.widget.k, androidx.core.widget.b {

    /* renamed from: b, reason: collision with root package name */
    private final e f874b;

    /* renamed from: c, reason: collision with root package name */
    private final v f875c;

    /* renamed from: d, reason: collision with root package name */
    private final u f876d;

    /* renamed from: e, reason: collision with root package name */
    private Future<a.f.j.c> f877e;

    public AppCompatTextView(Context context) {
        this(context, null);
    }

    private void d() {
        Future<a.f.j.c> future = this.f877e;
        if (future != null) {
            try {
                this.f877e = null;
                androidx.core.widget.i.a(this, future.get());
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        e eVar = this.f874b;
        if (eVar != null) {
            eVar.a();
        }
        v vVar = this.f875c;
        if (vVar != null) {
            vVar.a();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (androidx.core.widget.b.f1314a) {
            return super.getAutoSizeMaxTextSize();
        }
        v vVar = this.f875c;
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
        v vVar = this.f875c;
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
        v vVar = this.f875c;
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
        v vVar = this.f875c;
        return vVar != null ? vVar.f() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (androidx.core.widget.b.f1314a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        v vVar = this.f875c;
        if (vVar != null) {
            return vVar.g();
        }
        return 0;
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return androidx.core.widget.i.b(this);
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return androidx.core.widget.i.c(this);
    }

    @Override // a.f.l.v
    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.f874b;
        if (eVar != null) {
            return eVar.b();
        }
        return null;
    }

    @Override // a.f.l.v
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.f874b;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f875c.h();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f875c.i();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        d();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        u uVar;
        if (Build.VERSION.SDK_INT < 28 && (uVar = this.f876d) != null) {
            return uVar.a();
        }
        return super.getTextClassifier();
    }

    public c.a getTextMetricsParamsCompat() {
        return androidx.core.widget.i.f(this);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        l.a(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        v vVar = this.f875c;
        if (vVar != null) {
            vVar.a(z, i, i2, i3, i4);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        d();
        super.onMeasure(i, i2);
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        v vVar = this.f875c;
        if (vVar == null || androidx.core.widget.b.f1314a || !vVar.j()) {
            return;
        }
        this.f875c.b();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (androidx.core.widget.b.f1314a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        v vVar = this.f875c;
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
        v vVar = this.f875c;
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
        v vVar = this.f875c;
        if (vVar != null) {
            vVar.a(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.f874b;
        if (eVar != null) {
            eVar.a(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        e eVar = this.f874b;
        if (eVar != null) {
            eVar.a(i);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        v vVar = this.f875c;
        if (vVar != null) {
            vVar.k();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        v vVar = this.f875c;
        if (vVar != null) {
            vVar.k();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        v vVar = this.f875c;
        if (vVar != null) {
            vVar.k();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        v vVar = this.f875c;
        if (vVar != null) {
            vVar.k();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.i.a(this, callback));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i);
        } else {
            androidx.core.widget.i.a(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i);
        } else {
            androidx.core.widget.i.b(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i) {
        androidx.core.widget.i.c(this, i);
    }

    public void setPrecomputedText(a.f.j.c cVar) {
        androidx.core.widget.i.a(this, cVar);
    }

    @Override // a.f.l.v
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e eVar = this.f874b;
        if (eVar != null) {
            eVar.b(colorStateList);
        }
    }

    @Override // a.f.l.v
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e eVar = this.f874b;
        if (eVar != null) {
            eVar.a(mode);
        }
    }

    @Override // androidx.core.widget.k
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.f875c.a(colorStateList);
        this.f875c.a();
    }

    @Override // androidx.core.widget.k
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.f875c.a(mode);
        this.f875c.a();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        v vVar = this.f875c;
        if (vVar != null) {
            vVar.a(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        u uVar;
        if (Build.VERSION.SDK_INT < 28 && (uVar = this.f876d) != null) {
            uVar.a(textClassifier);
        } else {
            super.setTextClassifier(textClassifier);
        }
    }

    public void setTextFuture(Future<a.f.j.c> future) {
        this.f877e = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(c.a aVar) {
        androidx.core.widget.i.a(this, aVar);
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f2) {
        if (androidx.core.widget.b.f1314a) {
            super.setTextSize(i, f2);
            return;
        }
        v vVar = this.f875c;
        if (vVar != null) {
            vVar.a(i, f2);
        }
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface, int i) {
        Typeface a2 = (typeface == null || i <= 0) ? null : a.f.e.c.a(getContext(), typeface, i);
        if (a2 != null) {
            typeface = a2;
        }
        super.setTypeface(typeface, i);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet, int i) {
        super(q0.b(context), attributeSet, i);
        this.f874b = new e(this);
        this.f874b.a(attributeSet, i);
        this.f875c = new v(this);
        this.f875c.a(attributeSet, i);
        this.f875c.a();
        this.f876d = new u(this);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i != 0 ? a.a.k.a.a.c(context, i) : null, i2 != 0 ? a.a.k.a.a.c(context, i2) : null, i3 != 0 ? a.a.k.a.a.c(context, i3) : null, i4 != 0 ? a.a.k.a.a.c(context, i4) : null);
        v vVar = this.f875c;
        if (vVar != null) {
            vVar.k();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i != 0 ? a.a.k.a.a.c(context, i) : null, i2 != 0 ? a.a.k.a.a.c(context, i2) : null, i3 != 0 ? a.a.k.a.a.c(context, i3) : null, i4 != 0 ? a.a.k.a.a.c(context, i4) : null);
        v vVar = this.f875c;
        if (vVar != null) {
            vVar.k();
        }
    }
}
