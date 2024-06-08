package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AutoCompleteTextView;

/* loaded from: classes.dex */
public class d extends AutoCompleteTextView implements a.f.l.v {

    /* renamed from: d, reason: collision with root package name */
    private static final int[] f956d = {R.attr.popupBackground};

    /* renamed from: b, reason: collision with root package name */
    private final e f957b;

    /* renamed from: c, reason: collision with root package name */
    private final v f958c;

    public d(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        e eVar = this.f957b;
        if (eVar != null) {
            eVar.a();
        }
        v vVar = this.f958c;
        if (vVar != null) {
            vVar.a();
        }
    }

    @Override // a.f.l.v
    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.f957b;
        if (eVar != null) {
            return eVar.b();
        }
        return null;
    }

    @Override // a.f.l.v
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.f957b;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        l.a(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.f957b;
        if (eVar != null) {
            eVar.a(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        e eVar = this.f957b;
        if (eVar != null) {
            eVar.a(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.i.a(this, callback));
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i) {
        setDropDownBackgroundDrawable(a.a.k.a.a.c(getContext(), i));
    }

    @Override // a.f.l.v
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e eVar = this.f957b;
        if (eVar != null) {
            eVar.b(colorStateList);
        }
    }

    @Override // a.f.l.v
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e eVar = this.f957b;
        if (eVar != null) {
            eVar.a(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        v vVar = this.f958c;
        if (vVar != null) {
            vVar.a(context, i);
        }
    }

    public d(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.a.a.autoCompleteTextViewStyle);
    }

    public d(Context context, AttributeSet attributeSet, int i) {
        super(q0.b(context), attributeSet, i);
        t0 a2 = t0.a(getContext(), attributeSet, f956d, i, 0);
        if (a2.g(0)) {
            setDropDownBackgroundDrawable(a2.b(0));
        }
        a2.a();
        this.f957b = new e(this);
        this.f957b.a(attributeSet, i);
        this.f958c = new v(this);
        this.f958c.a(attributeSet, i);
        this.f958c.a();
    }
}
