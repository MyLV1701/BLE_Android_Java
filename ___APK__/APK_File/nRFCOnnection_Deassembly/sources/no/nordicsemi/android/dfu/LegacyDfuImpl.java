package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import java.util.UUID;
import no.nordicsemi.android.dfu.BaseCustomDfuImpl;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.UnknownResponseException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class LegacyDfuImpl extends BaseCustomDfuImpl {
    private static final int DFU_STATUS_SUCCESS = 1;
    private static final int OP_CODE_ACTIVATE_AND_RESET_KEY = 5;
    private static final int OP_CODE_INIT_DFU_PARAMS_KEY = 2;
    private static final int OP_CODE_PACKET_RECEIPT_NOTIF_KEY = 17;
    private static final int OP_CODE_PACKET_RECEIPT_NOTIF_REQ_KEY = 8;
    private static final int OP_CODE_RECEIVE_FIRMWARE_IMAGE_KEY = 3;
    private static final int OP_CODE_RESET_KEY = 6;
    private static final int OP_CODE_RESPONSE_CODE_KEY = 16;
    private static final int OP_CODE_START_DFU_KEY = 1;
    private static final int OP_CODE_VALIDATE_KEY = 4;
    private final LegacyBluetoothCallback mBluetoothCallback;
    private BluetoothGattCharacteristic mControlPointCharacteristic;
    private boolean mImageSizeInProgress;
    private BluetoothGattCharacteristic mPacketCharacteristic;
    static final UUID DEFAULT_DFU_SERVICE_UUID = new UUID(23296205844446L, 1523193452336828707L);
    static final UUID DEFAULT_DFU_CONTROL_POINT_UUID = new UUID(23300500811742L, 1523193452336828707L);
    static final UUID DEFAULT_DFU_PACKET_UUID = new UUID(23304795779038L, 1523193452336828707L);
    static final UUID DEFAULT_DFU_VERSION_UUID = new UUID(23313385713630L, 1523193452336828707L);
    static UUID DFU_SERVICE_UUID = DEFAULT_DFU_SERVICE_UUID;
    static UUID DFU_CONTROL_POINT_UUID = DEFAULT_DFU_CONTROL_POINT_UUID;
    static UUID DFU_PACKET_UUID = DEFAULT_DFU_PACKET_UUID;
    static UUID DFU_VERSION_UUID = DEFAULT_DFU_VERSION_UUID;
    private static final byte[] OP_CODE_START_DFU = {1, 0};
    private static final byte[] OP_CODE_START_DFU_V1 = {1};
    private static final byte[] OP_CODE_INIT_DFU_PARAMS = {2};
    private static final byte[] OP_CODE_INIT_DFU_PARAMS_START = {2, 0};
    private static final byte[] OP_CODE_INIT_DFU_PARAMS_COMPLETE = {2, 1};
    private static final byte[] OP_CODE_RECEIVE_FIRMWARE_IMAGE = {3};
    private static final byte[] OP_CODE_VALIDATE = {4};
    private static final byte[] OP_CODE_ACTIVATE_AND_RESET = {5};
    private static final byte[] OP_CODE_RESET = {6};
    private static final byte[] OP_CODE_PACKET_RECEIPT_NOTIF_REQ = {8, 0, 0};

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public class LegacyBluetoothCallback extends BaseCustomDfuImpl.BaseCustomBluetoothCallback {
        protected LegacyBluetoothCallback() {
            super();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (bluetoothGattCharacteristic.getIntValue(17, 0).intValue() != 17) {
                if (!LegacyDfuImpl.this.mRemoteErrorOccurred) {
                    if (bluetoothGattCharacteristic.getIntValue(17, 2).intValue() != 1) {
                        LegacyDfuImpl.this.mRemoteErrorOccurred = true;
                    }
                    handleNotification(bluetoothGatt, bluetoothGattCharacteristic);
                }
            } else {
                LegacyDfuImpl.this.mProgressInfo.setBytesReceived(bluetoothGattCharacteristic.getIntValue(20, 1).intValue());
                handlePacketReceiptNotification(bluetoothGatt, bluetoothGattCharacteristic);
            }
            LegacyDfuImpl.this.notifyLock();
        }

        @Override // no.nordicsemi.android.dfu.BaseCustomDfuImpl.BaseCustomBluetoothCallback
        protected void onPacketCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (LegacyDfuImpl.this.mImageSizeInProgress) {
                LegacyDfuImpl.this.mService.sendLogBroadcast(5, "Data written to " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
                LegacyDfuImpl.this.mImageSizeInProgress = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LegacyDfuImpl(Intent intent, DfuBaseService dfuBaseService) {
        super(intent, dfuBaseService);
        this.mBluetoothCallback = new LegacyBluetoothCallback();
    }

    private int getStatusCode(byte[] bArr, int i) {
        if (bArr != null && bArr.length == 3 && bArr[0] == 16 && bArr[1] == i && bArr[2] >= 1 && bArr[2] <= 6) {
            return bArr[2];
        }
        throw new UnknownResponseException("Invalid response received", bArr, 16, i);
    }

    private int readVersion(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic != null) {
            return bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        }
        return 0;
    }

    private void resetAndRestart(BluetoothGatt bluetoothGatt, Intent intent) {
        this.mService.sendLogBroadcast(15, "Last upload interrupted. Restarting device...");
        this.mProgressInfo.setProgress(-5);
        logi("Sending Reset command (Op Code = 6)");
        writeOpCode(this.mControlPointCharacteristic, OP_CODE_RESET);
        this.mService.sendLogBroadcast(10, "Reset request sent");
        this.mService.waitUntilDisconnected();
        this.mService.sendLogBroadcast(5, "Disconnected by the remote device");
        BluetoothGattService service = bluetoothGatt.getService(BaseDfuImpl.GENERIC_ATTRIBUTE_SERVICE_UUID);
        this.mService.refreshDeviceCache(bluetoothGatt, !((service == null || service.getCharacteristic(BaseDfuImpl.SERVICE_CHANGED_UUID) == null) ? false : true));
        this.mService.close(bluetoothGatt);
        logi("Restarting the service");
        Intent intent2 = new Intent();
        intent2.fillIn(intent, 24);
        restartService(intent2, false);
    }

    private void setNumberOfPackets(byte[] bArr, int i) {
        bArr[1] = (byte) (i & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
    }

    private void writeImageSize(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        this.mReceivedData = null;
        this.mError = 0;
        this.mImageSizeInProgress = true;
        bluetoothGattCharacteristic.setWriteType(1);
        bluetoothGattCharacteristic.setValue(new byte[4]);
        bluetoothGattCharacteristic.setValue(i, 20, 0);
        this.mService.sendLogBroadcast(1, "Writing to characteristic " + bluetoothGattCharacteristic.getUuid());
        this.mService.sendLogBroadcast(0, "gatt.writeCharacteristic(" + bluetoothGattCharacteristic.getUuid() + ")");
        this.mGatt.writeCharacteristic(bluetoothGattCharacteristic);
        try {
            synchronized (this.mLock) {
                while (true) {
                    if ((!this.mImageSizeInProgress || !this.mConnected || this.mError != 0 || this.mAborted) && !this.mPaused) {
                        break;
                    } else {
                        this.mLock.wait();
                    }
                }
            }
        } catch (InterruptedException e2) {
            loge("Sleeping interrupted", e2);
        }
        if (!this.mAborted) {
            if (this.mConnected) {
                int i2 = this.mError;
                if (i2 != 0) {
                    throw new DfuException("Unable to write Image Size", i2);
                }
                return;
            }
            throw new DeviceDisconnectedException("Unable to write Image Size: device disconnected");
        }
        throw new UploadAbortedException();
    }

    private void writeOpCode(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        writeOpCode(bluetoothGattCharacteristic, bArr, bArr[0] == 6 || bArr[0] == 5);
    }

    @Override // no.nordicsemi.android.dfu.BaseCustomDfuImpl
    protected UUID getControlPointCharacteristicUUID() {
        return DFU_CONTROL_POINT_UUID;
    }

    @Override // no.nordicsemi.android.dfu.BaseCustomDfuImpl
    protected UUID getDfuServiceUUID() {
        return DFU_SERVICE_UUID;
    }

    @Override // no.nordicsemi.android.dfu.BaseCustomDfuImpl
    protected UUID getPacketCharacteristicUUID() {
        return DFU_PACKET_UUID;
    }

    @Override // no.nordicsemi.android.dfu.DfuService
    public boolean isClientCompatible(Intent intent, BluetoothGatt bluetoothGatt) {
        BluetoothGattCharacteristic characteristic;
        BluetoothGattService service = bluetoothGatt.getService(DFU_SERVICE_UUID);
        if (service == null || (characteristic = service.getCharacteristic(DFU_CONTROL_POINT_UUID)) == null || characteristic.getDescriptor(BaseDfuImpl.CLIENT_CHARACTERISTIC_CONFIG) == null) {
            return false;
        }
        this.mControlPointCharacteristic = characteristic;
        this.mPacketCharacteristic = service.getCharacteristic(DFU_PACKET_UUID);
        return this.mPacketCharacteristic != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x02e1 A[Catch: UnknownResponseException -> 0x02e2, UploadAbortedException -> 0x02ea, RemoteDfuException -> 0x02f2, TRY_LEAVE, TryCatch #3 {RemoteDfuException -> 0x02f2, blocks: (B:95:0x01d5, B:98:0x01de, B:100:0x01e2, B:102:0x02d0, B:106:0x02da, B:107:0x02df, B:109:0x02e0, B:110:0x02e1), top: B:94:0x01d5 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x03a6 A[Catch: UnknownResponseException -> 0x02e2, UploadAbortedException -> 0x02ea, RemoteDfuException -> 0x0644, TryCatch #7 {RemoteDfuException -> 0x0644, blocks: (B:48:0x03a2, B:50:0x03a6, B:52:0x03b1, B:53:0x0421, B:56:0x0450, B:57:0x0457, B:58:0x03f4, B:60:0x045a, B:62:0x045e, B:67:0x046c, B:68:0x04ac, B:70:0x04cb, B:71:0x04de, B:73:0x053c, B:75:0x05f0, B:78:0x061f, B:81:0x0624, B:82:0x062b, B:83:0x062c, B:84:0x0633, B:87:0x0635, B:88:0x063b, B:89:0x0468, B:114:0x02f4, B:118:0x02fe, B:120:0x039a, B:125:0x063c, B:126:0x0641, B:127:0x0642, B:128:0x0643), top: B:113:0x02f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x046c A[Catch: UnknownResponseException -> 0x02e2, UploadAbortedException -> 0x02ea, RemoteDfuException -> 0x0644, TryCatch #7 {RemoteDfuException -> 0x0644, blocks: (B:48:0x03a2, B:50:0x03a6, B:52:0x03b1, B:53:0x0421, B:56:0x0450, B:57:0x0457, B:58:0x03f4, B:60:0x045a, B:62:0x045e, B:67:0x046c, B:68:0x04ac, B:70:0x04cb, B:71:0x04de, B:73:0x053c, B:75:0x05f0, B:78:0x061f, B:81:0x0624, B:82:0x062b, B:83:0x062c, B:84:0x0633, B:87:0x0635, B:88:0x063b, B:89:0x0468, B:114:0x02f4, B:118:0x02fe, B:120:0x039a, B:125:0x063c, B:126:0x0641, B:127:0x0642, B:128:0x0643), top: B:113:0x02f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x053c A[Catch: UnknownResponseException -> 0x02e2, UploadAbortedException -> 0x02ea, RemoteDfuException -> 0x0644, TryCatch #7 {RemoteDfuException -> 0x0644, blocks: (B:48:0x03a2, B:50:0x03a6, B:52:0x03b1, B:53:0x0421, B:56:0x0450, B:57:0x0457, B:58:0x03f4, B:60:0x045a, B:62:0x045e, B:67:0x046c, B:68:0x04ac, B:70:0x04cb, B:71:0x04de, B:73:0x053c, B:75:0x05f0, B:78:0x061f, B:81:0x0624, B:82:0x062b, B:83:0x062c, B:84:0x0633, B:87:0x0635, B:88:0x063b, B:89:0x0468, B:114:0x02f4, B:118:0x02fe, B:120:0x039a, B:125:0x063c, B:126:0x0641, B:127:0x0642, B:128:0x0643), top: B:113:0x02f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x062c A[Catch: UnknownResponseException -> 0x02e2, UploadAbortedException -> 0x02ea, RemoteDfuException -> 0x0644, TryCatch #7 {RemoteDfuException -> 0x0644, blocks: (B:48:0x03a2, B:50:0x03a6, B:52:0x03b1, B:53:0x0421, B:56:0x0450, B:57:0x0457, B:58:0x03f4, B:60:0x045a, B:62:0x045e, B:67:0x046c, B:68:0x04ac, B:70:0x04cb, B:71:0x04de, B:73:0x053c, B:75:0x05f0, B:78:0x061f, B:81:0x0624, B:82:0x062b, B:83:0x062c, B:84:0x0633, B:87:0x0635, B:88:0x063b, B:89:0x0468, B:114:0x02f4, B:118:0x02fe, B:120:0x039a, B:125:0x063c, B:126:0x0641, B:127:0x0642, B:128:0x0643), top: B:113:0x02f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01dc  */
    @Override // no.nordicsemi.android.dfu.DfuService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void performDfu(android.content.Intent r23) {
        /*
            Method dump skipped, instructions count: 1782
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.LegacyDfuImpl.performDfu(android.content.Intent):void");
    }

    @Override // no.nordicsemi.android.dfu.DfuCallback
    public BaseCustomDfuImpl.BaseCustomBluetoothCallback getGattCallback() {
        return this.mBluetoothCallback;
    }

    private void writeImageSize(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, int i2, int i3) {
        this.mReceivedData = null;
        this.mError = 0;
        this.mImageSizeInProgress = true;
        bluetoothGattCharacteristic.setWriteType(1);
        bluetoothGattCharacteristic.setValue(new byte[12]);
        bluetoothGattCharacteristic.setValue(i, 20, 0);
        bluetoothGattCharacteristic.setValue(i2, 20, 4);
        bluetoothGattCharacteristic.setValue(i3, 20, 8);
        this.mService.sendLogBroadcast(1, "Writing to characteristic " + bluetoothGattCharacteristic.getUuid());
        this.mService.sendLogBroadcast(0, "gatt.writeCharacteristic(" + bluetoothGattCharacteristic.getUuid() + ")");
        this.mGatt.writeCharacteristic(bluetoothGattCharacteristic);
        try {
            synchronized (this.mLock) {
                while (true) {
                    if ((!this.mImageSizeInProgress || !this.mConnected || this.mError != 0 || this.mAborted) && !this.mPaused) {
                        break;
                    } else {
                        this.mLock.wait();
                    }
                }
            }
        } catch (InterruptedException e2) {
            loge("Sleeping interrupted", e2);
        }
        if (!this.mAborted) {
            if (this.mConnected) {
                int i4 = this.mError;
                if (i4 != 0) {
                    throw new DfuException("Unable to write Image Sizes", i4);
                }
                return;
            }
            throw new DeviceDisconnectedException("Unable to write Image Sizes: device disconnected");
        }
        throw new UploadAbortedException();
    }
}
