package no.nordicsemi.android.ble;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;
import no.nordicsemi.android.ble.BleManager;
import no.nordicsemi.android.ble.BleManagerCallbacks;
import no.nordicsemi.android.ble.Request;
import no.nordicsemi.android.ble.callback.DataReceivedCallback;
import no.nordicsemi.android.ble.callback.SuccessCallback;
import no.nordicsemi.android.ble.data.Data;
import no.nordicsemi.android.ble.error.GattError;
import no.nordicsemi.android.ble.utils.ILogger;
import no.nordicsemi.android.ble.utils.ParserUtils;

/* loaded from: classes.dex */
public abstract class BleManager<E extends BleManagerCallbacks> extends TimeoutHandler implements ILogger {
    private static final long CONNECTION_TIMEOUT_THRESHOLD = 20000;
    protected static final int PAIRING_VARIANT_CONSENT = 3;
    protected static final int PAIRING_VARIANT_DISPLAY_PASSKEY = 4;
    protected static final int PAIRING_VARIANT_DISPLAY_PIN = 5;
    protected static final int PAIRING_VARIANT_OOB_CONSENT = 6;
    protected static final int PAIRING_VARIANT_PASSKEY = 1;
    protected static final int PAIRING_VARIANT_PASSKEY_CONFIRMATION = 2;
    protected static final int PAIRING_VARIANT_PIN = 0;
    private static final String TAG = "BleManager";

    @Deprecated
    private ValueChangedCallback mBatteryLevelNotificationCallback;
    private BluetoothDevice mBluetoothDevice;
    private BluetoothGatt mBluetoothGatt;
    protected E mCallbacks;
    private ConnectRequest mConnectRequest;
    private boolean mConnected;
    private long mConnectionTime;
    private final Context mContext;
    private BleManager<E>.BleManagerGattCallback mGattCallback;
    private boolean mInitialConnection;
    private boolean mReady;
    private boolean mReliableWriteInProgress;
    private Request mRequest;
    private RequestQueue mRequestQueue;
    private boolean mServiceDiscoveryRequested;
    private boolean mServicesDiscovered;
    private boolean mUserDisconnected;
    private WaitForValueChangedRequest mValueChangedRequest;
    private static final UUID CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    private static final UUID BATTERY_SERVICE = UUID.fromString("0000180F-0000-1000-8000-00805f9b34fb");
    private static final UUID BATTERY_LEVEL_CHARACTERISTIC = UUID.fromString("00002A19-0000-1000-8000-00805f9b34fb");
    private static final UUID GENERIC_ATTRIBUTE_SERVICE = UUID.fromString("00001801-0000-1000-8000-00805f9b34fb");
    private static final UUID SERVICE_CHANGED_CHARACTERISTIC = UUID.fromString("00002A05-0000-1000-8000-00805f9b34fb");
    private final Object mLock = new Object();
    private int mConnectionCount = 0;
    private int mConnectionState = 0;

