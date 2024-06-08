package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class EddystoneEidIdentityKeyDialogBuilder extends WriteDialogBuilder {
    private static final String PATTERN_EID_IDENTITY_KEY = "[0-9a-fA-F]{32}";

    /* loaded from: classes.dex */
    private class ViewHolder {
        private EditText eidIdentityKey;

        private ViewHolder() {
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_es_eid_identity_key, (ViewGroup) null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.eidIdentityKey = (EditText) inflate.findViewById(R.id.eid_identity_key);
        inflate.setTag(viewHolder);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        byte[] bArr = new byte[16];
        ParserUtils.setByteArrayValue(bArr, 0, ((ViewHolder) view.getTag()).eidIdentityKey.getText().toString());
        return bArr;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        String trim = viewHolder.eidIdentityKey.getText().toString().trim();
        if (trim.isEmpty()) {
            viewHolder.eidIdentityKey.setError("Please enter EID Identity Key");
            return false;
        }
        if (trim.matches(PATTERN_EID_IDENTITY_KEY)) {
            return true;
        }
        viewHolder.eidIdentityKey.setError("Please enter a new EID Identity Key");
        return false;
    }
}
