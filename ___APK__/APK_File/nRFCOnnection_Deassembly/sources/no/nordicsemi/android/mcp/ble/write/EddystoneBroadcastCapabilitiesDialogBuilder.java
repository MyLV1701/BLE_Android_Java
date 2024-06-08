package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import java.util.ArrayList;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class EddystoneBroadcastCapabilitiesDialogBuilder extends WriteDialogBuilder {
    private static final int IS_VARIABLE_ADV_SUPPORTED = 1;
    private static final int IS_VARIABLE_TX_POWER_SUPPORTED = 2;
    private static final String PATTERN_TX_POWER = "(-?\\d{1,3}(,|$))+";
    private static final int TYPE_EID = 8;
    private static final int TYPE_TLM = 4;
    private static final int TYPE_UID = 1;
    private static final int TYPE_URL = 2;
    private int mVariableAdv = 0;
    private int mVariableTxPower = 0;
    private int mUid = 0;
    private int mUrl = 0;
    private int mTlm = 0;
    private int mEid = 0;

    /* loaded from: classes.dex */
    private class ViewHolder {
        private EditText supportedTxPower;
        private EditText totalEidSlots;
        private EditText totalSlots;
        private EditText version;

        private ViewHolder() {
        }
    }

    private void setUnit16(byte[] bArr, int i, int i2) {
        bArr[i2] = (byte) (i & 255);
        bArr[i2 + 1] = (byte) ((i >> 8) & 255);
    }

    public /* synthetic */ void a(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.mVariableAdv = 1;
        } else {
            this.mVariableAdv = 0;
        }
    }

    public /* synthetic */ void b(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.mVariableTxPower = 2;
        } else {
            this.mVariableTxPower = 0;
        }
    }

    public /* synthetic */ void c(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.mUid = 1;
        } else {
            this.mUid = 0;
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_es_broadcast_capabilities, (ViewGroup) null);
        ViewHolder viewHolder = new ViewHolder();
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.variable_adv);
        CheckBox checkBox2 = (CheckBox) inflate.findViewById(R.id.variable_tx_power);
        CheckBox checkBox3 = (CheckBox) inflate.findViewById(R.id.frame_type_uid);
        CheckBox checkBox4 = (CheckBox) inflate.findViewById(R.id.frame_type_url);
        CheckBox checkBox5 = (CheckBox) inflate.findViewById(R.id.frame_type_tlm);
        CheckBox checkBox6 = (CheckBox) inflate.findViewById(R.id.frame_type_eid);
        viewHolder.version = (EditText) inflate.findViewById(R.id.version_byte);
        viewHolder.totalSlots = (EditText) inflate.findViewById(R.id.max_slots);
        viewHolder.totalEidSlots = (EditText) inflate.findViewById(R.id.eid_slots);
        viewHolder.supportedTxPower = (EditText) inflate.findViewById(R.id.variable_tx_power_values);
        inflate.setTag(viewHolder);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.ble.write.k
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                EddystoneBroadcastCapabilitiesDialogBuilder.this.a(compoundButton, z);
            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.ble.write.l
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                EddystoneBroadcastCapabilitiesDialogBuilder.this.b(compoundButton, z);
            }
        });
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.ble.write.n
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                EddystoneBroadcastCapabilitiesDialogBuilder.this.c(compoundButton, z);
            }
        });
        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.ble.write.o
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                EddystoneBroadcastCapabilitiesDialogBuilder.this.d(compoundButton, z);
            }
        });
        checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.ble.write.j
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                EddystoneBroadcastCapabilitiesDialogBuilder.this.e(compoundButton, z);
            }
        });
        checkBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.ble.write.m
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                EddystoneBroadcastCapabilitiesDialogBuilder.this.f(compoundButton, z);
            }
        });
        return inflate;
    }

    public /* synthetic */ void d(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.mUrl = 2;
        } else {
            this.mUrl = 0;
        }
    }

    public /* synthetic */ void e(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.mTlm = 4;
        } else {
            this.mTlm = 0;
        }
    }

    public /* synthetic */ void f(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.mEid = 8;
        } else {
            this.mEid = 0;
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        int i = this.mUid | this.mUrl | this.mTlm | this.mEid;
        String[] split = viewHolder.supportedTxPower.getText().toString().trim().split(",");
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < split.length; i2++) {
            if (!split[i2].isEmpty()) {
                arrayList.add(Byte.valueOf((byte) Integer.parseInt(split[i2].trim())));
            }
        }
        byte[] bArr = new byte[arrayList.size()];
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            bArr[i3] = ((Byte) arrayList.get(i3)).byteValue();
        }
        byte[] bArr2 = new byte[bArr.length + 6];
        bArr2[0] = (byte) Integer.parseInt(viewHolder.version.getText().toString().trim());
        bArr2[1] = (byte) Integer.parseInt(viewHolder.totalSlots.getText().toString().trim());
        bArr2[2] = (byte) Integer.parseInt(viewHolder.totalEidSlots.getText().toString().trim());
        bArr2[3] = (byte) (this.mVariableAdv | this.mVariableTxPower);
        setUnit16(bArr2, i, 4);
        System.arraycopy(bArr, 0, bArr2, 6, bArr.length);
        return bArr2;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ae  */
    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean isValueValid(android.view.View r7) {
        /*
            r6 = this;
            java.lang.Object r7 = r7.getTag()
            no.nordicsemi.android.mcp.ble.write.EddystoneBroadcastCapabilitiesDialogBuilder$ViewHolder r7 = (no.nordicsemi.android.mcp.ble.write.EddystoneBroadcastCapabilitiesDialogBuilder.ViewHolder) r7
            android.widget.EditText r0 = no.nordicsemi.android.mcp.ble.write.EddystoneBroadcastCapabilitiesDialogBuilder.ViewHolder.access$100(r7)
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.trim()
            boolean r1 = r0.isEmpty()
            r2 = 0
            if (r1 == 0) goto L27
            android.widget.EditText r7 = no.nordicsemi.android.mcp.ble.write.EddystoneBroadcastCapabilitiesDialogBuilder.ViewHolder.access$100(r7)
            java.lang.String r0 = "Enter version value"
            r7.setError(r0)
            return r2
        L27:
            r1 = 1
            r3 = -256(0xffffffffffffff00, float:NaN)
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L35
            r0 = r0 & r3
            if (r0 == 0) goto L33
            if (r0 != r3) goto L35
        L33:
            r0 = 1
            goto L36
        L35:
            r0 = 0
        L36:
            java.lang.String r4 = "Value does not match UINT8"
            if (r0 != 0) goto L42
            android.widget.EditText r7 = no.nordicsemi.android.mcp.ble.write.EddystoneBroadcastCapabilitiesDialogBuilder.ViewHolder.access$100(r7)
            r7.setError(r4)
            return r2
        L42:
            android.widget.EditText r0 = no.nordicsemi.android.mcp.ble.write.EddystoneBroadcastCapabilitiesDialogBuilder.ViewHolder.access$200(r7)
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.trim()
            boolean r5 = r0.isEmpty()
            if (r5 == 0) goto L62
            android.widget.EditText r7 = no.nordicsemi.android.mcp.ble.write.EddystoneBroadcastCapabilitiesDialogBuilder.ViewHolder.access$200(r7)
            java.lang.String r0 = "Enter total number of slots"
            r7.setError(r0)
            return r2
        L62:
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L6d
            r0 = r0 & r3
            if (r0 == 0) goto L6b
            if (r0 != r3) goto L6d
        L6b:
            r0 = 1
            goto L6e
        L6d:
            r0 = 0
        L6e:
            if (r0 != 0) goto L78
            android.widget.EditText r7 = no.nordicsemi.android.mcp.ble.write.EddystoneBroadcastCapabilitiesDialogBuilder.ViewHolder.access$200(r7)
            r7.setError(r4)
            return r2
        L78:
            android.widget.EditText r0 = no.nordicsemi.android.mcp.ble.write.EddystoneBroadcastCapabilitiesDialogBuilder.ViewHolder.access$300(r7)
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.trim()
            boolean r5 = r0.isEmpty()
            if (r5 == 0) goto L98
            android.widget.EditText r7 = no.nordicsemi.android.mcp.ble.write.EddystoneBroadcastCapabilitiesDialogBuilder.ViewHolder.access$300(r7)
            java.lang.String r0 = "Enter total number of eid slots"
            r7.setError(r0)
            return r2
        L98:
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> La3
            r0 = r0 & r3
            if (r0 == 0) goto La1
            if (r0 != r3) goto La3
        La1:
            r0 = 1
            goto La4
        La3:
            r0 = 0
        La4:
            if (r0 != 0) goto Lae
            android.widget.EditText r7 = no.nordicsemi.android.mcp.ble.write.EddystoneBroadcastCapabilitiesDialogBuilder.ViewHolder.access$300(r7)
            r7.setError(r4)
            return r2
        Lae:
            android.widget.EditText r0 = no.nordicsemi.android.mcp.ble.write.EddystoneBroadcastCapabilitiesDialogBuilder.ViewHolder.access$400(r7)
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.trim()
            boolean r3 = r0.isEmpty()
            if (r3 == 0) goto Lce
            android.widget.EditText r7 = no.nordicsemi.android.mcp.ble.write.EddystoneBroadcastCapabilitiesDialogBuilder.ViewHolder.access$400(r7)
            java.lang.String r0 = "Enter supported Tx power values"
            r7.setError(r0)
            return r2
        Lce:
            java.lang.String r3 = "(-?\\d{1,3}(,|$))+"
            boolean r0 = r0.matches(r3)
            if (r0 != 0) goto Le0
            android.widget.EditText r7 = no.nordicsemi.android.mcp.ble.write.EddystoneBroadcastCapabilitiesDialogBuilder.ViewHolder.access$400(r7)
            java.lang.String r0 = "Please enter a valid value for supported Tx power"
            r7.setError(r0)
            return r2
        Le0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.ble.write.EddystoneBroadcastCapabilitiesDialogBuilder.isValueValid(android.view.View):boolean");
    }
}