    @Deprecated
    private int mBatteryValue = -1;
    private int mMtu = 23;
    private final HashMap<BluetoothGattCharacteristic, ValueChangedCallback> mNotificationCallbacks = new HashMap<>();
    private final BroadcastReceiver mBluetoothStateBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.ble.BleManager.1
        private String state2String(int i) {
            switch (i) {
                case 10:
                    return "OFF";
                case 11:
                    return "TURNING ON";
                case 12:
                    return "ON";
                case 13:
                    return "TURNING OFF";
                default:
                    return "UNKNOWN (" + i + ")";
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
            int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", 10);
            BleManager.this.log(3, "[Broadcast] Action received: android.bluetooth.adapter.action.STATE_CHANGED, state changed to " + state2String(intExtra));
            if (intExtra == 10 || intExtra == 13) {
                if (intExtra2 != 13 && intExtra2 != 10) {
                    BleManagerGattCallback bleManagerGattCallback = BleManager.this.mGattCallback;
                    if (bleManagerGattCallback != null) {
                        bleManagerGattCallback.mOperationInProgress = true;
                        bleManagerGattCallback.cancelQueue();
                        bleManagerGattCallback.mInitQueue = null;
                    }
                    BluetoothDevice bluetoothDevice = BleManager.this.mBluetoothDevice;
                    if (bluetoothDevice != null) {
                        if (BleManager.this.mRequest != null && BleManager.this.mRequest.type != Request.Type.DISCONNECT) {
                            BleManager.this.mRequest.notifyFail(bluetoothDevice, -100);
                            BleManager.this.mRequest = null;
                        }
                        if (BleManager.this.mValueChangedRequest != null) {
                            BleManager.this.mValueChangedRequest.notifyFail(bluetoothDevice, -100);
                            BleManager.this.mValueChangedRequest = null;
                        }
                        if (BleManager.this.mConnectRequest != null) {
                            BleManager.this.mConnectRequest.notifyFail(bluetoothDevice, -100);
                            BleManager.this.mConnectRequest = null;
                        }
                    }
                    BleManager.this.mUserDisconnected = true;
                    if (bleManagerGattCallback != null) {
                        bleManagerGattCallback.mOperationInProgress = false;
                        if (bluetoothDevice != null) {
                            bleManagerGattCallback.notifyDeviceDisconnected(bluetoothDevice);
                            return;
                        }
                        return;
                    }
                    return;
                }
                BleManager.this.close();
            }
        }
    };
    private BroadcastReceiver mBondingBroadcastReceiver = new AnonymousClass2();
    private final BroadcastReceiver mPairingRequestBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.ble.BleManager.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (BleManager.this.mBluetoothDevice == null || bluetoothDevice == null || !bluetoothDevice.getAddress().equals(BleManager.this.mBluetoothDevice.getAddress())) {
                return;
            }
            int intExtra = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", 0);
            BleManager.this.log(3, "[Broadcast] Action received: android.bluetooth.device.action.PAIRING_REQUEST, pairing variant: " + BleManager.this.pairingVariantToString(intExtra) + " (" + intExtra + ")");
            BleManager.this.onPairingRequestReceived(bluetoothDevice, intExtra);
        }
    };
    final Handler mHandler = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: no.nordicsemi.android.ble.BleManager$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 extends BroadcastReceiver {
        AnonymousClass2() {
        }

        public /* synthetic */ void a() {
            BleManager.this.log(2, "Discovering services...");
            BleManager.this.log(3, "gatt.discoverServices()");
            BleManager.this.mBluetoothGatt.discoverServices();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1);
            int intExtra2 = intent.getIntExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", -1);
            if (BleManager.this.mBluetoothDevice == null || !bluetoothDevice.getAddress().equals(BleManager.this.mBluetoothDevice.getAddress())) {
                return;
            }
            BleManager.this.log(3, "[Broadcast] Action received: android.bluetooth.device.action.BOND_STATE_CHANGED, bond state changed to: " + BleManager.this.bondStateToString(intExtra) + " (" + intExtra + ")");
            switch (intExtra) {
                case 10:
                    if (intExtra2 != 11) {
                        if (intExtra2 == 12 && BleManager.this.mRequest != null && BleManager.this.mRequest.type == Request.Type.REMOVE_BOND) {
                            BleManager.this.log(4, "Bond information removed");
                            BleManager.this.mRequest.notifySuccess(bluetoothDevice);
                            BleManager.this.mRequest = null;
                            break;
                        }
                    } else {
                        BleManager.this.mCallbacks.onBondingFailed(bluetoothDevice);
                        BleManager.this.log(5, "Bonding failed");
                        if (BleManager.this.mRequest != null) {
                            BleManager.this.mRequest.notifyFail(bluetoothDevice, -4);
                            BleManager.this.mRequest = null;
                            break;
                        }
                    }
                    break;
                case 11:
                    BleManager.this.mCallbacks.onBondingRequired(bluetoothDevice);
                    return;
                case 12:
                    BleManager.this.log(4, "Device bonded");
                    BleManager.this.mCallbacks.onBonded(bluetoothDevice);
                    if (BleManager.this.mRequest == null || BleManager.this.mRequest.type != Request.Type.CREATE_BOND) {
                        if (!BleManager.this.mServicesDiscovered && !BleManager.this.mServiceDiscoveryRequested) {
                            BleManager.this.mServiceDiscoveryRequested = true;
                            BleManager.this.mHandler.post(new Runnable() { // from class: no.nordicsemi.android.ble.a
                                @Override // java.lang.Runnable
                                public final void run() {
                                    BleManager.AnonymousClass2.this.a();
                                }
                            });
                            return;
                        } else if (Build.VERSION.SDK_INT < 26 && BleManager.this.mRequest != null && BleManager.this.mRequest.type != Request.Type.CREATE_BOND) {
                            BleManager.this.mGattCallback.enqueueFirst(BleManager.this.mRequest);
                            break;
                        } else {
                            return;
                        }
                    } else {
                        BleManager.this.mRequest.notifySuccess(bluetoothDevice);
                        BleManager.this.mRequest = null;
                        break;
                    }
            }
            BleManager.this.mGattCallback.nextRequest(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: no.nordicsemi.android.ble.BleManager$4, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$no$nordicsemi$android$ble$Request$Type = new int[Request.Type.values().length];

        static {
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.WAIT_FOR_INDICATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.WAIT_FOR_NOTIFICATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.CONNECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.DISCONNECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.CREATE_BOND.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.REMOVE_BOND.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.SET.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.READ.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.WRITE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.READ_DESCRIPTOR.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.WRITE_DESCRIPTOR.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.BEGIN_RELIABLE_WRITE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.EXECUTE_RELIABLE_WRITE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.ABORT_RELIABLE_WRITE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.ENABLE_NOTIFICATIONS.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.ENABLE_INDICATIONS.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.DISABLE_NOTIFICATIONS.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.DISABLE_INDICATIONS.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.READ_BATTERY_LEVEL.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.ENABLE_BATTERY_LEVEL_NOTIFICATIONS.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.DISABLE_BATTERY_LEVEL_NOTIFICATIONS.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.ENABLE_SERVICE_CHANGED_INDICATIONS.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.REQUEST_MTU.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.REQUEST_CONNECTION_PRIORITY.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.SET_PREFERRED_PHY.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.READ_PHY.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.READ_RSSI.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.REFRESH_CACHE.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$ble$Request$Type[Request.Type.SLEEP.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PairingVariant {
    }

    public BleManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    static /* synthetic */ int access$1804(BleManager bleManager) {
        int i = bleManager.mConnectionCount + 1;
        bleManager.mConnectionCount = i;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ensureServiceChangedEnabled() {
        BluetoothGattService service;
        BluetoothGattCharacteristic characteristic;
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null || !this.mConnected || bluetoothGatt.getDevice().getBondState() != 12 || (service = bluetoothGatt.getService(GENERIC_ATTRIBUTE_SERVICE)) == null || (characteristic = service.getCharacteristic(SERVICE_CHANGED_CHARACTERISTIC)) == null) {
            return false;
        }
        log(4, "Service Changed characteristic found on a bonded device");
        return internalEnableIndications(characteristic);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BluetoothGattDescriptor getCccd(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        if (bluetoothGattCharacteristic == null || (i & bluetoothGattCharacteristic.getProperties()) == 0) {
            return null;
        }
        return bluetoothGattCharacteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalAbortReliableWrite() {
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null || !this.mConnected || !this.mReliableWriteInProgress) {
            return false;
        }
        log(2, "Aborting reliable write...");
        if (Build.VERSION.SDK_INT >= 19) {
            log(3, "gatt.abortReliableWrite()");
            bluetoothGatt.abortReliableWrite();
            return true;
        }
        log(3, "gatt.abortReliableWrite(device)");
        bluetoothGatt.abortReliableWrite(this.mBluetoothDevice);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalBeginReliableWrite() {
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null || !this.mConnected) {
            return false;
        }
        if (this.mReliableWriteInProgress) {
            return true;
        }
        log(2, "Beginning reliable write...");
        log(3, "gatt.beginReliableWrite()");
        boolean beginReliableWrite = bluetoothGatt.beginReliableWrite();
        this.mReliableWriteInProgress = beginReliableWrite;
        return beginReliableWrite;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalConnect(BluetoothDevice bluetoothDevice, ConnectRequest connectRequest) {
        boolean isEnabled = BluetoothAdapter.getDefaultAdapter().isEnabled();
        if (!this.mConnected && isEnabled) {
            synchronized (this.mLock) {
                if (this.mBluetoothGatt != null) {
                    if (!this.mInitialConnection) {
                        log(3, "gatt.close()");
                        this.mBluetoothGatt.close();
                        this.mBluetoothGatt = null;
                        try {
                            log(3, "wait(200)");
                            Thread.sleep(200L);
                        } catch (InterruptedException unused) {
                        }
                    } else {
                        this.mInitialConnection = false;
                        this.mConnectionTime = 0L;
                        this.mConnectionState = 1;
                        log(2, "Connecting...");
                        this.mCallbacks.onDeviceConnecting(bluetoothDevice);
                        log(3, "gatt.connect()");
                        this.mBluetoothGatt.connect();
                        return true;
                    }
                } else {
                    this.mContext.registerReceiver(this.mBluetoothStateBroadcastReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
                    this.mContext.registerReceiver(this.mBondingBroadcastReceiver, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
                    this.mContext.registerReceiver(this.mPairingRequestBroadcastReceiver, new IntentFilter("android.bluetooth.device.action.PAIRING_REQUEST"));
                }
                if (connectRequest == null) {
                    return false;
                }
                boolean shouldAutoConnect = connectRequest.shouldAutoConnect();
                this.mUserDisconnected = !shouldAutoConnect;
                if (shouldAutoConnect) {
                    this.mInitialConnection = true;
                }
                this.mBluetoothDevice = bluetoothDevice;
                this.mGattCallback.setHandler(this.mHandler);
                log(2, connectRequest.isFirstAttempt() ? "Connecting..." : "Retrying...");
                this.mConnectionState = 1;
                this.mCallbacks.onDeviceConnecting(bluetoothDevice);
                this.mConnectionTime = SystemClock.elapsedRealtime();
                int i = Build.VERSION.SDK_INT;
                if (i >= 26) {
                    int preferredPhy = connectRequest.getPreferredPhy();
                    log(3, "gatt = device.connectGatt(autoConnect = false, TRANSPORT_LE, " + phyMaskToString(preferredPhy) + ")");
                    this.mBluetoothGatt = bluetoothDevice.connectGatt(this.mContext, false, this.mGattCallback, 2, preferredPhy);
                } else if (i >= 23) {
                    log(3, "gatt = device.connectGatt(autoConnect = false, TRANSPORT_LE)");
                    this.mBluetoothGatt = bluetoothDevice.connectGatt(this.mContext, false, this.mGattCallback, 2);
                } else {
                    log(3, "gatt = device.connectGatt(autoConnect = false)");
                    this.mBluetoothGatt = bluetoothDevice.connectGatt(this.mContext, false, this.mGattCallback);
                }
                return true;
            }
        }
        BluetoothDevice bluetoothDevice2 = this.mBluetoothDevice;
        if (isEnabled && bluetoothDevice2 != null && bluetoothDevice2.equals(bluetoothDevice)) {
            this.mConnectRequest.notifySuccess(bluetoothDevice);
        } else {
            ConnectRequest connectRequest2 = this.mConnectRequest;
            if (connectRequest2 != null) {
                connectRequest2.notifyFail(bluetoothDevice, isEnabled ? -4 : -100);
            }
        }
        this.mConnectRequest = null;
        BleManager<E>.BleManagerGattCallback bleManagerGattCallback = this.mGattCallback;
        if (bleManagerGattCallback != null) {
            bleManagerGattCallback.nextRequest(true);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalCreateBond() {
        BluetoothDevice bluetoothDevice = this.mBluetoothDevice;
        if (bluetoothDevice == null) {
            return false;
        }
        log(2, "Starting pairing...");
        if (bluetoothDevice.getBondState() == 12) {
            log(5, "Device already bonded");
            this.mRequest.notifySuccess(bluetoothDevice);
            this.mGattCallback.nextRequest(true);
            return true;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            log(3, "device.createBond()");
            return bluetoothDevice.createBond();
        }
        try {
            Method method = bluetoothDevice.getClass().getMethod("createBond", new Class[0]);
            log(3, "device.createBond() (hidden)");
            return ((Boolean) method.invoke(bluetoothDevice, new Object[0])).booleanValue();
        } catch (Exception e2) {
            Log.w(TAG, "An exception occurred while creating bond", e2);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalDisableIndications(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return internalDisableNotifications(bluetoothGattCharacteristic);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalDisableNotifications(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BluetoothGattDescriptor cccd;
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null || !this.mConnected || (cccd = getCccd(bluetoothGattCharacteristic, 16)) == null) {
            return false;
        }
        log(3, "gatt.setCharacteristicNotification(" + bluetoothGattCharacteristic.getUuid() + ", false)");
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, false);
        cccd.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
        log(2, "Disabling notifications and indications for " + bluetoothGattCharacteristic.getUuid());
        log(3, "gatt.writeDescriptor(" + CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID + ", value=0x00-00)");
        return internalWriteDescriptorWorkaround(cccd);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalDisconnect() {
        this.mUserDisconnected = true;
        this.mInitialConnection = false;
        this.mReady = false;
        if (this.mBluetoothGatt != null) {
            this.mConnectionState = 3;
            log(2, this.mConnected ? "Disconnecting..." : "Cancelling connection...");
            this.mCallbacks.onDeviceDisconnecting(this.mBluetoothGatt.getDevice());
            boolean z = this.mConnected;
            log(3, "gatt.disconnect()");
            this.mBluetoothGatt.disconnect();
            if (z) {
                return true;
            }
            this.mConnectionState = 0;
            log(4, "Disconnected");
            this.mCallbacks.onDeviceDisconnected(this.mBluetoothGatt.getDevice());
        }
        Request request = this.mRequest;
        if (request != null && request.type == Request.Type.DISCONNECT) {
            BluetoothDevice bluetoothDevice = this.mBluetoothDevice;
            if (bluetoothDevice != null) {
                request.notifySuccess(bluetoothDevice);
            } else {
                request.notifyInvalidRequest();
            }
        }
        BleManager<E>.BleManagerGattCallback bleManagerGattCallback = this.mGattCallback;
        if (bleManagerGattCallback != null) {
            bleManagerGattCallback.nextRequest(true);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalEnableIndications(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BluetoothGattDescriptor cccd;
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null || !this.mConnected || (cccd = getCccd(bluetoothGattCharacteristic, 32)) == null) {
            return false;
        }
        log(3, "gatt.setCharacteristicNotification(" + bluetoothGattCharacteristic.getUuid() + ", true)");
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
        cccd.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
        log(2, "Enabling indications for " + bluetoothGattCharacteristic.getUuid());
        log(3, "gatt.writeDescriptor(" + CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID + ", value=0x02-00)");
        return internalWriteDescriptorWorkaround(cccd);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalEnableNotifications(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BluetoothGattDescriptor cccd;
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null || !this.mConnected || (cccd = getCccd(bluetoothGattCharacteristic, 16)) == null) {
            return false;
        }
        log(3, "gatt.setCharacteristicNotification(" + bluetoothGattCharacteristic.getUuid() + ", true)");
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
        cccd.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        log(2, "Enabling notifications for " + bluetoothGattCharacteristic.getUuid());
        log(3, "gatt.writeDescriptor(" + CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID + ", value=0x01-00)");
        return internalWriteDescriptorWorkaround(cccd);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalExecuteReliableWrite() {
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null || !this.mConnected || !this.mReliableWriteInProgress) {
            return false;
        }
        log(2, "Executing reliable write...");
        log(3, "gatt.executeReliableWrite()");
        return bluetoothGatt.executeReliableWrite();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    public boolean internalReadBatteryLevel() {
        BluetoothGattService service;
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null || !this.mConnected || (service = bluetoothGatt.getService(BATTERY_SERVICE)) == null) {
            return false;
        }
        return internalReadCharacteristic(service.getCharacteristic(BATTERY_LEVEL_CHARACTERISTIC));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalReadCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null || !this.mConnected || (bluetoothGattCharacteristic.getProperties() & 2) == 0) {
            return false;
        }
        log(2, "Reading characteristic " + bluetoothGattCharacteristic.getUuid());
        log(3, "gatt.readCharacteristic(" + bluetoothGattCharacteristic.getUuid() + ")");
        return bluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalReadDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor) {
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null || bluetoothGattDescriptor == null || !this.mConnected) {
            return false;
        }
        log(2, "Reading descriptor " + bluetoothGattDescriptor.getUuid());
        log(3, "gatt.readDescriptor(" + bluetoothGattDescriptor.getUuid() + ")");
        return bluetoothGatt.readDescriptor(bluetoothGattDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalReadPhy() {
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null || !this.mConnected) {
            return false;
        }
        log(2, "Reading PHY...");
        log(3, "gatt.readPhy()");
        bluetoothGatt.readPhy();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalReadRssi() {
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null || !this.mConnected) {
            return false;
        }
        log(2, "Reading remote RSSI...");
        log(3, "gatt.readRemoteRssi()");
        return bluetoothGatt.readRemoteRssi();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalRefreshDeviceCache() {
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null) {
            return false;
        }
        log(2, "Refreshing device cache...");
        log(3, "gatt.refresh() (hidden)");
        try {
            return ((Boolean) bluetoothGatt.getClass().getMethod("refresh", new Class[0]).invoke(bluetoothGatt, new Object[0])).booleanValue();
        } catch (Exception e2) {
            Log.w(TAG, "An exception occurred while refreshing device", e2);
            log(5, "gatt.refresh() method not found");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalRemoveBond() {
        BluetoothDevice bluetoothDevice = this.mBluetoothDevice;
        if (bluetoothDevice == null) {
            return false;
        }
        log(2, "Removing bond information...");
        if (bluetoothDevice.getBondState() == 10) {
            log(5, "Device is not bonded");
            this.mRequest.notifySuccess(bluetoothDevice);
            this.mGattCallback.nextRequest(true);
            return true;
        }
        try {
            Method method = bluetoothDevice.getClass().getMethod("removeBond", new Class[0]);
            log(3, "device.removeBond() (hidden)");
            return ((Boolean) method.invoke(bluetoothDevice, new Object[0])).booleanValue();
        } catch (Exception e2) {
            Log.w(TAG, "An exception occurred while removing bond", e2);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalRequestConnectionPriority(int i) {
        String str;
        String str2;
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null || !this.mConnected) {
            return false;
        }
        if (i == 1) {
            str = Build.VERSION.SDK_INT >= 23 ? "HIGH (11.25–15ms, 0, 20s)" : "HIGH (7.5–10ms, 0, 20s)";
            str2 = "HIGH";
        } else if (i != 2) {
            str = "BALANCED (30–50ms, 0, 20s)";
            str2 = "BALANCED";
        } else {
            str = "LOW POWER (100–125ms, 2, 20s)";
            str2 = "LOW POWER";
        }
        log(2, "Requesting connection priority: " + str + "...");
        log(3, "gatt.requestConnectionPriority(" + str2 + ")");
        return bluetoothGatt.requestConnectionPriority(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalRequestMtu(int i) {
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null || !this.mConnected) {
            return false;
        }
        log(2, "Requesting new MTU...");
        log(3, "gatt.requestMtu(" + i + ")");
        return bluetoothGatt.requestMtu(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    public boolean internalSetBatteryNotifications(boolean z) {
        BluetoothGattService service;
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null || !this.mConnected || (service = bluetoothGatt.getService(BATTERY_SERVICE)) == null) {
            return false;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(BATTERY_LEVEL_CHARACTERISTIC);
        if (z) {
            return internalEnableNotifications(characteristic);
        }
        return internalDisableNotifications(characteristic);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalSetPreferredPhy(int i, int i2, int i3) {
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null || !this.mConnected) {
            return false;
        }
        log(2, "Requesting preferred PHYs...");
        log(3, "gatt.setPreferredPhy(" + phyMaskToString(i) + ", " + phyMaskToString(i2) + ", coding option = " + phyCodedOptionToString(i3) + ")");
        bluetoothGatt.setPreferredPhy(i, i2, i3);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalWriteCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null || !this.mConnected || (bluetoothGattCharacteristic.getProperties() & 12) == 0) {
            return false;
        }
        log(2, "Writing characteristic " + bluetoothGattCharacteristic.getUuid() + " (" + writeTypeToString(bluetoothGattCharacteristic.getWriteType()) + ")");
        StringBuilder sb = new StringBuilder();
        sb.append("gatt.writeCharacteristic(");
        sb.append(bluetoothGattCharacteristic.getUuid());
        sb.append(")");
        log(3, sb.toString());
        return bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean internalWriteDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor) {
        if (this.mBluetoothGatt == null || bluetoothGattDescriptor == null || !this.mConnected) {
            return false;
        }
        log(2, "Writing descriptor " + bluetoothGattDescriptor.getUuid());
        log(3, "gatt.writeDescriptor(" + bluetoothGattDescriptor.getUuid() + ")");
        return internalWriteDescriptorWorkaround(bluetoothGattDescriptor);
    }

    private boolean internalWriteDescriptorWorkaround(BluetoothGattDescriptor bluetoothGattDescriptor) {
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null || bluetoothGattDescriptor == null || !this.mConnected) {
            return false;
        }
        BluetoothGattCharacteristic characteristic = bluetoothGattDescriptor.getCharacteristic();
        int writeType = characteristic.getWriteType();
        characteristic.setWriteType(2);
        boolean writeDescriptor = bluetoothGatt.writeDescriptor(bluetoothGattDescriptor);
        characteristic.setWriteType(writeType);
        return writeDescriptor;
    }

    private String phyCodedOptionToString(int i) {
        if (i == 0) {
            return "No preferred";
        }
        if (i == 1) {
            return "S2";
        }
        if (i == 2) {
            return "S8";
        }
        return "UNKNOWN (" + i + ")";
    }

    private String phyMaskToString(int i) {
        switch (i) {
            case 1:
                return "LE 1M";
            case 2:
                return "LE 2M";
            case 3:
                return "LE 1M or LE 2M";
            case 4:
                return "LE Coded";
            case 5:
                return "LE 1M or LE Coded";
            case 6:
                return "LE 2M or LE Coded";
            case 7:
                return "LE 1M, LE 2M or LE Coded";
            default:
                return "UNKNOWN (" + i + ")";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String phyToString(int i) {
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

    private void runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.mHandler.post(runnable);
        } else {
            runnable.run();
        }
    }

    public /* synthetic */ void a(BluetoothDevice bluetoothDevice, Data data) {
        if (data.size() == 1) {
            int intValue = data.getIntValue(17, 0).intValue();
            this.mBatteryValue = intValue;
            BleManager<E>.BleManagerGattCallback bleManagerGattCallback = this.mGattCallback;
            if (bleManagerGattCallback != null) {
                bleManagerGattCallback.onBatteryValueReceived(this.mBluetoothGatt, intValue);
            }
            this.mCallbacks.onBatteryValueReceived(bluetoothDevice, intValue);
        }
    }

    public /* synthetic */ void b(BluetoothDevice bluetoothDevice, Data data) {
        if (data.size() == 1) {
            int intValue = data.getIntValue(17, 0).intValue();
            log(4, "Battery Level received: " + intValue + "%");
            this.mBatteryValue = intValue;
            BleManager<E>.BleManagerGattCallback bleManagerGattCallback = this.mGattCallback;
            if (bleManagerGattCallback != null) {
                bleManagerGattCallback.onBatteryValueReceived(this.mBluetoothGatt, intValue);
            }
            this.mCallbacks.onBatteryValueReceived(bluetoothDevice, intValue);
        }
    }

    protected RequestQueue beginAtomicRequestQueue() {
        return new RequestQueue().setManager((BleManager) this);
    }

    protected ReliableWriteRequest beginReliableWrite() {
        return Request.newReliableWriteRequest().setManager((BleManager) this);
    }

    protected String bondStateToString(int i) {
        switch (i) {
            case 10:
                return "BOND_NONE";
            case 11:
                return "BOND_BONDING";
            case 12:
                return "BOND_BONDED";
            default:
                return "UNKNOWN";
        }
    }

    protected void clearQueue() {
        BleManager<E>.BleManagerGattCallback bleManagerGattCallback = this.mGattCallback;
        if (bleManagerGattCallback != null) {
            bleManagerGattCallback.cancelQueue();
        }
    }

    public void close() {
        try {
            this.mContext.unregisterReceiver(this.mBluetoothStateBroadcastReceiver);
            this.mContext.unregisterReceiver(this.mBondingBroadcastReceiver);
            this.mContext.unregisterReceiver(this.mPairingRequestBroadcastReceiver);
        } catch (Exception unused) {
        }
        synchronized (this.mLock) {
            if (this.mBluetoothGatt != null) {
                if (shouldClearCacheWhenDisconnected()) {
                    if (internalRefreshDeviceCache()) {
                        log(4, "Cache refreshed");
                    } else {
                        log(5, "Refreshing failed");
                    }
                }
                log(3, "gatt.close()");
                this.mBluetoothGatt.close();
                this.mBluetoothGatt = null;
            }
            this.mConnected = false;
            this.mInitialConnection = false;
            this.mReliableWriteInProgress = false;
            this.mNotificationCallbacks.clear();
            this.mConnectionState = 0;
            if (this.mGattCallback != null) {
                this.mGattCallback.cancelQueue();
                ((BleManagerGattCallback) this.mGattCallback).mInitQueue = null;
            }
            this.mGattCallback = null;
            this.mBluetoothDevice = null;
        }
    }

    public final ConnectRequest connect(BluetoothDevice bluetoothDevice) {
        if (this.mCallbacks == null) {
            throw new NullPointerException("Set callbacks using setGattCallbacks(E callbacks) before connecting");
        }
        if (bluetoothDevice != null) {
            return Request.connect(bluetoothDevice).useAutoConnect(shouldAutoConnect()).setManager((BleManager) this);
        }
        throw new NullPointerException("Bluetooth device not specified");
    }

    protected Request createBond() {
        return Request.createBond().setManager(this);
    }

    @Deprecated
    protected void disableBatteryLevelNotifications() {
        Request.newDisableBatteryLevelNotificationsRequest().setManager((BleManager) this).done(new SuccessCallback() { // from class: no.nordicsemi.android.ble.k
            @Override // no.nordicsemi.android.ble.callback.SuccessCallback
            public final void onRequestCompleted(BluetoothDevice bluetoothDevice) {
                BleManager.this.a(bluetoothDevice);
            }
        }).enqueue();
    }

    protected WriteRequest disableIndications(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return Request.newDisableIndicationsRequest(bluetoothGattCharacteristic).setManager((BleManager) this);
    }

    protected WriteRequest disableNotifications(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return Request.newDisableNotificationsRequest(bluetoothGattCharacteristic).setManager((BleManager) this);
    }

    public final DisconnectRequest disconnect() {
        return Request.disconnect().setManager((BleManager) this);
    }

    @Deprecated
    protected void enableBatteryLevelNotifications() {
        if (this.mBatteryLevelNotificationCallback == null) {
            this.mBatteryLevelNotificationCallback = new ValueChangedCallback().with(new DataReceivedCallback() { // from class: no.nordicsemi.android.ble.i
                @Override // no.nordicsemi.android.ble.callback.DataReceivedCallback
                public final void onDataReceived(BluetoothDevice bluetoothDevice, Data data) {
                    BleManager.this.a(bluetoothDevice, data);
                }
            });
        }
        Request.newEnableBatteryLevelNotificationsRequest().setManager((BleManager) this).done(new SuccessCallback() { // from class: no.nordicsemi.android.ble.j
            @Override // no.nordicsemi.android.ble.callback.SuccessCallback
            public final void onRequestCompleted(BluetoothDevice bluetoothDevice) {
                BleManager.this.b(bluetoothDevice);
            }
        }).enqueue();
    }

    protected WriteRequest enableIndications(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return Request.newEnableIndicationsRequest(bluetoothGattCharacteristic).setManager((BleManager) this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WriteRequest enableNotifications(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return Request.newEnableNotificationsRequest(bluetoothGattCharacteristic).setManager((BleManager) this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public final void enqueue(Request request) {
        final BleManager<E>.BleManagerGattCallback bleManagerGattCallback = this.mGattCallback;
        if (bleManagerGattCallback == null) {
            bleManagerGattCallback = getGattCallback();
            this.mGattCallback = bleManagerGattCallback;
        }
        bleManagerGattCallback.enqueue(request);
        runOnUiThread(new Runnable() { // from class: no.nordicsemi.android.ble.g
            @Override // java.lang.Runnable
            public final void run() {
                BleManager.BleManagerGattCallback.this.nextRequest(false);
            }
        });
    }

    @Deprecated
    public final int getBatteryValue() {
        return this.mBatteryValue;
    }

    public BluetoothDevice getBluetoothDevice() {
        return this.mBluetoothDevice;
    }

    public final int getConnectionState() {
        return this.mConnectionState;
    }

    protected final Context getContext() {
        return this.mContext;
    }

    protected abstract BleManager<E>.BleManagerGattCallback getGattCallback();

    /* JADX INFO: Access modifiers changed from: protected */
    public int getMtu() {
        return this.mMtu;
    }

    protected int getServiceDiscoveryDelay(boolean z) {
        return z ? 1600 : 300;
    }

    protected final boolean isBonded() {
        BluetoothDevice bluetoothDevice = this.mBluetoothDevice;
        return bluetoothDevice != null && bluetoothDevice.getBondState() == 12;
    }

    public final boolean isConnected() {
        return this.mConnected;
    }

    public final boolean isReady() {
        return this.mReady;
    }

    protected final boolean isReliableWriteInProgress() {
        return this.mReliableWriteInProgress;
    }

    @Override // no.nordicsemi.android.ble.utils.ILogger
    public void log(int i, int i2, Object... objArr) {
        log(i, this.mContext.getString(i2, objArr));
    }

    public void log(int i, String str) {
    }

    protected void onPairingRequestReceived(BluetoothDevice bluetoothDevice, int i) {
    }

    @Override // no.nordicsemi.android.ble.TimeoutHandler
    void onRequestTimeout(TimeoutableRequest timeoutableRequest) {
        this.mRequest = null;
        this.mValueChangedRequest = null;
        Request.Type type = timeoutableRequest.type;
        if (type == Request.Type.CONNECT) {
            this.mConnectRequest = null;
            internalDisconnect();
        } else {
            if (type == Request.Type.DISCONNECT) {
                close();
                return;
            }
            BleManager<E>.BleManagerGattCallback bleManagerGattCallback = this.mGattCallback;
            if (bleManagerGattCallback != null) {
                bleManagerGattCallback.nextRequest(true);
            }
        }
    }

    protected void overrideMtu(int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.mMtu = i;
        }
    }

    protected String pairingVariantToString(int i) {
        switch (i) {
            case 0:
                return "PAIRING_VARIANT_PIN";
            case 1:
                return "PAIRING_VARIANT_PASSKEY";
            case 2:
                return "PAIRING_VARIANT_PASSKEY_CONFIRMATION";
            case 3:
                return "PAIRING_VARIANT_CONSENT";
            case 4:
                return "PAIRING_VARIANT_DISPLAY_PASSKEY";
            case 5:
                return "PAIRING_VARIANT_DISPLAY_PIN";
            case 6:
                return "PAIRING_VARIANT_OOB_CONSENT";
            default:
                return "UNKNOWN";
        }
    }

    @Deprecated
    protected void readBatteryLevel() {
        Request.newReadBatteryLevelRequest().setManager((BleManager) this).with(new DataReceivedCallback() { // from class: no.nordicsemi.android.ble.h
            @Override // no.nordicsemi.android.ble.callback.DataReceivedCallback
            public final void onDataReceived(BluetoothDevice bluetoothDevice, Data data) {
                BleManager.this.b(bluetoothDevice, data);
            }
        }).enqueue();
    }

    protected ReadRequest readCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return Request.newReadRequest(bluetoothGattCharacteristic).setManager((BleManager) this);
    }

    protected ReadRequest readDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor) {
        return Request.newReadRequest(bluetoothGattDescriptor).setManager((BleManager) this);
    }

    protected PhyRequest readPhy() {
        return Request.newReadPhyRequest().setManager((BleManager) this);
    }

    protected ReadRssiRequest readRssi() {
        return Request.newReadRssiRequest().setManager((BleManager) this);
    }

    protected Request refreshDeviceCache() {
        return Request.newRefreshCacheRequest().setManager(this);
    }

    protected Request removeBond() {
        return Request.removeBond().setManager(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ConnectionPriorityRequest requestConnectionPriority(int i) {
        return Request.newConnectionPriorityRequest(i).setManager((BleManager) this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MtuRequest requestMtu(int i) {
        return Request.newMtuRequest(i).setManager((BleManager) this);
    }

    public void setGattCallbacks(E e2) {
        this.mCallbacks = e2;
    }

    protected ValueChangedCallback setIndicationCallback(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return setNotificationCallback(bluetoothGattCharacteristic);
    }

    protected ValueChangedCallback setNotificationCallback(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        ValueChangedCallback valueChangedCallback = this.mNotificationCallbacks.get(bluetoothGattCharacteristic);
        if (valueChangedCallback == null) {
            valueChangedCallback = new ValueChangedCallback();
            if (bluetoothGattCharacteristic != null) {
                this.mNotificationCallbacks.put(bluetoothGattCharacteristic, valueChangedCallback);
            }
        }
        return valueChangedCallback.free();
    }

    protected PhyRequest setPreferredPhy(int i, int i2, int i3) {
        return Request.newSetPreferredPhyRequest(i, i2, i3).setManager((BleManager) this);
    }

    @Deprecated
    protected boolean shouldAutoConnect() {
        return false;
    }

    protected boolean shouldClearCacheWhenDisconnected() {
        return false;
    }

    protected SleepRequest sleep(long j) {
        return Request.newSleepRequest(j).setManager((BleManager) this);
    }

    protected String stateToString(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "DISCONNECTED" : "DISCONNECTING" : "CONNECTED" : "CONNECTING";
    }

    protected WaitForValueChangedRequest waitForIndication(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return Request.newWaitForIndicationRequest(bluetoothGattCharacteristic).setManager((BleManager) this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WaitForValueChangedRequest waitForNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return Request.newWaitForNotificationRequest(bluetoothGattCharacteristic).setManager((BleManager) this);
    }

    protected WriteRequest writeCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic, Data data) {
        return Request.newWriteRequest(bluetoothGattCharacteristic, data != null ? data.getValue() : null).setManager((BleManager) this);
    }

    protected WriteRequest writeDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor, Data data) {
        return Request.newWriteRequest(bluetoothGattDescriptor, data != null ? data.getValue() : null).setManager((BleManager) this);
    }

    protected String writeTypeToString(int i) {
        if (i == 1) {
            return "WRITE COMMAND";
        }
        if (i == 2) {
            return "WRITE REQUEST";
        }
        if (i == 4) {
            return "WRITE SIGNED";
        }
        return "UNKNOWN: " + i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WriteRequest writeCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        return Request.newWriteRequest(bluetoothGattCharacteristic, bArr).setManager((BleManager) this);
    }

    protected WriteRequest writeDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr) {
        return Request.newWriteRequest(bluetoothGattDescriptor, bArr).setManager((BleManager) this);
    }

    protected WriteRequest writeCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i, int i2) {
        return Request.newWriteRequest(bluetoothGattCharacteristic, bArr, i, i2).setManager((BleManager) this);
    }

    protected WriteRequest writeDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr, int i, int i2) {
        return Request.newWriteRequest(bluetoothGattDescriptor, bArr, i, i2).setManager((BleManager) this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public abstract class BleManagerGattCallback extends MainThreadBluetoothGattCallback {
        private static final String ERROR_AUTH_ERROR_WHILE_BONDED = "Phone has lost bonding information";
        private static final String ERROR_CONNECTION_PRIORITY_REQUEST = "Error on connection priority request";
        private static final String ERROR_CONNECTION_STATE_CHANGE = "Error on connection state change";
        private static final String ERROR_DISCOVERY_SERVICE = "Error on discovering services";
        private static final String ERROR_MTU_REQUEST = "Error on mtu request";
        private static final String ERROR_PHY_UPDATE = "Error on PHY update";
        private static final String ERROR_READ_CHARACTERISTIC = "Error on reading characteristic";
        private static final String ERROR_READ_DESCRIPTOR = "Error on reading descriptor";
        private static final String ERROR_READ_PHY = "Error on PHY read";
        private static final String ERROR_READ_RSSI = "Error on RSSI read";
        private static final String ERROR_RELIABLE_WRITE = "Error on Execute Reliable Write";
        private static final String ERROR_WRITE_CHARACTERISTIC = "Error on writing characteristic";
        private static final String ERROR_WRITE_DESCRIPTOR = "Error on writing descriptor";
        private boolean mInitInProgress;
        private Deque<Request> mInitQueue;
        private boolean mOperationInProgress;
        private final Deque<Request> mTaskQueue = new LinkedList();
        private boolean mConnectionPriorityOperationInProgress = false;

        /* JADX INFO: Access modifiers changed from: protected */
        public BleManagerGattCallback() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void enqueue(Request request) {
            (this.mInitInProgress ? this.mInitQueue : this.mTaskQueue).add(request);
            request.enqueued = true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void enqueueFirst(Request request) {
            (this.mInitInProgress ? this.mInitQueue : this.mTaskQueue).addFirst(request);
            request.enqueued = true;
        }

        @Deprecated
        private boolean isBatteryLevelCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            return bluetoothGattCharacteristic != null && BleManager.BATTERY_LEVEL_CHARACTERISTIC.equals(bluetoothGattCharacteristic.getUuid());
        }

        private boolean isCCCD(BluetoothGattDescriptor bluetoothGattDescriptor) {
            return bluetoothGattDescriptor != null && BleManager.CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID.equals(bluetoothGattDescriptor.getUuid());
        }

        private boolean isServiceChangedCCCD(BluetoothGattDescriptor bluetoothGattDescriptor) {
            return bluetoothGattDescriptor != null && BleManager.SERVICE_CHANGED_CHARACTERISTIC.equals(bluetoothGattDescriptor.getCharacteristic().getUuid());
        }

        private boolean isServiceChangedCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            return bluetoothGattCharacteristic != null && BleManager.SERVICE_CHANGED_CHARACTERISTIC.equals(bluetoothGattCharacteristic.getUuid());
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Failed to find 'out' block for switch in B:52:0x013e. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:107:0x0289 A[Catch: all -> 0x03c8, TRY_ENTER, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:108:0x0291 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:109:0x0299 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:110:0x02a1 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:111:0x02a9 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:112:0x02b3 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:113:0x02bd A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:114:0x02c7 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:115:0x02d1 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:116:0x02d9 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:117:0x02e1 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:122:0x02fd A[Catch: all -> 0x03c8, TRY_ENTER, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:126:0x0319 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:127:0x0322 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:131:0x0344 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:132:0x034d A[Catch: all -> 0x03c8, TRY_LEAVE, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:135:0x0359 A[Catch: all -> 0x03c8, TRY_ENTER, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:136:0x0360 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:137:0x0367 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:138:0x036e A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:139:0x011f A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:153:0x00f0 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:158:0x00ce  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x005f A[Catch: Exception -> 0x006c, all -> 0x03c8, TryCatch #0 {Exception -> 0x006c, blocks: (B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:159:0x0048), top: B:20:0x0023 }] */
        /* JADX WARN: Removed duplicated region for block: B:33:0x006f A[Catch: all -> 0x03c8, TRY_ENTER, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:45:0x00c9  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x0114 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:53:0x0141  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x0388 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:66:0x0143 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:69:0x0180 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:72:0x019e A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:73:0x01a6 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:81:0x01d3 A[Catch: all -> 0x03c8, TRY_ENTER, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:89:0x020c A[Catch: all -> 0x03c8, TRY_ENTER, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:97:0x0246 A[Catch: all -> 0x03c8, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x000f, B:13:0x001a, B:15:0x001c, B:21:0x0023, B:23:0x002b, B:25:0x0037, B:27:0x005f, B:29:0x0063, B:33:0x006f, B:35:0x0073, B:37:0x0087, B:39:0x009f, B:41:0x00a8, B:43:0x00b4, B:48:0x010e, B:50:0x0114, B:51:0x0130, B:52:0x013e, B:56:0x0388, B:59:0x03ac, B:60:0x039e, B:66:0x0143, B:68:0x014b, B:69:0x0180, B:71:0x0188, B:72:0x019e, B:73:0x01a6, B:75:0x01ac, B:76:0x01b4, B:78:0x01bc, B:81:0x01d3, B:83:0x01d9, B:84:0x01ed, B:86:0x01f5, B:89:0x020c, B:91:0x0212, B:92:0x0220, B:94:0x0224, B:96:0x0230, B:97:0x0246, B:99:0x0254, B:101:0x0258, B:102:0x0264, B:104:0x026c, B:107:0x0289, B:108:0x0291, B:109:0x0299, B:110:0x02a1, B:111:0x02a9, B:112:0x02b3, B:113:0x02bd, B:114:0x02c7, B:115:0x02d1, B:116:0x02d9, B:117:0x02e1, B:119:0x02e9, B:122:0x02fd, B:124:0x0304, B:125:0x0311, B:126:0x0319, B:127:0x0322, B:129:0x0329, B:130:0x033d, B:131:0x0344, B:132:0x034d, B:135:0x0359, B:136:0x0360, B:137:0x0367, B:138:0x036e, B:139:0x011f, B:141:0x0127, B:142:0x03bb, B:145:0x00d0, B:147:0x00db, B:149:0x00e3, B:153:0x00f0, B:156:0x0105, B:159:0x0048), top: B:2:0x0001, inners: #1 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public synchronized void nextRequest(boolean r10) {
            /*
                Method dump skipped, instructions count: 1030
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.ble.BleManager.BleManagerGattCallback.nextRequest(boolean):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void notifyDeviceDisconnected(BluetoothDevice bluetoothDevice) {
            boolean z = BleManager.this.mConnected;
            BleManager.this.mConnected = false;
            BleManager.this.mServicesDiscovered = false;
            BleManager.this.mServiceDiscoveryRequested = false;
            this.mInitInProgress = false;
            BleManager.this.mConnectionState = 0;
            if (z) {
                if (BleManager.this.mUserDisconnected) {
                    BleManager.this.log(4, "Disconnected");
                    BleManager.this.close();
                    BleManager.this.mCallbacks.onDeviceDisconnected(bluetoothDevice);
                    Request request = BleManager.this.mRequest;
                    if (request != null && request.type == Request.Type.DISCONNECT) {
                        request.notifySuccess(bluetoothDevice);
                    }
                } else {
                    BleManager.this.log(5, "Connection lost");
                    BleManager.this.mCallbacks.onLinkLossOccurred(bluetoothDevice);
                }
            } else {
                BleManager.this.log(5, "Connection attempt timed out");
                BleManager.this.close();
                BleManager.this.mCallbacks.onDeviceDisconnected(bluetoothDevice);
            }
            onDeviceDisconnected();
        }

        private void onError(BluetoothDevice bluetoothDevice, String str, int i) {
            BleManager.this.log(6, "Error (0x" + Integer.toHexString(i) + "): " + GattError.parse(i));
            BleManager.this.mCallbacks.onError(bluetoothDevice, str, i);
        }

        public /* synthetic */ void a(int i, BluetoothGatt bluetoothGatt) {
            if (i == BleManager.this.mConnectionCount && BleManager.this.mConnected && bluetoothGatt.getDevice().getBondState() != 11) {
                BleManager.this.mServiceDiscoveryRequested = true;
                BleManager.this.log(2, "Discovering services...");
                BleManager.this.log(3, "gatt.discoverServices()");
                bluetoothGatt.discoverServices();
            }
        }

        protected void cancelQueue() {
            this.mTaskQueue.clear();
        }

        @Deprecated
        protected Deque<Request> initGatt(BluetoothGatt bluetoothGatt) {
            return null;
        }

        protected void initialize() {
        }

        protected boolean isOptionalServiceSupported(BluetoothGatt bluetoothGatt) {
            return false;
        }

        protected abstract boolean isRequiredServiceSupported(BluetoothGatt bluetoothGatt);

        @Deprecated
        protected void onBatteryValueReceived(BluetoothGatt bluetoothGatt, int i) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // no.nordicsemi.android.ble.MainThreadBluetoothGattCallback
        /* renamed from: onCharacteristicChangedSafe */
        public final void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
            if (!isServiceChangedCharacteristic(bluetoothGattCharacteristic)) {
                BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(BleManager.CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID);
                boolean z = descriptor == null || descriptor.getValue() == null || descriptor.getValue().length != 2 || descriptor.getValue()[0] == 1;
                String parse = ParserUtils.parse(bArr);
                if (z) {
                    BleManager.this.log(4, "Notification received from " + bluetoothGattCharacteristic.getUuid() + ", value: " + parse);
                    onCharacteristicNotified(bluetoothGatt, bluetoothGattCharacteristic);
                } else {
                    BleManager.this.log(4, "Indication received from " + bluetoothGattCharacteristic.getUuid() + ", value: " + parse);
                    onCharacteristicIndicated(bluetoothGatt, bluetoothGattCharacteristic);
                }
                if (BleManager.this.mBatteryLevelNotificationCallback != null && isBatteryLevelCharacteristic(bluetoothGattCharacteristic)) {
                    BleManager.this.mBatteryLevelNotificationCallback.notifyValueChanged(bluetoothGatt.getDevice(), bArr);
                }
                ValueChangedCallback valueChangedCallback = (ValueChangedCallback) BleManager.this.mNotificationCallbacks.get(bluetoothGattCharacteristic);
                if (valueChangedCallback != null && valueChangedCallback.matches(bArr)) {
                    valueChangedCallback.notifyValueChanged(bluetoothGatt.getDevice(), bArr);
                }
                WaitForValueChangedRequest waitForValueChangedRequest = BleManager.this.mValueChangedRequest;
                if (waitForValueChangedRequest == null || waitForValueChangedRequest.characteristic != bluetoothGattCharacteristic || waitForValueChangedRequest.isTriggerPending() || !waitForValueChangedRequest.matches(bArr)) {
                    return;
                }
                waitForValueChangedRequest.notifyValueChanged(bluetoothGatt.getDevice(), bArr);
                if (waitForValueChangedRequest.hasMore()) {
                    return;
                }
                waitForValueChangedRequest.notifySuccess(bluetoothGatt.getDevice());
                BleManager.this.mValueChangedRequest = null;
                if (waitForValueChangedRequest.isTriggerCompleteOrNull()) {
                    nextRequest(true);
                    return;
                }
                return;
            }
            this.mOperationInProgress = true;
            cancelQueue();
            this.mInitQueue = null;
            BleManager.this.log(4, "Service Changed indication received");
            BleManager.this.log(2, "Discovering Services...");
            BleManager.this.log(3, "gatt.discoverServices()");
            bluetoothGatt.discoverServices();
        }

        @Deprecated
        protected void onCharacteristicIndicated(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        }

        @Deprecated
        protected void onCharacteristicNotified(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        }

        @Deprecated
        protected void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // no.nordicsemi.android.ble.MainThreadBluetoothGattCallback
        /* renamed from: onCharacteristicReadSafe */
        public final void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i) {
            if (i == 0) {
                BleManager.this.log(4, "Read Response received from " + bluetoothGattCharacteristic.getUuid() + ", value: " + ParserUtils.parse(bArr));
                onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic);
                if (BleManager.this.mRequest instanceof ReadRequest) {
                    ReadRequest readRequest = (ReadRequest) BleManager.this.mRequest;
                    boolean matches = readRequest.matches(bArr);
                    if (matches) {
                        readRequest.notifyValueChanged(bluetoothGatt.getDevice(), bArr);
                    }
                    if (matches && !readRequest.hasMore()) {
                        readRequest.notifySuccess(bluetoothGatt.getDevice());
                    } else {
                        enqueueFirst(readRequest);
                    }
                }
            } else if (i != 5 && i != 8 && i != 137) {
                Log.e(BleManager.TAG, "onCharacteristicRead error " + i);
                if (BleManager.this.mRequest instanceof ReadRequest) {
                    BleManager.this.mRequest.notifyFail(bluetoothGatt.getDevice(), i);
                }
                BleManager.this.mValueChangedRequest = null;
                onError(bluetoothGatt.getDevice(), ERROR_READ_CHARACTERISTIC, i);
            } else {
                BleManager.this.log(5, "Authentication required (" + i + ")");
                if (bluetoothGatt.getDevice().getBondState() != 10) {
                    Log.w(BleManager.TAG, ERROR_AUTH_ERROR_WHILE_BONDED);
                    BleManager.this.mCallbacks.onError(bluetoothGatt.getDevice(), ERROR_AUTH_ERROR_WHILE_BONDED, i);
                    return;
                }
                return;
            }
            nextRequest(true);
        }

        @Deprecated
        protected void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // no.nordicsemi.android.ble.MainThreadBluetoothGattCallback
        /* renamed from: onCharacteristicWriteSafe */
        public final void b(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i) {
            if (i == 0) {
                BleManager.this.log(4, "Data written to " + bluetoothGattCharacteristic.getUuid() + ", value: " + ParserUtils.parse(bArr));
                onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic);
                if (BleManager.this.mRequest instanceof WriteRequest) {
                    WriteRequest writeRequest = (WriteRequest) BleManager.this.mRequest;
                    if (!writeRequest.notifyPacketSent(bluetoothGatt.getDevice(), bArr) && (BleManager.this.mRequestQueue instanceof ReliableWriteRequest)) {
                        writeRequest.notifyFail(bluetoothGatt.getDevice(), -6);
                        BleManager.this.mRequestQueue.cancelQueue();
                    } else if (writeRequest.hasMore()) {
                        enqueueFirst(writeRequest);
                    } else {
                        writeRequest.notifySuccess(bluetoothGatt.getDevice());
                    }
                }
            } else if (i != 5 && i != 8 && i != 137) {
                Log.e(BleManager.TAG, "onCharacteristicWrite error " + i);
                if (BleManager.this.mRequest instanceof WriteRequest) {
                    BleManager.this.mRequest.notifyFail(bluetoothGatt.getDevice(), i);
                    if (BleManager.this.mRequestQueue instanceof ReliableWriteRequest) {
                        BleManager.this.mRequestQueue.cancelQueue();
                    }
                }
                BleManager.this.mValueChangedRequest = null;
                onError(bluetoothGatt.getDevice(), ERROR_WRITE_CHARACTERISTIC, i);
            } else {
                BleManager.this.log(5, "Authentication required (" + i + ")");
                if (bluetoothGatt.getDevice().getBondState() != 10) {
                    Log.w(BleManager.TAG, ERROR_AUTH_ERROR_WHILE_BONDED);
                    BleManager.this.mCallbacks.onError(bluetoothGatt.getDevice(), ERROR_AUTH_ERROR_WHILE_BONDED, i);
                    return;
                }
                return;
            }
            nextRequest(true);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // no.nordicsemi.android.ble.MainThreadBluetoothGattCallback
        /* renamed from: onConnectionStateChangeSafe */
        public final void a(final BluetoothGatt bluetoothGatt, int i, int i2) {
            BleManager.this.log(3, "[Callback] Connection state changed with status: " + i + " and new state: " + i2 + " (" + BleManager.this.stateToString(i2) + ")");
            if (i == 0 && i2 == 2) {
                if (BleManager.this.mBluetoothDevice == null) {
                    Log.e(BleManager.TAG, "Device received notification after disconnection.");
                    BleManager.this.log(3, "gatt.close()");
                    bluetoothGatt.close();
                    return;
                }
                BleManager.this.log(4, "Connected to " + bluetoothGatt.getDevice().getAddress());
                BleManager.this.mConnected = true;
                BleManager.this.mConnectionTime = 0L;
                BleManager.this.mConnectionState = 2;
                BleManager.this.mCallbacks.onDeviceConnected(bluetoothGatt.getDevice());
                if (BleManager.this.mServiceDiscoveryRequested) {
                    return;
                }
                int serviceDiscoveryDelay = BleManager.this.getServiceDiscoveryDelay(bluetoothGatt.getDevice().getBondState() == 12);
                if (serviceDiscoveryDelay > 0) {
                    BleManager.this.log(3, "wait(" + serviceDiscoveryDelay + ")");
                }
                final int access$1804 = BleManager.access$1804(BleManager.this);
                BleManager.this.mHandler.postDelayed(new Runnable() { // from class: no.nordicsemi.android.ble.f
                    @Override // java.lang.Runnable
                    public final void run() {
                        BleManager.BleManagerGattCallback.this.a(access$1804, bluetoothGatt);
                    }
                }, serviceDiscoveryDelay);
                return;
            }
            if (i2 == 0) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                boolean z = BleManager.this.mConnectionTime > 0;
                boolean z2 = z && elapsedRealtime > BleManager.this.mConnectionTime + BleManager.CONNECTION_TIMEOUT_THRESHOLD;
                if (i != 0) {
                    BleManager.this.log(5, "Error: (0x" + Integer.toHexString(i) + "): " + GattError.parseConnectionError(i));
                }
                if (i != 0 && z && !z2 && BleManager.this.mConnectRequest != null && BleManager.this.mConnectRequest.canRetry()) {
                    int retryDelay = BleManager.this.mConnectRequest.getRetryDelay();
                    if (retryDelay > 0) {
                        BleManager.this.log(3, "wait(" + retryDelay + ")");
                    }
                    BleManager.this.mHandler.postDelayed(new Runnable() { // from class: no.nordicsemi.android.ble.d
                        @Override // java.lang.Runnable
                        public final void run() {
                            BleManager.BleManagerGattCallback.this.a(bluetoothGatt);
                        }
                    }, retryDelay);
                    return;
                }
                this.mOperationInProgress = true;
                cancelQueue();
                this.mInitQueue = null;
                BleManager.this.mReady = false;
                boolean z3 = BleManager.this.mConnected;
                notifyDeviceDisconnected(bluetoothGatt.getDevice());
                if (BleManager.this.mRequest != null && BleManager.this.mRequest.type != Request.Type.DISCONNECT && BleManager.this.mRequest.type != Request.Type.REMOVE_BOND) {
                    BleManager.this.mRequest.notifyFail(bluetoothGatt.getDevice(), i == 0 ? -1 : i);
                    BleManager.this.mRequest = null;
                }
                if (BleManager.this.mValueChangedRequest != null) {
                    BleManager.this.mValueChangedRequest.notifyFail(BleManager.this.mBluetoothDevice, -1);
                    BleManager.this.mValueChangedRequest = null;
                }
                if (BleManager.this.mConnectRequest != null) {
                    BleManager.this.mConnectRequest.notifyFail(bluetoothGatt.getDevice(), BleManager.this.mServicesDiscovered ? -2 : i == 0 ? -1 : (i == 133 && z2) ? -5 : i);
                    BleManager.this.mConnectRequest = null;
                }
                this.mOperationInProgress = false;
                if (!z3 || !BleManager.this.mInitialConnection) {
                    BleManager.this.mInitialConnection = false;
                    nextRequest(false);
                } else {
                    BleManager.this.internalConnect(bluetoothGatt.getDevice(), null);
                }
                if (z3 || i == 0) {
                    return;
                }
            } else if (i != 0) {
                BleManager.this.log(6, "Error (0x" + Integer.toHexString(i) + "): " + GattError.parseConnectionError(i));
            }
            BleManager.this.mCallbacks.onError(bluetoothGatt.getDevice(), ERROR_CONNECTION_STATE_CHANGE, i);
        }

        @TargetApi(26)
        @Deprecated
        protected void onConnectionUpdated(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // no.nordicsemi.android.ble.MainThreadBluetoothGattCallback
        /* renamed from: onConnectionUpdatedSafe */
        public final void a(BluetoothGatt bluetoothGatt, int i, int i2, int i3, int i4) {
            if (i4 == 0) {
                BleManager bleManager = BleManager.this;
                StringBuilder sb = new StringBuilder();
                sb.append("Connection parameters updated (interval: ");
                double d2 = i;
                Double.isNaN(d2);
                sb.append(d2 * 1.25d);
                sb.append("ms, latency: ");
                sb.append(i2);
                sb.append(", timeout: ");
                sb.append(i3 * 10);
                sb.append("ms)");
                bleManager.log(4, sb.toString());
                onConnectionUpdated(bluetoothGatt, i, i2, i3);
                if (BleManager.this.mRequest instanceof ConnectionPriorityRequest) {
                    ((ConnectionPriorityRequest) BleManager.this.mRequest).notifyConnectionPriorityChanged(bluetoothGatt.getDevice(), i, i2, i3);
                    BleManager.this.mRequest.notifySuccess(bluetoothGatt.getDevice());
                }
            } else if (i4 == 59) {
                Log.e(BleManager.TAG, "onConnectionUpdated received status: Unacceptable connection interval, interval: " + i + ", latency: " + i2 + ", timeout: " + i3);
                BleManager bleManager2 = BleManager.this;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Connection parameters update failed with status: UNACCEPT CONN INTERVAL (0x3b) (interval: ");
                double d3 = (double) i;
                Double.isNaN(d3);
                sb2.append(d3 * 1.25d);
                sb2.append("ms, latency: ");
                sb2.append(i2);
                sb2.append(", timeout: ");
                sb2.append(i3 * 10);
                sb2.append("ms)");
                bleManager2.log(5, sb2.toString());
                if (BleManager.this.mRequest instanceof ConnectionPriorityRequest) {
                    BleManager.this.mRequest.notifyFail(bluetoothGatt.getDevice(), i4);
                    BleManager.this.mValueChangedRequest = null;
                }
            } else {
                Log.e(BleManager.TAG, "onConnectionUpdated received status: " + i4 + ", interval: " + i + ", latency: " + i2 + ", timeout: " + i3);
                BleManager bleManager3 = BleManager.this;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Connection parameters update failed with status ");
                sb3.append(i4);
                sb3.append(" (interval: ");
                double d4 = (double) i;
                Double.isNaN(d4);
                sb3.append(d4 * 1.25d);
                sb3.append("ms, latency: ");
                sb3.append(i2);
                sb3.append(", timeout: ");
                sb3.append(i3 * 10);
                sb3.append("ms)");
                bleManager3.log(5, sb3.toString());
                if (BleManager.this.mRequest instanceof ConnectionPriorityRequest) {
                    BleManager.this.mRequest.notifyFail(bluetoothGatt.getDevice(), i4);
                    BleManager.this.mValueChangedRequest = null;
                }
                BleManager.this.mCallbacks.onError(bluetoothGatt.getDevice(), ERROR_CONNECTION_PRIORITY_REQUEST, i4);
            }
            if (this.mConnectionPriorityOperationInProgress) {
                this.mConnectionPriorityOperationInProgress = false;
                nextRequest(true);
            }
        }

        @Deprecated
        protected void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // no.nordicsemi.android.ble.MainThreadBluetoothGattCallback
        /* renamed from: onDescriptorReadSafe */
        public void a(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr, int i) {
            if (i == 0) {
                BleManager.this.log(4, "Read Response received from descr. " + bluetoothGattDescriptor.getUuid() + ", value: " + ParserUtils.parse(bArr));
                onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor);
                if (BleManager.this.mRequest instanceof ReadRequest) {
                    ReadRequest readRequest = (ReadRequest) BleManager.this.mRequest;
                    readRequest.notifyValueChanged(bluetoothGatt.getDevice(), bArr);
                    if (readRequest.hasMore()) {
                        enqueueFirst(readRequest);
                    } else {
                        readRequest.notifySuccess(bluetoothGatt.getDevice());
                    }
                }
            } else if (i != 5 && i != 8 && i != 137) {
                Log.e(BleManager.TAG, "onDescriptorRead error " + i);
                if (BleManager.this.mRequest instanceof ReadRequest) {
                    BleManager.this.mRequest.notifyFail(bluetoothGatt.getDevice(), i);
                }
                BleManager.this.mValueChangedRequest = null;
                onError(bluetoothGatt.getDevice(), ERROR_READ_DESCRIPTOR, i);
            } else {
                BleManager.this.log(5, "Authentication required (" + i + ")");
                if (bluetoothGatt.getDevice().getBondState() != 10) {
                    Log.w(BleManager.TAG, ERROR_AUTH_ERROR_WHILE_BONDED);
                    BleManager.this.mCallbacks.onError(bluetoothGatt.getDevice(), ERROR_AUTH_ERROR_WHILE_BONDED, i);
                    return;
                }
                return;
            }
            nextRequest(true);
        }

        @Deprecated
        protected void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // no.nordicsemi.android.ble.MainThreadBluetoothGattCallback
        /* renamed from: onDescriptorWriteSafe */
        public final void b(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr, int i) {
            if (i == 0) {
                BleManager.this.log(4, "Data written to descr. " + bluetoothGattDescriptor.getUuid() + ", value: " + ParserUtils.parse(bArr));
                if (isServiceChangedCCCD(bluetoothGattDescriptor)) {
                    BleManager.this.log(4, "Service Changed notifications enabled");
                } else if (isCCCD(bluetoothGattDescriptor)) {
                    if (bArr != null && bArr.length == 2 && bArr[1] == 0) {
                        byte b2 = bArr[0];
                        if (b2 == 0) {
                            BleManager.this.mNotificationCallbacks.remove(bluetoothGattDescriptor.getCharacteristic());
                            BleManager.this.log(4, "Notifications and indications disabled");
                        } else if (b2 == 1) {
                            BleManager.this.log(4, "Notifications enabled");
                        } else if (b2 == 2) {
                            BleManager.this.log(4, "Indications enabled");
                        }
                        onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor);
                    }
                } else {
                    onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor);
                }
                if (BleManager.this.mRequest instanceof WriteRequest) {
                    WriteRequest writeRequest = (WriteRequest) BleManager.this.mRequest;
                    if (!writeRequest.notifyPacketSent(bluetoothGatt.getDevice(), bArr) && (BleManager.this.mRequestQueue instanceof ReliableWriteRequest)) {
                        writeRequest.notifyFail(bluetoothGatt.getDevice(), -6);
                        BleManager.this.mRequestQueue.cancelQueue();
                    } else if (writeRequest.hasMore()) {
                        enqueueFirst(writeRequest);
                    } else {
                        writeRequest.notifySuccess(bluetoothGatt.getDevice());
                    }
                }
            } else if (i != 5 && i != 8 && i != 137) {
                Log.e(BleManager.TAG, "onDescriptorWrite error " + i);
                if (BleManager.this.mRequest instanceof WriteRequest) {
                    BleManager.this.mRequest.notifyFail(bluetoothGatt.getDevice(), i);
                    if (BleManager.this.mRequestQueue instanceof ReliableWriteRequest) {
                        BleManager.this.mRequestQueue.cancelQueue();
                    }
                }
                BleManager.this.mValueChangedRequest = null;
                onError(bluetoothGatt.getDevice(), ERROR_WRITE_DESCRIPTOR, i);
            } else {
                BleManager.this.log(5, "Authentication required (" + i + ")");
                if (bluetoothGatt.getDevice().getBondState() != 10) {
                    Log.w(BleManager.TAG, ERROR_AUTH_ERROR_WHILE_BONDED);
                    BleManager.this.mCallbacks.onError(bluetoothGatt.getDevice(), ERROR_AUTH_ERROR_WHILE_BONDED, i);
                    return;
                }
                return;
            }
            nextRequest(true);
        }

        protected abstract void onDeviceDisconnected();

        protected void onDeviceReady() {
            BleManager bleManager = BleManager.this;
            bleManager.mCallbacks.onDeviceReady(bleManager.mBluetoothGatt.getDevice());
        }

        protected void onManagerReady() {
        }

        @Deprecated
        protected void onMtuChanged(BluetoothGatt bluetoothGatt, int i) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // no.nordicsemi.android.ble.MainThreadBluetoothGattCallback
        /* renamed from: onMtuChangedSafe */
        public final void b(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i2 == 0) {
                BleManager.this.log(4, "MTU changed to: " + i);
                BleManager.this.mMtu = i;
                onMtuChanged(bluetoothGatt, i);
                if (BleManager.this.mRequest instanceof MtuRequest) {
                    ((MtuRequest) BleManager.this.mRequest).notifyMtuChanged(bluetoothGatt.getDevice(), i);
                    BleManager.this.mRequest.notifySuccess(bluetoothGatt.getDevice());
                }
            } else {
                Log.e(BleManager.TAG, "onMtuChanged error: " + i2 + ", mtu: " + i);
                if (BleManager.this.mRequest instanceof MtuRequest) {
                    BleManager.this.mRequest.notifyFail(bluetoothGatt.getDevice(), i2);
                    BleManager.this.mValueChangedRequest = null;
                }
                onError(bluetoothGatt.getDevice(), ERROR_MTU_REQUEST, i2);
            }
            nextRequest(true);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // no.nordicsemi.android.ble.MainThreadBluetoothGattCallback
        /* renamed from: onPhyReadSafe */
        public final void a(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            if (i3 == 0) {
                BleManager.this.log(4, "PHY read (TX: " + BleManager.this.phyToString(i) + ", RX: " + BleManager.this.phyToString(i2) + ")");
                if (BleManager.this.mRequest instanceof PhyRequest) {
                    ((PhyRequest) BleManager.this.mRequest).notifyPhyChanged(bluetoothGatt.getDevice(), i, i2);
                    BleManager.this.mRequest.notifySuccess(bluetoothGatt.getDevice());
                }
            } else {
                BleManager.this.log(5, "PHY read failed with status " + i3);
                if (BleManager.this.mRequest instanceof PhyRequest) {
                    BleManager.this.mRequest.notifyFail(bluetoothGatt.getDevice(), i3);
                }
                BleManager.this.mValueChangedRequest = null;
                BleManager.this.mCallbacks.onError(bluetoothGatt.getDevice(), ERROR_READ_PHY, i3);
            }
            nextRequest(true);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // no.nordicsemi.android.ble.MainThreadBluetoothGattCallback
        /* renamed from: onPhyUpdateSafe */
        public final void b(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            if (i3 == 0) {
                BleManager.this.log(4, "PHY updated (TX: " + BleManager.this.phyToString(i) + ", RX: " + BleManager.this.phyToString(i2) + ")");
                if (BleManager.this.mRequest instanceof PhyRequest) {
                    ((PhyRequest) BleManager.this.mRequest).notifyPhyChanged(bluetoothGatt.getDevice(), i, i2);
                    BleManager.this.mRequest.notifySuccess(bluetoothGatt.getDevice());
                }
            } else {
                BleManager.this.log(5, "PHY updated failed with status " + i3);
                if (BleManager.this.mRequest instanceof PhyRequest) {
                    BleManager.this.mRequest.notifyFail(bluetoothGatt.getDevice(), i3);
                    BleManager.this.mValueChangedRequest = null;
                }
                BleManager.this.mCallbacks.onError(bluetoothGatt.getDevice(), ERROR_PHY_UPDATE, i3);
            }
            if (BleManager.this.mRequest instanceof PhyRequest) {
                nextRequest(true);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // no.nordicsemi.android.ble.MainThreadBluetoothGattCallback
        /* renamed from: onReadRemoteRssiSafe */
        public final void c(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i2 == 0) {
                BleManager.this.log(4, "Remote RSSI received: " + i + " dBm");
                if (BleManager.this.mRequest instanceof ReadRssiRequest) {
                    ((ReadRssiRequest) BleManager.this.mRequest).notifyRssiRead(bluetoothGatt.getDevice(), i);
                    BleManager.this.mRequest.notifySuccess(bluetoothGatt.getDevice());
                }
            } else {
                BleManager.this.log(5, "Reading remote RSSI failed with status " + i2);
                if (BleManager.this.mRequest instanceof ReadRssiRequest) {
                    BleManager.this.mRequest.notifyFail(bluetoothGatt.getDevice(), i2);
                }
                BleManager.this.mValueChangedRequest = null;
                BleManager.this.mCallbacks.onError(bluetoothGatt.getDevice(), ERROR_READ_RSSI, i2);
            }
            nextRequest(true);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // no.nordicsemi.android.ble.MainThreadBluetoothGattCallback
        /* renamed from: onReliableWriteCompletedSafe */
        public final void a(BluetoothGatt bluetoothGatt, int i) {
            boolean z = BleManager.this.mRequest.type == Request.Type.EXECUTE_RELIABLE_WRITE;
            BleManager.this.mReliableWriteInProgress = false;
            if (i != 0) {
                Log.e(BleManager.TAG, "onReliableWriteCompleted execute " + z + ", error " + i);
                BleManager.this.mRequest.notifyFail(bluetoothGatt.getDevice(), i);
                onError(bluetoothGatt.getDevice(), ERROR_RELIABLE_WRITE, i);
            } else if (z) {
                BleManager.this.log(4, "Reliable Write executed");
                BleManager.this.mRequest.notifySuccess(bluetoothGatt.getDevice());
            } else {
                BleManager.this.log(5, "Reliable Write aborted");
                BleManager.this.mRequest.notifySuccess(bluetoothGatt.getDevice());
                BleManager.this.mRequestQueue.notifyFail(bluetoothGatt.getDevice(), -4);
            }
            nextRequest(true);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // no.nordicsemi.android.ble.MainThreadBluetoothGattCallback
        /* renamed from: onServicesDiscoveredSafe */
        public final void b(BluetoothGatt bluetoothGatt, int i) {
            BleManager.this.mServiceDiscoveryRequested = false;
            if (i == 0) {
                BleManager.this.log(4, "Services discovered");
                BleManager.this.mServicesDiscovered = true;
                if (isRequiredServiceSupported(bluetoothGatt)) {
                    BleManager.this.log(2, "Primary service found");
                    boolean isOptionalServiceSupported = isOptionalServiceSupported(bluetoothGatt);
                    if (isOptionalServiceSupported) {
                        BleManager.this.log(2, "Secondary service found");
                    }
                    BleManager.this.mCallbacks.onServicesDiscovered(bluetoothGatt.getDevice(), isOptionalServiceSupported);
                    this.mInitInProgress = true;
                    this.mOperationInProgress = true;
                    this.mInitQueue = initGatt(bluetoothGatt);
                    boolean z = this.mInitQueue != null;
                    if (z) {
                        Iterator<Request> it = this.mInitQueue.iterator();
                        while (it.hasNext()) {
                            it.next().enqueued = true;
                        }
                    }
                    if (this.mInitQueue == null) {
                        this.mInitQueue = new LinkedList();
                    }
                    int i2 = Build.VERSION.SDK_INT;
                    if (i2 < 23 || i2 == 26 || i2 == 27 || i2 == 28) {
                        enqueueFirst(Request.newEnableServiceChangedIndicationsRequest().setManager(BleManager.this));
                    }
                    if (z) {
                        BleManager.this.readBatteryLevel();
                        if (BleManager.this.mCallbacks.shouldEnableBatteryLevelNotifications(bluetoothGatt.getDevice())) {
                            BleManager.this.enableBatteryLevelNotifications();
                        }
                    }
                    initialize();
                    this.mInitInProgress = false;
                    nextRequest(true);
                    return;
                }
                BleManager.this.log(5, "Device is not supported");
                BleManager.this.mCallbacks.onDeviceNotSupported(bluetoothGatt.getDevice());
                BleManager.this.internalDisconnect();
                return;
            }
            Log.e(BleManager.TAG, "onServicesDiscovered error " + i);
            onError(bluetoothGatt.getDevice(), ERROR_DISCOVERY_SERVICE, i);
            if (BleManager.this.mConnectRequest != null) {
                BleManager.this.mConnectRequest.notifyFail(bluetoothGatt.getDevice(), -4);
                BleManager.this.mConnectRequest = null;
            }
            BleManager.this.internalDisconnect();
        }

        public /* synthetic */ void a(BluetoothGatt bluetoothGatt) {
            BleManager.this.internalConnect(bluetoothGatt.getDevice(), BleManager.this.mConnectRequest);
        }

        public /* synthetic */ void a(ConnectionPriorityRequest connectionPriorityRequest, BluetoothDevice bluetoothDevice) {
            connectionPriorityRequest.notifySuccess(bluetoothDevice);
            nextRequest(true);
        }

        public /* synthetic */ void a(BluetoothDevice bluetoothDevice) {
            BleManager.this.log(4, "Cache refreshed");
            BleManager.this.mRequest.notifySuccess(bluetoothDevice);
            BleManager.this.mRequest = null;
            if (BleManager.this.mValueChangedRequest != null) {
                BleManager.this.mValueChangedRequest.notifyFail(bluetoothDevice, -3);
                BleManager.this.mValueChangedRequest = null;
            }
            cancelQueue();
            this.mInitQueue = null;
            if (BleManager.this.mConnected) {
                onDeviceDisconnected();
                BleManager.this.log(2, "Discovering Services...");
                BleManager.this.log(3, "gatt.discoverServices()");
                BleManager.this.mBluetoothGatt.discoverServices();
            }
        }

        public /* synthetic */ void a(SleepRequest sleepRequest, BluetoothDevice bluetoothDevice) {
            sleepRequest.notifySuccess(bluetoothDevice);
            nextRequest(true);
        }
    }

    public /* synthetic */ void a(BluetoothDevice bluetoothDevice) {
        log(4, "Battery Level notifications disabled");
    }

    @Deprecated
    public final ConnectRequest connect(BluetoothDevice bluetoothDevice, int i) {
        if (this.mCallbacks == null) {
            throw new NullPointerException("Set callbacks using setGattCallbacks(E callbacks) before connecting");
        }
        if (bluetoothDevice != null) {
            return Request.connect(bluetoothDevice).usePreferredPhy(i).useAutoConnect(shouldAutoConnect()).setManager((BleManager) this);
        }
        throw new NullPointerException("Bluetooth device not specified");
    }

    public /* synthetic */ void b(BluetoothDevice bluetoothDevice) {
        log(4, "Battery Level notifications enabled");
    }
}
