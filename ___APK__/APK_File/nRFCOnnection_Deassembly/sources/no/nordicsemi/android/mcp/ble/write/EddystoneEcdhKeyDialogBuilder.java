package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class EddystoneEcdhKeyDialogBuilder extends WriteDialogBuilder {
    private static final String PATTERN_ECDH_KEY = "[0-9a-fA-F]{64}";

    /* loaded from: classes.dex */
    private class ViewHolder {
        private EditText ecdhKey;

        private ViewHolder() {
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_es_ecdh_key, (ViewGroup) null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.ecdhKey = (EditText) inflate.findViewById(R.id.ecdh_key);
        inflate.setTag(viewHolder);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        byte[] bArr = new byte[32];
        ParserUtils.setByteArrayValue(bArr, 0, ((ViewHolder) view.getTag()).ecdhKey.getText().toString());
        return bArr;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        String trim = viewHolder.ecdhKey.getText().toString().trim();
        if (trim.isEmpty()) {
            viewHolder.ecdhKey.setError("Please enter ECDH Key");
            return false;
        }
        if (trim.matches(PATTERN_ECDH_KEY)) {
            return true;
        }
        viewHolder.ecdhKey.setError("Please enter a valid ECDH Key");
        return false;
    }
}
