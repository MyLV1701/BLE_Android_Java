package no.nordicsemi.android.mcp.server;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import androidx.appcompat.app.d;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.j;
import com.google.android.material.snackbar.Snackbar;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.UUID;
import no.nordicsemi.android.log.LogContract;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.BluetoothLeService;
import no.nordicsemi.android.mcp.connection.FileBrowserAppsAdapter;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.server.ServerConfigurationsAdapter;
import no.nordicsemi.android.mcp.server.ServerServiceAdapter;
import no.nordicsemi.android.mcp.server.domain.Characteristic;
import no.nordicsemi.android.mcp.server.domain.Descriptor;
import no.nordicsemi.android.mcp.server.domain.ServerConfiguration;
import no.nordicsemi.android.mcp.server.domain.Service;
import no.nordicsemi.android.mcp.util.FileHelper;
import no.nordicsemi.android.mcp.widget.RemovableItemTouchHelperCallback;
import no.nordicsemi.android.mcp.widget.ViewAnimator;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.HyphenStyle;

/* loaded from: classes.dex */
public class ServerFragment extends Fragment implements AdapterView.OnItemSelectedListener, ServerConfigurationsAdapter.ActionListener, ViewAnimator.ExpandCollapseListener, ServerServiceAdapter.ConfigurationListener {
    public static final String PREFS_SERVER_CONFIGURATION = "server_configuration_id";
    private static final int REQUEST_SAVE = 2679;
    private static final int SELECT_FILE_REQ = 2678;
    public static final long SERVER_CONFIGURATION_DISABLED = 0;
    private static final String SIS_CHANGES_COUNT = "changes_count";
    private static final String SIS_LAST_CONFIGURATION = "server_last_configuration";
    private static final String TAG = "ServerFragment";
    private ServerServiceAdapter mAdapter;
    private int mChangesCount;
    private ServerConfiguration mConfiguration;
    private ServerConfigurationsAdapter mConfigurationsAdapter;
    private DatabaseHelper mDatabaseHelper;
    private String mLastConfiguration;
    private SharedPreferences mPreferences;
    private RecyclerView mRecyclerView;
    private long mSelectedId = 0;
    private View mServerDisabledView;

    private void closeConfigurationsSpinner() {
        ((ServerActivity) requireActivity()).closeToolbarSpinner();
    }

