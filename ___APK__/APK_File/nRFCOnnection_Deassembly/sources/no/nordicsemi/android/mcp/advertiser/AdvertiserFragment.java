package no.nordicsemi.android.mcp.advertiser;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.TabsAdapter;
import no.nordicsemi.android.mcp.TabsFragment;
import no.nordicsemi.android.mcp.advertiser.PhoneNameDialogFragment;
import no.nordicsemi.android.mcp.ble.AdvertisingStatusCallback;
import no.nordicsemi.android.mcp.ble.BluetoothLeService;
import no.nordicsemi.android.mcp.ble.IBluetoothLeAdvertiser;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.widget.RemovableItemTouchHelperCallback;
import no.nordicsemi.android.mcp.widget.ViewAnimator;

/* loaded from: classes.dex */
public class AdvertiserFragment extends Fragment implements TabsAdapter.TabListener, ViewAnimator.ExpandCollapseListener, AdvertiserActionListener, PhoneNameDialogFragment.DeviceNameListener {
    public static final String PREFS_ADVERTISER_SUPPORTED = "advertiser_supported";
    private FloatingActionButton mActionAdd;
    private AdvertisementAdapter mAdapter;
    private RecyclerView mAdvertisementsView;
    private DatabaseHelper mDatabaseHelper;
    private TextView mEmptyView;
    private IBluetoothLeAdvertiser mService;
    private final BroadcastReceiver mBluetoothStateBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.1
        /* JADX WARN: Code restructure failed: missing block: B:5:0x0016, code lost:
        
            if (r0 != 13) goto L23;
         */
        @Override // android.content.BroadcastReceiver
        @android.annotation.TargetApi(21)
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onReceive(android.content.Context r3, android.content.Intent r4) {
            /*
                r2 = this;
                r3 = 10
                java.lang.String r0 = "android.bluetooth.adapter.extra.STATE"
                int r0 = r4.getIntExtra(r0, r3)
                java.lang.String r1 = "android.bluetooth.adapter.extra.PREVIOUS_STATE"
                int r4 = r4.getIntExtra(r1, r3)
                r1 = 12
                if (r0 == r3) goto L62
                if (r0 == r1) goto L19
                r3 = 13
                if (r0 == r3) goto L62
                goto L86
            L19:
                no.nordicsemi.android.mcp.advertiser.AdvertiserFragment r3 = no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.this
                android.content.Context r3 = r3.getContext()
                android.content.SharedPreferences r3 = android.preference.PreferenceManager.getDefaultSharedPreferences(r3)
                android.bluetooth.BluetoothAdapter r4 = android.bluetooth.BluetoothAdapter.getDefaultAdapter()
                android.bluetooth.le.BluetoothLeAdvertiser r4 = r4.getBluetoothLeAdvertiser()
                if (r4 == 0) goto L2f
                r4 = 1
                goto L30
            L2f:
                r4 = 0
            L30:
                android.content.SharedPreferences$Editor r3 = r3.edit()
                java.lang.String r0 = "advertiser_supported"
                android.content.SharedPreferences$Editor r3 = r3.putBoolean(r0, r4)
                r3.apply()
                if (r4 == 0) goto L55
                no.nordicsemi.android.mcp.advertiser.AdvertiserFragment r3 = no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.this
                com.google.android.material.floatingactionbutton.FloatingActionButton r3 = no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.access$000(r3)
                r3.e()
                no.nordicsemi.android.mcp.advertiser.AdvertiserFragment r3 = no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.this
                android.widget.TextView r3 = no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.access$100(r3)
                r4 = 2131821145(0x7f110259, float:1.9275025E38)
                r3.setText(r4)
                goto L86
            L55:
                no.nordicsemi.android.mcp.advertiser.AdvertiserFragment r3 = no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.this
                android.widget.TextView r3 = no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.access$100(r3)
                r4 = 2131820661(0x7f110075, float:1.9274043E38)
                r3.setText(r4)
                goto L86
            L62:
                if (r4 != r1) goto L86
                no.nordicsemi.android.mcp.advertiser.AdvertiserFragment r3 = no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.this
                no.nordicsemi.android.mcp.ble.IBluetoothLeAdvertiser r3 = no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.access$200(r3)
                if (r3 == 0) goto L75
                no.nordicsemi.android.mcp.advertiser.AdvertiserFragment r3 = no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.this
                no.nordicsemi.android.mcp.ble.IBluetoothLeAdvertiser r3 = no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.access$200(r3)
                r3.stopAllAdvertisements()
            L75:
                no.nordicsemi.android.mcp.advertiser.AdvertiserFragment r3 = no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.this
                no.nordicsemi.android.mcp.advertiser.AdvertisementAdapter r3 = no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.access$300(r3)
                if (r3 == 0) goto L86
                no.nordicsemi.android.mcp.advertiser.AdvertiserFragment r3 = no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.this
                no.nordicsemi.android.mcp.advertiser.AdvertisementAdapter r3 = no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.access$300(r3)
                r3.notifyDataSetChanged()
            L86:
                no.nordicsemi.android.mcp.advertiser.AdvertiserFragment r3 = no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.this
                androidx.fragment.app.d r3 = r3.getActivity()
                r3.invalidateOptionsMenu()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.AnonymousClass1.onReceive(android.content.Context, android.content.Intent):void");
        }
    };
    private ServiceConnection mServiceConnection = new ServiceConnection() { // from class: no.nordicsemi.android.mcp.advertiser.AdvertiserFragment.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            AdvertiserFragment.this.mService = (IBluetoothLeAdvertiser) iBinder;
            if (AdvertiserFragment.this.mAdapter != null) {
                AdvertiserFragment.this.mAdapter.setBluetoothLeAdvertiser(AdvertiserFragment.this.mService);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            AdvertiserFragment.this.mService = null;
            if (AdvertiserFragment.this.mAdapter != null) {
                AdvertiserFragment.this.mAdapter.setBluetoothLeAdvertiser(null);
            }
        }
    };

    public /* synthetic */ void a(BluetoothAdapter bluetoothAdapter, View view) {
        androidx.fragment.app.c editAdvertisementDialogFragment;
        if (Build.VERSION.SDK_INT >= 26) {
            if (bluetoothAdapter.isLeExtendedAdvertisingSupported()) {
                editAdvertisementDialogFragment = EditOreoExtendedAdvertisementDialogFragment.getInstance(0L);
            } else {
                editAdvertisementDialogFragment = EditOreoAdvertisementDialogFragment.getInstance(0L);
            }
        } else {
            editAdvertisementDialogFragment = EditAdvertisementDialogFragment.getInstance(0L);
        }
        editAdvertisementDialogFragment.show(getChildFragmentManager(), (String) null);
    }

    public void onAdvertisementEdited(long j) {
        int i;
        Cursor advertisingPackets = this.mDatabaseHelper.getAdvertisingPackets();
        while (true) {
            if (!advertisingPackets.moveToNext()) {
                i = -1;
                break;
            } else if (advertisingPackets.getLong(0) == j) {
                i = advertisingPackets.getPosition();
                break;
            }
        }
        this.mAdapter.changeCursor(advertisingPackets);
        if (j > 0) {
            this.mAdapter.notifyItemChanged(i);
        } else {
            this.mAdapter.notifyItemInserted(i);
            this.mEmptyView.setVisibility(8);
        }
    }

    @Override // no.nordicsemi.android.mcp.MainActivity.OnBackPressedListener
    public boolean onBackPressed() {
        return false;
    }

    @Override // no.nordicsemi.android.mcp.advertiser.AdvertiserActionListener
    public void onCloneAdvertisement(long j) {
        this.mDatabaseHelper.cloneAdvertisingPacket(j);
        this.mAdapter.changeCursor(this.mDatabaseHelper.getAdvertisingPackets());
        this.mAdapter.notifyItemInserted(r2.getItemCount() - 1);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 21) {
            this.mDatabaseHelper = new DatabaseHelper(getContext());
            getActivity().registerReceiver(this.mBluetoothStateBroadcastReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
            getContext().bindService(new Intent(getContext(), (Class<?>) BluetoothLeService.class), this.mServiceConnection, 0);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        int i = R.menu.advertiser_not_supported;
        if (defaultAdapter != null && Build.VERSION.SDK_INT >= 21) {
            if (defaultAdapter.getBluetoothLeAdvertiser() == null) {
                if (defaultAdapter.isEnabled()) {
                    menuInflater.inflate(R.menu.advertiser_not_supported, menu);
                    return;
                }
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                boolean contains = defaultSharedPreferences.contains(PREFS_ADVERTISER_SUPPORTED);
                boolean z = defaultSharedPreferences.getBoolean(PREFS_ADVERTISER_SUPPORTED, false);
                if (contains) {
                    if (z) {
                        i = R.menu.advertiser;
                    }
                    menuInflater.inflate(i, menu);
                    return;
                }
                return;
            }
            menuInflater.inflate(R.menu.advertiser, menu);
            MenuItem findItem = menu.findItem(R.id.action_disable_all);
            AdvertisementAdapter advertisementAdapter = this.mAdapter;
            findItem.setVisible(advertisementAdapter != null && advertisementAdapter.getItemCount() > 0);
            MenuItem findItem2 = menu.findItem(R.id.action_restore_popups);
            AdvertisementAdapter advertisementAdapter2 = this.mAdapter;
            findItem2.setVisible(advertisementAdapter2 != null && advertisementAdapter2.getItemCount() > 0);
            menu.findItem(R.id.action_rename_device).setVisible(defaultAdapter.isEnabled());
            return;
        }
        menuInflater.inflate(R.menu.advertiser_not_supported, menu);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_advertiser_list, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mDatabaseHelper = null;
        AdvertisementAdapter advertisementAdapter = this.mAdapter;
        if (advertisementAdapter != null) {
            advertisementAdapter.onDestroy();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getActivity().unregisterReceiver(this.mBluetoothStateBroadcastReceiver);
            getContext().unbindService(this.mServiceConnection);
        }
    }

    @Override // no.nordicsemi.android.mcp.advertiser.PhoneNameDialogFragment.DeviceNameListener
    public void onDeviceRenamed(String str) {
        AdvertisementAdapter advertisementAdapter = this.mAdapter;
        if (advertisementAdapter != null) {
            advertisementAdapter.notifyDataSetChanged();
        }
    }

    @Override // no.nordicsemi.android.mcp.advertiser.AdvertiserActionListener
    public void onEditAdvertisement(long j) {
        androidx.fragment.app.c editAdvertisementDialogFragment;
        if (Build.VERSION.SDK_INT >= 26) {
            if (BluetoothAdapter.getDefaultAdapter().isLeExtendedAdvertisingSupported()) {
                editAdvertisementDialogFragment = EditOreoExtendedAdvertisementDialogFragment.getInstance(j);
            } else {
                editAdvertisementDialogFragment = EditOreoAdvertisementDialogFragment.getInstance(j);
            }
        } else {
            editAdvertisementDialogFragment = EditAdvertisementDialogFragment.getInstance(j);
        }
        editAdvertisementDialogFragment.show(getChildFragmentManager(), (String) null);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_disable_all /* 2131296337 */:
                IBluetoothLeAdvertiser iBluetoothLeAdvertiser = this.mService;
                if (iBluetoothLeAdvertiser != null) {
                    iBluetoothLeAdvertiser.stopAllAdvertisements();
                }
                this.mAdapter.notifyDataSetChanged();
                return true;
            case R.id.action_disable_tab /* 2131296339 */:
                ((TabsFragment) getParentFragment()).disableAdvertiserFragment();
                return true;
            case R.id.action_rename_device /* 2131296398 */:
                PhoneNameDialogFragment.getInstance().show(getChildFragmentManager(), (String) null);
                return true;
            case R.id.action_restore_popups /* 2131296402 */:
                Cursor advertisingPackets = this.mDatabaseHelper.getAdvertisingPackets();
                try {
                    SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
                    while (advertisingPackets.moveToNext()) {
                        edit.remove(SetTimeoutDialogFragment.PREFS_DONT_SHOW_DIALOG_FOR_PACKET + advertisingPackets.getLong(0));
                    }
                    edit.apply();
                    return true;
                } finally {
                    advertisingPackets.close();
                }
            default:
                return false;
        }
    }

    @Override // no.nordicsemi.android.mcp.advertiser.AdvertiserActionListener
    public void onRemoveAdvertisement(long j, final int i) {
        IBluetoothLeAdvertiser iBluetoothLeAdvertiser = this.mService;
        if (iBluetoothLeAdvertiser != null) {
            iBluetoothLeAdvertiser.stopAdvertisement(j);
        }
        this.mDatabaseHelper.removeDeletedAdvertisingPackets();
        this.mDatabaseHelper.deleteAdvertisingPacket(j);
        this.mAdapter.changeCursor(this.mDatabaseHelper.getAdvertisingPackets());
        if (this.mAdapter.getItemCount() == 0) {
            this.mEmptyView.setVisibility(0);
        }
        getActivity().invalidateOptionsMenu();
        Snackbar a2 = Snackbar.a(this.mAdvertisementsView, R.string.adv_deleted, 0);
        a2.e(getResources().getColor(R.color.variant));
        a2.a(R.string.action_undo, new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdvertiserFragment.this.a(i, view);
            }
        });
        a2.k();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        AdvertisementAdapter advertisementAdapter = this.mAdapter;
        if (advertisementAdapter != null) {
            advertisementAdapter.notifyDataSetChanged();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        AdvertisementAdapter advertisementAdapter = this.mAdapter;
        if (advertisementAdapter != null) {
            advertisementAdapter.onSaveInstanceState(bundle);
        }
    }

