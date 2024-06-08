package androidx.preference;

import android.R;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/* loaded from: classes.dex */
public class a extends f {
    private EditText j;
    private CharSequence k;

    public static a a(String str) {
        a aVar = new a();
        Bundle bundle = new Bundle(1);
        bundle.putString("key", str);
        aVar.setArguments(bundle);
        return aVar;
    }

    private EditTextPreference c() {
        return (EditTextPreference) a();
    }

    @Override // androidx.preference.f
    protected boolean b() {
        return true;
    }

    @Override // androidx.preference.f, androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.k = c().h();
        } else {
            this.k = bundle.getCharSequence("EditTextPreferenceDialogFragment.text");
        }
    }

    @Override // androidx.preference.f, androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("EditTextPreferenceDialogFragment.text", this.k);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.f
    public void a(View view) {
        super.a(view);
        this.j = (EditText) view.findViewById(R.id.edit);
        EditText editText = this.j;
        if (editText != null) {
            editText.requestFocus();
            this.j.setText(this.k);
            EditText editText2 = this.j;
            editText2.setSelection(editText2.getText().length());
            if (c().g() != null) {
                c().g().a(this.j);
                return;
            }
            return;
        }
        throw new IllegalStateException("Dialog view must contain an EditText with id @android:id/edit");
    }

    @Override // androidx.preference.f
    public void a(boolean z) {
        if (z) {
            String obj = this.j.getText().toString();
            EditTextPreference c2 = c();
            if (c2.callChangeListener(obj)) {
                c2.a(obj);
            }
        }
    }
}
