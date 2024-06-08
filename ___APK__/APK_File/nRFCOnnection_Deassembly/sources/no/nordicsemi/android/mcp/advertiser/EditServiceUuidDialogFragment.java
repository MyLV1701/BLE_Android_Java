package no.nordicsemi.android.mcp.advertiser;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FilterQueryProvider;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.appcompat.app.d;
import java.util.Locale;
import java.util.UUID;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.parser.AdvertisingDataParser;
import no.nordicsemi.android.mcp.ble.parser.gap.ServicesParser;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.DatabaseUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class EditServiceUuidDialogFragment extends androidx.fragment.app.c implements View.OnClickListener {
    private static final String ARG_DESTINATION = "destination";
    private static final String ARG_EIR = "eir";
    private static final String ARG_EIR_TYPE = "eir_type";
    private static final String ARG_POSITION = "position";
    private View mPreviewContainer;
    private TextView mPreviewView;
    private byte[] mResult;
    private Cursor mServicesCursor;
    private int mType;
    private AutoCompleteTextView mUuidView;

    public static EditServiceUuidDialogFragment getInstance(boolean z, int i, int i2, byte[] bArr) {
        EditServiceUuidDialogFragment editServiceUuidDialogFragment = new EditServiceUuidDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(ARG_DESTINATION, z);
        bundle.putInt(ARG_POSITION, i);
        bundle.putByteArray(ARG_EIR, bArr);
        bundle.putInt(ARG_EIR_TYPE, i2);
        editServiceUuidDialogFragment.setArguments(bundle);
        return editServiceUuidDialogFragment;
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

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ItemEditedListener itemEditedListener = (ItemEditedListener) getParentFragment();
        Bundle arguments = getArguments();
        itemEditedListener.onItemEdited(arguments.getBoolean(ARG_DESTINATION), arguments.getInt(ARG_POSITION), this.mType, this.mResult);
        dismiss();
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_advertisement_edit_service_uuid, (ViewGroup) null);
        this.mUuidView = (AutoCompleteTextView) inflate.findViewById(R.id.uuid);
        this.mPreviewContainer = inflate.findViewById(R.id.preview_container);
        this.mPreviewView = (TextView) inflate.findViewById(R.id.preview);
        final DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, null, new String[]{"name"}, new int[]{android.R.id.text1}, 0);
        simpleCursorAdapter.setFilterQueryProvider(new FilterQueryProvider() { // from class: no.nordicsemi.android.mcp.advertiser.y
            @Override // android.widget.FilterQueryProvider
            public final Cursor runQuery(CharSequence charSequence) {
                return EditServiceUuidDialogFragment.this.a(databaseHelper, charSequence);
            }
        });
        simpleCursorAdapter.setCursorToStringConverter(new SimpleCursorAdapter.CursorToStringConverter() { // from class: no.nordicsemi.android.mcp.advertiser.z
            @Override // android.widget.SimpleCursorAdapter.CursorToStringConverter
            public final CharSequence convertToString(Cursor cursor) {
                CharSequence string;
                string = cursor.getString(4);
                return string;
            }
        });
        this.mUuidView.setAdapter(simpleCursorAdapter);
        this.mUuidView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.x
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                EditServiceUuidDialogFragment.this.a(databaseHelper, adapterView, view, i, j);
            }
        });
        inflate.findViewById(R.id.action_clear_uuid).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditServiceUuidDialogFragment.this.a(view);
            }
        });
        d.a aVar = new d.a(getContext());
        aVar.c(R.string.adv_new_service_uuid);
        aVar.b(inflate);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(R.string.ok, null);
        androidx.appcompat.app.d c2 = aVar.c();
        final Button b2 = c2.b(-1);
        b2.setOnClickListener(this);
        this.mUuidView.addTextChangedListener(new TextWatcher() { // from class: no.nordicsemi.android.mcp.advertiser.EditServiceUuidDialogFragment.1
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
                            EditServiceUuidDialogFragment.this.mPreviewContainer.setVisibility(0);
                            SpannableString spannableString = new SpannableString(EditServiceUuidDialogFragment.this.getResources().getString(R.string.key_value, ServicesParser.COMPLETE_128, fromString.toString()));
                            spannableString.setSpan(new ForegroundColorSpan(-7829368), 0, 39, 0);
                            EditServiceUuidDialogFragment.this.mPreviewView.setText(spannableString);
                            b2.setEnabled(true);
                            EditServiceUuidDialogFragment.this.mResult = ParserUtils.decodeUuid(fromString);
                            EditServiceUuidDialogFragment.this.mType = 7;
                            return;
                        }
                        lowerCase = fromString.toString().substring(0, 8);
                    }
                }
                if (lowerCase.matches("^[a-fA-F0-9]{4}$")) {
                    EditServiceUuidDialogFragment.this.mPreviewContainer.setVisibility(0);
                    SpannableString spannableString2 = new SpannableString(EditServiceUuidDialogFragment.this.getResources().getString(R.string.key_value, ServicesParser.COMPLETE_16, "0x" + lowerCase));
                    spannableString2.setSpan(new ForegroundColorSpan(-7829368), 0, 38, 0);
                    EditServiceUuidDialogFragment.this.mPreviewView.setText(spannableString2);
                    EditServiceUuidDialogFragment.this.mResult = ParserUtils.decodeUuid(lowerCase);
                    b2.setEnabled(true);
                    EditServiceUuidDialogFragment.this.mType = 3;
                    return;
                }
                if (lowerCase.matches("^[a-fA-F0-9]{8}$")) {
                    EditServiceUuidDialogFragment.this.mPreviewContainer.setVisibility(0);
                    SpannableString spannableString3 = new SpannableString(EditServiceUuidDialogFragment.this.getResources().getString(R.string.key_value, ServicesParser.COMPLETE_32, "0x" + lowerCase));
                    spannableString3.setSpan(new ForegroundColorSpan(-7829368), 0, 38, 0);
                    EditServiceUuidDialogFragment.this.mPreviewView.setText(spannableString3);
                    b2.setEnabled(true);
                    EditServiceUuidDialogFragment.this.mResult = ParserUtils.decodeUuid(lowerCase);
                    EditServiceUuidDialogFragment.this.mType = 5;
                    return;
                }
                EditServiceUuidDialogFragment.this.mPreviewContainer.setVisibility(8);
                b2.setEnabled(false);
                EditServiceUuidDialogFragment.this.mResult = null;
                EditServiceUuidDialogFragment.this.mType = 0;
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
        if (byteArray != null && (byteArray.length == 2 || byteArray.length == 4 || byteArray.length == 16)) {
            UUID parseUuid = AdvertisingDataParser.parseUuid(i, byteArray);
            if (parseUuid != null) {
                this.mUuidView.setText(parseUuid.toString());
            }
        } else {
            b2.setEnabled(false);
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

    public /* synthetic */ void a(DatabaseHelper databaseHelper, AdapterView adapterView, View view, int i, long j) {
        this.mUuidView.setText(databaseHelper.getServiceUuid(j).toString());
    }

    public /* synthetic */ void a(View view) {
        this.mUuidView.setText((CharSequence) null);
    }
}
