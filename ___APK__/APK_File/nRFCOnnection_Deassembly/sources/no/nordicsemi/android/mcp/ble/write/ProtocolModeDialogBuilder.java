package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class ProtocolModeDialogBuilder extends WriteDialogBuilder {
    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_3_options, (ViewGroup) null);
        RadioButton radioButton = (RadioButton) inflate.findViewById(R.id.option_1);
        radioButton.setText(R.string.write_protocol_mode_boot);
        radioButton.setChecked(true);
        ((RadioButton) inflate.findViewById(R.id.option_2)).setText(R.string.write_protocol_mode_report);
        inflate.findViewById(R.id.option_3).setVisibility(8);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        byte[] bArr = new byte[1];
        bArr[0] = ((RadioGroup) view).getCheckedRadioButtonId() == R.id.option_1 ? (byte) 0 : (byte) 1;
        return bArr;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        return true;
    }
}
