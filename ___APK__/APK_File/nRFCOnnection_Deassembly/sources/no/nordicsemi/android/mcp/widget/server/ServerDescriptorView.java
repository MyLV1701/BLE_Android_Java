package no.nordicsemi.android.mcp.widget.server;

import android.content.Context;
import android.database.Cursor;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Locale;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.server.ServerServiceAdapter;
import no.nordicsemi.android.mcp.server.domain.Descriptor;

/* loaded from: classes.dex */
public class ServerDescriptorView extends RelativeLayout {
    private View mActionEdit;
    private WeakReference<ServerServiceAdapter.ConfigurationListener> mConfigurationListener;
    private Descriptor mDescriptor;
    private TextView mName;
    private TextView mUuid;
    private TextView mValue;

    public ServerDescriptorView(Context context) {
        this(context, null, 0);
    }

    private SpannableString format(int i, String str) {
        String string = getContext().getString(i);
        SpannableString spannableString = new SpannableString(getContext().getString(R.string.key_value, string, str));
        spannableString.setSpan(new ForegroundColorSpan(-7829368), 0, string.length() + 1, 0);
        return spannableString;
    }

    private void setupClickListeners() {
        this.mActionEdit.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.server.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ServerDescriptorView.this.a(view);
            }
        });
    }

    private void setupView() {
        this.mName = (TextView) findViewById(R.id.display_name);
        this.mUuid = (TextView) findViewById(R.id.uuid);
        this.mValue = (TextView) findViewById(R.id.value);
        this.mActionEdit = findViewById(R.id.action_edit);
        setupClickListeners();
    }

    public /* synthetic */ void a(View view) {
        this.mConfigurationListener.get().onEditDescriptor(this.mDescriptor.getCharacteristic(), this.mDescriptor);
    }

    public Descriptor getDescriptor() {
        return this.mDescriptor;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isInEditMode()) {
            return;
        }
        setupView();
    }

    public void setConfigurationListener(WeakReference<ServerServiceAdapter.ConfigurationListener> weakReference) {
        this.mConfigurationListener = weakReference;
    }

    public void setDescriptor(DatabaseHelper databaseHelper, Descriptor descriptor, boolean z) {
        this.mDescriptor = descriptor;
        Cursor descriptor2 = databaseHelper.getDescriptor(descriptor.getUuid());
        try {
            String name = descriptor.getName();
            if (descriptor2.moveToNext()) {
                if (!TextUtils.isEmpty(name)) {
                    this.mName.setText(name);
                } else {
                    this.mName.setText(descriptor2.getString(4));
                }
                int i = descriptor2.getInt(3);
                if (i > 0) {
                    this.mUuid.setText(format(R.string.descriptor_uuid, String.format(Locale.US, "0x%04X", Integer.valueOf(i))));
                } else {
                    this.mUuid.setText(format(R.string.descriptor_uuid, descriptor.getUuid().toString()));
                }
            } else {
                if (!TextUtils.isEmpty(name)) {
                    this.mName.setText(name);
                } else {
                    this.mName.setText(getContext().getString(R.string.descriptor_unknown));
                }
                this.mUuid.setText(format(R.string.descriptor_uuid, descriptor.getUuid().toString()));
            }
            descriptor2.close();
            this.mActionEdit.setVisibility((descriptor.getCharacteristic().getService().isPredefined() || descriptor.isManaged()) ? 8 : 0);
            String valueAsString = descriptor.getValueAsString();
            if (!TextUtils.isEmpty(valueAsString)) {
                this.mValue.setText(format(R.string.characteristic_value, valueAsString));
                this.mValue.setVisibility(0);
            } else {
                this.mValue.setVisibility(8);
            }
            this.mName.setEnabled(z && descriptor.isEnabled());
        } catch (Throwable th) {
            descriptor2.close();
            throw th;
        }
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        if (this.mDescriptor.isManaged()) {
            return;
        }
        super.setOnTouchListener(onTouchListener);
        this.mActionEdit.setOnTouchListener(onTouchListener);
    }

    public ServerDescriptorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ServerDescriptorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
