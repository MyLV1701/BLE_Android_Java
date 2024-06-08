package no.nordicsemi.android.mcp.widget.connection;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Locale;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.ble.parser.gatt.GattUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.widget.SelectionListener;
import no.nordicsemi.android.mcp.widget.ViewAnimator;
import no.nordicsemi.android.mcp.widget.connection.CharacteristicView;

/* loaded from: classes.dex */
public class ServiceView extends LinearLayout implements View.OnLongClickListener, View.OnClickListener {
    private WeakReference<ViewAnimator> mAnimator;
    private View.OnClickListener mCharacteristicClickListener;
    private View.OnLongClickListener mCharacteristicLongClickListener;
    private ViewGroup mCharacteristicsList;
    private boolean mClient;
    private WeakReference<CharacteristicView.OnConditionalSleepListener> mConditionalSleepListener;
    private boolean mConnected;
    private WeakReference<IBluetoothLeConnection> mConnection;
    private boolean mEnabled;
    private View mMainView;
    private TextView mName;
    private View mNoCharacteristicsView;
    private TextView mPredefinedView;
    private WeakReference<SelectionListener> mSelectionListener;
    public WeakReference<BluetoothGattService> mService;
    private boolean mSystemManaged;
    private TextView mType;
    private TextView mUuid;
    private WeakReference<CharacteristicView.OnWriteRequestListener> mWriteListener;

    public ServiceView(Context context) {
        this(context, null, 0);
    }

    private void setupClickListeners() {
        View findViewById = findViewById(R.id.service_main);
        this.mMainView = findViewById;
        findViewById.setOnLongClickListener(this);
        findViewById.setOnClickListener(this);
    }

    private void setupView() {
        this.mName = (TextView) findViewById(R.id.display_name);
        this.mUuid = (TextView) findViewById(R.id.uuid);
        this.mType = (TextView) findViewById(R.id.type);
        this.mCharacteristicsList = (ViewGroup) findViewById(R.id.characteristics_list);
        this.mNoCharacteristicsView = findViewById(R.id.characteristics_empty_list);
        this.mPredefinedView = (TextView) findViewById(R.id.predefined);
        setupClickListeners();
    }

    private void updateConnectionColor() {
        this.mName.setEnabled(this.mConnected);
    }

    public ViewGroup getCharacteristicsView() {
        return this.mCharacteristicsList;
    }

    public View getMainView() {
        return this.mMainView;
    }

