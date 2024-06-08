package no.nordicsemi.android.mcp.test.domain;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import java.util.List;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.exception.TargetInUseException;
import no.nordicsemi.android.mcp.util.PhyHelper;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
/* loaded from: classes.dex */
public class Target {

    @Attribute(required = false)
    private String address;

    @Attribute
    private String id;
    private BluetoothLeBasicConnection mTargetConnection;

    @Attribute(required = false)
    private String name = "Bluetooth LE device";

    public Target(@Attribute(name = "id") String str) {
        this.id = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void abortOperation() {
        this.mTargetConnection.abortOperation();
    }

    public int abortReliableWrite(LogSession logSession) {
        this.mTargetConnection.abortReliableWrite();
        try {
            return this.mTargetConnection.waitUntilOperationCompleted();
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Connection timeout");
            return 8;
        }
    }

    public boolean beginReliableWrite(LogSession logSession) {
        return this.mTargetConnection.beginReliableWrite();
    }

    public int bond(LogSession logSession) {
        this.mTargetConnection.createBond();
        try {
            int waitUntilOperationCompleted = this.mTargetConnection.waitUntilOperationCompleted();
            return waitUntilOperationCompleted != 0 ? waitUntilOperationCompleted : this.mTargetConnection.getBondState() == 12 ? 0 : -1;
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Connection timeout");
            return 8;
        }
    }

    public void close() {
        BluetoothLeBasicConnection bluetoothLeBasicConnection = this.mTargetConnection;
        if (bluetoothLeBasicConnection != null) {
            bluetoothLeBasicConnection.disconnectAndClose(true);
            try {
                this.mTargetConnection.waitUntilOperationCompleted();
            } catch (InterruptedException unused) {
            }
        }
        this.mTargetConnection = null;
    }

    public int connect(Context context, LogSession logSession) {
        this.mTargetConnection.connect();
        try {
            int waitUntilOperationCompleted = this.mTargetConnection.waitUntilOperationCompleted();
            return waitUntilOperationCompleted != 0 ? waitUntilOperationCompleted : this.mTargetConnection.getConnectionState() == 2 ? 0 : -1;
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Connection timeout");
            return 8;
        }
    }

    public int dfu(int i, String str, String str2, String str3, LogSession logSession) {
        this.mTargetConnection.startDfuUpload(i, str, str2, null, str3, null);
        try {
            return this.mTargetConnection.waitUntilOperationCompleted();
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Operation timeout");
            return 8;
        }
    }

    public int disconnect(LogSession logSession) {
        this.mTargetConnection.disconnect();
        try {
            int waitUntilOperationCompleted = this.mTargetConnection.waitUntilOperationCompleted();
            return waitUntilOperationCompleted != 0 ? waitUntilOperationCompleted : (this.mTargetConnection.getConnectionState() == 0 || this.mTargetConnection.getConnectionState() == 5) ? 0 : -1;
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Operation timeout");
            return 8;
        }
    }

    public int discoverServices(LogSession logSession) {
        this.mTargetConnection.discoverServices();
        try {
            int waitUntilOperationCompleted = this.mTargetConnection.waitUntilOperationCompleted();
            return waitUntilOperationCompleted != 0 ? waitUntilOperationCompleted : !this.mTargetConnection.getSupportedGattServices().isEmpty() ? 0 : -1;
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Connection timeout");
            return 8;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean ensureCallback(Context context, DatabaseHelper databaseHelper, ConstantsManager constantsManager, Result result, LogSession logSession) {
        String str = this.address;
        if (str == null) {
            Logger.e(logSession, "Target '" + this.id + "' must be bound before use");
            result.setOperationStatus("Target '" + this.id + "' must be bound before use");
            return false;
        }
        if (this.mTargetConnection != null) {
            return true;
        }
        String decode = constantsManager.decode(str);
        if (!BluetoothAdapter.checkBluetoothAddress(decode)) {
            Logger.e(logSession, decode + " is not a valid Bluetooth address");
            result.setOperationStatus(decode + " is not a valid Bluetooth address");
            return false;
        }
        this.mTargetConnection = new BluetoothLeBasicConnection(context, new Handler(), databaseHelper, BluetoothAdapter.getDefaultAdapter().getRemoteDevice(decode), logSession);
        this.mTargetConnection.setDeviceName(this.name);
        return true;
    }

    public int executeReliableWrite(LogSession logSession) {
        this.mTargetConnection.executeReliableWrite();
        try {
            return this.mTargetConnection.waitUntilOperationCompleted();
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Connection timeout");
            return 8;
        }
    }

    public String getAddress() {
        return this.address;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getId() {
        return this.id;
    }

    public int getMtu() {
        return this.mTargetConnection.getMtu();
    }

    public String getName() {
        return this.name;
    }

    public PhyHelper.Phy getPhy() {
        return this.mTargetConnection.getCurrentPhy();
    }

    public int getRssi() {
        return this.mTargetConnection.getLastRemoteRssi();
    }

    public BluetoothGattService getService(String str) {
        return getService(str, 0);
    }

    public List<BluetoothGattService> getServices() {
        return this.mTargetConnection.getSupportedGattServices();
    }

    public void initWaitForNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic, LogSession logSession) {
        this.mTargetConnection.waitForNotification(bluetoothGattCharacteristic);
    }

    public int read(BluetoothGattCharacteristic bluetoothGattCharacteristic, LogSession logSession) {
        bluetoothGattCharacteristic.setValue((byte[]) null);
        this.mTargetConnection.readCharacteristic(bluetoothGattCharacteristic);
        try {
            return this.mTargetConnection.waitUntilOperationCompleted();
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Connection timeout");
            return 8;
        }
    }

    public int readPhy(LogSession logSession) {
        if (!this.mTargetConnection.readPhy()) {
            return -1;
        }
        try {
            return this.mTargetConnection.waitUntilOperationCompleted();
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Connection timeout");
            return 8;
        }
    }

    public int readRssi(LogSession logSession) {
        this.mTargetConnection.readRemoteRssi();
        try {
            return this.mTargetConnection.waitUntilOperationCompleted();
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Connection timeout");
            return 8;
        }
    }

    public boolean refresh(LogSession logSession) {
        boolean refreshCache = this.mTargetConnection.refreshCache();
        Logger.d(logSession, "wait(1000)");
        synchronized (this) {
            try {
                wait(1000L);
            } catch (InterruptedException unused) {
            }
        }
        return refreshCache;
    }

    public int requestConnectionPriority(int i, LogSession logSession) {
        boolean requestConnectionPriority = this.mTargetConnection.requestConnectionPriority(i);
        if (!requestConnectionPriority || Build.VERSION.SDK_INT < 26) {
            return requestConnectionPriority ? 0 : -1;
        }
        try {
            return this.mTargetConnection.waitUntilOperationCompleted();
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Connection timeout");
            return 8;
        }
    }

    public int requestMtu(int i, LogSession logSession) {
        this.mTargetConnection.requestMtu(i);
        try {
            return this.mTargetConnection.waitUntilOperationCompleted();
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Connection timeout");
            return 8;
        }
    }

    public void setAddress(String str) {
        if (str != null) {
            if (BluetoothAdapter.checkBluetoothAddress(str)) {
                if (this.mTargetConnection != null && !str.equals(this.address)) {
                    int connectionState = this.mTargetConnection.getConnectionState();
                    if (connectionState != 0 && connectionState != 5) {
                        throw new TargetInUseException("Target " + this.id + " is in use and can't be bound to a new address");
                    }
                    close();
                }
                this.address = str;
                return;
            }
            throw new IllegalArgumentException(str + " is not a valid Bluetooth address");
        }
        throw new NullPointerException("Address may not be null");
    }

    public int setPreferredPhy(LogSession logSession, int i, int i2, int i3) {
        if (!this.mTargetConnection.setPreferredPhy(i, i2, i3)) {
            return -1;
        }
        try {
            return this.mTargetConnection.waitUntilOperationCompleted();
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Connection timeout");
            return 8;
        }
    }

    public int sleep(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, boolean z, LogSession logSession) {
        this.mTargetConnection.sleep(bluetoothGattCharacteristic, bArr, z, 0L, false);
        try {
            int waitUntilOperationCompleted = this.mTargetConnection.waitUntilOperationCompleted();
            if (waitUntilOperationCompleted != 0) {
                return waitUntilOperationCompleted;
            }
            return this.mTargetConnection.characteristicValueEquals(bluetoothGattCharacteristic, bArr) == z ? 0 : 8;
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Operation timeout");
            return 8;
        }
    }

    public int unbond(LogSession logSession) {
        this.mTargetConnection.removeBond();
        try {
            int waitUntilOperationCompleted = this.mTargetConnection.waitUntilOperationCompleted();
            return waitUntilOperationCompleted != 0 ? waitUntilOperationCompleted : this.mTargetConnection.getBondState() == 10 ? 0 : -1;
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Connection timeout");
            return 8;
        }
    }

    public int waitForNotification(LogSession logSession) {
        try {
            return this.mTargetConnection.waitUntilOperationCompleted();
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Operation timeout");
            return 8;
        }
    }

    public int write(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, LogSession logSession) {
        this.mTargetConnection.setWriteType(bluetoothGattCharacteristic, i);
        this.mTargetConnection.writeCharacteristic(bluetoothGattCharacteristic, bluetoothGattCharacteristic.getValue());
        try {
            return this.mTargetConnection.waitUntilOperationCompleted();
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Connection timeout");
            return 8;
        }
    }

    public BluetoothGattService getService(String str, int i) {
        List<BluetoothGattService> supportedGattServices = this.mTargetConnection.getSupportedGattServices();
        if (supportedGattServices == null) {
            return null;
        }
        int i2 = 0;
        for (BluetoothGattService bluetoothGattService : supportedGattServices) {
            if (str.equalsIgnoreCase(bluetoothGattService.getUuid().toString())) {
                if (i2 == i) {
                    return bluetoothGattService;
                }
                i2++;
            }
        }
        return null;
    }

    public Target(@Attribute(name = "id") String str, @Attribute(name = "address") String str2) {
        this.id = str;
        this.address = str2;
    }

    public int read(BluetoothGattDescriptor bluetoothGattDescriptor, LogSession logSession) {
        bluetoothGattDescriptor.setValue(null);
        this.mTargetConnection.readDescriptor(bluetoothGattDescriptor);
        try {
            return this.mTargetConnection.waitUntilOperationCompleted();
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Connection timeout");
            return 8;
        }
    }

    public int write(BluetoothGattDescriptor bluetoothGattDescriptor, LogSession logSession) {
        this.mTargetConnection.writeDescriptor(bluetoothGattDescriptor, bluetoothGattDescriptor.getValue());
        try {
            return this.mTargetConnection.waitUntilOperationCompleted();
        } catch (InterruptedException unused) {
            Logger.w(logSession, "Connection timeout");
            return 8;
        }
    }
}