    @Override // no.nordicsemi.android.mcp.advertiser.AdvertiserActionListener
    public void onStartAdvertisement(long j) {
        androidx.fragment.app.c setTimeoutDialogFragment;
        if (!SetTimeoutDialogFragment.shouldSkipDialog(getContext(), j)) {
            if (Build.VERSION.SDK_INT >= 26 && BluetoothAdapter.getDefaultAdapter().isLeExtendedAdvertisingSupported()) {
                setTimeoutDialogFragment = SetTimeoutAndMaxEventsDialogFragment.getInstance(j);
            } else {
                setTimeoutDialogFragment = SetTimeoutDialogFragment.getInstance(j);
            }
            setTimeoutDialogFragment.show(getChildFragmentManager(), (String) null);
            return;
        }
        onStartAdvertisement(j, SetTimeoutDialogFragment.getAdvertisementDuration(getContext(), j), SetTimeoutAndMaxEventsDialogFragment.getMaxExtendedAdvertisingEvents(getContext(), j));
    }

    @Override // no.nordicsemi.android.mcp.advertiser.AdvertiserActionListener
    public void onStopAdvertisement(long j) {
        IBluetoothLeAdvertiser iBluetoothLeAdvertiser = this.mService;
        if (iBluetoothLeAdvertiser != null) {
            iBluetoothLeAdvertiser.stopAdvertisement(j);
        }
    }

