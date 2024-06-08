package no.nordicsemi.android.mcp.ble.dfu.initiator;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelUuid;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.d;
import java.util.Iterator;
import java.util.UUID;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.dfu.initiator.ScannerFragment;

/* loaded from: classes.dex */
public class ScannerFragment extends androidx.fragment.app.c {
    private static final boolean DEVICE_IS_BONDED = true;
    private static final boolean DEVICE_NOT_BONDED = false;
    static final int NO_RSSI = -1000;
    private static final String PARAM_UUID = "param_uuid";
    private static final long SCAN_DURATION = 5000;
    private static final String TAG = "ScannerFragment";
    private DeviceListAdapter mAdapter;
    private BluetoothAdapter mBluetoothAdapter;
    private Handler mHandler = new Handler();
    private boolean mIsScanning = false;
    private BluetoothAdapter.LeScanCallback mLEScanCallback = new AnonymousClass1();
    private OnDeviceSelectedListener mListener;
    private Button mScanButton;
    private UUID mUuid;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: no.nordicsemi.android.mcp.ble.dfu.initiator.ScannerFragment$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements BluetoothAdapter.LeScanCallback {
        AnonymousClass1() {
        }

        public /* synthetic */ void a(BluetoothDevice bluetoothDevice, int i) {
            ScannerFragment.this.mAdapter.updateRssiOfBondedDevice(bluetoothDevice.getAddress(), i);
            ScannerFragment.this.mAdapter.addOrUpdateDevice(new ExtendedBluetoothDevice(bluetoothDevice, i, false));
        }

        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(final BluetoothDevice bluetoothDevice, final int i, byte[] bArr) {
            ScannerFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: no.nordicsemi.android.mcp.ble.dfu.initiator.a
                @Override // java.lang.Runnable
                public final void run() {
                    ScannerFragment.AnonymousClass1.this.a(bluetoothDevice, i);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public interface OnDeviceSelectedListener {
        void onDeviceSelected(BluetoothDevice bluetoothDevice);

        void onDialogCanceled();
    }

    private void addBondedDevices() {
        Iterator<BluetoothDevice> it = this.mBluetoothAdapter.getBondedDevices().iterator();
        while (it.hasNext()) {
            this.mAdapter.addBondedDevice(new ExtendedBluetoothDevice(it.next(), NO_RSSI, DEVICE_IS_BONDED));
        }
    }

    public static ScannerFragment getInstance(UUID uuid) {
        ScannerFragment scannerFragment = new ScannerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARAM_UUID, new ParcelUuid(uuid));
        scannerFragment.setArguments(bundle);
        return scannerFragment;
    }

    private void startScan() {
        this.mAdapter.clearDevices();
        this.mScanButton.setText(R.string.scanner_action_cancel);
        this.mBluetoothAdapter.startLeScan(this.mLEScanCallback);
        this.mIsScanning = DEVICE_IS_BONDED;
        this.mHandler.postDelayed(new Runnable() { // from class: no.nordicsemi.android.mcp.ble.dfu.initiator.c
            @Override // java.lang.Runnable
            public final void run() {
                ScannerFragment.this.a();
            }
        }, 5000L);
    }

    private void stopScan() {
        if (this.mIsScanning) {
            this.mScanButton.setText(R.string.scanner_action_scan);
            this.mBluetoothAdapter.stopLeScan(this.mLEScanCallback);
            this.mIsScanning = false;
        }
    }

    public /* synthetic */ void a(androidx.appcompat.app.d dVar, AdapterView adapterView, View view, int i, long j) {
        stopScan();
        dVar.dismiss();
        this.mListener.onDeviceSelected(((ExtendedBluetoothDevice) this.mAdapter.getItem(i)).device);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mListener = (OnDeviceSelectedListener) context;
        } catch (ClassCastException unused) {
            throw new ClassCastException(context.toString() + " must implement OnDeviceSelectedListener");
        }
    }

    @Override // androidx.fragment.app.c, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        this.mListener.onDialogCanceled();
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mUuid = ((ParcelUuid) getArguments().getParcelable(PARAM_UUID)).getUuid();
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        d.a aVar = new d.a(getActivity());
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_device_selection, (ViewGroup) null);
        ListView listView = (ListView) inflate.findViewById(android.R.id.list);
        listView.setEmptyView(inflate.findViewById(android.R.id.empty));
        DeviceListAdapter deviceListAdapter = new DeviceListAdapter(getActivity());
        this.mAdapter = deviceListAdapter;
        listView.setAdapter((ListAdapter) deviceListAdapter);
        aVar.c(R.string.scanner_title);
        aVar.b(inflate);
        final androidx.appcompat.app.d a2 = aVar.a();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: no.nordicsemi.android.mcp.ble.dfu.initiator.b
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                ScannerFragment.this.a(a2, adapterView, view, i, j);
            }
        });
        this.mScanButton = (Button) inflate.findViewById(R.id.action_cancel);
        this.mScanButton.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.ble.dfu.initiator.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScannerFragment.this.a(a2, view);
            }
        });
        addBondedDevices();
        if (bundle == null) {
            startScan();
        }
        return a2;
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onDestroyView() {
        stopScan();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onDetach() {
        this.mListener = null;
        super.onDetach();
    }

    public /* synthetic */ void a(androidx.appcompat.app.d dVar, View view) {
        if (view.getId() == R.id.action_cancel) {
            if (this.mIsScanning) {
                dVar.cancel();
            } else {
                startScan();
            }
        }
    }

    public /* synthetic */ void a() {
        if (this.mIsScanning) {
            stopScan();
        }
    }
}
