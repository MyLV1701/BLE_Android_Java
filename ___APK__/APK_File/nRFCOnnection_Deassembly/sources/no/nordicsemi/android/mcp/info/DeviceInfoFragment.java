package no.nordicsemi.android.mcp.info;

import a.f.d.b;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class DeviceInfoFragment extends Fragment {
    private TextView mBleDisabledStatusView;
    private View mBleDisabledView;
    private Button mBleEnableAction;
    private final BroadcastReceiver mBluetoothStateBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.info.DeviceInfoFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10)) {
                case 10:
                    DeviceInfoFragment.this.mBleDisabledStatusView.setText(R.string.ble_adapter_disabled);
                    DeviceInfoFragment.this.mBleDisabledView.setBackgroundColor(DeviceInfoFragment.this.getResources().getColor(R.color.alert_state_off));
                    DeviceInfoFragment.this.mBleEnableAction.setVisibility(0);
                    DeviceInfoFragment.this.mBleDisabledView.setVisibility(0);
                    break;
                case 11:
                    DeviceInfoFragment.this.mBleDisabledStatusView.setText(R.string.ble_adapter_enabling);
                    DeviceInfoFragment.this.mBleDisabledView.setBackgroundColor(DeviceInfoFragment.this.getResources().getColor(R.color.alert_state_turning_on));
                    DeviceInfoFragment.this.mBleEnableAction.setVisibility(8);
                    DeviceInfoFragment.this.mBleDisabledView.setVisibility(0);
                    break;
                case 12:
                    DeviceInfoFragment.this.mBleDisabledView.setVisibility(8);
                    DeviceInfoFragment deviceInfoFragment = DeviceInfoFragment.this;
                    deviceInfoFragment.onViewCreated(deviceInfoFragment.getView(), null);
                    break;
                case 13:
                    DeviceInfoFragment.this.mBleDisabledStatusView.setText(R.string.ble_adapter_disabling);
                    DeviceInfoFragment.this.mBleDisabledView.setBackgroundColor(DeviceInfoFragment.this.getResources().getColor(R.color.alert_state_turning_off));
                    DeviceInfoFragment.this.mBleEnableAction.setVisibility(8);
                    DeviceInfoFragment.this.mBleDisabledView.setVisibility(0);
                    break;
            }
            DeviceInfoFragment.this.requireActivity().invalidateOptionsMenu();
        }
    };

    private void enableBle() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            defaultAdapter.enable();
        }
    }

    public /* synthetic */ void a(View view) {
        enableBle();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requireContext().registerReceiver(this.mBluetoothStateBroadcastReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_device_information, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        requireContext().unregisterReceiver(this.mBluetoothStateBroadcastReceiver);
    }

    @Override // androidx.fragment.app.Fragment
    @SuppressLint({"NewApi"})
    public void onViewCreated(View view, Bundle bundle) {
        this.mBleDisabledView = view.findViewById(R.id.ble_disabled);
        this.mBleDisabledStatusView = (TextView) this.mBleDisabledView.findViewById(R.id.status);
        Button button = (Button) this.mBleDisabledView.findViewById(R.id.action_enable);
        this.mBleEnableAction = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.info.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DeviceInfoFragment.this.a(view2);
            }
        });
        ((TextView) view.findViewById(R.id.device_info_device_name)).setText(c.b.a.a.a.a());
        ((TextView) view.findViewById(R.id.device_info_android_version)).setText(Build.VERSION.RELEASE);
        ((TextView) view.findViewById(R.id.device_info_manufacturer)).setText(Build.MANUFACTURER);
        ((TextView) view.findViewById(R.id.device_info_model)).setText(Build.MODEL);
        ((TextView) view.findViewById(R.id.device_info_build_version)).setText(Build.DISPLAY);
        ((TextView) view.findViewById(R.id.device_info_board)).setText(Build.BOARD);
        ((TextView) view.findViewById(R.id.device_info_product)).setText(Build.PRODUCT);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        int a2 = b.a(requireContext(), R.color.green);
        int a3 = b.a(requireContext(), R.color.red);
        int a4 = b.a(requireContext(), R.color.nordicDarkGray);
        boolean z = requireContext().getPackageManager().hasSystemFeature("android.hardware.bluetooth_le") && defaultAdapter != null;
        boolean z2 = !z || (z && defaultAdapter.isEnabled());
        this.mBleDisabledView.setVisibility(z2 ? 8 : 0);
        TextView textView = (TextView) view.findViewById(R.id.device_info_ble_supported);
        int i = R.string.device_info_no;
        textView.setText(z ? R.string.device_info_yes : R.string.device_info_no);
        textView.setTextColor(z ? a2 : a3);
        TextView textView2 = (TextView) view.findViewById(R.id.device_info_native_hid_supported);
        boolean z3 = z && Build.VERSION.SDK_INT >= 19;
        textView2.setText(z3 ? R.string.device_info_yes : R.string.device_info_no);
        textView2.setTextColor(z3 ? a2 : a3);
        TextView textView3 = (TextView) view.findViewById(R.id.device_info_lollipop_api_supported);
        boolean z4 = z && Build.VERSION.SDK_INT >= 21;
        textView3.setText(z2 ? z4 ? R.string.device_info_yes : R.string.device_info_no : R.string.device_info_unknown);
        textView3.setTextColor(z2 ? z4 ? a2 : a3 : a4);
        TextView textView4 = (TextView) view.findViewById(R.id.device_info_scanning_filtering_supported);
        boolean z5 = z4 && defaultAdapter.isOffloadedFilteringSupported();
        textView4.setText(z2 ? z5 ? R.string.device_info_yes : R.string.device_info_no : R.string.device_info_unknown);
        textView4.setTextColor(z2 ? z5 ? a2 : a3 : a4);
        TextView textView5 = (TextView) view.findViewById(R.id.device_info_scanning_batching_supported);
        boolean z6 = z4 && defaultAdapter.isOffloadedFilteringSupported();
        textView5.setText(z2 ? z6 ? R.string.device_info_yes : R.string.device_info_no : R.string.device_info_unknown);
        textView5.setTextColor(z2 ? z6 ? a2 : a3 : a4);
        TextView textView6 = (TextView) view.findViewById(R.id.device_info_peripheral_mode_supported);
        boolean z7 = z4 && defaultAdapter.getBluetoothLeAdvertiser() != null;
        textView6.setText(z2 ? z7 ? R.string.device_info_yes : R.string.device_info_no : R.string.device_info_unknown);
        textView6.setTextColor(z2 ? z7 ? a2 : a3 : a4);
        TextView textView7 = (TextView) view.findViewById(R.id.device_info_multiple_adv_supported);
        boolean z8 = z4 && defaultAdapter.isMultipleAdvertisementSupported();
        textView7.setText(z2 ? z8 ? R.string.device_info_yes : R.string.device_info_no : R.string.device_info_unknown);
        textView7.setTextColor(z2 ? z8 ? a2 : a3 : a4);
        TextView textView8 = (TextView) view.findViewById(R.id.device_info_phy_le_2m_supported);
        boolean z9 = z && Build.VERSION.SDK_INT >= 26;
        boolean z10 = z9 && defaultAdapter.isLe2MPhySupported();
        textView8.setText(z2 ? z10 ? R.string.device_info_yes : R.string.device_info_no : R.string.device_info_unknown);
        textView8.setTextColor(z2 ? z10 ? a2 : a3 : a4);
        TextView textView9 = (TextView) view.findViewById(R.id.device_info_phy_le_coded_supported);
        boolean z11 = z9 && defaultAdapter.isLeCodedPhySupported();
        textView9.setText(z2 ? z11 ? R.string.device_info_yes : R.string.device_info_no : R.string.device_info_unknown);
        textView9.setTextColor(z2 ? z11 ? a2 : a3 : a4);
        TextView textView10 = (TextView) view.findViewById(R.id.device_info_periodic_advertisement_supported);
        boolean z12 = z9 && defaultAdapter.isLePeriodicAdvertisingSupported();
        textView10.setText(z2 ? z12 ? R.string.device_info_yes : R.string.device_info_no : R.string.device_info_unknown);
        textView10.setTextColor(z2 ? z12 ? a2 : a3 : a4);
        TextView textView11 = (TextView) view.findViewById(R.id.device_info_extended_advertisement_supported);
        boolean z13 = z9 && defaultAdapter.isLeExtendedAdvertisingSupported();
        if (!z2) {
            i = R.string.device_info_unknown;
        } else if (z13) {
            i = R.string.device_info_yes;
        }
        textView11.setText(i);
        if (!z2) {
            a2 = a4;
        } else if (!z13) {
            a2 = a3;
        }
        textView11.setTextColor(a2);
        TextView textView12 = (TextView) view.findViewById(R.id.device_info_max_advertising_data_length);
        int leMaximumAdvertisingDataLength = z9 ? defaultAdapter.getLeMaximumAdvertisingDataLength() : 0;
        textView12.setText(leMaximumAdvertisingDataLength > 0 ? String.valueOf(leMaximumAdvertisingDataLength) : getString(R.string.not_available));
        view.findViewById(R.id.device_info_unknown_info).setVisibility(z2 ? 8 : 0);
        TextView textView13 = (TextView) view.findViewById(R.id.device_info_dimensions_px);
        TextView textView14 = (TextView) view.findViewById(R.id.device_info_dimensions_dip);
        Display defaultDisplay = ((WindowManager) requireContext().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getRealMetrics(displayMetrics);
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        textView13.setText(i2 + " x " + i3);
        textView14.setText(((int) (((float) i2) / displayMetrics.density)) + " x " + ((int) (((float) i3) / displayMetrics.density)));
    }
}
