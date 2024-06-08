package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class EddystoneAdvertisingIntervalDialogBuilder extends WriteDialogBuilder {

    /* loaded from: classes.dex */
    private class ViewHolder {
        private EditText advertisingInterval;

        private ViewHolder() {
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_es_advertising_interval, (ViewGroup) null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.advertisingInterval = (EditText) inflate.findViewById(R.id.advertising_interval);
        inflate.setTag(viewHolder);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        byte[] bArr = new byte[2];
        ParserUtils.setValue(bArr, 0, Integer.parseInt(((ViewHolder) view.getTag()).advertisingInterval.getText().toString().trim()), 98);
        return bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0042 A[RETURN] */
    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean isValueValid(android.view.View r5) {
        /*
            r4 = this;
            java.lang.Object r5 = r5.getTag()
            no.nordicsemi.android.mcp.ble.write.EddystoneAdvertisingIntervalDialogBuilder$ViewHolder r5 = (no.nordicsemi.android.mcp.ble.write.EddystoneAdvertisingIntervalDialogBuilder.ViewHolder) r5
            android.widget.EditText r0 = no.nordicsemi.android.mcp.ble.write.EddystoneAdvertisingIntervalDialogBuilder.ViewHolder.access$100(r5)
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.trim()
            boolean r1 = r0.isEmpty()
            r2 = 0
            if (r1 == 0) goto L27
            android.widget.EditText r5 = no.nordicsemi.android.mcp.ble.write.EddystoneAdvertisingIntervalDialogBuilder.ViewHolder.access$100(r5)
            java.lang.String r0 = "Enter advertising interval value"
            r5.setError(r0)
            return r2
        L27:
            r1 = 1
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L35
            r3 = -65536(0xffffffffffff0000, float:NaN)
            r0 = r0 & r3
            if (r0 == 0) goto L33
            if (r0 != r3) goto L35
        L33:
            r0 = 1
            goto L36
        L35:
            r0 = 0
        L36:
            if (r0 != 0) goto L42
            android.widget.EditText r5 = no.nordicsemi.android.mcp.ble.write.EddystoneAdvertisingIntervalDialogBuilder.ViewHolder.access$100(r5)
            java.lang.String r0 = "Value does not match UINT16"
            r5.setError(r0)
            return r2
        L42:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.ble.write.EddystoneAdvertisingIntervalDialogBuilder.isValueValid(android.view.View):boolean");
    }
}
