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
public class UserControlPointDialogBuilder extends WriteDialogBuilder implements AdapterView.OnItemSelectedListener {

    /* loaded from: classes.dex */
    private class ViewHolder {
        private EditText consent;
        private View consentLabel;
        private Spinner opCode;
        private EditText userIndex;
        private View userIndexLabel;

        private ViewHolder() {
        }
    }

    private void setUint16(byte[] bArr, int i, int i2) {
        bArr[i2] = (byte) (i & 255);
        bArr[i2 + 1] = (byte) ((i >> 8) & 255);
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_ucp_control_point, (ViewGroup) null);
        ViewHolder viewHolder = new ViewHolder();
        Spinner spinner = viewHolder.opCode = (Spinner) inflate.findViewById(R.id.value);
        viewHolder.userIndexLabel = inflate.findViewById(R.id.user_index_label);
        viewHolder.userIndex = (EditText) inflate.findViewById(R.id.user_index);
        viewHolder.consentLabel = inflate.findViewById(R.id.consent_label);
        viewHolder.consent = (EditText) inflate.findViewById(R.id.consent);
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
            byte[] bArr = {1, 0, 0};
            setUint16(bArr, Integer.parseInt(viewHolder.consent.getText().toString()), 1);
            return bArr;
        }
        if (selectedItemPosition != 1) {
            return new byte[]{3};
        }
        byte[] bArr2 = {2, 0, 0, 0};
        bArr2[1] = (byte) (Integer.parseInt(viewHolder.userIndex.getText().toString()) & 255);
        setUint16(bArr2, Integer.parseInt(viewHolder.consent.getText().toString()), 2);
        return bArr2;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder.opCode.getSelectedItemPosition() < 2) {
            try {
                Integer valueOf = Integer.valueOf(Integer.parseInt(viewHolder.consent.getText().toString()));
                if (valueOf.intValue() < 0 || valueOf.intValue() > 9999) {
                    viewHolder.consent.setError("Number out of range");
                    return false;
                }
            } catch (Exception unused) {
                viewHolder.consent.setError("Number not valid");
                return false;
            }
        }
        if (viewHolder.opCode.getSelectedItemPosition() == 1) {
            try {
                Integer valueOf2 = Integer.valueOf(Integer.parseInt(viewHolder.userIndex.getText().toString()));
                if (valueOf2.intValue() < 0 || valueOf2.intValue() > 254) {
                    viewHolder.userIndex.setError("Number out of range");
                    return false;
                }
            } catch (Exception unused2) {
                viewHolder.userIndex.setError("Number not valid");
                return false;
            }
        }
        return true;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        ViewHolder viewHolder = (ViewHolder) adapterView.getTag();
        boolean z = i < 2;
        viewHolder.consentLabel.setVisibility(z ? 0 : 8);
        viewHolder.consent.setVisibility(z ? 0 : 8);
        boolean z2 = i == 1;
        viewHolder.userIndexLabel.setVisibility(z2 ? 0 : 8);
        viewHolder.userIndex.setVisibility(z2 ? 0 : 8);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
        ViewHolder viewHolder = (ViewHolder) adapterView.getTag();
        viewHolder.consentLabel.setVisibility(8);
        viewHolder.consent.setVisibility(8);
        viewHolder.userIndex.setVisibility(8);
        viewHolder.userIndexLabel.setVisibility(8);
    }
}
