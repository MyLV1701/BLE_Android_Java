package no.nordicsemi.android.mcp.connection;

import a.a.o.b;
import a.k.a.a;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.FillDirection;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.XYPlot;
import com.google.android.material.tabs.TabLayout;
import io.runtime.mcumgr.dfu.FirmwareUpgradeManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.log.LogContract;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.TabsFragment;
import no.nordicsemi.android.mcp.ble.BluetoothLeService;
import no.nordicsemi.android.mcp.ble.ConnectionListener;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.ble.ServiceConstants;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.ble.parser.gatt.GattUtils;
import no.nordicsemi.android.mcp.ble.write.DialogBuilderFactory;
import no.nordicsemi.android.mcp.fragment.EddystonePairingDialogFragment;
import no.nordicsemi.android.mcp.fragment.SaveLogSessionFragment;
import no.nordicsemi.android.mcp.scanner.graph.ParcelableXYSeries;
import no.nordicsemi.android.mcp.server.domain.ServerConfiguration;
import no.nordicsemi.android.mcp.settings.SettingsFragment;
import no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedAtLeastOnceException;
import no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException;
import no.nordicsemi.android.mcp.util.AdoptedServicesHelper;
import no.nordicsemi.android.mcp.util.FileHelper;
import no.nordicsemi.android.mcp.util.LogProviderUtil;
import no.nordicsemi.android.mcp.widget.OnTabSelectedListenerAdapter;
import no.nordicsemi.android.mcp.widget.SelectionListener;
import no.nordicsemi.android.mcp.widget.SlidingPaneLayout;
import no.nordicsemi.android.mcp.widget.ViewAnimator;
import no.nordicsemi.android.mcp.widget.connection.CharacteristicView;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.HyphenStyle;

