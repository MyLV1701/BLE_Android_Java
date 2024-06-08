package no.nordicsemi.android.mcp;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import no.nordicsemi.android.mcp.MainActivity;
import no.nordicsemi.android.mcp.advertiser.AdvertiserFragment;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.ble.IBluetoothLeService;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.bonded.BondedDevicesListFragment;
import no.nordicsemi.android.mcp.connection.DeviceDetailsFragment2;
import no.nordicsemi.android.mcp.scanner.ScannerFragment;
import no.nordicsemi.android.mcp.widget.ClosablePagerAdapter;

/* loaded from: classes.dex */
public class TabsAdapter extends ClosablePagerAdapter {
    private static final int CUSTOM_TABS = 3;
    private static final String PREFS_ADVERTISER_TAB_DISABLED = "advertiser_tab_disabled";
    private static final String SIS_DEVICES = "devices";
    private String TITLE_ADVERTISER;
    private String TITLE_BONDED;
    private String TITLE_SCANNER;
    private boolean mAdvertiserDisabled;
    private Context mContext;
    private TabListener mCurrentTabFragment;
    private int mCustomTabsCount;
    private final ArrayList<Device> mDevices;
    private IBluetoothLeService mService;
    private final TabsFragment mTabsFragment;

    /* loaded from: classes.dex */
    public interface TabListener extends MainActivity.OnBackPressedListener {
        void onTabClosed();

        void onTabOpen();
    }

    public TabsAdapter(TabsFragment tabsFragment, androidx.fragment.app.l lVar, Bundle bundle) {
        super(lVar);
        this.mCustomTabsCount = 3;
        this.mContext = tabsFragment.getContext();
        this.mTabsFragment = tabsFragment;
        if (bundle != null) {
            this.mDevices = bundle.getParcelableArrayList("devices");
        } else {
            this.mDevices = new ArrayList<>();
        }
        this.mAdvertiserDisabled = PreferenceManager.getDefaultSharedPreferences(this.mContext).getBoolean(PREFS_ADVERTISER_TAB_DISABLED, false);
        if (this.mAdvertiserDisabled) {
            this.mCustomTabsCount--;
        }
        this.TITLE_SCANNER = tabsFragment.getString(R.string.tab_scanner);
        this.TITLE_BONDED = tabsFragment.getString(R.string.tab_bonded);
        this.TITLE_ADVERTISER = tabsFragment.getString(R.string.tab_advertiser);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int disableAdvertiserFragment() {
        PreferenceManager.getDefaultSharedPreferences(this.mContext).edit().putBoolean(PREFS_ADVERTISER_TAB_DISABLED, true).apply();
        this.mCustomTabsCount--;
        this.mAdvertiserDisabled = true;
        notifyDataSetChanged();
        return 2;
    }

    @Override // androidx.viewpager.widget.a
    public int getCount() {
        if (this.mService == null) {
            return this.mCustomTabsCount;
        }
        return this.mCustomTabsCount + this.mDevices.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCustomTabsCount() {
        return this.mCustomTabsCount;
    }

    @Override // androidx.fragment.app.s
    public Fragment getItem(int i) {
        if (i == 0) {
            return new ScannerFragment();
        }
        if (i != 1) {
            if (i == 2 && !this.mAdvertiserDisabled) {
                return new AdvertiserFragment();
            }
            Device device = this.mDevices.get(i - this.mCustomTabsCount);
            IBluetoothLeConnection connection = this.mService.getConnection(device);
            return DeviceDetailsFragment2.getInstance(device, connection != null && connection.hasAutoConnect());
        }
        return new BondedDevicesListFragment();
    }

    @Override // androidx.viewpager.widget.a
    public int getItemPosition(Object obj) {
        if ((obj instanceof AdvertiserFragment) && this.mAdvertiserDisabled) {
            return -2;
        }
        if (this.mService != null && (obj instanceof DeviceDetailsFragment2)) {
            int indexOf = this.mDevices.indexOf((Device) ((DeviceDetailsFragment2) obj).requireArguments().getParcelable("device"));
            if (indexOf == -1) {
                return -2;
            }
            return indexOf + this.mCustomTabsCount;
        }
        return super.getItemPosition(obj);
    }

    @Override // no.nordicsemi.android.mcp.widget.ClosablePagerAdapter
    public CharSequence getPageSubtitle(int i) {
        int i2 = this.mCustomTabsCount;
        if (i < i2) {
            return null;
        }
        return this.mDevices.get(i - i2).getAddress(this.mContext);
    }

    @Override // androidx.viewpager.widget.a
    public CharSequence getPageTitle(int i) {
        if (i == 0) {
            return this.TITLE_SCANNER;
        }
        if (i != 1) {
            if (i == 2 && !this.mAdvertiserDisabled) {
                return this.TITLE_ADVERTISER;
            }
            String name = this.mDevices.get(i - this.mCustomTabsCount).getName();
            return !TextUtils.isEmpty(name) ? name : this.mTabsFragment.getString(R.string.not_available);
        }
        return this.TITLE_BONDED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTabPosition(Device device) {
        return this.mDevices.indexOf(device) + this.mCustomTabsCount;
    }

    @Override // no.nordicsemi.android.mcp.widget.ClosablePagerAdapter
    public boolean isPageClosable(int i) {
        return i >= this.mCustomTabsCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onTabsClosed() {
        TabListener tabListener = this.mCurrentTabFragment;
        if (tabListener != null) {
            tabListener.onTabClosed();
        }
        this.mCurrentTabFragment = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void refreshDevices() {
        this.mDevices.clear();
        IBluetoothLeService iBluetoothLeService = this.mService;
        if (iBluetoothLeService != null) {
            this.mDevices.addAll(iBluetoothLeService.getConnectedDevices());
        }
        try {
            notifyDataSetChanged();
        } catch (IndexOutOfBoundsException unused) {
            this.mDevices.clear();
            IBluetoothLeService iBluetoothLeService2 = this.mService;
            if (iBluetoothLeService2 != null) {
                this.mDevices.addAll(iBluetoothLeService2.getConnectedDevices());
            }
            notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void saveInstanceState(Bundle bundle) {
        bundle.putParcelableArrayList("devices", this.mDevices);
    }

    @Override // androidx.fragment.app.s, androidx.viewpager.widget.a
    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        try {
            super.setPrimaryItem(viewGroup, i, obj);
        } catch (Exception e2) {
            Log.e("TabsAdapter", "Delayed tab selecting failed", e2);
        }
        TabListener tabListener = (TabListener) obj;
        if (this.mCurrentTabFragment == tabListener) {
            return;
        }
        this.mTabsFragment.setChildOnBackPressedListener(tabListener);
        TabListener tabListener2 = this.mCurrentTabFragment;
        if (tabListener2 != null) {
            tabListener2.onTabClosed();
        }
        tabListener.onTabOpen();
        this.mCurrentTabFragment = tabListener;
    }

    public void setService(IBluetoothLeService iBluetoothLeService) {
        boolean z = this.mService == null;
        this.mService = iBluetoothLeService;
        if (z || !(this.mService == null || this.mDevices.size() == this.mService.getConnectedDevices().size())) {
            refreshDevices();
        }
    }
}
