package no.nordicsemi.android.mcp.connection;

import a.k.a.a;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.d;
import androidx.fragment.app.Fragment;
import io.runtime.mcumgr.dfu.FirmwareUpgradeManager;
import java.util.Locale;
import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.log.LogContract;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.connection.DfuFragment;

/* loaded from: classes.dex */
public class DfuFragment extends Fragment implements a.InterfaceC0025a {
    static final String EXTRA_FILE_PATH = "no.nordicsemi.android.mcp.FILE_PATH";
    static final String EXTRA_FILE_URI = "no.nordicsemi.android.mcp.FILE_URI";
    private static final int LOAD_BIN_FILE_LOADER_REQ = 4;
    private static final int LOAD_FILE_LOADER_REQ = 2;
    private static final int LOAD_INIT_FILE_LOADER_REQ = 3;
    private static final int SELECT_BIN_FILE_REQ = 3;
    private static final int SELECT_FILE_REQ = 1;
    private static final int SELECT_INIT_FILE_REQ = 2;
    private static final String SIS_FILE_PATH = "file_path";
    private static final String SIS_FILE_STREAM = "file_stream";
    private static final String SIS_FILE_TYPE = "file_type";
    private static final String SIS_INIT_FILE_STREAM = "init_file_stream";
    protected IBluetoothLeConnection mConnection;
    private String mFilePath;
    private Uri mFileStreamUri;
    private int mFileType;
    private Uri mInitFileStreamUri;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: no.nordicsemi.android.mcp.connection.DfuFragment$1Holder, reason: invalid class name */
    /* loaded from: classes.dex */
    public class C1Holder {
        private FirmwareUpgradeManager.Mode mode = FirmwareUpgradeManager.Mode.TEST_AND_CONFIRM;

        C1Holder() {
        }
    }

