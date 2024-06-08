package no.nordicsemi.android.mcp.ble.write;

import android.app.Dialog;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.d;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.ConnectionListener;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.ble.ServiceConstants;
import no.nordicsemi.android.mcp.database.init.ThirdPartyCharacteristics;
import no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException;

/* loaded from: classes.dex */
public abstract class WriteDialogBuilder extends androidx.fragment.app.c {
    private static final String ACTION = "action";
    public static final int ACTION_SEND_NOTIFICATION = 3;
    public static final int ACTION_SET_VALUE = 2;
    public static final int ACTION_WRITE = 1;
    private static final String CHARACTERISTIC_INSTANCE_ID = "characteristic_instance_id";
    private static final String CHARACTERISTIC_UUID = "characteristic_uuid";
    private static final String DESCRIPTOR_INSTANCE_ID = "descriptor_instance_id";
    private static final String DESCRIPTOR_UUID = "descriptor_uuid";
    private static final String DEVICE_ADDRESS = "device_address";
    private static final String SERVICE_INSTANCE_ID = "service_instance_id";
    private static final String SERVICE_UUID = "service_uuid";
    protected int mAction;
    protected BluetoothGattCharacteristic mCharacteristic;
    private IBluetoothLeConnection mConnection;
    protected BluetoothGattDescriptor mDescriptor;
    private ConnectionListener mListener;
    private Button mNeutralButton;
    private Button mOkButton;
    private ConnectionListener.OnServiceConnectedCallback mServiceConnectedCallback = new ConnectionListener.OnServiceConnectedCallback() { // from class: no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder.1
        @Override // no.nordicsemi.android.mcp.ble.ConnectionListener.OnServiceConnectedCallback
        public void onServiceConnected(IBluetoothLeConnection iBluetoothLeConnection) {
            UUID uuid;
            int i;
            WriteDialogBuilder.this.mConnection = iBluetoothLeConnection;
            Bundle arguments = WriteDialogBuilder.this.getArguments();
            UUID uuid2 = ((ParcelUuid) arguments.getParcelable(WriteDialogBuilder.SERVICE_UUID)).getUuid();
            int i2 = arguments.getInt(WriteDialogBuilder.SERVICE_INSTANCE_ID);
            UUID uuid3 = ((ParcelUuid) arguments.getParcelable(WriteDialogBuilder.CHARACTERISTIC_UUID)).getUuid();
            int i3 = arguments.getInt(WriteDialogBuilder.CHARACTERISTIC_INSTANCE_ID);
            ParcelUuid parcelUuid = (ParcelUuid) arguments.getParcelable(WriteDialogBuilder.DESCRIPTOR_UUID);
            int i4 = 0;
            if (parcelUuid != null) {
                uuid = parcelUuid.getUuid();
                i = arguments.getInt(WriteDialogBuilder.DESCRIPTOR_INSTANCE_ID);
            } else {
                uuid = null;
                i = 0;
            }
            List<BluetoothGattService> supportedGattServices = WriteDialogBuilder.this.mAction == 1 ? iBluetoothLeConnection.getSupportedGattServices() : iBluetoothLeConnection.getServerGattServices(false);
            if (supportedGattServices != null) {
                for (BluetoothGattService bluetoothGattService : supportedGattServices) {
                    if (uuid2.equals(bluetoothGattService.getUuid()) && i2 == bluetoothGattService.getInstanceId() && bluetoothGattService.getCharacteristics() != null) {
                        for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                            if (uuid3.equals(bluetoothGattCharacteristic.getUuid()) && i3 == bluetoothGattCharacteristic.getInstanceId()) {
                                if (parcelUuid == null) {
                                    WriteDialogBuilder.this.mCharacteristic = bluetoothGattCharacteristic;
                                } else {
                                    Iterator<BluetoothGattDescriptor> it = bluetoothGattCharacteristic.getDescriptors().iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            break;
                                        }
                                        BluetoothGattDescriptor next = it.next();
                                        if (uuid.equals(next.getUuid())) {
                                            if (i4 == i) {
                                                WriteDialogBuilder.this.mDescriptor = next;
                                                break;
                                            }
                                            i4++;
                                        }
                                    }
                                }
                                if (WriteDialogBuilder.this.mOkButton != null) {
                                    WriteDialogBuilder.this.mOkButton.setEnabled(WriteDialogBuilder.this.mConnection.isConnected());
                                    return;
                                }
                                return;
                            }
                        }
                    }
                }
            }
            WriteDialogBuilder writeDialogBuilder = WriteDialogBuilder.this;
            writeDialogBuilder.mCharacteristic = null;
            writeDialogBuilder.mDescriptor = null;
            if (writeDialogBuilder.mOkButton != null) {
                WriteDialogBuilder.this.mOkButton.setEnabled(false);
            }
        }
    };
    private BroadcastReceiver mConnectionStateBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.startsWith(ServiceConstants.ACTION_GATT_DISCONNECTED)) {
                WriteDialogBuilder.this.mOkButton.setEnabled(false);
            } else if (action.startsWith(ServiceConstants.ACTION_GATT_SERVICES_DISCOVERED)) {
                WriteDialogBuilder.this.mListener.requestConnection(WriteDialogBuilder.this.mServiceConnectedCallback);
            }
        }
    };

    /* loaded from: classes.dex */
    private class DialogListener implements View.OnClickListener {
        private final View mCustomView;
        private final androidx.appcompat.app.d mDialog;
        private final View mMasterView;

        DialogListener(androidx.appcompat.app.d dVar, View view, View view2) {
            this.mDialog = dVar;
            this.mMasterView = view;
            this.mCustomView = view2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            View view2 = this.mCustomView;
            if (!WriteDialogBuilder.this.isValueValid(view2)) {
                WriteDialogBuilder.this.hideSoftKeyboard(view2);
                return;
            }
            byte[] valueFromView = WriteDialogBuilder.this.getValueFromView(view2);
            this.mDialog.dismiss();
            try {
                if (WriteDialogBuilder.this.mCharacteristic != null) {
                    int i = WriteDialogBuilder.this.mAction;
                    if (i != 1) {
                        if (i == 2) {
                            WriteDialogBuilder.this.mCharacteristic.setValue(valueFromView);
                            WriteDialogBuilder.this.mConnection.onCharacteristicValueSet(WriteDialogBuilder.this.mCharacteristic);
                            WriteDialogBuilder.this.mConnection.notifyDatasetChanged(false);
                            return;
                        } else {
                            if (i != 3) {
                                return;
                            }
                            WriteDialogBuilder.this.mCharacteristic.setValue(valueFromView);
                            if (((RadioGroup) this.mMasterView.getTag()).getCheckedRadioButtonId() == R.id.choice_indicate) {
                                WriteDialogBuilder.this.mConnection.sendCharacteristicIndication(WriteDialogBuilder.this.mCharacteristic);
                            } else {
                                WriteDialogBuilder.this.mConnection.sendCharacteristicNotification(WriteDialogBuilder.this.mCharacteristic);
                            }
                            WriteDialogBuilder.this.mConnection.notifyDatasetChanged(false);
                            return;
                        }
                    }
                    RadioGroup radioGroup = (RadioGroup) this.mMasterView.getTag();
                    if (radioGroup.getCheckedRadioButtonId() == R.id.choice_request && WriteDialogBuilder.this.mCharacteristic.getWriteType() != 2) {
                        WriteDialogBuilder.this.mConnection.setWriteType(WriteDialogBuilder.this.mCharacteristic, 2);
                    } else if (radioGroup.getCheckedRadioButtonId() == R.id.choice_command && WriteDialogBuilder.this.mCharacteristic.getWriteType() != 1) {
                        WriteDialogBuilder.this.mConnection.setWriteType(WriteDialogBuilder.this.mCharacteristic, 1);
                    } else if (radioGroup.getCheckedRadioButtonId() == R.id.choice_signed && WriteDialogBuilder.this.mCharacteristic.getWriteType() != 4) {
                        WriteDialogBuilder.this.mConnection.setWriteType(WriteDialogBuilder.this.mCharacteristic, 4);
                    }
                    if (!ThirdPartyCharacteristics.EDDYSTONE_UNLOCK_UUID.equals(WriteDialogBuilder.this.mCharacteristic.getUuid())) {
                        WriteDialogBuilder.this.mConnection.writeCharacteristic(WriteDialogBuilder.this.mCharacteristic, valueFromView);
                        return;
                    } else {
                        WriteDialogBuilder.this.mConnection.unlockEddystone(WriteDialogBuilder.this.mCharacteristic, valueFromView);
                        return;
                    }
                }
                if (WriteDialogBuilder.this.mDescriptor != null) {
                    if (WriteDialogBuilder.this.mAction == 1) {
                        WriteDialogBuilder.this.mConnection.writeDescriptor(WriteDialogBuilder.this.mDescriptor, valueFromView);
                        return;
                    }
                    WriteDialogBuilder.this.mDescriptor.setValue(valueFromView);
                    WriteDialogBuilder.this.mConnection.onDescriptorValueSet(WriteDialogBuilder.this.mDescriptor);
                    WriteDialogBuilder.this.mConnection.notifyDatasetChanged(false);
                }
            } catch (DeviceNotConnectedException unused) {
            }
        }
    }

    /* loaded from: classes.dex */
    private class NeutralButtonListener implements View.OnClickListener {
        private final View mCustomView;
        private final androidx.appcompat.app.d mDialog;

        NeutralButtonListener(androidx.appcompat.app.d dVar, View view) {
            this.mDialog = dVar;
            this.mCustomView = view;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            WriteDialogBuilder.this.onNeutralButtonClick(this.mDialog, this.mCustomView);
        }
    }

    private void configureWriteTypes(RadioGroup radioGroup) {
        if (this.mAction == 3) {
            int i = R.id.choice_indicate;
            radioGroup.findViewById(R.id.choice_indicate).setVisibility(0);
            radioGroup.findViewById(R.id.choice_notify).setVisibility(0);
            BluetoothGattCharacteristic bluetoothGattCharacteristic = this.mCharacteristic;
            if (bluetoothGattCharacteristic != null) {
                if ((bluetoothGattCharacteristic.getProperties() & 32) <= 0) {
                    i = R.id.choice_notify;
                }
                radioGroup.check(i);
                return;
            }
            return;
        }
        int i2 = R.id.choice_request;
        radioGroup.findViewById(R.id.choice_request).setVisibility(0);
        radioGroup.findViewById(R.id.choice_command).setVisibility(0);
        BluetoothGattCharacteristic bluetoothGattCharacteristic2 = this.mCharacteristic;
        if (bluetoothGattCharacteristic2 != null) {
            if ((bluetoothGattCharacteristic2.getProperties() & 64) > 0) {
                radioGroup.findViewById(R.id.choice_signed).setVisibility(0);
            }
            int writeType = this.mCharacteristic.getWriteType();
            if (writeType == 1) {
                i2 = R.id.choice_command;
            } else if (writeType != 2) {
                if (writeType != 4) {
                    i2 = 0;
                } else {
                    radioGroup.findViewById(R.id.choice_signed).setVisibility(0);
                    i2 = R.id.choice_signed;
                }
            }
            radioGroup.check(i2);
        }
    }

    private View createViewInternal(Context context, View view) {
        int i;
        if (this.mDescriptor != null || ((i = this.mAction) != 3 && i != 1)) {
            return view;
        }
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.dialog_write_wrapper, (ViewGroup) null);
        final RadioGroup radioGroup = (RadioGroup) viewGroup.findViewById(R.id.writeType);
        configureWriteTypes(radioGroup);
        ((CheckBox) viewGroup.findViewById(R.id.action_expand)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.ble.write.q
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                radioGroup.setVisibility(r2 ? 0 : 8);
            }
        });
        viewGroup.setTag(radioGroup);
        ((ViewGroup) viewGroup.findViewById(R.id.container)).addView(view, 0);
        return viewGroup;
    }

    private int getDescriptorInstance(BluetoothGattDescriptor bluetoothGattDescriptor) {
        int i = 0;
        for (BluetoothGattDescriptor bluetoothGattDescriptor2 : bluetoothGattDescriptor.getCharacteristic().getDescriptors()) {
            if (bluetoothGattDescriptor2.getUuid().equals(bluetoothGattDescriptor.getUuid())) {
                if (bluetoothGattDescriptor2 == bluetoothGattDescriptor) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    private static IntentFilter makeGattUpdateIntentFilter(String str) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ServiceConstants.ACTION_GATT_SERVICES_DISCOVERED + str);
        intentFilter.addAction(ServiceConstants.ACTION_GATT_DISCONNECTED + str);
        return intentFilter;
    }

    protected View createTitleView(Context context, int i) {
        return null;
    }

    protected abstract View createView(Context context);

    protected int getNeutralButtonTitleResId() {
        return 0;
    }

    protected abstract byte[] getValueFromView(View view);

    protected void hideSoftKeyboard(View view) {
    }

    protected boolean isValueValid(View view) {
        return true;
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mAction = arguments.getInt(ACTION);
        a.l.a.a.a(requireContext()).a(this.mConnectionStateBroadcastReceiver, makeGattUpdateIntentFilter(arguments.getString(DEVICE_ADDRESS)));
        this.mListener = (ConnectionListener) getParentFragment();
        this.mListener.requestConnection(this.mServiceConnectedCallback);
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        int i = this.mAction;
        int i2 = i != 2 ? i != 3 ? R.string.alert_title_write : R.string.alert_title_notify_indicate : R.string.alert_title_set_value;
        Context requireContext = requireContext();
        View createView = createView(requireContext);
        View createViewInternal = createViewInternal(requireContext, createView);
        View createTitleView = createTitleView(requireContext, i2);
        int neutralButtonTitleResId = getNeutralButtonTitleResId();
        d.a aVar = new d.a(requireContext);
        aVar.c(i2);
        aVar.a(createTitleView);
        aVar.b(createViewInternal);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(this.mAction != 2 ? R.string.send : R.string.set, null);
        if (neutralButtonTitleResId > 0) {
            aVar.b(neutralButtonTitleResId, (DialogInterface.OnClickListener) null);
        }
        androidx.appcompat.app.d c2 = aVar.c();
        c2.setCanceledOnTouchOutside(false);
        this.mOkButton = c2.b(-1);
        this.mOkButton.setOnClickListener(new DialogListener(c2, createViewInternal, createView));
        this.mOkButton.setEnabled((this.mCharacteristic == null && this.mDescriptor == null) ? false : true);
        if (neutralButtonTitleResId > 0) {
            Button b2 = c2.b(-3);
            this.mNeutralButton = b2;
            b2.setOnClickListener(new NeutralButtonListener(c2, createViewInternal));
        }
        return c2;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mListener = null;
        a.l.a.a.a(requireContext()).a(this.mConnectionStateBroadcastReceiver);
    }

    protected void onNeutralButtonClick(androidx.appcompat.app.d dVar, View view) {
    }

    public void setAction(int i) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putInt(ACTION, i);
        setArguments(arguments);
    }

    public void setCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putParcelable(SERVICE_UUID, new ParcelUuid(bluetoothGattCharacteristic.getService().getUuid()));
        arguments.putInt(SERVICE_INSTANCE_ID, bluetoothGattCharacteristic.getService().getInstanceId());
        arguments.putParcelable(CHARACTERISTIC_UUID, new ParcelUuid(bluetoothGattCharacteristic.getUuid()));
        arguments.putInt(CHARACTERISTIC_INSTANCE_ID, bluetoothGattCharacteristic.getInstanceId());
        setArguments(arguments);
    }

    public void setDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        BluetoothGattCharacteristic characteristic = bluetoothGattDescriptor.getCharacteristic();
        arguments.putParcelable(SERVICE_UUID, new ParcelUuid(characteristic.getService().getUuid()));
        arguments.putInt(SERVICE_INSTANCE_ID, characteristic.getService().getInstanceId());
        arguments.putParcelable(CHARACTERISTIC_UUID, new ParcelUuid(characteristic.getUuid()));
        arguments.putInt(CHARACTERISTIC_INSTANCE_ID, characteristic.getInstanceId());
        arguments.putParcelable(DESCRIPTOR_UUID, new ParcelUuid(bluetoothGattDescriptor.getUuid()));
        arguments.putInt(DESCRIPTOR_INSTANCE_ID, getDescriptorInstance(bluetoothGattDescriptor));
        setArguments(arguments);
    }

    public void setDeviceAddress(String str) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putString(DEVICE_ADDRESS, str);
        setArguments(arguments);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setNeutralButtonVisible(int i) {
        Button button = this.mNeutralButton;
        if (button != null) {
            button.setVisibility(i);
        }
    }
}
