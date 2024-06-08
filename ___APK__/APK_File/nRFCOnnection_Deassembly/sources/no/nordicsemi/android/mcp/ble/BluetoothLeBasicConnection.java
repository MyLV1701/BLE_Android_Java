package no.nordicsemi.android.mcp.ble;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import io.runtime.mcumgr.McuMgrTransport;
import io.runtime.mcumgr.dfu.FirmwareUpgradeCallback;
import io.runtime.mcumgr.dfu.FirmwareUpgradeController;
import io.runtime.mcumgr.dfu.FirmwareUpgradeManager;
import io.runtime.mcumgr.exception.McuMgrErrorException;
import io.runtime.mcumgr.exception.McuMgrException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.log.ILogSession;
import no.nordicsemi.android.log.LocalLogSession;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.ble.dfu.DfuService;
import no.nordicsemi.android.mcp.ble.dfu.LoggingMcuMgrBleTransport;
import no.nordicsemi.android.mcp.ble.parser.CharacteristicParser;
import no.nordicsemi.android.mcp.ble.parser.DescriptorParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ClientCharacteristicConfigurationParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.GattUtils;
import no.nordicsemi.android.mcp.ble.parser.gatt.GeneralCharacteristicParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.GeneralDescriptorParser;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.ble.server.ServiceServerController;
import no.nordicsemi.android.mcp.connection.macro.domain.AbortReliableWrite;
import no.nordicsemi.android.mcp.connection.macro.domain.BeginReliableWrite;
import no.nordicsemi.android.mcp.connection.macro.domain.ExecuteReliableWrite;
import no.nordicsemi.android.mcp.connection.macro.domain.Macro;
import no.nordicsemi.android.mcp.connection.macro.domain.Read;
import no.nordicsemi.android.mcp.connection.macro.domain.ReadDescriptor;
import no.nordicsemi.android.mcp.connection.macro.domain.ReadPhy;
import no.nordicsemi.android.mcp.connection.macro.domain.ReadRSSI;
import no.nordicsemi.android.mcp.connection.macro.domain.RequestConnectionPriority;
import no.nordicsemi.android.mcp.connection.macro.domain.RequestMtu;
import no.nordicsemi.android.mcp.connection.macro.domain.SendIndication;
import no.nordicsemi.android.mcp.connection.macro.domain.SendNotification;
import no.nordicsemi.android.mcp.connection.macro.domain.SetCharacteristicValue;
import no.nordicsemi.android.mcp.connection.macro.domain.SetDescriptorValue;
import no.nordicsemi.android.mcp.connection.macro.domain.SetPreferredPhy;
import no.nordicsemi.android.mcp.connection.macro.domain.Sleep;
import no.nordicsemi.android.mcp.connection.macro.domain.SleepIf;
import no.nordicsemi.android.mcp.connection.macro.domain.SleepUntil;
import no.nordicsemi.android.mcp.connection.macro.domain.WaitForExecuteWrite;
import no.nordicsemi.android.mcp.connection.macro.domain.WaitForIndication;
import no.nordicsemi.android.mcp.connection.macro.domain.WaitForNotification;
import no.nordicsemi.android.mcp.connection.macro.domain.WaitForPhyUpdate;
import no.nordicsemi.android.mcp.connection.macro.domain.WaitForRead;
import no.nordicsemi.android.mcp.connection.macro.domain.WaitForReadDescriptor;
import no.nordicsemi.android.mcp.connection.macro.domain.WaitForWrite;
import no.nordicsemi.android.mcp.connection.macro.domain.WaitForWriteDescriptor;
import no.nordicsemi.android.mcp.connection.macro.domain.Write;
import no.nordicsemi.android.mcp.connection.macro.domain.WriteDescriptor;
import no.nordicsemi.android.mcp.database.DatabaseUtils;
import no.nordicsemi.android.mcp.database.init.ThirdPartyCharacteristics;
import no.nordicsemi.android.mcp.database.init.ThirdPartyServices;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.log.LocalLogContract;
import no.nordicsemi.android.mcp.settings.SettingsFragment;
import no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedAtLeastOnceException;
import no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException;
import no.nordicsemi.android.mcp.util.BondHelper;
import no.nordicsemi.android.mcp.util.PhyHelper;

