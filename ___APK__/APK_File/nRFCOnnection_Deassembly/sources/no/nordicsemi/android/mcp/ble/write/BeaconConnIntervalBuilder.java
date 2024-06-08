package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class BeaconConnIntervalBuilder extends WriteDialogBuilder {

    /* loaded from: classes.dex */
    private class ViewHolder {
        private EditText interval;

        private ViewHolder() {
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_beacon_conn_interval, (ViewGroup) null);
        EditText editText = (EditText) inflate.findViewById(R.id.value);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.interval = editText;
        inflate.setTag(viewHolder);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        int parseInt = Integer.parseInt(((ViewHolder) view.getTag()).interval.getText().toString());
        return new byte[]{(byte) (parseInt & 255), (byte) ((parseInt >> 8) & 255)};
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        String obj = viewHolder.interval.getText().toString();
        boolean z = false;
        if (TextUtils.isEmpty(obj)) {
            viewHolder.interval.setError(getString(R.string.alert_value_invalid_empty));
            return false;
        }
        try {
            int parseInt = Integer.parseInt(obj);
            if (parseInt >= 100 && parseInt <= 10240) {
                z = true;
            }
        } catch (Exception unused) {
        }
        if (!z) {
            viewHolder.interval.setError(getString(R.string.alert_value_invalid));
        }
        return z;
    }
}
