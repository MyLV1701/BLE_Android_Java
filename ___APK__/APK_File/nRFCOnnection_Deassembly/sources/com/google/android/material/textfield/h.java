package com.google.android.material.textfield;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import c.a.a.a.j;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class h extends e {

    /* renamed from: d, reason: collision with root package name */
    private final TextWatcher f2767d;

    /* renamed from: e, reason: collision with root package name */
    private final TextInputLayout.f f2768e;

    /* renamed from: f, reason: collision with root package name */
    private final TextInputLayout.g f2769f;

    /* loaded from: classes.dex */
    class a implements TextWatcher {
        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            h.this.f2755c.setChecked(!r1.c());
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* loaded from: classes.dex */
    class b implements TextInputLayout.f {
        b() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.f
        public void a(TextInputLayout textInputLayout) {
            EditText editText = textInputLayout.getEditText();
            textInputLayout.setEndIconVisible(true);
            h.this.f2755c.setChecked(!r4.c());
            editText.removeTextChangedListener(h.this.f2767d);
            editText.addTextChangedListener(h.this.f2767d);
        }
    }

    /* loaded from: classes.dex */
    class c implements TextInputLayout.g {
        c(h hVar) {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.g
        public void a(TextInputLayout textInputLayout, int i) {
            EditText editText = textInputLayout.getEditText();
            if (editText == null || i != 1) {
                return;
            }
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    /* loaded from: classes.dex */
    class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EditText editText = h.this.f2753a.getEditText();
            if (editText == null) {
                return;
            }
            int selectionEnd = editText.getSelectionEnd();
            if (h.this.c()) {
                editText.setTransformationMethod(null);
            } else {
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            editText.setSelection(selectionEnd);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.f2767d = new a();
        this.f2768e = new b();
        this.f2769f = new c(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        EditText editText = this.f2753a.getEditText();
        return editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.textfield.e
    public void a() {
        this.f2753a.setEndIconDrawable(a.a.k.a.a.c(this.f2754b, c.a.a.a.e.design_password_eye));
        TextInputLayout textInputLayout = this.f2753a;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(j.password_toggle_content_description));
        this.f2753a.setEndIconOnClickListener(new d());
        this.f2753a.a(this.f2768e);
        this.f2753a.a(this.f2769f);
    }
}
