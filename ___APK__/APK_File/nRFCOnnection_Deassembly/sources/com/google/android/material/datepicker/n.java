package com.google.android.material.datepicker;

import android.content.Context;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class n extends LinearLayoutManager {

    /* loaded from: classes.dex */
    class a extends androidx.recyclerview.widget.n {
        a(n nVar, Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.n
        protected float a(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(Context context, int i, boolean z) {
        super(context, i, z);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public void a(RecyclerView recyclerView, RecyclerView.a0 a0Var, int i) {
        a aVar = new a(this, recyclerView.getContext());
        aVar.c(i);
        b(aVar);
    }
}
