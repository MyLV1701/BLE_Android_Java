package no.nordicsemi.android.mcp.scanner;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.androidplot.xy.XYSeries;
import com.google.android.material.snackbar.Snackbar;
import d.t.e;
import d.t.f;
import d.t.i;
import d.t.l;
import d.t.m;
import java.util.ArrayList;
import java.util.List;
import no.nordicsemi.android.mcp.HasTabs;
import no.nordicsemi.android.mcp.MainActivity;
import no.nordicsemi.android.mcp.PermissionRationaleFragment;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.TabsAdapter;
import no.nordicsemi.android.mcp.TabsFragment;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat;
import no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompatProvider;
import no.nordicsemi.android.mcp.connection.SelectPreferredPhyDialogFragment;
import no.nordicsemi.android.mcp.fragment.ShowOnceDialogFragment;
import no.nordicsemi.android.mcp.scanner.DeviceListFragment2;
import no.nordicsemi.android.mcp.scanner.details.DeviceDetailsDialogActivity;
import no.nordicsemi.android.mcp.scanner.graph.RssiGraphFragment;
import no.nordicsemi.android.mcp.scanner.timeline.TimelineDialogActivity;
import no.nordicsemi.android.mcp.util.BondHelper;
import no.nordicsemi.android.mcp.widget.SlidingPaneLayout;
import no.nordicsemi.android.mcp.widget.scanner.DeviceView;

