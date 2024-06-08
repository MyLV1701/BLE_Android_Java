package no.nordicsemi.android.mcp.widget.bonded;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import no.nordicsemi.android.mcp.HasTabs;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.widget.SelectionListener;
import no.nordicsemi.android.mcp.widget.ViewSelector;

/* loaded from: classes.dex */
public class BondedDeviceView extends LinearLayout implements View.OnLongClickListener, View.OnClickListener {
    private TextView mAddress;
    private TextView mBondState;
    private View mConnectButton;
    private Device mDevice;
    private WeakReference<BondedDeviceListener> mDeviceListener;
    private TextView mDeviceName;
    private ImageView mIcon;
    private View mMainView;
    private View mOpenButton;
    private WeakReference<SelectionListener> mSelectionListener;
    private WeakReference<HasTabs> mTabsAdapter;
    private WeakReference<ViewSelector> mViewSelector;

    /* loaded from: classes.dex */
    public interface BondedDeviceListener {
        HasTabs getTabsAdapter();

        void onConnect(View view, Device device, boolean z);

        void onUnbound(Device device);
    }

    public BondedDeviceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void setupClickListeners() {
        this.mMainView.setOnLongClickListener(this);
        this.mMainView.setOnClickListener(this);
    }

    private void setupConnectButton() {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.bonded.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BondedDeviceView.this.a(view);
            }
        };
        this.mOpenButton.setOnClickListener(onClickListener);
        this.mConnectButton.setOnClickListener(onClickListener);
        findViewById(R.id.action_connect_more).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.bonded.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BondedDeviceView.this.b(view);
            }
        });
    }

    private void setupView() {
        this.mIcon = (ImageView) findViewById(R.id.icon);
        this.mConnectButton = findViewById(R.id.action_connect);
        this.mOpenButton = findViewById(R.id.action_open);
        this.mDeviceName = (TextView) findViewById(R.id.display_name);
        this.mAddress = (TextView) findViewById(R.id.address);
        this.mBondState = (TextView) findViewById(R.id.bondState);
        this.mMainView = findViewById(R.id.device_main);
        setupConnectButton();
        setupClickListeners();
    }

    public /* synthetic */ void a(View view) {
        if (this.mSelectionListener.get().isActionModeEnabled()) {
            return;
        }
        this.mDeviceListener.get().onConnect(this, this.mDevice, false);
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
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: no.nordicsemi.android.mcp.widget.bonded.b
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return BondedDeviceView.this.a(device, menuItem);
            }
        });
        popupMenu.show();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.mSelectionListener.get().isActionModeEnabled()) {
            boolean isActivated = this.mViewSelector.get().isActivated();
            this.mViewSelector.get().setActivated(!isActivated);
            if (!isActivated) {
                this.mSelectionListener.get().onViewSelected();
            } else {
                this.mSelectionListener.get().onViewDeselected();
            }
        }
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
        this.mViewSelector.get().setActivated(true);
        this.mSelectionListener.get().onViewSelected();
        return true;
    }

    public void setDevice(Device device, ViewSelector viewSelector) {
        this.mDevice = device;
        this.mViewSelector = new WeakReference<>(viewSelector);
        this.mBondState.setText(device.getBondState());
        this.mAddress.setText(device.getAddress(getContext()));
        WeakReference<HasTabs> weakReference = this.mTabsAdapter;
        boolean z = weakReference != null && weakReference.get().isConnectionOpen(device);
        this.mConnectButton.setVisibility(z ? 4 : 0);
        this.mOpenButton.setVisibility(z ? 0 : 8);
        String name = device.getName();
        if (!"egg".equalsIgnoreCase(name) && !"easteregg".equalsIgnoreCase(name) && !"easter egg".equalsIgnoreCase(name)) {
            this.mIcon.setImageResource(R.drawable.ic_device_other);
        } else {
            this.mIcon.setImageResource(R.drawable.ic_device_egg);
        }
        if (TextUtils.isEmpty(name)) {
            this.mDeviceName.setText(R.string.not_available);
        } else {
            this.mDeviceName.setText(name);
        }
    }

    public void setDeviceListener(BondedDeviceListener bondedDeviceListener) {
        this.mDeviceListener = new WeakReference<>(bondedDeviceListener);
        this.mTabsAdapter = new WeakReference<>(bondedDeviceListener.getTabsAdapter());
    }

    public void setSelectionListener(SelectionListener selectionListener) {
        this.mSelectionListener = new WeakReference<>(selectionListener);
    }

    public /* synthetic */ boolean a(Device device, MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_autoconnect) {
            this.mDeviceListener.get().onConnect(this, device, true);
            return true;
        }
        if (itemId != R.id.action_unbind) {
            return false;
        }
        this.mDeviceListener.get().onUnbound(device);
        return true;
    }
}
