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
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.d;
import androidx.appcompat.widget.SwitchCompat;
import java.util.UUID;
import java.util.regex.Pattern;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.server.ServerServiceAdapter;
import no.nordicsemi.android.mcp.server.domain.Service;

/* loaded from: classes.dex */
public class EditServiceDialogFragment extends androidx.fragment.app.c {
    private static final String ARG_SERVICE = "service";
    private Spinner mConfigurationSpinner;
    private ViewGroup mCustomConfiguration;
    private DatabaseHelper mDatabaseHelper;
    private AutoCompleteTextView mNameView;
    private Cursor mServicesCursor;
    private SwitchCompat mSwitchView;
    private EditText mUuidView;

    public static androidx.fragment.app.c getInstance(Service service) {
        EditServiceDialogFragment editServiceDialogFragment = new EditServiceDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("service", service);
        editServiceDialogFragment.setArguments(bundle);
        return editServiceDialogFragment;
    }

    private void onOkClick() {
        String trim = this.mUuidView.getText().toString().trim();
        String trim2 = this.mNameView.getText().toString().trim();
        int selectedItemPosition = this.mConfigurationSpinner.getSelectedItemPosition();
        if (selectedItemPosition == 0 && (TextUtils.isEmpty(trim) || !Pattern.matches("[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}", trim))) {
            this.mUuidView.setError(getString(R.string.server_alert_service_uuid_error));
            return;
        }
        if (TextUtils.isEmpty(trim2)) {
            trim2 = null;
        }
        Service.PredefinedService predefinedService = Service.PredefinedService.values()[selectedItemPosition];
        UUID fromString = !TextUtils.isEmpty(trim) ? UUID.fromString(trim) : null;
        if (fromString != null && trim2 != null) {
            Cursor service = this.mDatabaseHelper.getService(fromString);
            try {
                if (service.moveToNext()) {
                    if (trim2.equals(service.getString(4))) {
                        trim2 = null;
                    }
                }
            } finally {
                service.close();
            }
        }
        String str = trim2;
        Service service2 = (Service) getArguments().getParcelable("service");
        int internalId = service2 != null ? service2.getInternalId() : -1;
        ServerServiceAdapter.ConfigurationListener configurationListener = (ServerServiceAdapter.ConfigurationListener) getParentFragment();
        configurationListener.onPrepareConfigurationChange();
        configurationListener.onServiceChanged(internalId, predefinedService, str, fromString, this.mSwitchView.isChecked());
        dismiss();
    }

    public /* synthetic */ void a(View view) {
        this.mNameView.setText((CharSequence) null);
    }

    public /* synthetic */ void b(View view) {
        this.mUuidView.setText((CharSequence) null);
    }

    public /* synthetic */ void c(View view) {
        onOkClick();
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDatabaseHelper = new DatabaseHelper(getContext());
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        Service service = (Service) getArguments().getParcelable("service");
        View inflate = LayoutInflater.from(new ContextThemeWrapper(getActivity(), R.style.AppTheme_DialogTheme)).inflate(R.layout.dialog_server_edit_title, (ViewGroup) null, false);
        ((TextView) inflate.findViewById(android.R.id.title)).setText(service != null ? R.string.server_alert_service_title_edit : R.string.server_alert_service_title_add);
        this.mSwitchView = (SwitchCompat) inflate.findViewById(android.R.id.checkbox);
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.dialog_server_edit_service, (ViewGroup) null, false);
        this.mConfigurationSpinner = (Spinner) viewGroup.findViewById(R.id.configuration);
        this.mCustomConfiguration = (ViewGroup) viewGroup.findViewById(R.id.custom);
        this.mNameView = (AutoCompleteTextView) viewGroup.findViewById(R.id.display_name);
        this.mUuidView = (EditText) viewGroup.findViewById(R.id.uuid);
        viewGroup.findViewById(R.id.action_clear_name).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.f0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditServiceDialogFragment.this.a(view);
            }
        });
        viewGroup.findViewById(R.id.action_clear_uuid).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.e0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditServiceDialogFragment.this.b(view);
            }
        });
        final View findViewById = viewGroup.findViewById(R.id.info);
        final DatabaseHelper databaseHelper = this.mDatabaseHelper;
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, null, new String[]{"name"}, new int[]{android.R.id.text1}, 0);
        simpleCursorAdapter.setFilterQueryProvider(new FilterQueryProvider() { // from class: no.nordicsemi.android.mcp.server.i0
            @Override // android.widget.FilterQueryProvider
            public final Cursor runQuery(CharSequence charSequence) {
                return EditServiceDialogFragment.this.a(databaseHelper, charSequence);
            }
        });
        simpleCursorAdapter.setCursorToStringConverter(new SimpleCursorAdapter.CursorToStringConverter() { // from class: no.nordicsemi.android.mcp.server.h0
            @Override // android.widget.SimpleCursorAdapter.CursorToStringConverter
            public final CharSequence convertToString(Cursor cursor) {
                CharSequence string;
                string = cursor.getString(4);
                return string;
            }
        });
        this.mNameView.setAdapter(simpleCursorAdapter);
        this.mNameView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: no.nordicsemi.android.mcp.server.d0
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                EditServiceDialogFragment.this.a(databaseHelper, adapterView, view, i, j);
            }
        });
        this.mConfigurationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: no.nordicsemi.android.mcp.server.EditServiceDialogFragment.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                EditServiceDialogFragment.this.mCustomConfiguration.setVisibility(i == 0 ? 0 : 8);
                if (i > 0) {
                    EditServiceDialogFragment.this.mNameView.setText((CharSequence) null);
                    EditServiceDialogFragment.this.mUuidView.setText((CharSequence) null);
                }
                findViewById.setVisibility(8);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        if (service != null) {
            this.mSwitchView.setChecked(service.isEnabled());
            this.mConfigurationSpinner.setSelection(service.getConfiguration().getPosition());
            this.mCustomConfiguration.setVisibility(service.isPredefined() ? 8 : 0);
            if (!service.isPredefined()) {
                String name = service.getName();
                UUID uuid = service.getUuid();
                this.mUuidView.setText(uuid != null ? uuid.toString() : null);
                if (TextUtils.isEmpty(name)) {
                    Cursor service2 = databaseHelper.getService(uuid);
                    try {
                        if (service2.moveToNext()) {
                            this.mNameView.setText(service2.getString(4));
                        }
                    } finally {
                        service2.close();
                    }
                } else {
                    this.mNameView.setText(name);
                }
            }
        }
        d.a aVar = new d.a(requireContext());
        aVar.a(inflate);
        aVar.b(viewGroup);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(R.string.ok, null);
        aVar.b(R.string.info, (DialogInterface.OnClickListener) null);
        androidx.appcompat.app.d c2 = aVar.c();
        c2.b(-1).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.g0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditServiceDialogFragment.this.c(view);
            }
        });
        c2.b(-3).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.j0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                findViewById.setVisibility(0);
            }
        });
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

    public /* synthetic */ void a(DatabaseHelper databaseHelper, AdapterView adapterView, View view, int i, long j) {
        this.mUuidView.setText(databaseHelper.getServiceUuid(j).toString());
    }
}
