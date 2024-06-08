package no.nordicsemi.android.mcp.widget.server;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Locale;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.parser.gatt.GattUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.server.ServerServiceAdapter;
import no.nordicsemi.android.mcp.server.domain.Characteristic;
import no.nordicsemi.android.mcp.server.domain.Service;
import no.nordicsemi.android.mcp.widget.ViewAnimator;

/* loaded from: classes.dex */
public class ServerServiceView extends LinearLayout implements View.OnClickListener {
    private Button mActionAddCharacteristic;
    private View mActionEdit;
    private WeakReference<ViewAnimator> mAnimator;
    private ViewGroup mCharacteristicsList;
    private ViewGroup mCharacteristicsListContainer;
    private WeakReference<ServerServiceAdapter.ConfigurationListener> mConfigurationListener;
    private View mMainView;
    private TextView mName;
    private View mPredefined;
    private WeakReference<Service> mService;
    private View mSwipedView;
    private TextView mType;
    private TextView mUuid;
    private final View.OnTouchListener releaseSwipedViewListener;
    private final View.OnTouchListener setSwipedViewListener;

    public ServerServiceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.releaseSwipedViewListener = new View.OnTouchListener() { // from class: no.nordicsemi.android.mcp.widget.server.ServerServiceView.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ServerServiceView.this.mSwipedView = null;
                return false;
            }
        };
        this.setSwipedViewListener = new View.OnTouchListener() { // from class: no.nordicsemi.android.mcp.widget.server.ServerServiceView.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (view instanceof Button) {
                    ServerServiceView.this.mSwipedView = (View) view.getParent().getParent();
                    return false;
                }
                if (!(view instanceof ImageButton)) {
                    ServerServiceView.this.mSwipedView = view;
                    return true;
                }
                ServerServiceView.this.mSwipedView = (View) view.getParent().getParent();
                return false;
            }
        };
    }

    private void setupClickListeners() {
        setOnTouchListener(this.releaseSwipedViewListener);
        View findViewById = findViewById(R.id.service_main);
        this.mMainView = findViewById;
        findViewById.setOnClickListener(this);
        findViewById.setOnTouchListener(this.releaseSwipedViewListener);
        this.mActionAddCharacteristic.setOnTouchListener(this.releaseSwipedViewListener);
        this.mActionAddCharacteristic.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.server.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ServerServiceView.this.a(view);
            }
        });
        this.mActionEdit.setOnTouchListener(this.releaseSwipedViewListener);
        this.mActionEdit.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.server.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ServerServiceView.this.b(view);
            }
        });
    }

    private void setupView() {
        this.mName = (TextView) findViewById(R.id.display_name);
        this.mPredefined = findViewById(R.id.predefined);
        this.mUuid = (TextView) findViewById(R.id.uuid);
        this.mType = (TextView) findViewById(R.id.type);
        this.mCharacteristicsList = (ViewGroup) findViewById(R.id.characteristics_list);
        this.mCharacteristicsListContainer = (ViewGroup) findViewById(R.id.characteristics_list_container);
        this.mActionEdit = findViewById(R.id.action_edit);
        this.mActionAddCharacteristic = (Button) findViewById(R.id.action_add);
        setupClickListeners();
    }

    public /* synthetic */ void a(View view) {
        this.mConfigurationListener.get().onAddCharacteristic(this.mService.get());
    }

    public /* synthetic */ void b(View view) {
        this.mConfigurationListener.get().onEditService(this.mService.get());
    }

    public ViewGroup getCharacteristicsView() {
        return this.mCharacteristicsList;
    }

    public View getMainView() {
        return this.mMainView;
    }

    public View getSwipedView() {
        return this.mSwipedView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
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

    public void setConfigurationListener(ServerServiceAdapter.ConfigurationListener configurationListener) {
        this.mConfigurationListener = new WeakReference<>(configurationListener);
    }

    public void setService(DatabaseHelper databaseHelper, Service service, ViewAnimator viewAnimator) {
        this.mService = new WeakReference<>(service);
        this.mAnimator = new WeakReference<>(viewAnimator);
        Cursor service2 = databaseHelper.getService(service.getUuid());
        try {
            String name = service.getName();
            if (service2.moveToNext()) {
                if (!TextUtils.isEmpty(name)) {
                    this.mName.setText(name);
                } else {
                    this.mName.setText(service2.getString(4));
                }
                int i = service2.getInt(3);
                if (i > 0) {
                    this.mUuid.setText(String.format(Locale.US, "0x%04X", Integer.valueOf(i)));
                } else {
                    this.mUuid.setText(service.getUuid().toString());
                }
            } else {
                if (!TextUtils.isEmpty(name)) {
                    this.mName.setText(name);
                } else {
                    this.mName.setText(getContext().getString(R.string.service_unknown));
                }
                this.mUuid.setText(service.getUuid().toString());
            }
            this.mType.setText(GattUtils.getTypeAsString(getContext(), service.getType()));
            service2.close();
            boolean isEnabled = service.isEnabled();
            this.mName.setEnabled(isEnabled);
            boolean isPredefined = service.isPredefined();
            this.mPredefined.setVisibility(isPredefined ? 0 : 8);
            this.mActionAddCharacteristic.setVisibility(isPredefined ? 8 : 0);
            ViewGroup viewGroup = this.mCharacteristicsListContainer;
            List<Characteristic> characteristics = service.getCharacteristics();
            LayoutInflater from = LayoutInflater.from(getContext());
            int i2 = 0;
            for (Characteristic characteristic : characteristics) {
                ServerCharacteristicView serverCharacteristicView = (ServerCharacteristicView) viewGroup.getChildAt(i2);
                if (serverCharacteristicView == null) {
                    serverCharacteristicView = (ServerCharacteristicView) from.inflate(R.layout.server_characteristic_item, viewGroup, false);
                    serverCharacteristicView.setConfigurationListener(this.mConfigurationListener);
                    viewGroup.addView(serverCharacteristicView);
                }
                serverCharacteristicView.setCharacteristic(databaseHelper, characteristic, isEnabled);
                serverCharacteristicView.setOnTouchListener(isPredefined ? null : this.setSwipedViewListener);
                serverCharacteristicView.setTranslationX(0.0f);
                i2++;
            }
            while (i2 < viewGroup.getChildCount()) {
                viewGroup.removeViewAt(i2);
            }
        } catch (Throwable th) {
            service2.close();
            throw th;
        }
    }
}