    private void ensureSampleServerConfiguration(DatabaseHelper databaseHelper) {
        if (this.mPreferences.getLong(PREFS_SERVER_CONFIGURATION, -1L) != -1) {
            return;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.gatt_server_configuration)));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append("\n");
                } else {
                    databaseHelper.addServerConfiguration("Sample configuration", sb.toString());
                    this.mPreferences.edit().putLong(PREFS_SERVER_CONFIGURATION, 0L).apply();
                    return;
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, "Creating sample configuration failed", e2);
        }
    }

    private void exportServerConfiguration() {
        String str = this.mConfiguration.getName() + ".xml";
        if (Build.VERSION.SDK_INT >= 19) {
            Intent intent = new Intent("android.intent.action.CREATE_DOCUMENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("text/xml");
            intent.putExtra("android.intent.extra.TITLE", str);
            startActivityForResult(intent, REQUEST_SAVE);
            return;
        }
        try {
            File file = new File(Environment.getExternalStorageDirectory(), FileHelper.NORDIC_FOLDER);
            if (!file.exists()) {
                file.mkdir();
            }
            File file2 = new File(file, FileHelper.SERVER_FOLDER);
            if (!file2.exists()) {
                file2.mkdir();
            }
            File file3 = new File(file2, str);
            file3.createNewFile();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file3));
            outputStreamWriter.append((CharSequence) this.mDatabaseHelper.getServerConfiguration(((ServerActivity) requireActivity()).getToolbarSpinnerSelectionId()));
            outputStreamWriter.close();
            Uri contentUri = FileHelper.getContentUri(requireContext(), file3);
            if (contentUri != null) {
                FileHelper.showFileNotification(requireContext(), contentUri, "text/xml");
            }
        } catch (Exception e2) {
            Log.e(TAG, "Error while exporting server configuration", e2);
            Toast.makeText(getActivity(), R.string.error_export_failed, 0).show();
        }
    }

    private void loadServerConfiguration(InputStream inputStream) {
        final String localizedMessage;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append("\n");
            }
            String sb2 = sb.toString();
            String name = ((ServerConfiguration) new Persister(new Format(new HyphenStyle())).read(ServerConfiguration.class, sb2)).getName();
            if (!this.mDatabaseHelper.serverConfigurationExists(name)) {
                final long addServerConfiguration = this.mDatabaseHelper.addServerConfiguration(name, sb2);
                refreshConfigurations();
                new Handler().post(new Runnable() { // from class: no.nordicsemi.android.mcp.server.r0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ServerFragment.this.a(addServerConfiguration);
                    }
                });
                return;
            }
            Toast.makeText(getActivity(), R.string.server_name_already_taken, 1).show();
        } catch (Exception e2) {
            Log.e(TAG, "Loading configuration failed", e2);
            if (e2.getLocalizedMessage() != null) {
                localizedMessage = e2.getLocalizedMessage();
            } else {
                localizedMessage = (e2.getCause() == null || e2.getCause().getLocalizedMessage() == null) ? "Unknown error" : e2.getCause().getLocalizedMessage();
            }
            Snackbar a2 = Snackbar.a(this.mRecyclerView, R.string.server_loading_failed, -2);
            a2.e(getResources().getColor(R.color.variant));
            a2.a(R.string.action_details, new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.o0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ServerFragment.this.a(localizedMessage, view);
                }
            });
            a2.k();
        }
    }

    private void refreshConfigurations() {
        this.mConfigurationsAdapter.swapCursor(this.mDatabaseHelper.getServerConfigurationsNames());
        this.mConfigurationsAdapter.notifyDataSetChanged();
    }

    private void saveServerConfiguration() {
        ServerConfiguration serverConfiguration = this.mConfiguration;
        try {
            Persister persister = new Persister(new Format(new HyphenStyle()));
            StringWriter stringWriter = new StringWriter();
            persister.write(serverConfiguration, stringWriter);
            this.mDatabaseHelper.updateServerConfiguration(serverConfiguration.getName(), stringWriter.toString());
        } catch (Exception e2) {
            Log.e(TAG, "Error while creating a new configuration", e2);
        }
    }

    private void selectConfiguration(int i) {
        ((ServerActivity) requireActivity()).setToolbarSpinnerSelection(i);
    }

    private void setupConfigurations(SpinnerAdapter spinnerAdapter) {
        ((ServerActivity) requireActivity()).setToolbarSpinnerAdapter(spinnerAdapter, this);
    }

    private void undoLastChange() {
        String str = this.mLastConfiguration;
        if (str == null) {
            return;
        }
        try {
            this.mConfiguration = (ServerConfiguration) new Persister(new Format(new HyphenStyle())).read(ServerConfiguration.class, str);
            this.mDatabaseHelper.updateServerConfiguration(this.mConfiguration.getName(), str);
            this.mAdapter.setConfiguration(this.mConfiguration, false);
            this.mLastConfiguration = null;
            requireActivity().invalidateOptionsMenu();
            this.mChangesCount--;
        } catch (Exception e2) {
            Log.e(TAG, "Error while undoing changes", e2);
        }
    }

    public /* synthetic */ void a(View view) {
        this.mDatabaseHelper.restoreDeletedServerConfigurations();
        refreshConfigurations();
    }

    public /* synthetic */ void b(String str, View view) {
        d.a aVar = new d.a(requireContext());
        aVar.a(str);
        aVar.c(R.string.action_details);
        aVar.c(R.string.ok, null);
        aVar.c();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Uri data = intent != null ? intent.getData() : null;
        if (i2 == 0 || data == null) {
            return;
        }
        if (i != SELECT_FILE_REQ) {
            if (i != REQUEST_SAVE) {
                return;
            }
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(requireContext().getContentResolver().openOutputStream(data));
                outputStreamWriter.append((CharSequence) this.mDatabaseHelper.getServerConfiguration(((ServerActivity) requireActivity()).getToolbarSpinnerSelectionId()));
                outputStreamWriter.close();
                Toast.makeText(requireContext(), R.string.file_saved, 0).show();
                return;
            } catch (Exception e2) {
                Log.e(TAG, "Error while exporting server configuration", e2);
                Toast.makeText(requireContext(), R.string.error_export_failed, 0).show();
                return;
            }
        }
        if (data.getScheme().equals("file")) {
            try {
                loadServerConfiguration(new FileInputStream(data.getPath()));
            } catch (FileNotFoundException unused) {
                Toast.makeText(getActivity(), R.string.error_import_failed, 1).show();
            }
        } else if (data.getScheme().equals(LogContract.Session.Content.CONTENT)) {
            Bundle extras = intent.getExtras();
            if (extras != null && extras.containsKey("android.intent.extra.STREAM")) {
                data = (Uri) extras.getParcelable("android.intent.extra.STREAM");
            }
            try {
                loadServerConfiguration(requireContext().getContentResolver().openInputStream(data));
            } catch (FileNotFoundException unused2) {
                Toast.makeText(getActivity(), R.string.error_import_failed, 1).show();
            }
        }
    }

    @Override // no.nordicsemi.android.mcp.server.ServerServiceAdapter.ConfigurationListener
    public void onAddCharacteristic(Service service) {
        onEditCharacteristic(service, null);
    }

    @Override // no.nordicsemi.android.mcp.server.ServerServiceAdapter.ConfigurationListener
    public void onAddDescriptor(Characteristic characteristic) {
        onEditDescriptor(characteristic, null);
    }

    @Override // no.nordicsemi.android.mcp.server.ServerServiceAdapter.ConfigurationListener
    public void onAddService() {
        onEditService(null);
    }

    @Override // no.nordicsemi.android.mcp.server.ServerServiceAdapter.ConfigurationListener
    public void onCharacteristicChanged(int i, int i2, String str, UUID uuid, int i3, int i4, String str2, String str3, boolean z) {
        Service service;
        Characteristic characteristic;
        Iterator<Service> it = this.mConfiguration.getServices().iterator();
        int i5 = 0;
        int i6 = 0;
        while (true) {
            if (!it.hasNext()) {
                service = null;
                break;
            }
            service = it.next();
            if (service.getInternalId() == i) {
                i5 = i6;
                break;
            }
            i6++;
        }
        if (service == null) {
            Log.w(TAG, "onCharacteristicChanged: service not found with internal id: " + i);
            return;
        }
        if (i2 < 0) {
            characteristic = new Characteristic();
            service.addCharacteristic(characteristic);
        } else {
            Iterator<Characteristic> it2 = service.getCharacteristics().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    characteristic = null;
                    break;
                }
                Characteristic next = it2.next();
                if (next.getInternalId() == i2) {
                    characteristic = next;
                    break;
                }
            }
            if (characteristic == null) {
                characteristic = new Characteristic();
                service.addCharacteristic(characteristic);
            }
        }
        characteristic.setName(str);
        characteristic.setUuid(uuid);
        characteristic.setProperties(i3);
        characteristic.setPermissions(i4);
        if (str3 != null) {
            characteristic.setValue(str3);
        } else {
            characteristic.setStringValue(str2);
        }
        characteristic.setEnabled(z);
        this.mAdapter.notifyItemChanged(i5);
        onConfigurationChanged();
    }

    @Override // no.nordicsemi.android.mcp.server.ServerServiceAdapter.ConfigurationListener
    public void onConfigurationChanged() {
        this.mChangesCount++;
        saveServerConfiguration();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        this.mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        this.mDatabaseHelper = new DatabaseHelper(getActivity());
        ensureSampleServerConfiguration(this.mDatabaseHelper);
        this.mConfigurationsAdapter = new ServerConfigurationsAdapter(getActivity(), this, this.mDatabaseHelper.getServerConfigurationsNames());
        if (bundle != null) {
            this.mLastConfiguration = bundle.getString(SIS_LAST_CONFIGURATION);
            this.mChangesCount = bundle.getInt(SIS_CHANGES_COUNT);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (this.mSelectedId > 0) {
            menuInflater.inflate(R.menu.server, menu);
            menu.findItem(R.id.action_undo).setEnabled(this.mLastConfiguration != null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_configure_gatt_server, viewGroup, false);
    }

    @Override // no.nordicsemi.android.mcp.server.ServerServiceAdapter.ConfigurationListener
    public void onDescriptorChanged(int i, int i2, String str, UUID uuid, int i3, String str2, String str3, boolean z) {
        Service service;
        Characteristic characteristic;
        Descriptor descriptor;
        Iterator<Service> it = this.mConfiguration.getServices().iterator();
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (!it.hasNext()) {
                service = null;
                break;
            }
            service = it.next();
            if (service.getInternalId() == (i >> 8)) {
                i4 = i5;
                break;
            }
            i5++;
        }
        if (service == null) {
            Log.w(TAG, "onDescriptorChanged: service not found with internal id: " + (i >> 8));
            return;
        }
        Iterator<Characteristic> it2 = service.getCharacteristics().iterator();
        while (true) {
            if (!it2.hasNext()) {
                characteristic = null;
                break;
            } else {
                characteristic = it2.next();
                if (characteristic.getInternalId() == i) {
                    break;
                }
            }
        }
        if (characteristic == null) {
            Log.w(TAG, "onDescriptorChanged: characteristic not found with internal id: " + (i & 255));
            return;
        }
        if (i2 < 0) {
            descriptor = new Descriptor();
            characteristic.addDescriptor(descriptor);
        } else {
            Iterator<Descriptor> it3 = characteristic.getDescriptors().iterator();
            while (true) {
                if (!it3.hasNext()) {
                    descriptor = null;
                    break;
                }
                Descriptor next = it3.next();
                if (next.getInternalId() == i2) {
                    descriptor = next;
                    break;
                }
            }
            if (descriptor == null) {
                descriptor = new Descriptor();
                characteristic.addDescriptor(descriptor);
            }
        }
        descriptor.setName(str);
        descriptor.setConfiguration(Descriptor.PredefinedDescriptors.CUSTOM, uuid);
        descriptor.setPermissions(i3);
        if (str3 != null) {
            descriptor.setValue(str3);
        } else {
            descriptor.setStringValue(str2);
        }
        descriptor.setEnabled(z);
        this.mAdapter.notifyItemChanged(i4);
        onConfigurationChanged();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.mChangesCount > 0) {
            a.l.a.a.a(requireContext()).a(new Intent(BluetoothLeService.ACTION_SERVER_CONFIGURATION_CHANGED));
        }
        this.mConfigurationsAdapter = null;
    }

    @Override // no.nordicsemi.android.mcp.server.ServerConfigurationsAdapter.ActionListener
    public void onDisableServer() {
        closeConfigurationsSpinner();
        selectConfiguration(0);
        this.mPreferences.edit().putLong(PREFS_SERVER_CONFIGURATION, 0L).apply();
        this.mSelectedId = 0L;
        this.mServerDisabledView.setVisibility(0);
        this.mAdapter.setConfiguration(null, true);
    }

    @Override // no.nordicsemi.android.mcp.server.ServerServiceAdapter.ConfigurationListener
    public void onEditCharacteristic(Service service, Characteristic characteristic) {
        EditCharacteristicDialogFragment.getInstance(service, characteristic).show(getChildFragmentManager(), (String) null);
    }

    @Override // no.nordicsemi.android.mcp.server.ServerServiceAdapter.ConfigurationListener
    public void onEditDescriptor(Characteristic characteristic, Descriptor descriptor) {
        EditDescriptorDialogFragment.getInstance(characteristic, descriptor).show(getChildFragmentManager(), (String) null);
    }

    @Override // no.nordicsemi.android.mcp.server.ServerServiceAdapter.ConfigurationListener
    public void onEditService(Service service) {
        EditServiceDialogFragment.getInstance(service).show(getChildFragmentManager(), (String) null);
    }

    @Override // no.nordicsemi.android.mcp.server.ServerConfigurationsAdapter.ActionListener
    public void onImportClick() {
        closeConfigurationsSpinner();
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("text/xml");
        intent.addCategory("android.intent.category.OPENABLE");
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivityForResult(intent, SELECT_FILE_REQ);
            return;
        }
        View inflate = requireActivity().getLayoutInflater().inflate(R.layout.app_file_browser, (ViewGroup) null);
        final ListView listView = (ListView) inflate.findViewById(android.R.id.list);
        listView.setAdapter((ListAdapter) new FileBrowserAppsAdapter(getActivity()));
        listView.setChoiceMode(1);
        listView.setItemChecked(0, true);
        d.a aVar = new d.a(requireContext());
        aVar.c(R.string.dfu_alert_no_filebrowser_title);
        aVar.b(inflate);
        aVar.a(R.string.no, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.s0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        aVar.c(R.string.yes, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.p0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ServerFragment.this.a(listView, dialogInterface, i);
            }
        });
        aVar.c();
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        final String localizedMessage;
        requireActivity().invalidateOptionsMenu();
        if (i == 0) {
            this.mChangesCount = j.f.DEFAULT_DRAG_ANIMATION_DURATION;
        }
        if (j != this.mSelectedId && i > 0) {
            int i2 = this.mChangesCount;
            if (i2 == -100) {
                this.mChangesCount = 0;
            } else {
                this.mChangesCount = i2 + 100;
            }
            this.mSelectedId = j;
            try {
                this.mConfiguration = (ServerConfiguration) new Persister(new Format(new HyphenStyle())).read(ServerConfiguration.class, this.mDatabaseHelper.getServerConfiguration(j));
                this.mServerDisabledView.setVisibility(8);
                this.mPreferences.edit().putLong(PREFS_SERVER_CONFIGURATION, j).apply();
                this.mAdapter.setConfiguration(this.mConfiguration, true);
            } catch (Exception e2) {
                Log.e(TAG, "Selecting configuration failed", e2);
                onDisableServer();
                selectConfiguration(0);
                if (e2.getLocalizedMessage() != null) {
                    localizedMessage = e2.getLocalizedMessage();
                } else {
                    localizedMessage = (e2.getCause() == null || e2.getCause().getLocalizedMessage() == null) ? "Unknown error" : e2.getCause().getLocalizedMessage();
                }
                Snackbar a2 = Snackbar.a(this.mRecyclerView, R.string.server_loading_failed, -2);
                a2.e(getResources().getColor(R.color.variant));
                a2.a(R.string.action_details, new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.t0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        ServerFragment.this.b(localizedMessage, view2);
                    }
                });
                a2.k();
                return;
            }
        }
        requireActivity().invalidateOptionsMenu();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onNewConfiguration(String str, boolean z) {
        if (this.mDatabaseHelper.serverConfigurationExists(str)) {
            Toast.makeText(getActivity(), R.string.server_name_already_taken, 1).show();
            return;
        }
        ServerConfiguration serverConfiguration = this.mConfiguration;
        if (!z) {
            serverConfiguration = new ServerConfiguration(str);
        } else {
            serverConfiguration.setName(str);
        }
        try {
            Persister persister = new Persister(new Format(new HyphenStyle()));
            StringWriter stringWriter = new StringWriter();
            persister.write(serverConfiguration, stringWriter);
            long addServerConfiguration = this.mDatabaseHelper.addServerConfiguration(str, stringWriter.toString());
            refreshConfigurations();
            selectConfiguration(this.mConfigurationsAdapter.getItemPosition(addServerConfiguration));
        } catch (Exception e2) {
            Log.e(TAG, "Error while creating a new configuration", e2);
        }
    }

    @Override // no.nordicsemi.android.mcp.server.ServerConfigurationsAdapter.ActionListener
    public void onNewConfigurationClick() {
        closeConfigurationsSpinner();
        NewConfigurationDialogFragment.getInstance(null, false).show(getChildFragmentManager(), (String) null);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        String name = this.mConfiguration.getName();
        switch (menuItem.getItemId()) {
            case R.id.action_duplicate /* 2131296344 */:
                NewConfigurationDialogFragment.getInstance(name, true).show(getChildFragmentManager(), (String) null);
                return true;
            case R.id.action_export /* 2131296357 */:
                exportServerConfiguration();
                return true;
            case R.id.action_remove /* 2131296396 */:
                this.mDatabaseHelper.removeDeletedServerConfigurations();
                this.mDatabaseHelper.deleteServerConfiguration(name);
                refreshConfigurations();
                if (this.mConfigurationsAdapter.isEmpty()) {
                    onDisableServer();
                }
                Snackbar a2 = Snackbar.a(this.mRecyclerView, R.string.server_configuration_deleted, -2);
                a2.e(getResources().getColor(R.color.variant));
                a2.a(R.string.action_undo, new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.q0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ServerFragment.this.a(view);
                    }
                });
                a2.d(5000);
                a2.k();
                return true;
            case R.id.action_rename /* 2131296397 */:
                NewConfigurationDialogFragment.getInstance(name, false).show(getChildFragmentManager(), (String) null);
                return true;
            case R.id.action_share /* 2131296412 */:
                String serverConfiguration = this.mDatabaseHelper.getServerConfiguration(((ServerActivity) requireActivity()).getToolbarSpinnerSelectionId());
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setFlags(268435456);
                intent.setType("text/xml");
                intent.putExtra("android.intent.extra.TEXT", serverConfiguration);
                intent.putExtra("android.intent.extra.SUBJECT", this.mConfiguration.getName());
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(getActivity(), R.string.no_uri_application, 0).show();
                }
                return true;
            case R.id.action_undo /* 2131296429 */:
                undoLastChange();
                return true;
            default:
                return false;
        }
    }

    @Override // no.nordicsemi.android.mcp.server.ServerServiceAdapter.ConfigurationListener
    public void onPrepareConfigurationChange() {
        this.mLastConfiguration = this.mDatabaseHelper.getServerConfiguration(this.mSelectedId);
        requireActivity().invalidateOptionsMenu();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onRenameConfiguration(String str) {
        if (this.mDatabaseHelper.serverConfigurationExists(str)) {
            Toast.makeText(getActivity(), R.string.server_name_already_taken, 1).show();
            return;
        }
        String name = this.mConfiguration.getName();
        this.mConfiguration.setName(str);
        try {
            Persister persister = new Persister(new Format(new HyphenStyle()));
            StringWriter stringWriter = new StringWriter();
            persister.write(this.mConfiguration, stringWriter);
            this.mDatabaseHelper.renameServerConfiguration(name, str, stringWriter.toString());
            refreshConfigurations();
        } catch (Exception e2) {
            Log.e(TAG, "Error while renaming configuration", e2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(SIS_LAST_CONFIGURATION, this.mLastConfiguration);
        bundle.putInt(SIS_CHANGES_COUNT, this.mChangesCount);
        this.mAdapter.onSaveInstanceState(bundle);
    }

    @Override // no.nordicsemi.android.mcp.server.ServerServiceAdapter.ConfigurationListener
    public void onServiceChanged(int i, Service.PredefinedService predefinedService, String str, UUID uuid, boolean z) {
        Service service;
        int itemCount = this.mAdapter.getItemCount() - 1;
        if (i < 0) {
            service = new Service();
            this.mConfiguration.addService(service);
        } else {
            int i2 = 0;
            Iterator<Service> it = this.mConfiguration.getServices().iterator();
            while (true) {
                if (!it.hasNext()) {
                    service = null;
                    break;
                }
                Service next = it.next();
                if (next.getInternalId() == i) {
                    itemCount = i2;
                    service = next;
                    break;
                }
                i2++;
            }
            if (service == null) {
                service = new Service();
                this.mConfiguration.addService(service);
            }
        }
        service.setConfiguration(predefinedService, uuid);
        service.setName(str);
        service.setEnabled(z);
        if (i < 0) {
            this.mAdapter.notifyItemInserted(itemCount);
        } else {
            this.mAdapter.notifyItemChanged(itemCount);
        }
        onConfigurationChanged();
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewAnimator.ExpandCollapseListener
    public void onViewCollapsed(int i) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        this.mServerDisabledView = view.findViewById(android.R.id.empty);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(android.R.id.list);
        this.mRecyclerView = recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new androidx.recyclerview.widget.g(view.getContext(), 1));
        androidx.recyclerview.widget.e eVar = new androidx.recyclerview.widget.e();
        eVar.a(500L);
        recyclerView.setItemAnimator(eVar);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ServerServiceAdapter serverServiceAdapter = new ServerServiceAdapter(this.mDatabaseHelper, this, bundle);
        this.mAdapter = serverServiceAdapter;
        recyclerView.setAdapter(serverServiceAdapter);
        new androidx.recyclerview.widget.j(new RemovableItemTouchHelperCallback(this.mAdapter)).a(recyclerView);
        setupConfigurations(this.mConfigurationsAdapter);
        if (bundle == null) {
            selectConfiguration(this.mConfigurationsAdapter.getItemPosition(this.mPreferences.getLong(PREFS_SERVER_CONFIGURATION, 0L)));
            this.mChangesCount = -100;
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewAnimator.ExpandCollapseListener
    public void onViewExpanded(int i) {
        this.mRecyclerView.smoothScrollToPosition(i);
    }

    public /* synthetic */ void a(ListView listView, DialogInterface dialogInterface, int i) {
        int checkedItemPosition = listView.getCheckedItemPosition();
        if (checkedItemPosition >= 0) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getResources().getStringArray(R.array.dfu_app_file_browser_action)[checkedItemPosition])));
        }
    }

    public /* synthetic */ void a(long j) {
        selectConfiguration(this.mConfigurationsAdapter.getItemPosition(j));
    }

    public /* synthetic */ void a(String str, View view) {
        d.a aVar = new d.a(requireContext());
        aVar.a(str);
        aVar.c(R.string.action_details);
        aVar.c(R.string.ok, null);
        aVar.c();
    }
}
