package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class EddystoneAdvancedAdvertisedTxPowerDialogBuilder extends WriteDialogBuilder {
    private static final String PATTERN_TX_POWER = "(-?\\d{1,3}(|$))+";

    /* loaded from: classes.dex */
    private class ViewHolder {
        private EditText advertisedTxPower;

        private ViewHolder() {
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_es_adv_advertised_tx_power, (ViewGroup) null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.advertisedTxPower = (EditText) inflate.findViewById(R.id.adv_advertised__tx_power);
        inflate.setTag(viewHolder);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        return new byte[]{(byte) Integer.parseInt(((ViewHolder) view.getTag()).advertisedTxPower.getText().toString().trim())};
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        String trim = viewHolder.advertisedTxPower.getText().toString().trim();
        if (trim.isEmpty()) {
            viewHolder.advertisedTxPower.setError("Enter advertised Tx power value");
            return false;
        }
        if (trim.matches(PATTERN_TX_POWER)) {
            return true;
        }
        viewHolder.advertisedTxPower.setError("Please enter a valid value for advertised Tx power");
        return false;
    }
}
