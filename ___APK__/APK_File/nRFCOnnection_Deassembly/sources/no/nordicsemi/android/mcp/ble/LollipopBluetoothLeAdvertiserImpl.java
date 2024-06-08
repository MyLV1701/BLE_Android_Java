package no.nordicsemi.android.mcp.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import no.nordicsemi.android.mcp.ble.IBluetoothLeAdvertiser;
import no.nordicsemi.android.mcp.ble.LollipopBluetoothLeAdvertiserImpl;
import no.nordicsemi.android.mcp.ble.parser.AdvertisingDataParser;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class LollipopBluetoothLeAdvertiserImpl implements IBluetoothLeAdvertiser {
    private final SparseArray<AdvertiseCallbackWithTimeout> mAdvertiseCallbacks = new SparseArray<>();
    private final IBluetoothLeAdvertiser.AdvertisingStartedObserver mAdvertisingStartedObserver;
    private final IBluetoothLeAdvertiser.AdvertisingStoppedObserver mAdvertisingStoppedObserver;
    private final Context mContext;
    private final DatabaseHelper mDatabase;
    private final Handler mHandler;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class AdvertiseCallbackWithTimeout extends AdvertiseCallback {
        private AdvertisingStatusCallback callback;
        private long id;
        private int timeout;
        private Runnable timeoutRunnable;
        private int txPower;

        AdvertiseCallbackWithTimeout(long j, int i, int i2, AdvertisingStatusCallback advertisingStatusCallback) {
            this.txPower = -128;
            this.id = j;
            this.timeout = i;
            this.callback = advertisingStatusCallback;
            this.txPower = i2;
        }

        private String getError(int i) {
            if (i == 1) {
                return "Error 1: Advertisement data too large.";
            }
            if (i == 2) {
                return "Error 2: Too many advertisers.";
            }
            if (i == 3) {
                return "Error 3: Advertisement already started.";
            }
            if (i == 4) {
                return "Error 4: Internal error.";
            }
            if (i == 5) {
                return "Error 5: Feature unsupported.";
            }
            return "Unknown error (" + i + ")";
        }

        public /* synthetic */ void a() {
            this.timeoutRunnable = null;
            LollipopBluetoothLeAdvertiserImpl.this.stopAdvertisement(this.id);
        }

        @Override // android.bluetooth.le.AdvertiseCallback
        public void onStartFailure(int i) {
            this.callback.onAdvertisingFailed(getError(i));
        }

        @Override // android.bluetooth.le.AdvertiseCallback
        public void onStartSuccess(AdvertiseSettings advertiseSettings) {
            LollipopBluetoothLeAdvertiserImpl.this.mAdvertiseCallbacks.put((int) this.id, this);
            if (LollipopBluetoothLeAdvertiserImpl.this.mAdvertiseCallbacks.size() == 1) {
                LollipopBluetoothLeAdvertiserImpl.this.mAdvertisingStartedObserver.onAdvertisingStarted();
            }
            Intent intent = new Intent(IBluetoothLeAdvertiser.BROADCAST_ADVERTISING_STATE_CHANGED + this.id);
            intent.putExtra(IBluetoothLeAdvertiser.EXTRA_STATE, true);
            intent.putExtra(IBluetoothLeAdvertiser.EXTRA_TX_POWER, this.txPower);
            a.l.a.a.a(LollipopBluetoothLeAdvertiserImpl.this.mContext).a(intent);
            if (this.timeout > 0) {
                Handler handler = LollipopBluetoothLeAdvertiserImpl.this.mHandler;
                Runnable runnable = new Runnable() { // from class: no.nordicsemi.android.mcp.ble.k
                    @Override // java.lang.Runnable
                    public final void run() {
                        LollipopBluetoothLeAdvertiserImpl.AdvertiseCallbackWithTimeout.this.a();
                    }
                };
                this.timeoutRunnable = runnable;
                handler.postDelayed(runnable, this.timeout);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LollipopBluetoothLeAdvertiserImpl(Context context, DatabaseHelper databaseHelper, Handler handler, IBluetoothLeAdvertiser.AdvertisingStartedObserver advertisingStartedObserver, IBluetoothLeAdvertiser.AdvertisingStoppedObserver advertisingStoppedObserver) {
        this.mContext = context;
        this.mDatabase = databaseHelper;
        this.mHandler = handler;
        this.mAdvertisingStartedObserver = advertisingStartedObserver;
        this.mAdvertisingStoppedObserver = advertisingStoppedObserver;
    }

    public static int intervalToLollipopMode(int i) {
        if (i != 400) {
            return i != 1600 ? 2 : 0;
        }
        return 1;
    }

    public static int lollipopModeToInterval(int i) {
        if (i != 0) {
            return i != 1 ? 160 : 400;
        }
        return 1600;
    }

    public static int lollipopTxPowerTotxPowerLevel(int i) {
        if (i == 1) {
            return -15;
        }
        if (i != 2) {
            return i != 3 ? -21 : 1;
        }
        return -7;
    }

    public static int txPowerLevelToLollipopTxPower(int i) {
        if (i == -15) {
            return 1;
        }
        if (i != -7) {
            return i != 1 ? 0 : 3;
        }
        return 2;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeAdvertiser
    public int getAdvertisingTxPower(long j) {
        AdvertiseCallbackWithTimeout advertiseCallbackWithTimeout = this.mAdvertiseCallbacks.get((int) j);
        if (advertiseCallbackWithTimeout != null) {
            return advertiseCallbackWithTimeout.txPower;
        }
        return -128;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeAdvertiser
    public boolean isAdvertisementActive(long j) {
        return this.mAdvertiseCallbacks.get((int) j) != null;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeAdvertiser
    public boolean isAdvertising() {
        return this.mAdvertiseCallbacks.size() > 0;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeAdvertiser
    public void startAdvertisement(long j, int i, int i2, AdvertisingStatusCallback advertisingStatusCallback) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.isEnabled()) {
            BluetoothLeAdvertiser bluetoothLeAdvertiser = defaultAdapter.getBluetoothLeAdvertiser();
            if (bluetoothLeAdvertiser == null) {
                advertisingStatusCallback.onAdvertisingFailed("Advertising not supported.");
                return;
            }
            Cursor advertisingPacket = this.mDatabase.getAdvertisingPacket(j);
            try {
                try {
                    if (advertisingPacket.moveToNext()) {
                        int intervalToLollipopMode = intervalToLollipopMode(advertisingPacket.getInt(5));
                        int i3 = advertisingPacket.getInt(6);
                        int txPowerLevelToLollipopTxPower = txPowerLevelToLollipopTxPower(i3);
                        boolean z = true;
                        if (advertisingPacket.getInt(2) != 1) {
                            z = false;
                        }
                        byte[] blob = advertisingPacket.getBlob(11);
                        byte[] blob2 = advertisingPacket.getBlob(12);
                        AdvertiseSettings build = new AdvertiseSettings.Builder().setAdvertiseMode(intervalToLollipopMode).setTimeout(i).setTxPowerLevel(txPowerLevelToLollipopTxPower).setConnectable(z).build();
                        AdvertiseData.Builder builder = new AdvertiseData.Builder();
                        AdvertisingDataParser.parse(builder, blob);
                        AdvertiseData build2 = builder.build();
                        AdvertiseData advertiseData = null;
                        if (blob2 != null && blob2.length > 0) {
                            AdvertiseData.Builder builder2 = new AdvertiseData.Builder();
                            AdvertisingDataParser.parse(builder2, blob2);
                            advertiseData = builder2.build();
                        }
                        bluetoothLeAdvertiser.startAdvertising(build, build2, advertiseData, new AdvertiseCallbackWithTimeout(j, i, i3, advertisingStatusCallback));
                    } else {
                        advertisingStatusCallback.onAdvertisingFailed("Advertisement data not found");
                    }
                } catch (Exception unused) {
                    advertisingStatusCallback.onAdvertisingFailed("Starting advertising failed");
                }
                return;
            } finally {
                advertisingPacket.close();
            }
        }
        advertisingStatusCallback.onAdvertisingFailed("Bluetooth adapter disabled.");
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeAdvertiser
    public void stopAdvertisement(long j) {
        BluetoothLeAdvertiser bluetoothLeAdvertiser;
        if (isAdvertisementActive(j)) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null && (bluetoothLeAdvertiser = defaultAdapter.getBluetoothLeAdvertiser()) != null) {
                AdvertiseCallbackWithTimeout advertiseCallbackWithTimeout = this.mAdvertiseCallbacks.get((int) j);
                bluetoothLeAdvertiser.stopAdvertising(advertiseCallbackWithTimeout);
                if (advertiseCallbackWithTimeout.timeoutRunnable != null) {
                    this.mHandler.removeCallbacks(advertiseCallbackWithTimeout.timeoutRunnable);
                }
                Intent intent = new Intent(IBluetoothLeAdvertiser.BROADCAST_ADVERTISING_STATE_CHANGED + j);
                intent.putExtra(IBluetoothLeAdvertiser.EXTRA_STATE, false);
                a.l.a.a.a(this.mContext).a(intent);
            }
            this.mAdvertiseCallbacks.remove((int) j);
            if (this.mAdvertiseCallbacks.size() == 0) {
                this.mAdvertisingStoppedObserver.onAdvertisingStopped();
            }
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeAdvertiser
    public void stopAllAdvertisements() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            try {
                BluetoothLeAdvertiser bluetoothLeAdvertiser = defaultAdapter.getBluetoothLeAdvertiser();
                if (bluetoothLeAdvertiser != null) {
                    for (int i = 0; i < this.mAdvertiseCallbacks.size(); i++) {
                        bluetoothLeAdvertiser.stopAdvertising(this.mAdvertiseCallbacks.valueAt(i));
                    }
                }
            } catch (Exception e2) {
                Log.e("LollipopAdvertiser", "stopAllAdvertisements failed", e2);
            }
        }
        this.mAdvertiseCallbacks.clear();
        this.mAdvertisingStoppedObserver.onAdvertisingStopped();
    }
}
