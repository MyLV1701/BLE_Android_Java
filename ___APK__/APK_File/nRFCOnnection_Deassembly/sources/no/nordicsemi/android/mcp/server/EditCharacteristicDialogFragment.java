package no.nordicsemi.android.mcp.server;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.appcompat.app.d;
import androidx.appcompat.widget.SwitchCompat;
import java.util.UUID;
import java.util.regex.Pattern;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.server.ServerServiceAdapter;
import no.nordicsemi.android.mcp.server.domain.Characteristic;
import no.nordicsemi.android.mcp.server.domain.Service;

/* loaded from: classes.dex */
public class EditCharacteristicDialogFragment extends androidx.fragment.app.c {
    private static final String ARG_CHARACTERISTIC = "characteristic";
    private static final String ARG_SERVICE_INTERNAL_ID = "service_internal_id";
    private Cursor mCharacteristicsCursor;
    private DatabaseHelper mDatabaseHelper;
    private AutoCompleteTextView mNameView;
    private CheckBox mPermissionRead;
    private CheckBox mPermissionReadEncrypted;
    private CheckBox mPermissionReadEncryptedMitm;
    private CheckBox mPermissionWrite;
    private CheckBox mPermissionWriteEncrypted;
    private CheckBox mPermissionWriteEncryptedMitm;
    private CheckBox mPermissionWriteSigned;
    private CheckBox mPermissionWriteSignedMitm;
    private CheckBox mPropertyBroadcast;
    private CheckBox mPropertyIndicate;
    private CheckBox mPropertyNotify;
    private CheckBox mPropertyRead;
    private CheckBox mPropertyReliableWrite;
    private CheckBox mPropertySignedWrite;
    private CheckBox mPropertyWriteAuxiliary;
    private CheckBox mPropertyWriteCommand;
    private CheckBox mPropertyWriteRequest;
    private SwitchCompat mSwitchView;
    private EditText mUuidView;
    private RadioButton mValueBytesOption;
    private EditText mValueBytesView;
    private RadioButton mValueEmptyOption;
    private RadioButton mValueTextOption;
    private EditText mValueTextView;

