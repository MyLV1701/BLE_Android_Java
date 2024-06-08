package no.nordicsemi.android.mcp.ble.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.settings.SettingsFragment;
import no.nordicsemi.android.mcp.util.ParcelableTimespan;

/* loaded from: classes.dex */
public abstract class BluetoothLeScannerCompat {
    public static final String EXTRA_SCANNING = "EXTRA_SCANNING";
    public static final String EXTRA_SCANNING_STARTED = "EXTRA_SCANNING_STARTED";
    public static final String EXTRA_WORKING_TIMESPANS = "EXTRA_WORKING_TIMESPANS";
    private static final long SCAN_INTERVAL = 2000;
    private BulkDevicesListener mBulkDevicesListener;
    private DatabaseHelper mDatabaseHelper;
    private DeviceListener mDeviceListener;
    protected SharedPreferences mPreferences;
    private boolean mScanning;
    private ScannerListener mScanningListener;
    private long mScanningStartTime;
    private ScheduledExecutorService mScheduler;
    private boolean mShouldStartScanning;
    private Runnable mStopAndStartScanTask;
    private Runnable mStopScanTask;
    private static final ArrayList<Device> mDevices = new ArrayList<>();
    private static final Map<String, Device> mDevicesMap = new HashMap();
    private static int counter = 0;
    private Runnable mRefreshTask = new Runnable() { // from class: no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat.2
        @Override // java.lang.Runnable
        public void run() {
            if (BluetoothLeScannerCompat.this.mBulkDevicesListener != null) {
                BluetoothLeScannerCompat.this.mBulkDevicesListener.onDevicesUpdated(BluetoothLeScannerCompat.mDevices, false);
            }
        }
    };
    private Runnable mRefreshTaskFromOtherThread = new Runnable() { // from class: no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat.3
        @Override // java.lang.Runnable
        public void run() {
            BluetoothLeScannerCompat.this.mHandler.post(BluetoothLeScannerCompat.this.mRefreshTask);
        }
    };
    protected BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private Handler mHandler = new Handler();
    private final ArrayList<ParcelableTimespan> mScannerWorkingTimespans = new ArrayList<>();

    /* loaded from: classes.dex */
    public interface BulkDevicesListener {
        void onDevicesUpdated(List<Device> list, boolean z);
    }

    /* loaded from: classes.dex */
    public interface DeviceListener {
        void onDeviceUpdated(Device device);
    }

    /* loaded from: classes.dex */
    public interface ScannerListener {
        void onScanningStarted();

