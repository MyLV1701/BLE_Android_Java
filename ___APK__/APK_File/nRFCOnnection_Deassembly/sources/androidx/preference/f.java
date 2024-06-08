package androidx.preference;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.d;
import androidx.lifecycle.x;
import androidx.preference.DialogPreference;

/* loaded from: classes.dex */
public abstract class f extends androidx.fragment.app.c implements DialogInterface.OnClickListener {

    /* renamed from: b, reason: collision with root package name */
    private DialogPreference f1612b;

    /* renamed from: c, reason: collision with root package name */
    private CharSequence f1613c;

    /* renamed from: d, reason: collision with root package name */
    private CharSequence f1614d;

    /* renamed from: e, reason: collision with root package name */
    private CharSequence f1615e;

    /* renamed from: f, reason: collision with root package name */
    private CharSequence f1616f;
    private int g;
    private BitmapDrawable h;
    private int i;

    public DialogPreference a() {
        if (this.f1612b == null) {
            this.f1612b = (DialogPreference) ((DialogPreference.a) getTargetFragment()).findPreference(getArguments().getString("key"));
        }
        return this.f1612b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(d.a aVar) {
    }

    public abstract void a(boolean z);

    protected boolean b() {
        return false;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.i = i;
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        x targetFragment = getTargetFragment();
        if (targetFragment instanceof DialogPreference.a) {
            DialogPreference.a aVar = (DialogPreference.a) targetFragment;
            String string = getArguments().getString("key");
            if (bundle == null) {
                this.f1612b = (DialogPreference) aVar.findPreference(string);
                this.f1613c = this.f1612b.d();
                this.f1614d = this.f1612b.f();
                this.f1615e = this.f1612b.e();
                this.f1616f = this.f1612b.c();
                this.g = this.f1612b.b();
                Drawable a2 = this.f1612b.a();
                if (a2 != null && !(a2 instanceof BitmapDrawable)) {
                    Bitmap createBitmap = Bitmap.createBitmap(a2.getIntrinsicWidth(), a2.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(createBitmap);
                    a2.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                    a2.draw(canvas);
                    this.h = new BitmapDrawable(getResources(), createBitmap);
                    return;
                }
                this.h = (BitmapDrawable) a2;
                return;
            }
            this.f1613c = bundle.getCharSequence("PreferenceDialogFragment.title");
            this.f1614d = bundle.getCharSequence("PreferenceDialogFragment.positiveText");
            this.f1615e = bundle.getCharSequence("PreferenceDialogFragment.negativeText");
            this.f1616f = bundle.getCharSequence("PreferenceDialogFragment.message");
            this.g = bundle.getInt("PreferenceDialogFragment.layout", 0);
            Bitmap bitmap = (Bitmap) bundle.getParcelable("PreferenceDialogFragment.icon");
            if (bitmap != null) {
                this.h = new BitmapDrawable(getResources(), bitmap);
                return;
            }
            return;
        }
        throw new IllegalStateException("Target fragment must implement TargetFragment interface");
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        androidx.fragment.app.d activity = getActivity();
        this.i = -2;
        d.a aVar = new d.a(activity);
        aVar.b(this.f1613c);
        aVar.a(this.h);
        aVar.b(this.f1614d, this);
        aVar.a(this.f1615e, this);
        View a2 = a(activity);
        if (a2 != null) {
            a(a2);
            aVar.b(a2);
        } else {
            aVar.a(this.f1616f);
        }
        a(aVar);
        androidx.appcompat.app.d a3 = aVar.a();
        if (b()) {
            a(a3);
        }
        return a3;
    }

    @Override // androidx.fragment.app.c, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        a(this.i == -1);
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("PreferenceDialogFragment.title", this.f1613c);
        bundle.putCharSequence("PreferenceDialogFragment.positiveText", this.f1614d);
        bundle.putCharSequence("PreferenceDialogFragment.negativeText", this.f1615e);
        bundle.putCharSequence("PreferenceDialogFragment.message", this.f1616f);
        bundle.putInt("PreferenceDialogFragment.layout", this.g);
        BitmapDrawable bitmapDrawable = this.h;
        if (bitmapDrawable != null) {
            bundle.putParcelable("PreferenceDialogFragment.icon", bitmapDrawable.getBitmap());
        }
    }

    private void a(Dialog dialog) {
        dialog.getWindow().setSoftInputMode(5);
    }

    protected View a(Context context) {
        int i = this.g;
        if (i == 0) {
            return null;
        }
        return LayoutInflater.from(context).inflate(i, (ViewGroup) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(View view) {
        View findViewById = view.findViewById(R.id.message);
        if (findViewById != null) {
            CharSequence charSequence = this.f1616f;
            int i = 8;
            if (!TextUtils.isEmpty(charSequence)) {
                if (findViewById instanceof TextView) {
                    ((TextView) findViewById).setText(charSequence);
                }
                i = 0;
            }
            if (findViewById.getVisibility() != i) {
                findViewById.setVisibility(i);
            }
        }
    }
}
