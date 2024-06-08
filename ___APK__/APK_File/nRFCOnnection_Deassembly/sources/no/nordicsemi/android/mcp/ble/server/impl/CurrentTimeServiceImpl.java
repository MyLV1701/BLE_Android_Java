package no.nordicsemi.android.mcp.ble.server.impl;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;
import java.util.Calendar;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.server.ServiceMap;
import no.nordicsemi.android.mcp.ble.server.ServiceServerController;

/* loaded from: classes.dex */
public class CurrentTimeServiceImpl extends ServerCallbackAdapter {
    private static final UUID CURRENT_TIME_UUID = UUID.fromString("00002A2B-0000-1000-8000-00805F9B34FB");
    private static final UUID LOCAL_TIME_INFORMATION_UUID = UUID.fromString("00002A0F-0000-1000-8000-00805F9B34FB");
    private static final byte REASON_DAYLIGHT_SAVING = 8;
    private static final byte REASON_ETERNAL_REF_UPDATE = 2;
    private static final byte REASON_MANUAL_UPDATE = 1;
    private static final byte REASON_TIME_ZONE_CHANGE = 4;
    private static final byte REASON_UNKNOWN = 0;
    private static final String TAG = "CurrentTimeServiceImpl";
    private final BroadcastReceiver mTimeChangedBroadcastReceiver;

    public CurrentTimeServiceImpl(Context context, Handler handler, ServiceServerController serviceServerController, ServiceMap serviceMap, BluetoothGattService bluetoothGattService) {
        super(context, handler, serviceServerController, serviceMap, bluetoothGattService);
        this.mTimeChangedBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.ble.server.impl.CurrentTimeServiceImpl.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                BluetoothGattCharacteristic characteristic = CurrentTimeServiceImpl.this.getServerService().getCharacteristic(CurrentTimeServiceImpl.CURRENT_TIME_UUID);
                if (characteristic == null) {
                    Log.w(CurrentTimeServiceImpl.TAG, "No Current Time characteristic found");
                    return;
                }
                char c2 = 65535;
                int hashCode = action.hashCode();
                if (hashCode != 502473491) {
                    if (hashCode == 505380757 && action.equals("android.intent.action.TIME_SET")) {
                        c2 = 0;
                    }
                } else if (action.equals("android.intent.action.TIMEZONE_CHANGED")) {
                    c2 = 1;
                }
                if (c2 == 0) {
                    CurrentTimeServiceImpl.this.fillCurrentTime(characteristic, CurrentTimeServiceImpl.REASON_MANUAL_UPDATE);
                    CurrentTimeServiceImpl.this.sendCharacteristicNotification(characteristic, false);
                } else {
                    if (c2 != 1) {
                        return;
                    }
                    CurrentTimeServiceImpl.this.fillCurrentTime(characteristic, CurrentTimeServiceImpl.REASON_TIME_ZONE_CHANGE);
                    CurrentTimeServiceImpl.this.sendCharacteristicNotification(characteristic, false);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        context.registerReceiver(this.mTimeChangedBroadcastReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fillCurrentTime(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte b2) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value == null) {
            value = new byte[10];
        }
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar.get(5);
        int i4 = calendar.get(11);
        int i5 = calendar.get(12);
        int i6 = calendar.get(13);
        int i7 = calendar.get(7) - 1;
        if (i7 <= 0) {
            i7 = 7;
        }
        bluetoothGattCharacteristic.setValue(value);
        bluetoothGattCharacteristic.setValue(i, 18, 0);
        bluetoothGattCharacteristic.setValue(i2, 17, 2);
        bluetoothGattCharacteristic.setValue(i3, 17, 3);
        bluetoothGattCharacteristic.setValue(i4, 17, 4);
        bluetoothGattCharacteristic.setValue(i5, 17, 5);
        bluetoothGattCharacteristic.setValue(i6, 17, 6);
        bluetoothGattCharacteristic.setValue(i7, 17, 7);
        bluetoothGattCharacteristic.setValue((int) (calendar.get(14) * 0.255f), 17, 8);
        bluetoothGattCharacteristic.setValue(b2, 17, 9);
    }

    private void fillLocalTimeInformation(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value == null) {
            value = new byte[2];
        }
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(15) / 900000;
        int i2 = calendar.get(16) / 900000;
        bluetoothGattCharacteristic.setValue(value);
        bluetoothGattCharacteristic.setValue(i, 33, 0);
        bluetoothGattCharacteristic.setValue(i2, 17, 1);
    }

    @Override // no.nordicsemi.android.mcp.ble.server.impl.ServerCallbackAdapter, no.nordicsemi.android.mcp.ble.server.IServerCallback
    public boolean onCharacteristicReadRequest(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice, int i, int i2, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        UUID uuid = bluetoothGattCharacteristic.getUuid();
        if (CURRENT_TIME_UUID.equals(uuid)) {
            fillCurrentTime(bluetoothGattCharacteristic, REASON_UNKNOWN);
        } else if (LOCAL_TIME_INFORMATION_UUID.equals(uuid)) {
            fillLocalTimeInformation(bluetoothGattCharacteristic);
        }
        return false;
    }

    @Override // no.nordicsemi.android.mcp.ble.server.impl.ServerCallbackAdapter, no.nordicsemi.android.mcp.ble.server.IServerCallback
    public void onServerClosed() {
        getContext().unregisterReceiver(this.mTimeChangedBroadcastReceiver);
    }
}
