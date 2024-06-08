package no.nordicsemi.android.mcp.widget.scanner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import no.nordicsemi.android.mcp.HasTabs;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.widget.AppearanceIconHelper;
import no.nordicsemi.android.mcp.widget.SelectionListener;

/* loaded from: classes.dex */
public class DeviceView extends LinearLayout implements View.OnLongClickListener, View.OnClickListener {
    private TextView mAddress;
    private TextView mAdvInterval;
    private WeakReference<DeviceViewAnimator> mAnimator;
    private TextView mBondState;
    private View mConnectButton;
    private DeviceDetailsView mDetailsView;
    private Device mDevice;
    private WeakReference<DeviceListener> mDeviceListener;
    private TextView mDeviceName;
    private TextView mDeviceNameSecondary;
    private View mFavoriteMark;
    private ImageView mIcon;
    private ImageView mIconHover;
    private long mLastDataHash;
    private View mMainView;
    private int mMaxColors;
    private WeakReference<OnFavoriteStateChangeListener> mOnFavoriteStateChangeListener;
    private View mOpenButton;
    private TextView mRSSI;
    private WeakReference<SelectionListener> mSelectionListener;
    private boolean mSlideEnabled;
    private WeakReference<HasTabs> mTabsAdapter;

    /* loaded from: classes.dex */
    public interface DeviceListener {
        public static final String PREFS_CLONE_INFO_SHOWN = "clone_info_shown";

        HasTabs getTabsAdapter();

        void onBond(Device device);

        void onConnect(Device device, boolean z);

        void onSelectPreferredPhy(Device device);

        void onShowCloneInfo();

        void onShowDeviceDetails(Device device);

        void onUnbound(Device device);
    }

    /* loaded from: classes.dex */
    public interface OnFavoriteStateChangeListener {
        void onFavoriteStateChanged(Device device, boolean z);
    }

    public DeviceView(Context context) {
        this(context, null, 0);
    }

    private void setupClickListeners() {
        this.mMainView.setOnLongClickListener(this);
        this.mMainView.setOnClickListener(this);
    }

