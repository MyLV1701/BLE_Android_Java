package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class BeaconMajorMinorBuilder extends WriteDialogBuilder {

    /* loaded from: classes.dex */
    private class ViewHolder {
        private EditText major;
        private EditText minor;

        private ViewHolder() {
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_beacon_major_minor, (ViewGroup) null);
        EditText editText = (EditText) inflate.findViewById(R.id.major);
        EditText editText2 = (EditText) inflate.findViewById(R.id.minor);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.major = editText;
        viewHolder.minor = editText2;
        inflate.setTag(viewHolder);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        int parseInt = Integer.parseInt(viewHolder.major.getText().toString());
        int parseInt2 = Integer.parseInt(viewHolder.minor.getText().toString());
        return new byte[]{(byte) ((parseInt >> 8) & 255), (byte) (parseInt & 255), (byte) ((parseInt2 >> 8) & 255), (byte) (parseInt2 & 255)};
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean isValueValid(android.view.View r5) {
        /*
            r4 = this;
            java.lang.Object r5 = r5.getTag()
            no.nordicsemi.android.mcp.ble.write.BeaconMajorMinorBuilder$ViewHolder r5 = (no.nordicsemi.android.mcp.ble.write.BeaconMajorMinorBuilder.ViewHolder) r5
            r0 = 65535(0xffff, float:9.1834E-41)
            r1 = 0
            android.widget.EditText r2 = no.nordicsemi.android.mcp.ble.write.BeaconMajorMinorBuilder.ViewHolder.access$100(r5)     // Catch: java.lang.Exception -> L21
            android.text.Editable r2 = r2.getText()     // Catch: java.lang.Exception -> L21
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> L21
            int r2 = java.lang.Integer.parseInt(r2)     // Catch: java.lang.Exception -> L21
            r3 = r2 & r0
            if (r3 == r2) goto L1f
            goto L21
        L1f:
            r2 = 1
            goto L22
        L21:
            r2 = 0
        L22:
            android.widget.EditText r5 = no.nordicsemi.android.mcp.ble.write.BeaconMajorMinorBuilder.ViewHolder.access$200(r5)     // Catch: java.lang.Exception -> L37
            android.text.Editable r5 = r5.getText()     // Catch: java.lang.Exception -> L37
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> L37
            int r5 = java.lang.Integer.parseInt(r5)     // Catch: java.lang.Exception -> L37
            r0 = r0 & r5
            if (r0 == r5) goto L36
            goto L37
        L36:
            r1 = r2
        L37:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.ble.write.BeaconMajorMinorBuilder.isValueValid(android.view.View):boolean");
    }
}
