package com.google.android.material.textview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.Filterable;
import android.widget.ListAdapter;
import androidx.appcompat.widget.d;
import androidx.appcompat.widget.f0;
import c.a.a.a.b;
import com.google.android.material.textfield.TextInputLayout;

/* loaded from: classes.dex */
public class a extends d {

    /* renamed from: e, reason: collision with root package name */
    private final f0 f2773e;

    /* renamed from: f, reason: collision with root package name */
    private final AccessibilityManager f2774f;

    /* renamed from: com.google.android.material.textview.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0093a implements AdapterView.OnItemClickListener {
        C0093a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            a.this.a(i < 0 ? a.this.f2773e.j() : a.this.getAdapter().getItem(i));
            AdapterView.OnItemClickListener onItemClickListener = a.this.getOnItemClickListener();
            if (onItemClickListener != null) {
                if (view == null || i < 0) {
                    view = a.this.f2773e.m();
                    i = a.this.f2773e.l();
                    j = a.this.f2773e.k();
                }
                onItemClickListener.onItemClick(a.this.f2773e.f(), view, i, j);
            }
            a.this.f2773e.dismiss();
        }
    }

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.autoCompleteTextViewStyle);
    }

    @Override // android.widget.TextView
    public CharSequence getHint() {
        TextInputLayout a2 = a();
        if (a2 != null && a2.d()) {
            return a2.getHint();
        }
        return super.getHint();
    }

    @Override // android.widget.AutoCompleteTextView
    public <T extends ListAdapter & Filterable> void setAdapter(T t) {
        super.setAdapter(t);
        this.f2773e.a(getAdapter());
    }

    @Override // android.widget.AutoCompleteTextView
    public void showDropDown() {
        AccessibilityManager accessibilityManager;
        if (getInputType() == 0 && (accessibilityManager = this.f2774f) != null && accessibilityManager.isTouchExplorationEnabled()) {
            this.f2773e.d();
        } else {
            super.showDropDown();
        }
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2774f = (AccessibilityManager) context.getSystemService("accessibility");
        this.f2773e = new f0(getContext());
        this.f2773e.a(true);
        this.f2773e.a(this);
        this.f2773e.g(2);
        this.f2773e.a(getAdapter());
        this.f2773e.a(new C0093a());
    }

    private TextInputLayout a() {
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof TextInputLayout) {
                return (TextInputLayout) parent;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T extends ListAdapter & Filterable> void a(Object obj) {
        if (Build.VERSION.SDK_INT >= 17) {
            setText(convertSelectionToString(obj), false);
            return;
        }
        ListAdapter adapter = getAdapter();
        setAdapter(null);
        setText(convertSelectionToString(obj));
        setAdapter(adapter);
    }
}
