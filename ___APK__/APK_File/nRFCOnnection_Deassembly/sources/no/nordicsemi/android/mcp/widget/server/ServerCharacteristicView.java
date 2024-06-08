package no.nordicsemi.android.mcp.widget.server;

import android.content.Context;
import android.database.Cursor;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import no.nordicsemi.android.mcp.server.domain.Descriptor;

/* loaded from: classes.dex */
public class ServerCharacteristicView extends LinearLayout {
    private Button mActionAddDescriptor;
    private View mActionEdit;
    private Characteristic mCharacteristic;
    private WeakReference<ServerServiceAdapter.ConfigurationListener> mConfigurationListener;
    private ViewGroup mDescriptorsListContainer;
    private TextView mDescriptorsTitle;
    private TextView mName;
    private TextView mProperties;
    private TextView mUuid;
    private TextView mValue;

    public ServerCharacteristicView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private SpannableString format(int i, String str) {
        return format(i, str, false);
    }

    private void setupClickListeners() {
        this.mActionEdit.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.server.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ServerCharacteristicView.this.a(view);
            }
        });
        this.mActionAddDescriptor.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.server.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ServerCharacteristicView.this.b(view);
            }
        });
    }

    private void setupView() {
        this.mName = (TextView) findViewById(R.id.display_name);
        this.mUuid = (TextView) findViewById(R.id.uuid);
        this.mProperties = (TextView) findViewById(R.id.properties);
        this.mValue = (TextView) findViewById(R.id.value);
        this.mActionEdit = findViewById(R.id.action_edit);
        this.mDescriptorsTitle = (TextView) findViewById(R.id.descriptors_title);
        this.mDescriptorsListContainer = (ViewGroup) findViewById(R.id.descriptors_list);
        this.mActionAddDescriptor = (Button) findViewById(R.id.action_add);
        setupClickListeners();
    }

    public /* synthetic */ void a(View view) {
        this.mConfigurationListener.get().onEditCharacteristic(this.mCharacteristic.getService(), this.mCharacteristic);
    }

    public /* synthetic */ void b(View view) {
        this.mConfigurationListener.get().onAddDescriptor(this.mCharacteristic);
    }

    public Characteristic getCharacteristic() {
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

    public void setCharacteristic(DatabaseHelper databaseHelper, Characteristic characteristic, boolean z) {
        this.mCharacteristic = characteristic;
        Cursor characteristic2 = databaseHelper.getCharacteristic(characteristic.getUuid());
        try {
            String name = characteristic.getName();
            if (characteristic2.moveToNext()) {
                if (!TextUtils.isEmpty(name)) {
                    this.mName.setText(name);
                } else {
                    this.mName.setText(characteristic2.getString(4));
                }
                int i = characteristic2.getInt(3);
                if (i > 0) {
                    this.mUuid.setText(format(R.string.characteristic_uuid, String.format(Locale.US, "0x%04X", Integer.valueOf(i))));
                } else {
                    this.mUuid.setText(format(R.string.characteristic_uuid, characteristic.getUuid().toString()));
                }
            } else {
                if (!TextUtils.isEmpty(name)) {
                    this.mName.setText(name);
                } else {
                    this.mName.setText(getContext().getString(R.string.characteristic_unknown));
                }
                this.mUuid.setText(format(R.string.characteristic_uuid, characteristic.getUuid().toString()));
            }
            characteristic2.close();
            this.mProperties.setText(format(R.string.characteristic_properties, GattUtils.getPropertiesAsString(getContext(), characteristic.getProperties())));
            String valueAsString = characteristic.getValueAsString();
            if (!TextUtils.isEmpty(valueAsString)) {
                this.mValue.setText(format(R.string.characteristic_value, valueAsString));
                this.mValue.setVisibility(0);
            } else {
                this.mValue.setVisibility(8);
            }
            this.mName.setEnabled(z && characteristic.isEnabled());
            boolean isPredefined = characteristic.getService().isPredefined();
            this.mActionAddDescriptor.setVisibility(isPredefined ? 8 : 0);
            this.mActionEdit.setVisibility(isPredefined ? 8 : 0);
            List<Descriptor> descriptors = characteristic.getDescriptors();
            this.mDescriptorsTitle.setVisibility(descriptors.isEmpty() ? 8 : 0);
            ViewGroup viewGroup = this.mDescriptorsListContainer;
            LayoutInflater from = LayoutInflater.from(getContext());
            int i2 = 0;
            for (Descriptor descriptor : descriptors) {
                ServerDescriptorView serverDescriptorView = (ServerDescriptorView) viewGroup.getChildAt(i2);
                if (serverDescriptorView == null) {
                    serverDescriptorView = (ServerDescriptorView) from.inflate(R.layout.server_descriptor_item, viewGroup, false);
                    serverDescriptorView.setConfigurationListener(this.mConfigurationListener);
                    viewGroup.addView(serverDescriptorView);
                }
                serverDescriptorView.setDescriptor(databaseHelper, descriptor, z && characteristic.isEnabled());
                serverDescriptorView.setTranslationX(0.0f);
                i2++;
            }
            while (i2 < viewGroup.getChildCount()) {
                viewGroup.removeViewAt(i2);
            }
        } catch (Throwable th) {
            characteristic2.close();
            throw th;
        }
    }

    public void setConfigurationListener(WeakReference<ServerServiceAdapter.ConfigurationListener> weakReference) {
        this.mConfigurationListener = weakReference;
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        super.setOnTouchListener(onTouchListener);
        this.mActionEdit.setOnTouchListener(onTouchListener);
        this.mActionAddDescriptor.setOnTouchListener(onTouchListener);
        ViewGroup viewGroup = this.mDescriptorsListContainer;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setOnTouchListener(onTouchListener);
        }
    }

    private SpannableString format(int i, String str, boolean z) {
        String string = getContext().getString(i);
        SpannableString spannableString = new SpannableString(getContext().getString(R.string.key_value, string, str));
        spannableString.setSpan(new ForegroundColorSpan(!z ? -7829368 : getResources().getColor(R.color.actionBarColorDark)), 0, string.length() + 1, 0);
        return spannableString;
    }
}
