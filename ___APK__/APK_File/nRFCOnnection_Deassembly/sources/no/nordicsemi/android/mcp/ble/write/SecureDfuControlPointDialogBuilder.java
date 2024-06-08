package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class SecureDfuControlPointDialogBuilder extends WriteDialogBuilder implements AdapterView.OnItemSelectedListener {

    /* loaded from: classes.dex */
    private class ViewHolder {
        private View nopLabel;
        private EditText numberOfPackets;
        private EditText objectSize;
        private View objectSizeLabel;
        private RadioButton objectTypeCommand;
        private RadioButton objectTypeData;
        private RadioGroup objectTypeGroup;
        private Spinner opCode;

        private ViewHolder() {
        }
    }

    private void setNumberOfPackets(byte[] bArr, int i) {
        bArr[1] = (byte) (i & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
    }

    private void setObjectSize(byte[] bArr, int i) {
        bArr[2] = (byte) (i & 255);
        bArr[3] = (byte) ((i >> 8) & 255);
        bArr[4] = (byte) ((i >> 16) & 255);
        bArr[5] = (byte) ((i >> 24) & 255);
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_secure_dfu_control_point, (ViewGroup) null);
        ViewHolder viewHolder = new ViewHolder();
        Spinner spinner = viewHolder.opCode = (Spinner) inflate.findViewById(R.id.value);
        viewHolder.objectSizeLabel = inflate.findViewById(R.id.object_size_info);
        viewHolder.objectSize = (EditText) inflate.findViewById(R.id.object_size);
        viewHolder.nopLabel = inflate.findViewById(R.id.size_info);
        viewHolder.numberOfPackets = (EditText) inflate.findViewById(R.id.prn_value);
        viewHolder.objectTypeGroup = (RadioGroup) inflate.findViewById(R.id.object_type_group);
        viewHolder.objectTypeCommand = (RadioButton) inflate.findViewById(R.id.object_type_command);
        viewHolder.objectTypeData = (RadioButton) inflate.findViewById(R.id.object_type_data);
        inflate.setTag(viewHolder);
        spinner.setOnItemSelectedListener(this);
        spinner.setTag(viewHolder);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        int selectedItemPosition = viewHolder.opCode.getSelectedItemPosition();
        if (selectedItemPosition != 0) {
            if (selectedItemPosition != 1) {
                return selectedItemPosition != 4 ? new byte[]{(byte) (selectedItemPosition + 1)} : viewHolder.objectTypeCommand.isChecked() ? new byte[]{6, 1} : new byte[]{6, 2};
            }
            byte[] bArr = {2};
            setNumberOfPackets(bArr, Integer.parseInt(viewHolder.numberOfPackets.getText().toString()));
            return bArr;
        }
        int i = viewHolder.objectTypeCommand.isChecked() ? 1 : 2;
        byte[] bArr2 = new byte[6];
        bArr2[0] = 1;
        bArr2[1] = (byte) i;
        setObjectSize(bArr2, Integer.parseInt(viewHolder.objectSize.getText().toString()));
        return bArr2;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        int selectedItemPosition = viewHolder.opCode.getSelectedItemPosition();
        if (selectedItemPosition == 0) {
            try {
                if (Integer.valueOf(Integer.parseInt(viewHolder.objectSize.getText().toString())).intValue() <= 0) {
                    viewHolder.numberOfPackets.setError("Number out of range");
                    return false;
                }
            } catch (Exception unused) {
                viewHolder.numberOfPackets.setError("Number not valid");
                return false;
            }
        } else if (selectedItemPosition == 1) {
            try {
                Integer valueOf = Integer.valueOf(Integer.parseInt(viewHolder.numberOfPackets.getText().toString()));
                if (valueOf.intValue() < 0 || valueOf.intValue() > 65535) {
                    viewHolder.numberOfPackets.setError("Number out of range");
                    return false;
                }
            } catch (Exception unused2) {
                viewHolder.numberOfPackets.setError("Number not valid");
                return false;
            }
        }
        return true;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        ViewHolder viewHolder = (ViewHolder) adapterView.getTag();
        boolean z = true;
        boolean z2 = i == 0;
        viewHolder.objectSizeLabel.setVisibility(z2 ? 0 : 8);
        viewHolder.objectSize.setVisibility(z2 ? 0 : 8);
        boolean z3 = i == 1;
        viewHolder.nopLabel.setVisibility(z3 ? 0 : 8);
        viewHolder.numberOfPackets.setVisibility(z3 ? 0 : 8);
        if (i != 0 && i != 4) {
            z = false;
        }
        viewHolder.objectTypeGroup.setVisibility(z ? 0 : 8);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
        ViewHolder viewHolder = (ViewHolder) adapterView.getTag();
        viewHolder.nopLabel.setVisibility(8);
        viewHolder.numberOfPackets.setVisibility(8);
        viewHolder.objectTypeGroup.setVisibility(8);
    }
}