    @Override // no.nordicsemi.android.mcp.TabsAdapter.TabListener
    public void onTabClosed() {
    }

    @Override // no.nordicsemi.android.mcp.TabsAdapter.TabListener
    public void onTabOpen() {
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewAnimator.ExpandCollapseListener
    public void onViewCollapsed(int i) {
    }

    @Override // androidx.fragment.app.Fragment
    @SuppressLint({"NewApi"})
    public void onViewCreated(View view, Bundle bundle) {
        setHasOptionsMenu(true);
        if (Build.VERSION.SDK_INT >= 21) {
            this.mEmptyView = (TextView) view.findViewById(android.R.id.empty);
            this.mActionAdd = (FloatingActionButton) view.findViewById(R.id.fab);
            final BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null || defaultAdapter.getBluetoothLeAdvertiser() == null) {
                if (defaultAdapter != null && !defaultAdapter.isEnabled()) {
                    SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                    boolean contains = defaultSharedPreferences.contains(PREFS_ADVERTISER_SUPPORTED);
                    boolean z = defaultSharedPreferences.getBoolean(PREFS_ADVERTISER_SUPPORTED, false);
                    if (contains && !z) {
                        this.mEmptyView.setText(R.string.advertising_not_supported);
                        return;
                    } else if (!contains) {
                        this.mEmptyView.setText(R.string.ble_adapter_disabled);
                        return;
                    }
                } else {
                    this.mEmptyView.setText(R.string.advertising_not_supported);
                    return;
                }
            }
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean(PREFS_ADVERTISER_SUPPORTED, true).apply();
            this.mEmptyView.setText(R.string.no_advertising_packets);
            this.mActionAdd.e();
            this.mActionAdd.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AdvertiserFragment.this.a(defaultAdapter, view2);
                }
            });
            Cursor advertisingPackets = this.mDatabaseHelper.getAdvertisingPackets();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(android.R.id.list);
            this.mAdvertisementsView = recyclerView;
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new androidx.recyclerview.widget.e());
            recyclerView.addItemDecoration(new androidx.recyclerview.widget.g(requireContext(), 1));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            AdvertisementAdapter advertisementAdapter = new AdvertisementAdapter(advertisingPackets, this, this, bundle);
            this.mAdapter = advertisementAdapter;
            recyclerView.setAdapter(advertisementAdapter);
            new androidx.recyclerview.widget.j(new RemovableItemTouchHelperCallback(this.mAdapter)).a(recyclerView);
            this.mAdapter.setBluetoothLeAdvertiser(this.mService);
            if (advertisingPackets.getCount() > 0) {
                this.mEmptyView.setVisibility(8);
            }
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewAnimator.ExpandCollapseListener
    public void onViewExpanded(int i) {
        this.mAdvertisementsView.smoothScrollToPosition(i);
    }

    public /* synthetic */ void a(String str) {
        Toast.makeText(getContext(), str, 0).show();
    }

    public /* synthetic */ void a(int i, View view) {
        this.mDatabaseHelper.restoreDeletedAdvertisingPackets();
        this.mAdapter.changeCursor(this.mDatabaseHelper.getAdvertisingPackets());
        this.mAdapter.notifyItemInserted(i);
        this.mEmptyView.setVisibility(8);
        getActivity().invalidateOptionsMenu();
    }

    @Override // no.nordicsemi.android.mcp.advertiser.AdvertiserActionListener
    public void onStartAdvertisement(long j, int i, int i2) {
        IBluetoothLeAdvertiser iBluetoothLeAdvertiser = this.mService;
        if (iBluetoothLeAdvertiser != null) {
            iBluetoothLeAdvertiser.startAdvertisement(j, i, i2, new AdvertisingStatusCallback() { // from class: no.nordicsemi.android.mcp.advertiser.a
                @Override // no.nordicsemi.android.mcp.ble.AdvertisingStatusCallback
                public final void onAdvertisingFailed(String str) {
                    AdvertiserFragment.this.a(str);
                }
            });
        }
    }
}
