package no.nordicsemi.android.mcp.advertiser;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.d;
import java.util.Locale;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.parser.gap.ManufacturerDataParser;
import no.nordicsemi.android.mcp.ble.parser.utils.CompanyIdentifier2;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class EditManufacturerDataDialogFragment extends androidx.fragment.app.c implements View.OnClickListener {
    private static final String ARG_DESTINATION = "destination";
    private static final String ARG_EIR = "eir";
    private static final String ARG_EIR_TYPE = "eir_type";
    private static final String ARG_POSITION = "position";
    private KeyListener hexKeyListener = new NumberKeyListener() { // from class: no.nordicsemi.android.mcp.advertiser.EditManufacturerDataDialogFragment.3
        @Override // android.text.method.NumberKeyListener
        protected char[] getAcceptedChars() {
            return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'a', 'b', 'c', 'd', 'e', 'f'};
        }

        @Override // android.text.method.KeyListener
        public int getInputType() {
            return 528385;
        }
    };
    private EditText mCompanyIdView;
    private EditText mDataView;
    private Button mOkButton;
    private View mPreviewContainer;
    private TextView mPreviewView;
    private byte[] mResult;
    private int mType;

    public static EditManufacturerDataDialogFragment getInstance(boolean z, int i, int i2, byte[] bArr) {
        EditManufacturerDataDialogFragment editManufacturerDataDialogFragment = new EditManufacturerDataDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(ARG_DESTINATION, z);
        bundle.putInt(ARG_POSITION, i);
        bundle.putByteArray(ARG_EIR, bArr);
        bundle.putInt(ARG_EIR_TYPE, i2);
        editManufacturerDataDialogFragment.setArguments(bundle);
        return editManufacturerDataDialogFragment;
    }

    private boolean isDataValid() {
        String obj = this.mDataView.getText().toString();
        return obj.length() > 0 && obj.length() % 2 == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePreview() {
        if (this.mResult != null && isDataValid()) {
            String upperCase = this.mDataView.getText().toString().toUpperCase(Locale.US);
            String withId = CompanyIdentifier2.withId(ParserUtils.decodeUuid16(this.mResult, 0));
            SpannableString spannableString = new SpannableString(getResources().getString(R.string.key_value, ManufacturerDataParser.MANUFACTURER_DATA_4_1, "Company: " + withId + " 0x" + upperCase));
            spannableString.setSpan(new ForegroundColorSpan(-7829368), 0, 39, 0);
            this.mPreviewView.setText(spannableString);
            this.mPreviewContainer.setVisibility(0);
            this.mOkButton.setEnabled(true);
            return;
        }
        this.mPreviewContainer.setVisibility(8);
        this.mOkButton.setEnabled(false);
    }

    public /* synthetic */ void a(View view) {
        this.mCompanyIdView.setText((CharSequence) null);
    }

    public /* synthetic */ void b(View view) {
        this.mDataView.setText((CharSequence) null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ItemEditedListener itemEditedListener = (ItemEditedListener) getParentFragment();
        Bundle arguments = getArguments();
        boolean z = arguments.getBoolean(ARG_DESTINATION);
        int i = arguments.getInt(ARG_POSITION);
        String obj = this.mDataView.getText().toString();
        byte[] bArr = new byte[this.mResult.length + (obj.length() / 2)];
        byte[] bArr2 = this.mResult;
        System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
        ParserUtils.setByteArrayValue(bArr, this.mResult.length, obj);
        itemEditedListener.onItemEdited(z, i, this.mType, bArr);
        dismiss();
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        final View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_advertisement_edit_manufacturer_data, (ViewGroup) null);
        this.mCompanyIdView = (EditText) inflate.findViewById(R.id.company_id);
        this.mDataView = (EditText) inflate.findViewById(R.id.data);
        this.mPreviewContainer = inflate.findViewById(R.id.preview_container);
        this.mPreviewView = (TextView) inflate.findViewById(R.id.preview);
        inflate.findViewById(R.id.action_clear_company_id).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditManufacturerDataDialogFragment.this.a(view);
            }
        });
        inflate.findViewById(R.id.action_info).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                inflate.findViewById(R.id.info).setVisibility(0);
            }
        });
        inflate.findViewById(R.id.action_clear_data).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditManufacturerDataDialogFragment.this.b(view);
            }
        });
        d.a aVar = new d.a(getContext());
        aVar.c(R.string.adv_new_manufacturer_data);
        aVar.b(inflate);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(R.string.ok, null);
        androidx.appcompat.app.d c2 = aVar.c();
        Button b2 = c2.b(-1);
        this.mOkButton = b2;
        b2.setOnClickListener(this);
        this.mCompanyIdView.setKeyListener(this.hexKeyListener);
        this.mDataView.setKeyListener(this.hexKeyListener);
        this.mDataView.addTextChangedListener(new TextWatcher() { // from class: no.nordicsemi.android.mcp.advertiser.EditManufacturerDataDialogFragment.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                EditManufacturerDataDialogFragment.this.updatePreview();
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.mCompanyIdView.addTextChangedListener(new TextWatcher() { // from class: no.nordicsemi.android.mcp.advertiser.EditManufacturerDataDialogFragment.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                String lowerCase = editable.toString().toLowerCase(Locale.US);
                if (!lowerCase.matches("^[a-fA-F0-9]{4}$")) {
                    EditManufacturerDataDialogFragment.this.mResult = null;
                    EditManufacturerDataDialogFragment.this.mType = 0;
                    EditManufacturerDataDialogFragment.this.updatePreview();
                } else {
                    EditManufacturerDataDialogFragment.this.mResult = ParserUtils.decodeUuid(lowerCase);
                    EditManufacturerDataDialogFragment.this.mType = 255;
                    EditManufacturerDataDialogFragment.this.updatePreview();
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        byte[] byteArray = getArguments().getByteArray(ARG_EIR);
        if (byteArray != null) {
            int decodeUuid16 = ParserUtils.decodeUuid16(byteArray, 0);
            String bytesToHex = ParserUtils.bytesToHex(byteArray, 2, byteArray.length - 2, false);
            this.mCompanyIdView.setText(String.format(Locale.US, "%04X", Integer.valueOf(decodeUuid16)));
            this.mDataView.setText(bytesToHex);
        } else {
            b2.setEnabled(false);
        }
        return c2;
    }
}
