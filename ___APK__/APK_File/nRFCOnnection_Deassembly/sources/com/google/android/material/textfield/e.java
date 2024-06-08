package com.google.android.material.textfield;

import android.content.Context;
import com.google.android.material.internal.CheckableImageButton;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class e {

    /* renamed from: a, reason: collision with root package name */
    TextInputLayout f2753a;

    /* renamed from: b, reason: collision with root package name */
    Context f2754b;

    /* renamed from: c, reason: collision with root package name */
    CheckableImageButton f2755c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(TextInputLayout textInputLayout) {
        this.f2753a = textInputLayout;
        this.f2754b = textInputLayout.getContext();
        this.f2755c = textInputLayout.getEndIconView();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(int i) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b() {
        return false;
    }
}