    private void onDfuFileSelected(String str, final int i, final String str2, final Uri uri) {
        if (str2 == null && uri == null) {
            Toast.makeText(requireContext(), R.string.dfu_no_file, 0).show();
            return;
        }
        String upperCase = str.toUpperCase(Locale.US);
        if (!upperCase.endsWith(".HEX") && !upperCase.endsWith(".BIN") && !upperCase.endsWith(".ZIP")) {
            Toast.makeText(requireContext(), R.string.dfu_invalid_file, 0).show();
            return;
        }
        if (i != 0) {
            d.a aVar = new d.a(requireContext());
            aVar.c(R.string.dfu_file_init_title);
            aVar.b(R.string.dfu_file_init_message);
            aVar.b(R.string.cancel, (DialogInterface.OnClickListener) null);
            aVar.a(R.string.no, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.n
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    DfuFragment.this.a(i, str2, uri, dialogInterface, i2);
                }
            });
            aVar.c(R.string.yes, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.r
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    DfuFragment.this.a(dialogInterface, i2);
                }
            });
            aVar.c();
            return;
        }
        onStartDfuUpload(i, str2, uri, null, null);
    }

    private void onSelectFile(int i) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("application/*");
        intent.addCategory("android.intent.category.OPENABLE");
        if (intent.resolveActivity(requireContext().getPackageManager()) != null) {
            startActivityForResult(intent, i);
            return;
        }
        View inflate = requireActivity().getLayoutInflater().inflate(R.layout.app_file_browser, (ViewGroup) null);
        final ListView listView = (ListView) inflate.findViewById(android.R.id.list);
        listView.setAdapter((ListAdapter) new FileBrowserAppsAdapter(requireContext()));
        listView.setChoiceMode(1);
        listView.setItemChecked(0, true);
        d.a aVar = new d.a(requireContext());
        aVar.c(R.string.dfu_alert_no_filebrowser_title);
        aVar.b(inflate);
        aVar.a(R.string.no, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.m
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
            }
        });
        aVar.c(R.string.yes, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.o
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                DfuFragment.this.a(listView, dialogInterface, i2);
            }
        });
        aVar.c();
    }

    private void showSelectFileTypeDialog() {
        int i = this.mFileType;
        int i2 = 2;
        if (i != 0) {
            if (i == 1) {
                i2 = 1;
            } else if (i != 2) {
                if (i == 4) {
                    i2 = 3;
                }
            }
            d.a aVar = new d.a(requireContext());
            aVar.c(R.string.dfu_file_type_title);
            aVar.a(R.array.dfu_file_type, i2, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.q
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i3) {
                    DfuFragment.this.b(dialogInterface, i3);
                }
            });
            aVar.c(R.string.ok, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.k
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i3) {
                    DfuFragment.this.c(dialogInterface, i3);
                }
            });
            aVar.b(R.string.info, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.l
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i3) {
                    DfuFragment.this.d(dialogInterface, i3);
                }
            });
            aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
            aVar.c();
        }
        i2 = 0;
        d.a aVar2 = new d.a(requireContext());
        aVar2.c(R.string.dfu_file_type_title);
        aVar2.a(R.array.dfu_file_type, i2, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.q
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i3) {
                DfuFragment.this.b(dialogInterface, i3);
            }
        });
        aVar2.c(R.string.ok, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.k
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i3) {
                DfuFragment.this.c(dialogInterface, i3);
            }
        });
        aVar2.b(R.string.info, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.l
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i3) {
                DfuFragment.this.d(dialogInterface, i3);
            }
        });
        aVar2.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar2.c();
    }

    private void showUpgradeModeDialog() {
        final C1Holder c1Holder = new C1Holder();
        d.a aVar = new d.a(requireContext());
        aVar.c(R.string.mcumgr_mode_title);
        aVar.a(R.array.mcumgr_mode, 0, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.j
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DfuFragment.b(DfuFragment.C1Holder.this, dialogInterface, i);
            }
        });
        aVar.c(R.string.ok, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.p
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DfuFragment.this.a(c1Holder, dialogInterface, i);
            }
        });
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c();
    }

    public /* synthetic */ void a(ListView listView, DialogInterface dialogInterface, int i) {
        int checkedItemPosition = listView.getCheckedItemPosition();
        if (checkedItemPosition >= 0) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getResources().getStringArray(R.array.dfu_app_file_browser_action)[checkedItemPosition])));
        }
    }

    public /* synthetic */ void b(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            this.mFileType = 0;
            return;
        }
        if (i == 1) {
            this.mFileType = 1;
        } else if (i == 2) {
            this.mFileType = 2;
        } else {
            if (i != 3) {
                return;
            }
            this.mFileType = 4;
        }
    }

    public /* synthetic */ void c(DialogInterface dialogInterface, int i) {
        onSelectFile(1);
    }

    public /* synthetic */ void d(DialogInterface dialogInterface, int i) {
        new ZipInfoFragment().show(getChildFragmentManager(), (String) null);
    }

    protected boolean ensureNoMacro() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isBleEnabled() {
        BluetoothAdapter defaultAdapter;
        return (getActivity() == null || (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null || !defaultAdapter.isEnabled()) ? false : true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            if (i == 1) {
                this.mFilePath = null;
                this.mFileStreamUri = null;
                Uri data = intent.getData();
                if (data.getScheme().equals("file")) {
                    String path = data.getPath();
                    this.mFilePath = path;
                    onDfuFileSelected(path.substring(path.lastIndexOf(47)), this.mFileType, path, null);
                } else if (data.getScheme().equals(LogContract.Session.Content.CONTENT)) {
                    this.mFileStreamUri = data;
                    Bundle extras = intent.getExtras();
                    if (extras != null && extras.containsKey("android.intent.extra.STREAM")) {
                        this.mFileStreamUri = (Uri) extras.getParcelable("android.intent.extra.STREAM");
                    }
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(EXTRA_FILE_URI, data);
                    a.k.a.a.a(this).b(2, bundle, this);
                }
            } else if (i == 2) {
                Uri data2 = intent.getData();
                if (data2.getScheme().equals("file")) {
                    String path2 = data2.getPath();
                    if (!path2.toUpperCase(Locale.US).endsWith(".DAT")) {
                        Toast.makeText(requireContext(), R.string.dfu_invalid_init_file, 0).show();
                        return;
                    }
                    onStartDfuUpload(this.mFileType, this.mFilePath, this.mFileStreamUri, path2, null);
                } else if (data2.getScheme().equals(LogContract.Session.Content.CONTENT)) {
                    this.mInitFileStreamUri = data2;
                    Bundle extras2 = intent.getExtras();
                    if (extras2 != null && extras2.containsKey("android.intent.extra.STREAM")) {
                        this.mInitFileStreamUri = (Uri) extras2.getParcelable("android.intent.extra.STREAM");
                    }
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable(EXTRA_FILE_URI, data2);
                    a.k.a.a.a(this).b(3, bundle2, this);
                }
            } else if (i == 3) {
                this.mFilePath = null;
                this.mFileStreamUri = null;
                Uri data3 = intent.getData();
                if (data3.getScheme().equals("file")) {
                    this.mFilePath = data3.getPath();
                    showUpgradeModeDialog();
                } else if (data3.getScheme().equals(LogContract.Session.Content.CONTENT)) {
                    this.mFileStreamUri = data3;
                    Bundle extras3 = intent.getExtras();
                    if (extras3 != null && extras3.containsKey("android.intent.extra.STREAM")) {
                        this.mFileStreamUri = (Uri) extras3.getParcelable("android.intent.extra.STREAM");
                    }
                    Bundle bundle3 = new Bundle();
                    bundle3.putParcelable(EXTRA_FILE_URI, data3);
                    a.k.a.a.a(this).b(4, bundle3, this);
                }
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFileType = 0;
        if (bundle != null) {
            this.mFilePath = bundle.getString(SIS_FILE_PATH);
            this.mFileStreamUri = (Uri) bundle.getParcelable(SIS_FILE_STREAM);
            this.mInitFileStreamUri = (Uri) bundle.getParcelable(SIS_INIT_FILE_STREAM);
            this.mFileType = bundle.getInt(SIS_FILE_TYPE);
        }
    }

    public a.k.b.c onCreateLoader(int i, Bundle bundle) {
        if (i != 2 && i != 3 && i != 4) {
            throw new UnsupportedOperationException("Wrong loader ID: " + i);
        }
        return new a.k.b.b(requireContext(), (Uri) bundle.getParcelable(EXTRA_FILE_URI), null, null, null, null);
    }

    public void onLoadFinished(a.k.b.c cVar, Object obj) {
        int id = cVar.getId();
        if (id == 2 || id == 3 || id == 4) {
            Cursor cursor = (Cursor) obj;
            if (cursor.moveToNext()) {
                int columnIndex = cursor.getColumnIndex("_display_name");
                if (columnIndex == -1) {
                    Toast.makeText(requireContext(), R.string.error_loading_file_failed, 0).show();
                    return;
                }
                String string = cursor.getString(columnIndex);
                if (string == null) {
                    Toast.makeText(requireContext(), R.string.error_loading_file_failed, 0).show();
                    return;
                }
                int columnIndex2 = cursor.getColumnIndex("_data");
                String string2 = columnIndex2 != -1 ? cursor.getString(columnIndex2) : null;
                int id2 = cVar.getId();
                if (id2 == 2) {
                    onDfuFileSelected(string, this.mFileType, string2, this.mFileStreamUri);
                    return;
                }
                if (id2 == 3) {
                    if (!string.toUpperCase(Locale.US).endsWith(".DAT")) {
                        Toast.makeText(requireContext(), R.string.dfu_invalid_init_file, 0).show();
                        return;
                    } else {
                        onStartDfuUpload(this.mFileType, this.mFilePath, this.mFileStreamUri, string2, this.mInitFileStreamUri);
                        return;
                    }
                }
                if (id2 != 4) {
                    return;
                }
                if (!string.toUpperCase(Locale.US).endsWith(".BIN")) {
                    Toast.makeText(requireContext(), R.string.mcumgr_invalid_bin_file, 0).show();
                } else {
                    showUpgradeModeDialog();
                }
            }
        }
    }

    public void onLoaderReset(a.k.b.c cVar) {
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_dfu) {
            if (!ensureNoMacro()) {
                return true;
            }
            if (this.mConnection.hasDfuService()) {
                showSelectFileTypeDialog();
            } else if (this.mConnection.isMcuMgr()) {
                onSelectFile(3);
            }
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        boolean z = this.mConnection != null;
        boolean z2 = z && this.mConnection.isDfuInProgress();
        boolean z3 = z && this.mConnection.isConnected();
        boolean z4 = z && (this.mConnection.hasDfuService() || this.mConnection.isMcuMgr());
        MenuItem findItem = menu.findItem(R.id.action_dfu);
        if (findItem != null) {
            findItem.setVisible(z3 && !z2 && z4);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(SIS_FILE_PATH, this.mFilePath);
        bundle.putParcelable(SIS_FILE_STREAM, this.mFileStreamUri);
        bundle.putParcelable(SIS_INIT_FILE_STREAM, this.mInitFileStreamUri);
        bundle.putInt(SIS_FILE_TYPE, this.mFileType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onServiceConnected(IBluetoothLeConnection iBluetoothLeConnection) {
        this.mConnection = iBluetoothLeConnection;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onServicesChanged() {
    }

    void onStartDfuUpload(int i, String str, Uri uri, String str2, Uri uri2) {
    }

    void onStartMcuMgrImageUpload(String str, Uri uri, FirmwareUpgradeManager.Mode mode) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(C1Holder c1Holder, DialogInterface dialogInterface, int i) {
        if (i == 0) {
            c1Holder.mode = FirmwareUpgradeManager.Mode.TEST_AND_CONFIRM;
        } else if (i == 1) {
            c1Holder.mode = FirmwareUpgradeManager.Mode.TEST_ONLY;
        } else {
            if (i != 2) {
                return;
            }
            c1Holder.mode = FirmwareUpgradeManager.Mode.CONFIRM_ONLY;
        }
    }

    public /* synthetic */ void a(int i, String str, Uri uri, DialogInterface dialogInterface, int i2) {
        onStartDfuUpload(i, str, uri, null, null);
    }

    public /* synthetic */ void a(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType(DfuBaseService.MIME_TYPE_OCTET_STREAM);
        intent.addCategory("android.intent.category.OPENABLE");
        startActivityForResult(intent, 2);
    }

    public /* synthetic */ void a(C1Holder c1Holder, DialogInterface dialogInterface, int i) {
        onStartMcuMgrImageUpload(this.mFilePath, this.mFileStreamUri, c1Holder.mode);
    }
}
