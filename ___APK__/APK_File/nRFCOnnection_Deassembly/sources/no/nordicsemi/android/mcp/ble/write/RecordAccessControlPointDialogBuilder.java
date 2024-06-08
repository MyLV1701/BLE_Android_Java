package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class RecordAccessControlPointDialogBuilder extends WriteDialogBuilder {
    private boolean mNumberofReconrdsSelected = false;
    private boolean mResponseCodesSelected = false;

    /* loaded from: classes.dex */
    private class ViewHolder {
        private EditText filter;
        private View filterContainer;
        private View filterLabel;
        private Spinner filterPredefined;
        private Spinner opCode;
        private EditText operand1;
        private View operand1Container;
        private EditText operand2;
        private View operand2Container;
        private View operandLabel;
        private Spinner operator;
        private Spinner requestOpCode;
        private View requestOpCodeLabel;
        private View statusCodeLabel;
        private Spinner statusCodes;

        private ViewHolder() {
        }
    }

    private void setUnit16(byte[] bArr, int i, int i2) {
        bArr[i2] = (byte) (i & 255);
        bArr[i2 + 1] = (byte) ((i >> 8) & 255);
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_record_access_control_point, (ViewGroup) null);
        ViewHolder viewHolder = new ViewHolder();
        Spinner spinner = viewHolder.opCode = (Spinner) inflate.findViewById(R.id.value);
        Spinner spinner2 = viewHolder.operator = (Spinner) inflate.findViewById(R.id.operator);
        Spinner spinner3 = viewHolder.filterPredefined = (Spinner) inflate.findViewById(R.id.filter_predefined);
        Spinner spinner4 = viewHolder.requestOpCode = (Spinner) inflate.findViewById(R.id.request_opcode);
        Spinner spinner5 = viewHolder.statusCodes = (Spinner) inflate.findViewById(R.id.status_codes);
        viewHolder.filterLabel = inflate.findViewById(R.id.filter_label);
        viewHolder.filterContainer = inflate.findViewById(R.id.filter_container);
        viewHolder.filter = (EditText) inflate.findViewById(R.id.filter);
        viewHolder.operandLabel = inflate.findViewById(R.id.operand_label);
        viewHolder.operand1Container = inflate.findViewById(R.id.operand1_container);
        viewHolder.operand1 = (EditText) inflate.findViewById(R.id.operand1);
        viewHolder.operand2Container = inflate.findViewById(R.id.operand2_container);
        viewHolder.operand2 = (EditText) inflate.findViewById(R.id.operand2);
        viewHolder.requestOpCodeLabel = inflate.findViewById(R.id.request_opcode_label);
        viewHolder.statusCodeLabel = inflate.findViewById(R.id.status_label);
        inflate.setTag(viewHolder);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: no.nordicsemi.android.mcp.ble.write.RecordAccessControlPointDialogBuilder.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                ViewHolder viewHolder2 = (ViewHolder) adapterView.getTag();
                if (i == 2) {
                    viewHolder2.operator.setSelection(0);
                    viewHolder2.operator.setEnabled(false);
                    viewHolder2.filterLabel.setVisibility(8);
                    viewHolder2.filterPredefined.setVisibility(8);
                    viewHolder2.filterContainer.setVisibility(8);
                    viewHolder2.operandLabel.setVisibility(8);
                    viewHolder2.operand1Container.setVisibility(8);
                    viewHolder2.operand2Container.setVisibility(8);
                    viewHolder2.requestOpCodeLabel.setVisibility(8);
                    viewHolder2.requestOpCode.setVisibility(8);
                    viewHolder2.statusCodeLabel.setVisibility(8);
                    viewHolder2.statusCodes.setVisibility(8);
                    return;
                }
                if (i == 4) {
                    viewHolder2.operator.setEnabled(false);
                    viewHolder2.filterLabel.setVisibility(8);
                    viewHolder2.filterPredefined.setVisibility(8);
                    viewHolder2.filterContainer.setVisibility(8);
                    viewHolder2.operandLabel.setVisibility(8);
                    viewHolder2.operand2Container.setVisibility(8);
                    viewHolder2.requestOpCodeLabel.setVisibility(8);
                    viewHolder2.requestOpCode.setVisibility(8);
                    viewHolder2.statusCodeLabel.setVisibility(8);
                    viewHolder2.statusCodes.setVisibility(8);
                    viewHolder2.operand1Container.setVisibility(0);
                    viewHolder2.operand1.setHint(R.string.racp_number_store_records_hint);
                    RecordAccessControlPointDialogBuilder.this.mNumberofReconrdsSelected = true;
                    viewHolder2.operator.setSelection(0);
                    return;
                }
                if (i == 5) {
                    viewHolder2.operator.setEnabled(false);
                    viewHolder2.filterLabel.setVisibility(8);
                    viewHolder2.filterPredefined.setVisibility(8);
                    viewHolder2.filterContainer.setVisibility(8);
                    viewHolder2.operandLabel.setVisibility(8);
                    viewHolder2.operand1Container.setVisibility(8);
                    viewHolder2.operand2Container.setVisibility(8);
                    viewHolder2.requestOpCodeLabel.setVisibility(0);
                    viewHolder2.requestOpCode.setVisibility(0);
                    viewHolder2.statusCodeLabel.setVisibility(0);
                    viewHolder2.statusCodes.setVisibility(0);
                    RecordAccessControlPointDialogBuilder.this.mResponseCodesSelected = true;
                    viewHolder2.operator.setSelection(0);
                    return;
                }
                viewHolder2.operator.setSelection(1);
                viewHolder2.operator.setEnabled(true);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinner.setSelection(0);
        spinner.setTag(viewHolder);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: no.nordicsemi.android.mcp.ble.write.RecordAccessControlPointDialogBuilder.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                boolean z;
                boolean z2;
                ViewHolder viewHolder2 = (ViewHolder) adapterView.getTag();
                if (i != 0 || (!RecordAccessControlPointDialogBuilder.this.mNumberofReconrdsSelected && !RecordAccessControlPointDialogBuilder.this.mResponseCodesSelected)) {
                    boolean z3 = true;
                    if (i == 2 || i == 3) {
                        z = true;
                        z2 = false;
                    } else if (i == 4) {
                        z = true;
                        z2 = true;
                    } else {
                        z = false;
                        z2 = false;
                        z3 = false;
                    }
                    viewHolder2.filterLabel.setVisibility(z3 ? 0 : 8);
                    viewHolder2.filterPredefined.setVisibility(z3 ? 0 : 8);
                    viewHolder2.filterPredefined.setSelection(0);
                    viewHolder2.filterContainer.setVisibility(z3 ? 0 : 8);
                    viewHolder2.operandLabel.setVisibility(z ? 0 : 8);
                    viewHolder2.operand1Container.setVisibility(z ? 0 : 8);
                    viewHolder2.operand2Container.setVisibility(z2 ? 0 : 8);
                    viewHolder2.requestOpCodeLabel.setVisibility(8);
                    viewHolder2.requestOpCode.setVisibility(8);
                    viewHolder2.statusCodeLabel.setVisibility(8);
                    viewHolder2.statusCodes.setVisibility(8);
                    return;
                }
                RecordAccessControlPointDialogBuilder.this.mNumberofReconrdsSelected = false;
                RecordAccessControlPointDialogBuilder.this.mResponseCodesSelected = false;
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinner2.setSelection(1);
        spinner2.setTag(viewHolder);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: no.nordicsemi.android.mcp.ble.write.RecordAccessControlPointDialogBuilder.3
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                ((ViewHolder) adapterView.getTag()).filterContainer.setVisibility(adapterView.getVisibility() == 0 && i == 0 ? 0 : 8);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinner3.setTag(viewHolder);
        spinner4.setSelection(0);
        spinner4.setTag(viewHolder);
        spinner5.setSelection(0);
        spinner5.setTag(viewHolder);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        int selectedItemPosition = viewHolder.opCode.getSelectedItemPosition() + 1;
        int selectedItemPosition2 = viewHolder.operator.getSelectedItemPosition();
        int i = (selectedItemPosition2 == 2 || selectedItemPosition2 == 3) ? 5 : 2;
        if (selectedItemPosition2 == 4) {
            i = 7;
        }
        if (selectedItemPosition2 == 0 && (selectedItemPosition == 5 || selectedItemPosition == 6)) {
            i = 4;
        }
        byte[] bArr = new byte[i];
        bArr[0] = (byte) selectedItemPosition;
        bArr[1] = (byte) selectedItemPosition2;
        if (selectedItemPosition2 == 2 || selectedItemPosition2 == 3 || selectedItemPosition2 == 4) {
            int selectedItemPosition3 = viewHolder.filterPredefined.getSelectedItemPosition();
            if (selectedItemPosition3 > 0) {
                bArr[2] = (byte) selectedItemPosition3;
            } else {
                bArr[2] = (byte) Integer.parseInt(viewHolder.filter.getText().toString().trim());
            }
            setUnit16(bArr, Integer.parseInt(viewHolder.operand1.getText().toString().trim()), 3);
        }
        if (selectedItemPosition2 == 4) {
            setUnit16(bArr, Integer.parseInt(viewHolder.operand2.getText().toString().trim()), 5);
        }
        if (selectedItemPosition == 5 && selectedItemPosition2 == 0) {
            setUnit16(bArr, Integer.parseInt(viewHolder.operand1.getText().toString().trim()), 2);
        }
        if (selectedItemPosition == 6 && selectedItemPosition2 == 0) {
            bArr[2] = (byte) (viewHolder.requestOpCode.getSelectedItemPosition() + 1);
            bArr[3] = (byte) (viewHolder.statusCodes.getSelectedItemPosition() + 1);
        }
        return bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00c6  */
    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean isValueValid(android.view.View r8) {
        /*
            r7 = this;
            java.lang.Object r8 = r8.getTag()
            no.nordicsemi.android.mcp.ble.write.RecordAccessControlPointDialogBuilder$ViewHolder r8 = (no.nordicsemi.android.mcp.ble.write.RecordAccessControlPointDialogBuilder.ViewHolder) r8
            android.view.View r0 = no.nordicsemi.android.mcp.ble.write.RecordAccessControlPointDialogBuilder.ViewHolder.access$700(r8)
            int r0 = r0.getVisibility()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L4c
            android.widget.EditText r0 = no.nordicsemi.android.mcp.ble.write.RecordAccessControlPointDialogBuilder.ViewHolder.access$800(r8)
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.trim()
            boolean r3 = r0.isEmpty()
            if (r3 == 0) goto L32
            android.widget.EditText r8 = no.nordicsemi.android.mcp.ble.write.RecordAccessControlPointDialogBuilder.ViewHolder.access$800(r8)
            java.lang.String r0 = "Enter filter value"
            r8.setError(r0)
            return r2
        L32:
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L3f
            r3 = -256(0xffffffffffffff00, float:NaN)
            r0 = r0 & r3
            if (r0 == 0) goto L3d
            if (r0 != r3) goto L3f
        L3d:
            r0 = 1
            goto L40
        L3f:
            r0 = 0
        L40:
            if (r0 != 0) goto L4c
            android.widget.EditText r8 = no.nordicsemi.android.mcp.ble.write.RecordAccessControlPointDialogBuilder.ViewHolder.access$800(r8)
            java.lang.String r0 = "Value does not match UINT8"
            r8.setError(r0)
            return r2
        L4c:
            android.view.View r0 = no.nordicsemi.android.mcp.ble.write.RecordAccessControlPointDialogBuilder.ViewHolder.access$1000(r8)
            int r0 = r0.getVisibility()
            java.lang.String r3 = "Value does not match UINT16"
            java.lang.String r4 = "Enter value"
            r5 = -65536(0xffffffffffff0000, float:NaN)
            if (r0 != 0) goto L90
            android.widget.EditText r0 = no.nordicsemi.android.mcp.ble.write.RecordAccessControlPointDialogBuilder.ViewHolder.access$1100(r8)
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.trim()
            boolean r6 = r0.isEmpty()
            if (r6 == 0) goto L7a
            android.widget.EditText r8 = no.nordicsemi.android.mcp.ble.write.RecordAccessControlPointDialogBuilder.ViewHolder.access$1100(r8)
            r8.setError(r4)
            return r2
        L7a:
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L85
            r0 = r0 & r5
            if (r0 == 0) goto L83
            if (r0 != r5) goto L85
        L83:
            r0 = 1
            goto L86
        L85:
            r0 = 0
        L86:
            if (r0 != 0) goto L90
            android.widget.EditText r8 = no.nordicsemi.android.mcp.ble.write.RecordAccessControlPointDialogBuilder.ViewHolder.access$1100(r8)
            r8.setError(r3)
            return r2
        L90:
            android.view.View r0 = no.nordicsemi.android.mcp.ble.write.RecordAccessControlPointDialogBuilder.ViewHolder.access$1200(r8)
            int r0 = r0.getVisibility()
            if (r0 != 0) goto Lce
            android.widget.EditText r0 = no.nordicsemi.android.mcp.ble.write.RecordAccessControlPointDialogBuilder.ViewHolder.access$1300(r8)
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.trim()
            boolean r6 = r0.isEmpty()
            if (r6 == 0) goto Lb8
            android.widget.EditText r8 = no.nordicsemi.android.mcp.ble.write.RecordAccessControlPointDialogBuilder.ViewHolder.access$1300(r8)
            r8.setError(r4)
            return r2
        Lb8:
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> Lc3
            r0 = r0 & r5
            if (r0 == 0) goto Lc1
            if (r0 != r5) goto Lc3
        Lc1:
            r0 = 1
            goto Lc4
        Lc3:
            r0 = 0
        Lc4:
            if (r0 != 0) goto Lce
            android.widget.EditText r8 = no.nordicsemi.android.mcp.ble.write.RecordAccessControlPointDialogBuilder.ViewHolder.access$1300(r8)
            r8.setError(r3)
            return r2
        Lce:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.ble.write.RecordAccessControlPointDialogBuilder.isValueValid(android.view.View):boolean");
    }
}
