package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;
import java.io.IOException;
import java.util.UUID;
import java.util.zip.CRC32;
import no.nordicsemi.android.dfu.BaseDfuImpl;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.HexFileValidationException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class BaseCustomDfuImpl extends BaseDfuImpl {
    boolean mFirmwareUploadInProgress;
    private boolean mInitPacketInProgress;
    int mPacketsBeforeNotification;
    private int mPacketsSentSinceNotification;
    boolean mRemoteErrorOccurred;

    /* loaded from: classes.dex */
    class BaseCustomBluetoothCallback extends BaseDfuImpl.BaseBluetoothGattCallback {
        /* JADX INFO: Access modifiers changed from: package-private */
        public BaseCustomBluetoothCallback() {
            super();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void handleNotification(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            BaseCustomDfuImpl.this.mService.sendLogBroadcast(5, "Notification received from " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
            BaseCustomDfuImpl.this.mReceivedData = bluetoothGattCharacteristic.getValue();
            BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void handlePacketReceiptNotification(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            BaseCustomDfuImpl baseCustomDfuImpl = BaseCustomDfuImpl.this;
            if (!baseCustomDfuImpl.mFirmwareUploadInProgress) {
                handleNotification(bluetoothGatt, bluetoothGattCharacteristic);
                return;
            }
            BluetoothGattCharacteristic characteristic = bluetoothGatt.getService(baseCustomDfuImpl.getDfuServiceUUID()).getCharacteristic(BaseCustomDfuImpl.this.getPacketCharacteristicUUID());
            try {
                BaseCustomDfuImpl.this.mPacketsSentSinceNotification = 0;
                BaseCustomDfuImpl.this.waitIfPaused();
                if (!BaseCustomDfuImpl.this.mAborted && BaseCustomDfuImpl.this.mError == 0 && !BaseCustomDfuImpl.this.mRemoteErrorOccurred && !BaseCustomDfuImpl.this.mResetRequestSent) {
                    boolean isComplete = BaseCustomDfuImpl.this.mProgressInfo.isComplete();
                    boolean isObjectComplete = BaseCustomDfuImpl.this.mProgressInfo.isObjectComplete();
                    if (!isComplete && !isObjectComplete) {
                        int availableObjectSizeIsBytes = BaseCustomDfuImpl.this.mProgressInfo.getAvailableObjectSizeIsBytes();
                        byte[] bArr = BaseCustomDfuImpl.this.mBuffer;
                        if (availableObjectSizeIsBytes < bArr.length) {
                            bArr = new byte[availableObjectSizeIsBytes];
                        }
                        BaseCustomDfuImpl.this.writePacket(bluetoothGatt, characteristic, bArr, BaseCustomDfuImpl.this.mFirmwareStream.read(bArr));
                        return;
                    }
                    BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
                    BaseCustomDfuImpl.this.notifyLock();
                    return;
                }
                BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
                BaseCustomDfuImpl.this.mService.sendLogBroadcast(15, "Upload terminated");
            } catch (HexFileValidationException unused) {
                BaseCustomDfuImpl.this.loge("Invalid HEX file");
                BaseCustomDfuImpl.this.mError = DfuBaseService.ERROR_FILE_INVALID;
            } catch (IOException e2) {
                BaseCustomDfuImpl.this.loge("Error while reading the input stream", e2);
                BaseCustomDfuImpl.this.mError = DfuBaseService.ERROR_FILE_IO_EXCEPTION;
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (i == 0) {
                if (bluetoothGattCharacteristic.getUuid().equals(BaseCustomDfuImpl.this.getPacketCharacteristicUUID())) {
                    if (BaseCustomDfuImpl.this.mInitPacketInProgress) {
                        BaseCustomDfuImpl.this.mService.sendLogBroadcast(5, "Data written to " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
                        BaseCustomDfuImpl.this.mInitPacketInProgress = false;
                    } else {
                        BaseCustomDfuImpl baseCustomDfuImpl = BaseCustomDfuImpl.this;
                        if (baseCustomDfuImpl.mFirmwareUploadInProgress) {
                            baseCustomDfuImpl.mProgressInfo.addBytesSent(bluetoothGattCharacteristic.getValue().length);
                            BaseCustomDfuImpl.access$108(BaseCustomDfuImpl.this);
                            BaseCustomDfuImpl baseCustomDfuImpl2 = BaseCustomDfuImpl.this;
                            boolean z = baseCustomDfuImpl2.mPacketsBeforeNotification > 0 && baseCustomDfuImpl2.mPacketsSentSinceNotification >= BaseCustomDfuImpl.this.mPacketsBeforeNotification;
                            boolean isComplete = BaseCustomDfuImpl.this.mProgressInfo.isComplete();
                            boolean isObjectComplete = BaseCustomDfuImpl.this.mProgressInfo.isObjectComplete();
                            if (z) {
                                return;
                            }
                            if (!isComplete && !isObjectComplete) {
                                try {
                                    BaseCustomDfuImpl.this.waitIfPaused();
                                    if (!BaseCustomDfuImpl.this.mAborted && BaseCustomDfuImpl.this.mError == 0 && !BaseCustomDfuImpl.this.mRemoteErrorOccurred && !BaseCustomDfuImpl.this.mResetRequestSent) {
                                        int availableObjectSizeIsBytes = BaseCustomDfuImpl.this.mProgressInfo.getAvailableObjectSizeIsBytes();
                                        byte[] bArr = BaseCustomDfuImpl.this.mBuffer;
                                        if (availableObjectSizeIsBytes < bArr.length) {
                                            bArr = new byte[availableObjectSizeIsBytes];
                                        }
                                        BaseCustomDfuImpl.this.writePacket(bluetoothGatt, bluetoothGattCharacteristic, bArr, BaseCustomDfuImpl.this.mFirmwareStream.read(bArr));
                                        return;
                                    }
                                    BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
                                    BaseCustomDfuImpl.this.mService.sendLogBroadcast(15, "Upload terminated");
                                    BaseCustomDfuImpl.this.notifyLock();
                                    return;
                                } catch (HexFileValidationException unused) {
                                    BaseCustomDfuImpl.this.loge("Invalid HEX file");
                                    BaseCustomDfuImpl.this.mError = DfuBaseService.ERROR_FILE_INVALID;
                                } catch (IOException e2) {
                                    BaseCustomDfuImpl.this.loge("Error while reading the input stream", e2);
                                    BaseCustomDfuImpl.this.mError = DfuBaseService.ERROR_FILE_IO_EXCEPTION;
                                }
                            } else {
                                BaseCustomDfuImpl baseCustomDfuImpl3 = BaseCustomDfuImpl.this;
                                baseCustomDfuImpl3.mFirmwareUploadInProgress = false;
                                baseCustomDfuImpl3.notifyLock();
                                return;
                            }
                        } else {
                            onPacketCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
                        }
                    }
                } else {
                    BaseCustomDfuImpl.this.mService.sendLogBroadcast(5, "Data written to " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
                    BaseCustomDfuImpl.this.mRequestCompleted = true;
                }
            } else {
                BaseCustomDfuImpl baseCustomDfuImpl4 = BaseCustomDfuImpl.this;
                if (baseCustomDfuImpl4.mResetRequestSent) {
                    baseCustomDfuImpl4.mRequestCompleted = true;
                } else {
                    baseCustomDfuImpl4.loge("Characteristic write error: " + i);
                    BaseCustomDfuImpl.this.mError = i | DfuBaseService.ERROR_CONNECTION_MASK;
                }
            }
            BaseCustomDfuImpl.this.notifyLock();
        }

        protected void onPacketCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0050, code lost:
    
        if (r8 <= 65535) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BaseCustomDfuImpl(android.content.Intent r8, no.nordicsemi.android.dfu.DfuBaseService r9) {
        /*
            r7 = this;
            r7.<init>(r8, r9)
            java.lang.String r0 = "no.nordicsemi.android.dfu.extra.EXTRA_PRN_ENABLED"
            boolean r1 = r8.hasExtra(r0)
            r2 = 65535(0xffff, float:9.1834E-41)
            r3 = 1
            r4 = 23
            r5 = 0
            r6 = 12
            if (r1 == 0) goto L30
            int r9 = android.os.Build.VERSION.SDK_INT
            if (r9 >= r4) goto L19
            goto L1a
        L19:
            r3 = 0
        L1a:
            boolean r9 = r8.getBooleanExtra(r0, r3)
            java.lang.String r0 = "no.nordicsemi.android.dfu.extra.EXTRA_PRN_VALUE"
            int r8 = r8.getIntExtra(r0, r6)
            if (r8 < 0) goto L28
            if (r8 <= r2) goto L2a
        L28:
            r8 = 12
        L2a:
            if (r9 != 0) goto L2d
            r8 = 0
        L2d:
            r7.mPacketsBeforeNotification = r8
            goto L59
        L30:
            android.content.SharedPreferences r8 = android.preference.PreferenceManager.getDefaultSharedPreferences(r9)
            int r9 = android.os.Build.VERSION.SDK_INT
            if (r9 >= r4) goto L39
            goto L3a
        L39:
            r3 = 0
        L3a:
            java.lang.String r9 = "settings_packet_receipt_notification_enabled"
            boolean r9 = r8.getBoolean(r9, r3)
            java.lang.String r0 = java.lang.String.valueOf(r6)
            java.lang.String r1 = "settings_number_of_packets"
            java.lang.String r8 = r8.getString(r1, r0)
            int r8 = java.lang.Integer.parseInt(r8)     // Catch: java.lang.NumberFormatException -> L52
            if (r8 < 0) goto L52
            if (r8 <= r2) goto L54
        L52:
            r8 = 12
        L54:
            if (r9 != 0) goto L57
            r8 = 0
        L57:
            r7.mPacketsBeforeNotification = r8
        L59:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.BaseCustomDfuImpl.<init>(android.content.Intent, no.nordicsemi.android.dfu.DfuBaseService):void");
    }

    static /* synthetic */ int access$108(BaseCustomDfuImpl baseCustomDfuImpl) {
        int i = baseCustomDfuImpl.mPacketsSentSinceNotification;
        baseCustomDfuImpl.mPacketsSentSinceNotification = i + 1;
        return i;
    }

    private void writeInitPacket(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i) {
        if (!this.mAborted) {
            if (bArr.length != i) {
                byte[] bArr2 = new byte[i];
                System.arraycopy(bArr, 0, bArr2, 0, i);
                bArr = bArr2;
            }
            this.mReceivedData = null;
            this.mError = 0;
            this.mInitPacketInProgress = true;
            bluetoothGattCharacteristic.setWriteType(1);
            bluetoothGattCharacteristic.setValue(bArr);
            logi("Sending init packet (Value = " + parse(bArr) + ")");
            this.mService.sendLogBroadcast(1, "Writing to characteristic " + bluetoothGattCharacteristic.getUuid());
            this.mService.sendLogBroadcast(0, "gatt.writeCharacteristic(" + bluetoothGattCharacteristic.getUuid() + ")");
            this.mGatt.writeCharacteristic(bluetoothGattCharacteristic);
            try {
                synchronized (this.mLock) {
                    while (true) {
                        if ((!this.mInitPacketInProgress || !this.mConnected || this.mError != 0) && !this.mPaused) {
                            break;
                        } else {
                            this.mLock.wait();
                        }
                    }
                }
            } catch (InterruptedException e2) {
                loge("Sleeping interrupted", e2);
            }
            if (this.mConnected) {
                int i2 = this.mError;
                if (i2 != 0) {
                    throw new DfuException("Unable to write Init DFU Parameters", i2);
                }
                return;
            }
            throw new DeviceDisconnectedException("Unable to write Init DFU Parameters: device disconnected");
        }
        throw new UploadAbortedException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writePacket(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i) {
        if (i <= 0) {
            return;
        }
        if (bArr.length != i) {
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, i);
            bArr = bArr2;
        }
        bluetoothGattCharacteristic.setWriteType(1);
        bluetoothGattCharacteristic.setValue(bArr);
        bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void finalize(Intent intent, boolean z) {
        boolean z2;
        boolean z3 = false;
        boolean booleanExtra = intent.getBooleanExtra(DfuBaseService.EXTRA_KEEP_BOND, false);
        this.mService.refreshDeviceCache(this.mGatt, z || !booleanExtra);
        this.mService.close(this.mGatt);
        if (this.mGatt.getDevice().getBondState() == 12) {
            boolean booleanExtra2 = intent.getBooleanExtra(DfuBaseService.EXTRA_RESTORE_BOND, false);
            if (booleanExtra2 || !booleanExtra) {
                removeBond();
                this.mService.waitFor(2000L);
                z2 = true;
            } else {
                z2 = false;
            }
            if (!booleanExtra2 || (this.mFileType & 4) <= 0) {
                z3 = z2;
            } else {
                createBond();
            }
        }
        if (this.mProgressInfo.isLastPart()) {
            if (!z3) {
                this.mService.waitFor(1400L);
            }
            this.mProgressInfo.setProgress(-6);
            return;
        }
        logi("Starting service that will upload application");
        Intent intent2 = new Intent();
        intent2.fillIn(intent, 24);
        intent2.putExtra(DfuBaseService.EXTRA_FILE_MIME_TYPE, DfuBaseService.MIME_TYPE_ZIP);
        intent2.putExtra(DfuBaseService.EXTRA_FILE_TYPE, 4);
        intent2.putExtra(DfuBaseService.EXTRA_PART_CURRENT, this.mProgressInfo.getCurrentPart() + 1);
        intent2.putExtra(DfuBaseService.EXTRA_PARTS_TOTAL, this.mProgressInfo.getTotalParts());
        restartService(intent2, true);
    }

    protected abstract UUID getControlPointCharacteristicUUID();

    protected abstract UUID getDfuServiceUUID();

    protected abstract UUID getPacketCharacteristicUUID();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void uploadFirmwareImage(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (!this.mAborted) {
            this.mReceivedData = null;
            this.mError = 0;
            this.mFirmwareUploadInProgress = true;
            this.mPacketsSentSinceNotification = 0;
            byte[] bArr = this.mBuffer;
            try {
                int read = this.mFirmwareStream.read(bArr);
                this.mService.sendLogBroadcast(1, "Sending firmware to characteristic " + bluetoothGattCharacteristic.getUuid() + "...");
                writePacket(this.mGatt, bluetoothGattCharacteristic, bArr, read);
                try {
                    synchronized (this.mLock) {
                        while (true) {
                            if ((!this.mFirmwareUploadInProgress || this.mReceivedData != null || !this.mConnected || this.mError != 0) && !this.mPaused) {
                                break;
                            } else {
                                this.mLock.wait();
                            }
                        }
                    }
                } catch (InterruptedException e2) {
                    loge("Sleeping interrupted", e2);
                }
                if (this.mConnected) {
                    int i = this.mError;
                    if (i != 0) {
                        throw new DfuException("Uploading Firmware Image failed", i);
                    }
                    return;
                }
                throw new DeviceDisconnectedException("Uploading Firmware Image failed: device disconnected");
            } catch (HexFileValidationException unused) {
                throw new DfuException("HEX file not valid", DfuBaseService.ERROR_FILE_INVALID);
            } catch (IOException unused2) {
                throw new DfuException("Error while reading file", DfuBaseService.ERROR_FILE_IO_EXCEPTION);
            }
        }
        throw new UploadAbortedException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeInitData(BluetoothGattCharacteristic bluetoothGattCharacteristic, CRC32 crc32) {
        try {
            byte[] bArr = this.mBuffer;
            while (true) {
                int read = this.mInitPacketStream.read(bArr, 0, bArr.length);
                if (read == -1) {
                    return;
                }
                writeInitPacket(bluetoothGattCharacteristic, bArr, read);
                if (crc32 != null) {
                    crc32.update(bArr, 0, read);
                }
            }
        } catch (IOException e2) {
            loge("Error while reading Init packet file", e2);
            throw new DfuException("Error while reading Init packet file", DfuBaseService.ERROR_FILE_ERROR);
        }
    }
}
