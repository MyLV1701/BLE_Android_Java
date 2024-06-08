package androidx.preference;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.d;

/* loaded from: classes.dex */
public class c extends f {
    int j;
    private CharSequence[] k;
    private CharSequence[] l;

    /* loaded from: classes.dex */
    class a implements DialogInterface.OnClickListener {
        a() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            c cVar = c.this;
            cVar.j = i;
            cVar.onClick(dialogInterface, -1);
            dialogInterface.dismiss();
        }
    }

    public static c a(String str) {
        c cVar = new c();
        Bundle bundle = new Bundle(1);
        bundle.putString("key", str);
        cVar.setArguments(bundle);
        return cVar;
    }

    private ListPreference c() {
        return (ListPreference) a();
    }

    @Override // androidx.preference.f, androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            ListPreference c2 = c();
            if (c2.g() != null && c2.i() != null) {
                this.j = c2.a(c2.j());
                this.k = c2.g();
                this.l = c2.i();
                return;
            }
            throw new IllegalStateException("ListPreference requires an entries array and an entryValues array.");
        }
        this.j = bundle.getInt("ListPreferenceDialogFragment.index", 0);
        this.k = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entries");
        this.l = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entryValues");
    }

    @Override // androidx.preference.f, androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("ListPreferenceDialogFragment.index", this.j);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entries", this.k);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entryValues", this.l);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.f
    public void a(d.a aVar) {
        super.a(aVar);
        aVar.a(this.k, this.j, new a());
        aVar.b((CharSequence) null, (DialogInterface.OnClickListener) null);
    }

    @Override // androidx.preference.f
    public void a(boolean z) {
        int i;
        if (!z || (i = this.j) < 0) {
            return;
        }
        String charSequence = this.l[i].toString();
        ListPreference c2 = c();
        if (c2.callChangeListener(charSequence)) {
            c2.b(charSequence);
        }
    }
}
