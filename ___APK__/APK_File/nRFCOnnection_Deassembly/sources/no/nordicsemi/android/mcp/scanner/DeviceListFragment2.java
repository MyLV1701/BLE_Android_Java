package no.nordicsemi.android.mcp.scanner;

import a.a.o.b;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.g;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.e;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import d.n;
import d.t.m;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.MainActivity;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.TabsAdapter;
import no.nordicsemi.android.mcp.ble.BluetoothLeService;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.fragment.AlertDialogFragment;
import no.nordicsemi.android.mcp.scanner.DeviceNameDialogFragment;
import no.nordicsemi.android.mcp.settings.SettingsFragment;
import no.nordicsemi.android.mcp.util.FileHelper;
import no.nordicsemi.android.mcp.util.LogProviderUtil;
import no.nordicsemi.android.mcp.util.NotificationHelper;
import no.nordicsemi.android.mcp.widget.SelectionListener;
import no.nordicsemi.android.mcp.widget.SlidingPaneLayout;
import no.nordicsemi.android.mcp.widget.ViewAnimator;
import no.nordicsemi.android.mcp.widget.scanner.DeviceView;
import no.nordicsemi.android.mcp.widget.scanner.FilterView;

/* loaded from: classes.dex */
public class DeviceListFragment2 extends Fragment implements BluetoothLeScannerCompat.ScannerListener, BluetoothLeScannerCompat.BulkDevicesListener, SlidingPaneLayout.PanelSlideListener, SelectionListener, ViewAnimator.ExpandCollapseListener, b.a, FilterView.FilterListener, DeviceView.OnFavoriteStateChangeListener, MainActivity.DrawerListener, SwipeRefreshLayout.j, TabsAdapter.TabListener, DeviceNameDialogFragment.DeviceNameListener {
    private static final String LOGGER_MARKET_URI = "market://details?id=no.nordicsemi.android.log";
    private static final int REQUEST_SAVE = 145;
    private static final int REQUEST_SAVE_CSV = 146;
    private static final int REQUEST_SAVE_EXCEL = 147;
    private static final String SEPARATOR = "\n-----------------------------\n\n";
    private static final String SIS_ACTION_MODE_ENABLED = "action_mode_enabled";
    private static final String TAG = "DeviceListFragment2";
    private a.a.o.b mActionMode;
    private DeviceListAdapter2 mAdapter;
    private DatabaseHelper mDatabaseHelper;
    private OnDeviceListChangedListener mDeviceListChangedListener;
    private TextView mEmptyView;
    private View mEnableLocationAlert;
    private Filter mFilter;
    private FilterView mFilterView;
    private int mFirstCompletelyVisiblePosition;
    private OnDeviceListClearedListener mOnDeviceListClearedListener;
    private boolean mPanelClosable;
    private float mPanelOffset;
    private boolean mPauseRefreshing;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private final List<Device> mDevices = new ArrayList();
    private final List<Device> mFilteredDevices = new ArrayList();
    private RecyclerView.s touchInterceptor = new RecyclerView.s() { // from class: no.nordicsemi.android.mcp.scanner.DeviceListFragment2.1
        @Override // androidx.recyclerview.widget.RecyclerView.s
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                DeviceListFragment2.this.mPauseRefreshing = true;
            } else if (action == 1 || action == 3 || action == 2) {
                DeviceListFragment2.this.mPauseRefreshing = false;
            }
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.s
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.s
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        }
    };
    private RecyclerView.t scrollListener = new RecyclerView.t() { // from class: no.nordicsemi.android.mcp.scanner.DeviceListFragment2.2
        private int dySum;
        private int dySumTotal;

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            boolean z = false;
            if (i != 1) {
                this.dySumTotal = 0;
            }
            if (i == 0) {
                SwipeRefreshLayout swipeRefreshLayout = DeviceListFragment2.this.mSwipeRefreshLayout;
                if (DeviceListFragment2.this.mFirstCompletelyVisiblePosition == 0 && (!DeviceListFragment2.this.mPanelClosable || DeviceListFragment2.this.mPanelOffset == 0.0f)) {
                    z = true;
                }
                swipeRefreshLayout.setEnabled(z);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int H = linearLayoutManager.H();
            int i3 = DeviceListFragment2.this.mFirstCompletelyVisiblePosition = linearLayoutManager.G();
            if (i3 != -1) {
                DeviceListFragment2.this.mFilterView.setRaised(i3 > 0);
            }
            DeviceListFragment2.this.mSwipeRefreshLayout.setEnabled(i3 == 0 && (!DeviceListFragment2.this.mPanelClosable || DeviceListFragment2.this.mPanelOffset == 0.0f) && this.dySumTotal == 0);
            this.dySumTotal += i2;
            if (i2 <= 0) {
                this.dySum = 0;
            }
            if (H == 0 || i2 < -12) {
                DeviceListFragment2.this.mFilterView.show();
                return;
            }
            if (H <= 0 || i2 <= 0) {
                return;
            }
            this.dySum += i2;
            if (this.dySum > 300) {
                DeviceListFragment2.this.mFilterView.hide(true);
            }
        }
    };
    private BroadcastReceiver mBondStateBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.scanner.DeviceListFragment2.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DeviceListFragment2.this.mAdapter.notifyDataSetChanged();
        }
    };
    private BroadcastReceiver mConnectionsChangedBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.scanner.DeviceListFragment2.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra(BluetoothLeService.EXTRA_ACTION, 0) == 0) {
                DeviceListFragment2.this.mAdapter.notifyDataSetChanged();
            }
        }
    };

    /* loaded from: classes.dex */
    public interface OnDeviceListChangedListener {
        void onFilteredDevicesChanged(List<Device> list);
    }

    /* loaded from: classes.dex */
    public interface OnDeviceListClearedListener {
        void onDevicesCleared();
    }

    private String getDevicesAsString(List<Device> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<Device> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            sb.append(SEPARATOR);
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 32);
        }
        return sb.toString();
    }

    private boolean isBleSupported() {
        return requireContext().getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    private void notifyDeviceListChanged() {
        OnDeviceListChangedListener onDeviceListChangedListener = this.mDeviceListChangedListener;
        if (onDeviceListChangedListener != null) {
            onDeviceListChangedListener.onFilteredDevicesChanged(this.mFilteredDevices);
        }
    }

    private void onDevicesCleared() {
        this.mOnDeviceListClearedListener.onDevicesCleared();
        int size = this.mFilteredDevices.size();
        this.mDevices.clear();
        this.mFilteredDevices.clear();
        this.mAdapter.clearDevices();
        this.mAdapter.notifyItemRangeRemoved(1, size);
        this.mEmptyView.setVisibility(0);
        a.a.o.b bVar = this.mActionMode;
        if (bVar != null) {
            bVar.a();
        }
    }

    private void refreshDevices(List<Device> list) {
        for (Device device : list) {
            device.clearLastPacketTime();
            device.setUserName(this.mDatabaseHelper.getDeviceName(device));
        }
    }

    private void saveGraph(int i) {
        ArrayList<Device> selectedDevices = this.mAdapter.getSelectedDevices();
        switch (i) {
            case R.id.action_save /* 2131296403 */:
                String format = String.format(Locale.US, "Log %1$tF %1$tT - Bluetooth LE Scanner.txt", Calendar.getInstance());
                if (Build.VERSION.SDK_INT >= 19) {
                    Intent intent = new Intent("android.intent.action.CREATE_DOCUMENT");
                    intent.addCategory("android.intent.category.OPENABLE");
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.TITLE", format);
                    startActivityForResult(intent, REQUEST_SAVE);
                    return;
                }
                try {
                    File file = new File(Environment.getExternalStorageDirectory(), FileHelper.NORDIC_FOLDER);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    File file2 = new File(file, format);
                    file2.createNewFile();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file2));
                    outputStreamWriter.append((CharSequence) getDevicesAsString(selectedDevices));
                    outputStreamWriter.close();
                    Uri contentUri = FileHelper.getContentUri(requireContext(), file2);
                    if (contentUri != null) {
                        FileHelper.showFileNotification(requireContext(), contentUri, "text/plain");
                    }
                } catch (Exception unused) {
                    Toast.makeText(getActivity(), R.string.file_save_error, 0).show();
                }
                a.a.o.b bVar = this.mActionMode;
                if (bVar != null) {
                    bVar.a();
                    return;
                }
                return;
            case R.id.action_save_graph_csv /* 2131296404 */:
                String format2 = String.format(Locale.US, "Graph %1$tF %1$tT.csv", Calendar.getInstance());
                if (Build.VERSION.SDK_INT >= 19) {
                    Intent intent2 = new Intent("android.intent.action.CREATE_DOCUMENT");
                    intent2.addCategory("android.intent.category.OPENABLE");
                    intent2.setType("text/csv");
                    intent2.putExtra("android.intent.extra.TITLE", format2);
                    startActivityForResult(intent2, REQUEST_SAVE_CSV);
                    return;
                }
                char charAt = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(SettingsFragment.SETTINGS_CSV_SEPARATOR, ",").charAt(0);
                try {
                    File file3 = new File(Environment.getExternalStorageDirectory(), FileHelper.NORDIC_FOLDER);
                    if (!file3.exists()) {
                        file3.mkdir();
                    }
                    File file4 = new File(file3, format2);
                    file4.createNewFile();
                    b.a.a.a.a aVar = new b.a.a.a.a(new OutputStreamWriter(new FileOutputStream(file4)), charAt);
                    ((ScannerFragment) getParentFragment()).exportToCsv(selectedDevices, aVar);
                    aVar.close();
                    Uri contentUri2 = FileHelper.getContentUri(requireContext(), file4);
                    if (contentUri2 != null) {
                        FileHelper.showFileNotification(requireContext(), contentUri2, "text/csv");
                    }
                } catch (Exception unused2) {
                    Toast.makeText(getActivity(), R.string.export_log_failed, 0).show();
                }
                a.a.o.b bVar2 = this.mActionMode;
                if (bVar2 != null) {
                    bVar2.a();
                    return;
                }
                return;
            case R.id.action_save_graph_excel /* 2131296405 */:
                String format3 = String.format(Locale.US, "Graph %1$tF %1$tT.xls", Calendar.getInstance());
                if (Build.VERSION.SDK_INT >= 19) {
                    Intent intent3 = new Intent("android.intent.action.CREATE_DOCUMENT");
                    intent3.addCategory("android.intent.category.OPENABLE");
                    intent3.setType("application/vnd.ms-excel");
                    intent3.putExtra("android.intent.extra.TITLE", format3);
                    startActivityForResult(intent3, REQUEST_SAVE_EXCEL);
                    return;
                }
                File file5 = new File(Environment.getExternalStorageDirectory(), FileHelper.NORDIC_FOLDER);
                if (!file5.exists()) {
                    file5.mkdir();
                }
                File file6 = new File(file5, format3);
                try {
                    m a2 = n.a(file6);
                    ((ScannerFragment) getParentFragment()).exportToExcel(selectedDevices, a2);
                    a2.b();
                    Uri contentUri3 = FileHelper.getContentUri(requireContext(), file6);
                    if (contentUri3 != null) {
                        FileHelper.showFileNotification(requireContext(), contentUri3, "application/vnd.ms-excel");
                    }
                } catch (Exception unused3) {
                    Toast.makeText(getActivity(), R.string.export_log_failed, 0).show();
                }
                a.a.o.b bVar3 = this.mActionMode;
                if (bVar3 != null) {
                    bVar3.a();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public /* synthetic */ void a(View view) {
        startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
    }

    public /* synthetic */ void b(View view) {
        AlertDialogFragment.getInstance(R.string.no_location_title, R.string.no_location_message).show(getChildFragmentManager(), (String) null);
    }

    @Override // no.nordicsemi.android.mcp.widget.scanner.FilterView.FilterListener
    public void filterBy(Filter filter) {
        this.mFilter = filter;
        this.mFilter.apply(requireContext(), this.mDatabaseHelper, this.mDevices, this.mFilteredDevices);
        this.mAdapter.setDevices(this.mFilteredDevices);
        this.mAdapter.notifyDataSetChanged();
        notifyDeviceListChanged();
        this.mEmptyView.setVisibility(this.mFilteredDevices.isEmpty() ? 0 : 8);
    }

    @Override // no.nordicsemi.android.mcp.widget.SelectionListener
    public boolean isActionModeEnabled() {
        return this.mActionMode != null;
    }

    @Override // a.a.o.b.a
    public boolean onActionItemClicked(a.a.o.b bVar, MenuItem menuItem) {
        ArrayList<Device> selectedDevices = this.mAdapter.getSelectedDevices();
        int itemId = menuItem.getItemId();
        LogSession logSession = null;
        switch (itemId) {
            case R.id.action_copy /* 2131296333 */:
                ((ClipboardManager) requireContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(String.format(Locale.US, "Log %1$tF %1$tT - Bluetooth LE Scanner", Calendar.getInstance()), getDevicesAsString(selectedDevices)));
                Toast.makeText(requireContext(), R.string.export_clipboard_success, 0).show();
                break;
            case R.id.action_edit /* 2131296346 */:
                Device device = selectedDevices.get(0);
                DeviceNameDialogFragment.getInstance(device.getAddress(null), this.mDatabaseHelper.getDeviceName(device)).show(getChildFragmentManager(), (String) null);
                return true;
            case R.id.action_log /* 2131296369 */:
                if (!LogProviderUtil.logProviderExists(requireContext())) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(LOGGER_MARKET_URI));
                    intent.setFlags(268435456);
                    startActivity(intent);
                    break;
                } else if (!selectedDevices.isEmpty()) {
                    Iterator<Device> it = selectedDevices.iterator();
                    while (it.hasNext()) {
                        Device next = it.next();
                        LogSession newSession = Logger.newSession(requireContext(), "BLE Scanner", next.getAddress(requireContext()), next.getName());
                        Logger.log(newSession, 5, next.toString());
                        logSession = newSession;
                    }
                    if (logSession == null) {
                        Toast.makeText(requireContext(), R.string.error_saving_log_failed, 1).show();
                        break;
                    } else {
                        Intent intent2 = new Intent("android.intent.action.VIEW", selectedDevices.size() > 1 ? logSession.getSessionsUri() : logSession.getSessionUri());
                        intent2.setFlags(268435456);
                        PendingIntent activity = PendingIntent.getActivity(getActivity(), 451, intent2, 134217728);
                        g.d dVar = new g.d(requireContext(), NotificationHelper.CHANNEL_FILES);
                        dVar.a(activity);
                        dVar.c(getText(R.string.export_log_success));
                        dVar.b(getText(R.string.export_log_open));
                        dVar.a(true);
                        dVar.e(true);
                        dVar.e(getText(R.string.export_log_success));
                        dVar.d(R.drawable.ic_stat_notify_log);
                        ((NotificationManager) requireContext().getSystemService("notification")).notify(1287, dVar.a());
                        break;
                    }
                }
                break;
            case R.id.action_save /* 2131296403 */:
            case R.id.action_save_graph_csv /* 2131296404 */:
            case R.id.action_save_graph_excel /* 2131296405 */:
                saveGraph(itemId);
                return true;
            case R.id.action_share /* 2131296412 */:
                Intent intent3 = new Intent("android.intent.action.SEND");
                intent3.setType("message/rfc822");
                intent3.putExtra("android.intent.extra.TEXT", getDevicesAsString(selectedDevices));
                intent3.putExtra("android.intent.extra.SUBJECT", String.format(Locale.US, "Log %1$tF %1$tT - Bluetooth LE Scanner", Calendar.getInstance()));
                startActivity(intent3);
                break;
            case R.id.action_timeline /* 2131296425 */:
                ScannerFragment scannerFragment = (ScannerFragment) getParentFragment();
                if (selectedDevices.size() > 1) {
                    scannerFragment.onShowTimeline(selectedDevices);
                } else {
                    scannerFragment.onShowDeviceDetails(selectedDevices.get(0));
                }
                return true;
            default:
                return false;
        }
        bVar.a();
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        Uri data = intent != null ? intent.getData() : null;
        if (i2 == -1 && data != null) {
            ArrayList<Device> selectedDevices = this.mAdapter.getSelectedDevices();
            switch (i) {
                case REQUEST_SAVE /* 145 */:
                    try {
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(requireContext().getContentResolver().openOutputStream(data));
                        outputStreamWriter.append((CharSequence) getDevicesAsString(selectedDevices));
                        outputStreamWriter.close();
                        Toast.makeText(requireContext(), R.string.file_saved, 0).show();
                    } catch (Exception e2) {
                        Log.e(TAG, "Saving file failed", e2);
                        Toast.makeText(requireContext(), R.string.file_save_error, 0).show();
                    }
                    a.a.o.b bVar = this.mActionMode;
                    if (bVar != null) {
                        bVar.a();
                        return;
                    }
                    return;
                case REQUEST_SAVE_CSV /* 146 */:
                    try {
                        b.a.a.a.a aVar = new b.a.a.a.a(new OutputStreamWriter(requireContext().getContentResolver().openOutputStream(data)), PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(SettingsFragment.SETTINGS_CSV_SEPARATOR, ",").charAt(0));
                        ((ScannerFragment) getParentFragment()).exportToCsv(selectedDevices, aVar);
                        aVar.close();
                    } catch (Exception e3) {
                        Log.e(TAG, "Saving CSV failed", e3);
                        Toast.makeText(requireContext(), R.string.file_save_error, 0).show();
                    }
                    a.a.o.b bVar2 = this.mActionMode;
                    if (bVar2 != null) {
                        bVar2.a();
                        return;
                    }
                    return;
                case REQUEST_SAVE_EXCEL /* 147 */:
                    try {
                        m a2 = n.a(requireContext().getContentResolver().openOutputStream(data));
                        ((ScannerFragment) getParentFragment()).exportToExcel(selectedDevices, a2);
                        a2.b();
                    } catch (Exception e4) {
                        Log.e(TAG, "Saving Excel failed", e4);
                        Toast.makeText(requireContext(), R.string.file_save_error, 0).show();
                    }
                    a.a.o.b bVar3 = this.mActionMode;
                    if (bVar3 != null) {
                        bVar3.a();
                        return;
                    }
                    return;
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            ((MainActivity) context).setDrawerListener(this);
            this.mOnDeviceListClearedListener = (OnDeviceListClearedListener) getParentFragment();
        } catch (ClassCastException unused) {
            throw new ClassCastException(context.toString() + " must be a MainActivity");
        }
    }

    @Override // no.nordicsemi.android.mcp.MainActivity.OnBackPressedListener
    public boolean onBackPressed() {
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDatabaseHelper = new DatabaseHelper(getActivity());
        a.l.a.a.a(requireContext()).a(this.mConnectionsChangedBroadcastReceiver, new IntentFilter(BluetoothLeService.ACTION_CONNECTIONS_CHANGED));
        requireContext().registerReceiver(this.mBondStateBroadcastReceiver, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
    }

    @Override // a.a.o.b.a
    public boolean onCreateActionMode(a.a.o.b bVar, Menu menu) {
        bVar.d().inflate(R.menu.selected_devices, menu);
        this.mActionMode = bVar;
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (requireParentFragment().isMenuVisible()) {
            menuInflater.inflate(R.menu.refresh, menu);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_device_list2, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.mDeviceListChangedListener = null;
        this.mOnDeviceListClearedListener = null;
        this.touchInterceptor = null;
        this.scrollListener = null;
        a.l.a.a.a(requireContext()).a(this.mConnectionsChangedBroadcastReceiver);
        this.mConnectionsChangedBroadcastReceiver = null;
        MainActivity mainActivity = (MainActivity) requireActivity();
        mainActivity.unregisterReceiver(this.mBondStateBroadcastReceiver);
        mainActivity.setDrawerListener(null);
        this.mBondStateBroadcastReceiver = null;
        super.onDestroy();
    }

    @Override // a.a.o.b.a
    public void onDestroyActionMode(a.a.o.b bVar) {
        this.mActionMode = null;
        DeviceListAdapter2 deviceListAdapter2 = this.mAdapter;
        if (deviceListAdapter2 != null) {
            deviceListAdapter2.clearSelections();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mSwipeRefreshLayout.setOnRefreshListener(null);
        this.mRecyclerView.clearOnScrollListeners();
        this.mRecyclerView.setLayoutManager(null);
        this.mRecyclerView = null;
        this.mAdapter = null;
        this.mFilterView.setFilterListener(null);
        this.mFilterView = null;
        this.mFilter = null;
        this.mEmptyView = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        this.mOnDeviceListClearedListener = null;
        super.onDetach();
    }

    @Override // no.nordicsemi.android.mcp.scanner.DeviceNameDialogFragment.DeviceNameListener
    public void onDeviceRenamed(String str, String str2) {
        this.mDatabaseHelper.setDeviceName(str, str2);
        for (Device device : this.mFilteredDevices) {
            if (str.equals(device.getAddress(null))) {
                device.setUserName(str2);
                return;
            }
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat.BulkDevicesListener
    public void onDevicesUpdated(List<Device> list, boolean z) {
        if (!((ScannerFragment) requireParentFragment()).isLocationEnabled() && !z && this.mDevices.size() < list.size()) {
            ((ScannerFragment) requireParentFragment()).markLocationServiceAsNotRequired();
            setLocationStateIndicator(true);
        }
        if (this.mPauseRefreshing || list.isEmpty()) {
            return;
        }
        this.mDevices.clear();
        this.mDevices.addAll(list);
        if (z) {
            refreshDevices(list);
        }
        int size = this.mFilteredDevices.size();
        Filter filter = this.mFilter;
        if (filter != null) {
            filter.apply(requireContext(), this.mDatabaseHelper, this.mDevices, this.mFilteredDevices);
        }
        int size2 = this.mFilteredDevices.size();
        this.mAdapter.setDevices(this.mFilteredDevices);
        if (size < size2) {
            this.mAdapter.notifyItemRangeInserted(size + 1, size2 - size);
            notifyDeviceListChanged();
        } else {
            this.mAdapter.notifyDataSetChanged();
        }
        this.mEmptyView.setVisibility(this.mFilteredDevices.isEmpty() ? 0 : 8);
    }

    @Override // no.nordicsemi.android.mcp.MainActivity.DrawerListener
    public void onDrawerSlide(View view, float f2) {
        this.mFilterView.setEnabled(f2 == 0.0f && this.mPanelOffset == 0.0f);
    }

    @Override // no.nordicsemi.android.mcp.widget.scanner.DeviceView.OnFavoriteStateChangeListener
    public void onFavoriteStateChanged(Device device, boolean z) {
        if (z) {
            this.mDatabaseHelper.setDeviceFavorite(device);
        } else {
            this.mDatabaseHelper.removeFavoriteDevice(device.getAddress(null));
        }
        if (z || !this.mFilter.isOnlyFavorites()) {
            return;
        }
        int indexOf = this.mFilteredDevices.indexOf(device);
        this.mFilteredDevices.remove(device);
        this.mAdapter.notifyItemRemoved(indexOf + 1);
        notifyDeviceListChanged();
        this.mEmptyView.setVisibility(this.mFilteredDevices.isEmpty() ? 0 : 8);
    }

    @Override // no.nordicsemi.android.mcp.widget.scanner.FilterView.FilterListener
    public void onFilterCollapsed() {
        this.mSwipeRefreshLayout.setEnabled(this.mFirstCompletelyVisiblePosition == 0);
        ((ScannerFragment) requireParentFragment()).setSliderEnabled(true);
    }

    @Override // no.nordicsemi.android.mcp.widget.scanner.FilterView.FilterListener
    public void onFilterExpanded() {
        this.mSwipeRefreshLayout.setEnabled(false);
        ((ScannerFragment) requireParentFragment()).setSliderEnabled(false);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_refresh) {
            return false;
        }
        onDevicesCleared();
        return true;
    }

    @Override // no.nordicsemi.android.mcp.widget.SlidingPaneLayout.PanelSlideListener
    public void onPanelClosed(View view) {
        this.mPanelOffset = 0.0f;
        this.mAdapter.setPanelOffset(0.0f);
        this.mFilterView.setEnabled(true);
        this.mSwipeRefreshLayout.setEnabled(this.mFirstCompletelyVisiblePosition == 0);
    }

    @Override // no.nordicsemi.android.mcp.widget.SlidingPaneLayout.PanelSlideListener
    public void onPanelOpened(View view) {
        this.mPanelOffset = 1.0f;
        this.mAdapter.setPanelOffset(1.0f);
        this.mSwipeRefreshLayout.setEnabled(false);
    }

    @Override // no.nordicsemi.android.mcp.widget.SlidingPaneLayout.PanelSlideListener
    public void onPanelSlide(View view, float f2) {
        this.mPanelOffset = f2;
        this.mAdapter.setPanelOffset(f2);
        boolean z = true;
        this.mFilterView.setEnabled(f2 == 0.0f);
        SwipeRefreshLayout swipeRefreshLayout = this.mSwipeRefreshLayout;
        if (this.mPanelClosable && f2 != 0.0f) {
            z = false;
        }
        swipeRefreshLayout.setEnabled(z);
    }

    @Override // a.a.o.b.a
    public boolean onPrepareActionMode(a.a.o.b bVar, Menu menu) {
        int selectedItemCount = this.mAdapter.getSelectedItemCount();
        bVar.b(String.valueOf(selectedItemCount));
        bVar.a((CharSequence) null);
        boolean z = false;
        Device device = this.mAdapter.getSelectedDevices().get(0);
        MenuItem findItem = menu.findItem(R.id.action_edit);
        if (selectedItemCount == 1 && !device.isAnonymous()) {
            z = true;
        }
        findItem.setVisible(z);
        return true;
    }

    @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.j
    public void onRefresh() {
        this.mSwipeRefreshLayout.setRefreshing(false);
        onDevicesCleared();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mAdapter.notifyDataSetChanged();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(SIS_ACTION_MODE_ENABLED, this.mActionMode != null);
        this.mAdapter.onSaveInstanceState(bundle);
    }

    @Override // no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat.ScannerListener
    public void onScanningStarted() {
    }

    @Override // no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat.ScannerListener
    public void onScanningStopped() {
    }

    @Override // no.nordicsemi.android.mcp.TabsAdapter.TabListener
    public void onTabClosed() {
        this.mFilterView.onTabClosed();
    }

    @Override // no.nordicsemi.android.mcp.TabsAdapter.TabListener
    public void onTabOpen() {
        this.mFilterView.onTabOpen();
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewAnimator.ExpandCollapseListener
    public void onViewCollapsed(int i) {
        int G = ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).G();
        if (G != -1) {
            this.mFilterView.setRaised(G > 0);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_to_refresh);
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(android.R.id.list);
        this.mRecyclerView = recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new androidx.recyclerview.widget.g(requireActivity(), 1));
        e eVar = new e();
        eVar.a(500L);
        recyclerView.setItemAnimator(eVar);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.addOnItemTouchListener(this.touchInterceptor);
        DeviceListAdapter2 deviceListAdapter2 = new DeviceListAdapter2(this.mDatabaseHelper, (DeviceView.DeviceListener) getParentFragment(), this, bundle);
        this.mAdapter = deviceListAdapter2;
        recyclerView.setAdapter(deviceListAdapter2);
        recyclerView.addOnScrollListener(this.scrollListener);
        this.mEmptyView = (TextView) view.findViewById(android.R.id.empty);
        this.mEnableLocationAlert = view.findViewById(R.id.location_disabled_pane);
        this.mEnableLocationAlert.findViewById(R.id.action_enable_location).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.scanner.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DeviceListFragment2.this.a(view2);
            }
        });
        this.mEnableLocationAlert.findViewById(R.id.action_more).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.scanner.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DeviceListFragment2.this.b(view2);
            }
        });
        if (!isBleSupported()) {
            this.mEmptyView.setText(R.string.no_ble);
            this.mEmptyView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.no_ble, 0, 0);
        }
        ScannerFragment scannerFragment = (ScannerFragment) getParentFragment();
        boolean isLocationEnabled = scannerFragment.isLocationEnabled();
        if (scannerFragment.isLocationServiceRequired() && !isLocationEnabled) {
            this.mEmptyView.setText(R.string.no_location);
            this.mEmptyView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.no_location, 0, 0);
            this.mEnableLocationAlert.setVisibility(0);
        } else {
            this.mEnableLocationAlert.setVisibility(8);
        }
        FilterView filterView = (FilterView) view.findViewById(R.id.filter_view);
        this.mFilterView = filterView;
        filterView.setFilterListener(this);
        setHasOptionsMenu(true);
        if (bundle == null || !bundle.getBoolean(SIS_ACTION_MODE_ENABLED)) {
            return;
        }
        onViewSelected();
    }

    @Override // no.nordicsemi.android.mcp.widget.SelectionListener
    public void onViewDeselected() {
        if (this.mActionMode != null) {
            if (this.mAdapter.getSelectedItemCount() == 0) {
                this.mActionMode.a();
            } else {
                this.mActionMode.i();
            }
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewAnimator.ExpandCollapseListener
    public void onViewExpanded(int i) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.smoothScrollToPosition(i);
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.SelectionListener
    public void onViewSelected() {
        a.a.o.b bVar = this.mActionMode;
        if (bVar == null) {
            ((androidx.appcompat.app.e) requireActivity()).startSupportActionMode(this);
        } else {
            bVar.i();
        }
    }

    @Override // no.nordicsemi.android.mcp.scanner.DeviceNameDialogFragment.DeviceNameListener
    public void refreshAndCloseActionMode() {
        DeviceListAdapter2 deviceListAdapter2 = this.mAdapter;
        if (deviceListAdapter2 != null) {
            deviceListAdapter2.notifyDataSetChanged();
        }
        a.a.o.b bVar = this.mActionMode;
        if (bVar != null) {
            bVar.a();
        }
    }

    public void setLocationStateIndicator(boolean z) {
        TextView textView = this.mEmptyView;
        if (textView != null) {
            if (z) {
                textView.setText(R.string.no_devices);
                this.mEmptyView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.no_devices, 0, 0);
                this.mEnableLocationAlert.setVisibility(8);
            } else {
                textView.setText(R.string.no_location);
                this.mEmptyView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.no_location, 0, 0);
                this.mEnableLocationAlert.setVisibility(0);
            }
        }
    }

    public void setOnDeviceListChangedListener(OnDeviceListChangedListener onDeviceListChangedListener) {
        this.mDeviceListChangedListener = onDeviceListChangedListener;
        if (onDeviceListChangedListener != null) {
            onDeviceListChangedListener.onFilteredDevicesChanged(this.mFilteredDevices);
        }
    }

    public void setPanelInitOptions(boolean z, boolean z2) {
        this.mPanelOffset = z ? 1.0f : 0.0f;
        this.mPanelClosable = z2;
        this.mAdapter.setPanelOffset(this.mPanelOffset);
        this.mAdapter.setPanelClosable(z2);
        this.mFilterView.setEnabled((z2 && z) ? false : true);
    }
}
