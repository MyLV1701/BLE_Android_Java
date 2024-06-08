package no.nordicsemi.android.mcp.ble;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.net.Uri;
import io.runtime.mcumgr.dfu.FirmwareUpgradeManager;
import java.util.List;
import no.nordicsemi.android.mcp.util.PhyHelper;

/* loaded from: classes.dex */
public interface IBluetoothLeBasicConnection {
    public static final int STATE_CLOSED = 5;
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_DISCONNECTING = 3;
    public static final int STATE_DISCONNECTING_AND_CLOSING = 4;
    public static final int STATUS_DISCONNECTED_PEER_USER = 19;
    public static final int STATUS_FAILED_ABORTED = -2;
    public static final int STATUS_FAILED_ASSERT = -4;
    public static final int STATUS_FAILED_ATT_NOT_FOUND = -3;
    public static final int STATUS_FAILED_TIMEOUT = 8;
    public static final int STATUS_FAILED_UNKNOWN_REASON = -1;
    public static final int STATUS_SUCCESS = 0;

    void abortOperation();

    boolean abortReliableWrite();

    boolean beginReliableWrite();

    void connect();

    void connect(boolean z);

    boolean createBond();

    void disconnect();

    void discoverServices();

    boolean executeReliableWrite();

    void flashLog(boolean z);

    int getBondState();

    int getConnectionState();

    PhyHelper.Phy getCurrentPhy();

    String getDeviceName();

    int getLastRemoteRssi();

    Uri getLogSessionUri();

    int getMtu();

    Integer getPreferredPhy();

    List<BluetoothGattService> getServerGattServices(boolean z);

    List<BluetoothGattService> getSupportedGattServices();

    Boolean getWriteExecuteResult();

    boolean hasAutoConnect();

    boolean hasDfuService();

    boolean hasEddystoneConfigService();

    boolean hasIndicationsEnabled(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    boolean hasNotificationsEnabled(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    boolean hasServicesDiscovered();

    boolean isDfuInProgress();

    boolean isMcuMgr();

    boolean isMicrobit();

    boolean isOperationInProgress();

    boolean isReliableWriteInProgress();

    boolean isThingy();

    void onBluetoothOff();

    void onCharacteristicValueSet(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    void onDescriptorValueSet(BluetoothGattDescriptor bluetoothGattDescriptor);

    void onExecuteWrite(boolean z);

    void onNotificationSent(int i);

    void onReadRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    void onReadRequest(BluetoothGattDescriptor bluetoothGattDescriptor);

    void onWriteRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic, BluetoothGattCharacteristic bluetoothGattCharacteristic2);

    void onWriteRequest(BluetoothGattDescriptor bluetoothGattDescriptor, BluetoothGattDescriptor bluetoothGattDescriptor2);

    void readCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    void readDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor);

    boolean readPhy();

    boolean readRemoteRssi();

    boolean refreshCache();

    boolean removeBond();

    boolean requestConnectionPriority(int i);

    boolean requestMtu(int i);

    void saveLogAndFlash(int i, String str);

    void saveLogBulk(int i, String str);

    void saveLogBulk(int i, List<BluetoothGattService> list);

    void sendCharacteristicIndication(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    void sendCharacteristicNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    void setCharacteristicIndication(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z);

    void setCharacteristicNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z);

    void setDeviceName(String str);

    void setMtu(int i);

    boolean setPreferredPhy(int i, int i2, int i3);

    void setWriteType(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i);

    void sleep(long j);

    void sleep(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, boolean z, long j, boolean z2);

    void startDfuUpload(int i, String str, String str2, Uri uri, String str3, Uri uri2);

    void startMcuMgrImageUpload(String str, Uri uri, FirmwareUpgradeManager.Mode mode);

    void waitForExecuteWrite(boolean z);

    void waitForNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    void waitForPhyUpdate();

    void waitForReadRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    void waitForReadRequest(BluetoothGattDescriptor bluetoothGattDescriptor);

    void waitForWriteRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    void waitForWriteRequest(BluetoothGattDescriptor bluetoothGattDescriptor);

    int waitUntilOperationCompleted();

    void writeCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr);

    void writeDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr);
}
