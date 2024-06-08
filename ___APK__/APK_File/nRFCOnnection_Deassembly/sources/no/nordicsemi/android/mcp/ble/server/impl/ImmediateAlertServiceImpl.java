package no.nordicsemi.android.mcp.ble.server.impl;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import no.nordicsemi.android.mcp.ble.server.ServiceMap;
import no.nordicsemi.android.mcp.ble.server.ServiceServerController;

/* loaded from: classes.dex */
public class ImmediateAlertServiceImpl extends ServerCallbackAdapter {
    private static final byte HIGH_ALERT = 2;
    private static final byte MILD_ALERT = 1;
    private static final byte NO_ALERT = 0;
    private static final String TAG = "ImmAlertServiceImpl";
    private final List<BluetoothDevice> mHighAlertDevices;
    private final List<BluetoothDevice> mMildAlertDevices;
    private final Ringtone mRingtoneAlarm;
    private final Ringtone mRingtoneNotification;

    public ImmediateAlertServiceImpl(Context context, Handler handler, ServiceServerController serviceServerController, ServiceMap serviceMap, BluetoothGattService bluetoothGattService) {
        super(context, handler, serviceServerController, serviceMap, bluetoothGattService);
        this.mRingtoneAlarm = RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(4));
        this.mRingtoneNotification = RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(2));
        this.mMildAlertDevices = new ArrayList(2);
        this.mHighAlertDevices = new ArrayList(2);
    }

    @Override // no.nordicsemi.android.mcp.ble.server.impl.ServerCallbackAdapter, no.nordicsemi.android.mcp.ble.server.IServerCallback
    public boolean onCharacteristicWriteRequest(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice, int i, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, boolean z2, int i2, byte[] bArr) {
        if (bArr != null && bArr.length == 1) {
            AudioManager audioManager = (AudioManager) getContext().getSystemService("audio");
            byte b2 = bArr[0];
            if (b2 == 0) {
                if (this.mMildAlertDevices.contains(bluetoothDevice)) {
                    this.mRingtoneNotification.stop();
                    this.mMildAlertDevices.remove(bluetoothDevice);
                }
                if (this.mHighAlertDevices.contains(bluetoothDevice)) {
                    this.mRingtoneAlarm.stop();
                    this.mHighAlertDevices.remove(bluetoothDevice);
                }
            } else if (b2 != 1) {
                if (b2 == 2 && !this.mHighAlertDevices.contains(bluetoothDevice)) {
                    if (this.mMildAlertDevices.contains(bluetoothDevice)) {
                        this.mRingtoneNotification.stop();
                        this.mMildAlertDevices.remove(bluetoothDevice);
                    }
                    if (this.mHighAlertDevices.isEmpty()) {
                        audioManager.setStreamVolume(4, audioManager.getStreamMaxVolume(4), 8);
                        this.mRingtoneAlarm.play();
                    }
                    this.mHighAlertDevices.add(bluetoothDevice);
                }
            } else if (!this.mMildAlertDevices.contains(bluetoothDevice)) {
                if (this.mHighAlertDevices.contains(bluetoothDevice)) {
                    this.mRingtoneAlarm.stop();
                    this.mHighAlertDevices.remove(bluetoothDevice);
                }
                if (this.mMildAlertDevices.isEmpty()) {
                    audioManager.setStreamVolume(4, audioManager.getStreamMaxVolume(4), 8);
                    this.mRingtoneNotification.play();
                }
                this.mMildAlertDevices.add(bluetoothDevice);
            }
        }
        return false;
    }

    @Override // no.nordicsemi.android.mcp.ble.server.impl.ServerCallbackAdapter, no.nordicsemi.android.mcp.ble.server.IServerCallback
    public void onConnectionStateChange(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice, int i, int i2) {
        if (i2 == 0) {
            if (this.mMildAlertDevices.contains(bluetoothDevice)) {
                this.mRingtoneNotification.stop();
                this.mMildAlertDevices.remove(bluetoothDevice);
            }
            if (this.mHighAlertDevices.contains(bluetoothDevice)) {
                this.mRingtoneAlarm.stop();
                this.mHighAlertDevices.remove(bluetoothDevice);
            }
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.server.impl.ServerCallbackAdapter, no.nordicsemi.android.mcp.ble.server.IServerCallback
    public void onServerClosed() {
        try {
            this.mHighAlertDevices.clear();
            this.mRingtoneAlarm.stop();
        } catch (Exception e2) {
            Log.w(TAG, "Stopping alarm ringtone failed", e2);
        }
        try {
            this.mMildAlertDevices.clear();
            this.mRingtoneNotification.stop();
        } catch (Exception e3) {
            Log.w(TAG, "Stopping notification ringtone failed", e3);
        }
    }
}
