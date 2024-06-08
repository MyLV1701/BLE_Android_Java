package no.nordicsemi.android.mcp.bonded;

import a.a.o.b;
import a.l.a.a;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.g;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.e;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.HasTabs;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.TabsAdapter;
import no.nordicsemi.android.mcp.TabsFragment;
import no.nordicsemi.android.mcp.ble.BluetoothLeService;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.scanner.DeviceNameDialogFragment;
import no.nordicsemi.android.mcp.util.BondHelper;
import no.nordicsemi.android.mcp.util.LogProviderUtil;
import no.nordicsemi.android.mcp.util.NotificationHelper;
import no.nordicsemi.android.mcp.widget.SelectionListener;
import no.nordicsemi.android.mcp.widget.bonded.BondedDeviceView;

/* loaded from: classes.dex */
public class BondedDevicesListFragment extends Fragment implements TabsAdapter.TabListener, SelectionListener, BondedDeviceView.BondedDeviceListener, b.a, DeviceNameDialogFragment.DeviceNameListener {
    private static final String LOGGER_MARKET_URI = "market://details?id=no.nordicsemi.android.log";
    private static final String SEPARATOR = "\n-----------------------------\n\n";
    private static final String SIS_ACTION_MODE_ENABLED = "action_mode_enabled";
    private b mActionMode;
    private BondedDevicesAdapter mAdapter;
    private List<Device> mBondedDevices;
    private DatabaseHelper mDatabaseHelper;
    private TextView mEmptyView;
    private boolean mTabOpen;
    private final BroadcastReceiver mBondStateBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int i;
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            int type = bluetoothDevice.getType();
            if (type == 2 || type == 3) {
                int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", 10);
                if (intExtra == 10) {
                    ListIterator listIterator = BondedDevicesListFragment.this.mBondedDevices.listIterator();
                    while (true) {
                        if (!listIterator.hasNext()) {
                            i = -1;
                            break;
                        } else if (((Device) listIterator.next()).getAddress(null).equals(bluetoothDevice.getAddress())) {
                            i = listIterator.previousIndex();
                            break;
                        }
                    }
                    if (i > -1) {
                        BondedDevicesListFragment.this.mBondedDevices.remove(i);
                        BondedDevicesListFragment.this.mAdapter.notifyItemRemoved(i);
                    }
                } else if (intExtra == 12) {
                    int size = BondedDevicesListFragment.this.mBondedDevices.size();
                    BondedDevicesListFragment.this.mBondedDevices.add(new Device(bluetoothDevice));
                    BondedDevicesListFragment.this.mAdapter.notifyItemInserted(size);
                }
                BondedDevicesListFragment.this.mEmptyView.setVisibility(BondedDevicesListFragment.this.mBondedDevices.isEmpty() ? 0 : 8);
            }
        }
    };
    private final BroadcastReceiver mBluetoothStateBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment.2
        /* JADX WARN: Code restructure failed: missing block: B:10:0x0019, code lost:
        
            if (r3 != 13) goto L16;
         */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onReceive(android.content.Context r2, android.content.Intent r3) {
            /*
                r1 = this;
                no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment r2 = no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment.this
                boolean r2 = no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment.access$300(r2)
                if (r2 != 0) goto L9
                return
            L9:
                r2 = 10
                java.lang.String r0 = "android.bluetooth.adapter.extra.STATE"
                int r3 = r3.getIntExtra(r0, r2)
                if (r3 == r2) goto L22
                r2 = 12
                if (r3 == r2) goto L1c
                r2 = 13
                if (r3 == r2) goto L22
                goto L3c
            L1c:
                no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment r2 = no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment.this
                no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment.access$400(r2)
                goto L3c
            L22:
                no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment r2 = no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment.this
                java.util.List r2 = no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment.access$000(r2)
                r2.clear()
                no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment r2 = no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment.this
                no.nordicsemi.android.mcp.bonded.BondedDevicesAdapter r2 = no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment.access$100(r2)
                if (r2 == 0) goto L3c
                no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment r2 = no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment.this
                no.nordicsemi.android.mcp.bonded.BondedDevicesAdapter r2 = no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment.access$100(r2)
                r2.notifyDataSetChanged()
            L3c:
                no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment r2 = no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment.this
                androidx.fragment.app.d r2 = r2.requireActivity()
                r2.invalidateOptionsMenu()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment.AnonymousClass2.onReceive(android.content.Context, android.content.Intent):void");
        }
    };
    private BroadcastReceiver mConnectionsChangedBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra(BluetoothLeService.EXTRA_ACTION, 0) == 0) {
                BondedDevicesListFragment.this.mAdapter.notifyDataSetChanged();
            }
        }
    };

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

    private boolean isBleEnabled() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refresh() {
        this.mBondedDevices.clear();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            return;
        }
        for (BluetoothDevice bluetoothDevice : defaultAdapter.getBondedDevices()) {
            int type = bluetoothDevice.getType();
            if (type == 2 || type == 3) {
                Device device = new Device(bluetoothDevice);
                device.setUserName(this.mDatabaseHelper.getDeviceName(device));
                this.mBondedDevices.add(device);
            }
        }
        BondedDevicesAdapter bondedDevicesAdapter = this.mAdapter;
        if (bondedDevicesAdapter != null) {
            bondedDevicesAdapter.notifyDataSetChanged();
        }
        TextView textView = this.mEmptyView;
        if (textView != null) {
            textView.setVisibility(this.mBondedDevices.isEmpty() ? 0 : 8);
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.bonded.BondedDeviceView.BondedDeviceListener
    public HasTabs getTabsAdapter() {
        return (TabsFragment) getParentFragment();
    }

    @Override // no.nordicsemi.android.mcp.widget.SelectionListener
    public boolean isActionModeEnabled() {
        return this.mActionMode != null;
    }

    @Override // a.a.o.b.a
    public boolean onActionItemClicked(b bVar, MenuItem menuItem) {
        List<Device> selectedDevices = this.mAdapter.getSelectedDevices();
        LogSession logSession = null;
        switch (menuItem.getItemId()) {
            case R.id.action_copy /* 2131296333 */:
                ClipboardManager clipboardManager = (ClipboardManager) requireContext().getSystemService("clipboard");
                ClipData newPlainText = ClipData.newPlainText(String.format(Locale.US, "Log %1$tF %1$tT - Bluetooth LE Scanner", Calendar.getInstance()), getDevicesAsString(selectedDevices));
                if (clipboardManager != null) {
                    clipboardManager.setPrimaryClip(newPlainText);
                }
                Toast.makeText(getActivity(), R.string.export_clipboard_success, 0).show();
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
                    for (Device device2 : selectedDevices) {
                        LogSession newSession = Logger.newSession(requireContext(), "BLE Scanner", device2.getAddress(getContext()), device2.getName());
                        Logger.log(newSession, 5, device2.toString());
                        logSession = newSession;
                    }
                    Intent intent2 = new Intent("android.intent.action.VIEW", selectedDevices.size() > 1 ? logSession.getSessionsUri() : logSession.getSessionUri());
                    intent2.setFlags(268435456);
                    PendingIntent activity = PendingIntent.getActivity(requireActivity(), 451, intent2, 134217728);
                    g.d dVar = new g.d(requireContext(), NotificationHelper.CHANNEL_FILES);
                    dVar.a(activity);
                    dVar.c(getText(R.string.export_log_success));
                    dVar.b(getText(R.string.export_log_open));
                    dVar.a(true);
                    dVar.e(true);
                    dVar.e(getText(R.string.export_log_success));
                    dVar.d(R.drawable.ic_stat_notify_log);
                    Notification a2 = dVar.a();
                    NotificationManager notificationManager = (NotificationManager) requireContext().getSystemService("notification");
                    if (notificationManager != null) {
                        notificationManager.notify(1287, a2);
                        break;
                    }
                }
                break;
            case R.id.action_share /* 2131296412 */:
                Intent intent3 = new Intent("android.intent.action.SEND");
                intent3.setType("message/rfc822");
                intent3.putExtra("android.intent.extra.TEXT", getDevicesAsString(selectedDevices));
                intent3.putExtra("android.intent.extra.SUBJECT", String.format(Locale.US, "Log %1$tF %1$tT - Bluetooth LE Scanner", Calendar.getInstance()));
                startActivity(intent3);
                break;
        }
        bVar.a();
        return true;
    }

    @Override // no.nordicsemi.android.mcp.MainActivity.OnBackPressedListener
    public boolean onBackPressed() {
        return false;
    }

    @Override // no.nordicsemi.android.mcp.widget.bonded.BondedDeviceView.BondedDeviceListener
    public void onConnect(View view, Device device, boolean z) {
        if (!isBleEnabled()) {
            Toast.makeText(getActivity(), R.string.ble_adapter_disabled, 0).show();
            return;
        }
        TabsFragment tabsFragment = (TabsFragment) getParentFragment();
        if (tabsFragment != null) {
            tabsFragment.onDeviceSelected(device, z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDatabaseHelper = new DatabaseHelper(getActivity());
        this.mBondedDevices = new ArrayList();
        requireActivity().registerReceiver(this.mBluetoothStateBroadcastReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        a.a(requireActivity()).a(this.mConnectionsChangedBroadcastReceiver, new IntentFilter(BluetoothLeService.ACTION_CONNECTIONS_CHANGED));
        if (this.mTabOpen) {
            onTabOpen();
        }
    }

    @Override // a.a.o.b.a
    public boolean onCreateActionMode(b bVar, Menu menu) {
        bVar.d().inflate(R.menu.selected_bonded_devices, menu);
        this.mActionMode = bVar;
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_bonded_list, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        requireActivity().unregisterReceiver(this.mBluetoothStateBroadcastReceiver);
        a.a(requireActivity()).a(this.mConnectionsChangedBroadcastReceiver);
    }

    @Override // a.a.o.b.a
    public void onDestroyActionMode(b bVar) {
        this.mActionMode = null;
        this.mAdapter.clearSelections();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override // no.nordicsemi.android.mcp.scanner.DeviceNameDialogFragment.DeviceNameListener
    public void onDeviceRenamed(String str, String str2) {
        this.mDatabaseHelper.setDeviceName(str, str2);
        for (Device device : this.mBondedDevices) {
            if (str.equals(device.getAddress(null))) {
                device.setUserName(str2);
                return;
            }
        }
    }

    @Override // a.a.o.b.a
    public boolean onPrepareActionMode(b bVar, Menu menu) {
        int selectedItemCount = this.mAdapter.getSelectedItemCount();
        bVar.b(String.valueOf(selectedItemCount));
        menu.findItem(R.id.action_edit).setVisible(selectedItemCount == 1);
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(SIS_ACTION_MODE_ENABLED, this.mActionMode != null);
        this.mAdapter.onSaveInstanceState(bundle);
    }

    @Override // no.nordicsemi.android.mcp.TabsAdapter.TabListener
    public void onTabClosed() {
        this.mTabOpen = false;
        requireContext().unregisterReceiver(this.mBondStateBroadcastReceiver);
        this.mBondedDevices.clear();
    }

    @Override // no.nordicsemi.android.mcp.TabsAdapter.TabListener
    public void onTabOpen() {
        this.mTabOpen = true;
        if (getContext() != null) {
            refresh();
            getContext().registerReceiver(this.mBondStateBroadcastReceiver, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.bonded.BondedDeviceView.BondedDeviceListener
    public void onUnbound(Device device) {
        BondHelper.removeBond(device.getBluetoothDevice());
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(android.R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new androidx.recyclerview.widget.g(requireActivity(), 1));
        e eVar = new e();
        eVar.a(500L);
        recyclerView.setItemAnimator(eVar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        BondedDevicesAdapter bondedDevicesAdapter = new BondedDevicesAdapter(this, this, bundle);
        this.mAdapter = bondedDevicesAdapter;
        recyclerView.setAdapter(bondedDevicesAdapter);
        this.mAdapter.setBondedDevices(this.mBondedDevices);
        this.mEmptyView = (TextView) view.findViewById(android.R.id.empty);
        this.mEmptyView.setVisibility(this.mBondedDevices.isEmpty() ? 0 : 8);
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

    @Override // no.nordicsemi.android.mcp.widget.SelectionListener
    public void onViewSelected() {
        b bVar = this.mActionMode;
        if (bVar == null) {
            androidx.appcompat.app.e eVar = (androidx.appcompat.app.e) getActivity();
            if (eVar != null) {
                eVar.startSupportActionMode(this);
                return;
            }
            return;
        }
        bVar.i();
    }

    @Override // no.nordicsemi.android.mcp.scanner.DeviceNameDialogFragment.DeviceNameListener
    public void refreshAndCloseActionMode() {
        this.mAdapter.notifyDataSetChanged();
        b bVar = this.mActionMode;
        if (bVar != null) {
            bVar.a();
        }
    }
}
