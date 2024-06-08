package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import android.os.SystemClock;
import e.a.a.a.c;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.UUID;
import no.nordicsemi.android.dfu.BaseCustomDfuImpl;
import no.nordicsemi.android.dfu.BaseDfuImpl;
import no.nordicsemi.android.dfu.internal.ArchiveInputStream;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.RemoteDfuException;
import no.nordicsemi.android.dfu.internal.exception.RemoteDfuExtendedErrorException;
import no.nordicsemi.android.dfu.internal.exception.UnknownResponseException;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;
import no.nordicsemi.android.mcp.database.DatabaseUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class SecureDfuImpl extends BaseCustomDfuImpl {
    private static final int DFU_STATUS_SUCCESS = 1;
    private static final int MAX_ATTEMPTS = 3;
    private static final int OBJECT_COMMAND = 1;
    private static final int OBJECT_DATA = 2;
    private static final int OP_CODE_CALCULATE_CHECKSUM_KEY = 3;
    private static final int OP_CODE_CREATE_KEY = 1;
    private static final int OP_CODE_EXECUTE_KEY = 4;
    private static final int OP_CODE_PACKET_RECEIPT_NOTIF_REQ_KEY = 2;
    private static final int OP_CODE_RESPONSE_CODE_KEY = 96;
    private static final int OP_CODE_SELECT_OBJECT_KEY = 6;
    private final SecureBluetoothCallback mBluetoothCallback;
    private BluetoothGattCharacteristic mControlPointCharacteristic;
    private BluetoothGattCharacteristic mPacketCharacteristic;
    static final UUID DEFAULT_DFU_SERVICE_UUID = new UUID(279658205548544L, DatabaseUtils.LSB);
    static final UUID DEFAULT_DFU_CONTROL_POINT_UUID = new UUID(-8157989241631715488L, -6937650605005804976L);
    static final UUID DEFAULT_DFU_PACKET_UUID = new UUID(-8157989237336748192L, -6937650605005804976L);
    static UUID DFU_SERVICE_UUID = DEFAULT_DFU_SERVICE_UUID;
    static UUID DFU_CONTROL_POINT_UUID = DEFAULT_DFU_CONTROL_POINT_UUID;
    static UUID DFU_PACKET_UUID = DEFAULT_DFU_PACKET_UUID;
    private static final byte[] OP_CODE_CREATE_COMMAND = {1, 1, 0, 0, 0, 0};
    private static final byte[] OP_CODE_CREATE_DATA = {1, 2, 0, 0, 0, 0};
    private static final byte[] OP_CODE_PACKET_RECEIPT_NOTIF_REQ = {2, 0, 0};
    private static final byte[] OP_CODE_CALCULATE_CHECKSUM = {3};
    private static final byte[] OP_CODE_EXECUTE = {4};
    private static final byte[] OP_CODE_SELECT_OBJECT = {6, 0};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ObjectChecksum {
        int CRC32;
        int offset;

        private ObjectChecksum() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ObjectInfo extends ObjectChecksum {
        int maxSize;

        private ObjectInfo() {
            super();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public class SecureBluetoothCallback extends BaseCustomDfuImpl.BaseCustomBluetoothCallback {
        protected SecureBluetoothCallback() {
            super();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (bluetoothGattCharacteristic.getValue() != null && bluetoothGattCharacteristic.getValue().length >= 3) {
                if (bluetoothGattCharacteristic.getIntValue(17, 0).intValue() == 96) {
                    if (bluetoothGattCharacteristic.getIntValue(17, 1).intValue() != 3) {
                        if (!SecureDfuImpl.this.mRemoteErrorOccurred) {
                            if (bluetoothGattCharacteristic.getIntValue(17, 2).intValue() != 1) {
                                SecureDfuImpl.this.mRemoteErrorOccurred = true;
                            }
                            handleNotification(bluetoothGatt, bluetoothGattCharacteristic);
                        }
                    } else {
                        int intValue = bluetoothGattCharacteristic.getIntValue(20, 3).intValue();
                        if (((int) (((ArchiveInputStream) SecureDfuImpl.this.mFirmwareStream).getCrc32() & 4294967295L)) == bluetoothGattCharacteristic.getIntValue(20, 7).intValue()) {
                            SecureDfuImpl.this.mProgressInfo.setBytesReceived(intValue);
                        } else {
                            SecureDfuImpl secureDfuImpl = SecureDfuImpl.this;
                            if (secureDfuImpl.mFirmwareUploadInProgress) {
                                secureDfuImpl.mFirmwareUploadInProgress = false;
                                secureDfuImpl.notifyLock();
                                return;
                            }
                        }
                        handlePacketReceiptNotification(bluetoothGatt, bluetoothGattCharacteristic);
                    }
                } else {
                    SecureDfuImpl.this.loge("Invalid response: " + parse(bluetoothGattCharacteristic));
                    SecureDfuImpl.this.mError = DfuBaseService.ERROR_INVALID_RESPONSE;
                }
                SecureDfuImpl.this.notifyLock();
                return;
            }
            SecureDfuImpl.this.loge("Empty response: " + parse(bluetoothGattCharacteristic));
            SecureDfuImpl secureDfuImpl2 = SecureDfuImpl.this;
            secureDfuImpl2.mError = DfuBaseService.ERROR_INVALID_RESPONSE;
            secureDfuImpl2.notifyLock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SecureDfuImpl(Intent intent, DfuBaseService dfuBaseService) {
        super(intent, dfuBaseService);
        this.mBluetoothCallback = new SecureBluetoothCallback();
    }

    private int getStatusCode(byte[] bArr, int i) {
        if (bArr != null && bArr.length >= 3 && bArr[0] == 96 && bArr[1] == i && (bArr[2] == 1 || bArr[2] == 2 || bArr[2] == 3 || bArr[2] == 4 || bArr[2] == 5 || bArr[2] == 7 || bArr[2] == 8 || bArr[2] == 10 || bArr[2] == 11)) {
            return bArr[2];
        }
        throw new UnknownResponseException("Invalid response received", bArr, 96, i);
    }

    private ObjectChecksum readChecksum() {
        if (this.mConnected) {
            writeOpCode(this.mControlPointCharacteristic, OP_CODE_CALCULATE_CHECKSUM);
            byte[] readNotificationResponse = readNotificationResponse();
            int statusCode = getStatusCode(readNotificationResponse, 3);
            if (statusCode == 11) {
                throw new RemoteDfuExtendedErrorException("Receiving Checksum failed", readNotificationResponse[3]);
            }
            if (statusCode == 1) {
                ObjectChecksum objectChecksum = new ObjectChecksum();
                objectChecksum.offset = unsignedBytesToInt(readNotificationResponse, 3);
                objectChecksum.CRC32 = unsignedBytesToInt(readNotificationResponse, 7);
                return objectChecksum;
            }
            throw new RemoteDfuException("Receiving Checksum failed", statusCode);
        }
        throw new DeviceDisconnectedException("Unable to read Checksum: device disconnected");
    }

    private ObjectInfo selectObject(int i) {
        if (this.mConnected) {
            byte[] bArr = OP_CODE_SELECT_OBJECT;
            bArr[1] = (byte) i;
            writeOpCode(this.mControlPointCharacteristic, bArr);
            byte[] readNotificationResponse = readNotificationResponse();
            int statusCode = getStatusCode(readNotificationResponse, 6);
            if (statusCode == 11) {
                throw new RemoteDfuExtendedErrorException("Selecting object failed", readNotificationResponse[3]);
            }
            if (statusCode == 1) {
                ObjectInfo objectInfo = new ObjectInfo();
                objectInfo.maxSize = unsignedBytesToInt(readNotificationResponse, 3);
                objectInfo.offset = unsignedBytesToInt(readNotificationResponse, 7);
                objectInfo.CRC32 = unsignedBytesToInt(readNotificationResponse, 11);
                return objectInfo;
            }
            throw new RemoteDfuException("Selecting object failed", statusCode);
        }
        throw new DeviceDisconnectedException("Unable to read object info: device disconnected");
    }

    private void sendFirmware(BluetoothGatt bluetoothGatt) {
        int i;
        boolean z;
        String str;
        String str2;
        int i2 = this.mPacketsBeforeNotification;
        String str3 = ")";
        if (i2 > 0) {
            setPacketReceiptNotifications(i2);
            this.mService.sendLogBroadcast(10, "Packet Receipt Notif Req (Op Code = 2) sent (Value = " + i2 + ")");
        }
        logi("Setting object to Data (Op Code = 6, Type = 2)");
        ObjectInfo selectObject = selectObject(2);
        logi(String.format(Locale.US, "Data object info received (Max size = %d, Offset = %d, CRC = %08X)", Integer.valueOf(selectObject.maxSize), Integer.valueOf(selectObject.offset), Integer.valueOf(selectObject.CRC32)));
        this.mService.sendLogBroadcast(10, String.format(Locale.US, "Data object info received (Max size = %d, Offset = %d, CRC = %08X)", Integer.valueOf(selectObject.maxSize), Integer.valueOf(selectObject.offset), Integer.valueOf(selectObject.CRC32)));
        this.mProgressInfo.setMaxObjectSizeInBytes(selectObject.maxSize);
        int i3 = this.mImageSizeInBytes;
        int i4 = selectObject.maxSize;
        int i5 = ((i3 + i4) - 1) / i4;
        int i6 = selectObject.offset;
        if (i6 > 0) {
            try {
                i = i6 / i4;
                int i7 = i4 * i;
                int i8 = i6 - i7;
                if (i8 == 0) {
                    i7 -= i4;
                    i8 = i4;
                }
                int i9 = i7;
                if (i9 > 0) {
                    this.mFirmwareStream.read(new byte[i9]);
                    this.mFirmwareStream.mark(selectObject.maxSize);
                }
                this.mFirmwareStream.read(new byte[i8]);
                if (((int) (((ArchiveInputStream) this.mFirmwareStream).getCrc32() & 4294967295L)) == selectObject.CRC32) {
                    logi(selectObject.offset + " bytes of data sent before, CRC match");
                    this.mService.sendLogBroadcast(10, selectObject.offset + " bytes of data sent before, CRC match");
                    this.mProgressInfo.setBytesSent(selectObject.offset);
                    this.mProgressInfo.setBytesReceived(selectObject.offset);
                    if (i8 != selectObject.maxSize || selectObject.offset >= this.mImageSizeInBytes) {
                        z = true;
                    } else {
                        logi("Executing data object (Op Code = 4)");
                        writeExecute();
                        this.mService.sendLogBroadcast(10, "Data object executed");
                    }
                } else {
                    logi(selectObject.offset + " bytes sent before, CRC does not match");
                    this.mService.sendLogBroadcast(15, selectObject.offset + " bytes sent before, CRC does not match");
                    this.mProgressInfo.setBytesSent(i9);
                    this.mProgressInfo.setBytesReceived(i9);
                    selectObject.offset = selectObject.offset - i8;
                    selectObject.CRC32 = 0;
                    this.mFirmwareStream.reset();
                    logi("Resuming from byte " + selectObject.offset + "...");
                    this.mService.sendLogBroadcast(10, "Resuming from byte " + selectObject.offset + "...");
                }
                z = false;
            } catch (IOException e2) {
                loge("Error while reading firmware stream", e2);
                this.mService.terminateConnection(bluetoothGatt, DfuBaseService.ERROR_FILE_IO_EXCEPTION);
                return;
            }
        } else {
            this.mProgressInfo.setBytesSent(0);
            z = false;
            i = 0;
        }
        String str4 = "Data object executed";
        long elapsedRealtime = SystemClock.elapsedRealtime() + 400;
        if (selectObject.offset < this.mImageSizeInBytes) {
            int i10 = 1;
            while (this.mProgressInfo.getAvailableObjectSizeIsBytes() > 0) {
                if (!z) {
                    int availableObjectSizeIsBytes = this.mProgressInfo.getAvailableObjectSizeIsBytes();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Creating Data object (Op Code = 1, Type = 2, Size = ");
                    sb.append(availableObjectSizeIsBytes);
                    sb.append(") (");
                    int i11 = i + 1;
                    sb.append(i11);
                    sb.append("/");
                    sb.append(i5);
                    sb.append(str3);
                    logi(sb.toString());
                    writeCreateRequest(2, availableObjectSizeIsBytes);
                    this.mService.sendLogBroadcast(10, "Data object (" + i11 + "/" + i5 + ") created");
                    if (i == 0) {
                        this.mService.waitFor(400L);
                    }
                    this.mService.sendLogBroadcast(10, "Uploading firmware...");
                } else {
                    this.mService.sendLogBroadcast(10, "Resuming uploading firmware...");
                    z = false;
                }
                try {
                    logi("Uploading firmware...");
                    uploadFirmwareImage(this.mPacketCharacteristic);
                    logi("Sending Calculate Checksum command (Op Code = 3)");
                    ObjectChecksum readChecksum = readChecksum();
                    logi(String.format(Locale.US, "Checksum received (Offset = %d, CRC = %08X)", Integer.valueOf(readChecksum.offset), Integer.valueOf(readChecksum.CRC32)));
                    this.mService.sendLogBroadcast(10, String.format(Locale.US, "Checksum received (Offset = %d, CRC = %08X)", Integer.valueOf(readChecksum.offset), Integer.valueOf(readChecksum.CRC32)));
                    int bytesSent = this.mProgressInfo.getBytesSent() - readChecksum.offset;
                    if (bytesSent > 0) {
                        logw(bytesSent + " bytes were lost!");
                        this.mService.sendLogBroadcast(15, bytesSent + " bytes were lost");
                        try {
                            this.mFirmwareStream.reset();
                            this.mFirmwareStream.read(new byte[selectObject.maxSize - bytesSent]);
                            this.mProgressInfo.setBytesSent(readChecksum.offset);
                            int i12 = this.mPacketsBeforeNotification;
                            if (i12 == 0 || i12 > 1) {
                                this.mPacketsBeforeNotification = 1;
                                setPacketReceiptNotifications(1);
                                this.mService.sendLogBroadcast(10, "Packet Receipt Notif Req (Op Code = 2) sent (Value = 1)");
                            }
                        } catch (IOException e3) {
                            loge("Error while reading firmware stream", e3);
                            this.mService.terminateConnection(bluetoothGatt, DfuBaseService.ERROR_FILE_IO_EXCEPTION);
                            return;
                        }
                    }
                    int i13 = i10;
                    int crc32 = (int) (((ArchiveInputStream) this.mFirmwareStream).getCrc32() & 4294967295L);
                    if (crc32 != readChecksum.CRC32) {
                        str = str4;
                        str2 = str3;
                        String format = String.format(Locale.US, "CRC does not match! Expected %08X but found %08X.", Integer.valueOf(crc32), Integer.valueOf(readChecksum.CRC32));
                        if (i13 < 3) {
                            i10 = i13 + 1;
                            String str5 = format + String.format(Locale.US, " Retrying...(%d/%d)", Integer.valueOf(i10), 3);
                            logi(str5);
                            this.mService.sendLogBroadcast(15, str5);
                            try {
                                this.mFirmwareStream.reset();
                                this.mProgressInfo.setBytesSent(((ArchiveInputStream) this.mFirmwareStream).getBytesRead());
                            } catch (IOException e4) {
                                loge("Error while resetting the firmware stream", e4);
                                this.mService.terminateConnection(bluetoothGatt, DfuBaseService.ERROR_FILE_IO_EXCEPTION);
                                return;
                            }
                        } else {
                            loge(format);
                            this.mService.sendLogBroadcast(20, format);
                            this.mService.terminateConnection(bluetoothGatt, DfuBaseService.ERROR_CRC_ERROR);
                            return;
                        }
                    } else if (bytesSent > 0) {
                        i10 = i13;
                        z = true;
                    } else {
                        logi("Executing data object (Op Code = 4)");
                        writeExecute(this.mProgressInfo.isComplete());
                        str = str4;
                        this.mService.sendLogBroadcast(10, str);
                        i++;
                        this.mFirmwareStream.mark(0);
                        str2 = str3;
                        i10 = 1;
                    }
                    str3 = str2;
                    str4 = str;
                } catch (DeviceDisconnectedException e5) {
                    loge("Disconnected while sending data");
                    throw e5;
                }
            }
        } else {
            logi("Executing data object (Op Code = 4)");
            writeExecute(true);
            this.mService.sendLogBroadcast(10, str4);
        }
        long elapsedRealtime2 = SystemClock.elapsedRealtime();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Transfer of ");
        sb2.append(this.mProgressInfo.getBytesSent() - selectObject.offset);
        sb2.append(" bytes has taken ");
        long j = elapsedRealtime2 - elapsedRealtime;
        sb2.append(j);
        sb2.append(" ms");
        logi(sb2.toString());
        this.mService.sendLogBroadcast(10, "Upload completed in " + j + " ms");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0116  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void sendInitPacket(android.bluetooth.BluetoothGatt r18, boolean r19) {
        /*
            Method dump skipped, instructions count: 613
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.SecureDfuImpl.sendInitPacket(android.bluetooth.BluetoothGatt, boolean):void");
    }

    private void setNumberOfPackets(byte[] bArr, int i) {
        bArr[1] = (byte) (i & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
    }

    private void setObjectSize(byte[] bArr, int i) {
        bArr[2] = (byte) (i & 255);
        bArr[3] = (byte) ((i >> 8) & 255);
        bArr[4] = (byte) ((i >> 16) & 255);
        bArr[5] = (byte) ((i >> 24) & 255);
    }

    private void setPacketReceiptNotifications(int i) {
        if (this.mConnected) {
            logi("Sending the number of packets before notifications (Op Code = 2, Value = " + i + ")");
            setNumberOfPackets(OP_CODE_PACKET_RECEIPT_NOTIF_REQ, i);
            writeOpCode(this.mControlPointCharacteristic, OP_CODE_PACKET_RECEIPT_NOTIF_REQ);
            byte[] readNotificationResponse = readNotificationResponse();
            int statusCode = getStatusCode(readNotificationResponse, 2);
            if (statusCode == 11) {
                throw new RemoteDfuExtendedErrorException("Sending the number of packets failed", readNotificationResponse[3]);
            }
            if (statusCode != 1) {
                throw new RemoteDfuException("Sending the number of packets failed", statusCode);
            }
            return;
        }
        throw new DeviceDisconnectedException("Unable to read Checksum: device disconnected");
    }

    private int unsignedBytesToInt(byte[] bArr, int i) {
        return (bArr[i] & FlagsParser.UNKNOWN_FLAGS) + ((bArr[i + 1] * FlagsParser.UNKNOWN_FLAGS) << 8) + ((bArr[i + 2] & FlagsParser.UNKNOWN_FLAGS) << 16) + ((bArr[i + 3] & FlagsParser.UNKNOWN_FLAGS) << 24);
    }

    private void writeCreateRequest(int i, int i2) {
        if (this.mConnected) {
            byte[] bArr = i == 1 ? OP_CODE_CREATE_COMMAND : OP_CODE_CREATE_DATA;
            setObjectSize(bArr, i2);
            writeOpCode(this.mControlPointCharacteristic, bArr);
            byte[] readNotificationResponse = readNotificationResponse();
            int statusCode = getStatusCode(readNotificationResponse, 1);
            if (statusCode == 11) {
                throw new RemoteDfuExtendedErrorException("Creating Command object failed", readNotificationResponse[3]);
            }
            if (statusCode != 1) {
                throw new RemoteDfuException("Creating Command object failed", statusCode);
            }
            return;
        }
        throw new DeviceDisconnectedException("Unable to create object: device disconnected");
    }

    private void writeExecute() {
        if (this.mConnected) {
            writeOpCode(this.mControlPointCharacteristic, OP_CODE_EXECUTE);
            byte[] readNotificationResponse = readNotificationResponse();
            int statusCode = getStatusCode(readNotificationResponse, 4);
            if (statusCode == 11) {
                throw new RemoteDfuExtendedErrorException("Executing object failed", readNotificationResponse[3]);
            }
            if (statusCode != 1) {
                throw new RemoteDfuException("Executing object failed", statusCode);
            }
            return;
        }
        throw new DeviceDisconnectedException("Unable to read Checksum: device disconnected");
    }

    private void writeOpCode(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        writeOpCode(bluetoothGattCharacteristic, bArr, false);
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

    @Override // no.nordicsemi.android.dfu.BaseDfuImpl, no.nordicsemi.android.dfu.DfuService
    public boolean initialize(Intent intent, BluetoothGatt bluetoothGatt, @FileType int i, InputStream inputStream, InputStream inputStream2) {
        if (inputStream2 == null) {
            this.mService.sendLogBroadcast(20, "The Init packet is required by this version DFU Bootloader");
            this.mService.terminateConnection(bluetoothGatt, DfuBaseService.ERROR_INIT_PACKET_REQUIRED);
            return false;
        }
        return super.initialize(intent, bluetoothGatt, i, inputStream, inputStream2);
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

    /* JADX WARN: Can't wrap try/catch for region: R(9:7|8|(6:13|(1:15)|16|17|18|19)|27|(0)|16|17|18|19) */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0073, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007a, code lost:
    
        if (r10.mProgressInfo.isLastPart() == false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x007c, code lost:
    
        r10.mRemoteErrorOccurred = false;
        logw("Sending SD+BL failed. Trying to send App only");
        r10.mService.sendLogBroadcast(15, "Invalid system components. Trying to send application");
        r10.mFileType = 4;
        r0 = (no.nordicsemi.android.dfu.internal.ArchiveInputStream) r10.mFirmwareStream;
        r0.setContentType(r10.mFileType);
        r2 = r0.getApplicationInit();
        r10.mInitPacketStream = new java.io.ByteArrayInputStream(r2);
        r10.mInitPacketSizeInBytes = r2.length;
        r10.mImageSizeInBytes = r0.applicationImageSize();
        r10.mProgressInfo.init(r10.mImageSizeInBytes, 2, 2);
        sendInitPacket(r1, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00d2, code lost:
    
        throw r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006a A[Catch: RemoteDfuException -> 0x00d3, UnknownResponseException -> 0x016d, UploadAbortedException -> 0x0186, TRY_LEAVE, TryCatch #2 {RemoteDfuException -> 0x00d3, blocks: (B:8:0x0045, B:10:0x005e, B:15:0x006a, B:23:0x0074, B:25:0x007c, B:26:0x00d2, B:18:0x00b7), top: B:7:0x0045 }] */
    @Override // no.nordicsemi.android.dfu.DfuService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void performDfu(android.content.Intent r11) {
        /*
            Method dump skipped, instructions count: 392
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.SecureDfuImpl.performDfu(android.content.Intent):void");
    }

    @Override // no.nordicsemi.android.dfu.DfuCallback
    public BaseDfuImpl.BaseBluetoothGattCallback getGattCallback() {
        return this.mBluetoothCallback;
    }

    private void writeExecute(boolean z) {
        try {
            writeExecute();
        } catch (RemoteDfuException e2) {
            if (z && e2.getErrorNumber() == 5) {
                logw(e2.getMessage() + ": " + c.a(517));
                if (this.mFileType == 1) {
                    logw("Are you sure your new SoftDevice is API compatible with the updated one? If not, update the bootloader as well");
                }
                this.mService.sendLogBroadcast(15, String.format(Locale.US, "Remote DFU error: %s. SD busy? Retrying...", c.a(517)));
                logi("SD busy? Retrying...");
                logi("Executing data object (Op Code = 4)");
                writeExecute();
                return;
            }
            throw e2;
        }
    }
}
