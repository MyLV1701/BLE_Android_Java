package no.nordicsemi.android.mcp.widget.scanner;

import android.app.Dialog;
import android.bluetooth.le.ScanResult;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.d;
import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Locale;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.advertiser.AdvertiserFragment;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.ble.parser.AdvertisingDataParser;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.widget.scanner.DeviceDetailsView;
import no.nordicsemi.android.mcp.widget.scanner.DeviceView;

/* loaded from: classes.dex */
public class DeviceDetailsView extends LinearLayout {
    private boolean mAdvertiserSupported;
    private Button mCloneAction;
    private Device mDevice;
    private WeakReference<DeviceView.DeviceListener> mDeviceListener;
    private Button mMoreAction;
    private Button mOpenAction;
    private Button mRawAction;
    private Uri mUri;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ChoiceListener implements View.OnClickListener {
        private DialogInterface.OnClickListener clickListener;
        private final DeviceViewAnimator mAnimator;
        private final DataUnion mDataUnion;
        private final TextView mView;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DataUnion dataUnion = this.mDataUnion;
            d.a aVar = new d.a(view.getContext());
            aVar.c(R.string.choice_selector_title);
            aVar.a(dataUnion.getKeys(), dataUnion.getSelectedIndex(), this.clickListener);
            aVar.c();
        }

