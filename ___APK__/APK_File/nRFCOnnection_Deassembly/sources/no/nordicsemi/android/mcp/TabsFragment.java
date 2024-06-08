package no.nordicsemi.android.mcp;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import no.nordicsemi.android.mcp.MainActivity;
import no.nordicsemi.android.mcp.ble.BluetoothLeService;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.ble.IBluetoothLeService;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.fragment.SaveLogSessionFragment;
import no.nordicsemi.android.mcp.fragment.StillConnectedFragment;
import no.nordicsemi.android.mcp.log.LocalLogContract;
import no.nordicsemi.android.mcp.settings.SettingsFragment;
import no.nordicsemi.android.mcp.util.LogProviderUtil;
import no.nordicsemi.android.mcp.widget.ClosableTabLayout;

/* loaded from: classes.dex */
public class TabsFragment extends Fragment implements ViewPager.j, MainActivity.OnBackPressedListener, SaveLogSessionFragment.DialogListener, StillConnectedFragment.DisconnectAndCloseListener, ClosableTabLayout.OnTabClosedListener, HasTabs {
    public static final String FRAGMENT_ID = "tabs_fragment";
    private TabsAdapter mAdapter;
    private MainActivity.OnBackPressedListener mChildOnBackPressedListener;
    private IBluetoothLeService mService;
    private ViewPager mViewPager;
    private BroadcastReceiver mCloseAppBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.TabsFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            TabsFragment.this.onDisconnectAndClose("always".equals(PreferenceManager.getDefaultSharedPreferences(context).getString(SettingsFragment.SETTINGS_LOGGER, SettingsFragment.LOGGER_ASK)));
        }
    };
    private BroadcastReceiver mConnectionsChangedBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.TabsFragment.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (TabsFragment.this.mAdapter == null) {
                return;
            }
            if (intent.getIntExtra(BluetoothLeService.EXTRA_ACTION, 0) == 1) {
                TabsFragment.this.mAdapter.refreshDevices();
                if (TabsFragment.this.isAdded()) {
                    TabsFragment tabsFragment = TabsFragment.this;
                    tabsFragment.selectPage(tabsFragment.mAdapter.getCount() - 1);
                    return;
                }
                return;
            }
            TabsFragment.this.mAdapter.refreshDevices();
        }
    };
    private ServiceConnection mServiceConnection = new ServiceConnection() { // from class: no.nordicsemi.android.mcp.TabsFragment.3
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IBluetoothLeService iBluetoothLeService = TabsFragment.this.mService = (IBluetoothLeService) iBinder;
            TabsFragment.this.mAdapter.setService(iBluetoothLeService);
            TabsFragment tabsFragment = TabsFragment.this;
            tabsFragment.setupTabs(tabsFragment.mViewPager);
            TabsFragment.this.selectPage(iBluetoothLeService.getSelectedTabPosition());
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            TabsFragment.this.mService = null;
            TabsFragment.this.mAdapter.setService(null);
            TabsFragment.this.mAdapter.refreshDevices();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void selectPage(int i) {
        ((MainActivity) requireActivity()).selectTab(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupTabs(ViewPager viewPager) {
        ((MainActivity) requireActivity()).setupWithViewPager(viewPager);
    }

    public void disableAdvertiserFragment() {
        this.mAdapter.disableAdvertiserFragment();
    }

    @Override // no.nordicsemi.android.mcp.HasTabs
    public boolean isConnectionOpen(Device device) {
        TabsAdapter tabsAdapter = this.mAdapter;
        return tabsAdapter != null && tabsAdapter.getTabPosition(device) >= this.mAdapter.getCustomTabsCount();
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.setOnBackPressedListener(this);
        mainActivity.setOnTabCloseListener(this);
    }

    @Override // no.nordicsemi.android.mcp.MainActivity.OnBackPressedListener
    public boolean onBackPressed() {
        MainActivity.OnBackPressedListener onBackPressedListener = this.mChildOnBackPressedListener;
        if (onBackPressedListener != null && onBackPressedListener.onBackPressed()) {
            return true;
        }
        IBluetoothLeService iBluetoothLeService = this.mService;
        if (iBluetoothLeService != null && iBluetoothLeService.getConnectedDevices().size() > 0) {
            String string = PreferenceManager.getDefaultSharedPreferences(requireContext()).getString(SettingsFragment.SETTINGS_LOGGER, SettingsFragment.LOGGER_ASK);
            if (LogProviderUtil.logProviderExists(requireContext()) && !"never".equals(string)) {
                if (SettingsFragment.LOGGER_ASK.equals(string)) {
                    SaveLogSessionFragment.getInstance(null, true).show(getChildFragmentManager(), (String) null);
                } else {
                    StillConnectedFragment.getInstance(true).show(getChildFragmentManager(), (String) null);
                }
            } else {
                StillConnectedFragment.getInstance(false).show(getChildFragmentManager(), (String) null);
            }
            return true;
        }
        requireContext().getContentResolver().delete(LocalLogContract.LocalSession.CONTENT_URI, null, null);
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        androidx.fragment.app.d requireActivity = requireActivity();
        requireActivity.setTitle(R.string.nav_devices);
        requireActivity.registerReceiver(this.mCloseAppBroadcastReceiver, new IntentFilter(BluetoothLeService.ACTION_STOP_NRF_CONNECT));
        Intent intent = new Intent(requireActivity, (Class<?>) BluetoothLeService.class);
        try {
            requireActivity.startService(intent);
            requireActivity.bindService(intent, this.mServiceConnection, 0);
        } catch (IllegalStateException unused) {
            if (Build.VERSION.SDK_INT >= 26) {
                intent.putExtra(BluetoothLeService.EXTRA_BACKGROUND_MODE, true);
                requireActivity.startForegroundService(intent);
                requireActivity.bindService(intent, this.mServiceConnection, 0);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_viewpager, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        androidx.fragment.app.d requireActivity = requireActivity();
        requireActivity.unregisterReceiver(this.mCloseAppBroadcastReceiver);
        if (this.mService != null && requireActivity.isFinishing()) {
            this.mService.stopServerIfNoConnections();
        }
        requireActivity.unbindService(this.mServiceConnection);
        this.mService = null;
        this.mChildOnBackPressedListener = null;
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mAdapter.onTabsClosed();
        this.mAdapter.setService(null);
        a.l.a.a.a(requireContext()).a(this.mConnectionsChangedBroadcastReceiver);
        this.mConnectionsChangedBroadcastReceiver = null;
        this.mViewPager = null;
        this.mAdapter = null;
        super.onDestroyView();
    }

    public void onDeviceSelected(Device device, boolean z) {
        onDeviceSelected(device, z, null);
    }

    @Override // no.nordicsemi.android.mcp.fragment.StillConnectedFragment.DisconnectAndCloseListener
    public void onDisconnectAndClose(boolean z) {
        IBluetoothLeService iBluetoothLeService = this.mService;
        if (iBluetoothLeService != null) {
            iBluetoothLeService.disconnectAndCloseAll(z);
        }
        this.mAdapter.refreshDevices();
        requireActivity().finish();
    }

    @Override // androidx.viewpager.widget.ViewPager.j
    public void onPageScrollStateChanged(int i) {
    }

    @Override // androidx.viewpager.widget.ViewPager.j
    public void onPageScrolled(int i, float f2, int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.j
    public void onPageSelected(int i) {
        IBluetoothLeService iBluetoothLeService = this.mService;
        if (iBluetoothLeService != null) {
            iBluetoothLeService.setSelectedTabPosition(i);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        this.mAdapter.saveInstanceState(bundle);
    }

    @Override // no.nordicsemi.android.mcp.fragment.SaveLogSessionFragment.DialogListener
    public void onSaveLogSession(Device device, boolean z, boolean z2) {
        if (device != null) {
            IBluetoothLeConnection connection = this.mService.getConnection(device);
            if (z2) {
                this.mService.disconnectAndClose(connection, z);
                return;
            } else {
                connection.newLogSession(z);
                return;
            }
        }
        onDisconnectAndClose(z);
    }

    @Override // no.nordicsemi.android.mcp.widget.ClosableTabLayout.OnTabClosedListener
    public void onTabClosed(int i) {
        IBluetoothLeService iBluetoothLeService = this.mService;
        if (iBluetoothLeService == null || iBluetoothLeService.getConnectedDevices().size() <= i - this.mAdapter.getCustomTabsCount()) {
            return;
        }
        Device device = this.mService.getConnectedDevices().get(i - this.mAdapter.getCustomTabsCount());
        IBluetoothLeConnection connection = this.mService.getConnection(device);
        if (connection == null) {
            this.mAdapter.refreshDevices();
            return;
        }
        String string = PreferenceManager.getDefaultSharedPreferences(requireContext()).getString(SettingsFragment.SETTINGS_LOGGER, SettingsFragment.LOGGER_ASK);
        if (LogProviderUtil.logProviderExists(requireContext()) && !"never".equals(string)) {
            if (SettingsFragment.LOGGER_ASK.equals(string)) {
                SaveLogSessionFragment.getInstance(device, true).show(getChildFragmentManager(), (String) null);
                return;
            } else {
                this.mService.disconnectAndClose(connection, true);
                return;
            }
        }
        this.mService.disconnectAndClose(connection, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        this.mViewPager = viewPager;
        TabsAdapter tabsAdapter = new TabsAdapter(this, getChildFragmentManager(), bundle);
        this.mAdapter = tabsAdapter;
        viewPager.setAdapter(tabsAdapter);
        viewPager.addOnPageChangeListener(this);
        a.l.a.a.a(requireContext()).a(this.mConnectionsChangedBroadcastReceiver, new IntentFilter(BluetoothLeService.ACTION_CONNECTIONS_CHANGED));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setChildOnBackPressedListener(MainActivity.OnBackPressedListener onBackPressedListener) {
        this.mChildOnBackPressedListener = onBackPressedListener;
    }

    public void onDeviceSelected(Device device, int i) {
        onDeviceSelected(device, false, Integer.valueOf(i));
    }

    private void onDeviceSelected(Device device, boolean z, Integer num) {
        IBluetoothLeService iBluetoothLeService = this.mService;
        if (iBluetoothLeService != null) {
            if (iBluetoothLeService.getConnection(device.smallCopy()) == null) {
                IBluetoothLeConnection createConnection = this.mService.createConnection(device, true);
                createConnection.setAutoConnect(z);
                if (num == null || Build.VERSION.SDK_INT < 26) {
                    return;
                }
                createConnection.setPreferredPhy(num);
                return;
            }
            selectPage(this.mAdapter.getTabPosition(device));
        }
    }
}
