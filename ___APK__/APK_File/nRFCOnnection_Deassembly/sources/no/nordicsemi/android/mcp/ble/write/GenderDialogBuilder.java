package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class GenderDialogBuilder extends WriteDialogBuilder {
    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_3_options, (ViewGroup) null);
        RadioButton radioButton = (RadioButton) inflate.findViewById(R.id.option_1);
        radioButton.setText(R.string.gender_male);
        radioButton.setChecked(true);
        ((RadioButton) inflate.findViewById(R.id.option_2)).setText(R.string.gender_female);
        ((RadioButton) inflate.findViewById(R.id.option_3)).setText(R.string.gender_unspecified);
        return inflate;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
    
        return r1;
     */
    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected byte[] getValueFromView(android.view.View r4) {
        /*
            r3 = this;
            r0 = 1
            byte[] r1 = new byte[r0]
            android.widget.RadioGroup r4 = (android.widget.RadioGroup) r4
            int r4 = r4.getCheckedRadioButtonId()
            r2 = 0
            switch(r4) {
                case 2131296815: goto L15;
                case 2131296816: goto L12;
                case 2131296817: goto Le;
                default: goto Ld;
            }
        Ld:
            goto L17
        Le:
            r4 = 2
            r1[r2] = r4
            goto L17
        L12:
            r1[r2] = r0
            goto L17
        L15:
            r1[r2] = r2
        L17:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.ble.write.GenderDialogBuilder.getValueFromView(android.view.View):byte[]");
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        return true;
    }
}