        void onScanningStopped();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BluetoothLeScannerCompat(Context context) {
        this.mDatabaseHelper = new DatabaseHelper(context);
        this.mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static Device getDevices(int i) {
        return mDevices.get(i);
    }

    private long getScanningPeriod() {
        try {
            return Integer.parseInt(this.mPreferences.getString(SettingsFragment.SETTINGS_SCANNING_PERIOD, String.valueOf(45))) * 1000;
        } catch (NumberFormatException unused) {
            return 45000L;
        }
    }

    private void startBulkReporterThread(long j) {
        this.mScheduler = Executors.newSingleThreadScheduledExecutor();
        this.mScheduler.scheduleAtFixedRate(this.mRefreshTaskFromOtherThread, 400L, j, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startScanSpan() {
        synchronized (this.mScannerWorkingTimespans) {
            this.mScannerWorkingTimespans.add(new ParcelableTimespan().start());
        }
    }

    private void stopBulkReporterThread() {
        ScheduledExecutorService scheduledExecutorService = this.mScheduler;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            this.mScheduler = null;
            this.mHandler.postDelayed(this.mRefreshTask, 4000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopScanSpan() {
        synchronized (this.mScannerWorkingTimespans) {
            int size = this.mScannerWorkingTimespans.size() - 1;
            if (size > -1) {
                this.mScannerWorkingTimespans.get(size).stop();
            }
        }
    }

    public /* synthetic */ void a(int i) {
        if (i == counter) {
            stopLeScan();
        }
    }

    public void clearCache() {
        mDevices.clear();
        mDevicesMap.clear();
        synchronized (this.mScannerWorkingTimespans) {
            this.mScannerWorkingTimespans.clear();
        }
    }

    public ArrayList<ParcelableTimespan> getTimespans() {
        return this.mScannerWorkingTimespans;
    }

    public boolean isScanning() {
        return this.mScanning;
    }

    public void onCreate(Bundle bundle) {
        if (bundle == null) {
            mDevices.clear();
            mDevicesMap.clear();
            return;
        }
        BulkDevicesListener bulkDevicesListener = this.mBulkDevicesListener;
        if (bulkDevicesListener != null) {
            bulkDevicesListener.onDevicesUpdated(mDevices, true);
        }
        this.mShouldStartScanning = bundle.getBoolean(EXTRA_SCANNING);
        this.mScanningStartTime = bundle.getLong(EXTRA_SCANNING_STARTED);
        synchronized (this.mScannerWorkingTimespans) {
            this.mScannerWorkingTimespans.addAll(bundle.getParcelableArrayList(EXTRA_WORKING_TIMESPANS));
        }
    }

    public void onDestroy() {
        this.mDatabaseHelper = null;
        this.mBulkDevicesListener = null;
        this.mDeviceListener = null;
        this.mBulkDevicesListener = null;
        this.mHandler.removeCallbacks(this.mStopScanTask);
        Runnable runnable = this.mStopAndStartScanTask;
        if (runnable != null) {
            this.mHandler.removeCallbacks(runnable);
        }
        this.mStopScanTask = null;
        this.mRefreshTask = null;
        this.mRefreshTaskFromOtherThread = null;
        this.mHandler = null;
        this.mPreferences = null;
        this.mBluetoothAdapter = null;
        ScheduledExecutorService scheduledExecutorService = this.mScheduler;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            this.mScheduler = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onDeviceDiscovered(final BluetoothDevice bluetoothDevice, final int i, final byte[] bArr, final long j) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: no.nordicsemi.android.mcp.ble.scanner.c
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothLeScannerCompat.this.a(bluetoothDevice, bArr, i, j);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onDevicesDiscovered(final List<ScanResult> list) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: no.nordicsemi.android.mcp.ble.scanner.b
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothLeScannerCompat.this.a(list);
                }
            });
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean(EXTRA_SCANNING, this.mScanning);
        bundle.putLong(EXTRA_SCANNING_STARTED, this.mScanningStartTime);
        bundle.putParcelableArrayList(EXTRA_WORKING_TIMESPANS, this.mScannerWorkingTimespans);
    }

    public void onStart() {
        if (!this.mShouldStartScanning || this.mScanning) {
            return;
        }
        long scanningPeriod = getScanningPeriod();
        if (scanningPeriod > 0) {
            startLeScan(this.mScanningStartTime + scanningPeriod, true);
        } else {
            startLeScan(0L, true);
        }
    }

    public void onStop() {
        stopLeScan();
    }

    public void setBulkDevicesListener(BulkDevicesListener bulkDevicesListener) {
        this.mBulkDevicesListener = bulkDevicesListener;
    }

    public void setDeviceListener(DeviceListener deviceListener) {
        this.mDeviceListener = deviceListener;
    }

    public void setScannerListener(ScannerListener scannerListener) {
        this.mScanningListener = scannerListener;
    }

    public boolean startLeScan() {
        long scanningPeriod = getScanningPeriod();
        if (scanningPeriod > 0) {
            long uptimeMillis = SystemClock.uptimeMillis();
            this.mScanningStartTime = uptimeMillis;
            return startLeScan(uptimeMillis + scanningPeriod, false);
        }
        return startLeScan(0L, false);
    }

    protected abstract void startLeScanInternal();

    public void stopLeScan() {
        if (this.mScanning) {
            counter++;
            this.mScanning = false;
            this.mHandler.removeCallbacks(this.mStopScanTask);
            Runnable runnable = this.mStopAndStartScanTask;
            if (runnable != null) {
                this.mHandler.removeCallbacks(runnable);
            }
            this.mStopScanTask = null;
            this.mStopAndStartScanTask = null;
            try {
                stopLeScanInternal();
                stopScanSpan();
            } catch (Exception unused) {
            }
            Iterator<Device> it = mDevices.iterator();
            while (it.hasNext()) {
                it.next().getPacketsMetaData().get(0).last = true;
            }
            ScannerListener scannerListener = this.mScanningListener;
            if (scannerListener != null) {
                scannerListener.onScanningStopped();
            }
            BulkDevicesListener bulkDevicesListener = this.mBulkDevicesListener;
            if (bulkDevicesListener != null) {
                bulkDevicesListener.onDevicesUpdated(mDevices, false);
            }
            stopBulkReporterThread();
        }
    }

    protected abstract void stopLeScanInternal();

    public /* synthetic */ void a(BluetoothDevice bluetoothDevice, byte[] bArr, int i, long j) {
        if (this.mScanning) {
            String address = bluetoothDevice.getAddress();
            Device device = mDevicesMap.get(address);
            if (device == null) {
                device = new Device(bluetoothDevice, mDevicesMap.size());
                device.setUserName(this.mDatabaseHelper.getDeviceName(device));
                mDevices.add(device);
                mDevicesMap.put(address, device);
            }
            device.updateInfo(this.mDatabaseHelper, bArr, i, j);
            DeviceListener deviceListener = this.mDeviceListener;
            if (deviceListener != null) {
                deviceListener.onDeviceUpdated(device);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onDeviceDiscovered(final ScanResult scanResult) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: no.nordicsemi.android.mcp.ble.scanner.a
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothLeScannerCompat.this.a(scanResult);
                }
            });
        }
    }

