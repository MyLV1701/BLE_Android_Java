package no.nordicsemi.android.mcp.advertiser;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Build;
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
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.appcompat.app.d;
import java.util.Locale;
import java.util.UUID;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.parser.AdvertisingDataParser;
import no.nordicsemi.android.mcp.ble.parser.gap.servicedata.ServiceDataParser;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.DatabaseUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class EditServiceDataDialogFragment extends androidx.fragment.app.c implements View.OnClickListener {
    private static final String ARG_DESTINATION = "destination";
    private static final String ARG_EIR = "eir";
    private static final String ARG_EIR_TYPE = "eir_type";
    private static final String ARG_POSITION = "position";
    private static final String SIS_UUID = "uuid";
    private KeyListener hexKeyListener = new NumberKeyListener() { // from class: no.nordicsemi.android.mcp.advertiser.EditServiceDataDialogFragment.3
        @Override // android.text.method.NumberKeyListener
        protected char[] getAcceptedChars() {
            return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'a', 'b', 'c', 'd', 'e', 'f'};
        }

        @Override // android.text.method.KeyListener
        public int getInputType() {
            return 528385;
        }
    };
    private View mBugInfoView;
    private EditText mDataView;
    private Button mOkButton;
    private View mPreviewContainer;
    private TextView mPreviewView;
    private byte[] mResult;
    private Cursor mServicesCursor;
    private int mType;
    private String mUuid;
    private AutoCompleteTextView mUuidView;

    public static EditServiceDataDialogFragment getInstance(boolean z, int i, int i2, byte[] bArr) {
        EditServiceDataDialogFragment editServiceDataDialogFragment = new EditServiceDataDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(ARG_DESTINATION, z);
        bundle.putInt(ARG_POSITION, i);
        bundle.putByteArray(ARG_EIR, bArr);
        bundle.putInt(ARG_EIR_TYPE, i2);
        editServiceDataDialogFragment.setArguments(bundle);
        return editServiceDataDialogFragment;
    }

    private boolean isDataValid() {
        String obj = this.mDataView.getText().toString();
        return obj.length() > 0 && obj.length() % 2 == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePreview(String str) {
        String str2;
        int i = 8;
        if (str != null && isDataValid()) {
            String upperCase = this.mDataView.getText().toString().toUpperCase(Locale.US);
            if (str.length() < 16) {
                str2 = "0x" + str.toUpperCase(Locale.US);
            } else {
                str2 = str;
            }
            SpannableString spannableString = new SpannableString(getResources().getString(R.string.key_value, ServiceDataParser.SERVICE_DATA, "UUID: " + str2 + " Data: 0x" + upperCase));
            spannableString.setSpan(new ForegroundColorSpan(-7829368), 0, 13, 0);
            this.mPreviewView.setText(spannableString);
            this.mPreviewContainer.setVisibility(0);
            View view = this.mBugInfoView;
            if (Build.VERSION.SDK_INT < 26 && str.length() > 4) {
                i = 0;
            }
            view.setVisibility(i);
            this.mOkButton.setEnabled(true);
            return;
        }
        this.mPreviewContainer.setVisibility(8);
        this.mOkButton.setEnabled(false);
    }

    public /* synthetic */ Cursor a(DatabaseHelper databaseHelper, CharSequence charSequence) {
        if (charSequence == null) {
            Cursor allServices = databaseHelper.getAllServices();
            this.mServicesCursor = allServices;
            return allServices;
        }
        Cursor services = databaseHelper.getServices(charSequence.toString());
        this.mServicesCursor = services;
        return services;
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

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mUuid = bundle.getString(SIS_UUID);
        }
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_advertisement_edit_service_data, (ViewGroup) null);
        this.mUuidView = (AutoCompleteTextView) inflate.findViewById(R.id.uuid);
        this.mDataView = (EditText) inflate.findViewById(R.id.data);
        this.mPreviewContainer = inflate.findViewById(R.id.preview_container);
        this.mPreviewView = (TextView) inflate.findViewById(R.id.preview);
        this.mBugInfoView = inflate.findViewById(R.id.adv_bug_service_data_16);
        final DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, null, new String[]{"name"}, new int[]{android.R.id.text1}, 0);
        simpleCursorAdapter.setFilterQueryProvider(new FilterQueryProvider() { // from class: no.nordicsemi.android.mcp.advertiser.w
            @Override // android.widget.FilterQueryProvider
            public final Cursor runQuery(CharSequence charSequence) {
                return EditServiceDataDialogFragment.this.a(databaseHelper, charSequence);
            }
        });
        simpleCursorAdapter.setCursorToStringConverter(new SimpleCursorAdapter.CursorToStringConverter() { // from class: no.nordicsemi.android.mcp.advertiser.t
            @Override // android.widget.SimpleCursorAdapter.CursorToStringConverter
            public final CharSequence convertToString(Cursor cursor) {
                CharSequence string;
                string = cursor.getString(4);
                return string;
            }
        });
        this.mUuidView.setAdapter(simpleCursorAdapter);
        this.mUuidView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.u
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                EditServiceDataDialogFragment.this.a(databaseHelper, adapterView, view, i, j);
            }
        });
        inflate.findViewById(R.id.action_clear_uuid).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditServiceDataDialogFragment.this.a(view);
            }
        });
        inflate.findViewById(R.id.action_clear_data).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditServiceDataDialogFragment.this.b(view);
            }
        });
        d.a aVar = new d.a(getContext());
        aVar.c(R.string.adv_new_service_data);
        aVar.b(inflate);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(R.string.ok, null);
        androidx.appcompat.app.d c2 = aVar.c();
        Button b2 = c2.b(-1);
        this.mOkButton = b2;
        b2.setOnClickListener(this);
        this.mDataView.setKeyListener(this.hexKeyListener);
        this.mDataView.addTextChangedListener(new TextWatcher() { // from class: no.nordicsemi.android.mcp.advertiser.EditServiceDataDialogFragment.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                EditServiceDataDialogFragment editServiceDataDialogFragment = EditServiceDataDialogFragment.this;
                editServiceDataDialogFragment.updatePreview(editServiceDataDialogFragment.mUuid);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.mUuidView.addTextChangedListener(new TextWatcher() { // from class: no.nordicsemi.android.mcp.advertiser.EditServiceDataDialogFragment.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                String lowerCase = editable.toString().toLowerCase(Locale.US);
                if (lowerCase.matches("^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$")) {
                    UUID fromString = UUID.fromString(lowerCase);
                    long mostSignificantBits = fromString.getMostSignificantBits();
                    if (((-281470681743361L) & mostSignificantBits) == DatabaseUtils.MSB && fromString.getLeastSignificantBits() == DatabaseUtils.LSB) {
                        lowerCase = fromString.toString().substring(4, 8);
                    } else {
                        if ((mostSignificantBits & 4294967295L) != DatabaseUtils.MSB || fromString.getLeastSignificantBits() != DatabaseUtils.LSB) {
                            EditServiceDataDialogFragment.this.updatePreview(lowerCase);
                            EditServiceDataDialogFragment.this.mUuid = lowerCase;
                            EditServiceDataDialogFragment.this.mResult = ParserUtils.decodeUuid(fromString);
                            EditServiceDataDialogFragment.this.mType = 33;
                            return;
                        }
                        lowerCase = fromString.toString().substring(0, 8);
                    }
                }
                if (lowerCase.matches("^[a-fA-F0-9]{4}$")) {
                    EditServiceDataDialogFragment.this.updatePreview(lowerCase);
                    EditServiceDataDialogFragment.this.mUuid = lowerCase;
                    EditServiceDataDialogFragment.this.mResult = ParserUtils.decodeUuid(lowerCase);
                    EditServiceDataDialogFragment.this.mType = 22;
                    return;
                }
                if (lowerCase.matches("^[a-fA-F0-9]{8}$")) {
                    EditServiceDataDialogFragment.this.updatePreview(lowerCase);
                    EditServiceDataDialogFragment.this.mUuid = lowerCase;
                    EditServiceDataDialogFragment.this.mResult = ParserUtils.decodeUuid(lowerCase);
                    EditServiceDataDialogFragment.this.mType = 32;
                    return;
                }
                EditServiceDataDialogFragment.this.updatePreview(null);
                EditServiceDataDialogFragment.this.mUuid = null;
                EditServiceDataDialogFragment.this.mResult = null;
                EditServiceDataDialogFragment.this.mType = 0;
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        Bundle arguments = getArguments();
        byte[] byteArray = arguments.getByteArray(ARG_EIR);
        int i = arguments.getInt(ARG_EIR_TYPE);
        if (byteArray != null) {
            UUID parseUuid = AdvertisingDataParser.parseUuid(i, byteArray);
            byte[] copyServiceData = AdvertisingDataParser.copyServiceData(i, byteArray);
            if (parseUuid != null) {
                this.mUuidView.setText(parseUuid.toString());
                this.mDataView.setText(ParserUtils.bytesToHex(copyServiceData, false));
            }
        } else {
            this.mOkButton.setEnabled(false);
        }
        return c2;
    }

    @Override // androidx.fragment.app.c, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        Cursor cursor = this.mServicesCursor;
        if (cursor != null) {
            cursor.close();
        }
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(SIS_UUID, this.mUuid);
    }

    public /* synthetic */ void a(DatabaseHelper databaseHelper, AdapterView adapterView, View view, int i, long j) {
        this.mUuidView.setText(databaseHelper.getServiceUuid(j).toString());
    }

    public /* synthetic */ void a(View view) {
        this.mUuidView.setText((CharSequence) null);
    }
}