/* loaded from: classes.dex */
public class ScannerFragment extends Fragment implements BluetoothLeScannerCompat.BulkDevicesListener, BluetoothLeScannerCompat.ScannerListener, DeviceListFragment2.OnDeviceListClearedListener, DeviceView.DeviceListener, MainActivity.OnBackPressedListener, TabsAdapter.TabListener, PermissionRationaleFragment.PermissionDialogListener {
    private static final String PREFS_LOCATION_SERVICE_REQUIRED = "location_service_required";
    private static final int REQUEST_PERMISSION_REQ_CODE = 34;
    private static final String SIS_SLIDING_PANE_OPEN = "sliding_pane_open";
    private static final String TAG = "ScannerFragment";
    private BluetoothLeScannerCompat mBluetoothLeScanner;
    private DeviceListFragment2 mDeviceListFragment;
    private RssiGraphFragment mGraphFragment;
    private SlidingPaneLayout mSlider;
    private boolean mSlidingPaneOpen;
    private boolean mTabOpen;
    private BroadcastReceiver mLocationProviderChangedReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.scanner.ScannerFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean isLocationEnabled = ScannerFragment.this.isLocationEnabled();
            boolean isLocationServiceRequired = ScannerFragment.this.isLocationServiceRequired();
            ScannerFragment.this.requireActivity().invalidateOptionsMenu();
            if (ScannerFragment.this.mDeviceListFragment != null) {
                ScannerFragment.this.mDeviceListFragment.setLocationStateIndicator(isLocationServiceRequired && isLocationEnabled);
            }
        }
    };
    private BroadcastReceiver mBluetoothStateBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.scanner.ScannerFragment.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
            if ((intExtra == 10 || intExtra == 13) && ScannerFragment.this.mBluetoothLeScanner != null) {
                ScannerFragment.this.mBluetoothLeScanner.stopLeScan();
            }
            ScannerFragment.this.requireActivity().invalidateOptionsMenu();
        }
    };
    private SlidingPaneLayout.PanelSlideListener mSlideListener = new SlidingPaneLayout.PanelSlideListener() { // from class: no.nordicsemi.android.mcp.scanner.ScannerFragment.3
        @Override // no.nordicsemi.android.mcp.widget.SlidingPaneLayout.PanelSlideListener
        public void onPanelClosed(View view) {
            ScannerFragment.this.mSlidingPaneOpen = false;
            try {
                ScannerFragment.this.mDeviceListFragment.onPanelClosed(view);
                ScannerFragment.this.mGraphFragment.onPanelClosed(view);
            } catch (Exception unused) {
            }
        }

        @Override // no.nordicsemi.android.mcp.widget.SlidingPaneLayout.PanelSlideListener
        public void onPanelOpened(View view) {
            if (ScannerFragment.this.mSlider != null) {
                ScannerFragment.this.mSlidingPaneOpen = true;
            }
            try {
                ScannerFragment.this.mDeviceListFragment.onPanelOpened(view);
                ScannerFragment.this.mDeviceListFragment.onPanelSlide(view, 1.0f);
                ScannerFragment.this.mGraphFragment.onPanelOpened(view);
            } catch (Exception unused) {
            }
        }

        @Override // no.nordicsemi.android.mcp.widget.SlidingPaneLayout.PanelSlideListener
        public void onPanelSlide(View view, float f2) {
            ScannerFragment.this.mDeviceListFragment.onPanelSlide(view, f2);
        }
    };

    private void enableBle() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            defaultAdapter.enable();
        }
    }

    private boolean isBleEnabled() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    private boolean isBleSupported() {
        return requireContext().getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    private void startScan() {
        if (Build.VERSION.SDK_INT >= 23 && a.f.d.b.a(requireContext(), "android.permission.ACCESS_FINE_LOCATION") != 0) {
            if (androidx.core.app.a.a((Activity) requireActivity(), "android.permission.ACCESS_FINE_LOCATION")) {
                PermissionRationaleFragment.getInstance(R.string.rationale_location_message, "android.permission.ACCESS_FINE_LOCATION", 0).show(getChildFragmentManager(), (String) null);
                return;
            } else {
                onRequestPermission("android.permission.ACCESS_FINE_LOCATION", 0);
                return;
            }
        }
        this.mBluetoothLeScanner.startLeScan();
    }

    public /* synthetic */ void a(View view) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", requireContext().getPackageName(), null));
        intent.setFlags(268435456);
        startActivity(intent);
    }

    public void exportToCsv(List<Device> list, b.a.a.a.a aVar) {
        int size = list.size();
        String[] strArr = new String[size];
        String[] strArr2 = new String[size];
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Device device : list) {
            String name = device.getName();
            String address = device.getAddress(getContext());
            if (name == null) {
                name = "n/a";
            }
            strArr[i] = name;
            strArr2[i] = address;
            arrayList.add(this.mGraphFragment.getSeries(address));
            i++;
        }
        aVar.a(strArr);
        aVar.a(strArr2);
        aVar.a(arrayList);
    }

    public void exportToExcel(List<Device> list, m mVar) {
        Float y;
        l a2 = mVar.a("Data", 0);
        ArrayList<XYSeries> arrayList = new ArrayList();
        int i = 0;
        for (Device device : list) {
            String name = device.getName();
            String address = device.getAddress(getContext());
            if (name == null) {
                name = "n/a";
            }
            a2.a(new d.t.d(i, 0, name));
            a2.a(new d.t.d(i, 1, address));
            arrayList.add(this.mGraphFragment.getSeries(address));
            i++;
        }
        int i2 = 0;
        for (XYSeries xYSeries : arrayList) {
            if (i2 < xYSeries.size()) {
                i2 = xYSeries.size();
            }
        }
        i iVar = new i(f.f3211b);
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = 0;
            for (XYSeries xYSeries2 : arrayList) {
                if (xYSeries2.size() > i3 && (y = xYSeries2.getY(i3)) != null) {
                    a2.a(new e(i4, i3 + 2, y.floatValue(), iVar));
                }
                i4++;
            }
        }
        mVar.c();
    }

    @Override // no.nordicsemi.android.mcp.widget.scanner.DeviceView.DeviceListener
    public HasTabs getTabsAdapter() {
        return (TabsFragment) getParentFragment();
    }

    public boolean isLocationEnabled() {
        int i;
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        try {
            i = Settings.Secure.getInt(requireContext().getContentResolver(), "location_mode");
        } catch (Settings.SettingNotFoundException unused) {
            i = 0;
        }
        return i != 0;
    }

    public boolean isLocationServiceRequired() {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        return PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(PREFS_LOCATION_SERVICE_REQUIRED, true);
    }

    public void markLocationServiceAsNotRequired() {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean(PREFS_LOCATION_SERVICE_REQUIRED, false).apply();
    }

    @Override // no.nordicsemi.android.mcp.MainActivity.OnBackPressedListener
    public boolean onBackPressed() {
        SlidingPaneLayout slidingPaneLayout;
        if (!this.mSlidingPaneOpen || (slidingPaneLayout = this.mSlider) == null) {
            return false;
        }
        slidingPaneLayout.closePane();
        return true;
    }

    @Override // no.nordicsemi.android.mcp.widget.scanner.DeviceView.DeviceListener
    public void onBond(Device device) {
        this.mBluetoothLeScanner.stopLeScan();
        if (!isBleEnabled()) {
            Toast.makeText(getActivity(), R.string.ble_adapter_disabled, 0).show();
        } else {
            BondHelper.createBond(device.getBluetoothDevice());
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.scanner.DeviceView.DeviceListener
    public void onConnect(Device device, boolean z) {
        this.mBluetoothLeScanner.stopLeScan();
        if (!isBleEnabled()) {
            Toast.makeText(getActivity(), R.string.ble_adapter_disabled, 0).show();
        } else {
            ((TabsFragment) getParentFragment()).onDeviceSelected(device, z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT < 23 || !isBleSupported()) {
            return;
        }
        requireContext().registerReceiver(this.mLocationProviderChangedReceiver, new IntentFilter("android.location.MODE_CHANGED"));
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        BluetoothLeScannerCompat bluetoothLeScannerCompat = this.mBluetoothLeScanner;
        if (bluetoothLeScannerCompat == null) {
            return;
        }
        boolean isScanning = bluetoothLeScannerCompat.isScanning();
        menuInflater.inflate(isScanning ? R.menu.stop_scan : R.menu.start_scan, menu);
        boolean isBleEnabled = isBleEnabled();
        if (!isScanning) {
            menu.findItem(R.id.action_scan_start).setVisible(isBleEnabled);
        } else {
            menu.findItem(R.id.action_scan_stop).setVisible(true);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_scanner, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= 23 && isBleSupported()) {
            requireContext().unregisterReceiver(this.mLocationProviderChangedReceiver);
        }
        this.mLocationProviderChangedReceiver = null;
        BluetoothLeScannerCompat bluetoothLeScannerCompat = this.mBluetoothLeScanner;
        if (bluetoothLeScannerCompat == null) {
            return;
        }
        bluetoothLeScannerCompat.onDestroy();
        this.mBluetoothLeScanner = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (isBleSupported()) {
            requireContext().unregisterReceiver(this.mBluetoothStateBroadcastReceiver);
        }
        this.mBluetoothStateBroadcastReceiver = null;
        SlidingPaneLayout slidingPaneLayout = this.mSlider;
        if (slidingPaneLayout != null) {
            slidingPaneLayout.setPanelSlideListener(null);
            this.mSlider = null;
        }
        this.mDeviceListFragment.setOnDeviceListChangedListener(null);
        this.mDeviceListFragment = null;
        this.mGraphFragment = null;
    }

    @Override // no.nordicsemi.android.mcp.scanner.DeviceListFragment2.OnDeviceListClearedListener
    public void onDevicesCleared() {
        BluetoothLeScannerCompat bluetoothLeScannerCompat = this.mBluetoothLeScanner;
        if (bluetoothLeScannerCompat == null) {
            return;
        }
        bluetoothLeScannerCompat.clearCache();
        this.mGraphFragment.onDevicesCleared();
        if (isBleEnabled()) {
            startScan();
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat.BulkDevicesListener
    public void onDevicesUpdated(List<Device> list, boolean z) {
        this.mDeviceListFragment.onDevicesUpdated(list, z);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_scan_start /* 2131296406 */:
                if (!isBleEnabled()) {
                    enableBle();
                    return true;
                }
                startScan();
                return true;
            case R.id.action_scan_stop /* 2131296407 */:
                this.mBluetoothLeScanner.stopLeScan();
                return true;
            case R.id.action_show_graph /* 2131296413 */:
                this.mSlider.openPane();
                return true;
            default:
                return false;
        }
    }

    @Override // no.nordicsemi.android.mcp.PermissionRationaleFragment.PermissionDialogListener
    public void onRequestPermission(String str, int i) {
        requestPermissions(new String[]{str}, 34);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 34) {
            return;
        }
        if (iArr.length > 0 && iArr[0] == 0) {
            startScan();
            return;
        }
        Snackbar a2 = Snackbar.a(getView(), R.string.rationale_location_permission_denied, 0);
        a2.e(getResources().getColor(R.color.variant));
        a2.a(R.string.action_settings, new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.scanner.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScannerFragment.this.a(view);
            }
        });
        a2.k();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(SIS_SLIDING_PANE_OPEN, this.mSlidingPaneOpen);
        BluetoothLeScannerCompat bluetoothLeScannerCompat = this.mBluetoothLeScanner;
        if (bluetoothLeScannerCompat != null) {
            bluetoothLeScannerCompat.onSaveInstanceState(bundle);
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat.ScannerListener
    public void onScanningStarted() {
        this.mGraphFragment.onScanningStarted();
        this.mDeviceListFragment.onScanningStarted();
        requireActivity().invalidateOptionsMenu();
    }

    @Override // no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat.ScannerListener
    public void onScanningStopped() {
        try {
            this.mGraphFragment.onScanningStopped();
            this.mDeviceListFragment.onScanningStopped();
            requireActivity().invalidateOptionsMenu();
        } catch (Exception e2) {
            Log.e(TAG, "onScanningStopped: something's wrong", e2);
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.scanner.DeviceView.DeviceListener
    public void onSelectPreferredPhy(Device device) {
        this.mBluetoothLeScanner.stopLeScan();
        if (!isBleEnabled()) {
            Toast.makeText(getActivity(), R.string.ble_adapter_disabled, 0).show();
        } else {
            SelectPreferredPhyDialogFragment.getInstance(device).show(getChildFragmentManager(), (String) null);
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.scanner.DeviceView.DeviceListener
    public void onShowCloneInfo() {
        ShowOnceDialogFragment.getInstance(R.string.adv_alert_cloned_title, R.string.adv_alert_cloned_message, DeviceView.DeviceListener.PREFS_CLONE_INFO_SHOWN).show(getChildFragmentManager(), (String) null);
    }

    @Override // no.nordicsemi.android.mcp.widget.scanner.DeviceView.DeviceListener
    public void onShowDeviceDetails(Device device) {
        this.mBluetoothLeScanner.stopLeScan();
        Intent intent = new Intent(getActivity(), (Class<?>) DeviceDetailsDialogActivity.class);
        intent.putExtra(DeviceDetailsDialogActivity.DEVICE_INDEX, device.getDeviceIndex());
        intent.putParcelableArrayListExtra("timespans", this.mBluetoothLeScanner.getTimespans());
        requireActivity().startActivity(intent);
    }

    public void onShowTimeline(ArrayList<Device> arrayList) {
        this.mBluetoothLeScanner.stopLeScan();
        Intent intent = new Intent(getActivity(), (Class<?>) TimelineDialogActivity.class);
        int[] iArr = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            iArr[i] = arrayList.get(i).getDeviceIndex();
        }
        intent.putExtra(TimelineDialogActivity.DEVICES_INDEXES, iArr);
        intent.putParcelableArrayListExtra("timespans", this.mBluetoothLeScanner.getTimespans());
        requireActivity().startActivity(intent);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        BluetoothLeScannerCompat bluetoothLeScannerCompat = this.mBluetoothLeScanner;
        if (bluetoothLeScannerCompat != null) {
            bluetoothLeScannerCompat.onStart();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        BluetoothLeScannerCompat bluetoothLeScannerCompat = this.mBluetoothLeScanner;
        if (bluetoothLeScannerCompat != null) {
            bluetoothLeScannerCompat.onStop();
        }
    }

    @Override // no.nordicsemi.android.mcp.TabsAdapter.TabListener
    public void onTabClosed() {
        this.mTabOpen = false;
        BluetoothLeScannerCompat bluetoothLeScannerCompat = this.mBluetoothLeScanner;
        if (bluetoothLeScannerCompat != null) {
            bluetoothLeScannerCompat.stopLeScan();
        }
        DeviceListFragment2 deviceListFragment2 = this.mDeviceListFragment;
        if (deviceListFragment2 != null) {
            deviceListFragment2.onTabClosed();
        }
    }

    @Override // no.nordicsemi.android.mcp.TabsAdapter.TabListener
    public void onTabOpen() {
        this.mTabOpen = true;
        DeviceListFragment2 deviceListFragment2 = this.mDeviceListFragment;
        if (deviceListFragment2 != null) {
            deviceListFragment2.onTabOpen();
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.scanner.DeviceView.DeviceListener
    public void onUnbound(Device device) {
        this.mBluetoothLeScanner.stopLeScan();
        BondHelper.removeBond(device.getBluetoothDevice());
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        this.mGraphFragment = (RssiGraphFragment) getChildFragmentManager().a(R.id.graph_fragment);
        this.mDeviceListFragment = (DeviceListFragment2) getChildFragmentManager().a(R.id.device_list_fragment);
        this.mDeviceListFragment.setOnDeviceListChangedListener(this.mGraphFragment);
        if (this.mTabOpen) {
            this.mDeviceListFragment.onTabOpen();
        }
        if (bundle != null) {
            this.mSlidingPaneOpen = bundle.getBoolean(SIS_SLIDING_PANE_OPEN);
        }
        SlidingPaneLayout slidingPaneLayout = (SlidingPaneLayout) view.findViewById(R.id.slider);
        this.mSlider = slidingPaneLayout;
        if (slidingPaneLayout != null) {
            slidingPaneLayout.setPanelClosesOnMarginClick(false);
            slidingPaneLayout.setShadowResourceLeft(R.drawable.shadow);
            slidingPaneLayout.setSliderFadeColor(0);
            slidingPaneLayout.setPanelSlideListener(this.mSlideListener);
            if (this.mSlidingPaneOpen) {
                this.mSlider.openPane();
                this.mDeviceListFragment.setPanelInitOptions(true, true);
            } else {
                this.mDeviceListFragment.setPanelInitOptions(false, true);
            }
        } else {
            this.mDeviceListFragment.setPanelInitOptions(true, false);
            this.mGraphFragment.onPanelOpened(null);
        }
        setHasOptionsMenu(true);
        if (isBleSupported()) {
            this.mBluetoothLeScanner = BluetoothLeScannerCompatProvider.getBluetoothScanner(getActivity());
            this.mBluetoothLeScanner.setScannerListener(this);
            this.mBluetoothLeScanner.setDeviceListener(this.mGraphFragment);
            this.mBluetoothLeScanner.setBulkDevicesListener(this.mDeviceListFragment);
            this.mBluetoothLeScanner.onCreate(bundle);
            requireContext().registerReceiver(this.mBluetoothStateBroadcastReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
            if (bundle == null && isBleEnabled()) {
                if (Build.VERSION.SDK_INT < 23 || a.f.d.b.a(requireContext(), "android.permission.ACCESS_FINE_LOCATION") == 0) {
                    startScan();
                }
            }
        }
    }

    public void setSliderEnabled(boolean z) {
        SlidingPaneLayout slidingPaneLayout = this.mSlider;
        if (slidingPaneLayout != null) {
            slidingPaneLayout.setEnabled(z);
        }
    }

    public void onConnect(Device device, int i) {
        this.mBluetoothLeScanner.stopLeScan();
        if (!isBleEnabled()) {
            Toast.makeText(getActivity(), R.string.ble_adapter_disabled, 0).show();
        } else {
            ((TabsFragment) getParentFragment()).onDeviceSelected(device, i);
        }
    }
}
