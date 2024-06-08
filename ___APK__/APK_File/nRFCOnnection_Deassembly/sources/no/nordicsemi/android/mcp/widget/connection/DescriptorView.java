package no.nordicsemi.android.mcp.widget.connection;

import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Locale;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.ble.parser.DescriptorParser;
import no.nordicsemi.android.mcp.connection.DeviceDetailsFragment2;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException;
import no.nordicsemi.android.mcp.widget.connection.CharacteristicView;

/* loaded from: classes.dex */
public class DescriptorView extends LinearLayout {
    private View mActionRead;
    private View mActionSetValue;
    private View mActionWrite;
    private boolean mClient;
    private boolean mConnected;
    private WeakReference<IBluetoothLeConnection> mConnection;
    private BluetoothGattDescriptor mDescriptor;
    private boolean mEnabled;
    private TextView mName;
    private boolean mPredefinedServerService;
    private TextView mUuid;
    private TextView mValue;
    private WeakReference<CharacteristicView.OnWriteRequestListener> mWriteListener;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ReadDescriptorListener implements View.OnClickListener {
        private ReadDescriptorListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                ((IBluetoothLeConnection) DescriptorView.this.mConnection.get()).readDescriptor(DescriptorView.this.mDescriptor);
            } catch (DeviceNotConnectedException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class WriteDescriptorListener implements View.OnClickListener {
        private WriteDescriptorListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((CharacteristicView.OnWriteRequestListener) DescriptorView.this.mWriteListener.get()).onDescriptorWriteRequest(DescriptorView.this.mDescriptor, DescriptorView.this.mClient ? 1 : 2);
        }
    }

    public DescriptorView(Context context) {
        this(context, null, 0);
    }

    private SpannableString format(int i, String str) {
        String string = getContext().getString(i);
        SpannableString spannableString = new SpannableString(getContext().getString(R.string.key_value, string, str));
        spannableString.setSpan(new ForegroundColorSpan(-7829368), 0, string.length() + 1, 0);
        return spannableString;
    }

    private void setupView() {
        this.mName = (TextView) findViewById(R.id.display_name);
        this.mUuid = (TextView) findViewById(R.id.uuid);
        this.mValue = (TextView) findViewById(R.id.value);
        this.mActionRead = findViewById(R.id.action_read);
        this.mActionWrite = findViewById(R.id.action_write);
        this.mActionSetValue = findViewById(R.id.action_set_value);
        this.mActionRead.setOnClickListener(new ReadDescriptorListener());
        this.mActionWrite.setOnClickListener(new WriteDescriptorListener());
        this.mActionSetValue.setOnClickListener(new WriteDescriptorListener());
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

    public void setClient(boolean z) {
        this.mClient = z;
    }

    public void setConnected(boolean z) {
        this.mConnected = z;
    }

    public void setConnection(WeakReference<IBluetoothLeConnection> weakReference) {
        this.mConnection = weakReference;
    }

    public void setDescriptor(DatabaseHelper databaseHelper, BluetoothGattDescriptor bluetoothGattDescriptor) {
        boolean z;
        this.mDescriptor = bluetoothGattDescriptor;
        Context context = getContext();
        boolean z2 = true;
        boolean z3 = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(DeviceDetailsFragment2.PREFS_PARSE_KNOWN_CHAR, true);
        boolean z4 = this.mConnected;
        boolean z5 = this.mEnabled;
        Cursor descriptor = databaseHelper.getDescriptor(bluetoothGattDescriptor.getUuid());
        try {
            Integer num = null;
            if (descriptor.moveToNext()) {
                this.mName.setText(descriptor.getString(4));
                int i = descriptor.getInt(3);
                if (i > 0) {
                    this.mUuid.setText(format(R.string.descriptor_uuid, String.format(Locale.US, "0x%04X", Integer.valueOf(i))));
                    z = i == 10498;
                    if ((z3 && i == 10498) || i == 10496 || (i >= 10500 && i <= 10504)) {
                        z2 = false;
                    }
                } else {
                    this.mUuid.setText(format(R.string.descriptor_uuid, bluetoothGattDescriptor.getUuid().toString()));
                    z = false;
                }
                if (!descriptor.isNull(6)) {
                    num = Integer.valueOf(descriptor.getInt(6));
                }
            } else {
                this.mName.setText(context.getString(R.string.descriptor_unknown));
                this.mUuid.setText(format(R.string.descriptor_uuid, bluetoothGattDescriptor.getUuid().toString()));
                z = false;
            }
            this.mName.setEnabled(this.mConnected);
            descriptor.close();
            String valueAsString = DescriptorParser.getValueAsString(bluetoothGattDescriptor, num, z3);
            TextView textView = this.mValue;
            if (valueAsString == null) {
                valueAsString = "";
            }
            textView.setText(format(R.string.descriptor_value, valueAsString));
            int i2 = 8;
            this.mValue.setVisibility(bluetoothGattDescriptor.getValue() == null ? 8 : 0);
            this.mActionRead.setEnabled(z5);
            this.mActionWrite.setEnabled(z5);
            this.mActionSetValue.setEnabled(z5);
            this.mActionRead.setVisibility((z4 && this.mClient) ? 0 : 8);
            this.mActionWrite.setVisibility((z4 && this.mClient && z2) ? 0 : 8);
            View view = this.mActionSetValue;
            if (z4 && !this.mPredefinedServerService && !this.mClient && (!z3 || !z)) {
                i2 = 0;
            }
            view.setVisibility(i2);
        } catch (Throwable th) {
            descriptor.close();
            throw th;
        }
    }

    public void setOnWriteListener(WeakReference<CharacteristicView.OnWriteRequestListener> weakReference) {
        this.mWriteListener = weakReference;
    }

    public void setPredefined(boolean z) {
        this.mPredefinedServerService = z;
    }

    public DescriptorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DescriptorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
