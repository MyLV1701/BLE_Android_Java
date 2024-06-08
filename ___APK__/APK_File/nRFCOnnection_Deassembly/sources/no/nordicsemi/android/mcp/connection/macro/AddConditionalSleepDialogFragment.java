package no.nordicsemi.android.mcp.connection.macro;

import android.app.Dialog;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.appcompat.app.d;
import java.util.List;
import java.util.UUID;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.ConnectionListener;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.ble.ServiceConstants;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.widget.HexKeyListener;

/* loaded from: classes.dex */
public class AddConditionalSleepDialogFragment extends androidx.fragment.app.c implements View.OnClickListener {
    private static final String CHARACTERISTIC_INSTANCE_ID = "characteristic_instance_id";
    private static final String CHARACTERISTIC_UUID = "characteristic_uuid";
    private static final String DEVICE_ADDRESS = "device_address";
    private static final String SERVER = "server";
    private static final String SERVICE_INSTANCE_ID = "service_instance_id";
    private static final String SERVICE_UUID = "service_uuid";
    private BluetoothGattCharacteristic mCharacteristic;
    private ConnectionListener mListener;
    private Button mOkButton;
    private RadioGroup mRadioGroup;
    private EditText mTimeoutField;
    private EditText mValueField;
    private ConnectionListener.OnServiceConnectedCallback mServiceConnectedCallback = new ConnectionListener.OnServiceConnectedCallback() { // from class: no.nordicsemi.android.mcp.connection.macro.AddConditionalSleepDialogFragment.1
        @Override // no.nordicsemi.android.mcp.ble.ConnectionListener.OnServiceConnectedCallback
        public void onServiceConnected(IBluetoothLeConnection iBluetoothLeConnection) {
            Bundle arguments = AddConditionalSleepDialogFragment.this.getArguments();
            boolean z = arguments.getBoolean(AddConditionalSleepDialogFragment.SERVER);
            UUID uuid = ((ParcelUuid) arguments.getParcelable(AddConditionalSleepDialogFragment.SERVICE_UUID)).getUuid();
            int i = arguments.getInt(AddConditionalSleepDialogFragment.SERVICE_INSTANCE_ID);
            UUID uuid2 = ((ParcelUuid) arguments.getParcelable(AddConditionalSleepDialogFragment.CHARACTERISTIC_UUID)).getUuid();
            int i2 = arguments.getInt(AddConditionalSleepDialogFragment.CHARACTERISTIC_INSTANCE_ID);
            List<BluetoothGattService> supportedGattServices = !z ? iBluetoothLeConnection.getSupportedGattServices() : iBluetoothLeConnection.getServerGattServices(false);
            if (supportedGattServices != null) {
                for (BluetoothGattService bluetoothGattService : supportedGattServices) {
                    if (uuid.equals(bluetoothGattService.getUuid()) && i == bluetoothGattService.getInstanceId() && bluetoothGattService.getCharacteristics() != null) {
                        for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                            if (uuid2.equals(bluetoothGattCharacteristic.getUuid()) && i2 == bluetoothGattCharacteristic.getInstanceId()) {
                                AddConditionalSleepDialogFragment.this.mCharacteristic = bluetoothGattCharacteristic;
                                if (AddConditionalSleepDialogFragment.this.mValueField != null) {
                                    AddConditionalSleepDialogFragment.this.mValueField.setText(ParserUtils.bytesToHex(bluetoothGattCharacteristic.getValue(), false));
                                }
                                if (AddConditionalSleepDialogFragment.this.mOkButton != null) {
                                    AddConditionalSleepDialogFragment.this.mOkButton.setEnabled(iBluetoothLeConnection.isConnected());
                                    return;
                                }
                                return;
                            }
                        }
                    }
                }
            }
            AddConditionalSleepDialogFragment.this.mCharacteristic = null;
            if (AddConditionalSleepDialogFragment.this.mOkButton != null) {
                AddConditionalSleepDialogFragment.this.mOkButton.setEnabled(false);
            }
        }
    };
    private BroadcastReceiver mConnectionStateBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.connection.macro.AddConditionalSleepDialogFragment.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.startsWith(ServiceConstants.ACTION_GATT_DISCONNECTED)) {
                AddConditionalSleepDialogFragment.this.mOkButton.setEnabled(false);
            } else if (action.startsWith(ServiceConstants.ACTION_GATT_SERVICES_DISCOVERED)) {
                AddConditionalSleepDialogFragment.this.mListener.requestConnection(AddConditionalSleepDialogFragment.this.mServiceConnectedCallback);
            }
        }
    };

    /* loaded from: classes.dex */
    public interface Listener {
        void onConditionalSleepAdded(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, long j, boolean z, boolean z2);
    }

    public static AddConditionalSleepDialogFragment getInstance(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        AddConditionalSleepDialogFragment addConditionalSleepDialogFragment = new AddConditionalSleepDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(SERVER, z);
        bundle.putParcelable(SERVICE_UUID, new ParcelUuid(bluetoothGattCharacteristic.getService().getUuid()));
        bundle.putInt(SERVICE_INSTANCE_ID, bluetoothGattCharacteristic.getService().getInstanceId());
        bundle.putParcelable(CHARACTERISTIC_UUID, new ParcelUuid(bluetoothGattCharacteristic.getUuid()));
        bundle.putInt(CHARACTERISTIC_INSTANCE_ID, bluetoothGattCharacteristic.getInstanceId());
        addConditionalSleepDialogFragment.setArguments(bundle);
        return addConditionalSleepDialogFragment;
    }

    private static IntentFilter makeGattUpdateIntentFilter(String str) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ServiceConstants.ACTION_GATT_SERVICES_DISCOVERED + str);
        intentFilter.addAction(ServiceConstants.ACTION_GATT_DISCONNECTED + str);
        return intentFilter;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String obj = this.mValueField.getEditableText().toString();
        if (obj.length() % 2 != 0) {
            this.mValueField.setError(getString(R.string.alert_value_invalid_length));
            return;
        }
        byte[] bArr = new byte[obj.length() / 2];
        ParserUtils.setByteArrayValue(bArr, 0, obj);
        long j = 0;
        try {
            j = Integer.parseInt(this.mTimeoutField.getEditableText().toString());
        } catch (Exception unused) {
        }
        ((Listener) getParentFragment()).onConditionalSleepAdded(this.mCharacteristic, bArr, j, this.mRadioGroup.getCheckedRadioButtonId() == R.id.option_2, getArguments().getBoolean(SERVER));
        dismiss();
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a.l.a.a.a(requireContext()).a(this.mConnectionStateBroadcastReceiver, makeGattUpdateIntentFilter(getArguments().getString(DEVICE_ADDRESS)));
        this.mListener = (ConnectionListener) getParentFragment();
        this.mListener.requestConnection(this.mServiceConnectedCallback);
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        View inflate = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_macro_add_conditional_sleep, (ViewGroup) null);
        this.mRadioGroup = (RadioGroup) inflate;
        this.mValueField = (EditText) inflate.findViewById(R.id.value);
        this.mValueField.setKeyListener(new HexKeyListener());
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.mCharacteristic;
        if (bluetoothGattCharacteristic != null) {
            this.mValueField.setText(ParserUtils.bytesToHex(bluetoothGattCharacteristic.getValue(), false));
        }
        this.mTimeoutField = (EditText) inflate.findViewById(R.id.value_timeout);
        d.a aVar = new d.a(requireContext());
        aVar.c(R.string.macros_dialog_add_conditional_sleep);
        aVar.b(inflate);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(R.string.ok, null);
        androidx.appcompat.app.d c2 = aVar.c();
        this.mOkButton = c2.b(-1);
        this.mOkButton.setOnClickListener(this);
        return c2;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mListener = null;
        a.l.a.a.a(requireContext()).a(this.mConnectionStateBroadcastReceiver);
    }
}
