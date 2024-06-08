package io.runtime.mcumgr.ble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Build;
import f.a.b;
import f.a.c;
import io.runtime.mcumgr.McuMgrCallback;
import io.runtime.mcumgr.McuMgrScheme;
import io.runtime.mcumgr.McuMgrTransport;
import io.runtime.mcumgr.ble.callback.SmpDataCallback;
import io.runtime.mcumgr.ble.callback.SmpMerger;
import io.runtime.mcumgr.ble.callback.SmpResponse;
import io.runtime.mcumgr.exception.InsufficientMtuException;
import io.runtime.mcumgr.exception.McuMgrErrorException;
import io.runtime.mcumgr.exception.McuMgrException;
import io.runtime.mcumgr.exception.McuMgrTimeoutException;
import io.runtime.mcumgr.response.McuMgrResponse;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import no.nordicsemi.android.ble.BleManager;
import no.nordicsemi.android.ble.BleManagerCallbacks;
import no.nordicsemi.android.ble.WaitForValueChangedRequest;
import no.nordicsemi.android.ble.callback.FailCallback;
import no.nordicsemi.android.ble.callback.MtuCallback;
import no.nordicsemi.android.ble.callback.SuccessCallback;
import no.nordicsemi.android.ble.data.Data;
import no.nordicsemi.android.ble.data.DataMerger;
import no.nordicsemi.android.ble.error.GattError;
import no.nordicsemi.android.ble.exception.BluetoothDisabledException;
import no.nordicsemi.android.ble.exception.DeviceDisconnectedException;
import no.nordicsemi.android.ble.exception.InvalidRequestException;
import no.nordicsemi.android.ble.exception.RequestFailedException;

/* loaded from: classes.dex */
public class McuMgrBleTransport extends BleManager<BleManagerCallbacks> implements McuMgrTransport {
    private final List<McuMgrTransport.ConnectionObserver> mConnectionObservers;
    private final BluetoothDevice mDevice;
    private final BleManager<BleManagerCallbacks>.BleManagerGattCallback mGattCallback;
    private boolean mLoggingEnabled;
    private int mMaxPacketLength;
    private final DataMerger mSMPMerger;
    private BluetoothGattCharacteristic mSmpCharacteristic;
    private BluetoothGattService mSmpService;
    private static final b LOG = c.a((Class<?>) McuMgrBleTransport.class);
    public static final UUID SMP_SERVICE_UUID = UUID.fromString("8D53DC1D-1DB7-4CD3-868B-8A527460AA84");
    private static final UUID SMP_CHAR_UUID = UUID.fromString("DA2E7828-FBCE-4E01-AE9E-261174997C48");