    public BluetoothGattService getService() {
        return this.mService.get();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.mSelectionListener.get().isActionModeEnabled()) {
            if (this.mAnimator.get().isActivated()) {
                this.mAnimator.get().setActivated(false);
                this.mSelectionListener.get().onViewDeselected();
                return;
            }
            return;
        }
        this.mAnimator.get().toggle();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isInEditMode()) {
            return;
        }
        setupView();
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        if (!this.mSelectionListener.get().isActionModeEnabled()) {
            this.mAnimator.get().setActivated(true);
            this.mSelectionListener.get().onViewSelected();
        }
        return true;
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

    public void setConnection(IBluetoothLeConnection iBluetoothLeConnection) {
        this.mConnection = new WeakReference<>(iBluetoothLeConnection);
    }

    public void setOnConditionalSleepListener(CharacteristicView.OnConditionalSleepListener onConditionalSleepListener) {
        this.mConditionalSleepListener = new WeakReference<>(onConditionalSleepListener);
    }

    public void setOnWriteListener(CharacteristicView.OnWriteRequestListener onWriteRequestListener) {
        this.mWriteListener = new WeakReference<>(onWriteRequestListener);
    }

    public void setSelectionListener(SelectionListener selectionListener) {
        this.mSelectionListener = new WeakReference<>(selectionListener);
    }

    public void setService(DatabaseHelper databaseHelper, BluetoothGattService bluetoothGattService, ViewAnimator viewAnimator) {
        this.mService = new WeakReference<>(bluetoothGattService);
        this.mAnimator = new WeakReference<>(viewAnimator);
        setBackgroundColor(getResources().getColor(!this.mSystemManaged ? R.color.listBackgroundNormal : R.color.listBackgroundDisabled));
        Cursor service = databaseHelper.getService(bluetoothGattService.getUuid());
        try {
            if (service.moveToNext()) {
                this.mName.setText(service.getString(4));
                int i = service.getInt(3);
                if (i > 0) {
                    this.mUuid.setText(String.format(Locale.US, "0x%04X", Integer.valueOf(i)));
                } else {
                    this.mUuid.setText(bluetoothGattService.getUuid().toString());
                }
            } else {
                this.mName.setText(getContext().getString(R.string.service_unknown));
                this.mUuid.setText(bluetoothGattService.getUuid().toString());
            }
            this.mType.setText(GattUtils.getTypeAsString(getContext(), bluetoothGattService.getType()));
            updateConnectionColor();
            service.close();
            boolean z = !this.mClient && this.mConnection.get().isPredefinedServerService(bluetoothGattService);
            this.mPredefinedView.setVisibility((z || this.mSystemManaged) ? 0 : 8);
            if (z || this.mSystemManaged) {
                this.mPredefinedView.setText(z ? R.string.server_service_predefined : R.string.server_service_system);
            }
            ViewGroup viewGroup = this.mCharacteristicsList;
            List<BluetoothGattCharacteristic> characteristics = bluetoothGattService.getCharacteristics();
            this.mNoCharacteristicsView.setVisibility(characteristics.isEmpty() ? 0 : 8);
            LayoutInflater from = LayoutInflater.from(getContext());
            int i2 = 1;
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : characteristics) {
                CharacteristicView characteristicView = (CharacteristicView) viewGroup.getChildAt(i2);
                if (characteristicView == null) {
                    characteristicView = (CharacteristicView) from.inflate(R.layout.characteristic_item2, viewGroup, false);
                    characteristicView.setClient(this.mClient);
                    characteristicView.setOnWriteListener(this.mWriteListener);
                    characteristicView.setOnConditionalSleepListener(this.mConditionalSleepListener);
                    characteristicView.setOnLongClickListener(this.mCharacteristicLongClickListener);
                    characteristicView.setOnClickListener(this.mCharacteristicClickListener);
                    viewGroup.addView(characteristicView);
                }
                characteristicView.setPredefined(z || this.mSystemManaged);
                characteristicView.setConnection(this.mConnection);
                characteristicView.setActionsEnabled(this.mEnabled);
                characteristicView.setConnected(this.mConnected);
                characteristicView.setCharacteristic(databaseHelper, bluetoothGattCharacteristic);
                characteristicView.setTag(Integer.valueOf(i2));
                i2++;
            }
            while (i2 < viewGroup.getChildCount()) {
                viewGroup.removeViewAt(i2);
            }
        } catch (Throwable th) {
            service.close();
            throw th;
        }
    }

    public void setSystemManaged(boolean z) {
        this.mSystemManaged = z;
    }

    public ServiceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ServiceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCharacteristicLongClickListener = new View.OnLongClickListener() { // from class: no.nordicsemi.android.mcp.widget.connection.ServiceView.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                if (!((SelectionListener) ServiceView.this.mSelectionListener.get()).isActionModeEnabled()) {
                    ((ViewAnimator) ServiceView.this.mAnimator.get()).setChildActivated(view, ((Integer) view.getTag()).intValue(), true);
                    ((SelectionListener) ServiceView.this.mSelectionListener.get()).onViewSelected();
                }
                return true;
            }
        };
        this.mCharacteristicClickListener = new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.connection.ServiceView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (((SelectionListener) ServiceView.this.mSelectionListener.get()).isActionModeEnabled()) {
                    int intValue = ((Integer) view.getTag()).intValue();
                    if (((ViewAnimator) ServiceView.this.mAnimator.get()).isChildActivated(intValue)) {
                        ((ViewAnimator) ServiceView.this.mAnimator.get()).setChildActivated(view, intValue, false);
                        ((SelectionListener) ServiceView.this.mSelectionListener.get()).onViewDeselected();
                    }
                }
            }
        };
    }
}
