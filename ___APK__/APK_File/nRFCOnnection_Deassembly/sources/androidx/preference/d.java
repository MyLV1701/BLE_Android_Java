package androidx.preference;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.d;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class d extends f {
    Set<String> j = new HashSet();
    boolean k;
    CharSequence[] l;
    CharSequence[] m;

    /* loaded from: classes.dex */
    class a implements DialogInterface.OnMultiChoiceClickListener {
        a() {
        }

        @Override // android.content.DialogInterface.OnMultiChoiceClickListener
        public void onClick(DialogInterface dialogInterface, int i, boolean z) {
            if (z) {
                d dVar = d.this;
                dVar.k = dVar.j.add(dVar.m[i].toString()) | dVar.k;
            } else {
                d dVar2 = d.this;
                dVar2.k = dVar2.j.remove(dVar2.m[i].toString()) | dVar2.k;
            }
        }
    }

    public static d a(String str) {
        d dVar = new d();
        Bundle bundle = new Bundle(1);
        bundle.putString("key", str);
        dVar.setArguments(bundle);
        return dVar;
    }

    private MultiSelectListPreference c() {
        return (MultiSelectListPreference) a();
    }

    @Override // androidx.preference.f, androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            MultiSelectListPreference c2 = c();
            if (c2.g() != null && c2.h() != null) {
                this.j.clear();
                this.j.addAll(c2.i());
                this.k = false;
                this.l = c2.g();
                this.m = c2.h();
                return;
            }
            throw new IllegalStateException("MultiSelectListPreference requires an entries array and an entryValues array.");
        }
        this.j.clear();
        this.j.addAll(bundle.getStringArrayList("MultiSelectListPreferenceDialogFragmentCompat.values"));
        this.k = bundle.getBoolean("MultiSelectListPreferenceDialogFragmentCompat.changed", false);
        this.l = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entries");
        this.m = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entryValues");
    }

    @Override // androidx.preference.f, androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList("MultiSelectListPreferenceDialogFragmentCompat.values", new ArrayList<>(this.j));
        bundle.putBoolean("MultiSelectListPreferenceDialogFragmentCompat.changed", this.k);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entries", this.l);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entryValues", this.m);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.f
    public void a(d.a aVar) {
        super.a(aVar);
        int length = this.m.length;
        boolean[] zArr = new boolean[length];
        for (int i = 0; i < length; i++) {
            zArr[i] = this.j.contains(this.m[i].toString());
        }
        aVar.a(this.l, zArr, new a());
    }

    @Override // androidx.preference.f
    public void a(boolean z) {
        if (z && this.k) {
            MultiSelectListPreference c2 = c();
            if (c2.callChangeListener(this.j)) {
                c2.a(this.j);
            }
        }
        this.k = false;
    }
}
