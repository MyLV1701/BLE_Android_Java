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
import android.widget.RadioGroup;
import androidx.appcompat.app.d;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class SetPreferredPhyDialogFragment extends androidx.fragment.app.c {
    private Button mOkButton;
    private RadioGroup mOptionsGroup;
    private CheckBox mRx1M;
    private CheckBox mRx2M;
    private CheckBox mRxCoded;
    private CheckBox mTx1M;
    private CheckBox mTx2M;
    private CheckBox mTxCoded;

    private void execute() {
        int i = 1;
        int i2 = this.mTx1M.isChecked() ? 1 : 0;
        if (this.mTx2M.isChecked()) {
            i2 |= 2;
        }
        if (this.mTxCoded.isChecked()) {
            i2 |= 4;
        }
        int i3 = this.mRx1M.isChecked() ? 1 : 0;
        if (this.mRx2M.isChecked()) {
            i3 |= 2;
        }
        if (this.mRxCoded.isChecked()) {
            i3 |= 4;
        }
        if (this.mTxCoded.isChecked()) {
            int checkedRadioButtonId = this.mOptionsGroup.getCheckedRadioButtonId();
            if (checkedRadioButtonId != R.id.tx_coding_s2) {
                if (checkedRadioButtonId == R.id.tx_coding_s8) {
                    i = 2;
                }
            }
            ((DeviceDetailsFragment2) getParentFragment()).setPreferredPhy(i2, i3, i);
        }
        i = 0;
        ((DeviceDetailsFragment2) getParentFragment()).setPreferredPhy(i2, i3, i);
    }

    public static androidx.fragment.app.c getInstance() {
        return new SetPreferredPhyDialogFragment();
    }

    private boolean validate() {
        return (this.mTx1M.isChecked() || this.mTx2M.isChecked() || this.mTxCoded.isChecked()) && (this.mRx1M.isChecked() || this.mRx2M.isChecked() || this.mRxCoded.isChecked());
    }

    public /* synthetic */ void a(CompoundButton compoundButton, boolean z) {
        this.mOkButton.setEnabled(validate());
        if (compoundButton == this.mTxCoded) {
            this.mOptionsGroup.setVisibility(z ? 0 : 8);
        }
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        androidx.fragment.app.d activity = getActivity();
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_set_preferred_phy, (ViewGroup) null);
        this.mTx1M = (CheckBox) inflate.findViewById(R.id.tx_le_1m);
        this.mTx2M = (CheckBox) inflate.findViewById(R.id.tx_le_2m);
        this.mTxCoded = (CheckBox) inflate.findViewById(R.id.tx_le_coded);
        this.mOptionsGroup = (RadioGroup) inflate.findViewById(R.id.tx_coding_option);
        this.mRx1M = (CheckBox) inflate.findViewById(R.id.rx_le_1m);
        this.mRx2M = (CheckBox) inflate.findViewById(R.id.rx_le_2m);
        this.mRxCoded = (CheckBox) inflate.findViewById(R.id.rx_le_coded);
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.connection.g0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SetPreferredPhyDialogFragment.this.a(compoundButton, z);
            }
        };
        this.mTx1M.setOnCheckedChangeListener(onCheckedChangeListener);
        this.mTx2M.setOnCheckedChangeListener(onCheckedChangeListener);
        this.mTxCoded.setOnCheckedChangeListener(onCheckedChangeListener);
        this.mRx1M.setOnCheckedChangeListener(onCheckedChangeListener);
        this.mRx2M.setOnCheckedChangeListener(onCheckedChangeListener);
        this.mRxCoded.setOnCheckedChangeListener(onCheckedChangeListener);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mTx2M.setEnabled(defaultAdapter.isLe2MPhySupported());
        this.mRx2M.setEnabled(defaultAdapter.isLe2MPhySupported());
        this.mTxCoded.setEnabled(defaultAdapter.isLeCodedPhySupported());
        this.mRxCoded.setEnabled(defaultAdapter.isLeCodedPhySupported());
        d.a aVar = new d.a(activity);
        aVar.c(R.string.alert_set_preferred_phy_title);
        aVar.b(inflate);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(R.string.ok, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.h0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SetPreferredPhyDialogFragment.this.a(dialogInterface, i);
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
}
