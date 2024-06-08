package no.nordicsemi.android.mcp.scanner;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import java.util.List;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.ble.parser.AppearanceLibrary;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class Filter {
    private static final String PREF_FILTER = "filter_value";
    private static final String PREF_FILTER_ADV = "filter_adv_value";
    private static final String PREF_FILTER_EXCLUDE = "filter_exclude";
    private static final String PREF_FILTER_NO_NAME = "filter_no_name";
    private static final String PREF_FILTER_WITH_NAME = "filter_with_name";
    private static final String PREF_ONLY_FAVORITES = "only_favorites";
    private static final String PREF_RSSI = "rssi_value";
    private Excludes excludes;
    private String filter;
    private String filterAdvertisingData;
    private boolean noName;
    private boolean onlyFavorites;
    private int rssi;
    private boolean withName;

    /* loaded from: classes.dex */
    public static class Excludes {
        public boolean apple;
        public boolean beacons;
        public boolean bluetoothMesh;
        public boolean microsoft;

        /* JADX INFO: Access modifiers changed from: private */
        public int value() {
            int i = this.apple ? 1 : 0;
            if (this.microsoft) {
                i |= 2;
            }
            if (this.bluetoothMesh) {
                i |= 4;
            }
            return this.beacons ? i | 8 : i;
        }

        public boolean any() {
            return this.apple || this.microsoft || this.bluetoothMesh || this.beacons;
        }

        public void clear() {
            this.beacons = false;
            this.bluetoothMesh = false;
            this.microsoft = false;
            this.apple = false;
        }

        private Excludes(int i) {
            this.apple = (i & 1) != 0;
            this.microsoft = (i & 2) != 0;
            this.bluetoothMesh = (i & 4) != 0;
            this.beacons = (i & 8) != 0;
        }
    }

    private Filter(String str, boolean z, boolean z2, int i, String str2, int i2, boolean z3) {
        set(str, z, z2, str2, i2, z3);
        this.excludes = new Excludes(i);
    }

    public static Filter loadFilter(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return new Filter(defaultSharedPreferences.getString(PREF_FILTER, ""), defaultSharedPreferences.getBoolean(PREF_FILTER_NO_NAME, false), defaultSharedPreferences.getBoolean(PREF_FILTER_WITH_NAME, false), defaultSharedPreferences.getInt(PREF_FILTER_EXCLUDE, 0), defaultSharedPreferences.getString(PREF_FILTER_ADV, ""), defaultSharedPreferences.getInt(PREF_RSSI, -100), defaultSharedPreferences.getBoolean(PREF_ONLY_FAVORITES, false));
    }

    public void apply(Context context, DatabaseHelper databaseHelper, List<Device> list, List<Device> list2) {
        list2.clear();
        for (Device device : list) {
            if (matches(context, databaseHelper, device)) {
                list2.add(device);
            }
        }
    }

    public Excludes getExcludes() {
        return this.excludes;
    }

    public String getFilter() {
        return this.filter;
    }

    public String getFilterAdvertisingData() {
        return this.filterAdvertisingData;
    }

    public int getRssi() {
        return this.rssi;
    }

    public boolean isNoName() {
        return this.noName;
    }

    public boolean isOnlyFavorites() {
        return this.onlyFavorites;
    }

    public boolean isWithName() {
        return this.withName;
    }

    public boolean matches(Context context, DatabaseHelper databaseHelper, Device device) {
        boolean hadName = device.hadName();
        String lowerCase = !TextUtils.isEmpty(device.getName()) ? device.getName().toLowerCase() : null;
        String lowerCase2 = device.getAddress(context).toLowerCase(Locale.US);
        if (!TextUtils.isEmpty(this.filter) && ((lowerCase == null || (!this.noName && !lowerCase.contains(this.filter))) && !lowerCase2.contains(this.filter))) {
            return false;
        }
        if (this.noName && hadName) {
            return false;
        }
        if (this.withName && !hadName) {
            return false;
        }
        if (this.rssi != -100 && device.getHighestRssi() < this.rssi) {
            return false;
        }
        if (this.onlyFavorites && !databaseHelper.isDeviceFavorite(device)) {
            return false;
        }
        if (this.excludes.apple && device.getAppearance() == 8) {
            return false;
        }
        if (this.excludes.microsoft && device.getAppearance() == 28) {
            return false;
        }
        if (this.excludes.bluetoothMesh && device.isBluetoothMeshDevice()) {
            return false;
        }
        if (!this.excludes.beacons || (device.getAppearance() & AppearanceLibrary.MASK_BEACON) == 0) {
            return TextUtils.isEmpty(this.filterAdvertisingData) || device.getRawDataAsString().contains(this.filterAdvertisingData);
        }
        return false;
    }

    public void saveFilter(Context context) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(PREF_FILTER, this.filter);
        edit.putBoolean(PREF_FILTER_NO_NAME, this.noName);
        edit.putBoolean(PREF_FILTER_WITH_NAME, this.withName);
        edit.putInt(PREF_FILTER_EXCLUDE, this.excludes.value());
        edit.putString(PREF_FILTER_ADV, this.filterAdvertisingData);
        edit.putInt(PREF_RSSI, this.rssi);
        edit.putBoolean(PREF_ONLY_FAVORITES, this.onlyFavorites);
        edit.apply();
    }

    public void set(String str, boolean z, boolean z2, String str2, int i, boolean z3) {
        this.filter = str.toLowerCase(Locale.US);
        this.noName = z;
        this.withName = z2;
        this.filterAdvertisingData = str2;
        this.rssi = i;
        this.onlyFavorites = z3;
    }

    public String toString() {
        return "Value: " + this.filter + ", no name = " + this.noName + ", with name = " + this.withName + ", Adv data: " + this.filterAdvertisingData + ", RSSI: " + this.rssi + ", only favorites: " + this.onlyFavorites;
    }
}