/* loaded from: classes.dex */
public class BluetoothLeBasicConnection extends BluetoothGattCallback implements ServiceConstants, IBluetoothLeBasicConnection {
    private static final UUID CLIENT_CHARACTERISTIC_CONFIG;
    private static final UUID[] FIDO_UUIDS;
    private static final UUID[] HID_UUIDS;
    private static final int INCOMING = 0;
    private static final int INCOMING_INDICATION = 2;
    private static final int INCOMING_NOTIFICATION = 1;
    private static final int MAX_NOTIFICATIONS;
    private static final int OUTGOING = 4;
    private static final int PAIRING_VARIANT_CONSENT = 3;
    private static final int PAIRING_VARIANT_DISPLAY_PASSKEY = 4;
    private static final int PAIRING_VARIANT_DISPLAY_PIN = 5;
    private static final int PAIRING_VARIANT_OOB_CONSENT = 6;
    private static final int PAIRING_VARIANT_PASSKEY = 1;
    private static final int PAIRING_VARIANT_PASSKEY_CONFIRMATION = 2;
    private static final int PAIRING_VARIANT_PIN = 0;
    private static final String SETTINGS_KEEP_BOND = "settings_keep_bond";
    private static final String TAG = "BluetoothLeBasicConn";
    protected boolean mAutoConnect;
    protected BluetoothGattCharacteristic mAwaitingCharacteristic;
    protected BluetoothGattDescriptor mAwaitingDescriptor;
    protected BluetoothGattCharacteristic mAwaitingNotifCharacteristic;
    protected boolean mAwaitingNotifCharacteristicCondition;
    protected Runnable mAwaitingNotifCharacteristicTimeoutTask;
    protected byte[] mAwaitingNotifCharacteristicValue;
    protected Boolean mAwaitingPhyUpdate;
    protected Boolean mAwaitingWriteExecute;
    protected final BluetoothDevice mBluetoothDevice;
    protected final String mBluetoothDeviceAddress;
    protected String mBluetoothDeviceName;
    protected BluetoothGatt mBluetoothGatt;
    protected boolean mBroadcastReceiversRegistered;
    protected boolean mConnectionAttemptDone;
    protected boolean mConnectionPriorityRequested;
    protected int mConnectionState;
    protected final Context mContext;
    protected boolean mCurrentAutoConnect;
    private int mCurrentMTU;
    protected Macro mCurrentlyRecordingMacro;
    private byte[] mDataSent;
    protected final DatabaseHelper mDatabase;
    protected LinkedList<Float> mDfuAvgSpeedValues;
    protected boolean mDfuInProgress;
    protected LinkedList<Integer> mDfuProgressIndexes;
    protected LinkedList<Float> mDfuSpeedValues;
    private boolean mDfuSupported;
    private boolean mEddystoneSupported;
    protected FirmwareUpgradeManager mFirmwareUpgradeManager;
    private ScheduledFuture mFlashLogTaskHandler;
    protected final Handler mHandler;
    protected boolean mHasServices;
    protected Integer mInitialPreferredPhy;
    private int mLastRssi;
    protected ILogSession mLogSession;
    private boolean mMcuMgrSupported;
    private boolean mMicrobitSupported;
    protected boolean mOnConnectedCalled;
    protected boolean mOperationInProgress;
    protected int mOperationStatus;
    protected boolean mPhyRequested;
    private boolean mReliableWriteExecute;
    private boolean mReliableWriteInProgress;
    protected ServiceServerController mServerController;
    protected boolean mServicesDiscovered;
    private BluetoothGattCharacteristic mSmpCharacteristic;
    private boolean mThingyConfigServiceSupported;
    private final ScheduledExecutorService mExecutor = Executors.newSingleThreadScheduledExecutor();
    protected List<BluetoothGattService> mBluetoothGattServices = new ArrayList();
    protected int mDfuMaxAvgSpeed = 1;
    protected boolean mAsCentral = true;
    private final Object mLock = new Object();
    private final Object mGattLock = new Object();
    private final Object mLogLock = new Object();
    private int mRequestedMTU = 0;
    private int mCurrentTxPhy = 1;
    private int mCurrentRxPhy = 1;
    private final BroadcastReceiver mBondStateBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(BluetoothLeBasicConnection.this.mBluetoothDeviceAddress)) {
                int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1);
                int intExtra2 = intent.getIntExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", -1);
                BluetoothLeBasicConnection.this.saveLogBulk(0, "[Broadcast] Action received: android.bluetooth.device.action.BOND_STATE_CHANGED, bond state changed to: " + BluetoothLeBasicConnection.this.bondStateToString(intExtra) + " (" + intExtra + ")");
                BluetoothLeBasicConnection.this.broadcastAction(ServiceConstants.ACTION_GATT_BOND_STATE, intExtra);
                if (intExtra != 10) {
                    if (intExtra != 12) {
                        return;
                    }
                    BluetoothLeBasicConnection.this.saveLogAndFlash(5, "Device bonded");
                    BluetoothLeBasicConnection bluetoothLeBasicConnection = BluetoothLeBasicConnection.this;
                    if (bluetoothLeBasicConnection.mOnConnectedCalled) {
                        bluetoothLeBasicConnection.operationCompleted();
                        return;
                    } else {
                        bluetoothLeBasicConnection.onConnected();
                        return;
                    }
                }
                if (intExtra2 == 12) {
                    BluetoothLeBasicConnection.this.saveLogAndFlash(5, "Bond information deleted");
                }
                if (intExtra2 == 11) {
                    BluetoothLeBasicConnection.this.saveLogAndFlash(5, "Bonding failed");
                }
                BluetoothLeBasicConnection bluetoothLeBasicConnection2 = BluetoothLeBasicConnection.this;
                if (bluetoothLeBasicConnection2.mOnConnectedCalled) {
                    bluetoothLeBasicConnection2.operationCompleted();
                } else {
                    bluetoothLeBasicConnection2.onConnected();
                }
            }
        }
    };
    private final BroadcastReceiver mPairingRequestBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(BluetoothLeBasicConnection.this.mBluetoothDeviceAddress)) {
                int intExtra = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", 0);
                BluetoothLeBasicConnection.this.saveLogAndFlash(0, "[Broadcast] Action received: android.bluetooth.device.action.PAIRING_REQUEST, pairing variant: " + BluetoothLeBasicConnection.this.pairingVariantToString(intExtra) + " (" + intExtra + ")");
            }
        }
    };
    private final BroadcastReceiver mConnectionStateBroadcastReceiver = new AnonymousClass3();
    private final BroadcastReceiver mDfuBroadcastReceiver = new AnonymousClass4();
    private final List<ContentValues> valuesList = new ArrayList(8);
    private final List<ContentValues> valuesList2 = new ArrayList(8);
    private List<ContentValues> currentValuesList = this.valuesList;
    private final Runnable mFlashLogTask = new Runnable() { // from class: no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection.6
        @Override // java.lang.Runnable
        public void run() {
            BluetoothLeBasicConnection.this.mFlashLogTaskHandler = null;
            BluetoothLeBasicConnection bluetoothLeBasicConnection = BluetoothLeBasicConnection.this;
            ILogSession iLogSession = bluetoothLeBasicConnection.mLogSession;
            if (iLogSession == null) {
                return;
            }
            synchronized (bluetoothLeBasicConnection.mLogLock) {
                List list = BluetoothLeBasicConnection.this.currentValuesList;
                BluetoothLeBasicConnection.this.currentValuesList = BluetoothLeBasicConnection.this.currentValuesList == BluetoothLeBasicConnection.this.valuesList ? BluetoothLeBasicConnection.this.valuesList2 : BluetoothLeBasicConnection.this.valuesList;
                Logger.log(iLogSession, (List<ContentValues>) list);
                list.clear();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection$3, reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass3 extends BroadcastReceiver {
        AnonymousClass3() {
        }

        public /* synthetic */ void a(BluetoothDevice bluetoothDevice) {
            int i = BluetoothLeBasicConnection.this.mConnectionState;
            if (i == 0 || i == 5) {
                return;
            }
            boolean z = i == 3 || i == 4;
            BluetoothLeBasicConnection bluetoothLeBasicConnection = BluetoothLeBasicConnection.this;
            bluetoothLeBasicConnection.mConnectionState = 0;
            bluetoothLeBasicConnection.mServicesDiscovered = false;
            bluetoothLeBasicConnection.onDeviceDisconnected();
            BluetoothLeBasicConnection.this.saveLogAndFlash(5, "Disconnected");
            BluetoothLeBasicConnection.this.broadcastAction(ServiceConstants.ACTION_GATT_DISCONNECTED);
            if (bluetoothDevice.getBondState() == 10) {
                BluetoothLeBasicConnection.this.mDatabase.clearCCCD(bluetoothDevice);
            }
            if (z) {
                BluetoothLeBasicConnection.this.operationCompleted();
                return;
            }
            BluetoothLeBasicConnection bluetoothLeBasicConnection2 = BluetoothLeBasicConnection.this;
            if (bluetoothLeBasicConnection2.mServerController != null) {
                bluetoothLeBasicConnection2.operationFailed(-1);
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            final BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (bluetoothDevice.getAddress().equals(BluetoothLeBasicConnection.this.mBluetoothDeviceAddress)) {
                BluetoothLeBasicConnection bluetoothLeBasicConnection = BluetoothLeBasicConnection.this;
                if (bluetoothLeBasicConnection.mDfuInProgress) {
                    return;
                }
                bluetoothLeBasicConnection.mHandler.post(new Runnable() { // from class: no.nordicsemi.android.mcp.ble.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothLeBasicConnection.AnonymousClass3.this.a(bluetoothDevice);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection$4, reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass4 extends BroadcastReceiver {
        AnonymousClass4() {
        }

        public /* synthetic */ void a() {
            ((NotificationManager) BluetoothLeBasicConnection.this.mContext.getSystemService("notification")).cancel(DfuBaseService.NOTIFICATION_ID);
        }

        public /* synthetic */ void b() {
            ((NotificationManager) BluetoothLeBasicConnection.this.mContext.getSystemService("notification")).cancel(DfuBaseService.NOTIFICATION_ID);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            char c2;
            String action = intent.getAction();
            int hashCode = action.hashCode();
            if (hashCode == -2124230471) {
                if (action.equals(DfuBaseService.BROADCAST_LOG)) {
                    c2 = 2;
                }
                c2 = 65535;
            } else if (hashCode != -2021868104) {
                if (hashCode == -1282379203 && action.equals(DfuBaseService.BROADCAST_ERROR)) {
                    c2 = 1;
                }
                c2 = 65535;
            } else {
                if (action.equals(DfuBaseService.BROADCAST_PROGRESS)) {
                    c2 = 0;
                }
                c2 = 65535;
            }
            if (c2 != 0) {
                if (c2 == 1) {
                    a.l.a.a.a(BluetoothLeBasicConnection.this.mContext).a(this);
                    new Handler().postDelayed(new Runnable() { // from class: no.nordicsemi.android.mcp.ble.b
                        @Override // java.lang.Runnable
                        public final void run() {
                            BluetoothLeBasicConnection.AnonymousClass4.this.b();
                        }
                    }, 200L);
                    BluetoothLeBasicConnection.this.onDfuFinished();
                    BluetoothLeBasicConnection.this.operationFailed(intent.getIntExtra(DfuBaseService.EXTRA_DATA, -1));
                    return;
                }
                if (c2 != 2) {
                    return;
                }
                BluetoothLeBasicConnection.this.saveLogBulk(intent.getIntExtra(DfuBaseService.EXTRA_LOG_LEVEL, 0), intent.getStringExtra(DfuBaseService.EXTRA_LOG_MESSAGE));
                BluetoothLeBasicConnection.this.flashLog(false);
                return;
            }
            int intExtra = intent.getIntExtra(DfuBaseService.EXTRA_DATA, 0);
            float floatExtra = intent.getFloatExtra(DfuBaseService.EXTRA_SPEED_B_PER_MS, 0.0f);
            float floatExtra2 = intent.getFloatExtra(DfuBaseService.EXTRA_AVG_SPEED_B_PER_MS, 0.0f);
            if (intExtra == -7 || intExtra == -6) {
                a.l.a.a.a(BluetoothLeBasicConnection.this.mContext).a(this);
                new Handler().postDelayed(new Runnable() { // from class: no.nordicsemi.android.mcp.ble.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothLeBasicConnection.AnonymousClass4.this.a();
                    }
                }, 200L);
                BluetoothLeBasicConnection.this.onDfuFinished();
                BluetoothLeBasicConnection.this.operationCompleted();
                return;
            }
            if (intExtra == -5 || intExtra == -4 || intExtra == -2) {
                return;
            }
            if (intExtra != -1) {
                BluetoothLeBasicConnection.this.mDfuProgressIndexes.add(Integer.valueOf(intExtra));
                BluetoothLeBasicConnection.this.mDfuSpeedValues.add(Float.valueOf(floatExtra));
                BluetoothLeBasicConnection.this.mDfuAvgSpeedValues.add(Float.valueOf(floatExtra2));
                BluetoothLeBasicConnection bluetoothLeBasicConnection = BluetoothLeBasicConnection.this;
                if (bluetoothLeBasicConnection.mDfuMaxAvgSpeed < floatExtra2) {
                    bluetoothLeBasicConnection.mDfuMaxAvgSpeed = (int) (floatExtra2 + 1.0f);
                    return;
                }
                return;
            }
            LinkedList<Float> linkedList = BluetoothLeBasicConnection.this.mDfuSpeedValues;
            if (linkedList == null) {
                return;
            }
            linkedList.clear();
            BluetoothLeBasicConnection.this.mDfuAvgSpeedValues.clear();
            BluetoothLeBasicConnection.this.mDfuProgressIndexes.clear();
        }
    }

    /* renamed from: no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection$7, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$State = new int[FirmwareUpgradeManager.State.values().length];

        static {
            try {
                $SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$State[FirmwareUpgradeManager.State.UPLOAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$State[FirmwareUpgradeManager.State.VALIDATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$State[FirmwareUpgradeManager.State.TEST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$State[FirmwareUpgradeManager.State.CONFIRM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$State[FirmwareUpgradeManager.State.RESET.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        MAX_NOTIFICATIONS = i >= 21 ? 15 : i >= 19 ? 7 : 4;
        HID_UUIDS = new UUID[]{UUID.fromString("00002A4A-0000-1000-8000-00805F9B34FB"), UUID.fromString("00002A4B-0000-1000-8000-00805F9B34FB"), UUID.fromString("00002A4C-0000-1000-8000-00805F9B34FB"), UUID.fromString("00002A4D-0000-1000-8000-00805F9B34FB")};
        FIDO_UUIDS = new UUID[]{UUID.fromString("0000FFFD-0000-1000-8000-00805F9B34FB")};
        CLIENT_CHARACTERISTIC_CONFIG = new UUID(45088566677504L, DatabaseUtils.LSB);
    }

    public BluetoothLeBasicConnection(Context context, Handler handler, DatabaseHelper databaseHelper, BluetoothDevice bluetoothDevice, LogSession logSession) {
        this.mHandler = handler;
        this.mContext = context;
        this.mDatabase = databaseHelper;
        this.mBluetoothDevice = bluetoothDevice;
        this.mBluetoothDeviceName = bluetoothDevice.getName();
        this.mBluetoothDeviceAddress = bluetoothDevice.getAddress();
        if (logSession == null) {
            this.mLogSession = Logger.newSession(context, bluetoothDevice.getAddress(), bluetoothDevice.getName());
            if (this.mLogSession == null) {
                this.mLogSession = LocalLogSession.newSession(context, LocalLogContract.AUTHORITY_URI, bluetoothDevice.getAddress(), bluetoothDevice.getName());
            }
        } else {
            this.mLogSession = logSession;
        }
        this.mConnectionAttemptDone = false;
        context.registerReceiver(this.mBondStateBroadcastReceiver, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
        context.registerReceiver(this.mPairingRequestBroadcastReceiver, new IntentFilter("android.bluetooth.device.action.PAIRING_REQUEST"));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        context.registerReceiver(this.mConnectionStateBroadcastReceiver, intentFilter);
        this.mBroadcastReceiversRegistered = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String bondStateToString(int i) {
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

    private void bugWorkaround(List<BluetoothGattService> list) {
        if (Build.VERSION.SDK_INT >= 21) {
            return;
        }
        Iterator<BluetoothGattService> it = list.iterator();
        while (it.hasNext()) {
            List<BluetoothGattCharacteristic> characteristics = it.next().getCharacteristics();
            for (int i = 0; i < characteristics.size() - 1; i++) {
                List<BluetoothGattDescriptor> descriptors = characteristics.get(i).getDescriptors();
                ArrayList arrayList = new ArrayList();
                int i2 = 0;
                while (i2 < descriptors.size()) {
                    BluetoothGattDescriptor bluetoothGattDescriptor = descriptors.get(i2);
                    if (!arrayList.contains(bluetoothGattDescriptor.getUuid())) {
                        arrayList.add(bluetoothGattDescriptor.getUuid());
                        i2++;
                    } else {
                        moveDescriptor(bluetoothGattDescriptor, characteristics.get(i + 1));
                    }
                }
            }
        }
    }

    private void checkUUID(UUID uuid) {
        if (Build.VERSION.SDK_INT >= 24) {
            if (isIn(uuid, HID_UUIDS) || isIn(uuid, FIDO_UUIDS)) {
                throw new SecurityException("BLUETOOTH_PRIVILEGED permission required");
            }
        }
    }

    private void cleanAwaiting() {
        this.mAwaitingDescriptor = null;
        this.mAwaitingCharacteristic = null;
        this.mAwaitingNotifCharacteristic = null;
    }

    private void closeGatt() {
        synchronized (this.mGattLock) {
            this.mAwaitingDescriptor = null;
            this.mAwaitingCharacteristic = null;
            this.mAwaitingNotifCharacteristic = null;
            if (this.mConnectionState != 5 && this.mBluetoothGatt != null) {
                this.mConnectionState = 5;
                saveLogAndFlash(0, "gatt.close()");
                this.mBluetoothGatt.close();
                this.mBluetoothGatt = null;
            }
            try {
                synchronized (this.mLock) {
                    saveLogAndFlash(0, "wait(200)");
                    this.mLock.wait(200L);
                }
            } catch (InterruptedException e2) {
                loge("Wait interrupted", e2);
            }
        }
    }

    private BluetoothGatt connectGatt(BluetoothDevice bluetoothDevice, boolean z) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            Integer num = this.mInitialPreferredPhy;
            int intValue = num != null ? num.intValue() : 1;
            if (!z) {
                saveLogBulk(0, "gatt = device.connectGatt(autoConnect = " + this.mAutoConnect + ", TRANSPORT_LE, preferred PHY = " + phyMaskToString(intValue) + ")");
                Context context = this.mContext;
                boolean z2 = this.mAutoConnect;
                this.mCurrentAutoConnect = z2;
                return bluetoothDevice.connectGatt(context, z2, this, 2, intValue);
            }
            return connectGattOpportunisticApi26(bluetoothDevice, intValue);
        }
        if (i >= 23) {
            saveLogBulk(0, "gatt = device.connectGatt(autoConnect = " + this.mAutoConnect + ", TRANSPORT_LE)");
            Context context2 = this.mContext;
            boolean z3 = this.mAutoConnect;
            this.mCurrentAutoConnect = z3;
            return bluetoothDevice.connectGatt(context2, z3, this, 2);
        }
        if (i >= 21) {
            return connectGattApi21(bluetoothDevice);
        }
        saveLogBulk(0, "gatt = device.connectGatt(autoConnect = " + this.mAutoConnect + ")");
        Context context3 = this.mContext;
        boolean z4 = this.mAutoConnect;
        this.mCurrentAutoConnect = z4;
        return bluetoothDevice.connectGatt(context3, z4, this);
    }

    private BluetoothGatt connectGattApi21(BluetoothDevice bluetoothDevice) {
        try {
            Method method = bluetoothDevice.getClass().getMethod("connectGatt", Context.class, Boolean.TYPE, BluetoothGattCallback.class, Integer.TYPE);
            saveLogBulk(0, "gatt = device.connectGatt(autoConnect = " + this.mAutoConnect + ", TRANSPORT_LE) (hidden)");
            boolean z = this.mAutoConnect;
            this.mCurrentAutoConnect = z;
            return (BluetoothGatt) method.invoke(bluetoothDevice, this.mContext, Boolean.valueOf(z), this, 2);
        } catch (Exception e2) {
            saveLogBulk(15, "Connection failed: " + e2.getLocalizedMessage());
            Log.w(TAG, "An exception occurred while connecting gatt", e2);
            saveLogBulk(0, "gatt = device.connectGatt(autoConnect = " + this.mAutoConnect + ")");
            Context context = this.mContext;
            boolean z2 = this.mAutoConnect;
            this.mCurrentAutoConnect = z2;
            return bluetoothDevice.connectGatt(context, z2, this);
        }
    }

    private BluetoothGatt connectGattOpportunisticApi26(BluetoothDevice bluetoothDevice, int i) {
        try {
            Method method = bluetoothDevice.getClass().getMethod("connectGatt", Context.class, Boolean.TYPE, BluetoothGattCallback.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Handler.class);
            saveLogBulk(0, "gatt = device.connectGatt(autoConnect = " + this.mAutoConnect + ", TRANSPORT_LE, opportunistic = true, preferred PHY = " + phyMaskToString(i) + ") (hidden)");
            boolean z = this.mAutoConnect;
            this.mCurrentAutoConnect = z;
            return (BluetoothGatt) method.invoke(bluetoothDevice, this.mContext, Boolean.valueOf(z), this, 2, true, Integer.valueOf(i), null);
        } catch (Exception unused) {
            saveLogBulk(0, "gatt = device.connectGatt(autoConnect = " + this.mAutoConnect + ", TRANSPORT_LE, preferred PHY = " + phyMaskToString(i) + ")");
            Context context = this.mContext;
            boolean z2 = this.mAutoConnect;
            this.mCurrentAutoConnect = z2;
            return bluetoothDevice.connectGatt(context, z2, this, 2, i);
        }
    }

    private void discoverServicesNoRefresh() {
        long j = getBondState() == 12 ? 1600L : 0L;
        if (j > 0) {
            saveLogBulk(0, "wait(1600ms)");
        }
        this.mHandler.postDelayed(new Runnable() { // from class: no.nordicsemi.android.mcp.ble.d
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothLeBasicConnection.this.a();
            }
        }, j);
    }

    private boolean isIn(UUID uuid, UUID[] uuidArr) {
        for (UUID uuid2 : uuidArr) {
            if (uuid2.equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    private byte[] loadContent(InputStream inputStream) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        int available = bufferedInputStream.available();
        byte[] bArr = new byte[available];
        int i = 0;
        for (int i2 = 0; i < available && i2 < 5; i2++) {
            try {
                i += bufferedInputStream.read(bArr, i, available - i);
            } finally {
                bufferedInputStream.close();
            }
        }
        return bArr;
    }

    private void moveDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BluetoothGattCharacteristic characteristic = bluetoothGattDescriptor.getCharacteristic();
        if (characteristic.getUuid().equals(bluetoothGattCharacteristic.getUuid())) {
            characteristic.getDescriptors().remove(bluetoothGattDescriptor);
            bluetoothGattCharacteristic.addDescriptor(bluetoothGattDescriptor);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String pairingVariantToString(int i) {
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
                return "UNKNOWN (" + i + ")";
        }
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

    private boolean refreshDeviceCache(BluetoothGatt bluetoothGatt, boolean z) {
        if (bluetoothGatt != null) {
            String string = PreferenceManager.getDefaultSharedPreferences(this.mContext).getString(SettingsFragment.SETTINGS_REFRESH_AFTER_DISCONNECT, "always");
            if (!z && !"always".equals(string) && (!SettingsFragment.REFRESH_IF_NOT_BONDED.equals(string) || bluetoothGatt.getDevice().getBondState() != 10)) {
                return true;
            }
            saveLogBulk(0, "gatt.refresh() (hidden)");
            try {
                return ((Boolean) bluetoothGatt.getClass().getMethod("refresh", new Class[0]).invoke(bluetoothGatt, new Object[0])).booleanValue();
            } catch (Exception e2) {
                loge("An exception occurred while refreshing device", e2);
                saveLogBulk(15, "gatt.refresh() method not found");
                return false;
            }
        }
        throw new DeviceNotConnectedAtLeastOnceException();
    }

    private String stateToString(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "DISCONNECTED" : "DISCONNECTING" : "CONNECTED" : "CONNECTING";
    }

    private boolean valueEmpty(byte[] bArr) {
        if (bArr != null && bArr.length != 0) {
            for (byte b2 : bArr) {
                if (b2 != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public /* synthetic */ void a() {
        try {
            if (this.mConnectionState == 2) {
                broadcastAction(ServiceConstants.ACTION_GATT_DISCOVERING_SERVICES);
                startOperation();
                saveLogBulk(1, "Discovering services...");
                saveLogBulk(0, "gatt.discoverServices()");
                this.mBluetoothGatt.discoverServices();
                flashLog(true);
            }
        } catch (Exception unused) {
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void abortOperation() {
        this.mAwaitingNotifCharacteristic = null;
        Runnable runnable = this.mAwaitingNotifCharacteristicTimeoutTask;
        if (runnable != null) {
            this.mHandler.removeCallbacks(runnable);
            this.mAwaitingNotifCharacteristicTimeoutTask = null;
            this.mAwaitingNotifCharacteristicValue = null;
        }
        this.mAwaitingCharacteristic = null;
        this.mAwaitingDescriptor = null;
        operationFailed(-2);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean abortReliableWrite() {
        if (this.mOperationInProgress) {
            return false;
        }
        if (this.mBluetoothGatt != null) {
            Macro macro = this.mCurrentlyRecordingMacro;
            if (macro != null) {
                macro.addOperation(new AbortReliableWrite(1));
            }
            saveLogBulk(1, "Aborting reliable write...");
            startOperation();
            this.mReliableWriteExecute = false;
            if (Build.VERSION.SDK_INT >= 19) {
                saveLogBulk(0, "gatt.abortReliableWrite()");
                this.mBluetoothGatt.abortReliableWrite();
            } else {
                saveLogBulk(0, "gatt.abortReliableWrite(device)");
                this.mBluetoothGatt.abortReliableWrite(this.mBluetoothDevice);
            }
            flashLog(true);
            return true;
        }
        throw new DeviceNotConnectedException();
    }

    public /* synthetic */ void b() {
        this.mAwaitingNotifCharacteristicTimeoutTask = null;
        this.mAwaitingNotifCharacteristicValue = null;
        this.mAwaitingNotifCharacteristic = null;
        saveLogAndFlash(15, "Timeout occurred");
        operationCompleted();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean beginReliableWrite() {
        if (this.mOperationInProgress) {
            return false;
        }
        if (this.mBluetoothGatt != null) {
            Macro macro = this.mCurrentlyRecordingMacro;
            if (macro != null) {
                macro.addOperation(new BeginReliableWrite(1));
            }
            saveLogBulk(1, "Beginning reliable write...");
            saveLogBulk(0, "gatt.beginReliableWrite()");
            flashLog(true);
            this.mReliableWriteInProgress = true;
            return this.mBluetoothGatt.beginReliableWrite();
        }
        throw new DeviceNotConnectedException();
    }

    protected void broadcastAction(String str) {
    }

    protected void broadcastAction(String str, int i) {
    }

    protected void broadcastConnectionError(int i) {
        saveLogBulk(20, "Error " + i + " (0x" + Integer.toHexString(i) + "): " + e.a.a.a.a.b(i));
    }

    protected void broadcastDfuRemoteError(int i) {
        saveLogBulk(20, "Remote device returned error (0x" + Integer.toHexString(i) + "): " + e.a.a.a.a.b(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void broadcastError(Exception exc) {
        saveLogBulk(20, "Exception occurred (" + exc.getLocalizedMessage() + ")");
    }

    public boolean characteristicValueEquals(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        return bluetoothGattCharacteristic != null && ((valueEmpty(bluetoothGattCharacteristic.getValue()) && valueEmpty(bArr)) || Arrays.equals(bluetoothGattCharacteristic.getValue(), bArr));
    }

    public void close() {
        if (this.mBluetoothGatt == null) {
            if (this.mBroadcastReceiversRegistered) {
                try {
                    this.mContext.unregisterReceiver(this.mConnectionStateBroadcastReceiver);
                    this.mContext.unregisterReceiver(this.mBondStateBroadcastReceiver);
                    this.mContext.unregisterReceiver(this.mPairingRequestBroadcastReceiver);
                } catch (Exception unused) {
                }
                this.mBroadcastReceiversRegistered = false;
            }
            operationCompleted();
            return;
        }
        closeGatt();
        this.mBluetoothGattServices = new ArrayList();
        this.mHasServices = false;
        if (!this.mDfuInProgress && this.mBroadcastReceiversRegistered) {
            try {
                this.mContext.unregisterReceiver(this.mConnectionStateBroadcastReceiver);
                this.mContext.unregisterReceiver(this.mBondStateBroadcastReceiver);
                this.mContext.unregisterReceiver(this.mPairingRequestBroadcastReceiver);
            } catch (Exception unused2) {
            }
            this.mBroadcastReceiversRegistered = false;
        }
        operationCompleted();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void connect() {
        connect(true);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean createBond() {
        boolean createBondApi18;
        if (this.mBluetoothDevice.getBondState() == 12) {
            saveLogBulk(1, "Create bond request on already bonded device...");
            saveLogAndFlash(5, "Device bonded");
            return true;
        }
        saveLogBulk(1, "Starting pairing...");
        if (Build.VERSION.SDK_INT >= 19) {
            saveLogBulk(0, "device.createBond()");
            createBondApi18 = BondHelper.createBond(this.mBluetoothDevice);
        } else {
            saveLogBulk(0, "device.createBond() (hidden)");
            createBondApi18 = BondHelper.createBondApi18(this.mBluetoothDevice);
        }
        if (!createBondApi18) {
            saveLogBulk(15, "Creating bond failed");
        } else {
            startOperation();
        }
        flashLog(true);
        return createBondApi18;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void deleteLogSession() {
        if (this.mLogSession != null) {
            try {
                this.mContext.getContentResolver().delete(this.mLogSession.getSessionUri(), null, null);
            } catch (Exception e2) {
                loge("Could not delete log session", e2);
            }
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void disconnect() {
        disconnect(false);
    }

    public void disconnectAndClose(boolean z) {
        if (!z) {
            deleteLogSession();
        }
        this.mCurrentlyRecordingMacro = null;
        if (!this.mDfuInProgress) {
            a.l.a.a.a(this.mContext).a(this.mDfuBroadcastReceiver);
        }
        if (this.mDfuInProgress && this.mBroadcastReceiversRegistered) {
            this.mContext.unregisterReceiver(this.mConnectionStateBroadcastReceiver);
            this.mContext.unregisterReceiver(this.mBondStateBroadcastReceiver);
            this.mContext.unregisterReceiver(this.mPairingRequestBroadcastReceiver);
            this.mBroadcastReceiversRegistered = false;
        }
        int i = this.mConnectionState;
        if (i == 2) {
            disconnect(true);
        } else if (i == 1) {
            disconnect(false);
            close();
        } else {
            close();
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void discoverServices() {
        if (this.mBluetoothGatt != null) {
            broadcastAction(ServiceConstants.ACTION_GATT_DISCOVERING_SERVICES);
            startOperation();
            if (!this.mBluetoothGatt.getServices().isEmpty()) {
                refreshCache();
            }
            saveLogBulk(1, "Discovering services...");
            saveLogBulk(0, "gatt.discoverServices()");
            this.mBluetoothGatt.discoverServices();
            flashLog(true);
            return;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean executeReliableWrite() {
        if (this.mOperationInProgress) {
            return false;
        }
        if (this.mBluetoothGatt != null) {
            Macro macro = this.mCurrentlyRecordingMacro;
            if (macro != null) {
                macro.addOperation(new ExecuteReliableWrite(1));
            }
            saveLogBulk(1, "Executing reliable write...");
            startOperation();
            this.mReliableWriteExecute = true;
            saveLogBulk(0, "gatt.executeReliableWrite()");
            flashLog(true);
            return this.mBluetoothGatt.executeReliableWrite();
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void flashLog(boolean z) {
        if (this.mLogSession != null) {
            if (z || this.mFlashLogTaskHandler == null) {
                if (z) {
                    boolean z2 = true;
                    ScheduledFuture scheduledFuture = this.mFlashLogTaskHandler;
                    if (scheduledFuture != null) {
                        z2 = scheduledFuture.cancel(false);
                        this.mFlashLogTaskHandler = null;
                    }
                    if (z2) {
                        this.mExecutor.execute(this.mFlashLogTask);
                        return;
                    }
                    return;
                }
                this.mFlashLogTaskHandler = this.mExecutor.schedule(this.mFlashLogTask, 1000L, TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public int getBondState() {
        return this.mBluetoothDevice.getBondState();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public int getConnectionState() {
        return this.mConnectionState;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public PhyHelper.Phy getCurrentPhy() {
        return new PhyHelper.Phy(this.mCurrentTxPhy, this.mCurrentRxPhy);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public String getDeviceName() {
        return this.mBluetoothDeviceName;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public int getLastRemoteRssi() {
        return this.mLastRssi;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public Uri getLogSessionUri() {
        return this.mLogSession.getSessionUri();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public int getMtu() {
        return this.mCurrentMTU;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public Integer getPreferredPhy() {
        return this.mInitialPreferredPhy;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public List<BluetoothGattService> getServerGattServices(boolean z) {
        ServiceServerController serviceServerController = this.mServerController;
        if (serviceServerController == null) {
            return null;
        }
        return serviceServerController.getServices(this.mBluetoothDevice, z);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public List<BluetoothGattService> getSupportedGattServices() {
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null) {
            return this.mBluetoothGattServices;
        }
        List<BluetoothGattService> services = bluetoothGatt.getServices();
        this.mBluetoothGattServices = services;
        return services;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public Boolean getWriteExecuteResult() {
        return this.mAwaitingWriteExecute;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean hasAutoConnect() {
        return this.mAutoConnect;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean hasDfuService() {
        return this.mDfuSupported;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean hasEddystoneConfigService() {
        return this.mEddystoneSupported;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean hasIndicationsEnabled(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BluetoothGattDescriptor descriptor;
        return (this.mBluetoothGatt == null || bluetoothGattCharacteristic == null || (descriptor = bluetoothGattCharacteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG)) == null || ClientCharacteristicConfigurationParser.getValue(descriptor) != 2) ? false : true;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean hasNotificationsEnabled(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BluetoothGattDescriptor descriptor;
        return (this.mBluetoothGatt == null || bluetoothGattCharacteristic == null || (descriptor = bluetoothGattCharacteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG)) == null || ClientCharacteristicConfigurationParser.getValue(descriptor) != 1) ? false : true;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean hasServicesDiscovered() {
        return this.mHasServices;
    }

    protected boolean isAutoMtuRequestEnabled() {
        return false;
    }

    protected boolean isAutoStartServiceDiscovery() {
        return false;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean isDfuInProgress() {
        return this.mDfuInProgress;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean isMcuMgr() {
        return this.mMcuMgrSupported;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean isMicrobit() {
        return this.mMicrobitSupported;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean isOperationInProgress() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mOperationInProgress;
        }
        return z;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean isReliableWriteInProgress() {
        return this.mReliableWriteInProgress;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean isThingy() {
        return this.mThingyConfigServiceSupported;
    }

    protected void loge(String str) {
        Log.e(TAG, str);
    }

    protected void logw(String str) {
        Log.w(TAG, str);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void onBluetoothOff() {
        int i = this.mConnectionState;
        if (i != 0 && i != 5) {
            saveLogBulk(5, "Disconnected");
            broadcastAction(ServiceConstants.ACTION_GATT_DISCONNECTED);
            this.mConnectionState = 0;
            this.mServicesDiscovered = false;
            onDeviceDisconnected();
            BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
            if (bluetoothGatt != null && bluetoothGatt.getDevice().getBondState() == 10) {
                this.mDatabase.clearCCCD(this.mBluetoothGatt.getDevice());
            }
        }
        if (this.mBluetoothGatt != null) {
            closeGatt();
            cleanAwaiting();
            operationFailed(-1);
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] bArr;
        byte b2 = (bluetoothGattCharacteristic.getProperties() & 32) > 0 ? (byte) 2 : (byte) 1;
        Macro macro = this.mCurrentlyRecordingMacro;
        if (macro != null) {
            DatabaseHelper databaseHelper = this.mDatabase;
            macro.addOperation(databaseHelper, b2 == 1 ? new WaitForNotification(databaseHelper, this, bluetoothGattCharacteristic) : new WaitForIndication(databaseHelper, this, bluetoothGattCharacteristic));
        }
        if (this.mAwaitingNotifCharacteristic == bluetoothGattCharacteristic && ((bArr = this.mAwaitingNotifCharacteristicValue) == null || characteristicValueEquals(bluetoothGattCharacteristic, bArr) == this.mAwaitingNotifCharacteristicCondition)) {
            Runnable runnable = this.mAwaitingNotifCharacteristicTimeoutTask;
            if (runnable != null) {
                this.mHandler.removeCallbacks(runnable);
                this.mAwaitingNotifCharacteristicTimeoutTask = null;
            }
            this.mAwaitingNotifCharacteristicValue = null;
            this.mAwaitingNotifCharacteristic = null;
            operationCompleted();
        }
        if (!isOperationInProgress()) {
            BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG);
            byte[] value = descriptor != null ? descriptor.getValue() : null;
            if (descriptor != null && (value == null || value.length != 2 || value[0] != b2)) {
                descriptor.setValue(b2 == 1 ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                this.mDatabase.saveCCCD(bluetoothGatt.getDevice(), descriptor);
            }
        }
        broadcastAction(ServiceConstants.ACTION_DATA_AVAILABLE);
        saveLogAndFlash(bluetoothGattCharacteristic, b2);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        if (i == 0) {
            Macro macro = this.mCurrentlyRecordingMacro;
            if (macro != null) {
                DatabaseHelper databaseHelper = this.mDatabase;
                macro.addOperation(databaseHelper, new Read(databaseHelper, this, bluetoothGattCharacteristic));
            }
            saveLogAndFlash(bluetoothGattCharacteristic, 0);
            operationCompleted();
            broadcastAction(ServiceConstants.ACTION_DATA_AVAILABLE);
            return;
        }
        bluetoothGattCharacteristic.setValue((byte[]) null);
        loge("onCharacteristicRead received: " + i);
        operationFailed(i);
        broadcastError(i);
        flashLog(true);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void onCharacteristicValueSet(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        Macro macro = this.mCurrentlyRecordingMacro;
        if (macro != null) {
            DatabaseHelper databaseHelper = this.mDatabase;
            macro.addOperation(databaseHelper, new SetCharacteristicValue(databaseHelper, this, bluetoothGattCharacteristic));
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        if (i == 0) {
            if (this.mReliableWriteInProgress && !Arrays.equals(this.mDataSent, bluetoothGattCharacteristic.getValue())) {
                operationFailed(-4);
            } else {
                operationCompleted();
            }
            saveLogAndFlash(bluetoothGattCharacteristic, 4);
            broadcastAction(ServiceConstants.ACTION_DATA_SEND);
        } else {
            bluetoothGattCharacteristic.setValue((byte[]) null);
            loge("onCharacteristicWrite received: " + i);
            operationFailed(i);
            broadcastError(i);
            flashLog(true);
        }
        this.mDataSent = null;
    }

    protected void onConnected() {
        this.mOnConnectedCalled = true;
        if (Build.VERSION.SDK_INT >= 21 && isAutoMtuRequestEnabled()) {
            requestMtu(517, true);
        } else if (isAutoStartServiceDiscovery()) {
            discoverServicesNoRefresh();
        } else {
            operationCompleted();
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
        int i3;
        saveLogBulk(0, "[Callback] Connection state changed with status: " + i + " and new state: " + stateToString(i2) + " (" + i2 + ")");
        if (i != 0 && i != 19) {
            loge("Connection state changing error: " + i);
            broadcastConnectionError(i);
            if (i2 == 0 && (i3 = this.mConnectionState) != 0 && i3 != 5) {
                this.mConnectionState = 0;
                this.mServicesDiscovered = false;
                onDeviceDisconnected();
                saveLogBulk(5, "Disconnected");
                broadcastAction(ServiceConstants.ACTION_GATT_DISCONNECTED);
                if (bluetoothGatt.getDevice().getBondState() == 10) {
                    this.mDatabase.clearCCCD(bluetoothGatt.getDevice());
                }
                ServiceServerController serviceServerController = this.mServerController;
                if (serviceServerController != null) {
                    serviceServerController.onConnectionLost(this.mBluetoothDevice);
                }
            }
            operationFailed(i);
            flashLog(true);
            return;
        }
        if (i2 == 2 && this.mConnectionState != 2) {
            this.mConnectionState = 2;
            this.mServicesDiscovered = false;
            this.mOnConnectedCalled = false;
            saveLogBulk(5, "Connected to " + this.mBluetoothDeviceAddress);
            broadcastAction(ServiceConstants.ACTION_GATT_CONNECTED);
            if (getBondState() != 11) {
                onConnected();
            }
        } else if (i2 == 0) {
            if (i == 19) {
                saveLogBulk(15, "Connection terminated by peer (status 19)");
            }
            if (this.mConnectionState != 0) {
                saveLogBulk(5, "Disconnected");
                broadcastAction(ServiceConstants.ACTION_GATT_DISCONNECTED);
            }
            boolean z = this.mConnectionState == 4;
            this.mConnectionState = 0;
            this.mServicesDiscovered = false;
            onDeviceDisconnected();
            if (bluetoothGatt.getDevice().getBondState() == 10) {
                this.mDatabase.clearCCCD(bluetoothGatt.getDevice());
            }
            if (Build.VERSION.SDK_INT < 24) {
                try {
                    refreshDeviceCache(bluetoothGatt, false);
                } catch (DeviceNotConnectedAtLeastOnceException unused) {
                }
            }
            if (z) {
                close();
            } else {
                cleanAwaiting();
                operationCompleted();
            }
        }
        flashLog(true);
    }

    public void onConnectionUpdated(BluetoothGatt bluetoothGatt, int i, int i2, int i3, int i4) {
        if (i4 == 0) {
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
            saveLogBulk(5, sb.toString());
            if (this.mConnectionPriorityRequested) {
                operationCompleted();
            }
        } else if (i4 == 59) {
            loge("onConnectionUpdated received status: Unacceptable connection interval, interval: " + i + ", latency: " + i2 + ", timeout: " + i3);
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
            saveLogBulk(15, sb2.toString());
            if (this.mConnectionPriorityRequested) {
                operationFailed(i4);
            }
        } else {
            loge("onConnectionUpdated received status: " + i4 + ", interval: " + i + ", latency: " + i2 + ", timeout: " + i3);
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
            saveLogBulk(15, sb3.toString());
            if (this.mConnectionPriorityRequested) {
                operationFailed(i4);
            }
            broadcastError(i4);
        }
        this.mConnectionPriorityRequested = false;
        flashLog(true);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        if (i == 0) {
            saveLogBulk(bluetoothGattDescriptor, 0);
            Macro macro = this.mCurrentlyRecordingMacro;
            if (macro != null) {
                DatabaseHelper databaseHelper = this.mDatabase;
                macro.addOperation(databaseHelper, new ReadDescriptor(databaseHelper, this, bluetoothGattDescriptor));
            }
            if (CLIENT_CHARACTERISTIC_CONFIG.equals(bluetoothGattDescriptor.getUuid())) {
                BluetoothGattCharacteristic characteristic = bluetoothGattDescriptor.getCharacteristic();
                int value = ClientCharacteristicConfigurationParser.getValue(bluetoothGattDescriptor);
                if (value >= 0 && value <= 2) {
                    if (this.mDatabase.getCCCD(bluetoothGatt.getDevice(), bluetoothGattDescriptor) != value) {
                        this.mDatabase.saveCCCD(bluetoothGatt.getDevice(), bluetoothGattDescriptor);
                        StringBuilder sb = new StringBuilder();
                        sb.append("gatt.setCharacteristicNotification(");
                        sb.append(characteristic.getUuid());
                        sb.append(", ");
                        sb.append(value > 0);
                        sb.append(")");
                        saveLogBulk(0, sb.toString());
                        bluetoothGatt.setCharacteristicNotification(characteristic, value > 0);
                        if (value == 0) {
                            saveLogBulk(1, "Notifications and indications disabled for " + characteristic.getUuid());
                        } else if (value == 1) {
                            saveLogBulk(1, "Notifications enabled for " + characteristic.getUuid());
                        } else if (value == 2) {
                            saveLogBulk(1, "Indications enabled for " + characteristic.getUuid());
                        }
                    }
                } else {
                    saveLogBulk(15, "Invalid value of CCCD for " + characteristic.getUuid());
                }
            }
            operationCompleted();
            broadcastAction(ServiceConstants.ACTION_DATA_AVAILABLE);
        } else {
            bluetoothGattDescriptor.setValue(null);
            loge("onDescriptorRead received: " + i);
            operationFailed(i);
            broadcastError(i);
        }
        flashLog(true);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void onDescriptorValueSet(BluetoothGattDescriptor bluetoothGattDescriptor) {
        Macro macro = this.mCurrentlyRecordingMacro;
        if (macro != null) {
            DatabaseHelper databaseHelper = this.mDatabase;
            macro.addOperation(databaseHelper, new SetDescriptorValue(databaseHelper, this, bluetoothGattDescriptor));
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        if (i == 0) {
            saveLogBulk(bluetoothGattDescriptor, 4);
            if (CLIENT_CHARACTERISTIC_CONFIG.equals(bluetoothGattDescriptor.getUuid())) {
                BluetoothGattCharacteristic characteristic = bluetoothGattDescriptor.getCharacteristic();
                int value = ClientCharacteristicConfigurationParser.getValue(bluetoothGattDescriptor);
                if (value >= 0 && value <= 2) {
                    this.mDatabase.saveCCCD(bluetoothGatt.getDevice(), bluetoothGattDescriptor);
                    if (value == 0) {
                        saveLogBulk(1, "Notifications and indications disabled for " + characteristic.getUuid());
                    } else if (value == 1) {
                        saveLogBulk(1, "Notifications enabled for " + characteristic.getUuid());
                    } else if (value == 2) {
                        saveLogBulk(1, "Indications enabled for " + characteristic.getUuid());
                    }
                } else {
                    saveLogBulk(15, "Invalid value of CCCD for " + characteristic.getUuid());
                }
            }
            if (this.mReliableWriteInProgress && !Arrays.equals(this.mDataSent, bluetoothGattDescriptor.getValue())) {
                operationFailed(-4);
            } else {
                operationCompleted();
            }
            broadcastAction(ServiceConstants.ACTION_DATA_SEND);
        } else {
            bluetoothGattDescriptor.setValue(null);
            loge("onDescriptorWrite received: " + i);
            operationFailed(i);
            broadcastError(i);
        }
        this.mDataSent = null;
        flashLog(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDeviceDisconnected() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDfuFinished() {
        BluetoothGatt bluetoothGatt;
        this.mDfuInProgress = false;
        LinkedList<Float> linkedList = this.mDfuSpeedValues;
        if (linkedList != null) {
            linkedList.clear();
        }
        this.mDfuSpeedValues = null;
        this.mDfuAvgSpeedValues.clear();
        this.mDfuAvgSpeedValues = null;
        this.mDfuProgressIndexes.clear();
        this.mDfuProgressIndexes = null;
        this.mDfuMaxAvgSpeed = 1;
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.mSmpCharacteristic;
        if (bluetoothGattCharacteristic == null || (bluetoothGatt = this.mBluetoothGatt) == null) {
            return;
        }
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void onExecuteWrite(boolean z) {
        Macro macro = this.mCurrentlyRecordingMacro;
        if (macro != null) {
            macro.addOperation(new WaitForExecuteWrite(z));
        }
        if (this.mAwaitingWriteExecute != null) {
            this.mAwaitingWriteExecute = Boolean.valueOf(z);
            operationCompleted();
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
        this.mCurrentMTU = i;
        boolean z = this.mRequestedMTU == 0;
        if (i2 == 0) {
            int i3 = this.mRequestedMTU;
            if (i3 != 0 && i3 != i) {
                saveLogBulk(15, "Requested MTU not supported. MTU changed to: " + i);
            } else {
                saveLogBulk(5, "MTU changed to: " + i);
            }
            this.mRequestedMTU = 0;
            operationCompleted();
            broadcastAction(ServiceConstants.ACTION_MTU_CHANGED, i);
        } else {
            loge("onMtuChanged received status: " + i2 + ", mtu: " + i);
            operationFailed(i2);
            broadcastError(i2);
        }
        flashLog(true);
        if (!this.mServicesDiscovered && z && isAutoStartServiceDiscovery()) {
            discoverServicesNoRefresh();
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void onNotificationSent(int i) {
        if (i == 0) {
            operationCompleted();
        } else {
            operationFailed(i);
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onPhyRead(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
        this.mCurrentTxPhy = i;
        this.mCurrentRxPhy = i2;
        if (i3 == 0) {
            saveLogBulk(5, "PHY read (TX: " + phyToString(i) + ", RX: " + phyToString(i2) + ")");
            Macro macro = this.mCurrentlyRecordingMacro;
            if (macro != null) {
                macro.addOperation(new ReadPhy(i, i2));
            }
            operationCompleted();
        } else {
            saveLogBulk(15, "PHY read failed");
            loge("onPhyRead received status: " + i3 + ", txPhy: " + i + ", rxPhy: " + i2);
            operationFailed(i3);
            broadcastError(i3);
        }
        flashLog(true);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
        Macro macro;
        this.mCurrentTxPhy = i;
        this.mCurrentRxPhy = i2;
        if (i3 == 0) {
            saveLogBulk(5, "PHY updated (TX: " + phyToString(i) + ", RX: " + phyToString(i2) + ")");
            if (!this.mPhyRequested && (macro = this.mCurrentlyRecordingMacro) != null) {
                macro.addOperation(new WaitForPhyUpdate(i, i2));
            }
            if (this.mPhyRequested) {
                operationCompleted();
            }
        } else {
            saveLogBulk(15, "PHY update failed");
            loge("onPhyUpdate received status: " + i3 + ", txPhy: " + i + ", rxPhy: " + i2);
            if (this.mPhyRequested) {
                operationFailed(i3);
            }
            broadcastError(i3);
        }
        this.mPhyRequested = false;
        flashLog(true);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
        if (i2 == 0) {
            this.mLastRssi = i;
            saveLogBulk(5, "Remote RSSI received: " + i + " dBm");
            operationCompleted();
            broadcastAction(ServiceConstants.ACTION_RSSI_RECEIVED, i);
        } else {
            loge("onReadRemoteRssi received status: " + i2 + ", rssi: " + i);
            operationFailed(i2);
            broadcastError(i2);
        }
        flashLog(true);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void onReadRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        Macro macro = this.mCurrentlyRecordingMacro;
        if (macro != null) {
            DatabaseHelper databaseHelper = this.mDatabase;
            macro.addOperation(databaseHelper, new WaitForRead(databaseHelper, this, bluetoothGattCharacteristic));
        }
        if (this.mAwaitingCharacteristic == bluetoothGattCharacteristic) {
            this.mAwaitingCharacteristic = null;
            operationCompleted();
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i) {
        this.mReliableWriteInProgress = false;
        if (i == 0) {
            saveLogAndFlash(5, this.mReliableWriteExecute ? "Reliable write completed" : "Reliable write aborted");
            operationCompleted();
            broadcastAction(ServiceConstants.ACTION_DATA_SEND);
            return;
        }
        loge("onReliableWriteCompleted received: " + i);
        operationFailed(i);
        broadcastError(i);
        flashLog(true);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        BluetoothGattDescriptor descriptor;
        this.mDfuSupported = false;
        this.mMicrobitSupported = false;
        this.mThingyConfigServiceSupported = false;
        this.mEddystoneSupported = false;
        this.mServicesDiscovered = true;
        saveLogBulk(0, "[Callback] Services discovered with status: " + i);
        if (i == 0) {
            List<BluetoothGattService> services = bluetoothGatt.getServices();
            bugWorkaround(services);
            this.mHasServices = !services.isEmpty();
            saveLogBulk(5, "Services discovered");
            saveLogBulk(1, services);
            int i2 = 0;
            for (BluetoothGattService bluetoothGattService : services) {
                this.mDfuSupported = this.mDfuSupported || ThirdPartyServices.isDfuService(bluetoothGattService);
                this.mEddystoneSupported = this.mEddystoneSupported || ThirdPartyServices.isEddystoneConfigService(bluetoothGattService);
                this.mThingyConfigServiceSupported = this.mThingyConfigServiceSupported || ThirdPartyServices.isThingyConfigService(bluetoothGattService);
                this.mMicrobitSupported = this.mMicrobitSupported || ThirdPartyServices.isMicrobitService(bluetoothGattService);
                if (ThirdPartyServices.isMcuMgrService(bluetoothGattService)) {
                    this.mSmpCharacteristic = bluetoothGattService.getCharacteristic(ThirdPartyCharacteristics.SMP_CHARACTERISTIC_UUID);
                    this.mMcuMgrSupported = this.mSmpCharacteristic != null;
                }
                for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                    if ((bluetoothGattCharacteristic.getProperties() & 48) > 0 && bluetoothGattCharacteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG) != null) {
                        i2++;
                    }
                }
            }
            boolean z = i2 <= MAX_NOTIFICATIONS;
            Iterator<BluetoothGattService> it = services.iterator();
            while (it.hasNext()) {
                for (BluetoothGattCharacteristic bluetoothGattCharacteristic2 : it.next().getCharacteristics()) {
                    int properties = bluetoothGattCharacteristic2.getProperties();
                    if ((properties & 48) > 0 && (descriptor = bluetoothGattCharacteristic2.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG)) != null) {
                        if (z && !ThirdPartyCharacteristics.isDfuCharacteristic(bluetoothGattCharacteristic2)) {
                            saveLogBulk(0, "gatt.setCharacteristicNotification(" + bluetoothGattCharacteristic2.getUuid() + ", true)");
                            bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic2, true);
                        }
                        int cccd = this.mDatabase.getCCCD(bluetoothGatt.getDevice(), descriptor);
                        if (cccd > 0) {
                            if (!z) {
                                bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic2, true);
                            }
                            descriptor.setValue(cccd == 1 ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                        }
                    }
                    if ((properties & 8) > 0) {
                        bluetoothGattCharacteristic2.setWriteType(2);
                    } else if ((properties & 4) > 0) {
                        bluetoothGattCharacteristic2.setWriteType(1);
                    } else if ((properties & 64) > 0) {
                        bluetoothGattCharacteristic2.setWriteType(4);
                    }
                }
            }
            operationCompleted();
            broadcastAction(ServiceConstants.ACTION_GATT_SERVICES_DISCOVERED);
        } else {
            loge("onServicesDiscovered received: " + i);
            operationFailed(i);
            broadcastError(i);
        }
        flashLog(true);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void onWriteRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic, BluetoothGattCharacteristic bluetoothGattCharacteristic2) {
        Macro macro = this.mCurrentlyRecordingMacro;
        if (macro != null) {
            DatabaseHelper databaseHelper = this.mDatabase;
            macro.addOperation(databaseHelper, new WaitForWrite(databaseHelper, this, bluetoothGattCharacteristic));
        }
        BluetoothGattCharacteristic bluetoothGattCharacteristic3 = this.mAwaitingCharacteristic;
        if (bluetoothGattCharacteristic3 != bluetoothGattCharacteristic && bluetoothGattCharacteristic3 != bluetoothGattCharacteristic2) {
            if (this.mAwaitingNotifCharacteristic == bluetoothGattCharacteristic) {
                byte[] bArr = this.mAwaitingNotifCharacteristicValue;
                if (bArr == null || characteristicValueEquals(bluetoothGattCharacteristic, bArr) == this.mAwaitingNotifCharacteristicCondition) {
                    Runnable runnable = this.mAwaitingNotifCharacteristicTimeoutTask;
                    if (runnable != null) {
                        this.mHandler.removeCallbacks(runnable);
                        this.mAwaitingNotifCharacteristicTimeoutTask = null;
                    }
                    this.mAwaitingNotifCharacteristicValue = null;
                    this.mAwaitingNotifCharacteristic = null;
                    operationCompleted();
                    return;
                }
                return;
            }
            return;
        }
        this.mAwaitingCharacteristic = null;
        operationCompleted();
    }

    protected void operationCompleted() {
        synchronized (this.mLock) {
            if (this.mOperationInProgress && !this.mDfuInProgress) {
                this.mOperationInProgress = false;
                this.mOperationStatus = 0;
                this.mLock.notifyAll();
                broadcastAction(ServiceConstants.ACTION_OPERATION_FINISHED);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void operationFailed(int i) {
        synchronized (this.mLock) {
            if (this.mOperationInProgress && !this.mDfuInProgress) {
                this.mOperationInProgress = false;
                this.mOperationStatus = i;
                this.mLock.notifyAll();
                broadcastAction(ServiceConstants.ACTION_OPERATION_FINISHED);
            }
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void readCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.mOperationInProgress && this.mAwaitingNotifCharacteristic == null) {
            return;
        }
        if (this.mBluetoothGatt != null) {
            String uuid = bluetoothGattCharacteristic.getUuid().toString();
            saveLogBulk(1, "Reading characteristic " + uuid);
            saveLogBulk(0, "gatt.readCharacteristic(" + uuid + ")");
            startOperation();
            try {
                checkUUID(bluetoothGattCharacteristic.getUuid());
            } catch (Exception e2) {
                operationFailed(-1);
                broadcastError(e2);
            }
            if (!this.mBluetoothGatt.readCharacteristic(bluetoothGattCharacteristic)) {
                throw new Exception("Reading characteristic failed");
            }
            flashLog(true);
            return;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void readDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor) {
        if (this.mOperationInProgress && this.mAwaitingNotifCharacteristic == null) {
            return;
        }
        if (this.mBluetoothGatt != null) {
            String uuid = bluetoothGattDescriptor.getUuid().toString();
            saveLogBulk(1, "Reading descriptor " + uuid);
            saveLogBulk(0, "gatt.readDescriptor(" + uuid + ")");
            startOperation();
            try {
                checkUUID(bluetoothGattDescriptor.getCharacteristic().getUuid());
            } catch (Exception e2) {
                operationFailed(-1);
                broadcastError(e2);
            }
            if (!this.mBluetoothGatt.readDescriptor(bluetoothGattDescriptor)) {
                throw new Exception("Reading descriptor failed");
            }
            flashLog(true);
            return;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean readPhy() {
        if (this.mOperationInProgress) {
            return false;
        }
        if (this.mBluetoothGatt != null) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (!defaultAdapter.isLe2MPhySupported() && !defaultAdapter.isLeCodedPhySupported()) {
                saveLogAndFlash(15, "Reading PHY not supported. PHY LE 1M (Legacy) used");
                return false;
            }
            saveLogBulk(1, "Reading PHY...");
            startOperation();
            saveLogAndFlash(0, "gatt.readPhy()");
            this.mBluetoothGatt.readPhy();
            return true;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean readRemoteRssi() {
        if (this.mOperationInProgress) {
            return false;
        }
        if (this.mBluetoothGatt != null) {
            Macro macro = this.mCurrentlyRecordingMacro;
            if (macro != null) {
                macro.addOperation(new ReadRSSI(1));
            }
            this.mLastRssi = 0;
            saveLogBulk(1, "Reading remote RSSI...");
            startOperation();
            saveLogBulk(0, "gatt.readRemoteRssi()");
            boolean readRemoteRssi = this.mBluetoothGatt.readRemoteRssi();
            if (!readRemoteRssi) {
                operationFailed(-1);
            }
            flashLog(true);
            return readRemoteRssi;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean refreshCache() {
        if (this.mBluetoothGatt != null) {
            saveLogBulk(1, "Refreshing device cache...");
            boolean refreshDeviceCache = refreshDeviceCache(this.mBluetoothGatt, true);
            if (refreshDeviceCache) {
                saveLogAndFlash(5, "Cache refreshed");
            } else {
                saveLogAndFlash(15, "Refreshing failed");
            }
            return refreshDeviceCache;
        }
        throw new DeviceNotConnectedAtLeastOnceException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean removeBond() {
        if (this.mBluetoothDevice.getBondState() == 10) {
            saveLogBulk(1, "Remove bond request on not bonded device...");
            saveLogAndFlash(5, "Bond information deleted");
            return true;
        }
        saveLogBulk(1, "Removing bond information...");
        saveLogBulk(0, "device.removeBond() (hidden)");
        boolean removeBond = BondHelper.removeBond(this.mBluetoothDevice);
        if (!removeBond) {
            saveLogBulk(15, "Removing bond failed");
        } else {
            startOperation();
        }
        flashLog(true);
        return removeBond;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    @TargetApi(21)
    public boolean requestConnectionPriority(int i) {
        String string;
        String str;
        if (this.mOperationInProgress) {
            return false;
        }
        if (this.mBluetoothGatt != null) {
            Macro macro = this.mCurrentlyRecordingMacro;
            if (macro != null) {
                macro.addOperation(new RequestConnectionPriority(i));
            }
            if (i == 1) {
                string = this.mContext.getString(R.string.priority_high);
                str = "HIGH";
            } else if (i != 2) {
                string = this.mContext.getString(R.string.priority_balanced);
                str = "BALANCED";
            } else {
                string = this.mContext.getString(R.string.priority_low);
                str = "LOW POWER";
            }
            saveLogBulk(1, "Requesting connection priority: " + string + "...");
            if (Build.VERSION.SDK_INT >= 26) {
                this.mConnectionPriorityRequested = true;
                startOperation();
            }
            saveLogBulk(0, "gatt.requestConnectionPriority(" + str + ")");
            boolean requestConnectionPriority = this.mBluetoothGatt.requestConnectionPriority(i);
            if (Build.VERSION.SDK_INT < 26) {
                this.mOperationStatus = requestConnectionPriority ? 0 : -1;
                if (requestConnectionPriority) {
                    saveLogBulk(5, "Connection parameter update request sent");
                } else {
                    saveLogBulk(20, "Failed to send connection parameter update");
                }
            } else if (!requestConnectionPriority) {
                operationFailed(-1);
                saveLogBulk(20, "Failed to send connection parameter update");
            }
            flashLog(true);
            return requestConnectionPriority;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean requestMtu(int i) {
        if (this.mOperationInProgress) {
            return false;
        }
        if (this.mBluetoothGatt != null) {
            Macro macro = this.mCurrentlyRecordingMacro;
            if (macro != null) {
                macro.addOperation(new RequestMtu(i));
            }
            startOperation();
            return requestMtu(i, false);
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void saveLogAndFlash(int i, String str) {
        saveLogBulk(i, str);
        flashLog(true);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void saveLogBulk(int i, String str) {
        ILogSession iLogSession = this.mLogSession;
        if (iLogSession == null) {
            return;
        }
        synchronized (this.mLogLock) {
            this.currentValuesList.add(Logger.logEntry(iLogSession, i, str));
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void sendCharacteristicIndication(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        ServiceServerController serviceServerController;
        if (this.mOperationInProgress || (serviceServerController = this.mServerController) == null) {
            return;
        }
        if (serviceServerController.isConnected(this.mBluetoothDevice)) {
            Macro macro = this.mCurrentlyRecordingMacro;
            if (macro != null) {
                DatabaseHelper databaseHelper = this.mDatabase;
                macro.addOperation(databaseHelper, new SendIndication(databaseHelper, this, bluetoothGattCharacteristic));
            }
            startOperation();
            this.mServerController.sendCharacteristicNotification(this.mBluetoothDevice, bluetoothGattCharacteristic, true);
            return;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void sendCharacteristicNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        ServiceServerController serviceServerController;
        if (this.mOperationInProgress || (serviceServerController = this.mServerController) == null) {
            return;
        }
        if (serviceServerController.isConnected(this.mBluetoothDevice)) {
            Macro macro = this.mCurrentlyRecordingMacro;
            if (macro != null) {
                DatabaseHelper databaseHelper = this.mDatabase;
                macro.addOperation(databaseHelper, new SendNotification(databaseHelper, this, bluetoothGattCharacteristic));
            }
            startOperation();
            this.mServerController.sendCharacteristicNotification(this.mBluetoothDevice, bluetoothGattCharacteristic, false);
            return;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void setCharacteristicIndication(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        if (this.mOperationInProgress) {
            return;
        }
        if (this.mBluetoothGatt != null) {
            BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG);
            if (descriptor == null) {
                logw("Unable to configure indications on " + bluetoothGattCharacteristic.getUuid());
                saveLogAndFlash(15, "Unable to configure indications for " + bluetoothGattCharacteristic.getUuid());
                return;
            }
            descriptor.setValue(z ? BluetoothGattDescriptor.ENABLE_INDICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
            Macro macro = this.mCurrentlyRecordingMacro;
            if (macro != null) {
                DatabaseHelper databaseHelper = this.mDatabase;
                macro.addOperation(databaseHelper, new WriteDescriptor(databaseHelper, this, descriptor, descriptor.getValue()));
            }
            if (z) {
                saveLogBulk(1, "Enabling indications for " + bluetoothGattCharacteristic.getUuid());
            } else {
                saveLogBulk(1, "Disabling indications for " + bluetoothGattCharacteristic.getUuid());
            }
            startOperation();
            try {
                saveLogBulk(0, "gatt.setCharacteristicNotification(" + bluetoothGattCharacteristic.getUuid() + ", " + z + ")");
                this.mBluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
                StringBuilder sb = new StringBuilder();
                sb.append("gatt.writeDescriptor(");
                sb.append(descriptor.getUuid());
                sb.append(z ? ", value=0x0200)" : ", value=0x0000)");
                saveLogBulk(0, sb.toString());
                checkUUID(bluetoothGattCharacteristic.getUuid());
                this.mDataSent = descriptor.getValue();
                this.mBluetoothGatt.writeDescriptor(descriptor);
            } catch (Exception e2) {
                this.mDataSent = null;
                operationFailed(-1);
                broadcastError(e2);
            }
            flashLog(true);
            return;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void setCharacteristicNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        if (this.mOperationInProgress) {
            return;
        }
        if (this.mBluetoothGatt != null) {
            BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG);
            if (descriptor == null) {
                logw("Unable to configure notifications on " + bluetoothGattCharacteristic.getUuid());
                saveLogAndFlash(15, "Unable to configure notifications for " + bluetoothGattCharacteristic.getUuid());
                return;
            }
            startOperation();
            descriptor.setValue(z ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
            Macro macro = this.mCurrentlyRecordingMacro;
            if (macro != null) {
                DatabaseHelper databaseHelper = this.mDatabase;
                macro.addOperation(databaseHelper, new WriteDescriptor(databaseHelper, this, descriptor, descriptor.getValue()));
            }
            if (z) {
                saveLogBulk(1, "Enabling notifications for " + bluetoothGattCharacteristic.getUuid());
            } else {
                saveLogBulk(1, "Disabling notifications for " + bluetoothGattCharacteristic.getUuid());
            }
            try {
                saveLogBulk(0, "gatt.setCharacteristicNotification(" + bluetoothGattCharacteristic.getUuid() + ", " + z + ")");
                this.mBluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
                StringBuilder sb = new StringBuilder();
                sb.append("gatt.writeDescriptor(");
                sb.append(descriptor.getUuid());
                sb.append(z ? ", value=0x0100)" : ", value=0x0000)");
                saveLogBulk(0, sb.toString());
                checkUUID(bluetoothGattCharacteristic.getUuid());
                this.mDataSent = descriptor.getValue();
                this.mBluetoothGatt.writeDescriptor(descriptor);
            } catch (Exception e2) {
                this.mDataSent = null;
                operationFailed(-1);
                broadcastError(e2);
            }
            flashLog(true);
            return;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void setDeviceName(String str) {
        this.mBluetoothDeviceName = str;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void setMtu(int i) {
        this.mCurrentMTU = i;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public boolean setPreferredPhy(int i, int i2, int i3) {
        if (this.mOperationInProgress) {
            return false;
        }
        if (this.mBluetoothGatt != null) {
            Macro macro = this.mCurrentlyRecordingMacro;
            if (macro != null) {
                macro.addOperation(new SetPreferredPhy(i, i2, i3));
            }
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (!defaultAdapter.isLe2MPhySupported() && !defaultAdapter.isLeCodedPhySupported()) {
                saveLogAndFlash(15, "Setting PHY not supported. PHY LE 1M (Legacy) used");
                return false;
            }
            saveLogBulk(1, "Requesting preferred PHYs...");
            startOperation();
            this.mPhyRequested = true;
            saveLogAndFlash(0, "gatt.setPreferredPhy(" + phyMaskToString(i) + ", " + phyMaskToString(i2) + ", coding option = " + phyCodedOptionToString(i3) + ")");
            this.mBluetoothGatt.setPreferredPhy(i, i2, i3);
            return true;
        }
        throw new DeviceNotConnectedException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setServerController(ServiceServerController serviceServerController) {
        this.mServerController = serviceServerController;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void setWriteType(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        saveLogBulk(1, "Changing write type for " + bluetoothGattCharacteristic.getUuid() + " to " + GattUtils.getWriteTypeAsString(this.mContext, i));
        StringBuilder sb = new StringBuilder();
        sb.append("characteristic.setWriteType(");
        sb.append(GattUtils.getWriteTypeAsString(this.mContext, i));
        sb.append(")");
        saveLogAndFlash(0, sb.toString());
        bluetoothGattCharacteristic.setWriteType(i);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void sleep(long j) {
        Macro macro = this.mCurrentlyRecordingMacro;
        if (macro != null) {
            macro.addOperation(new Sleep(j));
        }
        startOperation();
        try {
            synchronized (this.mLock) {
                saveLogAndFlash(0, "wait(" + j + ")");
                this.mLock.wait(j);
                operationCompleted();
            }
        } catch (InterruptedException unused) {
            operationFailed(-1);
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void startDfuUpload(int i, String str, String str2, Uri uri, String str3, Uri uri2) {
        if (this.mDfuInProgress) {
            return;
        }
        this.mDfuInProgress = true;
        this.mDfuSpeedValues = new LinkedList<>();
        this.mDfuAvgSpeedValues = new LinkedList<>();
        this.mDfuProgressIndexes = new LinkedList<>();
        this.mDfuMaxAvgSpeed = 1;
        startOperation();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DfuBaseService.BROADCAST_PROGRESS);
        intentFilter.addAction(DfuBaseService.BROADCAST_ERROR);
        intentFilter.addAction(DfuBaseService.BROADCAST_LOG);
        a.l.a.a.a(this.mContext).a(this.mDfuBroadcastReceiver, intentFilter);
        boolean z = PreferenceManager.getDefaultSharedPreferences(this.mContext).getBoolean(SETTINGS_KEEP_BOND, true);
        Intent intent = new Intent(this.mContext, (Class<?>) DfuService.class);
        intent.putExtra(DfuBaseService.EXTRA_DEVICE_ADDRESS, this.mBluetoothDeviceAddress);
        intent.putExtra(DfuBaseService.EXTRA_DEVICE_NAME, this.mBluetoothDeviceName);
        intent.putExtra(DfuBaseService.EXTRA_DISABLE_NOTIFICATION, false);
        intent.putExtra(DfuBaseService.EXTRA_FOREGROUND_SERVICE, true);
        intent.putExtra(DfuBaseService.EXTRA_FILE_MIME_TYPE, str);
        intent.putExtra(DfuBaseService.EXTRA_FILE_TYPE, i);
        intent.putExtra(DfuBaseService.EXTRA_FILE_PATH, str2);
        intent.putExtra(DfuBaseService.EXTRA_FILE_URI, uri);
        intent.putExtra(DfuBaseService.EXTRA_INIT_FILE_PATH, str3);
        intent.putExtra(DfuBaseService.EXTRA_INIT_FILE_URI, uri2);
        intent.putExtra(DfuBaseService.EXTRA_KEEP_BOND, z);
        intent.putExtra(DfuBaseService.EXTRA_UNSAFE_EXPERIMENTAL_BUTTONLESS_DFU, true);
        intent.putExtra(DfuBaseService.EXTRA_MTU, 517);
        intent.putExtra(DfuBaseService.EXTRA_CURRENT_MTU, this.mCurrentMTU);
        intent.putExtra(DfuBaseService.EXTRA_MAX_DFU_ATTEMPTS, 2);
        this.mContext.startService(intent);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void startMcuMgrImageUpload(String str, Uri uri, FirmwareUpgradeManager.Mode mode) {
        byte[] loadContent;
        if (this.mDfuInProgress) {
            return;
        }
        final LoggingMcuMgrBleTransport loggingMcuMgrBleTransport = new LoggingMcuMgrBleTransport(this.mContext, this.mBluetoothDevice);
        loggingMcuMgrBleTransport.setLogger(this.mLogSession);
        FirmwareUpgradeCallback firmwareUpgradeCallback = new FirmwareUpgradeCallback() { // from class: no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection.5
            private int lastBytesSent;
            private long lastTime;
            private long startTime;

            /* JADX INFO: Access modifiers changed from: private */
            public void sendProgressBroadcast(int i) {
                Intent intent = new Intent(DfuBaseService.BROADCAST_PROGRESS);
                intent.putExtra(DfuBaseService.EXTRA_DEVICE_ADDRESS, BluetoothLeBasicConnection.this.mBluetoothDeviceAddress);
                intent.putExtra(DfuBaseService.EXTRA_DATA, i);
                a.l.a.a.a(BluetoothLeBasicConnection.this.mContext).a(intent);
            }

            @Override // io.runtime.mcumgr.dfu.FirmwareUpgradeCallback
            public void onStateChanged(FirmwareUpgradeManager.State state, FirmwareUpgradeManager.State state2) {
                int i = AnonymousClass7.$SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$State[state2.ordinal()];
                if (i == 1) {
                    loggingMcuMgrBleTransport.log(2, "Uploading firmware...");
                    loggingMcuMgrBleTransport.setUploading(true);
                    sendProgressBroadcast(-2);
                } else if (i == 2 || i == 3 || i == 4 || i == 5) {
                    loggingMcuMgrBleTransport.setUploading(false);
                    sendProgressBroadcast(-4);
                }
            }

            @Override // io.runtime.mcumgr.dfu.FirmwareUpgradeCallback
            public void onUpgradeCanceled(FirmwareUpgradeManager.State state) {
                loggingMcuMgrBleTransport.addObserver(new McuMgrTransport.ConnectionObserver() { // from class: no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection.5.3
                    @Override // io.runtime.mcumgr.McuMgrTransport.ConnectionObserver
                    public void onConnected() {
                    }

                    @Override // io.runtime.mcumgr.McuMgrTransport.ConnectionObserver
                    public void onDisconnected() {
                        loggingMcuMgrBleTransport.removeObserver(this);
                        sendProgressBroadcast(-7);
                        BluetoothLeBasicConnection.this.onDfuFinished();
                        BluetoothLeBasicConnection.this.operationCompleted();
                    }
                });
                loggingMcuMgrBleTransport.release();
                sendProgressBroadcast(-5);
            }

            @Override // io.runtime.mcumgr.dfu.FirmwareUpgradeCallback
            public void onUpgradeCompleted() {
                if (loggingMcuMgrBleTransport.isConnected()) {
                    loggingMcuMgrBleTransport.addObserver(new McuMgrTransport.ConnectionObserver() { // from class: no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection.5.1
                        @Override // io.runtime.mcumgr.McuMgrTransport.ConnectionObserver
                        public void onConnected() {
                        }

                        @Override // io.runtime.mcumgr.McuMgrTransport.ConnectionObserver
                        public void onDisconnected() {
                            loggingMcuMgrBleTransport.removeObserver(this);
                            sendProgressBroadcast(-6);
                            BluetoothLeBasicConnection.this.onDfuFinished();
                            BluetoothLeBasicConnection.this.operationCompleted();
                        }
                    });
                    loggingMcuMgrBleTransport.setUploading(false);
                    loggingMcuMgrBleTransport.release();
                    sendProgressBroadcast(-5);
                    return;
                }
                sendProgressBroadcast(-6);
                BluetoothLeBasicConnection.this.onDfuFinished();
                BluetoothLeBasicConnection.this.operationCompleted();
            }

            @Override // io.runtime.mcumgr.dfu.FirmwareUpgradeCallback
            public void onUpgradeFailed(FirmwareUpgradeManager.State state, final McuMgrException mcuMgrException) {
                McuMgrTransport.ConnectionObserver connectionObserver = new McuMgrTransport.ConnectionObserver() { // from class: no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection.5.2
                    @Override // io.runtime.mcumgr.McuMgrTransport.ConnectionObserver
                    public void onConnected() {
                    }

                    @Override // io.runtime.mcumgr.McuMgrTransport.ConnectionObserver
                    public void onDisconnected() {
                        loggingMcuMgrBleTransport.removeObserver(this);
                        Intent intent = new Intent(DfuBaseService.BROADCAST_ERROR);
                        intent.putExtra(DfuBaseService.EXTRA_DEVICE_ADDRESS, BluetoothLeBasicConnection.this.mBluetoothDeviceAddress);
                        if (mcuMgrException instanceof McuMgrErrorException) {
                            intent.putExtra(DfuBaseService.EXTRA_ERROR_TYPE, 3);
                            BluetoothLeBasicConnection.this.operationFailed(((McuMgrErrorException) mcuMgrException).getCode().value());
                        } else {
                            intent.putExtra(DfuBaseService.EXTRA_ERROR_TYPE, 0);
                            BluetoothLeBasicConnection.this.operationFailed(-1);
                        }
                        a.l.a.a.a(BluetoothLeBasicConnection.this.mContext).a(intent);
                        BluetoothLeBasicConnection.this.onDfuFinished();
                        BluetoothLeBasicConnection.this.operationCompleted();
                    }
                };
                if (loggingMcuMgrBleTransport.isConnected()) {
                    loggingMcuMgrBleTransport.addObserver(connectionObserver);
                    loggingMcuMgrBleTransport.release();
                    sendProgressBroadcast(-5);
                    return;
                }
                connectionObserver.onDisconnected();
            }

            @Override // io.runtime.mcumgr.dfu.FirmwareUpgradeCallback
            public void onUpgradeStarted(FirmwareUpgradeController firmwareUpgradeController) {
                BluetoothLeBasicConnection bluetoothLeBasicConnection = BluetoothLeBasicConnection.this;
                bluetoothLeBasicConnection.mDfuInProgress = true;
                bluetoothLeBasicConnection.mDfuSpeedValues = new LinkedList<>();
                BluetoothLeBasicConnection.this.mDfuAvgSpeedValues = new LinkedList<>();
                BluetoothLeBasicConnection.this.mDfuProgressIndexes = new LinkedList<>();
                BluetoothLeBasicConnection bluetoothLeBasicConnection2 = BluetoothLeBasicConnection.this;
                bluetoothLeBasicConnection2.mDfuMaxAvgSpeed = 1;
                bluetoothLeBasicConnection2.mBluetoothGatt.setCharacteristicNotification(bluetoothLeBasicConnection2.mSmpCharacteristic, false);
                sendProgressBroadcast(-1);
            }

            @Override // io.runtime.mcumgr.dfu.FirmwareUpgradeCallback
            public void onUploadProgressChanged(int i, int i2, long j) {
                float f2;
                if (this.startTime == 0) {
                    this.startTime = j;
                }
                Intent intent = new Intent(DfuBaseService.BROADCAST_PROGRESS);
                intent.putExtra(DfuBaseService.EXTRA_DEVICE_ADDRESS, BluetoothLeBasicConnection.this.mBluetoothDeviceAddress);
                intent.putExtra(DfuBaseService.EXTRA_DATA, (i * 100) / i2);
                long j2 = this.startTime;
                float f3 = 0.0f;
                if (j2 != j) {
                    f3 = (i - this.lastBytesSent) / ((float) (j - this.lastTime));
                    f2 = i / ((float) (j - j2));
                } else {
                    f2 = 0.0f;
                }
                this.lastBytesSent = i;
                this.lastTime = j;
                intent.putExtra(DfuBaseService.EXTRA_SPEED_B_PER_MS, f3);
                intent.putExtra(DfuBaseService.EXTRA_AVG_SPEED_B_PER_MS, f2);
                a.l.a.a.a(BluetoothLeBasicConnection.this.mContext).a(intent);
            }
        };
        try {
            if (uri != null) {
                loadContent = loadContent(this.mContext.getContentResolver().openInputStream(uri));
            } else {
                loadContent = loadContent(new FileInputStream(new File(str)));
            }
            this.mFirmwareUpgradeManager = new FirmwareUpgradeManager(loggingMcuMgrBleTransport, firmwareUpgradeCallback);
            try {
                startOperation();
                this.mFirmwareUpgradeManager.setMode(mode);
                this.mFirmwareUpgradeManager.start(loadContent);
            } catch (McuMgrException e2) {
                Log.e(TAG, "Invalid image selected", e2);
                Intent intent = new Intent(DfuBaseService.BROADCAST_ERROR);
                intent.putExtra(DfuBaseService.EXTRA_DEVICE_ADDRESS, this.mBluetoothDeviceAddress);
                intent.putExtra(DfuBaseService.EXTRA_DATA, DfuBaseService.ERROR_FILE_INVALID);
                a.l.a.a.a(this.mContext).a(intent);
            }
        } catch (Exception e3) {
            Log.e(TAG, "Loading file failed", e3);
            Intent intent2 = new Intent(DfuBaseService.BROADCAST_ERROR);
            intent2.putExtra(DfuBaseService.EXTRA_DEVICE_ADDRESS, this.mBluetoothDeviceAddress);
            intent2.putExtra(DfuBaseService.EXTRA_DATA, DfuBaseService.ERROR_FILE_ERROR);
            a.l.a.a.a(this.mContext).a(intent2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startOperation() {
        synchronized (this.mLock) {
            this.mOperationInProgress = true;
            this.mOperationStatus = 0;
            broadcastAction(ServiceConstants.ACTION_OPERATION_STARTED);
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void waitForExecuteWrite(boolean z) {
        if (this.mBluetoothGatt != null) {
            this.mAwaitingWriteExecute = Boolean.valueOf(z);
            startOperation();
            return;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void waitForNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.mBluetoothGatt != null) {
            this.mAwaitingNotifCharacteristic = bluetoothGattCharacteristic;
            startOperation();
            return;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void waitForPhyUpdate() {
        if (this.mBluetoothGatt != null) {
            this.mAwaitingPhyUpdate = true;
            startOperation();
            return;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void waitForReadRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.mBluetoothGatt != null) {
            this.mAwaitingCharacteristic = bluetoothGattCharacteristic;
            startOperation();
            return;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void waitForWriteRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.mBluetoothGatt != null) {
            this.mAwaitingCharacteristic = bluetoothGattCharacteristic;
            startOperation();
            return;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public int waitUntilOperationCompleted() {
        int i;
        synchronized (this.mLock) {
            while (true) {
                if (!this.mOperationInProgress && !this.mDfuInProgress) {
                    break;
                }
                this.mLock.wait();
            }
            if (this.mAwaitingNotifCharacteristic != null) {
                this.mOperationInProgress = true;
            }
            i = this.mOperationStatus;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int waitUntilOperationCompletedSafe() {
        try {
            return waitUntilOperationCompleted();
        } catch (InterruptedException unused) {
            return this.mOperationStatus;
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void writeCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        if (this.mOperationInProgress && this.mAwaitingNotifCharacteristic == null) {
            return;
        }
        if (this.mBluetoothGatt != null) {
            Macro macro = this.mCurrentlyRecordingMacro;
            if (macro != null) {
                DatabaseHelper databaseHelper = this.mDatabase;
                macro.addOperation(databaseHelper, new Write(databaseHelper, this, bluetoothGattCharacteristic, bArr));
            }
            int writeType = bluetoothGattCharacteristic.getWriteType();
            String str = writeType != 1 ? writeType != 4 ? "request" : "signed command" : "command";
            String uuid = bluetoothGattCharacteristic.getUuid().toString();
            saveLogBulk(1, "Writing " + str + " to characteristic " + uuid);
            saveLogBulk(0, "gatt.writeCharacteristic(" + uuid + ", value=" + ParserUtils.bytesToHex(bArr, 0, bArr.length, true) + ")");
            startOperation();
            try {
                checkUUID(bluetoothGattCharacteristic.getUuid());
                this.mDataSent = bArr;
                bluetoothGattCharacteristic.setValue(bArr);
            } catch (Exception e2) {
                this.mDataSent = null;
                operationFailed(-1);
                broadcastError(e2);
            }
            if (!this.mBluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic)) {
                throw new Exception("Writing characteristic failed");
            }
            flashLog(bluetoothGattCharacteristic.getWriteType() != 1);
            return;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void writeDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr) {
        boolean writeDescriptor;
        if (CLIENT_CHARACTERISTIC_CONFIG.equals(bluetoothGattDescriptor.getUuid()) && bArr.length == 2 && bArr[1] == BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE[1]) {
            boolean z = bArr[0] == BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE[0];
            boolean z2 = bArr[0] == BluetoothGattDescriptor.ENABLE_INDICATION_VALUE[0];
            boolean z3 = bArr[0] == BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE[0];
            BluetoothGattCharacteristic characteristic = bluetoothGattDescriptor.getCharacteristic();
            if (z) {
                setCharacteristicNotification(characteristic, true);
                return;
            }
            if (z2) {
                setCharacteristicIndication(characteristic, true);
                return;
            } else if (z3) {
                if ((characteristic.getProperties() | 32) > 0) {
                    setCharacteristicIndication(characteristic, false);
                    return;
                } else {
                    setCharacteristicNotification(characteristic, false);
                    return;
                }
            }
        }
        if (this.mOperationInProgress && this.mAwaitingNotifCharacteristic == null) {
            return;
        }
        if (this.mBluetoothGatt != null) {
            Macro macro = this.mCurrentlyRecordingMacro;
            if (macro != null) {
                DatabaseHelper databaseHelper = this.mDatabase;
                macro.addOperation(databaseHelper, new WriteDescriptor(databaseHelper, this, bluetoothGattDescriptor, bArr));
            }
            String uuid = bluetoothGattDescriptor.getUuid().toString();
            saveLogBulk(1, "Writing to descriptor " + uuid);
            saveLogBulk(0, "gatt.writeDescriptor(" + uuid + ", value=" + ParserUtils.bytesToHex(bArr, 0, bArr.length, true) + ")");
            startOperation();
            try {
                this.mDataSent = bArr;
                BluetoothGattCharacteristic characteristic2 = bluetoothGattDescriptor.getCharacteristic();
                checkUUID(characteristic2.getUuid());
                int writeType = characteristic2.getWriteType();
                characteristic2.setWriteType(2);
                bluetoothGattDescriptor.setValue(bArr);
                writeDescriptor = this.mBluetoothGatt.writeDescriptor(bluetoothGattDescriptor);
                characteristic2.setWriteType(writeType);
            } catch (Exception e2) {
                this.mDataSent = null;
                operationFailed(-1);
                broadcastError(e2);
            }
            if (!writeDescriptor) {
                throw new Exception("Writing descriptor failed");
            }
            flashLog(true);
            return;
        }
        throw new DeviceNotConnectedException();
    }

    private void disconnect(boolean z) {
        if (this.mBluetoothGatt == null) {
            return;
        }
        int i = this.mConnectionState;
        if (i == 0) {
            broadcastAction(ServiceConstants.ACTION_GATT_DISCONNECTED);
        } else if (i == 1) {
            this.mConnectionState = 3;
            if (z) {
                this.mConnectionState = 4;
            }
            broadcastAction(ServiceConstants.ACTION_GATT_DISCONNECTING);
            ServiceServerController serviceServerController = this.mServerController;
            if (serviceServerController != null && serviceServerController.isStarted() && this.mServerController.isConnected(this.mBluetoothDevice)) {
                this.mServerController.cancelConnection(this.mBluetoothDevice);
            }
            synchronized (this.mGattLock) {
                if (this.mBluetoothGatt != null) {
                    saveLogBulk(1, "Cancelling connection...");
                    saveLogBulk(0, "gatt.disconnect()");
                    this.mBluetoothGatt.disconnect();
                }
            }
            this.mConnectionState = 0;
            saveLogBulk(5, "Disconnected");
            broadcastAction(ServiceConstants.ACTION_GATT_DISCONNECTED);
            if (z) {
                close();
            } else {
                operationCompleted();
                cleanAwaiting();
            }
        } else if (i == 2) {
            this.mConnectionState = 3;
            if (z) {
                this.mConnectionState = 4;
            }
            broadcastAction(ServiceConstants.ACTION_GATT_DISCONNECTING);
            ServiceServerController serviceServerController2 = this.mServerController;
            if (serviceServerController2 != null && serviceServerController2.isStarted() && this.mServerController.isConnected(this.mBluetoothDevice)) {
                this.mServerController.cancelConnection(this.mBluetoothDevice);
            }
            synchronized (this.mGattLock) {
                if (this.mBluetoothGatt != null) {
                    saveLogBulk(1, "Disconnecting...");
                    saveLogBulk(0, "gatt.disconnect()");
                    startOperation();
                    this.mBluetoothGatt.disconnect();
                }
            }
        }
        flashLog(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void broadcastError(int i) {
        saveLogBulk(20, "Error " + i + " (0x" + Integer.toHexString(i) + "): " + e.a.a.a.a.a(i));
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void connect(boolean z) {
        int i = this.mConnectionState;
        if (i == 2 || i == 1 || this.mDfuInProgress) {
            return;
        }
        if (z || !this.mAutoConnect) {
            synchronized (this.mGattLock) {
                if (this.mBluetoothGatt != null) {
                    saveLogBulk(0, "gatt.close()");
                    this.mBluetoothGatt.close();
                    this.mBluetoothGatt = null;
                    try {
                        synchronized (this.mLock) {
                            saveLogAndFlash(0, "wait(200)");
                            this.mLock.wait(200L);
                        }
                    } catch (InterruptedException e2) {
                        loge("Wait interrupted", e2);
                    }
                }
            }
        }
        BluetoothDevice bluetoothDevice = this.mBluetoothDevice;
        if (bluetoothDevice.getBondState() == 10) {
            this.mDatabase.clearCCCD(bluetoothDevice);
        }
        this.mConnectionState = 1;
        saveLogBulk(1, "Connecting to " + bluetoothDevice.getAddress() + "...");
        broadcastAction(ServiceConstants.ACTION_GATT_CONNECTING);
        startOperation();
        if (this.mBluetoothGatt == null) {
            this.mBluetoothGatt = connectGatt(bluetoothDevice, (z && this.mAsCentral) ? false : true);
        } else {
            saveLogBulk(0, "gatt.connect()");
            this.mBluetoothGatt.connect();
        }
        flashLog(true);
        this.mConnectionAttemptDone = true;
        this.mAsCentral = true;
    }

    protected void loge(String str, Throwable th) {
        Log.e(TAG, str, th);
    }

    protected void saveLogAndFlash(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        UUID uuid = bluetoothGattCharacteristic.getUuid();
        String parse = (bluetoothGattCharacteristic.getValue() == null || bluetoothGattCharacteristic.getValue().length <= 0) ? "0 bytes" : GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        String valueAsString = CharacteristicParser.getValueAsString(this.mDatabase, bluetoothGattCharacteristic, null, true);
        if (i == 0) {
            saveLogBulk(5, "Read Response received from " + uuid + ", value: " + parse);
            if (!TextUtils.isEmpty(valueAsString)) {
                saveLogBulk(10, "\"" + valueAsString + "\" received");
            }
            flashLog(true);
            return;
        }
        if (i == 1) {
            saveLogBulk(5, "Notification received from " + uuid + ", value: " + parse);
            if (!TextUtils.isEmpty(valueAsString)) {
                saveLogBulk(10, "\"" + valueAsString + "\" received");
            }
            flashLog(false);
            return;
        }
        if (i == 2) {
            saveLogBulk(5, "Indication received from " + uuid + ", value: " + parse);
            if (!TextUtils.isEmpty(valueAsString)) {
                saveLogBulk(10, "\"" + valueAsString + "\" received");
            }
            flashLog(true);
            return;
        }
        if (i != 4) {
            return;
        }
        saveLogBulk(5, "Data written to " + uuid + ", value: " + parse);
        if (this.mReliableWriteInProgress && !Arrays.equals(this.mDataSent, bluetoothGattCharacteristic.getValue())) {
            saveLogBulk(15, "Data corrupted");
        }
        if (!TextUtils.isEmpty(valueAsString)) {
            saveLogBulk(10, "\"" + valueAsString + "\" sent");
        }
        flashLog(bluetoothGattCharacteristic.getWriteType() != 1);
    }

    public void saveLogBulk(BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        UUID uuid = bluetoothGattDescriptor.getUuid();
        String parse = (bluetoothGattDescriptor.getValue() == null || bluetoothGattDescriptor.getValue().length <= 0) ? "0 bytes" : GeneralDescriptorParser.parse(bluetoothGattDescriptor);
        String valueAsString = DescriptorParser.getValueAsString(bluetoothGattDescriptor, null, true);
        if (i == 0) {
            saveLogBulk(5, "Read Response received from descr. " + uuid + ", value: " + parse);
            if (TextUtils.isEmpty(valueAsString)) {
                return;
            }
            saveLogBulk(10, "\"" + valueAsString + "\" received");
            return;
        }
        if (i != 4) {
            return;
        }
        saveLogBulk(5, "Data written to descr. " + uuid + ", value: " + parse);
        if (this.mReliableWriteInProgress && !Arrays.equals(this.mDataSent, bluetoothGattDescriptor.getValue())) {
            saveLogBulk(15, "Data corrupted");
        }
        if (TextUtils.isEmpty(valueAsString)) {
            return;
        }
        saveLogBulk(10, "\"" + valueAsString + "\" sent");
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void waitForReadRequest(BluetoothGattDescriptor bluetoothGattDescriptor) {
        if (this.mBluetoothGatt != null) {
            this.mAwaitingDescriptor = bluetoothGattDescriptor;
            startOperation();
            return;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void waitForWriteRequest(BluetoothGattDescriptor bluetoothGattDescriptor) {
        if (this.mBluetoothGatt != null) {
            this.mAwaitingDescriptor = bluetoothGattDescriptor;
            startOperation();
            return;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void onReadRequest(BluetoothGattDescriptor bluetoothGattDescriptor) {
        Macro macro = this.mCurrentlyRecordingMacro;
        if (macro != null) {
            DatabaseHelper databaseHelper = this.mDatabase;
            macro.addOperation(databaseHelper, new WaitForReadDescriptor(databaseHelper, this, bluetoothGattDescriptor));
        }
        if (this.mAwaitingDescriptor == bluetoothGattDescriptor) {
            this.mAwaitingDescriptor = null;
            operationCompleted();
        }
    }

    private boolean requestMtu(int i, boolean z) {
        if (!z) {
            this.mRequestedMTU = i;
        }
        saveLogBulk(1, "Requesting new MTU...");
        saveLogBulk(0, "gatt.requestMtu(" + i + ")");
        boolean requestMtu = this.mBluetoothGatt.requestMtu(i);
        if (!requestMtu) {
            operationFailed(-1);
        }
        flashLog(true);
        return requestMtu;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void sleep(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, boolean z, long j, boolean z2) {
        if (this.mBluetoothGatt != null) {
            Macro macro = this.mCurrentlyRecordingMacro;
            if (macro != null) {
                if (z) {
                    DatabaseHelper databaseHelper = this.mDatabase;
                    macro.addOperation(databaseHelper, new SleepUntil(databaseHelper, this, bluetoothGattCharacteristic, bArr, j, z2));
                } else {
                    DatabaseHelper databaseHelper2 = this.mDatabase;
                    macro.addOperation(databaseHelper2, new SleepIf(databaseHelper2, this, bluetoothGattCharacteristic, bArr, j, z2));
                }
            }
            if (characteristicValueEquals(bluetoothGattCharacteristic, bArr) == z) {
                return;
            }
            this.mAwaitingNotifCharacteristic = bluetoothGattCharacteristic;
            this.mAwaitingNotifCharacteristicValue = bArr;
            this.mAwaitingNotifCharacteristicCondition = z;
            startOperation();
            if (j > 0) {
                Handler handler = this.mHandler;
                Runnable runnable = new Runnable() { // from class: no.nordicsemi.android.mcp.ble.e
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothLeBasicConnection.this.b();
                    }
                };
                this.mAwaitingNotifCharacteristicTimeoutTask = runnable;
                handler.postDelayed(runnable, j);
                return;
            }
            return;
        }
        throw new DeviceNotConnectedException();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void onWriteRequest(BluetoothGattDescriptor bluetoothGattDescriptor, BluetoothGattDescriptor bluetoothGattDescriptor2) {
        Macro macro = this.mCurrentlyRecordingMacro;
        if (macro != null) {
            DatabaseHelper databaseHelper = this.mDatabase;
            macro.addOperation(databaseHelper, new WaitForWriteDescriptor(databaseHelper, this, bluetoothGattDescriptor));
        }
        BluetoothGattDescriptor bluetoothGattDescriptor3 = this.mAwaitingDescriptor;
        if (bluetoothGattDescriptor3 == bluetoothGattDescriptor || bluetoothGattDescriptor3 == bluetoothGattDescriptor2) {
            this.mAwaitingDescriptor = null;
            operationCompleted();
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void saveLogBulk(int i, List<BluetoothGattService> list) {
        if (list != null && !list.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (BluetoothGattService bluetoothGattService : list) {
                String string = this.mContext.getString(R.string.service_unknown);
                String uuid = bluetoothGattService.getUuid().toString();
                Cursor service = this.mDatabase.getService(bluetoothGattService.getUuid());
                try {
                    int i2 = 4;
                    if (service.moveToNext()) {
                        string = service.getString(4);
                        int i3 = service.getInt(3);
                        if (i3 > 0) {
                            uuid = String.format(Locale.US, "0x%04X", Integer.valueOf(i3));
                        }
                    }
                    service.close();
                    sb.append(this.mContext.getString(R.string.log_service, string, uuid));
                    for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                        String string2 = this.mContext.getString(R.string.characteristic_unknown);
                        String uuid2 = bluetoothGattCharacteristic.getUuid().toString();
                        String properties = ParserUtils.getProperties(bluetoothGattCharacteristic);
                        Cursor characteristic = this.mDatabase.getCharacteristic(bluetoothGattCharacteristic.getUuid());
                        try {
                            if (characteristic.moveToNext()) {
                                string2 = characteristic.getString(i2);
                                int i4 = characteristic.getInt(3);
                                if (i4 > 0) {
                                    uuid2 = String.format(Locale.US, "0x%04X", Integer.valueOf(i4));
                                }
                            }
                            characteristic.close();
                            sb.append(this.mContext.getString(R.string.log_characteristic, string2, properties, uuid2));
                            for (BluetoothGattDescriptor bluetoothGattDescriptor : bluetoothGattCharacteristic.getDescriptors()) {
                                String string3 = this.mContext.getString(R.string.descriptor_unknown);
                                String uuid3 = bluetoothGattDescriptor.getUuid().toString();
                                Cursor descriptor = this.mDatabase.getDescriptor(bluetoothGattDescriptor.getUuid());
                                try {
                                    if (descriptor.moveToNext()) {
                                        string3 = descriptor.getString(4);
                                        int i5 = descriptor.getInt(3);
                                        if (i5 > 0) {
                                            uuid3 = String.format(Locale.US, "0x%04X", Integer.valueOf(i5));
                                        }
                                    }
                                    descriptor.close();
                                    sb.append(this.mContext.getString(R.string.log_descriptor, string3, uuid3));
                                } catch (Throwable th) {
                                    descriptor.close();
                                    throw th;
                                }
                            }
                            i2 = 4;
                        } catch (Throwable th2) {
                            characteristic.close();
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    service.close();
                    throw th3;
                }
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            }
            saveLogBulk(i, sb.toString());
            return;
        }
        saveLogBulk(i, "No services found ");
    }
}
