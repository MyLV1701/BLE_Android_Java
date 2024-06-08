package no.nordicsemi.android.mcp.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.AdvertisingSet;
import android.bluetooth.le.AdvertisingSetCallback;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import no.nordicsemi.android.mcp.ble.IBluetoothLeAdvertiser;
import no.nordicsemi.android.mcp.ble.OreoBluetoothLeAdvertiserImpl;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class OreoBluetoothLeAdvertiserImpl implements IBluetoothLeAdvertiser {
    private final SparseArray<AdvertisingSetCallbackWithTimeout> mAdvertiseCallbacks = new SparseArray<>();
    private final IBluetoothLeAdvertiser.AdvertisingStartedObserver mAdvertisingStartedObserver;
    private final IBluetoothLeAdvertiser.AdvertisingStoppedObserver mAdvertisingStoppedObserver;
    private final Context mContext;
    private final DatabaseHelper mDatabase;
    private final Handler mHandler;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class AdvertisingSetCallbackWithTimeout extends AdvertisingSetCallback {
        private AdvertisingStatusCallback callback;
        private long id;
        private int timeout;
        private Runnable timeoutRunnable;
        private int txPower = -128;

        AdvertisingSetCallbackWithTimeout(long j, int i, AdvertisingStatusCallback advertisingStatusCallback) {
            this.id = j;
            this.timeout = i;
            this.callback = advertisingStatusCallback;
        }

        public /* synthetic */ void a() {
            this.timeoutRunnable = null;
            OreoBluetoothLeAdvertiserImpl.this.stopAdvertisement(this.id);
        }

        @Override // android.bluetooth.le.AdvertisingSetCallback
        public void onAdvertisingEnabled(AdvertisingSet advertisingSet, boolean z, int i) {
            Log.d("OreoAdvertiser", "onAdvertisingEnabled (enable: " + z + ", status: " + i + ")");
            if (z) {
                return;
            }
            OreoBluetoothLeAdvertiserImpl.this.stopAdvertisement(this.id);
        }

        @Override // android.bluetooth.le.AdvertisingSetCallback
        public void onAdvertisingParametersUpdated(AdvertisingSet advertisingSet, int i, int i2) {
            Log.d("OreoAdvertiser", "onAdvertisingParametersUpdated (txPower: " + i + ", status: " + i2 + ")");
        }

        @Override // android.bluetooth.le.AdvertisingSetCallback
        public void onAdvertisingSetStarted(AdvertisingSet advertisingSet, int i, int i2) {
            if (i2 != 0) {
                if (i2 != 18) {
                    this.callback.onAdvertisingFailed("Advertising failed to start (error " + i2 + ")");
                    return;
                }
                this.callback.onAdvertisingFailed("Invalid advertising parameters");
                return;
            }
            this.txPower = i;
            OreoBluetoothLeAdvertiserImpl.this.mAdvertiseCallbacks.put((int) this.id, this);
            if (OreoBluetoothLeAdvertiserImpl.this.mAdvertiseCallbacks.size() == 1) {
                OreoBluetoothLeAdvertiserImpl.this.mAdvertisingStartedObserver.onAdvertisingStarted();
            }
            Intent intent = new Intent(IBluetoothLeAdvertiser.BROADCAST_ADVERTISING_STATE_CHANGED + this.id);
            intent.putExtra(IBluetoothLeAdvertiser.EXTRA_STATE, true);
            intent.putExtra(IBluetoothLeAdvertiser.EXTRA_TX_POWER, i);
            a.l.a.a.a(OreoBluetoothLeAdvertiserImpl.this.mContext).a(intent);
            if (this.timeout > 0) {
                Handler handler = OreoBluetoothLeAdvertiserImpl.this.mHandler;
                Runnable runnable = new Runnable() { // from class: no.nordicsemi.android.mcp.ble.l
                    @Override // java.lang.Runnable
                    public final void run() {
                        OreoBluetoothLeAdvertiserImpl.AdvertisingSetCallbackWithTimeout.this.a();
                    }
                };
                this.timeoutRunnable = runnable;
                handler.postDelayed(runnable, this.timeout);
            }
        }

        @Override // android.bluetooth.le.AdvertisingSetCallback
        public void onAdvertisingSetStopped(AdvertisingSet advertisingSet) {
            Intent intent = new Intent(IBluetoothLeAdvertiser.BROADCAST_ADVERTISING_STATE_CHANGED + this.id);
            intent.putExtra(IBluetoothLeAdvertiser.EXTRA_STATE, false);
            a.l.a.a.a(OreoBluetoothLeAdvertiserImpl.this.mContext).a(intent);
            OreoBluetoothLeAdvertiserImpl.this.mAdvertiseCallbacks.remove((int) this.id);
            if (OreoBluetoothLeAdvertiserImpl.this.mAdvertiseCallbacks.size() == 0) {
                OreoBluetoothLeAdvertiserImpl.this.mAdvertisingStoppedObserver.onAdvertisingStopped();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OreoBluetoothLeAdvertiserImpl(Context context, DatabaseHelper databaseHelper, Handler handler, IBluetoothLeAdvertiser.AdvertisingStartedObserver advertisingStartedObserver, IBluetoothLeAdvertiser.AdvertisingStoppedObserver advertisingStoppedObserver) {
        this.mContext = context;
        this.mDatabase = databaseHelper;
        this.mHandler = handler;
        this.mAdvertisingStartedObserver = advertisingStartedObserver;
        this.mAdvertisingStoppedObserver = advertisingStoppedObserver;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeAdvertiser
    public int getAdvertisingTxPower(long j) {
        AdvertisingSetCallbackWithTimeout advertisingSetCallbackWithTimeout = this.mAdvertiseCallbacks.get((int) j);
        if (advertisingSetCallbackWithTimeout != null) {
            return advertisingSetCallbackWithTimeout.txPower;
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x010c A[Catch: all -> 0x0186, TryCatch #4 {all -> 0x0186, blocks: (B:48:0x00c3, B:54:0x00e2, B:57:0x00e9, B:58:0x00fd, B:60:0x010c, B:62:0x010f, B:64:0x0120, B:66:0x0123, B:68:0x0134, B:71:0x0146), top: B:47:0x00c3 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0120 A[Catch: all -> 0x0186, TryCatch #4 {all -> 0x0186, blocks: (B:48:0x00c3, B:54:0x00e2, B:57:0x00e9, B:58:0x00fd, B:60:0x010c, B:62:0x010f, B:64:0x0120, B:66:0x0123, B:68:0x0134, B:71:0x0146), top: B:47:0x00c3 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0134 A[Catch: all -> 0x0186, TRY_LEAVE, TryCatch #4 {all -> 0x0186, blocks: (B:48:0x00c3, B:54:0x00e2, B:57:0x00e9, B:58:0x00fd, B:60:0x010c, B:62:0x010f, B:64:0x0120, B:66:0x0123, B:68:0x0134, B:71:0x0146), top: B:47:0x00c3 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:78:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r2v7, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r2v8 */
    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeAdvertiser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void startAdvertisement(long r21, int r23, int r24, no.nordicsemi.android.mcp.ble.AdvertisingStatusCallback r25) {
        /*
            Method dump skipped, instructions count: 474
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.ble.OreoBluetoothLeAdvertiserImpl.startAdvertisement(long, int, int, no.nordicsemi.android.mcp.ble.AdvertisingStatusCallback):void");
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeAdvertiser
    public void stopAdvertisement(long j) {
        BluetoothAdapter defaultAdapter;
        BluetoothLeAdvertiser bluetoothLeAdvertiser;
        if (!isAdvertisementActive(j) || (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null || (bluetoothLeAdvertiser = defaultAdapter.getBluetoothLeAdvertiser()) == null) {
            return;
        }
        AdvertisingSetCallbackWithTimeout advertisingSetCallbackWithTimeout = this.mAdvertiseCallbacks.get((int) j);
        bluetoothLeAdvertiser.stopAdvertisingSet(advertisingSetCallbackWithTimeout);
        if (advertisingSetCallbackWithTimeout.timeoutRunnable != null) {
            this.mHandler.removeCallbacks(advertisingSetCallbackWithTimeout.timeoutRunnable);
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeAdvertiser
    public void stopAllAdvertisements() {
        BluetoothLeAdvertiser bluetoothLeAdvertiser;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && (bluetoothLeAdvertiser = defaultAdapter.getBluetoothLeAdvertiser()) != null) {
            for (int i = 0; i < this.mAdvertiseCallbacks.size(); i++) {
                bluetoothLeAdvertiser.stopAdvertisingSet(this.mAdvertiseCallbacks.valueAt(i));
            }
        }
        this.mAdvertiseCallbacks.clear();
    }
}