        private ChoiceListener(TextView textView, DataUnion dataUnion, DeviceViewAnimator deviceViewAnimator) {
            this.clickListener = new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.DeviceDetailsView.ChoiceListener.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    DataUnion dataUnion2 = ChoiceListener.this.mDataUnion;
                    dataUnion2.setSelected(i);
                    DataUnion.Data selectedData = dataUnion2.getSelectedData();
                    SpannableString spannableString = new SpannableString(ChoiceListener.this.mView.getResources().getString(R.string.key_value, selectedData.key, selectedData.value));
                    spannableString.setSpan(new ForegroundColorSpan(ChoiceListener.this.mView.getResources().getColor(R.color.variant)), 0, selectedData.key.length() + 1, 0);
                    ChoiceListener.this.mView.setText(spannableString);
                    ChoiceListener.this.mAnimator.recalculateExpandableViewHeight();
                }
            };
            this.mView = textView;
            this.mDataUnion = dataUnion;
            this.mAnimator = deviceViewAnimator;
        }
    }

    /* loaded from: classes.dex */
    public static class RawDataDialogFragment extends androidx.fragment.app.c {
        private static final String ARG_DEVICE = "device";

        /* JADX INFO: Access modifiers changed from: private */
        public static androidx.fragment.app.c getInstance(Device device) {
            RawDataDialogFragment rawDataDialogFragment = new RawDataDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("device", device);
            rawDataDialogFragment.setArguments(bundle);
            return rawDataDialogFragment;
        }

        public /* synthetic */ void a(Device device, View view) {
            ((ClipboardManager) getActivity().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(String.format(Locale.US, "Log %1$tF %1$tT - Bluetooth LE Scanner", Calendar.getInstance()), device.getRawDataAsString()));
            Toast.makeText(getActivity(), R.string.export_clipboard_success, 0).show();
        }

        @Override // androidx.fragment.app.c
        public Dialog onCreateDialog(Bundle bundle) {
            LayoutInflater from = LayoutInflater.from(getActivity());
            final Device device = (Device) getArguments().getParcelable("device");
            View inflate = from.inflate(R.layout.dialog_raw_data, (ViewGroup) null, false);
            ImageButton imageButton = (ImageButton) inflate.findViewById(R.id.action_copy);
            TextView textView = (TextView) inflate.findViewById(R.id.data);
            TableLayout tableLayout = (TableLayout) inflate.findViewById(R.id.table);
            textView.setText(device.getRawDataAsString());
            imageButton.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DeviceDetailsView.RawDataDialogFragment.this.a(device, view);
                }
            });
            String[] rawDataDetails = device.getRawDataDetails();
            for (int i = 0; i < rawDataDetails.length; i += 3) {
                View inflate2 = from.inflate(R.layout.dialog_raw_data_item, (ViewGroup) tableLayout, false);
                TextView textView2 = (TextView) inflate2.findViewById(R.id.length);
                TextView textView3 = (TextView) inflate2.findViewById(R.id.type);
                TextView textView4 = (TextView) inflate2.findViewById(R.id.value);
                textView2.setText(rawDataDetails[i]);
                textView3.setText(rawDataDetails[i + 1]);
                textView4.setText(rawDataDetails[i + 2]);
                tableLayout.addView(inflate2);
            }
            d.a aVar = new d.a(requireActivity());
            aVar.b(inflate);
            aVar.c(R.string.ok, null);
            return aVar.c();
        }
    }

    public DeviceDetailsView(Context context) {
        super(context);
        setOrientation(1);
    }

    private void setupCloneAction() {
        if (this.mAdvertiserSupported) {
            this.mCloneAction.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DeviceDetailsView.this.a(view);
                }
            });
        } else {
            this.mCloneAction.setVisibility(8);
        }
    }

    private void setupMoreAction() {
        this.mMoreAction.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceDetailsView.this.b(view);
            }
        });
    }

    private void setupOpenAction() {
        this.mOpenAction.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceDetailsView.this.c(view);
            }
        });
    }

    private void setupRawAction() {
        this.mRawAction.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceDetailsView.this.d(view);
            }
        });
    }

    private void setupView() {
        this.mOpenAction = (Button) findViewById(R.id.action_visit);
        this.mCloneAction = (Button) findViewById(R.id.action_clone);
        this.mRawAction = (Button) findViewById(R.id.action_raw_data);
        this.mMoreAction = (Button) findViewById(R.id.action_more);
        this.mAdvertiserSupported = PreferenceManager.getDefaultSharedPreferences(getContext()).contains(AdvertiserFragment.PREFS_ADVERTISER_SUPPORTED);
        setupOpenAction();
        setupCloneAction();
        setupRawAction();
        setupMoreAction();
    }

    public /* synthetic */ void a(View view) {
        boolean z;
        boolean z2;
        boolean z3;
        int i;
        boolean z4;
        int i2;
        int i3;
        ScanResult lastScanResult;
        boolean z5;
        int i4;
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        String string = getContext().getString(R.string.adv_name_template, !TextUtils.isEmpty(this.mDevice.getName()) ? this.mDevice.getName() : getContext().getString(R.string.not_available));
        if (Build.VERSION.SDK_INT >= 26) {
            ScanResult lastScanResult2 = this.mDevice.getLastScanResult();
            z = lastScanResult2 == null || lastScanResult2.isLegacy();
        } else {
            z = true;
        }
        byte[] rawData = this.mDevice.getPacketsHistory().get(0).getRawData();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        AdvertisingDataParser.clone(rawData, byteArrayOutputStream, byteArrayOutputStream2, z);
        boolean z6 = byteArrayOutputStream2.toByteArray().length > 0;
        if (Build.VERSION.SDK_INT < 26 || (lastScanResult = this.mDevice.getLastScanResult()) == null) {
            z2 = z6;
            z3 = z2;
            i = -7;
            z4 = false;
            i2 = 1;
            i3 = 1;
        } else {
            boolean isConnectable = lastScanResult.isConnectable();
            z3 = (z && isConnectable) || byteArrayOutputStream2.toByteArray().length > 0;
            if (lastScanResult.getTxPower() != 127) {
                i4 = lastScanResult.getTxPower();
                z5 = true;
            } else {
                z5 = false;
                i4 = -7;
            }
            int primaryPhy = lastScanResult.getPrimaryPhy();
            i3 = lastScanResult.getSecondaryPhy();
            i = i4;
            i2 = primaryPhy;
            z4 = z5;
            z2 = isConnectable;
        }
        long addAdvertisingPacket = databaseHelper.addAdvertisingPacket(string, z2, z3, 400, i, byteArrayOutputStream.toByteArray(), byteArrayOutputStream2.toByteArray());
        if (Build.VERSION.SDK_INT >= 26) {
            databaseHelper.updateAdvertisingPacket(addAdvertisingPacket, z, z4, this.mDevice.isAnonymous(), i2, i3);
        }
        if (!PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(DeviceView.DeviceListener.PREFS_CLONE_INFO_SHOWN, false)) {
            this.mDeviceListener.get().onShowCloneInfo();
        } else {
            Toast.makeText(getContext(), R.string.adv_alert_cloned_toast, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void assign(Device device, DeviceViewAnimator deviceViewAnimator) {
        this.mDevice = device;
        this.mUri = null;
        for (int i = 0; i < getChildCount() - 1; i++) {
            TextView textView = (TextView) getChildAt(i);
            if (textView.getVisibility() == 8) {
                break;
            }
            textView.setVisibility(8);
            textView.setText((CharSequence) null);
            textView.setOnClickListener(null);
            textView.setClickable(false);
        }
        for (int i2 = 0; i2 < device.getInfoSize() && i2 < getChildCount() - 1; i2++) {
            try {
                DataUnion info = device.getInfo(i2);
                DataUnion.Data selectedData = info.getSelectedData();
                TextView textView2 = (TextView) getChildAt(i2);
                SpannableString spannableString = new SpannableString(getResources().getString(R.string.key_value, selectedData.key, selectedData.value));
                if (info.isMultiple()) {
                    spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.variant)), 0, selectedData.key.length() + 1, 0);
                    textView2.setOnClickListener(new ChoiceListener(textView2, info, deviceViewAnimator));
                } else {
                    spannableString.setSpan(new ForegroundColorSpan(-7829368), 0, selectedData.key.length() + 1, 0);
                    textView2.setOnClickListener(null);
                    textView2.setClickable(false);
                }
                textView2.setText(spannableString);
                textView2.setVisibility(0);
                if (selectedData.uri != null) {
                    this.mUri = Uri.parse(selectedData.uri);
                }
            } catch (Exception e2) {
                throw new RuntimeException(this.mDevice.toString(), e2);
            }
        }
        this.mOpenAction.setVisibility(this.mUri == null ? 8 : 0);
    }

    public /* synthetic */ void b(View view) {
        this.mDeviceListener.get().onShowDeviceDetails(this.mDevice);
    }

    public /* synthetic */ void c(View view) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", this.mUri);
            intent.addFlags(268435456);
            view.getContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(view.getContext(), R.string.no_uri_application, 0).show();
        }
    }

    public /* synthetic */ void d(View view) {
        RawDataDialogFragment.getInstance(this.mDevice).show(((androidx.appcompat.app.e) getContext()).getSupportFragmentManager(), (String) null);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isInEditMode()) {
            return;
        }
        setupView();
    }

    public void setDeviceListener(WeakReference<DeviceView.DeviceListener> weakReference) {
        this.mDeviceListener = weakReference;
    }

    public DeviceDetailsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(1);
    }

    public DeviceDetailsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOrientation(1);
    }
}
