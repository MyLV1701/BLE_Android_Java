package no.nordicsemi.android.mcp.ble.server.impl;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Handler;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;
import no.nordicsemi.android.mcp.ble.server.ServiceMap;
import no.nordicsemi.android.mcp.ble.server.ServiceServerController;
import no.nordicsemi.android.mcp.util.AdoptedCharacteristicsHelper;

/* loaded from: classes.dex */
public class HeartRateServiceImpl extends ServerCallbackAdapter {
    private static final long INTERVAL = 1000;
    private static final String PREFS_ENERGY_EXPENDED = "hrm_energy_expended";
    private int mCounter;
    private float mCumulatedEnergy;
    private final List<BluetoothDevice> mEnabledDevices;
    private final Runnable mNotifyTask;
    private boolean mStarted;

    public HeartRateServiceImpl(Context context, Handler handler, ServiceServerController serviceServerController, ServiceMap serviceMap, BluetoothGattService bluetoothGattService) {
        super(context, handler, serviceServerController, serviceMap, bluetoothGattService);
        this.mNotifyTask = new Runnable() { // from class: no.nordicsemi.android.mcp.ble.server.impl.HeartRateServiceImpl.1
            @Override // java.lang.Runnable
            public void run() {
                if (HeartRateServiceImpl.this.mStarted) {
                    BluetoothGattCharacteristic bluetoothGattCharacteristic = HeartRateServiceImpl.this.getServerService().getCharacteristics().get(0);
                    double d2 = HeartRateServiceImpl.this.mCounter;
                    Double.isNaN(d2);
                    HeartRateServiceImpl.fillHRM(bluetoothGattCharacteristic, false, (int) ((Math.sin(d2 / 60.0d) * 30.0d) + 90.0d), true, HeartRateServiceImpl.this.mCounter % 5 < 3, HeartRateServiceImpl.this.mCounter % 10 == 0, (int) HeartRateServiceImpl.this.mCumulatedEnergy, null);
                    HeartRateServiceImpl heartRateServiceImpl = HeartRateServiceImpl.this;
                    double d3 = heartRateServiceImpl.mCumulatedEnergy;
                    double d4 = HeartRateServiceImpl.this.mCounter;
                    Double.isNaN(d4);
                    double sin = ((Math.sin(d4 / 60.0d) * 7.0d) + 9.0d) / 60.0d;
                    Double.isNaN(d3);
                    heartRateServiceImpl.mCumulatedEnergy = (float) (d3 + sin);
                    HeartRateServiceImpl.access$108(HeartRateServiceImpl.this);
                    HeartRateServiceImpl heartRateServiceImpl2 = HeartRateServiceImpl.this;
                    heartRateServiceImpl2.sendCharacteristicNotification(heartRateServiceImpl2.mEnabledDevices, bluetoothGattCharacteristic, false);
                    HeartRateServiceImpl heartRateServiceImpl3 = HeartRateServiceImpl.this;
                    heartRateServiceImpl3.mHandler.postDelayed(heartRateServiceImpl3.mNotifyTask, HeartRateServiceImpl.INTERVAL);
                }
            }
        };
        this.mEnabledDevices = new ArrayList();
        this.mCumulatedEnergy = PreferenceManager.getDefaultSharedPreferences(context).getFloat(PREFS_ENERGY_EXPENDED, 0.0f);
    }

    static /* synthetic */ int access$108(HeartRateServiceImpl heartRateServiceImpl) {
        int i = heartRateServiceImpl.mCounter;
        heartRateServiceImpl.mCounter = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void fillHRM(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, int i, boolean z2, boolean z3, boolean z4, int i2, int[] iArr) {
        int i3 = z ? 3 : 2;
        if (z4) {
            i3 += 2;
        }
        if (iArr != null) {
            i3 += iArr.length * 2;
        }
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value == null || value.length != i3) {
            value = new byte[i3];
        }
        bluetoothGattCharacteristic.setValue(value);
        int i4 = z ? 1 : 0;
        if (z2 && z3) {
            i4 |= 2;
        }
        if (z2) {
            i4 |= 4;
        }
        if (z4) {
            i4 |= 8;
        }
        if (iArr != null && iArr.length > 0) {
            i4 |= 16;
        }
        bluetoothGattCharacteristic.setValue(i4, 17, 0);
        bluetoothGattCharacteristic.setValue(i, z ? 18 : 17, 1);
        int i5 = z ? 3 : 2;
        if (z4) {
            bluetoothGattCharacteristic.setValue(i2 <= 65535 ? i2 : 65535, 18, i5);
            i5 += 2;
        }
        if (iArr != null) {
            for (int i6 : iArr) {
                bluetoothGattCharacteristic.setValue(i6, 18, i5);
                i5 += 2;
            }
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.server.impl.ServerCallbackAdapter, no.nordicsemi.android.mcp.ble.server.IServerCallback
    public boolean onCharacteristicWriteRequest(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice, int i, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, boolean z2, int i2, byte[] bArr) {
        if (AdoptedCharacteristicsHelper.isHeartRateControlPoint(bluetoothGattCharacteristic.getUuid()) && !z && i2 == 0 && bArr != null && bArr.length == 1 && bArr[0] == 1) {
            this.mCumulatedEnergy = 0.0f;
        }
        return false;
    }

    @Override // no.nordicsemi.android.mcp.ble.server.impl.ServerCallbackAdapter, no.nordicsemi.android.mcp.ble.server.IServerCallback
    public void onConnectionStateChange(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice, int i, int i2) {
        if (i2 == 0) {
            this.mEnabledDevices.remove(bluetoothDevice);
            if (this.mEnabledDevices.isEmpty()) {
                this.mStarted = false;
            }
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.server.impl.ServerCallbackAdapter, no.nordicsemi.android.mcp.ble.server.IServerCallback
    public boolean onDescriptorWriteRequest(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice, int i, BluetoothGattDescriptor bluetoothGattDescriptor, boolean z, boolean z2, int i2, byte[] bArr) {
        if (bArr != null && bArr.length == 2) {
            if (bArr[0] == 1) {
                if (!this.mEnabledDevices.contains(bluetoothDevice)) {
                    this.mEnabledDevices.add(bluetoothDevice);
                }
                if (!this.mStarted) {
                    this.mStarted = true;
                    this.mCounter = 0;
                    this.mHandler.postDelayed(this.mNotifyTask, INTERVAL);
                }
            } else {
                this.mEnabledDevices.remove(bluetoothDevice);
                if (this.mEnabledDevices.isEmpty()) {
                    this.mStarted = false;
                }
            }
        }
        return false;
    }

    @Override // no.nordicsemi.android.mcp.ble.server.impl.ServerCallbackAdapter, no.nordicsemi.android.mcp.ble.server.IServerCallback
    public void onServerClosed() {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putFloat(PREFS_ENERGY_EXPENDED, this.mCumulatedEnergy).apply();
    }
}
