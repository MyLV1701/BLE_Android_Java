package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class EddystoneRadioTxPowerDialogBuilder extends WriteDialogBuilder {

    /* loaded from: classes.dex */
    private class ViewHolder {
        private EditText radioTxPower;

        private ViewHolder() {
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_es_radio_tx_power, (ViewGroup) null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.radioTxPower = (EditText) inflate.findViewById(R.id.radio_tx_power);
        inflate.setTag(viewHolder);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        return new byte[]{(byte) Integer.parseInt(((ViewHolder) view.getTag()).radioTxPower.getText().toString().trim())};
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        String trim = viewHolder.radioTxPower.getText().toString().trim();
        String trim2 = viewHolder.radioTxPower.getText().toString().trim();
        if (trim.isEmpty()) {
            viewHolder.radioTxPower.setError("Enter supported Radio Tx power value");
            return false;
        }
        try {
            int parseInt = Integer.parseInt(trim2);
            if (parseInt < -100 || parseInt > 20) {
                throw new NumberFormatException();
            }
            return true;
        } catch (Exception unused) {
            viewHolder.radioTxPower.setError("Please enter a valid value for Radio Tx power");
            return false;
        }
    }
}
