package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.EddystoneUtils;

/* loaded from: classes.dex */
public class EddystoneUnlockDialogBuilder extends WriteDialogBuilder {
    private static final String PATTERN_TX_POWER = "[0-9a-fA-F]{32}";

    /* loaded from: classes.dex */
    private class ViewHolder {
        private EditText lockCode;

        private ViewHolder() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View view = null;
        ViewHolder viewHolder = new ViewHolder();
        int i = this.mAction;
        if (i == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.dialog_write_es_unlock, (ViewGroup) null);
        } else if (i == 2) {
            view = LayoutInflater.from(context).inflate(R.layout.dialog_write_es_unlock_set, (ViewGroup) null);
        }
        viewHolder.lockCode = (EditText) view.findViewById(R.id.lock_code);
        view.setTag(viewHolder);
        viewHolder.lockCode.setText(EddystoneUtils.getLastLockKey(getContext()));
        return view;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        String trim = ((ViewHolder) view.getTag()).lockCode.getText().toString().trim();
        EddystoneUtils.storeLockKey(getContext(), trim);
        byte[] bArr = new byte[16];
        ParserUtils.setByteArrayValue(bArr, 0, trim);
        return bArr;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        byte[] value = this.mCharacteristic.getValue();
        if (this.mAction == 1 && value == null) {
            Toast.makeText(getActivity(), "Please read the challenge in order to unlock the beacon.", 0).show();
            return false;
        }
        if (this.mAction != 1 || value.length == 16) {
            String trim = viewHolder.lockCode.getText().toString().trim();
            if (trim.isEmpty()) {
                viewHolder.lockCode.setError("Please enter lock code");
                return false;
            }
            if (trim.matches(PATTERN_TX_POWER)) {
                return true;
            }
            viewHolder.lockCode.setError("Please enter a valid value for lock code");
            return false;
        }
        Toast.makeText(getActivity(), "The challenge read from Unlock characteristic has incorrect length.", 0).show();
        return false;
    }
}
