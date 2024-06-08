package no.nordicsemi.android.mcp.widget.connection;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.UUID;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException;
import no.nordicsemi.android.mcp.widget.ForegroundLinearLayout;

/* loaded from: classes.dex */
public class CharacteristicView extends ForegroundLinearLayout {
    private static final UUID CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    private View mActionDisableIndications;
    private View mActionDisableNotifications;
    private View mActionEnableIndications;
    private View mActionEnableNotifications;
    private View mActionRead;
    private View mActionSetValue;
    private View mActionSleep;
    private View mActionWrite;
    private BluetoothGattCharacteristic mCharacteristic;
    private boolean mClient;
    private WeakReference<OnConditionalSleepListener> mConditionalSleepListener;
    private boolean mConnected;
    private WeakReference<IBluetoothLeConnection> mConnection;
    private ViewGroup mDescriptorsList;
    private TextView mDescriptorsTitle;
    private boolean mEnabled;
    private TextView mName;
    private boolean mPredefinedServerService;
    private TextView mProperties;
    private Recycler<DescriptorView> mRecycler;
    private TextView mUuid;
    private TextView mValue;
    private WeakReference<OnWriteRequestListener> mWriteListener;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ConditionalSleepListener implements View.OnClickListener {
        private ConditionalSleepListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((OnConditionalSleepListener) CharacteristicView.this.mConditionalSleepListener.get()).onConditionalSleepRequested(CharacteristicView.this.mCharacteristic, !CharacteristicView.this.mClient);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class IndicationListener implements View.OnClickListener {
        private final boolean mEnable;

        IndicationListener(boolean z) {
            this.mEnable = z;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                ((IBluetoothLeConnection) CharacteristicView.this.mConnection.get()).setCharacteristicIndication(CharacteristicView.this.mCharacteristic, this.mEnable);
            } catch (DeviceNotConnectedException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class NotificationsListener implements View.OnClickListener {
        private final boolean mEnable;

        NotificationsListener(boolean z) {
            this.mEnable = z;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                ((IBluetoothLeConnection) CharacteristicView.this.mConnection.get()).setCharacteristicNotification(CharacteristicView.this.mCharacteristic, this.mEnable);
            } catch (DeviceNotConnectedException unused) {
            }
        }
    }

    /* loaded from: classes.dex */
    public interface OnConditionalSleepListener {
        void onConditionalSleepRequested(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z);
    }

    /* loaded from: classes.dex */
    public interface OnWriteRequestListener {
        void onCharacteristicWriteRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i);

        void onDescriptorWriteRequest(BluetoothGattDescriptor bluetoothGattDescriptor, int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ReadListener implements View.OnClickListener {
        private ReadListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                ((IBluetoothLeConnection) CharacteristicView.this.mConnection.get()).readCharacteristic(CharacteristicView.this.mCharacteristic);
            } catch (DeviceNotConnectedException unused) {
            }
        }
    }

    /* loaded from: classes.dex */
    private static class Recycler<T extends DescriptorView> {
        final ArrayList<T> mCachedViews;

        private Recycler() {
            this.mCachedViews = new ArrayList<>();
        }

        public synchronized T getView() {
            int size = this.mCachedViews.size();
            if (size <= 0) {
                return null;
            }
            int i = size - 1;
            T t = this.mCachedViews.get(i);
            this.mCachedViews.remove(i);
            return t;
        }

        public synchronized void recyclerView(T t) {
            this.mCachedViews.add(t);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class WriteListener implements View.OnClickListener {
        private int mAction;

        WriteListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.mAction == 0) {
                this.mAction = CharacteristicView.this.mClient ? 1 : 3;
            }
            ((OnWriteRequestListener) CharacteristicView.this.mWriteListener.get()).onCharacteristicWriteRequest(CharacteristicView.this.mCharacteristic, this.mAction);
        }

        WriteListener(int i) {
            this.mAction = i;
        }
    }

    public CharacteristicView(Context context) {
        this(context, null, 0);
    }

    private SpannableString format(int i, String str) {
        return format(i, str, false);
    }

    private void setActionsEnabledInternal(boolean z, boolean z2) {
        this.mActionSetValue.setEnabled(z);
        this.mActionRead.setEnabled(z);
        this.mActionWrite.setEnabled(z);
        this.mActionEnableNotifications.setEnabled(z);
        this.mActionDisableNotifications.setEnabled(z);
        this.mActionEnableIndications.setEnabled(z);
        this.mActionDisableIndications.setEnabled(z);
    }

    private void setVisibleIfSet(View view, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, boolean z) {
        view.setVisibility((!z || (bluetoothGattCharacteristic.getProperties() & i) <= 0) ? 8 : 0);
    }

    private void setupView() {
        this.mName = (TextView) findViewById(R.id.display_name);
        this.mUuid = (TextView) findViewById(R.id.uuid);
        this.mProperties = (TextView) findViewById(R.id.properties);
        this.mValue = (TextView) findViewById(R.id.value);
        this.mDescriptorsTitle = (TextView) findViewById(R.id.descriptors_title);
        this.mDescriptorsList = (ViewGroup) findViewById(R.id.descriptors_list);
        this.mActionSetValue = findViewById(R.id.action_set_value);
        this.mActionRead = findViewById(R.id.action_read);
        this.mActionWrite = findViewById(R.id.action_write);
        this.mActionSleep = findViewById(R.id.action_sleep);
        this.mActionEnableNotifications = findViewById(R.id.action_start_notifications);
        this.mActionDisableNotifications = findViewById(R.id.action_stop_notifications);
        this.mActionEnableIndications = findViewById(R.id.action_start_indications);
        this.mActionDisableIndications = findViewById(R.id.action_stop_indications);
        this.mActionSleep.setOnClickListener(new ConditionalSleepListener());
        this.mActionSetValue.setOnClickListener(new WriteListener(2));
        this.mActionRead.setOnClickListener(new ReadListener());
        this.mActionWrite.setOnClickListener(new WriteListener());
        this.mActionEnableNotifications.setOnClickListener(new NotificationsListener(true));
        this.mActionDisableNotifications.setOnClickListener(new NotificationsListener(false));
        this.mActionEnableIndications.setOnClickListener(new IndicationListener(true));
        this.mActionDisableIndications.setOnClickListener(new IndicationListener(false));
    }

    public BluetoothGattCharacteristic getCharacteristic() {
        return this.mCharacteristic;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isInEditMode()) {
            return;
        }
        setupView();
    }

