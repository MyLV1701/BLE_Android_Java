package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class EddystoneActiveSlotDialogBuilder extends WriteDialogBuilder {

    /* loaded from: classes.dex */
    private class ViewHolder {
        private EditText activeSlot;

        private ViewHolder() {
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_es_active_slot, (ViewGroup) null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.activeSlot = (EditText) inflate.findViewById(R.id.active_slot);
        inflate.setTag(viewHolder);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        return new byte[]{(byte) Integer.parseInt(((ViewHolder) view.getTag()).activeSlot.getText().toString().trim())};
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
            no.nordicsemi.android.mcp.ble.write.EddystoneActiveSlotDialogBuilder$ViewHolder r5 = (no.nordicsemi.android.mcp.ble.write.EddystoneActiveSlotDialogBuilder.ViewHolder) r5
            android.widget.EditText r0 = no.nordicsemi.android.mcp.ble.write.EddystoneActiveSlotDialogBuilder.ViewHolder.access$100(r5)
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.trim()
            boolean r1 = r0.isEmpty()
            r2 = 0
            if (r1 == 0) goto L27
            android.widget.EditText r5 = no.nordicsemi.android.mcp.ble.write.EddystoneActiveSlotDialogBuilder.ViewHolder.access$100(r5)
            java.lang.String r0 = "Enter active slot value"
            r5.setError(r0)
            return r2
        L27:
            r1 = 1
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L35
            r3 = -256(0xffffffffffffff00, float:NaN)
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
            android.widget.EditText r5 = no.nordicsemi.android.mcp.ble.write.EddystoneActiveSlotDialogBuilder.ViewHolder.access$100(r5)
            java.lang.String r0 = "Value does not match UINT8"
            r5.setError(r0)
            return r2
        L42:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.ble.write.EddystoneActiveSlotDialogBuilder.isValueValid(android.view.View):boolean");
    }
}
