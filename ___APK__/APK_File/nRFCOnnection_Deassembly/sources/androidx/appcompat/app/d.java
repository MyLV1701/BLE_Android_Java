package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AlertController;

/* loaded from: classes.dex */
public class d extends i implements DialogInterface {

    /* renamed from: d, reason: collision with root package name */
    final AlertController f662d;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private final AlertController.f f663a;

        /* renamed from: b, reason: collision with root package name */
        private final int f664b;

        public a(Context context) {
            this(context, d.a(context, 0));
        }

        public a a(View view) {
            this.f663a.g = view;
            return this;
        }

        public Context b() {
            return this.f663a.f628a;
        }

        public a c(int i) {
            AlertController.f fVar = this.f663a;
            fVar.f633f = fVar.f628a.getText(i);
            return this;
        }

        public a(Context context, int i) {
            this.f663a = new AlertController.f(new ContextThemeWrapper(context, d.a(context, i)));
            this.f664b = i;
        }

        public a a(CharSequence charSequence) {
            this.f663a.h = charSequence;
            return this;
        }

        public a b(CharSequence charSequence) {
            this.f663a.f633f = charSequence;
            return this;
        }

        public a c(int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f663a;
            fVar.i = fVar.f628a.getText(i);
            this.f663a.k = onClickListener;
            return this;
        }

        public a a(int i) {
            this.f663a.f630c = i;
            return this;
        }

        public a b(int i) {
            AlertController.f fVar = this.f663a;
            fVar.h = fVar.f628a.getText(i);
            return this;
        }

        public a a(Drawable drawable) {
            this.f663a.f631d = drawable;
            return this;
        }

        public a b(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f663a;
            fVar.i = charSequence;
            fVar.k = onClickListener;
            return this;
        }

        public d c() {
            d a2 = a();
            a2.show();
            return a2;
        }

        public a a(int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f663a;
            fVar.l = fVar.f628a.getText(i);
            this.f663a.n = onClickListener;
            return this;
        }

        public a b(int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f663a;
            fVar.o = fVar.f628a.getText(i);
            this.f663a.q = onClickListener;
            return this;
        }

        public a a(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f663a;
            fVar.l = charSequence;
            fVar.n = onClickListener;
            return this;
        }

        public a b(View view) {
            AlertController.f fVar = this.f663a;
            fVar.z = view;
            fVar.y = 0;
            fVar.E = false;
            return this;
        }

        public a a(boolean z) {
            this.f663a.r = z;
            return this;
        }

        public a a(DialogInterface.OnKeyListener onKeyListener) {
            this.f663a.u = onKeyListener;
            return this;
        }

        public a a(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f663a;
            fVar.w = listAdapter;
            fVar.x = onClickListener;
            return this;
        }

        public a a(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            AlertController.f fVar = this.f663a;
            fVar.v = charSequenceArr;
            fVar.J = onMultiChoiceClickListener;
            fVar.F = zArr;
            fVar.G = true;
            return this;
        }

        public a a(int i, int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f663a;
            fVar.v = fVar.f628a.getResources().getTextArray(i);
            AlertController.f fVar2 = this.f663a;
            fVar2.x = onClickListener;
            fVar2.I = i2;
            fVar2.H = true;
            return this;
        }

        public a a(CharSequence[] charSequenceArr, int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f663a;
            fVar.v = charSequenceArr;
            fVar.x = onClickListener;
            fVar.I = i;
            fVar.H = true;
            return this;
        }

        public a a(ListAdapter listAdapter, int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f663a;
            fVar.w = listAdapter;
            fVar.x = onClickListener;
            fVar.I = i;
            fVar.H = true;
            return this;
        }

        public d a() {
            d dVar = new d(this.f663a.f628a, this.f664b);
            this.f663a.a(dVar.f662d);
            dVar.setCancelable(this.f663a.r);
            if (this.f663a.r) {
                dVar.setCanceledOnTouchOutside(true);
            }
            dVar.setOnCancelListener(this.f663a.s);
            dVar.setOnDismissListener(this.f663a.t);
            DialogInterface.OnKeyListener onKeyListener = this.f663a.u;
            if (onKeyListener != null) {
                dVar.setOnKeyListener(onKeyListener);
            }
            return dVar;
        }
    }

    protected d(Context context, int i) {
        super(context, a(context, i));
        this.f662d = new AlertController(getContext(), this, getWindow());
    }

    static int a(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(a.a.a.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public Button b(int i) {
        return this.f662d.a(i);
    }

    @Override // androidx.appcompat.app.i, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f662d.b();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.f662d.a(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.f662d.b(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // androidx.appcompat.app.i, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f662d.b(charSequence);
    }

    public ListView b() {
        return this.f662d.a();
    }
}