/* loaded from: classes.dex */
public class DeviceDetailsFragment2 extends MacroFragment implements a.InterfaceC0025a, ConnectionListener, ViewAnimator.ExpandCollapseListener, CharacteristicView.OnWriteRequestListener, SelectionListener, b.a {
    private static final int ADAPTER_TYPE_CLIENT = 0;
    private static final int ADAPTER_TYPE_SERVER = 1;
    private static final String ARG_AUTO_CONNECT = "auto_connect";
    public static final String ARG_DEVICE = "device";
    private static final String KEY_ADAPTER_STATE = "adapter_state";
    private static final String KEY_ADAPTER_TYPE = "server_adapter_type";
    private static final String KEY_ITEM_CHECKED = "item_checked";
    private static final String KEY_LOG_OPEN = "log_open";
    private static final String KEY_LOG_SCROLL_POSITION = "log_scroll_position";
    public static final int LOAD_LOG_LOADER_REQ = 1;
    private static final int LOG_SCROLLED_TO_BOTTOM = -2;
    private static final int LOG_SCROLL_NULL = -1;
    public static final String MIN_LEVEL_LOADER_ARG = "min_level";
    private static final long MIN_REFRESH_INTERVAL = 200;
    public static final String PREFS_MICROBIT_INFO_SHOWN = "mcp_microbit_info_shown";
    private static final String PREFS_MIN_LEVEL = "mcp_min_level";
    public static final String PREFS_PARSE_KNOWN_CHAR = "mcp_parst_known_char";
    public static final String PREFS_THINGY_INFO_SHOWN = "mcp_thingy_info_shown";
    private static final int REQUEST_SAVE_LOG = 6;
    private static final String SIS_ACTION_MODE_ENABLED = "action_mode_enabled";
    private static final String SIS_AUTO_CONNECT = "auto_connect";
    private static final String TAG = "DeviceDetailsFragment";
    private boolean actionModeEnabled;
    private boolean clientRefreshScheduled;
    private a.a.o.b mActionMode;
    private View mActionMore;
    private ServicesAdapter2 mAdapter;
    private boolean mAutoConnect;
    private TextView mBondStateView;
    private View mBusy;
    private TextView mConnectionStateView;
    private ServicesAdapter2 mCurrentAdapter;
    private Device mDevice;
    private String mDeviceAddress;
    private String mDeviceAddressIncremented;
    private Handler mHandler;
    private long mLastRefreshTime;
    private ListView mLog;
    private LogAdapter mLogAdapter;
    private Spinner mLogLevel;
    private SlidingPaneLayout mLogPane;
    private int mLogScrollPosition;
    private TabLayout mModeTabLayout;
    private ConnectionListener.OnServiceConnectedCallback mOnServiceConnectedCallback;
    private View mOperationInProgress;
    private boolean mPauseRefreshing;
    private View mProgressContainer;
    private XYPlot mProgressGraph;
    private TextView mProgressText;
    private ServicesAdapter2 mServerAdapter;
    private RecyclerView mServicesView;
    private ParcelableXYSeries mUploadAvgSpeedSeries;
    private ParcelableXYSeries mUploadSpeedSeries;
    private boolean serverRefreshScheduled;
    private ServiceConnection mServiceConnection = new ServiceConnection() { // from class: no.nordicsemi.android.mcp.connection.DeviceDetailsFragment2.1
        /* JADX WARN: Code restructure failed: missing block: B:50:0x01d5, code lost:
        
            if (r9 != 5) goto L63;
         */
        /* JADX WARN: Removed duplicated region for block: B:53:0x020b  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x0222  */
        @Override // android.content.ServiceConnection
        @android.annotation.SuppressLint({"RestrictedApi"})
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onServiceConnected(android.content.ComponentName r8, android.os.IBinder r9) {
            /*
                Method dump skipped, instructions count: 566
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.connection.DeviceDetailsFragment2.AnonymousClass1.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            DeviceDetailsFragment2 deviceDetailsFragment2 = DeviceDetailsFragment2.this;
            deviceDetailsFragment2.mConnection = null;
            if (deviceDetailsFragment2.requireActivity().isFinishing()) {
                return;
            }
            DeviceDetailsFragment2.this.requireActivity().finish();
        }
    };
    private BroadcastReceiver mDfuBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.connection.DeviceDetailsFragment2.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (DeviceDetailsFragment2.this.getActivity() == null || DeviceDetailsFragment2.this.getActivity().isFinishing() || DeviceDetailsFragment2.this.mConnection == null) {
                return;
            }
            String stringExtra = intent.getStringExtra(DfuBaseService.EXTRA_DEVICE_ADDRESS);
            if (stringExtra.equals(DeviceDetailsFragment2.this.mDeviceAddress) || stringExtra.equals(DeviceDetailsFragment2.this.mDeviceAddressIncremented)) {
                String action = intent.getAction();
                if (DfuBaseService.BROADCAST_PROGRESS.equals(action)) {
                    DeviceDetailsFragment2.this.updateDfuProgress(intent.getIntExtra(DfuBaseService.EXTRA_DATA, 0), intent.getFloatExtra(DfuBaseService.EXTRA_SPEED_B_PER_MS, 0.0f), intent.getFloatExtra(DfuBaseService.EXTRA_AVG_SPEED_B_PER_MS, 0.0f), false);
                } else if (DfuBaseService.BROADCAST_ERROR.equals(action)) {
                    int intExtra = intent.getIntExtra(DfuBaseService.EXTRA_DATA, 0);
                    DeviceDetailsFragment2.this.setDfuProgressVisible(false);
                    DeviceDetailsFragment2.this.updateConnectionState(R.string.status_error, e.a.a.a.a.a(intExtra));
                    DeviceDetailsFragment2.this.updateDfuProgress(intExtra, 0.0f, 0.0f, true);
                }
            }
        }
    };
    private AdapterView.OnItemSelectedListener mLevelListener = new AdapterView.OnItemSelectedListener() { // from class: no.nordicsemi.android.mcp.connection.DeviceDetailsFragment2.3
        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            DeviceDetailsFragment2 deviceDetailsFragment2 = DeviceDetailsFragment2.this;
            if (deviceDetailsFragment2.mConnection == null) {
                return;
            }
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(deviceDetailsFragment2.requireContext()).edit();
            edit.putInt(DeviceDetailsFragment2.PREFS_MIN_LEVEL, i);
            edit.apply();
            int i2 = 1;
            if (i == 0) {
                i2 = 0;
            } else if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        i2 = 10;
                    } else if (i == 4) {
                        i2 = 15;
                    } else if (i == 5) {
                        i2 = 20;
                    }
                }
                i2 = 5;
            }
            DeviceDetailsFragment2.this.mLogAdapter.setMinLevel(i2);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
            DeviceDetailsFragment2.this.mLogAdapter.setMinLevel(5);
        }
    };
    private BroadcastReceiver mConnectionBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.connection.DeviceDetailsFragment2.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action;
            if (DeviceDetailsFragment2.this.getActivity() == null || DeviceDetailsFragment2.this.getActivity().isFinishing() || (action = intent.getAction()) == null) {
                return;
            }
            if (action.startsWith(ServiceConstants.ACTION_OPERATION_FINISHED)) {
                DeviceDetailsFragment2.this.mOperationInProgress.setVisibility(4);
                return;
            }
            if (action.startsWith(ServiceConstants.ACTION_OPERATION_STARTED)) {
                DeviceDetailsFragment2.this.mOperationInProgress.setVisibility(0);
                return;
            }
            if (action.startsWith(ServiceConstants.ACTION_GATT_CONNECTING)) {
                DeviceDetailsFragment2.this.updateConnectionState(R.string.status_connecting, new Object[0]);
                DeviceDetailsFragment2.this.mBusy.setVisibility(0);
                return;
            }
            if (action.startsWith(ServiceConstants.ACTION_GATT_CONNECTED)) {
                if (PreferenceManager.getDefaultSharedPreferences(DeviceDetailsFragment2.this.requireContext()).getBoolean(SettingsFragment.SETTINGS_AUTO_SERVICE_DISCOVERY, true)) {
                    DeviceDetailsFragment2.this.updateConnectionState(R.string.status_discovering, new Object[0]);
                } else {
                    DeviceDetailsFragment2.this.mBusy.setVisibility(8);
                    DeviceDetailsFragment2.this.updateConnectionState(R.string.status_connected, new Object[0]);
                }
                DeviceDetailsFragment2.this.mActionMore.setVisibility(0);
                return;
            }
            if (action.startsWith(ServiceConstants.ACTION_GATT_DISCONNECTING)) {
                DeviceDetailsFragment2.this.mBusy.setVisibility(0);
                DeviceDetailsFragment2.this.setAdapterConnectedFlag(false);
                DeviceDetailsFragment2.this.updateConnectionState(R.string.status_disconnecting, new Object[0]);
                DeviceDetailsFragment2.this.onServicesChanged();
                return;
            }
            if (action.startsWith(ServiceConstants.ACTION_GATT_DISCONNECTED)) {
                DeviceDetailsFragment2.this.mBusy.setVisibility(8);
                DeviceDetailsFragment2.this.setAdapterConnectedFlag(false);
                DeviceDetailsFragment2.this.updateConnectionState(R.string.status_disconnected, new Object[0]);
                DeviceDetailsFragment2.this.onServicesChanged();
                return;
            }
            if (action.startsWith(ServiceConstants.ACTION_GATT_SERVER_CONNECTED)) {
                if (DeviceDetailsFragment2.this.mServerAdapter != null) {
                    DeviceDetailsFragment2.this.mServerAdapter.setConnected(true);
                }
                DeviceDetailsFragment2.this.onServicesChanged();
                return;
            }
            if (action.startsWith(ServiceConstants.ACTION_GATT_SERVER_DISCONNECTED)) {
                if (DeviceDetailsFragment2.this.mServerAdapter != null) {
                    DeviceDetailsFragment2.this.mServerAdapter.setConnected(false);
                }
                DeviceDetailsFragment2.this.onServicesChanged();
                return;
            }
            if (action.startsWith(ServiceConstants.ACTION_GATT_DISCOVERING_SERVICES)) {
                DeviceDetailsFragment2.this.mBusy.setVisibility(0);
                DeviceDetailsFragment2.this.updateConnectionState(R.string.status_discovering, new Object[0]);
                return;
            }
            if (action.startsWith(ServiceConstants.ACTION_GATT_SERVICES_DISCOVERED)) {
                DeviceDetailsFragment2.this.mBusy.setVisibility(8);
                DeviceDetailsFragment2.this.onServicesChanged();
                DeviceDetailsFragment2.this.updateConnectionState(R.string.status_connected, new Object[0]);
                DeviceDetailsFragment2.this.displayGattServices(DeviceDetailsFragment2.this.mConnection.getSupportedGattServices());
                DeviceDetailsFragment2.this.checkThingy();
                DeviceDetailsFragment2.this.checkMicrobit();
                return;
            }
            if (action.startsWith(ServiceConstants.ACTION_GATT_BOND_STATE)) {
                DeviceDetailsFragment2.this.updateBondState(intent.getIntExtra(ServiceConstants.EXTRA_DATA, 10));
                return;
            }
            if (action.startsWith(ServiceConstants.ACTION_GATT_ERROR)) {
                DeviceDetailsFragment2.this.notifyDataSetChanged(true, false);
                DeviceDetailsFragment2.this.mBusy.setVisibility(8);
                return;
            }
            if (action.startsWith(ServiceConstants.ACTION_DATA_AVAILABLE)) {
                DeviceDetailsFragment2.this.notifyDataSetChanged(true, false);
                return;
            }
            if (action.startsWith(ServiceConstants.ACTION_DATA_SEND)) {
                DeviceDetailsFragment2.this.notifyDataSetChanged(true, false);
                return;
            }
            if (action.startsWith(ServiceConstants.ACTION_GATT_SERVER_DATA_SEND)) {
                if (intent.hasExtra(ServiceConstants.EXTRA_DATA)) {
                    DeviceDetailsFragment2.this.mServerAdapter.updateServicesToCopy(DeviceDetailsFragment2.this.mConnection.getServerGattServices(false));
                }
                DeviceDetailsFragment2.this.notifyDataSetChanged(false, true);
                return;
            }
            if (action.startsWith(ServiceConstants.ACTION_RSSI_RECEIVED)) {
                IBluetoothLeConnection iBluetoothLeConnection = DeviceDetailsFragment2.this.mConnection;
                if (iBluetoothLeConnection == null || !iBluetoothLeConnection.isMacroRunning()) {
                    Toast.makeText(DeviceDetailsFragment2.this.requireContext(), DeviceDetailsFragment2.this.getString(R.string.rssi_ready, Integer.valueOf(intent.getIntExtra(ServiceConstants.EXTRA_DATA, 0))), 0).show();
                    return;
                }
                return;
            }
            if (action.startsWith(ServiceConstants.ACTION_NEW_LOG_SESSION)) {
                DeviceDetailsFragment2.this.mLogAdapter.reload();
            } else if (ServiceConstants.ACTION_GATT_SERVER_CONFIGURATION_CHANGED.equals(action)) {
                if (DeviceDetailsFragment2.this.mServerAdapter != null) {
                    DeviceDetailsFragment2.this.mServerAdapter.updateServices(DeviceDetailsFragment2.this.mConnection.getServerGattServices(false));
                }
                DeviceDetailsFragment2.this.onServicesChanged();
            }
        }
    };
    private final View.OnClickListener mActionSaveListener = new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.h
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            DeviceDetailsFragment2.this.e(view);
        }
    };
    private final View.OnClickListener mActionShareListener = new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.c
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            DeviceDetailsFragment2.this.f(view);
        }
    };
    private final View.OnClickListener mActionCopyListener = new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.a
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            DeviceDetailsFragment2.this.g(view);
        }
    };
    private final View.OnClickListener mActionRemoveListener = new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.e
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            DeviceDetailsFragment2.this.h(view);
        }
    };
    private RecyclerView.s touchInterceptor = new RecyclerView.s() { // from class: no.nordicsemi.android.mcp.connection.DeviceDetailsFragment2.5
        @Override // androidx.recyclerview.widget.RecyclerView.s
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                DeviceDetailsFragment2.this.mPauseRefreshing = true;
            } else if (action == 1 || action == 3) {
                DeviceDetailsFragment2.this.mPauseRefreshing = false;
                if (DeviceDetailsFragment2.this.clientRefreshScheduled || DeviceDetailsFragment2.this.serverRefreshScheduled) {
                    boolean z = DeviceDetailsFragment2.this.clientRefreshScheduled;
                    boolean z2 = DeviceDetailsFragment2.this.serverRefreshScheduled;
                    DeviceDetailsFragment2.this.clientRefreshScheduled = false;
                    DeviceDetailsFragment2.this.serverRefreshScheduled = false;
                    DeviceDetailsFragment2.this.notifyDataSetChanged(z, z2);
                }
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
    private OnTabSelectedListenerAdapter onTabSelectedListener = new OnTabSelectedListenerAdapter() { // from class: no.nordicsemi.android.mcp.connection.DeviceDetailsFragment2.6
        @Override // no.nordicsemi.android.mcp.widget.OnTabSelectedListenerAdapter, com.google.android.material.tabs.TabLayout.e
        public void onTabSelected(TabLayout.h hVar) {
            int intValue = ((Integer) hVar.e()).intValue();
            DeviceDetailsFragment2 deviceDetailsFragment2 = DeviceDetailsFragment2.this;
            ServicesAdapter2 servicesAdapter2 = deviceDetailsFragment2.mCurrentAdapter = intValue == 0 ? deviceDetailsFragment2.mAdapter : deviceDetailsFragment2.mServerAdapter;
            if (DeviceDetailsFragment2.this.mServicesView.getAdapter() == servicesAdapter2) {
                return;
            }
            DeviceDetailsFragment2.this.mServicesView.setAdapter(servicesAdapter2);
            DeviceDetailsFragment2.this.mConnection.put(DeviceDetailsFragment2.KEY_ADAPTER_TYPE, Integer.valueOf(intValue));
        }
    };
    private final Runnable refreshAdapterTask = new Runnable() { // from class: no.nordicsemi.android.mcp.connection.DeviceDetailsFragment2.7
        @Override // java.lang.Runnable
        public void run() {
            if (DeviceDetailsFragment2.this.mPauseRefreshing) {
                return;
            }
            DeviceDetailsFragment2.this.mLastRefreshTime = SystemClock.elapsedRealtime();
            if (DeviceDetailsFragment2.this.clientRefreshScheduled && DeviceDetailsFragment2.this.mAdapter != null) {
                DeviceDetailsFragment2.this.mAdapter.notifyDataSetChanged();
            }
            if (DeviceDetailsFragment2.this.serverRefreshScheduled && DeviceDetailsFragment2.this.mServerAdapter != null) {
                DeviceDetailsFragment2.this.mServerAdapter.notifyDataSetChanged();
            }
            DeviceDetailsFragment2.this.clientRefreshScheduled = false;
            DeviceDetailsFragment2.this.serverRefreshScheduled = false;
        }
    };

    /* loaded from: classes.dex */
    private class SelectedRow {
        private Integer format;
        private String name;
        private String text;
        private int type;
        private UUID uuid;

        private SelectedRow() {
        }
    }

    @TargetApi(21)
    private void changeStatusBarColor(int i, int i2) {
        ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), Integer.valueOf(i), Integer.valueOf(i2));
        ofObject.setDuration(MIN_REFRESH_INTERVAL);
        ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: no.nordicsemi.android.mcp.connection.b
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                DeviceDetailsFragment2.this.a(valueAnimator);
            }
        });
        ofObject.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkMicrobit() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        boolean z = defaultSharedPreferences.getBoolean(PREFS_MICROBIT_INFO_SHOWN, false);
        if (isResumed() && !z && this.mConnection.isMicrobit()) {
            defaultSharedPreferences.edit().putBoolean(PREFS_MICROBIT_INFO_SHOWN, true).apply();
            new MicrobitFragment().show(getChildFragmentManager(), (String) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkThingy() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        boolean z = defaultSharedPreferences.getBoolean(PREFS_THINGY_INFO_SHOWN, false);
        if (isResumed() && !z && this.mConnection.isThingy()) {
            defaultSharedPreferences.edit().putBoolean(PREFS_THINGY_INFO_SHOWN, true).apply();
            new ThingyFragment().show(getChildFragmentManager(), (String) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayGattServices(List<BluetoothGattService> list) {
        if (list == null) {
            return;
        }
        this.mAdapter.updateServices(list);
        ServicesAdapter2 servicesAdapter2 = this.mServerAdapter;
        if (servicesAdapter2 != null) {
            servicesAdapter2.setConnected(true);
        }
        requireActivity().invalidateOptionsMenu();
    }

    public static DeviceDetailsFragment2 getInstance(Device device, boolean z) {
        DeviceDetailsFragment2 deviceDetailsFragment2 = new DeviceDetailsFragment2();
        Bundle bundle = new Bundle();
        bundle.putParcelable("device", device.smallCopy());
        bundle.putBoolean("auto_connect", z);
        deviceDetailsFragment2.setArguments(bundle);
        return deviceDetailsFragment2;
    }

    private static IntentFilter makeDfuIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DfuBaseService.BROADCAST_PROGRESS);
        intentFilter.addAction(DfuBaseService.BROADCAST_ERROR);
        return intentFilter;
    }

    private static IntentFilter makeGattUpdateIntentFilter(String str) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ServiceConstants.ACTION_OPERATION_STARTED + str);
        intentFilter.addAction(ServiceConstants.ACTION_OPERATION_FINISHED + str);
        intentFilter.addAction(ServiceConstants.ACTION_GATT_ERROR + str);
        intentFilter.addAction(ServiceConstants.ACTION_GATT_CONNECTED + str);
        intentFilter.addAction(ServiceConstants.ACTION_GATT_CONNECTING + str);
        intentFilter.addAction(ServiceConstants.ACTION_GATT_DISCONNECTED + str);
        intentFilter.addAction(ServiceConstants.ACTION_GATT_DISCONNECTING + str);
        intentFilter.addAction(ServiceConstants.ACTION_GATT_BOND_STATE + str);
        intentFilter.addAction(ServiceConstants.ACTION_GATT_DISCOVERING_SERVICES + str);
        intentFilter.addAction(ServiceConstants.ACTION_GATT_SERVICES_DISCOVERED + str);
        intentFilter.addAction(ServiceConstants.ACTION_DATA_SEND + str);
        intentFilter.addAction(ServiceConstants.ACTION_DATA_AVAILABLE + str);
        intentFilter.addAction(ServiceConstants.ACTION_GATT_SERVER_CONNECTED + str);
        intentFilter.addAction(ServiceConstants.ACTION_GATT_SERVER_DISCONNECTED + str);
        intentFilter.addAction(ServiceConstants.ACTION_GATT_SERVER_DATA_SEND + str);
        intentFilter.addAction(ServiceConstants.ACTION_RSSI_RECEIVED + str);
        intentFilter.addAction(ServiceConstants.ACTION_MTU_CHANGED + str);
        intentFilter.addAction(ServiceConstants.ACTION_NEW_LOG_SESSION + str);
        intentFilter.addAction(ServiceConstants.ACTION_GATT_SERVER_CONFIGURATION_CHANGED);
        return intentFilter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int normalizeDfuMaxValue(float f2) {
        return ((int) Math.floor(f2 * 2.0f)) + 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDataSetChanged(boolean z, boolean z2) {
        ServicesAdapter2 servicesAdapter2;
        ServicesAdapter2 servicesAdapter22;
        boolean z3 = this.clientRefreshScheduled;
        if (!z3 && !this.serverRefreshScheduled) {
            if (this.mPauseRefreshing) {
                this.clientRefreshScheduled = z3 || z;
                this.serverRefreshScheduled = this.serverRefreshScheduled || z2;
                return;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long j = this.mLastRefreshTime;
            if (j + MIN_REFRESH_INTERVAL < elapsedRealtime) {
                if (z && (servicesAdapter22 = this.mAdapter) != null) {
                    servicesAdapter22.notifyDataSetChanged();
                }
                if (z2 && (servicesAdapter2 = this.mServerAdapter) != null) {
                    servicesAdapter2.notifyDataSetChanged();
                }
                this.mLastRefreshTime = elapsedRealtime;
                return;
            }
            if (this.clientRefreshScheduled || this.serverRefreshScheduled) {
                return;
            }
            this.clientRefreshScheduled = z;
            this.serverRefreshScheduled = z2;
            this.mHandler.postDelayed(this.refreshAdapterTask, (MIN_REFRESH_INTERVAL - elapsedRealtime) + j);
            return;
        }
        this.clientRefreshScheduled = this.clientRefreshScheduled || z;
        this.serverRefreshScheduled = this.serverRefreshScheduled || z2;
    }

    private void prepareUploadUi() {
        this.mConnection.remove(KEY_ADAPTER_STATE);
        setDfuProgressVisible(true);
        this.mProgressText.setText(R.string.dfu_status_initializing);
        this.mUploadSpeedSeries.clear();
        this.mUploadAvgSpeedSeries.clear();
        XYPlot xYPlot = this.mProgressGraph;
        xYPlot.setRangeBoundaries(0, 1, BoundaryMode.FIXED);
        xYPlot.redraw();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAdapterConnectedFlag(boolean z) {
        ServicesAdapter2 servicesAdapter2 = this.mAdapter;
        if (servicesAdapter2 != null) {
            servicesAdapter2.setConnected(z);
        }
        ServicesAdapter2 servicesAdapter22 = this.mServerAdapter;
        if (servicesAdapter22 != null) {
            servicesAdapter22.setConnected(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDfuProgressVisible(boolean z) {
        RecyclerView recyclerView = this.mServicesView;
        if (recyclerView != null) {
            recyclerView.setVisibility(z ? 8 : 0);
            this.mProgressContainer.setVisibility(z ? 0 : 8);
            this.mActionMore.setVisibility(z ? 4 : 0);
            if (z) {
                hideMacros();
            } else {
                SlidingPaneLayout slidingPaneLayout = this.mLogPane;
                if (slidingPaneLayout == null || !slidingPaneLayout.isOpen()) {
                    showMacros();
                }
            }
        }
        if (getActivity() != null) {
            getActivity().invalidateOptionsMenu();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBondState(int i) {
        if (i == 11) {
            this.mBondStateView.setText(R.string.status_bonding);
        } else if (i != 12) {
            this.mBondStateView.setText(R.string.status_not_bonded);
        } else {
            this.mBondStateView.setText(R.string.status_bonded);
        }
        if (getActivity() != null) {
            getActivity().invalidateOptionsMenu();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConnectionState(int i, Object... objArr) {
        this.mConnectionStateView.setText(getString(i, objArr));
        if (i == R.string.status_disconnected) {
            this.mActionMore.setVisibility(4);
        }
        if (getActivity() != null) {
            getActivity().invalidateOptionsMenu();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDfuProgress(int i, float f2, float f3, boolean z) {
        updateConnectionState(R.string.status_uploading, new Object[0]);
        this.mBusy.setVisibility(8);
        switch (i) {
            case DfuBaseService.PROGRESS_ABORTED /* -7 */:
                this.mProgressText.setText(R.string.dfu_status_aborted);
                Toast.makeText(requireContext(), R.string.dfu_status_aborted_msg, 0).show();
                this.mHandler.postDelayed(new Runnable() { // from class: no.nordicsemi.android.mcp.connection.d
                    @Override // java.lang.Runnable
                    public final void run() {
                        DeviceDetailsFragment2.this.a();
                    }
                }, 400L);
                return;
            case -6:
                this.mProgressText.setText(R.string.dfu_status_completed);
                Toast.makeText(requireContext(), R.string.dfu_status_completed_msg, 0).show();
                this.mHandler.postDelayed(new Runnable() { // from class: no.nordicsemi.android.mcp.connection.i
                    @Override // java.lang.Runnable
                    public final void run() {
                        DeviceDetailsFragment2.this.b();
                    }
                }, 400L);
                return;
            case -5:
                this.mProgressText.setText(R.string.dfu_status_disconnecting);
                return;
            case -4:
                this.mProgressText.setText(R.string.dfu_status_validating);
                return;
            case -3:
                this.mProgressText.setText(R.string.dfu_status_switching_to_dfu);
                return;
            case -2:
                this.mProgressText.setText(R.string.dfu_status_starting);
                return;
            case -1:
                this.mProgressText.setText(R.string.dfu_status_connecting);
                this.mUploadSpeedSeries.clear();
                this.mUploadAvgSpeedSeries.clear();
                this.mProgressGraph.redraw();
                return;
            default:
                if (z) {
                    this.mProgressText.setText(R.string.dfu_status_error);
                    updateConnectionState(R.string.status_error, e.a.a.a.a.a(i));
                    return;
                }
                this.mProgressText.setText(getString(R.string.progress, Integer.valueOf(i), Float.valueOf(f3)));
                int intValue = this.mProgressGraph.getUserMaxY().intValue();
                int normalizeDfuMaxValue = normalizeDfuMaxValue(f3);
                if (normalizeDfuMaxValue > intValue) {
                    this.mProgressGraph.setRangeBoundaries(0, Integer.valueOf(normalizeDfuMaxValue), BoundaryMode.FIXED);
                }
                this.mUploadSpeedSeries.addLast(Integer.valueOf(i), Float.valueOf(f2));
                this.mUploadAvgSpeedSeries.addLast(Integer.valueOf(i), Float.valueOf(f3));
                this.mProgressGraph.redraw();
                return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0060 A[ExcHandler: DeviceNotConnectedException -> 0x0060, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ boolean a(android.view.MenuItem r5) {
        /*
            r4 = this;
            int r5 = r5.getItemId()
            r0 = 0
            r1 = 0
            r2 = 1
            switch(r5) {
                case 2131296299: goto L5a;
                case 2131296313: goto L54;
                case 2131296348: goto L4e;
                case 2131296352: goto L48;
                case 2131296389: goto L42;
                case 2131296390: goto L3c;
                case 2131296391: goto L36;
                case 2131296400: goto L2a;
                case 2131296409: goto L1e;
                case 2131296855: goto L18;
                case 2131296856: goto L12;
                case 2131296857: goto Lb;
                default: goto La;
            }
        La:
            goto L60
        Lb:
            no.nordicsemi.android.mcp.ble.IBluetoothLeConnection r5 = r4.mConnection     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            r0 = 2
            r5.requestConnectionPriority(r0)     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            return r2
        L12:
            no.nordicsemi.android.mcp.ble.IBluetoothLeConnection r5 = r4.mConnection     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            r5.requestConnectionPriority(r2)     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            return r2
        L18:
            no.nordicsemi.android.mcp.ble.IBluetoothLeConnection r5 = r4.mConnection     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            r5.requestConnectionPriority(r1)     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            return r2
        L1e:
            androidx.fragment.app.c r5 = no.nordicsemi.android.mcp.connection.SetPreferredPhyDialogFragment.getInstance()     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            androidx.fragment.app.l r3 = r4.getChildFragmentManager()     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            r5.show(r3, r0)     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            return r2
        L2a:
            androidx.fragment.app.c r5 = no.nordicsemi.android.mcp.connection.RequestMtuDialogFragment.getInstance()     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            androidx.fragment.app.l r3 = r4.getChildFragmentManager()     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            r5.show(r3, r0)     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            return r2
        L36:
            no.nordicsemi.android.mcp.ble.IBluetoothLeConnection r5 = r4.mConnection     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            r5.readRemoteRssi()     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            return r2
        L3c:
            no.nordicsemi.android.mcp.ble.IBluetoothLeConnection r5 = r4.mConnection     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            r5.readPhy()     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            return r2
        L42:
            no.nordicsemi.android.mcp.ble.IBluetoothLeConnection r5 = r4.mConnection     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            r5.readAllCharacteristics()     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            return r2
        L48:
            no.nordicsemi.android.mcp.ble.IBluetoothLeConnection r5 = r4.mConnection     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            r5.executeReliableWrite()     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            return r2
        L4e:
            no.nordicsemi.android.mcp.ble.IBluetoothLeConnection r5 = r4.mConnection     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            r5.enableAllServices()     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            return r2
        L54:
            no.nordicsemi.android.mcp.ble.IBluetoothLeConnection r5 = r4.mConnection     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            r5.beginReliableWrite()     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            return r2
        L5a:
            no.nordicsemi.android.mcp.ble.IBluetoothLeConnection r5 = r4.mConnection     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            r5.abortReliableWrite()     // Catch: no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException -> L60
            return r2
        L60:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.connection.DeviceDetailsFragment2.a(android.view.MenuItem):boolean");
    }

    public /* synthetic */ void b() {
        setDfuProgressVisible(false);
        this.mUploadSpeedSeries.clear();
        this.mUploadAvgSpeedSeries.clear();
    }

    @Override // no.nordicsemi.android.mcp.connection.MacroFragment
    boolean canShowMacros() {
        SlidingPaneLayout slidingPaneLayout = this.mLogPane;
        return slidingPaneLayout == null || !slidingPaneLayout.isOpen();
    }

    public /* synthetic */ void e(View view) {
        String format = String.format(Locale.US, "Log %1$tF %1$tT.txt", Calendar.getInstance());
        if (Build.VERSION.SDK_INT >= 19) {
            Intent intent = new Intent("android.intent.action.CREATE_DOCUMENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.TITLE", format);
            startActivityForResult(intent, 6);
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
            outputStreamWriter.append((CharSequence) this.mConnection.getLogContent());
            outputStreamWriter.close();
            Uri contentUri = FileHelper.getContentUri(requireContext(), file2);
            if (contentUri != null) {
                FileHelper.showFileNotification(requireContext(), contentUri, "text/plain");
            }
        } catch (Exception unused) {
            Toast.makeText(requireContext(), R.string.file_save_error, 0).show();
        }
    }

    public /* synthetic */ void f(View view) {
        String logContent = this.mConnection.getLogContent();
        if (logContent.length() < 130000) {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setFlags(268435456);
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.TEXT", logContent);
            intent.putExtra("android.intent.extra.SUBJECT", String.format(Locale.US, "Log %1$tF %1$tT", Calendar.getInstance()));
            try {
                startActivity(intent);
                return;
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(requireContext(), R.string.no_uri_application, 0).show();
                return;
            }
        }
        Toast.makeText(requireContext(), R.string.too_long, 0).show();
    }

    public /* synthetic */ void g(View view) {
        try {
            ((ClipboardManager) requireContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(String.format(Locale.US, "Log %1$tF %1$tT", Calendar.getInstance()), this.mConnection.getLogContent()));
            Toast.makeText(getActivity(), R.string.export_clipboard_success, 0).show();
        } catch (Exception unused) {
            Toast.makeText(getActivity(), R.string.export_clipboard_failed, 0).show();
        }
    }

    public /* synthetic */ void h(View view) {
        TabsFragment tabsFragment = (TabsFragment) requireParentFragment();
        Device device = this.mConnection.getDevice();
        String string = PreferenceManager.getDefaultSharedPreferences(requireContext()).getString(SettingsFragment.SETTINGS_LOGGER, SettingsFragment.LOGGER_ASK);
        if (LogProviderUtil.logProviderExists(requireContext()) && !"never".equals(string)) {
            if (SettingsFragment.LOGGER_ASK.equals(string)) {
                SaveLogSessionFragment.getInstance(device, false).show(requireFragmentManager(), (String) null);
                return;
            } else {
                tabsFragment.onSaveLogSession(device, true, false);
                return;
            }
        }
        tabsFragment.onSaveLogSession(device, false, false);
    }

    public /* synthetic */ void i(View view) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.inflate(R.menu.fragment_details_client);
        popupMenu.getMenu().findItem(R.id.action_read_all).setEnabled(this.mConnection.areServicesDiscovered());
        popupMenu.getMenu().findItem(R.id.action_enable_all).setEnabled(this.mConnection.areServicesDiscovered());
        boolean isReliableWriteInProgress = this.mConnection.isReliableWriteInProgress();
        popupMenu.getMenu().findItem(R.id.action_begin_reliable_write).setEnabled(!isReliableWriteInProgress);
        popupMenu.getMenu().findItem(R.id.action_execute_reliable_write).setEnabled(isReliableWriteInProgress);
        popupMenu.getMenu().findItem(R.id.action_abort_reliable_write).setEnabled(isReliableWriteInProgress);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: no.nordicsemi.android.mcp.connection.f
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return DeviceDetailsFragment2.this.a(menuItem);
            }
        });
        popupMenu.show();
    }

    @Override // no.nordicsemi.android.mcp.widget.SelectionListener
    public boolean isActionModeEnabled() {
        return this.actionModeEnabled;
    }

    @Override // a.a.o.b.a
    public boolean onActionItemClicked(a.a.o.b bVar, MenuItem menuItem) {
        SelectedRow selectedRow = (SelectedRow) bVar.f();
        if (selectedRow == null) {
            return false;
        }
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_copy) {
            ((ClipboardManager) requireContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("UUID", selectedRow.uuid.toString()));
            Toast.makeText(requireContext(), R.string.export_clipboard_success, 0).show();
            bVar.a();
            return true;
        }
        if (itemId == R.id.action_edit) {
            EditNameDialogFragment.getInstance(selectedRow.type, selectedRow.uuid, selectedRow.name, selectedRow.format).show(getChildFragmentManager(), (String) null);
            return true;
        }
        if (itemId != R.id.action_share) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setFlags(268435456);
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", selectedRow.text);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(requireContext(), R.string.no_uri_application, 0).show();
        }
        bVar.a();
        return true;
    }

    @Override // no.nordicsemi.android.mcp.connection.MacroFragment, no.nordicsemi.android.mcp.connection.DfuFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        Uri data;
        if (i2 != -1 || i != 6) {
            super.onActivityResult(i, i2, intent);
            return;
        }
        if (intent == null || (data = intent.getData()) == null) {
            return;
        }
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(requireContext().getContentResolver().openOutputStream(data));
            outputStreamWriter.append((CharSequence) this.mConnection.getLogContent());
            outputStreamWriter.close();
            Toast.makeText(requireContext(), R.string.file_saved, 0).show();
        } catch (Exception e2) {
            Log.e(TAG, "Opening file failed", e2);
            Toast.makeText(requireContext(), R.string.file_save_error, 0).show();
        }
    }

    @Override // no.nordicsemi.android.mcp.connection.MacroFragment, no.nordicsemi.android.mcp.MainActivity.OnBackPressedListener
    public boolean onBackPressed() {
        SlidingPaneLayout slidingPaneLayout = this.mLogPane;
        if (slidingPaneLayout != null && slidingPaneLayout.isOpen()) {
            this.mLogPane.closePane();
            return true;
        }
        return super.onBackPressed();
    }

    @Override // no.nordicsemi.android.mcp.widget.connection.CharacteristicView.OnWriteRequestListener
    public void onCharacteristicWriteRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        DialogBuilderFactory.showDialog(this, this.mConnection.getDevice().getAddress(null), bluetoothGattCharacteristic, i);
    }

    @Override // no.nordicsemi.android.mcp.connection.MacroFragment, no.nordicsemi.android.mcp.connection.DfuFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mHandler = new Handler();
        Bundle arguments = getArguments();
        if (arguments.containsKey("auto_connect")) {
            this.mAutoConnect = arguments.getBoolean("auto_connect");
            arguments.remove("auto_connect");
        } else if (bundle != null) {
            this.mAutoConnect = bundle.getBoolean("auto_connect");
        }
        this.mDevice = (Device) arguments.getParcelable("device");
        String address = this.mDevice.getAddress(null);
        this.mDeviceAddress = address;
        this.mDeviceAddressIncremented = address.substring(0, 15) + String.format(Locale.US, "%02X", Integer.valueOf((Integer.valueOf(address.substring(15), 16).intValue() + 1) & 255));
        if (this.mConnection == null) {
            requireActivity().bindService(new Intent(requireContext(), (Class<?>) BluetoothLeService.class), this.mServiceConnection, 0);
        }
    }

    @Override // a.a.o.b.a
    public boolean onCreateActionMode(a.a.o.b bVar, Menu menu) {
        bVar.d().inflate(R.menu.selected_service, menu);
        if (Build.VERSION.SDK_INT >= 21) {
            changeStatusBarColor(getResources().getColor(R.color.actionBarColorDark), getResources().getColor(R.color.actionModeDark));
        }
        ServicesAdapter2 servicesAdapter2 = this.mAdapter;
        if (servicesAdapter2 != null && this.mServerAdapter != null) {
            servicesAdapter2.setEnabled(false);
            this.mServerAdapter.setEnabled(false);
        }
        this.mActionMore.setVisibility(4);
        this.mActionMode = bVar;
        this.actionModeEnabled = true;
        return true;
    }

    @Override // no.nordicsemi.android.mcp.connection.MacroFragment, no.nordicsemi.android.mcp.connection.DfuFragment, a.k.a.a.InterfaceC0025a
    public a.k.b.c onCreateLoader(int i, Bundle bundle) {
        if (i != 1) {
            return super.onCreateLoader(i, bundle);
        }
        String[] strArr = {"_id", LogContract.LogColumns.TIME, LogContract.LogColumns.LEVEL, "data"};
        String[] strArr2 = {String.valueOf(5)};
        if (bundle != null) {
            strArr2[0] = String.valueOf(bundle.getInt(MIN_LEVEL_LOADER_ARG));
        }
        return new a.k.b.b(requireContext(), this.mConnection.getLogSessionEntriesUri(), strArr, "level >= ?", strArr2, "time ASC");
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (getActivity() == null) {
            return;
        }
        menuInflater.inflate(R.menu.fragment_details, menu);
    }

    @Override // androidx.fragment.app.Fragment
    @SuppressLint({"InlinedApi", "NewApi", "RestrictedApi"})
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_device_detail2, viewGroup, false);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        this.mBusy = inflate.findViewById(R.id.busy);
        this.mConnectionStateView = (TextView) inflate.findViewById(R.id.connection_state);
        this.mBondStateView = (TextView) inflate.findViewById(R.id.bond_state);
        this.mLogPane = (SlidingPaneLayout) inflate.findViewById(R.id.slider);
        SlidingPaneLayout slidingPaneLayout = this.mLogPane;
        if (slidingPaneLayout != null) {
            slidingPaneLayout.setSliderFadeColor(0);
            this.mLogPane.setShadowResourceLeft(R.drawable.shadow);
            this.mLogPane.setPanelSlideListener(this);
        }
        this.mLogLevel = (Spinner) inflate.findViewById(R.id.log_level);
        this.mLogLevel.setSelection(defaultSharedPreferences.getInt(PREFS_MIN_LEVEL, 2) == 2 ? 0 : 2, false);
        this.mLogLevel.setOnItemSelectedListener(this.mLevelListener);
        this.mLog = (ListView) inflate.findViewById(R.id.log);
        this.mLog.setEmptyView(inflate.findViewById(R.id.empty));
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(android.R.id.list);
        this.mServicesView = recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new androidx.recyclerview.widget.g(requireContext(), 1));
        recyclerView.setItemAnimator(new androidx.recyclerview.widget.e());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.addOnItemTouchListener(this.touchInterceptor);
        this.mOperationInProgress = inflate.findViewById(R.id.action_in_progress);
        TabLayout tabLayout = (TabLayout) inflate.findViewById(R.id.tab_layout);
        this.mModeTabLayout = tabLayout;
        tabLayout.addOnTabSelectedListener(this.onTabSelectedListener);
        this.mActionMore = inflate.findViewById(R.id.action_more);
        this.mActionMore.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceDetailsFragment2.this.i(view);
            }
        });
        inflate.findViewById(R.id.action_copy).setOnClickListener(this.mActionCopyListener);
        inflate.findViewById(R.id.action_save).setOnClickListener(this.mActionSaveListener);
        inflate.findViewById(R.id.action_share).setOnClickListener(this.mActionShareListener);
        inflate.findViewById(R.id.action_remove).setOnClickListener(this.mActionRemoveListener);
        this.mProgressContainer = inflate.findViewById(R.id.dfu_progress_container);
        this.mProgressText = (TextView) inflate.findViewById(R.id.progress);
        XYPlot xYPlot = (XYPlot) inflate.findViewById(R.id.plot);
        this.mProgressGraph = xYPlot;
        xYPlot.setRangeBoundaries(0, 1, BoundaryMode.FIXED);
        xYPlot.setRangeStepValue(5.0d);
        xYPlot.setDomainBoundaries(0, 100, BoundaryMode.FIXED);
        xYPlot.setDomainStepValue(10.0d);
        ParcelableXYSeries parcelableXYSeries = new ParcelableXYSeries("SPEED");
        this.mUploadSpeedSeries = parcelableXYSeries;
        xYPlot.addSeries(parcelableXYSeries, new LineAndPointFormatter(-4210753, null, 1723842495, null, FillDirection.BOTTOM));
        ParcelableXYSeries parcelableXYSeries2 = new ParcelableXYSeries("AVG SPEED");
        this.mUploadAvgSpeedSeries = parcelableXYSeries2;
        xYPlot.addSeries(parcelableXYSeries2, new LineAndPointFormatter(-16737058, null, null, null));
        ListView listView = this.mLog;
        LogAdapter logAdapter = new LogAdapter(this, this, null);
        this.mLogAdapter = logAdapter;
        listView.setAdapter((ListAdapter) logAdapter);
        updateBondState(this.mDevice.getBluetoothDevice().getBondState());
        a.l.a.a a2 = a.l.a.a.a(requireContext());
        a2.a(this.mConnectionBroadcastReceiver, makeGattUpdateIntentFilter(this.mDevice.getAddress(null)));
        a2.a(this.mDfuBroadcastReceiver, makeDfuIntentFilter());
        setHasOptionsMenu(true);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.widget.connection.CharacteristicView.OnWriteRequestListener
    public void onDescriptorWriteRequest(BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        DialogBuilderFactory.showDialog(this, this.mConnection.getDevice().getAddress(null), bluetoothGattDescriptor, i);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        requireActivity().unbindService(this.mServiceConnection);
        this.mConnectionBroadcastReceiver = null;
        this.mDfuBroadcastReceiver = null;
        super.onDestroy();
    }

    @Override // a.a.o.b.a
    public void onDestroyActionMode(a.a.o.b bVar) {
        this.mActionMode = null;
        this.actionModeEnabled = false;
        this.mCurrentAdapter.clearSelections();
        this.mAdapter.setEnabled(true);
        this.mServerAdapter.setEnabled(true);
        this.mActionMore.setVisibility(this.mConnection.isConnected() ? 0 : 4);
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                changeStatusBarColor(getResources().getColor(R.color.actionModeDark), getResources().getColor(R.color.actionBarColorDark));
            } catch (IllegalStateException unused) {
            }
        }
    }

    @Override // no.nordicsemi.android.mcp.connection.MacroFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        if (this.mConnection != null) {
            ListView listView = this.mLog;
            this.mConnection.put(KEY_LOG_SCROLL_POSITION, Integer.valueOf(listView.getCount() > 0 && listView.getLastVisiblePosition() == listView.getCount() - 1 ? -2 : listView.getFirstVisiblePosition()));
            SlidingPaneLayout slidingPaneLayout = this.mLogPane;
            if (slidingPaneLayout != null) {
                this.mConnection.put(KEY_LOG_OPEN, Boolean.valueOf(slidingPaneLayout.isOpen()));
            }
            if (this.mCurrentAdapter != null) {
                this.mConnection.put(KEY_ADAPTER_STATE, this.mServicesView.getLayoutManager().y());
            }
        }
        a.k.a.a.a(this).a(1);
        this.mModeTabLayout.removeOnTabSelectedListener(this.onTabSelectedListener);
        this.mServicesView.removeOnItemTouchListener(this.touchInterceptor);
        this.mServicesView.setLayoutManager(null);
        this.mServicesView = null;
        a.l.a.a a2 = a.l.a.a.a(requireContext());
        a2.a(this.mDfuBroadcastReceiver);
        a2.a(this.mConnectionBroadcastReceiver);
        this.mLogLevel.setOnItemSelectedListener(null);
        this.mLogLevel = null;
        this.mLogAdapter.onDestroy();
        this.mLogAdapter = null;
        super.onDestroyView();
    }

    @Override // no.nordicsemi.android.mcp.connection.MacroFragment, no.nordicsemi.android.mcp.connection.DfuFragment, a.k.a.a.InterfaceC0025a
    public void onLoadFinished(a.k.b.c cVar, Object obj) {
        if (obj == null) {
            Toast.makeText(requireContext(), R.string.error_loading_file_failed, 0).show();
            return;
        }
        if (cVar.getId() != 1) {
            super.onLoadFinished(cVar, obj);
            return;
        }
        ListView listView = this.mLog;
        int i = this.mLogScrollPosition;
        boolean z = i == -2 || (listView.getCount() > 0 && listView.getLastVisiblePosition() == listView.getCount() - 1);
        this.mLogAdapter.swapCursor((Cursor) obj);
        if (i > -1) {
            listView.setSelectionFromTop(i, 0);
        } else if (z) {
            listView.setSelection(listView.getCount() - 1);
        }
        this.mLogScrollPosition = -1;
    }

    @Override // no.nordicsemi.android.mcp.connection.MacroFragment, no.nordicsemi.android.mcp.connection.DfuFragment, a.k.a.a.InterfaceC0025a
    public void onLoaderReset(a.k.b.c cVar) {
        if (cVar.getId() != 1) {
            super.onLoaderReset(cVar);
            return;
        }
        LogAdapter logAdapter = this.mLogAdapter;
        if (logAdapter != null) {
            logAdapter.swapCursor(null);
        }
    }

    @Override // no.nordicsemi.android.mcp.connection.MacroFragment
    void onMacroStateChanged() {
        super.onMacroStateChanged();
        this.mAdapter.notifyDataSetChanged();
        this.mServerAdapter.notifyDataSetChanged();
    }

    @Override // no.nordicsemi.android.mcp.connection.DfuFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        String str;
        if (this.mConnection == null) {
            Log.e(TAG, "Connection is null. This should never happen!", new NullPointerException("mConnection is null"));
            requireActivity().finish();
            return true;
        }
        switch (menuItem.getItemId()) {
            case R.id.action_autoconnect /* 2131296304 */:
                boolean z = !menuItem.isChecked();
                menuItem.setChecked(z);
                IBluetoothLeConnection iBluetoothLeConnection = this.mConnection;
                this.mAutoConnect = z;
                iBluetoothLeConnection.setAutoConnect(z);
                return true;
            case R.id.action_bond /* 2131296314 */:
                if (ensureNoMacro() && !this.mConnection.createBond()) {
                    Toast.makeText(getActivity(), R.string.operation_failed, 0).show();
                }
                return true;
            case R.id.action_clone /* 2131296326 */:
                String string = getString(R.string.server_name_template, !TextUtils.isEmpty(this.mConnection.getDeviceName()) ? this.mConnection.getDeviceName() : getString(R.string.not_available));
                int i = 0;
                while (true) {
                    if (i == 0) {
                        str = string;
                    } else {
                        str = string + " " + i;
                    }
                    if (!this.mDatabaseHelper.serverConfigurationExists(str)) {
                        List<BluetoothGattService> supportedGattServices = this.mConnection.getSupportedGattServices();
                        int i2 = 0;
                        while (i2 < supportedGattServices.size()) {
                            BluetoothGattService bluetoothGattService = supportedGattServices.get(i2);
                            if (AdoptedServicesHelper.isGenericAccess(bluetoothGattService.getUuid()) || AdoptedServicesHelper.isGenericAttribute(bluetoothGattService.getUuid())) {
                                supportedGattServices.remove(i2);
                                i2--;
                            }
                            i2++;
                        }
                        if (supportedGattServices.isEmpty()) {
                            Toast.makeText(requireContext(), R.string.server_cloning_empty, 0).show();
                            return true;
                        }
                        ServerConfiguration clone = ServerConfiguration.clone(str, supportedGattServices);
                        try {
                            Persister persister = new Persister(new Format(new HyphenStyle()));
                            StringWriter stringWriter = new StringWriter();
                            persister.write(clone, stringWriter);
                            this.mDatabaseHelper.addServerConfiguration(clone.getName(), stringWriter.toString());
                            Toast.makeText(requireContext(), getString(R.string.server_cloning_success, str), 0).show();
                        } catch (Exception e2) {
                            Log.e(TAG, "Error while creating a new configuration", e2);
                            Toast.makeText(requireContext(), R.string.server_cloning_failed, 0).show();
                        }
                        return true;
                    }
                    i++;
                }
                break;
            case R.id.action_connect /* 2131296328 */:
                this.mConnection.connect();
                return true;
            case R.id.action_disconnect /* 2131296340 */:
                this.mConnection.disconnect();
                return true;
            case R.id.action_discover_services /* 2131296341 */:
                if (!ensureNoMacro()) {
                    return true;
                }
                this.mBusy.setVisibility(0);
                try {
                    this.mConnection.discoverServices();
                } catch (Exception unused) {
                }
                return true;
            case R.id.action_eddystone_pair /* 2131296345 */:
                if (!ensureNoMacro()) {
                    return true;
                }
                EddystonePairingDialogFragment.getInstance(this.mDevice).show(getChildFragmentManager(), (String) null);
                return true;
            case R.id.action_parse_known_char /* 2131296385 */:
                boolean z2 = !menuItem.isChecked();
                menuItem.setChecked(z2);
                PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putBoolean(PREFS_PARSE_KNOWN_CHAR, z2).apply();
                notifyDataSetChanged(true, true);
                return true;
            case R.id.action_refresh_cache /* 2131296394 */:
                if (!ensureNoMacro()) {
                    return true;
                }
                try {
                    if (!this.mConnection.refreshCache()) {
                        Toast.makeText(requireContext(), R.string.operation_failed, 0).show();
                    }
                } catch (DeviceNotConnectedAtLeastOnceException unused2) {
                }
                return true;
            case R.id.action_set_preferred_phy /* 2131296409 */:
                IBluetoothLeConnection iBluetoothLeConnection2 = this.mConnection;
                if (iBluetoothLeConnection2 != null && Build.VERSION.SDK_INT >= 26) {
                    SelectPreferredPhyDialogFragment.getInstance(iBluetoothLeConnection2.getPreferredPhy()).show(getChildFragmentManager(), (String) null);
                }
                return true;
            case R.id.action_show_log /* 2131296415 */:
                this.mLogPane.openPane();
                return true;
            case R.id.action_unbind /* 2131296428 */:
                if (ensureNoMacro() && !this.mConnection.removeBond()) {
                    Toast.makeText(getActivity(), R.string.operation_failed, 0).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // a.a.o.b.a
    public boolean onPrepareActionMode(a.a.o.b bVar, Menu menu) {
        Object selectedItem = this.mCurrentAdapter.getSelectedItem();
        boolean z = false;
        if (selectedItem == null) {
            bVar.a();
            return false;
        }
        Integer num = null;
        SelectedRow selectedRow = new SelectedRow();
        if (selectedItem instanceof BluetoothGattService) {
            BluetoothGattService bluetoothGattService = (BluetoothGattService) selectedItem;
            selectedRow.uuid = bluetoothGattService.getUuid();
            selectedRow.type = 1;
            selectedRow.text = GattUtils.getServiceAsString(this.mDatabaseHelper, requireContext(), bluetoothGattService);
            Cursor service = this.mDatabaseHelper.getService(bluetoothGattService.getUuid());
            try {
                if (service.moveToNext()) {
                    String string = service.getString(4);
                    bVar.b(string);
                    int i = service.getInt(3);
                    if (i > 0) {
                        bVar.a(String.format(Locale.US, "0x%04X", Integer.valueOf(i)));
                    } else {
                        bVar.a(bluetoothGattService.getUuid().toString());
                    }
                    selectedRow.name = string;
                    selectedRow.format = null;
                    if (service.getInt(6) == 1) {
                    }
                } else {
                    bVar.b(getString(R.string.service_unknown));
                    bVar.a(bluetoothGattService.getUuid().toString());
                }
                z = true;
            } finally {
                service.close();
            }
        } else {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = (BluetoothGattCharacteristic) selectedItem;
            selectedRow.uuid = bluetoothGattCharacteristic.getUuid();
            selectedRow.type = 2;
            selectedRow.text = GattUtils.getCharacteristicAsString(this.mDatabaseHelper, requireContext(), bluetoothGattCharacteristic);
            Cursor characteristic = this.mDatabaseHelper.getCharacteristic(bluetoothGattCharacteristic.getUuid());
            try {
                if (characteristic.moveToNext()) {
                    String string2 = characteristic.getString(4);
                    bVar.b(string2);
                    int i2 = characteristic.getInt(3);
                    if (i2 > 0) {
                        bVar.a(String.format(Locale.US, "0x%04X", Integer.valueOf(i2)));
                    } else {
                        bVar.a(bluetoothGattCharacteristic.getUuid().toString());
                    }
                    selectedRow.name = string2;
                    if (!characteristic.isNull(6)) {
                        num = Integer.valueOf(characteristic.getInt(6));
                    }
                    selectedRow.format = num;
                    if (characteristic.getInt(7) == 1) {
                    }
                } else {
                    bVar.b(getString(R.string.characteristic_unknown));
                    bVar.a(bluetoothGattCharacteristic.getUuid().toString());
                }
                z = true;
            } finally {
                characteristic.close();
            }
        }
        bVar.a(selectedRow);
        menu.findItem(R.id.action_edit).setVisible(z);
        return true;
    }

    @Override // no.nordicsemi.android.mcp.connection.DfuFragment, androidx.fragment.app.Fragment
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        boolean z = false;
        boolean z2 = this.mConnection != null;
        boolean z3 = z2 && this.mConnection.isDfuInProgress();
        boolean z4 = z2 && this.mConnection.isConnected();
        boolean z5 = z2 && this.mConnection.canDisconnect();
        boolean z6 = z2 && this.mConnection.hasServicesDiscovered();
        boolean z7 = z2 && this.mConnection.hasEddystoneConfigService();
        boolean isBleEnabled = isBleEnabled();
        menu.findItem(R.id.action_autoconnect).setChecked(this.mAutoConnect);
        menu.findItem(R.id.action_connect).setVisible(isBleEnabled && z2 && !z5 && !z3);
        menu.findItem(R.id.action_disconnect).setVisible(isBleEnabled && z2 && z5 && !z3);
        menu.findItem(R.id.action_show_log).setVisible(this.mLogPane != null);
        menu.findItem(R.id.action_eddystone_pair).setVisible(z4 && !z3 && z7);
        menu.findItem(R.id.action_clone).setEnabled(z6);
        MenuItem findItem = menu.findItem(R.id.action_bond);
        MenuItem findItem2 = menu.findItem(R.id.action_unbind);
        IBluetoothLeConnection iBluetoothLeConnection = this.mConnection;
        boolean z8 = iBluetoothLeConnection != null && iBluetoothLeConnection.getDevice().getBluetoothDevice().getBondState() == 12;
        findItem.setVisible((!isBleEnabled || z3 || z8) ? false : true);
        findItem2.setVisible(isBleEnabled && !z3 && z8);
        menu.findItem(R.id.action_parse_known_char).setChecked(PreferenceManager.getDefaultSharedPreferences(getActivity()).getBoolean(PREFS_PARSE_KNOWN_CHAR, true)).setVisible(!z3);
        menu.findItem(R.id.action_discover_services).setVisible(isBleEnabled && z4 && !z3).setTitle(z6 ? R.string.menu_action_discover_services_refresh : R.string.menu_action_discover_services);
        MenuItem findItem3 = menu.findItem(R.id.action_refresh_cache);
        if (isBleEnabled && !z4 && !z3) {
            z = true;
        }
        findItem3.setVisible(z);
    }

    @Override // no.nordicsemi.android.mcp.connection.MacroFragment, no.nordicsemi.android.mcp.connection.DfuFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("auto_connect", this.mAutoConnect);
        bundle.putBoolean(SIS_ACTION_MODE_ENABLED, this.mActionMode != null);
    }

    @Override // no.nordicsemi.android.mcp.connection.DfuFragment
    void onStartDfuUpload(int i, String str, Uri uri, String str2, Uri uri2) {
        if (this.mConnection == null) {
            return;
        }
        prepareUploadUi();
        this.mConnection.startDfuUpload(i, i == 0 ? DfuBaseService.MIME_TYPE_ZIP : DfuBaseService.MIME_TYPE_OCTET_STREAM, str, uri, str2, uri2);
    }

    @Override // no.nordicsemi.android.mcp.connection.DfuFragment
    void onStartMcuMgrImageUpload(String str, Uri uri, FirmwareUpgradeManager.Mode mode) {
        if (this.mConnection == null) {
            return;
        }
        prepareUploadUi();
        this.mConnection.startMcuMgrImageUpload(str, uri, mode);
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewAnimator.ExpandCollapseListener
    public void onViewCollapsed(int i) {
    }

    @Override // no.nordicsemi.android.mcp.connection.MacroFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (bundle != null) {
            this.actionModeEnabled = bundle.getBoolean(SIS_ACTION_MODE_ENABLED);
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.SelectionListener
    public void onViewDeselected() {
        a.a.o.b bVar = this.mActionMode;
        if (bVar != null) {
            bVar.a();
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewAnimator.ExpandCollapseListener
    public void onViewExpanded(int i) {
        RecyclerView recyclerView = this.mServicesView;
        if (recyclerView != null) {
            recyclerView.smoothScrollToPosition(i);
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.SelectionListener
    public void onViewSelected() {
        if (this.mActionMode == null) {
            ((androidx.appcompat.app.e) requireActivity()).startSupportActionMode(this);
        }
    }

    public void refreshAndCloseActionMode() {
        a.a.o.b bVar = this.mActionMode;
        if (bVar != null) {
            bVar.a();
        }
        IBluetoothLeConnection iBluetoothLeConnection = this.mConnection;
        if (iBluetoothLeConnection != null) {
            iBluetoothLeConnection.remove(KEY_ITEM_CHECKED);
        }
        notifyDataSetChanged(true, true);
    }

    @Override // no.nordicsemi.android.mcp.ble.ConnectionListener
    public void requestConnection(ConnectionListener.OnServiceConnectedCallback onServiceConnectedCallback) {
        IBluetoothLeConnection iBluetoothLeConnection = this.mConnection;
        if (iBluetoothLeConnection == null) {
            this.mOnServiceConnectedCallback = onServiceConnectedCallback;
        } else {
            onServiceConnectedCallback.onServiceConnected(iBluetoothLeConnection);
        }
    }

    public void requestMtu(int i) {
        IBluetoothLeConnection iBluetoothLeConnection = this.mConnection;
        if (iBluetoothLeConnection != null) {
            try {
                iBluetoothLeConnection.requestMtu(i);
            } catch (DeviceNotConnectedException unused) {
            }
        }
    }

    public void setPreferredPhy(int i) {
        IBluetoothLeConnection iBluetoothLeConnection = this.mConnection;
        if (iBluetoothLeConnection != null) {
            iBluetoothLeConnection.setPreferredPhy(Integer.valueOf(i));
        }
    }

    public void setPreferredPhy(int i, int i2, int i3) {
        IBluetoothLeConnection iBluetoothLeConnection = this.mConnection;
        if (iBluetoothLeConnection != null) {
            try {
                iBluetoothLeConnection.setPreferredPhy(i, i2, i3);
            } catch (DeviceNotConnectedException unused) {
            }
        }
    }

    public /* synthetic */ void a(ValueAnimator valueAnimator) {
        requireActivity().getWindow().setStatusBarColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    public /* synthetic */ void a() {
        setDfuProgressVisible(false);
        this.mUploadSpeedSeries.clear();
        this.mUploadAvgSpeedSeries.clear();
    }
}