    public static androidx.fragment.app.c getInstance(Service service, Characteristic characteristic) {
        EditCharacteristicDialogFragment editCharacteristicDialogFragment = new EditCharacteristicDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SERVICE_INTERNAL_ID, service.getInternalId());
        bundle.putParcelable("characteristic", characteristic);
        editCharacteristicDialogFragment.setArguments(bundle);
        return editCharacteristicDialogFragment;
    }

    private void onOkClick() {
        String trim = this.mUuidView.getText().toString().trim();
        String trim2 = this.mNameView.getText().toString().trim();
        if (!TextUtils.isEmpty(trim) && Pattern.matches("[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}", trim)) {
            if (!propertiesValid()) {
                this.mPropertyRead.setError(getString(R.string.server_alert_characteristic_properties_error));
                return;
            }
            String trim3 = this.mValueBytesView.getText().toString().trim();
            String str = (TextUtils.isEmpty(trim3) || !this.mValueBytesOption.isChecked()) ? null : trim3;
            if (str != null) {
                if (!(str.length() % 2 == 0 && str.matches("^[0-9a-fA-F]+$"))) {
                    this.mValueBytesView.setError(getString(R.string.server_alert_value_bytes_error));
                    return;
                }
            }
            String obj = this.mValueTextView.getText().toString();
            String str2 = (TextUtils.isEmpty(obj) || !this.mValueTextOption.isChecked()) ? null : obj;
            if (TextUtils.isEmpty(trim2)) {
                trim2 = null;
            }
            UUID fromString = UUID.fromString(trim);
            if (trim2 != null) {
                Cursor characteristic = this.mDatabaseHelper.getCharacteristic(fromString);
                try {
                    if (characteristic.moveToNext()) {
                        if (trim2.equals(characteristic.getString(4))) {
                            trim2 = null;
                        }
                    }
                } finally {
                    characteristic.close();
                }
            }
            String str3 = trim2;
            int mask = (this.mPropertyBroadcast.isChecked() ? Characteristic.Property.Name.BROADCAST.getMask() : 0) | 0 | (this.mPropertyRead.isChecked() ? Characteristic.Property.Name.READ.getMask() : 0) | (this.mPropertyWriteCommand.isChecked() ? Characteristic.Property.Name.WRITE_WITHOUT_RESPONSE.getMask() : 0) | (this.mPropertyWriteRequest.isChecked() ? Characteristic.Property.Name.WRITE.getMask() : 0) | (this.mPropertyNotify.isChecked() ? Characteristic.Property.Name.NOTIFY.getMask() : 0) | (this.mPropertyIndicate.isChecked() ? Characteristic.Property.Name.INDICATE.getMask() : 0) | (this.mPropertySignedWrite.isChecked() ? Characteristic.Property.Name.SIGNED_WRITE.getMask() : 0) | (this.mPropertyReliableWrite.isChecked() ? Characteristic.Property.Name.RELIABLE_WRITE.getMask() : 0) | (this.mPropertyWriteAuxiliary.isChecked() ? Characteristic.Property.Name.WRITE_AUXILIARY.getMask() : 0);
            int mask2 = (this.mPermissionRead.isChecked() ? Characteristic.Permission.Name.READ.getMask() : 0) | 0 | (this.mPermissionReadEncrypted.isChecked() ? Characteristic.Permission.Name.READ_ENCRYPTED.getMask() : 0) | (this.mPermissionReadEncryptedMitm.isChecked() ? Characteristic.Permission.Name.READ_ENCRYPTED_MITM.getMask() : 0) | (this.mPermissionWrite.isChecked() ? Characteristic.Permission.Name.WRITE.getMask() : 0) | (this.mPermissionWriteEncrypted.isChecked() ? Characteristic.Permission.Name.WRITE_ENCRYPTED.getMask() : 0) | (this.mPermissionWriteEncryptedMitm.isChecked() ? Characteristic.Permission.Name.WRITE_ENCRYPTED_MITM.getMask() : 0) | (this.mPermissionWriteSigned.isChecked() ? Characteristic.Permission.Name.WRITE_SIGNED.getMask() : 0) | (this.mPermissionWriteSignedMitm.isChecked() ? Characteristic.Permission.Name.WRITE_SIGNED_MITM.getMask() : 0);
            Bundle arguments = getArguments();
            Characteristic characteristic2 = (Characteristic) arguments.getParcelable("characteristic");
            int internalId = characteristic2 != null ? characteristic2.getInternalId() : -1;
            int i = arguments.getInt(ARG_SERVICE_INTERNAL_ID);
            ServerServiceAdapter.ConfigurationListener configurationListener = (ServerServiceAdapter.ConfigurationListener) getParentFragment();
            configurationListener.onPrepareConfigurationChange();
            configurationListener.onCharacteristicChanged(i, internalId, str3, fromString, mask, mask2, str2, str, this.mSwitchView.isChecked());
            dismiss();
            return;
        }
        this.mUuidView.setError(getString(R.string.server_alert_service_uuid_error));
    }

    private boolean propertiesValid() {
        return this.mPropertyBroadcast.isChecked() || this.mPropertyRead.isChecked() || this.mPropertyWriteCommand.isChecked() || this.mPropertyWriteRequest.isChecked() || this.mPropertyNotify.isChecked() || this.mPropertyIndicate.isChecked() || this.mPropertySignedWrite.isChecked() || this.mPropertyReliableWrite.isChecked() || this.mPropertyWriteAuxiliary.isChecked();
    }

    private void setValueOption(int i) {
        this.mValueEmptyOption.setChecked(i == R.id.option_value_empty);
        this.mValueTextOption.setChecked(i == R.id.option_value_text);
        this.mValueBytesOption.setChecked(i == R.id.option_value_bytes);
    }

    public /* synthetic */ void a(View view) {
        this.mNameView.setText((CharSequence) null);
    }

    public /* synthetic */ void b(View view) {
        this.mUuidView.setText((CharSequence) null);
    }

    public /* synthetic */ void c(View view) {
        setValueOption(R.id.option_value_bytes);
        this.mValueBytesView.setText((CharSequence) null);
        this.mValueBytesView.requestFocus();
    }

    public /* synthetic */ void d(View view) {
        setValueOption(R.id.option_value_text);
    }

    public /* synthetic */ void e(View view) {
        setValueOption(R.id.option_value_bytes);
    }

    public /* synthetic */ void f(View view) {
        onOkClick();
    }

    public /* synthetic */ void g(View view) {
        setValueOption(R.id.option_value_text);
        this.mValueTextView.setText((CharSequence) null);
        this.mValueTextView.requestFocus();
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDatabaseHelper = new DatabaseHelper(getContext());
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        Characteristic characteristic = (Characteristic) getArguments().getParcelable("characteristic");
        View inflate = LayoutInflater.from(new ContextThemeWrapper(getActivity(), R.style.AppTheme_DialogTheme)).inflate(R.layout.dialog_server_edit_title, (ViewGroup) null, false);
        ((TextView) inflate.findViewById(android.R.id.title)).setText(characteristic != null ? R.string.server_alert_characteristic_title_edit : R.string.server_alert_characteristic_title_add);
        this.mSwitchView = (SwitchCompat) inflate.findViewById(android.R.id.checkbox);
        final ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.dialog_server_edit_characteristic, (ViewGroup) null, false);
        this.mNameView = (AutoCompleteTextView) viewGroup.findViewById(R.id.display_name);
        this.mUuidView = (EditText) viewGroup.findViewById(R.id.uuid);
        viewGroup.findViewById(R.id.action_clear_name).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditCharacteristicDialogFragment.this.a(view);
            }
        });
        viewGroup.findViewById(R.id.action_clear_uuid).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditCharacteristicDialogFragment.this.b(view);
            }
        });
        final DatabaseHelper databaseHelper = this.mDatabaseHelper;
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, null, new String[]{"name"}, new int[]{android.R.id.text1}, 0);
        simpleCursorAdapter.setFilterQueryProvider(new FilterQueryProvider() { // from class: no.nordicsemi.android.mcp.server.e
            @Override // android.widget.FilterQueryProvider
            public final Cursor runQuery(CharSequence charSequence) {
                return EditCharacteristicDialogFragment.this.a(databaseHelper, charSequence);
            }
        });
        simpleCursorAdapter.setCursorToStringConverter(new SimpleCursorAdapter.CursorToStringConverter() { // from class: no.nordicsemi.android.mcp.server.d
            @Override // android.widget.SimpleCursorAdapter.CursorToStringConverter
            public final CharSequence convertToString(Cursor cursor) {
                CharSequence string;
                string = cursor.getString(4);
                return string;
            }
        });
        this.mNameView.setAdapter(simpleCursorAdapter);
        this.mNameView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: no.nordicsemi.android.mcp.server.g
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                EditCharacteristicDialogFragment.this.a(databaseHelper, adapterView, view, i, j);
            }
        });
        this.mPropertyBroadcast = (CheckBox) viewGroup.findViewById(R.id.property_broadcast);
        this.mPropertyRead = (CheckBox) viewGroup.findViewById(R.id.property_read);
        this.mPropertyWriteCommand = (CheckBox) viewGroup.findViewById(R.id.property_write_command);
        this.mPropertyWriteRequest = (CheckBox) viewGroup.findViewById(R.id.property_write_request);
        this.mPropertyNotify = (CheckBox) viewGroup.findViewById(R.id.property_notify);
        this.mPropertyIndicate = (CheckBox) viewGroup.findViewById(R.id.property_indicate);
        this.mPropertySignedWrite = (CheckBox) viewGroup.findViewById(R.id.property_signed_write);
        final View findViewById = viewGroup.findViewById(R.id.extended_properties);
        this.mPropertyReliableWrite = (CheckBox) viewGroup.findViewById(R.id.property_reliable_write);
        this.mPropertyWriteAuxiliary = (CheckBox) viewGroup.findViewById(R.id.property_write_auxiliary);
        ((CheckBox) viewGroup.findViewById(R.id.action_expand_properties)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.server.b
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                EditCharacteristicDialogFragment.this.a(findViewById, compoundButton, z);
            }
        });
        this.mPermissionRead = (CheckBox) viewGroup.findViewById(R.id.permission_read);
        this.mPermissionReadEncrypted = (CheckBox) viewGroup.findViewById(R.id.permission_read_encrypted);
        this.mPermissionReadEncryptedMitm = (CheckBox) viewGroup.findViewById(R.id.permission_read_encrypted_mitm);
        this.mPermissionWrite = (CheckBox) viewGroup.findViewById(R.id.permission_write);
        this.mPermissionWriteEncrypted = (CheckBox) viewGroup.findViewById(R.id.permission_write_encrypted);
        this.mPermissionWriteEncryptedMitm = (CheckBox) viewGroup.findViewById(R.id.permission_write_encrypted_mitm);
        this.mPermissionWriteSigned = (CheckBox) viewGroup.findViewById(R.id.permission_write_signed);
        this.mPermissionWriteSignedMitm = (CheckBox) viewGroup.findViewById(R.id.permission_write_signed_mitm);
        ((CheckBox) viewGroup.findViewById(R.id.action_expand_permissions)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.server.k
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                EditCharacteristicDialogFragment.this.a(compoundButton, z);
            }
        });
        this.mValueTextView = (EditText) viewGroup.findViewById(R.id.value_string);
        this.mValueTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: no.nordicsemi.android.mcp.server.l
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                EditCharacteristicDialogFragment.this.a(view, z);
            }
        });
        viewGroup.findViewById(R.id.action_clear_value_string).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditCharacteristicDialogFragment.this.g(view);
            }
        });
        this.mValueBytesView = (EditText) viewGroup.findViewById(R.id.value);
        this.mValueBytesView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: no.nordicsemi.android.mcp.server.a
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                EditCharacteristicDialogFragment.this.b(view, z);
            }
        });
        viewGroup.findViewById(R.id.action_clear_value).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditCharacteristicDialogFragment.this.c(view);
            }
        });
        this.mValueEmptyOption = (RadioButton) viewGroup.findViewById(R.id.option_value_empty);
        this.mValueEmptyOption.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditCharacteristicDialogFragment.this.a(viewGroup, view);
            }
        });
        this.mValueTextOption = (RadioButton) viewGroup.findViewById(R.id.option_value_text);
        this.mValueTextOption.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditCharacteristicDialogFragment.this.d(view);
            }
        });
        this.mValueBytesOption = (RadioButton) viewGroup.findViewById(R.id.option_value_bytes);
        this.mValueBytesOption.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditCharacteristicDialogFragment.this.e(view);
            }
        });
        if (characteristic != null) {
            String name = characteristic.getName();
            UUID uuid = characteristic.getUuid();
            this.mUuidView.setText(uuid.toString());
            this.mSwitchView.setChecked(characteristic.isEnabled());
            if (TextUtils.isEmpty(name)) {
                Cursor characteristic2 = databaseHelper.getCharacteristic(uuid);
                try {
                    if (characteristic2.moveToNext()) {
                        this.mNameView.setText(characteristic2.getString(4));
                    }
                } finally {
                    characteristic2.close();
                }
            } else {
                this.mNameView.setText(name);
            }
            int properties = characteristic.getProperties();
            this.mPropertyBroadcast.setChecked((Characteristic.Property.Name.BROADCAST.getMask() & properties) > 0);
            this.mPropertyRead.setChecked((Characteristic.Property.Name.READ.getMask() & properties) > 0);
            this.mPropertyWriteCommand.setChecked((Characteristic.Property.Name.WRITE_WITHOUT_RESPONSE.getMask() & properties) > 0);
            this.mPropertyWriteRequest.setChecked((Characteristic.Property.Name.WRITE.getMask() & properties) > 0);
            this.mPropertyNotify.setChecked((Characteristic.Property.Name.NOTIFY.getMask() & properties) > 0);
            this.mPropertyIndicate.setChecked((Characteristic.Property.Name.INDICATE.getMask() & properties) > 0);
            this.mPropertySignedWrite.setChecked((Characteristic.Property.Name.SIGNED_WRITE.getMask() & properties) > 0);
            this.mPropertyReliableWrite.setChecked((Characteristic.Property.Name.RELIABLE_WRITE.getMask() & properties) > 0);
            this.mPropertyWriteAuxiliary.setChecked((properties & Characteristic.Property.Name.WRITE_AUXILIARY.getMask()) > 0);
            int permissions = characteristic.getPermissions();
            this.mPermissionRead.setChecked((Characteristic.Permission.Name.READ.getMask() & permissions) > 0);
            this.mPermissionReadEncrypted.setChecked((Characteristic.Permission.Name.READ_ENCRYPTED.getMask() & permissions) > 0);
            this.mPermissionReadEncryptedMitm.setChecked((Characteristic.Permission.Name.READ_ENCRYPTED_MITM.getMask() & permissions) > 0);
            this.mPermissionWrite.setChecked((Characteristic.Permission.Name.WRITE.getMask() & permissions) > 0);
            this.mPermissionWriteEncrypted.setChecked((Characteristic.Permission.Name.WRITE_ENCRYPTED.getMask() & permissions) > 0);
            this.mPermissionWriteEncryptedMitm.setChecked((Characteristic.Permission.Name.WRITE_ENCRYPTED_MITM.getMask() & permissions) > 0);
            this.mPermissionWriteSigned.setChecked((Characteristic.Permission.Name.WRITE_SIGNED.getMask() & permissions) > 0);
            this.mPermissionWriteSignedMitm.setChecked((permissions & Characteristic.Permission.Name.WRITE_SIGNED_MITM.getMask()) > 0);
            String rawStringValue = characteristic.getRawStringValue();
            if (!TextUtils.isEmpty(rawStringValue)) {
                this.mValueTextView.setText(rawStringValue);
                setValueOption(R.id.option_value_text);
            }
            String rawValue = characteristic.getRawValue();
            if (!TextUtils.isEmpty(rawValue)) {
                this.mValueBytesView.setText(rawValue);
                setValueOption(R.id.option_value_bytes);
            }
        }
        d.a aVar = new d.a(requireContext());
        aVar.a(inflate);
        aVar.b(viewGroup);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(R.string.ok, null);
        androidx.appcompat.app.d c2 = aVar.c();
        c2.b(-1).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditCharacteristicDialogFragment.this.f(view);
            }
        });
        return c2;
    }

    @Override // androidx.fragment.app.c, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        Cursor cursor = this.mCharacteristicsCursor;
        if (cursor != null) {
            cursor.close();
        }
    }

    public /* synthetic */ Cursor a(DatabaseHelper databaseHelper, CharSequence charSequence) {
        if (charSequence == null) {
            Cursor allCharacteristics = databaseHelper.getAllCharacteristics();
            this.mCharacteristicsCursor = allCharacteristics;
            return allCharacteristics;
        }
        Cursor characteristics = databaseHelper.getCharacteristics(charSequence.toString());
        this.mCharacteristicsCursor = characteristics;
        return characteristics;
    }

    public /* synthetic */ void b(View view, boolean z) {
        if (z) {
            setValueOption(R.id.option_value_bytes);
        }
    }

    public /* synthetic */ void a(DatabaseHelper databaseHelper, AdapterView adapterView, View view, int i, long j) {
        this.mUuidView.setText(databaseHelper.getCharacteristicUuid(j).toString());
    }

    public /* synthetic */ void a(View view, CompoundButton compoundButton, boolean z) {
        int i = z ? 0 : 8;
        this.mPropertyBroadcast.setVisibility(i);
        this.mPropertySignedWrite.setVisibility(i);
        view.setVisibility(i);
        this.mPropertyReliableWrite.setVisibility(i);
        this.mPropertyWriteAuxiliary.setVisibility(i);
    }

    public /* synthetic */ void a(CompoundButton compoundButton, boolean z) {
        int i = z ? 0 : 8;
        this.mPermissionReadEncrypted.setVisibility(i);
        this.mPermissionReadEncryptedMitm.setVisibility(i);
        this.mPermissionWriteEncrypted.setVisibility(i);
        this.mPermissionWriteEncryptedMitm.setVisibility(i);
        this.mPermissionWriteSigned.setVisibility(i);
        this.mPermissionWriteSignedMitm.setVisibility(i);
    }

    public /* synthetic */ void a(View view, boolean z) {
        if (z) {
            setValueOption(R.id.option_value_text);
        }
    }

    public /* synthetic */ void a(ViewGroup viewGroup, View view) {
        setValueOption(R.id.option_value_empty);
        if (this.mValueTextView.hasFocus() || this.mValueBytesView.hasFocus()) {
            ((InputMethodManager) requireContext().getSystemService("input_method")).hideSoftInputFromWindow(viewGroup.getWindowToken(), 0);
            this.mNameView.requestFocus();
        }
    }
}