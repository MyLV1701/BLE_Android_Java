package androidx.preference;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

/* loaded from: classes.dex */
public class DropDownPreference extends ListPreference {
    private final Context m;
    private final ArrayAdapter n;
    private Spinner o;
    private final AdapterView.OnItemSelectedListener p;

    /* loaded from: classes.dex */
    class a implements AdapterView.OnItemSelectedListener {
        a() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (i >= 0) {
                String charSequence = DropDownPreference.this.i()[i].toString();
                if (charSequence.equals(DropDownPreference.this.j()) || !DropDownPreference.this.callChangeListener(charSequence)) {
                    return;
                }
                DropDownPreference.this.b(charSequence);
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public DropDownPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, m.dropdownPreferenceStyle);
    }

    private int c(String str) {
        CharSequence[] i = i();
        if (str == null || i == null) {
            return -1;
        }
        for (int length = i.length - 1; length >= 0; length--) {
            if (i[length].equals(str)) {
                return length;
            }
        }
        return -1;
    }

    private void l() {
        this.n.clear();
        if (g() != null) {
            for (CharSequence charSequence : g()) {
                this.n.add(charSequence.toString());
            }
        }
    }

    protected ArrayAdapter k() {
        return new ArrayAdapter(this.m, R.layout.simple_spinner_dropdown_item);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public void notifyChanged() {
        super.notifyChanged();
        ArrayAdapter arrayAdapter = this.n;
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(l lVar) {
        this.o = (Spinner) lVar.itemView.findViewById(p.spinner);
        this.o.setAdapter((SpinnerAdapter) this.n);
        this.o.setOnItemSelectedListener(this.p);
        this.o.setSelection(c(j()));
        super.onBindViewHolder(lVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.DialogPreference, androidx.preference.Preference
    public void onClick() {
        this.o.performClick();
    }

    public DropDownPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public DropDownPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.p = new a();
        this.m = context;
        this.n = k();
        l();
    }
}