    public McuMgrBleTransport(Context context, BluetoothDevice bluetoothDevice) {
        super(context);
        this.mSMPMerger = new SmpMerger();
        this.mGattCallback = new BleManager<BleManagerCallbacks>.BleManagerGattCallback() { // from class: io.runtime.mcumgr.ble.McuMgrBleTransport.6
            @Override // no.nordicsemi.android.ble.BleManager.BleManagerGattCallback
            protected void initialize() {
                McuMgrBleTransport.this.requestMtu(515).with(new MtuCallback() { // from class: io.runtime.mcumgr.ble.McuMgrBleTransport.6.2
                    @Override // no.nordicsemi.android.ble.callback.MtuCallback
                    public void onMtuChanged(BluetoothDevice bluetoothDevice2, int i) {
                        McuMgrBleTransport mcuMgrBleTransport = McuMgrBleTransport.this;
                        mcuMgrBleTransport.mMaxPacketLength = Math.max(i - 3, mcuMgrBleTransport.mMaxPacketLength);
                    }
                }).fail(new FailCallback() { // from class: io.runtime.mcumgr.ble.McuMgrBleTransport.6.1
                    @Override // no.nordicsemi.android.ble.callback.FailCallback
                    public void onRequestFailed(BluetoothDevice bluetoothDevice2, int i) {
                        McuMgrBleTransport.this.mMaxPacketLength = Math.max(r2.getMtu() - 3, McuMgrBleTransport.this.mMaxPacketLength);
                    }
                }).enqueue();
                McuMgrBleTransport mcuMgrBleTransport = McuMgrBleTransport.this;
                mcuMgrBleTransport.enableNotifications(mcuMgrBleTransport.mSmpCharacteristic).enqueue();
            }

            @Override // no.nordicsemi.android.ble.BleManager.BleManagerGattCallback
            protected boolean isRequiredServiceSupported(BluetoothGatt bluetoothGatt) {
                McuMgrBleTransport.this.mSmpService = bluetoothGatt.getService(McuMgrBleTransport.SMP_SERVICE_UUID);
                if (McuMgrBleTransport.this.mSmpService == null) {
                    McuMgrBleTransport.LOG.a("Device does not support SMP service");
                    return false;
                }
                McuMgrBleTransport mcuMgrBleTransport = McuMgrBleTransport.this;
                mcuMgrBleTransport.mSmpCharacteristic = mcuMgrBleTransport.mSmpService.getCharacteristic(McuMgrBleTransport.SMP_CHAR_UUID);
                if (McuMgrBleTransport.this.mSmpCharacteristic == null) {
                    McuMgrBleTransport.LOG.a("Device does not support SMP characteristic");
                    return false;
                }
                int properties = McuMgrBleTransport.this.mSmpCharacteristic.getProperties();
                boolean z = (properties & 4) > 0;
                boolean z2 = (properties & 16) > 0;
                if (z && z2) {
                    return true;
                }
                McuMgrBleTransport.LOG.c("SMP characteristic does not support either write ({}) or notify ({})", Boolean.valueOf(z), Boolean.valueOf(z2));
                return false;
            }

            @Override // no.nordicsemi.android.ble.BleManager.BleManagerGattCallback
            protected void onDeviceDisconnected() {
                McuMgrBleTransport.this.mSmpService = null;
                McuMgrBleTransport.this.mSmpCharacteristic = null;
                McuMgrBleTransport.this.notifyDisconnected();
            }
        };
        this.mConnectionObservers = new LinkedList();
        this.mDevice = bluetoothDevice;
        setGattCallbacks(new McuMgrBleCallbacksStub());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void notifyConnected() {
        Iterator<McuMgrTransport.ConnectionObserver> it = this.mConnectionObservers.iterator();
        while (it.hasNext()) {
            it.next().onConnected();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void notifyDisconnected() {
        Iterator<McuMgrTransport.ConnectionObserver> it = this.mConnectionObservers.iterator();
        while (it.hasNext()) {
            it.next().onDisconnected();
        }
    }

    @Override // io.runtime.mcumgr.McuMgrTransport
    public synchronized void addObserver(McuMgrTransport.ConnectionObserver connectionObserver) {
        this.mConnectionObservers.add(connectionObserver);
    }

    @Override // io.runtime.mcumgr.McuMgrTransport
    public void connect(final McuMgrTransport.ConnectionCallback connectionCallback) {
        if (!isConnected()) {
            connect(this.mDevice).retry(3, 100).done(new SuccessCallback() { // from class: io.runtime.mcumgr.ble.McuMgrBleTransport.4
                @Override // no.nordicsemi.android.ble.callback.SuccessCallback
                public void onRequestCompleted(BluetoothDevice bluetoothDevice) {
                    McuMgrBleTransport.this.notifyConnected();
                    McuMgrTransport.ConnectionCallback connectionCallback2 = connectionCallback;
                    if (connectionCallback2 == null) {
                        return;
                    }
                    connectionCallback2.onConnected();
                }
            }).fail(new FailCallback() { // from class: io.runtime.mcumgr.ble.McuMgrBleTransport.3
                @Override // no.nordicsemi.android.ble.callback.FailCallback
                public void onRequestFailed(BluetoothDevice bluetoothDevice, int i) {
                    McuMgrTransport.ConnectionCallback connectionCallback2 = connectionCallback;
                    if (connectionCallback2 == null) {
                        return;
                    }
                    if (i == -100) {
                        connectionCallback2.onError(new McuMgrException("Bluetooth adapter disabled"));
                        return;
                    }
                    if (i == -4) {
                        connectionCallback2.onError(new McuMgrException("Other device already connected"));
                        return;
                    }
                    if (i == -2) {
                        connectionCallback2.onError(new McuMgrException("Device does not support SMP Service"));
                    } else if (i != -1) {
                        connectionCallback2.onError(new McuMgrException(GattError.parseConnectionError(i)));
                    } else {
                        connectionCallback2.onError(new McuMgrException("Device has disconnected"));
                    }
                }
            }).enqueue();
        } else if (connectionCallback != null) {
            connectionCallback.onConnected();
        }
    }

    @Override // no.nordicsemi.android.ble.BleManager
    public BluetoothDevice getBluetoothDevice() {
        return this.mDevice;
    }

    @Override // no.nordicsemi.android.ble.BleManager
    protected BleManager<BleManagerCallbacks>.BleManagerGattCallback getGattCallback() {
        return this.mGattCallback;
    }

    @Override // io.runtime.mcumgr.McuMgrTransport
    public McuMgrScheme getScheme() {
        return McuMgrScheme.BLE;
    }

    @Override // no.nordicsemi.android.ble.BleManager, no.nordicsemi.android.ble.utils.ILogger
    public void log(int i, String str) {
        if (this.mLoggingEnabled) {
            if (i == 3) {
                LOG.e(str);
                return;
            }
            if (i == 4) {
                LOG.b(str);
                return;
            }
            if (i == 5) {
                LOG.c(str);
            } else if (i != 6 && i != 7) {
                LOG.d(str);
            } else {
                LOG.a(str);
            }
        }
    }

    @Override // io.runtime.mcumgr.McuMgrTransport
    public void release() {
        disconnect().enqueue();
    }

    @Override // io.runtime.mcumgr.McuMgrTransport
    public synchronized void removeObserver(McuMgrTransport.ConnectionObserver connectionObserver) {
        this.mConnectionObservers.remove(connectionObserver);
    }

    public void requestConnPriority(final int i) {
        connect(this.mDevice).done(new SuccessCallback() { // from class: io.runtime.mcumgr.ble.McuMgrBleTransport.5
            @Override // no.nordicsemi.android.ble.callback.SuccessCallback
            public void onRequestCompleted(BluetoothDevice bluetoothDevice) {
                if (Build.VERSION.SDK_INT >= 21) {
                    McuMgrBleTransport.super.requestConnectionPriority(i).enqueue();
                }
            }
        }).retry(3, 100).enqueue();
    }

    @Override // io.runtime.mcumgr.McuMgrTransport
    public <T extends McuMgrResponse> T send(byte[] bArr, Class<T> cls) {
        boolean isConnected = isConnected();
        try {
            connect(this.mDevice).retry(3, 100).timeout(25000L).await();
            if (!isConnected) {
                notifyConnected();
            }
            int i = this.mMaxPacketLength;
            if (i >= bArr.length) {
                try {
                    SmpResponse smpResponse = (SmpResponse) waitForNotification(this.mSmpCharacteristic).merge(this.mSMPMerger).trigger(writeCharacteristic(this.mSmpCharacteristic, bArr).split()).timeout(30000L).await((WaitForValueChangedRequest) new SmpResponse(cls));
                    if (smpResponse.isValid()) {
                        return (T) smpResponse.getResponse();
                    }
                    throw new McuMgrException("Error building McuMgrResponse from response data: " + smpResponse.getRawData());
                } catch (InterruptedException unused) {
                    throw new McuMgrException("Request timed out");
                } catch (BluetoothDisabledException unused2) {
                    throw new McuMgrException("Bluetooth adapter disabled");
                } catch (DeviceDisconnectedException unused3) {
                    throw new McuMgrException("Device has disconnected");
                } catch (InvalidRequestException unused4) {
                    throw new RuntimeException("Invalid request");
                } catch (RequestFailedException e2) {
                    throw new McuMgrException(GattError.parse(e2.getStatus()));
                }
            }
            throw new InsufficientMtuException(bArr.length, i);
        } catch (InterruptedException unused5) {
            throw new McuMgrException("Connection routine timed out.");
        } catch (BluetoothDisabledException unused6) {
            throw new McuMgrException("Bluetooth adapter disabled");
        } catch (DeviceDisconnectedException unused7) {
            throw new McuMgrException("Device has disconnected");
        } catch (InvalidRequestException unused8) {
            throw new RuntimeException("Invalid request");
        } catch (RequestFailedException e3) {
            int status = e3.getStatus();
            if (status == -5) {
                throw new McuMgrTimeoutException();
            }
            if (status == -4) {
                throw new McuMgrException("Other device already connected");
            }
            if (status != -2) {
                throw new McuMgrException("Unknown error");
            }
            throw new McuMgrException("Device does not support SMP Service");
        }
    }

    public void setDeviceSidePacketMergingSupported(int i) {
        this.mMaxPacketLength = i;
    }

    public void setLoggingEnabled(boolean z) {
        this.mLoggingEnabled = z;
    }

    @Override // io.runtime.mcumgr.McuMgrTransport
    public <T extends McuMgrResponse> void send(final byte[] bArr, final Class<T> cls, final McuMgrCallback<T> mcuMgrCallback) {
        final boolean isConnected = isConnected();
        connect(this.mDevice).done(new SuccessCallback() { // from class: io.runtime.mcumgr.ble.McuMgrBleTransport.2
            @Override // no.nordicsemi.android.ble.callback.SuccessCallback
            public void onRequestCompleted(BluetoothDevice bluetoothDevice) {
                if (!isConnected) {
                    McuMgrBleTransport.this.notifyConnected();
                }
                int i = McuMgrBleTransport.this.mMaxPacketLength;
                byte[] bArr2 = bArr;
                if (i < bArr2.length) {
                    mcuMgrCallback.onError(new InsufficientMtuException(bArr2.length, McuMgrBleTransport.this.mMaxPacketLength));
                    return;
                }
                McuMgrBleTransport mcuMgrBleTransport = McuMgrBleTransport.this;
                WaitForValueChangedRequest with = mcuMgrBleTransport.waitForNotification(mcuMgrBleTransport.mSmpCharacteristic).merge(McuMgrBleTransport.this.mSMPMerger).with(new SmpDataCallback<T>(cls) { // from class: io.runtime.mcumgr.ble.McuMgrBleTransport.2.2
                    @Override // io.runtime.mcumgr.ble.callback.SmpDataCallback, no.nordicsemi.android.ble.callback.profile.ProfileDataCallback
                    public void onInvalidDataReceived(BluetoothDevice bluetoothDevice2, Data data) {
                        mcuMgrCallback.onError(new McuMgrException("Error building McuMgrResponse from response data: " + data));
                    }

                    /* JADX WARN: Incorrect types in method signature: (Landroid/bluetooth/BluetoothDevice;TT;)V */
                    @Override // io.runtime.mcumgr.ble.callback.SmpCallback
                    public void onResponseReceived(BluetoothDevice bluetoothDevice2, McuMgrResponse mcuMgrResponse) {
                        if (mcuMgrResponse.isSuccess()) {
                            mcuMgrCallback.onResponse(mcuMgrResponse);
                        } else {
                            mcuMgrCallback.onError(new McuMgrErrorException(mcuMgrResponse));
                        }
                    }
                });
                McuMgrBleTransport mcuMgrBleTransport2 = McuMgrBleTransport.this;
                with.trigger(mcuMgrBleTransport2.writeCharacteristic(mcuMgrBleTransport2.mSmpCharacteristic, bArr).split()).fail(new FailCallback() { // from class: io.runtime.mcumgr.ble.McuMgrBleTransport.2.1
                    @Override // no.nordicsemi.android.ble.callback.FailCallback
                    public void onRequestFailed(BluetoothDevice bluetoothDevice2, int i2) {
                        if (i2 == -100) {
                            mcuMgrCallback.onError(new McuMgrException("Bluetooth adapter disabled"));
                            return;
                        }
                        if (i2 == -5) {
                            mcuMgrCallback.onError(new McuMgrException("Request timed out"));
                        } else if (i2 != -1) {
                            mcuMgrCallback.onError(new McuMgrException(GattError.parse(i2)));
                        } else {
                            mcuMgrCallback.onError(new McuMgrException("Device has disconnected"));
                        }
                    }
                }).timeout(30000L).enqueue();
            }
        }).fail(new FailCallback() { // from class: io.runtime.mcumgr.ble.McuMgrBleTransport.1
            @Override // no.nordicsemi.android.ble.callback.FailCallback
            public void onRequestFailed(BluetoothDevice bluetoothDevice, int i) {
                if (i == -100) {
                    mcuMgrCallback.onError(new McuMgrException("Bluetooth adapter disabled"));
                    return;
                }
                if (i == -5) {
                    mcuMgrCallback.onError(new McuMgrTimeoutException());
                    return;
                }
                if (i == -4) {
                    mcuMgrCallback.onError(new McuMgrException("Other device already connected"));
                    return;
                }
                if (i == -2) {
                    mcuMgrCallback.onError(new McuMgrException("Device does not support SMP Service"));
                } else if (i != -1) {
                    mcuMgrCallback.onError(new McuMgrException(GattError.parseConnectionError(i)));
                } else {
                    mcuMgrCallback.onError(new McuMgrException("Device has disconnected"));
                }
            }
        }).retry(3, 100).enqueue();
    }
}
