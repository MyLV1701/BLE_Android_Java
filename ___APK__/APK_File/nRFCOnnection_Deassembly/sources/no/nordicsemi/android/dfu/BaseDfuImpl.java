package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.UUID;
import no.nordicsemi.android.dfu.DfuCallback;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScannerFactory;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;
import no.nordicsemi.android.mcp.database.DatabaseUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class BaseDfuImpl implements DfuService {
    static final int INDICATIONS = 2;
    private static final int MAX_PACKET_SIZE_DEFAULT = 20;
    static final int NOTIFICATIONS = 1;
    private static final String TAG = "DfuImpl";
    boolean mAborted;
    private int mCurrentMtu;
    int mError;
    int mFileType;
    InputStream mFirmwareStream;
    BluetoothGatt mGatt;
    int mImageSizeInBytes;
    int mInitPacketSizeInBytes;
    InputStream mInitPacketStream;
    boolean mPaused;
    DfuProgressInfo mProgressInfo;
    boolean mRequestCompleted;
    boolean mResetRequestSent;
    DfuBaseService mService;
    static final UUID GENERIC_ATTRIBUTE_SERVICE_UUID = new UUID(26392574038016L, DatabaseUtils.LSB);
    static final UUID SERVICE_CHANGED_UUID = new UUID(46200963207168L, DatabaseUtils.LSB);
    static final UUID CLIENT_CHARACTERISTIC_CONFIG = new UUID(45088566677504L, DatabaseUtils.LSB);
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    final Object mLock = new Object();
    byte[] mReceivedData = null;
    byte[] mBuffer = new byte[20];
    boolean mConnected = true;

    /* loaded from: classes.dex */
    protected class BaseBluetoothGattCallback extends DfuCallback.DfuGattCallback {
        /* JADX INFO: Access modifiers changed from: protected */
        public BaseBluetoothGattCallback() {
        }

        private String phyToString(int i) {
            if (i == 1) {
                return "LE 1M";
            }
            if (i == 2) {
                return "LE 2M";
            }
            if (i == 3) {
                return "LE Coded";
            }
            return "UNKNOWN (" + i + ")";
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (i == 0) {
                BaseDfuImpl.this.mService.sendLogBroadcast(5, "Read Response received from " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
                BaseDfuImpl.this.mReceivedData = bluetoothGattCharacteristic.getValue();
                BaseDfuImpl.this.mRequestCompleted = true;
            } else {
                BaseDfuImpl.this.loge("Characteristic read error: " + i);
                BaseDfuImpl.this.mError = i | DfuBaseService.ERROR_CONNECTION_MASK;
            }
            BaseDfuImpl.this.notifyLock();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (i == 0) {
                if (BaseDfuImpl.CLIENT_CHARACTERISTIC_CONFIG.equals(bluetoothGattDescriptor.getUuid())) {
                    BaseDfuImpl.this.mService.sendLogBroadcast(5, "Read Response received from descr." + bluetoothGattDescriptor.getCharacteristic().getUuid() + ", value (0x): " + parse(bluetoothGattDescriptor));
                    if (BaseDfuImpl.SERVICE_CHANGED_UUID.equals(bluetoothGattDescriptor.getCharacteristic().getUuid())) {
                        BaseDfuImpl.this.mRequestCompleted = true;
                    } else {
                        BaseDfuImpl.this.loge("Unknown descriptor read");
                    }
                }
            } else {
                BaseDfuImpl.this.loge("Descriptor read error: " + i);
                BaseDfuImpl.this.mError = i | DfuBaseService.ERROR_CONNECTION_MASK;
            }
            BaseDfuImpl.this.notifyLock();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (i == 0) {
                if (BaseDfuImpl.CLIENT_CHARACTERISTIC_CONFIG.equals(bluetoothGattDescriptor.getUuid())) {
                    BaseDfuImpl.this.mService.sendLogBroadcast(5, "Data written to descr." + bluetoothGattDescriptor.getCharacteristic().getUuid() + ", value (0x): " + parse(bluetoothGattDescriptor));
                    if (BaseDfuImpl.SERVICE_CHANGED_UUID.equals(bluetoothGattDescriptor.getCharacteristic().getUuid())) {
                        BaseDfuImpl.this.mService.sendLogBroadcast(1, "Indications enabled for " + bluetoothGattDescriptor.getCharacteristic().getUuid());
                    } else {
                        BaseDfuImpl.this.mService.sendLogBroadcast(1, "Notifications enabled for " + bluetoothGattDescriptor.getCharacteristic().getUuid());
                    }
                }
            } else {
                BaseDfuImpl.this.loge("Descriptor write error: " + i);
                BaseDfuImpl.this.mError = i | DfuBaseService.ERROR_CONNECTION_MASK;
            }
            BaseDfuImpl.this.notifyLock();
        }

        @Override // no.nordicsemi.android.dfu.DfuCallback.DfuGattCallback
        public void onDisconnected() {
            BaseDfuImpl baseDfuImpl = BaseDfuImpl.this;
            baseDfuImpl.mConnected = false;
            baseDfuImpl.notifyLock();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i2 == 0) {
                BaseDfuImpl.this.mService.sendLogBroadcast(5, "MTU changed to: " + i);
                int i3 = i + (-3);
                BaseDfuImpl baseDfuImpl = BaseDfuImpl.this;
                if (i3 > baseDfuImpl.mBuffer.length) {
                    baseDfuImpl.mBuffer = new byte[i3];
                }
                BaseDfuImpl.this.logi("MTU changed to: " + i);
            } else {
                BaseDfuImpl.this.logw("Changing MTU failed: " + i2 + " (mtu: " + i + ")");
                if (i2 == 4 && BaseDfuImpl.this.mCurrentMtu > 23) {
                    int i4 = BaseDfuImpl.this.mCurrentMtu - 3;
                    BaseDfuImpl baseDfuImpl2 = BaseDfuImpl.this;
                    if (i4 > baseDfuImpl2.mBuffer.length) {
                        baseDfuImpl2.mBuffer = new byte[baseDfuImpl2.mCurrentMtu - 3];
                        BaseDfuImpl.this.logi("MTU restored to: " + BaseDfuImpl.this.mCurrentMtu);
                    }
                }
            }
            BaseDfuImpl baseDfuImpl3 = BaseDfuImpl.this;
            baseDfuImpl3.mRequestCompleted = true;
            baseDfuImpl3.notifyLock();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            if (i3 == 0) {
                BaseDfuImpl.this.mService.sendLogBroadcast(5, "PHY updated (TX: " + phyToString(i) + ", RX: " + phyToString(i2) + ")");
                BaseDfuImpl.this.logi("PHY updated (TX: " + phyToString(i) + ", RX: " + phyToString(i2) + ")");
                return;
            }
            BaseDfuImpl.this.logw("Updating PHY failed: " + i3 + " (txPhy: " + i + ", rxPhy: " + i2 + ")");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            return parse(bluetoothGattCharacteristic.getValue());
        }

        protected String parse(BluetoothGattDescriptor bluetoothGattDescriptor) {
            return parse(bluetoothGattDescriptor.getValue());
        }

        private String parse(byte[] bArr) {
            int length;
            if (bArr == null || (length = bArr.length) == 0) {
                return "";
            }
            char[] cArr = new char[(length * 3) - 1];
            for (int i = 0; i < length; i++) {
                int i2 = bArr[i] & FlagsParser.UNKNOWN_FLAGS;
                int i3 = i * 3;
                cArr[i3] = BaseDfuImpl.HEX_ARRAY[i2 >>> 4];
                cArr[i3 + 1] = BaseDfuImpl.HEX_ARRAY[i2 & 15];
                if (i != length - 1) {
                    cArr[i3 + 2] = '-';
                }
            }
            return new String(cArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseDfuImpl(Intent intent, DfuBaseService dfuBaseService) {
        this.mService = dfuBaseService;
        this.mProgressInfo = dfuBaseService.mProgressInfo;
    }

    private boolean createBondApi18(BluetoothDevice bluetoothDevice) {
        try {
            Method method = bluetoothDevice.getClass().getMethod("createBond", new Class[0]);
            this.mService.sendLogBroadcast(0, "gatt.getDevice().createBond() (hidden)");
            return ((Boolean) method.invoke(bluetoothDevice, new Object[0])).booleanValue();
        } catch (Exception e2) {
            Log.w(TAG, "An exception occurred while creating bond", e2);
            return false;
        }
    }

    private boolean isServiceChangedCCCDEnabled() {
        BluetoothGattCharacteristic characteristic;
        BluetoothGattDescriptor descriptor;
        if (this.mConnected) {
            if (!this.mAborted) {
                BluetoothGatt bluetoothGatt = this.mGatt;
                BluetoothGattService service = bluetoothGatt.getService(GENERIC_ATTRIBUTE_SERVICE_UUID);
                if (service == null || (characteristic = service.getCharacteristic(SERVICE_CHANGED_UUID)) == null || (descriptor = characteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG)) == null) {
                    return false;
                }
                this.mRequestCompleted = false;
                this.mError = 0;
                logi("Reading Service Changed CCCD value...");
                this.mService.sendLogBroadcast(1, "Reading Service Changed CCCD value...");
                this.mService.sendLogBroadcast(0, "gatt.readDescriptor(" + descriptor.getUuid() + ")");
                bluetoothGatt.readDescriptor(descriptor);
                try {
                    synchronized (this.mLock) {
                        while (true) {
                            if ((this.mRequestCompleted || !this.mConnected || this.mError != 0) && !this.mPaused) {
                                break;
                            }
                            this.mLock.wait();
                        }
                    }
                } catch (InterruptedException e2) {
                    loge("Sleeping interrupted", e2);
                }
                if (this.mConnected) {
                    int i = this.mError;
                    if (i == 0) {
                        return descriptor.getValue() != null && descriptor.getValue().length == 2 && descriptor.getValue()[0] == BluetoothGattDescriptor.ENABLE_INDICATION_VALUE[0] && descriptor.getValue()[1] == BluetoothGattDescriptor.ENABLE_INDICATION_VALUE[1];
                    }
                    throw new DfuException("Unable to read Service Changed CCCD", i);
                }
                throw new DeviceDisconnectedException("Unable to read Service Changed CCCD: device disconnected");
            }
            throw new UploadAbortedException();
        }
        throw new DeviceDisconnectedException("Unable to read Service Changed CCCD: device disconnected");
    }

    @Override // no.nordicsemi.android.dfu.DfuController
    public void abort() {
        this.mPaused = false;
        this.mAborted = true;
        notifyLock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean createBond() {
        boolean createBondApi18;
        BluetoothDevice device = this.mGatt.getDevice();
        if (device.getBondState() == 12) {
            return true;
        }
        this.mRequestCompleted = false;
        this.mService.sendLogBroadcast(1, "Starting pairing...");
        if (Build.VERSION.SDK_INT >= 19) {
            this.mService.sendLogBroadcast(0, "gatt.getDevice().createBond()");
            createBondApi18 = device.createBond();
        } else {
            createBondApi18 = createBondApi18(device);
        }
        try {
            synchronized (this.mLock) {
                while (!this.mRequestCompleted && !this.mAborted) {
                    this.mLock.wait();
                }
            }
        } catch (InterruptedException e2) {
            loge("Sleeping interrupted", e2);
        }
        return createBondApi18;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00db, code lost:
    
        if (r10.mError == 0) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void enableCCCD(android.bluetooth.BluetoothGattCharacteristic r11, int r12) {
        /*
            Method dump skipped, instructions count: 377
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.BaseDfuImpl.enableCCCD(android.bluetooth.BluetoothGattCharacteristic, int):void");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:1|(1:3)|(1:5)|6|(12:32|33|(1:35)|36|9|10|11|(2:13|(1:15)(1:16))|17|18|(3:24|(1:26)|27)|28)|8|9|10|11|(0)|17|18|(5:20|22|24|(0)|27)|28) */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0064 A[Catch: Exception -> 0x0077, TryCatch #1 {Exception -> 0x0077, blocks: (B:11:0x005e, B:13:0x0064, B:15:0x0068, B:16:0x006f, B:17:0x0072), top: B:10:0x005e }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a1  */
    @Override // no.nordicsemi.android.dfu.DfuService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean initialize(android.content.Intent r6, android.bluetooth.BluetoothGatt r7, int r8, java.io.InputStream r9, java.io.InputStream r10) {
        /*
            r5 = this;
            r5.mGatt = r7
            r5.mFileType = r8
            r5.mFirmwareStream = r9
            r5.mInitPacketStream = r10
            r0 = 1
            java.lang.String r1 = "no.nordicsemi.android.dfu.extra.EXTRA_PART_CURRENT"
            int r1 = r6.getIntExtra(r1, r0)
            java.lang.String r2 = "no.nordicsemi.android.dfu.extra.EXTRA_PARTS_TOTAL"
            int r2 = r6.getIntExtra(r2, r0)
            java.lang.String r3 = "no.nordicsemi.android.dfu.extra.EXTRA_CURRENT_MTU"
            r4 = 23
            int r6 = r6.getIntExtra(r3, r4)
            r5.mCurrentMtu = r6
            r6 = 15
            r3 = 2
            r4 = 4
            if (r8 <= r4) goto L41
            java.lang.String r8 = "DFU target does not support (SD/BL)+App update, splitting into 2 parts"
            r5.logw(r8)
            no.nordicsemi.android.dfu.DfuBaseService r8 = r5.mService
            java.lang.String r2 = "Sending system components"
            r8.sendLogBroadcast(r6, r2)
            int r8 = r5.mFileType
            r8 = r8 & (-5)
            r5.mFileType = r8
            java.io.InputStream r8 = r5.mFirmwareStream
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r8 = (no.nordicsemi.android.dfu.internal.ArchiveInputStream) r8
            int r2 = r5.mFileType
            r8.setContentType(r2)
            r2 = 2
        L41:
            if (r1 != r3) goto L4a
            no.nordicsemi.android.dfu.DfuBaseService r8 = r5.mService
            java.lang.String r4 = "Sending application"
            r8.sendLogBroadcast(r6, r4)
        L4a:
            r6 = 0
            if (r10 == 0) goto L5b
            boolean r8 = r10.markSupported()     // Catch: java.lang.Exception -> L5b
            if (r8 == 0) goto L56
            r10.reset()     // Catch: java.lang.Exception -> L5b
        L56:
            int r8 = r10.available()     // Catch: java.lang.Exception -> L5b
            goto L5c
        L5b:
            r8 = 0
        L5c:
            r5.mInitPacketSizeInBytes = r8
            boolean r8 = r9.markSupported()     // Catch: java.lang.Exception -> L77
            if (r8 == 0) goto L72
            boolean r8 = r9 instanceof no.nordicsemi.android.dfu.internal.ArchiveInputStream     // Catch: java.lang.Exception -> L77
            if (r8 == 0) goto L6f
            r8 = r9
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r8 = (no.nordicsemi.android.dfu.internal.ArchiveInputStream) r8     // Catch: java.lang.Exception -> L77
            r8.fullReset()     // Catch: java.lang.Exception -> L77
            goto L72
        L6f:
            r9.reset()     // Catch: java.lang.Exception -> L77
        L72:
            int r6 = r9.available()     // Catch: java.lang.Exception -> L77
            goto L78
        L77:
        L78:
            r5.mImageSizeInBytes = r6
            no.nordicsemi.android.dfu.DfuProgressInfo r8 = r5.mProgressInfo
            r8.init(r6, r1, r2)
            android.bluetooth.BluetoothDevice r6 = r7.getDevice()
            int r6 = r6.getBondState()
            r8 = 12
            if (r6 != r8) goto Lad
            java.util.UUID r6 = no.nordicsemi.android.dfu.BaseDfuImpl.GENERIC_ATTRIBUTE_SERVICE_UUID
            android.bluetooth.BluetoothGattService r6 = r7.getService(r6)
            if (r6 == 0) goto Lad
            java.util.UUID r7 = no.nordicsemi.android.dfu.BaseDfuImpl.SERVICE_CHANGED_UUID
            android.bluetooth.BluetoothGattCharacteristic r6 = r6.getCharacteristic(r7)
            if (r6 == 0) goto Lad
            boolean r7 = r5.isServiceChangedCCCDEnabled()
            if (r7 != 0) goto La4
            r5.enableCCCD(r6, r3)
        La4:
            no.nordicsemi.android.dfu.DfuBaseService r6 = r5.mService
            r7 = 10
            java.lang.String r8 = "Service Changed indications enabled"
            r6.sendLogBroadcast(r7, r8)
        Lad:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.BaseDfuImpl.initialize(android.content.Intent, android.bluetooth.BluetoothGatt, int, java.io.InputStream, java.io.InputStream):boolean");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isBonded() {
        return this.mGatt.getDevice().getBondState() == 12;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void loge(String str) {
        Log.e(TAG, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logi(String str) {
        if (DfuBaseService.DEBUG) {
            Log.i(TAG, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logw(String str) {
        if (DfuBaseService.DEBUG) {
            Log.w(TAG, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyLock() {
        synchronized (this.mLock) {
            this.mLock.notifyAll();
        }
    }

    @Override // no.nordicsemi.android.dfu.DfuCallback
    public void onBondStateChanged(int i) {
        this.mRequestCompleted = true;
        notifyLock();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String parse(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) == 0) {
            return "";
        }
        char[] cArr = new char[(length * 3) - 1];
        for (int i = 0; i < length; i++) {
            int i2 = bArr[i] & FlagsParser.UNKNOWN_FLAGS;
            int i3 = i * 3;
            char[] cArr2 = HEX_ARRAY;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
            if (i != length - 1) {
                cArr[i3 + 2] = '-';
            }
        }
        return new String(cArr);
    }

    @Override // no.nordicsemi.android.dfu.DfuController
    public void pause() {
        this.mPaused = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] readNotificationResponse() {
        try {
            synchronized (this.mLock) {
                while (true) {
                    if ((this.mReceivedData != null || !this.mConnected || this.mError != 0 || this.mAborted) && !this.mPaused) {
                        break;
                    }
                    this.mLock.wait();
                }
            }
        } catch (InterruptedException e2) {
            loge("Sleeping interrupted", e2);
        }
        if (!this.mAborted) {
            if (this.mConnected) {
                int i = this.mError;
                if (i == 0) {
                    return this.mReceivedData;
                }
                throw new DfuException("Unable to write Op Code", i);
            }
            throw new DeviceDisconnectedException("Unable to write Op Code: device disconnected");
        }
        throw new UploadAbortedException();
    }

    @Override // no.nordicsemi.android.dfu.DfuService
    public void release() {
        this.mService = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean removeBond() {
        BluetoothDevice device = this.mGatt.getDevice();
        if (device.getBondState() == 10) {
            return true;
        }
        this.mService.sendLogBroadcast(1, "Removing bond information...");
        boolean z = false;
        try {
            Method method = device.getClass().getMethod("removeBond", new Class[0]);
            this.mRequestCompleted = false;
            this.mService.sendLogBroadcast(0, "gatt.getDevice().removeBond() (hidden)");
            z = ((Boolean) method.invoke(device, new Object[0])).booleanValue();
            try {
                synchronized (this.mLock) {
                    while (!this.mRequestCompleted && !this.mAborted) {
                        this.mLock.wait();
                    }
                }
            } catch (InterruptedException e2) {
                loge("Sleeping interrupted", e2);
            }
        } catch (Exception e3) {
            Log.w(TAG, "An exception occurred while removing bond information", e3);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void requestMtu(int i) {
        if (!this.mAborted) {
            this.mRequestCompleted = false;
            this.mService.sendLogBroadcast(1, "Requesting new MTU...");
            this.mService.sendLogBroadcast(0, "gatt.requestMtu(" + i + ")");
            if (this.mGatt.requestMtu(i)) {
                try {
                    synchronized (this.mLock) {
                        while (true) {
                            if ((this.mRequestCompleted || !this.mConnected || this.mError != 0) && !this.mPaused) {
                                break;
                            } else {
                                this.mLock.wait();
                            }
                        }
                    }
                } catch (InterruptedException e2) {
                    loge("Sleeping interrupted", e2);
                }
                if (!this.mConnected) {
                    throw new DeviceDisconnectedException("Unable to read Service Changed CCCD: device disconnected");
                }
                return;
            }
            return;
        }
        throw new UploadAbortedException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void restartService(Intent intent, boolean z) {
        String str;
        if (z) {
            this.mService.sendLogBroadcast(1, "Scanning for the DFU Bootloader...");
            str = BootloaderScannerFactory.getScanner().searchFor(this.mGatt.getDevice().getAddress());
            logi("Scanning for new address finished with: " + str);
            if (str != null) {
                this.mService.sendLogBroadcast(5, "DFU Bootloader found with address " + str);
            } else {
                this.mService.sendLogBroadcast(5, "DFU Bootloader not found. Trying the same address...");
            }
        } else {
            str = null;
        }
        if (str != null) {
            intent.putExtra(DfuBaseService.EXTRA_DEVICE_ADDRESS, str);
        }
        intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DFU_ATTEMPT", 0);
        this.mService.startService(intent);
    }

    @Override // no.nordicsemi.android.dfu.DfuController
    public void resume() {
        this.mPaused = false;
        notifyLock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void waitIfPaused() {
        try {
            synchronized (this.mLock) {
                while (this.mPaused) {
                    this.mLock.wait();
                }
            }
        } catch (InterruptedException e2) {
            loge("Sleeping interrupted", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeOpCode(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, boolean z) {
        if (!this.mAborted) {
            this.mReceivedData = null;
            this.mError = 0;
            this.mRequestCompleted = false;
            this.mResetRequestSent = z;
            bluetoothGattCharacteristic.setWriteType(2);
            bluetoothGattCharacteristic.setValue(bArr);
            this.mService.sendLogBroadcast(1, "Writing to characteristic " + bluetoothGattCharacteristic.getUuid());
            this.mService.sendLogBroadcast(0, "gatt.writeCharacteristic(" + bluetoothGattCharacteristic.getUuid() + ")");
            this.mGatt.writeCharacteristic(bluetoothGattCharacteristic);
            try {
                synchronized (this.mLock) {
                    while (true) {
                        if ((this.mRequestCompleted || !this.mConnected || this.mError != 0) && !this.mPaused) {
                            break;
                        } else {
                            this.mLock.wait();
                        }
                    }
                }
            } catch (InterruptedException e2) {
                loge("Sleeping interrupted", e2);
            }
            if (!this.mResetRequestSent && !this.mConnected) {
                throw new DeviceDisconnectedException("Unable to write Op Code " + ((int) bArr[0]) + ": device disconnected");
            }
            if (this.mResetRequestSent || this.mError == 0) {
                return;
            }
            throw new DfuException("Unable to write Op Code " + ((int) bArr[0]), this.mError);
        }
        throw new UploadAbortedException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void loge(String str, Throwable th) {
        Log.e(TAG, str, th);
    }
}
