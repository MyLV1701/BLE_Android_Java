package no.nordicsemi.android.mcp.settings;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.d;
import androidx.preference.PreferenceScreen;
import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.dfu.DfuSettingsConstants;
import no.nordicsemi.android.log.LogContract;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class SettingsFragment extends androidx.preference.g implements DfuSettingsConstants, SharedPreferences.OnSharedPreferenceChangeListener {
    public static final String LOGGER_ALWAYS = "always";
    public static final String LOGGER_ASK = "ask";
    public static final String LOGGER_NEVER = "never";
    public static final String REFRESH_ALWAYS = "always";
    public static final String REFRESH_IF_NOT_BONDED = "not_bonded";
    public static final String REFRESH_NEVER = "never";
    public static final String SETTINGS_AUTO_MTU_REQUEST = "settings_auto_request_highest_mtu";
    public static final String SETTINGS_AUTO_SERVICE_DISCOVERY = "settings_auto_service_discovery";
    public static final String SETTINGS_CSV_SEPARATOR = "settings_csv_separator";
    public static final String SETTINGS_LOGGER = "settings_logger";
    public static final String SETTINGS_LOGGER_OPEN = "settings_logger_open";
    public static final String SETTINGS_REFRESH_AFTER_DISCONNECT = "settings_refresh_after_disconnect";
    public static final String SETTINGS_SANTA = "settings_santa_mode";
    public static final String SETTINGS_SCANNING_API = "settings_scanning_api";
    public static final String SETTINGS_SCANNING_BATCHING = "settings_scanning_batching";
    public static final String SETTINGS_SCANNING_COMPATIBILITY_MODE = "settings_scanning_compatibility_mode";
    public static final String SETTINGS_SCANNING_CONTINUOUS = "settings_scanning_mode";
    public static final String SETTINGS_SCANNING_MODE_LOLLIPOP = "settings_scanning_mode_lollipop";
    public static final int SETTINGS_SCANNING_MODE_LOLLIPOP_DEFAULT = 2;
    public static final String SETTINGS_SCANNING_PERIOD = "setting_scanning_period";
    public static final int SETTINGS_SCANNING_PERIOD_DEFAULT = 45;
    public static final String SETTINGS_SERVER_RESPONSE_TYPE = "settings_server_response_type";
    public static final String SETTINGS_SHOW_INCOMING_CONNECTION = "settings_show_incoming_connection";
    public static final String SETTINGS_THEME = "settings_theme";

    public static SettingsFragment getInstance(String str) {
        SettingsFragment settingsFragment = new SettingsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(androidx.preference.g.ARG_PREFERENCE_ROOT, str);
        settingsFragment.setArguments(bundle);
        return settingsFragment;
    }

    private int getPreferencesByCategory(String str) {
        return "scanner".equals(str) ? R.xml.settings_scanner : "connectivity".equals(str) ? R.xml.settings_connectivity : DfuBaseService.NOTIFICATION_CHANNEL_DFU.equals(str) ? R.xml.settings_dfu : "logger".equals(str) ? R.xml.settings_logger : "export".equals(str) ? R.xml.settings_export : R.xml.settings_about;
    }

    private void updateMBRSize() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.a(DfuSettingsConstants.SETTINGS_MBR_SIZE).setSummary(getPreferenceManager().h().getString(DfuSettingsConstants.SETTINGS_MBR_SIZE, String.valueOf(4096)));
    }

    private void updateNumberOfPacketsSummary() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        SharedPreferences h = getPreferenceManager().h();
        int i = 12;
        String string = h.getString(DfuSettingsConstants.SETTINGS_NUMBER_OF_PACKETS, String.valueOf(12));
        if (TextUtils.isEmpty(string)) {
            h.edit().putString(DfuSettingsConstants.SETTINGS_NUMBER_OF_PACKETS, String.valueOf(12)).apply();
        } else {
            try {
                i = Integer.parseInt(string);
            } catch (Exception unused) {
            }
        }
        preferenceScreen.a(DfuSettingsConstants.SETTINGS_NUMBER_OF_PACKETS).setSummary(String.valueOf(i));
        if (i <= 200 || Build.VERSION.SDK_INT >= 23) {
            return;
        }
        TextView textView = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.textview, (ViewGroup) null);
        textView.setText(R.string.settings_dfu_number_of_packets_info);
        d.a aVar = new d.a(requireContext());
        aVar.b(textView);
        aVar.c(R.string.settings_information);
        aVar.b(R.string.ok, (DialogInterface.OnClickListener) null);
        aVar.c();
    }

    @SuppressLint({"NewApi"})
    private void updateScanningSettings() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        boolean equals = "21".equals(getPreferenceManager().h().getString(SETTINGS_SCANNING_API, null));
        int i = Build.VERSION.SDK_INT;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        preferenceScreen.a(SETTINGS_SCANNING_API).setEnabled(i >= 21);
        preferenceScreen.a(SETTINGS_SCANNING_MODE_LOLLIPOP).setEnabled(i >= 21 && equals);
        boolean z = i >= 21 && defaultAdapter != null && defaultAdapter.isOffloadedScanBatchingSupported();
        preferenceScreen.a(SETTINGS_SCANNING_BATCHING).setEnabled(z && equals);
        if (z) {
            return;
        }
        preferenceScreen.a(SETTINGS_SCANNING_BATCHING).setSummary(R.string.not_supported);
    }

    @Override // androidx.preference.g
    public void onCreatePreferences(Bundle bundle, String str) {
        int preferencesByCategory = getPreferencesByCategory(str);
        addPreferencesFromResource(preferencesByCategory);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        switch (preferencesByCategory) {
            case R.xml.settings_dfu /* 2132017154 */:
                updateNumberOfPacketsSummary();
                updateMBRSize();
                return;
            case R.xml.settings_export /* 2132017155 */:
            default:
                return;
            case R.xml.settings_logger /* 2132017156 */:
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setType(LogContract.Session.CONTENT_TYPE);
                intent.addCategory("android.intent.category.DEFAULT");
                boolean z = intent.resolveActivity(requireContext().getPackageManager()) != null;
                preferenceScreen.a(SETTINGS_LOGGER).setEnabled(z);
                preferenceScreen.a(SETTINGS_LOGGER_OPEN).setTitle(z ? R.string.settings_logger_open : R.string.settings_logger_download);
                return;
            case R.xml.settings_scanner /* 2132017157 */:
                updateScanningSettings();
                return;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (SETTINGS_SCANNING_API.equals(str)) {
            d.a aVar = new d.a(requireContext());
            aVar.b(R.string.settings_restart_required);
            aVar.c(R.string.settings_information);
            aVar.c(R.string.ok, null);
            aVar.c();
            updateScanningSettings();
            return;
        }
        if (SETTINGS_SCANNING_BATCHING.equals(str)) {
            if (sharedPreferences.getBoolean(SETTINGS_SCANNING_BATCHING, false)) {
                d.a aVar2 = new d.a(requireContext());
                aVar2.b(R.string.settings_scanning_batching_disabled_test);
                aVar2.c(R.string.settings_information);
                aVar2.c(R.string.ok, null);
                aVar2.c();
                return;
            }
            return;
        }
        if (DfuSettingsConstants.SETTINGS_PACKET_RECEIPT_NOTIFICATION_ENABLED.equals(str)) {
            if (!(!sharedPreferences.getBoolean(DfuSettingsConstants.SETTINGS_PACKET_RECEIPT_NOTIFICATION_ENABLED, true)) || Build.VERSION.SDK_INT >= 23) {
                return;
            }
            d.a aVar3 = new d.a(requireContext());
            aVar3.b(R.string.settings_dfu_number_of_packets_info);
            aVar3.c(R.string.settings_information);
            aVar3.c(R.string.ok, null);
            aVar3.c();
            return;
        }
        if (DfuSettingsConstants.SETTINGS_NUMBER_OF_PACKETS.equals(str)) {
            updateNumberOfPacketsSummary();
            return;
        }
        if (DfuSettingsConstants.SETTINGS_MBR_SIZE.equals(str)) {
            updateMBRSize();
            return;
        }
        if (DfuSettingsConstants.SETTINGS_ASSUME_DFU_NODE.equals(str) && sharedPreferences.getBoolean(str, false)) {
            d.a aVar4 = new d.a(requireContext());
            aVar4.b(R.string.settings_dfu_assume_dfu_mode_info);
            aVar4.c(R.string.settings_dfu_information);
            aVar4.c(R.string.ok, null);
            aVar4.c();
            return;
        }
        if (!SETTINGS_SCANNING_CONTINUOUS.equals(str) || sharedPreferences.getBoolean(SETTINGS_SCANNING_CONTINUOUS, true)) {
            return;
        }
        d.a aVar5 = new d.a(requireContext());
        aVar5.b(R.string.settings_scanning_mode_info);
        aVar5.c(R.string.settings_information);
        aVar5.c(R.string.ok, null);
        aVar5.c();
    }

    @Override // androidx.preference.g, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.setBackgroundResource(R.color.listBackgroundNormal);
    }
}
