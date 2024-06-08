package no.nordicsemi.android.mcp.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.d;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.BluetoothLeService;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.ble.IBluetoothLeService;
import no.nordicsemi.android.mcp.ble.ServiceConstants;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.EddystoneUtils;

/* loaded from: classes.dex */
public class EddystonePairingDialogFragment extends androidx.fragment.app.c {
    public static final String ARG_DEVICE = "device";
    private IBluetoothLeConnection mConnection;
    private Device mDevice;
    private Button mInfoButton;
    private View mInfoView;
    private TextView mLockCodeView;
    private TextView mNameView;
    private Button mOkButton;
    private final ServiceConnection mServiceConnection = new ServiceConnection() { // from class: no.nordicsemi.android.mcp.fragment.EddystonePairingDialogFragment.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            EddystonePairingDialogFragment eddystonePairingDialogFragment = EddystonePairingDialogFragment.this;
            eddystonePairingDialogFragment.mConnection = ((IBluetoothLeService) iBinder).getConnection(eddystonePairingDialogFragment.mDevice);
            EddystonePairingDialogFragment.this.updateUI();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            EddystonePairingDialogFragment.this.mConnection = null;
            EddystonePairingDialogFragment.this.updateUI();
        }
    };
    private BroadcastReceiver mConnectionStateBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.fragment.EddystonePairingDialogFragment.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().startsWith(ServiceConstants.ACTION_GATT_DISCONNECTED)) {
                EddystonePairingDialogFragment.this.mOkButton.setEnabled(false);
            }
        }
    };

    public static androidx.fragment.app.c getInstance(Device device) {
        EddystonePairingDialogFragment eddystonePairingDialogFragment = new EddystonePairingDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("device", device);
        eddystonePairingDialogFragment.setArguments(bundle);
        return eddystonePairingDialogFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUI() {
        Button button = this.mOkButton;
        if (button != null) {
            IBluetoothLeConnection iBluetoothLeConnection = this.mConnection;
            button.setEnabled(iBluetoothLeConnection != null && iBluetoothLeConnection.isConnected());
        }
    }

    public /* synthetic */ void a(View view) {
        if (this.mConnection == null) {
            return;
        }
        String trim = this.mNameView.getText().toString().trim();
        if (trim.isEmpty()) {
            this.mNameView.setError("The name must not be empty.");
            return;
        }
        this.mNameView.setError(null);
        String charSequence = this.mLockCodeView.getText().toString();
        if (charSequence.isEmpty()) {
            this.mLockCodeView.setError("Lock code is obligatory.");
            return;
        }
        if (!charSequence.matches("[0-9a-fA-F]{32}")) {
            this.mLockCodeView.setError("Value must be 16-byte HEX.");
            return;
        }
        this.mLockCodeView.setError(null);
        EddystoneUtils.storeLockKey(getContext(), charSequence);
        byte[] bArr = new byte[16];
        ParserUtils.setByteArrayValue(bArr, 0, charSequence);
        this.mConnection.registerEddystoneSlot(trim, bArr);
        dismiss();
    }

    public /* synthetic */ void b(View view) {
        this.mInfoView.setVisibility(0);
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDevice = (Device) getArguments().getParcelable("device");
        a.l.a.a.a(requireContext()).a(this.mConnectionStateBroadcastReceiver, new IntentFilter(ServiceConstants.ACTION_GATT_DISCONNECTED + this.mDevice.getAddress(null)));
        requireContext().bindService(new Intent(requireContext(), (Class<?>) BluetoothLeService.class), this.mServiceConnection, 0);
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        d.a aVar = new d.a(requireContext());
        aVar.c(R.string.eddystone_dialog_title);
        View inflate = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_es_pair, (ViewGroup) null);
        this.mInfoView = inflate.findViewById(R.id.info);
        this.mNameView = (TextView) inflate.findViewById(R.id.display_name);
        this.mLockCodeView = (TextView) inflate.findViewById(R.id.lock_code);
        this.mLockCodeView.setText(EddystoneUtils.getLastLockKey(requireContext()));
        aVar.b(inflate);
        aVar.c(R.string.ok, null);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.b(R.string.info, (DialogInterface.OnClickListener) null);
        androidx.appcompat.app.d c2 = aVar.c();
        this.mOkButton = c2.b(-1);
        this.mOkButton.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.fragment.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EddystonePairingDialogFragment.this.a(view);
            }
        });
        this.mInfoButton = c2.b(-3);
        this.mInfoButton.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.fragment.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EddystonePairingDialogFragment.this.b(view);
            }
        });
        updateUI();
        return c2;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        a.l.a.a.a(requireContext()).a(this.mConnectionStateBroadcastReceiver);
        requireContext().unbindService(this.mServiceConnection);
    }
}