    public void setActionsEnabled(boolean z) {
        this.mEnabled = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0206 A[LOOP:0: B:92:0x0200->B:94:0x0206, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x022d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setCharacteristic(no.nordicsemi.android.mcp.database.provider.DatabaseHelper r20, android.bluetooth.BluetoothGattCharacteristic r21) {
        /*
            Method dump skipped, instructions count: 626
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.widget.connection.CharacteristicView.setCharacteristic(no.nordicsemi.android.mcp.database.provider.DatabaseHelper, android.bluetooth.BluetoothGattCharacteristic):void");
    }

    public void setClient(boolean z) {
        this.mClient = z;
    }

    public void setConnected(boolean z) {
        this.mConnected = z;
    }

    public void setConnection(WeakReference<IBluetoothLeConnection> weakReference) {
        this.mConnection = weakReference;
    }

    public void setOnConditionalSleepListener(WeakReference<OnConditionalSleepListener> weakReference) {
        this.mConditionalSleepListener = weakReference;
    }

    public void setOnWriteListener(WeakReference<OnWriteRequestListener> weakReference) {
        this.mWriteListener = weakReference;
    }

    public void setPredefined(boolean z) {
        this.mPredefinedServerService = z;
    }

    public CharacteristicView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private SpannableString format(int i, String str, boolean z) {
        String string = getContext().getString(i);
        SpannableString spannableString = new SpannableString(getContext().getString(R.string.key_value, string, str));
        spannableString.setSpan(new ForegroundColorSpan(!z ? -7829368 : getResources().getColor(R.color.actionBarColorDark)), 0, string.length() + 1, 0);
        return spannableString;
    }

    public CharacteristicView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRecycler = new Recycler<>();
    }
}