    private void setupConnectButton() {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceView.this.a(view);
            }
        };
        this.mOpenButton.setOnClickListener(onClickListener);
        this.mConnectButton.setOnClickListener(onClickListener);
        findViewById(R.id.action_connect_more).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceView.this.b(view);
            }
        });
    }

    private void setupFavoriteAction() {
        this.mIcon.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceView.this.c(view);
            }
        });
    }

    private void setupView() {
        this.mIcon = (ImageView) findViewById(R.id.icon);
        this.mIconHover = (ImageView) findViewById(R.id.icon_hover);
        this.mFavoriteMark = findViewById(R.id.favorite);
        this.mConnectButton = findViewById(R.id.action_connect);
        this.mOpenButton = findViewById(R.id.action_open);
        this.mDeviceName = (TextView) findViewById(R.id.display_name);
        this.mDeviceNameSecondary = (TextView) findViewById(R.id.name_secondary);
        this.mAddress = (TextView) findViewById(R.id.address);
        this.mBondState = (TextView) findViewById(R.id.bondState);
        this.mRSSI = (TextView) findViewById(R.id.rssi);
        this.mAdvInterval = (TextView) findViewById(R.id.adv_interval);
        this.mMainView = findViewById(R.id.device_main);
        this.mDetailsView = (DeviceDetailsView) findViewById(R.id.device_details);
        setupFavoriteAction();
        setupConnectButton();
        setupClickListeners();
    }

    public /* synthetic */ void a(View view) {
        if (this.mSelectionListener.get().isActionModeEnabled()) {
            return;
        }
        this.mDeviceListener.get().onConnect(this.mDevice, false);
    }

    public /* synthetic */ void b(View view) {
        if (this.mSelectionListener.get().isActionModeEnabled()) {
            return;
        }
        final Device device = this.mDevice;
        boolean z = device.getBluetoothDevice().getBondState() == 12;
        boolean z2 = device.getBluetoothDevice().getBondState() == 10;
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.inflate(R.menu.connect);
        popupMenu.getMenu().findItem(R.id.action_bond).setVisible(!z).setEnabled(z2);
        popupMenu.getMenu().findItem(R.id.action_unbind).setVisible(z);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.g
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return DeviceView.this.a(device, menuItem);
            }
        });
        popupMenu.show();
    }

    public /* synthetic */ void c(View view) {
        if (this.mDevice.isAnonymous()) {
            return;
        }
        if (!(this.mFavoriteMark.getVisibility() == 0)) {
            this.mFavoriteMark.setVisibility(0);
            this.mFavoriteMark.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_in_top_favorite));
            this.mOnFavoriteStateChangeListener.get().onFavoriteStateChanged(this.mDevice, true);
        } else {
            Animation loadAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_out_top_favorite);
            loadAnimation.setAnimationListener(new AnimationListenerAdapter() { // from class: no.nordicsemi.android.mcp.widget.scanner.DeviceView.1
                @Override // no.nordicsemi.android.mcp.widget.scanner.AnimationListenerAdapter, android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    DeviceView.this.mFavoriteMark.setVisibility(8);
                }
            });
            this.mFavoriteMark.startAnimation(loadAnimation);
            this.mOnFavoriteStateChangeListener.get().onFavoriteStateChanged(this.mDevice, false);
        }
    }

    public DeviceDetailsView getDetailsView() {
        return this.mDetailsView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.mSelectionListener.get().isActionModeEnabled()) {
            boolean isActivated = this.mAnimator.get().isActivated();
            this.mAnimator.get().setActivated(!isActivated);
            if (!isActivated) {
                this.mSelectionListener.get().onViewSelected();
                return;
            } else {
                this.mSelectionListener.get().onViewDeselected();
                return;
            }
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
        if (this.mSelectionListener.get().isActionModeEnabled()) {
            this.mAnimator.get().toggle();
        } else {
            this.mAnimator.get().setActivated(true);
            this.mSelectionListener.get().onViewSelected();
        }
        return true;
    }

    @Override // android.view.View
    public void setActivated(boolean z) {
        super.setActivated(z);
        if (this.mSlideEnabled) {
            this.mIconHover.setAlpha(z ? 1.0f : 0.0f);
        }
    }

    public void setDevice(Device device, DeviceViewAnimator deviceViewAnimator) {
        long rawDataHash = device.getRawDataHash();
        this.mBondState.setText(device.getBondState());
        this.mRSSI.setText(getResources().getString(R.string.rssi, Integer.valueOf(device.getRssi())));
        this.mAddress.setText(device.getAddress(getContext()));
        long advInterval = device.getAdvInterval();
        if (advInterval > 0) {
            this.mAdvInterval.setText(getResources().getString(R.string.interval, Long.valueOf(device.getAdvInterval())));
        } else {
            this.mAdvInterval.setText(getResources().getString(R.string.not_available));
        }
        this.mRSSI.setEnabled(device.isInRange());
        this.mAdvInterval.setEnabled(device.isInRange() && advInterval > 0);
        WeakReference<HasTabs> weakReference = this.mTabsAdapter;
        boolean z = weakReference != null && weakReference.get().isConnectionOpen(device);
        this.mConnectButton.setVisibility((!device.isConnectible() || z) ? 4 : 0);
        this.mOpenButton.setVisibility(z ? 0 : 8);
        this.mLastDataHash = rawDataHash;
        this.mDevice = device;
        this.mAnimator = new WeakReference<>(deviceViewAnimator);
        this.mDetailsView.assign(device, deviceViewAnimator);
        this.mIconHover.setImageLevel(deviceViewAnimator.getDeviceIndex() % this.mMaxColors);
        AppearanceIconHelper.assign(this.mIcon, this.mDeviceName, this.mDeviceNameSecondary, device.getAppearance(), device.getName(), deviceViewAnimator.getDeviceIndex());
        deviceViewAnimator.recalculateExpandableViewHeight();
    }

    public void setDeviceListener(DeviceListener deviceListener) {
        this.mDeviceListener = new WeakReference<>(deviceListener);
        this.mDetailsView.setDeviceListener(this.mDeviceListener);
        this.mTabsAdapter = new WeakReference<>(deviceListener.getTabsAdapter());
    }

    public void setFavorite(boolean z) {
        this.mFavoriteMark.setVisibility(z ? 0 : 8);
    }

    public void setOnFavoriteStateChangeListener(OnFavoriteStateChangeListener onFavoriteStateChangeListener) {
        this.mOnFavoriteStateChangeListener = new WeakReference<>(onFavoriteStateChangeListener);
    }

    public void setSelectionListener(SelectionListener selectionListener) {
        this.mSelectionListener = new WeakReference<>(selectionListener);
    }

    public void setSlideEnabled(boolean z) {
        this.mSlideEnabled = z;
    }

    public void setSlideOffset(float f2) {
        if (f2 <= 0.0f || f2 >= 0.5f) {
            WeakReference<DeviceViewAnimator> weakReference = this.mAnimator;
            if (weakReference != null && weakReference.get().isActivated()) {
                this.mIconHover.setAlpha(1.0f);
            } else {
                this.mIconHover.setAlpha(Math.max(0.0f, (2.0f * f2) - 1.0f));
            }
            if (f2 <= 0.0f || f2 >= 1.0f) {
                this.mMainView.setEnabled(!this.mSlideEnabled || f2 == 0.0f);
            }
        }
    }

    public DeviceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DeviceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMaxColors = getResources().getIntArray(R.array.hover_colors).length;
    }

    public /* synthetic */ boolean a(Device device, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_autoconnect /* 2131296304 */:
                this.mDeviceListener.get().onConnect(device, true);
                return true;
            case R.id.action_bond /* 2131296314 */:
                this.mDeviceListener.get().onBond(device);
                return true;
            case R.id.action_connect_with_phy /* 2131296330 */:
                this.mDeviceListener.get().onSelectPreferredPhy(device);
                return true;
            case R.id.action_unbind /* 2131296428 */:
                this.mDeviceListener.get().onUnbound(device);
                return true;
            default:
                return false;
        }
    }
}
