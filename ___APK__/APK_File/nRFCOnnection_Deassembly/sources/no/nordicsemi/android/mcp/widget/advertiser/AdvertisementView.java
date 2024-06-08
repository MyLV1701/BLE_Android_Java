package no.nordicsemi.android.mcp.widget.advertiser;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.advertiser.AdvertiserActionListener;
import no.nordicsemi.android.mcp.ble.IBluetoothLeAdvertiser;
import no.nordicsemi.android.mcp.ble.model.AdvData;
import no.nordicsemi.android.mcp.ble.parser.GenericAccessProfileParser;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.widget.AppearanceIconHelper;
import no.nordicsemi.android.mcp.widget.ViewAnimator;

@TargetApi(21)
/* loaded from: classes.dex */
public class AdvertisementView extends LinearLayout implements View.OnClickListener {
    private WeakReference<AdvertiserActionListener> mActionListener;
    private TextView mAddress;
    private final BroadcastReceiver mAdvertisementStateBroadcastReceiver;
    private ViewAnimator mAnimator;
    private DatabaseHelper mDatabaseHelper;
    private AdvertisementDetailsView mDetailsView;
    private TextView mDisplayName;
    private ImageView mIcon;
    private long mId;
    private TextView mInterval;
    private View mMainView;
    private TextView mSecondaryName;
    private TextView mState;
    private DelayedSwitch mSwitch;
    private TextView mTxPower;

    public AdvertisementView(Context context) {
        this(context, null, 0);
    }

    private void setupClickListeners() {
        this.mMainView.setOnClickListener(this);
    }

    private void setupSwitch() {
        this.mSwitch.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.advertiser.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdvertisementView.this.a(view);
            }
        });
    }

    private void setupView() {
        this.mIcon = (ImageView) findViewById(R.id.icon);
        this.mDisplayName = (TextView) findViewById(R.id.display_name);
        this.mSecondaryName = (TextView) findViewById(R.id.name_secondary);
        this.mAddress = (TextView) findViewById(R.id.address);
        this.mSwitch = (DelayedSwitch) findViewById(R.id.action_enable);
        this.mState = (TextView) findViewById(R.id.adv_state);
        this.mInterval = (TextView) findViewById(R.id.adv_interval);
        this.mTxPower = (TextView) findViewById(R.id.adv_tx_power);
        this.mMainView = findViewById(R.id.adv_main);
        this.mDetailsView = (AdvertisementDetailsView) findViewById(R.id.adv_details);
        setupSwitch();
        setupClickListeners();
    }

    public /* synthetic */ void a(View view) {
        if (this.mSwitch.isChecked()) {
            this.mActionListener.get().onStopAdvertisement(this.mId);
        } else {
            this.mActionListener.get().onStartAdvertisement(this.mId);
        }
    }

    public AdvertisementDetailsView getDetailsView() {
        return this.mDetailsView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.mAnimator.toggle();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isInEditMode()) {
            return;
        }
        setupView();
        this.mDatabaseHelper = new DatabaseHelper(getContext());
    }

    public void onViewAttached() {
        a.l.a.a.a(getContext()).a(this.mAdvertisementStateBroadcastReceiver, new IntentFilter(IBluetoothLeAdvertiser.BROADCAST_ADVERTISING_STATE_CHANGED + this.mId));
    }

    public void onViewDetached() {
        a.l.a.a.a(getContext()).a(this.mAdvertisementStateBroadcastReceiver);
    }

    public void setActionListener(AdvertiserActionListener advertiserActionListener) {
        this.mActionListener = new WeakReference<>(advertiserActionListener);
        this.mDetailsView.setActionListener(this.mActionListener);
    }

    public void setBluetoothLeAdvertiser(IBluetoothLeAdvertiser iBluetoothLeAdvertiser) {
        if (iBluetoothLeAdvertiser != null) {
            setState(iBluetoothLeAdvertiser.isAdvertisementActive(this.mId), iBluetoothLeAdvertiser.getAdvertisingTxPower(this.mId));
        }
    }

    public void setData(Cursor cursor, ViewAnimator viewAnimator) {
        boolean z;
        this.mId = cursor.getLong(0);
        this.mAnimator = viewAnimator;
        this.mDisplayName.setText(cursor.getString(1));
        this.mState.setText(cursor.getInt(2) == 1 ? R.string.adv_state_enabled : R.string.adv_state_disabled);
        this.mInterval.setText(getResources().getString(R.string.interval, Integer.valueOf((int) (cursor.getInt(5) * 0.625f))));
        int i = cursor.getInt(6);
        this.mTxPower.setText(getResources().getString(R.string.rssi, Integer.valueOf(i)));
        AdvData advData = new AdvData();
        byte[] blob = cursor.getBlob(11);
        byte[] blob2 = cursor.getBlob(12);
        GenericAccessProfileParser.parse(advData, 2);
        boolean z2 = cursor.getInt(2) == 1;
        boolean z3 = cursor.getInt(3) == 1 && blob2 != null && blob2.length > 0;
        if (Build.VERSION.SDK_INT >= 26) {
            int i2 = cursor.getInt(6);
            boolean z4 = cursor.getInt(7) == 1;
            z = cursor.getInt(8) == 1;
            int i3 = cursor.getInt(9);
            int i4 = cursor.getInt(10);
            GenericAccessProfileParser.parseAdvertisingType(advData, z);
            GenericAccessProfileParser.parse(advData, z, i3, i4, z4 ? Integer.valueOf(i2) : null);
        } else {
            GenericAccessProfileParser.parseAdvertisingType(advData, true);
            z = true;
        }
        if ((z && z3) || z2) {
            GenericAccessProfileParser.parse(this.mDatabaseHelper, advData, new byte[]{2, 1, -1});
        }
        GenericAccessProfileParser.parse(this.mDatabaseHelper, advData, blob, true, i);
        GenericAccessProfileParser.parse(this.mDatabaseHelper, advData, blob2, true, i);
        this.mDetailsView.assign(cursor.getLong(0), advData, viewAnimator);
        AppearanceIconHelper.assign(this.mIcon, this.mDisplayName, this.mSecondaryName, advData.getAppearance(), cursor.getString(1), cursor.getPosition());
        this.mAddress.setText(!(cursor.getInt(4) == 1) ? R.string.adv_random_address : R.string.anonymous);
        viewAnimator.recalculateExpandableViewHeight();
    }

    public void setState(boolean z, int i) {
        this.mTxPower.setEnabled(z);
        this.mInterval.setEnabled(z);
        this.mSwitch.setChecked(z);
        if (i > -128) {
            this.mTxPower.setText(getResources().getString(R.string.rssi, Integer.valueOf(i)));
        }
    }

    public AdvertisementView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AdvertisementView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAdvertisementStateBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.widget.advertiser.AdvertisementView.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                AdvertisementView.this.setState(intent.getBooleanExtra(IBluetoothLeAdvertiser.EXTRA_STATE, false), intent.getIntExtra(IBluetoothLeAdvertiser.EXTRA_TX_POWER, -128));
            }
        };
    }
}
