package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class BeaconCalibrationBuilder extends WriteDialogBuilder {

    /* loaded from: classes.dex */
    private class ViewHolder {
        private EditText rssi;

        private ViewHolder() {
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_beacon_calibration, (ViewGroup) null);
        EditText editText = (EditText) inflate.findViewById(R.id.value);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.rssi = editText;
        inflate.setTag(viewHolder);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        return new byte[]{(byte) (Byte.parseByte(((ViewHolder) view.getTag()).rssi.getText().toString()) & FlagsParser.UNKNOWN_FLAGS)};
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        String obj = viewHolder.rssi.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            viewHolder.rssi.setError(getString(R.string.alert_value_invalid_empty));
            return false;
        }
        try {
            Byte.parseByte(obj);
            return true;
        } catch (Exception unused) {
            viewHolder.rssi.setError(getString(R.string.alert_value_invalid));
            return false;
        }
    }
}
