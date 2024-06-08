package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class DfuControlPointDialogBuilder extends WriteDialogBuilder implements AdapterView.OnItemSelectedListener {

    /* loaded from: classes.dex */
    private class ViewHolder {
        private CheckBox application;
        private CheckBox bootloader;
        private RadioButton initModeComplete;
        private RadioGroup initModeGroup;
        private RadioButton initModeNone;
        private RadioButton initModeStart;
        private View nopLabel;
        private EditText numberOfPackets;
        private Spinner opCode;
        private CheckBox softDevice;
        private View umLabel;

        private ViewHolder() {
        }
    }

    private void setNumberOfPackets(byte[] bArr, int i) {
        bArr[1] = (byte) (i & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_dfu_control_point, (ViewGroup) null);
        ViewHolder viewHolder = new ViewHolder();
        Spinner spinner = viewHolder.opCode = (Spinner) inflate.findViewById(R.id.value);
        viewHolder.nopLabel = inflate.findViewById(R.id.size_info);
        viewHolder.numberOfPackets = (EditText) inflate.findViewById(R.id.size);
        viewHolder.umLabel = inflate.findViewById(R.id.upload_mode_info);
        viewHolder.softDevice = (CheckBox) inflate.findViewById(R.id.soft_device);
        viewHolder.bootloader = (CheckBox) inflate.findViewById(R.id.bootloader);
        viewHolder.application = (CheckBox) inflate.findViewById(R.id.application);
        viewHolder.initModeGroup = (RadioGroup) inflate.findViewById(R.id.init_mode);
        viewHolder.initModeNone = (RadioButton) inflate.findViewById(R.id.init_mode_none);
        viewHolder.initModeStart = (RadioButton) inflate.findViewById(R.id.init_mode_start);
        viewHolder.initModeComplete = (RadioButton) inflate.findViewById(R.id.init_mode_complete);
        inflate.setTag(viewHolder);
        spinner.setOnItemSelectedListener(this);
        spinner.setTag(viewHolder);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        int selectedItemPosition = viewHolder.opCode.getSelectedItemPosition();
        if (selectedItemPosition == 0) {
            byte b2 = viewHolder.softDevice.isChecked() ? (byte) 1 : (byte) 0;
            if (viewHolder.bootloader.isChecked()) {
                b2 = (byte) (b2 | 2);
            }
            if (viewHolder.application.isChecked()) {
                b2 = (byte) (b2 | 4);
            }
            return new byte[]{(byte) (selectedItemPosition + 1), b2};
        }
        if (selectedItemPosition == 1) {
            return viewHolder.initModeNone.isChecked() ? new byte[]{(byte) (selectedItemPosition + 1)} : new byte[]{(byte) (selectedItemPosition + 1), viewHolder.initModeComplete.isChecked()};
        }
        if (selectedItemPosition != 7) {
            return new byte[]{(byte) (selectedItemPosition + 1)};
        }
        byte[] bArr = {8};
        setNumberOfPackets(bArr, Integer.parseInt(viewHolder.numberOfPackets.getText().toString()));
        return bArr;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder.opCode.getSelectedItemPosition() != 7) {
            return true;
        }
        try {
            Integer valueOf = Integer.valueOf(Integer.parseInt(viewHolder.numberOfPackets.getText().toString()));
            if (valueOf.intValue() >= 0 && valueOf.intValue() <= 65535) {
                return true;
            }
            viewHolder.numberOfPackets.setError("Number out of range");
            return false;
        } catch (Exception unused) {
            viewHolder.numberOfPackets.setError("Number not valid");
            return false;
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        ViewHolder viewHolder = (ViewHolder) adapterView.getTag();
        boolean z = i == 7;
        viewHolder.nopLabel.setVisibility(z ? 0 : 8);
        viewHolder.numberOfPackets.setVisibility(z ? 0 : 8);
        boolean z2 = i == 0;
        viewHolder.umLabel.setVisibility(z2 ? 0 : 8);
        viewHolder.softDevice.setVisibility(z2 ? 0 : 8);
        viewHolder.bootloader.setVisibility(z2 ? 0 : 8);
        viewHolder.application.setVisibility(z2 ? 0 : 8);
        viewHolder.initModeGroup.setVisibility(i != 1 ? 8 : 0);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
        ViewHolder viewHolder = (ViewHolder) adapterView.getTag();
        viewHolder.nopLabel.setVisibility(8);
        viewHolder.numberOfPackets.setVisibility(8);
        viewHolder.initModeGroup.setVisibility(8);
    }
}