    protected boolean startLeScan(long j, boolean z) {
        if ((!z && this.mScanning) || !this.mBluetoothAdapter.isEnabled()) {
            return false;
        }
        final int i = counter;
        if (j > 0) {
            Handler handler = this.mHandler;
            Runnable runnable = new Runnable() { // from class: no.nordicsemi.android.mcp.ble.scanner.d
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothLeScannerCompat.this.a(i);
                }
            };
            this.mStopScanTask = runnable;
            handler.postAtTime(runnable, j);
        }
        if (!this.mPreferences.getBoolean(SettingsFragment.SETTINGS_SCANNING_CONTINUOUS, true)) {
            Handler handler2 = this.mHandler;
            Runnable runnable2 = new Runnable() { // from class: no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat.1
                @Override // java.lang.Runnable
                public void run() {
                    if (i == BluetoothLeScannerCompat.counter) {
                        BluetoothLeScannerCompat.this.stopLeScanInternal();
                        BluetoothLeScannerCompat.this.stopScanSpan();
                        Iterator it = BluetoothLeScannerCompat.mDevices.iterator();
                        while (it.hasNext()) {
                            ((Device) it.next()).getPacketsMetaData().get(0).last = true;
                        }
                        BluetoothLeScannerCompat.this.startScanSpan();
                        BluetoothLeScannerCompat.this.startLeScanInternal();
                        if (BluetoothLeScannerCompat.this.mHandler != null) {
                            BluetoothLeScannerCompat.this.mHandler.postDelayed(this, BluetoothLeScannerCompat.SCAN_INTERVAL);
                        }
                    }
                }
            };
            this.mStopAndStartScanTask = runnable2;
            handler2.postDelayed(runnable2, SCAN_INTERVAL);
        }
        this.mScanning = true;
        startScanSpan();
        startBulkReporterThread(1000L);
        startLeScanInternal();
        ScannerListener scannerListener = this.mScanningListener;
        if (scannerListener != null) {
            scannerListener.onScanningStarted();
        }
        return true;
    }

    public /* synthetic */ void a(ScanResult scanResult) {
        if (this.mScanning) {
            BluetoothDevice device = scanResult.getDevice();
            String address = device.getAddress();
            Device device2 = mDevicesMap.get(address);
            if (device2 == null) {
                device2 = new Device(device, mDevicesMap.size());
                device2.setUserName(this.mDatabaseHelper.getDeviceName(device2));
                mDevices.add(device2);
                mDevicesMap.put(address, device2);
            }
            device2.updateInfo(this.mDatabaseHelper, scanResult);
            DeviceListener deviceListener = this.mDeviceListener;
            if (deviceListener != null) {
                deviceListener.onDeviceUpdated(device2);
            }
        }
    }

    public /* synthetic */ void a(List list) {
        if (this.mScanning) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ScanResult scanResult = (ScanResult) it.next();
                BluetoothDevice device = scanResult.getDevice();
                String address = device.getAddress();
                Device device2 = mDevicesMap.get(address);
                if (device2 == null) {
                    device2 = new Device(device, mDevicesMap.size());
                    device2.setUserName(this.mDatabaseHelper.getDeviceName(device2));
                    mDevices.add(device2);
                    mDevicesMap.put(address, device2);
                }
                device2.updateInfo(this.mDatabaseHelper, scanResult);
                DeviceListener deviceListener = this.mDeviceListener;
                if (deviceListener != null) {
                    deviceListener.onDeviceUpdated(device2);
                }
            }
        }
    }
}
