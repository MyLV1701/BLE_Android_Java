package no.nordicsemi.android.mcp.connection;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.appcompat.app.d;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.scanner.ScannerFragment;

/* loaded from: classes.dex */
public class SelectPreferredPhyDialogFragment extends androidx.fragment.app.c {
    public static final String ARG_DEVICE = "device";
    public static final String ARG_PREFERRED_PHY = "phy";
    private Button mOkButton;
    private CheckBox mTx1M;
    private CheckBox mTx2M;
    private CheckBox mTxCoded;

    private void execute() {
        int i = this.mTx1M.isChecked() ? 1 : 0;
        if (this.mTx2M.isChecked()) {
            i |= 2;
        }
        if (this.mTxCoded.isChecked()) {
            i |= 4;
        }
        Device device = (Device) getArguments().getParcelable("device");
        if (device != null) {
            ((ScannerFragment) getParentFragment()).onConnect(device, i);
        } else {
            ((DeviceDetailsFragment2) getParentFragment()).setPreferredPhy(i);
        }
    }

    public static androidx.fragment.app.c getInstance(Device device) {
        SelectPreferredPhyDialogFragment selectPreferredPhyDialogFragment = new SelectPreferredPhyDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("device", device.smallCopy());
        selectPreferredPhyDialogFragment.setArguments(bundle);
        return selectPreferredPhyDialogFragment;
    }

    private boolean validate() {
        return this.mTx1M.isChecked() || this.mTx2M.isChecked() || this.mTxCoded.isChecked();
    }

    public /* synthetic */ void a(CompoundButton compoundButton, boolean z) {
        this.mOkButton.setEnabled(validate());
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        androidx.fragment.app.d activity = getActivity();
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_select_preferred_phy, (ViewGroup) null);
        this.mTx1M = (CheckBox) inflate.findViewById(R.id.tx_le_1m);
        this.mTx2M = (CheckBox) inflate.findViewById(R.id.tx_le_2m);
        this.mTxCoded = (CheckBox) inflate.findViewById(R.id.tx_le_coded);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mTx2M.setEnabled(defaultAdapter.isLe2MPhySupported());
        this.mTxCoded.setEnabled(defaultAdapter.isLeCodedPhySupported());
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(ARG_PREFERRED_PHY)) {
            int i = arguments.getInt(ARG_PREFERRED_PHY);
            this.mTx1M.setChecked((i & 1) > 0);
            this.mTx2M.setChecked((i & 2) > 0);
            this.mTxCoded.setChecked((i & 4) > 0);
        } else {
            CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.connection.f0
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    SelectPreferredPhyDialogFragment.this.a(compoundButton, z);
                }
            };
            this.mTx1M.setOnCheckedChangeListener(onCheckedChangeListener);
            this.mTx2M.setOnCheckedChangeListener(onCheckedChangeListener);
            this.mTxCoded.setOnCheckedChangeListener(onCheckedChangeListener);
        }
        d.a aVar = new d.a(activity);
        aVar.c(R.string.alert_set_initial_preferred_phy_title);
        aVar.b(inflate);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(R.string.ok, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.e0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                SelectPreferredPhyDialogFragment.this.a(dialogInterface, i2);
            }
        });
        androidx.appcompat.app.d c2 = aVar.c();
        c2.setCanceledOnTouchOutside(false);
        this.mOkButton = c2.b(-1);
        return c2;
    }

    public /* synthetic */ void a(DialogInterface dialogInterface, int i) {
        execute();
    }

    public static androidx.fragment.app.c getInstance(Integer num) {
        SelectPreferredPhyDialogFragment selectPreferredPhyDialogFragment = new SelectPreferredPhyDialogFragment();
        Bundle bundle = new Bundle();
        if (num == null) {
            bundle.putInt(ARG_PREFERRED_PHY, 0);
        } else {
            bundle.putInt(ARG_PREFERRED_PHY, num.intValue());
        }
        selectPreferredPhyDialogFragment.setArguments(bundle);
        return selectPreferredPhyDialogFragment;
    }
}
